package com.nbicocchi.javafx.geometry.physics.world;

import com.nbicocchi.javafx.geometry.app.UIComponents;
import com.nbicocchi.javafx.geometry.physics.body.Body;
import com.nbicocchi.javafx.geometry.physics.math.Collisions;
import com.nbicocchi.javafx.geometry.physics.math.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Body> bodies;
    private final WorldClock clock;
    private long lastTime = 0;

    private final double width;
    private final double height;

    protected World(WorldClock worldClock) {
        this.clock = worldClock;
        bodies = new ArrayList<>();

        width = WorldController.getWidth();
        height = WorldController.getHeight();

        start();
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public void addBody(double x, double y, Body.ShapeType shapeType, double width, double height, Color color,
                        Vector linearVelocity, double rotation, double rotationVelocity, double density,
                        double restitution, double area, boolean active, double radius) {

//        double scale = 4;
//        width *= scale;
//        height *= scale;
//        radius *= scale;

        bodies.add(new Body(x, y, linearVelocity, rotation, rotationVelocity, density, restitution, area, active, radius, height, width, shapeType, color));
    }

    public void removeBody(Body body) {
        bodies.remove(body);
    }

    public void renderBodies(GraphicsContext gc) {
        for (Body body : bodies) {
            // Render the body shape
            body.getShapeComponent().getShape().paint(gc);

            // Calculate the center of the shape
            Vector center = body.getCenter();

            // Render a small point at the center of the shape
            gc.setFill(Color.RED); // You can change the color to whatever you prefer
            double pointRadius = 2; // Size of the point
            gc.fillOval(center.getX() - pointRadius, center.getY() - pointRadius, pointRadius * 2, pointRadius * 2);
        }
    }


    protected void update(long now) {

        double deltaTime = (now - lastTime) / 1000000000.0;
        lastTime = now;

        moveBodies(deltaTime);
        deleteOutsideBodies();
        Collisions.checkCollisions(bodies, this::handleCollision);
    }

    private void moveBodies(double deltaTime) {

        for (Body body : bodies) {

            body.move(body.getTransform().getLinearVelocity().multiply(deltaTime));
            //System.out.println(body.getTransform().getLinearVelocity().getX());
            //System.out.println(body.getTransform().getLinearVelocity().getY());
        }
    }

    private void handleCollision(Body hittingBody, Body hittedBody) {
        System.out.println("Collision detected between body " + bodies.indexOf(hittingBody) + " and body " + bodies.indexOf(hittedBody));
        // Implement additional collision response logic here if needed

        Vector normal = hittedBody.getCenter().subtract(hittingBody.getCenter()).normalized().inverted();
        double distance = Vector.distance(hittedBody.getCenter(), hittingBody.getCenter());

        double radii = (hittedBody.getShapeComponent().getWidth() + hittingBody.getShapeComponent().getWidth())/2;

        double depth = (radii - distance)/2;

        hittedBody.move(normal.multiply(depth).inverted());
        hittingBody.move(normal.multiply(depth));
    }

    // Starting the clock is part of the world lifecycle
    public void start() {
        clock.start();
        System.out.println("World clock started");
    }

    public void stop() {
        clock.stop();
    }


    public void deleteOutsideBodies()
    {
        for (Body body : bodies)
        {
            double x = body.getPosition().getX();
            double y = body.getPosition().getY();

            if(x > width || y > height || x < -body.getShapeComponent().getWidth() || y < -body.getShapeComponent().getHeight())
            {
                removeBody(body);
            }

        }
    }

}
