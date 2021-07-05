package generator;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Random;
import java.util.function.Function;

import javax.imageio.ImageIO;

import generator.mask.Mask;
import generator.mask.Masks;
import generator.processor.ClassicProcessor;
import mcmeta.CTMGlassJSON;

public class Generator {
	//BufferedImage edgeMask;
	//BufferedImage centerMask;
	Mask mask = Masks.CLASSIC_EDGE;
	Function<BufferedImage,BufferedImage> processor = new ClassicProcessor();
	BufferedImage source;
	String sourceName;
	CTMGlassJSON json = new CTMGlassJSON();
	
	//protected static final byte SIZE = 16;
	
	protected static final String TEXTURE_PATH = "block/ctm/";
	protected static final String OUTPUT_PATH = "resources/output/";
	
	public Generator() {
		//edgeMask = SourceIO.loadImage("resources/edgeMask.png");
		//centerMask = SourceIO.loadImage("resources/centerMask.png");
		//System.out.println((byte)this.edgeMask.getRGB(0, 0));
		//System.out.println((byte)this.edgeMask.getRGB(16, 16));
		new File(OUTPUT_PATH + "ct/").mkdirs();
		new File(OUTPUT_PATH + "json/").mkdirs();
	}
	
	public void setSource(String imagePath, String sourceName) {
		this.source = SourceIO.loadImage(imagePath);
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
		BufferedImage texture = this.processor.apply(source);
		SourceIO.writeImage(texture, OUTPUT_PATH + "ct/" + this.sourceName + "_ctm.png");
		SourceIO.writeJSON(this.json, OUTPUT_PATH + "json/" + this.sourceName + ".png.mcmeta");
	}

}
