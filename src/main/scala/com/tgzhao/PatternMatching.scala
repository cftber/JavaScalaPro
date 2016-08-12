package com.tgzhao

/**
 * Created by tgzhao on 2016/8/12.
 */
object PatternMatching {
  def main(args: Array[String]) {
    // 1. match tuple
    val hostPort = ("localhost", 80)
    val result = hostPort match {
      case ("localhost", port) => s"this is localhost address, port is $port"
      case (host, port) => s"some other address: $host : $port"
    }

    println(result)

    // 2. match option
    val map = Map(1 -> "one", 2 -> "two")
    val result2 = map.get(1) match {
      case Some(str) => s"get something from map: $str" + str
      case None => "no result"
    }
    println(result2)
  }
}
