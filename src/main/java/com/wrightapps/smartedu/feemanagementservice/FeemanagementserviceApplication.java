package com.wrightapps.smartedu.feemanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.ComponentScan.Filter;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
)
@ComponentScan(basePackages = "com.wrightapps.*", excludeFilters = {@Filter(type = FilterType.REGEX, pattern = "com\\.wrightapps\\.config\\..*")})
@EnableConfigurationProperties
@EnableScheduling
public class FeemanagementserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeemanagementserviceApplication.class, args);
	}

}
