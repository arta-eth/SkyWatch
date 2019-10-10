package skyWatch;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	public static BufferedImage background1 = null;
	public static BufferedImage background2 = null;
	public static BufferedImage background3 = null;
	public static BufferedImage background4 = null;
	public static BufferedImage falcon = null;
	public static BufferedImage building1 = null;
	public static BufferedImage building2 = null;
	public static BufferedImage building3 = null;
	public static BufferedImage building4 = null;
	public static BufferedImage animfalcon = null;
	public static BufferedImage gLayer = null;
	public static BufferedImage welcomescreen = null;
	public static BufferedImage welcomescreenfree = null;
	public static BufferedImage welcomescreenstory = null;
	public static BufferedImage blownfalcon = null;
	public static BufferedImage tLayer = null;
	public static BufferedImage missiles = null;
	public static BufferedImage half1 = null;
	public static BufferedImage half2 = null;
	public static BufferedImage explosion = null;
	public static BufferedImage buildexplode1 = null;
	public static BufferedImage buildexplode2 = null;
	public static BufferedImage buildexplode3 = null;
	public static BufferedImage buildexplode4 = null;
	public static BufferedImage mLauncher = null;
	public static BufferedImage health = null;
	public static BufferedImage end = null;
	public static BufferedImage desc = null;
	public static BufferedImage badge = null;
	public static BufferedImage instru = null;
	public static BufferedImage explode = null;
	public static BufferedImage endturn = null;
	public static BufferedImage backover = null;
	public static BufferedImage com1 = null;
	public static BufferedImage com1stat = null;
	public static BufferedImage com2stat = null;
	public static BufferedImage com3stat = null;
	public static BufferedImage com4stat = null;
	public static BufferedImage gameover = null;

	final static int width = 10;
	final static int height = 10;
	final static int rows = 5;
	final static int cols = 5;

	public ImageLoader() {
		try {
			background1 = ImageIO.read(new File("src/textures/back1.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			background2 = ImageIO.read(new File("src/textures/back2.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			background3 = ImageIO.read(new File("src/textures/back3.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			background4 = ImageIO.read(new File("src/textures/back4.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			falcon = ImageIO.read(new File("src/textures/bfi.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			building1 = ImageIO.read(new File("src/textures/building1.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			buildexplode1 = ImageIO.read(new File("src/textures/building1explode.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			buildexplode2 = ImageIO.read(new File("src/textures/building2explode.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			buildexplode3 = ImageIO.read(new File("src/textures/building3explode.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			buildexplode4 = ImageIO.read(new File("src/textures/building4explode.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			building2 = ImageIO.read(new File("src/textures/building2.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			building3 = ImageIO.read(new File("src/textures/building3.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			building4 = ImageIO.read(new File("src/textures/building4.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			animfalcon = ImageIO.read(new File("src/textures/bfianim.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			gLayer = ImageIO.read(new File("src/textures/ground.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			tLayer = ImageIO.read(new File("src/textures/topground.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			welcomescreen = ImageIO.read(new File("src/textures/title.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			welcomescreenfree = ImageIO.read(new File("src/textures/titlefree.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			welcomescreenstory = ImageIO.read(new File("src/textures/titlestory.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			blownfalcon = ImageIO.read(new File("src/textures/bfiexplode.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			missiles = ImageIO.read(new File("src/textures/missile.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			half1 = ImageIO.read(new File("src/textures/bfiairexplode1.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			half2 = ImageIO.read(new File("src/textures/bfiairexplode2.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			explosion = ImageIO.read(new File("src/textures/explode.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			mLauncher = ImageIO.read(new File("src/textures/missilelauncher.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}
		try {
			health = ImageIO.read(new File("src/textures/health.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			end = ImageIO.read(new File("src/textures/endurance.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			desc = ImageIO.read(new File("src/textures/wordpage.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			badge = ImageIO.read(new File("src/textures/badge.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			instru = ImageIO.read(new File("src/textures/instructions.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			explode = ImageIO.read(new File("src/textures/exploder.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			endturn = ImageIO.read(new File("src/textures/enduranceturn.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			backover = ImageIO.read(new File("src/textures/back.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			com1 = ImageIO.read(new File("src/textures/complete1.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			com1stat = ImageIO.read(new File("src/textures/back1stat.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			com2stat = ImageIO.read(new File("src/textures/back2stat.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			com3stat = ImageIO.read(new File("src/textures/back3stat.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			com4stat = ImageIO.read(new File("src/textures/back4stat.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			gameover = ImageIO.read(new File("src/textures/beat game.png"));

		} catch (IOException e) {
			e.printStackTrace();

		}

	}
}
