import unittest
import jpeg
import numpy as np


class MyTestCase(unittest.TestCase):
    def testMatrixMultiplication(self):
        A = [[5, 7, 9, 10],
             [2, 3, 3, 8],
             [8, 10, 2, 3],
             [3, 3, 4, 8]]
        B = [[3, 10, 12, 18],
             [12, 1, 4, 9],
             [9, 10, 12, 2],
             [3, 12, 4, 10]]
        expected = [
            [210, 267, 236, 271],
            [93, 149, 104, 149],
            [171, 146, 172, 268],
            [105, 169, 128, 169]
        ]
        output = jpeg.matrixMultiplication(A, B, 4)
        equal = True
        for i in range(4):
            for j in range(4):
                if not output[i, j] == expected[i][j]:
                    equal = False
                    break
        self.assertTrue(equal)

    def testMatrixTranspose(self):
        A = [[5, 7, 9, 10],
             [2, 3, 3, 8],
             [8, 10, 2, 3],
             [3, 3, 4, 8]]
        expected = [
            [5, 2, 8, 3],
            [7, 3, 10, 3],
            [9, 3, 2, 4],
            [10, 8, 3, 8]
        ]
        output = jpeg.transposed(np.asarray(A), 4)
        equal = True
        for i in range(4):
            for j in range(4):
                if not output[i, j] == expected[i][j]:
                    equal = False
                    break
        self.assertTrue(equal)

    def testDCT(self):
        input = [[139, 144, 149, 153, 155, 155, 155, 155],
                 [144, 151, 153, 156, 159, 156, 156, 156],
                 [150, 155, 160, 163, 158, 156, 156, 159],
                 [159, 161, 162, 160, 160, 159, 159, 155],
                 [159, 160, 161, 162, 162, 155, 155, 157],
                 [161, 161, 161, 161, 160, 157, 157, 157],
                 [162, 162, 161, 163, 162, 157, 157, 157],
                 [162, 162, 161, 161, 163, 158, 158, 158]]
        # DCT matrix
        ar = np.array([range(8)])
        T = np.array(0.5 * np.cos(ar.T * (2 * ar + 1) * np.pi / 16))
        T[0, :] = np.sqrt(1 / 8)
        # compute
        im = np.asarray(input)
        sz = im.shape
        output = np.zeros(sz)
        transformed = jpeg.matrixMultiplication(T, jpeg.matrixMultiplication(im, jpeg.transposed(T, 8), 8), 8)
        # join 8x8 encoded blocks to image of original size
        for i in range(8):
            for j in range(8):
                output[i, j] = round(transformed[i, j])
        expected = [
            [235, -1, -12, -5, 2, -2, -3, 1],
            [-22, -17, -6, -3, -3, 0, 0, -1],
            [-11, -10, -2, 1, 0, -1, -1, 0],
            [-7, -2, 0, 1, 1, 0, 0, 0],
            [-1, -1, 0, 1, 0, -1, 1, 1],
            [2, 0, 2, 0, -1, 1, 1, -1],
            [-1, 0, 0, -1, 0, 2, 1, -1],
            [-3, 2, -4, -2, 2, 1, -1, 0]
        ]
        print("DCT matrix: ", T)
        print("Expected: ", expected)
        print("Output:", output)
        equal = True
        for i in range(8):
            for j in range(8):
                if not output[i, j] == expected[i][j]:
                    equal = False
                    break
        self.assertTrue(equal)

    def testQuantization(self):
        input = [
            [235, -1, -12, -5, 2, -2, -3, 1],
            [-22, -17, -6, -3, -3, 0, 0, -1],
            [-11, -10, -2, 1, 0, -1, -1, 0],
            [-7, -2, 0, 1, 1, 0, 0, 0],
            [-1, -1, 0, 1, 0, -1, 1, 1],
            [2, 0, 2, 0, -1, 1, 1, -1],
            [-1, 0, 0, -1, 0, 2, 1, -1],
            [-3, 2, -4, -2, 2, 1, -1, 0]
        ]
        q = [[16, 11, 10, 16, 24, 40, 51, 61],
             [12, 12, 14, 19, 26, 58, 60, 55],
             [14, 13, 16, 24, 40, 57, 69, 56],
             [14, 17, 22, 29, 51, 87, 80, 62],
             [18, 22, 37, 56, 68, 109, 103, 77],
             [24, 35, 55, 64, 81, 104, 113, 92],
             [49, 64, 78, 87, 103, 121, 120, 101],
             [72, 92, 95, 98, 112, 100, 103, 99]]

        im = np.asarray(input)
        sz = im.shape
        height = sz[0]
        width = sz[1]

        output = np.zeros(sz)
        # for each block:
        encoded = jpeg.divideElementwise(im, q, 8)
        for i in range(8):
            for j in range(8):
                output[i, j] = round(encoded[i, j])
        expected = [
            [15, 0, -1, 0, 0, 0, 0, 0],
            [-2, -1, 0, 0, 0, 0, 0, 0],
            [-1, -1, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0]
        ]
        print("Expected: ", expected)
        print("Output:", output)
        equal = True
        for i in range(8):
            for j in range(8):
                if not output[i, j] == expected[i][j]:
                    equal = False
                    break
        self.assertTrue(equal)

    def testDCTandQuantization(self):
        input = [[139, 144, 149, 153, 155, 155, 155, 155],
                 [144, 151, 153, 156, 159, 156, 156, 156],
                 [150, 155, 160, 163, 158, 156, 156, 159],
                 [159, 161, 162, 160, 160, 159, 159, 155],
                 [159, 160, 161, 162, 162, 155, 155, 157],
                 [161, 161, 161, 161, 160, 157, 157, 157],
                 [162, 162, 161, 163, 162, 157, 157, 157],
                 [162, 162, 161, 161, 163, 158, 158, 158]]
        # DCT matrix
        ar = np.array([range(8)])
        T = np.array(0.5 * np.cos(ar.T * (2 * ar + 1) * np.pi / 16))
        T[0, :] = np.sqrt(1 / 8)
        q = [[16, 11, 10, 16, 24, 40, 51, 61],
             [12, 12, 14, 19, 26, 58, 60, 55],
             [14, 13, 16, 24, 40, 57, 69, 56],
             [14, 17, 22, 29, 51, 87, 80, 62],
             [18, 22, 37, 56, 68, 109, 103, 77],
             [24, 35, 55, 64, 81, 104, 113, 92],
             [49, 64, 78, 87, 103, 121, 120, 101],
             [72, 92, 95, 98, 112, 100, 103, 99]]

        im = np.asarray(input)
        sz = im.shape
        height = sz[0]
        width = sz[1]

        output = np.zeros(sz)
        # for each block:
        for b in range(0, height, 8):
            for a in range(0, width, 8):
                # transform using DCT
                block = im[b:b + 8, a:a + 8]
                transformed = jpeg.doDCT(block, T, 8)
                # divide element-wise by quantization matrix and round to integers
                encoded = jpeg.divideElementwise(transformed, q, 8)
                # join 8x8 encoded blocks to image of original size
                for i in range(8):
                    for j in range(8):
                        output[b + i, a + j] = round(encoded[i, j])
        expected = [
            [15, 0, -1, 0, 0, 0, 0, 0],
            [-2, -1, 0, 0, 0, 0, 0, 0],
            [-1, -1, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0]
        ]
        print("Expected: ", expected)
        print("Output:", output)
        equal = True
        for i in range(8):
            for j in range(8):
                if not output[i, j] == expected[i][j]:
                    equal = False
                    break
        self.assertTrue(equal)


if __name__ == '__main__':
    unittest.main()
