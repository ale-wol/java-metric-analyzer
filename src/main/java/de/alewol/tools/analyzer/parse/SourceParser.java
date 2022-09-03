package de.alewol.tools.analyzer.parse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;

public class SourceParser {
	
	public ParseResult<CompilationUnit> parse(Path file)
	{
		InputStream inputStream = null;
		ParseResult<CompilationUnit> compilationUnit = null;
		JavaParser javaParser = new JavaParser();
		try {
			inputStream = new FileInputStream(file.toString());
			compilationUnit = javaParser.parse(inputStream);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				inputStream.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		return compilationUnit;
	}

}
