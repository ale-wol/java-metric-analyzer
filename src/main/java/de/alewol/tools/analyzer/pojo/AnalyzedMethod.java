package de.alewol.tools.analyzer.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
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

}
