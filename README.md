Instructions for running innsystems project:

Run the Controller Program
Enter number of particles you wish to run on the PSO under num particles
Enter number of neighbors each fo the particles will have under num neighbors
Enter epoch limit for the PSO (how many interations you want it to make)
Click the Set button

ON client computer run Client
In the same directory as the client jar file create a new text file ex "serverlist.txt"
Open up the file and on each line enter an IP address of the possible Controller computers, or if you know the number ahead of time just enter the IP of the computer hosting the controller on the first line.
In the client go to the "Server List" text box and enter the filename ex "serverlist.txt"

On client computer run Client
In the same directory as the client jar file create a new text file ex "serverlist.txt"
Open up the file and on each line enter an IP address of the possible Controller computers, or if you know the number ahead of time just enter the IP of the computer hosting the controller on the first line.
In the client go to the "Server List" text box and enter the filename ex "serverlist.txt"
Below enter the name of the database file, the included one is called "AppleTestData.db"
Click the "Connect" button

On the Controller in the "Output Filename" box enter a name for the file to be written with the results of running the pso. Probably best to follow the format "numParticles_numNeigh_epochs.txt" as this information is not written into the file, just the gbest of each epoch and the coordinates that go with it.

Click "Run PSO" button, this will start the program and sending data to the connected computers.

There is no indication yet on when the program finishs running you just have to see if the output data file is of a size other then 0kb. If the cpu usage of the controller program is zero, along with the cpu usage of the client(s) then the program is done.
