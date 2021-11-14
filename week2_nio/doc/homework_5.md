PS C:\Windows\system32> sb -u http://localhost:8801/test -c 20 -N 60
Starting at 2021/11/14 9:13:03
[Press C to stop the test]
419830  (RPS: 6584.2)
---------------Finished!----------------
Finished at 2021/11/14 9:14:07 (took 00:01:03.8688857)
Status 200:    419835

RPS: 6868.8 (requests/second)
Max: 110ms
Min: 0ms
Avg: 0ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 0ms
99.9%   below 3ms


### 总结
Netty由于使用NIO模型, 能支持高并发高性能的处理