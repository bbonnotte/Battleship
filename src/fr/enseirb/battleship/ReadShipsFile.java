/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/


package fr.enseirb.battleship;

import fr.enseirb.battleship.ships.*;

import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
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

public class ReadShipsFile {
    
    public Set<Ships> haveShips(String shipsFile) {
    	Set<Ships> allShips = new HashSet<Ships>(); 

        try {
        	File fXmlFile = new File(shipsFile);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(fXmlFile);
	                
	        doc.getDocumentElement().normalize();

	        NodeList ships = doc.getElementsByTagName("ship");
	        int sizeList = ships.getLength();
	        
	        for(int i=0;i < sizeList;i++) {
	        	
	        	// Ships elements tag : id and type
	            Element ship = (Element) ships.item(i);
	            
	            String type = ship.getAttribute("type");
	            String id = ship.getAttribute("id");
	            
	            // Ships position tag : x, y and orientation
	            Element position = (Element) ship.getElementsByTagName("position").item(0);
	           
	            int x = Integer.parseInt(position.getAttribute("x"));
	            int y = Integer.parseInt(position.getAttribute("y"));
	            String orientation = position.getAttribute("orientation");
	            
	            
	           // determine the number of cells of each ship
	           if (type.equals("battleship")) {
	        	   int nb_cell = 4;
	               Battleship newShip = new Battleship(type, id, x, y, orientation); 
	               allShips.add(newShip);
	           }
	           
	           if (type.equals("patrol-boat")) {
	        	  int  nb_cell = 3;
	              PatrolBoat newShip = new PatrolBoat(type, id, x, y, orientation); 
	              allShips.add(newShip);
	           }
	           
	           if (type.equals("submarine")) {
	        	  int nb_cell = 3;
	              Submarine newShip = new Submarine(type, id, x, y, orientation); 
	              allShips.add(newShip);
	           }
	           
	           if (type.equals("destroyer")) {
	        	   int nb_cell = 2;
	               Destroyer newShip = new Destroyer(type, id, x, y, orientation); 
	               allShips.add(newShip);
	           }
	           
	           if (type.equals("aircraft-carrier")) {
	        	   int nb_cell = 5;
	               AircraftCarrier newShip = new AircraftCarrier(type, id, x, y, orientation); 
	               allShips.add(newShip);
	           } 
	        }	 
        }         
        catch (Exception e) {
        	e.printStackTrace();
        }
        finally{
        }
        return allShips;
    }
}
    