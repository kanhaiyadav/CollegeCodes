import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

public class SampleApplet extends Applet {
    
    public void init() {
        // Initialize the applet
        setBackground(java.awt.Color.white);
    }
    
    public void paint(Graphics g) {
        // Set font properties
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        
        // Get applet dimensions
        int width = getWidth();
        int height = getHeight();
        
        // Calculate position for centered text
        String message = "A sample applet program";
        int stringWidth = g.getFontMetrics().stringWidth(message);
        int x = (width - stringWidth) / 2;
        int y = height / 2;
        
        // Draw the text
        g.drawString(message, x, y);
    }
}