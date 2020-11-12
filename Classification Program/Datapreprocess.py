import pandas as pd
import numpy as np

def rand_choose(col,df):
    to_return = []
    for x in range(4):
        df[col[x]] = np.where(df[col[x]] == 1, 1, 0.0)
        s = df.sample(n=250, weights=col[x])
        to_return.append(s)
    to_return = pd.concat(to_return)

    return to_return

df = pd.read_csv('Cleaned-Data.csv')
col = ['Severity_None', 'Severity_Mild', 'Severity_Moderate', 'Severity_Severe']


cleaned_data = rand_choose(col,df)
cleaned_data = cleaned_data[['Fever', 'Tiredness', 'Dry-Cough', 'Difficulty-in-Breathing', 'Sore-Throat', 'Pains', 'Nasal-Congestion', 'Runny-Nose', 'Diarrhea', 'Contact_Dont-Know', 'Contact_No', 'Contact_Yes' ]]
#print(cleaned_data.head())

cleaned_data.to_csv("processed_input.csv",index=False)