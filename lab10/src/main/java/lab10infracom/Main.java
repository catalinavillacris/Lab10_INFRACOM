package lab10infracom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Main {

    public final static String ALGORITMOA= "RSA";
    private final static String ALGORITMO = "AES";
    

    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción de cifrado:");
            System.out.println("1. Cifrado Simétrico (AES)");
            System.out.println("2. Cifrado Asimétrico (RSA)");
            System.out.println("3.Control de Integridad");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea pendiente

            switch (option) {
                case 1:
                    System.out.println("seleccione una opcion del menu de mains");
                    System.out.print("1.");
                    System.out.print("2.");
                    System.out.print("3.");
                    System.out.print("4.");
                    int main = scanner.nextInt();
                    scanner.nextLine();
                    switch (main) {
                        case 1:
                            Main.mainSimetrico();
                            break;
                        case 2:
                            Main2.mainSimetrico();
                        case 3:
                            Main3.main3Simetrico();
                        case 4:
                            Main4.main4Simetrico();
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                    break;
                case 2:
                    System.out.println("seleccione una opcion del menu de mains");
                    System.out.print("1.");
                    System.out.print("2.");
                    System.out.print("3.");
                    System.out.print("4.");
                    int mainAsimetrico = scanner.nextInt();
                    scanner.nextLine();
                    switch (mainAsimetrico) {
                        case 1:
                            Main.mainAsimetrico();
                            break;
                        case 2:
                            Main2.mainAsimetrico();
                        case 3:
                            Main3.main3Asimetrico();
                        case 4:
                            Main4.main4Asimetrico();
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");   
                    }
                    break;
                case 3:
                    System.out.println("seleccione una opcion: ");
                    System.out.print("1. Inresar texto");
                    System.out.print("2. Ingresar archivo");
                    int integridad = scanner.nextInt();
                    scanner.nextLine();
                    switch (integridad) {
                        case 1:
                            System.out.println("Ingrese el texto: "); 
                            String texto=scanner.nextLine();
                            System.out.println("El texto ingresado es:  "+ texto);
        
                            byte[] textoClaro= texto.getBytes();
                            byte[] textoMD5= Digest.getDigest("MD5", textoClaro);
                            Digest.imprimirHexa(textoMD5);
                            byte[] textoSHA= Digest.getDigest("SHA-1", textoClaro);
                            Digest.imprimirHexa(textoSHA);
                        case 2:
                            System.out.println("Ingrese el nombre del archivo: ");
                            String archivo=scanner.nextLine();
                            try {
                                byte[] digestMD5=  Main2Digest.getDigestFile("MD5", archivo);
                                Digest.imprimirHexa(digestMD5);

                                byte[] digestSHA=  Main2Digest.getDigestFile("SHA-1", archivo);
                                Digest.imprimirHexa(digestSHA);
                                
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
    
                    }
                    break;



                case 4:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }

    public static boolean verificar(byte[] array1, byte[] array2) {
        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }

        return true; // Si no se encuentra ninguna diferencia, son idénticos
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
        PublicKey llavePublica = keyPair.getPublic();

        byte[] textoCifrado = Asimetrico.cifrar(llavePublica, ALGORITMOA, texto);
        System.out.print("Input cifrado en RSA con llave pública (en bytes): ");
        imprimir(textoCifrado);

        byte[] textoDescifrado= Asimetrico.descifrar(llavePrivada, texto, textoCifrado);
        imprimir(textoDescifrado);
        System.out.println("Texto descifrado: " + new String(textoDescifrado));
    }
}
