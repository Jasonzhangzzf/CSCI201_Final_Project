from numpy import genfromtxt
import numpy as np
import pandas as pd

def load_data():
    my_data = genfromtxt('processed_input.csv', delimiter=',')
    my_data = np.delete(my_data, (0), axis=0)
    return my_data

def write_data(user_array,userID):
    df = pd.read_csv('cleaned_Data')