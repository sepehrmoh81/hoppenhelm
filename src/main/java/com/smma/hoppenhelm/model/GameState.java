package com.smma.hoppenhelm.model;

public class GameState {
    public enum State {
        BLANK,
        COIN,
        POTION,
        SPIKE,
        ENEMY
    }

    private static final State[] columns = new State[4];

    private static final GameObject[] objects = new GameObject[4];

    public static GameObject[] getObjects() {
        return objects;
    }
    public static State getPlayerFront(){
        return columns[1];
    }
    public static void initStates() {
        for (int i = 0; i < 4; i++) {
            columns[i] = State.BLANK;
            objects[i] = new Blank();
        }
    }

    public static State moveStates() {
        for (int i = 0; i < 3; i++) {
            if(i == 0 && objects[0] instanceof SpikedGround){
                ((SpikedGround) objects[0]).halt();
                System.out.println("Halted Spike");
            }

            columns[i] = columns[i + 1];
            objects[i].move();
            objects[i] = objects[i + 1];
        }
        return columns[0];
    }

    public static void addState(State state, GameObject gm) {
        columns[3] = state;
        objects[3] = gm;
    }

    public static void modifyState(State state, int position) {
        columns[position] = state;
    }
}
