package com.tgzhao

import scala.io.Source

/**
 * Created by tgzhao on 2016/7/26.
 */
//“伴侣”对象（Companion object)
//如果你需要定义的类的companion对象，Scala要求你把这两个定义放在同一个文件中。类和其companion对象可以互相访问对方的私有成员
object ArryListTuplesSetMapApp03 {


  def readFileTest() = {
    for (line <- Source.fromFile("D:/result.txt").getLines())
      println(line.length + " " + line)
  }

  def main(args: Array[String]): Unit = {

    readFileTest()
    setMapTest()
    arryTest()
    listTest()
    tuplesTest()


    //参数缺省值
    saySomething()
    saySomething("jimmy")
    saySomething("jimmy", "hi")


  }

  def setMapTest() = {
    //Set的基Trait类型Set（Trait的概念类似于Java中的Interface，所不同的Scala中的Trait可以有方法的实现），分两个包定义Mutable（可变）和Immutable（不可变）
    //缺省情况Set为Immutable Set，如果你需要使用可修改的集合类（Set类型），你可以使用全路径来指明Set，比如 scala.collection.mutalbe.Set
    var jetSet = Set ("Boeing","Airbus")
    jetSet +="Lear"
    println(jetSet.contains("Cessna"))

    //Scala提供的另外一个类型为Map类型，Scala也提供了Mutable和Immutable 两种Map 类型
    val romanNumeral = Map ( 1 -> "I" , 2 -> "II",
      3 -> "III", 4 -> "IV", 5 -> "V")
    println (romanNumeral(4))
  }

  def arryTest() = {
    val greetStrings = new Array[String](3)
    greetStrings(0) = "hello"
    greetStrings(1) = ","
    greetStrings(2) = "world!\n"

    for (i <- 0 to 2)
      print(greetStrings(i))

    //以上和下面等效
    //greetStrings(1) 其实调用greetString.apply(1)
    val greetStrings2 =new Array[String](3)
    greetStrings2.update(0,"Hello")
    greetStrings2.update(1,",")
    greetStrings2.update(2,"world!\n")
    for(i <- 0 to 2)
      print(greetStrings2.apply(i))

    //这里使用（）其实还是调用Array类的关联对象Array的apply方法
    val greetStrings3 =Array("Hello",",","World\n")
    val greetStrings4 =Array.apply("Hello",",","World\n")

  }

  def listTest() = {
    val oneTwo = List(1,2)
    val threeFour = List(3,4)
    //通过:::操作符（其实为:::方法）将两个列表链接起来
    val oneTwoThreeFour = oneTwo ::: threeFour
    println(oneTwo + " and " + threeFour + " were not mutated.")
    println("Thus, " + oneTwoThreeFour + " is a new list")

    //List也提供了一个::方法用来向List中添加一个元素，::方法（操作符）是右操作符，也就是使用::右边的对象来调用它的::方法，
    // Scala中规定所有以：开头的操作符都是右操作符，因此如果你自己定义以：开头的方法（操作符）也是右操作符。
    val oneTowThree = 1 :: 2 ::3 :: Nil
    val onetwothree = Nil.::(3).::(2).::(1)
    println(oneTowThree)
    println(onetwothree)
  }

  /**
   * Tuples在方法需要返回多个结果时非常有用
   * 一旦定义了一个元组，可以使用._和索引来访问员组的元素（矢量的分量，注意和数组不同的是，元组的索引从1开始）
   * 目前Scala支持的元祖的最大长度为22.如果有需要，你可以自己扩展更长的元祖
   */
  def tuplesTest() = {
    val pair=(99,"Luftballons")
    println(pair._1)
    println(pair._2)
  }
  /**
   * 参数缺省值
   * @param person
   * @param msg
   */
  def saySomething(person: String = "somebody", msg: String = "Hello") = {
    println(person + " say : " + msg);
  }

}
