/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/

package fr.enseirb.battleship;

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;

public class ReadGridFile {
	
	// Fields that we need to get from XmlFile
	 int width;
	 int height;
		 
	// Read Xml file    
	public String[] grid_maker(String gridFile){
		
		// DOM Parse
		try {
	
			File fXmlFile = new File(gridFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			doc.getDocumentElement().normalize();
							
			NodeList nList = doc.getElementsByTagName("grid");
								
			// array which contain our variables
			String[] array = new String[9]; 
		
			for (int temp = 0; temp < nList.getLength(); temp++) {
		
				Node nNode = nList.item(temp);
												
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		
					Element eElement = (Element) nNode;
					 
					// Get Elements of the Xml file
					array[0] = eElement.getElementsByTagName("horizontal").item(0).getTextContent(); // size horizontal
					array[1] =  eElement.getElementsByTagName("vertical").item(0).getTextContent(); // size vertical
					array[2] = eElement.getElementsByTagName("battleship").item(0).getTextContent(); // nbr battleship
					array[3] =  eElement.getElementsByTagName("submarine").item(0).getTextContent(); // nbr submarine
					array[4] =  eElement.getElementsByTagName("destroyer").item(0).getTextContent(); // nbr destroyer
					array[5] =  eElement.getElementsByTagName("patrol-boat").item(0).getTextContent(); // nbr patrolBoat
					array[6] =  eElement.getElementsByTagName("aircraft-carrier").item(0).getTextContent(); // nbr aircraftCarrier
				}
			}
			
	        NodeList strategies = doc.getElementsByTagName("strategies");
	        Element strategie = (Element) strategies.item(0);
	        String placement  = strategie.getAttribute("placement");
	        String firing = strategie.getAttribute("firing");
	        array[7] = placement;
	        array[8] = firing;
	        
			return array;
	    } 
		catch (Exception e) {
			e.printStackTrace();
	    }
		return null;
	}

	 
	public int set_xml_width () {
		return width;
	}
	public int set_xml_height () {
		return height;
	}
	
	
}
	  
