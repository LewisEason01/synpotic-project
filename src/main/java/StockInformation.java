public class StockInformation {
    private String productDescription;
    private String productCode;
    private String storeName;
    private String storeCode;
    private Integer quantity;

    public StockInformation(String storeCode, String storeName, String productCode, String productDescription, Integer quantity) {
        setProductDescription(productDescription);
        setProductCode(productCode);
        setStoreName(storeName);
        setStoreCode(storeCode);
        setQuantity(quantity);
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

