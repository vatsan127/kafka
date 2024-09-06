# _Confluent kafka setup_

1. Download the data community package

```
curl -O https://packages.confluent.io/archive/7.7/confluent-community-7.7.0.zip
```

2. Unzip package

```
unzip confluent-community-7.7.0.zip
```

3. Set environment vairables

```
vim ~/.bashrc
```

add these in the end of the file

```
export CONFLUENT_HOME=~/confluent-7.7.0
export PATH=$PATH:$CONFLUENT_HOME/bin
```

```
source ~/.bashrc
```

4. zookeeper properties

```
${CONFLUENT_HOME}/etc/kafka/zookeeper.properties
```

6. Kafka properties

```
${CONFLUENT_HOME}/etc/kafka/server.properties
```
add these properties
```
listeners=PLAINTEXT://:9092
advertised.listeners=PLAINTEXT://localhost:9092
```

7. schema registry properties

```
${CONFLUENT_HOME}/etc/schema-registry/schema-registry.properties
```

----
start All components:

```
zookeeper-server-start ${CONFLUENT_HOME}/etc/kafka/zookeeper.properties
```

```
kafka-server-start ${CONFLUENT_HOME}/etc/kafka/server.properties
```

```
schema-registry-start ${CONFLUENT_HOME}/etc/schema-registry/schema-registry.properties
```