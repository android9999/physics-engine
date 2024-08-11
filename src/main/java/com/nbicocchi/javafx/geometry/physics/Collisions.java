package com.nbicocchi.javafx.geometry.physics;

import java.util.List;

import com.nbicocchi.javafx.geometry.physics.CollisionsFunctions;


public class Collisions {

    private static boolean IntersectCircles(Vector c1, Vector c2, double radius1, double radius2)
    {
        double distance = Vector.distance(c1, c2);

        if(distance >= radius1 + radius2)
        {
            return false;
        }
        return true;
    }

    public static void checkCollisions(List<Body> bodies, CollisionsFunctions collisionFunction)
    {
        for(int i = 0; i < bodies.size(); i++)
        {
            for(int j = i+1; j < bodies.size(); j++)
            {
                Body HittingBody = bodies.get(i);
                Body HittedBody = bodies.get(j);

                if (IntersectCircles(HittingBody.getCenter(), HittedBody.getCenter(), HittingBody.radius, HittedBody.radius))
                {
                    collisionFunction.ifCollision(HittingBody, HittedBody);
                }

            }
        }
    }
}
