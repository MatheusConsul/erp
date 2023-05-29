package com.grupo2.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private static Stage stageLogin;

    @Override
    public void start(Stage stagePrincipal) throws IOException {

        scene = new Scene(loadFXML("sceneInicial"));
        
        stagePrincipal.setTitle("Sistema ERP");
        String iconPath = App.class.getResource("/com/grupo2/imagens/icones/iconePequeno.png").toExternalForm();
        System.out.println("+++++++++++++++++++++++\n"+iconPath);
        stagePrincipal.getIcons().add(new Image(iconPath));

        stagePrincipal.setScene(scene);
        stagePrincipal.setMaximized(true);
        stagePrincipal.show();

        stageLogin = new Stage();
        stageLogin.setTitle("Sistema ERP");
        stageLogin.getIcons().add(new Image(iconPath));
        stageLogin.initModality(Modality.APPLICATION_MODAL);
        stageLogin.initOwner(stagePrincipal);
        Scene sceneLogin = new Scene(loadFXML("telaLogin"));
        stageLogin.setScene(sceneLogin);
        stageLogin.showAndWait();
       
    }


    static public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        stageLogin.close();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/grupo2/telas/"+fxml + ".fxml"));
        
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}