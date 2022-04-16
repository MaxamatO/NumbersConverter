package com.maksymilian.converter;

import java.util.*;
import java.util.stream.Collectors;

public class ConverterOfFun implements MaxamatOConverter {
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


    /**
     * It is not the greatest converter but it works.
     * @param toEncode Text to encode
     * @return Returns a String of base64 encoded input text
     */
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

        String groupedIntoSix = splitIntoGroupsOfSix(binaryValuesInString);
        List<String> listOfSixBits = Arrays.stream(groupedIntoSix.split(" ")).collect(Collectors.toList());

        // Checks how many digits are missing
        String BASE64CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        if(listOfSixBits.get(listOfSixBits.size() - 1).length() == 4){
            String toFill = listOfSixBits.get(listOfSixBits.size()-1)+"00";
            listOfSixBits.remove(listOfSixBits.size()-1);
            listOfSixBits.add(toFill);
            List<Integer> listOfIntegers = listOfSixBits.stream().map(this::binaryToDecimal).collect(Collectors.toList());
            List<String> resultList = new ArrayList<>();
            for(int i =0; i<=listOfIntegers.size()-1; i++){
                resultList.add(String.valueOf(BASE64CHARS.charAt(listOfIntegers.get(i))));
            }
            return removeSpaces(convertToString(resultList))+"=";
        }

        // Checks how many digits are missing
        if(listOfSixBits.get(listOfSixBits.size() - 1).length() == 2){
            String toFill = listOfSixBits.get(listOfSixBits.size()-1)+"0000";
            listOfSixBits.remove(listOfSixBits.size()-1);
            listOfSixBits.add(toFill);
            List<Integer> listOfIntegers = listOfSixBits.stream().map(this::binaryToDecimal).collect(Collectors.toList());
            List<String> resultList = new ArrayList<>();
            for(int i =0; i<=listOfIntegers.size()-1; i++){
                resultList.add(String.valueOf(BASE64CHARS.charAt(listOfIntegers.get(i))));
            }
            return removeSpaces(convertToString(resultList))+"==";
        }

        List<Integer> listOfIntegers = listOfSixBits.stream().map(this::binaryToDecimal).collect(Collectors.toList());
        List<String> resultList = new ArrayList<>();
        for(int i =0; i<=listOfIntegers.size()-1; i++){
            resultList.add(String.valueOf(BASE64CHARS.charAt(listOfIntegers.get(i))));
        }
        return removeSpaces(convertToString(resultList));
    }

    public String toBase64(int toEncode){
        return "";
    }

    /**
     * Encodes integer into 8 bits binary
     * @param numberToChange Integer to represent in binary
     * @return A string of binaries
     */
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
     * @param textToChange Input String that will be changed into binary representation
     * @return Returns a String of binary representation of textToChange
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
     * Filling data with missing zeros, so that it has 8 digits total.
     * Meant for binary operations specifically.
     * @param data List of Strings to fill the zeros to.
     * @return Returns a List of Strings with filled zeros.
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
