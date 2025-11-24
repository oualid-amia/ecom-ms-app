package com.amia.billingservice.web;

import com.amia.billingservice.entities.Bill;
import com.amia.billingservice.feign.CustomerServiceRestClient;
import com.amia.billingservice.feign.InventoryServiceRestClient;
import com.amia.billingservice.model.Customer;
import com.amia.billingservice.model.Product;
import com.amia.billingservice.repository.BillRepository;
import com.amia.billingservice.repository.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerServiceRestClient customerServiceRestClient;
    private InventoryServiceRestClient inventoryServiceRestClient;

    public BillRestController(BillRepository billRepository, ProductItemRepository productItemRepository,
                              CustomerServiceRestClient customerServiceRestClient, InventoryServiceRestClient inventoryServiceRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerServiceRestClient = customerServiceRestClient;
        this.inventoryServiceRestClient = inventoryServiceRestClient;
    }

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerServiceRestClient.findCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryServiceRestClient.findProductById(productItem.getProductId()));
        });

        return bill;
    }
}
