package com.tgzhao

import scala.util.Random

/**
 * Created by tgzhao on 2016/8/3.
 */
object CommonTest {
  def main(args: Array[String]) {
    val seqtest : Seq[String] = Seq("werwe", "aaa", "grgr", "bfer")
    println(seqtest)
    println(Random.shuffle(seqtest))
    println(0.shortValue)
    return ;

    val songTitles = List("The White Hare", "Childe the Hunter", "Take no Rogues")
    println(songTitles.map(t => t.toLowerCase))
    //或者，利用 Scala 的 占位符语法(placeholder syntax) 得到更加简短的代码：
    println(songTitles.map(_.toLowerCase))

    println()
    println(listYield)
    println("end")
    val lists = List(1, 2, 3) :: List.empty:: List(4, 5) :: Nil //List(List(1, 2, 3), List(), List(4, 5))
    val lists2 = List(1, 2, 3) :: List.empty :: Nil :: List(4, 5) //List(List(1, 2, 3), List(), List(), 4, 5)
    println(lists)
    println(lists2)

    println("end")

    println(hallOfFame)
    println(hallOfFame2)
  }

  def gameResults(): Seq[(String, Int)] =
    ("Daniel", 3500) :: ("Melissa", 13000) :: ("John", 7000) :: Nil

  //List(Melissa, John)
  def hallOfFame = for {
    result <- gameResults()
    (name, score) = result
    if (score > 5000)
  } yield name

  //List(Melissa, John)
  def hallOfFame2 = for {
    (name, score) <- gameResults()
    if (score > 5000)
  } yield name

  //List(3, 2)
  def listYield = {
    val lists = List(1, 2, 3) :: List.empty :: List(5, 3) :: Nil
    for {
      list @ head :: _ <- lists
    } yield list.size
  }
}
