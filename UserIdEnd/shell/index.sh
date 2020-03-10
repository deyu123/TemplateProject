#!/bin/bash

class_name=com.deyu.user.UserIdEnd
class_path=/home/hadoop/deyu.zhang/2020-02-05/useridend-module-1.0.0.jar
inputPath=/flume/source/jsonlogs_new/
outputPath=/tmp/deyu.zhang/
timeYmd=2020-02-05

hadoop fs -rm -r ${outputPath}${timeYmd}

spark-submit --master yarn --deploy-mode client --num-executors 3 --executor-memory 3G --executor-cores 3 --class  ${class_name}  ${class_path} ${inputPath} ${outputPath}  ${timeYmd}

if [ "$?" -ne "0" ]; then
   exit 2
fi