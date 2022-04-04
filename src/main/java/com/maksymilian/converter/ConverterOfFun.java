import java.util.ArrayList;
import java.util.List;

public class ConverterOfFun {

    public static void main(String[] args) {

        ConverterOfFun converterOfFun = new ConverterOfFun();
        String textToConvert = "Example   string";
//        System.out.println(converterOfFun.toBinary("Example string     "));
        System.out.println(converterOfFun.toBinary(textToConvert));
//        System.out.println(base62.removeSpaces(base62.toBinary(textToConvert)));
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



    public List<String> toBinary(int numberToChange){

        List<String> result = new ArrayList<>();
        boolean changed = false;

        if(numberToChange==1){
            result.add("00000001");
            changed=true;
        }

        while(!changed){
            if(numberToChange==1){
                result.add("1");
                changed=true;
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
        result=reverse(result);
        return result;
    }

    public List<String> reverse(List<String> data){
        List<String> result = new ArrayList<>();
        for (int i = data.size(); i > 0; i--){
            result.add((data.get(i-1)));
        }
        return result;
    }

//    protected String convertToString(List<String> data){
////        System.out.println(makeEightDigits(data));
//        String result ="";
//        for (int i =0; i < data.size(); i++){
//            result += data.get(i);
//            result += " ";
//        }
//
////        result = removeSpaces(result);
//
//        return result;
//    }

    public String toBinary(String textToChange){

        List<Character> chars = new ArrayList<>();
        List<Integer> ints = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        for(char c:textToChange.toCharArray()){
            chars.add(c);
        }
        for (Character aChar : chars) {
            ints.add((int) aChar);
        }
        for (int i : ints){
            result.append(toBinary(i));
        }
        return removeSpaces(result.toString());
    }

    public String removeSpaces(String data){

        String replaced = data.replace("  ", "and");
        replaced = replaced.replace(" ", "");
        replaced = replaced.replace("and", " ");
        return replaced;
    }

}
