package dk.sdu.mmmi.cbse.commonasteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {
    private double speedX = 50;
    private double speedY = 10;

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }
}
