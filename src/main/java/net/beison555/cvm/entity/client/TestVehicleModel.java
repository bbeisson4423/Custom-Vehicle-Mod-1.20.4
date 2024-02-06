package net.beison555.cvm.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.beison555.cvm.entity.custom.TestVehicleEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class TestVehicleModel<T extends TestVehicleEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("cvm", "car_basic_texture"), "main");
	private final ModelPart root;
	private final ModelPart bone;
	private final ModelPart frontLeftTire;
	private final ModelPart frontRightTire;
	private final ModelPart rearRightTire;
	private final ModelPart rearLeftTire;
	private final ModelPart seat;

	public TestVehicleModel(ModelPart root) {
		this.root = root;
		this.bone = root.getChild("bone");
		this.frontLeftTire = root.getChild("frontLeftTire");
		this.frontRightTire = root.getChild("frontRightTire");
		this.rearRightTire = root.getChild("rearRightTire");
		this.rearLeftTire = root.getChild("rearLeftTire");
		this.seat = root.getChild("seat");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -40.0F, 2.0F, 2.0F, 80.0F, new CubeDeformation(0.0F)).texOffs(0, 76)
				.addBox(-17.0F, -1.0F, -28.0F, 34.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 72).addBox(-17.0F, -1.0F, 26.0F, 34.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 15.0F, 0.0F));
		PartDefinition frontLeftTire = partdefinition.addOrReplaceChild("frontLeftTire", CubeListBuilder.create().texOffs(92, 158).addBox(-4.0F, -2.0F, -10.0F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(22.0F, 13.0F, -27.0F));
		PartDefinition cube_r1 = frontLeftTire.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(92, 134).addBox(-2.0F, -16.7821F, -3.8771F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -2.7489F, 0.0F, 0.0F));
		PartDefinition cube_r2 = frontLeftTire.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 154).addBox(-2.0F, -13.3137F, 1.3137F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -2.3562F, 0.0F, 0.0F));
		PartDefinition cube_r3 = frontLeftTire.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 106).addBox(-2.0F, -8.1229F, 4.7821F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.9635F, 0.0F, 0.0F));
		PartDefinition cube_r4 = frontLeftTire.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 154).addBox(-2.0F, -2.0F, 6.0F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cube_r5 = frontLeftTire.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(56, 106).addBox(-2.0F, 4.1229F, 4.7821F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.1781F, 0.0F, 0.0F));
		PartDefinition cube_r6 = frontLeftTire.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(128, 154).addBox(-2.0F, 9.3137F, 1.3137F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r7 = frontLeftTire.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(156, 0).addBox(-2.0F, 12.7821F, -3.8771F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -0.3927F, 0.0F, 0.0F));
		PartDefinition frontRightTire = partdefinition.addOrReplaceChild("frontRightTire", CubeListBuilder.create().texOffs(156, 48).addBox(-4.0F, -2.0F, -10.0F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(-20.0F, 13.0F, -27.0F));
		PartDefinition cube_r8 = frontRightTire.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(128, 106).addBox(-2.0F, -16.7821F, -3.8771F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -2.7489F, 0.0F, 0.0F));
		PartDefinition cube_r9 = frontRightTire.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 130).addBox(-2.0F, -13.3137F, 1.3137F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -2.3562F, 0.0F, 0.0F));
		PartDefinition cube_r10 = frontRightTire.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(84, 48).addBox(-2.0F, -8.1229F, 4.7821F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.9635F, 0.0F, 0.0F));
		PartDefinition cube_r11 = frontRightTire.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(56, 130).addBox(-2.0F, -2.0F, 6.0F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cube_r12 = frontRightTire.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(92, 86).addBox(-2.0F, 4.1229F, 4.7821F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.1781F, 0.0F, 0.0F));
		PartDefinition cube_r13 = frontRightTire.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(128, 130).addBox(-2.0F, 9.3137F, 1.3137F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r14 = frontRightTire.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(156, 24).addBox(-2.0F, 12.7821F, -3.8771F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -0.3927F, 0.0F, 0.0F));
		PartDefinition rearRightTire = partdefinition.addOrReplaceChild("rearRightTire", CubeListBuilder.create().texOffs(120, 28).addBox(-4.0F, -2.0F, -10.0F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(-20.0F, 13.0F, 27.0F));
		PartDefinition cube_r15 = rearRightTire.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -16.7821F, -3.8771F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -2.7489F, 0.0F, 0.0F));
		PartDefinition cube_r16 = rearRightTire.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(164, 120).addBox(-2.0F, -13.3137F, 1.3137F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -2.3562F, 0.0F, 0.0F));
		PartDefinition cube_r17 = rearRightTire.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(92, 110).addBox(-2.0F, -8.1229F, 4.7821F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.9635F, 0.0F, 0.0F));
		PartDefinition cube_r18 = rearRightTire.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(128, 82).addBox(-2.0F, -2.0F, 6.0F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cube_r19 = rearRightTire.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(120, 4).addBox(-2.0F, 4.1229F, 4.7821F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.1781F, 0.0F, 0.0F));
		PartDefinition cube_r20 = rearRightTire.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(164, 144).addBox(-2.0F, 9.3137F, 1.3137F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r21 = rearRightTire.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 82).addBox(-2.0F, 12.7821F, -3.8771F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -0.3927F, 0.0F, 0.0F));
		PartDefinition rearLeftTire = partdefinition.addOrReplaceChild("rearLeftTire", CubeListBuilder.create().texOffs(120, 52).addBox(-4.0F, -2.0F, -10.0F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(22.0F, 12.0F, 26.0F));
		PartDefinition cube_r22 = rearLeftTire.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -16.7821F, -3.8771F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -2.7489F, 0.0F, 0.0F));
		PartDefinition cube_r23 = rearLeftTire.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(164, 72).addBox(-2.0F, -13.3137F, 1.3137F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -2.3562F, 0.0F, 0.0F));
		PartDefinition cube_r24 = rearLeftTire.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(56, 82).addBox(-2.0F, -8.1229F, 4.7821F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.9635F, 0.0F, 0.0F));
		PartDefinition cube_r25 = rearLeftTire.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(84, 0).addBox(-2.0F, -2.0F, 6.0F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cube_r26 = rearLeftTire.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(84, 24).addBox(-2.0F, 4.1229F, 4.7821F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -1.1781F, 0.0F, 0.0F));
		PartDefinition cube_r27 = rearLeftTire.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(164, 96).addBox(-2.0F, 9.3137F, 1.3137F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r28 = rearLeftTire.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 24).addBox(-2.0F, 12.7821F, -3.8771F, 8.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 0.0F, -0.3927F, 0.0F, 0.0F));
		PartDefinition seat = partdefinition.addOrReplaceChild("seat", CubeListBuilder.create().texOffs(0, 178).addBox(-7.0F, 2.1658F, -9.721F, 14.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 9.8342F, 7.721F));
		PartDefinition cube_r29 = seat.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(170, 168).addBox(-6.0F, 6.0F, 6.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 6.1658F, -7.721F, 1.1781F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 256, 256);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLeftTire.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontRightTire.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rearRightTire.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rearLeftTire.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		seat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}

	@Override
	public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

	}
}