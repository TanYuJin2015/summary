package disjoint;

public class DisjSets {
    private int[] s;

    /**
     * 构造函数
     * @param numElements 不相交集类数组的初始化大小
     */
    public DisjSets(int numElements) {
        s = new int[numElements];
        for(int i = 0; i < s.length; i++) {
            s[i] = -1;
        }
    }

    /**
     * 按高度（秩）求并, 合并两个不相交集
     * @param root1
     * @param root2
     */
    public void union(int root1, int root2) {
        if(s[root2] < s[root1]) {
            // root2深度更大, 则root2作为新的根, root1作为子树
            s[root1] = root2;
        } else {
            if(s[root1] == s[root2]) {
                s[root1]--;
            }
            s[root2] = root1;
        }
   }

   /**
    * 查找包含x的集合（采用了路径压缩）
    * @param x
    * @return 包含x的集合
    */
    public int find(int x) {
        if(s[x] < 0) {
            return x;
        } else {
            return s[x] = find(s[x]);
        }
    }

}
