package de.alewol.tools.analyzer.find;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Finder extends SimpleFileVisitor<Path>{

	
	private final PathMatcher matcher;
	private int numMatches = 0;
	
	Finder(String pattern) {
		matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
	}
	
	void find (Path file)
	{
		Path name = file.getFileName();
		if(name != null && matcher.matches(name))
		{
			numMatches ++;
			JavaMetricAnalyzer.getSourceFiles().add(file);
			log.info("File found: " + file);
		}
	}
	
	void done() {
		log.info("Found " + numMatches + " Source Files");
	}
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
	{
		find(file);
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
	{
		find(dir);
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException e)
	{
		log.error(e);
		return FileVisitResult.CONTINUE;
	}
}
