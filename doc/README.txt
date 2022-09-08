***********************************************
* Java Metric Analyzer
* Developer: Alexander Wolf
***********************************************

Description:
============
Small Java Console Application to scan an Java Project an calculate some useful metrics.

Metrics that the Tool calculates:
=================================
Average Loc for Classes
Average Loc for Methods
Average Cyclomatic Complexity for Methods
Average Method Number per Class

Checks that the Tool is doing:
==============================
Checks if any Class or Method is violating the configured max Loc.

System Prerequisite:
====================
Java Runtime

Config:
======
config/config.ini File is needed to run the Application, Example Config:
[LinesOfCode]
maxLocClasses = 100
maxLocMethods = 20

Example Usage:
==============
java -jar java-metric-analyzer-{version}-shade.jar C:\JavaProjectToScan\src\main


Distribution:
============
logs/                                       Destination directory for log files
doc/                                        Documentation
README.txt                                  This File :)
config/                                     Configuration Files
    config.ini
java-metric-analyzer-{version}-shade.jar    The Application
