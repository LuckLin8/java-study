1. ArrayList构造器初始化了什么？数组在什么时候进行初始化？
- 无参构造器指定了一个空数组，在第一次add时，初始化10长度的数组
- 如果指定了初始长度，构造指定长度的空数组

2. ArrayList是如何扩容的？
- ArrayList扩容后的大小等于扩容前大小的1.5倍，当ArrayList很大的时候，这样扩容还是挺浪费空间的，甚至会导致内存不足抛出OutOfMemoryError。
- 扩容的时候还需要对数组进行拷贝，这个也挺费时的。所以我们使用的时候要竭力避免扩容，提供一个初始估计容量参数，以免扩容对性能带来较大影响

3. ArrayList是线程安全的么？

- 线程安全版本的数组容器是Vector。Vector的实现很简单，就是把所有的方法统统加上synchronized
- Collections.synchronizedList把一个普通ArrayList包装成一个线程安全版本的数组容器，原理同Vector是一样的，就是给所有的方法套上一层synchronized
- CopyOnWriteArrayList,写时复制集合，可以保障线程安全

4. 什么情况下你会使用ArrayList？什么时候你会选择LinkedList？
- 频繁查询使用ArrayList，底层为数组，事件复杂度为O(1)，LinkedList时间复杂度为O(n)
- 频繁修改使用LinkedList，在ArrayList中增加或者删除某个元素，通常会调用System.arraycopy方法，很耗费性能