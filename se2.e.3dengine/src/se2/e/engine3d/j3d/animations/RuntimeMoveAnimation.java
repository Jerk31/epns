package se2.e.engine3d.j3d.animations;

import java.util.logging.Logger;

import javax.media.j3d.Transform3D;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.vecmath.Vector3d;

import se2.e.engine3d.j3d.DynamicBranch;
import se2.e.engine3d.j3d.J3DEngine;
import se2.e.simulator.runtime.petrinet.RuntimeToken;
import se2.e.utilities.PathInterpolator;
import se2.e.utilities.Vector2D;
import se2.e.utilities.Where;
import se2.e.utilities.path.LinearPathInterpolator;
import animations.Move;

/**
 * The Class RuntimeMoveAnimation.
 * 
 * @author cosmin, anders
 */
public class RuntimeMoveAnimation extends RuntimeAnimation<Move> {

	/** The Constant UPDATE_FRAME_COUNT. */
	private static final int UPDATE_FRAME_COUNT = 10;

	private PathInterpolator pathInterpolator;
	private Where currentPosition = null;

	private double distance = 0;

	/**
	 * Instantiates a new runtime move animation.
	 * 
	 * @param targetBranch the target branch
	 * @param animation the animation
	 * @param token the token
	 * @param engine the engine
	 * @author cosmin
	 */
	public RuntimeMoveAnimation(DynamicBranch targetBranch, Move animation, RuntimeToken token, J3DEngine engine) {
		super(targetBranch, animation, token, engine);
	}

	@Override
	public void init() {
		Vector2D[] trackPoints = engine.getGeometryAndAppearanceLoader().getTrackPoints(animation.getGeoTrack());
		this.pathInterpolator = new LinearPathInterpolator(trackPoints);
		currentPosition = this.pathInterpolator.start();
	}

	@Override
	public WakeupCondition onUpdateAnimation() {
		Logger.getAnonymousLogger().info("Updating animation...");
		// Compute new update location
		distance += animation.getSpeed();
		currentPosition = pathInterpolator.findPosition(distance, currentPosition);

		// Move the object to the new position
		Transform3D t = new Transform3D();
		t.setTranslation(new Vector3d(currentPosition.getPosition().getX(), currentPosition.getY(), 0));
		this.targetBranch.getTransformGroup().setTransform(t);

		// Check for finishing conditions
		if (pathInterpolator.getLength() < distance)
		{
			onAnimationFinished();
			return null;
		}
		return new WakeupOnElapsedFrames(UPDATE_FRAME_COUNT);
	}

	@Override
	protected void onAnimationFinished() {
		System.out.println("Animation Finished.");
		engine.getSceneRoot().removeChild(this.targetBranch.getBranchGroup());
		engine.animationFinished(token);
	}

}
