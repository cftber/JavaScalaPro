package com.tgzhao

/**
 * Created by tgzhao on 2016/8/4.
 */
object CommonTest2 {

  def main(args: Array[String]) {
    val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) ::
      ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil

    println(wordFrequencies.map(_._2))
    println(wordFrequencies.filter(p => p._2 > 5 && p._1 != "and"))
//    println(wordFrequencies.collect())
    println
    val pf = new PartialFunction[(String, Int), String] {
      def apply(wordFrequencie: (String, Int)) = wordFrequencie match {
        case (word, freq) if freq > 3 && freq < 50 => word
      }

      def isDefinedAt(wordFrequencie: (String, Int)): Boolean = wordFrequencie match {
        case (word, freq) if freq > 3 && freq < 50 => true
        case _ => false
      }
    }
    println(wordFrequencies.collect(pf))

    println(wordFrequencies.collect({
      case (word, freq) if freq > 3 && freq < 50 => word
    }))

  }

}
