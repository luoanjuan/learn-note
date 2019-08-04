package algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 1 可以用动态规划的不要用递归去做，动态规划是存储计算第n步需要的前面步骤的值，分治算法是前面的值每次也需要去算
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */



public class TwoPointer {

    /**
     *在有序数组中找出两个数，使它们的和为 target。
     * @param target
     * @param numbers
     */
    public static void twoSum(int target, int[] numbers){
        if(numbers == null || numbers.length == 0){
            System.out.println("没有满足要求的两个数");
            return;
        }
        int smallPoint = 0, bigPoint = numbers.length-1;
        int sum ;
        while (smallPoint < bigPoint){
            sum = numbers[smallPoint] + numbers[bigPoint];
            if (sum == target) {
                System.out.println("两个数为" + numbers[smallPoint] + "、" + numbers[bigPoint]);
                return;
            } else if(sum < target){
                smallPoint ++;
            } else {
                bigPoint--;
            }
        }
        System.out.println("没有满足要求的两个数");
   }


    /**
     * 使数组的奇数在偶数的前面，且偶数和偶数，奇数和奇数的相对位置不变
     * @param array
     */
   public static void reOrderArray(int[] array){
        int jishu = array.length - 1, oushu = jishu,swap;
        for(int i = array.length-1; i >0; i--){
            if(array[i]%2==0){
                oushu = i;
            }else {
                jishu = i;
            }
            if(oushu < jishu){
                swap = array[jishu];
                array[jishu] = array[oushu];
                array[oushu] = swap;
            }
       }
   }

    /**
     * 反转链表
     */

   public static class Node{
       Node next;
       int value;

       public Node(int value){
           this.value = value;
       }

       public Node(int value, Node node){
           this(value);
           this.next = node;
       }
   }
   public static Node reverseList(Node head){
       if(head.next == null){
           return head;
       }
        Node next = head.next;
        head.next = null;
        Node newHead = reverseList(next);
        next.next = head;
        return newHead;
   }
   public static Node reverseList1(Node head){
       Node node = head;
       while (head.next!=null){
           head = head.next;
           head.next = node;
           node = head;
       }
       return node;
   }

    /**
     * 合并两个已排序的链表
     */
    // 迭代
    public static Node mergeOrdered(Node one, Node two){
        Node headOne = one, headTwo = two, node = new Node(-1), newHead = node;
        while ( headTwo != null&&headOne != null){
            if(headOne.value <= headTwo.value){
                node.next = headOne;
                headOne = headOne.next;
            }else {
                node.next = headTwo;
                headTwo = headTwo.next;
            }
            node = node.next;
        }
        if(headOne != null){
            node.next = headOne;
        }
        if(headTwo != null){
            node.next = headTwo;
        }

        return newHead.next;
    }
    //递归
    public static Node mergeOrdered1(Node one, Node two){
        if(one == null){
            return two;
        }
        if(two == null){
            return one;
        }
        if(one.value <= two.value){
            one.next = mergeOrdered(one.next, two);
            return one;
        }else {
            two.next = mergeOrdered(one, two.next);
            return two;
        }
    }

    public class TreeNode{
        TreeNode left;
        TreeNode right;
        int value;
    }
    /**
     * 判断A树是不是B树的子树
     */
    public static boolean subTree(TreeNode one, TreeNode two){
        if(one == null || two == null){
            return false;
        }
        return isSubTree(one,two) || isSubTree(one.left, two) || isSubTree(one.right, two);
    }

    public static boolean isSubTree(TreeNode one, TreeNode two){
        if(one == null && two == null){
            return true;
        }
        if(one == null || two == null || one.value != two.value){
            return false;
        }
        return isSubTree(one.left, two.left) && isSubTree(one.right, two.right);
    }


    /**
     * 顺时针打印矩阵
     */
    public static void printMatrix(int[][] matrix){
        int n = matrix.length;
        printSubMatrix(matrix,0,n-1);

    }


