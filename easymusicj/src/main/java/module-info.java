module com.easymusicj {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jfugue;
    requires junit;
    

    opens com.easymusic to javafx.fxml;
    exports com.easymusic;

    opens com.model to javafx.fxml;
    exports com.model;
}
