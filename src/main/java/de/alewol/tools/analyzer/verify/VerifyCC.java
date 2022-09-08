package de.alewol.tools.analyzer.verify;

import java.util.LinkedHashMap;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import de.alewol.tools.analyzer.conf.InitConf;
import de.alewol.tools.analyzer.pojo.AnalyzedClass;
import de.alewol.tools.analyzer.pojo.AnalyzedMethod;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class VerifyCC {
	
	public void verifyCyclomaticCompexityMethods() {
		log.info("Check Cyclomatic Complexity of Code Methods:");
		log.info("Max Cyclomatic Complexity : " + InitConf.getMaxCyclomaticComplexityMethods().toString());
		LinkedHashMap<String, String> methodsBreakingCcMax = new LinkedHashMap<>();

		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses)
		{
			for(AnalyzedMethod analyzedmethod : analyzedClass.getAnalyzedMethodList())
			{
				if(analyzedmethod.getCyclomaticComplexity() >= InitConf.getMaxCyclomaticComplexityMethods())
				{
					methodsBreakingCcMax.put(analyzedClass.getClassName(), analyzedmethod.getMethodName());
				}
			}
		}
		
		if(!methodsBreakingCcMax.isEmpty())
		{
			log.info(methodsBreakingCcMax.size() + " Methods violating the configured max Cyclomatic Compexity:");
			
			for(var entry : methodsBreakingCcMax.entrySet())
			{
				log.info(entry.getValue() + " Method in class " + entry.getKey());
			}
		}
		else
		{
			log.info("No Method is violating the configured max Cyclomatic Compexity :)");
		}
	}
}
