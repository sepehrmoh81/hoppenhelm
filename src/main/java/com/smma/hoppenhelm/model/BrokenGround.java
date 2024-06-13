package com.smma.hoppenhelm.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BrokenGround extends GameObject implements Runnable,IDrawable {
    private final int breakTime;
    private final Thread groundBreakThread;
    private static final String IMAGE_PATH = "ground.png";

    public BrokenGround(int breakTime) {
        this.breakTime = breakTime;
        this.groundBreakThread = new Thread(this);
    }

    public void breakGround() {
        // Implement ground breaking logic
    }
    @Override
    public ImageView draw(){
       ImageView iv = new ImageView(new Image(IMAGE_PATH));
       iv.setFitWidth(90);
       iv.setFitHeight(90);
       return iv;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(breakTime);
            breakGround();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startBreaking() {
        groundBreakThread.start();
    }
}