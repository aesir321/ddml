import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object kmers {
	def main(args: Array[String]): Unit = {
		if (args.size < 3) {
			println("Usage: <Kmer fasta file path> <K> <N>")
			sys.exit(1)
		}
		
		val sparkConf = new SparkConf().setAppName("kmers")
		val sc = new SparkContext(sparkConf)
		
		val input = args(0)
		val K = args(1).toInt
		val N = args(2).toInt

		val bcK = sc.broadcast(K)
		val bcN = sc.broadcast(N)
		val data = sc.textFile(input)
		
		// BAd naming here, think it will clash with object name, so capitalised the K.
		val Kmers = data.flatMap(_.sliding(bcK.value, 1).map((_, 1))) 
		val groupedKmers = Kmers.reduceByKey(_ + _)
		val partitions = groupedKmers.mapPartitions(_.toList.sortBy(_._2).takeRight(bcN.value).toIterator)
		val allTopN = partitions.sortBy(_._2, false, 1).take(bcN.value)

		allTopN.foreach(println)

		sc.stop()
	}
}
