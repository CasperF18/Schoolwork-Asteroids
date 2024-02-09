package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    private final double ENEMY_SPEED_Y = 50;
    private final double ENEMY_SPEED_X = 10;
    private final int SHOOTING_CHANCE = 49;
    private Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {

            Enemy enemy1 = (Enemy) enemy;
            enemy1.setTimeSinceLastShot(enemy1.getTimeSinceLastShot() + gameData.getDelta());

            float deltaTime = gameData.getDelta();
            double newY = enemy.getY() + ENEMY_SPEED_Y * deltaTime;
            double newX = enemy.getX() + ENEMY_SPEED_X * deltaTime;

            enemy.setY(newY);
            enemy.setX(newX);

            double angleRadians = Math.atan2(ENEMY_SPEED_Y, ENEMY_SPEED_X);
            double angleDegrees = Math.toDegrees(angleRadians);
            enemy.setRotation(angleDegrees);

            if (random.nextInt() > SHOOTING_CHANCE && enemy1.getTimeSinceLastShot() >= enemy1.getFireRate()) {
                enemy1.setTimeSinceLastShot(0);
                for(BulletSPI bulletSPI : getBulletSPIs()) {
                    Entity bullet = bulletSPI.createBullet(enemy, gameData);
                    world.addEntity(bullet);
                }
            }

            reachingSideHandler(enemy, gameData);
        }
    }

    private void reachingSideHandler(Entity enemy, GameData gameData) {
        if (enemy.getX() > gameData.getDisplayWidth()) {
            enemy.setX(0);
        }
        if (enemy.getY() > gameData.getDisplayHeight()) {
            enemy.setY(0);
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
