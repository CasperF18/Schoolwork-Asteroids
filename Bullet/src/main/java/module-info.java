import dk.sdu.mmmi.cbse.bulletsystem.BulletProcessor;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Bullet {
    requires Common;
    requires CommonBullet;
    provides IGamePluginService with dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
    provides BulletSPI with BulletProcessor;
    provides IEntityProcessingService with BulletProcessor;
}