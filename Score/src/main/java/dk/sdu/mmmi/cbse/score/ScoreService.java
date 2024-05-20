package dk.sdu.mmmi.cbse.score;

import dk.sdu.mmmi.cbse.scoreconsumer.ScoreUpdater;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;

public class ScoreService implements IEntityProcessingService {
    private ScoreUpdater scoreUpdater;
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity.getIsHit() && entity instanceof Asteroid) {
                scoreUpdater.updateScore();
            }
        }

    }
}
