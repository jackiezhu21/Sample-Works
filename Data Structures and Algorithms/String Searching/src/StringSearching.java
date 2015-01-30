import java.util.ArrayList;
import java.util.List;

public class StringSearching implements StringSearchingInterface {

    @Override
    public List<Integer> boyerMoore(CharSequence pattern, CharSequence text) {
        if (pattern == null || text == null || pattern.length() == 0) {
            throw new IllegalArgumentException();
        }
        int n = text.length();
        int m = pattern.length();
        if (n == 0 || m > text.length()) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] arr = buildLastTable(pattern);
        int i = m - 1;
        int j = m - 1;
        while (i <= n - 1) {
            char c = text.charAt(i);
            if (pattern.charAt(j) == c) {
                if (j == 0) {
                    list.add(i);
                    i += m - Math.min(j, 1 + arr[c]);
                    j = m - 1;
                } else {
                    i--;
                    j--;
                }
            } else {
                i += m - Math.min(j, 1 + arr[c]);
                j = m - 1;
            }
        }
        return list;
    }

    @Override
    public int[] buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException();
        }
        int[] arr = new int[Character.MAX_VALUE + 1];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }

        for (int i = 0; i < pattern.length(); i++) {
            arr[pattern.charAt(i)] = i;
        }

        return arr;
    }

    @Override
    public int generateHash(CharSequence current, int length) {
        if (current == null || length < 0 || length > current.length()) {
            throw new IllegalArgumentException();
        }
        int hash = 0;
        for (int i = 0; i < length; i++) {
            int base = 1;
            for (int j = 0; j < length - 1 - i; j++) {
                base *= BASE;
            }
            hash += current.charAt(i) * base;
        }
        return hash;
    }

    @Override
    public int updateHash(int oldHash, int length, char oldChar, char newChar) {
        int base = 1;
        for (int j = 0; j < length - 1; j++) {
            base *= BASE;
        }
        oldHash -= oldChar * base;
        oldHash *= BASE;
        oldHash += newChar;
        return oldHash;
    }

    @Override
    public List<Integer> rabinKarp(CharSequence pattern, CharSequence text) {
        if (pattern == null || text == null || pattern.length() == 0) {
            throw new IllegalArgumentException();
        }

        int n = text.length();
        int m = pattern.length();
        if (n == 0 || m > text.length()) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        int hashP = generateHash(pattern, m);
        int hashT = generateHash(text, m);

        for (int i = m - 1; i < n; i++) {
            if (hashP == hashT) {
                boolean equal = true;
                int x = i - m + 1;
                for (int j = 0; j < m; j++) {
                    if (pattern.charAt(j) != text.charAt(x)) {
                        equal = false;
                    }
                    x++;
                }
                if (equal) {
                    list.add(i - m + 1);
                }
            }
            if (i + 1 < n) {
                hashT = updateHash(hashT, m, text.charAt(i - m + 1),
                        text.charAt(i + 1));
            }
        }
        return list;
    }

}
