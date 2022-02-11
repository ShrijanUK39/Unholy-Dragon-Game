package shrijan.game.GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import shrijan.game.Audio.JukeBox;
import shrijan.game.Entity.Enemy;
import shrijan.game.Entity.EnemyProjectile;
import shrijan.game.Entity.EnergyParticle;
import shrijan.game.Entity.Explosion;
import shrijan.game.Entity.HUD;
import shrijan.game.Entity.Player;
import shrijan.game.Entity.PlayerSave;
import shrijan.game.Entity.Teleport;
import shrijan.game.Entity.Title;
import shrijan.game.Entity.Enemies.Gazer;
import shrijan.game.Entity.Enemies.GelPop;
import shrijan.game.Entity.Enemies.Tengu;
import shrijan.game.Handlers.Keys;
import shrijan.game.Main.GamePanel;
import shrijan.game.TileMap.Background;
import shrijan.game.TileMap.TileMap;


public class Level3State extends GameState {
	
	private Background temple;
	
	private Player player;
	private TileMap tileMap;
	private ArrayList<Enemy> enemies;
	private ArrayList<EnemyProjectile> eprojectiles;
	private ArrayList<EnergyParticle> energyParticles;
	private ArrayList<Explosion> explosions;
	
	private HUD hud;
	private BufferedImage level3;
	private Title title;
	private Title subtitle;
	private Teleport teleport;
	
	// events
	private boolean blockInput = false;
	private int eventCount = 0;
	private boolean eventStart;
	private ArrayList<Rectangle> tb;
	private boolean eventFinish;
	private boolean eventDead;
	private boolean eventQuake;
	
	public Level3State(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		
		// backgrounds
		temple = new Background("/Backgrounds/temple.gif", 0.5, 0);
		
		// tilemap
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/ruinstileset.gif");
		tileMap.loadMap("/Maps/level1b.map");
		tileMap.setPosition(140, 0);
		tileMap.setTween(1);
		
		// player
		player = new Player(tileMap);
		player.setPosition(300, 131);
		player.setHealth(PlayerSave.getHealth());
		player.setLives(PlayerSave.getLives());
		player.setTime(PlayerSave.getTime());
		
		// enemies
		enemies = new ArrayList<Enemy>();
		eprojectiles = new ArrayList<EnemyProjectile>();
		populateEnemies();
		
		// energy particle
		energyParticles = new ArrayList<EnergyParticle>();
		
		player.init(enemies, energyParticles);
		
		// explosions
		explosions = new ArrayList<Explosion>();
		
		// hud
		hud = new HUD(player);
		
		// title and subtitle
		try {
			level3 = ImageIO.read(
				getClass().getResourceAsStream("/HUD/Level3.gif")
			);
			title = new Title(level3.getSubimage(0, 0, 203, 44));
			title.sety(25);
		}
			
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// teleport
		teleport = new Teleport(tileMap);
		teleport.setPosition(2850, 371);
		
		// start event
		eventStart = true;
		tb = new ArrayList<Rectangle>();
		eventStart();
		
		// sfx
		JukeBox.load("/SFX/teleport.mp3", "teleport");
		JukeBox.load("/SFX/explode.mp3", "explode");
		JukeBox.load("/SFX/enemyhit.mp3", "enemyhit");
		
		// music
				JukeBox.load("/Music/level1.mp3", "level1");
				JukeBox.loop("level1", 600, JukeBox.getFrames("level1") - 2200);
		
	}
	
