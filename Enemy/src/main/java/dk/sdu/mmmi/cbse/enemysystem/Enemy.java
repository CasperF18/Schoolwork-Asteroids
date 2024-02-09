package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity{
    private final double FIRE_RATE_IN_SECONDS = 3;
    private double timeSinceLastShot = FIRE_RATE_IN_SECONDS;

    public double getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    public void setTimeSinceLastShot(double time) {
        this.timeSinceLastShot = time;
    }

    public double getFireRate() {
        return FIRE_RATE_IN_SECONDS;
    }
}
