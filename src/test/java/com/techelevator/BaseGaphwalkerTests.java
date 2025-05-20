package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;

public class BaseGaphwalkerTests implements VendingMachineGeneral, VendingMachineBuyingMenu {
  private VendingMachine testVM;
  private int salesReportCount = 0;
  private int currentItemStock = 0;
  private double totalMoneySpent = 0;
  private double totalMoneyAdded = 0;
  private static final String BUY_ITEM_CODE = "A1";
  private static final int MONEY_ADDED_TO_MACHINE = 20;
  private String screen;
  private boolean error = false;

  private final List<String> allCodes = Arrays.asList(
      "A1",
      "A2",
      "A3",
      "A4",
      "B1",
      "B2",
      "B3",
      "B4",
      "C1",
      "C2",
      "C3",
      "C4",
      "D1",
      "D2",
      "D3",
      "D4");

  private final List<String> allName = Arrays.asList(
      "Potato Crisps",
      "Stackers",
      "Grain Waves",
      "Cloud Popcorn",
      "Moonpie",
      "Cowtales",
      "Wonka Bar",
      "Crunchie",
      "Cola",
      "Dr. Salt",
      "Mountain Melter",
      "Heavy",
      "U-Chews",
      "Little League Chew",
      "Chiclets",
      "Triplemint");

  // Stores the content that would be printed to the console (System.out)
  private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  // Save the original System.out (the actual console output), to restore later
  private PrintStream originalOut = System.out;

  private int getCurrentSalesReportCount() {
    File salesReportsDir = new File("sales-reports");
    if (salesReportsDir.exists() && salesReportsDir.isDirectory()) {
      File[] salesReportFiles = salesReportsDir.listFiles();
      return salesReportFiles != null ? salesReportFiles.length : 0;
    }
    return -1;
  }

  @Before
  public void setUp() {
    // Before each test, redirect System.out to capture test output
    resetAndRedirectOut();

    File salesReportsDir = new File("sales-reports");
    if (salesReportsDir.exists() && salesReportsDir.isDirectory()) {
      File[] salesReportFiles = salesReportsDir.listFiles();
      salesReportCount = salesReportFiles != null ? salesReportFiles.length : 0;
    }
    try {
      testVM = new VendingMachine();
    } catch (IOException e) {
      System.out.println("Error while trying to write the machine log. Please try again.");
    }
  }

  @After
  public void tearDown() {
    // After each test, restore standard output to the console
    restoreOriginalOut();
  }

  // Redirect System.out to the outContent buffer
  // This allows you to capture everything that is printed with
  // System.out.println(...)
  public void resetAndRedirectOut() {
    outContent.reset();
    System.setOut(new PrintStream(outContent));
  }

  // Restore standard output to the console (original System.out)
  public void restoreOriginalOut() {
    System.setOut(originalOut);
  }

  @Override
  public void v_start_vending_machine() {
    testVM.getInventory().values()
        .forEach(product -> assertEquals(5, product.getQuantity()));
    assertEquals(0, testVM.getMachineBalance(), 0.0001);
  }

  @Override
  public void v_exit() {
    assertEquals("Thanks for using the VENDO-MATIC 800. Have a wonderful day!", screen);
  }

  @Override
  public void v_generate_sales_report() {
    // Ensure that the reports are generated with different names and no overwriting
    // the existing
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.out.println("Thread was interrupted, failed to complete sleep.");
    }

