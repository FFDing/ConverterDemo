/**
 *  This is a test class to test all functions in class HtmlConverter
 * */
public class HtmlConverterTest {
    public static void main(String[] args){
        testConvertHeader();
        testConvertLink();
        testConvertParagraph();
        testConvert();
    }

    public static void testConvertHeader(){
        HtmlConverter converter = new HtmlConverter();
        String htmlString1 = converter.convertHeader("#test");
        System.out.println(htmlString1.equals("<h1>test</h1>") ? "h1: passed" :"h2: failed");

        String htmlString2 = converter.convertHeader("##test");
        System.out.println(htmlString2.equals("<h2>test</h2>") ? "h2: passed" :"h2: failed");

        String htmlString3 = converter.convertHeader("###test");
        System.out.println(htmlString3.equals("<h3>test</h3>") ? "h3: passed" :"h3: failed");

        String htmlString4 = converter.convertHeader("####test");
        System.out.println(htmlString4.equals("<h4>test</h4>") ? "h4: passed" :"h4: failed");

        String htmlString5 = converter.convertHeader("#####test");
        System.out.println(htmlString5.equals("<h5>test</h5>") ? "h5: passed" :"h5: failed");

        String htmlString6 = converter.convertHeader("######test");
        System.out.println(htmlString6.equals("<h6>test</h6>") ? "h6: passed" :"h6: failed");
    }

    public static void testConvertLink(){
        HtmlConverter converter = new HtmlConverter();
        String htmlString = converter.convertLink("[Link text](https://www.example.com)");
        System.out.println(htmlString.equals("<a href=\"https://www.example.com\">Link text</a>") ? "Link: passed" :"Link: failed");
    }

    public static void testConvertParagraph(){
        HtmlConverter converter = new HtmlConverter();
        String htmlString = converter.convertParagraphs("test");
        System.out.println(htmlString.equals("<p>test</p>") ? "Paragraph: passed" :"Paragraph: failed");
    }

    public static void testConvert(){
        HtmlConverter converter = new HtmlConverter();
        String htmlString = converter.convert("This is sample markdown for the [Mailchimp](https://www.mailchimp.com) homework assignment.");
        System.out.println(htmlString.equals("<p>This is sample markdown for the <a href=\"https://www.mailchimp.com\">Mailchimp</a> homework assignment.</p>") ? "Convert: passed" :"Convert: failed");
    }
}
