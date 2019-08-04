
- 订阅工具是DTS，可以是cannal，也可以自己订阅和分析binlog，读写分离时候，可用于数据同步到读库时候通知缓存失效。
- 分库分表工具：阿里TDDL，DRDS和cobar，开源社区的sharding-jdbc，MYCAT，美团zebra。其中Client模式有TDDL和
sharding-jdbc，PROXY模式有阿里的cobar和MyCAT。