package com.code;

import java.awt.EventQueue;
import java.lang.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Table().setVisible(true);
            }
        });
    }
}



// Modificar el numero de permits para forzar un deadlock
