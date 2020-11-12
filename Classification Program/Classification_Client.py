from numpy import genfromtxt
import numpy as np

def load_data():
    my_data = genfromtxt('processed_input.csv', delimiter=',')
    my_data = np.delete(my_data, (0), axis=0)
    return my_data
