package br.com.couto.controllers;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.couto.configuration.GreetingConfiguration;
import br.com.couto.model.Greeting;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
	
	private static final String template = "%s, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private GreetingConfiguration configuration;
	
	@GetMapping
	public Greeting greeting(@RequestParam(value="name", defaultValue = "") String name) {
		if(name.isEmpty()) {
			name = configuration.getDefaultValue();
		}
		
		return new Greeting(counter.incrementAndGet(), String.format(template, configuration.getGreeting(), name));
	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		TimeZone tz = calendar.getTimeZone();
		System.out.println(tz);
		
	}

}
