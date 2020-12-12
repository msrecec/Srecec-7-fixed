package main.java.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.sample.covidportal.model.Bolest;
import main.java.sample.covidportal.model.Osoba;
import main.java.sample.covidportal.model.Zupanija;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PretragaOsobeController implements Initializable {
    @FXML
    private TableView tablicaOsoba ;
    @FXML
    private TableColumn<Osoba, String> imeStupac;
    @FXML
    private TableColumn<Osoba, String> prezimeStupac;
    @FXML
    private TableColumn<Osoba, String> korisnickoImeStupac;
    @FXML
    private TableColumn<Osoba, Integer> starostStupac;
    @FXML
    private TableColumn<Osoba, Zupanija> zupanijaStupac;
    @FXML
    private TableColumn<Osoba, Bolest> bolestStupac;
    @FXML
    private TableColumn<Osoba, List<Osoba>> kontaktiraneOsobeStupac;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        List<Osoba> odabranaOsoba = new ArrayList<>();
//        odabranaOsoba.add(Main.odabranaOsoba);
//
//        imeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, String>("ime"));
//        prezimeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, String>("prezime"));
//        korisnickoImeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, String>("korisnickoIme"));
//        starostStupac.setCellValueFactory(new PropertyValueFactory<Osoba, Integer>("starost"));
//        zupanijaStupac.setCellValueFactory(new PropertyValueFactory<Osoba, Zupanija>("zupanija"));
//        bolestStupac.setCellValueFactory(new PropertyValueFactory<Osoba, Bolest>("zarazenBolescu"));
//        kontaktiraneOsobeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, List<Osoba>>("kontaktiraneOsobe"));
//
//        tablicaOsoba.getItems().setAll(odabranaOsoba);
    }
    public void prikaziOsobu(Osoba osoba) {
        List<Osoba> odabranaOsoba = new ArrayList<>();
        odabranaOsoba.add(osoba);

        imeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, String>("ime"));
        prezimeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, String>("prezime"));
        korisnickoImeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, String>("korisnickoIme"));
        starostStupac.setCellValueFactory(new PropertyValueFactory<Osoba, Integer>("starost"));
        zupanijaStupac.setCellValueFactory(new PropertyValueFactory<Osoba, Zupanija>("zupanija"));
        bolestStupac.setCellValueFactory(new PropertyValueFactory<Osoba, Bolest>("zarazenBolescu"));
        kontaktiraneOsobeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, List<Osoba>>("kontaktiraneOsobe"));

        tablicaOsoba.getItems().setAll(odabranaOsoba);
    }
}
