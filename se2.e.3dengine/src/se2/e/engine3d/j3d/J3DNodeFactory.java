package se2.e.engine3d.j3d;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import se2.e.engine3d.GeometryAndAppearanceLoader;
import se2.e.geometry.GeometryObject;
import se2.e.geometry.Position;
import se2.e.geometry.Track;
import se2.e.utilities.Vector2D;
import appearance.AppearanceInfo;
import appearance.Model3D;
import appearance.Object3D;
import appearance.Shape3D;
import appearance.Surface;
import appearance.SurfaceColor;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;

/**
 * A factory for creating nodes containing the representation for various types of objects in Java3D.
 * 
 * @author ruxy, marius, cosmin
 */
public class J3DNodeFactory {

	/** The Constant DRAWING_PLANE_Z. */
	private static final double DRAWING_PLANE_Z = 0d;

	/** The loader. */
	private GeometryAndAppearanceLoader loader;

	/** The canvas. */
	private Canvas3D canvas;

	/** The engine. */
	private J3DEngine engine;

	/**
	 * Instantiates a new geometry node factory.
	 * 
	 * @param loader the geometry and appearance loader
	 * @param engine the engine
	 * @param canvas the canvas
	 */
	public J3DNodeFactory(GeometryAndAppearanceLoader loader, J3DEngine engine, Canvas3D canvas) {
		super();
		this.loader = loader;
		this.canvas = canvas;
		this.engine = engine;
	}

	/**
	 * Builds the Java3D Appearance based on the given Appearance Info.
	 * 
	 * @param appearanceInfo the information of how the surface should look like
	 * @return the appearance
	 */
	private Appearance buildSurfaceAppearance(Surface appearanceInfo) {
		// TODO: Fill in
		return null;
	}

	/**
	 * Gets a Transform Group containing the representation for a specific appearance label (e.g. 'cube', 'red_train').
	 * If a TranformGroup is provided, the nodes needed for representation should be added as children, otherwise a new
	 * TransformGroup should be created.
	 * 
	 * @param appearanceLabel the appearance label
	 * @param destinationTransformGroup the destination transform group, or null if a new Transform Group should be
	 * created
	 * @return the node containing the representation for the appearance, or null, if there is no appearance with the
	 * given label.
	 * 
	 */
	private TransformGroup getTransformGroupForAppearance(String appearanceLabel,
			TransformGroup destinationTransformGroup) {
		if (destinationTransformGroup == null)
			destinationTransformGroup = new TransformGroup();

		// TODO: To fill in...
		return destinationTransformGroup;
	}

	/**
	 * Gets a Transform Group containing the representation for a specific geometry label (e.g. 'track1'), corresponding
	 * to a {@link Track} object.
	 * 
	 * @param geometryLabel the geometry label
	 * @return the node containing the representation for the geometry, or null, if there is no geometry with the given
	 * label or it's not a {@link Track}.
	 * 
	 * @author cosmin, marius
	 */
	public Node getGeometryTransformGroup(String geometryLabel) {

		// Get the track points
		Vector2D[] trackPoints = loader.getTrackPoints(geometryLabel);
		if (trackPoints == null)
			return null;

		Track track = loader.getTrackFromLabel(geometryLabel);
		Logger.getAnonymousLogger().info("Generating " + geometryLabel + " for: " + trackPoints);

		// Prepare the points of the tracks
		LineArray lineArr = new LineArray((trackPoints.length - 1) * 2, LineArray.COORDINATES);
		lineArr.setCoordinate(0, new Point3d(trackPoints[0].getX(), trackPoints[0].getY(), DRAWING_PLANE_Z));
		for (int i = 1; i < trackPoints.length - 1; i++) {
			// Add each point twice, as it will be both an endpoint for a line
			// and a startpoint for the next one
			lineArr.setCoordinate(2 * i - 1, new Point3d(trackPoints[i].getX(), trackPoints[i].getY(), DRAWING_PLANE_Z));
			lineArr.setCoordinate(2 * i, new Point3d(trackPoints[i].getX(), trackPoints[i].getY(), DRAWING_PLANE_Z));
		}
		lineArr.setCoordinate(2 * (trackPoints.length - 1) - 1, new Point3d(trackPoints[trackPoints.length - 1].getX(),
				trackPoints[trackPoints.length - 1].getY(), DRAWING_PLANE_Z));

		// Add the line to the track group
		TransformGroup g = new TransformGroup();

		// set line width
		LineAttributes la = new LineAttributes();
		la.setLineWidth(5.0f);
		Appearance app = new Appearance();
		app.setLineAttributes(la);
		// set line color
		ColoringAttributes ca = new ColoringAttributes();

		boolean colorSet = false;
		if (track.getAppearanceLabel() != null) {
			AppearanceInfo color = loader.getAppearanceInfo(track.getAppearanceLabel());
			if (color != null && color instanceof SurfaceColor) {
				colorSet = true;
				SurfaceColor sc = (SurfaceColor) color;
				String[] rgb = sc.getColorCode().split(",");
				ca.setColor(new Color3f(Float.parseFloat(rgb[0]) / 255, Float.parseFloat(rgb[1]) / 255, Float
						.parseFloat(rgb[2]) / 255));
				app.setColoringAttributes(ca);
			}
		}
		if (colorSet == false) {
			ca.setColor(new Color3f(0.5f, 0.5f, 0.5f));
			app.setColoringAttributes(ca);
		}
		// TODO: The buildAppearance() method should be used...

		// System.out.println(new java.io.File(".").getAbsolutePath());
		/**
		 * the texture file and folder has to be in eclipse's home directory (where eclipse.exe is)
		 **/
		// Texture tex = new
		// TextureLoader("graphics/textures/texture-green.png",
		// engine).getTexture();
		// app.setTexture(tex);
		// TextureAttributes texAttr = new TextureAttributes();
		// texAttr.setTextureMode(TextureAttributes.REPLACE);
		// app.setTextureAttributes(texAttr);

		g.addChild(new javax.media.j3d.Shape3D(lineArr, app));
		return g;
	}

