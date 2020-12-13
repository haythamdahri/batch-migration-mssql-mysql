## CHECK IF JAR FILE IS IN PLACE
FILE=$(pwd)/migration-batch.jar
if test -f "$FILE"; then
    echo "$FILE exists."
else
  echo "No JAR FILE, PLEASE INCLUDE BATCH PROCESSING FILE: migration-batch.jar"
  exit
fi
## RUN JAR WITH JDK 11
/opt/Java/jdk-11.0.6/bin/java -jar -Dspring.batch.job.enabled=true -Dspring.config.location=file://$(pwd)/application.properties $FILE