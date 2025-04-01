package sort;

import heap.exception.UnderflowException;

import java.util.ArrayList;

public class Sort {
    // 快速排序的截止范围, 当数组元素个数 N <= CUTOFF, 用插入排序取代快速排序, 可节省约15%的运行时间
    private static final int CUTOFF = 3;

    /********************************** 选择排序法 **********************************/
    /**
     * 选择排序（升序）
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void selectAscSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        int minIndex = 0;
        T tmp;
        for(int i = 0; i < a.length - 1; i++) {
            minIndex = i;   // 无序区的最小数据数组下标
            for(int j = i + 1; j < a.length; j++) {
                // 在无序区中找到最小数据并保存其数组下标
                if( a[j].compareTo( a[minIndex] ) < 0 ) {
                    minIndex = j;
                }
            }
            if(minIndex != i) {
                // 如果不是无序区的最小值位置不是默认的第一个数据, 则交换之
                tmp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = tmp;
            }
        }
    }
    /**
     * 选择排序（降序）
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void selectDescSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        int maxIndex = 0;
        T tmp;
        for(int i = 0; i < a.length - 1; i++) {
            maxIndex = i;   // 无序区的最大数据数组下标
            for(int j = i + 1; j < a.length; j++) {
                // 在无序区中找到最大数据并保存其数组下标
                if( a[j].compareTo( a[maxIndex] ) > 0 ) {
                    maxIndex = j;
                }
            }
            if(maxIndex != i) {
                // 如果不是无序区的最大值位置不是默认的第一个数据, 则交换之
                tmp = a[i];
                a[i] = a[maxIndex];
                a[maxIndex] = tmp;
            }
        }
    }
    /**
     * 局部选择排序（升序）
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void selectAscSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        int minIndex = 0;
        T tmp;
        for(int i = left; i < right; i++) {
            minIndex = i;   // 无序区的最小数据数组下标
            for(int j = i + 1; j < right + 1; j++) {
                // 在无序区中找到最小数据并保存其数组下标
                if( a[j].compareTo( a[minIndex] ) < 0 ) {
                    minIndex = j;
                }
            }
            if(minIndex != i) {
                // 如果不是无序区的最小值位置不是默认的第一个数据, 则交换之
                tmp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = tmp;
            }
        }
    }
    /**
     * 局部选择排序（降序）
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void selectDescSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        int maxIndex = 0;
        T tmp;
        for(int i = left; i < right; i++) {
            maxIndex = i;   // 无序区的最大数据数组下标
            for(int j = i + 1; j < right + 1; j++) {
                // 在无序区中找到最大数据并保存其数组下标
                if( a[j].compareTo( a[maxIndex] ) > 0 ) {
                    maxIndex = j;
                }
            }
            if(maxIndex != i) {
                // 如果不是无序区的最大值位置不是默认的第一个数据, 则交换之
                tmp = a[i];
                a[i] = a[maxIndex];
                a[maxIndex] = tmp;
            }
        }
    }



    /********************************** 冒泡排序法 **********************************/
    /**
     * 冒泡排序（升序）
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void bubbleAscSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        int tmp = 0;
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j+1].compareTo(a[j]) < 0) {   // 最终最大的那个元素放到最后的位置
                    swapReferences(a, j+1, j);
                }
            }
        }
    }
    /**
     * 冒泡排序（降序）
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void bubbleDescSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        int tmp = 0;
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j+1].compareTo(a[j]) > 0) {   // 最终最小的那个元素放到最后的位置
                    swapReferences(a, j+1, j);
                }
            }
        }
    }
    /**
     * 局部冒泡排序（升序）
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void bubbleAscSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        int tmp = 0;
        for (int i = right; i > left; i--) {
            for (int j = left; j < i; j++) {
                if (a[j+1].compareTo(a[j]) < 0) {   // 最终最大的那个元素放到最后的位置
                    swapReferences(a, j+1, j);
                }
            }
        }
    }
    /**
     * 局部冒泡排序（降序）
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void bubbleDescSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        int tmp = 0;
        for (int i = right; i > left; i--) {
            for (int j = left; j < i; j++) {
                if (a[j+1].compareTo(a[j]) > 0) {   // 最终最小的那个元素放到最后的位置
                    swapReferences(a, j+1, j);
                }
            }
        }
    }



    /********************************** 插入排序法 **********************************/
    /**
     * 插入排序（升序）
     * 插入排序比冒泡排序快一倍，因为在每一趟排序发现插入点之前，平均只有全体数据项的一半真的进行了比较
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionAscSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        int j;
        for(int p = 1; p < a.length; p++) {
            T tmp = a[p];
            for(j = p; j > 0 && tmp.compareTo(a[j-1]) < 0; j--) {
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }
    /**
     * 插入排序（降序）
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionDescSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        int j;
        for(int p = 1; p < a.length; p++) {
            T tmp = a[p];
            for(j = p; j > 0 && tmp.compareTo(a[j-1]) > 0; j--) {
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }
    /**
     * 局部插入排序（升序）
     * 插入排序比冒泡排序快一倍，因为在每一趟排序发现插入点之前，平均只有全体数据项的一半真的进行了比较
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionAscSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        int j;
        for(int p = left + 1; p < right + 1; p++) {
            T tmp = a[p];
            for(j = p; j > left && tmp.compareTo(a[j-1]) < 0; j--) {
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }
    /**
     * 局部插入排序（降序）
     * 平均运行时间：O(N^2)
     * 最坏情形运行时间：O(N^2)
     * @param a
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionDescSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        int j;
        for(int p = left + 1; p < right + 1; p++) {
            T tmp = a[p];
            for(j = p; j > left && tmp.compareTo(a[j-1]) > 0; j--) {
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }



    /********************************** 希尔排序法 **********************************/
    /**
     * 希尔排序（升序）
     * 时间复杂度：采用不同的gap可能会有不同的时间复杂度
     * 最坏情形运行时间：O(N^2), 使用Hibbard增量的希尔排序的最坏情形运行时间为 O(N^(3/2))
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellAscSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        int j;

        final int k = (int)(Math.log(a.length + 1) / Math.log(2));

        for(int gap = (1 << k) - 1; gap > 0; gap = (gap - 1) / 2 ) {
            for(int i = gap; i < a.length; i++) {
                T tmp = a[i];
                for(j = i; j >= gap && tmp.compareTo(a[j-gap]) < 0; j -= gap) {
                    a[j] = a[j-gap];
                }
                a[j] = tmp;
            }
        }
    }
    /**
     * 希尔排序（降序）
     * 时间复杂度：采用不同的gap可能会有不同的时间复杂度
     * 最坏情形运行时间：O(N^2), 使用Hibbard增量的希尔排序的最坏情形运行时间为 O(N^(3/2))
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellDescSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        int j;

        final int k = (int)(Math.log(a.length + 1) / Math.log(2));

        for(int gap = (1 << k) - 1; gap > 0; gap = (gap - 1) / 2 ) {
            for(int i = gap; i < a.length; i++) {
                T tmp = a[i];
                for(j = i; j >= gap && tmp.compareTo(a[j-gap]) > 0; j -= gap) {
                    a[j] = a[j-gap];
                }
                a[j] = tmp;
            }
        }
    }
    /**
     * 局部希尔排序（升序）
     * 时间复杂度：采用不同的gap可能会有不同的时间复杂度
     * 最坏情形运行时间：O(N^2), 使用Hibbard增量的希尔排序的最坏情形运行时间为 O(N^(3/2))
     * @param a
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellAscSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        int j;

        final int k = (int)(Math.log(a.length + 1) / Math.log(2));

        for(int gap = (1 << k) - 1; gap > 0; gap = (gap - 1) / 2 ) {
            for(int i = left + gap; i < right + 1; i++) {
                T tmp = a[i];
                for(j = i; j >= left + gap && tmp.compareTo(a[j-gap]) < 0; j -= gap) {
                    a[j] = a[j-gap];
                }
                a[j] = tmp;
            }
        }
    }
    /**
     * 局部希尔排序（降序）
     * 时间复杂度：采用不同的gap可能会有不同的时间复杂度
     * 最坏情形运行时间：O(N^2), 使用Hibbard增量的希尔排序的最坏情形运行时间为 O(N^(3/2))
     * @param a
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellDescSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        int j;

        final int k = (int)(Math.log(a.length + 1) / Math.log(2));

        for(int gap = (1 << k) - 1; gap > 0; gap = (gap - 1) / 2 ) {
            for(int i = left + gap; i < right + 1; i++) {
                T tmp = a[i];
                for(j = i; j >= left + gap && tmp.compareTo(a[j-gap]) > 0; j -= gap) {
                    a[j] = a[j-gap];
                }
                a[j] = tmp;
            }
        }
    }



    /********************************** 堆排序法 **********************************/
    /**
     * 堆排序（升序）
     * 平均运行时间：O(N logN)
     * 最坏情形运行时间：O(N logN)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void heapAscSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        for(int i = a.length / 2 - 1; i >= 0; i--) {    // buildHeap
            maxPercDown(a, i, a.length);
        }
        for(int i = a.length - 1; i > 0; i--) {
            swapReferences(a, 0, i);
            maxPercDown(a, 0, i);
        }
    }
    /**
     * 堆排序（降序）
     * 平均运行时间：O(N logN)
     * 最坏情形运行时间：O(N logN)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void heapDescSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        for(int i = a.length / 2 - 1; i >= 0; i--) {    // buildHeap
            minPercDown(a, i, a.length);
        }
        for(int i = a.length - 1; i > 0; i--) {
            swapReferences(a, 0, i);
            minPercDown(a, 0, i);
        }
    }



    /********************************** 归并排序法 **********************************/
    /**
     * 归并排序（升序）
     * 平均运行时间：O(N logN)
     * 最坏情形运行时间：O(N logN)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeAscSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        T[] tmpArray = (T[])new Comparable[ a.length ];

        mergeAscSort(a, tmpArray, 0, a.length - 1);
    }
    /**
     * 归并排序（降序）
     * 平均运行时间：O(N logN)
     * 最坏情形运行时间：O(N logN)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeDescSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        T[] tmpArray = (T[])new Comparable[ a.length ];

        mergeDescSort(a, tmpArray, 0, a.length - 1);
    }
    /**
     * 局部归并排序（升序）
     * 平均运行时间：O(N logN)
     * 最坏情形运行时间：O(N logN)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeAscSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        T[] tmpArray = (T[])new Comparable[ a.length ];

        mergeAscSort(a, tmpArray, left, right);
    }
    /**
     * 局部归并排序（降序）
     * 平均运行时间：O(N logN)
     * 最坏情形运行时间：O(N logN)
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeDescSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        T[] tmpArray = (T[])new Comparable[ a.length ];

        mergeDescSort(a, tmpArray, left, right);
    }



    /********************************** 快速排序法 **********************************/
    /**
     * 快速排序法
     * 时间复杂度：O(N logN)
     * 最坏情形运行时间：O(N^2), 经过稍许努力可使这种情形极难出现
     */
    public static <T extends Comparable<? super T>> void quickAscSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        quickAscSortPrivate(a, 0, a.length - 1);
    }
    /**
     * 快速排序法
     * 时间复杂度：O(N logN)
     * 最坏情形运行时间：O(N^2), 经过稍许努力可使这种情形极难出现
     */
    public static <T extends Comparable<? super T>> void quickDescSort(T[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        quickDescSortPrivate(a, 0, a.length - 1);
    }
    /**
     * 局部快速排序法
     * 时间复杂度：O(N logN)
     * 最坏情形运行时间：O(N^2), 经过稍许努力可使这种情形极难出现
     */
    public static <T extends Comparable<? super T>> void quickAscSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        quickAscSortPrivate(a, left, right);
    }
    /**
     * 局部快速排序法
     * 时间复杂度：O(N logN)
     * 最坏情形运行时间：O(N^2), 经过稍许努力可使这种情形极难出现
     */
    public static <T extends Comparable<? super T>> void quickDescSort(T[] a, int left, int right) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }
        if(left > right || left < 0 || right > a.length - 1) {
            throw new UnderflowException();
        }

        quickDescSortPrivate(a, left, right);
    }



    /********************************** 字符串基数排序法 **********************************/
    /**
     * 定长字符串的基数排序（ArrayList的简单实现）
     * Assume all are all ASCII
     * Assume all have same length
     */
    public static void radixSortA(String[] arr, int stringLen) {
        final int BUCKETS = 256;
        ArrayList<String>[] buckets = new ArrayList[BUCKETS];

        for(int i = 0; i < BUCKETS; i++) {
            buckets[i] = new ArrayList<>();
        }

        for(int pos = stringLen - 1; pos >= 0; pos--) {
            for(String s: arr) {
                buckets[s.charAt(pos)].add(s);
            }

            int idx = 0;
            for(ArrayList<String> thisBucket: buckets) {
                for(String s: thisBucket) {
                    arr[idx++] = s;
                }
                thisBucket.clear();
            }
        }
    }
    /**
     * 定长字符串的计数基数排序
     * Assume all are all ASCII
     * Assume all have same length
     */
    public static void countingRadixSort(String[] arr, int stringLen) {
        final int BUCKETS = 256;

        int N = arr.length;
        String[] buffer = new String[N];

        String[] in = arr;
        String[] out = buffer;

        for(int pos = stringLen - 1; pos >= 0; pos--) {
            int[] count = new int[BUCKETS + 1];

            for(int i = 0; i < N; i++) {
                count[in[i].charAt(pos) + 1]++;
            }

            for(int b = 1; b <= BUCKETS; b++) {
                count[b] += count[b - 1];
            }

            for(int i = 0; i < N; i++) {
                out[ count[in[i].charAt(pos)]++ ] = in[i];
            }

            // 互换 in 和 out 的角色
            String[] tmp = in;
            in = out;
            out = tmp;
        }

        // 如果交换是奇数次, in 是 buffer, out 是 arr, 所以要复制回来
        if(stringLen % 2 == 1) {
            for(int i = 0; i < arr.length; i++) {
                out[i] = in[i];
            }
        }
    }
    /**
     * 变长字符串的基数排序
     * Assume all are all ASCII
     * Assume all have length bounded by maxLen
     */
    public static void radixSort( String [ ] arr, int maxLen ) {
        final int BUCKETS = 256;

        ArrayList<String>[] wordsByLength = new ArrayList[ maxLen + 1 ];
        ArrayList<String>[] buckets = new ArrayList[ BUCKETS ];

        for(int i = 0; i < wordsByLength.length; i++) {
            wordsByLength[i] = new ArrayList<>();
        }

        for(int i = 0; i < BUCKETS; i++) {
            buckets[i] = new ArrayList<>();
        }

        for(String s: arr) {
            wordsByLength[ s.length() ].add(s);
        }

        int idx = 0;
        for(ArrayList<String> wordList: wordsByLength) {
            for(String s : wordList) {
                arr[idx++] = s;
            }
        }

        int startingIndex = arr.length;
        for(int pos = maxLen - 1; pos >= 0; pos--) {
            startingIndex -= wordsByLength[ pos + 1 ].size( );

            for(int i = startingIndex; i < arr.length; i++) {
                buckets[ arr[i].charAt(pos) ].add( arr[i] );
            }

            idx = startingIndex;
            for(ArrayList<String> thisBucket: buckets) {
                for(String s: thisBucket) {
                    arr[idx++] = s;
                }
                thisBucket.clear( );
            }
        }
    }



    /******************************************************************************************/
    /**************************************** 私有方法 ****************************************/
    /******************************************************************************************/

    /**
     * 交换数组中的元素
     * @param a 数组
     * @param index1 第一个要交换的元素的索引
     * @param index2 第二个要交换的元素的索引
     */
    private static <T> void swapReferences(T [ ] a, int index1, int index2 ) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0) {
            return;
        }

        T tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }



    /********************************** 堆排序的内部方法 **********************************/
    /**
     * 堆排序的内部方法
     * @param i 元素的索引
     * @return 左儿子的索引
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }
    /**
     * （最大元）堆排序的内部方法（用于 deleteMax 和 buildHeap）
     * @param a 数组
     * @param i 开始下滤的位置
     * @param n 堆的大小
     */
    private static <T extends Comparable<? super T>> void maxPercDown(T[] a, int i, int n) {
        int child;
        T tmp;
        for(tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);   // i是父元素索引, 2i+1是其左儿子的索引

            if(child != n - 1 && a[child].compareTo( a[child + 1] ) < 0) {
                child++;    // 左儿子不是堆的最后一个元素, 且左儿子 < 右儿子, 则执行
            }
            if (tmp.compareTo(a[child]) < 0) {  // 父元素 < 儿子, 父元素下滤, 儿子上移
                a[i] = a[child];
            } else {
                break;
            }
        }
        a[i] = tmp;
    }
    /**
     * （最小元）堆排序的内部方法（用于 deleteMin 和 buildHeap）
     * @param a 数组
     * @param i 开始下滤的位置
     * @param n 堆的大小
     */
    private static <T extends Comparable<? super T>> void minPercDown(T[] a, int i, int n) {
        int child;
        T tmp;
        for(tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);   // i是父元素索引, 2i+1是其左儿子的索引

            if(child != n -1 && a[child].compareTo( a[child + 1] ) > 0) {
                child++;    // 左儿子不是堆的最后一个元素, 且左儿子 > 右儿子, 则执行
            }
            if (tmp.compareTo(a[child]) > 0) {  // 父元素 > 儿子, 父元素下滤, 儿子上移
                a[i] = a[child];
            } else {
                break;
            }
        }
        a[i] = tmp;
    }



    /********************************** 归并排序的内部方法 **********************************/
    /**
     * 归并排序的内部递归方法（升序）
     * @param a 欲排序的数组
     * @param tmpArray 存放结果的数组
     * @param left 子序列的最左端索引
     * @param right 子序列的最右端索引
     */
    private static <T extends Comparable<? super T>>
    void mergeAscSort(T[] a, T[] tmpArray, int left, int right) {
        if(left < right) {
            int center = (left + right) / 2;
            mergeAscSort(a, tmpArray, left, center);
            mergeAscSort(a, tmpArray, center + 1, right);
            ascMerge(a, tmpArray, left, center + 1, right);
        }
    }
    /**
     * 归并排序的内部递归方法（降序）
     * @param a 欲排序的数组
     * @param tmpArray 存放结果的数组
     * @param left 子序列的最左端索引
     * @param right 子序列的最右端索引
     */
    private static <T extends Comparable<? super T>>
    void mergeDescSort(T[] a, T[] tmpArray, int left, int right) {
        if(left < right) {
            int center = (left + right) / 2;
            mergeDescSort(a, tmpArray, left, center);
            mergeDescSort(a, tmpArray, center + 1, right);
            descMerge(a, tmpArray, left, center + 1, right);
        }
    }
    /**
     * 用于合并两个已排序序列的内部方法（升序）
     * @param a 欲排序的数组
     * @param tmpArray 存放结果的数组
     * @param leftPos 子序列的最左端索引
     * @param rightPos 第二个子序列的开始位置索引
     * @param rightEnd 子序列的最右端索引
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void ascMerge( AnyType [ ] a, AnyType [ ] tmpArray, int leftPos, int rightPos, int rightEnd ) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while(leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }

        while(leftPos <= leftEnd) {    // Copy rest of first half
            tmpArray[tmpPos++] = a[leftPos++];
        }

        while(rightPos <= rightEnd) {  // Copy rest of right half
            tmpArray[tmpPos++] = a[rightPos++];
        }

        // Copy tmpArray back
        for(int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }
    /**
     * 用于合并两个已排序序列的内部方法（降序）
     * @param a 欲排序的数组
     * @param tmpArray 存放结果的数组
     * @param leftPos 子序列的最左端索引
     * @param rightPos 第二个子序列的开始位置索引
     * @param rightEnd 子序列的最右端索引
     */
    private static <T extends Comparable<? super T>>
    void descMerge(T[] a, T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while(leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) >= 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }

        while(leftPos <= leftEnd) {    // Copy rest of first half
            tmpArray[tmpPos++] = a[leftPos++];
        }

        while(rightPos <= rightEnd) {  // Copy rest of right half
            tmpArray[tmpPos++] = a[rightPos++];
        }

        // Copy tmpArray back
        for(int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }



    /********************************** 快速排序的内部方法 **********************************/
    /**
     * 返回左端、右端、中心位置三者的中值, 并按升序交换三者位置
     * @param a
     * @param left
     * @param right
     */
    private static <T extends Comparable<? super T>> T ascMedian3(T[] a, int left, int right) {
        int center = (left + right) / 2;
        // left < center < right
        if(a[center].compareTo(a[left]) < 0) {
            swapReferences(a, left, center);
        }
        if(a[right].compareTo(a[left]) < 0) {
            swapReferences(a, left, right);
        }
        if(a[right].compareTo(a[center]) < 0) {
            swapReferences(a, center, right);
        }
        // 将枢纽元放在 right - 1 的位置
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }
    /**
     * 返回左端、右端、中心位置三者的中值, 并按降序交换三者位置
     * @param a
     * @param left
     * @param right
     */
    private static <T extends Comparable<? super T>> T descMedian3(T[] a, int left, int right) {
        int center = (left + right) / 2;
        // left > center > right
        if(a[center].compareTo(a[left]) > 0) {
            swapReferences(a, left, center);
        }
        if(a[right].compareTo(a[left]) > 0) {
            swapReferences(a, left, right);
        }
        if(a[right].compareTo(a[center]) > 0) {
            swapReferences(a, center, right);
        }
        // 将枢纽元放在 left + 1 的位置
        swapReferences(a, center, left + 1);
        return a[left + 1];
    }
    /**
     * 快速排序的内部递归方法（升序）
     * @param a
     * @param left
     * @param right
     */
    private static <T extends Comparable<? super T>> void quickAscSortPrivate(T[] a, int left, int right) {
        if(left + CUTOFF <= right) {
            T pivot = ascMedian3(a, left, right);

            // 开始分割
            int i = left, j = right - 1;
            for( ; ; ) {
                while(a[++i].compareTo(pivot) < 0) { }
                while(a[--j].compareTo(pivot) > 0) { }
                if(i < j) {
                    swapReferences(a, i, j);
                } else {
                    break;
                }
            }

            swapReferences(a, i, right - 1); // 交换当前i和枢纽元的位置

            quickAscSort(a, left, i - 1);
            quickAscSort(a, i + 1, right);
        } else {
            // 选用插入排序
            insertionAscSort(a, left, right);
        }
    }
    /**
     * 快速排序的内部递归方法（降序）
     * @param a
     * @param left
     * @param right
     */
    private static <T extends Comparable<? super T>> void quickDescSortPrivate(T[] a, int left, int right) {
        if(left + CUTOFF <= right) {
            T pivot = descMedian3(a, left, right);

            // 开始分割
            int i = left + 1, j = right;
            for( ; ; ) {
                while(a[++i].compareTo(pivot) > 0) { }
                while(a[--j].compareTo(pivot) < 0) { }
                if(i < j) {
                    swapReferences(a, i, j);
                } else {
                    break;
                }
            }

            swapReferences(a, j, left + 1); // 交换当前j和枢纽元的位置

            quickDescSort(a, left, j - 1);
            quickDescSort(a, j + 1, right);
        } else {
            // 选用插入排序
            insertionDescSort(a, left, right);
        }
    }
}
