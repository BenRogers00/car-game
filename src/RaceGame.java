import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RaceGame extends JPanel {
    private final Car car;

    public RaceGame() {
        //make new car at test location
        car = new Car(50, 50);

        //listener for key presses
        addKeyListener(new KeyAdapter() {
            //booleans for key press combinations
            boolean moveForward = false;
            boolean moveBackward = false;
            boolean rotateLeft = false;
            boolean rotateRight = false;

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    moveForward = true;
                    moveCar();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    moveBackward = true;
                    moveCar();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    rotateLeft = true;
                    moveCar();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rotateRight = true;
                    moveCar();
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    moveForward = false;
                    moveCar();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    moveBackward = false;
                    moveCar();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    rotateLeft = false;
                    moveCar();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rotateRight = false;
                    moveCar();
                }
            }

            public void moveCar() {
                if(moveForward&&rotateLeft){
                    car.moveCarForward();
                    car.rotateCarLeft();
                }
                else if(moveForward&&rotateRight){
                    car.moveCarForward();
                    car.rotateCarRight();
                }
                else if(moveBackward&&rotateLeft){
                    car.moveCarBackwards();
                    car.rotateCarRight();
                }
                else if(moveBackward&&rotateRight){
                    car.moveCarBackwards();
                    car.rotateCarLeft();
                }
                else if(moveForward && car.getX() >= 0){
                    car.moveCarForward();
                }
                else if(moveBackward && car.getX() >= 0 ){
                    car.moveCarBackwards();
                }
                repaint();
                System.out.println("x: "+car.getX() +"\ty: "+car.getY());
                if(car.getX() <0)
                {
                    car.setX(0);
                }
            }

        });

        setFocusable(true); //frame can receive key presses
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        car.draw(g);
    }

    public static void main(String[] args) {
        //setup window

        //create window
        JFrame frame = new JFrame("Race Game");
        //get size of screen, set to window size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height/2);
        //close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new RaceGame());

        //make visible
        frame.setVisible(true);
    }
}
