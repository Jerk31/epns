/**
 */
package animations;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Move</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link animations.Move#getSpeed <em>Speed</em>}</li>
 *   <li>{@link animations.Move#getGeoTrack <em>Geo Track</em>}</li>
 * </ul>
 * </p>
 *
 * @see animations.AnimationsPackage#getMove()
 * @model
 * @generated
 */
public interface Move extends Animation {
	/**
	 * Returns the value of the '<em><b>Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Speed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Speed</em>' attribute.
	 * @see #setSpeed(double)
	 * @see animations.AnimationsPackage#getMove_Speed()
	 * @model
	 * @generated
	 */
	double getSpeed();

	/**
	 * Sets the value of the '{@link animations.Move#getSpeed <em>Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Speed</em>' attribute.
	 * @see #getSpeed()
	 * @generated
	 */
	void setSpeed(double value);

	/**
	 * Returns the value of the '<em><b>Geo Track</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Geo Track</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Geo Track</em>' attribute.
	 * @see #setGeoTrack(String)
	 * @see animations.AnimationsPackage#getMove_GeoTrack()
	 * @model
	 * @generated
	 */
	String getGeoTrack();

	/**
	 * Sets the value of the '{@link animations.Move#getGeoTrack <em>Geo Track</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Geo Track</em>' attribute.
	 * @see #getGeoTrack()
	 * @generated
	 */
	void setGeoTrack(String value);

} // Move
