package de.alewol.tools.analyzer.pojo;

import java.nio.file.Path;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class AnalyzedClass {
	
	@Getter
	@Setter
	private String className;
	@Getter
	@Setter
	private Integer linesOfCode;
	@Getter
	@Setter
	private Double cyclomaticComplexity;
	@Getter
	@Setter
	private Path location;
	@Getter
	@Setter
	private ArrayList<AnalyzedMethod> analyzedMethodList;

	public AnalyzedClass(String className, Integer loc) {
		this.className = className;
		this.linesOfCode = loc;
	}
}
