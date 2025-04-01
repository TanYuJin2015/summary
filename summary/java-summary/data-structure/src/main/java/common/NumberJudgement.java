package common;

public class NumberJudgement {
    /**
     * 判断数字是否为偶数
     * @param n
     */
    public static boolean isEven(int n) {
        return ((n & 1) == 0);
    }
    /**
     * 判断数字是否为奇数
     * @param n
     */
    public static boolean isOdd(int n) {
        return ((n & 1) == 1);
    }
    /**
     * 从n为开始寻找下一个素数
     * @param n
     */
    public static int nextPrime(int n) {
        if( n % 2 == 0 ) {
            n++;
        }
        for( ; !isPrime( n ); n += 2 ); // 从奇数中找到最近的一个素数

        return n;
    }
    /**
     * 判断n是否为素数
     * @param n
     */
    public static boolean isPrime(int n) {
        if( n == 2 || n == 3 ) {
            return true;
        }
        if( n == 1 || n % 2 == 0 ) {
            return false;
        }
        for( int i = 3; i * i <= n; i += 2 ) {  // 到sqrt(n)止
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
