package com.talkingdata.deyu.bigdata.sparkcore.demo

import org.apache.spark.{SparkConf, SparkContext}

object RDD_Reduce {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("RDD_Reduce")
        val sc = new SparkContext(conf)
        val rdd = sc.parallelize(1 to 10)
        val sum = rdd.reduce((x, y) => (x + y))
        println(sum)
    }
}