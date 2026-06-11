package ejercicio_3;



import java.util.HashMap;
import java.util.Map;

public class ImageProxy implements Image {

    private String path;
    private static Map<String, ImageFile> cache = new HashMap<>();

    public ImageProxy(String path) {
        this.path = path;
    }

    @Override
    public void display() {

        ImageFile image = cache.get(path);

        if (image == null) {
            image = new ImageFile(path);
            cache.put(path, image);
        }

        image.display();
    }
}