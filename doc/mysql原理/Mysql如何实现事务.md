# 数据库如何实现事务？
mysql实现事务主要根据 `undo log` 和 `redo log`，锁技术和`MVCC`

## redo log
redo log叫做重做日志，是用来实现事务的持久性。该日志文件由两部分组成：重做日志缓冲（redo log buffer）以及重做日志文件（redo log）,前者是在内存中，后者在磁盘中。当事务提交之后会把所有修改信息都会存到该日志中

![redolog](https://youdaoyun1.oss-cn-shenzhen.aliyuncs.com/ES/redolog.jpg)

redo log 有什么作用？

mysql 为了提升性能不会把每次的修改都实时同步到磁盘，而是会先存到Boffer Pool(缓冲池)里头，把这个当作缓存来用。然后使用后台线程去做缓冲池和磁盘之间的同步。

如果数据在Buffer Pool中未同步到data page中时，就需要使用redo log将提交的事务数据同步到sql表中

- 总结：

**redo log是用来恢复数据的 用于保障，已提交事务的持久化特性**

## undo log

undo log 叫做回滚日志，用于记录数据被修改前的信息。他正好跟前面所说的重做日志所记录的相反，重做日志记录数据被修改后的信息。undo log主要记录的是数据的逻辑变化，为了在发生错误时回滚之前的操作，需要将之前的操作都记录下来，然后在发生错误时才可以回滚。

![undolog](https://youdaoyun1.oss-cn-shenzhen.aliyuncs.com/ES/undolog.jpg)

undo log记录的日志信息是反向操作信息，例如insert对应delete
(1) 如果在回滚日志里有新增数据记录，则生成删除该条的语句

(2) 如果在回滚日志里有删除数据记录，则生成生成该条的语句

(3) 如果在回滚日志里有修改数据记录，则生成修改到原先数据的语句

- 总结：
  **undo log是用来回滚数据的用于保障 未提交事务的原子性**

## mysql锁技术
- 共享锁(shared lock),又叫做"读锁"
  读锁是可以共享的，或者说多个读请求可以共享一把锁读数据，不会造成阻塞。

- 排他锁(exclusive lock),又叫做"写锁"
  写锁会排斥其他所有获取锁的请求，一直阻塞，直到写入完成释放锁。

- 总结：
  通过读写锁，可以做到读读可以并行，但是不能做到写读，写写并行
  事务的隔离性就是根据读写锁来实现,锁具体见mysql锁

## MVCC
MVCC (MultiVersion Concurrency Control) 叫做多版本并发控制。

InnoDB的 MVCC ，是通过在每行记录的后面保存两个隐藏的列来实现的。这两个列， 一个保存了行的创建时间，一个保存了行的过期时间， 当然存储的并不是实际的时间值，而是系统版本号。

他的主要实现思想是通过数据多版本来做到读写分离。从而实现不加锁读进而做到读写并行。

MVCC在mysql中的实现依赖的是undo log与read view

undo log :undo log 中记录某行数据的多个版本的数据。
read view :用来判断当前版本数据的可见性
![mvcc](https://youdaoyun1.oss-cn-shenzhen.aliyuncs.com/ES/mvcc.png)

## 事务的实现
前面讲的重做日志，回滚日志以及锁技术就是实现事务的基础。

事务的**原子性**是通过 `undo log` 来实现的
事务的**持久性**是通过 `redo log` 来实现的
事务的**隔离性**是通过 **(读写锁+MVCC)**来实现的
而事务的终极大 boss 一致性是通过原子性，持久性，隔离性来实现的！！！
原子性，持久性，隔离性折腾半天的目的也是为了保障数据的一致性！