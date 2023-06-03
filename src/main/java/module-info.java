module com.grupo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.grupo2.telas_controller to javafx.fxml;
    exports com.grupo2.main;
 
}
