package WumpusGame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class WumpusPane extends Pane{
    public Canvas canvas;
    public GraphicsContext gc;
    public Board b;

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Board getBoard() {
        return b;
    }

    public void setBoard(Board b) {
        this.b = b;
    }


    public WumpusPane(Board b,double cellsize){
        this.b = b;
        drawPane(cellsize);
        drawChess(cellsize);
        getChildren().add(canvas);
    }

    public void drawPane(double cell){

        canvas = new Canvas(300,300);
        gc = canvas.getGraphicsContext2D();

        gc.clearRect(0,0,300,300);

        //绘制棋盘
        gc.setStroke(Color.BLACK);

        for(int i=0;i < b.getBoardLength();i++){
            for(int j=0;j < b.getBoardLength();j++){
                gc.strokeRect(i *(300/cell), j*(300/cell),300/cell, 300/cell); //画出小方格
            }
        }
        gc.strokeRect(0,0,300,300);

    }

    public void drawPane(double cell, int x,int y){

        gc = canvas.getGraphicsContext2D();

        gc.clearRect(0,0,300, 300);

        //绘制棋盘

        for(int i=0;i<b.getBoardLength();i++)
            for(int j=0;j<b.getBoardLength();j++){
                gc.strokeRect(i *(300/cell), j*(300/cell),300/cell, 300/cell); //画出小方格
                if (x == j && y == i){
                    gc.setStroke(Color.GREEN);
                    gc.fillRect(i *(300/cell), j*(300/cell),300/cell, 300/cell);
                    gc.setStroke(Color.BLACK);
                }
            }
    }

    public void drawChess(double cell){
        /*获取棋子的位置*/
        Image blueWumpus = new Image("pics/blueWumpus.png");
        ImageView bW = new ImageView(blueWumpus);
        Image blueHero = new Image("pics/blueHero.png");
        ImageView bH = new ImageView(blueHero);
        Image blueMage = new Image("pics/blueMage.png");
        ImageView bM = new ImageView(blueMage);
        Image hole = new Image("pics/hole.png");
        ImageView h = new ImageView(hole);
        Image redWumpus = new Image("pics/redWumpus.png");
        ImageView rW = new ImageView(redWumpus);
        Image redHero = new Image("pics/redHero.png");
        ImageView rH = new ImageView(redHero);
        Image redMage = new Image("pics/redMage.png");
        ImageView rM = new ImageView(redMage);

        for(int i=0;i<this.b.getBoardLength();i++)
            for(int j=0;j<this.b.getBoardLength();j++){
                if (this.b.havePiece(i,j)){
                    if(this.b.getPiece(i,j).getTeam() == 0){
                        if (this.b.getPiece(i,j).getType() == 0){
                            gc.drawImage(redWumpus,j *(300/cell), i*(300/cell),300/cell, 300/cell);
                        }else if(this.b.getPiece(i,j).getType() == 1){
                            gc.drawImage(redHero,j *(300/cell), i*(300/cell),300/cell, 300/cell);
                        }else if(this.b.getPiece(i,j).getType() == 2){
                            gc.drawImage(redMage,j *(300/cell), i*(300/cell),300/cell, 300/cell);
                        }
                    }else if(this.b.getPiece(i,j).getTeam() == 1){
                        if (this.b.getPiece(i,j).getType() == 0){
                            gc.drawImage(blueWumpus,j *(300/cell), i*(300/cell),300/cell, 300/cell);
                        }else if(this.b.getPiece(i,j).getType() == 1){
                            gc.drawImage(blueHero,j *(300/cell), i*(300/cell),300/cell, 300/cell);
                        }else if(this.b.getPiece(i,j).getType() == 2){
                            gc.drawImage(blueMage,j *(300/cell), i*(300/cell),300/cell, 300/cell);
                        }
                    }else if(this.b.getPiece(i,j).getTeam() == -1){
                        gc.drawImage(hole,j *(300/cell), i*(300/cell),300/cell, 300/cell);
                    }

                }

            }

    }
}
