package net.beison555.cvm.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class TestTireModel<T extends CustomVehicleEntity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart test;

	public TestTireModel(ModelPart root) {
		this.root = root;
		this.test = root.getChild("test");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition test = partdefinition.addOrReplaceChild("test", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = test.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition frontLeftTire = body.addOrReplaceChild("frontLeftTire", CubeListBuilder.create().texOffs(0, 73).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0546F, -0.5F, -24.0546F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r1 = frontLeftTire.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 56).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.7489F, 0.0F));

		PartDefinition cube_r2 = frontLeftTire.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 59).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r3 = frontLeftTire.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 61).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.9635F, 0.0F));

		PartDefinition cube_r4 = frontLeftTire.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 63).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r5 = frontLeftTire.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 66).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r6 = frontLeftTire.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(28, 68).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r7 = frontLeftTire.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(56, 70).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition frontRightTire = body.addOrReplaceChild("frontRightTire", CubeListBuilder.create().texOffs(56, 49).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9454F, -0.5F, -24.0546F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r8 = frontRightTire.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(28, 54).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.7489F, 0.0F));

		PartDefinition cube_r9 = frontRightTire.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(54, 28).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r10 = frontRightTire.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(56, 0).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.9635F, 0.0F));

		PartDefinition cube_r11 = frontRightTire.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(56, 7).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r12 = frontRightTire.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(56, 14).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r13 = frontRightTire.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(56, 35).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r14 = frontRightTire.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(56, 42).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition rearLeftTire = body.addOrReplaceChild("rearLeftTire", CubeListBuilder.create().texOffs(52, 21).addBox(-6.0F, -2.5F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.5546F, -0.5F, 23.9454F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r15 = rearLeftTire.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 31).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -2.7489F, 0.0F));

		PartDefinition cube_r16 = rearLeftTire.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(28, 33).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r17 = rearLeftTire.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 38).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -1.9635F, 0.0F));

		PartDefinition cube_r18 = rearLeftTire.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(28, 40).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r19 = rearLeftTire.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 45).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r20 = rearLeftTire.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(28, 47).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r21 = rearLeftTire.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 52).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition rearRightTire = body.addOrReplaceChild("rearRightTire", CubeListBuilder.create().texOffs(28, 14).addBox(-6.0F, -2.5F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.4454F, -0.5F, 23.9454F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r22 = rearRightTire.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -2.7489F, 0.0F));

		PartDefinition cube_r23 = rearRightTire.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 7).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r24 = rearRightTire.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 14).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -1.9635F, 0.0F));

		PartDefinition cube_r25 = rearRightTire.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 21).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r26 = rearRightTire.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(26, 26).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r27 = rearRightTire.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(28, 0).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r28 = rearRightTire.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(28, 7).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		test.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return null;
	}
}