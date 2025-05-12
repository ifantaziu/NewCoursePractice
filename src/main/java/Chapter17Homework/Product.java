package Chapter17Homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Product {
    String productName;
    String category;
    Integer price;

    public Product(String productName, String category, Integer price) {
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public Integer getPrice() {
        return price;
    }

    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("tomato", "vegetables", 14));
        productList.add(new Product("soda", "beverages", 18));
        productList.add(new Product("milk", "dairy", 27));
        productList.add(new Product("cheese", "dairy", 35));
        productList.add(new Product("cucumber", "vegetables", 20));
        productList.add(new Product("juice", "beverages", 16));
        productList.add(new Product("corn", "vegetables", 22));

        List<Product> ascendingPriceSortedList = productList.stream()
                .sorted((o1, o2) -> o1.getPrice().compareTo(o2.getPrice())).toList();

        System.out.println("The list sorted by ascending price:" + ascendingPriceSortedList);

        Map<String, List<Product>> groupedProductsByCategory = productList.stream()
                .collect(Collectors.groupingBy(product -> product.getCategory()));
        System.out.println("================================================================");
        System.out.println("The list grouped by categories:");
        groupedProductsByCategory.forEach((category, products) -> {
            System.out.println(category + " category:");
            products.forEach(product ->
                    System.out.println("  " + product));
        });

        Optional<Integer> totalCostOfproducts = productList.stream()
                .sorted(((o1, o2) -> {
                    if (o1.getPrice() == o2.getPrice()) return 0;
                    return o1.getPrice() > o2.getPrice() ? 1 : -1;
                })).map(product -> product.getPrice())
                .reduce((productPrice, product2Price) -> Integer.sum(productPrice, product2Price));
        System.out.println("==================================================================");
        System.out.println("The total cost of products:" + totalCostOfproducts);


        List<String> productNamesList = productList.stream()
                .map(Product::getProductName)
                .toList();
        System.out.println("==================================================================");
        System.out.println("The list of product names:");
        productNamesList.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "\nProduct{name='" + productName + "', category='" + category + "', price=" + price + "}";
    }

}
