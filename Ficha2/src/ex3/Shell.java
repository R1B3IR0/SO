package ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Shell {
    private static List<String> readFile(String filename ) {
        List<String> lines = null;

        try {
            Charset ENCODING = StandardCharsets.UTF_8;
            Path path = Paths.get( filename );
            lines = Files.readAllLines(path, ENCODING);
        }
        catch (IOException ex) {
            System.out.println("Erro no acesso ao ficheiro...");
            System.exit( 0 );
        }
/*
		for (int i = 0; i < lines.size(); i++) {
			System.err.println("Linha n." + i + ": " + lines.get(i));
		}
*/
        return lines;
    }

    public static void main (String [] args) throws IOException {
        if ( args.length != 1 ) {
            System.err.println( "Modo de uso: java ex3.Shell <dir do ficheiro com comandos>" );
            System.exit( 0 );
        }

        List<String> lines = readFile( args[0] );

        for (int i = 0; i < lines.size(); i++) {
            // lines.get(i) contém o comando a executar
            ProcessBuilder pb = new ProcessBuilder( lines.get( i ) );
            Process process = pb.start();	// public Process start() throws IOException
            System.out.println( "Início do comando \"" + lines.get( i ) + "\"." );

            // obter acesso ao output do processo
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader( is );
            BufferedReader br = new BufferedReader( isr );

            // ler o output do processo
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println( line );
            }
            br.close();

            for(int k = 0; k < lines.size(); k++){
                try {
                    process.waitFor();
                    System.out.println( "Fim do comando \"" + lines.get(k) + "\"." );
                } catch ( InterruptedException iex ) {
                    iex.printStackTrace();
                }
            }
        }
    }
}