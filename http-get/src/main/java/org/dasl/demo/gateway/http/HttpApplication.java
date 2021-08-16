package org.dasl.demo.gateway.http;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@SpringBootApplication
@ImportResource("http-outbound-gateway.xml")
public class HttpApplication {

	@Autowired
	@Qualifier("get_send_channel")
	MessageChannel getSendChannel;

	@Autowired
	@Qualifier("get_send_channel_external")
	MessageChannel getSendChannelExternal;

	@Autowired
	@Qualifier("get_receive_channel")
	PollableChannel getReceiveChannel;

	@Autowired
	@Qualifier("get_receive_channel_external")
	PollableChannel getReceiveChannelExternal;

	public static void main(String[] args) {
		SpringApplication.run(HttpApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		
		return args -> {
			Message<?> message = MessageBuilder.withPayload("").build();						
			getSendChannel.send(message);
			String obj = getReceiveChannel.receive().getPayload().toString();
			System.out.println("Objeto: " + obj);
			String path = "../apache-ftpserver-1.1.1/res/home/";
			String name = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
			String fileName = path + name + ".json";			
			try {
				FileWriter file = new FileWriter(fileName);
				file.write(formatJSONStr.formatJSONString(obj, 4));
				file.flush();
				file.close();
				System.out.println("Archivo: " + fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			Message<?> messageExternal = MessageBuilder.withPayload("").build();
			getSendChannelExternal.send(messageExternal);
			String objExternal = getReceiveChannelExternal.receive().getPayload().toString();
			System.out.println("Objeto Externo: " + objExternal);
			String pathExternal = "../apache-ftpserver-1.1.1/res/home/";
			String nameExternal = "external_" + new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
			String fileNameExternal = pathExternal + nameExternal + ".json";
			try {
				FileWriter file = new FileWriter(fileNameExternal);
				file.write(formatJSONStr.formatJSONString(objExternal, 4));
				file.flush();
				file.close();
				System.out.println("Archivo Externo: " + fileNameExternal);
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
}