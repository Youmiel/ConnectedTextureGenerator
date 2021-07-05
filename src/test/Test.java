package test;
import com.alibaba.fastjson.JSON;

import mcmeta.CTMGlassJSON;
import mcmeta.CTMGlassPaneJSON;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CTMGlassJSON json = new CTMGlassJSON();
		System.out.println(JSON.toJSONString(json));
		
		CTMGlassPaneJSON json2 = new CTMGlassPaneJSON();
		System.out.println(JSON.toJSONString(json2));
		}

}
