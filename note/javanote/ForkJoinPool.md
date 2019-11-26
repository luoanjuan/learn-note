## Unsafe
- arrayBaseOffset 得到数组中第一个元素的偏移地址
- arrayIndexScale 得到数组中一个元素的增量地址，一个元素到下一个元素的增加地址。


## ForkJoinPool

- ForkJoinWorkerThread: 包装Thread
- WorkQueue：任务队列，双向
- ForkJoinTask：worker执行的对象，实现了Future。  
ForkJoinPool使用数组保存所有的WorkQueue，每个work而有属于自己的WorkQueue，但不是每个WorkQueue都有对应的worker。

- 没有worker的WorkQueue保存的是外部提交，在WorkQueue[]中下标是偶数  
- 属于woker的WorkQueue：保存的是task，在WorkQueue[]中下标是奇数  
WorkQueue是一个双端队列，同时支持LIFO的push和pop操作，和FIFO的poll操作。worker操作自己的WorkQueue是LIFO操作，除此之外，worker会尝试steal其他的
WorkQueue里的任务，这个时候执行的是FIFO操作。  

- LIFO操作只有对应的worker才能执行，push和pop不需要考虑并发
- 拆分时，越大的任务越在WorekQueue的base端，会尽早处理。

ctl是ForkJoinPool中的控制字段，按16bit为一组封装在一个long中：
- AC ：活动的worker数量
- TC ：总共的worker数量
- SS ：WorkQueue状态，第一位表示active还是inactive，其余十五位表示版本号（对付ABA）
- ID ：这里保存了一个WorkQueue在WorkQueue[]的下标，和其他worker通过字段stackPred组成一个TreiberStack。
  
AC和TC初始化时取的是parallelism负数，后续代码可以直接判断正负，为负代表还没有达到目标数量。另外ctl低32位有个技巧可以直接用sp=(int)ctl取得，为负代表存在空闲worker

- 任务提交
    - 提交任务入口：submit,execute,invoke
    - 完整版提交任务：externalSubmit(包括初始化)
    - 简单版提交任务：externalPush

- worker管理
    - 激活或创建：signalWork
    - 创建：tryAddWorker,createWorker
    - 注册、撤销注册：registerWorker,deregisterWorker
- worker执行(runWorker三部曲)
    - 获取：scan
    - 执行：runTask
    - 等待：awaitWork

- Fork
    - 等同于提交任务
- Join(doJoin)
    - 当前不是worker：externalAwaitDone
    - 当前是worker：awaitJoin

- awaitJoin等待两种策略
    - Helping：tryRemoveAndExec、helpStealer
    - Compensating：tryCompensate

- 等待所有任务完成
    - 静止：awaitQuiescence
    - 终止：awaitTermination

- 关闭
    - shutdown,shutdownNow
    - tryTerminate

ForkJoinPool#externalSubmit
第一次执行externalSubmit时候：

- 第一次循环，运行状态还没有STARTED，进行初始化操作：设置WorkQueue长度，社会组原子对象stealCounter，运行状态进入STARTED。
- 第二次循环进入第三个else if，创建第一个WorkQueue。
- 第三次循进入第二个else if，找到当创建的WorkQueue，从队列的top端加入任务，调用后面要将的signalWork集合或者创建worker。

ForkJoinPool#signalWork
首先是进入循环的条件，判断了ctl的正负，我们知道ctl的第一个16bit表示AC，为负时表示活动的worker还未达到预定的Parallelism，需要新增或者激活。mark1通过sp判断现在没有空闲worker，
需要执行增加，调用tryAddWorker。有空闲worker的情况进入解挂worker的过程，sp取栈顶WorkQueue的下标，在sp上，将状态从inactive改为active，累加版本号，解挂线程，通过stackPred
取得前一个WorkQueue的index，设回sp里。


### ForkJoinTask
status默认为0，当得出结果时变为负数：
  
- NORMAL
- CANCELLED
- EXCEPTIONAL

### WorkQueue
scanState描述WorkQueue当前状态 
 
- 偶数表示RUNNING
- 奇数表示SCANNING
- 负数表示inactive


### WorkQueues
很多操作都是关于work-stealing queues，它们是双端队列(deque)的特殊形式：仅支持四个端点操作的三个poll,push,pop(另外一个是
remove？都是操作deque的first元素)，并且存在如下限制：push和pop只能被队列所属的线程调用，poll是被其他线程调用。主要不同在
于GC需求，我们需要经可能快的消费并清除队列元素，以保持尽可能小的占用空间，即使在生成大量任务的程序中也是如此。为了满足这
个要求，我们将CAS操作从poll和pop中转移到队列中元素本身（通过base和top两个索引）。  
因为依赖引用索引的CAS情况，所以不需要再队列的队头和队尾使用标记位，索引是在基于数组循环队列中使用的简单整数。更新索引保证
top == base时队列为空。但是push、pop、或者poll操作没有完全完成的时候，可能会错误的出现队列看起来非空的情况。考虑这种情况，
poll操作是有等待的（有循环）。一个窃贼在另一个窃贼完成之前不能继续，但是总的来说保证了阻塞的概率。如果盗窃失败，小偷总是
随机选择下一个队列进行尝试。任何正在进行的轮询或空队列新的push都可以让一个窃贼继续前进。（这也是为什么使用pollAt方法，它
失败后会尝试其他方法，不像poll方法会进行重试）。  
这个方法同时支持本地任务先进先出，而不是后进先出，使用poll而不是pop即可。这在没有任务join的情景中很有用。

