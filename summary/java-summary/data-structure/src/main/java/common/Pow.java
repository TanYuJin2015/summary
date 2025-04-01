package common;

public class Pow {
    /**
     * 递归算法处理一个整数的幂运算
     * 时间复杂度：O(logN)
     * @param x 底数
     * @param n 幂
     */
    public static long pow(long x, int n) {
        if(n == 0) {
            return 1;
        }
        if(n == 1) {
            return x;
        }
        if(isEven(n)) {
            return pow(x * x, n / 2);
        } else {
            return pow(x * x, n / 2) * x;
        }
    }

    /**
     * 判断数字是否为偶数
     * @param n
     */
    private static boolean isEven(int n) {
        return ((n & 1) == 0);
    }
}
