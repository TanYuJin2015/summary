package hashing;

public class UniversalHash {
    private static final int DIGS = 31;
    private static final int mersennep = (1 << DIGS) - 1;   // 梅森素数, 相当于 2^31 - 1

    /**
     * 通用散列函数（卡特-韦格曼绝招）
     * @param x
     * @param A 任取, 只要满足：1<= A <= p-1, p为比最大输入键值大的一个素数
     * @param B 任取, 只要满足：1<= B <= p-1, p为比最大输入键值大的一个素数
     * @param M 散列表长度
     */
    public static int universalHash(int x, int A, int B, int M) {
        long hashVal = (long)A * x + B;

        hashVal = ((hashVal >> DIGS) + (hashVal & mersennep));  // 取模运算 用 位移运算 和 一次加法 来完成
        if(hashVal >= mersennep) {
            hashVal -= mersennep;
        }

        return (int)hashVal % M;
    }
}
