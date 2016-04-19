/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/

package fr.enseirb.battleship;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
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

public class Grid {
	/*Variable definition*/

	/*--------------*/
	// Title of the grid
	private String titre;
		
    /* Dimensions of the grid */
    private int nbCellWidth ;
    private int nbCellHeight;
    private int nbGridCell;
    private int nbGridCellBusy;
	
    /* Ship position in the grid */
	protected String[][] shipPosition;
	
	/* Shooting position in the grid */
	protected String[][] gridShot;
	
	/*Ships number*/
	private int nbBattleship;
	private int nbAircraftCarrier;
	private int nbDestroyer;
	private int nbPatrolBoat;
	private int nbSubmarine;
	private int nbTotalBoat;
	private String placement;
	private String firing;
	
	Set<Ships> elements = new HashSet<Ships>();
	Set<Ships> elementsIA = new HashSet<Ships>();
	
	/*--------------*/
	
	
	/*Access and Set functions*/
	/*--------------*/
	
	public String[][] getShipPosition(){
		return this.shipPosition;
	}
	
	public String[][] getGridShot(){
		return this.gridShot;
	}
	
	public void setGridShot(int x, int y, String value){
		this.gridShot[x][y] = value;
	}
	
	public void setShipPosition(int x, int y, String value){
		this.shipPosition[x][y] = value;
	}
	
    public String getTitre() {
        return this.titre;
    }
    
    public String[] parseGridFile(String gridFile){
    	ReadGridFile xml = new ReadGridFile();
    	String[] gridElements = xml.grid_maker(gridFile);
    	return gridElements;
    }
    public void setGridElements(String gridFile){
    	String[] gridElements = parseGridFile(gridFile);
    	this.nbCellWidth = Integer.parseInt(gridElements[0]);
    	this.nbCellHeight = Integer.parseInt(gridElements[1]);
    	this.nbBattleship = Integer.parseInt(gridElements[2]);
    	this.nbSubmarine = Integer.parseInt(gridElements[3]);
    	this.nbDestroyer = Integer.parseInt(gridElements[4]);
    	this.nbPatrolBoat = Integer.parseInt(gridElements[5]);
    	this.nbAircraftCarrier = Integer.parseInt(gridElements[6]);
    	this.placement = gridElements[7];
    	this.firing = gridElements[8];
    	setNbTotalBoat();
    	
		shipPosition = new String[2*nbCellWidth][nbCellHeight];
		gridShot = new String[2*nbCellWidth][nbCellHeight];
    	
		// nbCellWidth = one grid horizontal size -> two grid size = 2*nbCellWidth
        for (int i=0; i < 2*this.nbCellWidth; i++){
        	for (int j=0; j < this.nbCellHeight; j++){
        		// Initialization : no ships on the grid
        		shipPosition[i][j] = " ";
        		// Initialization of the shot memory : no fire on the grid
        		gridShot[i][j] = "nothing";
        	}
        }
    }
    
    public void setNbTotalBoat(){
    	this.nbTotalBoat = nbBattleship + nbSubmarine + nbDestroyer + nbPatrolBoat + nbAircraftCarrier;
    }
    
    public int getWidth() {
		return nbCellWidth;
	}
    
    public int getHeight() {
		return nbCellHeight;
	}
    
    public int getNbBattleship() {
		return nbBattleship;
	}
    
    public int getNbSubmarine() {
		return nbSubmarine;
	}
    
    public int getNbDestroyer() {
		return nbDestroyer;
	}
    
    public int getNbPatrolBoat() {
		return nbPatrolBoat;
	}
    
    public int getNbAircraftCarrier() {
		return nbAircraftCarrier;
	}
    
    public String getPlacement() {
    	return placement;
    }
    
    public String getFiring() {
    	return firing;
    }
    
    public void setNbGridCell(){
    	nbGridCell = nbCellWidth * nbCellHeight;
    }
    
