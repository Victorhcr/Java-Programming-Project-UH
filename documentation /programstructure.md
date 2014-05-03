When initialized, the program starts with the main class App. That class creates a GUI, which is structured by the View class. This class creates the listener for its button and ComboBox. If the user tries to click in the "Create Chart" button without giving a valid input and choosing a final year for the parsing, the program will not work and will wait until the valid entries are given.

After clicking on "Create Chart", the View class requests the YearHits class, which gets and organizes the data coming from New York Times API. To avoid loss of data and uncesserary further connections with New York Times database, YearHits class requests that FileHandle class saves the data on the user's computer. After having the real statistics from NYTimes in the computer, FileHandle requests the Class LinReg to create the prediction of the next year numbers based on the request by the user. The class FileHandle also saves this number in the computer.

After that, the Chart Create Action Listener Class requests the class Chart to plot the data saved in the computer. 

The user can plot other inputs and compare results.