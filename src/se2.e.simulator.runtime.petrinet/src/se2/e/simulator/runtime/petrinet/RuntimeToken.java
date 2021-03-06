package se2.e.simulator.runtime.petrinet;

/**
 * The Class RuntimeToken.
 * 
 * @author Ruxandra, Marius
 */
public class RuntimeToken {

	/** The label. */
	private String label;

	/** Whether the animation is finished. */
	private boolean finished;

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return "RuntimeToken[label=" + label + "]";
	}

	/**
	 * Sets the label.
	 * 
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Instantiates a new runtime token.
	 * 
	 * @param label the label
	 */
	public RuntimeToken(String label) {
		this.finished = false;
		this.label = label;
	}

	/**
	 * Checks if is finished.
	 * 
	 * @return true, if is finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Sets the finished.
	 * 
	 * @param finished the new finished
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
