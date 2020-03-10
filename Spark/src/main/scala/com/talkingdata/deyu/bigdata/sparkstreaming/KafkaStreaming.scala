package com.talkingdata.deyu.bigdata.sparkstreaming

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaStreaming {
  def main(args: Array[String]): Unit = {
    val config = Map(
      "spark.cores" -> "local[*]",
      "kafka.topic" -> "recommender"
    )

    val sparkConf = new SparkConf().setMaster(config("spark.cores")).setAppName("StreamingRecommender")

    // 创建一个SparkSession
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()

    // 拿到streaming context
    val sc = spark.sparkContext
    val ssc = new StreamingContext(sc, Seconds(2))    // batch duration

    import spark.implicits._
    // 定义kafka连接参数
    val kafkaParam = Map(
      "bootstrap.servers" -> "192.168.217.128:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "recommender",
      "auto.offset.reset" -> "latest"
    )

    // 通过kafka创建一个DStream
    val kafkaStream = KafkaUtils.createDirectStream[String, String]( ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String]( Array(config("kafka.topic")), kafkaParam )
    )

    val ratingStream = kafkaStream.map{
      msg =>
        println(msg)
        val attr = msg.value().split("\\|")
        ( attr(0).toInt, attr(1).toInt, attr(2).toDouble, attr(3).toInt )
    }

    ratingStream.foreachRDD(rdd => {
      println(rdd.map(_._1))
      println(rdd.map(_._2))
    })

    println("start ....")

    ssc.start()
    ssc.awaitTermination()

  }
}
