package search;

import heap.exception.UnderflowException;
import sort.Sort;

public class Search {
    private static final int NOT_FOUND = -1;
    private static final int CUTOFF = 3;    // 截止范围

    /********************************** 折半查找 **********************************/
    /**
     * 折半查找（默认升序） --- 时间复杂度：O(logN)
     * @param items 一个已升序排序的数组
     * @param item 欲查找的元素
     * @return 若找到欲查找的元素, 则返回其在数组中对应的下标; 找不到则返回 -1
     */
    public static <T extends Comparable<? super T>> int binaryAscSearch(T[] items, T item) {
        int low = 0, high = items.length - 1;

        while(low <= high) {
            int mid = (low + high) / 2;
            if(items[mid].compareTo(item) < 0) {
                // 中间元素 小于 欲查找元素, 因为升序, 查找右半边
                low = mid + 1;
            } else if(items[mid].compareTo(item) > 0) {
                // 中间元素 大于 欲查找元素, 因为升序, 查找左半边
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return NOT_FOUND;
    }
    /**
     * 折半查找（降序） --- 时间复杂度：O(logN)
     * @param items 一个已降序排序的数组
     * @param item 欲查找的元素
     * @return 若找到欲查找的元素, 则返回其在数组中对应的下标; 找不到则返回 -1
     */
    public static <T extends Comparable<? super T>> int binaryDescSearch(T[] items, T item) {
        int low = 0, high = items.length - 1;

        while(low <= high) {
            int mid = (low + high) / 2;
            if(items[mid].compareTo(item) < 0) {
                // 中间元素 小于 欲查找元素, 因为降序, 查找左半边
                high = mid - 1;
            } else if(items[mid].compareTo(item) > 0) {
                // 中间元素 大于 欲查找元素, 因为降序, 查找右半边
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return NOT_FOUND;
    }


    /********************************** 快速选择 **********************************/
    /**
     * 从一个任意数组中查询第k个最小元并返回
     * @param a 一个任意数组
     * @param k
     * @return 返回数组中第k个最大元
     */
    public static <T extends Comparable<? super T>> T quickMinSelect(T[] a, int k) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0 || k <= 0 || k > a.length) {
            throw new UnderflowException();
        }

        T[] tmp = (T[])new Comparable[ a.length ];
        System.arraycopy(a, 0, tmp, 0, a.length);   // 拷贝数组

        quickSelect(tmp, 0, a.length - 1, k);   // 折半的快速排序

        return tmp[k - 1];
    }
    /**
     * 从一个任意数组的[left, right]部分查询第k个最大元并返回（可转化为求第 a.length - k + 1 个最小元的问题）
     * @param a 一个任意数组
     * @param k
     * @return 返回数组中第k个最大元
     */
    public static <T extends Comparable<? super T>> T  quickMaxSelect(T[] a, int k) {
        if(a == null) {
            throw new NullPointerException();
        }
        if(a.length == 0 || k <= 0 || k > a.length) {
            throw new UnderflowException();
        }

        T[] tmp = (T[])new Comparable[ a.length ];
        System.arraycopy(a, 0, tmp, 0, a.length);   // 拷贝数组

        quickSelect(tmp, 0, a.length - 1, a.length - k + 1);    // 折半的快速排序

        return tmp[a.length - k];
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



    /********************************** 快速选择的内部方法 **********************************/
    /**
     * 返回左端、右端、中心位置三者的中值, 并按升序交换三者位置
     * @param a
     * @param left
     * @param right
     */
    private static <T extends Comparable<? super T>> T median3(T[] a, int left, int right) {
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
     * 快速选择的内部方法
     * @param a
     * @param left
     * @param right
     * @param k
     */
    private static <T extends Comparable<? super T>> void quickSelect(T[] a, int left, int right, int k) {
        if(left + CUTOFF <= right) {
            T pivot = median3(a, left, right);

            int i = left, j = right - 1;
            for( ; ; ) {
                while(a[++i].compareTo(pivot) < 0){ }
                while(a[--j].compareTo(pivot) > 0){ }
                if(i < j) {
                    swapReferences(a, i, j);
                } else {
                    break;
                }
            }

            swapReferences(a, i, right - 1);

            if(k <= i) {
                quickSelect(a, left, i - 1, k);
            } else if(k > i + 1) {
                quickSelect(a, i + 1, right, k);
            }
        } else  {
            Sort.insertionAscSort(a, left, right);
        }
    }
}
