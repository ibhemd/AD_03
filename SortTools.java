import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortTools {

    public static int[] createSequenceInc(int n) {
        int[] arr = {};
        if (n > 0) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i + 1;
            }
        }
        return arr;
    }

    public static int[] createSequenceDec(int n) {
        int[] arr = {};
        if (n > 0) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = n - i;
            }
        }
        return arr;
    }

    public static int[] createSequenceRand(int n) {
        int[] arr = {};
        if (n > 0) {
            arr = new int[n];
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                int value = rand.nextInt(n) + 1;
                arr[i] = value;
            }
        }
        return arr;
    }

    public static int[] createSequenceAlt(int n) {
        int[] arr = {};
        if (n > 0) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                if (i%2 == 0) arr[i] = 1;
                else arr[i] = 2;
            }
        }
        return arr;
    }

    public static int[] insertionSort(int[] a) {
        for (int j = 1; j < a.length; j++) {
            int val = a[j];
            int i = j - 1;
            while(i >= 0 && a[i] > val) {
                a[i+1] = a[i];
                i -= 1;
            }
            a[i+1] = val;
        }
        return a;
    }

    public static <T extends Comparable<T>> T[] insertionSortGen(T[] GenArray) {
        for (int j = 1; j < GenArray.length; j++) {
            T val = GenArray[j];
            int i = j - 1;
            while(i >= 0 && GenArray[i].compareTo(val) > 0) {
                GenArray[i+1] = GenArray[i];
                i -= 1;
            }
            GenArray[i+1] = val;
        }
        return GenArray;
    }

    public static int[] bubbleSort(int[] a) {
        for (int i=a.length; i >= 1; i--) {
            for (int j = 0; j <= i-2; j++) {
                if (a[j] > a[j+1]) {
                    int s = a[j];
                    a[j] = a[j+1];
                    a[j+1] = s;
                }
            }
        }
        return a;
    }

    public static int[] bubbleSortNew(int[] a) {
        for (int i=a.length; i >= 1; i--) {
            for (int j = 0; j <= i-10; j++) {
                int[] temp = Arrays.copyOfRange(a,j,j+10);
                insertionSort(temp);
                for (int k = 0; k < 10; k++) {
                    a[j+k] = temp[k];
                }
            }
        }
        return a;
    }

    public static <T extends Comparable<T>> T[] bubbleSortGen(T[] GenArray) {
        for (int i=GenArray.length; i >= 1; i--) {
            for (int j = 0; j <= i-2; j++) {
                if (GenArray[j].compareTo(GenArray[j+1]) > 0) {
                    T s = GenArray[j];
                    GenArray[j] = GenArray[j+1];
                    GenArray[j+1] = s;
                }
            }
        }
        return GenArray;
    }

    public static int[] mergeSort(int[] A, int p, int r) {
        if (p<r) {
            int q = (p+r)/2;
            mergeSort(A,p,q);
            mergeSort(A,q+1,r);
            merge(A,p,q,r);
        }
        return A;
    }

    public static int[] merge(int[] A, int p, int q, int r) {
        // split Array in 2 halfs and generate subarrays
        int[] L = new int[q-p+2], R = new int[r-q+1];

        // fill subarrays ; last diggit = max value
        for (int i = 0; i<L.length; i++) {
            if (i == L.length-1) {
                L[i] = Integer.MAX_VALUE;
            } else {
                L[i] = A[p+i];
            }
        }
        //System.out.println(Arrays.toString(A));
        for (int j = 0; j<R.length; j++) {
            if (j == R.length-1) {
                R[j] = Integer.MAX_VALUE;
            } else {
                R[j] = A[q+j+1];
            }
        }

        // init counter vars for coming FOR
        int i = 0, j = 0;

        // sort Array
        for (int k = p; k<=r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }

        return A;
    }

    public static int[] mergeSortNew(int[] A, int p, int s) {
        if (p<s) {
            int q = (s - p) / 3 + p ;
            int r = q + ((s - p) / 3) + 1;
            mergeSortNew(A, p, q);
            mergeSortNew(A, q+1, r);
            mergeSortNew(A, r+1, s);
            mergeNew(A, p, q, r, s);
        }
        return A;
    }

    public static int[] mergeNew(int[] A, int p, int q, int r, int s) {
        // split Array in 2 halfs and generate subarrays
        int[] L = new int[q-p+2];
        int[] M = new int[r-q+1];
        int[] R = new int[s-r+1];

        // fill subarrays ; last diggit = max value
        for (int i = 0; i<L.length; i++) {
            if (i == L.length-1) {
                L[i] = Integer.MAX_VALUE;
            } else {
                L[i] = A[p+i];
            }
        }
        for (int j = 0; j<M.length; j++) {
            if (j == M.length-1) {
                M[j] = Integer.MAX_VALUE;
            } else {
                M[j] = A[q+j+1];
            }
        }
        for (int k = 0; k<R.length; k++) {
            if (k == R.length-1) {
                R[k] = Integer.MAX_VALUE;
            } else {
                R[k] = A[r+k+1];
            }
        }

        // init counter vars for coming FOR
        int i = 0, j = 0, k = 0;

        // sort Array
        for (int l = p; l<=s; l++) {
            int min = Math.min(Math.min(L[i], M[j]), R[k]);

            if (L[i] == min) {
                A[l] = L[i];
                i++;
            } else if (M[j] == min) {
                A[l] = M[j];
                j++;
            } else {
                A[l] = R[k];
                k++;
            }
        }

        return A;
    }

    public static <T extends Comparable<T>> T[] mergeSortGen(T[] A, int p, int r) {
        if (p<r) {
            int q = (p+r)/2;
            mergeSortGen(A,p,q);
            mergeSortGen(A,q+1,r);
            mergeGen(A,p,q,r);
        }
        return A;
    }

    public static <T extends Comparable<T>> T[] mergeGen(T[] A, int p, int q, int r) {
        ArrayList<T> arrList = new ArrayList<>();

        for (int i = p; i <= q; i++) {
            arrList.add(A[i]);
        }
        for (int i = r; i >= q + 1; i--) {
            arrList.add(A[i]);
        }

        int i = 0;
        int j = arrList.size() - 1;

        for (int k = p; k <= r; k++) {
            if (arrList.get(i).compareTo(arrList.get(j)) <= 0) {
                A[k] = arrList.get(i);
                i++;
            } else {
                A[k] = arrList.get(j);
                j--;
            }
        }

        return A;
    }

    public static void main(String[] args) {

        int[] hD = createSequenceDec(100);
        int[] tD = createSequenceDec(1000);
        int[] ttD = createSequenceDec(10000);
        int[] htD = createSequenceDec(100000);
        int[] thtD = createSequenceDec(200000);

        int[] hI = createSequenceInc(100);
        int[] tI = createSequenceInc(1000);
        int[] ttI = createSequenceInc(10000);
        int[] htI = createSequenceInc(100000);
        int[] thtI = createSequenceInc(200000);

        long[] Begins = new long[10];
        long[] Ends = new long[10];
        long[] Durations = new long[10];
        long Duration = 0;

        for (int i = 0; i < 10; i++) {
            Begins[i] = System.nanoTime();
            mergeSortNew(hI,0, hI.length-1);                          // -->> manual changes here for tests: methods [insertionSort;bubbleSort;bubbleSortNew] and arrays [h;t;tt;ht]
            Ends[i] = System.nanoTime();
            Durations[i] = Ends[i] - Begins[i];
            Duration += Durations[i];
        }

        long Median = Duration/10;

        System.out.println(Arrays.toString(Durations));
        System.out.println(Median);

        // Test: 100 Dec
        // Durations InsertionsSort: [84200, 3000, 2400, 2500, 2500, 2500, 2400, 2400, 2400, 2500]
        // Median InsertionSort: 10680
        // Durations MergeSort: [65400, 65600, 60800, 66100, 56500, 53000, 55900, 56200, 25500, 10500]
        // Median MergeSort: 51550
        // Durations MergeSortNew: [130900, 81400, 83300, 67200, 71000, 68700, 131400, 68800, 22500, 13300]
        // Median MergeSortNew: 73850

        // Test: 100 Inc
        // Durations InsertionsSort: [4600, 2500, 2500, 2400, 2400, 2400, 2400, 2400, 2400, 2400]
        // Median InsertionSort: 2640
        // Durations MergeSort: [66000, 65200, 61200, 66600, 56600, 55100, 56900, 51000, 10000, 10200]
        // Median MergeSort: 49880
        // Durations MergeSortNew: [130000, 78800, 73800, 74500, 70000, 113700, 102200, 68600, 67200, 35000]
        // Median MergeSortNew: 81380

        // Test: 1.000 Dec
        // Durations InsertionsSort: [2874600, 5000, 4600, 4600, 4600, 4600, 5000, 4600, 4600, 4600]
        // Median InsertionSort: 291680
        // Durations MergeSort: [655500, 261400, 128300, 137000, 130300, 132500, 130800, 132100, 128600, 129300]
        // Median MergeSort: 196580
        // Durations MergeSortNew: [775000, 152900, 154500, 228700, 155200, 155100, 171600, 163500, 149500, 145300]
        // Median MergeSortNew: 225130

        // Test: 1.000 Inc
        // Durations InsertionsSort: [26200, 25400, 24500, 24300, 59000, 57700, 25500, 24500, 24800, 24700]
        // Median InsertionSort: 31660
        // Durations MergeSort: [577600, 160300, 129000, 133200, 130400, 128100, 128400, 128700, 131800, 130400]
        // Median MergeSort: 177790
        // Durations MergeSortNew: [733800, 149800, 149900, 153200, 148700, 148000, 148600, 147800, 147500, 147400]
        // Median MergeSortNew: 207470

        // Test: 10.000 Dec
        // Durations InsertionsSort: [22585000, 47600, 45600, 45500, 45600, 54200, 46300, 45800, 45800, 48500]
        // Median InsertionSort: 2300990
        // Durations MergeSort: [2056300, 1592900, 1596200, 1621800, 1624800, 1675800, 1676800, 1894900, 8229700, 8263000]
        // Median MergeSort: 3023220
        // Durations MergeSortNew: [2530500, 1805700, 1780200, 1771000, 1786000, 1779000, 1801000, 1827300, 1801000, 1787800]
        // Median MergeSortNew: 1866950

        // Test: 10.000 Inc
        // Durations InsertionsSort: [246900, 244800, 244600, 246800, 247700, 244000, 243900, 248200, 248400, 247200]
        // Median InsertionSort: 246250
        // Durations MergeSort: [2233700, 1608900, 1626300, 1630600, 1622000, 1626300, 1640300, 1963600, 7852700, 9194300]
        // Median MergeSort: 3099870
        // Durations MergeSortNew: [2377900, 1795300, 1773300, 1780600, 1818800, 1783900, 1771900, 1770300, 1760800, 2806600]
        // Median MergeSortNew: 1943940

        // Test: 100.000 Dec
        // Durations InsertionsSort: [1678705400, 469300, 455700, 452800, 455000, 452900, 461700, 454100, 434500, 214400]
        // Median InsertionSort: 168255580
        // Durations MergeSort: [21747500, 30002800, 4411300, 6770800, 6842500, 4384400, 4826400, 7444200, 8436300, 8293500]
        // Median MergeSort: 10315970
        // Durations MergeSortNew: [26138000, 49788200, 5707500, 5996000, 10078700, 5713400, 5814100, 6092000, 7951400, 9242500]
        // Median MergeSortNew: 13252180

        // Test: 100.000 Inc
        // Durations InsertionsSort: [1903700, 503100, 456100, 453400, 455700, 455500, 455100, 454900, 456800, 454800]
        // Median InsertionSort: 604910
        // Durations MergeSort: [25835600, 25452800, 4391800, 7270000, 6811200, 4430800, 4773500, 7971900, 8873500, 8995600]
        // Median MergeSort: 10480670
        // Durations MergeSortNew: [25434500, 20367400, 27308900, 6647500, 11028700, 6193300, 6281000, 6522600, 9408600, 9233100]
        // Median MergeSortNew: 12842560

        // Test: 200.000 Dec
        // Durations InsertionsSort: [6508737400, 899200, 881900, 886200, 883400, 482000, 358300, 442800, 409100, 451800]
        // Median InsertionSort: 651443210
        // Durations MergeSort: [37199500, 28688800, 9177000, 13797800, 17420400, 17044200, 17215000, 17144100, 17057300, 8993600]
        // Median MergeSort: 18373770
        // Durations MergeSortNew: [41817400, 22066400, 15948500, 12076800, 17980200, 19708600, 19127000, 18701600, 18714000, 18591800]
        // Median MergeSortNew: 20473230

        // Test: 200.000 Inc
        // Durations InsertionsSort: [2426200, 919200, 915300, 910500, 910700, 360200, 137500, 132600, 130000, 131300]
        // Median InsertionSort: 697350
        // Durations MergeSort: [33108800, 32924400, 9590800, 14044600, 17429600, 17570000, 17929800, 17678600, 18339100, 10214700]
        // Median MergeSort: 18883040
        // Durations MergeSortNew: [46561400, 47097400, 16815400, 13643600, 18293700, 19054500, 19182100, 19784400, 19880800, 19720900]
        // Median MergeSortNew: 24003420

        /*

        int[] testMergeSort = {1,5,6,4,3,2,9,5,3,1};
        System.out.println(Arrays.toString(mergeSort(testMergeSort,0,testMergeSort.length-1))); // [1, 1, 2, 3, 3, 4, 5, 5, 6, 9]

        int[] testMergeSortNew = {1,5,6,4,3,2,9,5,3,1};
        System.out.println(Arrays.toString(mergeSortNew(testMergeSortNew,0,testMergeSortNew.length-1))); // [1, 1, 2, 3, 3, 4, 5, 5, 6, 9]

        Integer[] GenI = {1,5,6,4,3,2,9,5,3,1};
        System.out.println(Arrays.toString(mergeSortGen(GenI, 0, GenI.length-1))); // [1, 1, 2, 3, 3, 4, 5, 5, 6, 9]

        Character[] GenC = {'f','a','b','j','d','c'};
        System.out.println(Arrays.toString(mergeSortGen(GenC, 0, GenC.length-1))); // [a, b, c, d, f, j]

        String[] GenS = {"abc","bac","abc","bac","cab","bca"};
        System.out.println(Arrays.toString(mergeSortGen(GenS, 0, GenS.length-1))); // [abc, abc, bac, bac, bca, cab]

         */

    }

}
