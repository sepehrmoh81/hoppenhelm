package com.smma.hoppenhelm.model;
import javafx.scene.image.ImageView;
public class SpikedGround extends Ground implements Runnable,IDrawable {
    private final int interval;
    private final Thread spikeThread;
    private final String imagePath = "visuals/spike.gif";
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
    public void Draw(Scene scene){
        
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