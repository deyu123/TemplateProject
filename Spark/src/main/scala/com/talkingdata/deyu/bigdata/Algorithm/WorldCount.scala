package com.talkingdata.deyu.bigdata.Algorithm

import org.apache.spark.{SparkConf, SparkContext}

object WorldCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("WorldCount")
    val sc = new SparkContext(conf)
//    sc.textFile("in").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).sortBy(_._2, false).saveAsTextFile("output")
    val input_path = "/datamodeling/user/ting.zhou/model/prediction/loans_market/output/2019/10/15,/datamodeling/user/ting.zhou/model/prediction/loans_market/output/2019/10/14,/datamodeling/user/ting.zhou/model/prediction/loans_market/output/2019/10/13,/datamodeling/user/ting.zhou/model/prediction/loans_market/output/2019/10/12"
    val paths = input_path.split(",")
    var flag = true
    var date = ""
    var existPath = ""
    for (i <- 0.to(paths.length -1) if flag) {
      val path = paths(i)
      //      if (HdfsUtil.exists(context, path)) {
      val pathStr = paths(i).split("/")
      val day = pathStr(pathStr.length - 1)
      val month = pathStr(pathStr.length - 2)
      val year = pathStr(pathStr.length - 3)
      date = year + "-" + month + "-" + day
      existPath = path
      println(existPath)
//      flag = false
      //      }
    }
  }
}
