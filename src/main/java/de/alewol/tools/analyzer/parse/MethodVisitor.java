package de.alewol.tools.analyzer.parse;

import static de.alewol.tools.analyzer.JavaMetricAnalyzer.LINE_SEPARATOR;

import org.apache.commons.lang3.StringUtils;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MethodVisitor extends VoidVisitorAdapter {

	public void visit(MethodDeclaration n, Object arg) {
		if (n.getBody().isPresent()) {
			JavaMetricAnalyzer.methodCounter++;

			String methodBody = n.getBody().get().toString();
			Long methodLenght = n.toString().lines().count() - n.getAllContainedComments().size();

			JavaMetricAnalyzer.locMethodList.add(methodLenght);

			Integer countIf = StringUtils.countMatches(methodBody, "if");
			Integer countFor = StringUtils.countMatches(methodBody, "for");
			Integer countWhile = StringUtils.countMatches(methodBody, "while");
			Integer countAnd = StringUtils.countMatches(methodBody, "&&");
			Integer countOr = StringUtils.countMatches(methodBody, "||");
			Integer countThrow = StringUtils.countMatches(methodBody, "throw");
			Integer countCatch = StringUtils.countMatches(methodBody, "catch");

			Integer cyclomaticComplexity = countIf + countFor + countWhile + countAnd + countOr + countThrow
					+ countCatch;
			JavaMetricAnalyzer.cyclomaticComplexityList.add(cyclomaticComplexity);

			log.info(LINE_SEPARATOR);
			log.info("Method Name: " + n.getName());
			log.info("Method Length: " + methodLenght);
			log.info("CyclomaticComplexity: " + cyclomaticComplexity);
			log.info(LINE_SEPARATOR);
		}
	}
}
