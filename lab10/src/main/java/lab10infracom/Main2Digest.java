package lab10infracom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main2Digest {
    public static byte[] getDigestFile(String algorithm, String fileName){
        MessageDigest md=null;
        try {
            md= MessageDigest.getInstance(algorithm);
            FileInputStream in = new FileInputStream(fileName);
            byte[]buffer= new byte[1024];

            int lenght;
            while ((lenght = in.read(buffer)) != -1) {
                md.update(buffer,0,lenght);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return md.digest();
    }

    
}
