package lab10infracom;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main2 {

    private final static String ALGORITMO = "AES";
    private final static String ALGORITMOA= "RSA";
    

    public static void main(String[] args) throws Exception {
        
    }

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }

    public static void mainSimetrico() throws NoSuchAlgorithmException {

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
        SecretKey k1 = keygen.generateKey();
        SecretKey k2 = keygen.generateKey();

        // Cifrar texto
        Long tiempoInicialcifrado= System.nanoTime();
        byte[] tc1 = Simetrico.cifrar(k1, texto);
        System.out.print("Texto cifrado: ");
        Long tiempoFinalcifrado= System.nanoTime();
        imprimir(tc1);
        System.out.println("Tiempo cifrado"+String.valueOf(tiempoFinalcifrado-tiempoInicialcifrado));
        
        Long tiempoInicialcifrado2= System.nanoTime();
        byte[] tc2 = Simetrico.cifrar(k2, texto);
        System.out.print("Texto cifrado: ");
        Long tiempoFinalcifrado2= System.nanoTime();
        imprimir(tc2);
        System.out.println("Tiempo cifrado"+String.valueOf(tiempoFinalcifrado2-tiempoInicialcifrado2));

        // Descifrar texto
        Long tiempoInicialdecifrado= System.nanoTime();
        byte[] textoDescifrado = Simetrico.descifrar(k1, tc1);
        System.out.print("Texto descifrado: ");
        Long tiempoFinaldecifrado= System.nanoTime();
        imprimir(textoDescifrado);
        System.out.println("Tiempo decifrado"+String.valueOf(tiempoFinaldecifrado-tiempoInicialdecifrado));

        String textoFinal = new String(textoDescifrado);
        System.out.println("Texto descifrado: " + textoFinal);
        
    }

    public static void mainAsimetrico() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escriba un mensaje de texto: ");
        String texto = scanner.nextLine();
        System.out.println("Texto plano: " + texto);

        byte[] textoClaro = texto.getBytes();
        System.out.print("Texto en bytes: ");
        imprimir(textoClaro);

        KeyPairGenerator generador = KeyPairGenerator.getInstance(ALGORITMOA);
        generador.initialize(1024);
        KeyPair keyPair = generador.generateKeyPair();
        PrivateKey llavePrivada = keyPair.getPrivate();

        byte[] textoCifrado = Asimetrico.cifrar(llavePrivada, ALGORITMOA, texto);
        System.out.print("Texto cifrado en bytes: ");
        imprimir(textoCifrado);

        byte[] textoDescifrado = Asimetrico.descifrar(llavePrivada, ALGORITMOA, textoCifrado);
        System.out.print("Texto descifrado en bytes: ");
        imprimir(textoDescifrado);

        String textoFinal = new String(textoDescifrado);
        System.out.println("Texto descifrado final: " + textoFinal);


        
    }

    
}
