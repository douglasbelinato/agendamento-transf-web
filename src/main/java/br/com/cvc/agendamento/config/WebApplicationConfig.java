package br.com.cvc.agendamento.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configurações para direcionar URLs desconhecidas para página 
 * personalizada de endereço não encontrado.
 *
 */
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/paginaNaoEncontrada").setViewName("forward:/index.html");
	}

	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
		return container -> container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/paginaNaoEncontrada"));
	}

}