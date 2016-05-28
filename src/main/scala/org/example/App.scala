package org.example

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._ 

object App {
  def main(args: Array[String]) {
    App.create.io("/tmp/in")("/tmp/out")
  }

  def create: App = new App
}
class App {

  def io(in: String)(out: String) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val stream = env.readTextFile(in)
    stream.flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }.
           map { (_, 1) }.
           keyBy(0).
           sum(1).
           writeAsText(out)
    env.execute("parallel-wordcount")
  }
}

