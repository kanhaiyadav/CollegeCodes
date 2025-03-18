import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

/*
 * <applet code="SampleApplet.class" width="400" height="200">
 * </applet>
 */
public class SampleApplet extends Applet {
    public void init() {
        // Initialization code
        setBackground(java.awt.Color.white);
    }
    
    public void paint(Graphics g) {
        // Set font for better visibility
        Font font = new Font("Arial", Font.BOLD, 18);
        g.setFont(font);
        
        // Draw the text in the applet window
        g.drawString("A sample applet program", 50, 100);
    }
}

/*
<html>
<head>
<title>Sample Applet</title>
</head>
<body>
<applet code="SampleApplet.class" width="400" height="200">
</applet>
</body>
</html>
*/
