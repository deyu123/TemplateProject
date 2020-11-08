package com.deyu.bigdata.flink

import org.apache.flink.streaming.api.scala._

object StreamWorldCount {
  def main(args: Array[String]): Unit = {
    val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    environment.socketTextStream()
  }

}
