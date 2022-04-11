package com.maksymilian.converter;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


public class ConverterOfFun {
    Map<String, String> map = new HashMap<>();
    String base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static void main(String[] args) {
        ConverterOfFun converterOfFun = new ConverterOfFun();
        converterOfFun.toBase64("example string");
//        System.out.println(converterOfFun.toBinary("Continual delighted as elsewhere am convinced unfeeling. And the god said, let it be. And so it happened"));
//        converterOfFun.toBinary("Continual delighted as elsewhere am convinced unfeeling.");
//        System.out.println(converterOfFun.binaryToText("01101000 01100101 01101100 01101111 00100000 01101001 01110100 01110011 00100000 00100000 01101101 01100101"));
//        System.out.println(converterOfFun.toBinary(1203));
    }

    public int binaryToDecimal(String binary){
        int length = binary.length();
        List<Integer> ints = new ArrayList<>();
        binary = binary.trim();
        for (int i=0; i<=length-1; i++){
            if(binary.charAt(length-1-i)=='1'){
                ints.add((int) Math.pow(2, i));
            }
            else if(binary.charAt(length-i-1)=='0'){
                ints.add(0);
            }
        }
        int result = 0;
        for (int i:ints) {
            result += i;
        }
        return result;
    }

    public String binaryToText(String binary){

        String[] splitBinary = binary.split("\\s+");
        List<String> chars = new ArrayList<>();
        for (int i=0; i<=splitBinary.length-1; i++){
            chars.add(Character.toString((char) binaryToDecimal(splitBinary[i])));
        }
        return removeSpaces(convertToString(chars));
    }

    public String toBase62(){
        return "encoded";
    }


    // TODO: Having a problem with the last char in a sentence
    public String toBase64(String toEncode){
        List<Integer> asciiValues = new ArrayList<>();
        List<String> binaryValues;
        String binaryValuesInString;


        for (char c:toEncode.toCharArray()) {
            asciiValues.add((int) c);
        }

        binaryValues = asciiValues.stream().map(this::toBinary).collect(Collectors.toList());
        binaryValues = addZeros(binaryValues);
        binaryValuesInString = removeSpaces(convertToString(binaryValues));

        List<String> binaryValuesInChars = new ArrayList<>();
        for (char c:binaryValuesInString.toCharArray()) {
            binaryValuesInChars.add(String.valueOf(c));
        }

        int length = binaryValuesInString.length();
        String removedChars;
        List<String> removedCharsAsList = new ArrayList<>();
        for(int i=0; i<=(length%6); i++){
            removedCharsAsList.add(String.valueOf(binaryValuesInChars.get(length-i-1)));
            binaryValuesInChars.remove(length-i-1);
            length -= 1;
        }
        removedCharsAsList = reverse(removedCharsAsList);
        removedChars = removeSpaces(convertToString(removedCharsAsList));
        removedCharsAsList.clear();
        removedCharsAsList.add(removedChars);
        removedCharsAsList = addZerosBase64(removedCharsAsList);

        String combined = convertToString(binaryValuesInChars) +
                convertToString(removedCharsAsList);

        String combinedNoSpaces = removeSpaces(combined);
        System.out.println(combinedNoSpaces);

        combinedNoSpaces=splitIntoGroupsOfSix(combinedNoSpaces);
        System.out.println(combinedNoSpaces);

        List<String> toParse;
        toParse = Arrays.asList(combinedNoSpaces.split(" "));
        System.out.println(toParse);
        List<Integer> ints = new ArrayList<>();

        for(String s:toParse){
            ints.add(binaryToDecimal(s));
        }
        System.out.println(ints);
        StringBuilder result = new StringBuilder();

        for(int i=0; i<=ints.size()-1; i++){
            result.append(base64Chars.charAt(ints.get(i)));
        }
        System.out.println(result);

        return "";
    }

    public String toBase64(int toEncode){
        return "";
    }



    public String toBinary(int numberToChange){

        List<String> result = new ArrayList<>();

        if(numberToChange==1){
            result.add("00000001");
            return convertToString(result);
        }
        if (numberToChange==0){
            result.add("00000000");
            return convertToString(result);
        }
        // Binary conversion process
        // If something is divisible by 2, append 0, else append 1
        while(true){
            if(numberToChange==1){
                result.add("1");
                break;
            }
            if(numberToChange%2==0){
                result.add("0");
            }
            else{
                result.add("1");
            }
            numberToChange = numberToChange/2;
        }

        // Reverse the list, so the binary goes in the right order
        result=reverse(result);
        result = Collections.singletonList(removeSpaces(convertToString(result)).trim());
        return convertToString(result);
    }

    // Loop through an array and initialize a new one that is reversed
    private List<String> reverse(List<String> data){
        List<String> result = new ArrayList<>();
        for (int i = data.size(); i > 0; i--){
            result.add((data.get(i-1)));
        }
        return result;
    }

    private String convertToString(List<String> data){
        StringBuilder result = new StringBuilder();
        for (String datum : data) {
            result.append(datum);
            result.append(" ");
        }
        return result.toString();
    }

    /**
     * To convert string to binary, we need to create an array of chars that the input string contains,
     * create an array of integers, that stores ASCII values of specific chars,
     * change these ASCII values into binary and return a list of binaries
     */
    public String toBinary(String textToChange){

        List<Character> chars = new ArrayList<>();
        List<Integer> ints = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for(char c:textToChange.toCharArray()){
            chars.add(c);
        }
        for (Character aChar : chars) {
            ints.add((int) aChar);

        }
        for (Integer i : ints){
            // Append a binary representation of an ASCII value to the String array
            result.add(toBinary(i));

        }
        // Convert List to a String

        result = Collections.singletonList(removeSpaces(convertToString(result)));
        result = addZeros(result);
        return convertToString(result).trim();
    }

    private String removeSpaces(String data){

        String replaced = data.replace("  ", "and");
        replaced = replaced.replace(" ", "");
        replaced = replaced.replace("and", " ");
        return replaced;
    }

    /**
     * Filling data with missing zeros, so that it has 8 digits total
     */
    private List<String> addZeros(List<String> data){
        String result = convertToString(data);
        String[] split = result.split("\\s+");
        List<String> withZeros = new ArrayList<>();
        for (String s:split) {
            withZeros.add(String.format("%08d", Integer.parseInt(s)));
        }
        return withZeros;
    }
    private List<String> addZerosBase64(List<String> data){
        String result = convertToString(data);
        String[] split = result.split("\\s+");
        List<String> withZeros = new ArrayList<>();
        for (String s:split) {
            withZeros.add(String.format("%06d", Integer.parseInt(s)));
        }
        return withZeros;
    }
    private String splitIntoGroupsOfSix(String data){

        StringBuilder result= new StringBuilder();

        for(int i=0; i<=data.length()-1; i++){
            if(i%6==0&&i!=0){
                result.append(" ");
            }
            result.append(data.charAt(i));
        }

        return result.toString();
    }
}


