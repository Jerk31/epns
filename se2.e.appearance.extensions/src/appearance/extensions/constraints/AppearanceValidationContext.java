/* File from the ePNK project */
package appearance.extensions.constraints;

import org.eclipse.emf.validation.model.IClientSelector;

public class AppearanceValidationContext implements IClientSelector {

	public boolean selects(Object object) {
		return true;
	}
}
