package de.alewol.tools.analyzer.verify;

import static de.alewol.tools.analyzer.JavaMetricAnalyzer.LINE_SEPARATOR;

import java.util.ArrayList;
import java.util.List;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import de.alewol.tools.analyzer.conf.InitConf;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class VerifyLoc {

	public void verifyLocClasses() {
		
		log.info(LINE_SEPARATOR);
		log.info("Max Loc Classes: " + InitConf.getMaxLocClasses().toString());
		List<String> classesBreakingLocMax = new ArrayList<>();
		List<String> keyList = new ArrayList<String>(JavaMetricAnalyzer.locClassesMap.keySet());
		for(int i = 0; i < keyList.size(); i++)
		{
			String className = keyList.get(i);
			Long loc = JavaMetricAnalyzer.locClassesMap.get(className);
			
			if(loc >= InitConf.getMaxLocClasses())
			{
				classesBreakingLocMax.add(className);
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
