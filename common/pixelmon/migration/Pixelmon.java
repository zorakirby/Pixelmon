/**
 * 
 */
package pixelmon.migration;

/**
 * @author MrMasochism
 * 
 *         The stripped back entity class used to model a particular pokemon's
 *         movement details etc. Will be passed to the minecraft server when a
 *         pokemon is to spawn
 */
public class Pixelmon {

	public String name;
	public boolean isLeader = false;
	public int x, y, z;
	public int level;

	/**
	 * 
	 */
	public Pixelmon(String name, int x, int y, int z, boolean isLeader) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
