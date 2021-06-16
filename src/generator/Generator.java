package generator;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

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
		edgeMask = SourceIO.loadImage("resources/edgeMask.png");
		centerMask = SourceIO.loadImage("resources/centerMask.png");
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
		SourceIO.writeImage(texture, OUTPUT_PATH + "ct/" + this.sourceName + "_ctm.png");
		SourceIO.writeJSON(this.json, OUTPUT_PATH + "json/" + this.sourceName + ".png.mcmeta");
	}

}
