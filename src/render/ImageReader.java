package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ImageReader {
	public static final int DONOTTHING = 0;
	public static final int OPTIMIZED = 32;
	private static ClassLoader cl = ImageReader.class.getClassLoader();
	
	public static ImageData[] get(String url) {
		return get(url,DONOTTHING);
	}
	
	public static ImageData[] get(String url, int mode) {
		String extension = url.substring(url.length()-3,url.length());
		
		if(extension.equals("gif")) {
			
			ImageData[] frame = null;
			String[] imageatt = new String[]{
		            "imageLeftPosition",
		            "imageTopPosition",
		            "imageWidth",
		            "imageHeight"
		    };
			try {
				javax.imageio.ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
				ImageInputStream stream = ImageIO.createImageInputStream(cl.getResourceAsStream(url));
				reader.setInput(stream);
				
				int count = reader.getNumImages(true);
				int delayTime = 10 ;
				int firstIWidth = 0, firstIHeight = 0;
				frame = new ImageData[count];
				BufferedImage master = null;
				for (int i = 0; i < count; i++) {

					BufferedImage getImage = reader.read(i);
					NodeList child = reader.getImageMetadata(i).getAsTree("javax_imageio_gif_image_1.0").getChildNodes();
	
					HashMap<String,Integer> imageAttr = new HashMap<String,Integer>();
					for (int j = 0; j < child.getLength(); j++) {
						Node nodeItem = child.item(j);
						if (nodeItem.getNodeName().equals("ImageDescriptor")) {
							for(int k=0;k<imageatt.length;k++){
								Node attrnode = nodeItem.getAttributes().getNamedItem(imageatt[k]);
								imageAttr.put(imageatt[k], Integer.parseInt(attrnode.getNodeValue()));
							}
							if((mode & OPTIMIZED) != 0){
								if(i==0){
									master = new BufferedImage(imageAttr.get("imageWidth"), imageAttr.get("imageHeight"), BufferedImage.TYPE_INT_ARGB);
									RenderHelper.addAntiAlising((Graphics2D)master.getGraphics());
								}
								master.getGraphics().drawImage(getImage, imageAttr.get("imageLeftPosition"), imageAttr.get("imageTopPosition"), null);
							}else{
								if(i==0){
									firstIWidth = imageAttr.get("imageWidth");
									firstIHeight = imageAttr.get("imageHeight");
								}
								master = new BufferedImage(firstIWidth, firstIHeight, BufferedImage.TYPE_INT_ARGB);
								RenderHelper.addAntiAlising((Graphics2D)master.getGraphics());
								master.getGraphics().drawImage(getImage, imageAttr.get("imageLeftPosition"), imageAttr.get("imageTopPosition"), null);
							}
							
						}
						if(nodeItem.getNodeName().equalsIgnoreCase("GraphicControlExtension")){
							delayTime = Integer.parseInt(((IIOMetadataNode)nodeItem).getAttribute("delayTime"));
						}
					}
					
					BufferedImage imageExport = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);
					imageExport.getGraphics().drawImage(master, 0, 0, null);
					frame[i] = new ImageData(imageExport);
					frame[i].setDelay(delayTime*10);
					
					if((mode & OPTIMIZED) == 0)
						frame[i].setOffset(imageAttr.get("imageLeftPosition"), imageAttr.get("imageTopPosition"));

				}

	
			} catch (Exception e) {
				throw new RuntimeException("Error : "+ url);
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
					throw new RuntimeException("Error : "+ url);
//					e.printStackTrace();
//					return null;
				}
			}
			
		} 
		
		return null;
	}
}
