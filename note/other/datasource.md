### tip

- 表结构优化
- SQL语句优化
- 分区
- 分表

# innoDB

### 性能调优
OLTP 在线事务处理，多在日常事务应用中，偏IO，OLAP 在线分析处理，数据仓库或数据集市，偏CPU。

### 事务
#### redo
重做日志用来实现事务的持久性。内存中的重做日志缓冲；重做日志文件。重做日志包括redo log和undo log。redo log用来保证事务的持久性，undo log用来帮助事务回滚及MVCC的功能。
redo log是顺序写的，在数据库运行时不需要对redo log的文件进行读取操作。undo log是需要进行随机读写的。  
重做日志先写入日志缓冲，在写入文件系统缓冲，为了保证重做日志写入磁盘，必须进行一次fsync操作。innodb_flush_log_at_trx_commit用来控制重做日志刷新到磁盘的策略：  
- 1 表示事务提交时必须调用一次fsync操作  
- 0 事务提交时不进行写入重做日志操作，这个操作仅在master thread中完成，master thread中每1秒进行一次重做日志文件的fsync操作 
- 2 事务提交时仅缓存中的重做日志写入文件系统的缓存中，不进行fsync操作。  

#### redo log
二进制文件（binlog），用来进行POINT-IN—TIME的恢复及主从复制（Replication）。重做日志是InnoDB存储引擎层产生，二进制文件是在MySQL数据库的上层产生，对任何引擎都会产生。
binlog是一中逻辑日志，重做日志是一种物理格式日志。binlog是事务提交时一次写入，重做日志是在事务进行中不断的提交。  
redo log 是以512字节进行存储的，称之为重做日志块redo log block。若一页产生的重做日志数量大于512字节，那么需要分割为多个重做日志块进行存储。redo log block还包括
12字节的日志头和8字节的日志尾。  
日志头：  
- LOG_BLOCK_HDR_NO:4个字节，循环递增引用，标记在数组中的位置，第一位用来判断是否是flush bit。
- LOG_BLOCK_HDR_DATA_LEN:2个字节，表示log block锁占用大小，写满时为0X200
- LOG_BLOCK_FIRST_REC_GROUP:2个字节，表示第一个新日志的所在偏移量，如果和LOG_BLOCK_HDR_DATA_LEN相同，表示这个redo log block不包含新事物日志
- LOG_BLOCK_CHECKPOINT_NO:4个字节，表示log block最后被写入时的检查点  

log group 重做日志组，逻辑概念，每个log group中的日志文件大小是相同的  

重做日志格式：  
InnoDB存储引擎的存储管理是基于页的，故其重做日志也是基于页的。 对一个页的redo log 日志会连续存储吗？

#### undo log
undo存放在数据库内部的一个特殊段中，称为undo段（undo segment）。undo段位于共享表空间内。undo是逻辑日志，只能将数据逻辑的恢复带原来的样子，不能讲数据库物理的恢复到
执行语句或事务前的样子。InnoDB存储引擎有rollback segment，每个回滚段中记录了undo log segment，在每个undo log segment段中进行undo页的申请。共享表空间为5的页记录了
虽有rollback segment header所在的页。在InnoDB1.1版本之前，只有一个rollback segment，因此支持同时在线的事物限制为1024，现在支持128个rollback segment（所有表公用的？？）。
事务在undo log segment分配页并写入undo log，事务提交时：
- undo log放入列表中，以供之后的purge操作
- 判断undo log所在的页是否可以重用，若可以分片给下个事务使用。判断undo页使用空间是否小于3/4，若是表示该undo页可以被重用，之后新的undo log记录在当前undo log的后面


undo log格式：undo的类型；事务id，table_id（undo log所对应的表对象）（**如果一个事务涉及多张表，是存放多个table_id吗，还是分多条存放**）需要记录主键列和值用于定位记录位置，
以及更新字段的值。  

purge用于最终完成delete和update操作。这样升级时因为InnoDB存储引擎支持MVCC，所以记录不能在事务提交时进行处理。History列表根据事务提交的顺序，将undo log进行连接。从History log定位
undo log页中log是从效率考虑。

## 5 MYSQL Server Administration ##

#### 5.1 The MySQL Server ####

##### Configuring the Server #####

````text
## mysql服务器默认使用的命令选项和系统变量值
mysqld --verbose --help ‘=
## 查看当前运行的系统使用的系统变量
SHOW VARIABLES
## 查看一些统计和状态指标
SHOW STATUS
## 系统变量和状态也可以使用mysqladmin查看
mysqladmin variables
mysqladmin extended-status
````

#####  Server Configuration Validation ######

MySQL 8.0.16支持 --validate-config 参数以支持启动配置问题

####  5.2 The MySQL Data Directory  ####

MySQL 



#### 5.3 The Mysql System Schema







