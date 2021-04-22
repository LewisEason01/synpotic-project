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
        stock.add(new StockInformation("101", "Plymouth", "500101", "Kettle", 5));
        stock.add(new StockInformation("101", "Plymouth", "500101", "Seat", 6));
        stock.add(new StockInformation("101", "Plymouth", "500101", "Furniture", 14));
        stock.add(new StockInformation("102", "Exeter", "500102", "Kettle", 23));
        stock.add(new StockInformation("102", "Exeter", "500102", "Seat", 2));
        stock.add(new StockInformation("102", "Exeter", "500102", "Furniture", 1));
        stock.add(new StockInformation("103", "Torquay", "500103", "Kettle", 17));
        stock.add(new StockInformation("103", "Torquay", "500103", "Seat", 9));
        stock.add(new StockInformation("103", "Torquay", "500103", "Furniture", 8));
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
