package br.com.cvc.agendamento;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.gson.GsonBuilder;

import br.com.cvc.agendamento.integration.json.serializer.LocalDateDeserializer;
import br.com.cvc.agendamento.integration.json.serializer.LocalDateSerializer;

/**
 * Classe principal para iniciar a aplicação SpringBoot 
 *
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
    
    /**
     * Criação de bean personalizado com adaptadores específicos para 
     * serializar e desarializar de forma correta os objetos LocalDate no Json
     * 
     * @return
     */
    @Bean
    public GsonBuilder gsonBuilder() {
    	GsonBuilder gsonBuilder = new GsonBuilder();    	
    	gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
    	gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
    	return gsonBuilder;
    }
    
}