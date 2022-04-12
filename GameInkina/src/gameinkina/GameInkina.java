
package gameinkina;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/**
 *
 * @author INKINA
 */
public class GameInkina extends JFrame {
    
private static GameInkina game_window;  
private static long last_frame_time;
private static Image space;  
private static Image dog;  
private static Image rainb; 

private static float drop_left =20;
private static float drop_top = 10;
private static float drop_v =10;
private static int score =0;
//Inkina
public static void main(String[] args) throws IOException {
    
  space = ImageIO.read(GameInkina.class.getResourceAsStream("space.jpg"));
  dog = ImageIO.read(GameInkina.class.getResourceAsStream("dog.png"));
  rainb = ImageIO.read(GameInkina.class.getResourceAsStream("rainb.png"));
    
game_window = new GameInkina();

game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
game_window.setLocation(200, 50);
game_window.setSize(900, 600);
game_window.setResizable(false);
last_frame_time= System.nanoTime();
GameField game_field = new GameField();
game_window.add(game_field);
game_window.setVisible(true);

game_field.addMouseListener(new MouseAdapter() { 
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float drop_right = drop_left + rainb.getWidth(null);
                float drop_bottom = drop_top + rainb.getHeight(null);
                boolean is_drop = x >= drop_left && x <= drop_right && y >= drop_top && y <= drop_bottom;
                

                if (is_drop) {
                    drop_top = -100; 
                    drop_left = (int) (Math.random() * (game_field.getWidth() - rainb.getWidth(null))); 
                    drop_v = drop_v + 10;
                    score ++;
                    game_window.setTitle("Score: " + score); //Inkina
                }
            }
});
}

private static void onRepaint(Graphics g){
//INKINA
long current_time = System.nanoTime();
float delt_time = (current_time - last_frame_time) * 0.000000001f;
last_frame_time = current_time;
drop_top = drop_top + drop_v * delt_time;
g.drawImage(space, 0, 0, null);
g.drawImage(dog,(int) drop_left, (int) drop_top, null);
if (drop_top > game_window.getHeight()) g.drawImage(rainb, 300, 300, null);

}
//INKINA
private static class GameField extends JPanel {
@Override
protected void paintComponent(Graphics g) {
super.paintComponent(g);
onRepaint(g);
repaint ();
}
}
}
 