package lab10infracom;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main4 {
        

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }

    public static void main4Simetrico() throws IOException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Escriba el nombre del archivo llave: ");
        String nombreArchivo = scanner.nextLine();
        InputStream archivo= new FileInputStream(nombreArchivo);
        ObjectInputStream ois= new ObjectInputStream(archivo);
        byte[] llave =(byte[]) ois.readObject();
        ois.close();
        System.out.print("Escriba el nombre del archivo texto cifrado: ");
        String nombreArchivotexto = scanner.nextLine();
        InputStream archivotexto= new FileInputStream(nombreArchivotexto);
        ObjectInputStream ois2= new ObjectInputStream(archivotexto);
        byte[] textoCifrado =(byte[]) ois2.readObject();
        ois2.close();

        imprimir(llave);
        imprimir(textoCifrado);
       


        
       }

    public static void main4Asimetrico   () throws IOException, ClassNotFoundException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escriba el nombre del archivo llave privada: ");
        String nombreArchivopriv = scanner.nextLine();
        InputStream archivopriv= new FileInputStream(nombreArchivopriv);
        ObjectInputStream oispriv= new ObjectInputStream(archivopriv);
        byte[] llavePrivada =(byte[]) oispriv.readObject();
        oispriv.close();

        imprimir(llavePrivada);
       
        System.out.print("Escriba el nombre del archivo llave publica: ");
        String nombreArchivopubli = scanner.nextLine();
        InputStream archivopubli= new FileInputStream(nombreArchivopubli);
        ObjectInputStream oispubli= new ObjectInputStream(archivopubli);
        byte[] llavePublica =(byte[]) oispubli.readObject();
        oispubli.close();

        imprimir(llavePublica);

        System.out.print("Escriba el nombre del archivo texto cifrado: ");
        String nombreArchivotexto = scanner.nextLine();
        InputStream archivotexto= new FileInputStream(nombreArchivotexto);
        ObjectInputStream ois2= new ObjectInputStream(archivotexto);
        byte[] textoCifrado =(byte[]) ois2.readObject();
        ois2.close();

        imprimir(textoCifrado);
    } 
        
    }
    

