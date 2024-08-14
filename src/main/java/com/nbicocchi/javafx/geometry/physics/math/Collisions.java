package com.nbicocchi.javafx.geometry.physics.math;
import java.io.Serial;
import java.util.List;

import com.nbicocchi.javafx.geometry.physics.body.Body;


public class Collisions {

    private static boolean IntersectCircles(Vector c1, Vector c2, double radius1, double radius2)
    {
        double distance = Vector.distance(c1, c2);

        return !(distance >= radius1 + radius2);
    }

    public static void checkCirclesCollisions(List<Body> bodies, CollisionsFunctions collisionFunction)
    {
        for(int i = 0; i < bodies.size(); i++)
        {
            for(int j = i+1; j < bodies.size(); j++)
            {
                Body hittingBody = bodies.get(i);
                Body hittedBody = bodies.get(j);




                if(identifyTypeOfCollision(hittedBody.getShapeType(), hittingBody.getShapeType(), hittingBody.getCenter(), hittedBody.getCenter(), hittingBody.getShapeComponent().getHeight()/2, hittedBody.getShapeComponent().getHeight()/2))
                {
                    collisionFunction.ifCollision(hittingBody, hittedBody);
                }

            }
        }

    }

    private static boolean identifyTypeOfCollision(Body.ShapeType shapeType, Body.ShapeType collisionShapeType, Vector c1, Vector c2, double radius1, double radius2)
    {
        if(shapeType == Body.ShapeType.Circle && collisionShapeType == Body.ShapeType.Circle)
        {
            return IntersectCircles(c1, c2, radius1, radius2);
        }

        else
        {
            throw new IllegalArgumentException("Not Implemented shape types");
        }
    }

}
