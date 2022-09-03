package de.alewol.tools.analyzer;

public class App 
{
    public static void main( String[] args )
    {
    	JavaMetricAnalyzer analyzer =  JavaMetricAnalyzer.getInstance();
    	analyzer.runApp(args);
    }
}
