package shrijan.game.GameState;

import java.awt.Color;





import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import shrijan.game.TileMap.Background;
import shrijan.game.Audio.JukeBox;
import shrijan.game.Handlers.Keys;
import shrijan.game.Main.GamePanel;
import shrijan.game.TileMap.Background;


public class FinishState extends GameState {
	
	private Font font;
	
	public FinishState(GameStateManager gsm) {
		
		super(gsm);
		

		// music
				JukeBox.load("/Music/metalover.mp3", "metalover");
				JukeBox.loop("metalover", 600, JukeBox.getFrames("metalover") - 2200);
				
		
				
		// fonts
		font = new Font("Arial", Font.PLAIN, 22);
		
	}
	
	

	public void init() {}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("CONGRATULATIONS", 22, 50);
		g.drawString("MISSION PASSED", 22, 90);
		g.drawString("CREDITS", 22, 130);
		g.drawString("Designer - Shrijan Tiwari", 22, 170);
		g.drawString("Developer - Shrijan Tiwari", 22, 210);
	
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) gsm.setPaused(false);
		if(Keys.isPressed(Keys.BUTTON1)) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}
}



