import java.util.Scanner;

/**
 *  This is a class to convert markdown to Html. Run this class, then input text, the class will print html.
 * */
public class HtmlConverter {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in).useDelimiter("\n");;
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

    public String convert(String markdown){
        if(markdown == null || markdown.isBlank()){
            return "";
        }

         markdown = convertHeader(markdown);
         markdown = convertLink(markdown);
         markdown = convertParagraphs(markdown);

        return markdown;
    }

    public String convertHeader(String markdown){
        if(markdown.startsWith("######")){
            return convertHeader(markdown,"(?m)^######(.+)$", "<h6>$1</h6>");
        }else if(markdown.startsWith("#####")){
            return convertHeader(markdown,"(?m)^#####(.+)$", "<h5>$1</h5>");
        }else if(markdown.startsWith("####")) {
            return convertHeader(markdown, "(?m)^####(.+)$", "<h4>$1</h4>");
        }else if(markdown.startsWith("###")) {
            return convertHeader(markdown, "(?m)^###(.+)$", "<h3>$1</h3>");
        }else if(markdown.startsWith("##")) {
            return convertHeader(markdown, "(?m)^##(.+)$", "<h2>$1</h2>");
        }else if(markdown.startsWith("#")) {
            return convertHeader(markdown, "(?m)^#(.+)$", "<h1>$1</h1>");
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
