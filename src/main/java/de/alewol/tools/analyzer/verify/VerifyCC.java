package de.alewol.tools.analyzer.verify;

import java.util.ArrayList;

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
		ArrayList<AnalyzedMethod> methodsBreakingCcMax = new ArrayList<>();

		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses)
		{
			for(AnalyzedMethod analyzedmethod : analyzedClass.getAnalyzedMethodList())
			{
				if(analyzedmethod.getCyclomaticComplexity() >= InitConf.getMaxCyclomaticComplexityMethods())
				{
					methodsBreakingCcMax.add(analyzedmethod);
				}
			}
		}
		
		if(!methodsBreakingCcMax.isEmpty())
		{
			log.info(methodsBreakingCcMax.size() + " Methods violating the configured max Cyclomatic Compexity:");
			
			for(AnalyzedMethod analyzedMethod : methodsBreakingCcMax)
			{
				log.info(analyzedMethod.getMethodName() + " Method in Class " + analyzedMethod.getAffiliatedClass().getClassName());
			}
		}
		else
		{
			log.info("No Method is violating the configured max Cyclomatic Compexity :)");
		}
	}
}
