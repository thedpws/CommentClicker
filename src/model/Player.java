package model;

import controller.Controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public abstract class Player implements Serializable {

    private static long serial;
    public static Asset[] assets;
    public static int nComments;
    public static Controller ctrl;

    public static boolean init(){
        nComments = 0;
        assets = Asset.values();
        return true;
    }

    public static void spend(int price){
        nComments -= price;
        update();
    }

    public static boolean save() throws IOException {
        return false;
    }

    public static boolean load(File sv) throws IOException{
        return false;
    }

    public static boolean profit(int profit){
        if (profit == 0) return false;
        nComments += profit;
        update();
        return true;
    }

    private static void update(){
       Player.ctrl.update();
    }


}
