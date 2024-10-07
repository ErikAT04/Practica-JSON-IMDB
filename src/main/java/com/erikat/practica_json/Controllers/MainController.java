package com.erikat.practica_json.Controllers;

import com.erikat.practica_json.Utils.AlertUtils;
import com.erikat.practica_json.obj.Pelicula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MainController implements Initializable {

    public static final ObjectMapper JSON_MAPPER = new ObjectMapper(); //Mapper de JSON
    @FXML
    private TextField dateTField;

    @FXML
    private TextField dirTField;

    @FXML
    private ListView<Pelicula> filmListView;

    @FXML
    private TextField genreTField;

    @FXML
    private TextField titleTField;

    @FXML
    void onImportClick(ActionEvent event) {
        Pelicula p = filmListView.getSelectionModel().getSelectedItem(); //Coge el objeto seleccionado de la lista
        if (p==null){ //Si el objeto es nulo (Si no se ha seleccionado ningún objeto de la lista)
            AlertUtils.showAlert("Error de selección", "No se ha seleccionado ninguna película", Alert.AlertType.ERROR);
        } else { //Si no, introduce los datos de la película en el formulario
            dateTField.setText(p.getFecha());
            dirTField.setText(p.getDirector());
            genreTField.setText(p.getGenero());
            titleTField.setText(p.getTitulo());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<Pelicula> pelis = JSON_MAPPER.readValue(new File("src/main/resources/utils/peliculas.json"), JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Pelicula.class)); //Se crea la lista del JSON
            ObservableList<Pelicula> peliculaObservableList = FXCollections.observableArrayList(pelis);//Se convierte en ObservableArrayList (Lista de FXCollections)
            filmListView.setItems(peliculaObservableList); //Se añade la lista al ListView
        }catch (IOException e){ //Si no se encuentra el objeto o hay algún error en la lectura del JSON
            System.out.println(e.getMessage());
            AlertUtils.showAlert("Error de JSON", "No se ha leido correctamente", Alert.AlertType.ERROR);
        }
    }
}