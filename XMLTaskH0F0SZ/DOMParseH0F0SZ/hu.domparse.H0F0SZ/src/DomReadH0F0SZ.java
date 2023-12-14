import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
        Document newdoc = dBuilder.newDocument();

        doc.getDocumentElement().normalize();

        //Gyökérelem megadás
        Element root = newdoc.createElementNS("XMLH0F0SZ", "bajnoksag");
        newdoc.appendChild(root);

        //Új XML elemeinek a feltöltése
        root.appendChild(createStatisztika(newdoc, "stat1","300", "10", "0", "4", "10","l1"));
        root.appendChild(createStatisztika(newdoc, "stat2","200", "11", "1", "0", "2","l2"));
        root.appendChild(createStatisztika(newdoc, "stat3","400", "0", "0", "0", "2","l3"));
        root.appendChild(createLabdarugo(newdoc, "l1","Callum Styles", "25", "CM", "cs1"));
        root.appendChild(createLabdarugo(newdoc, "l2","Sallai Roland", "25", "ST", "cs2"));
        root.appendChild(createLabdarugo(newdoc, "l3","Szoboszlai Dominik", "21", "CAM", "cs3"));
        root.appendChild(createCsapat(newdoc, "cs1","Millwall", "Kiss Pista", new String[]{"Premier Leauge","Europe Leauge"}, "s1"));
        root.appendChild(createCsapat(newdoc, "cs2","Freiburg", "Nagy Janos", new String[]{"Premier Leauge","Europe Leauge"}, "s2"));
        root.appendChild(createCsapat(newdoc, "cs3","Leipzig", "Kerek Elemer", new String[]{"Premier Leauge","Europe Leauge"}, "s3"));
        root.appendChild(createMerkozes(newdoc, "m1","0", "2", "17:00", "s1"));
        root.appendChild(createMerkozes(newdoc, "m2","1", "1", "18:00", "s2"));
        root.appendChild(createMerkozes(newdoc, "m3","3", "2", "20:00", "s3"));
        root.appendChild(createStadion(newdoc, "s1","Emirates Stadium", "London", "Eper", "12", "60000"));
        root.appendChild(createStadion(newdoc, "s2","London Stadium", "London", "Malna", "12", "60000"));
        root.appendChild(createStadion(newdoc, "s3","Etihad Stadium", "Manchester", "Szilva", "12", "60000"));
        root.appendChild(createJatszik(newdoc, "cs1","m1", "0"));
        root.appendChild(createJatszik(newdoc, "cs2","m2", "1"));
        root.appendChild(createJatszik(newdoc, "cs3","m3", "3"));




        //Transzformáció
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

        //Fájl létrehozása
        DOMSource source = new DOMSource(newdoc);
        File myFile = new File("XMLH0F0SZ1.xml");

        //Kiiratás
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        //transf.transform(source, console);
        transf.transform(source, file);


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

    //Segéd függyvények az XML fájl íráshoz
    private static Node createStatisztika(Document newdoc, String id, String jatekperc, String sarga, String piros, String gol, String golpassz, String fid) {

        Element node = newdoc.createElement("statisztika");

        node.setAttribute("statisztikaID", id);
        node.setAttribute("l_stat", fid);
        node.appendChild(createElement(newdoc,"jatekperc", jatekperc));

        Node node1 = createElement(newdoc, "sarga", sarga);
        Node node2 = createElement(newdoc, "piros", piros);

        Element cards = newdoc.createElement("lapok");
        cards.appendChild(node1);
        cards.appendChild(node2);
        node.appendChild(cards);

        node.appendChild(createElement(newdoc,"gol", gol));
        node.appendChild(createElement(newdoc,"golpassz", golpassz));

        return node;
    }

    private static Node createLabdarugo(Document newdoc, String id, String lnev, String kor, String poszt, String fid) {

        Element node = newdoc.createElement("labdarugo");

        node.setAttribute("labdarugoID", id);
        node.setAttribute("l_stat", fid);
        node.appendChild(createElement(newdoc,"lnev", lnev));
        node.appendChild(createElement(newdoc,"kor",kor));

        node.appendChild(createElement(newdoc,"poszt", poszt));

        return node;
    }

    private static Node createCsapat(Document newdoc, String id, String csnev, String edzo, String[] ligak, String fid) {

        Element node = newdoc.createElement("csapat");

        node.setAttribute("csapatID", id);
        node.setAttribute("l_stat", fid);
        node.appendChild(createElement(newdoc,"csnev", csnev));
        node.appendChild(createElement(newdoc,"edzo",edzo));
        for (String liga:ligak) {
            node.appendChild(createElement(newdoc,"liga",liga));
        }

        return node;
    }

    private static Node createMerkozes(Document newdoc, String id, String hazaigolok, String vendeggolok, String idopont, String fid) {

        Element node = newdoc.createElement("merkozes");

        node.setAttribute("merkozesID", id);
        node.setAttribute("stad_m", fid);
        node.appendChild(createElement(newdoc,"hazaigolok", hazaigolok));
        node.appendChild(createElement(newdoc,"vendeggolok",vendeggolok));
        node.appendChild(createElement(newdoc,"idopont",idopont));


        return node;
    }

    private static Node createStadion(Document newdoc, String id, String snev, String varos, String utca, String hazszam, String ferohely) {

        Element node = newdoc.createElement("stadion");

        node.setAttribute("stadionID", id);
        node.appendChild(createElement(newdoc,"snev", snev));

        Node node1 = createElement(newdoc, "varos", varos);
        Node node2 = createElement(newdoc, "utca", utca);
        Node node3 = createElement(newdoc, "hazszam", hazszam);

        Element address = newdoc.createElement("cim");
        address.appendChild(node1);
        address.appendChild(node2);
        address.appendChild(node3);
        node.appendChild(address);

        node.appendChild(createElement(newdoc,"ferohely",ferohely));

        return node;
    }

    private static Node createJatszik(Document newdoc, String fid2, String fid1, String pont) {

        Element node = newdoc.createElement("jatszik");

        node.setAttribute("cs_j", fid1);
        node.setAttribute("m_j", fid2);
        node.appendChild(createElement(newdoc,"pont", pont));


        return node;
    }

    private static Node createElement(Document newdoc, String name, String value) {

        Element node = newdoc.createElement(name);
        node.appendChild(newdoc.createTextNode(value));

        return node;

    }


}