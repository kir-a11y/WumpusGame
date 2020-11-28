package WumpusGame;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import WumpusGame.WumpusPane;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import java.io.*;

import static java.lang.Thread.sleep;

public class WumpusController implements EventHandler<MouseEvent> {
    private Board b;
    private WumpusPane wumpusPane;
    private boolean isClicked;
    private int[] clickedPos = new int[2];
    private boolean isRed = false;
    private Label note = new Label();
    private Label turn = new Label("Blue Turn!");
    private int mode;
    //1 = p2p, 2 = p2ai, 3 = ai2ai


    public WumpusController(Board board, WumpusPane wPane) {
        this.b = board;
        this.wumpusPane = wPane;
        mode = 1;
    }

    public void setMode(int m) {
        this.mode = m;
    }

    public Label getNote() {
        return note;
    }

    public Label getTurn() {
        return turn;
    }

    @Override
    public void handle(MouseEvent event) {
        if (mode == 1 || mode == 2) {
            if (mode == 2 && isRed){
                note.setText("You cannot move when AI is moving!");
                return;
            }
            double cell = b.getBoardLength();
            //获取鼠标点击坐标
            double x = event.getX();
            double y = event.getY();
            System.out.println(x);
            System.out.println(y);
            /*映射到数组中的坐标*/
            int j = (int) (x / (300 / cell));
            int i = (int) (y / (300 / cell));
            //System.out.println(i);
            //System.out.println(j);

            /*改变棋盘状态*/
            if (isClicked) {
                if (i == clickedPos[0] && j == clickedPos[1]) {
                    //System.out.println("here");
                    isClicked = false;
                }//同色
                else if (!b.havePiece(i, j)) {
                    if (b.movePiece(clickedPos[0], clickedPos[1], i, j)) {
                        isRed = (!isRed);
                        if (isRed) {
                            b.setIsRed(true);
                            turn.setText("Red Turn!");
                        } else {
                            b.setIsRed(false);
                            turn.setText("Blue Turn!");
                        }
                        isClicked = false;
                    }
                } else if (b.getPiece(i, j).getTeam() == b.getPiece(clickedPos[0], clickedPos[1]).getTeam()) {
                    note.setText("You cannot eat your own team mate!");
                    return;
                } else if (b.movePiece(clickedPos[0], clickedPos[1], i, j)) {
                    isRed = (!isRed);
                    if (isRed) {
                        b.setIsRed(true);
                        turn.setText("Red Turn!");
                    } else {
                        b.setIsRed(false);
                        turn.setText("Blue Turn!");
                    }
                    isClicked = false;
                } else {
                    note.setText("You cannot move to that location!");
                }
            } else {
                if (!b.havePiece(i, j)) {
                    note.setText("");
                } else {
                    //错误的颜色
                    if ((b.getPiece(i, j).getTeam() == 1 && isRed) || (b.getPiece(i, j).getTeam() == 0 && (!isRed))) {
                        note.setText("You cannot choose your opponent's piece!");
                    } else if (b.getPiece(i, j).getTeam() != -1) {
                        isClicked = true;
                        clickedPos[0] = i;
                        clickedPos[1] = j;
                    }
                }
            }

            /*重新渲染棋盘*/
            if (!isClicked) {
                clickedPos[0] = -1;
                clickedPos[1] = -1;
            }
            wumpusPane.drawPane(cell, clickedPos[0], clickedPos[1]);

            /*重新渲染棋子*/
            wumpusPane.drawChess(cell);
            b.printBoard();

            if (b.getCountR() == 0 && b.getCountB() == 0) {
                AlertBox.display("result", "It's a draw!");
            } else if (b.getCountR() == 0) {
                AlertBox.display("result", "Blue Team wins!");
            } else if (b.getCountB() == 0) {
                AlertBox.display("result", "Red Team wins!");
            }

            if (mode == 2 && isRed){
                b.calculateValue();
                buildTree tree = new buildTree();
                tree.buildthreelayer(b);
                MinMax optimumTest = new MinMax(b);
                int min = Integer.MIN_VALUE;
                int max = Integer.MAX_VALUE;
                optimumTest.minMax(b, 3, min, max, true);
                note.setText("AI calculating...");
                try {
                    sleep(1000);
                    note.setText("AI moved!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(optimumTest.getBestBoard().getFather().getFather() == null && (optimumTest.getBestBoard().getCountB()==0 || optimumTest.getBestBoard().getCountR()==0)){
                    this.wumpusPane.setBoard(optimumTest.getBestBoard());
                    this.b = optimumTest.getBestBoard();
                }else{
                    Board AI = optimumTest.getBestBoard().getFather().getFather();
                    this.wumpusPane.setBoard(AI.duplicate());
                    this.b = AI.duplicate();
                }

                /*重新渲染棋盘*/
                clickedPos[0] = -1;
                clickedPos[1] = -1;
                wumpusPane.drawPane(cell, clickedPos[0], clickedPos[1]);

                /*重新渲染棋子*/
                wumpusPane.drawChess(cell);
                if (b.getCountR() == 0 && b.getCountB() == 0) {
                    AlertBox.display("result", "It's a draw!");
                } else if (b.getCountR() == 0) {
                    AlertBox.display("result", "Blue Team wins!");
                } else if (b.getCountB() == 0) {
                    AlertBox.display("result", "Red Team wins!");
                }
                isRed = (!isRed);
                if (isRed) {
                    b.setIsRed(true);
                    turn.setText("Red Turn!");
                } else {
                    b.setIsRed(false);
                    turn.setText("Blue Turn!");
                }
                isClicked = false;

                System.out.println("AI moved");
                b.printBoard();
            }


        }
    }

}

