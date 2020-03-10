package com.talkingdata.deyu.bigdata.Algorithm

import org.apache.spark.{SparkConf, SparkContext}

object TestSubstring {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")
    val sc = new SparkContext(conf)
//    sc.textFile("in").map(line => {line  + "\t" + line.substring(8,9)}).foreach(println)

    for(i <- 0.to(10)){
      if (i < 10) {
        println("0" + i)

      }
    }
  }
}
