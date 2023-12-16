package exercicios;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ex4 {
    public static void main(String[] args){
        if(args.length == 1 && args[0].equals("conteudo")){
            try{
                Charset ENCODING = StandardCharsets.UTF_8;
                Path path = Paths.get("C:\\Users\\RIBEIRO\\Documents\\ESTG\\SO\\E_Especial\\Fichas\\Guiao_0\\src\\exercicios\\conteudo.txt");

                if(Files.exists(path)){
                    List<String> linhas = Files.readAllLines(path, ENCODING);
                    for(int i = 0; i< linhas.size(); i++){
                        System.err.println("Linha nº" + i + "::" + linhas.get(i));
                    }
                } else {
                    System.out.println("ERRO: O ficheiro\n" + path + " não existe!!!");
                }

            }catch (IOException ex) {
                System.out.println("ERRO: Ocorreu um erro ao ler o ficheiro.");
            }
        } else {
            System.out.println("Argumento inválido!!\n"+
                    "Deve inserir o argumento 'conteudo'.");
        }
    }
}
