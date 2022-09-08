package de.alewol.tools.analyzer.conf;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.ini4j.Wini;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InitConf {

	private static final String INI_LOC = "LinesOfCode";
	private static final String INI_CC = "CyclomaticComplexity";

	@Getter
	private static File configFile;
	@Getter
	private static Integer maxLocClasses;
	@Getter
	private static Integer maxLocMethods;
	@Getter
	private static Integer maxCyclomaticComplexityMethods;

	private InitConf() {
		throw new IllegalStateException();
	}

	public static void initReadConfig() {
		try {
			readConfig();
		} catch (IOException e) {
			log.error("Error while reading the config.ini File");
			e.printStackTrace();
		}

	}
	
	private static synchronized void readConfig() throws IOException {
		String configPath = "";
		log.info("read config.ini file");
		configPath = FilenameUtils.getFullPathNoEndSeparator(new java.io.File("config/config.ini").getAbsolutePath());

		log.debug("configPath: " + configPath);

		configFile = new File(configPath, FilenameUtils.getName("config.ini"));

		Wini ini = new Wini(configFile);

		maxLocClasses = ini.get(INI_LOC, "maxLocClasses", Integer.class);
		maxLocMethods = ini.get(INI_LOC, "maxLocMethods", Integer.class);
		maxCyclomaticComplexityMethods = ini.get(INI_CC, "maxCyclomaticComplexityMethods", Integer.class);

		log.info("maxLocClasses: " + maxLocClasses);
		log.info("maxLocMethods: " + maxLocMethods);
	}
}
