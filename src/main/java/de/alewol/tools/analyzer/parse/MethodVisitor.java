package de.alewol.tools.analyzer.parse;

import static de.alewol.tools.analyzer.JavaMetricAnalyzer.LINE_SEPARATOR;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import de.alewol.tools.analyzer.pojo.AnalyzedMethod;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MethodVisitor extends VoidVisitorAdapter<List<AnalyzedMethod>> {

	@Override
	public void visit(MethodDeclaration md, List<AnalyzedMethod> collector) {
		if (md.getBody().isPresent()) {
			
			String methodBody = md.getBody().get().toString();
			Long methodLenght = md.toString().lines().count() - md.getAllContainedComments().size();

			Integer countIf = StringUtils.countMatches(methodBody, "if");
			Integer countFor = StringUtils.countMatches(methodBody, "for");
			Integer countWhile = StringUtils.countMatches(methodBody, "while");
			Integer countAnd = StringUtils.countMatches(methodBody, "&&");
			Integer countOr = StringUtils.countMatches(methodBody, "||");
			Integer countThrow = StringUtils.countMatches(methodBody, "throw");
			Integer countCatch = StringUtils.countMatches(methodBody, "catch");

			Integer cyclomaticComplexity = countIf + countFor + countWhile + countAnd + countOr + countThrow
					+ countCatch;

			log.info(LINE_SEPARATOR);
			log.info("Method Name: " + md.getName());
			log.info("Method Length: " + methodLenght);
			log.info("CyclomaticComplexity: " + cyclomaticComplexity);
			log.info(LINE_SEPARATOR);
			
			AnalyzedMethod analyzedMethod = new AnalyzedMethod(md.getName().toString(), methodLenght, cyclomaticComplexity);
			collector.add(analyzedMethod);
		}
	}
}
