# BowlingScore
--------------

SUMMARY
-------
This is a small java application that calculates a bowling score based on a file that's read for input. When data is read from the file, a score is calculated based on each frame. 
That score is written to a text file.

BowlingScore has no arguments. 

It doesn't have a GUI, either. You run it from a command prompt.

INPUT
-----
Data is read in from:
     ../BowlingScore/InputFiles/BowlingScoreData.txt

The user CANNOT specify their own input file path.


OUTPUT
------
Output is written to a file: 
     ../BowlingScore/OutputFiles/BowlingScoreOutput.txt


REQUIREMENTS
------------
Java 1.8.0_181 or higher 
(https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)


OPTIONAL
--------
JUnit 4.12 or higher
Specifically for running unit tests. JUnit isn't required, but it's suggested. 


GETTING STARTED
---------------
There are two steps to make BowlingScorer work:
 - Compile the application
 - Execute the application


TO START
--------
 - Open a command prompt in Linux or Windows.
 - Change directories to the location of BowlingScore.
 - Compile the application.
 - Execute the application.


<h4>COMPILING the application:</h4>
javac src/main/com/bowlingscore/businesslogic/BowlingScoreMain.java


<h4>EXECUTING the application:</h4>
java src/main/com/bowlingscore/businesslogic/BowlingScoreMain

