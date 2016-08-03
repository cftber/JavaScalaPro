package com.tgzhao

/**
 * Created by tgzhao on 2016/8/2.
 * http://herbix.me/archives/scala%E5%AD%A6%E4%B9%A0%EF%BC%88%E4%B8%83%EF%BC%89-apply%E5%92%8Cunapply
 */
object ApplyUnApplyTest {
  def main(args: Array[String]) {

    //某种意义上讲，apply方法其实是对()的重载，作用是给对象赋予可被调用的特征\
    val v = new Vec3(1, 2, 3)
    println(v(0))  // 1
    println(v(1))  // 2
    println(v(2))  // 3
    //最后三句等价于
    println(v.apply(0))
    println(v.apply(1))
    println(v.apply(2))

    val v3 = new Vec32(1, 2, 3)
    v3(1) = 10
    println(v3(1))  // 10
  }
}

/**
 * 某种意义上讲，apply方法其实是对()的重载，作用是给对象赋予可被调用的特征
 * @param x
 * @param y
 * @param z
 */
class Vec3(val x: Int, val y: Int, val z: Int) {
  def apply(i: Int): Int = if(i == 0) x else if(i == 1) y else z
}

/**
 * update方法是对()=的重载，作用是修改通过apply获得的值：
 * @param x
 * @param y
 * @param z
 */
class Vec32(var x: Int, var y: Int, var z: Int) {
  def apply(i: Int): Int = if(i == 0) x else if(i == 1) y else z
  def update(i: Int, v: Int): Unit =
    if(i == 0) x = v
    else if(i == 1) y = v
    else z = v
}