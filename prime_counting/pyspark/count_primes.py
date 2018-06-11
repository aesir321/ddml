#!/usr/bin/env python

import sys
from pyspark.sql import DataFrame
from pyspark.sql import Row
from pyspark.sql import SparkSession
from pyspark.sql.functions import udf
from pyspark.sql.types import *


if __name__ == "__main__":
        spark = SparkSession.builder.appName("count_primes").getOrCreate()


        def is_prime(n):
                if n == 2 or n == 3: return True
                if n < 2 or n % 2 == 0: return False
                if n < 9: return True
                if n % 3 == 0: return False
                r = int(n**0.5)
                f = 5

                while f <= r:
                        if n % f == 0: return False
                        if n % (f + 2) == 0: return False
                        f +=6
                return True
        
        is_prime_udf = udf(is_prime)

        n = 1 #int(sys.argv[1]) if len(sys.argv) > 1 else 9
        numbers = [1, 2]
        numbers.extend(spark.xrange(3, long(10 ** n), step=2))
        primes = numbers.withColumn('is_prime', is_prime_udf('id').cast(LongType()))
        prime_count = primes.agg({'is_prime':'sum'})
        prime_count.write.csv('output/pyspark_10e_' + str(n))

        spark.stop()
