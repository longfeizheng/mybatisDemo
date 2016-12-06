package test.mybatis.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import test.mybatis.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

/**
 * Created on 2016/12/6 0006.
 *
 * @author zlf
 * @since 1.0
 */
public class XpathDemo {
    private static Document document;
    private static XPath xPath;

    public static void main(String[] args) throws Exception {
        init();
        getRootEle();
        getChildEles();
        getPartEles();
        haveChildsEles();
        getAttrEles();

        //打印根节点下的所有元素节点
        System.out.println(document.getDocumentElement().getChildNodes().getLength());
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                System.out.print(nodeList.item(i).getNodeName() + " ");
            }
        }
    }

    //初始化Document,XPath对象
    public static void init() throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setValidating(false);
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        document = builder.parse(ResourceLoader.class.getClassLoader().getResourceAsStream("test/mybatis/book.xml"));

        //创建XPath对象
        XPathFactory factory = XPathFactory.newInstance();
        xPath = factory.newXPath();
    }

    //获取根元素
    //表达式可以更换为/* /book
    public static void getRootEle() throws Exception {
//        Node node = (Node) xPath.evaluate("/book", document, XPathConstants.NODE);
        Node node = (Node) xPath.evaluate("/*", document, XPathConstants.NODE);
        System.out.println(node.getNodeName() + "---------------" + node.getNodeValue());
    }

    //获取子元素并打印
    public static void getChildEles() throws Exception {
        NodeList nodeList = (NodeList) xPath.evaluate("book/authors/author", document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getNodeName() + " ");
        }
        System.out.println();
    }

    //获取部分元素
    //获取元素名称为author的元素
    public static void getPartEles() throws Exception {
        NodeList nodeList = (NodeList) xPath.evaluate("//*[name() = 'price']", document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent());
        }
        System.out.println();
    }

    // 获取包含子节点的元素
    public static void haveChildsEles() throws Exception {
        NodeList nodeList = (NodeList) xPath.evaluate("//*[*]", document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + " ");
        }
        System.out.println();
    }

    // 获取指定属性的元素
    // 获取所有年龄大于35的人员名称
    public static void getAttrEles() throws Exception {
        NodeList nodeList = (NodeList) xPath.evaluate("//authors/author[@age>35]/@name", document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent() + " ");
        }
        System.out.println();
    }
}
