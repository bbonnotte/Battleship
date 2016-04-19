package fr.enseirb.battleship;
import fr.enseirb.battleship.play.*;
import fr.enseirb.battleship.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.enseirb.battleship.play.Players;
import fr.enseirb.battleship.ships.AircraftCarrier;
import fr.enseirb.battleship.ships.Battleship;
import fr.enseirb.battleship.ships.Destroyer;
import fr.enseirb.battleship.ships.PatrolBoat;
import fr.enseirb.battleship.ships.Submarine;

public class TestWin{

	public static void main(String[] args) throws ShipOutOfBoundsException, ShipOverlapException {
		boolean win;
		
		Grid r = new Grid();
		Ia H = new Ia();
		Human M = new Human();
		
		ReadGridFile xml = new ReadGridFile();
		SaveFile file = new SaveFile();
		
		String gridFile = "ships.xml";
		String shipsFile = "grid.xml";
		
		try {
			r.svgMaker(new FileWriter("debug.svg"), r, gridFile, shipsFile);
			
			H.fire(r, 0, 0);
			H.fire(r, 1, 0);
			H.fire(r, 2, 0);
			H.fire(r, 0, 2);
			H.fire(r, 0, 3);
			H.fire(r, 2, 2);
			H.fire(r, 2, 3);
			H.fire(r, 2, 4);
			H.fire(r, 4, 2);
			H.fire(r, 4, 3);
			H.fire(r, 4, 4);
			H.fire(r, 4, 5);
			H.fire(r, 4, 6);
			H.fire(r, 6, 2);
			H.fire(r, 6, 3);
			H.fire(r, 6, 4);
			H.fire(r, 6, 5);
			H.fire(r, 0, 9);
			H.fire(r, 1, 9);
			H.fire(r, 2, 9);
			H.fire(r, 3, 9);
			H.fire(r, 4, 9);
			
			// Check if the IA win -> the player looses
			win = H.win(r, 0);
			
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (InvalidGridException e) {
			System.out.println("Not good size ");
		}
		
	}
}

