package com.maksymilian.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConverterOfFun {

    public static void main(String[] args) {
//        ConverterOfFun converterOfFun = new ConverterOfFun();
//        System.out.println(converterOfFun.toBinary("Continual delighted as elsewhere am convinced unfeeling. And the god said, let it be. And so it happened"));
//        converterOfFun.toBinary("Continual delighted as elsewhere am convinced unfeeling.");
    }

    public int toDecimal(){
        return 1;
    }

    public String toText(){
        return "decoded";
    }

    public String toBase62(){
        return "encoded";
    }



    public String toBinary(int numberToChange){

        List<String> result = new ArrayList<>();

        if(numberToChange==1){
            result.add("1");
            return convertToString(result);
        }
        if (numberToChange==0){
            result.add("0");
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
}
