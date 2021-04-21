import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StockSearchResults {

    public ObservableList<StockInformation> getOneProductFromOneStore (String storeCode, String productCode) {
        List<StockInformation> specificResults = allProduct()
                .stream()
                .filter(stockInformation -> stockInformation.getStoreCode().contains(storeCode)
                        && stockInformation.getProductCode().contains(productCode))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(specificResults);
    }

    public ObservableList<StockInformation> getOneProductAcrossAllStores (String productCode) {
        List<StockInformation> specificResults = allProduct()
                .stream()
                .filter(stockInformation -> stockInformation.getProductCode().contains(productCode))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(specificResults);
    }

    public ObservableList<StockInformation> getStoreCodeForStoreName (String storeName) {
        List<StockInformation> specificResults = allProduct()
                .stream()
                .filter(stockInformation -> stockInformation.getStoreName().contains(storeName))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(specificResults);
    }

    public ObservableList<StockInformation> getProductCodeForProductDescription (String productDescription) {
        List<StockInformation> specificResults = allProduct()
                .stream()
                .filter(stockInformation -> stockInformation.getProductDescription().contains(productDescription))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(specificResults);
    }

    public Set<String> getStoreNames () {
        return allProduct()
                .stream()
                .map(StockInformation::getStoreName)
                .collect(Collectors.toSet());
    }

    public Set<String> getProductDescriptions () {
        return allProduct()
                .stream()
                .map(StockInformation::getProductDescription)
                .collect(Collectors.toSet());
    }

    public ObservableList<StockInformation> getAllProductsFromOneStore (String storeCode) {
        List<StockInformation> specificResults = allProduct()
                .stream()
                .filter(stockInformation -> stockInformation.getStoreCode().contains(storeCode))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(specificResults);
    }

    public ObservableList<StockInformation> allProduct() {
        List<StockInformation> stock = new ArrayList<>();
        stock.add(new StockInformation("Kettle", "500101", "Plymouth", "101", 5));
        stock.add(new StockInformation("Seat", "500102", "Plymouth", "101", 8));
        stock.add(new StockInformation("Furniture", "500103", "Plymouth", "101", 9));
        stock.add(new StockInformation("Kettle", "500101", "Exeter", "102", 7));
        stock.add(new StockInformation("Seat", "500102", "Exeter", "102", 3));
        stock.add(new StockInformation("Furniture", "500103", "Exeter", "102", 12));
        stock.add(new StockInformation("Kettle", "500101", "Torquay", "103", 16));
        stock.add(new StockInformation("Seat", "500102", "Torquay", "103", 4));
        stock.add(new StockInformation("Furniture", "500103", "Torquay", "103", 2));
        return FXCollections.observableArrayList(stock);
    }

//    public Boolean validateCode(String code) {
//        return allProduct().stream().anyMatch(o -> o.ge().equals(name));
//    }

    public Boolean validateCodes(String storeCode, String productCode) {
        return allProduct().stream().anyMatch(o -> o.getStoreCode().equals(storeCode) && o.getProductCode().equals(productCode));
    }

    public Boolean validateStoreCode(String storeCode) {
        return allProduct().stream().anyMatch(o -> o.getStoreCode().equals(storeCode));
    }

    public Boolean validateProductCode(String productCode) {
        return allProduct().stream().anyMatch(o -> o.getProductCode().equals(productCode));
    }
}
