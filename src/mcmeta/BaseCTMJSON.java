package mcmeta;

import com.alibaba.fastjson.annotation.JSONField;

import mcmeta.CTMGlassJSON.CTMContent;
import mcmeta.CTMGlassJSON.CTMContent.ExtraField;

public class BaseCTMJSON {

	  @JSONField(name = "__comment", ordinal = 1)
	  public String comment = "Generated from CTMGlassGenerator by Youmiel";
	  
	  @JSONField(name = "ctm", ordinal = 2)
	  public CTMContent ctm = new CTMContent();
	  class CTMContent{
		  @JSONField(name = "ctm_version", ordinal = 1)
		  public int version = 1;
		  
		  @JSONField(name = "type", ordinal = 2)
		  public String type = "CTM";
		  
		  @JSONField(name = "layer", ordinal = 3)
		  public String layer = "CUT_OUT";
		  
		  @JSONField(name = "textures", ordinal = 4)
		  public String[] textures = new String[1];
		  
		  @JSONField(name = "extra", ordinal = 5)
		  public ExtraField extra = new ExtraField();
		  class ExtraField{
			  @JSONField(name = "ignore_states", ordinal = 1)
			  public boolean igore_states = true;
		  }
	  }
	  
	  public void setTexture(String texturePath) {
		  this.ctm.textures[0] = "minecraft:" + texturePath;
	  }
}
