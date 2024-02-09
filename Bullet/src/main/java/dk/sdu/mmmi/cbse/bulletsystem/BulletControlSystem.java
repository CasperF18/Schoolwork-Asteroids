package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    private final double BULLET_SPEED = 300;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double deltaTime = gameData.getDelta();
            double changeX = Math.cos(Math.toRadians(bullet.getRotation())) * BULLET_SPEED * deltaTime;
            double changeY = Math.sin(Math.toRadians(bullet.getRotation())) * BULLET_SPEED * deltaTime;
            bullet.setX(bullet.getX() + changeX);
            bullet.setY(bullet.getY() + changeY);
        }
    }

    @Override
    public Bullet createBullet(Entity shooter, GameData gameData) {
        Bullet bullet = new Bullet();
        bullet.setRotation(shooter.getRotation());

        // Calculate the spawn position at the tip of the player
        double spawnX = shooter.getX() + Math.cos(Math.toRadians(shooter.getRotation())) * 5;
        double spawnY = shooter.getY() + Math.sin(Math.toRadians(shooter.getRotation())) * 5;
        bullet.setX(spawnX);
        bullet.setY(spawnY);

        setShape(bullet);
        return bullet;
    }

    private void setShape(Entity entity) {
        entity.setPolygonCoordinates(-1,-1,-1,1,1,1,1,-1);
    }

}
