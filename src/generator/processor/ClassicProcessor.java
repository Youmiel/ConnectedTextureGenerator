package generator.processor;

import java.awt.image.BufferedImage;
import generator.mask.Mask;
import generator.mask.Masks;

public class ClassicProcessor extends BaseProcessor{
	Mask mask = Masks.CLASSIC_EDGE;
	
	@Override
	public BufferedImage apply(BufferedImage source) {
		int size = source.getHeight();
		BufferedImage output = new BufferedImage(size*2,size*2,
				BufferedImage.TYPE_4BYTE_ABGR);
		int backgroundRGB = source.getRGB(size/2, size/2);
		for(int x = 0; x<size*2; x++) {
			for(int y = 0; y<size*2; y++) {
				int sourcePix = source.getRGB(x%size, y%size);
				if(this.mask.isMask((x+0.5)/(size*2),(y+0.5)/(size*2))) {
					output.setRGB(x,y,sourcePix);
				}else {
					output.setRGB(x, y, backgroundRGB);
				}
			}
		}
		return output;
	}
}
