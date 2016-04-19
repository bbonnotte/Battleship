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


public abstract class Players {
	/*Variable definition*/
	/*--------------*/
	protected int nbCellWidth ;
	protected int nbCellHeight;

	/*Constructor*/
	/*--------------*/
	
	/*Abstract method*/
	/*--------------*/
	public abstract String fire(Grid g, int x, int y);
	/*--------------*/

	/*Method definition*/
	/*--------------*/

	
	public boolean win(Grid g, int x){
		String shipPosition[][] = g.getShipPosition();
		nbCellWidth = g.getWidth();
		nbCellHeight = g.getHeight();
		
		boolean win = true;
		int xMax, yMax;
		
		// grid of the player
		if(x==0){
			xMax = nbCellWidth;
		}
		// grid of the IA
		else{
			xMax = 2*nbCellWidth;
		}
		
		yMax = nbCellHeight;
		
		
		// check the grid matrix
		for (int i=x; i<xMax; i++){
			for (int j=0; j<yMax; j++){
				
				if (shipPosition[i][j].equals(" ")){
					
				}
				else {
					win = false;
				}
			}
		}
		
		if (win == true){
			if(x==0){
				System.out.println("You loose !\n");
			}
			else{
				System.out.println("You win ! \n");
			}
		}
		
		return win;
	}
	/*--------------*/

}

