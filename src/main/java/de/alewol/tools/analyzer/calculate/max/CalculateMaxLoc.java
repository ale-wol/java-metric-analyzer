package de.alewol.tools.analyzer.calculate.max;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import de.alewol.tools.analyzer.pojo.AnalyzedClass;
import de.alewol.tools.analyzer.pojo.AnalyzedMethod;

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

	
	public AnalyzedMethod calculateMaxMethodsLoc() {
		AnalyzedMethod methodWithMaxLoc = null;
		
		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses)
		{
			for (AnalyzedMethod analyzedMethod : analyzedClass.getAnalyzedMethodList())
			{
				if(methodWithMaxLoc == null || analyzedMethod.getLinesOfCode() > methodWithMaxLoc.getLinesOfCode())
				{
					methodWithMaxLoc = analyzedMethod;
				}
			}
		}
		return methodWithMaxLoc;
		
	}
}
