package com.trackerService.billsAPI;


import com.trackerService.billsAPI.Bill;
//import com.example.billtracker.service.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular
public class BillController {
  private final BillService billService;

  public BillController(BillService billService) {
    this.billService = billService;
  }

  @GetMapping
  public List<Bill> getAllBills() {
    return billService.getAllBills();
  }

  @GetMapping("/{id}")
  public Bill getBillById(@PathVariable Long id) {
    return billService.getBillById(id);
  }

  @PostMapping
  public Bill createBill(@RequestBody Bill bill) {
    return billService.saveBill(bill);
  }

  @PutMapping("/{id}")
  public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
    bill.setId(id); // Ensure the ID is set for update
    return billService.saveBill(bill);
  }

  @DeleteMapping("/{id}")
  public void deleteBill(@PathVariable Long id) {
    billService.deleteBill(id);
  }
}
