#!/usr/bin/python

from pyspark.ml.feature import NGram
from pyspark.sql import SparkSession
from read_fasta import read_fasta

if __name__ == "__main__":
        spark = SparkSession.builder.appName("4gram").getOrCreate()

        fasta_line = list(read_fasta("tests.fasta"))
        df = spark.createDataFrame([(0, fasta_line)], ["id", "aminos"])
        ngram = NGram(n=4, inputCol="aminos", outputCol="ngrams_aminos")
        ngram_df = ngram.transform(df)
        ngram_df.select("ngrams_aminos").show(truncate=False)
        spark.stop()
