# Description 
Small Java Console Application to scan an Java Project an calculate some usefull metrics.

# Metrics that the Tool calculates:
* Average Loc for Classes
* Average Loc for Methods
* Average Cyclomatic Complexity for Methods
* Average Method Number per Class

# Checks that the Tool is doing:
* Checks if any Class or Method is violating the configured max Loc.

# Prerequisite
Java Runtime

# Config
config/config.ini File is needed to run the Application, Example Config:
<br>
[LinesOfCode]
<br>
maxLocClasses = 100
<br>
maxLocMethods = 20

# Example Usage
java -jar java-metric-analyzer-{version}-shade.jar C:\JavaProjectToScan\src\main
