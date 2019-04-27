package com.magfin.ccs.webccs;

import com.magfin.ccs.webccs.model.UsrResource;
import com.magfin.ccs.webccs.service.UsrResourceService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SpringBootApplication
@MapperScan("com.magfin.ccs.webccs")
public class WebccsApplication {


	public static void main(String[] args) {
		SpringApplication.run(WebccsApplication.class, args);
	}




}
