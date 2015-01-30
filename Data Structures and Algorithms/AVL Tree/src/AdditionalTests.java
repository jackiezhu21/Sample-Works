import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * This test suite ensures that the most basic parts of your AVL tree work.
 * However, much of it's ability to completely test your implementation
 * depends on you having used the fact that subtrees are themselves an
 * AVL tree, to your advantage. In other words, because every left and
 * right node of an AVL node is itself an AVL tree, this test suite doesn't
 * have to test insane numbers of cases to completely test your implementation.
 *
 * Disclaimer: Use these at your own risk. I'm not responsible for what you do
 * with this file, whether it be getting a bad grade because you trusted me and
 * I turned out to be wrong, starting a thermonuclear war, bricking your phone,
 * or any other side effect, intended or unintended. Though if you believe that
 * there's a problem with these test cases, please tell me! Enjoy!
 *
 * @author Siddharth Duddikunta
 */
public class AdditionalTests {
    private AVL<String> tree;
    private AVL<Integer> integerList;

    @Before
    public void setup() {
        tree = new AVL<>();
        integerList = new AVL<Integer>();
    }

    @Test(timeout = 250)
    public void basicAddNoRotation() {
        Node<Integer> root = integerList.getRoot();
        testNode(root, null, null, null, 0, true, 0, 0);

        integerList.add(2); //Adding 2
        integerList.add(2);
        root = integerList.getRoot();
        testNode(root, 2, null, null, 1, false, 0, 0);

        integerList.add(2);
        integerList.add(1); //Adding 1
        root = integerList.getRoot();
        testNode(root,           2,    1, null, 2, false, 1, 1);
        testNode(root.getLeft(), 1, null, null, 2, false, 0, 0);

        integerList.add(1);
        integerList.add(3); //Adding 3
        root = integerList.getRoot();
        testNode(root,            2,    1,    3, 3, false, 1, 0);
        testNode(root.getLeft(),  1, null, null, 3, false, 0, 0);
        testNode(root.getRight(), 3, null, null, 3, false, 0, 0);

        assertEquals("Did not remove correct data", new Integer(3), integerList.remove(3));
        assertNull("Did not correctly remove nonexistent data", integerList.remove(3));
        root = integerList.getRoot();
        testNode(root,           2,    1, null, 2, false, 1, 1);
        testNode(root.getLeft(), 1, null, null, 2, false, 0, 0);

        assertEquals("Did not remove correct data", new Integer(2), integerList.remove(2));
        assertNull("Did not correctly remove nonexistent data", integerList.remove(2));
        root = integerList.getRoot();
        testNode(root, 1, null, null, 1, false, 0, 0);

        assertEquals("Did not remove correct data", new Integer(1), integerList.remove(1));
        assertNull("Did not correctly remove nonexistent data", integerList.remove(1));
        root = integerList.getRoot();
        testNode(root, null, null, null, 0, true, 0, 0);
    }

    @Test(timeout = 250)
    public void basicAddSingleRotation() {
        Node<Integer> root = integerList.getRoot();
        testNode(root, null, null, null, 0, true, 0, 0);

        integerList.add(1); //Adding 1
        integerList.add(1);
        root = integerList.getRoot();
        testNode(root, 1, null, null, 1, false, 0, 0);

        integerList.add(1);
        integerList.add(2); //Adding 2
        root = integerList.getRoot();
        testNode(root,            1, null,    2, 2, false, 1, -1);
        testNode(root.getRight(), 2, null, null, 2, false, 0,  0);

        integerList.add(1);
        integerList.add(3); //Adding 3
        integerList.add(2);
        root = integerList.getRoot();
        testNode(root,            2,    1,    3, 3, false, 1, 0);
        testNode(root.getLeft(),  1, null, null, 3, false, 0, 0);
        testNode(root.getRight(), 3, null, null, 3, false, 0, 0);
    }

    @Test(timeout = 250)
    public void basicAddDoubleRotation() {
        Node<Integer> root = integerList.getRoot();
        testNode(root, null, null, null, 0, true, 0, 0);

        integerList.add(1);
        root = integerList.getRoot();
        testNode(root, 1, null, null, 1, false, 0, 0);

        integerList.add(3);
        root = integerList.getRoot();
        testNode(root,            1, null,    3, 2, false, 1, -1);
        testNode(root.getRight(), 3, null, null, 2, false, 0,  0);

        integerList.add(2);
        root = integerList.getRoot();
        testNode(root,            2,    1,    3, 3, false, 1, 0);
        testNode(root.getLeft(),  1, null, null, 3, false, 0, 0);
        testNode(root.getRight(), 3, null, null, 3, false, 0, 0);
    }

