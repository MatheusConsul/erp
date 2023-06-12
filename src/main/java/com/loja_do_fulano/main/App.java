package com.loja_do_fulano.main;

import java.io.IOException;
import com.loja_do_fulano.telas_controller.TelaLoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    public static boolean statusConexao = false;

    @Override
    public void start(Stage stagePrincipal) throws IOException {

        scene = new Scene(loadFXML("sceneInicial"));
        
        stagePrincipal.setTitle("Sistema ERP");
        String iconPath = App.class.getResource("/com/loja_do_fulano/imagens/icones/iconePequeno.png").toExternalForm();
        stagePrincipal.getIcons().add(new Image(iconPath));

        stagePrincipal.setScene(scene);
        stagePrincipal.setMaximized(true);
        stagePrincipal.show();

        telaLogin(stagePrincipal);

    }

    static public void telaLogin(Stage stagePrincipal) throws IOException{
        
        String iconPath = App.class.getResource("/com/loja_do_fulano/imagens/icones/iconePequeno.png").toExternalForm();
        Stage stageLogin = new Stage();
        stageLogin.setTitle("Sistema ERP");
        stageLogin.getIcons().add(new Image(iconPath));
        stageLogin.initModality(Modality.APPLICATION_MODAL);
        stageLogin.initOwner(stagePrincipal);
        stageLogin.setResizable(false);
        TelaLoginController.setStageLogin(stageLogin);
        Scene sceneLogin = new Scene(loadFXML("telaLogin"));
        stageLogin.setScene(sceneLogin);
        stageLogin.showAndWait();
        
    }


    static public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/loja_do_fulano/telas/"+fxml + ".fxml"));
        
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}

