import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class DomModifyH0F0SZ {
    public static void main(String argv[]) {

        try {

            File inputFile = new File("XMLH0F0SZ.xml");

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document doc = documentBuilder.parse(inputFile);

            // statisztika módosítása
            Node stat = doc.getElementsByTagName("statisztika").item(0);
            NodeList statList = stat.getChildNodes();

            for (int i = 0; i < statList.getLength(); i++) {
                Node node = statList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    if ("jatekperc".equals(eElement.getNodeName())) {
                        eElement.setTextContent("300");
                    }
                }
            }

            //stadion módosítása
            Node stad = doc.getElementsByTagName("stadion").item(0);
            NodeList stadlist = stad.getChildNodes();

            for (int i = 0; i < stadlist.getLength(); i++) {
                Node node = stadlist.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if ("ferohely".equals(eElement.getNodeName())) {
                        if ("65000".equals(eElement.getTextContent())) {
                            eElement.setTextContent("60000");
                        }
                        if ("39000".equals(eElement.getTextContent())) {
                            eElement.setTextContent("40000");
                        }
                    }
                }
            }

            //merkozes módosítása
            Node merk = doc.getElementsByTagName("merkozes").item(0);
            NodeList merklist = merk.getChildNodes();

            for (int i = 0; i < merklist.getLength(); i++) {
                Node node = merklist.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if ("hazaigolok".equals(eElement.getNodeName())) {
                        if ("3".equals(eElement.getTextContent())) {
                            eElement.setTextContent("2");
                        }
                    }
                    if ("vendeggolok".equals(eElement.getNodeName())) {
                        if ("1".equals(eElement.getTextContent())) {
                            eElement.setTextContent("0");
                        }
                    }
                }
            }

            // Tartalom konzolra és fájlba való írása

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);

            System.out.println("-----Módosított fájl-----");

            StreamResult consoleResult = new StreamResult(System.out);
            StreamResult file = new StreamResult(inputFile);

            transformer.transform(source, consoleResult);
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
