package com.sk02.sk02_gui_service.controller;

import com.sk02.sk02_gui_service.restclient.clients.notification.NotificationRestClient;
import com.sk02.sk02_gui_service.restclient.dto.notification.ArchivedNotificationDto;
import com.sk02.sk02_gui_service.view.panes.ArchivedNotificationPane;
import com.sk02.sk02_gui_service.view.shared.ErrorDialog;
import com.sk02.sk02_gui_service.view.shared.NotificationsDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class FilterNotificationsController implements EventHandler<ActionEvent> {

    private NotificationRestClient notificationRestClient;

    public FilterNotificationsController() {
        notificationRestClient = new NotificationRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String type = NotificationsDialog.getInstance().getTfType().getText();
        LocalDate dateStart = NotificationsDialog.getInstance().getDpStart().getValue();
        LocalDate dateEnd = NotificationsDialog.getInstance().getDpEnd().getValue();

        if((dateStart != null && dateEnd == null) || (dateStart == null && dateEnd != null)){
            new ErrorDialog("Wrong Date Input!").show();
            return;
        }

        try {
            List<ArchivedNotificationDto> archivedNotifications = notificationRestClient.getArchivedNotifications(type, dateStart, dateEnd);
            NotificationsDialog.getInstance().getVbNotifications().getChildren().clear();

            for(ArchivedNotificationDto archivedNotificationDto: archivedNotifications){
                NotificationsDialog.getInstance().getVbNotifications().getChildren().add(new ArchivedNotificationPane(archivedNotificationDto));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
