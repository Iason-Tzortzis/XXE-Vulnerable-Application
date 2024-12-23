package xxe_vulnerable_app;

import xxe_vulnerable_app.repo.bookRepo;
import xxe_vulnerable_app.domain.bookDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class XmlProcessorSax {

    @Autowired
    bookRepo bookRepo;

    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser saxParser;
    private DefaultHandler handler;
    private List<String> messages = new ArrayList<>();




    public XmlProcessorSax() {
        try {

            saxParser = factory.newSAXParser();
            handler = new DefaultHandler() {

                boolean books = false;
                boolean btitle = false;
                boolean btype = false;
                boolean bprice = false;
                boolean bdescription = false;

                String title = "";
                String type = "";
                String price = "";
                String description = "";

                Map<String, String> values = new HashMap<>();

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("BOOKS")) books = true;
                    if (qName.equalsIgnoreCase("TITLE")) btitle = true;
                    if (qName.equalsIgnoreCase("TYPE")) btype = true;
                    if (qName.equalsIgnoreCase("PRICE")) bprice = true;
                    if (qName.equalsIgnoreCase("DESCRIPTION")) bdescription = true;
                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (btitle && books) {
                        title += new String(ch, start, length);
                    }

                    if (btype && books) {
                        type += new String(ch, start, length);
                    }

                    if (bprice && books) {
                        price += new String(ch, start, length);
                    }

                    if (bdescription && books) {
                        description += new String(ch, start, length);
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("TITLE")) btitle = false;
                    if (qName.equalsIgnoreCase("TYPE")) btype = false;
                    if (qName.equalsIgnoreCase("PRICE")) bprice = false;
                    if (qName.equalsIgnoreCase("DESCRIPTION")) bdescription = false;



                    if (qName.equalsIgnoreCase("BOOKS")) {
                        bookDomain newBook = new bookDomain(title, type, price, description);
                        bookRepo.save(newBook);
                        messages.add("Successful upload");
                        reset();
                    }

                }

                private void reset() {
                    boolean books = false;
                    boolean btitle = false;
                    boolean btype = false;
                    boolean bprice = false;
                    boolean bdescription = false;

                    String title = "";
                    String type = "";
                    String price = "";
                    String description = "";
                }
            };
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException("Upload error occurred", e);
        }
    }

    public List<String> parseXML(InputStream f) {
        messages.clear();
        try {
            saxParser.parse(f,handler);
        } catch (SAXException | IOException e) {
            messages.add(e.getMessage());
            throw new RuntimeException("Upload error occurred", e);
        }
        return messages;
    }

}
