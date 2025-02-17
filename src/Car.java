import java.awt.*;
import java.awt.geom.AffineTransform;

public class Car {
    private int x, y, width, length;
    private double angle;
    private Rectangle body;
    private Rectangle[] tyres;
    private final int moveDistance = 4;

    public Car(int x, int y) {
        this.angle = 0;
        this.length = 50;
        this.width = 20;
        this.x = x - length / 2;
        this.y = y - width / 2;
        this.body = new Rectangle(this.x, this.y, length, width);
        this.tyres = new Rectangle[4];

        //set initial tyre positions (moved around car
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
        //move car (tyres move based on xy that car starts at)
        this.body.setLocation(this.x, this.y);
        tyres[0].setLocation(this.x +5, this.y - 15);
        tyres[1].setLocation(this.x + 5, this.y + 4);
        tyres[2].setLocation(this.x + 35, this.y - 15);
        tyres[3].setLocation(this.x + 35, this.y + 4);
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

        // Apply transformations: translate, rotate
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(angle));

        // Draw tyres
        g2d.setColor(Color.BLACK);
        for (Rectangle tyre : tyres) {
            g2d.fillRect(tyre.x - x-23, tyre.y - y+2, tyre.width, tyre.height);
        }

        // Draw car body
        g2d.setColor(Color.RED);
        g2d.fillRect(-length / 2, -width / 2, body.width, body.height);

        //Draw centre
        g2d.setColor(Color.green);
        g2d.fillOval(-length/2 + 20, -width/2 + 5, 10,10);

        g2d.setTransform(original);
    }
}

