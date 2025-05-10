package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VendingMachineGeneralTest implements VendingMachineGeneral, VendingMachineBuyingMenu {
    private VendingMachine testVM;
    private int salesReportCount = 0;
    private String screen;
    private boolean error = false;

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
    public void v_start_vending_machine() { // OK
        testVM.getInventory().values()
                .forEach(product -> assertEquals(5, product.getQuantity()));
        assertEquals(0, testVM.getMachineBalance(), 0.0001);
    }

    @Override
    public void v_exit() {
    } // OK

    @Override
    public void v_generate_sales_report() { // Ok
        int currentCount = -1;
        File salesReportsDir = new File("sales-reports");
        if (salesReportsDir.exists() && salesReportsDir.isDirectory()) {
            File[] salesReportFiles = salesReportsDir.listFiles();
            currentCount = salesReportFiles != null ? salesReportFiles.length : 0;
        }
        assertEquals(currentCount, salesReportCount + 1);
    }

    @Override
    public void e_restart_machine() {
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
    public void v_show_available_items() {
    } // OK

    @Override
    public void e_gsr_return_to_menu_principal() {
        salesReportCount += 1;
    }

    @Override
    public void e_sai_return_to_menu_principal() {
    }

    @Override
    public void v_menu_principal() { // OK
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
    }

    @Override
    public void e_selected_product_error() {
        testVM.transaction("Z1");
    }

    @Override
    public void e_add_new_value_success() {
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
    }

    @Override
    public void v_menu_compra() {
    }

    @Override
    public void v_libera_produto() {
    } // Ok

    @Override
    public void v_espera_produto() {
        // Assert screen
    } // Ok

    @Override
    public void e_menu_compra_opcao_1() {
    }

    @Override
    public void v_termina() {
    } // Ok

    @Override
    public void e_menu_compra_opcao_3() {
    }

    @Override
    public void e_produto_liberado() {
    }

    @Override
    public void e_menu_compra_opcao_2() {
    }

    @Override
    public void e_add_new_value_error() {
    }

    @Override
    public void e_entrega_troco() {
    }

    @Override
    public void e_selected_product_success() {
        // TODO: make transaction
    }

    @Override
    public void v_espera_moeda() {
        // Assert IO?
    } // Ok

    @Override
    public void v_espera_produto_error() {

    }

    @Override
    public void e_epe_return_menu_compra() {

    }

    @Override
    public void v_espera_moeda_error() {

    }

    @Override
    public void e_eme_return_menu_compra() {

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
    public void testPath2() { // ok
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
        v_menu_principal();
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
        v_menu_principal();
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
        v_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_1();
        v_show_available_items();
        e_sai_return_to_menu_principal();
        v_menu_principal();
        v_menu_principal();
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
        v_menu_compra();
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

// FALTA O ENTREGA TROCO