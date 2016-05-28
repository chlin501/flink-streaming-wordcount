package org.example

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.io.TextInputFormat
import org.apache.flink.api.java.typeutils.TypeExtractor
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.source.FileSourceFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

class MyReader(input: TextInputFormat, info: TypeInformation[String]) 
      extends FileSourceFunction[String](input, info) {

  @throws(classOf[Exception])
  override def open(conf: Configuration) {

  }

  override def run(ctx: SourceContext[String]) {
    
  }
}

object App {
  def main(args: Array[String]) {

  }
}
class App {

  def fromText(path: String) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.execute
  }
}

