## InnoDB

InnoDB 存储引擎支持事务，特点为行锁设计支持外键，默认读取不会产生锁

InnoDB 通过多版本并发控制`MVCC`获取高并发性，实现四种隔离级别，默认为`可重复读`

InnoDB 数据表的存储，采用了聚簇的方式，每张表按照主键的顺序进行存储，如果没有显式的指定主键，会生成`6`字节的`ROWID`作为主键

## 索引

InnoDB常见的索引：
1. B+Tree索引
2. 全文索引
3. 哈希索引

> 哈希索引为自适应索引，InnoDB会根据表的使用情况自动为表生成hash索引，不能人为干预是否在一张表中生成hash索引

> B+Tree索引并不能直接找到具体的行，能找到的只是查找数据所在的页，之后将页数据加载到内存中，在内存中进行查找最后找到查找数据



