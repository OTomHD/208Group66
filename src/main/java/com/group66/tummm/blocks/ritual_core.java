package com.group66.tummm.blocks;

import com.group66.tummm.MainTummm;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.CommandBlockExecutor;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Random;

public class ritual_core extends Block{
    protected static final VoxelShape COLLISION_SHAPE;
    protected static final VoxelShape OUTLINE_SHAPE;
    public static final IntProperty TRAP_TYPE; //= IntProperty.of("ritual_core", 0, 1);
    public static final BooleanProperty TRAP_SET;
    public static PlayerEntity OWNER;
    public static final EntityAttributeModifier FIRST_TRANCENDENCE;
    public static final EntityAttributeModifier SECOND_TRANCENDENCE;
    public static final EntityAttributeModifier THIRD_TRANCENDENCE;
    public static final EntityAttributeModifier FOURTH_TRANCENDENCE;
    //public static final EnumProperty TRAP_SET = EnumProperty.of("ritual_core", int.class.getClass());

    public ritual_core(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(TRAP_TYPE, 0).with(TRAP_SET, false));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = (double)pos.getX() + 0.5D;
        double e = (double)pos.getY() + 0.5D;
        double f = (double)pos.getZ() + 0.5D;

        world.addParticle(ParticleTypes.PORTAL, d, e, f, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        double d = (double)pos.getX() + 0.5D;
        double e = (double)pos.getY() + 0.5D;
        double f = (double)pos.getZ() + 0.5D;

        if((!world.isClient() && hand == hand.MAIN_HAND)) {
            player.sendMessage(new LiteralText("Working"), false);
            MainTummm.LOGGER.info((String.valueOf(state.get(TRAP_TYPE))));
            MainTummm.LOGGER.info(player.getStackInHand(hand).toString());
            MainTummm.LOGGER.info(state.get(TRAP_SET).toString());
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0D, 0.0D, 0.0D);
        }
        if((!world.isClient()) && player.getStackInHand(hand).toString().contains("mana_crystal")) {
            if(!state.get(TRAP_SET)) {
                player.sendMessage(new LiteralText("You have set the trap"), false);
                world.setBlockState(pos, state.with(TRAP_SET, true), Block.NOTIFY_ALL);
                OWNER = player;
            }
            else{
                player.sendMessage(new LiteralText("You have deactivated the ritual"), false);
                world.setBlockState(pos, state.with(TRAP_SET, false).with(TRAP_TYPE, 0), Block.NOTIFY_ALL);
                //world.setBlockState(pos, (BlockState)state.with(TRAP_TYPE, 0), Block.NOTIFY_ALL);
            }
            //trapSet = 1;
        }

