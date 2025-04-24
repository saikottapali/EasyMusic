module com.controllers {
    
    requires javafx.fxml;
    requires javafx.controls;
    requires json.simple;
    requires jfugue;
    requires junit;
    requires javafx.graphics;

    exports com.controllers;
    opens com.controllers to javafx.fxml;
}
