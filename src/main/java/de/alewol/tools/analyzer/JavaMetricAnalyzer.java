package de.alewol.tools.analyzer;

import java.nio.file.Path;
import java.util.ArrayList;

import de.alewol.tools.analyzer.find.FindSources;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JavaMetricAnalyzer {

	public static final String LINE_SEPARATOR = "------------------------------------------------------------------------";
	public static Integer methodCounter = 0;

	private static JavaMetricAnalyzer singletonInstance;

	@Getter
	private static ArrayList<Path> sourceFiles = new ArrayList<>();

	public static ArrayList<Long> locClassesList = new ArrayList<>();
	public static ArrayList<Long> locMethodList = new ArrayList<>();
	public static ArrayList<Integer> methodCounterList = new ArrayList<>();
	public static ArrayList<Integer> cyclomaticComplexityList = new ArrayList<>();

	private JavaMetricAnalyzer() {

	}

	public static synchronized JavaMetricAnalyzer getInstance() {
		if (singletonInstance == null) {
			singletonInstance = new JavaMetricAnalyzer();
		}
		return singletonInstance;
	}

	public void runApp(String[] args) {
		if (args.length == 1) {
			String directoryToScan = args[0];
			
			FindSources finder = new FindSources();
			finder.findJavaSourceFiles(directoryToScan);
			
			for(Path fileToParse : sourceFiles)
			{
				parseSourceFile(fileToParse);
			}
		} else {
			//TODO Wrong Number of Arguments
		}

	}
	
	private void parseSourceFile(Path fileToParse) {
		
	}

}
