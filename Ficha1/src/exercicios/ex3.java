package exercicios;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ex3 {
    public static void main (String[] args){
        if(args.length == 1 && args[0].equals("valores")) {
            try {
                Charset ENCODING = StandardCharsets.UTF_8;
                Path path = Paths.get("C:\\Users\\RIBEIRO\\Documents\\ESTG\\SO\\E_Especial\\Fichas\\Guiao_0\\src\\exercicios\\valores.txt");

                if(Files.exists(path)) {
                    List<String> linhas = Files.readAllLines(path, ENCODING);
                    int num1 = 0, num2 = 0;
                    for(int i = 0; i < linhas.size(); i++){
                        num1 = Integer.parseInt(linhas.get(0));
                        num2 = Integer.parseInt(linhas.get(1));

                    }
                    int soma = num1 + num2;
                    int multiplicacao = num1 * num2;
                    float divisao = (float) num1/num2;
                    int subtracao = num1 - num2;

                    System.out.println("Soma:" + soma);
                    System.out.println("Multiplicação:" + multiplicacao);
                    System.out.println("Divisão: " + String.format("%.2f", divisao));
                    System.out.println("Subtracao:" + subtracao);

                } else {
                    System.out.println("ERRO: O ficheiro\n" + path + " não existe!!!");
                }

            }catch (IOException ex){
                System.out.println("Erro no acesso ao ficheiro");
            }
        } else {
            System.out.println("Número de argumentos inválido!Apenas deve passar 1 argumento");
        }
    }
}
