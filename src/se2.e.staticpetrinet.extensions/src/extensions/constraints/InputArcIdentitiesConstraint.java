package extensions.constraints;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

import extendedpetrinet.Arc;

/**
 * InputArcIdentitiesConstraint class
 * 
 * @author Juan, María and Pablo.
 */
public class InputArcIdentitiesConstraint extends AbstractModelConstraint {

	@Override
	public IStatus validate(IValidationContext vc) {
		EObject object = vc.getTarget();
		if (object instanceof Transition) {
			Transition transition = (Transition) object;
			EList<org.pnml.tools.epnk.pnmlcoremodel.Arc> in = transition.getIn();
			Set<Integer> idIn = new HashSet<Integer>();
			for(org.pnml.tools.epnk.pnmlcoremodel.Arc a : in){
				if(a instanceof Arc){
					Arc arc = (Arc)a;
					if(arc.getIdentity()!=null && !arc.getIdentity().equals("")){
						if(idIn.contains(new Integer(((Arc)a).getIdentity().getText())))
							return vc.createFailureStatus(new Object[] {transition});
						idIn.add(new Integer(((Arc)a).getIdentity().getText()));
					}
				}
			}	
		}
		return vc.createSuccessStatus();
	}
}
