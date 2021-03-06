import pandas as pd
import numpy as np
import mpld3
from mpld3 import plugins
import socket
from random import *
import matplotlib.pyplot as plt
import base64
from io import BytesIO
import urllib
import webbrowser
import os

host = '127.0.0.1'
port = 4567
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((host, port))

full_data = pd.read_csv("sampled_data.csv")
# full_data = pd.read_csv("Documents/2020-21/CSCI-201/Final_project/Cleaned-Data.csv")

while 1:
    data = s.recv(1024)
    response = str(data.decode('ascii'))
    fever = int(response[0])
    tiredness = int(response[1])
    dry_cough = int(response[2])
    difficulty_breathing = int(response[3])
    pains = int(response[4])
    runny_nose = int(response[5])
    gender = 0
    # HERE
    test_result = int(response[6])

    if (gender == 1):
        compacted_data = full_data[(full_data["Fever"] == fever) & (full_data["Tiredness"] == tiredness) & (full_data["Dry-Cough"] == dry_cough) & (full_data["Difficulty-in-Breathing"] == difficulty_breathing) & (full_data["Pains"] == pains) & (full_data["Runny-Nose"].astype(np.int) == runny_nose) & (full_data["Gender_Male"] == 1)]
    else:
        compacted_data = full_data[(full_data["Fever"] == fever) & (full_data["Tiredness"] == tiredness) & (full_data["Dry-Cough"] == dry_cough) & (full_data["Difficulty-in-Breathing"] == difficulty_breathing) & (full_data["Pains"] == pains) & (full_data["Runny-Nose"].astype(np.int) == runny_nose) & (full_data["Gender_Female"] == 1)]

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

    if ((num_none == 0) | (num_mild == 0) | (num_moderate == 0) | (num_severe == 0)):
        if (gender == 1):
            compacted_data = full_data[(full_data["Fever"] == fever) & (full_data["Tiredness"] == tiredness) & (full_data["Gender_Male"] == 1)]
        else:
            compacted_data = full_data[(full_data["Fever"] == fever) & (full_data["Tiredness"] == tiredness) & (full_data["Gender_Female"] == 1)]

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


    most_significant = len(str(num_none)) - 2
    if (most_significant < 3):
        most_significant += 1
    divisor = 10**most_significant

    fig = plt.figure()
    ax = fig.add_axes([0, 0, 1, 1])
    ax.axis('equal')
    severity = ['None', 'Mild', 'Moderate', 'Severe']
    amounts = [num_none % divisor, num_mild % divisor, num_moderate % divisor, num_severe % divisor]
    ax.pie(amounts, labels = severity, autopct='%1.1f%%')
    if(test_result):
        plt.title("Based on our prediction, you should get a real test !!!")
    else:
        plt.title("Based on our prediction, you do not need to get a real test")

    tmpfile = BytesIO()
    fig.savefig(tmpfile, format='png')
    encoded = base64.b64encode(tmpfile.getvalue()).decode('utf-8')
    html = "<!DOCTYPE html>\n<html>\n<head>\n<style>\nimg {\n  width: 50%;\n}\n</style>\n</head>\n<body>\n"
    if(test_result):
        html += "<h1>Based on our prediction, you should get a real test !!!</h1>\n"
    else:
        html += "<h1>Based on our prediction, you do not need to get a real test</h1>\n"
    html += "<h2>COVID-19 Severity with Similar Symptoms</h2>\n<img src=\'data:image/png;base64,{}\'>".format(encoded) + "\n</body>\n</html>"

    with open("test.html", 'w') as f:
        f.write(html)

    webbrowser.open_new_tab('test.html')

s.close()
