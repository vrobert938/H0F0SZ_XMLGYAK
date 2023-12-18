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

public class DomWriteH0F0SZ {
    public static void main(String[] args) throws SAXException,
            IOException, ParserConfigurationException, TransformerException {

        //Builderek létrehozása
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        //Új dokumnetum kiválasztás
        Document newdoc = dBuilder.newDocument();

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
        StreamResult file = new StreamResult(myFile);

        //transf.transform(source, console);
        transf.transform(source, file);
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