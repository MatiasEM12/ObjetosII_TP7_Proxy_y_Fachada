package ejercicio_3;


public class ImageGalery {

    public static void main(String[] args) {

        Image image1 = new ImageProxy("src/main/resources/image1.jpeg");
        image1.display();

        Image image2 = new ImageProxy("src/main/resources/image1.jpeg");
        image2.display();
    }

}