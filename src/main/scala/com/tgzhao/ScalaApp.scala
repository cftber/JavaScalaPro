package com.tgzhao

import scala.io.StdIn

/**
 * Created by tgzhao on 2016/7/26.
 */
class ScalaApp {

  def main(args: Array[String]) {
    println("please input something,press Enter to exit:") //每一行最后加不加分号都可以
    readKeyboard //注：无参数的函数调用，加不加括号都行
    println(add(1, 2) + "\n------\n")
    println(div(1, 2) + "\n------\n")
    println(div(1, 0) + "\n------\n")
    println("5!=" + factorial(5) + "\n------\n")
    println("-1!=" + factorial2(-1) + "\n------\n")
    println("5!=" + factorial2(5) + "\n------\n")

//  1. 函数定义，除了用正式的def来定义外，还可以"匿名"定义，比如下面这样：
    var addtest = (a: Integer, b: Integer) => a + b //是不是有点c#中匿名方法和lambda的味道了:)
    println(addtest(1, 2))

    def oneFunc = {
      println("one func")
    }

//  2. 对于无参函数，可以做为另一个函数的参数
    /**
     * 无参函数，可以直接作为另一个函数的参数（有点象c#中的委托）
     * @param a
     */
    def twoFunc(a: Unit) = {
      a
      println("two func")
    }

    //调用示例
    twoFunc(oneFunc)

//  3. for循环还可以写得更强大：
    def forDemo = {
      for (x <- 1 to 2; y <- 3 to 4) println("x=" + x + ",y=" + y)

      println

      //上面的写法，等效于下面的写法
      for (x <- 1 to 2)
        for (y <- 3 to 4)
          println("x=" + x + ",y=" + y)

      println
      //带条件的for循环(打印10以内的偶数)
      for (i <- 1 to 10; if i % 2 == 0) println(i)
    }
  }

  /**
   * 键盘读取示例（无返回值）
   */
  def readKeyboard() {
    //注：无返回值，即相当于返回值为Unit,所以上面的方法声明也可以写成
    // def readKeyboard(): Unit = {
    var line = StdIn.readLine()
    while (line != "") {
      println("you just input the : " + line)
      line = StdIn.readLine()
    }
    println("bye\n")
  }

  /**
   * 整数加法(带返回值示例)
   * @param x
   * @param y
   * @return
   */
  def add(x: Integer, y: Integer): Integer = {
    print(x + " + " + y + " = ")
    x + y //返回值，连return都不用加，十分简洁
  }

  /**
   * 异常处理示例
   */
  def div(x: Long, y: Long): Double = {
    var hasError = false
    try {
      if (y.equals(0L)) throw new RuntimeException("divide by zero") else x.toDouble / y
    }
    catch {
      case e: Exception => {
        hasError = true
        println("error:" + e.getMessage())
        Double.MinValue
      }
    }
    finally {
      if (hasError) println("finished , but there has a error") else println("finished")
    }
  }

  /**
   * 递归示例
   * @param x
   * @return
   */
  def factorial(x: Integer): Long = {
    if (x.equals(0)) 1 else x * factorial(x - 1)
  }

  /**
   * 演示for循环
   * @param x
   * @return
   */
  def factorial2(x: Integer): Long = {
    if (x <= 0) return 1 //注：加return后，下面的代码就不执行了
    println("test")
    var temp = 1L
    for (i <- 1 to x) {
      temp = temp * i
    }
    temp
  }
}
