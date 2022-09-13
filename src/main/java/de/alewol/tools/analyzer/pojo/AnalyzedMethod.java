package de.alewol.tools.analyzer.pojo;

import lombok.Getter;
import lombok.Setter;

public class AnalyzedMethod {
	
	@Getter
	@Setter
	private String methodName;
	@Getter
	@Setter
	private Long linesOfCode;
	@Getter
	@Setter
	private Integer cyclomaticComplexity;
	@Getter
	@Setter
	private AnalyzedClass affiliatedClass;
	
	public AnalyzedMethod(String methodName, Long linesOfCode, Integer cyclomaticComplexity) {
		this.methodName = methodName;
		this.linesOfCode = linesOfCode;
		this.cyclomaticComplexity = cyclomaticComplexity;
	}
}
