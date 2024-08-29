import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *  This is a class to convert markdown to Html. Run this class, then input text, the class will print html.
 * */
public class HtmlConverter {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Please input markdown");
        HtmlConverter converter = new HtmlConverter();
        String inputString = scan.next();
        while(!inputString.equals("stop")){
            System.out.println(converter.convert(inputString));
            System.out.println("\nPlease input markdown");
            inputString = scan.next();
        }

        scan.close();
    }


    public String convertStrings(String s){
        String[] ss = s.split("\n");
        List<String> convertedS = Arrays.stream(ss)
                .map(this::convert).collect(Collectors.toList());
        return String.join("\n", convertedS);
    }

    public String convert(String markdown){
        if(markdown == null ){
            return "";
        }
        if(markdown.isBlank()){
            return markdown;
        }

         markdown = markdown.trim();
         markdown = convertHeader(markdown);
         markdown = convertLink(markdown);
         markdown = convertParagraphs(markdown);

        return markdown;
    }

    public String convertHeader(String markdown){
        if(markdown.startsWith("######")){
            return convertHeader(markdown,"(?m)^######\s*(.+)$", "<h6>$1</h6>");
        }else if(markdown.startsWith("#####")){
            return convertHeader(markdown,"(?m)^#####\s*(.+)$", "<h5>$1</h5>");
        }else if(markdown.startsWith("####")) {
            return convertHeader(markdown, "(?m)^####\s*(.+)$", "<h4>$1</h4>");
        }else if(markdown.startsWith("###")) {
            return convertHeader(markdown, "(?m)^###\s*(.+)$", "<h3>$1</h3>");
        }else if(markdown.startsWith("##")) {
            return convertHeader(markdown, "(?m)^##\s*(.+)$", "<h2>$1</h2>");
        }else if(markdown.startsWith("#")) {
            return convertHeader(markdown, "(?m)^#\s*(.+)$", "<h1>$1</h1>");
        }
       return markdown;
    }

    public String convertHeader(String markdown, String reg, String htmlFormat){
        markdown = markdown.replaceAll(reg, htmlFormat);
        return markdown;
    }

    public String convertLink(String markdown){
        markdown = markdown.replaceAll("\\[(.+?)\\]\\((.+?)\\)", "<a href=\"$2\">$1</a>");
        return markdown;
    }

    public String convertParagraphs(String markdown){
        markdown = markdown.replaceAll("(?m)(^[^<].+)$", "<p>$1</p>");
        return markdown;
    }

}
