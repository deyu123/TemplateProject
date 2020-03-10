package com.talkingdata.deyu.bigdata.sparkcore.roaringbitmap

import org.apache.spark.{SparkConf, SparkContext}

import scala.util.parsing.json._


object RoaringBitmapOttAge {

  def regJSON(json: Option[Any])  = json match {
    case Some(map: Map[String, Any]) => map
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RoaringBitmapOttAge").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val result = sc.textFile("input").map(str => JSON.parseFull(str))
//    result.foreach(println)
    result.foreach(strJson => {
      strJson match {
        case Some(json:Map[String, Any]) => {
          if(json.contains("tags")){
            val tags = json.get("tags")
            println(tags)
            val people = regJSON(tags).get("people")
            println(people)
          }
        }
        case None => println( "parsing failed!" + strJson )
        case other => println("unknown data structure" + other)
      }
  })
  }
}
