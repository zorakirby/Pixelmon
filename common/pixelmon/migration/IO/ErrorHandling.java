package pixelmon.migration.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class ErrorHandling {

	/**
	 * Handles errors from migration code. Since it is on a separate thread I
	 * decided to store the errors in a separate log to make it easy for us to
	 * check stuff.
	 * 
	 * @param error  The error that has been encountered
	 */
	public static void handleError(String error) {
		try {
			File errorFile = new File("migration.error");
			if (!errorFile.exists()) {

				errorFile.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(errorFile);
			OutputStreamWriter out = new OutputStreamWriter(fos);

			out.write(error + "\n");

			out.close();
			fos.close();

		} catch (Exception e) {
		}
	}

}
