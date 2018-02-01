package cn.cd.caoyeung.webapp.utils.filezip;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class RealiseZipFile {
	public List<String> realise(String unzipfile, String extFold) {
		// String unzipfile = "c:\\test\\test.zip";
		// String extFold = "e:\\test\\";
		int intCount = 0;
		ZipInputStream zin = null;
		List<String> filePaths = new ArrayList<String>();
		if (!extFold.endsWith(File.separator)){
			extFold = extFold+File.separator;
		}
		try {
			// File oldDirec = new File(unzipfile);
			zin = new ZipInputStream(new FileInputStream(unzipfile));
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					File directory = new File(extFold, entry.getName());
					if (!directory.exists() && directory.mkdirs())
						System.exit(0);
					zin.closeEntry();
				} else {
					String strFileName = entry.getName();
					if (strFileName.indexOf("/") != -1) {
						makeDirs(extFold, strFileName.substring(0, strFileName
								.lastIndexOf("/")));
					}
					File myFile = new File(strFileName);
					FileOutputStream fout = new FileOutputStream(extFold
							+ myFile.getPath());
					DataOutputStream dout = new DataOutputStream(fout);
					byte[] b = new byte[1024];
					int len = 0;
					while ((len = zin.read(b)) != -1) {
						dout.write(b, 0, len);
					}
					filePaths.add(extFold+myFile.getPath());
					dout.close();
					fout.close();
					zin.closeEntry();
				}
				intCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (zin != null) {
					zin.closeEntry();
					zin.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return filePaths;
	}

	private static boolean makeDirs(String strBottomFoldName, String strFoldName) {
		boolean blnResult = true;
		File newFold = new File(strBottomFoldName, strFoldName);
		if (!newFold.exists() && !newFold.mkdirs()) {
			blnResult = false;
		}
		return blnResult;
	}
}