    public static void printSubMatrix(int[][] matrix, int begin, int end){
        if(end >= begin){
         for(int i = begin; i<=end; i++){
             System.out.println(matrix[begin][i]);
         }
         for(int i = begin+1; i<=end; i++){
             System.out.println(matrix[i][end]);
         }
         for(int i = end -1; i>= begin; i--){
             System.out.println(matrix[end][i]);
         }
         for(int i = end-1; i>begin; i--){
             System.out.println(matrix[i][begin]);
         }
         printSubMatrix(matrix, begin+1, end-1);
        }
    }


    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左到右
     */
    public static void printBinaryTree(TreeNode treeNode){
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(treeNode);
        while (!list.isEmpty()){
            TreeNode treeNode1 =  list.getFirst();
            System.out.println(treeNode1.value);
            if(treeNode1.left != null){
               list.addLast(treeNode1.left);
            }
            if(treeNode1.right != null){
                list.addLast(treeNode1.right);
            }
        }
    }

    public static void VerifySquenceOfBST(TreeNode treeNode, int[] squence){

    }

    public static void getSquenceOfBST(TreeNode treeNode, List<TreeNode> aa){
        if(treeNode.right == null){
            aa.add(treeNode);
        }else {
            getSquenceOfBST(treeNode.right, aa);
            aa.add(treeNode);
        }
        getSquenceOfBST(treeNode.left, aa);
    }

    /**
     * 二叉树中和为某一个值的路径
     */
    public static ArrayList<ArrayList<TreeNode>> findPath(TreeNode treeNode, int target){
        ArrayList<ArrayList<TreeNode>> arrayLists = new ArrayList<>();
        findPathInternal(treeNode, target, new ArrayList<>(), arrayLists);
        return arrayLists;
    }

    public static void findPathInternal(TreeNode treeNode, int target, ArrayList<TreeNode> arrayList, ArrayList<ArrayList<TreeNode>> ret){
        if(treeNode == null){
            return;
        }
        arrayList.add(treeNode);
        target -= treeNode.value;

        if (target == 0 && treeNode.left == null && treeNode.right == null){
            ret.add(new ArrayList<>(arrayList));
        } else if (target > 0) {
            findPathInternal(treeNode.left, target, arrayList, ret);
        }
        arrayList.remove(arrayList.size()-1);
    }


    public static class RamdonListNode{
        int value;
        RamdonListNode next;
        RamdonListNode ramdon;

        public RamdonListNode(int lable){
            this.value = lable;
        }

        public RamdonListNode(int lable, RamdonListNode next){
            this(lable);
            this.next = next;
        }
    }
    /**
     * 复杂链表复制，每个结点有两个指针，一个指向下一个结点，一个指向任意结点
     */
    public static RamdonListNode copyComplexList(RamdonListNode ramdonListNode){
        RamdonListNode head = ramdonListNode;
        while(head != null){
            head.next = new RamdonListNode(head.value, head.next);
            head = head.next.next;
        }
        head = ramdonListNode;
        while (head != null){
            if(head.ramdon!= null){
                head.next.ramdon = head.ramdon.next;
            }
            head = head.next.next;
        }
        head = ramdonListNode;
        RamdonListNode newNode = head.next;
        while (head.next != null){
            RamdonListNode node = head.next;
            head.next = head.next.next;
            node.next = node.next.next;
            head = head.next;

        }
        return newNode;
    }

    /**
     * 二叉搜索树与双向链表
     * 输入一棵二叉搜索树，将二叉搜索树转换成一个排序的双向链表，要求不创建任何新的结点
     */
    static TreeNode pre = null, head = null;
//    public static TreeNode convertToList(TreeNode treeNode){
//
//    }

    public static void inOrderConvert(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        inOrderConvert(treeNode.left);
        treeNode.left = pre;
        if(head == null){
            head = treeNode;
        }
        if(pre!= null){
            pre.left = treeNode;
        }
        pre = treeNode;
        inOrderConvert(treeNode.right);
    }

    /**
     * 字符串的排列
     */
//    ArrayList<String> arrayList = new ArrayList<>();
//    public static ArrayList<String> Permutation(String str){
//       char[]  chars = str.toCharArray();
//
//
//    }

    public static void permutation2(String string, char[] chars, ArrayList<String> arrayList){
        if(chars.length == 1){
            arrayList.add(string+chars[0]);
        }else {
//            for(char chars1: chars){
//                permutation2(string+chars1, );
//            }
        }
    }


