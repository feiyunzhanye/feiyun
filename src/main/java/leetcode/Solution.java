package leetcode;

import javafx.scene.Node;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Solution {
    public int test;


    /**
     * 最长回文字符串
     * 选取元素为中心向左右扩展找到最大的回文
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        String pd = "";
        for (int i = 0; i < len; i++) {
            int j = 0;
            String s1 = lp(s, i, i);
            String s2 = lp(s, i, i + 1);
            String s3 = s1.length() > s2.length() ? s1 : s2;
            pd = pd.length() > s3.length() ? pd : s3;
        }

        return pd;
    }

    public String lp(String s, int left, int right) {
        String pd1 = "";
        while (true) {
            if (left < 0 || right >= s.length()) {
                break;
            }
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            if (s.charAt(left) == s.charAt(right)) {

                String s2 = s.substring(left, right + 1);
                if (s2.length() > pd1.length()) {
                    pd1 = s2;
                }
            }
            left--;
            right++;
        }
        return pd1;
    }

    /**
     * 两个正序数组中位数
     *
     * @param
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int numsLen = nums1.length + nums2.length;
        double med = 0;
        if (numsLen % 2 == 0) {
            int i = 0;
            int j = 0;
            int midLen1 = numsLen / 2;
            int midLen0 = midLen1 - 1;
            int mid0 = 0;
            int nums = 0;
            while (i < nums1.length || j < nums2.length) {
                int flag = 0;
                if (i >= nums1.length || (j < nums2.length && nums1[i] >= nums2[j])) {
                    flag = nums2[j];
                    j++;
                } else {
                    flag = nums1[i];
                    i++;
                }
                if (nums == midLen0) {
                    mid0 = flag;
                } else if (nums == midLen1) {
                    med = (double) (mid0 + flag) / 2;
                    break;
                }
                nums++;

            }
        } else {
            int i = 0;
            int j = 0;
            int midLen0 = numsLen / 2;
            int nums = 0;
            while (i < nums1.length || j < nums2.length) {
                int flag = 0;
                if (i >= nums1.length || (j < nums2.length && nums1[i] >= nums2[j])) {
                    flag = nums2[j];
                    j++;
                } else {
                    flag = nums1[i];
                    i++;
                }
                if (nums == midLen0) {
                    med = (double) flag;
                    break;
                }
                nums++;
            }
        }
        return med;
    }

    public void test() {
        System.out.println("测试");
    }

    /**
     * 二叉树转单向链表
     *
     * @param
     */
    public static void flatten(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            TreeNode cur = node.left;
            while (cur.right != null) {
                cur = cur.right;
            }
            cur.right = node.right;
            node.right = node.left;
            node.left = null;

        }
        flatten(node.right);
    }

    /***
     * 快速排序
     * 比较交换
     *
     * @param
     */
    public static void kq0(int[] a) {
        kq(a, 0, a.length - 1);
        System.out.println(a);
    }

    public static void kq(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int flag = a[start];
        int i = start;
        int j = end;
        while(i != j){
            while(i < j && a[j] >= flag){
                j--;
            }
            while(i < j && a[i] <= flag){
                i++;
            }
            if(i < j){
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }

        }
        a[start] = a[i];
        a[i] = flag;
        kq(a, start, i - 1);
        kq(a, i + 1, end);
    }

    /**
     * 判断能否形成等差数列
     */
    public static boolean dc(int[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] * 2 != arr[i - 1] + arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 交换k次获取最小字符串
     *
     * @param num
     * @param k
     * @return
     */
    public static String minInteger(String num, int k) {
        /**
         * 思路：
         * 首先我先根据0-9存储所有的数
         * 从最小的数遍历
         * 找到最小的数的位置如果位置大于k 则找下一个
         * 找到后插入到位置前
         */
        char[] chars = num.toCharArray();
        int[] t = new int[10];
        for (char aChar : chars) {
            int n = (int) aChar - (int) '0';
            t[n] = t[n] + 1;
        }
        //定义起始需要移动的坐标
        int pre = 0;
        //定义数用完的标记

        while (k > 0) {
            boolean end = true;
            for (int i = 0; i < t.length;) {
                if (t[i] != 0) {
                    end = false;
                    for (int j = 0; j < chars.length; j++) {
                        if (k < j - pre) {
                            i++;
                            break;
                        }
                        //得到需要移动的位置
                        if ((int) chars[j] -(int)'0' == i) {
                            //移动位置
                            yd(chars,pre,j);
                            k = k - (j - pre);
                            pre++;
                            t[i] = t[i] - 1;
                            break;
                        }
                    }
                }

                if( k == 0){
                    break;
                }
                if(t[i] == 0){
                    i++;
                }

            }
            if(end){
                break;
            }
        }
        System.out.println(String.valueOf(chars));
        return String.valueOf(chars);
    }

    public static void yd(char[] a, int start, int end) {
        char flag = a[end];
        for(int j = end;j>0;j--){
            a[j] = a[j-1];
        }
        a[start] = flag;
    }

    /**
     * 二分查找
     * @param
     */
    public static int binarySearch2(int[] array, int a, int lo, int hi){

        if(lo <= hi){
            int mid = (lo + hi) /2;
            if(array[mid] == a){
                return mid;
            }else if(array[mid] < a){
                return binarySearch2(array,a,mid+1,hi);
            }else if(array[mid] > a){
                return binarySearch2(array,a,lo,mid-1);
            }
        }
        return -1;

    }

    /**
     * 合并两个正序数组
     * @param
     */
    public static int[] hb(int[] a ,int[] b){
        int i = 0;
        int j = 0;
        int x = 0;
        int[] c = new int[a.length + b.length];
        while(i<a.length || j<b.length){
            if(j>= b.length || (i<a.length &&a[i] < b[j])){
                c[x] = a[i];
                x++;
                i++;
            }else{
                c[x] = b[j];
                x++;
                j++;
            }
        }
        return c;
    }

    /**
     * 链表反转
     */
    public class Node {

        private Object data;//数据域
        private Node next;//指针域

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 链表反转
     * @param node
     * @return
     */
    public static Node reverseListNode(Node node){
            Node pre = null;
            Node cur = node;
            Node next = null;
            while(cur != null){
                next = node.getNext();
                cur.setNext(pre);
                pre = cur;
                cur = next;
            }
            return pre;
    }

    /**
     * 设计缓存结构LRU结构
     * @param args
     */
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     * 设计一个int[]存储每个key的顺序
     * 用hashMap存key value
     */
    public static int[] LRU(int[][] operators, int k) {

        //用LinkHashMap 记录插入顺序
        LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<>();

        //记录输出的结果
        ArrayList<Integer> list = new ArrayList<>();

        for (int[] a : operators) {
            if (a.length == 0) {
                continue;
            }
            /**
             * put数据时 如果为空 直接插入，如果已存在先删除，如果不存在，超了，删除最早的插入，没超插入
             */
            if (a[0] == 1) {
                if(linkedHashMap.containsKey(a[1])){
                    linkedHashMap.remove(a[1]);
                }
                if(linkedHashMap.size() >= k){
                    linkedHashMap.remove(linkedHashMap.keySet().toArray()[0]);
                }
                linkedHashMap.put(a[1],a[2]);
            }
            //get数据把获取到的数据存进去
            //并且重新插入进去
            else if (a[0] == 2) {

                int res = -1;
                if(linkedHashMap.containsKey(a[1])){
                    res = linkedHashMap.get(a[1]);
                    linkedHashMap.remove(a[1]);
                    linkedHashMap.put(a[1],res);
                }
                list.add(res);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 排序
     */
    public static void sortLRU(int[] a,HashMap<Integer,Integer> hashMap,int key){
        if(!hashMap.containsKey(key)){
            hashMap.put(key, 0);
        }
        int index = hashMap.get(key);
        for(int i = index;i>0;i--){
            a[index] = a[index-1];
            hashMap.put(a[index],index);
        }
        a[0] = key;
    }
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }
    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders (TreeNode root) {
        TreeNode node1 = root;
        TreeNode node2 = root;
        TreeNode node3 = root;
        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
        List<Integer> a3 = new ArrayList<>();
        preOrder(node1,a1);
        inOrder(node2,a2);
        postOrder(node3,a3);
        int[][] a = new int[3][];
        a[0] = a1.stream().mapToInt(Integer::intValue).toArray();
        a[1] = a2.stream().mapToInt(Integer::intValue).toArray();
        a[2] = a3.stream().mapToInt(Integer::intValue).toArray();
        return a;

    }

    /**
     * 前序遍历
     * @param node
     * @param a
     */
    public static void preOrder(TreeNode node, List<Integer> a){
        if(node == null){
            return;
        }
        a.add(node.val);
        preOrder(node.left,a);
        preOrder(node.right,a);
    }

    /**
     * 中序遍历
     * @param node
     * @param a
     */
    public static void inOrder(TreeNode node,List<Integer> a){
        if(node == null){
            return;
        }
        inOrder(node.left,a);
        a.add(node.val);
        inOrder(node.right,a);

    }

    public static void postOrder(TreeNode node ,List<Integer> a){
        if(node == null){
            return;
        }
        postOrder(node.left,a);
        postOrder(node.right,a);
        a.add(node.val);
    }

    /**
     * 找出最小的k个数
     * @param k
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if(k > input.length){
            return new ArrayList<Integer>();
        }
        Arrays.sort(input);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i= 0;i<k;i++){
            list.add(input[i]);
        }
        return list;
    }
    /**
     * 层序遍历
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList<>>
     */
    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        //记录每一层的数据
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0;i<size;i++){
               TreeNode node =  queue.poll();
               if(node == null){
                   break;
               }
               list.add(node.val);
               if(node.left != null){
                   queue.offer(node.left);
               }
               if(node.right != null){
                   queue.offer(node.right);
               }
            }
            lists.add(list);
        }
        return lists;
    }

    /**
     * 找出最大的K数
     */
    public int findKth(int[] a, int n, int K) {
        // write code here
        findK(a,0,n-1,n-K);
        return a[n-K];
    }
    public static void findK(int[] a,int start,int end,int K){
        if(a.length == 0){
            return;
        }
        int flag = a[start];
        int i = start;
        int j = end;
        while(i != j){
            while(i<j && a[j] >= flag){
                j--;
            }
            while(i<j && a[i]<= flag){
                i++;
            }
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        a[start] = a[i];
        a[i] = flag;

        if(K == i){
            return;
        }else if(K < i){
            findK(a,start,i-1,K);
        }else{
            findK(a,i+1,end,K);
        }

    }

    /**
     *
     * @param numbers int整型一维数组
     * @param target int整型
     * @return int整型一维数组
     */
    public static int[] twoSum (int[] numbers, int target) {
        int[] res = new int[2];
        // write code here
        for(int i = 0;i<numbers.length;i++){
            for(int j = 1;j<numbers.length;j++){
                if(numbers[i] + numbers[j] == target){

                    if(numbers[i] < numbers[j]){
                        res[0] = i+1;
                        res[1] = j+1;

                    }else{
                        res[1] = i+1;
                        res[0] = j+1;
                    }
                    return res;
                }
            }

        }
        return res;
    }
    public static int[] twoSum2 (int[] numbers, int target) {
        int[] res = new int[2];
        // write code here
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<numbers.length;i++){
            if(!map.containsKey(numbers[i])){
                map.put(target - numbers[i],i+1);
            }else {
                res[0] = map.get(numbers[i]);
                res[1] = i+1;
                return res;
            }
        }
        return res;
    }

    /**
     *
     * @return ListNode类
     */
    public class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val){
            this.val = val;
        }
    }
    public ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        // write code here
        ListNode node0 = new ListNode(0);
        ListNode node = node0;
        while(l1!=null && l2!=null){
            if(l1.val < l2.val){
                node.next = l1;
                node = node.next;
                l1 = l1.next;
            }else{
                node.next = l2;
                node = node.next;
                l2 = l2.next;
            }
        }
        if(l1 == null){
            node.next = l2;
        }
        if(l2 == null){
            node.next = l1;
        }

        return node0.next;
    }
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {

       if(stack2.isEmpty()){
           while(!stack1.isEmpty()){
               stack2.push(stack1.pop());
           }
       }
       return stack2.pop();

    }

    /**
     * 小跳蛙
     * n = (n-1) + (n-2)
     * @param
     */
    public int jumpFloor(int target) {
        if(target == 1){
            return 1;
        }else if(target == 2){
            return 2;
        }else if(target == 0){
            return 0;
        }
        return jumpFloor(target-1) + jumpFloor(target-2);
    }

    /**
     * max sum of the subarray
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxsumofSubarray (int[] arr) {
        // write code here
        int res = 0;
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            res = j + res;
            if (res < 0) {
                res = 0;
            }
            max = Math.max(res, max);
        }
        return max;
    }

    /**
     *
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;
        while(head != null){
            ListNode tail = pre;
            for(int i = 0;i<k;++i){
                tail = tail.next;
                if(tail == null){
                    return hair.next;
                }
            }
            ListNode temp = tail.next;
            ListNode[] rev = reverse(pre.next,tail);
            pre.next = rev[0];
            pre = rev[1];
            pre.next = temp;
            head = temp;
        }
        return hair.next;

    }
    public ListNode[] reverse(ListNode start,ListNode end){
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while(cur != end){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return new ListNode[]{cur,start};
    }

    public boolean hasCycle(ListNode head) {
        HashMap<ListNode,Integer> map = new HashMap<>();
        while(head != null){
            if(map.containsKey(head)){
                return true;
            }
            map.put(head,0);
            head = head.next;
        }
        return true;
    }
    public boolean hasCycle2(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 合并两个有序的数组
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public static void merge(int A[], int m, int B[], int n) {
        int i = m-1;
        int j = n-1;
        int index = m+n-1;
        while(i>=0 && j>=0){
            A[index--] = A[i] > B[j] ? A[i--] : B[j--];
        }
        while(j>=0){
            A[index--] = B[j--];
        }
    }

    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> listNodes = new HashSet<>();
        while(head != null){
            if(listNodes.contains(head)){
                return head;
            }
            listNodes.add(head);
            head = head.next;
        }
        return null;
    }

    /**
     *
     * @param s string字符串
     * @return bool布尔型
     */
    public boolean isValid (String s) {
        // write code here
        Stack<Character> queue = new Stack<>();
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if( c == '(' || c == '{' || c =='['){
                queue.push(c);
            }else {
                if(queue.isEmpty()){
                    return false;
                }
                switch (c){
                    case ')':{
                        if(queue.peek() == '('){
                            queue.pop();
                        }
                        break;
                    }
                    case ']':{
                        if(queue.peek() == '['){
                            queue.pop();
                        }
                        break;
                    }
                    case '}':{
                        if(queue.peek() == '{'){
                            queue.pop();
                        }
                        break;
                    }
                }
            }
        }
        return queue.isEmpty();

    }

    /**
     *
     * @param head ListNode类
     * @param n int整型
     * @return ListNode类
     * 两个指针相邻n，先记录链表的头
     */
    public ListNode removeNthFromEnd (ListNode head, int n) {
        // write code here
        if(head == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        for(int i=0;i<n;i++){
            fast = fast.next;
        }
        if(fast == null){
            return head.next;
        }
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;

    }
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("A");
        int[] a = {};
        int[] b = {1};
        int[][] c = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        Solution.merge(a,0,b,1);
    }


}
