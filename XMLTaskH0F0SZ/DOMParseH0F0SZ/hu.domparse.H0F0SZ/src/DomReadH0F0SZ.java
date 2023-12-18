import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.File;
import java.io.IOException;

public class DomReadH0F0SZ {
    public static void main(String[] args) throws SAXException,
            IOException, ParserConfigurationException, TransformerException {
        //File megnyitás
        File xmlFile = new File("XMLH0F0SZ.xml");

        //Builderek létrehozása
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        //Új és meglévő dokumnetum kibálasztás
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        //Transzformáció
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

        //Gyökér elem kiírás
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());


        //Adatok kigyűjtése az XML-ből
        NodeList statList = doc.getElementsByTagName("statisztika");
        for(int i = 0; i < statList.getLength(); i++) {
        Node nNode = statList.item(i);

        System.out.println("\nCurrent Element: " + nNode.getNodeName());

        if( nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element elem = (Element) nNode;

            String sid = elem.getAttribute("statisztikaID");
            String lstatid = elem.getAttribute("l_stat");

            Node node1 = elem.getElementsByTagName("jatekperc").item(0);
            String mins = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("sarga").item(0);
            Node node3 = elem.getElementsByTagName("piros").item(0);
            String yellow =node2.getTextContent();
            String red = node3.getTextContent();

            Node node4 = elem.getElementsByTagName("gol").item(0);
            String goals = node4.getTextContent();

            Node node5 = elem.getElementsByTagName("golpassz").item(0);
            String assists = node5.getTextContent();


            System.out.println("Statistics id: " + sid);
            System.out.println("Played minutes: " + mins);
            System.out.println("Yellow cards: " + yellow);
            System.out.println("Red cards: " + red);
            System.out.println("Goals: " + goals);
            System.out.println("Assits: " + assists);
            System.out.println("Foreign key to football player: " + lstatid);
        }
        }
        NodeList lList = doc.getElementsByTagName("labdarugo");


        for(int i = 0; i < lList.getLength(); i++) {
        Node nNode = lList.item(i);

        System.out.println("\nCurrent Element: " + nNode.getNodeName());

        if( nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element elem = (Element) nNode;

            String lid = elem.getAttribute("labdarugoID");
            String cslid = elem.getAttribute("cs_l");

            Node node1 = elem.getElementsByTagName("lnev").item(0);
            String lname = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("kor").item(0);
            String age =node2.getTextContent();

            Node node3 = elem.getElementsByTagName("poszt").item(0);
            String pos = node3.getTextContent();


            System.out.println("Football player id: " + lid);
            System.out.println("Name: " + lname);
            System.out.println("Age: " + age);
            System.out.println("Position: " + pos);
            System.out.println("Foreign key to team: " + cslid);
        }
    }
        NodeList csList = doc.getElementsByTagName("csapat");


        for(int i = 0; i < csList.getLength(); i++) {
        Node nNode = csList.item(i);

        System.out.println("\nCurrent Element: " + nNode.getNodeName());

        if( nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element elem = (Element) nNode;

            String csid = elem.getAttribute("csapatID");
            String stadcsid = elem.getAttribute("stad_cs");

            Node node1 = elem.getElementsByTagName("csnev").item(0);
            String csname = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("edzo").item(0);
            String manager =node2.getTextContent();

            System.out.println("Team id: " + csid);
            System.out.println("Name: " + csname);
            System.out.println("Manager: " + manager);

            for (int j = 0; j < elem.getElementsByTagName("liga").getLength(); j++) {
                Node node3 = elem.getElementsByTagName("liga").item(j);
                System.out.println("Leauge:" + node3.getTextContent());
            }

            System.out.println("Foreign key to stadium: " + stadcsid);
        }
    }
        NodeList mList = doc.getElementsByTagName("merkozes");


        for(int i = 0; i < mList.getLength(); i++) {
        Node nNode = mList.item(i);

        System.out.println("\nCurrent Element: " + nNode.getNodeName());

        if( nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element elem = (Element) nNode;

            String mid = elem.getAttribute("merkozesID");
            String stadmid = elem.getAttribute("stad_m");

            Node node1 = elem.getElementsByTagName("hazaigolok").item(0);
            String hgoals = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("vendeggolok").item(0);
            String agoals =node2.getTextContent();

            Node node3 = elem.getElementsByTagName("idopont").item(0);
            String time = node3.getTextContent();


            System.out.println("Match id: " + mid);
            System.out.println("Home goals: " + hgoals);
            System.out.println("Away goals: " + agoals);
            System.out.println("Start time: " + time);
            System.out.println("Foreign key to stadium: " + stadmid);
        }
    }
        NodeList stadList = doc.getElementsByTagName("stadion");


        for(int i = 0; i < stadList.getLength(); i++) {
        Node nNode = stadList.item(i);

        System.out.println("\nCurrent Element: " + nNode.getNodeName());

        if( nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element elem = (Element) nNode;

            String sid = elem.getAttribute("stadionID");

            Node node1 = elem.getElementsByTagName("snev").item(0);
            String sname = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("varos").item(0);
            String city =node2.getTextContent();

            Node node3 = elem.getElementsByTagName("utca").item(0);
            String street = node3.getTextContent();

            Node node4 = elem.getElementsByTagName("hazszam").item(0);
            String hnumber = node4.getTextContent();

            Node node5 = elem.getElementsByTagName("ferohely").item(0);
            String cap = node5.getTextContent();


            System.out.println("Game id: " + sid);
            System.out.println("Name: " + sname);
            System.out.println("City: " + city);
            System.out.println("Street: " + street);
            System.out.println("House Number: " + hnumber);
            System.out.println("Capacity: " + cap);
        }
    }
        NodeList jList = doc.getElementsByTagName("jatszik");


        for(int i = 0; i < jList.getLength(); i++) {
        Node nNode = jList.item(i);

        System.out.println("\nCurrent Element: " + nNode.getNodeName());

        if( nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element elem = (Element) nNode;

            String csjid = elem.getAttribute("cs_j");
            String mjid = elem.getAttribute("m_j");

            Node node1 = elem.getElementsByTagName("pont").item(0);
            String point = node1.getTextContent();

            System.out.println("Team id: " + csjid);
            System.out.println("Game id: " + mjid);
            System.out.println("Earned point(s): " + point);
        }
        }
    }

}