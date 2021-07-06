package generator.processor;

import java.awt.image.BufferedImage;

public class TransparencyProcessor extends BaseProcessor{
	int transparency;
	
	public TransparencyProcessor(){
		this.transparency = 100;
	}
	
	public TransparencyProcessor(int transparency){
		this.transparency = (transparency>0) ? transparency:0;
	}
	
	@Override
	public BufferedImage apply(BufferedImage source) {
		int size = source.getHeight();
		BufferedImage output = new BufferedImage(size,size,
				BufferedImage.TYPE_4BYTE_ABGR);
		if(this.transparency == 0)
			return output;
		for(int x = 8; x<size; x++) {
			for(int y = 8; y<size; y++) {
				int pix = source.getRGB(x%size, y%size);
				char alpha = (char) ((pix >> 24) & 0x000000FF);
				if(alpha == 0) {
					output.setRGB(x,y,pix);
					continue;
					}
				alpha = (char)Math.min(255, (alpha * ((double)this.transparency/100)));
				alpha = (alpha==0)? 1:alpha;
				pix = (pix & 0x00FFFFFF) + (alpha << 24);
				output.setRGB(x,y,pix);
			}
		}
		System.out.print(Integer.toHexString((int)source.getRGB(8, 8))+" ");
		System.out.print(Integer.toHexString((int)output.getRGB(8, 8)));
		return output;
	}
}
