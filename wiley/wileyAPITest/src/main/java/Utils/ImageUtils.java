package Utils;

import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {


    public void checkImageWidth(InputStream stream, int expectedWidth) {
        BufferedImage bImg;

        try {
            bImg = ImageIO.read(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int imageWidth = bImg.getWidth();
        Assert.assertEquals(imageWidth, expectedWidth, String.format("Ширина изображения не соответсвует ожидаемому;\n" +
                                                       "Ожидаемая ширина - %s, актульная - %s", expectedWidth, imageWidth));

    }
}
