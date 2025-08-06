package br.com.saboresconectados.pedidos.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class Configuracao {
    
    @Bean
    public ModelMapper obterModelMapper(){
        return new ModelMapper();
    }
}
