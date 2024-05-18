package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Collection;

public class Collision implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        Collection<Entity> entities = world.getEntities();

        for (Entity entityA : entities) {
            for (Entity entityB : entities) {
                if (entityA != entityB && !(entityA.getCannotCollideWithEachOther() && entityB.getCannotCollideWithEachOther())) {
                    if (isThereCollision(entityA, entityB)) {
                        entityA.setIsHit(true);
                        entityB.setIsHit(true);
                    }
                }
            }

        }
    }

    public Boolean isThereCollision(Entity entityA, Entity entityB) {
        double dx = entityA.getX() - entityB.getX();
        double dy = entityA.getY() - entityB.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance < entityA.getRadius() + entityB.getRadius();
    }
}
