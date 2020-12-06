# Diese Funktion liest eine Sequenz von Zeichen und codiert diese nach dem LZW Verfahren
#
# Input:        input:          Zeichensequenz
#
# Output:       output:         codierte Zeichensequenz
#               encodeDict:     Wörterbuch
def lzwEncode(input, list_from_string):
    # initialize output
    output = []

    # initialize dictionary
    encode_dict = {}
    dict_idx = 0
    for c in list_from_string:
        encode_dict[c] = dict_idx
        dict_idx += 1

    # encode + build dictionary
    input_idx = 0
    s = input[0]
    while input_idx < len(input) - 1:
        c = input[input_idx + 1]
        if s + c in encode_dict:
            s = s + c
            input_idx += 1
        else:
            output.append(encode_dict[s])
            encode_dict[s + c] = dict_idx
            dict_idx += 1
            s = c
            input_idx += 1
    # don't forget to output the last sequence of letters
    output.append((encode_dict[s]))

    return output, encode_dict


# Diese Funktion decodiert eine LZW-codierte Zeichensequenz
#
# Input:        input:          codierte Zeichensequenz
#
# Output:       output:         decodierte Zeichensequenz
#               decodeDict:     Wörterbuch

def lzwDecode(input, list_from_string):
    output = ""

    decode_dict = {}
    dict_idx = 0
    for c in list_from_string:
        # dictionary with NUMBERS AS KEYS AND LETTER AS VALUES: keys and values switched from encoding function
        decode_dict[dict_idx] = c
        dict_idx += 1

    input_idx = 0
    i = input[0]
    output += decode_dict[i]
    while input_idx < len(input) - 1:
        j = input[input_idx + 1]

        if j in decode_dict:
            decode_dict[dict_idx] = decode_dict[i] + decode_dict[j][:1]
            output += decode_dict[j]

        else:
            decode_dict[dict_idx] = decode_dict[i] + decode_dict[i][:1]
            output += decode_dict[i] + decode_dict[i][:1]

        dict_idx += 1
        input_idx += 1
        i = j

    return output, decode_dict


# for testing
def check_comp_and_decom_equals(string_to_encode, decompressed):
    if string_to_encode == decompressed:
        print("TEST: SUCCESS\n")
    else:
        print("TEST: FAIL\n")


def main():
    string_to_encode = 'AAAAAAAAAA'#'/WED/WE/WEE/WEB/WET'

    # added so we don't have to hard-code the input alphabet
    list_from_string = list(set(string_to_encode))

    print(string_to_encode)
    compressed, enDict = lzwEncode(string_to_encode, list_from_string)
    print(compressed)
    print(enDict)
    decompressed, deDict = lzwDecode(compressed, list_from_string)
    print(decompressed)

    check_comp_and_decom_equals(string_to_encode, decompressed)


if __name__ == "__main__":
    main()
