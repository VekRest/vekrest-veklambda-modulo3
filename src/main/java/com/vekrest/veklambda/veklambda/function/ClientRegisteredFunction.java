package com.vekrest.veklambda.veklambda.function;

import com.vekrest.veklambda.veklambda.function.dto.ClientRegisteredMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public class ClientRegisteredFunction {
    @Bean
    public Consumer<ClientRegisteredMessage> clientRegisteredConsumer() {
        return consumer -> {
            System.out.println("Um cliente deseja realizar cadastro: " + consumer.name() + ", data de nascimento: " + consumer.birth() + ", cep: " + consumer.address().cep() + ", estado: " + consumer.address().state() + "!" );
        };
    }
}
