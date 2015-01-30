import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

    // Do NOT add any variables (instance or static)

    /**
     * Builds a frequency map of characters for the given string.
     *
     * This should just be the count of each character.
     * No character not in the input string should be in the frequency map.
     *
     * @param s the string to map
     * @return the character -> Integer frequency map
     */
    public static Map<Character, Integer> buildFrequencyMap(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            Integer charCount = map.get(s.charAt(i));
            int count = 1;
            if (charCount != null) {
                count = charCount + 1;
            }
            map.put(s.charAt(i), count);
        }
        return map;
    }


    /**
     * Build the Huffman tree using the frequencies given.
     *
     * Add the nodes to the tree based on their natural ordering (the order
     * given by the compareTo method).
     * The frequency map will not necessarily come from the
     * {@code buildFrequencyMap()} method. Every entry in the map should be in
     * the tree.
     *
     * @param freq the frequency map to represent
     * @return the root of the Huffman Tree
     */
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freq) {
        if (freq == null) {
            throw new IllegalArgumentException();
        }
        PriorityQueue<HuffmanNode> huff = new PriorityQueue<HuffmanNode>();
        for (Character c : freq.keySet()) {
            huff.add(new HuffmanNode(c, freq.get(c)));
        }
        while (huff.size() > 1) {
            HuffmanNode less = huff.poll();
            HuffmanNode more = huff.poll();
            HuffmanNode parent = new HuffmanNode(less, more);
            huff.add(parent);
        }
        return huff.poll();
    }

    /**
     * Traverse the tree and extract the encoding for each character in the
     * tree.
     *
     * The tree provided will be a valid huffman tree but may not come from the
     * {@code buildHuffmanTree()} method.
     *
     * @param tree the root of the Huffman Tree
     * @return the map of each character to the encoding string it represents
     */
    public static Map<Character, EncodedString> buildEncodingMap(
            HuffmanNode tree) {
        if (tree == null) {
            throw new IllegalArgumentException();
        }
        HashMap<Character, EncodedString> map =
                new HashMap<Character, EncodedString>();
        EncodedString code = new EncodedString();
        if (tree.getLeft() == null && tree.getRight() == null) {
            code.one();
        }
        encodeHelp(tree, code, map);
        return map;
    }

    /**
     * Encode each character in the string using the map provided.
     *
     * If a character in the string doesn't exist in the map ignore it.
     *
     * The encoding map may not necessarily come from the
     * {@code buildEncodingMap()} method, but will be correct for the tree given
     * to {@code decode()} when decoding this method's output.
     *
     * @param encodingMap the map of each character to the encoding string it
     * represents
     * @param s the string to encode
     * @return the encoded string
     */
    public static EncodedString encode(Map<Character, EncodedString>
            encodingMap, String s) {
        if (s == null || s.equals("") || encodingMap == null) {
            throw new IllegalArgumentException();
        }
        EncodedString code = new EncodedString();
        for (int i = 0; i < s.length(); i++) {
            EncodedString curr = encodingMap.get(s.charAt(i));
            if (curr != null) {
                code.concat(curr);
            }
        }
        return code;
    }


    /**
     * Decode the encoded string using the tree provided.
     *
     * The encoded string may not necessarily come from {@code encode()}, but
     * will be a valid string for the given tree.
     *
     * (tip: use StringBuilder to make this method faster -- concatenating
     * strings is SLOW.)
     *
     * @param tree the tree to use to decode the string
     * @param es the encoded string
     * @return the decoded string
     */
    public static String decode(HuffmanNode tree, EncodedString es) {
        if (es == null || tree == null) {
            throw new IllegalArgumentException();
        }
        HuffmanNode node = tree;
        boolean canAdd = true;
        StringBuilder str = new StringBuilder();
        Iterator<Byte> it = es.iterator();
        while (it.hasNext()) {
            Byte b = it.next();
            if (node != null) {
                if (b == (byte) 0) {
                    if (node.getLeft() == null) {
                        str.append(node.getCharacter());
                        canAdd = false;
                    } else {
                        node = node.getLeft();
                    }
                } else if (b == (byte) 1) {
                    if (node.getRight() == null) {
                        str.append(node.getCharacter());
                        canAdd = false;
                    } else {
                        node = node.getRight();
                    }
                }
            }
            if (node != null && canAdd
                    && node.getLeft() == null && node.getRight() == null) {
                str.append(node.getCharacter());
                node = tree;
            }

        }
        return str.toString();
    }


    /**
     * Recursively goes through the whole tree, finding the EncodedString
     * for each character.
     * @param node the current node at instance of traversal
     * @param code EncodedString
     * @param map of characters and their corresponding EncodedStrings from tree
     */
    private static void encodeHelp(HuffmanNode node,
            EncodedString code, Map<Character, EncodedString> map) {
        if (node.getLeft() == null && node.getRight() == null) {
            map.put(node.getCharacter(), code);
        }
        if (node.getLeft() != null) {
            EncodedString curr = new EncodedString();
            curr.concat(code);
            curr.zero();
            encodeHelp(node.getLeft(), curr, map);
        }
        if (node.getRight() != null) {
            EncodedString curr = new EncodedString();
            curr.concat(code);
            curr.one();
            encodeHelp(node.getRight(), curr, map);
        }
    }

}
