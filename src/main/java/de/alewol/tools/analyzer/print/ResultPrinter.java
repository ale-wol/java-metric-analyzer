package de.alewol.tools.analyzer.print;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import de.alewol.tools.analyzer.JavaMetricAnalyzer;
import de.alewol.tools.analyzer.pojo.AnalyzedClass;
import de.alewol.tools.analyzer.pojo.AnalyzedMethod;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResultPrinter {

	Path reportPath;

	public void generateReportDirectory() {
		String projectName = JavaMetricAnalyzer.getProjectDirToScan();
		String cuttedProjectName = projectName.substring(projectName.lastIndexOf("\\") + 1);

		
		log.info("projectName: " + projectName);
		log.info("cuttedProjectName: " + cuttedProjectName);

		reportPath = Paths.get("report/" + cuttedProjectName);
		try {
			Files.createDirectories(reportPath);
		} catch (IOException e) {
			log.error("Error while creating Report Directory");
			e.printStackTrace();
		}
	}

	public void writeClassReport() {
		String[] HEADERS = { "Class Name", "Lines of Code", "Number of Methods" };

		try {
			FileWriter out = new FileWriter(reportPath.toString() + "/ClassReport.csv");

			try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL.withHeader(HEADERS))) {
				for (AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses) {
					try {
						printer.printRecord(analyzedClass.getClassName(), analyzedClass.getLinesOfCode(),
								analyzedClass.getAnalyzedMethodList().size());
					} catch (IOException e) {
						log.error("Error while writing Class Report");
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			log.error("Error while creating Class Report");
			e.printStackTrace();
		}
	}

	public void writeMethodReport() {
		String[] HEADERS = { "Method Name", "affiliatedClass" , "Lines of Code", "Cyclomatic Complexity"};

		try {
			FileWriter out = new FileWriter(reportPath.toString() + "/MethodReport.csv");

			try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL.withHeader(HEADERS))) {
				for (AnalyzedClass analyzedClass : JavaMetricAnalyzer.analyzedClasses) {
					for(AnalyzedMethod analyzedMethod : analyzedClass.getAnalyzedMethodList()) {
						try {
							printer.printRecord(analyzedMethod.getMethodName(), analyzedMethod.getAffiliatedClass().getClassName() , analyzedClass.getLinesOfCode(),
									analyzedMethod.getCyclomaticComplexity());
						} catch (IOException e) {
							log.error("Error while writing Method Report");
							e.printStackTrace();
						}
					}
				}
			}
		} catch (IOException e) {
			log.error("Error while creating Method Report");
			e.printStackTrace();
		}
	}
	
	public void writeValidationReport() {
		//check Configured Metrics
	}

	
	public void writeMedianReport() {
		// Average and Max Values
	}
}
