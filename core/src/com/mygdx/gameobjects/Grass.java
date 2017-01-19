package com.mygdx.gameobjects;


public class Grass extends Scrollable {
    // When Grass's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

    }

    public void onRestart(int x, int scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }
}
