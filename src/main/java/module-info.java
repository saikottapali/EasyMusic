module com.easymusicj {
    
    requires javafx.fxml;
    requires javafx.controls;
    requires json.simple;
    requires jfugue;
    requires junit;
    requires javafx.base;

    exports com.easymusicj;
    opens com.easymusicj to javafx.fxml;
}
