module com.grupo2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.grupo2.telas_controller to javafx.fxml;
    //exports com.grupo2.telas_controller;
    exports com.grupo2.main;
    //opens com.grupo2.main;
}
