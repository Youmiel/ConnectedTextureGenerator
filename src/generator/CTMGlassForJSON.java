package generator;
import com.alibaba.fastjson.annotation.JSONField;

public class CTMGlassForJSON {

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
		  public String layer = "TRANSLUCENT";
		  
		  @JSONField(name = "textures", ordinal = 4)
		  public String[] textures = new String[1];
	  }
	  
	  public void setTexture(String texturePath) {
		  this.ctm.textures[0] = "minecraft:" + texturePath;
	  }
}
/*
 *     "__comment": "We will have a ctm_version to indicate the version that the json is built for. We will most likely not need it, but who knows? -D ",
    "ctm":{
        "ctm_version": 1,
        "type":"CTM",
        "layer":"TRANSLUCENT",
        "textures":[
            "minecraft:blocks/glass_green_ctm"
        ]
    }
 */
