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

That's the default file path for input. The user CANNOT specify their own input file path.



OUTPUT
------
No output is written to any files.

All output is written to the screen, in the following format:

Timestamp   OrderAction   Price

Timestamp - The time in milliseconds since midnight.
Order Action - Whether this order will sell this stock (S), or buy it (B).
Price - The profit from selling this stock (S), or the expense of buying this stock (B). Expressed as a decimal.

EXAMPLES:
28800758 S 8832.56
28800796 S NA
28800812 S 8832.56
28800974 B 8865.00
28800975 B NA
28812071 S NA
28813830 B 8845.00

REQUIREMENTS
------------
Java 1.8.0_181 or higher 
(https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)


OPTIONAL
--------
JUnit 4.12 or higher
This is specifically for running unit tests. JUnit isn't required, but it's suggested for any developer. 


GETTING STARTED
---------------
There are two steps to make Pricer work:
 - Compile the application
 - Execute the application

TO START
--------
 - Open a command prompt in Linux or Windows.
 - Change directories to the location of Pricer.
 - Compile the application.
 - Execute the application.

COMPILING the application:
javac src/main/com/pricer/businesslogic/PricerMain.java


EXECUTING the application:

WITH the TargetSize argument:
-----------------------------
java src/main/com/pricer/businesslogic/PricerMain [*targetSize*]

Default values will be used if no parameter is provided. They are:
targetSize: 200
