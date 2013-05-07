package pixelmon.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pixelmon.DownloadHelper;
import pixelmon.config.PixelmonConfig;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModClassLoader;

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
	public static String databaseURL = "http://pixelmonmod.com/pixelmon.db";
	public static String sqliteURL = "http://www.mediafire.com/download.php?um6vgovuapow8d3";

	public static boolean checkForDatabaseUpdates = true;

	public static boolean has(boolean checkForUpdates) {
		try {
			checkForDatabaseUpdates = checkForUpdates;
			File databaseDir = new File(DownloadHelper.getDir(), "database");
			if (!databaseDir.exists()) {
				databaseDir.mkdir();
				DownloadHelper.downloadFile("database/Pixelmon.db", databaseURL);
				DownloadHelper.downloadFile("database/sqlite-jdbc-3.7.2.jar", sqliteURL);
			} else {
				File databaseFile = new File(databaseDir, "Pixelmon.db");
				if (!databaseFile.exists()) {
					DownloadHelper.downloadFile("database/Pixelmon.db", databaseURL);
				} else {
					if (checkForDatabaseUpdates)
						checkVersion();
				}
				File sqlitejar = new File(databaseDir, "sqlite-jdbc-3.7.2.jar");
				if (!sqlitejar.exists())
					DownloadHelper.downloadFile("database/sqlite-jdbc-3.7.2.jar", sqliteURL);
				if (!sqlitejar.exists())
					System.out.println("SQLite Jar still not found at " + sqlitejar.getAbsolutePath());
				if (!databaseFile.exists())
					System.out.println("Database still not found at " + databaseFile.getAbsolutePath());
				((ModClassLoader) Loader.instance().getModClassLoader()).addFile(sqlitejar);
			}

			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:" + DownloadHelper.getDir() + "/database/Pixelmon.db");
			if (c == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void checkVersion() {
		if (!DownloadHelper.compareVersion("database/Pixelmon.db", databaseURL)) {
			DownloadHelper.downloadFile("database/Pixelmon.db", databaseURL);
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
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + DownloadHelper.getDir() + "/database/Pixelmon.db");
			con.setReadOnly(true);
			return con;

		} catch (Exception e) {
			System.out.println("Could not get a connection to pixelmon.db");
			return null;
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
