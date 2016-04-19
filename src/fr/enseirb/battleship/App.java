/*ARNEAU Megan - BONOTTE Benjamin - GUISSET Abdoul*/


package fr.enseirb.battleship;
import fr.enseirb.battleship.play.*;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.enseirb.battleship.*;
import fr.enseirb.battleship.play.Players;
import fr.enseirb.battleship.ships.AircraftCarrier;
import fr.enseirb.battleship.ships.Battleship;
import fr.enseirb.battleship.ships.Destroyer;
import fr.enseirb.battleship.ships.PatrolBoat;
import fr.enseirb.battleship.ships.Submarine;

public class App {

	public static void main(String[] args) throws ShipOutOfBoundsException, ShipOverlapException {
				
		if (args.length < 2) {
		    System.err.println("Not enough arguments received.");
		    return;
		}
		
		
		String value;
		
		Grid r = new Grid();
		
		Ia Artif = new Ia();
		Human Megan = new Human();
		
		ReadGridFile xml = new ReadGridFile();
		SaveFile file = new SaveFile();
		

		String command = args[0];
		String gridFile = args[1];
		String shipsFile = args[2];
		
		if(command.equals("debug")){
			try{
				r.svgMaker(new FileWriter("debug.svg"), r, gridFile, shipsFile);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		int height;
		int width;

		
		Scanner sc = new Scanner(System.in);
		
		int x1 = 0;
		int y1 = 0;
		int xMemory = 0;
		int yMemory = 0;
		
		if(command.equals("play")){
			System.out.println("Play.\n");

			Random generator = new Random();

			try{
				r.svgMaker(new FileWriter("debug.svg"), r, gridFile, shipsFile);
			}
			catch(Exception e){
				
			}
			
			height = r.getHeight();
			width = r.getWidth();
			
			// Decide who begin the game.
			int firstPlayer = generator.nextInt(2);
			
			if (firstPlayer==0){
				System.out.println("IA begins the game.\n");
				x1 = generator.nextInt(height);
				y1 = generator.nextInt(width);
				Artif.fire(r, x1, y1);
				
				xMemory = x1;
				yMemory = y1;
			}
			else{
				System.out.println("You begin the game.\n");
			}
			
			try {
				boolean win = false;
				while(win==false){
		
					System.out.println("Enter a command : fire - debug - view - exit.\n");
					String str = sc.nextLine();	
					if (str.equals("fire")){
						System.out.println("Give x.\n");
						int x = sc.nextInt();
						System.out.println("Give y.\n");
						int y = sc.nextInt();
						Megan.fire(r, x, y);
						sc.nextLine();
						
						String fireType = r.getFiring();
						if (fireType.equals("random")){
							x1 = generator.nextInt(width);
							y1 = generator.nextInt(height);
							Artif.fire(r, x1, y1);
						}
						
						if (fireType.equals("pack")){
							x1 = generator.nextInt(width/2);
							y1 = generator.nextInt(height/2);
							Artif.fire(r, x1, y1);
						}
						
						if (fireType.equals("far")){
							// random = min + random(max-min)
							if(xMemory < width/2 && yMemory < height/2){
								x1 = width/2 + generator.nextInt(width-width/2);
								y1 = generator.nextInt(height/2);
								Artif.fire(r, x1, y1);
							}
							else if(xMemory > width/2 && yMemory < height/2){
								x1 = width/2 + generator.nextInt(width-width/2);
								y1 = height/2 + generator.nextInt(height-height/2);
								Artif.fire(r, x1, y1);
							}
							else if(xMemory < width/2 && yMemory > height/2)
							{								
								x1 = generator.nextInt(width/2);
								y1 = generator.nextInt(height/2);
								Artif.fire(r, x1, y1);
							}
							else{
								x1 = generator.nextInt(width/2);
								y1 = height/2+ generator.nextInt(height-height/2);
								Artif.fire(r, x1, y1);
							}
							
							xMemory = x1;
							yMemory  = y1;
						}
						
						if (fireType.equals("detection")){
	
							if(xMemory < width/2 && yMemory < height/2){
								x1 = generator.nextInt(width/2);
								y1 = generator.nextInt(height/2);
	
								value = Artif.fire(r, x1, y1);
							}
							else if(xMemory > width/2 && yMemory < height/2){
								x1 = width/2 + generator.nextInt(width-width/2);
								y1 = generator.nextInt(height/2);
								
								value = Artif.fire(r, x1, y1);
							}
							else if(xMemory < width/2 && yMemory > height/2)
							{								
								x1 = generator.nextInt(width/2);
								y1 = height/2+ generator.nextInt(height-height/2);
								
								value = Artif.fire(r, x1, y1);
							}
							else{
								x1 = width/2 + generator.nextInt(width-width/2);
								y1 = height/2 + generator.nextInt(height-height/2);
	
								value = Artif.fire(r, x1, y1);
							}
							
							// if we touch a boat, we go on shooting in the same direction
							if (value.equals("Touch")){
								xMemory = x1;
								yMemory = y1;
							}
							// else, we shoot at random
							else{
								xMemory = generator.nextInt(width);;
								yMemory = generator.nextInt(height);;
							}
						}
					}
					
					else if (str.equals("debug")){
						file.debug(Artif, r);
					}
	
					else if (str.equals("view")){
						file.view(new FileWriter("view.svg"), r, gridFile, shipsFile);
					}
					
					else if (str.equals("exit")){
						System.out.println("You quit the game.\n");
						System.exit(0);
					}
	
					else{
						String message = "You do not enter a valid command.\n";
						throw new CommandException(message);
					}
					
					win = Artif.win(r, 0);
					if (win==true){
						System.exit(0);
					}
	
					win = Megan.win(r, 10);
					}
			}

			catch (Exception e) {
			    e.printStackTrace();
			} 
			finally{
				
			}
		}
	}
}

