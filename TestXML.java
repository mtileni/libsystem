/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author bruce
 */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class TestXML 
{

  public void xmlLoad()
  {
	  try {

			File fXmlFile = new File("/home/bruce/Documents/category.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("category");

			for (int temp = 0; temp < nList.getLength(); temp++) 
			{

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
		        {
					Element eElement = (Element) nNode;
					BookCategory bc=new BookCategory();
					Integer idC=Integer.parseInt(eElement.getAttribute("id"));
					bc.setIdCategory(idC);
					bc.setCategory(eElement.getElementsByTagName("bookCategory").item(0).getTextContent());
					
					System.out.println("Category  : " + bc.getCategory());
					System.out.println("Book Category ID: " +bc.getIdCategory());
					
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
  }
  public static void main(String argv[]) 
  {
	TestXML test=new TestXML();
	test.xmlLoad();
        InputStream input;
//        OutputStream out=input.read(b);
    
  }

}