package org.example.Tests;

import org.example.TestComponents.BaseTest;
import org.example.page_objects.CartPage;
import org.example.page_objects.OrderCancellationPage;
import org.example.page_objects.ProductsPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void submit_order(HashMap<String, Object> dataset) throws IOException {
        String username = (String) dataset.get("username");
        String password = (String) dataset.get("password");
        List<String> products = (List<String>) dataset.get("products");
        String expectedUsername = (String) dataset.get("expectedUsername");
        String expectedConfirmationText = (String) dataset.get("expectedConfirmationText");
        String expectedCancelMsg = (String) dataset.get("expectedCancelMsg");

        // Login and proceed with the test steps
        ProductsPage productsPage = login_page.login_application(username, password);
        CartPage cartPage = productsPage.search_and_add_products_to_cart(products.toArray(new String[0]));
        OrderCancellationPage orderCancellationPage = cartPage.submit_order(products.size(), expectedUsername, expectedConfirmationText);
        orderCancellationPage.cancel_orders(expectedCancelMsg);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, Object>> datasets = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//org//example//Data//submit_order_test_data_set.json");
        return datasets.stream().map(data -> new Object[]{data}).toArray(Object[][]::new);
    }
}
