package mcmeta;
import com.alibaba.fastjson.annotation.JSONField;

public class GlassModel implements TextureJSON{
    @JSONField(name = "parent", ordinal = 1)
    public String parent = "minecraft:block/cube_all";

    class TextureJSON{
        @JSONField(name = "all", ordinal = 1)
        public String all = "";

        public void setTexture(String texturePath) {
            this.all = "minecraft:" + texturePath;
        }
    }
    @JSONField(name = "textures", ordinal = 2)
    public TextureJSON textures = new TextureJSON();
    
    public void setTexture(String texturePath) {
        String path = texturePath.substring(texturePath.lastIndexOf('/') + 1, texturePath.lastIndexOf("_ctm"));
        path = "block/glass/" + path;
        this.textures.setTexture(path);
    }
}
/*
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "minecraft:block/black_stained_glass"
  }
}
 */