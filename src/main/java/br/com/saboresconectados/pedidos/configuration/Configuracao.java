package br.com.saboresconectados.pedidos.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracao {
    
    @Bean
    public ModelMapper obterModelMapper(){
        return new ModelMapper();
    }
}
