package de.alewol.tools.analyzer;

import static de.alewol.tools.analyzer.JavaMetricAnalyzer.LINE_SEPARATOR;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

import de.alewol.tools.analyzer.calculate.average.CalculateAverageValues;
import de.alewol.tools.analyzer.conf.InitConf;
import de.alewol.tools.analyzer.find.FindSources;
import de.alewol.tools.analyzer.parse.ClassVisitor;
import de.alewol.tools.analyzer.parse.MethodVisitor;
import de.alewol.tools.analyzer.parse.SourceParser;
import de.alewol.tools.analyzer.pojo.AnalyzedClass;
import de.alewol.tools.analyzer.pojo.AnalyzedMethod;
import de.alewol.tools.analyzer.verify.VerifyCC;
import de.alewol.tools.analyzer.verify.VerifyLoc;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JavaMetricAnalyzer {

	public static final String LINE_SEPARATOR = "------------------------------------------------------------------------";
	
	private static JavaMetricAnalyzer singletonInstance;

	@Getter
	private static ArrayList<Path> sourceFiles = new ArrayList<>();
	
	public static final List<AnalyzedClass> analyzedClasses = new ArrayList<>();

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
			//Wrong Number of Arguments
		}
	}
	
	private void parseSourceFile(Path fileToParse) {
		SourceParser parser = new SourceParser();
		VoidVisitor<List<AnalyzedMethod>> methodVisitor = new MethodVisitor();
		VoidVisitor<List<AnalyzedClass>> classVisitor = new ClassVisitor();
		
		log.info("Parse File: " + fileToParse.toString());
		ParseResult<CompilationUnit> parseResult = parser.parse(fileToParse);
		ArrayList <AnalyzedMethod> collectedMethods = new ArrayList<>();
		
		if(parseResult.getResult().isPresent())
		{
			CompilationUnit compilationUnit = parseResult.getResult().get();
			classVisitor.visit(compilationUnit, analyzedClasses);
			methodVisitor.visit(compilationUnit, collectedMethods);
			
			analyzedClasses.get(analyzedClasses.size() - 1).setAnalyzedMethodList(collectedMethods);
		}
		log.info(collectedMethods.size() + " Methods in File " + fileToParse.toString());
		log.info(LINE_SEPARATOR);
	}
	
	private void calculateAverageValues()
	{
		CalculateAverageValues calculateAverage = new CalculateAverageValues();
		log.info("#### AVERAGE VALUES ####");
		log.info("Average Class Lines of Code: " + calculateAverage.calculateAverageClassLoc());
		log.info("Average Method Number per Class: " + calculateAverage.calculateAverageMethodCounterOfClasses());
		log.info("Average Method Lines of Code: " + calculateAverage.calculateAverageMethodLoc());
		log.info("Average Cyclomatic Complexity of all Methods: " + calculateAverage.calculateAverageCyclomaticComplexity());
		log.info("analyzedClasses: " + analyzedClasses.size());
	}
	
	private void verifyMetrics()
	{
		log.info(LINE_SEPARATOR);
		log.info("#### CHECK CONFIGURED METRICS ####");
		VerifyLoc verifyLoc = new VerifyLoc();
		verifyLoc.verifyLocClasses();
		log.info(LINE_SEPARATOR);
		verifyLoc.verifyLocMethods();
		log.info(LINE_SEPARATOR);
		VerifyCC verifyCc = new VerifyCC();
		verifyCc.verifyCyclomaticCompexityMethods();
		log.info(LINE_SEPARATOR);
	}
}