	private void populateEnemies() {
		enemies.clear();
		GelPop gp;
		Gazer g;
		Tengu t;
		
		gp = new GelPop(tileMap, player);
		gp.setPosition(750, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(900, 150);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1320, 250);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2600, 370);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2620, 370);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2640, 370);
		enemies.add(gp);
		
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(1570, 160);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(1500, 160);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(1450, 160);
		enemies.add(t);
		g = new Gazer(tileMap);
		g.setPosition(350, 100);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(400, 100);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(450, 100);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1470, 100);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1400, 100);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(550, 120);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(650, 120);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(904, 130);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1080, 270);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1180, 270);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1230, 270);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1704, 300);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1850, 510);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1770, 510);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(2300, 490);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(2600, 300);
		enemies.add(g);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(900, 330);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(1800, 580);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(1900, 580);
		enemies.add(t);
		
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2330, 550);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2600, 380);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2650, 380);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2700, 380);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2750, 380);
		enemies.add(t);
		
	}
	
	public void update() {
		
		// check keys
		handleInput();
		
		// check if quake event should start
		if(player.getx() > 2175 && !tileMap.isShaking()) {
			eventQuake = blockInput = true;
		}
		
		// check if end of level event should start
		if(teleport.contains(player)) {
			eventFinish = blockInput = true;
		}
		
		// play events
		if(eventStart) eventStart();
		if(eventDead) eventDead();
		if(eventQuake) eventQuake();
		if(eventFinish) eventFinish();
		
		// move title and subtitle
		if(title != null) {
			title.update();
			if(title.shouldRemove()) title = null;
		}
		if(subtitle != null) {
			subtitle.update();
			if(subtitle.shouldRemove()) subtitle = null;
		}
		
		// move backgrounds
		temple.setPosition(tileMap.getx(), tileMap.gety());
		
		// update player
		player.update();
		if(player.getHealth() == 0 || player.gety() > tileMap.getHeight()) {
			eventDead = blockInput = true;
		}
		
		// update tilemap
		tileMap.setPosition(
			GamePanel.WIDTH / 2 - player.getx(),
			GamePanel.HEIGHT / 2 - player.gety()
		);
		tileMap.update();
		tileMap.fixBounds();
		
		// update enemies
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(
					new Explosion(tileMap, e.getx(), e.gety()));
			}
		}
		
		// update enemy projectiles
		for(int i = 0; i < eprojectiles.size(); i++) {
			EnemyProjectile ep = eprojectiles.get(i);
			ep.update();
			if(ep.shouldRemove()) {
				eprojectiles.remove(i);
				i--;
			}
		}
		
		// update explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
		
		// update teleport
		teleport.update();
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw background
		temple.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw enemies
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		
		// draw enemy projectiles
		for(int i = 0; i < eprojectiles.size(); i++) {
			eprojectiles.get(i).draw(g);
		}
		
		// draw explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).draw(g);
		}
		
		// draw player
		player.draw(g);
		
		// draw teleport
		teleport.draw(g);
		
		// draw hud
		hud.draw(g);
		
		// draw title
		if(title != null) title.draw(g);
		if(subtitle != null) subtitle.draw(g);
		
		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for(int i = 0; i < tb.size(); i++) {
			g.fill(tb.get(i));
		}
		
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) gsm.setPaused(true);
		if(blockInput || player.getHealth() == 0) return;
		player.setUp(Keys.keyState[Keys.UP]);
		player.setLeft(Keys.keyState[Keys.LEFT]);
		player.setDown(Keys.keyState[Keys.DOWN]);
		player.setRight(Keys.keyState[Keys.RIGHT]);
		player.setJumping(Keys.keyState[Keys.BUTTON1]);
		player.setDashing(Keys.keyState[Keys.BUTTON2]);
		if(Keys.isPressed(Keys.BUTTON3)) player.setAttacking();
		if(Keys.isPressed(Keys.BUTTON4)) player.setCharging();
	}

///////////////////////////////////////////////////////
//////////////////// EVENTS
///////////////////////////////////////////////////////
	
	// reset level
	private void reset() {
		player.loseLife();
		player.reset();
		player.setPosition(300, 131);
		populateEnemies();
		blockInput = true;
		eventCount = 0;
		tileMap.setShaking(false, 0);
		eventStart = true;
		eventStart();
		title = new Title(level3.getSubimage(0, 0, 203, 44));
		title.sety(25);
		
	}
	
	// level started
	private void eventStart() {
		eventCount++;
		if(eventCount == 1) {
			tb.clear();
			tb.add(new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT / 2));
			tb.add(new Rectangle(0, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT));
			tb.add(new Rectangle(0, GamePanel.HEIGHT / 2, GamePanel.WIDTH, GamePanel.HEIGHT / 2));
			tb.add(new Rectangle(GamePanel.WIDTH / 2, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT));
		}
		if(eventCount > 1 && eventCount < 60) {
			tb.get(0).height -= 4;
			tb.get(1).width -= 6;
			tb.get(2).y += 4;
			tb.get(3).x += 6;
		}
		if(eventCount == 30) title.begin();
		if(eventCount == 60) {
			eventStart = blockInput = false;
			eventCount = 0;
			tb.clear();
		}
	}
	
	// player has died
	private void eventDead() {
		eventCount++;
		if(eventCount == 1) player.setDead();
		if(eventCount == 60) {
			tb.clear();
			tb.add(new Rectangle(
				GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, 0, 0));
		}
		else if(eventCount > 60) {
			tb.get(0).x -= 6;
			tb.get(0).y -= 4;
			tb.get(0).width += 12;
			tb.get(0).height += 8;
		}
		if(eventCount >= 120) {
			if(player.getLives() == 0) {
				gsm.setState(GameStateManager.MENUSTATE);
				JukeBox.stop("level1");
			}
			else {
				eventDead = blockInput = false;
				eventCount = 0;
				reset();
			}
		}
	}
	
	// earthquake
	private void eventQuake() {
		eventCount++;
		if(eventCount == 1) {
			player.stop();
			player.setPosition(2175, player.gety());
		}
		if(eventCount == 60) {
			player.setEmote(Player.CONFUSED);
		}
		if(eventCount == 120) player.setEmote(Player.NONE);
		if(eventCount == 150) tileMap.setShaking(true, 10);
		if(eventCount == 180) player.setEmote(Player.SURPRISED);
		if(eventCount == 300) {
			player.setEmote(Player.NONE);
			eventQuake = blockInput = false;
			eventCount = 0;
		}
	}
	
	// finished level
	private void eventFinish() {
		eventCount++;
		if(eventCount == 1) {
			JukeBox.play("teleport");
			player.setTeleporting(true);
			player.stop();
		}
		else if(eventCount == 120) {
			tb.clear();
			tb.add(new Rectangle(
				GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, 0, 0));
		}
		else if(eventCount > 120) {
			tb.get(0).x -= 6;
			tb.get(0).y -= 4;
			tb.get(0).width += 12;
			tb.get(0).height += 8;
			JukeBox.stop("teleport");
		}
		if(eventCount == 180) {
			PlayerSave.setHealth(player.getHealth());
			PlayerSave.setLives(player.getLives());
			PlayerSave.setTime(player.getTime());
			gsm.setState(GameStateManager.LEVEL4STATE);
			
			
		}
		
	}

}