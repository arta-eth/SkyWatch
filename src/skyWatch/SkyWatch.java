package skyWatch;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JFrame;

public class SkyWatch implements ActionListener, MouseListener, KeyListener {

	public static SkyWatch skywatch; // Main Class called

	public static final int WIDTH = 800; // Screen Width

	public static final int HEIGHT = 800; // Screen Height

	public static Renderer gamerender; // Renderer Class

	public static Rectangle ship; // Ship Object

	public static Rectangle ship2; // Ship Object (second half) after collision
									// with Missile

	public static Rectangle endurance; // Endurance Object

	public Rectangle welcome; // Welcome Screen Object

	public Rectangle missile; // Missile Object

	public static Rectangle ground; // Ground Object

	public static Rectangle campaignbutton; // Button Object

	public static Rectangle topground; // Top Ground Object

	public static ArrayList<Rectangle> building; // Building Array List Object

	public Rectangle healthBar; // Health Bar Object

	public int ticks, fall; // Value of Ticks & Value of Y Movement

	public static int score; // Value of km travelled

	public static int level = 1; // Value of Level

	public static int health = 200; // Value of Health

	public static boolean gameOver; // Game Over

	public static boolean gameOverMissile = false; // Game Over by missile

	public static boolean started; // Game Started

	public Animations a = new Animations(); // Animations Class called

	public static boolean flash; // Flash from missile collision

	public static int aX = 0, aY = 0; // Animation X & Animation Y

	public static Random rand; // Random for Building Height/WIDTH

	public double rotation; // Rotation of Ship on/off

	public double mrotation; // Rotation of Missile

	public static boolean pause = false; // Pause Game

	public static Random rando = new Random(); // Random for Backgrounds

	int speed = 10; // Ship Speed

	static int bGround; // Background type on Free Mode

	static int background; // Background type on Campaign Mode

	int score1; // Km Travelled during Lvl 1

	int score2; // Km Travelled during Lvl 2

	int score3; // Km Travelled during Lvl 3

	int score4; // Km Travelled during Lvl 4

	public static boolean hScore; // Unimplemented High Score

	public static boolean docking; // Docked With Endurance

	public static boolean missilefire; // Missile Firing

	public static boolean read; // Campaign Mode mission Synopsis

	public static boolean campaignMode; // Campaign Mode

	public static boolean freeMode; // Free Play Mode

	public static boolean endurancemove; // Endurance Moving Forward

	public static boolean hatchopen; // Docking Hatch Open

	public static boolean moveBack; // Endurance Moving Backwards

	public static boolean nextlevel0 = true; // Lvl 1

	public static boolean nextlevel1; // Lvl 2

	public static boolean nextlevel2; // Lvl 3

	public static boolean nextlevel3; // Lvl 4

	public static boolean nextlevel4; // Campaign Finished

	public static boolean levelStart; // Next Mission Starting

	public static boolean instructions; // Instructions Screen

	public static boolean noback = true; // No Background Value

	static JFrame frame = new JFrame(); // Game Frame

	Color color = new Color(1f, 0f, 0f, .5f); // Custom Color(s)

	public SkyWatch() {
		// SETS UP JFRAME

		JFrame frame = new JFrame();
		Timer time = new Timer(20, this);

		gamerender = new Renderer();

		rand = new Random();

		frame.add((Component) gamerender);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(2560, 1800);

		frame.addMouseListener(this);
		frame.addKeyListener(this);
		frame.setResizable(true);
		frame.setTitle("Sky Watch | Copyright. 2017");
		frame.setVisible(true);

		ship = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 110, 30);
		endurance = new Rectangle(ship.x + 1040, HEIGHT / 2 - 85, 120, 55);
		building = new ArrayList<Rectangle>();
		ground = new Rectangle(0, 695, WIDTH - 695, HEIGHT + 800);
		campaignbutton = new Rectangle(WIDTH + 600, 0, 40, 40);
		welcome = new Rectangle(WIDTH, HEIGHT, 0, 0);
		healthBar = new Rectangle(HEIGHT / 2 + 800, WIDTH / 2 - 380, 30, health);
		missile = new Rectangle(-100, HEIGHT / 2, 20, 200);
		topground = new Rectangle(0, 0, WIDTH - 695, HEIGHT + 800);

		addBuilding(true);
		addBuilding(true);
		addBuilding(true);
		addBuilding(true);

		new ImageLoader();

		bGround = rand.nextInt(3);

		pickSong();

