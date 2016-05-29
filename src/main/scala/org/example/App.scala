package org.example

import org.apache.flink.api.scala._ 
import org.apache.flink.api.common.accumulators.IntCounter
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

import org.slf4j.LoggerFactory

object App {

  val RECORD_COUNTER = "record_counter"
  
  def main(args: Array[String]) {
    App.create.io("/tmp/in")("/tmp/out")
  }

  def create: App = new App
}
class App {

  import App._

  val log = LoggerFactory.getLogger(classOf[App])

  def io(in: String)(out: String) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val stream = env.readTextFile(in)
    stream.map(new CounterMapFn)
    val result = env.execute("parallel-count")
    val intResult = result.getAccumulatorResult(RECORD_COUNTER).
                           asInstanceOf[Int]
    log.info("total records counted -> "+intResult)
    // write intResult to zk so consumer side can check within its own 
    // accumulator, which is global within the job being executed.
    // when a consumer (thread) finds after its execution reaching the 
    // total count found at zk, then the consumer writes to zk and stop 
    // consumer side job. The procuder side that watches the zk can start 
    // the next job/ step.
  }
}

class CounterMapFn extends RichMapFunction[String, String] {

  import App._

  var counter = new IntCounter
  var serialVersionUID = 1L

  @throws(classOf[Exception])
  override def open(conf: Configuration) {
    getRuntimeContext.addAccumulator(RECORD_COUNTER, counter)  
  }

  @throws(classOf[Exception])
  override def map(value: String): String = {
    counter.add(1)
    value
  }

  @throws(classOf[Exception])
  override def close() { }
}
