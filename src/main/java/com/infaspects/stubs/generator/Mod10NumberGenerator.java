package com.infaspects.stubs.generator;

import com.infaspects.stubs.util.CheckDigitHelper;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Generate a random number of a given length with the check digit that passes mod10 test
 */
@Component
public class Mod10NumberGenerator {

    private int numberLength;

    /**
     * Constructor to initialize the object with the length of the random number to be generated
     *
     * @param numberLength - lenght of the random number including the check digit
     *
     */
    public Mod10NumberGenerator(int numberLength) {
        this.numberLength = numberLength;
    }

    /**
     * Generate the random number with the check digit
     *
     * @return random number with the check digit
     */
    public String getMod10Number() {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder mod10String = new StringBuilder();


        for (int index = 0; index < this.numberLength - 1; ++index) {
            mod10String.append(random.nextInt(10));
        }

        return mod10String.append(CheckDigitHelper.generateCheckDigit(mod10String.toString())).toString();
    }
}
