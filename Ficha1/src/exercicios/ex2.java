package exercicios;

public class ex2 {
    public static void main (String[] args){
        String[] argV = args;

        if(argV.length == 2) {
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);

            int soma = num1 + num2;
            int multiplicacao = num1 * num2;
            float divisao = (float) num1/num2;
            int subtracao = num1 - num2;


            System.out.println("Soma:" + soma);
            System.out.println("Multiplicação:" + multiplicacao);
            System.out.println("Divisão: " + String.format("%.2f", divisao));
            System.out.println("Subtracao:" + subtracao);
        } else {
            System.out.println("Número de argumentos inválido");
        }
    }
}