    @Test(timeout = 250)
    public void complexAddNoRotation() {
        Node<Integer> root = integerList.getRoot();
        testNode(root, null, null, null, 0, true, 0, 0);

        integerList.add(4); //Adding 4
        integerList.add(4);
        root = integerList.getRoot();
        testNode(root, 4, null, null, 1, false, 0, 0);

        integerList.add(2); //Adding 2
        integerList.add(4);
        integerList.add(2);
        root = integerList.getRoot();
        testNode(root,           4,    2, null, 2, false, 1, 1);
        testNode(root.getLeft(), 2, null, null, 2, false, 0, 0);

        integerList.add(6); //Adding 6
        integerList.add(4);
        integerList.add(2);
        root = integerList.getRoot();
        testNode(root,            4,    2,    6, 3, false, 1, 0);
        testNode(root.getLeft(),  2, null, null, 3, false, 0, 0);
        testNode(root.getRight(), 6, null, null, 3, false, 0, 0);

        integerList.add(1); //Adding 1
        integerList.add(6);
        root = integerList.getRoot();
        testNode(root,                     4,    2,    6, 4, false, 2, 1);
        testNode(root.getLeft(),           2,    1, null, 4, false, 1, 1);
        testNode(root.getLeft().getLeft(), 1, null, null, 4, false, 0, 0);
        testNode(root.getRight(),          6, null, null, 4, false, 0, 0);

        integerList.add(5); //Adding 5
        integerList.add(6);
        integerList.add(4);
        root = integerList.getRoot();
        testNode(root,                      4,    2,    6, 5, false, 2, 0);
        testNode(root.getLeft(),            2,    1, null, 5, false, 1, 1);
        testNode(root.getLeft().getLeft(),  1, null, null, 5, false, 0, 0);
        testNode(root.getRight(),           6,    5, null, 5, false, 1, 1);
        testNode(root.getRight().getLeft(), 5, null, null, 5, false, 0, 0);

        integerList.add(7); //Adding 7
        integerList.add(1);
        integerList.add(7);
        root = integerList.getRoot();
        testNode(root,                       4,    2,    6, 6, false, 2, 0);
        testNode(root.getLeft(),             2,    1, null, 6, false, 1, 1);
        testNode(root.getLeft().getLeft(),   1, null, null, 6, false, 0, 0);
        testNode(root.getRight(),            6,    5,    7, 6, false, 1, 0);
        testNode(root.getRight().getLeft(),  5, null, null, 6, false, 0, 0);
        testNode(root.getRight().getRight(), 7, null, null, 6, false, 0, 0);

        integerList.add(3); //Adding 3
        root = integerList.getRoot();
        testNode(root,                       4,    2,    6, 7, false, 2, 0);
        testNode(root.getLeft(),             2,    1,    3, 7, false, 1, 0);
        testNode(root.getLeft().getLeft(),   1, null, null, 7, false, 0, 0);
        testNode(root.getLeft().getRight(),  3, null, null, 7, false, 0, 0);
        testNode(root.getRight(),            6,    5,    7, 7, false, 1, 0);
        testNode(root.getRight().getLeft(),  5, null, null, 7, false, 0, 0);
        testNode(root.getRight().getRight(), 7, null, null, 7, false, 0, 0);
    }

