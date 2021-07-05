package mcmeta;

import com.alibaba.fastjson.annotation.JSONField;

public class CTMGlassPaneJSON extends CTMGlassJSON {
	@JSONField(name = "ctm", ordinal = 2)
	  public CTMGlassPaneContent ctm = new CTMGlassPaneContent();
	  class CTMGlassPaneContent extends BaseCTMJSON.CTMContent{
		  @JSONField(name = "layer", ordinal = 3)
		  public String layer = "AAAAA";
	  }
}
