What did you not test automatically and why?

1) I did not test if the points are shown correctly in the chart. I don't know how to do it.
2) I did not test the methods that write files. I tried in many ways, but it seems that the tests don't save the files when we are testing, so I cannot really compare anything. 

How have these things been tested manually?

1) I opened the text document and compared with the chart for more than 20 tests, and all of them showed the precise result.

2) The program has to read the files that were written so that he can plot correctly. Since we are reading the expected values, I supposed that the writing process is occurring correctly. I used JUnit tests in the reading methods.

Bugs:

If a person changes some values in the files folder they can compromise the whole program. I didn't have time to do it, but a good idea in those cases would be to create a "Reset Button" with which the user could erase all the damaged data and continue using the program as it was new.

Sometimes NYTimes changes some articles in their database, which makes some tests to fail. The solution is to update the program numbers as soon as the problem is seen.

If you type for example the word "@ @", you entry will be validated as " ", and it will be considered a word searched. Nevertheless, it won't be plotted, since the chart has another check system. This is not so hard to correct, but to do so I would have to send the project after the deadline.