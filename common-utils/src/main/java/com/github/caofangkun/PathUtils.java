package com.github.caofangkun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class PathUtils {
	  public static void writeToPath(byte[] msg, File dest) throws IOException {
		    File tf = new File(dest.getPath() + "N");
		    FileOutputStream fos = null;
		    try {
		      fos = new FileOutputStream(tf);
		      fos.write(msg);
		    } finally {
		      fos.flush();
		      IOUtils.closeQuietly(fos);
		    }
		    if (!tf.renameTo(dest)) {
		      throw new IOException("Can not rename " + tf.getAbsolutePath() + " to "
		          + dest.getAbsolutePath());
		    }

		  }
}
