#!/usr/bin/env bash
SPARK=spark-submit

 ${SPARK}   --master "spark://[HOST]:[PORT]" \
            --class br.ufc.mdcc.spark.reviews.ReviewsWordCountExample \
	        --driver-memory 512m \
	        --driver-class-path target/scala-2.11/spark-tutorial-ufc-assembly-1.0-deps.jar \
	        --executor-memory 512m \
	        --deploy-mode client \
	        --jars target/scala-2.11/spark-tutorial-ufc-assembly-1.0-deps.jar \
            target/scala-2.11/spark-tutorial-ufc-assembly-1.0.jar \
            $1
