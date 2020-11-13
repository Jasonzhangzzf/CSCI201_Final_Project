import numpy as np
from sklearn.neighbors import KNeighborsClassifier
from Data_Loading_Writing import load_data

def KNN_Classification(my_data, user_symptom):
    model = KNeighborsClassifier(n_neighbors=10)

    # separate the data into symptom and label lists
    symptoms = []
    labels = []
    for i in range(len(my_data)):
        temp_symptom = []
        for j in range(12):
            temp_symptom.append(my_data[i][j])
        symptoms.append(temp_symptom)
        labels.append(my_data[i][12])

    # Train the model using the training sets
    model.fit(symptoms, labels)

    #Predict Output
    predicted= model.predict([user_symptom]) # 0:negative, 1:positive
    print(predicted)

my_data = load_data()
KNN_Classification(my_data, [0,1,0,0,0,1,1,1,1,1,0,0])