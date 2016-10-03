package Super_Trump;
//import com.sun.org.apache.xerces.internal.parsers.DOMParser;
//import org.w3c.dom.Document;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
///**
// * Created by jc319816 on 3/10/16.
// */
//public class STReadXML {
//
//
//
//    try {
//        DOMParser parser = new DOMParser();
//        parser.parse("MstCards_151021.plist");
//        Document doc = parser.getDocument();
//
//        // Get the document's root XML node
//        NodeList root = doc.getChildNodes();
//
//        // Navigate down the hierarchy to get to the CEO node
//        Node comp = getNode("fileName", root);
//        Node exec = getNode("cardType", comp.getChildNodes() );
//        String execType = getNodeAttr("value", exec);
//
//        // Load the executive's data from the XML
//        NodeList nodes = exec.getChildNodes();
//        String lastName = getNodeValue("LastName", nodes);
//        String firstName = getNodeValue("FirstName", nodes);
//        String street = getNodeValue("street", nodes);
//        String city = getNodeValue("city", nodes);
//        String state = getNodeValue("state", nodes);
//        String zip = getNodeValue("zip", nodes);
//
//        System.out.println("Executive Information:");
//        System.out.println("Type: " + execType);
//        System.out.println(lastName + ", " + firstName);
//        System.out.println(street);
//        System.out.println(city + ", " + state + " " + zip);
//    } catch ( Exception e ) {
//        e.printStackTrace();
//    }
//
//// ...
//
//    protected Node getNode(String tagName, NodeList nodes) {
//        for ( int x = 0; x < nodes.getLength(); x++ ) {
//            Node node = nodes.item(x);
//            if (node.getNodeName().equalsIgnoreCase(tagName)) {
//                return node;
//            }
//        }
//
//        return null;
//    }
//
//    protected String getNodeValue( Node node ) {
//        NodeList childNodes = node.getChildNodes();
//        for (int x = 0; x < childNodes.getLength(); x++ ) {
//            Node data = childNodes.item(x);
//            if ( data.getNodeType() == Node.TEXT_NODE )
//                return data.getNodeValue();
//        }
//        return "";
//    }
//
//    protected String getNodeValue(String tagName, NodeList nodes ) {
//        for ( int x = 0; x < nodes.getLength(); x++ ) {
//            Node node = nodes.item(x);
//            if (node.getNodeName().equalsIgnoreCase(tagName)) {
//                NodeList childNodes = node.getChildNodes();
//                for (int y = 0; y < childNodes.getLength(); y++ ) {
//                    Node data = childNodes.item(y);
//                    if ( data.getNodeType() == Node.TEXT_NODE )
//                        return data.getNodeValue();
//                }
//            }
//        }
//        return "";
//    }
//
//    protected String getNodeAttr(String attrName, Node node ) {
//        NamedNodeMap attrs = node.getAttributes();
//        for (int y = 0; y < attrs.getLength(); y++ ) {
//            Node attr = attrs.item(y);
//            if (attr.getNodeName().equalsIgnoreCase(attrName)) {
//                return attr.getNodeValue();
//            }
//        }
//        return "";
//    }
//
//    protected String getNodeAttr(String tagName, String attrName, NodeList nodes ) {
//        for ( int x = 0; x < nodes.getLength(); x++ ) {
//            Node node = nodes.item(x);
//            if (node.getNodeName().equalsIgnoreCase(tagName)) {
//                NodeList childNodes = node.getChildNodes();
//                for (int y = 0; y < childNodes.getLength(); y++ ) {
//                    Node data = childNodes.item(y);
//                    if ( data.getNodeType() == Node.ATTRIBUTE_NODE ) {
//                        if ( data.getNodeName().equalsIgnoreCase(attrName) )
//                            return data.getNodeValue();
//                    }
//                }
//            }
//        }
//
//        return "";
//    }
//
//}

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class STReadXML {

    public static void main(String argv[]) {

        try {

            File fXmlFile = new File("MstCards_151021.plist");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("fileName");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Staff id : " + eElement.getAttribute("cardType"));
                    System.out.println("First Name : " + eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("chemistry").item(0).getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName("classification").item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName("crystal_system").item(0).getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
