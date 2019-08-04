# tip

- 代码中可以使用label，label可以定义在循环前，假设存在两层循环，label定义在外层循环前，在内层循环使用 continue label直接跳过外层的本次循环或者使用break label可以直接跳出外层的循环。
- Thread.sleep方法可以被中断，会抛异常，且中断会复位，Object.wait方法可以被中断，会抛异常，中断不会复位。Thread.interrupted和Thread.isInterrupted可以检测中断，Thread.interrupted
清除中断位，Thread.isInterrupted不会。
