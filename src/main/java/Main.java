public class Main {
    public static void main(String[] args) {

        String n1 = "USD 1,098.30";
        String n2 = "Total Estimated Cost: USD 1,098.30 per 1 month";

        //System.out.println(stringToDouble(n1));
        //System.out.println(stringToDouble(n2));
        System.out.println(stringToDouble(n2) == stringToDouble(n1));


    }

    public static double stringToDouble(String number){
        System.out.println(number);
        String string = number.replaceAll("( per.+month)", "");
        string.replaceAll("[^0-9.]", "");

        /*
        StringBuilder sb = new StringBuilder(number);
        Pattern p = Pattern.compile("[^0-9.]");
        Matcher m = p.matcher(sb);
        m.replaceAll("");

*/


        return Double.parseDouble(string.replaceAll("[^0-9.]", ""));
    }


}



