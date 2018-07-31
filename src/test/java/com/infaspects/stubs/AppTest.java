package com.infaspects.stubs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.infaspects.stubs.generator.CardNumberGenerator;
import com.infaspects.stubs.generator.Mod10NumberGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

@Component
@ContextConfiguration(
        classes={Mod10NumberGenerator.class, CardNumberGenerator.class}
)
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {

    Mod10NumberGenerator mod10NumberGenerator;

    CardNumberGenerator cardNumberGenerator;

    @Bean(name="cardNumberGenerator")
    public CardNumberGenerator cardNumberGenerator() {
        return new CardNumberGenerator("4519", "02", mod10NumberGenerator.getMod10Number());
    }

    @Test
    public void testMod10Generator() {
        String number = mod10NumberGenerator.getMod10Number();
        System.out.println(number);
        assertTrue(number.length() == 9);
    }

    @Test
    public void testCardNumberGenerator() {
        String cardNumber = cardNumberGenerator.generateClientCard();
        System.out.println(cardNumber);

        assertTrue(cardNumber.length() == 16);
    }
}

