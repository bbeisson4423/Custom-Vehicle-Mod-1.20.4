package net.beison555.cvm.entity.client.model.test;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class TestMiddleModel<T extends CustomVehicleEntity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart test;

	public TestMiddleModel(ModelPart root) {
		this.root = root;
		this.test = root.getChild("test");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition test = partdefinition.addOrReplaceChild("test", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = test.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition middle = body.addOrReplaceChild("middle", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition frame = middle.addOrReplaceChild("frame", CubeListBuilder.create().texOffs(50, 45).addBox(15.0F, -13.0F, -24.0F, 1.0F, 12.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(0, 33).addBox(-16.0F, -13.0F, -24.0F, 1.0F, 12.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(100, 55).addBox(-15.0F, -13.0F, -24.0F, 30.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(18, 0).addBox(15.0F, -27.0F, 23.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 17).addBox(-16.0F, -27.0F, 23.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 0).addBox(15.0F, -21.0F, -24.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(21, 14).addBox(-16.0F, -21.0F, -24.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(13, 14).addBox(-16.0F, -27.0F, 3.0F, 1.0F, 14.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(8, 0).addBox(15.0F, -27.0F, 3.0F, 1.0F, 14.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(100, 33).addBox(-16.0F, -28.0F, 3.0F, 32.0F, 1.0F, 21.0F, new CubeDeformation(0.0F))
				.texOffs(50, 33).addBox(-15.0F, -21.0F, -24.0F, 30.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = frame.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -13.5F, -0.5F, 1.0F, 28.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 0).addBox(30.5F, -13.5F, -0.5F, 1.0F, 28.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.5F, -24.1483F, -10.5756F, 1.8326F, 0.0F, 0.0F));

		PartDefinition cube_r2 = frame.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -1.0F, -16.0F, 48.0F, 1.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition seat = middle.addOrReplaceChild("seat", CubeListBuilder.create().texOffs(100, 72).addBox(-8.0F, -3.0F, -14.0F, 15.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r3 = seat.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 93).addBox(-8.0F, -2.0F, 1.0F, 15.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

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