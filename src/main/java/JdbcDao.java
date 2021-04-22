import javafx.scene.control.ComboBox;

import java.sql.*;

public class JdbcDao {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD = "Ea$1ano01";
    private static final String SELECT_QUERY = "SELECT * FROM stock_login where un = ? and pwd = ?";
    private static final String SELECT_STORE_LOCATIONS =  "SELECT store_name FROM store_locations";
    private static final String SELECT_PRODUCT =  "SELECT product_description FROM stock, store_locations, product " +
            "WHERE store_name = ? AND store_locations.store_code = stock.store_code AND product.product_code = stock.product_code";
    private static final String SELECT_ALL_PRODUCTS =  "SELECT product_description FROM product";
    private static final String STORE_LOCATION_STORE_CODE = "SELECT stock.store_code FROM stock, store_locations " +
            "WHERE store_name = ? AND store_" +
            "locations.store_code = stock.store_code";
    private static final String PRODUCT_DESCRIPTION_PRODUCT_CODE = "SELECT stock.product_code FROM stock, product\n" +
            "WHERE product_description = ? AND product.product_code = stock.product_code ";
    private static final String SELECT_ALL_CODES = "SELECT stock.store_code, stock.product_code FROM stock \n" +
            "WHERE stock.store_code = ? AND stock.product_code = ?";
    private static final String SELECT_EVERYTHING = "select stock.store_code, store_name, stock.product_code, product_description, quantity from stock, product, store_locations \n" +
            "where stock.product_code = product.product_code and stock.store_code = store_locations.store_code";
    private static final String SELECT_STORE_PRODUCT = "select stock.store_code, store_name, stock.product_code, product_description, quantity from stock, product, store_locations \n " +
    "where stock.product_code = product.product_code and stock.store_code = store_locations.store_code and stock.store_code = ? and stock.product_code = ?";
    private static final String SELECT_STORE_PRODUCTS = "select stock.store_code, store_name, stock.product_code, product_description, quantity from stock, product, store_locations \n " +
            "where stock.product_code = product.product_code and stock.store_code = store_locations.store_code and stock.store_code = ?";
    private static final String SELECT_PRODUCT_AT_ALL_STORES = "select stock.store_code, store_name, stock.product_code, product_description, quantity from stock, product, store_locations \n " +
            "where stock.product_code = product.product_code and stock.store_code = store_locations.store_code and stock.product_code = ?";
    private static final String VALIDATE_STORE_CODE = "select store_code from store_locations where store_code = ?";
    private static final String VALIDATE_PRODUCT_CODE = "select product_code from product where product_code = ?";

    public ResultSet selectLoginCredentials(String username, String password) throws SQLException {

        // Checks if the username and password entered match the record in the table
      Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            return preparedStatement.executeQuery();
    }

    public ResultSet selectStoreProducts(String storeLocationChoice) throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT);
        preparedStatement.setString(1, storeLocationChoice);

        return preparedStatement.executeQuery();
    }

    public ResultSet validateCodes(String storeCodeChoice, String productCodeChoice) throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CODES);
        preparedStatement.setString(1, storeCodeChoice);
        preparedStatement.setString(2, productCodeChoice);

        return preparedStatement.executeQuery();
    }

    public ResultSet validateStoreCode(String storeCodeChoice) throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_STORE_CODE);
        preparedStatement.setString(1, storeCodeChoice);

        return preparedStatement.executeQuery();
    }

    public ResultSet validateProductCode(String productCodeChoice) throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_PRODUCT_CODE);
        preparedStatement.setString(1, productCodeChoice);

        return preparedStatement.executeQuery();
    }

    public ResultSet retrieveStoreProducts(String storeCodeChoice) throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STORE_PRODUCTS);
        preparedStatement.setString(1, storeCodeChoice);

        return preparedStatement.executeQuery();
    }

    public ResultSet retrieveProductAtAllStores(String productCodeChoice) throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_AT_ALL_STORES);
        preparedStatement.setString(1, productCodeChoice);

        return preparedStatement.executeQuery();
    }

    public ResultSet selectStoreProduct(String storeCodeChoice,
                                        String productCodeChoice) throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STORE_PRODUCT);
        preparedStatement.setString(1, storeCodeChoice);
        preparedStatement.setString(2, productCodeChoice);

        return preparedStatement.executeQuery();
    }

    public ResultSet selectStoreCode(String storeLocationChoice) throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(STORE_LOCATION_STORE_CODE);
        preparedStatement.setString(1, storeLocationChoice);

        return preparedStatement.executeQuery();
    }

    public ResultSet selectProductCode(String productDescriptionChoice) throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_DESCRIPTION_PRODUCT_CODE);
        preparedStatement.setString(1, productDescriptionChoice);

        return preparedStatement.executeQuery();
    }

    public ResultSet selectStoreLocations() throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STORE_LOCATIONS);

        return preparedStatement.executeQuery();
    }

    public ResultSet selectProducts() throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);

        return preparedStatement.executeQuery();
    }

    public ResultSet selectEverything() throws SQLException {

        // Checks if the username and password entered match the record in the table
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVERYTHING);

        return preparedStatement.executeQuery();
    }
}