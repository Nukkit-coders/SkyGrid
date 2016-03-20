package skygrid.generator;

import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.format.generic.BaseFullChunk;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;

public class SkygridGenerator extends Generator {
	
	public static final int TYPE_SKYGRID = 3;
	
	public final int[] blocklist = {2, 12, 13, 14, 15, 16, 21, 24, 56, 73, 79, 80, 82, 86, 99, 100, 103, 110, 129, 159, 162};
	
	private ChunkManager level;
	private NukkitRandom random;
	
	private Map<String, Object> options;
	
	private Random rand = new Random(Calendar.getInstance().getTimeInMillis());
	
	public SkygridGenerator(Map<String, Object> map) {
		options = map;
	}
	
	@Override
	public int getId() {
		return TYPE_SKYGRID;
	}

	@Override
	public void init(ChunkManager level, NukkitRandom random) {
		this.level = level;
		this.random = random;
	}

	@Override
	public void generateChunk(int chunkX, int chunkZ) {
		BaseFullChunk chunk = this.level.getChunk(chunkX, chunkZ);
		for (int x = 0; x < 16; x += 4) {
			for (int z = 0; z < 16; z += 4) {
				for (int y = 0; y < 128; y += 4) {
					int blockid = getRandomBlockId();
					chunk.setBlock(x, y, z, blockid, (blockid == 17) ? rand.nextInt(16) : 0);
				}
			}
		}
	}
	
	private int getRandomBlockId() {
		if (rand.nextInt(100) < 50) {
			int[] blockid = {1, 3, 17};
			return blockid[rand.nextInt(blockid.length)];
		}
		return blocklist[rand.nextInt(blocklist.length)];
	}

	@Override
	public void populateChunk(int chunkX, int chunkZ) {
		this.random.setSeed(0xdeadbeef ^ (chunkX << 8) ^ chunkZ ^ this.level.getSeed());
	}

	@Override
	public Map<String, Object> getSettings() {
		return options;
	}

	@Override
	public String getName() {
		return "skygrid";
	}

	@Override
	public Vector3 getSpawn() {
		return new Vector3(0, 80, 0);
	}

	@Override
	public ChunkManager getChunkManager() {
		return level;
	}
	
}