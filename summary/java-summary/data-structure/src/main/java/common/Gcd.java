package common;

public class Gcd {

    /**
     * 欧几里得算法
     * 传入两个整数, 计算这两个整数的最大公因数
     * 时间复杂度：O(logN)
     * @param m 整数1
     * @param n 整数2
     */
    public static long gcd(long m, long n) {
        while(n != 0) {
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }
}
