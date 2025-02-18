import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Car {
    private int x, y, width, length;
    private double angle;
    private Rectangle body;
    private Rectangle[] tyres;
    private final int moveDistance = 8;
    private ArrayList<TyreMark> tyreMarksLeft = new ArrayList<>();
    private ArrayList<TyreMark> tyreMarksRight = new ArrayList<>();

    public Car(int x, int y) {
        this.angle = 0;
        this.length = 50;
        this.width = 20;
        this.x = x - length / 2;
        this.y = y - width / 2;
        this.body = new Rectangle(this.x, this.y, length, width);
        this.tyres = new Rectangle[4];

        //set initial tyre positions (moved around car)
        tyres[0] = new Rectangle(x + 5, y - 4, 8, 8); // left rear tyre
        tyres[1] = new Rectangle(x + 5, y + 16, 8, 8); // right rear tyre
        tyres[2] = new Rectangle(x + 35, y - 4, 8, 8); // left front tyre
        tyres[3] = new Rectangle(x + 35, y + 16, 8, 8); // rear front tyre
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x - length / 2;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y - width / 2;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.body.setLocation(this.x - length / 2, this.y - width / 2);

        double radians = Math.toRadians(angle);

        int rearLeftX = (int) (x + (-length / 2 + 5) * Math.cos(radians) - (-width / 2 - 4) * Math.sin(radians));
        int rearLeftY = (int) (y + (-length / 2 + 5) * Math.sin(radians) + (-width / 2 - 4) * Math.cos(radians));

        int rearRightX = (int) (x + (-length / 2 + 5) * Math.cos(radians) - (width / 2 + 4) * Math.sin(radians));
        int rearRightY = (int) (y + (-length / 2 + 5) * Math.sin(radians) + (width / 2 + 4) * Math.cos(radians));

        int frontLeftX = (int) (x + (length / 2 - 5) * Math.cos(radians) - (-width / 2 - 4) * Math.sin(radians));
        int frontLeftY = (int) (y + (length / 2 - 5) * Math.sin(radians) + (-width / 2 - 4) * Math.cos(radians));

        int frontRightX = (int) (x + (length / 2 - 5) * Math.cos(radians) - (width / 2 + 4) * Math.sin(radians));
        int frontRightY = (int) (y + (length / 2 - 5) * Math.sin(radians) + (width / 2 + 4) * Math.cos(radians));

        // Set tyre position
        tyres[0].setLocation(rearLeftX, rearLeftY);
        tyres[1].setLocation(rearRightX, rearRightY);
        tyres[2].setLocation(frontLeftX, frontLeftY);
        tyres[3].setLocation(frontRightX, frontRightY);

        // Save tyre marks in ArrayList
        tyreMarksLeft.add(new TyreMark(rearLeftX, rearLeftY));
        tyreMarksRight.add(new TyreMark(rearRightX, rearRightY));
    }





    public void moveCarForward() {

        double radians = Math.toRadians(angle);

        this.x += (int) (moveDistance * Math.cos(radians));
        this.y += (int) (moveDistance * Math.sin(radians));

        setPosition(x, y);
    }

    public void moveCarBackwards() {

        double radians = Math.toRadians(angle);

        this.x -= (int) (moveDistance * Math.cos(radians));
        this.y -= (int) (moveDistance * Math.sin(radians));

        setPosition(x, y);
    }

    public void rotateCarLeft() {
        angle -= 2;
        if (angle < 0) {
            angle += 360;
        }
    }

    public void rotateCarRight() {
        angle += 2;
        if (angle > 360) {
            angle -= 360;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform original = g2d.getTransform();

        // Move car
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(angle));

        // Draw tyres
        g2d.setColor(Color.BLACK);
        g2d.fillRect(-length / 2 + 5, -width / 2 - 4, 8, 8); // Left rear
        g2d.fillRect(-length / 2 + 5, width / 2 - 4, 8, 8);  // Right rear
        g2d.fillRect(length / 2 - 13, -width / 2 - 4, 8, 8); // Left front
        g2d.fillRect(length / 2 - 13, width / 2 - 4, 8, 8);  // Right front

        // Draw body
        g2d.setColor(Color.RED);
        g2d.fillRect(-length / 2, -width / 2, body.width, body.height);

        // Draw centre
        g2d.setColor(Color.GREEN);
        g2d.fillOval(-5, -5, 10, 10);

        g2d.setTransform(original);

        // Draw tyre marks
        g2d.setColor(Color.GRAY);
        for (TyreMark tyreMark : tyreMarksLeft) {
            if(tyreMark.time > 0) {
                g2d.fillRect(tyreMark.x, tyreMark.y, tyreMark.width, tyreMark.height);
                tyreMark.time -= 1;
            }
        }
        for (TyreMark tyreMark : tyreMarksRight) {
            if(tyreMark.time > 0) {
                g2d.fillRect(tyreMark.x, tyreMark.y, tyreMark.width, tyreMark.height);
                tyreMark.time -= 1;
            }
        }
    }

}

