package generator.processor;

import java.awt.image.BufferedImage;

public class QuadCopyProcessor extends BaseProcessor{
	
	@Override
	public BufferedImage apply(BufferedImage source) {
		int size = source.getHeight();
		BufferedImage output = new BufferedImage(size*2,size*2,
				BufferedImage.TYPE_4BYTE_ABGR);
		for(int x = 0; x<size*2; x++) {
			for(int y = 0; y<size*2; y++) {
					output.setRGB(x,y,source.getRGB(x%size, y%size));
			}
		}
		return output; 
	}
}
