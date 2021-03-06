package se2.e.engine3d;

import se2.e.simulator.runtime.petrinet.RuntimeToken;

/**
 * The listener interface for receiving {@link Engine3D} events. The class that is interested in processing an
 * {@link Engine3D} event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>setEngine3DListener<code> method. When the Engine3D event occurs, 
 * that object's appropriate method is invoked.
 * 
 * @see AnimationProgressEvent
 * @author cosmin
 */
public interface Engine3DListener {

	/**
	 * Method run when an animation is finished.
	 * 
	 * @param token the token
	 */
	public void onAnimationFinished(RuntimeToken token);

	/**
	 * Method run when an user interaction with an input place has been detected.
	 * 
	 * @param label the label of the element the user interacted with
	 */
	public void onUserInteraction(String label);

	/**
	 * Method run when the simulation should start, usually as an effect of the user clicking on a Start button.
	 * Expected behavior includes creating of dynamic objects (e.g. {@link Token} representations) and starting initial
	 * animations.
	 */
	public void onStartSimulation();

	/**
	 * Method run when the simulation should stop, usually as an effect of the user clicking on a Stop button. Expected
	 * behavior includes reseting any running simulation, so that a subsequent call to onStartSimulation() works as
	 * expected.
	 */
	public void onStopSimulation();
}
