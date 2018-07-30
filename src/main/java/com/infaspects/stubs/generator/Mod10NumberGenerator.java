package com.infaspects.stubs.generator;

import com.infaspects.stubs.util.CheckDigitHelper;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Mod10NumberGenerator {

    private int numberLength;

    public Mod10NumberGenerator(int numberLength) {
        this.numberLength = numberLength;
    }

    public String getMod10Number(int numberLength) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder mod10String = new StringBuilder();


        for (int index = 0; index < numberLength - 1; ++index) {
            mod10String.append(random.nextInt(10));
        }

        return mod10String.append(CheckDigitHelper.generateCheckDigit(mod10String.toString())).toString();
    }
}
