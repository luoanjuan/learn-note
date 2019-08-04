## Thread基础 
java线程在运行的声明中可能处于以下状态  

| 状态名称 | 说明|
|---|---|
|NEW|初始状态，线程被构建，还没有调用start()方法|
|RUNABLE| 运行状态，java线程将操作系统中的就绪和运行两种状态统称为“运行中”|
|BLOCKED|阻塞状态，表示线程阻塞于锁|
|WAITING|等待状态，表示线程进入等待状态，进入该状态的线程需要其他线程做一些特定的动作|
|TIME_WAITING|超时等待状态|
|TERMINATED|终止状态|

#### Daemon线程
Daemon线程是一种支持型线程，因为主要被用于程序中后台调度以及支持性工作。Daemon属性需要在启动线程之前设置，不能在启动线程之后设置。java虚拟机退出时finally块并不一定执行。

#### 中断
是线程的一个标识属性，其他线程可以通过调用该线程的interruppt()方法对其进行中断。线程可以通过检查自身是否被中断来进行响应。线程通过isInterruoted()来进行判断是否被中断，也可以调用静态方法Thread.interrupted()对当前线程中断标识位进行复位。如果线程处于终结状态，即使线程被中断过，调用isInterruoted()方法返回的也是false。java的API中声明了InterruptedException的方法都会受中断标识的影响，这些方法在抛出InterruptedException之前会重置中断标识。

#### 过期的suspend()、resume()、stop()
这些方法完成了线程的暂停、恢复、终止，已经不建议使用。调用suspend方法后，线下不会释放已经占有的资源（如锁），而是占有着资源进行睡眠状态，stop方法终止线程之前不会保证线程资源的正确释放。

#### 安全的终止线程
终端标识和boolean变量

#### synchronized关键字
任意一个对象都拥有自己的监视器，当这个对象由同步块或者这个对象同步方法调用时，执行方法的线程必须先获取到该对象的监视器才能进入同步块或者同步方法，而没有获取到监视器的线程会被阻塞在同步块和同步方法的入口处，进入BLOCKED状态，进入同步队列，等待其前驱线程释放锁。

#### 等待/通知机制

|方法名称|描述|
|---|---|
|notify()|通知一个在对象上等待的线程，使其从wait()方法返回|
|notifyAll()||
|wait()||
|wait(long||
|wait(long,int)||  
- 调用wait()方法是将当前线程防止到该对象的等待队列
- notify()和notifyAll()方法调用后，等待线程依旧不会从wait()方法返回，需要调用notify()或notifyAll()方法线程释放锁后，等待线程才用机会从wait返回
- notify()方法将等待队列中的一个线程从等待队列移到同步队列，notifyAll()方法会将所有线程从等待队列移到同步队列，线程状态从WAITING变成BLOCKING
- 从wait方法返回的前提是获得了调用对象的锁

#### 管道输入/输出
管道输入/输出流主要用于线程之间的数据传输，传输的媒介是内存。主要包括如下四种实现：PipedOutputStream、PipedInputStream、PipeReader和PipeWriter。就是一个线程在用PipeWriter写，另一个线程用PipeReader读。PipeWriter.connecet(PipeReader)

#### Thread.join
一个线程A执行了thread.join，当前线程A等待thread线程终止之后才从thread.join返回，在join方法中会调用thread的wait方法，在线程终止时会调用自身的notifyAll方法。

#### ThreadLocal
ThreadLocal是一个以ThreadLocal对象为键，任意对象为值的存储结构，这个结构被附带在线程上。

