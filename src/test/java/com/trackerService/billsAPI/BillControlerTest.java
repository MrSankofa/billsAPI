package com.trackerService.billsAPI;

import com.trackerService.billsAPI.Bill;
import com.trackerService.billsAPI.BillService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BillControllerTest {

  private MockMvc mockMvc;

  @Mock
  private BillService billService;

  @InjectMocks
  private BillController billController;

  public BillControllerTest() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(billController).build();
  }

  @Test
  void getAllBills_ShouldReturnListOfBills() throws Exception {

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

    when(billService.getAllBills()).thenReturn(Arrays.asList(
        firstBill,
        secondBill
    ));

    mockMvc.perform(get("/api/bills")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Rent"))
        .andExpect(jsonPath("$[1].name").value("Electricity"));

    verify(billService, times(1)).getAllBills();
  }

  @Test
  void createBill_ShouldSaveAndReturnBill() throws Exception {
    Bill firstBill = new Bill();
    firstBill.setId(1L);
    firstBill.setName("Rent");
    firstBill.setDueDate(1700764800000L);
    firstBill.setAmount(1200.0);
    firstBill.setBankAccount("Checking");
    firstBill.setCategory("Fixed Monthly Bill");
    firstBill.setPaid(true);


    when(billService.saveBill(any(Bill.class))).thenReturn(firstBill);

    mockMvc.perform(post("/api/bills")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"Rent\", \"dueDate\": 1700764800000, \"amount\": 1200, \"bankAccount\": \"Checking\", \"category\": \"Fixed Monthly Bill\", \"isPaid\": true}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Rent"));

    verify(billService, times(1)).saveBill(any(Bill.class));
  }
}
