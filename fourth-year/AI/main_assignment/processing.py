if __name__ == '__main__':

    file = open('f2_l-d_kp_20_878.txt', 'r')
    linesfile = file.readlines()
    resulttoken0 = []
    resulttoken1 = []
    for x in linesfile:
        resulttoken0.append(int(x.split()[0]))
        resulttoken1.append(int(x.split()[1]))
        file.close()
    print('column  0 ( values ): ' + str(resulttoken0))
    print('column  1 ( weights ): ' + str(resulttoken1))
