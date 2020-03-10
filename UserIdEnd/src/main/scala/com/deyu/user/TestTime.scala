package com.deyu.user

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.commons.lang3.time.FastDateFormat
import org.joda.time.DateTime

object TestTime {
  def main(args: Array[String]): Unit = {
    // 将日期转成时间戳
//    val dateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS")
//    val timeFormat = dateFormat.parse("2020-02-05 09:08:09.999").getTime.toString
//    println(timeFormat)

    //将时间戳转成日期
    val tm = "1502036122000"
    val a = tranTimeToString(tm)
    println(a)

    // 测试mkString

    val list = List(1, 2,3,4)
    val str = list.mkString(",")
    println(str)

  }

  def tranTimeToString(tm:String) :String={
    val fm = new SimpleDateFormat("yyyy-MM-dd")
    val tim = fm.format(new Date(tm.toLong))
    tim
  }



}
