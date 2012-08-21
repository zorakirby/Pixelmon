package pixelmon.database;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pixelmon.ClientProxy;

import cpw.mods.fml.common.network.FMLNetworkHandler;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

/**
 * A simple little helper to handle errors and get info from SQLite
 * 
 * @author Grethen
 */
public class DatabaseHelper {
	/**
	 * A check to make sure the user has the SQLite Jar, currently ignores the
	 * <code>SQLExcpetion</code> that has to do with drivers because it seems to
	 * always throw that
	 * 
	 * @return True if they do, otherwise false
	 */
	public static boolean has() {
		try
		{
			Class.forName("org.sqlite.JDBC");
			if(!getDir().exists())
			{
				getDir().mkdir();
				downloadDatabase();
			}
			else
			{
				File databaseFile = new File(getDir(), "Pixelmon.db");
				if(!databaseFile.exists())
				{
					downloadDatabase();
				}
			}
			
			Connection c = DriverManager.getConnection("jdbc:sqlite:" + getDir() + "/Pixelmon.db");
			if(c == null)
			{
				return false;
			}
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		
		/*try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:" + Minecraft.getMinecraftDir() + "/database/Pixelmon.db");
			if (c == null) {
				System.out.println("Could not find the Pixelmon database at " + Minecraft.getMinecraftDir() + "/database/Pixelmon.db");
				return false;
			} else {
				System.out.println("Found Database at " + Minecraft.getMinecraftDir() + "/database/Pixelmon.db");
			}
			return true;
		} catch (java.lang.NoClassDefFoundError e) {
			System.out.println("Could not find SQLite Jar");
			return false;
		} catch (java.sql.SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find SQLite Jar !!");
			return false;
		}*/
	}

	/**
	 * Gets the connection to the path
	 * 
	 * @return The connection associated with the path
	 */
	public static Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + getDir() + "/Pixelmon.db");
			con.setReadOnly(true);
			return con;

		} catch (Exception e) {
			System.out.println("Could not get a connection to pixelmon.db");
			return null;
		}
	}
	
	public static File getDir()
	{
		if(MinecraftServer.getServer() != null)
		{
			if(!MinecraftServer.getServer().isSinglePlayer())
			{
				return MinecraftServer.getServer().getFile("database");
			}
		}
		return  new File(ClientProxy.getMinecraftDir(), "database");
	}
	
	public static void downloadDatabase()
	{
		String databaseURL = "https://dl.dropbox.com/s/8crv95bumdjy6wt/Pixelmon.db?dl=1";
		try
		{
			System.out.println("Attempting to download the Database!");
		}
		catch(Exception e)
		{
			System.out.println("Failec to download the Database!");
		}
	}

	/**
	 * Gets a <code>Statement</code> from the given <code>Connection</code>
	 * 
	 * @param c
	 *            - The <code>Connection</code> to use
	 * @return The <code>Statement</code> from the <code>Connection</code>
	 */
	public static Statement getStatement(Connection c) {
		try {
			return c.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not create statement for database");
			return null;
		}
	}

	/**
	 * @return The <code>Statement</code> from the default
	 *         <code>Connection</code>
	 */
	public static Statement getStatement() {
		return getStatement(getConnection());
	}

	/**
	 * Gets a <code>ResultSet</code> from the given <code>Statement</code> and
	 * query
	 * 
	 * @param s
	 *            - The <code>Statement</code> to use
	 * @param query
	 *            - The query to look for
	 * @return The <code>ResultSet</code>
	 */
	public static ResultSet getResultSet(Statement s, String query) {
		try {
			return s.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Could not create ResultSet for query " + query + " for database because " + e.getMessage());
			return null;
		}
	}

	/**
	 * Gets a <code>ResultSet</code> from the default <code>Statement</code>
	 * 
	 * @param query
	 *            - The query to look for
	 * @return The <code>ResultSet</code>
	 */
	public static ResultSet getResultSet(String query) {
		return getResultSet(getStatement(), query);
	}

	public static void finish(Connection c, Statement s) {
		try {
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void finish(ResultSet r) {
		try {
			finish(r.getStatement().getConnection(), r.getStatement());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
