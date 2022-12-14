package de.alewol.tools.analyzer.calculate.average;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import de.alewol.tools.analyzer.pojo.AnalyzedClass;
import de.alewol.tools.analyzer.pojo.AnalyzedMethod;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CalculateAverageValues {

	public long calculateAverageClassLoc() {
		long averageClassLoc = 0;
		long sumClassLength = 0;
		
		sumClassLength = JavaMetricAnalyzer.analyzedClasses.stream().mapToInt(AnalyzedClass::getLinesOfCode).sum();
		averageClassLoc = sumClassLength / JavaMetricAnalyzer.analyzedClasses.size();
		
		log.debug("sumClassLength: " + sumClassLength);
		log.debug("JavaMetricAnalyzer.locClassesMap.size(): " + JavaMetricAnalyzer.analyzedClasses.size());
		
		return averageClassLoc;
	}
	
	public long calculateAverageMethodLoc() {
		long averageMethodLoc = 0;
		long sumMethodLoc = 0;
		Integer sumAllMethods = 0;
		
		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses) {
			sumMethodLoc += analyzedClass.getAnalyzedMethodList().stream().mapToLong(AnalyzedMethod::getLinesOfCode).sum();
			sumAllMethods += analyzedClass.getAnalyzedMethodList().size();
		}
		averageMethodLoc = sumMethodLoc / sumAllMethods;

		log.debug("sumAllMethods: " + sumAllMethods);		
		log.debug("sumMethodLength: " + sumMethodLoc);
		
		return averageMethodLoc;
	}
	
	public Double calculateAverageMethodCounterOfClasses() {
		Double averageMethodCounterOfClasses;
		Integer sumAllMethods = 0;
		
		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses) {
			sumAllMethods += analyzedClass.getAnalyzedMethodList().size();
		}
		averageMethodCounterOfClasses = (double) sumAllMethods/JavaMetricAnalyzer.analyzedClasses.size();
				
		log.debug("sumAllMethods: " + sumAllMethods);
		log.debug("JavaMetricAnalyzer.analyzedClasses.size() " + JavaMetricAnalyzer.analyzedClasses.size());
		
		return averageMethodCounterOfClasses;
	}
	
	public Double calculateAverageCyclomaticComplexity() {
		Double averageCyclomaticComplexityOfMethods;
		Integer sumAllCyclomaticComplexityValues = 0;
		Integer sumAllMethods = 0;

		
		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses) {
			sumAllMethods += analyzedClass.getAnalyzedMethodList().size();
			sumAllCyclomaticComplexityValues += analyzedClass.getAnalyzedMethodList().stream().mapToInt(AnalyzedMethod::getCyclomaticComplexity).sum();

		}
		
		averageCyclomaticComplexityOfMethods = (double) sumAllCyclomaticComplexityValues/sumAllMethods;
		
		log.debug("sumAllCyclomaticComplexityValues: " + sumAllCyclomaticComplexityValues);
		log.debug("sumAllMethods: " + sumAllMethods);
		
		return averageCyclomaticComplexityOfMethods;
	}
}
