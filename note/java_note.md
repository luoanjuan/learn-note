# tip

- 代码中可以使用label，label可以定义在循环前，假设存在两层循环，label定义在外层循环前，在内层循环使用 continue label直接跳过外层的本次循环或者使用break label可以直接跳出外层的循环。


# ThreadPoolExecutor
自带的四种拒绝策略：

- CallerRunsPolicy：在当前线程中执行
- AbortPolicy：抛异常
- DiscardPolicy：直接丢弃任务
- DiscardOldestPolicy：丢弃最旧的任务


# LongAdder
## Striped64
伪共享：缓存系统中是以缓存行为单位存储的。缓存行是2的整数幂个连续字节，一般为32-256个字节，最常见的是64个字节。当多个线程修改相互对立的变量时，如果这些变量共享同一个缓存行，就会无意中影响彼此的性能，这就是伪共享。
Striped64的设计的核心思路就是通过内部的分散计算来避免竞争。没有竞争的情况下，要累加的数通过cas累加到base上；如果有竞争的话，会将累加的数累加到Cells数组中的某个cell元素里面。所以整个Striped64的值是base加上各个cell中的值

