package com.infaspects.stubs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.infaspects.stubs.generator.CardNumberGenerator;
import com.infaspects.stubs.generator.Mod10NumberGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = {Mod10NumberGenerator.class, CardNumberGenerator.class, AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    Mod10NumberGenerator mod10ClientID = (Mod10NumberGenerator) context.getBean("mod10ClientID");
    CardNumberGenerator cardNumberGenWithClientID = (CardNumberGenerator) context.getBean("cardNumberGenWithClientID");

    @Test
    public void testMod10Generator() {
        String number = mod10ClientID.getMod10Number();
        System.out.println(number);
        assertTrue(number.length() == 9);
    }

    @Test
    public void testCardNumberGenerator() {
        String cardNumber = cardNumberGenWithClientID.generateClientCard();
        System.out.println(cardNumber);

        assertTrue(cardNumber.length() == 16);
    }
}

