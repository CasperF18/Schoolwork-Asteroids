package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;

public class AsteroidPlugin implements IGamePluginService {
    private Entity asteroid;

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);
    }

    private Entity createAsteroid (GameData gameData) {
        Entity asteroid = new Asteroid();

        //I made the asteroid too small at the start, so made this to be able to scale it
        double[] originalCoordinates = {1.17, 0, 0.96, 0.7, 0.37, 1.15, -0.36, 1.12, -1.01, 0.74, -1.01, 0, -0.85, -0.62, -0.36, -1.09, 0.4, -1.23, 0.88, -0.64};
        double[] scaledCoordinates = new double[originalCoordinates.length];
        double scale = 20;

        for (int i = 0; i < originalCoordinates.length; i++) {
            scaledCoordinates[i] = originalCoordinates[i] * scale;
        }

        asteroid.setPolygonCoordinates(scaledCoordinates);

        asteroid.setX(0);
        asteroid.setY(gameData.getDisplayHeight());
        asteroid.setRadius(1.23 * scale);

        return asteroid;
    }
}
