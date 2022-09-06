package de.alewol.tools.analyzer;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;

import de.alewol.tools.analyzer.calculate.CalculateAverageValues;
import de.alewol.tools.analyzer.conf.InitConf;
import de.alewol.tools.analyzer.find.FindSources;
import de.alewol.tools.analyzer.parse.ClassVisitor;
import de.alewol.tools.analyzer.parse.MethodVisitor;
import de.alewol.tools.analyzer.parse.SourceParser;
import de.alewol.tools.analyzer.verify.VerifyLoc;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JavaMetricAnalyzer {

	public static final String LINE_SEPARATOR = "------------------------------------------------------------------------";
	public static Integer methodCounter = 0;

	private static JavaMetricAnalyzer singletonInstance;

	@Getter
	private static ArrayList<Path> sourceFiles = new ArrayList<>();

	public static LinkedHashMap<String, Long> locClassesMap = new LinkedHashMap<>();
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
			InitConf.initReadConfig();
			String directoryToScan = args[0];
			
			FindSources finder = new FindSources();
			finder.findJavaSourceFiles(directoryToScan);
			
			for(Path fileToParse : sourceFiles)
			{
				parseSourceFile(fileToParse);
			}
			calculateAverageValues();
			verifyMetrics();
		} else {
			//TODO Wrong Number of Arguments
		}
	}
	
	private void parseSourceFile(Path fileToParse) {
		SourceParser parser = new SourceParser();
		MethodVisitor methodVisitor = new MethodVisitor();
		ClassVisitor classVisitor = new ClassVisitor();
		
		log.info("Parse File: " + fileToParse.toString());
		ParseResult<CompilationUnit> result = parser.parse(fileToParse);
		
		if(result.getResult().isPresent())
		{
			CompilationUnit compilationUnit = result.getResult().get();
			classVisitor.visit(compilationUnit, null);
			methodVisitor.visit(compilationUnit, null);
		}
		methodCounterList.add(methodCounter);
		log.info(methodCounter + " Methods in File " + fileToParse.toString());
		log.info(LINE_SEPARATOR);
		methodCounter = 0;
	}
	
	private void calculateAverageValues()
	{
		CalculateAverageValues calculateAverage = new CalculateAverageValues();
		log.info("#### AVERAGE VALUES ####");
		log.info("Average Class Length: " + calculateAverage.calculateAverageClassLength());
		log.info("Average Method Number per Class: " + calculateAverage.calculateAverageMethodCounterOfClasses());
		log.info("Average Method Length: " + calculateAverage.calculateAverageMethodLength());
		log.info("Average Cyclomatic Complexity of all Methods: " + calculateAverage.calculateAverageCyclomaticComplexity());
	}
	
	private void verifyMetrics()
	{
		VerifyLoc verifyLoc = new VerifyLoc();
		verifyLoc.verifyLocClasses();
	}
}
