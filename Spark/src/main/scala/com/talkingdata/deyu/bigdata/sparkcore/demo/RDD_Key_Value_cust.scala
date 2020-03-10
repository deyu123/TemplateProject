package com.talkingdata.deyu.bigdata.sparkcore.demo

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object RDD_Key_Value_cust {
  def main(args: Array[String]): Unit = {
    //partition by
    val conf = new SparkConf().setAppName("WC").setMaster("local")
    val sc = new SparkContext(conf)

    val RDD = sc.parallelize(List(("a", 3), ("a", 3), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 3)
    val partitionRDD = RDD.partitionBy(new MyHashPartitioner(3))
    val result = partitionRDD.mapPartitionsWithIndex {
      (ind, datas) => {
        //  改变一些结构
        datas.map((ind, _))
      }
    }
    result.collect().foreach(println)

  }

}

class MyHashPartitioner(num:Int) extends Partitioner {
  // 分区的个数
  override def numPartitions: Int = num

  // 分区的规则
  override def getPartition(key: Any): Int = {
    // 也可以使用模式匹配
//    1
    2
  }
}
