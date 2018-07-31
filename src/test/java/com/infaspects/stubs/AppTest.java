package com.infaspects.stubs;
import org.springframework.beans.factory.annotation.Autowired;
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

@ContextConfiguration(
        classes={Mod10NumberGenerator.class, CardNumberGenerator.class}
)
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {

    @Resource(name="mod10NumberGeneratorTest")
    Mod10NumberGenerator mod10NumberGeneratorTest;

    @Resource(name="cardNumberGeneratorTest")
    CardNumberGenerator cardNumberGeneratorTest;

    @Bean(name="mod10NumberGeneratorTest")
    public Mod10NumberGenerator mod10NumberGeneratorTest() {
        return new Mod10NumberGenerator(9);
    }

    @Bean(name="cardNumberGeneratorTest")
    public CardNumberGenerator cardNumberGeneratorTest() {
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

