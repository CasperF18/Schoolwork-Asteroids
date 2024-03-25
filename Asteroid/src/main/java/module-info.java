import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonasteroid.IAsteroidSplitter;

module Asteroid {
    requires Common;
    requires CommonAsteroid;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid.AsteroidProcessor;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;
    provides IAsteroidSplitter with dk.sdu.mmmi.cbse.asteroid.AsteroidSplitter;
}