package teamdraco.wcfarmlife.common.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import teamdraco.wcfarmlife.registry.WCFarmLifeEntities;
import teamdraco.wcfarmlife.registry.WCFarmLifeItems;
import teamdraco.wcfarmlife.registry.WCFarmLifeSounds;

import javax.annotation.Nullable;

public class DomesticTribullEntity extends Animal {

    public DomesticTribullEntity(EntityType<? extends DomesticTribullEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.BEETROOT;
    }

    protected SoundEvent getAmbientSound() {
        return WCFarmLifeSounds.DOMESTIC_TRIBULL_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return WCFarmLifeSounds.DOMESTIC_TRIBULL_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return WCFarmLifeSounds.DOMESTIC_TRIBULL_DEATH.get();
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    @Nullable
    @Override
    public DomesticTribullEntity getBreedOffspring(ServerLevel world, AgeableMob ageable) {
        return WCFarmLifeEntities.DOMESTIC_TRIBULL.get().create(this.level);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(WCFarmLifeItems.DOMESTIC_TRIBULL_SPAWN_EGG.get());
    }

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return this.isBaby() ? 0.5F : 1.0F;
    }
}
