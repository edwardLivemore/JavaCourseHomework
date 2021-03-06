## 各类GC总结
GC名称|线程|STW特点|CPU利用率|年轻代回收算法|老年代回收算法|启动方式|适合场景|其他
:----|:----|:----|:----|:----|:----|:----|:----|:----
串行GC|单线程|暂停时间长且停止所有业务线程|高|标记-复制|标记-清除-整理|-XX:UseSerialGC|几百MB堆内存的单核CPU机器|
并行GC|多线程,默认线程数为CPU核心数|暂停时间较长停止所有业务线程, 调用所有GC线程回收垃圾|高|标记-复制|标记-复制-整理|-XX:UseParallelGC|多核服务器且吞吐量要求高|JDK1.8 默认垃圾回收器
CMSGC|多线程,默认线程数为CPU核心数的1/4|暂停时间短(只在初始标记及最终标记阶段暂停)且与业务线程并发执行|低|标记-复制|标记-清除|-XX:UseConcMarkSweepGC|适合低延迟的系统,且一般配合ParNew使用,即ParNew回收年轻代,CMS回收老年代,缺点会造成内存碎片
G1GC|多线程,默认线程数为CPU核心数的1/4|暂停时间短,且可配置期望暂停时间|低|标记-清除-整理|标记-清除-整理|-XX:UseG1GC|适合低延迟的系统, 且一般配合MaxPauseMills参数使用|JDK9以上的默认垃圾回收器, 但在以下场景下会退化为串行GC: 1.并发模式失败 2.晋升失败 3.巨型对象分配失败

## 堆内存总结
堆内存一般分为新生代(Eden, S0, S1)和老年代(Old), 新生代与老年代之间的比例可以通过-XX:NewRatio参数调节(如设置为4，则年轻代与年老代所占比值为1：4，年轻代占整个堆栈的1/5), 新生代存活次数达到阈值(默认为15次)或为大对象时,会晋升到老年代(可以通过参数-XX:MaxTenuringThreshold设置, 如果设置为0, 则对象不经过survivor区, 直接到老年代), 新建对象一般都存放在Eden区, 新生代由于生命周期较短,存活率低,所以一般采用标记-复制的垃圾回收算法; 老年代由于生命周期较长, 一般采用标记-清除(容易造成内存碎片)或标记-清除-整理算法。

## GC参数配置总结
一般情况下过小的堆内存会更频繁的触发MinorGC和FullGC(如: -Xmx256m -Xms256m),造成对业务的影响且容易引发OutOfMemoryError, 解决方案为:1.增大堆内存(蓄水池效应, 减少GC频率, 但可能增加单次GC的STW时间) 2.修改年轻代和老年代之间的比例, 尽可能让年轻代能容纳更多的新对象 3.降低年轻代晋升为老年代的次数阈值 4.使用效果更好的GC 如CMS,G1
另外, 建议-Xmx与Xms设置一样的大小, 避免GC在回收过程中动态扩容堆内存造成的性能损失.


