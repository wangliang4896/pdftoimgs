package demo;
/**
 * 作者：cassiePython 
 * 来源：CSDN
 * 原文：https://blog.csdn.net/cassiePython/article/details/41155747
 */
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

public class PDFtoJpg {

    public static void main(String[] args) {

    	//select the path here 
    	//you can use the direct filepath or absolute path
    	//you can change it as you like
    	String filePath = "f:/signtest/sus/wls1.pdf";
        Document document = new Document();

        try {
            document.setFile(filePath);
        	} catch (Exception ex) {
        	System.out.println("Set File failed");
        }

        //determine the scale of the image 
        //and the direction of the image 
        
        float scale = 1.3f;
        float rotation = 0f;

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)
            document.getPageImage(i,GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;

            try {
                System.out.println("\t capturing page " + i);//——only for testing,you can delete it
            	//the next two lines determine the saving path 
            	//you can change it via the way you like
                File file = new File("f:/signtest/sus/wl1_" + i + ".jpg");
                ImageIO.write(rendImage, "jpg", file);
            	} catch (IOException e) {
                e.printStackTrace();
            }

            image.flush();
        }
        document.dispose();
    }
}
