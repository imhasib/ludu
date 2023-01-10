package com.hsb.ludu;

import com.hsb.ludu.dao.Player;
import com.hsb.ludu.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class LuduApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuduApplication.class, args);
	}

}
