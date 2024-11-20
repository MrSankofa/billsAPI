package com.trackerService.billsAPI;

import com.trackerService.billsAPI.Bill;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillServiceTest {

  @Mock
  private BillRepository billRepository;

  @InjectMocks
  private BillService billService;

  public BillServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllBills_ShouldReturnListOfBills() {
    Bill firstBill = new Bill();
    firstBill.setId(1L);
    firstBill.setName("Rent");
    firstBill.setDueDate(1700764800000L);
    firstBill.setAmount(1200.0);
    firstBill.setBankAccount("Checking");
    firstBill.setCategory("Fixed Monthly Bill");
    firstBill.setPaid(true);

    Bill secondBill = new Bill();
    secondBill.setId(1L);
    secondBill.setName("Electricity");
    secondBill.setDueDate(1701387600000L);
    secondBill.setAmount(150.0);
    secondBill.setBankAccount("Savings");
    secondBill.setCategory("Fixed Utilities Bill");
    secondBill.setPaid(false);

    List<Bill> bills = Arrays.asList(
        firstBill,
        secondBill
    );

    when(billRepository.findAll()).thenReturn(bills);

    List<Bill> result = billService.getAllBills();

    assertEquals(2, result.size());
    assertEquals("Rent", result.get(0).getName());
    verify(billRepository, times(1)).findAll();
  }

  @Test
  void getBillById_ShouldReturnBill_WhenBillExists() {
    Bill firstBill = new Bill();
    firstBill.setId(1L);
    firstBill.setName("Rent");
    firstBill.setDueDate(1700764800000L);
    firstBill.setAmount(1200.0);
    firstBill.setBankAccount("Checking");
    firstBill.setCategory("Fixed Monthly Bill");
    firstBill.setPaid(true);


    when(billRepository.findById(1L)).thenReturn(Optional.of(firstBill));

    Bill result = billService.getBillById(1L);

    assertNotNull(result);
    assertEquals("Rent", result.getName());
    verify(billRepository, times(1)).findById(1L);
  }

  @Test
  void saveBill_ShouldSaveAndReturnBill() {
    Bill secondBill = new Bill();
    secondBill.setId(1L);
    secondBill.setName("Electricity");
    secondBill.setDueDate(1701387600000L);
    secondBill.setAmount(150.0);
    secondBill.setBankAccount("Savings");
    secondBill.setCategory("Fixed Utilities Bill");
    secondBill.setPaid(false);
    when(billRepository.save(secondBill)).thenReturn(secondBill);

    Bill result = billService.saveBill(secondBill);

    assertNotNull(result);
    assertEquals("Electricity", result.getName());
    verify(billRepository, times(1)).save(secondBill);
  }
}

