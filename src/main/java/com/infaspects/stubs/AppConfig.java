package com.infaspects.stubs;

import com.infaspects.stubs.generator.CardNumberGenerator;
import com.infaspects.stubs.generator.Mod10NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "mod10ClientID")
    public Mod10NumberGenerator mod10ClientID() {
        return new Mod10NumberGenerator(9);
    }


    @Autowired
    public Mod10NumberGenerator mod10ClientID;

    @Bean(name = "cardNumberGenWithClientID")
    public CardNumberGenerator cardNumberGenWithClientID() {
        return new CardNumberGenerator("5402", "02", mod10ClientID.getMod10Number());
    }
}
