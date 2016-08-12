package com.tgzhao

/**
 * Created by tgzhao on 2016/8/12.
 */

trait CarElement {
  def accept(visitor: Visitors.Visitor) = visitor(this)
}

case class Body() extends CarElement

case class Engine() extends CarElement

case class Wheel(name: String) extends CarElement

case class Car() extends CarElement {
  val elements: Seq[CarElement] = Seq(
    Wheel("front left"), Wheel("front right"),
    Wheel("back left"), Wheel("back right"),
    Body(), Engine())

  override def accept(visitor: Visitors.Visitor) = {
    elements.foreach(_.accept(visitor))
    visitor(this)
  }
}

object Visitors {
  type Visitor = CarElement => Unit

  val printVisitor: Visitor = {
    case Wheel(name) => println(s"Visiting $name wheel")
    case Body() => println("Visiting Body")
    case Engine() => println("Visiting Engine")
    case Car() => println("Visiting Car")
  }

  val doVisitor: Visitor = {
    case Wheel(name) => println(s"Kicking my $name wheel")
    case Body() => println("Moving my body")
    case Engine() => println("Starting my engine")
    case Car() => println("Starting my car")
  }
}

object VisitorDemo {
  def main(args: Array[String]) {
    val car = Car()
    car.accept(Visitors.printVisitor)
    car.accept(Visitors.doVisitor)
  }
}
