import java.awt.*;
import java.applet.*;

public class SimpleApplet extends Applet {
    public void paint(Graphics g) {
        g.drawString("A Simple Applet", 20, 20);
    }
}

/*
<html>
<applet code="SimpleApplet" width=200 height=60>
</applet>
</html>
*/
