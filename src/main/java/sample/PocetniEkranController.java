package main.java.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PocetniEkranController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordField;

    public void login() throws IOException {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if("pero".equals(username) && "pp".equals(password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prijava u aplikaciju");
            alert.setHeaderText("Uspješna prijava");
            alert.setContentText("Čestitamo, uspješno ste se prijavili u aplikaciju!");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("drugiEkran.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ekran aplikacije");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Prijava u aplikaciju");
            alert.setHeaderText("Nespješna prijava");
            alert.setContentText("Unijeli ste neispravno korisničko ime ili lozinku!");
            alert.showAndWait();
        }
    }

}
