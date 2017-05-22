package br.ufc.mdcc.spark.wordcount

import org.apache.spark.{SparkConf, SparkContext}


object WordCountExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("Word Count Example MDCC UFC")
      .setMaster("local[*]")

    val sc = new SparkContext(conf)

    val json = sc.textFile("file://[FILE]")

    val words = json
      // turn line into a list of strings
      .flatMap( splitLine )
      // Map each word to a tuple (word, 1)
      .map( word => (word, 1) )

    val wordCounts = words.reduceByKey( (a,b) => a + b )

    val top20 = wordCounts
      .sortBy( word => word._2, ascending = false )
      .take(20)

    top20.foreach(println)

    sc.stop()
  }

  def splitLine(line: String): Array[String] = {
    line
      // split the line by whitespace
      .split(" ")
      // consider only words larger than 2
      .filter( word => word.length > 2 )
  }

}
