package com.smma.hoppenhelm.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpikedGround extends Ground implements Runnable,IDrawable {
    private final int interval;
    private final Thread spikeThread;
    private static final String IMAGE_PATH = "spike.png";
    public SpikedGround(int interval) {
        this.interval = interval;
        this.spikeThread = new Thread(this);
    }

    public void extendSpikes() {
        // Implement extend spikes logic
    }

    public void retractSpikes() {
        // Implement retract spikes logic
    }

    public void showSpikes() {
        // Implement show spikes logic
    }
     @Override
    public ImageView draw(){
       ImageView iv = new ImageView(new Image(IMAGE_PATH));
       iv.setFitWidth(90);
       iv.setFitHeight(30);
       return iv;
    }
    @Override
    public void run() {
        try {
            while (true) {
                extendSpikes();
                Thread.sleep(interval);
                retractSpikes();
                Thread.sleep(interval);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startSpikes() {
        spikeThread.start();
    }
}