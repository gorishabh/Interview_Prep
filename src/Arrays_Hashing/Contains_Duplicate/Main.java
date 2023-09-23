package Arrays_Hashing.Contains_Duplicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class Main {

    public static void main(String[] args) {
        int[] array = {-1, 2, 3, 4, -1};

        System.out.println(hasDistinctElementsOptimal(array));
    }

    // NLogN T ; N S (worst case)
    public static boolean hasDistinctElements(int[] array) {
        if (array.length == 0) return false;
        if (array.length == 1) return true;
        Arrays.sort(array); // Takes O(NLogN) T and O(LogN) S for stack space and worst case O(N)
        int i = 0;
        int j = 1;
        while (j < array.length) {
            if (array[i++] == array[j++]) return false;
        }
        return true;
    }

    // N T ; N S (worst case)
    public static boolean hasDistinctElementsOptimal(int[] array) {
        if (array.length == 0) return false;
        HashSet<Integer> set = new HashSet<>(array.length);
        for (int j : array) {
            set.add(j);
        }
        return set.size() == array.length;
    }

}
