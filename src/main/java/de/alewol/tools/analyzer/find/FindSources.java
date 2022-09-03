package de.alewol.tools.analyzer.find;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FindSources {
	
	public void findJavaSourceFiles(String startingDirString)
	{
		log.info("Project Path: " + startingDirString);
		
		Path startingDirPath = Paths.get(startingDirString);
		
		Finder finder = new Finder("*.java");
		try {
			Files.walkFileTree(startingDirPath, finder);
		}
		catch (IOException e) {
			log.error("Error while walking threw File Tree.");
			e.printStackTrace();
		}
		finally {
			finder.done();
		}
	}	
}
