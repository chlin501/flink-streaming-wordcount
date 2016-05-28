## Flink Stremaing Wordcount Example

### Requirement

 * java: 1.8.x
 * sbt: 0.13.x
 * scala: 2.11.x

### Usage

  * edit `taskmanager.numberOfTaskSlots` value in `${flink-home}/conf/flink-conf.yaml`  
  * start local flink server `start-local.sh`.
  * execute `flink run -p <parallelism-value> /path/to/assembly-jar`.
  * check `/tmp/out` folder.
  * stop local flink server `stop-local.sh`
