import java.util.ArrayList;
import java.util.List;

public class ConverterOfFun {

    public static void main(String[] args) {

        ConverterOfFun converterOfFun = new ConverterOfFun();
        String textToConvert = "Example string";
        System.out.println(converterOfFun.toBinary("Example string     "));
        System.out.println(converterOfFun.toBinary(103));
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



    public String toBinary(int numberToChange){

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
        return convertToString(result);
    }

    public List<String> reverse(List<String> data){
        List<String> result = new ArrayList<>();
        for (int i = data.size(); i > 0; i--){
            result.add((data.get(i-1)));
        }
        return result;
    }

    protected String convertToString(List<String> data){

        StringBuilder result = new StringBuilder();
        for (String s:data){
            result.append(s);
            result.append(" ");
        }

        return result.toString();
    }

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
        for (int i : ints){
            result.add(toBinary(i));
        }
        return convertToString(result);
    }

    public String removeSpaces(String toRemove){
        // TODO: make this a thing...
        String result = toRemove.replaceAll("  ", "-");
        result = result.replaceAll(" ", "");
        result = result.replaceAll("-", " ");

        return result;
    }

}
