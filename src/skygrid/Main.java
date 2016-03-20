package skygrid;

import cn.nukkit.level.generator.Generator;
import cn.nukkit.plugin.PluginBase;
import skygrid.generator.SkygridGenerator;

public class Main extends PluginBase {
	@Override
	public void onLoad() {
		Generator.addGenerator(SkygridGenerator.class, "skygrid", SkygridGenerator.TYPE_SKYGRID);
	}
}
