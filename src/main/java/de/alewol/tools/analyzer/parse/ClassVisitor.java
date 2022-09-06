package de.alewol.tools.analyzer.parse;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClassVisitor extends VoidVisitorAdapter{

	@Override
	public void visit(final ClassOrInterfaceDeclaration n, Object arg) {
		String className = n.getName().toString();
		Long classLength = n.toString().lines().count() - n.getAllContainedComments().size();
		
		JavaMetricAnalyzer.locClassesMap.put(className, classLength);
		
		
		log.info("Class Name: " + className);
		log.info("Class Length: " + classLength);
	}
}
