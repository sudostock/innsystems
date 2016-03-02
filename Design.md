# Design/Structure #

Shows the design of the current program and the breakdown of the classes.


## innsystems ##

### backpropagation package ###

  * backpropagation.java - This is the main neural networking class, it is generalized so it can handle any data given to it in the correct format

  * Format2.java - This class is going to be rewritten for each type of data that will be tested, while it sounds like a pain in the end it will allow for a generalization of the whole project and then the individual can create there own "Format2.java" class to specify how to handle they data they want to test.

### pso ###

  * Particles.java (Data) - Responsible for handling the data and positions of the particles during the run of the PSO. Keeps a list of all particles, there present variable values and the results of the current run. Also keeps track of the global best, etc

  * Methods.java (PSO) - Takes particle objects and uses the built in methods to manipulate the data, and perform the PSO calculations.

## gui ##

  * Controller.Form - Main program for the host/controller program

  * Client.Form - Client program that is to be distributed to all computers

### networking ###

  * broadcastS.java - Thread that will listen for incoming client requests and send back the ip address of the server and store the clients address for future use

  * clientComm.java - Thread that will handle all client side communications



