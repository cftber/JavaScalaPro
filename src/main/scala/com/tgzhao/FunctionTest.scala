package com.tgzhao

/**
 * Created by tgzhao on 2016/8/2.
 */
object FunctionTest {

  def main(args: Array[String]): Unit = {
    functionTest01
  }


  /**
   * 函数字面量
   */
  def functionTest01: Unit = {
    //函数字面量
    var increase = (x: Int) => {
      print("begin")
      x + 1
    }
    var result = increase(10)
    print(result)

    //可以作为参数传递个其它函数
    var someNums = List(-33, 23, 54, 0, -90)
    someNums.foreach(f => println(f))
    var reee = someNums.filter( x => x >0)
    println(reee)

    //Scala 还可以进一步简化，Scala允许使用”占位符”下划线”_”来替代一个或多个参数，只要这个参数值函数定义中只出现一次，Scala编译器可以推断出参数
    var reee2 = someNums.filter(_ >0)
    println(reee2)

    //如果你给出参数的类型，依然可以使用_来定义函数
    //因为_替代的参数在函数体中只能出现一次，因此多个“_”代表多个参数。第一个“_”代表第一个参数，第二个“_”代表第二个参数，以此类推
    val f = (_ :Int ) + ( _ :Int)
    println(f (5,10))

  }
}
