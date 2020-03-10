package com.talkingdata.deyu.myscala.chapter08

import scala.collection.immutable

object MaxMIn {
  def main(args: Array[String]): Unit = {
    val num: Int = 0
    // 限定数据的范围
    val fraction: Int = 1 max num min 128

    val list = List("a", "b", "c", "d")
    val map = Map("a" -> "av", "b"-> "bv")
    println(map)
    println(map.toSeq)   // 转换为了ArrayBuffer
    val seq: immutable.Seq[String] = list.toSeq
    println(seq)
    println(Int.MaxValue)
    println(fraction)

  }

}
