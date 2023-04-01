package com.example.esalaf;

import com.exemple.model.Client;
import com.exemple.model.ClientDAO;
import com.exemple.model.product;
import com.exemple.model.productDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class showProducts implements Initializable {


    @FXML
    private TextField prod_nom;

    @FXML
    private TextField prod_desc;

    @FXML
    private TextField prod_prix;
    @FXML
    private TableView<product> prodTab;
    @FXML
    private TableColumn<product, Integer> col_prod_id;
    @FXML
    private TableColumn<product, String> col_prod_nom;

    @FXML
    private TableColumn<product, String> col_prod_desc;

    @FXML
    private TableColumn<product, Float> col_prod_prix;
    @FXML
    private TableColumn<product, Void> col_delete;
    @FXML
    protected void onSaveClick(){

        product prod = new product(0l , prod_nom.getText() , prod_desc.getText(), prod_prix.getText());

        try {
            productDAO prodao = new productDAO();

            prodao.save(prod);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UpdateTable();

    }


    public void UpdateTable() {
        col_prod_id.setCellValueFactory(new PropertyValueFactory<product, Integer>("prod_id"));
        col_prod_nom.setCellValueFactory(new PropertyValueFactory<product, String>("name"));

        col_prod_desc.setCellValueFactory(new PropertyValueFactory<product, String>("description"));
        col_prod_prix.setCellValueFactory(new PropertyValueFactory<product, Float>("price"));

        //add delete button
        col_delete.setCellFactory(col -> {
            TableCell<product, Void> cell = new TableCell<product, Void>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        product prod = getTableRow().getItem();
                        try {
                            productDAO proDAO = new productDAO();
                            proDAO.delete(prod);
                            UpdateTable();
                            Alert alert =new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Delete");
                            alert.setHeaderText("you deleted this product");
                            alert.setContentText("deleted succesfully!");
                            alert.showAndWait();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setGraphic(deleteButton);
                    } else {
                        setGraphic(null);
                    }
                }
            };
            return cell;
        });


        prodTab.setItems(getDataProducts());
    }

    public static ObservableList<product> getDataProducts(){

        productDAO prodao = null;

        ObservableList<product> listfx = FXCollections.observableArrayList();

        try {
            prodao = new productDAO();
            for(product ettemp : prodao.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
    }
}
