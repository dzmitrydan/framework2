import org.apache.commons.lang3.StringUtils;

public class Main2 {
    public static void main(String[] args) {

        String s1 = "VM class: regular";
        String s2 = "Instance type: n1-standard-8";
        String s3 = "Region: Frankfurt";
        String s4 = "Total available local SSD space 2x375 GiB";
        String s5 = "Commitment term: 1 Year";
        String s6 = "Region: Frankfurt (europe-west3)";
        String s7 = "regular";


        System.out.println(stringExtractorColon(s1));
        System.out.println(stringExtractorColon(s2));
        System.out.println(stringExtractorColon(s3));
        System.out.println(stringExtractorColon(s4));
        System.out.println(stringExtractorColon(s5));
        System.out.println(stringExtractorColon(stringExtractorBrackets(s6)));
        System.out.println(stringCapitalize(s7));


    }

    public static String stringExtractorColon(String string){
        String[] words = string.split(":");
        return words[words.length-1].trim();
    }

    public static String stringExtractorBrackets(String string){
        return string.replaceAll("\\([^()]*\\)", "").trim();
    }

    public static String stringCapitalize(String string) {
        return StringUtils.capitalize(string);
    }
}
