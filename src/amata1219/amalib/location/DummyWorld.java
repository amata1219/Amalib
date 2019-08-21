package amata1219.amalib.location;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

import org.bukkit.BlockChangeDelegate;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Difficulty;
import org.bukkit.Effect;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.StructureType;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Consumer;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

@SuppressWarnings("deprecation")
public class DummyWorld implements World {

	public static final World INSTANCE = new DummyWorld();

	@Override
	public Set<String> getListeningPluginChannels() {
		return null;
	}

	@Override
	public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
	}

	@Override
	public List<MetadataValue> getMetadata(String arg0) {
		return null;
	}

	@Override
	public boolean hasMetadata(String arg0) {
		return false;
	}

	@Override
	public void removeMetadata(String arg0, Plugin arg1) {
	}

	@Override
	public void setMetadata(String arg0, MetadataValue arg1) {
	}

	@Override
	public boolean canGenerateStructures() {
		return false;
	}

	@Override
	public boolean createExplosion(Location arg0, float arg1) {
		return false;
	}

	@Override
	public boolean createExplosion(Location arg0, float arg1, boolean arg2) {
		return false;
	}

	@Override
	public boolean createExplosion(double arg0, double arg1, double arg2, float arg3) {
		return false;
	}

	@Override
	public boolean createExplosion(double arg0, double arg1, double arg2, float arg3, boolean arg4) {
		return false;
	}

	@Override
	public boolean createExplosion(double arg0, double arg1, double arg2, float arg3, boolean arg4, boolean arg5) {
		return false;
	}

	@Override
	public Item dropItem(Location arg0, ItemStack arg1) {
		return null;
	}

	@Override
	public Item dropItemNaturally(Location arg0, ItemStack arg1) {
		return null;
	}

	@Override
	public boolean generateTree(Location arg0, TreeType arg1) {
		return false;
	}

	@Override
	public boolean generateTree(Location arg0, TreeType arg1, BlockChangeDelegate arg2) {
		return false;
	}

	@Override
	public boolean getAllowAnimals() {
		return false;
	}

	@Override
	public boolean getAllowMonsters() {
		return false;
	}

	@Override
	public int getAmbientSpawnLimit() {
		return 0;
	}

	@Override
	public int getAnimalSpawnLimit() {
		return 0;
	}

	@Override
	public Biome getBiome(int arg0, int arg1) {
		return null;
	}

	@Override
	public Block getBlockAt(Location arg0) {
		return null;
	}

	@Override
	public Block getBlockAt(int arg0, int arg1, int arg2) {
		return null;
	}

	@Override
	public Chunk getChunkAt(Location arg0) {
		return null;
	}

	@Override
	public Chunk getChunkAt(Block arg0) {
		return null;
	}

	@Override
	public Chunk getChunkAt(int arg0, int arg1) {
		return null;
	}

	@Override
	public Difficulty getDifficulty() {
		return null;
	}

	@Override
	public ChunkSnapshot getEmptyChunkSnapshot(int arg0, int arg1, boolean arg2, boolean arg3) {
		return null;
	}

	@Override
	public List<Entity> getEntities() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> Collection<T> getEntitiesByClass(Class<T>... arg0) {
		return null;
	}

	@Override
	public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> arg0) {
		return null;
	}

	@Override
	public Collection<Entity> getEntitiesByClasses(Class<?>... arg0) {
		return null;
	}

	@Override
	public Environment getEnvironment() {
		return null;
	}

	@Override
	public Collection<Chunk> getForceLoadedChunks() {
		return null;
	}

	@Override
	public long getFullTime() {
		return 0;
	}

	@Override
	public <T> T getGameRuleDefault(GameRule<T> arg0) {
		return null;
	}

	@Override
	public String getGameRuleValue(String arg0) {
		return null;
	}

	@Override
	public <T> T getGameRuleValue(GameRule<T> arg0) {
		return null;
	}

	@Override
	public String[] getGameRules() {
		return null;
	}

	@Override
	public ChunkGenerator getGenerator() {
		return null;
	}

	@Override
	public Block getHighestBlockAt(Location arg0) {
		return null;
	}

	@Override
	public Block getHighestBlockAt(int arg0, int arg1) {
		return null;
	}

	@Override
	public int getHighestBlockYAt(Location arg0) {
		return 0;
	}

	@Override
	public int getHighestBlockYAt(int arg0, int arg1) {
		return 0;
	}

	@Override
	public double getHumidity(int arg0, int arg1) {
		return 0;
	}

	@Override
	public boolean getKeepSpawnInMemory() {
		return false;
	}

	@Override
	public List<LivingEntity> getLivingEntities() {
		return null;
	}

	@Override
	public Chunk[] getLoadedChunks() {
		return null;
	}

	@Override
	public int getMaxHeight() {
		return 0;
	}

	@Override
	public int getMonsterSpawnLimit() {
		return 0;
	}

	@Override
	public String getName() {
		return "dummy";
	}

	@Override
	public Collection<Entity> getNearbyEntities(BoundingBox arg0) {
		return null;
	}

	@Override
	public Collection<Entity> getNearbyEntities(BoundingBox arg0, Predicate<Entity> arg1) {
		return null;
	}

	@Override
	public Collection<Entity> getNearbyEntities(Location arg0, double arg1, double arg2, double arg3) {
		return null;
	}

	@Override
	public Collection<Entity> getNearbyEntities(Location arg0, double arg1, double arg2, double arg3,
			Predicate<Entity> arg4) {
		return null;
	}

	@Override
	public boolean getPVP() {
		return false;
	}

	@Override
	public List<Player> getPlayers() {
		return null;
	}

	@Override
	public List<BlockPopulator> getPopulators() {
		return null;
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	public long getSeed() {
		return 0;
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public double getTemperature(int arg0, int arg1) {
		return 0;
	}

	@Override
	public int getThunderDuration() {
		return 0;
	}

	@Override
	public long getTicksPerAnimalSpawns() {
		return 0;
	}

	@Override
	public long getTicksPerMonsterSpawns() {
		return 0;
	}

	@Override
	public long getTime() {
		return 0;
	}

	@Override
	public UUID getUID() {
		return null;
	}

	@Override
	public int getWaterAnimalSpawnLimit() {
		return 0;
	}

	@Override
	public int getWeatherDuration() {
		return 0;
	}

	@Override
	public WorldBorder getWorldBorder() {
		return null;
	}

	@Override
	public File getWorldFolder() {
		return null;
	}

	@Override
	public WorldType getWorldType() {
		return null;
	}

	@Override
	public boolean hasStorm() {
		return false;
	}

	@Override
	public boolean isAutoSave() {
		return false;
	}

	@Override
	public boolean isChunkForceLoaded(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean isChunkGenerated(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean isChunkInUse(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean isChunkLoaded(Chunk arg0) {
		return false;
	}

	@Override
	public boolean isChunkLoaded(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean isGameRule(String arg0) {
		return false;
	}

	@Override
	public boolean isThundering() {
		return false;
	}

	@Override
	public void loadChunk(Chunk arg0) {
	}

	@Override
	public void loadChunk(int arg0, int arg1) {
	}

	@Override
	public boolean loadChunk(int arg0, int arg1, boolean arg2) {
		return false;
	}

	@Override
	public Location locateNearestStructure(Location arg0, StructureType arg1, int arg2, boolean arg3) {
		return null;
	}

	@Override
	public void playEffect(Location arg0, Effect arg1, int arg2) {
	}

	@Override
	public <T> void playEffect(Location arg0, Effect arg1, T arg2) {
	}

	@Override
	public void playEffect(Location arg0, Effect arg1, int arg2, int arg3) {
	}

	@Override
	public <T> void playEffect(Location arg0, Effect arg1, T arg2, int arg3) {
	}

	@Override
	public void playSound(Location arg0, Sound arg1, float arg2, float arg3) {
	}

	@Override
	public void playSound(Location arg0, String arg1, float arg2, float arg3) {
	}

	@Override
	public void playSound(Location arg0, Sound arg1, SoundCategory arg2, float arg3, float arg4) {
	}

	@Override
	public void playSound(Location arg0, String arg1, SoundCategory arg2, float arg3, float arg4) {
	}

	@Override
	public RayTraceResult rayTrace(Location arg0, Vector arg1, double arg2, FluidCollisionMode arg3, boolean arg4,
			double arg5, Predicate<Entity> arg6) {
		return null;
	}

	@Override
	public RayTraceResult rayTraceBlocks(Location arg0, Vector arg1, double arg2) {
		return null;
	}

	@Override
	public RayTraceResult rayTraceBlocks(Location arg0, Vector arg1, double arg2, FluidCollisionMode arg3) {
		return null;
	}

	@Override
	public RayTraceResult rayTraceBlocks(Location arg0, Vector arg1, double arg2, FluidCollisionMode arg3,
			boolean arg4) {
		return null;
	}

	@Override
	public RayTraceResult rayTraceEntities(Location arg0, Vector arg1, double arg2) {
		return null;
	}

	@Override
	public RayTraceResult rayTraceEntities(Location arg0, Vector arg1, double arg2, double arg3) {
		return null;
	}

	@Override
	public RayTraceResult rayTraceEntities(Location arg0, Vector arg1, double arg2, Predicate<Entity> arg3) {
		return null;
	}

	@Override
	public RayTraceResult rayTraceEntities(Location arg0, Vector arg1, double arg2, double arg3,
			Predicate<Entity> arg4) {
		return null;
	}

	@Override
	public boolean refreshChunk(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean regenerateChunk(int arg0, int arg1) {
		return false;
	}

	@Override
	public void save() {
	}

	@Override
	public void setAmbientSpawnLimit(int arg0) {
	}

	@Override
	public void setAnimalSpawnLimit(int arg0) {
	}

	@Override
	public void setAutoSave(boolean arg0) {
	}

	@Override
	public void setBiome(int arg0, int arg1, Biome arg2) {
	}

	@Override
	public void setChunkForceLoaded(int arg0, int arg1, boolean arg2) {
	}

	@Override
	public void setDifficulty(Difficulty arg0) {
	}

	@Override
	public void setFullTime(long arg0) {
	}

	@Override
	public <T> boolean setGameRule(GameRule<T> arg0, T arg1) {
		return false;
	}

	@Override
	public boolean setGameRuleValue(String arg0, String arg1) {
		return false;
	}

	@Override
	public void setKeepSpawnInMemory(boolean arg0) {
	}

	@Override
	public void setMonsterSpawnLimit(int arg0) {
	}

	@Override
	public void setPVP(boolean arg0) {
	}

	@Override
	public void setSpawnFlags(boolean arg0, boolean arg1) {
	}

	@Override
	public boolean setSpawnLocation(Location arg0) {
		return false;
	}

	@Override
	public boolean setSpawnLocation(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public void setStorm(boolean arg0) {
	}

	@Override
	public void setThunderDuration(int arg0) {
	}

	@Override
	public void setThundering(boolean arg0) {
	}

	@Override
	public void setTicksPerAnimalSpawns(int arg0) {
	}

	@Override
	public void setTicksPerMonsterSpawns(int arg0) {
	}

	@Override
	public void setTime(long arg0) {
	}

	@Override
	public void setWaterAnimalSpawnLimit(int arg0) {
	}

	@Override
	public void setWeatherDuration(int arg0) {
	}

	@Override
	public <T extends Entity> T spawn(Location arg0, Class<T> arg1) throws IllegalArgumentException {
		return null;
	}

	@Override
	public <T extends Entity> T spawn(Location arg0, Class<T> arg1, Consumer<T> arg2) throws IllegalArgumentException {
		return null;
	}

	@Override
	public Arrow spawnArrow(Location arg0, Vector arg1, float arg2, float arg3) {
		return null;
	}

	@Override
	public <T extends Arrow> T spawnArrow(Location arg0, Vector arg1, float arg2, float arg3, Class<T> arg4) {
		return null;
	}

	@Override
	public Entity spawnEntity(Location arg0, EntityType arg1) {
		return null;
	}

	@Override
	public FallingBlock spawnFallingBlock(Location arg0, MaterialData arg1) throws IllegalArgumentException {
		return null;
	}

	@Override
	public FallingBlock spawnFallingBlock(Location arg0, BlockData arg1) throws IllegalArgumentException {
		return null;
	}

	@Override
	public FallingBlock spawnFallingBlock(Location arg0, Material arg1, byte arg2) throws IllegalArgumentException {
		return null;
	}

	@Override
	public void spawnParticle(Particle arg0, Location arg1, int arg2) {
	}

	@Override
	public <T> void spawnParticle(Particle arg0, Location arg1, int arg2, T arg3) {
	}

	@Override
	public void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4) {
	}

	@Override
	public <T> void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, T arg5) {
	}

	@Override
	public void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5) {
	}

	@Override
	public <T> void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5,
			T arg6) {
	}

	@Override
	public void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5,
			double arg6) {
	}

	@Override
	public void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5, double arg6,
			double arg7) {
	}

	@Override
	public <T> void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5,
			double arg6, T arg7) {
	}

	@Override
	public <T> void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5,
			double arg6, double arg7, T arg8) {
	}

	@Override
	public void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5, double arg6,
			double arg7, double arg8) {
	}

	@Override
	public <T> void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5,
			double arg6, T arg7, boolean arg8) {
	}

	@Override
	public <T> void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5,
			double arg6, double arg7, double arg8, T arg9) {
	}

	@Override
	public <T> void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5,
			double arg6, double arg7, double arg8, T arg9, boolean arg10) {
	}

	@Override
	public Spigot spigot() {
		return null;
	}

	@Override
	public LightningStrike strikeLightning(Location arg0) {
		return null;
	}

	@Override
	public LightningStrike strikeLightningEffect(Location arg0) {
		return null;
	}

	@Override
	public boolean unloadChunk(Chunk arg0) {
		return false;
	}

	@Override
	public boolean unloadChunk(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean unloadChunk(int arg0, int arg1, boolean arg2) {
		return false;
	}

	@Override
	public boolean unloadChunk(int arg0, int arg1, boolean arg2, boolean arg3) {
		return false;
	}

	@Override
	public boolean unloadChunkRequest(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean unloadChunkRequest(int arg0, int arg1, boolean arg2) {
		return false;
	}

}
