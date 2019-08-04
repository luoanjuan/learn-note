## Reference 引用及其子类

- 软引用是用来描述一些还有用但是并非必需的对象，对于软引用关联着的对象，在系统要发生内存溢出异常之前，会把这些对象列进回收方法列进回收范围进行第二次回收，也就是内存不够才会进行回收
- 弱引用也是用来描述非必需对象，但是它的强度比软引用更弱一些，被弱引用关联的对象，只能生存到下一次的垃圾收集发生之前。
- 虚引用是最弱的一种引用，一个对象是否有虚引用完全不会影响其生存时间，也无法通过虚引用来取得一个对象的实例，为一个对象设置虚引用关联的唯一目的就是能在这个对象被收集器回收时收到一个
系统通知。

### Reference 是三种引用的父类

- referent: 即Reference所包装的引用对象
- queue: 此Reference需要注册到的引用队列。ReferenceQueue提供队列功能，保存了一个Reference类型的head节点，Reference封装了next字段，这样可以组成一个单向链表。

Reference对象的四种状态：

- active: GC会特殊对待此状态的引用，一旦被引用的对象的可达性发生变化，GC会将引用放入pending队列并将其状态改为pending状态
- pending ：加入pending-list中，如果Reference创建的时候没有注册到队列是不会有这种状态
- Enqueued ：加入了创建时候注册的队列中，如果Reference创建的时候没有注册到队列是不会有这种状态
- inactive ：引用从queue队列出队后

Reference里有个静态字段pending，同时还通过静态代码块启动Reference-handler thread。当一个Reference的referent被回收时，垃圾回收器会把reference添加到pending这个链表中，
然后Reference-handler thread不断的读取pending中的reference，把它添加到对应的ReferenceQueue中。

- 堆外内存使用的是虚引用，引用堆外内存的对象回收时，对象内的虚引用也会回收，然后进行堆外内存的回收工作。
- ThreadLocal使用了弱引用，Thread中的ThreadLocalMap中存储的key是ThreadLocal的弱引用，当ThreadLocal出了栈帧后，这个对应的key会被回收，但是value是不会回收的，这样是防止数组被无用的key和value占满，当新的ThreadLocal映射到这个位置时，因为key为null可以重新占用这个位置，旧的key对应的value的强引用也没有了。







