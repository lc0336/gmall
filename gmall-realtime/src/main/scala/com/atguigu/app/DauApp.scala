package com.atguigu.app

import org.apache.spark.{SparkConf, SparkContext}

class DauApp {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("DauApp")
    val sc = new SparkContext(conf)


  }

}
