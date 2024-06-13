package com.smma.hoppenhelm.model;

public class GameState {
    private static final int[] columns = new int[4];
     /*
        states: 0 blank
                1 coin
                2 potion
                3 spiked ground
                4 enemy
     */
     public static void initStates(){
        for(int i = 0; i < 4; i++){
            columns[i] = 0;
        }
     }
     public static int moveStates(){
        columns[0] = columns[1];
        columns[1] = columns[2];
        columns[2] = columns[3];
        return columns[0];
    }

    public static void addState(int state) {
        columns[3] = state;
    }

    public static void modifyState(int state, int position) {
        columns[position] = state;
    }
}
