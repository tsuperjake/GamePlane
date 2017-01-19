package com.mygdx.gameworld;


import com.badlogic.gdx.math.Intersector;
import com.mygdx.gameobjects.ScrollHandler;
import com.mygdx.gameobjects.plane;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.planeHelper.AssetLoader;

public class GameWorld {
    public int midPointY;
    private GameState currentState;
    private int score = 0;
    private Rectangle ground;
    private boolean isAlive = true;
    private ScrollHandler scroller;
    private plane plane;
    public GameWorld(int midPointY) {
        this.midPointY = midPointY;
        currentState = GameState.READY;
        plane = new plane(33, midPointY - 50, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);

    }
    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        plane.update(delta);
        scroller.update(delta);

        if (scroller.collides(plane) && plane.isAlive()) {
            scroller.stop();
            plane.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(plane.getBoundingCircle(), ground)) {
            scroller.stop();
            plane.die();
            plane.decelerate();
            currentState = GameState.GAMEOVER;
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }
    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }
    public plane getPlane() {
        return plane;
    }
    public ScrollHandler getScroller(){
        return scroller;
    }
    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }
    public enum GameState {

        READY, RUNNING, GAMEOVER, HIGHSCORE

    }
    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }
    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }

    private void updateReady(float delta) {
    }
    public void restart() {
        currentState = GameState.READY;
        score = 0;
        plane.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }
}
