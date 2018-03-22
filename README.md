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

#### Supplier 供应商 生产者
```java
@FunctionalInterface
public interface Supplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
```
- 例子
```
public void log(Level level, Suppiler<String> msgSupplier){
    if(logger.isLoggable(level){
        log(level, msgSupplier.get());
    }
}
```
- 思考
    + 用这种方式的时候，一帮都是在需要胖丁第一个参数的
    状态，然后才操作第二个参数。
    + 所以一般遇到这种情况的时候，可以新建一个方法来避免这种
    显示的判断.