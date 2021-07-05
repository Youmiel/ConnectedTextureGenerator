package generator;

import java.io.File;
import java.io.FilenameFilter;

import generator.processor.QuadCopyProcessor;
import generator.processor.TransparencyProcessor;
import mcmeta.CTMGlassJSON;

public class Main {
	protected static final String INPUT_PATH = "resources/input/";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File[] fileList = new File(INPUT_PATH).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir,String name) {
				return name.toLowerCase().endsWith(".png");
			}
		});
		Generator gen = (new Generator())
				.setJSONObject(new CTMGlassJSON())
				.setProcessor(
						(new QuadCopyProcessor()).andThen(new TransparencyProcessor(5))
						);
		for(int i = 0; i<fileList.length; i++) {
			String fileName = fileList[i].getName();
			String name = fileName.substring(0,fileName.indexOf('.'));
			gen.setSource(fileList[i].getPath(),name);
			gen.generateTexture();
			System.out.println("[" + (i+1)+ "] " + name + " completed.");
		}
	}
}
