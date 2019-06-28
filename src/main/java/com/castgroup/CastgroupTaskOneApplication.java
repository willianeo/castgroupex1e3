package com.castgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.castgroup.model.Pessoa;
import com.castgroup.repository.PessoaRepository;

@SpringBootApplication
public class CastgroupTaskOneApplication implements CommandLineRunner {

	@Autowired
	PessoaRepository pessoaRepository;
	
	
	
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(CastgroupTaskOneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		pessoaRepository.save(new Pessoa(1, "Maria das Graças", "Antônio Medeiros, 78", "Lourdes", "Itaúna", "MG", "+55 37988 440 956", "37 3242-8281"));
		pessoaRepository.save(new Pessoa(2, "Rodrigo", "Antônio Medeiros, 78", "Lourdes", "Itaúna", "MG", "+55 37988 440 956", "37 3242-8281"));
		pessoaRepository.save(new Pessoa(3, "Rogério", "Antônio Medeiros, 78", "Lourdes", "Itaúna", "MG", "+55 37988 440 956", "37 3242-8281"));
		pessoaRepository.save(new Pessoa(4, "Rodrigo", "Antônio Medeiros, 78", "Lourdes", "Itaúna", "MG", "+55 37988 440 956", "37 3242-8281"));
	}

	@Configuration
	public class CorsConfig {

	    @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "PUT").allowedOrigins("*")
	                        .allowedHeaders("*");
	            }
	        };
	    }
	}
	
}
