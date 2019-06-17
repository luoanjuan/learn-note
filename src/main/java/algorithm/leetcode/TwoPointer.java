package algorithm.leetcode;

public class TwoPointer {

    public static void main(String[] args) {

    }

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

   public static void findLongWord(String longWords, String[] s){

   }
}
