package lab10infracom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Scanner;

public class Main {

    private final static String ALGORITMOA= "RSA";
    private final static String ALGORITMO = "AES";
    

    public static void main(String[] args) throws Exception {
        
        
    }

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }

    public static void mainSimetrico() throws NoSuchAlgorithmException{

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

        // Cifrar texto
        Long tiempoInicialcifrado= System.nanoTime();
        byte[] textoCifrado = Simetrico.cifrar(secretKey, texto);
        System.out.print("Texto cifrado: ");
        Long tiempoFinalcifrado= System.nanoTime();
        imprimir(textoCifrado);
        System.out.println("Tiempo cifrado"+String.valueOf(tiempoFinalcifrado-tiempoInicialcifrado));
        

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

    public static void mainAsimetrico() throws NoSuchAlgorithmException{

        Scanner scanner = new Scanner(System.in);
        System.out.print("Escriba un mensaje de texto: ");
        String texto = scanner.nextLine();
        System.out.println("Input en texto plano: " + texto);

        
        byte[] textoClaro = texto.getBytes();
        System.out.print("Input en bytes: ");
        imprimir(textoClaro);

        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMOA);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PrivateKey llavePrivada = keyPair.getPrivate();

        byte[] textoCifrado = Asimetrico.cifrar(llavePrivada, ALGORITMOA, texto);
        System.out.print("Input cifrado en RSA con llave pública (en bytes): ");
        imprimir(textoCifrado);

        byte[] textoDescifrado = Asimetrico.descifrar(llavePrivada, ALGORITMOA, textoCifrado);
        System.out.print("Texto descifrado en bytes: ");
        imprimir(textoDescifrado);
        String textoFinal = new String(textoDescifrado);
        System.out.println("Texto descifrado final: " + textoFinal);

    }
}
