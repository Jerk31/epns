/**
 */
package se2.e.configurator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see se2.e.configurator.ConfiguratorFactory
 * @model kind="package"
 * @generated
 */
public interface ConfiguratorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "configurator";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://configurator/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "configurator";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConfiguratorPackage eINSTANCE = se2.e.configurator.impl.ConfiguratorPackageImpl.init();

	/**
	 * The meta object id for the '{@link se2.e.configurator.impl.ConfiguratorImpl <em>Configurator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see se2.e.configurator.impl.ConfiguratorImpl
	 * @see se2.e.configurator.impl.ConfiguratorPackageImpl#getConfigurator()
	 * @generated
	 */
	int CONFIGURATOR = 0;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATOR__GEOMETRY = 0;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATOR__APPEARANCE = 1;

	/**
	 * The feature id for the '<em><b>Petrinet</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATOR__PETRINET = 2;

	/**
	 * The feature id for the '<em><b>Default Track Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATOR__DEFAULT_TRACK_WIDTH = 3;

	/**
	 * The number of structural features of the '<em>Configurator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATOR_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link se2.e.configurator.Configurator <em>Configurator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configurator</em>'.
	 * @see se2.e.configurator.Configurator
	 * @generated
	 */
	EClass getConfigurator();

	/**
	 * Returns the meta object for the reference '{@link se2.e.configurator.Configurator#getGeometry <em>Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Geometry</em>'.
	 * @see se2.e.configurator.Configurator#getGeometry()
	 * @see #getConfigurator()
	 * @generated
	 */
	EReference getConfigurator_Geometry();

	/**
	 * Returns the meta object for the reference '{@link se2.e.configurator.Configurator#getAppearance <em>Appearance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Appearance</em>'.
	 * @see se2.e.configurator.Configurator#getAppearance()
	 * @see #getConfigurator()
	 * @generated
	 */
	EReference getConfigurator_Appearance();

	/**
	 * Returns the meta object for the reference '{@link se2.e.configurator.Configurator#getPetrinet <em>Petrinet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Petrinet</em>'.
	 * @see se2.e.configurator.Configurator#getPetrinet()
	 * @see #getConfigurator()
	 * @generated
	 */
	EReference getConfigurator_Petrinet();

	/**
	 * Returns the meta object for the attribute '{@link se2.e.configurator.Configurator#getDefaultTrackWidth <em>Default Track Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Track Width</em>'.
	 * @see se2.e.configurator.Configurator#getDefaultTrackWidth()
	 * @see #getConfigurator()
	 * @generated
	 */
	EAttribute getConfigurator_DefaultTrackWidth();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConfiguratorFactory getConfiguratorFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link se2.e.configurator.impl.ConfiguratorImpl <em>Configurator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see se2.e.configurator.impl.ConfiguratorImpl
		 * @see se2.e.configurator.impl.ConfiguratorPackageImpl#getConfigurator()
		 * @generated
		 */
		EClass CONFIGURATOR = eINSTANCE.getConfigurator();

		/**
		 * The meta object literal for the '<em><b>Geometry</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATOR__GEOMETRY = eINSTANCE.getConfigurator_Geometry();

		/**
		 * The meta object literal for the '<em><b>Appearance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATOR__APPEARANCE = eINSTANCE.getConfigurator_Appearance();

		/**
		 * The meta object literal for the '<em><b>Petrinet</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATOR__PETRINET = eINSTANCE.getConfigurator_Petrinet();

		/**
		 * The meta object literal for the '<em><b>Default Track Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIGURATOR__DEFAULT_TRACK_WIDTH = eINSTANCE.getConfigurator_DefaultTrackWidth();

	}

} //ConfiguratorPackage
