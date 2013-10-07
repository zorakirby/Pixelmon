package pixelmon.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModClassLoader;

import pixelmon.Pixelmon;

/**
 * A simple little helper to handle errors and get info from SQLite
 * 
 * @author Grethen
 */
public class DatabaseHelper {
	static Connection con;
	static boolean madeConnection = false;
	static String databasePath = null;

	/**
	 * Gets the connection to the path
	 * 
	 * @return The connection associated with the path
	 */
	public static Connection getConnection() {
		try {
			if (madeConnection && !con.isClosed())
				return con;
			if (databasePath == null)
				databasePath = Pixelmon.modDirectory + "/database/";
			File databaseDir = new File(Pixelmon.modDirectory + "/database");
			if (!databaseDir.isDirectory()) {
				System.out.println("Creating database directory");
				databaseDir.mkdir();
			}
			File databaseFile = new File(databasePath + "Pixelmon2.h2.db");
			if (databaseFile.exists())
				databaseFile.delete();
			copyDatabaseFromJar();
			if (!new File(databasePath + "h2-1.3.173.jar").exists())
				copyDriverFromJar();
			((ModClassLoader) Loader.instance().getModClassLoader()).addFile(new File(databasePath + "h2-1.3.173.jar"));
			System.out.println("Loading Database Driver");
			Class.forName("org.h2.Driver");
			System.out.println("Establishing Connection");
			URL url = DatabaseHelper.class.getResource("/pixelmon/database/Pixelmon2.h2.db");
			con = DriverManager.getConnection("jdbc:h2:file:" + databasePath + "Pixelmon2");
			madeConnection = true;
			return con;
		} catch (Exception e) {
			System.out.println("Could not get a connection to database");
			return null;
		}
	}

	private static void copyDatabaseFromJar() {
		try {
			System.out.println("Extracting database");
			InputStream iStream = DatabaseHelper.class.getResourceAsStream("/pixelmon/database/Pixelmon2.h2.db");
			FileOutputStream fos = null;
			fos = new FileOutputStream(databasePath + "Pixelmon2.h2.db");
			byte[] buf = new byte[2048];
			int r = iStream.read(buf);
			while (r != -1) {
				fos.write(buf, 0, r);
				r = iStream.read(buf);
			}
			if (fos != null)
				fos.close();
		} catch (Exception e) {
			System.out.println("Failed to extract database");
		}
	}

	private static void copyDriverFromJar() {
		try {
			System.out.println("Extracting driver");
			InputStream iStream2 = DatabaseHelper.class.getResourceAsStream("/pixelmon/database/h2-1.3.173.jar");
			FileOutputStream fos2 = null;
			fos2 = new FileOutputStream(databasePath + "h2-1.3.173.jar");
			byte[] buf = new byte[2048];
			int r = iStream2.read(buf);
			while (r != -1) {
				fos2.write(buf, 0, r);
				r = iStream2.read(buf);
			}
			if (fos2 != null)
				fos2.close();
		} catch (Exception e) {
			System.out.println("Failed to extract driver");
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
