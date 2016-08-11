package com.tgzhao

/**
  * Created by tgzhao on 16/8/9.
  */
object TypeObj {

  type People = List[Person]

  type Tax = Person => Double
  type PersonPredicate = Person => Boolean
  val incomeTax: Tax = person => person.income * 5 / 100
  val kejuanzaTax: Tax = person => person.income * 20 / 100
  def giveMeYourMoney(p: Person) = {
    calculateTax(p, List(incomeTax, kejuanzaTax))
  }
  def calculateTax(person: Person, taxes: List[Tax]): Double = {
    taxes.foldLeft(0d) {
      (acc, curTax) => acc + curTax(person)
    }
  }

  def filterPerson(people: People, personPredicate: PersonPredicate): People = {
    people.filter(personPredicate)
  }

  def main(args: Array[String]) {
    tryAccessingLocalVariable
    val person1 = new Person("name1", 21, 9000)
    val person2 = new Person("name3", 30, 14000)
    val person3 = new Person("name2", 24, 28000)
    val people = List[Person](person1, person2, person3)
    println(giveMeYourMoney(person1))
    println(giveMeYourMoney(person2))
    println(giveMeYourMoney(person3))


    val personPredicate: PersonPredicate = person => person.income > 13000
    println(filterPerson(people, personPredicate))




  }

  def tryAccessingLocalVariable {
    var number = 123
    println(number)

    var lambda = () => {
      number = 456
      println(number)
    }

    lambda.apply()
    println(number)
  }
}

case class Person (val name: String, val age: Int, val income: Double) {
  def register() = {
    this.copy()
  }

}
