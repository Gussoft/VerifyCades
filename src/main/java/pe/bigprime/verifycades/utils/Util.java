package pe.bigprime.verifycades.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Util {

    public static File getPublicCert(){
        try {
            String certificadoPublico = System.getenv("JAVA_KEY");
            URL url = new URL(certificadoPublico);
            URLConnection urlCon = url.openConnection();
            System.out.println(urlCon.getContentType());
            InputStream is = urlCon.getInputStream();
            File tempFile = File.createTempFile("truststore", ".jks");
            FileOutputStream fos = new FileOutputStream(tempFile.getAbsolutePath());
            byte[] array = new byte[1000]; // buffer temporal de lectura.
            int leido = is.read(array);
            while (leido > 0) {
                fos.write(array, 0, leido);
                leido = is.read(array);
            }
            is.close();
            fos.close();
            return tempFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static File getFileUrl(String Uri){
        try {
            URL url = new URL(Uri);
            String ext = Util.getExtension(Uri);
            URLConnection urlCon = url.openConnection();
            System.out.println(urlCon.getContentType());
            InputStream is = urlCon.getInputStream();
            File tempFile = File.createTempFile("temp", ext);
            FileOutputStream fos = new FileOutputStream(tempFile.getAbsolutePath());
            byte[] array = new byte[1000]; // buffer temporal de lectura.
            int leido = is.read(array);
            while (leido > 0) {
                fos.write(array, 0, leido);
                leido = is.read(array);
            }
            is.close();
            fos.close();
            return tempFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getExtension(String file){
        String ext = "";
        ext = file.replaceAll("^.*\\.(.*)$", "$1");
        return "." + ext;
    }
}
