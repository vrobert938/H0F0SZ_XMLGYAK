import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryH0F0SZ {
    public static void main(String[] args) {

        try {
            File xmlFile = new File("studentH0F0SZ.xml");

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document =documentBuilder.parse("XMLH0F0SZ.xml");

            document.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();


            List<String> expressions = new ArrayList<>();

            //A statisztikák kiválasztása
            //String expression = "/bajnoksag/statisztika";
            expressions.add("/bajnoksag/statisztika");

            //A csapat első elemének a kiválasztása
            //String expression = "/bajnoksag/csapat[1]";
            expressions.add("/bajnoksag/csapat[1]");

            //Az össze elem kiválasztása a dokumentumból
            //String expression = "//*";
            expressions.add("//*");

            //A mérkzések időpontjainak a kiválasztása
            //String expression = "//idopont";
            expressions.add("//idopont");

            //A 40000-nél nagyobb férőhelyű stadionok kiválasztása
            //String expression = "//stadion[ferohely>40000]";
            expressions.add("//stadion[ferohely>40000]");

            // Hazai és vendég gólok számának lekérdezése
            //String expression = "//hazaigolok | //vendeggolok";
            expressions.add("//hazaigolok | //vendeggolok");

            for (String expression:expressions) {


                NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

                System.out.println("-----------------------");
                System.out.println("Query output:");
                System.out.println("-----------------------");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);

                    System.out.println("\nAktuális elem: " + node.getNodeName());

                    if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("statisztika")) {
                        Element element = (Element) node;

                        System.out.println("Statisztika ID:" + element.getAttribute("statisztikaID"));

                        System.out.println("Idegen kulcs:" + element.getAttribute("l_stat"));

                        System.out.println("Játékperc: " + element.getElementsByTagName("jatekperc").item(0).getTextContent());

                        System.out.println("Sárga lapok: " + element.getElementsByTagName("sarga").item(0).getTextContent());

                        System.out.println("Piros lapok: " + element.getElementsByTagName("piros").item(0).getTextContent());

                        System.out.println("Gólok: " + element.getElementsByTagName("gol").item(0).getTextContent());

                        System.out.println("Gólpasszok: " + element.getElementsByTagName("golpassz").item(0).getTextContent());
                    }

                    if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("labdarugo")) {
                        Element element = (Element) node;

                        System.out.println("Labdarúgó ID:" + element.getAttribute("labdarugoID"));

                        System.out.println("Idegen kulcs:" + element.getAttribute("cs_l"));

                        System.out.println("Név: " + element.getElementsByTagName("lnev").item(0).getTextContent());

                        System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());

                        System.out.println("Poszt: " + element.getElementsByTagName("poszt").item(0).getTextContent());
                    }

                    if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("csapat")) {
                        Element element = (Element) node;

                        System.out.println("Csapat ID:" + element.getAttribute("csapatID"));

                        System.out.println("Idegen kulcs:" + element.getAttribute("stad_cs"));

                        System.out.println("Név: " + element.getElementsByTagName("csnev").item(0).getTextContent());

                        System.out.println("Edző: " + element.getElementsByTagName("edzo").item(0).getTextContent());

                        if (nodeList.item(i).getChildNodes().getLength() > 2) {
                            int db = 0;
                            Node liga = element.getElementsByTagName("liga").item(0);
                            while (liga != null) {
                                liga = element.getElementsByTagName("liga").item(db);
                                if (liga != null) {
                                    String liganev = liga.getTextContent();
                                    System.out.println("Liga amelyben játszik a csapat: " + liganev);
                                }
                                db++;
                            }
                        }
                    }

                    if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("merkozes")) {
                        Element element = (Element) node;

                        System.out.println("Mérkőzés ID:" + element.getAttribute("merkozesID"));

                        System.out.println("Idegen kulcs:" + element.getAttribute("stad_m"));

                        System.out.println("Hazai gólok száma: " + element.getElementsByTagName("hazaigolok").item(0).getTextContent());

                        System.out.println("Vendég gólok száma: " + element.getElementsByTagName("vendeggolok").item(0).getTextContent());

                        System.out.println("Időpont: " + element.getElementsByTagName("idopont").item(0).getTextContent());
                    }

                    if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("stadion")) {
                        Element element = (Element) node;

                        System.out.println("Stadion ID:" + element.getAttribute("stadionID"));

                        System.out.println("Név: " + element.getElementsByTagName("snev").item(0).getTextContent());

                        System.out.println("Cím: " + element.getElementsByTagName("varos").item(0).getTextContent()
                                + ", " + element.getElementsByTagName("utca").item(0).getTextContent()
                                + " " + element.getElementsByTagName("hazszam").item(0).getTextContent());

                        System.out.println("Férőhely: " + element.getElementsByTagName("ferohely").item(0).getTextContent());
                    }

                    if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("jatszik")) {
                        Element element = (Element) node;

                        System.out.println("Csapatra mutató idegen kulcs:" + element.getAttribute("cs_j"));

                        System.out.println("Mérkőzésre mutató idegen kulcs:" + element.getAttribute("m_j"));

                        System.out.println("Pont: " + element.getElementsByTagName("pont").item(0).getTextContent());
                    }

                    // Mérkőzés időpontjának kiíratása
                    if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("idopont")) {

                        Element element = (Element) node;

                        System.out.println("Idopont: " + element.getTextContent());

                    }

                    // Hazai gólok számának kiiratása
                    if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("hazaigolok")) {

                        Element element = (Element) node;

                        System.out.println("Hazai gólok száma: " + element.getTextContent());

                    }

                    // Vendég gólok számának kiiratása
                    if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("vendeggolok")) {

                        Element element = (Element) node;

                        System.out.println("Vendég gólok száma: " + element.getTextContent());

                    }
                }
                System.out.println("-----------------------");
                System.out.println("End of the query");
                System.out.println("-----------------------");
            }
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (XPathExpressionException e)
        {
            e.printStackTrace();
        }

    }
}
