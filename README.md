# store-management

# Product

Product class is representing a product in the store.

Attributes:
- id
- name
- code: unique product identification code
- category
- quantity: quantity of the product
- price

# Product Repository

Product Repository facilitates interaction with the database.

Methods:
- List<Product> findAll()
- Optional<Product> findById(Integer id);
- List<Product> findAllByCategory(String category);
- Product save(Product product);
- void deleteById(Integer id);

# Product Service 

The Product Service is an interface used to manage application business logic.

Methods:
- List<Product> getProducts()
- Product getProductById(int id)
- List<Product> getProductsByCategory(String category)
- List<Product> getProductsInPriceRange(int min, int max) 
- List<Product> filterProductsByPrice(List<Product> products, int min, int max) {
- Product saveProduct(Product product);
- Product updateProduct(int id_product, Product product);
- Product changePrice(int id_product, double price);
- void deleteProduct(int id);

# Product Service Impl

The Product Service Impl is an implementation of the Product Service interface.

# Product Controller

The Product Controller handles HTTP requests and responses for interaction with products.

Methods:
- List<Product> getProducts()
- Product getProductById(@PathVariable int id) 
- List<Product> getProductsByCategory(@RequestParam(name = "category") String category) 
- List<Product> getProductsInPriceRange(@RequestParam(name = "min") int min, @RequestParam(name = "max") int max)
- Product addProduct(@RequestBody Product product) 
- Product updateProduct(@RequestBody Product product, @PathVariable int id) 
- Product changePrice(@PathVariable int id, @RequestParam(name = "price") double price)
- void deleteProductById

# ProductNotFoundException

This exception is used for when the product searched does not exist.

# GlobalExceptionHandler

This GlobalExceptionHandler class is used to handle the app exceptions.

# ProductServiceTest

The ProductServiceTest unit tests the class of service of products.



