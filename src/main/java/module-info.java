module com.example.scene {
    requires javafx.controls;
    requires javafx.fxml;

    requires hutool.all;
    requires java.sql;
    requires okhttp3;
    requires fastjson;

    opens com.example.scene2 to javafx.fxml;
    exports com.example.scene2;
    exports com.example.data;
    opens com.example.data to javafx.fxml;
}