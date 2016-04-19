/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/

package fr.enseirb.battleship;

public class ShipOverlapException extends Exception {

	public ShipOverlapException() {
		super();
		String message = "overlap";
		System.out.println(message + "\n");
	}

}
