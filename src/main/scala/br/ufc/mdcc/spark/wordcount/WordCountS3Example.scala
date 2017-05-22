package br.ufc.mdcc.spark.wordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by igobrilhante on 22/05/17.
  */
object WordCountS3Example {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("Word Count S3 Example MDCC UFC")
      .setMaster("local[*]")


    val sc = new SparkContext(conf)
    sc.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", "")
    sc.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", "")

    // s3n://myBucket/myFile1.log
    val lines = sc.textFile("s3n://[BUCKET]/[FILE]")

    val words = lines
      // turn line into a list of strings
      .flatMap( splitLine )
      // Map each word to a tuple (word, 1)
      .map( word => (word, 1) )

    val wordCounts = words.reduceByKey( (a,b) => a + b )

    val top = wordCounts.sortBy( word => word._2, ascending = false )

    top
      .take(20)
      .foreach(println)

    top.saveAsTextFile("s3n://[BUCKET]/[FOLDER]")

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
