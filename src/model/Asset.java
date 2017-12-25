package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Enumeration;

public enum Asset {
    //  (cost, profit, period)
    LVL0(15, 1, 5000, "single line comments"),
    LVL1(60, 1, 1000, "multi-line comments"),
    LVL2(500, 1, 100, "Doc comments"),
    LVL3(4500, 1, 10, "README.txt"),
    LVL4(30000, 1, 1, "QA Commenters"),
    LVL5(100000, 10, 1, "Readable Code");

    private int quantity;
    private int cost;
    private int baseProfit;
    private int period;
    private Timeline timeline;
    private String name;

    Asset(int cost, int profit, int periodInMS, String name){
        this.quantity = 0;
        this.cost = cost;
        this.baseProfit = profit;
        this.period = periodInMS;
        this.name = name;
        this.initiateTimeline();
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public int getBaseProfit() {
        return baseProfit;
    }

    public int getPeriod() {
        return period;
    }

    public boolean buy(){
        this.quantity++;
        return true;
    }

    public int profit(){
        return this.baseProfit * this.quantity;
    }

    private void initiateTimeline(){
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(this.period), e -> Player.profit(this.profit())));
        timeline.play();
    }

    public String getCostString(){
        return Integer.toString(this.cost);
    }

    public String getProfitString(){
        return "1 comment / " + Integer.toString(this.period/1000) + " seconds";
    }

    @Override
    public String toString(){
        return this.name;
    }
}
