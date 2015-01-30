import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class BasicTests {
    private String studentString;
    private HashMap<Character, Integer> studentMap;
    private HuffmanNode studentTree;
    private HashMap<Character, EncodedString> studentEncodedMap;
    private EncodedString studentEncodedString;

    @Before
    public void stringsToTest() {
        studentString = "hello";
    }

    public void makeStudentMap() {
        studentMap = new HashMap<Character, Integer>();
        studentMap.put('h', 1);
        studentMap.put('e', 1);
        studentMap.put('l', 2);
        studentMap.put('o', 1);
    }

    public void makeStudentTree() {
        HuffmanNode h = new HuffmanNode('h', 1);
        HuffmanNode e = new HuffmanNode('e', 1);

        HuffmanNode eh = new HuffmanNode(e, h);

        HuffmanNode o = new HuffmanNode('o', 1);

        HuffmanNode oeh = new HuffmanNode(o, eh);

        HuffmanNode l = new HuffmanNode('l', 2);

        HuffmanNode loeh = new HuffmanNode(l, oeh);

        studentTree = loeh;
    }

    public void makeStudentEncodedString() {
        EncodedString h = new EncodedString();
        h.one();
        h.one();
        h.one();
        EncodedString e = new EncodedString();
        e.one();
        e.one();
        e.zero();
        EncodedString l = new EncodedString();
        l.zero();
        EncodedString o = new EncodedString();
        o.one();
        o.zero();

        studentEncodedMap = new HashMap<Character, EncodedString>();
        studentEncodedMap.put('h', h);
        studentEncodedMap.put('e', e);
        studentEncodedMap.put('l', l);
        studentEncodedMap.put('o', o);

        studentEncodedString = new EncodedString();
        studentEncodedString.concat(h);
        studentEncodedString.concat(e);
        studentEncodedString.concat(l);
        studentEncodedString.concat(l);
        studentEncodedString.concat(o);
    }

    @Test
    public void studentStringBuildFrequencyMap() {
        makeStudentMap();
        assertEquals(studentMap, Huffman.buildFrequencyMap(studentString));
    }

    @Test
    public void studentStringBuildHuffmanTree() {
        makeStudentTree();
        makeStudentMap();
        HuffmanNode theOtherTree = Huffman.buildHuffmanTree(studentMap);
        assertEquals(studentTree, theOtherTree);
        assertEquals(studentTree.getLeft(), theOtherTree.getLeft());
        assertEquals(studentTree.getRight(), theOtherTree.getRight());
        assertEquals(studentTree.getRight().getLeft(), theOtherTree.getRight()
                .getLeft());
        assertEquals(studentTree.getRight().getRight(), theOtherTree.getRight()
                .getRight());
        assertEquals(studentTree.getRight().getRight().getLeft(), theOtherTree
                .getRight().getRight().getLeft());
        assertEquals(studentTree.getRight().getRight().getRight(), theOtherTree
                .getRight().getRight().getRight());

    }

    @Test
    public void studentStringBuildEncode() {
        makeStudentTree();
        makeStudentEncodedString();
        Map<Character, EncodedString> theStudentEncodedString = Huffman
            .buildEncodingMap(studentTree);
        assertEquals(studentEncodedMap.keySet(),
                theStudentEncodedString.keySet());
        for (Entry<Character, EncodedString> me : studentEncodedMap
                .entrySet()) {
            me.getKey();
            Iterator i = me.getValue().iterator();
            EncodedString string = theStudentEncodedString.get(me.getKey());
            for (Byte b : string) {
                assertEquals(b, i.next());
            }
        }
    }

    @Test
    public void studentStringEncode() {
        makeStudentEncodedString();
        EncodedString string = Huffman.encode(studentEncodedMap, studentString);
        Iterator i = studentEncodedString.iterator();
        for (Byte b : string) {
            assertEquals(b, i.next());
        }
    }

    @Test
    public void studentStringDecode() {
        makeStudentTree();
        makeStudentEncodedString();
        assertEquals(studentString, Huffman.decode(studentTree,
                    studentEncodedString));
    }




    @Test(expected=IllegalArgumentException.class)
    public void testNullString() {
        Map<Character, Integer> map = Huffman.buildFrequencyMap(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullHuffTree() {
        Huffman.buildHuffmanTree(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullEncodingMap() {
        Huffman.buildEncodingMap(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullEncode() {
        Huffman.encode(null, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullDecode() {
        Huffman.decode(null, null);
    }

    @Test
    public void testStringWithOneChar() {
        Map<Character, Integer> map = Huffman.buildFrequencyMap("aaaaaaaaaaaaaa");
        assertEquals("There should be only one encoded value",1, map.size());
        int freq = map.get('a');
        assertEquals("Did not get the correct frequency", 14, freq); //did this to shut intellij up

        HuffmanNode huffNode = Huffman.buildHuffmanTree(map);
        assertTrue("The HuffmanNode should not have children", (huffNode.getLeft() == null && huffNode.getRight() == null));

        Map<Character, EncodedString> enMap = Huffman.buildEncodingMap(huffNode);
        assertEquals("Should only have 1 key-value pair..", 1, enMap.size());
        assertEquals("The encoded value should only be one byte long", 1, enMap.get('a').length());

        EncodedString enstr = Huffman.encode(enMap, "aaaaaaaaaaaaaa");
        assertEquals("Can't decode or encode properly", "aaaaaaaaaaaaaa", Huffman.decode(huffNode, enstr));
    }

    @Test
    public void testNormalStr() {
        Map<Character, Integer> map = Huffman.buildFrequencyMap("abracadabra");
        assertEquals("Testing freq map size", 5, map.size());

        int freq; //intellij being annoying again
        for (Map.Entry<Character, Integer> curEntry : map.entrySet()) {
            if (curEntry.getKey() == 'a') {
                freq = curEntry.getValue();
                assertEquals("Wrong freq for a", 5, freq);
            } else if (curEntry.getKey() == 'b' || curEntry.getKey() == 'r') {
                freq = curEntry.getValue();
                assertEquals("Wrong freq for b or r", 2, freq);
            } else if (curEntry.getKey() == 'c' || curEntry.getKey() == 'd') {
                freq = curEntry.getValue();
                assertEquals("Wrong freq for c or d", 1, freq);
            }
        }

        HuffmanNode tree = Huffman.buildHuffmanTree(map);
        Map<Character, EncodedString> enMap = Huffman.buildEncodingMap(tree);


        EncodedString esAbraCadabra = Huffman.encode(enMap, "abracadabra");
        assertEquals("Can't decode or encode properly", "abracadabra", Huffman.decode(tree, esAbraCadabra));

        EncodedString esOther = Huffman.encode(enMap, "abbbbrrrrcccaaaaddddrrrrr");
        assertEquals("Can't decode or encode properly with a different string", "abbbbrrrrcccaaaaddddrrrrr", Huffman.decode(tree, esOther));
    }

    @Test
    public void testHuffman() throws Exception {
        testEncodeDecode("a");
        testEncodeDecode("aa");
        testEncodeDecode("aaa");
        testEncodeDecode("aaaa");
        testEncodeDecode("aaaaa");
        testEncodeDecode("ab");
        testEncodeDecode("1");
        testEncodeDecode("101");
        testEncodeDecode(" !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~");
        testEncodeDecode(" !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~" +
                " !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~");

        StringBuilder s = new StringBuilder(100000);
        for (int i = 0; i < 100000; i++) {
            s.append((char) (((i % 127) + 32) % 127));
        }
        testEncodeDecode(s.toString());
    }

    private void testEncodeDecode(String s) {
        Map<Character, Integer> map = Huffman.buildFrequencyMap(s);
        HuffmanNode node = Huffman.buildHuffmanTree(map);
        Map<Character, EncodedString> encodedmap = Huffman.buildEncodingMap(node);
        EncodedString es = Huffman.encode(encodedmap, s);
        String str = Huffman.decode(node, es);
        assertEquals("Encode should equal", s, str);
    }

}
