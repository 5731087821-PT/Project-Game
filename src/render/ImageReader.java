package render;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ImageReader {
	private static ClassLoader cl = ImageReader.class.getClassLoader();

	public static ImageData[] get(String url) {
		
		String extension = url.substring(url.length()-3,url.length());
		
		if(extension.equals("gif")) {
			
			ImageData[] frame = null;
			try {
				javax.imageio.ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
				ImageInputStream stream = ImageIO.createImageInputStream(cl.getResourceAsStream(url));
				reader.setInput(stream);
				int count = reader.getNumImages(true);
				frame = new ImageData[count];
	
				for (int i = 0; i < count; i++) {
					BufferedImage image = reader.read(i);
					frame[i] = new ImageData(image);
					NodeList child = reader.getImageMetadata(i).getAsTree("javax_imageio_gif_image_1.0").getChildNodes();
	
					for (int j = 0; j < child.getLength(); j++) {
						Node nodeItem = child.item(j);
						if (nodeItem.getNodeName().equals("ImageDescriptor")) {
							int offsetX = Integer
									.valueOf(nodeItem.getAttributes().getNamedItem("imageLeftPosition").getNodeValue());
							int offsetY = Integer
									.valueOf(nodeItem.getAttributes().getNamedItem("imageTopPosition").getNodeValue());
							frame[i].setOffset(offsetX, offsetY);
							break;
						}
					}
					
					for (int j = 0; j < child.getLength(); j++) {
						Node nodeItem = child.item(j);
						if(nodeItem.getNodeName().equalsIgnoreCase("GraphicControlExtension")){
							int delayTime = Integer
									.parseInt(((IIOMetadataNode)nodeItem).getAttribute("delayTime"));
							if(delayTime == 0) 
								((IIOMetadataNode)nodeItem).setAttribute("delayTime", "10");
							frame[i].setDelay(delayTime*10);
							break;
						}
					}
	
				}

	
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			return frame;

		} else if(extension.equals("png") || extension.equals("jpg")) {
			
			int frameCounterIndex = url.lastIndexOf(".",url.lastIndexOf(".")-1);

			if(frameCounterIndex>-1){
				int frameCount = Integer.parseInt(url.substring(frameCounterIndex+1,url.length()-4));
				ImageData[] frame = null;
				try {
					int frameWidth;
					int frameHeight;
					BufferedImage image;
					image = ImageIO.read(cl.getResource(url));
					
					frameWidth = image.getWidth()/frameCount;
					frameHeight = image.getHeight();
					frame = new ImageData[frameCount];
					
					for(int i=0;i<frameCount;i++){
						frame[i] = new ImageData(image.getSubimage(i*frameWidth, 0, frameWidth, frameHeight));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return frame;
			}else{
				ImageData[] image = new ImageData[1];
				
				try {
					image[0] = new ImageData(ImageIO.read(cl.getResource(url)));
					return image;
				} catch(IOException e) {
					e.printStackTrace();
					return null;
				}
			}
			
		} 
		
		return null;
	}

}
