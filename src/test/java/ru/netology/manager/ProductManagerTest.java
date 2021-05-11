package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

public class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book book1 = new Book(1, "Java", 1500, "Bates");
    private Book book2 = new Book(2, "C++", 1300, "Sierra");
    private Smartphone smartphone1 = new Smartphone(3, "Xiaomi redmi 4", 10_990, "Xiaomi");
    private Smartphone smartphone2 = new Smartphone(4, "iPhone 12 mini", 69_990, "Apple");
    private Smartphone smartphone3 = new Smartphone(5,"iPhone 12",79_990,"Apple");
    private Smartphone smartphone4 = new Smartphone(6,"iPhone 12 Pro",99_990,"Apple");

    @BeforeEach
    public void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
        manager.add(smartphone4);
    }

    @Test
    public void searchByBookName() {
        Product[] actual = manager.searchBy("Java");
        Product[] expected = new Product[] {book1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByBookAuthor() {
        Product[] actual = manager.searchBy("Sierra");
        Product[] expected = new Product[] {book2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchBySmartphoneName() {
        Product[] actual = manager.searchBy("Xiaomi redmi 4");
        Product[] expected = new Product[] {smartphone1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchBySmartphoneManufacturer() {
        Product[] actual = manager.searchBy("Apple");
        Product[] expected = new Product[] {smartphone2,smartphone3,smartphone4};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNonExistedProduct() {
        Product[] actual = manager.searchBy("Sony");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByEmptyQuery() {
        Product[] actual = manager.searchBy(null);
        Product[] expected = new Product[]{};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void removeById() {
        repository.removeById(4);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book1,book2,smartphone1,smartphone3,smartphone4};
        assertArrayEquals(expected,actual);
    }

}
