package exercicios;

import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);

        System.out.println("Número 1: ");
        int num1 = myObj.nextInt();
        System.out.println("Número 2: ");
        int num2 = myObj.nextInt();

        int soma = num1 + num2;
        int multiplicacao = num1 * num2;
        float divisao = (float) num1/num2;
        int subtracao = num1 - num2;


        System.out.println("Soma:" + soma);
        System.out.println("Multiplicação:" + multiplicacao);
        System.out.println("Divisão: " + String.format("%.2f", divisao));
        System.out.println("Subtracao:" + subtracao);

    }
}
