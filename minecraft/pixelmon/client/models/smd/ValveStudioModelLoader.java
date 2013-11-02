package pixelmon.client.models.smd;

import java.net.URL;

import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.IModelCustomLoader;
import net.minecraftforge.client.model.ModelFormatException;

/**
 * This loads Valve SMD models via an initialization file with extension '.pqc'.
 * PQC stands for 'Pseudo QC' where a 'QC' file stands for 'Quake C", which is 
 * what Valve's Studio Model Compiler uses to compile an SMD model into a MDL model.
 * Since the pqc's that will be used here function like qc files, but aren't actually
 * qc files, they're being referred to as 'pqc' files.
 * <br><br>
 * Currently the SMD features that are supported are:<br>
 * 1.Bones<br>
 * 2.Animations<br>
 * 3.Smoothing groups (sharp/smooth edges)<br>
 * <br>
 * Unfortunately, bone weights are currently not factored-in: if a bone even has 1%
 * influence over a vertex, it acts as though it has 100% influence. Luckily though, 
 * there does not yet seem to be much of a demand for bone weights. They WILL get
 * implemented eventually, though.<br>
 * <br>
 * How to use the SMD loader:<br>
 * &nbsp&nbsp&nbsp&nbsp A "PQC" text file should be created with commands on each line.
 * These are the commands currently supported:<br>
 * <code>{@literal $body <SMD Geometry File Name>}</code>: the main model to be used<br>
 * <code>{@literal $anim <animation keyname> <SMD Animation File name>}</code>: 
 * defines an animation. Animations are not required - if none are specified then 
 * the model will just be non-moving. Animations with the keyname "idle" will 
 * be automatically initialized as the starting animation for the model. The
 * keyname should be used when calling {@link ValveStudioModel#setAnimation(String)}<br><br>
 * To use an SMD model with a {@link pixelmon.client.models.animations.Module Module}:<br>
 * The first argument in each {@code Module} constructor should be a {@link Bone} - each bone
 * in an SMD model can be accessed via {@link SmdModel#getBoneByName(String)} where the String used
 * should be the name originally assigned to the bone in the 3D modeling software.
 *
 *@see pixelmon.client.models.pokemon.ModelFroslass
 */
public class ValveStudioModelLoader implements IModelCustomLoader{

	private static final String[] types = {"pqc"};
	
	public static final ValveStudioModelLoader instance = new ValveStudioModelLoader();
	
	@Override
	public String getType() {
		return "Valve Studio Model";
	}

	@Override
	public String[] getSuffixes() {
		return types;
	}

	@Override
	public IModelCustom loadInstance(String resourceName, URL resource)
			throws GabeNewellException {
		return new ValveStudioModel(resourceName, resource);
	}

}
