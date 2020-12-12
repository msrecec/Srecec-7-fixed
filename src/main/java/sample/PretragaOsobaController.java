package main.java.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.sample.covidportal.model.Bolest;
import main.java.sample.covidportal.model.Osoba;
import main.java.sample.covidportal.model.Zupanija;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PretragaOsobaController {
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
    @FXML
    private TextField unosNazivaOsobe;

    public void pretraga() throws IOException {
        String uneseniNazivOsobe = unosNazivaOsobe.getText().toLowerCase();

        // 1. zadatak - pretraga

        Optional<List<Osoba>> filtriraneOsobePoImenu = Optional.ofNullable(Main.osobe.stream()
                .filter(z -> (z.getIme().toLowerCase().contains(uneseniNazivOsobe)))
                .collect(Collectors.toList()));
        Optional<List<Osoba>> filtriraneOsobePoPrezimenu = Optional.ofNullable(Main.osobe.stream()
                .filter(z -> (z.getPrezime().toLowerCase().contains(uneseniNazivOsobe)))
                .collect(Collectors.toList()));

        Optional<List<Osoba>> filtriraneOsobePoKorisnickomImenu = Optional.ofNullable(Main.osobe.stream()
                .filter(z -> (z.getKorisnickoIme().toLowerCase().contains(uneseniNazivOsobe)))
                .collect(Collectors.toList()));

//        System.out.println(filtriraneZupanije.get(0).getNaziv());


        if(filtriraneOsobePoImenu.isPresent() || filtriraneOsobePoPrezimenu.isPresent() || filtriraneOsobePoKorisnickomImenu.isPresent()) {
            List<Osoba> filtriraneOsobe = new ArrayList<>();
            if(filtriraneOsobePoImenu.isPresent() && filtriraneOsobePoPrezimenu.isPresent() && filtriraneOsobePoKorisnickomImenu.isPresent()) {
                filtriraneOsobe.addAll(filtriraneOsobePoImenu.get());
                filtriraneOsobe.addAll(filtriraneOsobePoPrezimenu.get());
                filtriraneOsobe.addAll(filtriraneOsobePoKorisnickomImenu.get());
            } else if(filtriraneOsobePoImenu.isPresent() && filtriraneOsobePoPrezimenu.isEmpty() && filtriraneOsobePoKorisnickomImenu.isPresent()) {
                filtriraneOsobe.addAll(filtriraneOsobePoImenu.get());
                filtriraneOsobe.addAll(filtriraneOsobePoKorisnickomImenu.get());
            } else if(filtriraneOsobePoImenu.isPresent() && filtriraneOsobePoPrezimenu.isPresent() && filtriraneOsobePoKorisnickomImenu.isEmpty()) {
                filtriraneOsobe.addAll(filtriraneOsobePoImenu.get());
                filtriraneOsobe.addAll(filtriraneOsobePoPrezimenu.get());
            } else if (filtriraneOsobePoImenu.isPresent() && filtriraneOsobePoPrezimenu.isEmpty() && filtriraneOsobePoKorisnickomImenu.isEmpty()) {
                filtriraneOsobe.addAll(filtriraneOsobePoImenu.get());
            } else if(filtriraneOsobePoImenu.isEmpty() && filtriraneOsobePoPrezimenu.isPresent() && filtriraneOsobePoKorisnickomImenu.isPresent()) {
                filtriraneOsobe.addAll(filtriraneOsobePoPrezimenu.get());
                filtriraneOsobe.addAll(filtriraneOsobePoKorisnickomImenu.get());
            } else if(filtriraneOsobePoImenu.isEmpty() && filtriraneOsobePoPrezimenu.isEmpty() && filtriraneOsobePoKorisnickomImenu.isPresent()) {
                filtriraneOsobe.addAll(filtriraneOsobePoKorisnickomImenu.get());
            } else {
                filtriraneOsobe.addAll(filtriraneOsobePoPrezimenu.get());
            }
            Set<Osoba> setFiltriranihOsoba = new HashSet(filtriraneOsobe);
            filtriraneOsobe = new ArrayList<>();
            filtriraneOsobe.addAll(setFiltriranihOsoba);
            imeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, String>("ime"));
            prezimeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, String>("prezime"));
            korisnickoImeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, String>("korisnickoIme"));
            starostStupac.setCellValueFactory(new PropertyValueFactory<Osoba, Integer>("starost"));
            zupanijaStupac.setCellValueFactory(new PropertyValueFactory<Osoba, Zupanija>("zupanija"));
            bolestStupac.setCellValueFactory(new PropertyValueFactory<Osoba, Bolest>("zarazenBolescu"));
            kontaktiraneOsobeStupac.setCellValueFactory(new PropertyValueFactory<Osoba, List<Osoba>>("kontaktiraneOsobe"));

            tablicaOsoba.getItems().setAll(filtriraneOsobe);

            // 2. zadatak - double click event listener

            tablicaOsoba.setRowFactory( t -> {
                TableRow<Osoba> red = new TableRow<>();
                // dodajem callback funkciju na event listener - ala JavaScript ista logika samo brojimo klikove sa getClickCount() metodom umjesto nekakvom statičkom varijablom
                red.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!red.isEmpty())) {

                        // znam da je ruzan workaround preko globalne varijable , no ne stignem napraviti konstruktor niti metodu za prikazivanje preko kontrolera
//                        Main.odabranaOsoba = red.getItem();

                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("pretragaOsobe.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load(), 550, 380);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        Stage stg = new Stage(); // Ovo je koristimo ako zelimo otvoriti u novom prozoru

                        Stage stg = Main.getMainStage(); // Ovo koristimo ako zelimo otvoriti u istom prozoru

                        stg.setScene(scene);
                        stg.show();

                        PretragaOsobeController controller = loader.getController();
                        controller.prikaziOsobu(red.getItem());

//                        Parent pretragaOsobaFrame = null;
//                        try {
//                            pretragaOsobaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaOsobe.fxml"));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }



//                        Scene pretragaOsobaScene = new Scene(pretragaOsobaFrame, 550, 380);
//                        Main.getMainStage().setScene(pretragaOsobaScene);
                    }
                });
                return red ;
            });
        }


//        tablicaZupanija.setItems(FXCollections.observableArrayList(filtriraneZupanije));
    }

}