    /**
     * 从1到n的整数中1出现的次数
     * 分别计算个位，十位、百位...的1各出现了多少次，分三种情况。例如百位分别是>=2; 1; 0
     *  cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0) 是对三种情况的概况
     * @param n
     * @return
     */
    public static int NumberOf1Between1AndN_Solution(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }

    /**
     *礼物的最大价值，在m*n的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值，从左上角开始拿礼物，每次向右或向下移动一格，求最大价值
     *
     * 动态规划，因为是向下和向右，求到达i行的某个地方的最大价值，只需存储i-1行的最大价值
     * @param values
     * @return
     */
    public static int getMost(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0)
            return 0;
        int n = values[0].length;
        int[] dp = new int[n];
        for (int[] value : values) {
            dp[0] += value[0];
            for (int i = 1; i < n; i++)
                dp[i] = Math.max(dp[i], dp[i - 1]) + value[i];
        }
        return dp[n - 1];
    }

    /**
     * 丑数
     * 把质因子只包含2,3,5的数称作为丑数，习惯上把1当做第一个丑数
     * @param N
     * @return
     */

    public static int GetUglyNumber_Solution(int N) {
        if (N <= 6)
            return N;
        int i2 = 0, i3 = 0, i5 = 0;
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2)
                i2++;
            if (dp[i] == next3)
                i3++;
            if (dp[i] == next5)
                i5++;
        }
        return dp[N - 1];
    }


    /**
     * 产生平方序列
     * @param n
     * @return
     */
    private static List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            squares.add(square);
            square += diff;
            diff += 2;
        }
        return squares;
    }


    /**
     * 数组中等差递增的区间个数
     */


    /**
     * 分隔整数的最大乘积
     */
    public static int getMaxMuti(int n){
        int[] result = new int[n+1];
        result[1] = 1;
        result[2] = 2;
        for(int i = 3; i<=n; i++){
            int max = i;
            for(int j=1; j<i; j++){
                max = max>j*result[i-j]?max:result[i-j]*j;
            }
            result[i] = max;
        }
        return result[n];
    }

    /**
     * 最长递增子序列,不是连续的
     *
     */

    static public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // dp[i],表示0-i个字符中最长递增子序列
        for (int i = 0; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }
        return Arrays.stream(dp).max().orElse(0);
    }

    /**
     * 搜索：广度优先深度优先
     * 广度优先：一般都是用队列存储当前访问行的下一行
     * 深度优先：递归
     *
     */

    /**
     * 动态规划 一般定义的一维或二维数组都是表示有前i个元素时问题的解，然后增加i+1这个元素的解dp[i+1]怎么用前面的解表示
     * 根据前面的解计算出后面的解
     */

    /**
     * 背包问题，0-1背包和完全背包
     * 0-1 背包问题是物品只能用一次，是有限的
     * 完全背包：物品可以无限使用
     *
     * 一般都是动态规划问题：需要设置辅助二维数组或一维数组，然后设置初始值（一般是第一位的值）
     * 都是以物品种类为外循环，目标为内循环，如果是0-1问题，内循环从大到小，如果是完全背包问题：内循环从小到大
     * 剩下是循环体：需要找到第j个数组值和前面数组值的关系。
     */

    /**
     * 有一个容量为 N 的背包，要用这个背包装下物品的价值最大，这些物品有两个属性：体积 w 和价值 v。
     * @param N 物品总量
     * @param W 背包体积
     * @param weights 重量数组
     * @param values  价值数组
     */
    public static void beibao(int N, int W, int[] weights, int[] values){
        int[][] dp = new int[N+1][W+1];
        for(int i = 1; i <= N; i++){
            int weight = weights[i-1];
            int value = values[i-1];
//            for()
        }
    }


    public static void main(String[] args) {
        System.out.println(minSteps(64));
    }

    /**
     * 将一个字符A复制粘贴到n个字符需要操作的次数，复制算一步，粘贴算一步，可复制多个
     * @param n
     * @return
     */
    public static int minSteps(int n) {
        int[] dp = new int[n + 1];
        int h = (int) Math.sqrt(n);
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= h; j++) {
                if (i % j == 0) {
                    // 将i/j这么多字符看成一个字符x，将x扩展到j个就是n个字符，所以就是将A扩展到i/j的次数加上扩展到j呗的次数
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }
        return dp[n];
    }

    public int minSteps1(int n) {
        if (n == 1) return 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return i + minSteps1(n / i);
        }
        return n;
    }


    /**
     * 需要冷却期的股票交易
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        int[] buy = new int[N];
        int[] s1 = new int[N];
        int[] sell = new int[N];
        int[] s2 = new int[N];
        s1[0] = buy[0] = -prices[0];
        sell[0] = s2[0] = 0;
        for (int i = 1; i < N; i++) {
            buy[i] = s2[i - 1] - prices[i];
            s1[i] = Math.max(buy[i - 1], s1[i - 1]);
            sell[i] = Math.max(buy[i - 1], s1[i - 1]) + prices[i];
            s2[i] = Math.max(s2[i - 1], sell[i - 1]);
        }
        return Math.max(sell[N - 1], s2[N - 1]);
    }


    public int maxProfit(int[] prices, int fee) {
        int N = prices.length;
        int[] buy = new int[N];
        int[] s1 = new int[N];
        int[] sell = new int[N];
        int[] s2 = new int[N];
        s1[0] = buy[0] = -prices[0];
        sell[0] = s2[0] = 0;
        for (int i = 1; i < N; i++) {
            buy[i] = Math.max(sell[i - 1], s2[i - 1]) - prices[i];
            s1[i] = Math.max(buy[i - 1], s1[i - 1]);
            sell[i] = Math.max(buy[i - 1], s1[i - 1]) - fee + prices[i];
            s2[i] = Math.max(s2[i - 1], sell[i - 1]);
        }
        return Math.max(sell[N - 1], s2[N - 1]);
    }


    /**
     * 只能进行两次交易
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;
        for (int curPrice : prices) {
            if (firstBuy < -curPrice) {
                firstBuy = -curPrice;
            }
            if (firstSell < firstBuy + curPrice) {
                firstSell = firstBuy + curPrice;
            }
            if (secondBuy < firstSell - curPrice) {
                secondBuy = firstSell - curPrice;
            }
            if (secondSell < secondBuy + curPrice) {
                secondSell = secondBuy + curPrice;
            }
        }
        return secondSell;
    }


    /**
     * 只能进行K次股票交易, 分状态，如 第i天可以进行购买，出售，冷冻，建立这一天和前面状态的关系
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2) {   // 这种情况下该问题退化为普通的股票交易问题
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
        int[][] maxProfit = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = maxProfit[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                maxProfit[i][j] = Math.max(maxProfit[i][j - 1], prices[j] + localMax);
                localMax = Math.max(localMax, maxProfit[i - 1][j] - prices[j]);
            }
        }
        return maxProfit[k][n - 1];
    }

    static int sum = 0;
    public static void getLeftSum(TreeNode head){
        if(head == null){
            return;
        }
        if(head.left != null && head.left.left == null && head.right == null){
            sum = sum + head.left.value;
        }
        getLeftSum(head.left);
        getLeftSum(head.right);
    }

    public static void getMaxSameNode(TreeNode node, int value){
        sum = Math.max(sum, value);
        if(node == null || (node.left == null && node.right == null) ){
            sum = Math.max(sum, value);
            return;
        }
        int nextValue = value + 1;
        if(node.left != null){
            if(node.left.value == node.value){
                getMaxSameNode(node.left, nextValue);
            }else {
                getMaxSameNode(node.left, 0);
            }
        }
        if(node.right != null){
            if(node.right.value == node.value){
                getMaxSameNode(node.right,nextValue);
            }else {
                getMaxSameNode(node.right, 0);
            }
        }
    }

    private int path = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return path;
    }

    private int dfs(TreeNode root){
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int leftPath = root.left != null && root.left.value == root.value ? left + 1 : 0;
        int rightPath = root.right != null && root.right.value == root.value ? right + 1 : 0;
        path = Math.max(path, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }


}


