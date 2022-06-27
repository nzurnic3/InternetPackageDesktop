package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class InternetPackageController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private ToggleGroup speedToggle;
    @FXML
    private ToggleGroup bandwidthToggle;
    @FXML
    private ToggleGroup durationToggle;
    @FXML
    private TableView<InternetPackage> tableView;

    ObservableList<InternetPackage> internetPackages = FXCollections.observableArrayList();
    InternetPackage internetPackage;

    public InternetPackageController() {
    }

    public ObservableList<InternetPackage> getInternetPackages() {
        return internetPackages;
    }

    @FXML
    private void initialize() {
    }
    @FXML
    private void saveInternetPackage() {

        internetPackage = new InternetPackage();

        internetPackage.setFirstName(firstName.getText());
        internetPackage.setLastName(lastName.getText());
        internetPackage.setAddress(address.getText());
        internetPackage.setSpeed((String) speedToggle.getSelectedToggle().getUserData());
        internetPackage.setBandwidth((String) bandwidthToggle.getSelectedToggle().getUserData());
        internetPackage.setDuration((String) durationToggle.getSelectedToggle().getUserData());


            if(internetPackage.save()){
                internetPackages.add(internetPackage);
        } else {

            StringBuilder errorMessage = new StringBuilder();

            ArrayList<String> errorList = internetPackage.errorsProperty().get();

            for (String errorList1 : errorList) {
                errorMessage.append(errorList1);
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Internet Package can be saved!");
            alert.setHeaderText(null);
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
            errorList.clear();
        }
    }

    @FXML
    private void closeForm() {
        Platform.exit();
    }

    @FXML
    private void clearForm() {

        firstName.setText("");
        lastName.setText("");
        address.setText("");
        speedToggle.selectToggle(null);
        bandwidthToggle.selectToggle(null);
        durationToggle.selectToggle(null);

    }

    @FXML
    private void deleteInternetPackage(){

        internetPackage = tableView.getSelectionModel().getSelectedItem();
        internetPackages.remove(internetPackage);
    }
}
