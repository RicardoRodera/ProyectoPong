module com.example.pong {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.pong to javafx.fxml;
    opens modelo to javafx.fxml;
    exports com.example.pong;
    exports modelo;
    exports controlador;
    opens controlador to javafx.fxml;
}