        return ActionResult.SUCCESS;
    }

    //Collision with sides as well
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        //MainTummm.LOGGER.info(entity.getName().getString());
        if(!world.isClient()) {
            if (!state.get(TRAP_SET)) {  //Trap is not set
                if ((entity.getName().getString().equals("Fermented Spider Eye")) && !state.get(TRAP_SET)) {
                    world.setBlockState(pos, state.with(TRAP_TYPE, 1), Block.NOTIFY_ALL);
                    MainTummm.LOGGER.info("Swapping to Slowness");
                    entity.discard();
                }

                if ((entity.getName().getString().equals("Gunpowder"))) {
                    world.setBlockState(pos, state.with(TRAP_TYPE, 2), Block.NOTIFY_ALL);
                    MainTummm.LOGGER.info("Swapping to Explosive");
                    entity.discard();
                }

                if (entity.getName().getString().equals("Golden Apple")) {
                    world.setBlockState(pos, state.with(TRAP_TYPE, 10), Block.NOTIFY_ALL);
                    MainTummm.LOGGER.info("Swapping to Transcend");
                    entity.discard();
                }

                if (entity.getName().getString().equals("Stick")) {
                    world.setBlockState(pos, state.with(TRAP_TYPE, 15), Block.NOTIFY_ALL);
                    MainTummm.LOGGER.info("Swapping to remove powers");
                    entity.discard();
                }
                if (entity.getName().getString().equals("Paper")){
                    //MinecraftServer minecraftServer = world.getServer();
                    //CommandOutput com =
                    //ServerCommandSource sev = new ServerCommandSource();
                    CommandBlockExecutor cmd = new CommandBlockExecutor() {

                        public ServerWorld getWorld() {
                            return world.getServer().getWorld(world.getRegistryKey());
                        }

                        public void markDirty() {
                            BlockState blockState = world.getBlockState(pos);
                            this.getWorld().updateListeners(pos, blockState, blockState, 3);
                        }

                        public Vec3d getPos() {return Vec3d.ofCenter(pos);}

                        public ServerCommandSource getSource() {
                            return new ServerCommandSource(this, Vec3d.ofCenter(pos), Vec2f.ZERO, this.getWorld(), 2, this.getCustomName().getString(), this.getCustomName(), this.getWorld().getServer(), null);
                        }
                    };
                    String command = "/give @p written_book{pages:['{\"text\":\"Upon your arrival in this world you have surely come across strange materials unlike any from your world.\\\\nKeep them.\\\\nThey are far more valuable than you may realise...\"}','{\"text\":\"Any further notes are my discoveries on the uses of this worlds strange, yet powerful, resources.\\\\nFirst of all, you need to find yourself some platinum, diamonds, and glass. The diamonds, in particular, should be infused with mana. A lot of it. With this diamond as the focus the other resources should be\\\\n \"}','{\"text\":\"used in conjuction to generate a new crystal.\\\\n\\\\nWith thi\\\\n\\\\n(The book appears to be torn)\"}'],title:\"Tummmonicon Vol. 1\",author:Herobrine}";
                    cmd.setCommand(command);
                    cmd.execute(world);
                    entity.discard();
                    //minecraftServer.getCommandManager().execute(this, command);
                }
            } else { //TRAP is set
                if ((entity instanceof LivingEntity)) {
                    MainTummm.LOGGER.info("Triggered");
                    LivingEntity livingEntity = ((LivingEntity) entity);
                    /*EntityAttributeInstance moveSpeed = ((LivingEntity) entity).getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                    EntityAttributeInstance resistance = ((LivingEntity) entity).getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS); //idk
                    EntityAttributeInstance maxHP = ((LivingEntity) entity).getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
                    EntityAttributeInstance damage = ((LivingEntity) entity).getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    EntityAttributeInstance attackSpeed = ((LivingEntity) entity).getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED);*/

                    if (state.get(TRAP_TYPE) == 0) {
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 3));
                        //entityAttributeInstance.addPersistentModifier(TRANCENDENCE);
                        //MainTummm.LOGGER.info(entityAttributeInstance.getAttribute().toString());
                        //livingEntity.setMovementSpeed(2000);
                    } else if (state.get(TRAP_TYPE) == 1) {
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 100));
                        //entityAttributeInstance.clearModifiers();
                        entity.damage(DamageSource.MAGIC, 1.0F);
                    } else if (state.get(TRAP_TYPE) == 2) {
                        world.createExplosion(OWNER, pos.getX(), pos.getY() + 2, pos.getZ(), 10, Explosion.DestructionType.DESTROY);
                    } else if (state.get(TRAP_TYPE) == 10) {
                        MainTummm.LOGGER.info("They have become closer to accession");
                        //MainTummm.LOGGER.info(String.valueOf(maxHP.getValue()));
                        //clearModifiers(livingEntity);
                        augmentAbilities(livingEntity);
                        //MainTummm.LOGGER.info(String.valueOf(maxHP.getValue()));
                    } else if (state.get(TRAP_TYPE) == 15) {
                        MainTummm.LOGGER.info("They are cleansed");
                        clearModifiers(livingEntity);
                    }

                    world.setBlockState(pos, state.with(TRAP_TYPE, 0), Block.NOTIFY_ALL);
                    world.setBlockState(pos, state.with(TRAP_SET, false), Block.NOTIFY_ALL);
                }
            }
        }
    }

    public void augmentAbilities(LivingEntity livingEntity){
        EntityAttributeInstance moveSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        EntityAttributeInstance resistance = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR); //idk
        EntityAttributeInstance maxHP = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        EntityAttributeInstance damage = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        EntityAttributeInstance attackSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED);
        EntityAttributeModifier TRANSCENDENCE = FIRST_TRANCENDENCE;

        MainTummm.LOGGER.info("inside thing " + String.valueOf(maxHP.getValue()));
        int maxHPValue = (int) maxHP.getValue();

        if(maxHPValue == 20){
            TRANSCENDENCE = SECOND_TRANCENDENCE;
        }else if(maxHPValue == 28){
            TRANSCENDENCE = THIRD_TRANCENDENCE;
        }else if(maxHPValue == 40){
            TRANSCENDENCE = FOURTH_TRANCENDENCE;
        }else {
            MainTummm.LOGGER.info("Error "+String.valueOf(maxHPValue));
        }

        /*switch() {
            case 24: TRANSCENDENCE = SECOND_TRANCENDENCE;
            case 36: TRANSCENDENCE = THIRD_TRANCENDENCE;
            case 42: TRANSCENDENCE = FOURTH_TRANCENDENCE;
        }*/

        MainTummm.LOGGER.info("Multiplier: "+ String.valueOf(TRANSCENDENCE.getValue()) + " Level: " + TRANSCENDENCE.getName());
        clearModifiers(livingEntity);
        moveSpeed.addPersistentModifier(TRANSCENDENCE);
        resistance.addPersistentModifier(TRANSCENDENCE);
        maxHP.addPersistentModifier(TRANSCENDENCE);
        damage.addPersistentModifier(TRANSCENDENCE);
        attackSpeed.addPersistentModifier(TRANSCENDENCE);
    }

    public void clearModifiers(LivingEntity livingEntity){
        EntityAttributeInstance moveSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        EntityAttributeInstance resistance = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS); //idk
        EntityAttributeInstance maxHP = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        EntityAttributeInstance damage = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        EntityAttributeInstance attackSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED);

        moveSpeed.clearModifiers();
        resistance.clearModifiers();
        maxHP.clearModifiers();
        damage.clearModifiers();
        attackSpeed.clearModifiers();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TRAP_TYPE);
        builder.add(TRAP_SET);
    }

    /*@Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        MainTummm.LOGGER.info("Placed");
        world.setBlockState(pos, (BlockState)state.with(trapSet, 0), Block.NOTIFY_ALL);
    }*/

    //collision only with landing
    /*
    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        super.onEntityLand(world, entity);
        if((entity instanceof LivingEntity) && trapSet == 1){
            MainTummm.LOGGER.info("Triggered");
            LivingEntity livingEntity = ((LivingEntity) entity);
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 3));
            entity.damage(DamageSource.MAGIC, 1.0F);
            world.setBlockState(pos, (BlockState)state.with(trapSet, 0), Block.NOTIFY_ALL);
        }
    }*/

    static {
        COLLISION_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
        OUTLINE_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

        TRAP_TYPE = IntProperty.of("trap_type", 0, 15);
        TRAP_SET = BooleanProperty.of("trap_set");

        FIRST_TRANCENDENCE = new EntityAttributeModifier("First Transcendence", 0.2D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        SECOND_TRANCENDENCE = new EntityAttributeModifier("Second Transcendence", 0.4D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        THIRD_TRANCENDENCE = new EntityAttributeModifier("Third Transcendence", 1D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        FOURTH_TRANCENDENCE = new EntityAttributeModifier("Fourth Transcendence", 3D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}