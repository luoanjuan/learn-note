#### Lock接口
Lock接口提供的synchronized关键字不具备的主要特性

|特性|描述|
|--|--|
|尝试非阻塞的获取锁|当前线程尝试获取锁，如果这一时刻没有被其他线程获取到，则成功获取锁|
|能被中断获取锁|获取锁的线程能够响应中断，当获取到所得线程被中断时，中断异常将会被抛出，同时锁会被释放|
|超时获取锁|在指定截止时间之前获取锁，如果超时则返回|

#### 队列同步器
队列同步器AbstractQueuedSynchronizer是用来构建锁或其他同步器组件的基础线程，它使用一个int成员变量表示同步状态，通过内置的FIFO队列来完成资源获取线程的排队工作。子类通继承同步器并实现它的抽象方法来管理同步状态，子类被推荐为定义同步组件的静态内部类。锁是面向使用者的，它定义了使用者与锁交互的接口，隐藏了实现细节；同步器是面向锁的实现者，它简化了锁的实现方法，屏蔽了同步状态管理、线程排队等细节。  
同步器的设计是基于模板方法模式的，使用者需要继承同步器并重写指定的方法，随后将同步器组合在自定义同步组件的实现中，并调用同步器提供的模板方法，这这些模板方法将调用使用者重写的方法。重写同步器指定的方法时需要使用同步器提供的如下3个方法来访问或修改同步状态：getState(),setState(int newState),compareAndSetState(int expect, int update)。  

同步器可重写的方法：

|方法名称|描述|
|--|--|
|tryAcquire(int arg)|独占式获取同步状态，实现该方法需要查询当前状态并判断同步状态是否符合预期，然后再进行CAS设置同步状态|
|tryRelease(int arg)|独占式释放同步状态，其他获取同步状态的线程将有机会回去同步状态|
|tryAcquireShared(int arg)|共享式获取同步状态，返回大于等于0的值表示获取成功，反之，获取失败|
|tryReleaseShared(int arg)|共享式释放锁|
|isHeldExclusively()|当前同步器是否在独占式模式想被线程占用|

同步器提供的模板方法：
  
|方法名称|描述|
|--|--|
|void acquire(int arg)|独占式获取同步状态，如果当前线程获取同步状态成功，则该方法返回，否则，将会进入同步队列等待，该方法会调用重写的tryAcquire(int arg)方法|
|void acquireInterruptobly(int arg)|与acquire相同，但是该方法响应中断，当前线程为获取到同步状态而进入同步队列中，如果当前线程被中断，该方法会抛出InterruptedException并返回|
|boolean tryAcquireNanos（int args， long nanos)|在acquireInterruptobly(int arg)的基础上添加了超时限制|
|void acquireShared(int arg)|共享是获取同步状态，如果线程未获取到同步状态，将会进入同步队列等待，与独占式获取的主要去呗是在同一时刻可以有过个线程获取同步状态|
|void acquireSharedInterruptobly(int arg)||
|boolean tryAcquireSharedNanos（int args， long nanos)||
|boolean release(int arg)|独占式释放同步状态，该方法会在释放同步状态的基础上，将同步队列中第一个节点包含的线程唤醒|
|boolean releaseShared(int arg)|共享式释放同步状态|
|Collection<Thread> getQueuedThreads()|获取等待在同步队列上的线程集合|