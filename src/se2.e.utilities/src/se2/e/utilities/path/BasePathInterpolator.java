package se2.e.utilities.path;

import java.util.ArrayList;
import java.util.List;

import se2.e.utilities.PathInterpolator;
import se2.e.utilities.Vector2D;
import se2.e.utilities.Where;

/**
 * Base class for creating Path interpolators based on distinct steps.
 * The most obvious path interpolator using this class is
 * {@link se2.e.utilities.path.LinePathInterpolator}, but also
 * {@link se2.e.utilities.path.BezierPathInterpolatort} uses this class. 
 * 
 * @author Anders Kalhauge
 *
 */
public abstract class BasePathInterpolator implements PathInterpolator {
	protected List<Step> steps = new ArrayList<Step>();
	protected double length = 0.0;
	
	protected void addStep(Vector2D start, Vector2D end) {
		Step step = new Step(length, start, end);
		steps.add(step);
		length += step.getLength();
		//steps.add(step);
		}
	
	@Override
	public double getLength() { return length; }

	@Override
	public Where findPosition(double distance) {
		for (Step step : steps) {
			if (!step.isWithin(distance)) continue;
			return step.findWhere(distance);
			}
		return steps.get(steps.size()-1).findWhere(length);
		}

	@Override
	public List<Vector2D> getIntermediatePoints() {
		List<Vector2D> points = new ArrayList<Vector2D>();
		for (double distance = 0.0; distance <= length; distance += 1.0) {
			for (Step step : steps) {
				if (!step.isWithin(distance)) continue;
				points.add(step.findPosition(distance));
				}
			}
		return points;
		}

	@Override
	public Where start() { return findPosition(0); }

	protected class Step {
		private double baseDistance;
		private Vector2D originPoint;
		private Vector2D next;
		
		public Step(double baseDistance, Vector2D originPoint, Vector2D endPoint) {
			this.baseDistance = baseDistance;
			this.originPoint = originPoint;
			this.next = Vector2D.subtract(endPoint, originPoint);
			}
		
		public boolean isWithin(double distance) {
			if (distance < this.baseDistance) return false;
			if (next.longerThan(distance - this.baseDistance)) return true;
			return false;
			}
		
		public Vector2D findPosition(double distance) {
			double partial = distance - baseDistance;
			return new Vector2D(originPoint).deltaAdd(partial, next);
			}
		
		public Where findWhere(double distance) {
			double partial = distance - baseDistance;
			Where position = new Where(0, 0, 0);
			position.getPosition().setX(originPoint.getX() + next.deltaX(partial));
			position.getPosition().setY(originPoint.getY() + next.deltaY(partial));
			position.getOrientation().setAngle(next.getAngle());
			return position;
			}
		
		public double getLength() { return next.getLength(); }

		@Override
		public String toString() {
			return "Step [baseDistance=" + baseDistance + ", originPoint=" + originPoint + ", next=" + next + "]";
			}
		}

	}
