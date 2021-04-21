import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class StockAvailabilityController implements Initializable {

    @FXML TextField productCode, storeCode;
    @FXML Button checkStock, logout, clearStockInfo;
    @FXML ComboBox<String> productDescription = new ComboBox<>();
    @FXML ComboBox<String> storeName = new ComboBox<>();
    @FXML Label stockLabel, totalLabel;

    @FXML private TableView<StockInformation> tableView;
    @FXML private TableColumn<StockInformation, String> storeNameColumn;
    @FXML private TableColumn<StockInformation, String> storeCodeColumn;
    @FXML private TableColumn<StockInformation, String> productDescriptionColumn;
    @FXML private TableColumn<StockInformation, String> productCodeColumn;
    @FXML private TableColumn<StockInformation, Integer> quantityColumn;


    StockSearchResults stockSearchResults = new StockSearchResults();
    AlertBoxes alert = new AlertBoxes();


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle)  {
        productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        productDescriptionColumn.getStyleClass().add("rightAlignedTableColumnHeader");
        productCodeColumn.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        storeNameColumn.setCellValueFactory(new PropertyValueFactory<>("storeName"));
        storeCodeColumn.setCellValueFactory(new PropertyValueFactory<>("storeCode"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        populateStoreNames();
        populateProducts();
        instructions();
        handleStoreNameComboBox();
        handleProductDescriptionComboBox();
//        handleStoreCodeTextField();
//        handleProductCodeTextField();
    }

    public void instructions() {
        stockLabel.setText("Leave ALL fields blank to search for all products across all stores." + "\n" +
                "Fill in ONLY the store fields to search for all products at a specific store." + "\n" +
                "Fill in ONLY the product fields to search for one product across all stores." + "\n" +
                "Fill in ALL fields to search for one product at a specific store.");
    }

    public void handleStoreNameComboBox() {
        storeName.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                storeName.getItems().clear();
            } else {
//                populateStoreProducts();
                populateStoreCode();
            }
        });
    }

//    @FXML
//    public void handleStoreCodeTextField(ActionEvent event) {
//        storeCode.textProperty().addListener((obs, oldValue, newValue) -> {
//            populateStoreNameForStoreCode();
//            });
//    }
//
//    @FXML
//    public void handleProductCodeTextField(ActionEvent event) {
//        storeName.valueProperty().addListener((obs, oldValue, newValue) -> {
//            populateProductDescriptionForProductCode();
//        });
//    }

    public void handleProductDescriptionComboBox() {
        productDescription.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                productDescription.getItems().clear();
            } else {
//                populateProductstoreNames();
                populateProductCode();
            }
        });
    }

        public void populateStoreCode() {
            ObservableList<StockInformation> getStoreCodes =
                    stockSearchResults.getStoreCodeForStoreName(storeName.getValue());
            for(StockInformation storeCodes : getStoreCodes){
                storeCode.setText(storeCodes.getStoreCode());
            }
//        try {
//            String storeNameValue = storeName.getValue();
//            ResultSet resultSet = jdbcDao.selectStoreCode(storeNameValue);
//            while (resultSet.next()) {
//                storeCode.setText(resultSet.getString("store_code"));
//            }
//        } catch (SQLException e){
//            System.out.println("Error: " + e.getMessage());
//        }
    }

    public void populateProductCode() {
        ObservableList<StockInformation> getProductCodes =
                stockSearchResults.getProductCodeForProductDescription(productDescription.getValue());
        for (StockInformation productCodes : getProductCodes) {
            productCode.setText(productCodes.getProductCode());
        }
    }

//        public void populateStoreNameForStoreCode() {
//            ObservableList<StockInformation> getStoreNames =
//                    stockSearchResults.getAllProductsFromOneStore(storeCode.getText());
//            for(StockInformation storeNames : getStoreNames){
//                storeName.setValue(storeNames.getStoreName());
//            }
//        try {
//            String productDescriptionValue = productDescription.getValue();
//            ResultSet resultSet = jdbcDao.selectProductCode(productDescriptionValue);
//            while (resultSet.next()) {
//                productCode.setText(resultSet.getString("product_code"));
//            }
//        } catch (SQLException e){
//            System.out.println("Error: " + e.getMessage());
//        }
//    }

//    public void populateProductDescriptionForProductCode() {
//        ObservableList<StockInformation> getProductDescriptions =
//                stockSearchResults.getOneProductAcrossAllStores(productCode.getText());
//        for (StockInformation productDescriptions : getProductDescriptions) {
//            productDescription.setValue(productDescriptions.getProductDescription());
//        }
//    }

