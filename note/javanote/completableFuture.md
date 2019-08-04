## CompletableFuture

个人觉得CompletableFuture和Collection的Stream主要是为了实现流式编程。CompletableFuture可以提供异步计算，异步计算完成后再调用其他方法，所以也可以实现事件通知。

CompletableFuture中有属性Completion stack; stack决定了两条线。
- stack一般是UniCompletion的子类实现，具有src、des和next属性。src和des都是CompletableFuture类型，执行完CompletableFuture的stack中定义的运算后，
结果会存储在des，然后执行des中的stack。

- stack中des的链执行完后会执行stack的next中的任务。  

如果执行到任务时候，前面的执行已经完成，已经拿到前面执行的结果，直接执行。如果前面异步操作还没有执行完成，将这个操作封装成UniCompletion，添加到上一个CompletableFuture的stack中，
由前面的异步线程执行该任务。


## Stream

Stream是通过将操作封装Sink的子类，一个Stream的执行链，是从后往前封装的，但是是从前往后执行的。将每个操作封装成Sink主要是屏蔽每个操作的差异性。
