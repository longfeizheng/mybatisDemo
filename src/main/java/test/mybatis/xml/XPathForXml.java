package test.mybatis.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import test.mybatis.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

/**
 * Created on 2016/11/29 0029.
 *
 * @author zlf
 * @since 1.0
 */

/*      <?xml version="1.0" encoding="UTF-8"?>
        <book>
        <bookname name="XML详解" font="GB2312"></bookname>
        <authors>
        <author name="张孝祥" sex="男" age="45"></author>
        <author name="王勇" sex="男" age="35"></author>
        <author name="王波" sex="男" age="30"></author>
        </authors>
        <price value="￥55"></price>
        <publishdate>
        <value>2009-08-18</value>
        </publishdate>
        </book>
    */
public class XPathForXml {
    public static void main(String[] args) {
        new XPathForXml().parseXMLWithJdk();
    }

    public void parseXMLWithJdk() {
        try {
            //读取book.xml到内存
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(ResourceLoader.class.getClassLoader().getResourceAsStream("test/mybatis/book.xml"));
//            Document document = builder.parse(new FileInputStream("G:\\projects\\mybatistest\\src\\main\\resources\\test\\mybatis\\book.xml"));
            //通过xml获得book的authors的authors的author子节点
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            NodeList authors = (NodeList) xPath.evaluate("book/authors/author", document, XPathConstants.NODESET);
            System.out.println(authors.getLength());
            String name = "";
            String sex = "";
            String age = "";
            //遍历元素
            if (authors != null) {
                for (int i = 0; i < authors.getLength(); i++) {
                    Node author = authors.item(i);
                    int n = i + 1;
                    name = (String) xPath.evaluate("@name", author, XPathConstants.STRING);
                    sex = (String) xPath.evaluate("@sex", author, XPathConstants.STRING);
                    age = (String) xPath.evaluate("@age", author, XPathConstants.STRING);
                    System.out.println(n + ". 名字:" + name+",性别:"+sex+",年龄:"+age);
                }
            }
            //获得book的authors的第一个子节点，注意NODESET和NODE的区别
            Node author = (Node) xPath.evaluate("book/authors/author", document, XPathConstants.NODE);
            System.out.println("名称:" + author.getNodeName());
            System.out.println("内容:" + author.getTextContent());//如果存在内容则返回内容，不存在则返回空

            //获取节点的属性
            NamedNodeMap namedNodeMap = author.getAttributes();
            System.out.println("该节点的属性个数:" + namedNodeMap.getLength());

            //遍历元素的属性
            if (namedNodeMap != null) {
                for (int i = 0; i < namedNodeMap.getLength(); i++) {
                    int n = i + 1;
                    System.out.print("属性" + n + "名称" + namedNodeMap.item(i).getNodeName()+" ");
                    System.out.print("值" + namedNodeMap.item(i).getNodeValue()+" ");
                    System.out.print("类型" + namedNodeMap.item(i).getNodeType()+" ");
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