		time.start();

	}

	public void addBuilding(boolean start) {
		// ADDS BUILDING ON START

		int space = 500;
		int width = 700;
		int height = 50 + rand.nextInt(200);

		if (start) {

			building.add(new Rectangle(WIDTH + width + building.size() * 300, HEIGHT - height - 120, width, height));
			building.add(new Rectangle(WIDTH + width + (building.size() - 1) * 300, 0, width, HEIGHT - height - space));

		} else {
			building.add(
					new Rectangle(building.get(building.size() - 1).x + 600, HEIGHT - height - 120, width, height));
			building.add(new Rectangle(building.get(building.size() - 1).x, 0, width, HEIGHT - height - space));

		}

	}

	public void paintBuilding(Graphics screen, Rectangle building) {
		// BUILDING FILLS

		screen.setColor(Color.lightGray);
		screen.fillRect(building.x, building.y, building.width, building.height);

	}

	public void thruster() {
		// YMOTION OF THE SHIP

		ship2 = new Rectangle(ship.x, ship.y, 120, 40);

		if (gameOver || levelStart) {

			if (nextlevel1) {
				score1 = score;
			}

			else if (nextlevel2) {

				score2 = score;
			}

			else if (nextlevel3) {

				score3 = score;
			}

			else if (nextlevel4) {

				score4 = score;

			}
			if (!campaignMode) {

				bGround = rand.nextInt(4);
			}

			ship = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 110, 30);
			endurance = new Rectangle(ship.x + 1040, HEIGHT / 2 - 85, 120, 55);
			healthBar = new Rectangle(HEIGHT / 2 + 800, WIDTH / 2 - 380, 30, health);
			missile = new Rectangle(WIDTH / 2 + 300, HEIGHT / 2 + 450, missile.width, missile.height);
			missile.x = -100;
			missile.y = HEIGHT / 2;
			ground = new Rectangle(0, 695, WIDTH - 695, HEIGHT + 800);
			building.clear();
			health = 200;
			fall = 0;
			score = 0;
			background = rando.nextInt(4);

			addBuilding(true);
			addBuilding(true);
			addBuilding(true);
			addBuilding(true);

			levelStart = false;
			gameOver = false;
			gameOverMissile = false;
			a.stat = false;
			endurancemove = false;
			moveBack = false;
			hatchopen = false;
			missilefire = false;
			pause = false;

			a.sX = 0;
			a.sY = 0;

		}

		if (!started) {

			started = true;

		}

		else if (!gameOver) {

			if (fall > 0) {

				fall = 0;
			}

			fall -= 9;

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ACTION PERFORMED AFTER CLICKING SPACE/MOUSEBUTTON

		if (rotation >= 0) {

			speed = 10;
		}

		else if (rotation < 0) {

			speed = 35;

		}
		ticks++;

		if (started) {

			for (int i = 0; i < building.size(); i++) {
				Rectangle build = building.get(i);

				build.x -= speed;
			}

			if (ticks % 2 == 0 && fall < 15) {

				fall += 1;

			}

			for (int i = 0; i < building.size(); i++) {
				Rectangle build = building.get(i);

				if (build.x + build.width < 0) {

					building.remove(build);

					if (!gameOver && !started) {
						building.clear();

					}

					else {

					}

					if (build.y == 0) {

						addBuilding(false);
					}

				}

			}

			ship.y += fall;

			if (gameOver) {

				ship2.y += fall;
			}

			for (Rectangle build : building) {

				if (!gameOver && started) {

					if (build.y == 0 && ship.x + ship.width > build.x + build.width - speed / 2
							&& ship.x + ship.width < build.x + build.width + speed && !docking) {
						score = score + 1;
					}

					else {

					}

					if (ship.x == build.x) {

					}

					if (build.intersects(ship) && !docking) {

						health = health - 1 * 4;

						if (health <= 0) {

							gameOver = true;

						}

						if (gameOver) {

							if (ship.y < build.height) {

								ship.y = build.height;

							}

							if (ship.x <= build.x || ship2.x <= build.x) {

								ship.x = build.x - ship.width;
								ship2.x = build.x - ship2.width;

							}
						}

						else {

							if (build.y != 0) {

								ship.y = build.y - ship.height;

							}

						}

					}

				}

				if (ship.y > HEIGHT - 120 || ship.y < 0) {

					gameOver = true;

				}

				if (ship.y + fall >= HEIGHT - 120) {

					ship.y = HEIGHT - 120 - ship.height / 2;

				}

				if (ship2.y + fall >= HEIGHT - 120) {

					ship2.x = ship.x - 60;
					ship2.y = HEIGHT - 120 - ship2.height / 2;

				}

			}

			if (!gameOver && !docking && started && score >= 15) {

				int mspeed = 30;

				if (nextlevel0) {

					mspeed = 70;

				}

				else if (nextlevel1) {

					mspeed = 100;
				}

				else if (nextlevel2) {

					mspeed = 120;

				}

				else if (nextlevel3) {

					mspeed = 130;
				}
				missile.y = HEIGHT / 2;

				if (score % 15 == 0 && missile.x == -100) {
					missilefire = true;

					if (missilefire) {

						Music1.play("src/sounds/missilefire.wav");
						missilefire = false;
					}

				}

				missile.x += mspeed;

				mrotation = 90;

				// Add Missile Start

				if (missile.x + missile.y > 1500 + ship.x && score % 20 == 0) {

					Music1.play("src/sounds/missilefire.wav");
					missile.x = -100;
					missile.y = HEIGHT / 2;

				}

				if (missile.intersects(ship)) {

					flash = true;

				}

			}

		}

		gamerender.repaint();

	}

	public void repaint(Graphics screen) {
		// ALL TEXTURES FOR GAME

		// Background

		screen.setColor(Color.BLACK);
		screen.fillRect(0, 0, WIDTH + 800, HEIGHT);

		if (noback) {

			if ((nextlevel0 && !freeMode || bGround == 0 && !campaignMode) && !nextlevel1) {

				background = 0;
				screen.drawImage(ImageLoader.background1, 0, 0, WIDTH + 800, HEIGHT, null);
				if (started || gameOver || pause) {
					for (Rectangle building : building) {
						paintBuilding(screen, building);
						screen.drawImage(ImageLoader.building1, building.x, building.y, building.width, building.height,
								null);

					}

				}

			} else if ((nextlevel1 && !freeMode || bGround == 1 && !campaignMode) && !nextlevel2) {

				background = 1;
				screen.drawImage(ImageLoader.background2, 0, 0, WIDTH + 800, HEIGHT, null);
				if (started || gameOver || pause) {
					for (Rectangle building : building) {
						paintBuilding(screen, building);
						screen.drawImage(ImageLoader.building2, building.x, building.y, building.width, building.height,
								null);

					}

				}

			} else if ((nextlevel2 && !freeMode || bGround == 2 && !campaignMode) && !nextlevel3) {

				background = 2;
				screen.drawImage(ImageLoader.background3, 0, 0, WIDTH + 800, HEIGHT, null);
				if (started || gameOver || pause) {
					for (Rectangle building : building) {
						paintBuilding(screen, building);
						screen.drawImage(ImageLoader.building3, building.x, building.y, building.width, building.height,
								null);

					}

				}

			} else if ((nextlevel3 && !freeMode || bGround == 3 && !campaignMode) && !nextlevel4) {

				background = 3;
				a.background(screen);
				screen.setColor(color = new Color(0.46f, 0.46f, 0f, 0.16f).brighter());
				screen.fillRect(0, 0, WIDTH + 800, HEIGHT);
				if (started || gameOver || pause) {
					for (Rectangle building : building) {
						paintBuilding(screen, building);
						screen.drawImage(ImageLoader.building4, building.x, building.y, building.width, building.height,
								null);

					}

				}

			}
		}

		for (int i = 0; i < building.size(); i++) {

			Rectangle build = building.get(i);

			if (build.intersects(missile) || endurance.intersects(build)) {

				build.y += speed / 35 * 4;

				if (background == 0) {

					screen.drawImage(ImageLoader.buildexplode1, build.x, build.y, build.width, build.height, null);

				}

				if (background == 1) {

					screen.drawImage(ImageLoader.buildexplode2, build.x, build.y, build.width, build.height, null);
				}

				if (background == 2) {

					screen.drawImage(ImageLoader.buildexplode3, build.x, build.y, build.width, build.height, null);
				}

				if (background == 3) {

					screen.drawImage(ImageLoader.buildexplode4, build.x, build.y, build.width, build.height, null);
				}
			}

		}

		// top ground

		// underground
		screen.setColor(Color.gray);
		screen.fillRect(0, HEIGHT - 120, WIDTH + 800, 150);
		screen.drawImage(ImageLoader.gLayer, ground.x, ground.y, ground.height, ground.width, null);

		ground.x -= speed / 2;

		if (ground.x <= 0 - ground.width) {

			ground.x = 0;

		}

		screen.setColor(Color.gray);

		screen.drawImage(ImageLoader.tLayer, topground.x, topground.y, topground.height, topground.width, null);

		topground.x -= speed / 2;

		if (topground.x <= 0 - topground.width) {

			topground.x = 0;

		}

		screen.drawImage(ImageLoader.badge, 20, 50, 100, 140, null);

		screen.setColor(Color.darkGray.darker().darker().darker().darker());
		screen.fillRect(0, HEIGHT - 120, WIDTH + 800, 20);

		screen.setColor(Color.GREEN.darker().darker());
		screen.setFont(new Font("AlternateGothic2_BT", Font.BOLD, 20));
		screen.drawImage(ImageLoader.health, healthBar.x, healthBar.y, 200, healthBar.width, null);
		screen.setColor(color = new Color(0f, 0.5f, 0f, 0.68f).brighter());
		screen.fillRect(healthBar.x, healthBar.y, health, healthBar.width);

		if (gameOver) {
			screen.setColor(Color.RED);

			if (health < 100 && health > 10) {

				screen.drawString("0" + String.valueOf(health) + " / 200", WIDTH / 2 + 680, 45);
			}

			else if (health < 10 && health > 0) {

				screen.drawString("00" + String.valueOf(health) + " / 200", WIDTH / 2 + 680, 45);
			}

			else if (health <= 0) {

				screen.drawString("000 / 200", WIDTH / 2 + 680, 45);

			}

			else {

				screen.drawString(String.valueOf(health) + " / 200", WIDTH / 2 + 680, 45);
			}
		}

		else {

			if (health < 100 && health > 10) {

				screen.drawString("0" + String.valueOf(health) + " / 200", WIDTH / 2 + 680, 45);
			}

			else if (health < 10 && health > 0) {

				screen.drawString("00" + String.valueOf(health) + " / 200", WIDTH / 2 + 680, 45);
			}

			else if (health <= 0) {

				screen.drawString("000 / 200", WIDTH / 2 + 680, 45);

			}

			else {

				screen.drawString(String.valueOf(health) + " / 200", WIDTH / 2 + 680, 45);
			}
		}

		// Ship rotation on boost
		Graphics2D screen2 = (Graphics2D) screen;
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(rotation), ship.x + ship.getHeight() / 2, ship.y + ship.getHeight() / 2);
		AffineTransform old = screen2.getTransform();
		screen2.transform(transform);

		if (started && !gameOverMissile || pause || docking) {

			screen2.drawImage(ImageLoader.falcon, ship.x, ship.y, ship.width, ship.height, null);

		}

		// Missile Rotation
		screen.setColor(Color.CYAN);
		Graphics2D screen3 = (Graphics2D) screen2;
		AffineTransform mtransform = new AffineTransform();
		mtransform.rotate(Math.toRadians(mrotation), missile.x - 2 + missile.getHeight() / 2,
				missile.y + missile.getHeight() / 2);
		AffineTransform oldm = screen3.getTransform();
		screen3.transform(mtransform);

		if (started && score >= 15 && !gameOver && !docking || pause) {

			screen3.drawImage(ImageLoader.missiles, missile.x, missile.y, missile.width + 40, missile.height + 150,
					null);

		}

		screen3.setTransform(oldm);

		if (missile.x == -100 && !docking) {

			screen3.drawImage(ImageLoader.missiles, missile.x, missile.y, missile.width + 40, missile.height + 150,
					null);
		}

		int loc = 0;

		if (nextlevel0) {

			loc = 60;
		}

		else if (nextlevel1) {

			loc = 120;
		}

		else if (nextlevel2) {

			loc = 120;
		}

		else if (nextlevel3) {

			loc = 150;
		}

		if (score >= loc && campaignMode) { // ENDURANCE INTERVALS

			screen.drawImage(ImageLoader.end, endurance.x, endurance.y - 50, endurance.height * 2, endurance.width * 2,
					null);

			if (started && !pause || gameOver && !pause) {

				speed = 10;

				if (endurance.x >= ship.x + 900 && !docking) {
					moveBack = true;
				}

				if (moveBack) {

					endurance.x -= speed;
				}
				if (ship.intersects(endurance) && !gameOver) {

					hatchopen = true;
					docking = true;

				}

				if (endurance.x + endurance.width < -200 && !docking) {

					moveBack = false;

				}

				if (!moveBack) {
					if (!docking) {
						endurance.x += speed * 2;

					}
				}

				if (docking && started || docking && pause) {

					endurancemove = true;
					screen2.setTransform(old);
					ship.x = endurance.x + 40;
					ship.y = endurance.y + 45;
					endurance.x += speed * 2;

					// ADD MORE LEVELS
				}

				if (hatchopen && (nextlevel0 || nextlevel1 || nextlevel2 || nextlevel3 || nextlevel4)) {

					Music1.play("src/sounds/hatch.wav");
					hatchopen = false;

					if (endurancemove) {
						Music1.play("src/sounds/endurance.wav");
						endurancemove = false;
					}
				}
			}
		}

		if (flash) {

			Music1.play("src/sounds/missilehit.wav");
			gameOverMissile = true;

			flash = false;
		}

		if (!(rotation == 0) && !(rotation == 40) && !docking) {

			if (started && !gameOverMissile) {
				screen2.drawImage(ImageLoader.animfalcon, ship.x, ship.y, ship.width, ship.height, null);
			}
		}

		if (gameOverMissile) {

			screen2.drawImage(ImageLoader.half1, ship.x, ship.y, ship.width, ship.height, null);
			screen2.drawImage(ImageLoader.half2, ship2.x, ship2.y, ship2.width, ship2.height, null);
			a.explode(screen2);

			gameOver = true;

		} else {
			a.aX = 0;
			a.aY = 0;
		}

		if (gameOver && !gameOverMissile) {

			screen2.drawImage(ImageLoader.blownfalcon, ship.x, ship.y, ship.width, ship.height, null);

		}

		screen2.setTransform(old);

		if (!gameOver && !started && !docking) {

			if (!instructions) {

				screen.drawImage(ImageLoader.welcomescreen, 0, 0, WIDTH + 650, HEIGHT, null);
				screen.setColor(color = new Color(0f, 0.46f, 0.5f, 0.38f).brighter());
				screen.fillRect(campaignbutton.x / 2 - 80, campaignbutton.y + 470, campaignbutton.height + 235,
						campaignbutton.width + 20);
				screen.setColor(Color.RED.darker().darker());
				screen.setFont(new Font("AlternateGothic2_BT", 1, 25));
				screen.drawString("Instructions", campaignbutton.x / 2 - 20, campaignbutton.y + 510);

			}

			if (campaignMode && !read) {

				screen.drawImage(ImageLoader.welcomescreenstory, 0, 0, WIDTH + 650, HEIGHT, null);
				screen.setColor(color = new Color(0f, 0.26f, 0.3f, 0.38f).brighter());
				screen.fillRect(campaignbutton.x / 2 - 200, campaignbutton.y + 500, campaignbutton.height + 480,
						campaignbutton.width + 20);
				screen.setColor(Color.RED.darker().darker());
				screen.setFont(new Font("AlternateGothic2_BT", 1, 35));

				if (!pause) {

					screen.drawString("Start Game", campaignbutton.x / 2 - 50, campaignbutton.y + 545);
				}

				else if (pause) {

					screen.drawString("Resume Game", campaignbutton.x / 2 - 65, campaignbutton.y + 545);
				}

				screen.setColor(color = new Color(0f, 0.46f, 0.5f, 0.38f).brighter());
				screen.fillRect(10, 10, 140, 40);
				screen.setColor(Color.RED.darker().darker());
				screen.setFont(new Font("AlternateGothic2_BT", 1, 25));

				if (!pause) {

					screen.drawString("Back", 45, 40);
				}

				if (pause) {

					screen.setFont(new Font("AlternateGothic2_BT", 1, 15));
					screen.drawString("Back To Menu", 25, 35);
				}

			}

			else if (instructions) {

				screen.drawImage(ImageLoader.instru, 0, 0, WIDTH + 650, HEIGHT, null);
				screen.setColor(color = new Color(0f, 0.46f, 0.5f, 0.38f).brighter());
				screen.fillRect(10, 10, 140, 40);
				screen.setColor(Color.RED.darker().darker());
				screen.setFont(new Font("AlternateGothic2_BT", 1, 25));
				screen.drawString("Back", 45, 40);

			}

			else if (freeMode) {

				screen.drawImage(ImageLoader.welcomescreenfree, 0, 0, WIDTH + 650, HEIGHT, null);
				screen.setColor(color = new Color(0f, 0.26f, 0.3f, 0.38f).brighter());
				screen.fillRect(campaignbutton.x / 2 - 200, campaignbutton.y + 500, campaignbutton.height + 480,
						campaignbutton.width + 20);
				screen.setColor(Color.RED.darker().darker());
				screen.setFont(new Font("AlternateGothic2_BT", 1, 35));

				if (!pause) {

					screen.drawString("Start Game", campaignbutton.x / 2 - 50, campaignbutton.y + 545);
				}

				else if (pause) {

					screen.drawString("Resume Game", campaignbutton.x / 2 - 65, campaignbutton.y + 545);
				}

				screen.setColor(color = new Color(0f, 0.46f, 0.5f, 0.38f).brighter());
				screen.fillRect(10, 10, 140, 40);
				screen.setColor(Color.RED.darker().darker());
				screen.setFont(new Font("AlternateGothic2_BT", 1, 25));

				if (!pause) {

					screen.drawString("Back", 45, 40);
				}

				if (pause) {

					screen.setFont(new Font("AlternateGothic2_BT", 1, 15));
					screen.drawString("Back To Menu", 25, 35);
				}

			}

			if (read) {

				if (nextlevel0) {

					// MISSION 1 SPLASH SCREEN

					screen.drawImage(ImageLoader.desc, 0, 0, WIDTH + 650, HEIGHT, null);
					screen.setColor(color = new Color(0f, 0.5f, 0.5f, 0.4f).brighter());
					screen.fillRect(330, 260, WIDTH + 40, HEIGHT - 545);
					screen.setColor(Color.CYAN.darker());
					screen.setFont(new Font("AlternateGothic2_BT", 1, 70));
					screen.drawString("Mission 1 ", 550, HEIGHT / 2 - 200);
					screen.setFont(new Font("AlternateGothic2_BT", 1, 30));
					screen.setColor(Color.CYAN.brighter());
					screen.drawString("The Endurance crew have managed to retrieve data", 350, HEIGHT / 2 - 100);
					screen.drawString("from the alien planet Barium V, however, they are", 350, HEIGHT / 2 - 60);
					screen.drawString("being chased by remote-controlled missiles. You ", 350, HEIGHT / 2 - 20);
					screen.drawString("must navigate through the collapsing buildings  ", 350, HEIGHT / 2 + 20);
					screen.drawString("of Barium V and dock with the Endurance before   ", 350, HEIGHT / 2 + 60);
					screen.drawString("your Ranger gets shot down!", 350, HEIGHT / 2 + 100);

				}

				else if (nextlevel1) {

					// MISSION 2 SPLASH SCREEN

					screen.drawImage(ImageLoader.desc, 0, 0, WIDTH + 650, HEIGHT, null);
					screen.setColor(color = new Color(0f, 0.5f, 0.5f, 0.4f).brighter());
					screen.fillRect(330, 260, WIDTH + 40, HEIGHT - 545);
					screen.setColor(Color.CYAN.darker());
					screen.setFont(new Font("AlternateGothic2_BT", 1, 70));
					screen.drawString("Mission 2", 550, HEIGHT / 2 - 200);
					screen.setFont(new Font("AlternateGothic2_BT", 1, 30));
					screen.setColor(Color.CYAN.brighter());
					screen.drawString("The Endurance crew have left the planet's orbit. ", 350, HEIGHT / 2 - 100);
					screen.drawString("However, they have encountered a space station of", 350, HEIGHT / 2 - 60);
					screen.drawString("the Mushrom aliens, which are now trying to shoot", 350, HEIGHT / 2 - 20);
					screen.drawString("them. The Ranger has detatched to deal with the  ", 350, HEIGHT / 2 + 20);
					screen.drawString("problem, however, it must now find a way back to ", 350, HEIGHT / 2 + 60);
					screen.drawString("the Endurance to escape the dreaded solar system!", 350, HEIGHT / 2 + 100);

				}

				else if (nextlevel2) {

					// MISSION 3 SPLASH SCREEN

					screen.drawImage(ImageLoader.desc, 0, 0, WIDTH + 650, HEIGHT, null);
					screen.setColor(color = new Color(0f, 0.5f, 0.5f, 0.4f).brighter());
					screen.fillRect(330, 260, WIDTH + 40, HEIGHT - 545);
					screen.setColor(Color.CYAN.darker());
					screen.setFont(new Font("AlternateGothic2_BT", 1, 70));
					screen.drawString("Mission 3", 550, HEIGHT / 2 - 200);
					screen.setFont(new Font("AlternateGothic2_BT", 1, 30));
					screen.setColor(Color.CYAN.brighter());
					screen.drawString("After leaving the Mushroominian System, the Ranger ", 350, HEIGHT / 2 - 100);
					screen.drawString("and Endurance have made their way back to Earth,", 350, HEIGHT / 2 - 60);
					screen.drawString("and much to their dismay, the Volkrinians have  ", 350, HEIGHT / 2 - 20);
					screen.drawString("completely destroyed the Moon, and", 350, HEIGHT / 2 + 20);
					screen.drawString("Ranger I, while investigating, has been targeted.", 350, HEIGHT / 2 + 60);
					screen.drawString("It must make it's way back to the Endurance quick.", 350, HEIGHT / 2 + 100);

				}

				else if (nextlevel3) {

					// MISSION 4 SPLASH SCREEN

					screen.drawImage(ImageLoader.desc, 0, 0, WIDTH + 650, HEIGHT, null);
					screen.setColor(color = new Color(0f, 0.5f, 0.5f, 0.4f).brighter());
					screen.fillRect(330, 260, WIDTH + 40, HEIGHT - 545);
					screen.setColor(Color.CYAN.darker());
					screen.setFont(new Font("AlternateGothic2_BT", 1, 70));
					screen.drawString("Mission 4", 550, HEIGHT / 2 - 200);
					screen.setFont(new Font("AlternateGothic2_BT", 1, 30));
					screen.setColor(Color.CYAN.brighter());
					screen.drawString("At one last stand against the Musroom aliens, the", 350, HEIGHT / 2 - 100);
					screen.drawString("Endurance crew must evade the alien missiles for ", 350, HEIGHT / 2 - 60);
					screen.drawString("the black hole Goosh to be able to suck in   ", 350, HEIGHT / 2 - 20);
					screen.drawString("the aliens. The Ranger has left as a distraction,", 350, HEIGHT / 2 + 20);
					screen.drawString("and it must now make it back to the ship and fly ", 350, HEIGHT / 2 + 60);
					screen.drawString("away so that the Endurance can escape and survive!", 350, HEIGHT / 2 + 100);

				}

				else if (nextlevel4) {

					// Campaign mode complete

					screen.drawImage(ImageLoader.gameover, 0, 0, WIDTH + 650, HEIGHT, null);
					screen.setColor(color = new Color(0f, 0.5f, 0.5f, 0.2f).brighter());
					screen.fillRect(0, 460, WIDTH + 700, HEIGHT - 545);
					screen.setColor(Color.WHITE.darker());
					screen.setFont(new Font("AlternateGothic2_BT", 1, 70));
					score4 = score;
					screen.drawString("Total KM Travelled: " + (score1 + score2 + score3 + score4), 350,
							HEIGHT / 2 + 250);
					screen.setColor(Color.CYAN.brighter());
					screen.setColor(color = new Color(0f, 0.6f, 0.6f, 0.4f).brighter());
					screen.fillRect(campaignbutton.x / 2 - 230, campaignbutton.y + 500, campaignbutton.height + 480,
							campaignbutton.width + 20);
					screen.setFont(new Font("AlternateGothic2_BT", 1, 35));
					screen.setColor(Color.WHITE.darker());
					screen.drawString("Back To Menu", campaignbutton.x / 2 - 100, campaignbutton.y + 545);

				}

				if (!nextlevel4) {
					screen.setColor(color = new Color(0f, 0.26f, 0.3f, 0.38f).brighter());
					screen.fillRect(campaignbutton.x / 2 - 200, campaignbutton.y + 210, campaignbutton.height + 410,
							campaignbutton.width);
					screen.setColor(Color.RED.darker().darker());
					screen.setFont(new Font("AlternateGothic2_BT", 1, 30));
					screen.drawString("Start Game", campaignbutton.x / 2 - 50, campaignbutton.y + 240);
				}

			}

		}

		if (!started && !read && !gameOver && !docking && !campaignMode && !freeMode && !instructions) {

			screen.setFont(new Font("AlternateGothic2_BT", 1, 20));

			screen.setColor(color = new Color(0f, 0.5f, 0.5f, 0.58f).brighter());
			screen.fillRect(campaignbutton.x / 2 - 200, campaignbutton.y + 300, campaignbutton.height + 480,
					campaignbutton.width + 20);
			screen.fillRect(campaignbutton.x / 2 - 200, campaignbutton.y + 400, campaignbutton.height + 480,
					campaignbutton.width + 20);

			screen.setColor(Color.RED.darker().darker());
			screen.setFont(new Font("AlternateGothic2_BT", 1, 35));
			screen.drawString("CAMPAIGN MODE", campaignbutton.x / 2 - 100, campaignbutton.y + 345);
			screen.drawString("FREE PLAY MODE", campaignbutton.x / 2 - 95, campaignbutton.y + 445);

		}

		if (gameOver) {

			screen.setColor(Color.RED.darker());
			screen.setFont(new Font("AlternateGothic2_BT", 1, 100));
			screen.drawString("Game Over", WIDTH / 2 + 50, HEIGHT / 2);
			screen.setFont(new Font("AlternateGothic2_BT", 1, 30));
			screen.drawString("km travelled: " + String.valueOf(score), WIDTH / 2 - 350, 40);

		}

		if (!gameOver && started) {

			screen.setColor(Color.CYAN.darker());
			screen.setFont(new Font("Dead_Kansas", 1, 100));
			screen.setFont(new Font("AlternateGothic2_BT", 1, 30));
			screen.drawString("km travelled: " + String.valueOf(score), WIDTH / 2 - 350, 40);

		}

		if (started && docking && endurance.x > (WIDTH + 900) - endurance.width) {

			if (nextlevel0) {

				nextLevel1();
			}

			else if (nextlevel1) {

				nextLevel2();
			}

			else if (nextlevel2) {

				nextLevel3();
			}

			else if (nextlevel3) {

				nextLevel4();
			}

		}

		if (!started && docking) {

			if (nextlevel1 && !started && !read) {

				screen.setColor(Color.BLACK);
				screen.drawImage(ImageLoader.backover, 0, 15, WIDTH + 640, HEIGHT - 20, null);
				a.endturn(screen2);
				a.com(screen2);

				if (a.stat) {

					screen.drawImage(ImageLoader.com1stat, 500, 100, 400, 200, null);
				}

				screen.setColor(color = new Color(0f, 0.5f, 0.5f, 0.18f).brighter());
				screen.fillRect(0, 0, WIDTH + 800, HEIGHT);
				screen.setColor(Color.CYAN.darker().darker());
				screen.setFont(new Font("Dead_Kansas", 1, 90));

			}

			if (nextlevel2 && !started && !read) {

				screen.setColor(Color.BLACK);
				screen.drawImage(ImageLoader.backover, 0, 15, WIDTH + 640, HEIGHT - 20, null);
				a.endturn(screen2);
				a.com(screen2);

				if (a.stat) {

					screen.drawImage(ImageLoader.com2stat, 500, 100, 400, 200, null);
				}

				screen.setColor(color = new Color(0f, 0.5f, 0.5f, 0.18f).brighter());
				screen.fillRect(0, 0, WIDTH + 800, HEIGHT);
				screen.setColor(Color.CYAN.darker().darker());
				screen.setFont(new Font("Dead_Kansas", 1, 90));

			}

			if (nextlevel3 && !started && !read) {

				screen.setColor(Color.BLACK);
				screen.drawImage(ImageLoader.backover, 0, 15, WIDTH + 640, HEIGHT - 20, null);
				a.endturn(screen2);
				a.com(screen2);

				if (a.stat) {

					screen.drawImage(ImageLoader.com3stat, 500, 100, 400, 200, null);
				}

				screen.setColor(color = new Color(0f, 0.5f, 0.5f, 0.18f).brighter());
				screen.fillRect(0, 0, WIDTH + 800, HEIGHT);
				screen.setColor(Color.CYAN.darker().darker());
				screen.setFont(new Font("Dead_Kansas", 1, 90));

			}

			if (nextlevel4 && !started && !read) {

				screen.setColor(Color.BLACK);
				screen.drawImage(ImageLoader.backover, 0, 15, WIDTH + 640, HEIGHT - 20, null);
				a.endturn(screen2);
				a.com(screen2);

				if (a.stat) {

					screen.drawImage(ImageLoader.com4stat, 500, 100, 400, 200, null);
				}

				screen.setColor(color = new Color(0f, 0.5f, 0.5f, 0.18f).brighter());
				screen.fillRect(0, 0, WIDTH + 800, HEIGHT);
				screen.setColor(Color.CYAN.darker().darker());
				screen.setFont(new Font("Dead_Kansas", 1, 90));

			}

			screen.setColor(color = new Color(0f, 0.26f, 0.3f, 0.18f).brighter());
			screen.fillRect(campaignbutton.x / 2 - 210, campaignbutton.y + 300, campaignbutton.height + 410,
					campaignbutton.width);
			screen.setColor(Color.RED.darker().darker());
			screen.setFont(new Font("AlternateGothic2_BT", 1, 28));
			screen.drawString("Next Level", campaignbutton.x / 2 - 58, campaignbutton.y + 330);

		}

		if (started && !docking) {

			screen.setColor(color = new Color(0f, 0.46f, 0.5f, 0.38f).brighter());
			screen.fillRect(320, 10, 140, 40);
			screen.setColor(Color.RED.darker().darker());
			screen.setFont(new Font("AlternateGothic2_BT", 1, 25));
			screen.drawString("PAUSE", 350, 40);

		}

	}

	/*
	 * MAIN PROGRAM EXECUTION METHOD
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public static void main(String args[]) {

		skywatch = new SkyWatch();

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

		int x = e.getX();

		int y = e.getY();

		int xr = e.getX();

		int yr = e.getY();

		int xi = e.getX();

		int yi = e.getY();

		int xb = e.getX();

		int yb = e.getY();

		int xp = e.getX();

		int yp = e.getY();

		int xm = e.getX();

		int ym = e.getY();

		// 12,35
		// 149,73

		System.out.println(x + "," + y);

		if (x >= 501 && x <= 1019 && y >= 325 && y <= 383 && !started && !gameOver && nextlevel0 && !freeMode && !read
				&& !nextlevel4) {

			campaignMode = true;
			freeMode = false;

		}

		else if (xr >= 501 && xr <= 1020 && yr >= 525 && yr <= 583
				&& (campaignMode && !started && !freeMode && !docking && !nextlevel4)) {

			if (!pause) {

				read = true;

			}

			// 470,525
			// 989,583

			else {
				pause = false;
				started = true;
			}
		}

		else if (xm >= 470 && xm <= 989 && ym >= 525 && ym <= 583 && (campaignMode && nextlevel4)) {

			read = false;

			nextlevel4 = false;
			nextlevel3 = false;
			nextlevel2 = false;
			nextlevel1 = false;
			nextlevel0 = true;
			started = false;
			campaignMode = false;

		}

		else if (xr >= 501 && xr <= 1020 && yr >= 525 && yr <= 583 && freeMode && !started && !campaignMode
				&& !nextlevel4) {

			started = true;

		}

		else if (x >= 501 && x <= 1019 && y >= 425 && y <= 483 && !started && !gameOver && !campaignMode
				&& !nextlevel4) {

			freeMode = true;
			levelStart = true;
			campaignMode = false;

		}

		else if (xi >= 622 && xi <= 894 && yi >= 495 && yi <= 550 && !started && !gameOver && !nextlevel4) {

			instructions = true;

		}

		else if (xb >= 12 && xb <= 149 && yb >= 35 && yb <= 73 && !started && !gameOver
				&& (instructions || campaignMode || freeMode) && !nextlevel4) {

			instructions = false;
			campaignMode = false;
			freeMode = false;
			pause = false;

		}

		else if (xp >= 321 && xp <= 459 && yp >= 35 && yp <= 73 && !gameOver && (campaignMode || freeMode)
				&& !docking) {

			started = false;
			pause = true;

		}

		else if (!read && !started && docking) {

			read = true;
			docking = false;
		}

		else if (e.getButton() == MouseEvent.BUTTON1 && read && campaignMode && !freeMode && !started) {

			read = false;
			levelStart = true;
			started = true;

			if (nextlevel4) {

				started = false;
			}

			Music1.play("src/sounds/blast.wav");

			rotation = 0;

		}

		else if (e.getButton() == MouseEvent.BUTTON1 && !read && freeMode && !campaignMode && !started) {

			campaignMode = false;
			started = true;
			Music1.play("src/sounds/blast.wav");
			rotation = 0;
			bGround = rand.nextInt(4);

		}

		if ((campaignMode || freeMode) && !read && !docking && started) {

			rotation = 0;
			Music1.play("src/sounds/blast.wav");
			thruster();

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			if ((campaignMode || freeMode) && !read && started) {

				rotation = -7;
				Music1.play("src/sounds/blast.wav");
				thruster();
				Music1.clip.stop();

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public static void nextLevel1() {

		nextlevel1 = true;
		nextlevel0 = false;
		started = false;

	}

	public static void nextLevel2() {

		nextlevel2 = true;
		nextlevel1 = false;
		started = false;

	}

	public static void nextLevel3() {

		nextlevel3 = true;
		nextlevel2 = false;
		started = false;

	}

	public static void nextLevel4() {

		nextlevel4 = true;
		nextlevel3 = false;
		started = false;

	}

	public static void pickSong() {

		Music2.play("src/sounds/level4.wav");

	}

}
