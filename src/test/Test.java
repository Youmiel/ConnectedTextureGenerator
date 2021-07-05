package test;
import com.alibaba.fastjson.JSON;

import generator.mask.Mask;
import generator.mask.Masks;
import mcmeta.BaseCTMJSON;
import mcmeta.CTMGlassJSON;
import mcmeta.CTMGlassPaneJSON;
import sun.tools.tree.BitAndExpression;

public class Test {

	public static void testJSON() {
		BaseCTMJSON json = new BaseCTMJSON();
		System.out.println(JSON.toJSONString(json));
		
		CTMGlassJSON json2 = new CTMGlassJSON();
		System.out.println(JSON.toJSONString(json2));
		
		CTMGlassPaneJSON json3 = new CTMGlassPaneJSON();
		System.out.println(JSON.toJSONString(json3));
		
		Object json4 = (Object)new CTMGlassPaneJSON();
		System.out.println(JSON.toJSONString(json4));
	}
	
	public static void testMask() {
		Mask mask = Masks.CLASSIC_EDGE;
		double size = 64.0;
		for(int x=0;x<size;x++) {
			for(int y=0;y<size;y++) {
				System.out.print(mask.isMask((x+0.5)/size, (y+0.5)/size) ? '1' : '0');
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte b = (byte)255;
		System.out.print(Integer.toBinaryString((int)b));
	}
}
