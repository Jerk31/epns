
package se2.e.simulator.runtime.petrinet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.pnml.tools.epnk.pnmlcoremodel.Object;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNetDoc;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

import extendedpetrinet.Arc;
import extendedpetrinet.InteractiveInput;
import extendedpetrinet.Place;
import extendedpetrinet.Token;


/**
 * The Class RuntimePetriNetImpl.
 */
public class RuntimePetriNetImpl implements RuntimePetriNet{

	/** The transitions. */
	List<Transition> transitions;
	/** The tokens map. */
	HashMap<Place, List<RuntimeToken>> tokensMap;
	/** The preset. */
	HashMap<Transition, List<Place>> preset;
	/** The postset. */
	HashMap<Transition, List<Place>> postset;

	/**
	 * Initialize tokens in place list.
	 *
	 * @param place the place
	 * @author Ruxandra, Marius
	 */
	private List<TokenMovement> initializeTokensInPlaceList(Place place) {
		List<Token> tempTokens = place.getTokens();
		List<TokenMovement> movements = new ArrayList<TokenMovement>();
		List<RuntimeToken> tempTokensExt = new ArrayList<RuntimeToken>();
		for (Token token : tempTokens) {
			RuntimeToken rt = new RuntimeToken(token.getText());
			if(place.getAnimations()!= null)
				movements.add(new TokenMovement(rt, place.getGeometryLabel().getText(), place.getAnimations().getStructure(), false));
			else
				movements.add(new TokenMovement(rt, place.getGeometryLabel().getText(), null, false));
			tempTokensExt.add(rt);
		}
		tokensMap.put(place, tempTokensExt);
		return movements;
	}

	/**
	 * Initialize presets.
	 *
	 * @param selectedTransition the selected transition
	 * @author Ruxandra, Marius
	 */
	private void initializePresets(Transition selectedTransition) {
		List<Place> places = new ArrayList<Place>();
		for (org.pnml.tools.epnk.pnmlcoremodel.Arc arc : selectedTransition.getIn()) {
			Place place = (Place) arc.getSource();
			places.add(place);
		}
		preset.put(selectedTransition, places);
	}

	/**
	 * Initialize postsets.
	 *
	 * @param selectedTransition the selected transition
	 * @author Ruxandra, Marius
	 */
	private void initializePostsets(Transition selectedTransition) {
		List<Place> places = new ArrayList<Place>();
		for (org.pnml.tools.epnk.pnmlcoremodel.Arc arc : selectedTransition.getOut()) {
			Place place = (Place) arc.getTarget();
			places.add(place);
		}
		postset.put(selectedTransition, places);
	}

	
	/* (non-Javadoc)
	 * @see se2.e.simulator.runtime.petrinet.RuntimePetriNet#printPetri()
	 * @author Ruxandra, Marius
	 */
	public void printPetri() {
		for (Transition transition : transitions) {
			System.out.println(transition.getId() + " :");
			for (Place place : preset.get(transition)) {
				System.out.println("\t" + place.getId());
				for (RuntimeToken token : tokensMap.get(place)) {
					System.out.println("\t\t" + token.isFinished() + " " + token.getLabel());
				}
			}
			System.out.println();
		}
	}

	/**
	 * Instantiates a new runtime petri net.
	 *
	 * @param selectedPetri the selected petri
	 * @author Ruxandra, Marius
	 */
	public RuntimePetriNetImpl() {
		/* create an internal structure so that it will be easy to fire transitions */
		transitions = new ArrayList<Transition>();
		tokensMap = new HashMap<Place, List<RuntimeToken>>();
		preset = new HashMap<Transition, List<Place>>();
		postset = new HashMap<Transition, List<Place>>();
	}
	
	public List<TokenMovement> init(PetriNetDoc selectedPetri){
		List<TokenMovement> tokenMovementsList = new ArrayList<TokenMovement>();
		Iterator<Object> iter = selectedPetri.getNet().get(0).getPage().get(0).getObject().iterator();
		
		while (iter.hasNext()) {
			Object item = iter.next();
			if (item instanceof Transition) {
				/* add transition to list */
				transitions.add((Transition) item);
				/* create preset for each transition */
				this.initializePresets((Transition) item);
				/* create postset for each transition */
				this.initializePostsets((Transition) item);
			}
			if (item instanceof Place) {
				/* create a place->tokens hashmap */
				Place place = (Place) item;
				System.out.println(place.getId());
				tokenMovementsList.addAll(this.initializeTokensInPlaceList(place));
			}
		}
		printPetri();
		return tokenMovementsList;
	}

