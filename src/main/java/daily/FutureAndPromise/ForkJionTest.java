package daily.FutureAndPromise;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJionTest extends RecursiveTask<Integer> {

    private static final int THREAD_HOLD = 2;
    private int start;
    private int end;

    public ForkJionTest(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end -start) <= THREAD_HOLD;
        if(canCompute){
            for(int i = start; i <= end; i++){
                sum += i;
            }
        }else {
            int middle = (start + end)/2;
            ForkJionTest left = new ForkJionTest(start, middle);
            ForkJionTest right = new ForkJionTest(middle + 1, end);
            left.fork();
            right.fork();
            int lsum = left.join();
            int rsum = right.join();
            sum = lsum + rsum;
        }
        return sum;
    }

    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        ForkJionTest task = new ForkJionTest(1,4);
        Future<Integer> result = pool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException| ExecutionException e) {
            e.printStackTrace();
        }
    }
}
