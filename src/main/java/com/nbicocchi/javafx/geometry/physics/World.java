package com.nbicocchi.javafx.geometry.physics;

import com.nbicocchi.javafx.geometry.shapes.Circle;
import com.nbicocchi.javafx.geometry.shapes.DrawableShape;
import com.nbicocchi.javafx.geometry.shapes.Rectangle;
import com.nbicocchi.javafx.geometry.shapes.Triangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class World {

    private static World world;

    private List<Body> bodies;

    private World()
    {
        bodies = new ArrayList<>();
    }

    public static World getInstance()
    {
        if(world == null)
        {
            world = new World();
        }
        return world;
    }

    public List<Body> getBodies()
    {
        return bodies;
    }

    /*
    public static List<DrawableShape> shapes  = new ArrayList<>();
    public static void addShape(double x, double y, String type, double width, double height, Color color) {
        Rectangle2D bounds = new Rectangle2D(x, y, width, height);
        DrawableShape shape;
        switch (type) {
            case "Triangle" -> {
                shape = new Triangle(bounds, color);
                World.shapes.add(shape);
            }
            case "Rectangle" -> {
                shape = new Rectangle(bounds, color);
                World.shapes.add(shape);
            }
            case "Circle" -> {
                new Circle(bounds, color);
                shape = new Circle(bounds, color);
                World.shapes.add(shape);
            }
            default -> {
            }
        }
    }
*/

    public void addBody
            (double x,
             double y,
             Body.ShapeType shapeType,
             double width,
             double height,
             Color color,
             Vector linearVelocity,
             double rotation,
             double rotationVelocity,
             double density,
             double restitution,
             double area,
             boolean active,
             double radius
            )
    {
        double scale = 4;
        width *= scale;
        height *= scale;
        radius *= scale;

        bodies.add(new Body(x, y, linearVelocity, rotation, rotationVelocity, density, restitution, area, active, radius, height, width, shapeType, color));
    }

    static Vector displacement = new Vector(0, 0.1);

    void ifCollision(Body hittingBody, Body hittedBody)
    {
        System.out.println(bodies.indexOf(hittingBody) + " is colliding with ");
        System.out.println(bodies.indexOf(hittedBody));
    }

    public void Update()
    {
        moveBodies();
    }

    private static Vector getNormalOfCircles(Vector c1, Vector c2)
    {
        return c1.subtract(c2).normalized();
    }

    private void moveBodies()
    {
        int i = 0;

        for (Body body : bodies)
        {
            if (i == 1)
            {
                body.move(displacement);

                System.out.println( bodies.get(0).getPosition().getY() - body.getPosition().getY());
                System.out.println( bodies.get(0).radius + body.radius);
            }
            i++;
        }

        Collisions.checkCollisions(bodies, this::ifCollision);
    }

    public void removeBody(Body body)
    {
        bodies.remove(body);
    }

    public void renderBodies(GraphicsContext gc)
    {
        for (Body body : bodies) {
            body.getShape().paint(gc);
        }
    }
}
