package generator;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSON;

public class Generator {
	BufferedImage edgeMask;
	BufferedImage centerMask;
	BufferedImage source;
	String sourceName;
	CTMGlassForJSON json = new CTMGlassForJSON();
	
	//protected static final byte SIZE = 16;
	
	protected static final String TEXTURE_PATH = "block/ctm/";
	protected static final String INPUT_PATH = "resources/input/";
	protected static final String OUTPUT_PATH = "resources/output/";
	
	public Generator() {
		edgeMask = this.loadImage("resources/edgeMask.png");
		centerMask = this.loadImage("resources/centerMask.png");
		//System.out.println((byte)this.edgeMask.getRGB(0, 0));
		//System.out.println((byte)this.edgeMask.getRGB(16, 16));
		new File(OUTPUT_PATH + "ct/").mkdirs();
		new File(OUTPUT_PATH + "json/").mkdirs();
	}
	
	public void setSource(String imagePath, String sourceName) {
		this.source = this.loadImage(imagePath);
		this.sourceName = sourceName;
		this.json.setTexture(TEXTURE_PATH + sourceName + "_ctm");
	}
	
	public void generateTexture() {//only for glass
		if(this.source == null || this.source.getWidth() == 0 || this.source.getHeight() == 0) {
			System.out.println("source is NULL");
			return;
		}
		//offset
		if(this.source.getHeight() != this.source.getWidth()) {
			System.out.println("[" + this.sourceName + "] do not have a equal height and width.");
			return;
		}
		int size = this.source.getHeight();
		BufferedImage texture = new BufferedImage(size*2,size*2,
				BufferedImage.TYPE_4BYTE_ABGR);
		int backgroundRGB = this.source.getRGB(size/2, size/2);
		for(int x = 0; x<size*2; x++) {
			for(int y = 0; y<size*2; y++) {
				int sourcePix = this.source.getRGB(x%size, y%size);
				byte edgeMaskPix = (byte)this.edgeMask.getRGB(x, y);
				byte centerMaskPix = (byte)this.centerMask.getRGB(x, y);
				if(edgeMaskPix == 0 && centerMaskPix == 0) {
					texture.setRGB(x, y, backgroundRGB);
				}else {
					texture.setRGB(x,y,sourcePix);
				}
			}
		}
		this.writeImage(texture, OUTPUT_PATH + "ct/" + this.sourceName + "_ctm.png");
		this.writeJSON(this.json, OUTPUT_PATH + "json/" + this.sourceName + ".png.mcmeta");
	}
	
	public BufferedImage loadImage(String filePath) {
        File file = new File(filePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
	}
	
	public void writeImage(BufferedImage image, String filePath){
        String postfix = filePath.substring(filePath.indexOf('.')+1);
        try {
        	File imgFile = new File(filePath);
        	if (!imgFile.exists()) {
        		imgFile.createNewFile();
        		}
            ImageIO.write(image,postfix,imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void writeJSON(CTMGlassForJSON object, String filePath) {
		try {
			BufferedWriter outFile = new BufferedWriter(new FileWriter(filePath,false));
			outFile.write(JSON.toJSONString(object));
			outFile.flush();
			outFile.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File[] fileList = new File(INPUT_PATH).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir,String name) {
				return name.toLowerCase().endsWith(".png");
			}
		});
		Generator gen = new Generator();
		for(int i = 0; i<fileList.length; i++) {
			String fileName = fileList[i].getName();
			String name = fileName.substring(0,fileName.indexOf('.'));
			gen.setSource(fileList[i].getPath(),name);
			gen.generateTexture();
			System.out.println("[" + (i+1)+ "] " + name + " completed.");
		}
	}

}
