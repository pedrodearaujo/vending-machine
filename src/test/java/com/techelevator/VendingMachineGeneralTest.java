package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineGeneralTest implements VendingMachineGeneral, VendingMachineBuyingMenu {
    private VendingMachine testVM;
    private int salesReportCount = 0;
    private int currentItemStock = 0;
    private static final String BUY_ITEM_CODE = "A1";
    private String screen;
    private boolean error = false;

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
        // TODO: Try remove the try exception
        try {
            testVM = new VendingMachine();
        } catch (IOException e) {
            System.out.println("Error while trying to write the machine log. Please try again.");
        }
    }

    @Override
    public void v_show_available_items() {
        // TODO: try check print:
        // A1 Potato Crisps 5 remaining $3.05
        // A2 Stackers 5 remaining $1.45
        // A3 Grain Waves 5 remaining $2.75
        // A4 Cloud Popcorn 5 remaining $3.65
        // B1 Moonpie 5 remaining $1.80
        // B2 Cowtales 5 remaining $1.50
        // B3 Wonka Bar 5 remaining $1.50
        // B4 Crunchie 5 remaining $1.75
        // C1 Cola 5 remaining $1.25
        // C2 Dr. Salt 5 remaining $1.50
        // C3 Mountain Melter 5 remaining $1.50
        // C4 Heavy 5 remaining $1.50
        // D1 U-Chews 5 remaining $0.85
        // D2 Little League Chew 5 remaining $0.95
        // D3 Chiclets 5 remaining $0.75
        // D4 Triplemint 5 remaining $0.75
    }

    @Override
    public void e_gsr_return_to_menu_principal() {
        salesReportCount += 1;
    }

    @Override
    public void e_sai_return_to_menu_principal() {
        // TODO: clean output/screen
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
        testVM.addMoney(20);
    }

    @Override
    public void e_open_menu_principal() {
    }

    @Override
    public void e_menu_principal_opcao_2() {

    }

    @Override
    public void e_menu_principal_opcao_1() {
        // TODO: save output
        testVM.listInventory();
    }

    @Override
    public void v_menu_compra() {
    }

    @Override
    public void v_libera_produto() {
        assertEquals(testVM.getInventory().get(BUY_ITEM_CODE).getQuantity(), currentItemStock - 1);
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
    }

    @Override
    public void e_menu_compra_opcao_3() {
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
        testVM.getChange();
    }

    @Override
    public void e_selected_product_success() {
        screen = "";
        currentItemStock = testVM.getInventory().get(BUY_ITEM_CODE).getQuantity();
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

    @Test
    public void testPath1() {
        v_start_vending_machine();
        e_open_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_2();
        v_menu_compra();
        v_menu_compra();
        e_menu_compra_opcao_1();
        v_espera_moeda();
        e_add_new_value_success();
        v_menu_compra();
        e_menu_compra_opcao_2();
        v_espera_produto();
        e_selected_product_error();
        v_espera_produto_error();
        e_epe_return_menu_compra();
        v_menu_compra();
        v_menu_compra();
        v_menu_compra();
        e_menu_compra_opcao_3();
        v_termina();
        e_entrega_troco();
        v_menu_principal();
        e_menu_principal_opcao_4();
        v_generate_sales_report();
        e_gsr_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_2();
        v_menu_compra();
        v_menu_compra();
        e_menu_compra_opcao_2();
        v_espera_produto();
        e_selected_product_error();
        v_espera_produto_error();
        e_epe_return_menu_compra();
        v_menu_compra();
        v_menu_compra();
        v_menu_compra();
        e_menu_compra_opcao_3();
        v_termina();
        e_entrega_troco();
        v_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_3();
        v_exit();
        e_restart_machine();
    }

    @Test
    public void testPath2() {
        v_start_vending_machine();
        e_open_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_4();
        v_generate_sales_report();
        e_gsr_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_4();
        v_generate_sales_report();
        e_gsr_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_3();
        v_exit();
        e_restart_machine();
    }

    @Test
    public void testPath3() {
        v_start_vending_machine();
        e_open_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_4();
        v_generate_sales_report();
        e_gsr_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_1();
        v_show_available_items();
        e_sai_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_3();
        v_exit();
        e_restart_machine();
    }

    @Test
    public void testPath4() {
        v_start_vending_machine();
        e_open_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_4();
        v_generate_sales_report();
        e_gsr_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_1();
        v_show_available_items();
        e_sai_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_1();
        v_show_available_items();
        e_sai_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_1();
        v_show_available_items();
        e_sai_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_1();
        v_show_available_items();
        e_sai_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_2();
        v_menu_compra();
        e_menu_compra_opcao_2();
        v_espera_produto();
        e_selected_product_error();
        v_espera_produto_error();
        e_epe_return_menu_compra();
        v_menu_compra();
        e_menu_compra_opcao_1();
        v_espera_moeda();
        e_add_new_value_error();
        v_espera_moeda_error();
        e_eme_return_menu_compra();
        v_menu_compra();
        e_menu_compra_opcao_2();
        v_espera_produto();
        e_selected_product_error();
        v_espera_produto_error();
        e_epe_return_menu_compra();
        v_menu_compra();
        e_menu_compra_opcao_2();
        v_espera_produto();
        e_selected_product_error();
        v_espera_produto_error();
        e_epe_return_menu_compra();
        v_menu_compra();
        e_menu_compra_opcao_1();
        v_espera_moeda();
        e_add_new_value_success();
        v_menu_compra();
        e_menu_compra_opcao_2();
        v_espera_produto();
        e_selected_product_error();
        v_espera_produto_error();
        e_epe_return_menu_compra();
        v_menu_compra();
        e_menu_compra_opcao_2();
        v_espera_produto();
        e_selected_product_success();
        v_libera_produto();
    }
}