import socket
import numpy as np
from Datapreprocess import Preprocess
from Data_Loading_Writing import load_data, write_data
from Classification import KNN_Classification

def connection():
    # local host IP '127.0.0.1'
    host = '127.0.0.1'

    # Define the port on which you want to connect
    port = 6789

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # connect to server on local computer
    s.connect((host, port))
    # message you send to server


    Preprocess()

    user_array = np.empty([256, 13], dtype=int)
    my_data = load_data()

    s.sendall(b'Connection_Established\n')
    while 1:
        # message sent to server

        # messaga received from server
        data = s.recv(1024)

        # print the received message
        #print('Received from the server :', str(data.decode('ascii')))
        response = str(data.decode('ascii'))
        userID = 0
        i = 0
        for x in range(7,-1,-1):
            userID += pow(2,x)*int(response[i+1])
            i += 1

        if response[0] == '0':
            for x in range(12):
                user_array[userID][x] = int(response[9+x])
            result = str(KNN_Classification(my_data, user_array[userID][0:12]))
            to_send = response[1:9] + result +'\n';
            s.send(to_send.encode())



        elif response[0] == '1':
            user_array[userID][12] = int(response[21])
            write_data(user_array, userID)



    # close the connection
    s.close()

if __name__ == "__main__":
    connection()
