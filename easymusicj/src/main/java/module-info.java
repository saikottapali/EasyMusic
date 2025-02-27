module com.easymusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.easymusic to javafx.fxml;
    exports com.easymusic;

    opens com.model to javafx.fxml;
    exports com.model;
}
