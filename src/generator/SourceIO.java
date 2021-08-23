package generator;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSON;

import mcmeta.TextureJSON;

public class SourceIO {
	public static BufferedImage loadImage(String filePath) {
        File file = new File(filePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
	}
	
	public static void writeImage(BufferedImage image, String filePath){
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
	public static void writeJSON(TextureJSON object, String filePath) {
		try {
			BufferedWriter outFile = new BufferedWriter(new FileWriter(filePath,false));
			outFile.write(JSON.toJSONString(object));
			outFile.flush();
			outFile.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
