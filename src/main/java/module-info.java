module com.zkdas.oop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.zkdas.oop to javafx.fxml;
    exports com.zkdas.oop;
}