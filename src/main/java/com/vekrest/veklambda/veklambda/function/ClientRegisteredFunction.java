package com.vekrest.veklambda.veklambda.function;

import com.vekrest.veklambda.veklambda.function.dto.ClientRegisteredMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public class ClientRegisteredFunction {
    private static final Logger LOG = LoggerFactory.getLogger(ClientRegisteredFunction.class);

    @Bean
    public Consumer<ClientRegisteredMessage> clientRegisteredConsumer() {
        return consumer -> LOG.info("Um cliente deseja realizar cadastro: nome: {}, data de nascimento: {}, cep: {}, estado: {}!",
                consumer.name(),
                consumer.birth(),
                consumer.address().cep(),
                consumer.address().state()
        );

    }
}
