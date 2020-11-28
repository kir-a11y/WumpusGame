package WumpusGame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Platform;

public class AlertBox {
    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setOnCloseRequest(e->{
            Platform.exit();
            System.exit(0);
        });

        Label label = new Label(message);
        Button quit = new Button("close");
        quit.setOnAction(e-> {
            Platform.exit();
            System.exit(0);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, quit);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
    }
}
