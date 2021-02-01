package com.code.demo.sync;

<<<<<<< HEAD
import java.util.concurrent.ConcurrentHashMap;
=======
import java.util.HashMap;
import java.util.Map;
>>>>>>> bf39b600286b077ec01dbfb43ea28b1e3ea0c261

/**
 * @Date 2021/1/29 14:15
 * @Author LBWNB
 **/
public class Test {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1","1");
        //无参构造器不做任何动作，第一次put会进行初始化16的初始长度

        //    public ConcurrentHashMap(int initialCapacity) {
        //        if (initialCapacity < 0)
        //            throw new IllegalArgumentException();
        //        int cap = ((initialCapacity >= (MAXIMUM_CAPACITY >>> 1)) ?
        //                   MAXIMUM_CAPACITY :
        //                   tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1));
        //        this.sizeCtl = cap;
        //    }

        //指定长度的构造器，和Hashmap不同，如果传参为32初始化为64，tableSizeFor（）的入参为指定容量*3/2 +1

        //sizeCtl
        // 0 数组未初始化，并且初始化长度为16
        // 正数，如果未初始化表示第一次初始化长度，如果已经初始化，记录的为下次扩容的阈值
        // -1 正在进行初始化
        // < 0 且 != -1，表示当前正在扩容，-(1+n),有n个线程正在扩容

        //    public ConcurrentHashMap(int initialCapacity,
        //                             float loadFactor, int concurrencyLevel) {
        //        if (!(loadFactor > 0.0f) || initialCapacity < 0 || concurrencyLevel <= 0)
        //            throw new IllegalArgumentException();
        //        if (initialCapacity < concurrencyLevel)   // Use at least as many bins
        //            initialCapacity = concurrencyLevel;   // as estimated threads
        //        long size = (long)(1.0 + (long)initialCapacity / loadFactor);
        //        int cap = (size >= (long)MAXIMUM_CAPACITY) ?
        //            MAXIMUM_CAPACITY : tableSizeFor((int)size);
        //        this.sizeCtl = cap;
        //    }

        //第一次put
        //扰动算法和hashmap的不同
        //static final int spread(int h) {
        //        return (h ^ (h >>> 16)) & HASH_BITS;
        //    }
        //static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
        //高16低16异或运算后与 0x7fffffff进行与运算，因为0x7fffffff为integer最大值，二进制除了符号为全为0，所以最后得到的值一定为正数

        //private final Node<K,V>[] initTable() {
        //        Node<K,V>[] tab; int sc;
        //        while ((tab = table) == null || tab.length == 0) {
        //            if ((sc = sizeCtl) < 0)
        //                Thread.yield(); // lost initialization race; just spin
        //            else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
        //                try {
        //                    if ((tab = table) == null || tab.length == 0) {
        //                        int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
        //                        @SuppressWarnings("unchecked")
        //                        Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
        //                        table = tab = nt;
        //                        sc = n - (n >>> 2);
        //                    }
        //                } finally {
        //                    sizeCtl = sc;
        //                }
        //                break;
        //            }
        //        }
        //        return tab;
        //    }
        //第一次初始化，如果当前有线程正在初始化，让出时间片if ((sc = sizeCtl) < 0) Thread.yield();
        //如果没有线程在扩容，进行cas去修改sizeCtl的值，
        //使用dcl模式进行双重校验，防止多次初始化，初始化完成后将下次扩容的阈值赋值给sizeCtl


        //final V putVal(K key, V value, boolean onlyIfAbsent) {
        //        if (key == null || value == null) throw new NullPointerException();
        //        int hash = spread(key.hashCode());
        //        int binCount = 0;
        //        for (Node<K,V>[] tab = table;;) {
        //            Node<K,V> f; int n, i, fh;
        //            if (tab == null || (n = tab.length) == 0)
        //                tab = initTable();
        //            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
        //                if (casTabAt(tab, i, null,
        //                             new Node<K,V>(hash, key, value, null)))
        //                    break;                   // no lock when adding to empty bin
        //            }
        //            else if ((fh = f.hash) == MOVED)   moved说明正在进行扩容，不可以进行插入，进行协助扩容
        //                tab = helpTransfer(tab, f);
        //            else {
        //                V oldVal = null;
        //                synchronized (f) {
        //                    if (tabAt(tab, i) == f) {    //防止树的转换导致节点位置变化
        //                        if (fh >= 0) {
        //                            binCount = 1;
        //                            for (Node<K,V> e = f;; ++binCount) {
        //                                K ek;
        //                                if (e.hash == hash &&
        //                                    ((ek = e.key) == key ||
        //                                     (ek != null && key.equals(ek)))) {
        //                                    oldVal = e.val;
        //                                    if (!onlyIfAbsent)
        //                                        e.val = value;
        //                                    break;
        //                                }
        //                                Node<K,V> pred = e;
        //                                if ((e = e.next) == null) {
        //                                    pred.next = new Node<K,V>(hash, key,
        //                                                              value, null);
        //                                    break;
        //                                }
        //                            }
        //                        }
        //                        else if (f instanceof TreeBin) {
        //                            Node<K,V> p;
        //                            binCount = 2;
        //                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
        //                                                           value)) != null) {
        //                                oldVal = p.val;
        //                                if (!onlyIfAbsent)
        //                                    p.val = value;
        //                            }
        //                        }
        //                    }
        //                }
        //                if (binCount != 0) {
        //                    if (binCount >= TREEIFY_THRESHOLD)
        //                        treeifyBin(tab, i);
        //                    if (oldVal != null)
        //                        return oldVal;
        //                    break;
        //                }
        //            }
        //        }
        //        addCount(1L, binCount);
        //        return null;
        //    }


        //else if ((fh = f.hash) == MOVED)   moved说明正在进行扩容，不可以进行插入，进行协助扩容
        //synchronized (f) {   锁住数组的对应节点进行插入，别的桶的操作不会受到影响
        //node节点的hash值fh，等于-1表示正在扩容，>0表示为链表 否则为红黑树结构

        //如果桶上的链表>8并不会直接进行转换红黑树，只有在数组长度>64 的时候，才会进行转换树，否则进行扩容
    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[] {map.get(target-nums[i]),i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
