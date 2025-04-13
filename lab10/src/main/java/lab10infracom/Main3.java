package lab10infracom;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main3 {
    private final static String ALGORITMO = "AES";
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Escriba el texto que desea cifrar: ");
        String texto = scanner.nextLine();
        System.out.println(texto);

        byte[] textoClaro = texto.getBytes();
        System.out.print("Mensaje de entrada en texto claro: ");
        System.out.println(texto);
        System.out.print("Texto claro: ");
        imprimir(textoClaro);


        KeyGenerator keygen = KeyGenerator.getInstance(ALGORITMO);
        SecretKey secretKey = keygen.generateKey();

        System.out.print("Escriba el nombre del archivo llave: ");
        String nombreArchivo = scanner.nextLine();
        FileOutputStream archivo= new FileOutputStream(nombreArchivo);
        ObjectOutputStream oos= new ObjectOutputStream(archivo);
        oos.writeObject(secretKey);
        oos.close();

        
        // Cifrar texto
        Long tiempoInicialcifrado= System.nanoTime();
        byte[] textoCifrado = Simetrico.cifrar(secretKey, texto);
        System.out.print("Texto cifrado: ");
        Long tiempoFinalcifrado= System.nanoTime();
        imprimir(textoCifrado);
        System.out.println("Tiempo cifrado"+String.valueOf(tiempoFinalcifrado-tiempoInicialcifrado));
        
        System.out.print("Escriba el nombre del archivo texto: ");
        String nombreArchivotexto = scanner.nextLine();
        FileOutputStream archivotexto= new FileOutputStream(nombreArchivotexto);
        ObjectOutputStream oost= new ObjectOutputStream(archivotexto);
        oost.writeObject(textoCifrado);
        oost.close();

        // Descifrar texto
        Long tiempoInicialdecifrado= System.nanoTime();
        byte[] textoDescifrado = Simetrico.descifrar(secretKey, textoCifrado);
        System.out.print("Texto descifrado: ");
        Long tiempoFinaldecifrado= System.nanoTime();
        imprimir(textoDescifrado);
        System.out.println("Tiempo decifrado"+String.valueOf(tiempoFinaldecifrado-tiempoInicialdecifrado));

        String textoFinal = new String(textoDescifrado);
        System.out.println("Texto descifrado: " + textoFinal);
    }

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }
}
