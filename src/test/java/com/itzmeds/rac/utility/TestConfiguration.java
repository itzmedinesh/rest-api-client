package com.itzmeds.rac.utility;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.itzmeds.rac.core.ClientConfiguration;

public class TestConfiguration extends ClientConfiguration {

	private static final Logger LOGGER = LogManager
			.getLogger(TestConfiguration.class);

	public TestConfiguration() {
	}

	public static ClientConfiguration load() {

		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

		try {
			return objectMapper.readValue(new File("test.yml"),
					ClientConfiguration.class);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info("Error in TestConfiguration", e);
			return null;
		}
	}

}
