#!/usr/bin/python

def generate_number_file(n):
        with open('numbers_10e' + str(n) + '.txt', 'w') as n_file:
                for i in xrange(2, 10**n):
                        if i % 2 != 0:
                                n_file.write(str(i) + '\n')

generate_number_file(6)
