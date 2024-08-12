package com.nbicocchi.javafx.geometry.physics.world;

import com.nbicocchi.javafx.geometry.physics.body.Body;
import com.nbicocchi.javafx.geometry.physics.math.Collisions;
import com.nbicocchi.javafx.geometry.physics.math.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class World {
    private static World instance;

    private final List<Body> bodies;
    private final WorldClock clock;

    protected World(WorldClock worldClock) {
        this.clock = worldClock;
        bodies = new ArrayList<>();
        start();
    }

//    public static World getInstance() {
//        if (instance == null) {
//            instance = new World();
//        }
//        return instance;
//    }

    public List<Body> getBodies() {
        return bodies;
    }

    public void addBody(double x, double y, Body.ShapeType shapeType, double width, double height, Color color,
                        Vector linearVelocity, double rotation, double rotationVelocity, double density,
                        double restitution, double area, boolean active, double radius) {

        double scale = 4;
        width *= scale;
        height *= scale;
        radius *= scale;

        bodies.add(new Body(x, y, linearVelocity, rotation, rotationVelocity, density, restitution, area, active, radius, height, width, shapeType, color));
    }

    public void removeBody(Body body) {
        bodies.remove(body);
    }

    public void renderBodies(GraphicsContext gc) {
        for (Body body : bodies) {
            body.getShapeComponent().getShape().paint(gc);
        }
    }

    protected void update() {
        moveBodies();
        Collisions.checkCollisions(bodies, this::handleCollision);
    }

    Vector displacement = new Vector(0, 0.1);
    private void moveBodies() {

        byte i = 0;

        for (Body body : bodies) {

            if(i == 1)
            {
                body.move(displacement);
            }
            i++;

            System.out.print("body" + i + "position ");
            System.out.println(body.getPosition().getY());
        }
    }

    private void handleCollision(Body hittingBody, Body hittedBody) {
        System.out.println("Collision detected between body " + bodies.indexOf(hittingBody) + " and body " + bodies.indexOf(hittedBody));
        // Implement additional collision response logic here if needed
    }

    // Starting the clock is part of the world lifecycle
    public void start() {
        clock.start();
        System.out.println("World clock started");
    }

    public void stop() {
        clock.stop();
    }


}
