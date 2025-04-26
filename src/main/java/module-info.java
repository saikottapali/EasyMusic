module com.controllers {
    requires javafx.fxml;
    requires javafx.controls;
    requires json.simple;
    requires jfugue;
    requires junit;
    requires javafx.graphics;

    exports com.controllers;
    exports com.easymusicj;
    opens com.controllers to javafx.fxml;
    opens com.easymusicj to javafx.fxml, javafx.graphics;
}
