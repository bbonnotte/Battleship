/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/


package fr.enseirb.battleship.play;
import fr.enseirb.battleship.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Human extends Players {
	
	
	
	protected int xMin;
	protected int yMin = 0;
	protected int xMax;
	protected int yMax;
	
	public String fire(Grid g, int x, int y){
		String[][] gridShot = g.getGridShot();
		String[][] shipPosition = g.getShipPosition();

		String value = " ";
		boolean stop = false;
		int compteur = 0;
		
		int nbCellWidth = g.getWidth();
		int nbCellHeight = g.getHeight();
		
		xMin = nbCellWidth;
		xMax = 2*nbCellWidth;
		yMax = nbCellHeight;
		
		
		int x1 = x + nbCellWidth;
		int y1 = y;
				
		for (int i=xMin; i<xMax; i++){
			for (int j=yMin; j<yMax; j++){
				if(shipPosition[i][j].equals(shipPosition[x1][y1])){
					// if compteur == 1 and != " " -> boat sunk
					compteur = compteur + 1;
				}
			}
		}
		
		if(gridShot[x1][y1].equals("nothing")){
			
			// case : no boat on the cell
			if(shipPosition[x1][y1].equals(" ")){
				String info = "Missed boat at (" + x + ", " + y + ").";
				System.out.println(info);
				
				value = "Miss";
				// Update the grid memory
				g.setGridShot(x1, y1, value);

			}
			else{
				// case : the boat is still in the grid -> stop = True 
				for (int i=xMin; i<xMax; i++){
					for (int j=yMin; j<yMax; j++){
						if(shipPosition[i][j].equals(shipPosition[x1][y1])){
							while(stop==false){
								if (compteur == 1){
									String info = "Sunk boat " + shipPosition[x1][y1] + " at (" +  x + ", " + y + ").";
									System.out.println(info);
									stop = true;
									shipPosition[x1][y1] = " ";
								}
								else{
									String info = "Touched boat " + shipPosition[x1][y1] + " at (" +  x + ", "+ y+").";
									System.out.println(info);
									stop = true;
									shipPosition[x1][y1] = " ";
								}
							}
						}
					}
				}
				
				value = "Touch";
				// Update the grid memory
 				g.setGridShot(x1, y1, value);
			}
		}
		else{
			String info = "You have already fired this cell.";
			System.out.println(info);
		}	
		return value;
	}
	
	

	
}
