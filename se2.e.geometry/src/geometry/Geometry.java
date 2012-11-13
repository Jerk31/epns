/**
 */
package geometry;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Geometry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link geometry.Geometry#getGeoObjects <em>Geo Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @see geometry.GeometryPackage#getGeometry()
 * @model
 * @generated
 */
public interface Geometry extends EObject {
	/**
	 * Returns the value of the '<em><b>Geo Objects</b></em>' containment reference list.
	 * The list contents are of type {@link geometry.GeometryObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Geo Objects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Geo Objects</em>' containment reference list.
	 * @see geometry.GeometryPackage#getGeometry_GeoObjects()
	 * @model containment="true"
	 * @generated
	 */
	EList<GeometryObject> getGeoObjects();

} // Geometry