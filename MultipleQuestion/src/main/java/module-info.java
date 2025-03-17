module com.tth.multiplequestion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    opens com.tth.multiplequestion to javafx.fxml;
    exports com.tth.multiplequestion;
    exports com.tth.pojo;
}
