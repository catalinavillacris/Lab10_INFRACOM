package lab10infracom;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main4 {

    private final static String ALGORITMO = "AES";
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escriba el nombre del archivo llave: ");
        String nombreArchivo = scanner.nextLine();
        InputStream archivo= new FileInputStream(nombreArchivo);
        ObjectInputStream ois= new ObjectInputStream(archivo);
        byte[] textoCifrado =(byte[]) ois.readObject();
        ois.close();

        imprimir(textoCifrado);
       

        ObjectInputStream oist= new ObjectInputStream(archivo);
        SecretKey llave=(SecretKey)ois.readObject();
        oist.close();
        
       }

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }
    
}
