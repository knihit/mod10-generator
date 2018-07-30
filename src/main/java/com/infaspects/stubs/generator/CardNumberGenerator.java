package com.infaspects.stubs.generator;

import org.springframework.stereotype.Component;

@Component
public class CardNumberGenerator {

    private String bankIdentificationNum;

    private String centerCode;

    private String clientID;

    public CardNumberGenerator(String bankIdentificationNum, String centerCode, String clientID) {
        this.bankIdentificationNum = bankIdentificationNum;
        this.centerCode = centerCode;
        this.clientID = clientID;
    }

    public String generateClientCard () {
        StringBuilder clientCard = new StringBuilder();

        clientCard.append(bankIdentificationNum).append(centerCode).append(clientID);
        return clientCard.append(Mod10NumberGenerator.generateCheckDigit(clientCard.toString())).toString();
    }
}
