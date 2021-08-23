package generator;

import java.io.File;
import java.io.FilenameFilter;

import generator.processor.BaseProcessor;
import generator.processor.QuadCopyProcessor;
import generator.processor.TransparencyProcessor;
import mcmeta.CTMGlassJSON;
import mcmeta.CTMGlassPaneJSON;
import mcmeta.GlassModel;

public class Main {
	protected static final String INPUT_PATH = "resources/input/";

	public static void main(String[] args) {
		File[] fileList = new File(INPUT_PATH).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir,String name) {
				return name.toLowerCase().endsWith(".png");
			}
		});
		Generator gen = null;
		if (args.length == 0) {
			System.out.println("Not enough args");
			return;
		}else{
			switch(args[0]){
				case "glass_gen":
					gen = (new Generator())
					.setJSONObject(new CTMGlassJSON())
					.setProcessor(
							(new QuadCopyProcessor()).andThen(new TransparencyProcessor(50))
							);
					break;
				case "glass_pane_gen":
					gen = (new Generator())
					.setJSONObject(new CTMGlassPaneJSON())
					.setProcessor(
						(new QuadCopyProcessor()).andThen(new TransparencyProcessor(50))
						);
					break;
				case "glass_model":
					gen = (new Generator())
					.setJSONObject(new GlassModel())
					.setProcessor(
						new BaseProcessor()
					);
					break;
				default:
					System.out.println("Unknown arguments.");
					return;
			}

		}

		for(int i = 0; i<fileList.length; i++) {
			String fileName = fileList[i].getName();
			String name = fileName.substring(0,fileName.indexOf('.'));
			gen.setSource(fileList[i].getPath(),name);
			gen.generateTexture();
			System.out.println("[" + (i+1)+ "] " + name + " completed.");
		}
	}
}
