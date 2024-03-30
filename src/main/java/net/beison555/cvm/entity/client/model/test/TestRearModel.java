package net.beison555.cvm.entity.client.model.test;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class TestRearModel<T extends CustomVehicleEntity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart test;

	public TestRearModel(ModelPart root) {
		this.root = root;
		this.test = root.getChild("test");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition test = partdefinition.addOrReplaceChild("test", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = test.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 48.0F));

		PartDefinition rear = body.addOrReplaceChild("rear", CubeListBuilder.create().texOffs(0, 0).addBox(-19.875F, 3.875F, -11.0F, 32.0F, 13.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(8, 28).addBox(-19.875F, -4.125F, 1.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 0).addBox(11.125F, -4.125F, 1.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 26).addBox(-18.875F, -4.125F, 1.0F, 30.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(7.0F, 7.0F, 2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-17.0F, 7.0F, 2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.875F, -16.875F, -13.0F));

		PartDefinition cube_r1 = rear.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-0.5F, -7.5F, -0.5F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 28).addBox(30.5F, -7.5F, -0.5F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.375F, -7.1446F, -4.8835F, 1.0908F, 0.0F, 0.0F));

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