package pixelmon.database;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

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
				else
				{
					checkVersion();
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
	
	public static void checkVersion()
	{
		try
		{
			long currentVersion = getChecksum(new DataInputStream(new FileInputStream(new File(getDir(), "Pixelmon.db"))));
			long releasedVersion = getChecksum(new DataInputStream(new URL("https://dl.dropbox.com/s/8crv95bumdjy6wt/Pixelmon.db?dl=1").openStream()));
			if(currentVersion != releasedVersion)
			{
				downloadDatabase();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static long getChecksum(InputStream is)
    {
        CheckedInputStream cis = null;        
        long checksum = 0;
        try 
        {
            cis = new CheckedInputStream(is, new Adler32());
            byte[] tempBuf = new byte[128];
            while (cis.read(tempBuf) >= 0) 
            {
            }
            checksum = cis.getChecksum().getValue();
        } 
        catch (IOException e) 
        {
            checksum = 0;
        }
        finally
        {
            if (cis != null)
            {
                try
                {
                    cis.close();
                }
                catch (IOException ioe)
                {                    
                }
            }
        }
        return checksum;
    }
	
	public static void downloadDatabase()
	{
		try
		{
			System.out.println("Attempting to download the Database!");
			String databaseURL = "https://dl.dropbox.com/s/8crv95bumdjy6wt/Pixelmon.db?dl=1";
			URL url = new URL(databaseURL);
			File databaseFile = new File(getDir(), "Pixelmon.db");
			databaseFile.createNewFile();
			byte[] array = new byte[4096];
	        DataInputStream urlStream = new DataInputStream(url.openStream());
	        DataOutputStream fileStream = new DataOutputStream(new FileOutputStream(databaseFile));
	        boolean var8 = false;

	        do
	        {
	            int data;

	            if ((data = urlStream.read(array)) < 0)
	            {
	                urlStream.close();
	                fileStream.close();
	                break;
	            }

	            fileStream.write(array, 0, data);
	        }
	        while (true);
	        System.out.println("Database Downloaded!");
		}
		catch(Exception e)
		{
			System.out.println("Failed to download the Database!");
			e.printStackTrace();
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
