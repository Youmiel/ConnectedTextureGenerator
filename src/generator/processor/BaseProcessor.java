package generator.processor;

import java.awt.image.BufferedImage;
import java.util.function.Function;

public class BaseProcessor implements Function<BufferedImage,BufferedImage>{

	@Override
	public BufferedImage apply(BufferedImage source) {
		return source;
	}

}
