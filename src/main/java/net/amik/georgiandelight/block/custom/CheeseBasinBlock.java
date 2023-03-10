package net.amik.georgiandelight.block.custom;

import com.google.common.graph.Network;
import net.amik.georgiandelight.block.entity.CheeseBasinBlockEntity;
import net.amik.georgiandelight.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.stream.Stream;

public class CheeseBasinBlock extends BaseEntityBlock {

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(10, 14, 7, 11, 15, 9),
            Block.box(14, 3, 2, 16, 13, 14),
            Block.box(0, 3, 14, 16, 13, 16),
            Block.box(0, 3, 0, 16, 13, 2),
            Block.box(2, 0, 12, 4, 2, 14),
            Block.box(12, 0, 12, 14, 2, 14),
            Block.box(12, 0, 2, 14, 2, 4),
            Block.box(2, 0, 2, 4, 2, 4),
            Block.box(5, 15, 7, 11, 16, 9),
            Block.box(1, 13, 1, 15, 14, 15),
            Block.box(0, 3, 2, 2, 13, 14),
            Block.box(1, 2, 1, 15, 3, 15),
            Block.box(5, 14, 7, 6, 15, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(7, 14, 5, 9, 15, 6),
            Block.box(2, 3, 0, 14, 13, 2),
            Block.box(14, 3, 0, 16, 13, 16),
            Block.box(0, 3, 0, 2, 13, 16),
            Block.box(12, 0, 12, 14, 2, 14),
            Block.box(12, 0, 2, 14, 2, 4),
            Block.box(2, 0, 2, 4, 2, 4),
            Block.box(2, 0, 12, 4, 2, 14),
            Block.box(7, 15, 5, 9, 16, 11),
            Block.box(1, 13, 1, 15, 14, 15),
            Block.box(2, 3, 14, 14, 13, 16),
            Block.box(1, 2, 1, 15, 3, 15),
            Block.box(7, 14, 10, 9, 15, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_S = SHAPE_N;
    private static final VoxelShape SHAPE_E = SHAPE_W;

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        switch (pState.getValue(FACING)){
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }


    public CheeseBasinBlock(Properties p_49795_) {
        super(p_49795_);
    }
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsmoving) {
        if(pState.getBlock() != pNewState.getBlock()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof CheeseBasinBlockEntity){
                ((CheeseBasinBlockEntity) blockEntity).drops();
            }
        }
    }



    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()){
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof CheeseBasinBlockEntity){
                NetworkHooks.openGui(((ServerPlayer) pPlayer), (CheeseBasinBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container Provider is missing");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new CheeseBasinBlockEntity(p_153215_, p_153216_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pType) {
        return createTickerHelper(pType, ModBlockEntities.CHEESE_BASIN.get(), CheeseBasinBlockEntity::tick);
    }
}
