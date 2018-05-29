#!/usr/bin/env python3
import sys
from is_prime import is_prime

# input comes from STDIN (standard input)
for line in sys.stdin:
        # remove leading and trailing whitespace
        line + line.strip()
        # split the line into words
        words = line.split()
        #increase counters
        for word in words:
                # write the results to STDOUT (standard output);
                # what we output here will be the input for the
                # Reduce step, i.e, the input for reducer.py
                # 
                # tab-deliminated; the trivial word count is 1
                print('{}\t{}'.format(is_prime(int(word)), 1))
