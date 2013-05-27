package engine.rendering;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class TextureManager {

	
	
	protected List<Image2D> loadTextureAndParseImages(String imagePath, int singleImageSize, float scaleFactor) {
		
		List<Image2D> resultList = new LinkedList<Image2D>();
		
		//singleImageSize = Double.valueOf(singleImageSize * scaleFactor).intValue();
		
		try {
			Texture imageTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(imagePath));
			//Texture imageTexture = TextureLoader.getTexture("PNG", loadImage(imagePath, scaleFactor));
			Image2D image2D = new Image2D(imageTexture);
			for(int y = 0; y < image2D.getHeight()/singleImageSize; y++) {
				for(int x = 0; x < image2D.getWidth()/singleImageSize; x++) {
					resultList.add(image2D.getSubImage(x*singleImageSize, y*singleImageSize, singleImageSize, singleImageSize, scaleFactor));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultList;
		
	}
	
	private ByteArrayInputStream loadImage(String path, double scaleFactor) {
		BufferedImage image = loadBufferedImage(path);
		image = resizeImage(image, scaleFactor);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", byteArrayOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}
	
	private BufferedImage loadBufferedImage(String path) {
		BufferedImage source = null;

		URL picUrl = getClass().getClassLoader().getResource(path);

		try {
			source = ImageIO.read(picUrl);
		} catch (IOException ex) {

		}
		return source;
	}
	
	private BufferedImage resizeImage(BufferedImage image, double scaleFactor) {
		
		int scaledWidth = Double.valueOf(image.getWidth()*scaleFactor).intValue();
		int scaledHeight = Double.valueOf(image.getHeight()*scaleFactor).intValue();
		
		BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		graphics2D.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}
	


	
	
	
	
	
	
}
