import pandas as pd
import numpy as np
from numpy import random
# changes?
def generate_label0_data(arr_size, p_fever, p_tiredness, p_dry_cough, p_breathing, p_sore_throat, p_pains, p_nasal_congestion,
                         p_runny_nose, p_diarrhea, p_contact_yes, p_contact_no_given_not_yes, p_gender):
    generated_training_inputs, generated_training_labels = ([], [])
    # create arrays with size to be arr_size
    for i in range(arr_size):
        new_input = []
        if(random.rand() <= p_fever):
            new_input.append(1)
        else: new_input.append(0)

        if (random.rand() <= p_tiredness):
            new_input.append(1)
        else:
            new_input.append(0)

        if (random.rand() <= p_dry_cough):
            new_input.append(1)
        else:
            new_input.append(0)

        if (random.rand() <= p_breathing):
            new_input.append(1)
        else:
            new_input.append(0)

        if (random.rand() <= p_sore_throat):
            new_input.append(1)
        else:
            new_input.append(0)

        if (random.rand() <= p_pains):
            new_input.append(1)
        else:
            new_input.append(0)

        if (random.rand() <= p_nasal_congestion):
            new_input.append(1)
        else:
            new_input.append(0)

        if (random.rand() <= p_runny_nose):
            new_input.append(1)
        else:
            new_input.append(0)

        if (random.rand() <= p_diarrhea):
            new_input.append(1)
        else:
            new_input.append(0)

        #if contact is yes, push the remaining 2 symptoms as 0s
        if (random.rand() <= p_contact_yes):
            new_input.append(0)
            new_input.append(0)
        # if contact is no, push the remaining 1 symptom as 0
        elif(random.rand() <= p_contact_no_given_not_yes):
            new_input.append(0)
            new_input.append(1)

        else:
            new_input.append(1)
            new_input.append(0)

        if (random.rand() <= p_gender):
            new_input.append(1)
        else:
            new_input.append(0)
        # add to training and label array
        generated_training_inputs.append(new_input)
        generated_training_labels.append(0)
    #print(len(generated_training_inputs))
    #print(generated_training_inputs,generated_training_labels)
    return generated_training_inputs,generated_training_labels

def rand_choose(col,df):
    to_return = []
    for x in range(4):
        df[col[x]] = np.where(df[col[x]] == 1, 1, 0.0)
        s = df.sample(n=125, weights=col[x])
        to_return.append(s)
    to_return = pd.concat(to_return)

    return to_return

df = pd.read_csv('Cleaned-Data.csv')
col = ['Severity_None', 'Severity_Mild', 'Severity_Moderate', 'Severity_Severe']


cleaned_data = rand_choose(col,df)
cleaned_data = cleaned_data[['Fever', 'Tiredness', 'Dry-Cough', 'Difficulty-in-Breathing', 'Sore-Throat', 'Pains', 'Nasal-Congestion', 'Runny-Nose', 'Diarrhea', 'Contact_Dont-Know', 'Contact_No', 'Contact_Yes' ]]
labels = np.ones(500)
cleaned_data['Label'] = labels
label0_inputs, label0_labels = generate_label0_data(500, 0.3, 0.5, 0.3, 0.3, 0.3, 0.5, 0.6, 0.6, 0.5, 0.3, 0.7, 0.5)
# append label 0 array
for i in range(len(label0_inputs)):
    temp = label0_inputs[i]
    temp.append(0)

    cleaned_data.loc[len(cleaned_data)] = temp

print("changes??")
cleaned_data.to_csv("processed_input.csv", index=False)
