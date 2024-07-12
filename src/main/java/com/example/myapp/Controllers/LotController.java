package com.example.myapp.Controllers;

import com.example.myapp.DB.model.Customer;
import com.example.myapp.DB.model.Lot;
import com.example.myapp.DB.repository.CustomerRepository;
import com.example.myapp.DB.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lots")
public class LotController {

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lot> getLotById(@PathVariable Long id) {
        return lotRepository.findById(id)
                .map(lot -> ResponseEntity.ok().body(lot))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Lot> createLot(@RequestBody Lot lot) {
        if (lot.getCustomer() != null) {
            String customerCode = lot.getCustomer().getCustomerCode();
            customerRepository.findByCustomerCode(customerCode)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
        }
        Lot savedLot = lotRepository.save(lot);
        return ResponseEntity.ok().body(savedLot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lot> updateLot(@PathVariable Long id, @RequestBody Lot lotDetails) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lot.setLotName(lotDetails.getLotName());
                    if (lotDetails.getCustomer() != null) {
                        String customerCode = lotDetails.getCustomer().getCustomerCode();
                        Customer customer = customerRepository.findByCustomerCode(customerCode)
                                .orElseThrow(() -> new RuntimeException("Customer not found"));
                        lot.setCustomer(customer);
                    }
                    lot.setPrice(lotDetails.getPrice());
                    lot.setCurrencyCode(lotDetails.getCurrencyCode());
                    lot.setNdsRate(lotDetails.getNdsRate());
                    lot.setPlaceDelivery(lotDetails.getPlaceDelivery());
                    lot.setDateDelivery(lotDetails.getDateDelivery());
                    Lot updatedLot = lotRepository.save(lot);
                    return ResponseEntity.ok().body(updatedLot);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLot(@PathVariable Long id) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lotRepository.delete(lot);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
