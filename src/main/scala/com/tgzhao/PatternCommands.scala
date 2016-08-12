package com.tgzhao

/**
 * Created by tgzhao on 2016/8/12.
 */

case class Light(location: String) {
  def on() = println(s"$location light is on")

  def off() = println(s"$location light is off")
}

case class TV(location: String) {
  private var channel: Int = 0

  def on() = println(location + " TV is on")

  def off() = println(location + " TV is off")

  def setInputChannel() = {
    this.channel = 3
    println(location + " TV channel is set for DVD")
  }
}

case class Stereo(location: String) {
  def on() = println(s"$location stereo is on")

  def off() = println(s"$location stereo is off")
}

case class Hottub(var isOn: Boolean = false) {
  private var temperature: Int = 0

  def on() = isOn = true

  def off() = isOn = false

  def circulate() = if (isOn) println("Hottub is bubbling!")

  def setTemperature(temperature: Int) = {
    if (temperature > this.temperature) {
      println("Hottub is heating to a steaming " + temperature + " degrees")
    }
    else {
      println("Hottub is cooling to " + temperature + " degrees")
    }
    this.temperature = temperature
  }
}

object Commands {
  type Command = () => Unit

  def tvOn(tv: TV)() = {
    tv.on()
    tv.setInputChannel()
  }

  def hottubOn(hottub: Hottub)() = {
    hottub.on()
    hottub.setTemperature(104)
    hottub.circulate()
  }

  def hottubOff(hottub: Hottub)() = {
    hottub.setTemperature(98)
    hottub.off()
  }

  def macroCommand(commands: Command*)() =
    commands.foreach(_())
}

case class RemoteControl(onCommands: Seq[Commands.Command], offCommands: Seq[Commands.Command]) {
  def pushOnButton(slot: Int) = onCommands(slot)()

  def pushOffButton(slot: Int) = offCommands(slot)()
}

object RemoteLoader {
  def main(args: Array[String]) {
    val light = Light("living room")
    val tv = TV("living room")
    val stereo = Stereo("living room")
    val hottub = Hottub()

    val on: Commands.Command = Commands.macroCommand(light.on,
      stereo.on, Commands.tvOn(tv), Commands.hottubOn(hottub))

    val off: Commands.Command = Commands.macroCommand(light.off,
      stereo.off, tv.off, Commands.hottubOff(hottub))

    val remoteControl = RemoteControl(Seq(on), Seq(off))

    println("--- Pushing Macro On---")
    remoteControl.pushOnButton(0)
    println("--- Pushing Macro Off---")
    remoteControl.pushOffButton(0)
  }
}