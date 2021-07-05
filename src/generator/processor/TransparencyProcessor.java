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
		for(int x = 0; x<size; x++) {
			for(int y = 0; y<size; y++) {
				int pix = source.getRGB(x%size, y%size);
				byte alpha = (byte) (pix >> 24);
				alpha = (byte)Math.min(255, (alpha * ((double)this.transparency/100)));
				alpha = (alpha==0)? 1:alpha;
				pix = (pix & 0x00FFFFFF) + (alpha << 24);
				output.setRGB(x,y,pix);
			}
		}
		System.out.print(Integer.toHexString((int)output.getRGB(1, 1)));
		return output;
	}
}
