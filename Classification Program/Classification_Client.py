from numpy import genfromtxt
import numpy as np

my_data = genfromtxt('processed_input.csv', delimiter=',')
my_data = np.delete(my_data, (0), axis=0)
print(my_data[0])