//    public void populateStoreProducts() {
//        try {
//            String storeNameValue = storeName.getValue();
//            ResultSet resultSet = jdbcDao.selectStoreProducts(storeNameValue);
//            while (resultSet.next()) {
//                productDescription.getItems().setAll(resultSet.getString("product_description"));
//            }
//            TextFields.bindAutoCompletion(productDescription.getEditor(), productDescription.getproducts());
//        } catch (SQLException e){
//            System.out.println("Error: " + e.getMessage());
//        }
//    }

    public void populateProducts() {

        for(String productDescriptions : stockSearchResults.getProductDescriptions()){
            productDescription.getItems().addAll(productDescriptions);
        }
        TextFields.bindAutoCompletion(productDescription.getEditor(), productDescription.getItems());

//        try {
//            ResultSet resultSet = jdbcDao.selectProducts();
//            while (resultSet.next()) {
//                productDescription.getItems().addAll(resultSet.getString("product_description"));
//            }
//            TextFields.bindAutoCompletion(productDescription.getEditor(), productDescription.getItems());
//        } catch (SQLException e){
//            System.out.println("Error: " + e.getMessage());
//        }
    }

    public void populateStoreNames() {

        for(String storeNames : stockSearchResults.getStoreNames()){
            storeName.getItems().addAll(storeNames);
        }
        TextFields.bindAutoCompletion(storeName.getEditor(), storeName.getItems());

//        try {
//            ResultSet resultSet = jdbcDao.selectstoreNames();
//            while (resultSet.next()) {
//                storeName.getItems().addAll(resultSet.getString("store_name"));
//            }
//            TextFields.bindAutoCompletion(storeName.getEditor(), storeName.getItems());
//        } catch (SQLException e){
//            System.out.println("Error: " + e.getMessage());
//        }
    }

    public void clearStockInputs() {
        // Clear all fields on the notice page
        storeCode.clear();
        productCode.clear();
        productDescription.setValue(null);
        storeName.setValue(null);
        populateStoreNames();
        populateProducts();
    }

    public void logoutOfApplication(ActionEvent event) throws Exception {
        // Go back to the login page
        Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene noticeScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String confirmInformation = "Logout?";
        String loggedOut = "You have been successfully logged out!";
        boolean submittedInputs = alert.confirmInputsAlertBoxes(confirmInformation);
        if (submittedInputs) {
            window.setScene(noticeScene);
            window.show();
            alert.confirmAlertBoxes(loggedOut);
        }
    }

    public void viewStock(ActionEvent event) {
        // Sends the data to be displayed on the preview
        String invalidStoreCode = "The Store code is incorrect";
        String invalidProductCode = "The Product code is incorrect";
        String allInfo = storeCode.getText() + storeName.getValue() + productCode.getText() + productDescription.getValue();
        int total = tableView.getItems().stream().mapToInt(StockInformation::getQuantity).sum();
        totalLabel.setText("Total is " + total);

        if (!allInfo.isEmpty() && stockSearchResults.validateCodes(storeCode.getText(), productCode.getText())) {
            tableView.setItems(stockSearchResults.getOneProductFromOneStore(storeCode.getText(), productCode.getText()));
        } else if (storeCode.getText().isEmpty() && !productCode.getText().isEmpty() && stockSearchResults.validateProductCode(productCode.getText())) {
            tableView.setItems(stockSearchResults.getOneProductAcrossAllStores(productCode.getText()));
        } else if (!storeCode.getText().isEmpty() && productCode.getText().isEmpty() && stockSearchResults.validateStoreCode(storeCode.getText())) {
            tableView.setItems(stockSearchResults.getAllProductsFromOneStore(storeCode.getText()));
        } else if (storeCode.getText().isEmpty() && productCode.getText().isEmpty()) {
            tableView.setItems(stockSearchResults.allProduct());
        } else if (!stockSearchResults.validateStoreCode(storeCode.getText())) {
            tableView.getItems().clear();
            alert.errorAlertBoxes(invalidStoreCode);
        } else if (!stockSearchResults.validateProductCode(productCode.getText())) {
            tableView.getItems().clear();
            alert.errorAlertBoxes(invalidProductCode);
        }
    }

//        try {
//            ResultSet productQuantity = jdbcDao.selectStoreProductQuantity(stockInformation.getStoreCode(),
//                                                                            stockInformation.getproductCode());
//            ResultSet
//
//            if (storeProduct){
//                while (productQuantity.next()) {
//                    tableView.setText("Product quantity: " + productQuantity.getInt("quantity"));
//                }
//            }
//        }catch (SQLException e) {
//        }
    }
