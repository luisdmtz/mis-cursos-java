package org.example.lambdas.consumer;

import java.util.function.Consumer;

public class ConsumerExample { // CAMBIAR NOMBRE DE main a ConsumerExample
    public static void main(String[] args) {
        /*
         * 🛠️ Consumer
         * 💡 Recibe un valor y no retorna nada.
         */

        Consumer<String> consumer = System.out::print;
        consumer.accept("Santiago");

//        Consumer<String> consumer = (S) -> {
//            System.out.println(S);
//        };
//        consumer.accept("Santiago");
        
    }
}