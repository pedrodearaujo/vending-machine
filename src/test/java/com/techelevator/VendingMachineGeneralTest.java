package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class VendingMachineGeneralTest implements VendingMachineGeneral, VendingMachineBuyingMenu {
    private VendingMachine testVM;

    @Before
    public void setUp() {
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
    }

    @Override
    public void e_restart_machine() {
    }

    @Override
    public void v_show_available_items() {
    }

    @Override
    public void e_return_to_menu_principal() {

    }

    @Override
    public void v_menu_principal() {
    }

    @Override
    public void e_menu_principal_opcao_4() {

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
//        v_menu_principal();
//        e_menu_principal_opcao_4();
//        v_generate_sales_report();
//        e_return_to_menu_principal();
//        v_menu_principal();
//        v_menu_principal();
//        v_menu_principal();
//        e_menu_principal_opcao_3();
//        v_exit();
//        e_restart_machine();
    }
}


