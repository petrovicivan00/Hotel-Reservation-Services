package com.sk02.sk02_gui_service.view.manager.dialogs;

import com.sk02.sk02_gui_service.controller.AddRoomTypeController;
import com.sk02.sk02_gui_service.utils.ManagerUtils;
import com.sk02.sk02_gui_service.view.manager.ManagerView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RoomTypesDialog extends Stage {

    private static RoomTypesDialog instance;

    private TextField tfCategory;
    private TextField tfPrice;
    private TextField tfLowerB;
    private TextField tfUpperB;

    private VBox vbRoomTypes;

    private RoomTypesDialog(){
        init();
    }

    public static RoomTypesDialog getInstance(){
        if(instance == null){
            instance = new RoomTypesDialog();
        }
        return instance;
    }

    private void init(){
        setTitle("Room Types");

        //left
        Label lblTitleLeft = new Label("Room Types:");
        lblTitleLeft.getStyleClass().add("title");

        HBox hbTitle = new HBox();
        hbTitle.setAlignment(Pos.CENTER);
        hbTitle.setPadding(new Insets(15));
        hbTitle.getChildren().add(lblTitleLeft);

        //room types scroll
        vbRoomTypes = new VBox();
        vbRoomTypes.setAlignment(Pos.CENTER);
        vbRoomTypes.setPadding(new Insets(50));
        vbRoomTypes.setSpacing(20);

        ScrollPane spRoomTypes = new ScrollPane();
        spRoomTypes.setContent(vbRoomTypes);
        spRoomTypes.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spRoomTypes.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        vbRoomTypes.minWidthProperty().bind(spRoomTypes.widthProperty().multiply(0.95));

        BorderPane bpLeft = new BorderPane();
        bpLeft.setTop(hbTitle);
        bpLeft.setCenter(spRoomTypes);

        //right
        Button btnBack = new Button("Back");
        btnBack.setMinWidth(80);
        btnBack.getStyleClass().add("button-orange");

        btnBack.setOnAction(actionEvent -> {
            this.close();
        });

        HBox hbBack = new HBox();
        hbBack.setAlignment(Pos.CENTER_RIGHT);
        hbBack.setPadding(new Insets(15));
        hbBack.getChildren().add(btnBack);

        Label lblTitleRight = new Label("Create New Type:");
        lblTitleRight.setPadding(new Insets(30));
        lblTitleRight.getStyleClass().add("title");

        Label lblCategory = new Label("Category:");
        tfCategory = new TextField();
        tfCategory.setMaxWidth(130);

        Label lblPrice = new Label("Price:");
        tfPrice = new TextField();
        tfPrice.setMaxWidth(130);

        Label lblLowerB = new Label("Lower Bound:");
        tfLowerB = new TextField();
        tfLowerB.setMaxWidth(130);

        Label lblUpperB = new Label("Upper Bound:");
        tfUpperB = new TextField();
        tfUpperB.setMaxWidth(130);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.add(lblCategory, 0, 0);
        gridPane.add(tfCategory, 1, 0);

        gridPane.add(lblPrice, 0, 1);
        gridPane.add(tfPrice, 1, 1);

        gridPane.add(lblLowerB, 0, 2);
        gridPane.add(tfLowerB, 1, 2);

        gridPane.add(lblUpperB, 0, 3);
        gridPane.add(tfUpperB, 1, 3);

        Button btnAddType = new Button("Add Type");
        btnAddType.setMinWidth(80);
        btnAddType.getStyleClass().add("button-blue");

        btnAddType.setOnAction(new AddRoomTypeController());

        VBox vbRight = new VBox();
        vbRight.setAlignment(Pos.TOP_CENTER);
        vbRight.setPadding(new Insets(15));
        vbRight.setSpacing(15);
        vbRight.getChildren().addAll(hbBack, lblTitleRight, gridPane, btnAddType);

        //scene settings
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(bpLeft, vbRight);
        splitPane.setDividerPositions(0.6);
        bpLeft.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.5));
        vbRight.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.5));

        Scene scene = new Scene(splitPane, 1000, 600);
        setMinWidth(1010);
        setMinHeight(610);
        scene.getStylesheets().add("styles/style.css");
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
    }

    public void clean(){
        tfCategory.clear();
        tfPrice.clear();
        tfLowerB.clear();
        tfUpperB.clear();
    }

    public TextField getTfCategory() {
        return tfCategory;
    }

    public TextField getTfPrice() {
        return tfPrice;
    }

    public TextField getTfLowerB() {
        return tfLowerB;
    }

    public TextField getTfUpperB() {
        return tfUpperB;
    }

    public VBox getVbRoomTypes() {
        return vbRoomTypes;
    }
}
