package de.alewol.tools.analyzer.verify;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import de.alewol.tools.analyzer.conf.InitConf;
import de.alewol.tools.analyzer.pojo.AnalyzedClass;
import de.alewol.tools.analyzer.pojo.AnalyzedMethod;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class VerifyLoc {

	public void verifyLocClasses() {
		log.info("Check Lines of Code Classes:");
		log.info("Max Loc Classes: " + InitConf.getMaxLocClasses().toString());
		List<AnalyzedClass> classesBreakingLocMax = new ArrayList<>();
		
		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses)
		{
			if(analyzedClass.getLinesOfCode() >= InitConf.getMaxLocClasses())
			{
				classesBreakingLocMax.add(analyzedClass);
			}
		}
		
		if(!classesBreakingLocMax.isEmpty())
		{
			log.info(classesBreakingLocMax.size() + " Classes violating the configured max Lines of Code:");
			
			classesBreakingLocMax.stream().forEach( analyzedClass -> log.info(analyzedClass.getClassName()));
		}
		else
		{
			log.info("No Class is violating the configured max Lines of Code :)");
		}
	}
	
	public void verifyLocMethods() {
		log.info("Check Lines of Code Methods:");
		log.info("Max Loc Methods: " + InitConf.getMaxLocMethods().toString());
		List<AnalyzedMethod> methodsBreakingLocMax = new ArrayList<>();
		
		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses)
		{
			for(AnalyzedMethod analyzedmethod : analyzedClass.getAnalyzedMethodList())
			{
				if(analyzedmethod.getLinesOfCode() >= InitConf.getMaxLocMethods())
				{
					methodsBreakingLocMax.add(analyzedmethod);
				}
			}
		}
		
		if(!methodsBreakingLocMax.isEmpty())
		{
			log.info(methodsBreakingLocMax.size() + " Methods violating the configured max Lines of Code:");
			
			for(AnalyzedMethod analyzedMethod : methodsBreakingLocMax)
			{
				log.info(analyzedMethod.getMethodName() + " Method in Class " + analyzedMethod.getAffiliatedClass().getClassName());
			}
		}
		else
		{
			log.info("No Method is violating the configured max Lines of Code :)");
		}
	}
}
