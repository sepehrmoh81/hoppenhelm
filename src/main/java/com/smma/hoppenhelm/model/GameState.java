package com.smma.hoppenhelm.model;
public class GameState {
     private static int[] columns = new int[4];
     /*
        states: 0 blank
                1 spiked ground
                2 enemy
                3 projectile
     */
     public static int moveStates(){
        columns[0] = columns[1];
        columns[1] = columns[2];
        columns[2] = columns[3];
        return columns[0];
     }
     public static void addState(int state){
        columns[3] = state;
     }
    public static void modifyState(int state,int position){
        columns[position] = state;
    }
}
