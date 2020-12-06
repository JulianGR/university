import math
import numpy as np
import imageio
from scipy.fftpack import idct
from PIL import Image


# This function demonstrates the jpeg encoding
#
#   input  :   original image
#   output :   matrix with quantised DCT values
def jpegEncode(input):
    # DCT matrix
    ar = np.array([range(8)])
    T = np.array(0.5 * np.cos(ar.T * (2 * ar + 1) * np.pi / 16))
    T[0, :] = np.sqrt(1 / 8)

    # Luminance quantization matrix
    q = [[16, 11, 10, 16, 24, 40, 51, 61],
         [12, 12, 14, 19, 26, 58, 60, 55],
         [14, 13, 16, 24, 40, 57, 69, 56],
         [14, 17, 22, 29, 51, 87, 80, 62],
         [18, 22, 37, 56, 68, 109, 103, 77],
         [24, 35, 55, 64, 81, 104, 113, 92],
         [49, 64, 78, 87, 103, 121, 120, 101],
         [72, 92, 95, 98, 112, 100, 103, 99]]

    # read image with name 'input'
    img = imageio.imread(input)

    # decompose into 8x8 blocks
    # need to solve 'end-blocks' with different size ???
    im = np.asarray(img)
    sz = im.shape
    height = sz[0]
    width = sz[1]

    output = np.zeros(sz)
    # for each block:
    for b in range(0, height, 8):
        for a in range(0, width, 8):
            # transform using DCT
            block = im[b:b + 8, a:a + 8]
            transformed = doDCT(block, T, 8)
            # divide element-wise by quantization matrix and round to integers
            encoded = divideElementwise(transformed, q, 8)
            # join 8x8 encoded blocks to image of original size
            # output[b:b+8, a:a+8] = encoded
            for i in range(8):
                for j in range(8):
                    output[b + i, a + j] = round(encoded[i, j])
    return output


# This function demonstrates the jpeg decoding
#
#   input  :   matrix with quantised DCT values
#   output :   reconstructed image
def jpegDecode(input):
    # DCT matrix
    ar = np.array([range(8)])
    T = np.array(0.5 * np.cos(ar.T * (2 * ar + 1) * np.pi / 16))
    T[0, :] = np.sqrt(1 / 8)  # this line was missing in the original file, but is in encoding function

    # Luminance quantization matrix
    q = [[16, 11, 10, 16, 24, 40, 51, 61],
         [12, 12, 14, 19, 26, 58, 60, 55],
         [14, 13, 16, 24, 40, 57, 69, 56],
         [14, 17, 22, 29, 51, 87, 80, 62],
         [18, 22, 37, 56, 68, 109, 103, 77],
         [24, 35, 55, 64, 81, 104, 113, 92],
         [49, 64, 78, 87, 103, 121, 120, 101],
         [72, 92, 95, 98, 112, 100, 103, 99]]

    sz = input.shape
    height = sz[0]
    width = sz[1]

    output = np.zeros(sz)
    for b in range(0, height, 8):
        for a in range(0, width, 8):
            block = input[b:b + 8, a:a + 8]
            transformed = multiplyElementwise(block, q, 8) #pixel = pixel * q[i]
            decoded = doInverseDCT(transformed, T, 8)  # idct(value, norm='ortho')
            for i in range(8):
                for j in range(8):
                    output[b + i, a + j] = decoded[i, j]

    return output


def multiplyElementwise(arr1, arr2, N):
    res = np.zeros((N, N))
    for i in range(N):
        for j in range(N):
            res[i][j] = arr1[i][j] * arr2[i][j]
    return res


def divideElementwise(arr1, arr2, N):
    res = np.zeros((N, N))
    for i in range(N):
        for j in range(N):
            res[i][j] = arr1[i][j] / arr2[i][j]
    return res


def doDCT(img, dctMatrix, N):
    return matrixMultiplication(dctMatrix, matrixMultiplication(img, transposed(dctMatrix, N), N), N)


def doInverseDCT(img, dctMatrix, N):
    return matrixMultiplication(transposed(dctMatrix, N), matrixMultiplication(img, dctMatrix, N), N)


def matrixMultiplication(A, B, N):
    res = np.zeros((N, N))
    for i in range(N):
        for j in range(N):
            tmp = 0.0
            for k in range(N):
                    tmp = tmp + (A[i][k] * B[k][j])
            res[i, j] = tmp
    return res


def transposed(A, N):
    res = np.zeros((N, N))
    for i in range(N):
        for j in range(N):
            res[i, j] = A[j, i]
    return res


def main():
    input = "baboon.jpg"
    encoded = jpegEncode(input)
    decoded = jpegDecode(encoded)
    imageio.imwrite('outfile.jpg', decoded)


if __name__ == "__main__":
    main()
