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
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document =documentBuilder.parse("XMLH0F0SZ.xml");

            document.getDocumentElement().normalize();

         // 1) Labdarugok neveinek lekerdezese
            NodeList labdarugoList = document.getElementsByTagName("labdarugo");
            System.out.println("Labdarugok nevei:");
            for (int i = 0; i < labdarugoList.getLength(); i++) {
                Node node = labdarugoList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element labdarugo = (Element) node;
                    System.out.println(labdarugo.getElementsByTagName("lnev").item(0).getTextContent());
                }
            }
            
            System.out.println();
         // 2)  l2 ID-ju Labdarugo jatszott perceinek a lekerdezese
            String labdarugoIdToQuery = "l2";
            NodeList statisztikaList = document.getElementsByTagName("statisztika");
            for (int i = 0; i < statisztikaList.getLength(); i++) {
                Node node = statisztikaList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element statisztika = (Element) node;
                    if (statisztika.getAttribute("l_stat").equals(labdarugoIdToQuery)) {
                        System.out.println("Az l2 ID-ju labdarugo jatszott percei: " +
                        		statisztika.getElementsByTagName("jatekperc").item(0).getTextContent());
                        break;
                    }
                }
            }

            System.out.println();
         // 3) Csapatok lekerdezese
            NodeList csapatList = document.getElementsByTagName("csapat");
            System.out.println("Rendszerben szereplő csapatok:");
            for (int i = 0; i < csapatList.getLength(); i++) {
                Node node = csapatList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element csapat = (Element) node;
                    System.out.println(csapat.getElementsByTagName("csnev").item(0).getTextContent());
                }
            }
            
            System.out.println();
         // 4) Stadionok es ferohelyeik szama
            NodeList stadionList = document.getElementsByTagName("stadion");
            System.out.println("Stadionok és ferohelyeik szama:");
            for (int i = 0; i < stadionList.getLength(); i++) {
                Node node = stadionList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element stadion = (Element) node;
                    System.out.println(
                    		stadion.getElementsByTagName("snev").item(0).getTextContent()
                    		+" Ferohely: "+
                    		stadion.getElementsByTagName("ferohely").item(0).getTextContent()
                    		);
                }
            }
            
            System.out.println();
         // 5) merkozesek idelyenek lekerdezese
            NodeList merkozesList = document.getElementsByTagName("merkozes");
            System.out.println("A merkozesek idopontjai:");
            for (int i = 0; i < merkozesList.getLength(); i++) {
                Node node = merkozesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element merkozes = (Element) node;
                    System.out.println(
                    		merkozes.getElementsByTagName("idopont").item(0).getTextContent()
                    		);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
