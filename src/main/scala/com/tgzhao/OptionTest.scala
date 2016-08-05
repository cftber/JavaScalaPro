package com.tgzhao

/**
 * Created by tgzhao on 2016/8/4.
 * 作为集合的 Option
 * Option 表现的像集合
 */
object OptionTest {
  def main(args: Array[String]) {

    //在某些方面，Option 类型或 Option[T]，并不重视描述。它是一个具有两个子类 Some[T] 和 None 的泛型类，
    val footballTeamsAFCEast =
      Map("New England" -> "Patriots",
        "New York" -> "Jets",
        "Buffalo" -> "Bills",
        "Miami" -> "Dolphins",
        "Los Angeles" -> null)
    println(footballTeamsAFCEast.get("Miami"), Some("Dolphins"))
    println(footballTeamsAFCEast.get("Miami").get, "Dolphins")
    println(footballTeamsAFCEast.get("Los Angeles"), Some(null))
    println(footballTeamsAFCEast.get("Sacramento"), None)

    println

    println(show(footballTeamsAFCEast.get("Sacramento")))
    println(show(footballTeamsAFCEast.get("Miami")))
    println(show(footballTeamsAFCEast.get("Los Angeles")))

    println

    val user = User(2, "Johanna", "Doe", 30, None)
    println("Gender: " + user.gender.getOrElse("not specified")) // will print "not specified"
    UserRepository.findById(2).foreach(user => println(user.firstName + user.gender))
    val age = UserRepository.findById(1).map(_.age) // age is Some(32)
    val gender = UserRepository.findById(1).map(_.gender) // gender is an Option[Option[String]]
    println(age)
    println(gender)
  }

  //Some() 和 None 之间切换
  //清单 2 展示了 Scala 的模式匹配
  def show(value : Option[String]) =
  {
    value match
    {
      case Some(x) => x
      case None => "No team found"
    }
  }
}

case class User(
   id: Int,
   firstName: String,
   lastName: String,
   age: Int,
   gender: Option[String]
   )

object UserRepository {
  private val users = Map(1 -> User(1, "John", "Doe", 32, Some("male")),
                            2 -> User(2, "Johanna", "Doe", 30, None))
  def findById(id: Int): Option[User] = users.get(id)
  def findAll = users.values
}