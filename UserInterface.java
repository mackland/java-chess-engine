import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface extends JPanel implements MouseListener, MouseMotionListener{
    static int x=0,y=0;
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.red);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        g.setColor(Color.BLUE);
        g.fillRect(x - 20, y-20, 40, 40);
        g.setColor(new Color(190, 80, 215));
        g.fillRect(10, 10, 20, 20);
        g.drawString("Chess", x, y);
        Image chessPieceImages;
        chessPieceImages = new ImageIcon("ChessPieces.png").getImage();
        g.drawImage(chessPieceImages, x, y, x + 64, y + 64, 0, 0, 64, 64, this);
    }

    public void mouseMoved(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    
    public void mouseClicked(MouseEvent e){
        x = e.getX();
        y = e.getY();
        repaint();
    }
    
    public void mouseDragged(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
}
