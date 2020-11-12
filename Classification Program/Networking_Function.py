import socket
import numpy as np
from Datapreprocess import Preprocess
from Classification_Client import load_data

def connection(user_array):
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


    while True:
        # message sent to server
        s.sendall(b'Hello, world\n')

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

        elif response[0] == '1':
            user_array[userID][12] = int(response[21])




    # close the connection
    s.close()


