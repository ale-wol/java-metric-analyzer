package de.alewol.tools.analyzer.calculate.max;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import de.alewol.tools.analyzer.pojo.AnalyzedClass;
import de.alewol.tools.analyzer.pojo.AnalyzedMethod;

public class CalculateMaxCc {

	public AnalyzedMethod calculateMaxCyclomaticComplexity() {
		AnalyzedMethod methodWithMaxCc = null;
		
		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses)
		{
			for (AnalyzedMethod analyzedMethod : analyzedClass.getAnalyzedMethodList())
			{
				if(methodWithMaxCc == null || analyzedMethod.getCyclomaticComplexity() > methodWithMaxCc.getCyclomaticComplexity())
				{
					methodWithMaxCc = analyzedMethod;
				}
			}
		}
		return methodWithMaxCc;
		
	}
}
