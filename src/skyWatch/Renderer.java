package skyWatch;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel {

	private static final long serialVersionUID = 1L;
	public static int aX, aY, eX, eY;

	@Override
	protected void paintComponent(Graphics screen) {
		super.paintComponent(screen);
		SkyWatch.skywatch.repaint(screen);

	}
}
