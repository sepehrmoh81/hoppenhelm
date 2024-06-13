package com.smma.hoppenhelm.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BrokenGround extends Ground implements Runnable,IDrawable {
    private final int breakTime;
    private final Thread groundBreakThread;
    private final String imagePath = "visuals/ground.png";

    public BrokenGround(int breakTime) {
        this.breakTime = breakTime;
        this.groundBreakThread = new Thread(this);
    }

    public void breakGround() {
        // Implement ground breaking logic
    }
    @Override
    public ImageView Draw(){
       ImageView iv = new ImageView(new Image(imagePath));
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