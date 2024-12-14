import java.awt.*;
import java.awt.geom.AffineTransform;

public class Car {
    private int x, y, width, length, carFront, carBack;
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
        this.body = new Rectangle(x, y, length, width);
        this.tyres = new Rectangle[4];
        this.carFront = x + length;
        this.carBack = x;

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
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        //move car (tyres move based on xy that car starts at)
        this.body.setLocation(x, y);
        tyres[0].setLocation(x + 5, y - 4);
        tyres[1].setLocation(x + 5, y + 16);
        tyres[2].setLocation(x + 35, y - 4);
        tyres[3].setLocation(x + 35, y + 16);
    }

    public void moveCarForward() {

        double radians = Math.toRadians(angle);

        x += (int) (moveDistance * Math.cos(radians));
        y += (int) (moveDistance * Math.sin(radians));

        setPosition(x, y);
    }

    public void moveCarBackwards() {

        double radians = Math.toRadians(angle);

        x -= (int) (moveDistance * Math.cos(radians));
        y -= (int) (moveDistance * Math.sin(radians));

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

        //apply transformations: move, rotate
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(angle), x, y);
        g2d.fillOval(x, y, 5, 5);
        // Draw the tyres (in black)
        g2d.setColor(Color.BLACK);
        for (Rectangle tyre : tyres) {
            g2d.fillRect(tyre.x - x, tyre.y - y, tyre.width, tyre.height);
        }

        //draw car
        g2d.setColor(Color.RED);
        g2d.fillRect(x - length / 2, y - width / 2, body.width, body.height); // Draw the body
        g2d.setColor(Color.GREEN);
        // --- DEBUG --- turning radius centre
        g2d.fillOval(x-3, y-3, 6, 6);

        g2d.setTransform(original);

    }
}

