# java8-in-action
java8-in-action
#### 1.ForkJoin的框架的实现
```
if (任务足够小或不可分) {
    顺序计算该任务
} else {
    将任务分成两个子任务
    递归调用本方法，拆分每个子任务，等待所有子任务完成
    合并每个子任务的结果
}
```
- 具体代码的实现
```
int length = end - start;
if (length <= THRESHOLD) {
    return computeSequentially();
}
ForkJoinSumCalculator leftTask =
    new ForkJoinSumCalculator(numbers, start, start + length/2);
leftTask.fork();
ForkJoinSumCalculator rightTask =
    new ForkJoinSumCalculator(numbers, start + length/2, end);
Long rightResult = rightTask.compute();
Long leftResult = leftTask.join();
return leftResult + rightResult;
```
- 一种错误的方式
    + 这样会操成有线程变成了监工，不工作，分的越多，情况
    越明显
```
leftTask.fork();
rightTask.fork();

Long leftResult = leftTask.join();
Long rightResult = rightTask.join();
```