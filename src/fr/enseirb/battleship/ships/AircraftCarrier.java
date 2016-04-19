/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/


package fr.enseirb.battleship.ships;

import fr.enseirb.battleship.ShipOverlapException;
import java.io.Writer;

import fr.enseirb.battleship.Grid;
import fr.enseirb.battleship.ShipOutOfBoundsException;
import fr.enseirb.battleship.ShipOverlapException;
import fr.enseirb.battleship.Ships;


public class AircraftCarrier extends Ships {
	private static int nbBox = 5;
	
	public AircraftCarrier(String name, String registration, int x, int y, String orientation) throws ShipOutOfBoundsException{
		super(name, registration, x, y, orientation);	
	}
	
	public int getNbBox(){
		return nbBox;
	}

	
	@Override
	public String svg(int x, int y, String orientation) {
		String ligne = " ";
		int nbBox = getNbBox();
		if (orientation.equals("vertical")){
			ligne = String.format("<rect width='%d' height='%d' x='%d'  y='%d' style='fill:#ff0000 fill-opacity:0.25;stroke:#000000; stroke-width:1px' id='rect1353' />\n",100,100*nbBox, x, y);
		}
		if (orientation.equals("horizontal")){
			ligne = String.format("<rect width='%d' height='%d' x='%d'  y='%d' style='fill:#ff0000 fill-opacity:0.25;stroke:#000000; stroke-width:1px' id='rect1353' />\n",100*nbBox,100, x, y);
		}
        return ligne;
	}
	
	
	
	@Override
	public int setPosition(Grid g, int x, int y, String orientation){
		int busy;
		int x1;
		String id = getId();
		
		busy = cellAlreadyBusy(g, x, y);
		
		if (busy==1){
			return 1;
		}
		
		if (busy==2){
			return 2;
		}
		
		int height = g.getHeight();
		int width = g.getWidth();
		
		try{
			if (orientation.equals("horizontal")){
				for (int i=x; i<x+nbBox; i++){
					if ((x>=10) && (x<20)){
						 x1 = x-10;
					}
					else{
						x1 = x;
					}
					if (x1>width){
						//throw new ShipOutOfBoundsException(x1, y);
					}
					g.setShipPosition(i, y, id);
				}
			}
			if (orientation.equals("vertical")){
				for (int j=y; j<y+nbBox; j++){
					if (j>height){
						//throw new ShipOutOfBoundsException(x, y);
					}
					g.setShipPosition(x, j, id);
				}
			}
		}
		catch(Exception e){
			
		}
		return 0;
	}
	
	@Override
	public String name(int x, int y, String id) {
		String ligne = " ";
		ligne = String.format("<text id='title' x = '%d' y ='%d' font-size='50px'>%s</text>\n",x, y, id);
		return ligne;
	}
	
	
	@Override
	public int cellAlreadyBusy(Grid g, int x, int y){
		String[][] shipPosition;
		shipPosition = g.getShipPosition();
		String id = getId();
		int height = g.getHeight();
		int width = g.getWidth();
		
		if (orientation.equals("horizontal")){
			for (int i=x; i<x+nbBox; i++){
				if (i < 2*width){
					if (shipPosition[i][y].equals(" ")){
						
					}
					else{
						return 2;
					}
				}
				else{
					return 1;
				}
			}
		}
		if (orientation.equals("vertical")){
			for (int j=y; j<y+nbBox; j++){
				if (j<height){
					if (shipPosition[x][j].equals(" ")){
						
					}
					else{
						return 2;
					}
				}
				else{
					return 1;
				}
			}
		}	

		return 0;
	}
	
}


