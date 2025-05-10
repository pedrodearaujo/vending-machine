package com.techelevator;


import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.AfterExecution;
import org.graphwalker.java.annotation.BeforeExecution;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.Item;

@GraphWalker()
public class VendingMachineGeneralTest extends ExecutionContext implements VendingMachineGeneral, VendingMachineBuyingMenu {
    private static final Logger logger = LoggerFactory.getLogger(VendingMachineGeneralTest.class);
    private List<Item> products;

    @BeforeExecution
    public void setup() {
        logger.info("Preparing the Java Vending Machine");
        products = new ArrayList<>();
        products.add(new Item("A1","Potato Crisps",3.05,"Chip"));
        products.add(new Item("A2","Stackers",1.45,"Chip"));
        products.add(new Item("Z1", "inexistent_1", 3.05,"Chip"));
        products.add(new Item("Z2", "inexistent_2", 1.45,"Chip"));
    }

    @AfterExecution
    public void cleanup() {
        logger.info("Cleanup happens here");
    }

    @Override
    public void v_start_vending_machine() {
        logger.info("Starting the vending machine");
        for (Item product : products) {
            assertEquals(product.getQuantity(), 5);
        }
    }

    @Override
    public void v_exit() {
        logger.info("Exiting the vending machine");
    }

    @Override
    public void e_seleciona_opcao_2() {
        logger.info("Selected option 2");
    }

    @Override
    public void e_seleciona_opcao_1() {
        logger.info("Selected option 1");
    }

    @Override
    public void e_seleciona_opcao_4() {
        logger.info("Selected option 4");
    }

    @Override
    public void e_seleciona_opcao_3() {
        logger.info("Selected option 3");
    }

    @Override
    public void v_generate_sales_report() {
        logger.info("Generating sales report");
    }

    @Override
    public void e_return_to_menu() {
        logger.info("Returning to menu");
    }

    @Override
    public void e_restart_machine() {
        logger.info("Restarting machine");
    }

    @Override
    public void v_show_available_items() {
        logger.info("Showing available items");
    }

    @Override
    public void v_menu_principal() {
        logger.info("At main menu");
    }

    @Override
    public void e_open_menu_principal() {
        logger.info("Opening main menu");
    }

    @Override
    public void v_menu_compra() {
        logger.info("At purchase menu");
    }

    @Override
    public void v_libera_produto() {
        logger.info("Product dispensed");
    }

    @Override
    public void v_espera_produto() {
        logger.info("Waiting for product selection");
    }

    @Override
    public void e_add_newValue() {
        logger.info("Adding new value");
    }

    @Override
    public void v_termina() {
        logger.info("Transaction completed");
    }

    @Override
    public void e_produto_liberado() {
        logger.info("Product released");
    }

    @Override
    public void e_selectedProduct() {
        logger.info("Product selected");
    }

    @Override
    public void e_entrega_troco() {
        logger.info("Returning change");
    }

    @Override
    public void v_espera_moeda() {
        logger.info("Waiting for coin insertion");
    }

    @Test
    public void testPath1() {
        v_start_vending_machine();
        // e_open_menu_principal();
        // v_menu_principal();
        // e_seleciona_opcao_2();
        // v_menu_compra();
        // v_menu_compra();
        // e_seleciona_opcao_2();
        // v_espera_produto();
        // e_selectedProduct();
        // v_menu_compra();
        // e_seleciona_opcao_3();
        // v_termina();
        // e_entrega_troco();
        // v_menu_principal();
        // v_menu_principal();
        // e_seleciona_opcao_3();
        // v_exit();
        // e_restart_machine();
    }
}