    int currentSalesReportCount = getCurrentSalesReportCount();
    assertEquals(currentSalesReportCount, salesReportCount + 1);
  }

  @Override
  public void e_restart_machine() {
    salesReportCount = getCurrentSalesReportCount();
    screen = "";
    try {
      testVM = new VendingMachine();
    } catch (IOException e) {
      System.out.println("Error while trying to write the machine log. Please try again.");
    }
  }

  @Override
  public void v_show_available_items() {
    assertTrue(allCodes.stream().allMatch(outContent.toString()::contains));
    assertTrue(allName.stream().allMatch(outContent.toString()::contains));
  }

  @Override
  public void e_gsr_return_to_menu_principal() {
    salesReportCount += 1;
  }

  @Override
  public void e_sai_return_to_menu_principal() {
    resetAndRedirectOut();
  }

  @Override
  public void v_menu_principal() {
    assertEquals(0, testVM.getMachineBalance(), 0.0001);
  }

  @Override
  public void e_menu_principal_opcao_4() {
    VendingMachineSalesReport salesReport = new VendingMachineSalesReport(testVM);
    try {
      salesReport.generateSalesReport();
    } catch (FileNotFoundException e) {
      System.out.println("Error while trying to write sales report. Please try again.");
    }
  }

  @Override
  public void e_menu_principal_opcao_3() {
    screen = "Thanks for using the VENDO-MATIC 800. Have a wonderful day!";
  }

  @Override
  public void e_selected_product_error() {
    screen = "";
    try {
      testVM.transaction("Z1");
    } catch (Exception e) {
      error = true;
      screen = "INVALID INPUT. Please try again and enter a valid item code.";
    }
  }

  @Override
  public void e_add_new_value_success() {
    screen = "";
    testVM.addMoney(MONEY_ADDED_TO_MACHINE);
    totalMoneyAdded += MONEY_ADDED_TO_MACHINE;
  }

  @Override
  public void e_open_menu_principal() {
  }

  @Override
  public void e_menu_principal_opcao_2() {

  }

  @Override
  public void e_menu_principal_opcao_1() {
    resetAndRedirectOut();
    testVM.listInventory();
  }

  @Override
  public void v_menu_compra() {
  }

  @Override
  public void v_libera_produto() {
    assertEquals(testVM.getInventory().get(BUY_ITEM_CODE).getQuantity(), currentItemStock - 1);
    assertEquals(testVM.getMachineBalance(), totalMoneyAdded - totalMoneySpent, 0.0001);
  }

  @Override
  public void v_espera_produto() {
    assertEquals("Please enter the code for the item you'd like to purchase: ", screen);
  }

  @Override
  public void e_menu_compra_opcao_1() {
    screen = "How much money would you like to add? ($1, $2, $5, $10, or $20)";
  }

  @Override
  public void v_termina() {
    assertEquals(0, totalMoneySpent, 0.0001);
  }

  @Override
  public void e_menu_compra_opcao_3() {
    totalMoneySpent = 0;
  }

  @Override
  public void e_produto_liberado() {
  }

  @Override
  public void e_menu_compra_opcao_2() {
    screen = "Please enter the code for the item you'd like to purchase: ";
  }

  @Override
  public void e_add_new_value_error() {
    screen = "";
    error = true;
    screen = "INVALID INPUT. Please enter 1, 2, 5, 10, or 20.";
  }

  @Override
  public void e_entrega_troco() {
    // TODO? Como verificar isso? Aponta para o menu principal...
    testVM.getChange();
  }

  @Override
  public void e_selected_product_success() {
    screen = "";

    currentItemStock = testVM.getInventory().get(BUY_ITEM_CODE).getQuantity();
    totalMoneySpent += testVM.getInventory().get(BUY_ITEM_CODE).getPrice();

    testVM.transaction(BUY_ITEM_CODE);
  }

  @Override
  public void v_espera_moeda() {
    assertEquals("How much money would you like to add? ($1, $2, $5, $10, or $20)", screen);
  }

  @Override
  public void v_espera_produto_error() {
    if (error) {
      assertEquals("INVALID INPUT. Please try again and enter a valid item code.", screen);
    }
  }

  @Override
  public void e_epe_return_menu_compra() {
    if (error) {
      error = false;
      screen = "";
    }
  }

  @Override
  public void v_espera_moeda_error() {
    if (error) {
      assertEquals("INVALID INPUT. Please enter 1, 2, 5, 10, or 20.", screen);
    }
  }

  @Override
  public void e_eme_return_menu_compra() {
    if (error) {
      error = false;
      screen = "";
    }
  }

  @Override
  public void e_menu_principal_opcao_5() {

  }

  @Override
  public void e_ip_selected_product_error() {

  }

  @Override
  public void e_ipe_return_to_menu_principal() {

  }

  @Override
  public void e_ip_selected_product_success() {

  }

  @Override
  public void e_add_new_stock_success() {

  }

  @Override
  public void e_add_new_stock_error() {

  }

  @Override
  public void e_eqe_return_to_menu_principal() {

  }

  @Override
  public void v_increase_product() {

  }

  @Override
  public void v_increase_product_error() {

  }

  @Override
  public void v_espera_quantidade() {

  }

  @Override
  public void v_espera_quantidade_error() {

  }
}