    @Test(timeout = 250)
    public void complexAddBothRotations() {
        Node<Integer> root = integerList.getRoot();
        testNode(root, null, null, null, 0, true, 0, 0);

        integerList.add(4); //Adding 4
        root = integerList.getRoot();
        testNode(root, 4, null, null, 1, false, 0, 0);

        integerList.add(6); //Adding 6
        root = integerList.getRoot();
        testNode(root,            4, null,    6, 2, false, 1, -1);
        testNode(root.getRight(), 6, null, null, 2, false, 0,  0);

        integerList.add(7);
        root = integerList.getRoot();
        testNode(root,            6,    4,    7, 3, false, 1, 0);
        testNode(root.getLeft(),  4, null, null, 3, false, 0, 0);
        testNode(root.getRight(), 7, null, null, 3, false, 0, 0);

        integerList.add(2);
        root = integerList.getRoot();
        testNode(root,                     6,    4,    7, 4, false, 2, 1);
        testNode(root.getLeft(),           4,    2, null, 4, false, 1, 1);
        testNode(root.getLeft().getLeft(), 2, null, null, 4, false, 0, 0);
        testNode(root.getRight(),          7, null, null, 4, false, 0, 0);

        integerList.add(3);
        root = integerList.getRoot();
        testNode(root,                      6,    3,    7, 5, false, 2, 1);
        testNode(root.getLeft(),            3,    2,    4, 5, false, 1, 0);
        testNode(root.getLeft().getLeft(),  2, null, null, 5, false, 0, 0);
        testNode(root.getLeft().getRight(), 4, null, null, 5, false, 0, 0);
        testNode(root.getRight(),           7, null, null, 5, false, 0, 0);

        integerList.add(1);
        root = integerList.getRoot();
        testNode(root,                       3,    2,    6, 6, false, 2, 0);
        testNode(root.getLeft(),             2,    1, null, 6, false, 1, 1);
        testNode(root.getLeft().getLeft(),   1, null, null, 6, false, 0, 0);
        testNode(root.getRight(),            6,    4,    7, 6, false, 1, 0);
        testNode(root.getRight().getLeft(),  4, null, null, 6, false, 0, 0);
        testNode(root.getRight().getRight(), 7, null, null, 6, false, 0, 0);

        integerList.add(5);
        root = integerList.getRoot();
        testNode(root,                                  3,    2,    6, 7, false, 3, -1);
        testNode(root.getLeft(),                        2,    1, null, 7, false, 1,  1);
        testNode(root.getLeft().getLeft(),              1, null, null, 7, false, 0,  0);
        testNode(root.getRight(),                       6,    4,    7, 7, false, 2,  1);
        testNode(root.getRight().getLeft(),             4, null,    5, 7, false, 1, -1);
        testNode(root.getRight().getLeft().getRight(),  5, null, null, 7, false, 0,  0);
        testNode(root.getRight().getRight(),            7, null, null, 7, false, 0,  0);

        integerList.add(9);
        root = integerList.getRoot();
        testNode(root,                                  3,    2,    6, 8, false, 3, -1);
        testNode(root.getLeft(),                        2,    1, null, 8, false, 1,  1);
        testNode(root.getLeft().getLeft(),              1, null, null, 8, false, 0,  0);
        testNode(root.getRight(),                       6,    4,    7, 8, false, 2,  0);
        testNode(root.getRight().getLeft(),             4, null,    5, 8, false, 1, -1);
        testNode(root.getRight().getLeft().getRight(),  5, null, null, 8, false, 0,  0);
        testNode(root.getRight().getRight(),            7, null,    9, 8, false, 1, -1);
        testNode(root.getRight().getRight().getRight(), 9, null, null, 8, false, 0,  0);

        integerList.add(8);
        root = integerList.getRoot();
        testNode(root,                                  3,    2,    6, 9, false, 3, -1);
        testNode(root.getLeft(),                        2,    1, null, 9, false, 1,  1);
        testNode(root.getLeft().getLeft(),              1, null, null, 9, false, 0,  0);
        testNode(root.getRight(),                       6,    4,    8, 9, false, 2,  0);
        testNode(root.getRight().getLeft(),             4, null,    5, 9, false, 1, -1);
        testNode(root.getRight().getLeft().getRight(),  5, null, null, 9, false, 0,  0);
        testNode(root.getRight().getRight(),            8,    7,    9, 9, false, 1,  0);
        testNode(root.getRight().getRight().getLeft(),  7, null, null, 9, false, 0,  0);
        testNode(root.getRight().getRight().getRight(), 9, null, null, 9, false, 0,  0);

        assertEquals("Did not remove correctly", new Integer(3), integerList.remove(3));
        root = integerList.getRoot();
        testNode(root,                                  4,    2,    6, 8, false, 3, -1);
        testNode(root.getLeft(),                        2,    1, null, 8, false, 1,  1);
        testNode(root.getLeft().getLeft(),              1, null, null, 8, false, 0,  0);
        testNode(root.getRight(),                       6,    5,    8, 8, false, 2, -1);
        testNode(root.getRight().getLeft(),             5, null, null, 8, false, 0,  0);
        testNode(root.getRight().getRight(),            8,    7,    9, 8, false, 1,  0);
        testNode(root.getRight().getRight().getLeft(),  7, null, null, 8, false, 0,  0);
        testNode(root.getRight().getRight().getRight(), 9, null, null, 8, false, 0,  0);

        assertEquals("Did not remove correctly", new Integer(2), integerList.remove(2));
        root = integerList.getRoot();
        testNode(root,                       6,    4,    8, 7, false, 2,  0);
        testNode(root.getLeft(),             4,    1,    5, 7, false, 1,  0);
        testNode(root.getLeft().getLeft(),   1, null, null, 7, false, 0,  0);
        testNode(root.getLeft().getRight(),  5, null, null, 7, false, 0,  0);
        testNode(root.getRight(),            8,    7,    9, 7, false, 1,  0);
        testNode(root.getRight().getLeft(),  7, null, null, 7, false, 0,  0);
        testNode(root.getRight().getRight(), 9, null, null, 7, false, 0,  0);

        assertEquals("Did not remove correctly", new Integer(1), integerList.remove(1));
        assertNull("Did not check remove", integerList.remove(1));
        assertEquals("Did not remove correctly", new Integer(4), integerList.remove(4));
        assertEquals("Did not remove correctly", new Integer(5), integerList.remove(5));
        root = integerList.getRoot();
        testNode(root,                       8,    6,    9, 4, false, 2,  1);
        testNode(root.getLeft(),             6, null,    7, 4, false, 1, -1);
        testNode(root.getLeft().getRight(),  7, null, null, 4, false, 0,  0);
        testNode(root.getRight(),            9, null, null, 4, false, 0,  0);
    }

