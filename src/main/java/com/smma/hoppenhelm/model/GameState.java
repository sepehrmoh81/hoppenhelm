package com.smma.hoppenhelm.model;

import com.smma.hoppenhelm.model.GameState.states;

public class GameState {
    public enum states {
       BLANK,
       COIN,
       POTION,
       SPIKE,
       ENEMY
      }
    private static final states[] columns = new states[4];
     
     private static final GameObject[] objects = new GameObject[4];
     public static void initStates(){
        for(int i = 0; i < 4; i++){
            columns[i] = states.BLANK;
            objects[i] = new Blank();
        }
     }
     public static states moveStates(){
        for(int i=0;i<3;i++)
        {
         columns[i] = columns[i+1];
         objects[i].move();
         objects[i] = objects[i+1];       
        }     
        return columns[0];
    }

    public static void addState(states state,GameObject gm) {
        columns[3] = state;
        objects[3] = gm;
    }

    public static void modifyState(states state, int position) {
        columns[position] = state;
    }
}
