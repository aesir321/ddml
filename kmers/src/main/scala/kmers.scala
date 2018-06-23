import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object kmers {
	def main(args: Array[String]): Unit = {
		if (args.size < 3) {
			println("Usage: <Kmer fasta file path> <Output file path> <K>")
			sys.exit(1)
		}
		
		val sparkConf = new SparkConf().setAppName("kmers")
		val sc = new SparkContext(sparkConf)
		
		val input = args(0)
		val output = args(1)
		val K = args(2).toInt

		val bcK = sc.broadcast(K)
		val data = sc.textFile(input, sc.defaultParallelism)
		
		// Bad naming here, think it will clash with object name, so capitalised the K.
		val Kmers = data.flatMap(_.sliding(bcK.value, 1).map((_, 1))) 
		val groupedKmers = Kmers.reduceByKey(_ + _)
		val partitions = groupedKmers.mapPartitions(_.toList.sortBy(_._2).toIterator)
		partitions.saveAsTextFile(output)

		sc.stop()
	}
}