    private void testNode(Node<Integer> node, Integer data, Integer left, Integer right, int size, boolean empty, int height, int balance) {
        if (node != null) {
            assertEquals("Incorrect data", data, node.getData());
            if (left == null) {
                assertNull("Left node was not null", node.getLeft());
            } else {
                assertEquals("Incorrect left data", left, node.getLeft().getData());
            }
            if (right == null) {
                assertNull("Right node was not null", node.getRight());
            } else {
                assertEquals("Incorrect right data", right, node.getRight().getData());
            }
            assertEquals("Incorrect height", height, node.getHeight());
            assertEquals("Incorrect balance factor", balance, node.getBalanceFactor());
        }
        if (empty) {
            assertTrue("Incorrect emptiness", integerList.isEmpty());
        } else {
            assertFalse("Incorrect emptiness", integerList.isEmpty());
        }
    }

    // BEGIN BASIC FUNCTIONALITY TESTS

    @Test
    public void testNewTree() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        assertNull(tree.getRoot());
    }

    @Test
    public void testAddingAValue() {
        tree.add("somedata");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertNotNull(tree.getRoot());
    }

    @Test
    public void testAddingAndGetting() {
        tree.add("somedata");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertNotNull(tree.getRoot());
        assertTrue(tree.contains("somedata"));
        assertEquals("somedata", tree.get("somedata"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingNull() {
        tree.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGettingNull() {
        tree.get(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovingNull() {
        tree.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsNull() {
        tree.contains(null);
    }

    @Test()
    public void testRemovingNonexistant() {
        assertNull(tree.remove(""));
    }

    @Test()
    public void testGettingNonexistant() {
        assertNull(tree.get(""));
        assertFalse(tree.contains(""));
    }

    @Test
    public void testAddingASmallerValue() {
        testAddingAValue();
        tree.add("Something");
        assertEquals(2, tree.size());
        assertNotNull(tree.getRoot().getLeft());
    }

    @Test
    public void testAddingABiggerValue() {
        testAddingAValue();
        tree.add("z");
        assertEquals(2, tree.size());
        assertNotNull(tree.getRoot().getRight());
    }

    @Test
    public void testAddingBothBiggerAndSmaller() {
        testAddingASmallerValue();
        tree.add("z");
        assertEquals(3, tree.size());
        assertNotNull(tree.getRoot().getRight());
    }

    // END BASIC FUNCTIONALITY TESTS

    /* BEGIN ROTATION TESTS
        Note that failing any of these tests indicates a serious rebalancing
        problem, which will likely cause you to fail many other tests. These
        tests use simplest case rotations with both zero and some children.
     */

    @Test
    public void testInsertWithLeftRotation() {
        testInsert("abc", "bac");
    }

    @Test
    public void testInsertWithRightRotation() {
        testInsert("cba", "bac");
    }

    @Test
    public void testInsertWithRightLeftRotation() {
        testInsert("acb", "bac");
    }

    @Test
    public void testInsertWithLeftRightRotation() {
        testInsert("cab", "bac");
    }

    @Test
    public void testDeletionWithLeftRotate() {
        testDelete("bcad", "a", "cbd");
    }

    @Test
    public void testDeletionWithRightRotate() {
        testDelete("cbda", "d", "bac");
    }

    @Test
    public void testDeletionWithRightLeftRotation() {
        testDelete("bdac", "a", "cbd");
    }

    @Test
    public void testDeletionWithLeftRightRotation() {
        testDelete("cadb", "d", "bac");
    }

    @Test
    public void testComplexRotationCases() {
        testInsert("cbedfg", "ecfbdg");
        tree.clear();
        testDelete("cbedfag", "a", "ecfbdg");
        tree.clear();
        testInsert("ecfbda", "cbeadf");
        tree.clear();
        testDelete("ecfbdga", "g", "cbeadf");
        tree.clear();
        testInsert("ecjadhkgilf", "hejcgikadfl");
        tree.clear();
        testDelete("ecjadhkgilbf", "b", "hejcgikadfl");
        tree.clear();
        testInsert("hckbeiladfg", "echbdfkagil");
        tree.clear();
        testDelete("hckbeiladfjg", "j", "echbdfkagil");
    }

    // END ROTATION TESTS

    /* BEGIN "STRESS" TESTS
       These tests tie together multiple rotations, deletion types, etc.
       If you don't pass these, but pass the above, check your implementation
       for possible edge cases.
    */

    @Test
    public void testInserts() {
        testInsert("knYoVNlXDIgUhfKtTSjEMHeLQFcZWdiwyqsbrRGvBz",
                "VNkITctELRUYgqwDGKMQSXZeinsvyBFHWbdfhjlorz");
        tree.clear();
        testInsert("fleHpSsvdKqNZUPDjBrLEOTCGtMWxgVaYmFIbizJuc",
                "SKfDNZpBGLPUdjsCEIMOTWbeglqvFHJVYacimrtxuz");
        tree.clear();
        testInsert("OYAEUNrHWdRGoSnyPXlxzLVfwkJachTbsFMtDeuCvi",
                "YOoHUfxELRWcluyCGJNPSVXadinswzADFMTbehkrtv");
        tree.clear();
        testInsert("XgDCEinozUQkSyMIeGNRwKdvVcFJftpZaAjHsYOBxh",
                "XInEQevCGMScipyADFHKNRUZdgkotwzBJOVYafhjsx");
        tree.clear();
        testInsert("zdvagueYXyPlwLZjWASHmQJDIRcbKhOEMfTntCFVBk",
                "YPgHScnDLRWaelvBEJOQTXZbdfjmuyACFIKMVhktwz");
    }

    @Test
    public void testRemovals() {
        testDelete("KUTExFtpjyNiZLIAwGahsuXvogVkSlembnzrcCfDdP",
                "a", "iTtFboxCKVekrvyAEINUZcgjmpsuwzDGLSXdfhlnP");
        tree.clear();
        testDelete("txzvHrIKMGsaPioQOwcpRLblDCfUuNqAkyXhgnmJSZ",
                "n", "iMtIapxGKQclrvzCHJLOUbgkoqsuwyADNPRXfhmSZ");
        tree.clear();
        testDelete("hnDVgYzNtuJmaCklOoqRwxKATjHBfSQIMFPdrEyGcW",
                "c", "gOnHVkuCJSahmqxAEIMQTYdjlotwzBDFKNPRWfryG");
        tree.clear();
        testDelete("qaQIpioGeMJBvlCxjAdPcSEKzZugXHstyNDfLWbUwF",
                "m", "aIpCQivBGMXdlsyAEHKPUZcfjoquxzDFJLNSWbegtw");
        tree.clear();
        testDelete("QvnZswNzaBYGiXjfSAyKbTOxrHCcotkhgpPELIVeMF",
                "q", "ZNnGQfwBKOXbjsyAEHLPTYachkpvxzCFIMSVegiort");
    }

    // END "STRESS" TESTS

    // BEGIN UTILITY METHODS

    private void testInsert(String insertOrder, String levelOrder) {
        for (int i = 0; i < insertOrder.length(); i++) {
            tree.add(insertOrder.substring(i, i + 1));
        }
        String test = "";
        for (String s : tree.levelorder()) {
            test += s;
        }
        assertEquals(levelOrder, test);
    }

    private void testDelete(String insertOrder, String toDelete, String levelOrder) {
        for (int i = 0; i < insertOrder.length(); i++) {
            tree.add(insertOrder.substring(i, i + 1));
        }
        tree.remove(toDelete);
        String test = "";
        for (String s : tree.levelorder()) {
            test += s;
        }
        assertEquals(levelOrder, test);
    }

    // END UTILITY METHODS
}