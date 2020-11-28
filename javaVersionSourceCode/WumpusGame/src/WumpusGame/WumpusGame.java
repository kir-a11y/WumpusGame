package WumpusGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Scanner;

import WumpusGame.WumpusPane;


public class WumpusGame extends Application {

    Stage window;
    Scene openScene, scene1, scene2, scene3;
    Board b1, b2, b3;
    int[] d = {0};

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        String rule = "You can only move 1 block once.\nYou can move horizontally, \nvertically or diagonally.";
        b1 = new Board(1);
        b2 = new Board(2);
        b3 = new Board(3);
        //3*3 scene
        BorderPane layout1 = new BorderPane();
        //中 w1游戏组件
        WumpusPane w1 = new WumpusPane(b1,3);
        WumpusController wc1 = new WumpusController(b1, w1);
        w1.setOnMouseClicked(wc1);
        layout1.setCenter(w1);
        //w1游戏界面构建
        //上 按钮
        HBox top1 = new HBox();
        Button topButton1 = new Button("quit");
        topButton1.setOnAction(e->window.close());
        top1.getChildren().add(topButton1);
        layout1.setTop(top1);
        //左 游戏说明
        VBox left1 = new VBox();
        Label intro1 = new Label(rule);
        left1.getChildren().add(intro1);
        layout1.setLeft(left1);
        //右 到谁了的提示
        VBox right1 = new VBox();
        right1.getChildren().add(wc1.getTurn());
        layout1.setRight(right1);
        //下 可能出现的错误
        HBox bot1 = new HBox();
        bot1.getChildren().add(wc1.getNote());
        layout1.setBottom(bot1);
        //加入scene1
        scene1 = new Scene(layout1, 600, 600);

        //6*6 scene
        BorderPane layout2 = new BorderPane();
        //中 w2游戏组件
        WumpusPane w2 = new WumpusPane(b2,6);
        WumpusController wc2 = new WumpusController(b2, w2);
        w2.setOnMouseClicked(wc2);
        layout2.setCenter(w2);
        //w2游戏界面构建
        // 上 按钮
        HBox top2 = new HBox();
        Button topButton2 = new Button("quit");
        topButton2.setOnAction(e->window.close());
        top2.getChildren().add(topButton2);
        layout2.setTop(top2);
        //左 游戏说明
        VBox left2 = new VBox();
        Label intro2 = new Label(rule);
        left2.getChildren().add(intro2);
        layout2.setLeft(left2);
        //右 到谁了的提示
        VBox right2 = new VBox();
        right2.getChildren().add(wc2.getTurn());
        layout2.setRight(right2);
        //下 可能出现的错误
        HBox bot2 = new HBox();
        bot2.getChildren().add(wc2.getNote());
        layout2.setBottom(bot2);
        //加入scene2
        scene2 = new Scene(layout2, 600, 600);

        //9*9 scene
        BorderPane layout3 = new BorderPane();
        //中 w3游戏组件
        WumpusPane w3 = new WumpusPane(b3,9);
        WumpusController wc3 = new WumpusController(b3, w3);
        w3.setOnMouseClicked(wc3);
        layout3.setCenter(w3);
        //w3游戏界面构建
        // 上 按钮
        HBox top3 = new HBox();
        Button topButton3 = new Button("quit");
        topButton3.setOnAction(e->window.close());
        top3.getChildren().add(topButton3);
        layout3.setTop(top3);
        //左 游戏说明
        VBox left3 = new VBox();
        Label intro3 = new Label(rule);
        left3.getChildren().add(intro3);
        layout3.setLeft(left3);
        //右 到谁了的提示
        VBox right3 = new VBox();
        right3.getChildren().add(wc3.getTurn());
        layout3.setRight(right3);
        //下 可能出现的错误
        HBox bot3 = new HBox();
        bot3.getChildren().add(wc3.getNote());
        layout3.setBottom(bot3);
        //加入scene3
        scene3 = new Scene(layout3, 600, 600);

