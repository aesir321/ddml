import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import java.util.Calendar

object kmers {
	def main(args: Array[String]): Unit = {
		if (args.size < 3) {
			println("Usage: <Kmer fasta file path> <K> <E>")
			sys.exit(1)
		}

		val input = args(0)
		val K = args(1)
		val E = args(2)
		val appName = K + "mers" + "_" + E + "exs"
		val output = "output/uniref_90_" + appName +  "_" + Calendar.getInstance.getTimeInMillis

		val sparkConf = new SparkConf().setAppName(appName)
		val sc = new SparkContext(sparkConf)
		
		val KI = K.toInt
		val bcK = sc.broadcast(KI)
		val data = sc.textFile(input, sc.defaultParallelism)
		
		// Bad naming here, think it will clash with object name, so capitalised the K.
		val Kmers = data.flatMap(_.sliding(bcK.value, 1).map((_, 1))) 
		val groupedKmers = Kmers.reduceByKey(_ + _)
		val partitions = groupedKmers.mapPartitions(_.toList.sortBy(_._2).toIterator)
		
		partitions.saveAsTextFile(output)

		sc.stop()
	}
}
