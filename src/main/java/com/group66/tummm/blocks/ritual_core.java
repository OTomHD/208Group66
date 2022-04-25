package com.group66.tummm.blocks;

import com.group66.tummm.MainTummm;
import com.group66.tummm.items.custom.tummmManaCrystal;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TrackOwnerAttackerGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
import org.lwjgl.system.CallbackI;

import java.util.Random;

import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.Hand.OFF_HAND;

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
    public static final EntityAttributeModifier DAMAGE_TRANCENDENCE_FIRST;
    public static final EntityAttributeModifier DAMAGE_TRANCENDENCE_SECOND;
    public static final EntityAttributeModifier DAMAGE_TRANCENDENCE_THIRD;
    public static final EntityAttributeModifier DAMAGE_TRANCENDENCE_FOURTH;
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
            //player.sendMessage(new LiteralText("Working"), false);
            MainTummm.LOGGER.info((String.valueOf(state.get(TRAP_TYPE))));
            MainTummm.LOGGER.info(player.getStackInHand(hand).toString());
            if(player.getStackInHand(hand).getName().toString().contains("Tummmonicon Vol. 1")) {
                MainTummm.LOGGER.info("Its the book");
            }
            MainTummm.LOGGER.info(state.get(TRAP_SET).toString());
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0D, 0.0D, 0.0D);
        }
        if((!world.isClient()) && player.getStackInHand(hand).getItem() instanceof tummmManaCrystal) {
            MainTummm.LOGGER.info("Test 1");
            if(!state.get(TRAP_SET)) {
                if((state.get(TRAP_TYPE) < 10 || state.get(TRAP_TYPE) == 15) && hasMana(player, 150)) {
                    player.sendMessage(new LiteralText("You have set the trap"), false);
                    world.setBlockState(pos, state.with(TRAP_SET, true), Block.NOTIFY_ALL);
                    OWNER = player;
                }else if(state.get(TRAP_TYPE) == 10){
                    if (player.getMaxHealth() == 20.0){
                        if(hasMana(player, 300)){
                            player.sendMessage(new LiteralText("Transcendence Prepared"), false);
                            world.setBlockState(pos, state.with(TRAP_SET, true), Block.NOTIFY_ALL);
                            OWNER = player;
                        }
                    }else if(player.getMaxHealth() == 28.0){
                        if(hasMana(player, 600)){
                            player.sendMessage(new LiteralText("Transcendence Prepared"), false);
                            world.setBlockState(pos, state.with(TRAP_SET, true), Block.NOTIFY_ALL);
                            OWNER = player;
                        }
                    }else if(player.getMaxHealth() == 40.0){
                        if(hasMana(player, 1000)){
                            player.sendMessage(new LiteralText("Transcendence Prepared"), false);
                            world.setBlockState(pos, state.with(TRAP_SET, true), Block.NOTIFY_ALL);
                            OWNER = player;
                        }
                    }
                }
            }
            else if(hasMana(player, 150)){
                player.sendMessage(new LiteralText("You have deactivated the ritual"), false);
                world.setBlockState(pos, state.with(TRAP_SET, false).with(TRAP_TYPE, 0), Block.NOTIFY_ALL);
                //world.setBlockState(pos, (BlockState)state.with(TRAP_TYPE, 0), Block.NOTIFY_ALL);
            }
            //trapSet = 1;
        }else if((!world.isClient()) && state.get(TRAP_TYPE) == 13){
            String bookInHand = player.getStackInHand(hand).getName().toString();//.contains("Tummmonicon Vol. 1");
            String command = null;
            if (bookInHand.contains("air")){
                command = "/give @p written_book{pages:['{\"text\":\"Upon your arrival in this world you have surely come across strange materials unlike any from your world.\\\\nKeep them.\\\\nThey are far more valuable than you may realise...\"}','{\"text\":\"Any further notes are my discoveries on the uses of this worlds strange, yet powerful, resources.\\\\nFirst of all, you need to find yourself some platinum, diamonds, and glass. The diamonds, in particular, should be infused with mana. A lot of it. With this diamond as the focus the other resources should be\\\\n \"}','{\"text\":\"used in conjuction to generate a new crystal.\\\\n\\\\nWith thi\\\\n\\\\n(The book appears to be torn)\"}'],title:\"Tummmonomicon Vol. 1\",author:Herobrine}";
            }else if(bookInHand.contains("Vol. 1")){
                command ="/give @p written_book{pages:['{\"text\":\"Upon your arrival in this world you have surely come across strange materials unlike any from your world.\\\\nKeep them.\\\\nThey are far more valuable than you may realise...\"}','{\"text\":\"Any further notes are my discoveries on the uses of this worlds strange, yet powerful, resources.\\\\nFirst of all, you need to find yourself some platinum, diamonds, and glass. The diamonds, in particular, should be infused with mana. A lot of it. With this diamond as the focus the other resources should be\"}','{\"text\":\"used in conjuction to generate a new crystal.\\\\n\\\\nWith this, you are ready to start your journey to enlightenment.\\\\n\\\\nFrom here you are going to learn and experiment with rituals. You should try throwing different items and combinations\"}','{\"text\":\"onto the altar, maybe even this book...\\\\n\\\\nThe results of this may vary, some may be beneficial, some dangerous. it is up to you to experiment.\\\\n\\\\nA good starting point may be to place a fermented spider eye on the altar and use your mana crystal.\\\\n\\\\n \"}','{\"text\":\"Once you have gotten the hang of this I hope you have some resources left over as next you will be crafting wands.\\\\n\\\\nTo d\\\\n\\\\n(The rest of the book appears torn)\\\\n\\\\n \"}'],title:\"Tummmonomicon Vol. 2\",author:Herobrine}";
            }else if(bookInHand.contains("Vol. 2")){
                command = "/give @p written_book{pages:['{\"text\":\"Upon your arrival in this world you have surely come across strange materials unlike any from your world.\\\\nKeep them.\\\\nThey are far more valuable than you may realise...\"}','{\"text\":\"Any further notes are my discoveries on the uses of this worlds strange, yet powerful, resources.\\\\nFirst of all, you need to find yourself some platinum, diamonds, and glass. The diamonds, in particular, should be infused with mana. A lot of it. With this diamond as the focus the other resources should be\"}','{\"text\":\"used in conjuction to generate a new crystal.\\\\n\\\\nWith this, you are ready to start your journey to enlightenment.\\\\n\\\\nFrom here you are going to learn and experiment with rituals. You should try throwing different items and combinations\"}','{\"text\":\"onto the altar, maybe even this book...\\\\n\\\\nThe results of this may vary, some may be beneficial, some dangerous. it is up to you to experiment.\\\\n\\\\nA good starting point may be to place a fermented spider eye on the altar and use your mana crystal.\"}','{\"text\":\"Once you have gotten the hang of this I hope you have some resources left over as next you will be crafting wands.\\\\n\\\\nTo do this is quite simple, simply place two suitable sticks diagonally with a fitting object.\"}','{\"text\":\"While I leave the experimentation to you, a suggestion may be to try some of the harder to come by objects in this, a fire charge perhaps?\\\\n\\\\nWhen you have yourself a functional wand, you should hold both your crystal and the wand in order to make use of it.\\\\n \"}','{\"text\":\"With all this you are ready to take the next step...\\\\n\\\\n(The book is torn once again)\\\\n \"}'],title:\"Tummmonomicon Vol. 3\",author:Herobrine}";
            }else if(bookInHand.contains("Vol. 3")){
                command = "/give @p written_book{pages:['{\"text\":\"Upon your arrival in this world you have surely come across strange materials unlike any from your world.\\\\nKeep them.\\\\nThey are far more valuable than you may realise...\"}','{\"text\":\"Any further notes are my discoveries on the uses of this worlds strange, yet powerful, resources.\\\\nFirst of all, you need to find yourself some platinum, diamonds, and glass. The diamonds, in particular, should be infused with mana. A lot of it. With this diamond as the focus the other resources should be\"}','{\"text\":\"used in conjuction to generate a new crystal.\\\\n\\\\nWith this, you are ready to start your journey to enlightenment.\\\\n\\\\nFrom here you are going to learn and experiment with rituals. You should try throwing different items and combinations\"}','{\"text\":\"onto the altar, maybe even this book...\\\\n\\\\nThe results of this may vary, some may be beneficial, some dangerous. it is up to you to experiment.\\\\n\\\\nA good starting point may be to place a fermented spider eye on the altar and use your mana crystal.\"}','{\"text\":\"Once you have gotten the hang of this I hope you have some resources left over as next you will be crafting wands.\\\\n\\\\nTo do this is quite simple, simply place two suitable sticks diagonally with a fitting object.\"}','{\"text\":\"While I leave the experimentation to you, a suggestion may be to try some of the harder to come by objects in this, a fire charge perhaps?\\\\n\\\\nWhen you have yourself a functional wand, you should hold both your crystal and the wand in order to make use of it.\\\\n \"}','{\"text\":\"With all this you are ready to take the next step...\\\\n\\\\n \"}','{\"text\":\"In your experimentation, you may have noticed your mana crystal being unable to keep up with you and its mana draining.\\\\n\\\\nTo remedy this, it is possible to upgrade the crystal. However, this is not cheap. This can be done twice and will require the previous tier of\"}','{\"text\":\"crystal and will use a similar process to the original, with any glass being replaced with infused diamonds.\\\\n\\\\n(Another torn page...)\"}'],title:\"Tummmonomicon Vol. 4\",author:Herobrine}";
            }else if (bookInHand.contains("Vol. 4")){
                command = "/give @p written_book{pages:['{\"text\":\"Upon your arrival in this world you have surely come across strange materials unlike any from your world.\\\\nKeep them.\\\\nThey are far more valuable than you may realise...\"}','{\"text\":\"Any further notes are my discoveries on the uses of this worlds strange, yet powerful, resources.\\\\nFirst of all, you need to find yourself some platinum, diamonds, and glass. The diamonds, in particular, should be infused with mana. A lot of it. With this diamond as the focus the other resources should be\"}','{\"text\":\"used in conjuction to generate a new crystal.\\\\n\\\\nWith this, you are ready to start your journey to enlightenment.\\\\n\\\\nFrom here you are going to learn and experiment with rituals. You should try throwing different items and combinations\"}','{\"text\":\"onto the altar, maybe even this book...\\\\n\\\\nThe results of this may vary, some may be beneficial, some dangerous. it is up to you to experiment.\\\\n\\\\nA good starting point may be to place a fermented spider eye on the altar and use your mana crystal.\"}','{\"text\":\"Once you have gotten the hang of this I hope you have some resources left over as next you will be crafting wands.\\\\n\\\\nTo do this is quite simple, simply place two suitable sticks diagonally with a fitting object.\"}','{\"text\":\"While I leave the experimentation to you, a suggestion may be to try some of the harder to come by objects in this, a fire charge perhaps?\\\\n\\\\nWhen you have yourself a functional wand, you should hold both your crystal and the wand in order to make use of it.\\\\n \"}','{\"text\":\"With all this you are ready to take the next step...\\\\n\\\\n \"}','{\"text\":\"In your experimentation, you may have noticed your mana crystal being unable to keep up with you and its mana draining.\\\\n\\\\nTo remedy this, it is possible to upgrade the crystal. However, this is not cheap. This can be done twice and will require the previous tier of\"}','{\"text\":\"crystal and will use a similar process to the original, with any glass being replaced with infused diamonds.\\\\n\\\\n \"}','{\"text\":\"Now that you have made it this far you are ready for ascension.\\\\n\\\\nYou may have noticed a special occurence when using a golden apple with the ritual core. Each tier of the mana crystal provides a different benefit, so try them all. With all this you can become unstoppable...\\\\n \"}'],title:\"Tummmonomicon Vol. 5\",author:Herobrine}";
            }
            if(command != null) {
                runCommand(world, command, pos);
                if (!bookInHand.contains("air")) {
                    player.getStackInHand(hand).decrement(1);
                }
                world.setBlockState(pos, state.with(TRAP_TYPE, 0), Block.NOTIFY_ALL);
            }
        }

        return ActionResult.SUCCESS;
    }

    //Collision with sides as well
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(!world.isClient()) {
            //MainTummm.LOGGER.info(entity.getName().getString());
            //MainTummm.LOGGER.info(entity.getDisplayName().toString());
            //world.get
            //MainTummm.LOGGER.info(entity.get);
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
                if (entity.getName().getString().equals("Magic Page")){
                    MainTummm.LOGGER.info("Swapping book mode");
                    world.setBlockState(pos, state.with(TRAP_TYPE, 13), Block.NOTIFY_ALL);
                    //MinecraftServer minecraftServer = world.getServer();
                    //CommandOutput com =
                    //ServerCommandSource sev = new ServerCommandSource();

                    entity.discard();
                    //minecraftServer.getCommandManager().execute(this, command);
                }
                if (state.get(TRAP_TYPE) == 13){
                    String bookInHand = entity.getName().toString();
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
                        //StatusEffects.RESISTANCE
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

    public void runCommand(World world, String command, BlockPos pos){
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
        //String command = "/give @p written_book{pages:['{\"text\":\"Upon your arrival in this world you have surely come across strange materials unlike any from your world.\\\\nKeep them.\\\\nThey are far more valuable than you may realise...\"}','{\"text\":\"Any further notes are my discoveries on the uses of this worlds strange, yet powerful, resources.\\\\nFirst of all, you need to find yourself some platinum, diamonds, and glass. The diamonds, in particular, should be infused with mana. A lot of it. With this diamond as the focus the other resources should be\\\\n \"}','{\"text\":\"used in conjuction to generate a new crystal.\\\\n\\\\nWith thi\\\\n\\\\n(The book appears to be torn)\"}'],title:\"Tummmonicon Vol. 1\",author:Herobrine}";
        cmd.setCommand(command);
        cmd.execute(world);
    }

    public void augmentAbilities(LivingEntity livingEntity){
        EntityAttributeInstance moveSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        EntityAttributeInstance resistance = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR); //idk
        EntityAttributeInstance maxHP = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        EntityAttributeInstance damage = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        StatusEffectInstance regen;// = new StatusEffectInstance(StatusEffects.REGENERATION, 9999999, 10);
        StatusEffectInstance resist;// = new StatusEffectInstance(StatusEffects.RESISTANCE, 9999999, 10);


        //livingEntity.addStatusEffect(resist);
        EntityAttributeInstance attackSpeed = null;

        if(livingEntity instanceof PlayerEntity) {
             attackSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED);
        }
        EntityAttributeModifier TRANSCENDENCE = FIRST_TRANCENDENCE;
        EntityAttributeModifier DMG_TRANSCENDENCE = DAMAGE_TRANCENDENCE_FIRST;

        MainTummm.LOGGER.info("inside thing " + String.valueOf(maxHP.getValue()));
        int maxHPValue = (int) maxHP.getValue();

        if(maxHPValue == 20){
            TRANSCENDENCE = SECOND_TRANCENDENCE;
            DMG_TRANSCENDENCE = DAMAGE_TRANCENDENCE_SECOND;
        }else if(maxHPValue == 28){
            TRANSCENDENCE = THIRD_TRANCENDENCE;;
            DMG_TRANSCENDENCE = DAMAGE_TRANCENDENCE_THIRD;
        }else if(maxHPValue == 40){
            TRANSCENDENCE = FOURTH_TRANCENDENCE;
            DMG_TRANSCENDENCE = DAMAGE_TRANCENDENCE_FOURTH;
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
        //livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 99, 3));
        regen = new StatusEffectInstance(StatusEffects.REGENERATION, 9999999, (int) Math.ceil(TRANSCENDENCE.getValue()*2));
        resist = new StatusEffectInstance(StatusEffects.RESISTANCE, 9999999, (int) Math.ceil(TRANSCENDENCE.getValue()*2));
        regen.setPermanent(true);
        resist.setPermanent(true);

        livingEntity.addStatusEffect(resist);
        livingEntity.addStatusEffect(regen);
        moveSpeed.addPersistentModifier(TRANSCENDENCE);
        resistance.addPersistentModifier(TRANSCENDENCE);
        maxHP.addPersistentModifier(TRANSCENDENCE);
        if(damage != null) {
            damage.addPersistentModifier(DMG_TRANSCENDENCE);
        }
        //livingEntity.
        if(livingEntity instanceof PlayerEntity) {
            attackSpeed.addPersistentModifier(TRANSCENDENCE);
        }
    }

    public boolean hasMana(PlayerEntity player, int manaCost){
        ItemStack stack = player.getStackInHand(MAIN_HAND);
        MainTummm.LOGGER.info("Max damage"+stack.getMaxDamage());
        MainTummm.LOGGER.info("get damage"+stack.getDamage());
        if (stack.getMaxDamage()-stack.getDamage() > manaCost+1){
            stack.damage(manaCost, player, (p -> p.sendToolBreakStatus(MAIN_HAND)));
            return true;
        }

        return false;
    }

    public void clearModifiers(LivingEntity livingEntity){
        EntityAttributeInstance moveSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        EntityAttributeInstance resistance = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR); //idk
        EntityAttributeInstance maxHP = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        EntityAttributeInstance damage = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        livingEntity.removeStatusEffect(StatusEffects.RESISTANCE);
        livingEntity.removeStatusEffect(StatusEffects.REGENERATION);

        if(livingEntity instanceof PlayerEntity) {
            EntityAttributeInstance attackSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED);
            attackSpeed.clearModifiers();
        }

        moveSpeed.clearModifiers();
        resistance.clearModifiers();
        maxHP.clearModifiers();
        if(damage != null) {
            damage.clearModifiers();
        }
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
        FOURTH_TRANCENDENCE = new EntityAttributeModifier("Fourth Transcendence", 4D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

        DAMAGE_TRANCENDENCE_FIRST = new EntityAttributeModifier("First Transcendence dmg", 4D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        DAMAGE_TRANCENDENCE_SECOND = new EntityAttributeModifier("Second Transcendence dmg", 8D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        DAMAGE_TRANCENDENCE_THIRD = new EntityAttributeModifier("Third Transcendence dmg", 12D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        DAMAGE_TRANCENDENCE_FOURTH = new EntityAttributeModifier("Fourth Transcendence dmg", 20D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}