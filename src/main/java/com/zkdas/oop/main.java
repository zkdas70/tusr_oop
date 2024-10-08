package com.zkdas.oop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    /**
     * Метод отвечающий за загрузку и старт GUI
     */
    @Override
    public void start(Stage stage) throws IOException {
        // заррузка шаблонов
        //FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("ItemsTab.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("/com/zkdas/oop/mainWindow.fxml"));

        // создание сцен (сцена это содержимое окна)
        Scene scene = new Scene(fxmlLoader.load());

        // присваеваем имя окну
        stage.setTitle("OOP Business!");
        // присваеваем сцену окну
        stage.setScene(scene);

        stage.setMinWidth(600);
        stage.setMinHeight(500);
        // запускаем отображение окна
        stage.show();
    }

    public static void main(String[] args) {
        launch(); // закпускает Application.launch() (сделает тех. загрузку и запустит .start() )
    }
}