	/**
	 * Returns a dynamic branch that contains the representation for a particular {@link GeometryObject} referenced by
	 * the geometryLabel. The {@link AppearanceInfo} is obtained from the {@link GeometryObject}. The last parameter
	 * defines whether the place is an interactive input, so the {@link TransformGroup} has associated a
	 * InteractiveInput Listener.
	 * 
	 * @param geometryLabel the geometry label
	 * @param interactiveInput whether it is an interactive input place
	 * @return the geometry branch
	 * @author Marius
	 */
	public DynamicBranch getGeometryBranch(String geometryLabel, boolean interactiveInput) {
		// TODO: Fill in...
		return null;
	}

	/**
	 * Returns a dynamic branch that contains the representation for a particular object according to the
	 * {@link AppearanceInfo} corresponding to the appearanceLabel, but placed at the place defined by the
	 * {@link GeometryObject} referenced by the geometryLabel. If a {@link DynamicBranch} is given as a parameter, its
	 * transform group is used as a parent for the nodes of the representations. Otherwise, it is the responsibility of
	 * this method to create the Java3D nodes and connect the {@link TransformGroup} to the {@link BranchGroup}, before
	 * putting them in the {@link DynamicBranch}.
	 * <p>
	 * If the geometry is a Track, the representation is placed at the beginning of the track. If the geometry is a
	 * SimplePosition, the representation is placed at the corresponding position.
	 * </p>
	 * <p>
	 * If the destination DynamicBranch is not provided, the generated Dynamic Branch does not have an interactive input
	 * listener.
	 * </p>
	 * 
	 * @param appearanceLabel the appearance label
	 * @param geometryLabel the geometry label
	 * @param destinationBranch the branch
	 * @return the geometry branch
	 * @author Marius
	 */
	public DynamicBranch getGeometryBranch(String appearanceLabel, String geometryLabel, DynamicBranch destinationBranch) {

		// TODO: Change to behave according to the JavaDoc
		// TODO: Change to use getTransformGrupForAppearance and buildAppearance
		AppearanceInfo appearanceInfo = this.loader.getAppearanceInfo(appearanceLabel);
		Position position = this.loader.getSimplePositionObject(geometryLabel).getPosition();
		BranchGroup branchGroup = new BranchGroup();
		TransformGroup tg = null; // TODO: If needed, get the transform group from the destinationBranch
		System.out.println("Appearance: " + appearanceLabel + "     " + appearanceInfo);
		System.out.println("Position: " + position);
		// switch - case with strings only in JRE 7. for compatibility issues,
		// I'm using if - else
		if (appearanceInfo instanceof appearance.Shape3D) {
			Object3D type = ((appearance.Shape3D) appearanceInfo).getType();
			System.out.println("TYPE::::" + type);
			if (type == Object3D.CUBE) {
				ColorCube model = new ColorCube(0.5f);
				Transform3D trans3d = new Transform3D();
				trans3d.setTranslation(new Vector3d(position.getX(), position.getY(), DRAWING_PLANE_Z));
				tg = new TransformGroup(trans3d);
				tg.addChild(model);
				tg.setPickable(true);
				branchGroup.addChild(tg);
			} else if (type == Object3D.SPHERE) {
				System.out.println("SFERA!!!!!!!");
				Appearance app = new Appearance();
				// Texture tex = new
				// TextureLoader("graphics/textures/earth.png",
				// engine).getTexture();
				// //FIXME: may need to be changed from / to \ depending on the
				// operating system
				// app.setTexture(tex);
				// TextureAttributes texAttr = new TextureAttributes();
				// texAttr.setTextureMode(TextureAttributes.MODULATE);
				// app.setTextureAttributes(texAttr);
				// Sphere model = new Sphere(0.86f,
				// Sphere.GENERATE_TEXTURE_COORDS, app);
				ColoringAttributes ca = new ColoringAttributes();
				ca.setColor(new Color3f(0.0f, 1.0f, 0));
				app.setColoringAttributes(ca);
				Sphere model = new Sphere(5, app);
				Transform3D trans3d = new Transform3D();
				trans3d.setTranslation(new Vector3d(position.getX(), position.getY(), DRAWING_PLANE_Z));
				tg = new TransformGroup(trans3d);
				tg.addChild(model);
				tg.setPickable(true);
				branchGroup.addChild(tg);
			}
		}

		// TODO: Don't always build a new DynamicBranch group
		DynamicBranch branch = new DynamicInputBranch(branchGroup, tg, geometryLabel, engine, canvas);
		return branch;

	}

