package ejercicio_2;



import java.io.IOException;

public class FileAccessProxy implements Archivo {

    private Usuario usuario;
    private FileAccess real;

    public FileAccessProxy(
            Usuario usuario,
            String ruta,
            String nombreArchivo) {

        this.usuario = usuario;
        this.real = new FileAccess(ruta, nombreArchivo);
    }

    @Override
    public String readFile() throws IOException {

        String nombre = real.nombre();

        if(nombre.startsWith("i")) {

            if(!usuario.poseePermiso(Permiso.ADMIN)) {
                throw new RuntimeException("Acceso denegado. Se requiere permiso ADMIN");
            }
        }

        if(nombre.startsWith("m")) {

            boolean autorizado = usuario.poseePermiso(Permiso.ADMIN) || usuario.poseePermiso(Permiso.INTERMEDIO);

            if(!autorizado) {
                throw new RuntimeException("Acceso denegado. Se requiere permiso ADMIN o INTERMEDIO");
            }
        }

        return real.readFile();
    }
}