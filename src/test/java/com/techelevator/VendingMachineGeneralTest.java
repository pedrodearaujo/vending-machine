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
    }

    @Override
    public void v_generate_sales_report() {
        int currentCount = -1;
        File salesReportsDir = new File("sales-reports");
        if (salesReportsDir.exists() && salesReportsDir.isDirectory()) {
            File[] salesReportFiles = salesReportsDir.listFiles();
            currentCount = salesReportFiles != null ? salesReportFiles.length : 0;
        }
        assertEquals(currentCount, salesReportCount);
    }

    @Override
    public void e_restart_machine() {
    }

    @Override
    public void v_show_available_items() {
    }

    @Override
    public void e_gsr_return_to_menu_principal() {

    }

    @Override
    public void e_sai_return_to_menu_principal() {

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

    }

    @Override
    public void e_selected_product_error() {

    }

    @Override
    public void e_add_new_value_success() {

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
    }

    @Override
    public void v_espera_produto() {
    }

    @Override
    public void e_menu_compra_opcao_1() {

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

    }

    @Override
    public void e_add_new_value_error() {

    }

    @Override
    public void e_entrega_troco() {
    }

    @Override
    public void e_selected_product_success() {

    }

    @Override
    public void v_espera_moeda() {
    }

    @Test
    public void testPath1() {
        v_start_vending_machine();
        e_open_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_4();
        v_generate_sales_report();
        // e_gsr_return_to_menu_principal();
        // v_menu_principal();
        // v_menu_principal();
        // v_menu_principal();
        // e_menu_principal_opcao_3();
        // v_exit();
        // e_restart_machine();
    }
}