	/**
	 * Gets the token branch representation for a particular object according to the {@link AppearanceInfo}
	 * corresponding to the appearanceLabel. It is the responsibility of this method to create the Java3D node and
	 * connect the {@link TransformGroup} to the {@link BranchGroup}, before putting them in the {@link DynamicBranch}.
	 * 
	 * @param appearanceLabel the label
	 * @return the token branch
	 * @author Marius
	 */
	public DynamicBranch getTokenBranch(String appearanceLabel) {

		// TODO: Change to use getTransformGroupForAppearance and buildAppearance
		AppearanceInfo appearanceInfo = this.loader.getAppearanceInfo(appearanceLabel);
		BranchGroup branchGroup = new BranchGroup();
		branchGroup.setCapability(BranchGroup.ALLOW_DETACH);
		TransformGroup tg = null;

		// String apinfo = appearanceInfo.getLabel();
		// switch - case with strings only in JRE 7. for compatibility issues, I'm using if - else
		if (appearanceInfo instanceof Shape3D) {
			Object3D type = ((Shape3D) appearanceInfo).getType();
			if (type == Object3D.CUBE) {
				ColorCube model = new ColorCube(5);
				tg = new TransformGroup();
				tg.addChild(model);
				tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				branchGroup.addChild(tg);
			} else if (type == Object3D.SPHERE) {
				// load earth texture on sphere
				Appearance app = new Appearance();
				// Texture tex = new TextureLoader("graphics/textures/earth.png", engine).getTexture();
				// //FIXME: may need to be changed from / to \ depending on the operating system
				// app.setTexture(tex);
				// TextureAttributes texAttr = new TextureAttributes();
				// texAttr.setTextureMode(TextureAttributes.MODULATE);
				// app.setTextureAttributes(texAttr);
				// Sphere model = new Sphere(5, Sphere.GENERATE_TEXTURE_COORDS, app);
				ColoringAttributes ca = new ColoringAttributes();
				ca.setColor(new Color3f(0.0f, 1.0f, 0));
				app.setColoringAttributes(ca);
				Sphere model = new Sphere(5, app);
				tg = new TransformGroup();
				tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				tg.addChild(model);
				branchGroup.addChild(tg);
			}
		} else if (appearanceInfo instanceof Model3D) {
			String filepath = ((Model3D) appearanceInfo).getFile();

			Scene s = null;
			ObjectFile f = new ObjectFile();
			f.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);

			try {
				s = f.load(filepath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IncorrectFormatException e) {
				e.printStackTrace();
			} catch (ParsingErrorException e) {
				e.printStackTrace();
			}
			tg = new TransformGroup();
			tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D scale = new Transform3D();
			tg.getTransform(scale);
			scale.setScale(100);
			tg.setTransform(scale);
			tg.addChild(s.getSceneGroup());

			BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 15.0), 2000.0);
			Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
			AmbientLight ambientLightNode = new AmbientLight(ambientColor);
			ambientLightNode.setInfluencingBounds(bounds);
			tg.addChild(ambientLightNode);

			branchGroup.addChild(tg);
		} else {
			ColorCube model = new ColorCube(0.5f);
			tg = new TransformGroup();
			tg.addChild(model);
			branchGroup.addChild(tg);
		}

		DynamicBranch branch = new DynamicBranch(branchGroup, tg);
		return branch;
	}

}
