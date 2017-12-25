package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.Asset;
import model.Player;

import static java.lang.String.format;

public class Controller {

    int comments1;
    int comments2;
    int commentsPerSecond;

    @FXML
    private GridPane container;

    @FXML
    private TextArea stats;

    public Controller(){
        calculateCommentsPerSecond();
        stats = new TextArea();
        sendControllerToPlayer();
        update();
        System.out.println("Controller constructed!");
    }

    private void sendControllerToPlayer() {
        Player.ctrl = this;
    }

    public void update(){
        setMessage(formatMessage());
    }



    public void click(){
        Player.profit(1);
    }


    public void handleClick(ActionEvent actionEvent) {
        click();
    }

    public void handleBuy0(ActionEvent actionEvent) {
        purchaseAsset(0);
    }

    public void handleBuy1(ActionEvent actionEvent) {
        purchaseAsset(1);
        System.out.println("Buying asset...");
    }

    public void handleBuy2(ActionEvent actionEvent) {
        purchaseAsset(2);
    }

    public void handleBuy3(ActionEvent actionEvent) {
        purchaseAsset(3);
    }

    public void handleBuy4(ActionEvent actionEvent) {
        purchaseAsset(4);
    }

    private boolean purchaseAsset(int assetID){
        Asset asset = Player.assets[assetID];
        if (Player.nComments >= asset.getCost()){
            Player.spend(asset.getCost());
            asset.buy();
            update();
            return true;
        } else {
            return false;
        }
    }

    public void handleBuy5(ActionEvent actionEvent) { purchaseAsset(5);
    }

    public void setMessage(String message){
        this.stats.setText(message);
    }

    private String formatMessage(){
        int nComments = Player.nComments;
        String mainStats = String.format(
                        "lines commented: %d%n" +
                        "comments per second: %d%n%n", nComments, commentsPerSecond);
        StringBuilder assetStats = new StringBuilder();
        for (Asset a : Asset.values()){
            assetStats.append(String.format("%s: %d%n", a.toString(), a.getQuantity()));
        }
        return mainStats + assetStats;
    }

    private void calculateCommentsPerSecond(){
        Timeline commentsPerSecondTimeline = new Timeline();
        commentsPerSecondTimeline.setCycleCount(Timeline.INDEFINITE);
        commentsPerSecondTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            this.comments2 = Player.nComments;
            this.commentsPerSecond = comments2 - comments1;
            this.comments1 = this.comments2;
        }));
        commentsPerSecondTimeline.play();
    }
}
