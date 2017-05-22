package br.ufc.mdcc.spark.reviews

import org.apache.spark.{SparkConf, SparkContext}
import play.api.libs.json.Json

/**
  * Created by igobrilhante on 22/05/17.
  */

case class Review(title: String, text: String)

object ReviewsWordCountExample {

  implicit val reviewFormat = Json.format[Review]

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .setAppName("Review Word Count Cluster Example MDCC UFC")

    val sc = new SparkContext(conf)

    val json = sc.textFile("file://[FILE]")

    val words = json
      // turn line into a review
      .map(toReview)
      // turn review into a list of words
      .flatMap( review => review.text.split(" ").filter( w => w.length > 3))
      // Map each word to a tuple (word, 1)
      .map( word => (word, 1) )

    val wordCounts = words.reduceByKey( (a,b) => a + b )

    val top20 = wordCounts
      .sortBy( word => word._2, ascending = false )
      .take(20)

    top20.foreach(println)

    sc.stop()
  }

  def toReview(line: String): Review = {
    Json.parse(line).as[Review]
  }

}
