package com.deyu.user

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object UserIdEnd {
  def main(args: Array[String]): Unit = {
    val Array(inputPath,outputPath,timeYMD) = args
    val inputPathYmd = inputPath + timeYMD
    val outputPathYmd = outputPath + timeYMD
    val conf = new SparkConf().setAppName("UserIdEnd")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._
//    val json_df = spark.read.json(
//      spark.read.textFile(inputPathYmd).filter(
//        t => t.contains("\"type\":\"bd_client_em_lua_update\"")).toDF("value").map(_.getAs[String]("value")).rdd)

    val json_df = spark.read.table("datasystem.ods_bd_client_em_lua_update").filter($"targetday">="2020-01-14" && $"targetday"<="2020-01-18")
    json_df
      .select($"targetday",$"userId".mod(10).as("tail_num"),$"luaUpdateId")
      .groupBy("tail_num","luaUpdateId")
      .count()
      .repartition(1)
      .write
      .partitionBy("targetday")
      .mode("overwrite")
      .option("header","true")
      .csv("/test/output/lua")
  }

}
