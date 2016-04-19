/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/

package fr.enseirb.battleship;

public class InvalidGridException extends Exception {

	public InvalidGridException(int nbCellWidth, int nbCellHeight) {
		super();
		System.out.println("You have put " + nbCellWidth + "x" + nbCellHeight);
		System.out.println("You must have a minimum of 10x10");
	}

	public InvalidGridException(int nbCellGridBusy){
		super();
		System.out.println("Only " + nbCellGridBusy + " cells are busy.");
		System.out.println("You must have less than 20% of the total number of cells busy.");
	}
	
}
