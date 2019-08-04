## CRC32校验算法
循环冗余校验码基本原理：在K位信息（一般为二进制信息）后拼接R位的校验码，整个编码长度为N位，那么对于（N,K)，存在一个最高次幂为R=N-K的多项式G(x)。
根据G(x)可以生成K位信息的校验码，G(x)叫做这个CRC码的生成多项式。一般的具体生成过程为：假设要发送的信息多项式用C(x)表示，将C(x)左移R位即C(x)*x<sup>R</sup>
（左移后最右边的R位就是存放校验码的位置），C(x)*x<sup>R</sup>除以多项式G(x)得到的余数就是校验码。常用的多项式有：

CRC-4： x<sup>4</sup>+x+1 <br/>
CRC-8: x<sup>8</sup>+x<sup>5</sup>+x<sup>4</sup>+1 <br/>
CRC-12: x<sup>12</sup>+x<sup>11</sup>+x<sup>3</sup>+x<sup>2</sup>+x+1 <br/>
CRC-16: x<sup>16</sup>+x<sup>15</sup>+x<sup>2</sup>+1 <br/>
CRC-ITU**: x<sup>16</sup>+x<sup>12</sup>+x<sup>5</sup>+1 <br/>
CRC-32: x<sup>32</sup>+x<sup>26</sup>+x<sup>23</sup>+...+x<sup>2</sup>+x+1 <br/>
CRC-32c: x<sup>32</sup>+x<sup>28</sup>+x<sup>27</sup>+...+x<sup>8</sup>+x<sup>6</sup>+1 <br/>


## Paxos
### 应用
#### 1 用来确定一个不可变量的取值
 取值可以是任意二进制数据，一旦确定下来将不再更改，且可以被搜索的到
#### 2 在分布式存储系统中应用Paxos
- 数据本身可变，采用多副本进行存储
- 多个副本的更新操作序列[Op1, Op2, ..., Opn]是相同的，不变的。
- 用Paxos依次来确定不可变变量Opi的取值（即第i个操作是什么）
- 每确定完Opi之后，让各个数据副本执行Opi，依次类推

#### 难点
- 管理多个Proposer的并发执行
- 保证var变量的不可变性
- 容忍任意Proposer机器故障
- 容忍半数一下Acceptor机器故障  

#### 方案一
一个Accepter和多个Proposer  
Proposer独占式获取Accepter的锁，获取锁同时获取var值，var值不为空，设置值，然后释放锁

#### 方案二 一个Accepter
- 抢占式访问权：accepter可以让某个Proposer获取到的访问权失效，不再接收它的访问，之后，可以将访问权发放给其他Proposer
- Proposer向Accepter申请访问权时指定编号epoch（越大越新），获取到访问权后次啊能像accepter提交访问权
- Accepter采用喜新厌旧的原则：一但收到更大的epoch的申请，马上让旧的epoch失效。不再接收他们的提交的取值。然后给新的epoch发放访问权，值接收新epoch提交的值
- 不同epoch的Proposer采用后者认同前者原则：在肯定旧epoch无法生成确定性取值时。新的epoch会提交自己的value，一但旧epoch形成确定值，新的epoch可以获取到这个值
并且认同这个值。

#### Paxos 多个Accepter
一但某个epoch的取值f被半数以上Accepter接受，则认为此var取值被确定为f，不再更改。