        //欢迎界面
        VBox hello = new VBox();
        hello.setPadding(new Insets(10,10,10,10));
        //欢迎语句 模式 棋盘大小
        Label welcome = new Label("Welcome to our Wumpus Game! Please choose your mode and the size of the game!");
        Button p2p = new Button("play with your friend!");
        p2p.setOnAction(e-> d[0] = 1);
        Button p2ai = new Button("play with AI!");
        p2ai.setOnAction(e-> d[0] = 2);
//        Button ai2ai = new Button("Watch 2 AI Battle!");
//        ai2ai.setOnAction(e-> d[0] = 3);
        Label note = new Label();
        Label chooseNote = new Label("Please click your game size to start the game!");
        Button mode1 = new Button("3*3");
        mode1.setOnAction(e->{
            if (d[0] == 0){
                note.setText("Please Choose your mode before starting the game!");
            }else{
                wc1.setMode(d[0]);
                window.setScene(scene1);
            }
        });
        Button mode2 = new Button("6*6");
        mode2.setOnAction(e->{
            if (d[0] == 0){
                note.setText("Please Choose your mode before starting the game!");
            }else{
                wc2.setMode(d[0]);
                window.setScene(scene2);
            }
        });
        Button mode3 = new Button("9*9");
        mode3.setOnAction(e->{
            if (d[0] == 0){
                note.setText("Please Choose your mode before starting the game!");
            }else{
                wc3.setMode(d[0]);
                window.setScene(scene3);
            }
        });

        hello.getChildren().addAll(welcome, p2p, p2ai,
                //ai2ai,
                note, chooseNote, mode1, mode2, mode3);

        openScene = new Scene(hello, 500, 500);

        //主界面
        window.setScene(openScene);
        window.show();

    }


    public static void main(String[] args){
        launch(args);
        //gameplay();
    }
    public static void gameplay(){
        Scanner in;
        String userInput;
        in = new Scanner(System.in);
        System.out.println("Please type in the size you want for your game");
        userInput = in.nextLine();
        int num = Integer.parseInt(userInput);
        if (num%3 != 0) {
            System.out.println("input is not a multiple of 3, cannot build board.");
            return;
        }else if (num <= 0){
            System.out.println("input is smaller or equal to 0, cannot build board.");
            return;
        }
        System.out.println("build a "+num+"*"+num+"board.");
        Board b = new Board(num/3);
        b.printBoard();

        int x = -1;
        int y = -1;
        boolean isRed = true;

        while(b.getCountB() != 0 && b.getCountR() != 0){

            in = new Scanner(System.in);
            if (isRed) {
                System.out.println("Red turn!");
            }else{
                System.out.println("Blue turn!");
            }
            System.out.println("choose your piece:");
            int x1, y1;

            while(true) {
                userInput = in.nextLine();
                if (userInput.indexOf(' ')  == -1 ) {
                    System.out.println("wrong format! Please enter your piece again.");
                    continue;
                }
                x1 = Integer.parseInt(userInput.substring(0, userInput.indexOf(' ')));
                y1 = Integer.parseInt(userInput.substring(userInput.indexOf(' ')+1));
                if (x1 >= num || y1 >= num) {
                    System.out.println("x or y out of range! Please enter your piece again.");
                    continue;
                }else if(b.getPiece(x1,y1) == null){
                    System.out.println("You have to choose a piece! Please enter your piece again.");
                    continue;
                }else if((b.getPiece(x1,y1).getTeam() == 0 && isRed) || (b.getPiece(x1,y1).getTeam() == 1 && (!isRed))){
                    break;
                }else if (b.getPiece(x1,y1).getTeam() == -1) {
                    System.out.println("you chosed a pit! Please enter your piece again.");
                    continue;
                }else{
                    System.out.println("Invalid input! Please enter your piece again.");
                    continue;
                }
            }

            int x2, y2;

            System.out.println("move to:");
            while(true) {
                userInput = in.nextLine();
                if (userInput.indexOf(' ')  == -1 ) {
                    System.out.println("wrong format! Please enter your destination again.");
                    continue;
                }
                x2 = Integer.parseInt(userInput.substring(0, userInput.indexOf(' ')));
                y2 = Integer.parseInt(userInput.substring(userInput.indexOf(' ')+1));
                if (x2 >= num || y2 >= num) {
                    System.out.println("x or y out of range! Please enter your destination again.");
                    continue;
                }else{
                    if(!b.movePiece(x1,y1,x2,y2)){
                        System.out.println("you cannot put your piece here! Please enter your destination again.");
                    }else{
                        break;
                    }
                }
            }
            b.printBoard();
            //每完成一步下棋 就要换对手
            isRed = (!isRed);
        }
        if (b.getCountR() == 0 && b.getCountB() == 0) {
            System.out.println("Draw!");
        }else if (b.getCountR() == 0) {
            System.out.println("Blue team wins!");
        }else{
            System.out.println("Red team wins!");
        }
    }
}

