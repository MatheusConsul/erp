package com.grupo2.telas_controller;

import java.io.IOException;
import com.grupo2.main.App;

import javafx.fxml.FXML;

public class TelaEstoqueController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}