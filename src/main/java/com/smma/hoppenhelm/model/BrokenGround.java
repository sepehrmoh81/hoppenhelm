package com.smma.hoppenhelm.model;

public class BrokenGround extends Ground implements Runnable {
    private final int breakTime;
    private final Thread groundBreakThread;

    public BrokenGround(int breakTime) {
        this.breakTime = breakTime;
        this.groundBreakThread = new Thread(this);
    }

    public void breakGround() {
        // Implement ground breaking logic
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