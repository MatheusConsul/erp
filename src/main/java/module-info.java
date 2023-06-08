module com.grupo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.loja_do_fulano.telas_controller to javafx.fxml;
    exports com.loja_do_fulano.main;
 
}