	/**
	 * Fires a transition.
	 * 
	 * @param selectedTransition the selected transition
	 * @return the list of {@link Token}s that have been moved and their destination {@link Place}.
	 * @author Ruxandra, Marius
	 */
	public List<TokenMovement> fireTransition(Transition selectedTransition) {
		List<Place> selectedTransitionPreset = preset.get(selectedTransition);
		
		HashMap<Place, RuntimeToken> tokensToBeRemoved = new HashMap<Place, RuntimeToken>();
		/* check that every preset has a token */
		for (Place place : selectedTransitionPreset) {
			/* check all tokens to see if there is at least one marked */
			boolean atLeastOneMarked = false;
			for (RuntimeToken token : tokensMap.get(place)) {
				if (token.isFinished()) {
					tokensToBeRemoved.put(place, token);
					atLeastOneMarked = true;
					System.out.println("Marcat " + selectedTransition.getId());
					break;
				}
			}
			if (!atLeastOneMarked)
				return null;
		}
		List<TokenMovement> tokensMovement = new ArrayList<TokenMovement>();
		Set<RuntimeToken> removedTokens = new HashSet<RuntimeToken>();
		
		//matching all the out-bound arcs with identities
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc outArc : selectedTransition.getOut()){
			Arc out = (Arc) outArc;
			Place dest = (Place)out.getTarget();
			//match identities to the ones in the input
			if(out.getIdentity() != null){
				for(org.pnml.tools.epnk.pnmlcoremodel.Arc inArc : selectedTransition.getIn()){
					Arc in = (Arc)inArc;
					if(in.getIdentity() != null && in.getIdentity().getText() == out.getIdentity().getText()){
						Place src = (Place) in.getSource();
						RuntimeToken rt = tokensToBeRemoved.get(src);
						String label = rt.getLabel();
						
						if(removedTokens.contains(rt)){
							rt = new RuntimeToken(label);
						} else {
							tokensMap.get(src).remove(rt);
							removedTokens.add(rt);
						}
						rt.setFinished(false);
						tokensMap.get(dest).add(rt);
						
						
						if(dest.getAnimations()!= null)
							tokensMovement.add(new TokenMovement(rt, dest.getGeometryLabel().getText(), dest.getAnimations().getStructure(), false));
						else
							tokensMovement.add(new TokenMovement(rt, dest.getGeometryLabel().getText(), null, false));	
					}
				}
			}
		}
		
		int count = 0;
		List<Place> listOfPlaces = new ArrayList<Place>(tokensToBeRemoved.keySet());
		//match all out-bound arcs without identities
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc outArc : selectedTransition.getOut()){
			Arc out = (Arc) outArc;
			Place dest = (Place)out.getTarget();
			if(out.getIdentity() == null) {
				System.out.println("fara identitate");
				count = count % listOfPlaces.size();
				Place src = listOfPlaces.get(count);
				count ++;
				RuntimeToken rt = tokensToBeRemoved.get(src);
				String label = rt.getLabel();
				
				if(removedTokens.contains(rt)){
					rt = new RuntimeToken(label);
				} else {
					removedTokens.add(rt);
					tokensMap.get(src).remove(rt);
				}
				rt.setFinished(false);
				tokensMap.get(dest).add(rt);
				
				if(dest.getAnimations() != null) {
					System.out.println("ANIMATION");
					tokensMovement.add(new TokenMovement(rt, dest.getGeometryLabel().getText(), dest.getAnimations().getStructure(),false));
				}
				else {
					tokensMovement.add(new TokenMovement(rt, dest.getGeometryLabel().getText(), null, false));
					System.out.println("NO ANIMATION");
				}
			}
		}
		
		Iterator<Place> it = tokensToBeRemoved.keySet().iterator();
		while(it.hasNext()){
			Place place = it.next();
			RuntimeToken rt = tokensToBeRemoved.get(place);
			if(!removedTokens.contains(rt)){
				tokensMap.get(place).remove(rt);
				tokensMovement.add(new TokenMovement(rt, null, null, true));
			}
		}
		
		return tokensMovement;
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see se2.e.simulator.runtime.petrinet.RuntimePetriNet#fireTransitions()
	 * 
	 * Fires all the transitions possible.
	 * 
	 * @return the list of {@link Token}s that have been moved and their destination {@link Place}.
	 * @author Ruxandra, Marius
	 */
	public List<TokenMovement> fireTransitions() {
		System.out.println("Should be firing transitions");
		List<TokenMovement> tokensMovements = new ArrayList<TokenMovement>();
		for (Transition transition : transitions) {
			System.out.println("Selected transition: " + transition.getId());
			while (true) {
				/* tokenMovement = pair between the token and the place it's moving to */
				List<TokenMovement> movements = fireTransition(transition);
				if (movements == null)
					break;
				else
					tokensMovements.addAll(movements);
			}
		}
		printPetri();
		return tokensMovements;
	}

	/**
	 * Marks a token.
	 * 
	 * @param token the token
	 * @author Ruxandra
	 */
	public void markToken(RuntimeToken token) {
		(token).setFinished(true);
	}
	
	/**
	 * Drop token on place.
	 *
	 * @param geometryLabel the place's GEOMETRY label
	 * @author Ruxandra
	 */
	public Place dropTokenOnPlace(String geometryLabel) {
		Place placeForLabel = null;
		for(Place place : tokensMap.keySet()) {
			boolean isInteractive = false;
			InteractiveInput x = place.getInteractiveInput();
			if(x != null)
				isInteractive = x.isText();
			if(place.getGeometryLabel().getText().equals(geometryLabel) && isInteractive) {
				placeForLabel = place;
				break;
			}		
		}
		if(placeForLabel == null)
			return null;
		RuntimeToken droppedToken = new RuntimeToken(placeForLabel.getAppearance().getText());
		droppedToken.setFinished(true);
		tokensMap.get(placeForLabel).add(droppedToken);
		return placeForLabel;
		
	}

	/* (non-Javadoc)
	 * @see se2.e.simulator.runtime.petrinet.RuntimePetriNet#getInputPlaces()
	 * @author Ruxandra
	 */
	public Set<String> getInputPlaces() {
		Set<String> inputPlacesLabels = new TreeSet<String>();
		for(Place p : tokensMap.keySet()) {
			System.out.println("PLACE " + p.getGeometryLabel().getText() + " " +p.getInteractiveInput());
			if(p.getInteractiveInput() != null && p.getInteractiveInput().isText()){
				inputPlacesLabels.add(p.getGeometryLabel().getText());
			}
		}
		return inputPlacesLabels;
	}
	
	public HashMap<Place, List<RuntimeToken>>getTokenMap(){
		return tokensMap;
	}

}
