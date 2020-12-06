from scipy.io import wavfile
import numpy as np
from numpy.fft import fft, ifft, fftfreq
import matplotlib.pyplot as plt


def my_fft(inputSignal, nDeleteFreq, fftLength):
    if nDeleteFreq >= fftLength:
        print("Number of frequencies to be deleted must be lower than size of block.")
        return []

    outputSignal = []

    # divide 'inputSignal' into blocks of length 'fftLength'
    # if len isn't multiple of fftLength, pad input signal with zeros
    blockCount = int(np.ceil(len(inputSignal) / fftLength))

    # erase 'nDeleteFreq' frequencies with the lowest amplitude from each block
    # use built-in fft and ifft functions
    for idx in range(blockCount):
        # goes from start to stop-1
        start = idx * fftLength
        end = (idx + 1) * fftLength
        block = inputSignal[start:end]
        realLength = len(block)

        # FFT of block
        fftBlock = fft(block)

        # amplitudes
        absBlock = list(np.absolute(fftBlock))
        sortedAbsBlock = sorted(absBlock)
        # get threshold amplitude (this threshold amplitude will be preserved)
        if (realLength < fftLength):
            if (nDeleteFreq >= realLength):
                # if the last block has smaller length and we should delete more than length frequencies, delete them all
                outputSignal = outputSignal + ([0] * realLength)
                continue
        thr = sortedAbsBlock[nDeleteFreq]

        # TODO problem: if there are more coefficients with the same amplitude, wrong number of frequencies will be erased
        # erase respective coefficients
        if absBlock[0] < thr:
            fftBlock[0] = 0
        for i in range(1, realLength//2):
            if absBlock[i] < thr:
                fftBlock[i] = 0
                fftBlock[realLength - i] = 0
        # compute IFFT and add this block to output sequence
        ifftBlock = ifft(fftBlock)
        outputSignal = outputSignal + [int(a.real) for a in ifftBlock]

    return outputSignal


# Reads .wav file to a list of values
def readWav(path):
    # read rate and data from a .wav file defined by 'path'
    rate, signal = wavfile.read(path)
    return rate, signal.tolist()


# Writes a list of values to a .wav file
def writeWav(path, rate, data):
    # read rate and data from a .wav file defined by 'path'
    wavfile.write(path, rate, np.asarray(data, dtype=np.int16))


def main():
    filename = 'itu_female1'
    rate, signal = readWav(filename + '.wav')
    print('Length of signal:', len(signal))

    # just to check if writing to wav works
    writeWav(filename + '-same.wav', rate, signal)

    plt.plot(signal)
    plt.ylabel("Amplitude")
    plt.xlabel("Time")
    plt.title("Wave before processing")
    plt.show()

    output = my_fft(signal, 600, 1024)
    writeWav(filename + '-modified.wav', rate, output)

    plt.plot(output)
    plt.ylabel("Amplitude")
    plt.xlabel("Time")
    plt.title("Wave after processing")
    plt.show()


if __name__ == "__main__":
    main()
