package com.deyu.bigdata.flink

import org.apache.flink.streaming.api.scala._

case class SensorData(id:String, name:String, data:Double)
object TableSQL {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    val inputSteam: DataStream[String] = env.readTextFile("/Users/deyu/workspace/homeProject/TemplateProject/data/sensorData.txt")

    val dataStream: DataStream[SensorData] = inputSteam.map(line => {
      val dataArr = line.split(",")
      SensorData(dataArr(0), dataArr(1), dataArr(2).toDouble)
    })

    dataStream
    
  }

}
