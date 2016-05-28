## Flink Stremaing Wordcount Example

### Requirement

 * java: 1.8.x
 * sbt: 0.13.x
 * scala: 2.11.x

### Usage

  * start local flink server `start-local.sh`.
  * execute `flink run -p <parallelism-num> /path/to/assembly-jar`.
  * check /tmp/out folder.
  * stop local flink server `stop-local.sh`
