## LongAdder
## Striped64
伪共享：缓存系统中是以缓存行为单位存储的。缓存行是2的整数幂个连续字节，一般为32-256个字节，最常见的是64个字节。当多个线程修改相互对立的变量时，如果这些变量共享同一个缓存行，就会无意中影响彼此的性能，这就是伪共享。
Striped64的设计的核心思路就是通过内部的分散计算来避免竞争。没有竞争的情况下，要累加的数通过cas累加到base上；如果有竞争的话，会将累加的数累加到Cells数组中的某个cell元素里面。所以整个Striped64的值是base加上各个cell中的值  
缓存行是以什么为单位去获取填充的？如果只获取一个对象的一个属性，那么只会把这个对象的这个属性放入缓存行吗？两个对象会共享一个缓存行吗？  
Striped64是包可见，内部类Cell使用了@sun.misc.Contended注释，使得一个Cell对象独占一个缓存行。Striped64使用了一个based域，在没有竞争的情况下直接使用based域进行更新，有竞争（也就是CAS更新失败）时候
初始化Cells，初始为2，再次发生竞争时，扩大两倍，直到大于或等于cpu个数的最小2的次幂。如果达到最大容量发生冲突，通过随机改变线程的哈希码来搜索到Cell的映射。
但是LongAdder的sum和reset方法只有在非并发情况下才是准确的。