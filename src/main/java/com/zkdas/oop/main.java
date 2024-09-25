package com.zkdas.oop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /// Метод отвечающий за загрузку и старт GUI

        // заррузка шаблонов
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("ItemsTab.fxml"));

        // создание сцен (сцена это содержимое окна)
        Scene scene = new Scene(fxmlLoader.load());

        // присваеваем имя окну
        stage.setTitle("ItemsTab!");
        // присваеваем сцену окну
        stage.setScene(scene);

        stage.setMinWidth(500);
        stage.setMinHeight(400);
        // запускаем отображение окна
        stage.show();
    }

    public static void main(String[] args) {
        launch(); // закпускает Application.launch() (сделает тех. загрузку и запустит .start() )
    }
}