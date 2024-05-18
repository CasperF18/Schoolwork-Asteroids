package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollisionTest {
    @Test
    public void testEntityCollision() {
        Collision collision = new Collision();
        Entity e1 = new Entity();
        e1.setX(50);
        e1.setY(50);
        e1.setRadius(10);

        Entity e2 = new Entity();
        e2.setX(60);
        e2.setY(60);
        e2.setRadius(10);

        Boolean expectedValue = collision.isThereCollision(e1, e2);

        assertTrue(expectedValue, "There should be collision between the entities");
    }

    @Test
    public void TestEntityNoCollision() {
        Collision collision = new Collision();
        Entity e1 = new Entity();
        e1.setX(50);
        e1.setY(50);
        e1.setRadius(10);

        Entity e2 = new Entity();
        e2.setX(80);
        e2.setY(60);
        e2.setRadius(10);

        Boolean expectedValue = collision.isThereCollision(e1, e2);

        assertFalse(expectedValue, "There should not be collision between the entities");
    }

}
