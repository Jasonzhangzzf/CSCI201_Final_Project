import pandas as pd
import numpy as np
import mpld3
from mpld3 import plugins
from random import *

full_data = pd.read_csv("Documents/2020-21/CSCI-201/Final project/Cleaned-Data.csv")

fraction = int(len(full_data) * 15 / 17)
full_data = full_data.sample(frac = 1)
full_data = full_data.iloc[0:fraction, 0:len(full_data.columns)]
    
print(len(full_data))
fever = 1
tiredness = 1
dry_cough = 1
difficulty_breathing = 1
pains = 0
runny_nose = 0
gender = 0

if (gender == 1):
    compacted_data = full_data[(full_data["Fever"] == fever) & (full_data["Tiredness"] == tiredness) & (full_data["Dry-Cough"] == dry_cough) & (full_data["Difficulty-in-Breathing"] == difficulty_breathing) & (full_data["Pains"] == pains) & (full_data["Runny-Nose"].astype(np.int) == runny_nose) & (full_data["Gender_Male"] == 1)]
else:
    compacted_data = full_data[(full_data["Fever"] == fever) & (full_data["Tiredness"] == tiredness) & (full_data["Dry-Cough"] == dry_cough)]# & (full_data["Difficulty-in-Breathing"] == difficulty_breathing) & (full_data["Sore-Throat"] == sore_throat) & (full_data["Pains"] == pains) & (full_data["Nasal-Congestion"] == nasal_congestion) & (full_data["Runny-Nose"] == runny_nose) & (full_data["Diarrhea"] == diarrhea) & (full_data["Gender_Female"] == 1)]

none_data = compacted_data.groupby("Severity_None")["Severity_None"].count()
if (type(none_data) == None):
    num_none = 0
elif (none_data.get(1) == None):
    num_none = 0
else:
    num_none = none_data[1]

mild_data = compacted_data.groupby("Severity_Mild")["Severity_Mild"].count()
if (type(mild_data) == None):
    num_mild = 0
elif (mild_data.get(1) == None):
    num_mild = 0
else:
    num_mild = mild_data[1]

moderate_data = compacted_data.groupby("Severity_Moderate")["Severity_Moderate"].count()
if (type(moderate_data) == None):
    num_moderate = 0
elif (moderate_data.get(1) == None):
    num_moderate = 0
else:
    num_moderate = moderate_data[1]

severe_data = compacted_data.groupby("Severity_Severe")["Severity_Severe"].count()
if (type(severe_data) == None):
    num_severe = 0
elif (severe_data.get(1) == None):
    num_severe = 0
else:
    num_severe = severe_data[1]

total = num_none + num_mild + num_moderate + num_severe
print(total)
print(num_none)
print(num_mild)
print(num_moderate)
print(num_severe)

most_significant = len(str(num_none)) - 2
if (most_significant < 3):
    most_significant += 1
divisor = 10**most_significant

df = pd.DataFrame({'percent': [num_none % divisor, num_mild % divisor, num_moderate % divisor, num_severe % divisor]}, index=['None', 'Mild', 'Moderate', 'Severe'])
plot = df.plot.pie(y='percent', autopct='%1.0f%%')
