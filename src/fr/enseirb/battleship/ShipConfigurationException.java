/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/

package fr.enseirb.battleship;

public class ShipConfigurationException extends Exception {

	public ShipConfigurationException() {
		super();
		System.out.println("The boat number extracted from grid.xml does not match with the number of boats in ships.xml.");		
	}

}
