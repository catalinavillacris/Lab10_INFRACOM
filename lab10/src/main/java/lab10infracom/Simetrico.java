package lab10infracom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Simetrico {

    private final static String padding="AES/ECB/PKCS5Padding";

    public static byte[] cifrar(SecretKey llave, String texto){
        try{
            Cipher cifrador= Cipher.getInstance(padding);
            byte[] textoClaro=texto.getBytes();
            
            cifrador.init(Cipher.ENCRYPT_MODE,llave);
            byte[] textoCifrado=cifrador.doFinal(textoClaro);

            return textoCifrado;
        }catch(Exception e){
            System.out.println("Error al cifrar: "+e.getMessage());
            return null;
        }
    };

    public static byte[] descifrar(SecretKey llave,byte[] texto){
        byte[] textoClaro = null;
        try {
            Cipher cifrador=Cipher.getInstance(padding);
            cifrador.init(Cipher.DECRYPT_MODE, llave);
            textoClaro= cifrador.doFinal(texto);
        } catch (Exception e) {
            System.out.println("Error al decifrar: "+e.getMessage());
            return null;
        }
        return textoClaro;
    };
    
}
