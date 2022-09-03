package de.alewol.tools.analyzer.calculate;

import java.util.stream.Collectors;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CalculateAverageValues {

	public long calculateAverageClassLength() {
		long averageClassLength = 0;
		long sumClassLength = 0;
		
		sumClassLength = JavaMetricAnalyzer.locClassesList.stream().mapToLong(Long::longValue).sum();
		averageClassLength = sumClassLength / JavaMetricAnalyzer.locClassesList.size();
		
		log.debug("sumClassLength: " + sumClassLength);
		log.debug("JavaMetricAnalyzer.locClassesList.size(): " + JavaMetricAnalyzer.locClassesList.size());
		
		return averageClassLength;
	}
	
	public long calculateAverageMethodLength() {
		long averageMethodLength = 0;
		long sumMethodLength = 0;
		
		sumMethodLength = JavaMetricAnalyzer.locMethodList.stream().mapToLong(Long::longValue).sum();
		averageMethodLength = sumMethodLength / JavaMetricAnalyzer.locMethodList.size();
		
		log.debug("sumMethodLength: " + sumMethodLength);
		log.debug("JavaMetricAnalyzer.locMethodList.size(): " + JavaMetricAnalyzer.locMethodList.size());
		
		return averageMethodLength;
	}
	
	public Integer calculateAverageMethodCounterOfClasses() {
		Integer averageMethodCounterOfClasses = 0;
		Integer sumAllMehtodsInClasses = 0;
		
		averageMethodCounterOfClasses = JavaMetricAnalyzer.methodCounterList.stream().collect(Collectors.summingInt(Integer::intValue));
		sumAllMehtodsInClasses = sumAllMehtodsInClasses / JavaMetricAnalyzer.methodCounterList.size();
		
		log.debug("sumAllMehtodsInClasses: " + sumAllMehtodsInClasses);
		log.debug("JavaMetricAnalyzer.methodCounterList.size(): " + JavaMetricAnalyzer.methodCounterList.size());
		
		return averageMethodCounterOfClasses;
	}
	
	public Double calculateAverageCyclomaticComplexity() {
		Double averageCyclomaticComplexityOfMethods = (double) 0;
		Integer sumAllCyclomaticComplexityValues = 0;
		
		sumAllCyclomaticComplexityValues = JavaMetricAnalyzer.cyclomaticComplexityList.stream().collect(Collectors.summingInt(Integer::intValue));
		averageCyclomaticComplexityOfMethods = (double) sumAllCyclomaticComplexityValues/JavaMetricAnalyzer.cyclomaticComplexityList.size();
		
		log.debug("sumAllCyclomaticComplexityValues: " + sumAllCyclomaticComplexityValues);
		log.debug("ricAnalyzer.cyclomaticComplexityList.size(): " + JavaMetricAnalyzer.cyclomaticComplexityList.size());
		
		return averageCyclomaticComplexityOfMethods;
	}
}
