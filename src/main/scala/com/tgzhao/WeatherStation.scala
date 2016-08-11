package com.tgzhao


/**
  * Created by tgzhao on 16/8/11.
  */
case class WeatherData(temperature: Float = 0,
                       humidity: Float = 0,
                       pressure: Float = 0,
                       observers: Seq[Observers.Observer] = Nil,
                       history: Seq[WeatherData] = Nil) {

  def register(observer: Observers.Observer) =
    this.copy(temperature, humidity, pressure, observers :+ observer, history)

  def weatherChanged(weatherData: WeatherData) = {
    val newHistory = history :+ weatherData
    observers.foreach(observer => observer(newHistory))
    this.copy(temperature, humidity, pressure, observers, newHistory)
  }
}

object Observers {
  type Observer = Seq[WeatherData] => Unit

  val currentConditionsDisplay: Observer = history =>
    println(s"Current conditions: " +
      s"${history.last.temperature} F degrees and " +
      s"${history.last.humidity} % humidity")

  val statisticsDisplay: Observer = history =>
    println(s"Avg/Max/Min temperature = " +
      s"${history.map(_.temperature).sum / history.size}" +
      s"/${history.map(_.temperature).max}" +
      s"/${history.map(_.temperature).max}")

  val forecastDisplay: Observer = history => {
    val currentPressure = history.last.pressure
    val lastPressure = history.dropRight(1).last.pressure

    print("Forecast: ")
    if (currentPressure > lastPressure) println("Improving weather on the way!")
    else if (currentPressure == lastPressure) println("More of the same")
    else if (currentPressure < lastPressure) println("Watch out for cooler, rainy weather")
  }
}

object WeatherStation {
  def main(args: Array[String]) {
    val weatherData = WeatherData()
      .register(Observers.currentConditionsDisplay)
      .register(Observers.statisticsDisplay)
      .register(Observers.forecastDisplay)

    weatherData
      .weatherChanged(WeatherData(80, 65, 30.4f))
      .weatherChanged(WeatherData(82, 70, 29.2f))
      .weatherChanged(WeatherData(78, 90, 29.2f))
  }
}