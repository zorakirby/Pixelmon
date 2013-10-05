package pixelmon.database;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdotsoft.jarloader.JarClassLoader;

import pixelmon.config.PixelmonConfig;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModClassLoader;

/**
 * A simple little helper to handle errors and get info from SQLite
 * 
 * @author Grethen
 */
public class DatabaseHelper {
	static Connection con;
	static boolean madeConnection = false;

	/**
	 * Gets the connection to the path
	 * 
	 * @return The connection associated with the path
	 */
	public static Connection getConnection() {
		try {
			if (madeConnection && !con.isClosed())
				return con;
			if (java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") <= 0) {
				JarClassLoader jcl = new JarClassLoader();
				Thread.currentThread().setContextClassLoader(jcl);
			}
			Class.forName("org.h2.Driver");
			if (java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0) {
				URL url = DatabaseHelper.class.getResource("/pixelmon/database/Pixelmon2.h2.db");
				con = DriverManager.getConnection("jdbc:h2:" + url.toString().substring(0, url.toString().length() - 6));
				madeConnection = true;
				return con;
			} else {
				URL url = DatabaseHelper.class.getResource("/pixelmon/database/Pixelmon2.h2.db");
				System.out.println(url.toString());
				con = DriverManager.getConnection("jdbc:h2:" + url.toString().substring(0, url.toString().length() - 6));
				madeConnection = true;
				return con;
			}
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
