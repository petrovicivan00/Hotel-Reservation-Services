package com.sk02.sk02_gui_service.view.panes;

import com.sk02.sk02_gui_service.restclient.dto.notification.ArchivedNotificationDto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ArchivedNotificationPane extends GridPane {

    private ArchivedNotificationDto archivedNotificationDto;

    public ArchivedNotificationPane(ArchivedNotificationDto archivedNotificationDto){
        this.archivedNotificationDto = archivedNotificationDto;
        init();
    }

    private void init(){
        this.getStyleClass().add("custom-pane");

        setAlignment(Pos.CENTER_LEFT);
        setHgap(10);
        setVgap(10);
        setMinWidth(500);
        setPadding(new Insets(20));

        add(new Label("Type:"), 0, 0);
        add(new Label(archivedNotificationDto.getType()), 1, 0);

        add(new Label("Date:"), 0, 1);
        add(new Label(archivedNotificationDto.getCreated().toString()), 1, 1);

        add(new Label("Subject:"), 0, 2);
        add(new Label(archivedNotificationDto.getSubject()), 1, 2);

        add(new Label("Message:"), 0, 3);
        add(new Label(archivedNotificationDto.getMessage()), 1, 3);
    }
}
