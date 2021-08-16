package org.dasl.demo.gateway.ftp;

import java.io.File;
import java.util.List;

import org.dasl.demo.gateway.ftp.FtpConfiguration.GateFile;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtpApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner runner(GateFile gateFile) {
		return args -> {
			List<File> files = gateFile.mget (".");
			for (File file : files) {
				System.out.println ("File "+(files.indexOf(file)+1)+": "+file.getName());
			}
		};
	}
}
