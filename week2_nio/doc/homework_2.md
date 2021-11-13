PS C:\Windows\system32> sb -u http://localhost:8088/api/hello -c 20 -N 60
Starting at 2021/11/13 14:05:27
[Press C to stop the test]
284234  (RPS: 4432.1)
---------------Finished!----------------
Finished at 2021/11/13 14:06:31 (took 00:01:04.3279792)
Status 200:    284242

RPS: 4644.9 (requests/second)
Max: 149ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 2ms
  99%   below 3ms
99.9%   below 10ms