package de.alewol.tools.analyzer.verify;

import static de.alewol.tools.analyzer.JavaMetricAnalyzer.LINE_SEPARATOR;

import java.util.ArrayList;
import java.util.List;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import de.alewol.tools.analyzer.conf.InitConf;
import de.alewol.tools.analyzer.pojo.AnalyzedClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class VerifyLoc {

	public void verifyLocClasses() {
		
		log.info(LINE_SEPARATOR);
		log.info("Max Loc Classes: " + InitConf.getMaxLocClasses().toString());
		List<String> classesBreakingLocMax = new ArrayList<>();
		
		for(AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses)
		{
			if(analyzedClass.getLinesOfCode() >= InitConf.getMaxLocClasses())
			{
				classesBreakingLocMax.add(analyzedClass.getClassName());
			}
		}
		
		if(!classesBreakingLocMax.isEmpty())
		{
			log.info(classesBreakingLocMax.size() + " Classes violating the configured max Lines of Code:");
			
			classesBreakingLocMax.stream().forEach(log::info);
		}
		else
		{
			log.info("No Class is violating the configured max Lines of Code :)");
		}
	}
}
