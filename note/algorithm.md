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