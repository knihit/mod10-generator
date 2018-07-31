package com.infaspects.stubs;

import com.infaspects.stubs.generator.CardNumberGenerator;
import com.infaspects.stubs.generator.Mod10NumberGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import static org.junit.Assert.assertTrue;

public class AppTest {

    @Autowired
    Mod10NumberGenerator mod10NumberGeneratorTest;

    @Autowired
    CardNumberGenerator cardNumberGeneratorTest;

    @Bean
    public Mod10NumberGenerator getMod10NumberGeneratorTest() {
        return new Mod10NumberGenerator(9);
    }

    @Bean
    public CardNumberGenerator getCardNumberGeneratorTest() {
        return new CardNumberGenerator("4519", "02", mod10NumberGeneratorTest.getMod10Number());
    }

    @Test
    public void testMod10Generator() {
        String number = mod10NumberGeneratorTest.getMod10Number();
        System.out.println(number);
        assertTrue(number.length() == 9);
    }

    @Test
    public void testCardNumberGenerator() {
        String cardNumber = cardNumberGeneratorTest.generateClientCard();
        System.out.println(cardNumber);

        assertTrue(cardNumber.length() == 16);
    }
}

