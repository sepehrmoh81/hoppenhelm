package com.smma.hoppenhelm.model;

import java.util.concurrent.atomic.AtomicBoolean;

import com.smma.hoppenhelm.model.GameState.State;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpikedGround extends GameObject implements Runnable, IDrawable {
    ImageView spikeImageView;
    private final int intervalMillis;
    private final Thread spikeThread;
    private static final String IMAGE_PATH = "spike.png";
    private final AtomicBoolean running = new AtomicBoolean(true);
    private boolean extended = false;

    public SpikedGround(int intervalMillis) {
        this.intervalMillis = intervalMillis;
        this.spikeThread = new Thread(this);
    }

    public void extendSpikes() {
        spikeImageView.setTranslateY(0);
        extended = true;
        GameState.modifyState(State.SPIKE, position);
    }

    public void retractSpikes() {
        spikeImageView.setTranslateY(30);
        extended = false;
        GameState.modifyState(State.BLANK, position);
    }

    public void showSpikes() {
        spikeImageView.setTranslateY(15);
    }

    public boolean isExtended() {
        return extended;
    }

    public void start() {
        spikeThread.start();
    }

    public void halt(){
        synchronized(running){
            running.compareAndSet(true, false);
        }
    }

    @Override
    public ImageView draw() {
        spikeImageView = new ImageView(new Image(IMAGE_PATH));
        spikeImageView.setFitWidth(90);
        spikeImageView.setFitHeight(30);
        return spikeImageView;
    }

    @Override
    public void run() {
        try {
            while (running.get()) {
                showSpikes();
                Thread.sleep(intervalMillis);
                extendSpikes();
                Thread.sleep(intervalMillis);
                retractSpikes();
                Thread.sleep(intervalMillis);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}