package skyWatch;

import java.awt.Graphics;

public class Animations {

	int aX, aY, eX, eY, bX, bY, sX, sY;
	public boolean stat;

	public void explode(Graphics g) {
		
		g.drawImage(ImageLoader.explode, SkyWatch.ship.x - 50, SkyWatch.ship.y - 70, 520 + 100, 100 + 520, aX, aY,
				aX + 100, aY + 100, null);
		if (aX >= 900) {

			aX = 700;
			aY += 100;
		}

		aX += 100;

	}

	public void endturn(Graphics g) {

		g.drawImage(ImageLoader.endturn, 40, 255, 402, 605, eX, eY, eX + 200, eY + 200, null);

		if (eX >= 2000) {

			eX = 0;
			eY += 200;
		}

		if (eY == 1000) {

			eY = 0;

		}

		eX += 200;

	}

	public void background(Graphics g) {

		g.drawImage(ImageLoader.background4, -160, 0, SkyWatch.WIDTH + 800, SkyWatch.HEIGHT, bX, bY, bX + 200, bY + 100,
				null);

		if (bX >= 2000) {

			bX = 0;
			bY += 100;
		}

		if (bY == 1000) {

			bY = 0;

		}

		bX += 200;

	}

	public void com(Graphics g) {

		g.drawImage(ImageLoader.com1, 530, 90, 910, 290, sX, sY, sX + 384, sY + 270, null);

		if (sX >= 1920) {

			sX = 0;
			sY += 270;
		}

		sX += 384;

		if (sX >= 1920 && sY >= 1080) {
			stat = true;

		}

	}
}
