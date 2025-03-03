module com.tth.multiplequestion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.tth.multiplequestion to javafx.fxml;
    exports com.tth.multiplequestion;
}
