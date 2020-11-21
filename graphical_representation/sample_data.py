import pandas as pd

full_data = pd.read_csv("Documents/2020-21/CSCI-201/Final_project/Cleaned-Data.csv")

fraction = int(len(full_data) * 15 / 17)
full_data = full_data.sample(frac = 1)
full_data = full_data.iloc[0:fraction, 0:len(full_data.columns)]
    
full_data.to_csv("sampled_data.csv")
