import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class IPanel extends JPanel{
	public void paintComponent(Graphics g) {
		Image i = new ImageIcon("title.png").getImage();
		g.drawImage(i,10,10,this);
	}
}
