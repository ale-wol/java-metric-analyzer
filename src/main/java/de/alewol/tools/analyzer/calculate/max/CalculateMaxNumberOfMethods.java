package de.alewol.tools.analyzer.calculate.max;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import de.alewol.tools.analyzer.pojo.AnalyzedClass;

public class CalculateMaxNumberOfMethods {
	
	public AnalyzedClass calculateClassMaxNumberOfMethods() {
		AnalyzedClass classWithMaxNumberOfMethods = null;
				
		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses)
		{
			if(classWithMaxNumberOfMethods == null || analyzedClass.getAnalyzedMethodList().size() > classWithMaxNumberOfMethods.getAnalyzedMethodList().size())
			{
				classWithMaxNumberOfMethods = analyzedClass;
			}
		}
		return classWithMaxNumberOfMethods;
	}


}
