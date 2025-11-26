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
        return consumer -> LOG.info("VEKLAMBDA -> Um cliente deseja realizar cadastro: {}!", consumer.toString());
    }
}
