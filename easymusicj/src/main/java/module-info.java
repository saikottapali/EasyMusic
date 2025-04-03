module com.easymusicj {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jfugue;
    requires junit;
    requires powermock.api.mockito2;

    opens com.easymusic to javafx.fxml;
    exports com.easymusic;

    opens com.model to javafx.fxml;
    exports com.model;
}
