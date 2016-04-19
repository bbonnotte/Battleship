/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/


package fr.enseirb.battleship.play;

import fr.enseirb.battleship.Grid;

public class Ia extends Players {
	

	protected int xMin = 0;
	protected int yMin = 0;
	protected int xMax;
	protected int yMax;
	
	
	public String fire(Grid g, int x, int y){
		String[][] gridShot = g.getGridShot();
		String[][] shipPosition = g.getShipPosition();
		
		
		String value = " ";
		boolean stop = false;
		int compteur = 0;
		
		xMax = nbCellWidth;
		yMax = nbCellHeight;
		
		for (int i=xMin; i<xMax; i++){
			for (int j=yMin; j<yMax; j++){
				if(shipPosition[i][j].equals(shipPosition[x][y])){
					// if compteur == 1 and != " " -> boat sunk
					compteur = compteur + 1;
				}
			}
		}
		
		if(gridShot[x][y].equals("nothing")){
			
			// case : no boat on the cell
			if(shipPosition[x][y].equals(" ")){
				String info = "IA missed boat at (" + x + ", " + y + ").";
				System.out.println(info);
				
				// Update the grid memory
				value = "Miss";
				g.setGridShot(x, y, value);
			}
			else{
				// case : the boat is still in the grid -> stop = True 
				for (int i=xMin; i<xMax; i++){
					for (int j=yMin; j<yMax; j++){
						if(shipPosition[i][j].equals(shipPosition[x][y])){
							while(stop==false){
								if (compteur == 1){
									String info = "IA sunk boat " + shipPosition[x][y] + " at (" +  x + ", "+ y+").";
									System.out.println(info);
									stop = true;
									shipPosition[x][y] = " ";
								}
								else{
									String info = "IA touched boat " + shipPosition[x][y] + " at (" +  x + ", "+ y+").";
									System.out.println(info);
									stop = true;
									shipPosition[x][y] = " ";
								}
							}
						}
					}
				}
				
				// Update the grid memory
				value = "Touch";
				g.setGridShot(x, y, value);
			}
		}
		else{
			String info = "IA have already fired this cell.";
			System.out.println(info);
		}
		return value;
	}
}
