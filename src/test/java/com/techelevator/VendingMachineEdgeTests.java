package com.techelevator;

import org.junit.Test;

public class VendingMachineEdgeTests extends BaseGaphwalkerTests {

    @Test
    public void testPath1() {
        v_start_vending_machine();
        e_open_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_4();
        v_generate_sales_report();
        e_gsr_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_5();
        v_increase_product();
        e_ip_selected_product_success();
        v_increasing_product();
        e_add_new_stock_error();
        v_menu_principal();
        e_menu_principal_opcao_5();
        v_increase_product();
        e_ip_selected_product_error();
        v_menu_principal();
        e_menu_principal_opcao_1();
        v_show_available_items();
        e_sai_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_2();
        v_menu_compra();
        e_menu_compra_opcao_1();
        v_espera_moeda();
        e_add_new_value_success();
        v_menu_compra();
        e_menu_compra_opcao_3();
        v_termina();
        e_entrega_troco();
        v_menu_principal();
        e_menu_principal_opcao_4();
        v_generate_sales_report();
        e_gsr_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_5();
        v_increase_product();
        e_ip_selected_product_success();
        v_increasing_product();
        e_add_new_stock_success();
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
        e_menu_principal_opcao_3();
        v_exit();
        e_restart_machine();
    }

    @Test
    public void testPath3() {
        v_start_vending_machine();
        e_open_menu_principal();
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
        e_menu_principal_opcao_3();
        v_exit();
        e_restart_machine();
    }

    @Test
    public void testPath5() {
        v_start_vending_machine();
        e_open_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_3();
        v_exit();
        e_restart_machine();
    }

    @Test
    public void testPath6() {
        v_start_vending_machine();
        e_open_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_4();
        v_generate_sales_report();
        e_gsr_return_to_menu_principal();
        v_menu_principal();
        e_menu_principal_opcao_5();
        v_increase_product();
        e_ip_selected_product_error();
        v_menu_principal();
        e_menu_principal_opcao_2();
        v_menu_compra();
        e_menu_compra_opcao_3();
        v_termina();
        e_entrega_troco();
        v_menu_principal();
        e_menu_principal_opcao_2();
        v_menu_compra();
        e_menu_compra_opcao_1();
        v_espera_moeda();
        e_add_new_value_success();
        v_menu_compra();
        e_menu_compra_opcao_2();
        v_espera_produto();
        e_selected_product_success();
        v_libera_produto();
        e_produto_liberado();
        v_menu_compra();
        e_menu_compra_opcao_3();
        v_termina();
        e_entrega_troco();
        v_menu_principal();
        e_menu_principal_opcao_2();
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
        e_add_new_value_error();
        v_espera_moeda_error();
        e_eme_return_menu_compra();
        v_menu_compra();
    }
}