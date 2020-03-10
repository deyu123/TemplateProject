package com.talkingdata.deyu.bigdata.sparkcore.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object HFile {
  def main(args: Array[String]): Unit = {
    // 创建配置项
    val conf = new SparkConf().setMaster("local[*]").setAppName("HFile")
    val sc = new SparkContext(conf)


    val HFileRDD: RDD[(String, Int)] = sc.parallelize(List(("a", 3), ("a", 3), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 3)

    val partitionHFile = HFileRDD.partitionBy(new HFilePartition(1))

    val PartitionsHFile = HFileRDD.repartitionAndSortWithinPartitions(new HFilePartition(3))
    val repartitionHFile = HFileRDD.repartition(3)

    val result = repartitionHFile.mapPartitionsWithIndex {
      (ind, datas) => {
        // ind 分区
        datas.map((ind + "_", _))
      }
    }
    result.collect().foreach(println)

  }

}

class HFilePartition(num : Int) extends Partitioner {
  // 选定 1-128 的数值
  val fraction = 1 max num min 128

  override def numPartitions: Int = num

  override def getPartition(key: Any): Int = {
    1
  }
}
