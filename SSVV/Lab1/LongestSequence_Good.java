package Lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LongestSequence_Good {

    static int[] readSequence(int[] nrE) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of elements: ");
            nrE[0] = sc.nextInt();
            int[] valE = new int[nrE[0] + 1]; // 1-based
            System.out.print("Enter elements: ");
            for (int i = 1; i <= nrE[0]; i++) {
                valE[i] = sc.nextInt();
            }
            return valE;
        }
    }

    static boolean still3Values(int[] valE, int pos, Map<Integer, Integer> freq) {
        int val = valE[pos];
        if (freq.containsKey(val))
            return true;
        return freq.size() < 3;
    }

    static int[] computeASequence(int pozStart, int nrE, int[] valE) {
        Map<Integer, Integer> freq = new HashMap<>();
        int lungFinala = 0;
        int start = pozStart;

        while (pozStart <= nrE && still3Values(valE, pozStart, freq)) {
            freq.merge(valE[pozStart], 1, Integer::sum);
            lungFinala++;
            pozStart++;
        }
        return new int[] { start, lungFinala };
    }

    // FIX: i always advances by 1, never skips positions
    static int[] computeMaxSeq(int nrE, int[] valE) {
        int pozF = 1;
        int lungF = 0;

        for (int i = 1; i <= nrE; i++) {
            int[] result = computeASequence(i, nrE, valE);
            int lungFinala = result[1];

            if (lungFinala > lungF) {
                pozF = i;
                lungF = lungFinala;
            }
        }
        return new int[] { pozF, lungF };
    }

    static void printSequence(int[] valE, int pozF, int lungF) {
        System.out.println("Start index (1-based): " + pozF);
        System.out.println("Length               : " + lungF);
        System.out.print("Elements             : ");
        for (int i = pozF; i < pozF + lungF; i++) {
            System.out.print(valE[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nrE = new int[1];
        int[] valE = readSequence(nrE);
        int[] res = computeMaxSeq(nrE[0], valE);
        printSequence(valE, res[0], res[1]);
    }
}

/*
 * ## Test Cases to Run
 * 
 * ### Test 1 — Basic case
 * ```
 * 9
 * 4 1 2 3 2 2 2 2 2
 * ```
 ** Expected:** start=2, length=8, elements=`1 2 3 2 2 2 2 2`
 * 
 * ---
 * 
 * ### Test 2 — All same elements
 * ```
 * 5
 * 7 7 7 7 7
 * ```
 ** Expected:** start=1, length=5, elements=`7 7 7 7 7`
 * 
 * ---
 * 
 * ### Test 3 — All distinct elements (window always size 3)
 * ```
 * 6
 * 1 2 3 4 5 6
 * ```
 ** Expected:** start=1, length=3, elements=`1 2 3`
 * 
 * ---
 * 
 * ### Test 4 — Two distinct values only
 * ```
 * 5
 * 1 2 1 2 1
 * ```
 ** Expected:** start=1, length=5, elements=`1 2 1 2 1`
 * 
 * ---
 * 
 * ### Test 5 — Longest window is in the middle
 * ```
 * 8
 * 1 2 3 4 1 1 1 5
 * ```
 ** Expected:** start=3, length=4, elements=`3 4 1 1`
 * 
 * ---
 * 
 * ### Test 6 — Single element
 * ```
 * 1
 * 42
 */