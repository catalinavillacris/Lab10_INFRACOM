package lab10infracom;

import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class Asimetrico {

    public static byte[] cifrar (Key llave, String algoritmo, String texto){
        byte[] textoCifrado;
        try {
            Cipher cifrador=Cipher.getInstance(algoritmo);
            byte[] textoClaro= texto.getBytes();

            cifrador.init(Cipher.ENCRYPT_MODE, llave);
            textoCifrado=cifrador.doFinal(textoClaro);
            return textoCifrado;
        } catch (Exception e) {
            System.out.println("Error al cifrar: "+e.getMessage());
            return null;
        }
    }

    public static byte[] descifrar (Key llave, String algoritmo, byte[] texto){
        byte[] textoClaro;
        try {
            Cipher cifrador=Cipher.getInstance(algoritmo);
            cifrador.init(Cipher.DECRYPT_MODE, llave);
            textoClaro= cifrador.doFinal(texto);

        } catch (Exception e) {
            System.out.println("Error al descifrar: "+e.getMessage());
            return null;
        }

        return textoClaro;
    }
    
}
