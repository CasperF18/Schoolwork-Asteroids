package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;
import dk.sdu.mmmi.cbse.commonasteroid.IAsteroidSplitter;

public class AsteroidSplitter implements IAsteroidSplitter {
    private final double MIN_SPLIT_SIZE = 15;

    @Override
    public void createSplittedAsteroid(Entity e, World w) {
        if (!(e instanceof Asteroid)) {
            return; //Just to avoid all non-asteroid entities
        }

        Asteroid asteroid = (Asteroid) e;

        if (asteroid.getRadius() < MIN_SPLIT_SIZE) {
            return;
        }

        double newRadius = asteroid.getRadius() * 0.9;
        double[] newPolygons = new double[asteroid.getPolygonCoordinates().length];

        for (int i = 0; i < newPolygons.length; i++) {
            newPolygons[i] = asteroid.getPolygonCoordinates()[i] * 0.9;
        }

        //To ensure the two new asteroids doesn't instantly collide by spawning on top of each other, I made one spawn in the mirrored position compared to the first one,
        //so one where x is positive and one where x is negative. I then also make them not fly in the same direction by changing it by some degrees.
        double offset = newRadius * 1.5;
        double directionOffset = 45;
        double speedIncrease = 1.2;

        for (int i = 0; i < 2; i++) {
            Asteroid fragmentedAsteroid = new Asteroid();
            fragmentedAsteroid.setPolygonCoordinates(newPolygons);
            fragmentedAsteroid.setRadius(newRadius);
            fragmentedAsteroid.setCannotCollideWithEachOther(true);

            //Setting mirrored position
            fragmentedAsteroid.setX(asteroid.getX() + offset * (i == 0 ? 1 : -1));
            fragmentedAsteroid.setY(asteroid.getY());

            //Same treatment for direction
            double newDirection = (asteroid.getRotation() + directionOffset * (i == 0 ? 1 : -1)) % 360;
            fragmentedAsteroid.setRotation(newDirection);

            double oldSpeedX = asteroid.getSpeedX();
            double oldSpeedY = asteroid.getSpeedY();
            fragmentedAsteroid.setSpeedX(oldSpeedX * Math.cos(Math.toRadians(newDirection)) - oldSpeedY * Math.sin(Math.toRadians(newDirection)) * speedIncrease);
            fragmentedAsteroid.setSpeedY(oldSpeedX * Math.sin(Math.toRadians(newDirection)) + oldSpeedY * Math.cos(Math.toRadians(newDirection)) * speedIncrease);

            w.addEntity(fragmentedAsteroid);
        }
    }
}
