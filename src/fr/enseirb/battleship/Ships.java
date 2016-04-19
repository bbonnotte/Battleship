/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/

package fr.enseirb.battleship;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Ships {

    protected String type;
    protected String id;
    protected int x;
    protected int y;
    protected int nbBoat = 0;
    
    protected String orientation;

    public Ships(String type, String id, int x, int y, String orientation) throws ShipOutOfBoundsException{
            
            if (x<0 && y<0 || x<0 && y>0 || x>0 && y<0){
                    throw new ShipOutOfBoundsException();
            }
            else {
                    this.type = type;
                    this.id = id;
                    this.x = x;
                    this.y = y;
                    this.orientation = orientation;
            }
            
    }
    

    /*Abstract Method*/
    
    // draw the boat on the grid
    public abstract String svg(int x, int y, String orientation);

    // add the name on the grid
    public abstract String name(int x, int y, String id);  	  
    
    // keep in a matrix where are the boats
    public abstract int setPosition(Grid g, int x, int y, String orientation);
    
    // check if the cells where is the boat is already busy
    public abstract int cellAlreadyBusy(Grid g, int x, int y);
    /*-------------*/
    
    /*Access and Set Method*/
    public String getId(){
    	return id;
    }
    
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String isOrientation() {
		return orientation;
	}
	/*------------*/

}