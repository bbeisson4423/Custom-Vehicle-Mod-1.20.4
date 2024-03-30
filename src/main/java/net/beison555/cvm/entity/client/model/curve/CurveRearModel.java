package net.beison555.cvm.entity.client.model.curve;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CurveRearModel<T extends CustomVehicleEntity> extends HierarchicalModel<T> {
	private final ModelPart test;

	public CurveRearModel(ModelPart root) {
		this.test = root.getChild("test");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition test = partdefinition.addOrReplaceChild("test", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = test.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rear = body.addOrReplaceChild("rear", CubeListBuilder.create().texOffs(0, 12).addBox(-14.6709F, 4.3686F, -4.9452F, 31.9F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(-14.6209F, -0.2181F, 2.2723F, 31.75F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-14.6709F, -7.6314F, -4.9452F, 31.9F, 12.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 34).addBox(-14.5709F, -0.2314F, -3.9452F, 0.0F, 4.7F, 6.5F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(17.0291F, -0.2314F, -3.9452F, 0.0F, 4.7F, 6.5F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-14.5709F, -4.9314F, -3.9452F, 0.0F, 4.7F, 2.5F, new CubeDeformation(0.0F))
		.texOffs(0, 9).addBox(17.0291F, -4.9314F, -3.9452F, 0.0F, 4.7F, 2.5F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(17.0291F, -1.9314F, -1.4452F, 0.0F, 1.7F, 2.5F, new CubeDeformation(0.0F))
		.texOffs(0, 5).addBox(-14.5699F, -1.9314F, -1.4452F, 0.0F, 1.7F, 2.5F, new CubeDeformation(0.0F)), PartPose.offset(-1.2541F, -5.3436F, 28.9452F));

		PartDefinition cube_r1 = rear.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 41).addBox(1.35F, -0.5F, -2.0F, 2.7F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0291F, -2.563F, 0.6646F, -0.829F, 0.0F, 0.0F));

		PartDefinition cube_r2 = rear.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(38, 42).addBox(-15.85F, 0.0F, -1.0F, 2.7F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2791F, -3.6381F, 0.3577F, -0.829F, 0.0F, 0.0F));

		PartDefinition cube_r3 = rear.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.85F, -1.0F, -4.5F, 31.7F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2791F, -3.6381F, -1.6423F, -0.829F, 0.0F, 0.0F));

		PartDefinition cube_r4 = rear.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 38).addBox(-30.85F, 4.0F, -1.5F, 31.75F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.2291F, 0.9128F, 6.1041F, -0.6545F, 0.0F, 0.0F));

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