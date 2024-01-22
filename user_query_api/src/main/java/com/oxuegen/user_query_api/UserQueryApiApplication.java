package com.oxuegen.user_query_api;

import com.oxuegen.user_core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
public class UserQueryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserQueryApiApplication.class, args);
	}

}
