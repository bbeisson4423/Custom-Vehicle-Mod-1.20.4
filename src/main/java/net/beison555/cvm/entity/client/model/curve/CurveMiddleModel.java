package net.beison555.cvm.entity.client.model.curve;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CurveMiddleModel<T extends CustomVehicleEntity> extends HierarchicalModel<T> {
	private final ModelPart test;

	public CurveMiddleModel(ModelPart root) {
		this.test = root.getChild("test");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition test = partdefinition.addOrReplaceChild("test", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = test.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition middle = body.addOrReplaceChild("middle", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition frame = middle.addOrReplaceChild("frame", CubeListBuilder.create().texOffs(0, 33).addBox(15.0019F, 0.9667F, -21.625F, 1.0F, 12.0F, 48.0F, new CubeDeformation(0.0F))
		.texOffs(50, 45).addBox(-15.9981F, 0.9667F, -21.625F, 1.0F, 12.0F, 48.0F, new CubeDeformation(0.0F))
		.texOffs(100, 33).addBox(-14.9981F, 0.9667F, -21.625F, 30.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(100, 89).addBox(-13.7556F, -11.8358F, 4.375F, 27.5F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(100, 50).addBox(-15.6F, 1.0F, 24.0F, 31.2F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0019F, -13.9667F, -2.375F));

		PartDefinition cube_r1 = frame.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 0).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 15).addBox(26.0F, -8.0F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.2556F, -0.8219F, 23.7918F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r2 = frame.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 0).addBox(26.0F, -6.5F, -0.5F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.2556F, -4.8528F, -14.0177F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r3 = frame.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(100, 81).addBox(-13.75F, -0.5F, -3.5F, 27.5F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0056F, -9.8368F, -5.8497F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r4 = frame.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(100, 65).addBox(-13.75F, -0.5F, -3.5F, 27.5F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0056F, -8.6879F, 16.2294F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r5 = frame.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(100, 73).addBox(-13.75F, -0.5F, -3.5F, 27.5F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0056F, -10.7356F, 9.735F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r6 = frame.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(55, 105).addBox(-13.75F, -0.5F, -3.5F, 27.5F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0056F, -11.0326F, 0.9319F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r7 = frame.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(8, 0).addBox(-0.5F, -6.5F, 25.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.377F, -5.3477F, -20.625F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r8 = frame.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(12, 13).addBox(-0.5F, -6.5F, -1.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.3807F, -5.3477F, 5.375F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r9 = frame.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -1.0F, -16.0F, 48.0F, 1.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0019F, 13.9667F, 2.375F, 0.0F, 1.5708F, 0.0F));

		PartDefinition seat = middle.addOrReplaceChild("seat", CubeListBuilder.create().texOffs(110, 105).addBox(-8.0F, -3.0F, -14.0F, 15.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r10 = seat.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 93).addBox(-8.0F, -2.0F, 1.0F, 15.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
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