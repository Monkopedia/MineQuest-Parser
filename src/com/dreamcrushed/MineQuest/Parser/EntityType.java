package com.dreamcrushed.MineQuest.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.Location;

public enum EntityType {
    // These strings MUST match the strings in nms.EntityTypes and are case sensitive.
    CREEPER("Creeper", 50),
    SKELETON("Skeleton", 51),
    SPIDER("Spider", 52),
    GIANT("Giant", 53),
    ZOMBIE("Zombie", 54),
    SLIME("Slime", 55),
    GHAST("Ghast", 56),
    PIG_ZOMBIE("PigZombie", 57),
    ENDERMAN("Enderman", 58),
    CAVE_SPIDER("CaveSpider", 59),
    SILVERFISH("Silverfish", 60),
    BLAZE("Blaze", 61),
    MAGMA_CUBE("LavaSlime", 62),
    ENDER_DRAGON("EnderDragon", 63),
    WITHER("WitherBoss", 64),
    BAT("Bat", 65),
    WITCH("Witch", 66),
    PIG("Pig", 90),
    SHEEP("Sheep", 91),
    COW("Cow", 92),
    CHICKEN("Chicken", 93),
    SQUID("Squid", 94),
    WOLF("Wolf", 95),
    MUSHROOM_COW("MushroomCow", 96),
    SNOWMAN("SnowMan", 97),
    OCELOT("Ozelot", 98),
    IRON_GOLEM("VillagerGolem", 99),
    VILLAGER("Villager", 120),
    // These don't have an entity ID in nms.EntityTypes.
    /**
     * Spawn with {@link World#strikeLightning(org.bukkit.Location)}.
     */
    /**
     * An unknown entity without an Entity Class
     */
    ;

    private String name;
    private short typeId;
    private boolean independent;

    private static final Map<String, EntityType> NAME_MAP = new HashMap<String, EntityType>();
    private static final Map<Short, EntityType> ID_MAP = new HashMap<Short, EntityType>();
	public final static List<String> names = new ArrayList<String>();

    static {
        for (EntityType type : values()) {
            if (type.name != null) {
                NAME_MAP.put(type.name.toLowerCase(), type);
            }
            if (type.typeId > 0) {
                ID_MAP.put(type.typeId, type);
            }
            names.add(type.name);
        }
    }

    private EntityType(String name, int typeId) {
        this(name, typeId, true);
    }

    private EntityType(String name, int typeId, boolean independent) {
        this.name = name;
        this.typeId = (short) typeId;
        this.independent = independent;
    }

    public String getName() {
        return name;
    }

    public short getTypeId() {
        return typeId;
    }

    public static EntityType fromName(String name) {
        if (name == null) {
            return null;
        }
        return NAME_MAP.get(name.toLowerCase());
    }

    public static EntityType fromId(int id) {
        if (id > Short.MAX_VALUE) {
            return null;
        }
        return ID_MAP.get((short) id);
    }

    /**
     * Some entities cannot be spawned using {@link World#spawnCreature(Location, EntityType)}
     * or {@link World#spawn(Location, Class)}, usually
     * because they require additional information in order to spawn.
     * @return False if the entity type cannot be spawned
     */
    public boolean isSpawnable() {
        return independent;
    }
}
