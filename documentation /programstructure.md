When initialized, the program starts with the main class App. That class creates a GUI, which is structured by the View class. This class creates the listener for its button and ComboBox. If the user tries to click in the "Create Chart" button without giving a valid input and choosing a final year for the parsing, the program will not work and will wait until the valid entries are given. This GUI also requests from the class AllWordsSearched the 3 most searched words in the program.

After clicking on "Create Chart", the View class uses the classes MainFileWordsPerLog, AllWordsSearched and WordsPerLog to organize what was searched in the computer. The View class then requests the YearHits class, which gets and organizes the data coming from New York Times API. To avoid loss of data and uncesserary further connections with New York Times database, YearHits class requests that SaveAPIData class saves the data on the user's computer. After having the real statistics from NYTimes in the computer, SaveAPIData requests the Class Predictor to create the prediction of the next year numbers based on the request by the user. The class SaveAPIData also saves this number in the computer.

After that, the Chart Create Action Listener Class requests the class Chart to plot the data saved in the computer. 

Chart then request the Article and RelatedWords classes for an article example that was part of the chart and for related words that the user may be interested in searching after seeing the plot. The user can read a summary of the article and if he is interested he can read it fully in The New York Times website.

The RelatedWords class requests the documents saved in the computer by the WordsPerLog class for words written in each time the user oppened the program. An algorithm then defines the best words to show to the user.

The user can plot other inputs and compare results.