package com.mygdx.gameobjects;


import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Pipe extends Scrollable {
    private Random r;
    private boolean isScored = false;
    private Rectangle skullUp, skullDown, barUp, barDown;

    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;
    private float groundY;

    // When Pipe's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Pipe(float x, float y, int width, int height, float scrollSpeed,
                float groundY) {
        super(x, y, width, height, scrollSpeed);

        r = new Random();
        skullUp = new Rectangle();
        skullDown = new Rectangle();
        barUp = new Rectangle();
        barDown = new Rectangle();

        this.groundY = groundY;
    }

    @Override
    public void update(float delta) {
        // Call the update method in the superclass (Scrollable)
        super.update(delta);



        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
                groundY - (position.y + height + VERTICAL_GAP));


        // This shift is equivalent to: (SKULL_WIDTH - width) / 2
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
                - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);

    }

    @Override
    public void reset(float newX) {
        // Call the reset method in the superclass (Scrollable)
        super.reset(newX);
        // Change the height to a random number
        height = r.nextInt(90) + 15;
        isScored = false;
    }

    public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }

    public boolean collides(plane plane) {
        if (position.x < plane.getX() + plane.getWidth()) {
            return (Intersector.overlaps(plane.getBoundingCircle(), barUp)
                    || Intersector.overlaps(plane.getBoundingCircle(), barDown)
                    || Intersector.overlaps(plane.getBoundingCircle(), skullUp) || Intersector
                    .overlaps(plane.getBoundingCircle(), skullDown));
        }
        return false;
    }
    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean b) {
        isScored = b;
    }


    public void onRestart(float x, int scrollSpeed) {
        velocity.x = scrollSpeed;
        reset(x);
    }
}