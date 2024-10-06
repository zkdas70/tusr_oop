module com.zkdas.oop {
    requires javafx.controls;
    requires javafx.fxml;
    requires net.datafaker;


    opens com.zkdas.oop to javafx.fxml;
    exports com.zkdas.oop;
    exports com.zkdas.oop.controller;
    opens com.zkdas.oop.controller to javafx.fxml;
}