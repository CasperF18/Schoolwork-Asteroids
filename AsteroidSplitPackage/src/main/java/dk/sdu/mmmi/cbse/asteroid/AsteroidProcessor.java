package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;

public class AsteroidProcessor implements IEntityProcessingService {
    private AsteroidSplitter asteroidSplitter = new AsteroidSplitter();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Asteroid.class)) {
            if (entity instanceof Asteroid) { //Just to double check
                Asteroid asteroid = (Asteroid) entity;

                float deltaTime = gameData.getDelta();
                double newY = asteroid.getY() + asteroid.getSpeedY() * deltaTime;
                double newX = asteroid.getX() + asteroid.getSpeedX() * deltaTime;

                asteroid.setY(newY);
                asteroid.setX(newX);

                double angleRadians = Math.atan2(asteroid.getSpeedY(), asteroid.getSpeedX());
                double angleDegrees = Math.toDegrees(angleRadians);
                asteroid.setRotation(angleDegrees);

                asteroid.setCannotCollideWithEachOther(true);

                if (asteroid.getIsHit()) {
                    asteroidSplitter.createSplittedAsteroid(asteroid, world);
                }

                reachingSideHandler(asteroid, gameData);
            }
        }

    }

    private void reachingSideHandler(Entity asteroid, GameData gameData) {
        if (asteroid.getX() > gameData.getDisplayWidth()) {
            asteroid.setX(0);
        } else if (asteroid.getX() < 0) {
            asteroid.setX(gameData.getDisplayWidth());
        }

        if (asteroid.getY() > gameData.getDisplayHeight()) {
            asteroid.setY(0);
        } else if (asteroid.getY() < 0) {
            asteroid.setY(gameData.getDisplayHeight());
        }
    }


}
