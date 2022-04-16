package com.maksymilian.converter;

import java.util.List;

public interface MaxamatOConverter {

    int binaryToDecimal(String binary);
    String toBase64(String toEncode);
    String toBinary(int numberToChange);
    String toBinary(String textToChange);
    String binaryToText(String binary);
}
