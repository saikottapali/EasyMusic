module com.controllers {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires com.google.gson;
    requires jfugue;

    exports com.controllers;
    opens com.controllers to javafx.fxml;

    opens com.model to com.google.gson;
}
