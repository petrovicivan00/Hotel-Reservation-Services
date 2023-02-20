package com.sk02.sk02_gui_service.view.shared;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorDialog extends Stage {

    public ErrorDialog(String message){
        init(message);
    }

    private void init(String message){
        setTitle("Error!");

        Label lblTitle = new Label(message);
        lblTitle.getStyleClass().add("title-error");

        Button btnOk = new Button("OK");
        btnOk.setMinWidth(100);
        btnOk.getStyleClass().add("button-orange");
        btnOk.setOnAction(actionEvent -> {
            this.close();
        });

        VBox vbMain = new VBox();
        vbMain.setAlignment(Pos.CENTER);
        vbMain.setPadding(new Insets(20));
        vbMain.setSpacing(10);

        vbMain.getChildren().addAll(lblTitle, btnOk);

        Scene scene = new Scene(vbMain, 500, 200);
        setResizable(false);
        initModality(Modality.APPLICATION_MODAL);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
    }
}
