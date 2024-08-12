package com.nbicocchi.javafx.geometry.physics.math;
import java.util.List;

import com.nbicocchi.javafx.geometry.physics.body.Body;


public class Collisions {

    private static boolean IntersectCircles(Vector c1, Vector c2, double radius1, double radius2)
    {
        double distance = Vector.distance(c1, c2);

        return !(distance >= radius1 + radius2);
    }

    public static void checkCollisions(List<Body> bodies, CollisionsFunctions collisionFunction)
    {
        for(int i = 0; i < bodies.size(); i++)
        {
            for(int j = i+1; j < bodies.size(); j++)
            {
                Body HittingBody = bodies.get(i);
                Body HittedBody = bodies.get(j);

//                double bodyDistance = Vector.distance(HittingBody.getCenter(), HittedBody.getCenter());
//                double radii = HittedBody.radius + HittingBody.radius;
//
//                System.out.print("the bodies distance is ");
//                System.out.println(bodyDistance);
//                System.out.print("while the radii are ");
//                System.out.println(radii);

                if (IntersectCircles(HittingBody.getCenter(), HittedBody.getCenter(), HittingBody.getShapeComponent().getHeight()/2, HittedBody.getShapeComponent().getHeight()/2))
                {
                    collisionFunction.ifCollision(HittingBody, HittedBody);
                }

            }
        }

    }
}
