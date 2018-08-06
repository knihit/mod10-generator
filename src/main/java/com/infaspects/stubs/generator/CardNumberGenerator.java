package com.infaspects.stubs.generator;

import com.infaspects.stubs.AppConfig;
import com.infaspects.stubs.util.CheckDigitHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Generate credit, debit card numbers that are mod10 compliant
 */
@Component
public class CardNumberGenerator {

    /**
     * The bank identification number
     */
    @Value("${default.bin:5402}")
    private String bankIdentificationNum;

    /**
     * Center code as applicable
     */
    @Value("${default.center:02}")
    private String centerCode;

    /**
     * Client's identity
     */
    private String clientID;

    public CardNumberGenerator() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        this.clientID = ((Mod10NumberGenerator)context.getBean("mod10ClientID")).getMod10Number();
    }


    /**
     * Initialize the instance with BIN, Center Code & Client Identity
     *
     * @param bankIdentificationNum
     * @param centerCode
     * @param clientID
     */
    public CardNumberGenerator(String bankIdentificationNum,  String centerCode,  String clientID) {
        this.bankIdentificationNum = bankIdentificationNum;
        this.centerCode = centerCode;
        this.clientID = clientID;
    }

    /**
     * Initialize the instance with BIN & Center Code. Since Client Identity is not initialized, it
     * will generated by the method. The default length of the Client Identity is taken as 9 digits
     *
     * @param bankIdentificationNum
     * @param centerCode
     */
    @Autowired
    public CardNumberGenerator ( String bankIdentificationNum, String centerCode, Mod10NumberGenerator mod10ClientID) {
        this.bankIdentificationNum = bankIdentificationNum;
        this.centerCode = centerCode;
        this.clientID = mod10ClientID.getMod10Number();
    }

    /**
     * Method that returns client card
     *
     * @return client card number as String
     */
    public String generateClientCard () {
        StringBuilder clientCard = new StringBuilder();

        /*if ("".equals(clientID) || null == clientID) {
            clientID = mod10ClientID.getMod10Number();
        }*/

        clientCard.append(bankIdentificationNum).append(centerCode).append(clientID);
        return clientCard.append(CheckDigitHelper.generateCheckDigit(clientCard.toString())).toString();
    }
}
