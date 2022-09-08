package de.alewol.tools.analyzer.calculate.max;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import de.alewol.tools.analyzer.pojo.AnalyzedClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CalculateMaxLoc {
	
	public AnalyzedClass calculateMaxClassLoc() {
		AnalyzedClass classWithMaxLoc = null;
				
		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses)
		{
			if(classWithMaxLoc == null || analyzedClass.getLinesOfCode() > classWithMaxLoc.getLinesOfCode())
			{
				classWithMaxLoc = analyzedClass;
			}
		}
		return classWithMaxLoc;
	}

	
	public long calculateMaxMethodsLoc() {
		return 0;
		
	}
}
