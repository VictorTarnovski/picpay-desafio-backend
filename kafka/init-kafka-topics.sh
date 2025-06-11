#!/bin/sh

KT="/opt/kafka/bin/kafka-topics.sh"

echo "Waiting for kafka..."
"$KT" --bootstrap-server localhost:9092 --list

echo "Creating kafka topics"

topics=("accountOpened")

for topic in "${topics[@]}"
do
    "$KT" --bootstrap-server localhost:9092 --create --if-not-exists --topic "$topic" --replication-factor 1 --partitions 1
done

echo "Successfully created the following topics:"
"$KT" --bootstrap-server localhost:9092 --list
