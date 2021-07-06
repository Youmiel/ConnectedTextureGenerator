package mcmeta;
import com.alibaba.fastjson.annotation.JSONField;

public class CTMGlassJSON extends BaseCTMJSON{
	public CTMGlassJSON(){
		super.ctm.layer = "TRANSLUCENT";
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
        "extra":{
        	"ignore_states":true
        	}
    }
 */
