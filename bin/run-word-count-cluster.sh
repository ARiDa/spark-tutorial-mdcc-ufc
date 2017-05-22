#!/usr/bin/env bash
SPARK=spark-submit

 ${SPARK}   --master "spark://Igos-MacBook-Pro.local:7077" \
            --class br.ufc.mdcc.spark.wordcount.WordCountClusterExample \
	        --driver-memory 512m \
	        --executor-memory 512m \
	        --deploy-mode cluster \
            target/scala-2.11/spark-tutorial-ufc-assembly-1.0.jar \
            $1
