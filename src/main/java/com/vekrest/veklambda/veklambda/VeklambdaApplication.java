package com.vekrest.veklambda.veklambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeklambdaApplication implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(VeklambdaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VeklambdaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("VEKREST -> VEKLAMBDA - INICIALIZADO COM SUCESSO!");
	}
}
