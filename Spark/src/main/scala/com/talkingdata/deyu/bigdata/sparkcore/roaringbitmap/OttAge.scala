package com.talkingdata.deyu.bigdata.sparkcore.roaringbitmap

import org.apache.spark
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext, sql}
import org.roaringbitmap.RoaringBitmap


object OttAge {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("OttAge")
    val sqlContext = new spark.sql.SparkSession.Builder().config(conf).master("local[*]").appName("OttAge").getOrCreate()

    val tags = sqlContext.read.json("input").select("offsetid", "tags.people")
      .createOrReplaceTempView("A")
    val resultDF = sqlContext.sql("select offsetid, people.gender.tagid as gender, people.age.tagid as age, " +
      "people.marriage.tagid as marriage, people.car.tagid as car from A").na.drop()
//    val generDF = sqlContext.sql("select offsetid, people.gender.tagid from A").na.drop()
//    val ageDF = sqlContext.sql("select offsetid, people.age.tagid from A").na.drop()
//    val marriageDF = sqlContext.sql("select offsetid, people.marriage.tagid from A").na.drop()
//    val carDF = sqlContext.sql("select offsetid, people.car.tagid from A").na.drop()
    //    generDF.rdd.foreach(println)
    //    ageDF.rdd.foreach(println)
    //    marriageDF.rdd.foreach(println)
    //    carDF.rdd.foreach(println)
    resultDF

    val carResult = resultDF.rdd.flatMap(row => {
      val offsetid = row.getAs[Long]("offsetid")
      val car = row.getAs[Seq[String]]("car")
      car.filter(_ != "").flatMap(line => {
        Some(line, offsetid)
      }).distinct
    })

    carResult.foreach( println)

    println("car------------------")


//    val ageResult = ageDF.rdd.flatMap(row => {
//      val offsetid = row.getAs[Long]("offsetid")
//      val car = row.getAs[Seq[String]]("tagid")
//      car.filter(_ != "").flatMap(line => {
//        Some(line, offsetid)
//      }).distinct
//    })
//
//    ageResult.foreach(println)
//    println("ageResult------------------")
//
//
//    val generResult = generDF.rdd.flatMap(row => {
//      val offsetid = row.getAs[Long]("offsetid")
//      val car = row.getAs[Seq[String]]("tagid")
//      car.flatMap(line => {
//        Some(line, offsetid)
//      }).distinct
//    })
//
//    generResult.foreach(println)
//    println("gener------------------")
//
//
//    val marriageResult = marriageDF.rdd.flatMap(row => {
//      val offsetid = row.getAs[Long]("offsetid")
//      val car = row.getAs[Seq[String]]("tagid")
//      car.flatMap(line => {
//        Some(line, offsetid)
//      }).distinct
//    })
//
//    marriageResult.foreach(println)
//    println("marriage------------------")

//    val carResult = carDF.rdd.flatMap(row => {
//      val offsetid = row.getAs[String]("offsetid")
//      val car = row.get(1)
//      if (car.isInstanceOf[Seq[String]]) {
//        val carSeq = car.asInstanceOf[Seq[String]]
//        carSeq.flatMap(line => {
//          Some(line, offsetid)
//        }).distinct
//      } else {
//        Some(car, offsetid)
//      }
//    })
//
//    carResult.foreach(println)


//    val marriageResult = marriageDF.rdd.flatMap(row => {
//      val offsetid = row.getAs[String]("offsetid")
//      val marriage = row.get(1)
//      if (marriage.isInstanceOf[Seq[String]]) {
//        val marriageSeq = marriage.asInstanceOf[Seq[String]]
//        marriageSeq.flatMap(line => {
//          Some(line, offsetid)
//        }).distinct
//      } else {
//        Some(marriage, offsetid)
//      }
//    })
//    marriageResult.foreach(println)
//    val generResult = generDF.rdd.flatMap(row => {
//      val offsetid = row.getAs[String]("offsetid")
//      val gener = row.get(1)
//      if (gener.isInstanceOf[Seq[String]]) {
//        val generSeq = gener.asInstanceOf[Seq[String]]
//        generSeq.flatMap(line => {
//          Some(line, offsetid)
//        }).distinct
//      }else {
//        Some(gener, offsetid)
//      }
//
//    })
//
//    generResult.foreach(println)

    //    val genderRDD = genderResult.map(line => {
    //      val gender = line._1
    //      val offsetId = line._2.toLong
    //      val offsetIdInt = (offsetId % 10000000).toInt
    //      val partitionId = (offsetId / 10000000).toInt
    //      (gender, partitionId) -> RoaringBitmap.bitmapOf(offsetIdInt)
    //    }) reduceByKey ((a, b) => {
    //      a.or(b)
    //      a
    //    })

  }

}
