package teamdraco.wcfarmlife.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.wcfarmlife.WCFarmLife;
import teamdraco.wcfarmlife.common.entities.DomesticTribullEntity;
import teamdraco.wcfarmlife.common.entities.GalliraptorEntity;

public class WCFarmLifeEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, WCFarmLife.MOD_ID);

    public static final RegistryObject<EntityType<DomesticTribullEntity>> DOMESTIC_TRIBULL = create("domestic_tribull", EntityType.Builder.of(DomesticTribullEntity::new, MobCategory.CREATURE).sized(1.1f, 1.2f));
    public static final RegistryObject<EntityType<GalliraptorEntity>> GALLIRAPTOR = create("galliraptor", EntityType.Builder.of(GalliraptorEntity::new, MobCategory.CREATURE).sized(0.6f, 0.8f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return REGISTRY.register(name, () -> builder.build(WCFarmLife.MOD_ID + "." + name));
    }
}