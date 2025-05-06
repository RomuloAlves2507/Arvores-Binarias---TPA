import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivos {
    private static final String NOME_ARQUIVO = "C:\\Users\\USER\\OneDrive\\Documentos\\trabalho\\Arvores-Binarias---TPA\\src\\livros.txt";
    
    public static void main(String[] args) {
        System.out.println("NOME_ARQUIVO");
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            int numRegistros = Integer.parseInt(reader.readLine().trim());
            System.out.println("Número de registros: " + numRegistros);
            
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String autor = partes[2];
                String genero = partes[3];
                
                System.out.printf("ID: %d | Título: %s | Autor: %s | Gênero: %s\n", 
                id, nome, autor, genero);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro ao processar um dos valores numéricos: " + e.getMessage());
        }
    }
}
