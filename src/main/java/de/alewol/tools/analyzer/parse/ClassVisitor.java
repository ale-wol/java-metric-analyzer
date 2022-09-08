package de.alewol.tools.analyzer.parse;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import de.alewol.tools.analyzer.pojo.AnalyzedClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClassVisitor extends VoidVisitorAdapter<List<AnalyzedClass>>{

	@Override
	public void visit(ClassOrInterfaceDeclaration cd, List<AnalyzedClass> collector) {
		super.visit(cd, collector);
		String className = cd.getName().toString();
		Long classLength = cd.toString().lines().count() - cd.getAllContainedComments().size();
				
		
		log.info("Class Name: " + className);
		log.info("Class Length: " + classLength);
		
		AnalyzedClass analyzedClass = new AnalyzedClass(className, classLength.intValue());
		collector.add(analyzedClass);
	}
}
