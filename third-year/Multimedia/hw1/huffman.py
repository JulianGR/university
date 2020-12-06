from node import Node
from math import log2
import heapq
from collections import Counter
import struct
import array


# Diese Funktion liest eine Textdatei im ASCII-Format
# und berechnet die relative Häufigkeit der einzelnen
# Zeichen.
#
# Input:        fname       :   Dateiname
#
# Output:       probs       :   Vektor mit den relativen Häufigkeiten
#               characters  :   Vektor mit den aufgetretenen Zeichen
#
# nützliche Tools, Collections, Befehle: open, close, read, replace, sort, Counter, numpy, matplotlib
# HINWEIS: Datenstrukturen des Skripts können abgeändert werden
#          (z.B. statt Listen Dictionarys oder Counter Objekte verwenden)
def get_freq_dic(fname):
    with open(fname) as f:
        c = Counter()
        for line in f:
            c += Counter(line)

    characters = list(c.keys())
    total_characters = sum(c.values())
    probs = [x / total_characters for x in list(c.values())]
    return probs, characters


# Huffman - Codierung
#
# Input:    probs           :   Auftrittswahrscheinlichkeiten
#
# Output:   code            :   Code Tabelle
#           entropy         :   Entropie
#           meanLength      :   mittlere Codewortlänge
#
# Für den Testvektor
# P = [0.05, 0.03, 0.17, 0.23, 0.01, 0.32, 0.19]
# A = ['A', 'B', 'C', 'D', 'E', 'F', 'G']
# ergibt sich entropy = 2.3378 und meanLength = 2.39.
#
# Need 'characters' to build code dictionary (key = character, value = code)
def huffman(probs, characters):
    # check if probs contains any values to work with
    if len(probs) == 0:
        print("no probablities!")
        return None, None, None

    # check if number of probabilities matches number of characters
    if len(probs) != len(characters):
        print("probs and characters are of different lengths!")
        return None, None, None

    # outputs
    code = {}
    entropy = 0
    mean_length = 0

    # create Huffman code tree
    code_tree = construct_huffman_code_tree(probs)

    # build code table
    n = len(probs)
    ids_and_codes = get_codes(code_tree, "")
    list.sort(ids_and_codes)
    for j in range(n):
        code[characters[j]] = ids_and_codes[j][1]

    # compute entropy
    for p in probs:
        entropy -= p * log2(p)

    # compute mean length
    for j in range(n):
        mean_length += probs[j] * len(code[characters[j]])

    return code, entropy, mean_length


# Constructs Huffman code tree from a list of probabilities.
# Returns root of the tree (of type Node).
def construct_huffman_code_tree(probs):
    # initialize min heap with symbol trees
    forest = []
    node_id = 0
    for p in probs:
        forest.append((p, node_id, Node(p, node_id)))
        node_id += 1
    heapq.heapify(forest)

    # construct code tree according to Huffman rules
    while len(forest) >= 2:
        min1 = heapq.heappop(forest)
        min2 = heapq.heappop(forest)
        new_node = Node(min1[0] + min2[0], node_id)
        new_node.set_left(min1[2])
        new_node.set_right(min2[2])
        heapq.heappush(forest, (new_node.value, node_id, new_node))
        node_id += 1

    return forest[0][2]


# Computes code words from a binary code tree.
# Returns a list of tuples (node ID, code word).
# Code words are represented as strings containing zeros and ones.
def get_codes(node, code):
    if node.is_leaf():
        return [(node.id, code)]
    left_codes = []
    right_codes = []
    if node.left is not None:
        left_codes = get_codes(node.left, code + "0")
    if node.right is not None:
        right_codes = get_codes(node.right, code + "1")
    return left_codes + right_codes


# Encodes text from file 'fname' using Huffman code with code table 'code'.
# Result is stored in a binary file 'dest'.
def encode_text(fname, dest, code):
    # compute Huffman coding of the text (as string)
    with open(fname) as f:
        str_result = ""
        for line in f:
            for c in line:
                str_result += code[c]

    # divide the code to bytes (binary form)
    data = array.array('B')
    for i in range(0, len(str_result), 8):
        next_byte = str_result[i:i + 8]
        data.append(int(next_byte, 2))

    # write bytes to binary file
    with open(dest, "wb") as f:
        data.tofile(f)


def main():
    file = "./midsummer.txt"
    probs, characters = get_freq_dic(file)

    code, entropy, mean_length = huffman(probs, characters)
    print("Entropy:", entropy)
    print("Mean length:", mean_length)
    print("Code table:", code)

    encode_text(file, "./res.bin", code)

if __name__ == '__main__':
    main()