    public void setNbGridCellBusy(){
    	nbGridCellBusy = nbBattleship*4 + nbPatrolBoat*3 + nbSubmarine*3 + nbDestroyer*2 + nbAircraftCarrier*5;
    }
    
    public Set<Ships> getElementsIa(){
    	return elementsIA;
    }
    
    public Set<Ships> getElements(){
    	return elements;
    }
	/*--------------*/
     
	/* Add ship to a set*/
	/*--------------*/
    public void addShip(Ships e, Set<Ships> elements, Set<Ships> elementsIA) throws ShipOverlapException {
			elements.add(e);
			elementsIA.add(e);
	}
    /*--------------*/


    /*Create the svg grid*/
    /*--------------*/
	public void gridMaker( Writer w, int width, int height, int nbCellWidth,int nbCellHeight) {

		try {	 
			// Horizontal & Vertical lines
			for (int i =1 ; i <= nbCellWidth; i++) {
				w.append(String.format("<line x1='%d' y1='0' x2='%d' y2='%d' style='fill:#0000ff fill-opacity:0.75;stroke:#000000; stroke-width:5px' id='rect1353'/>\n",100*i,100*i, 100*height));
			}
			for (int j =1 ; j <= nbCellHeight; j++) {
				w.append(String.format("<line x1='0' y1='%d' x2='%d' y2='%d' style='fill:#0000ff fill-opacity:0.75;stroke:#000000; stroke-width:5px' id='rect1353'/>\n",100*j, width, 100*j));
			}
			
	        w.append(String.format("<line x1='%d' y1='%d' x2='%d' y2='%d' style='fill:#ff000 fill-opacity:0.75;stroke:#000000; stroke-width:10px' id='rect1353'/>\n",100*nbCellWidth/2, 0, 100*nbCellHeight/2, 100*nbCellWidth));
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	/*--------------*/
	
    
    /* Create svg */
    /*--------------*/
    public void svgMaker (Writer w, Grid grid, String gridFile, String shipsFile) throws InvalidGridException {

    	int nbBoat = 0; 
    	int busy = 1;
    	int busyElement;
    	String orientation = " ";
    	
    	grid.setGridElements(gridFile);

		ReadShipsFile xml = new ReadShipsFile();
    	
		// Data of the grid XML
		try{
			Set<Ships> list;
			list = xml.haveShips(shipsFile);
			
        	Iterator<Ships> itShips = list.iterator();
        	
            while (itShips.hasNext()) {
           	
               Ships elementShip = itShips.next() ;  
               grid.addShip(elementShip, elements, elementsIA);

			}
		}
		catch(ShipOverlapException e){
			
		}

		
		setNbGridCell();
		setNbGridCellBusy();
		
		double percent = 0.2*nbGridCell;
		
		
    	if ( nbCellWidth < 10 || nbCellHeight < 10 ){
    		throw new InvalidGridException(nbCellWidth,nbCellHeight);
    	}
    	
    	else if (nbGridCellBusy > percent){
    		throw new InvalidGridException(nbGridCellBusy);
    	}
    	
    	else {	
	    	// Total grid
	    	try{
	    		w.append("<?xml version='1.0' encoding='utf-8'?>\n");
	    		w.append(String.format("<svg xmlns='http://www.w3.org/2000/svg' version='1.1' width='%d' height='%d' style='fill:#0000ff fill-opacity:0.75;stroke:#000000; stroke-width:5px' id='rect1353'>\n", 200*nbCellWidth, 100*nbCellHeight));
	            
	            gridMaker(w, 200*nbCellWidth, 100*nbCellHeight, 2*nbCellWidth, 2*nbCellHeight);
	            
	            try {
	            	
	            	Iterator<Ships> it = elements.iterator();

	            	Iterator<Ships> itIA = elementsIA.iterator();
	            	
		            while (it.hasNext()) {
		           	
		               Ships element = it.next() ;  // retourne un objet de type String
		               
		               // return x y
		               int x = element.getX();
		               int y = element.getY();
		               
		               // give orientation
		               orientation = element.isOrientation();
		               
		               // add svg element 
		               String appen = element.svg(100*x, 100*y, orientation);
		               
		               // add the boat name
		               String id = element.getId();
		               String writeName = element.name(100*x, 100*y, id);
		               
		               // add to the file
		               w.append(String.format(appen));
		               w.append(String.format(writeName));
		               
		               // update of the mask of  the grid
		               busyElement = element.setPosition(this, x, y, orientation);
		               
		               if (busyElement == 1){
		            	   throw new ShipOutOfBoundsException();
		               }
		               
		               if (busyElement == 2){
		            	   throw new ShipOverlapException();
		               }
		               
		               nbBoat = nbBoat + 1;
		            }
		            	
		            if (nbBoat != nbTotalBoat){
		            	throw new ShipConfigurationException();
		            }
		            
		            
		            while (itIA.hasNext()) {
		            	int x1 = 0;
		            	int y1 = 0; 
		            	int xMemory = 0;
		            	int yMemory = 0;
						Ships elementIA = itIA.next();  
		            	busy = 1;
						Random generator = new Random();
						while(busy!=0){
			            	if (placement.equals("random")){
								
								// Random x y
								x1 = 100*nbCellWidth + 100*generator.nextInt(nbCellWidth);
								y1 = 100*generator.nextInt(nbCellHeight);
			            	}
			            	
			            	if (placement.equals("pack")){
								
								// Random x y
								x1 = 100*(nbCellWidth) + 100*generator.nextInt(nbCellWidth/2);
								y1 = 100*generator.nextInt(nbCellHeight/2);
			            	}
			            	
							if (placement.equals("far")){
								
								// random = min + random(max-min)
								if(xMemory < nbCellWidth/2 && yMemory < nbCellHeight/2){
									x1 = nbCellWidth/2 + generator.nextInt(nbCellWidth-nbCellWidth/2);
									y1 = generator.nextInt(nbCellHeight/2);
								}
								else if(xMemory > nbCellWidth/2 && yMemory < nbCellHeight/2){
									x1 = nbCellWidth/2 + generator.nextInt(nbCellWidth-nbCellWidth/2);
									y1 = nbCellHeight/2 + generator.nextInt(nbCellHeight-nbCellHeight/2);
								}
								else if(xMemory < nbCellWidth/2 && yMemory > nbCellHeight/2)
								
								{
									x1 = generator.nextInt(nbCellWidth/2);
									y1 = generator.nextInt(nbCellHeight/2);
								}
								else{
									x1 = generator.nextInt(nbCellWidth/2);
									y1 = nbCellHeight/2+ generator.nextInt(nbCellHeight-nbCellHeight/2);
								}
								
								xMemory = x1;
								yMemory = y1;
							
								x1 = nbCellWidth*100 + x1*100;
								y1 = y1*100;
							}
							
	
							
							if (firing.equals("diagonal")){
								
							}
							
							// give orientation
							orientation = elementIA.isOrientation();
							
				            // update of the mask of  the grid
				            busy = elementIA.setPosition(this, x1/100, y1/100, orientation);
				        }
						
						elementIA.setX(x1);
						elementIA.setY(y1);
					
						// add svg element 
						String appen = elementIA.svg(x1, y1, orientation);
						
						// add the boat name
						String id = elementIA.getId();
						String writeName = elementIA.name(x1, y1, id);
						
						// add to the file
						w.append(String.format(appen));
						w.append(String.format(writeName));
						

			         }
	            }
	            catch (Exception e) {
	            	e.printStackTrace();
	            }
	            w.append("</svg>");
	            w.close();
	    	}
	    	catch (IOException e) {
	            e.printStackTrace();
	    	}
    	}	
    }
    /*--------------*/

    
}


	