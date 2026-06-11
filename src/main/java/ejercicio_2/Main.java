package ejercicio_2;



import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        var admin = new Usuario("Pepe", List.of(Permiso.ADMIN));

        var basico = new Usuario("Argento", List.of(Permiso.BASICO));

        var intermedio = new  Usuario("Pablo", List.of(Permiso.INTERMEDIO));

        Archivo archivo1 = new FileAccessProxy(admin,  "src/main/resources", "importante.txt");

        Archivo archivo2 = new FileAccessProxy(basico,  "src/main/resources", "importante.txt");

        Archivo archivo3 = new FileAccessProxy(intermedio,  "src/main/resources", "importante.txt");

        Archivo archivo4 = new FileAccessProxy(intermedio,  "src/main/resources", "muestras.txt");

        try {
            System.out.println(archivo1.readFile());
        } catch (RuntimeException | IOException e) {

            System.out.println(e.getMessage());
        }

        try {
            System.out.println(archivo2.readFile());
        } catch (RuntimeException | IOException e) {

            System.out.println(e.getMessage());
        }

        try {
            System.out.println(archivo3.readFile());
        } catch (RuntimeException | IOException e) {

            System.out.println(e.getMessage());
        }

        try {
            System.out.println(archivo4.readFile());
        } catch (RuntimeException | IOException e) {

            System.out.println(e.getMessage());
        }


    }


}