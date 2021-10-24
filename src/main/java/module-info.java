module com.example.maoxiao {
    requires javafx.controls;
    requires javafx.fxml;
    requires hutool.all;
    requires java.sql;

    opens com.example._1123 to javafx.fxml;
    exports com.example._1123;
    exports com.example.data;
    opens com.example.data to javafx.fxml;
}