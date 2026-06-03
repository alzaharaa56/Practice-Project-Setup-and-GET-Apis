package com.example.trainee_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class BookstoreController {
    private static List<InventoryBook> inventoryList = new ArrayList<>();

    @GetMapping("/addInventoryBook")
    public String addInventory(@RequestParam int id,
                               @RequestParam String title,
                               @RequestParam double price,
                               @RequestParam int stock) {

        InventoryBook newBook = new InventoryBook(id, title, price, stock);
        inventoryList.add(newBook);
        return "Item added successfully to the catalog!";
    }

    @GetMapping("/checkStock")
    public String checkStock(@RequestParam int id) {
        for (InventoryBook book : inventoryList) {
            if (book.getId() == id) {
                if (book.getStockCount() > 0) {
                    return "Available: " + book.getTitle() + " costs $" + book.getPrice();
                } else {
                    return "Sold Out: " + book.getTitle() + " is currently out of stock.";
                }
            }
        }
        return "Error: Bookstore does not carry this title.";
    }

    @GetMapping("/reorderReport")
    public String getLowStockReport(@RequestParam int threshold) {
        StringBuilder report = new StringBuilder("Low Stock Report (Threshold: " + threshold + "):\n");
        boolean needsReorder = false;


        for (InventoryBook book : inventoryList) {
            if (book.getStockCount() <= threshold) {
                report.append("- Title: ").append(book.getTitle())
                        .append(" | Current Stock: ").append(book.getStockCount())
                        .append("\n");
                needsReorder = true;
            }
        }


        if (!needsReorder) {
            return "All items are well-stocked. No reorders needed for threshold: " + threshold;
        }


        return report.toString();
    }
}
