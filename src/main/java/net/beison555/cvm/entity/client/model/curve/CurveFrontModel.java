package net.beison555.cvm.entity.client.model.curve;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CurveFrontModel<T extends CustomVehicleEntity> extends HierarchicalModel<T> {
	private final ModelPart test;

	public CurveFrontModel(ModelPart root) {
		this.test = root.getChild("test");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition test = partdefinition.addOrReplaceChild("test", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = test.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition front = body.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 0).addBox(-15.8F, 1.1891F, -3.2029F, 32.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2F, -5.1891F, -34.2882F));

		PartDefinition cube_r1 = front.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(63, 61).addBox(-15.0F, -1.0F, -1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.2F, 1.9344F, -4.2029F, 1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r2 = front.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 64).addBox(-15.0F, 0.0F, -1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, 1.9344F, -5.2029F, 1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r3 = front.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 61).addBox(-16.0F, -1.0F, -0.5F, 32.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, 4.1891F, -3.7029F, 0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r4 = front.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 55).addBox(-16.0F, -1.0F, -2.0F, 32.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, 1.9344F, -3.2029F, 1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r5 = front.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 45).addBox(-16.0F, -1.0F, -1.0F, 32.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, -0.3487F, -2.0456F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r6 = front.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 32).addBox(-16.0F, -1.0F, -3.0F, 32.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, -3.7344F, 2.3666F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r7 = front.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 17).addBox(-16.0F, -1.0F, -3.0F, 32.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, -5.955F, 7.7278F, 0.3054F, 0.0F, 0.0F));

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