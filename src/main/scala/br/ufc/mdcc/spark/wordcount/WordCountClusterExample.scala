package br.ufc.mdcc.spark.wordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by igobrilhante on 22/05/17.
  */
object WordCountClusterExample {

  def main(args: Array[String]): Unit = {

    val topK = args.headOption.fold(10)( v => v.toInt )

    val conf = new SparkConf()
      .setAppName("Word Count Cluster Example MDCC UFC")

    val sc = new SparkContext(conf)

    val json = sc.textFile("file://[FILE]")

    val words = json
      // turn line into a list of strings
      .flatMap( splitLine )
      // Map each word to a tuple (word, 1)
      .map( word => (word, 1) )

    val wordCounts = words.reduceByKey( (a,b) => a + b )

    val top = wordCounts.sortBy( word => word._2, ascending = false )

    top
      .take(topK)
      .foreach(println)

    top.saveAsTextFile("file://[FOLDER]")

    //sc.stop()
  }

  def splitLine(line: String): Array[String] = {
    line
      // split the line by whitespace
      .split(" ")
      // consider only words larger than 2
      .filter( word => word.length > 2 )
  }

}
