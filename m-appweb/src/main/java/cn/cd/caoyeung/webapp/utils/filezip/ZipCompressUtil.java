package cn.cd.caoyeung.webapp.utils.filezip;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 该压缩技术支持多级目录的压缩，文件目录不支持中文 压缩时调用compress()方法进行压缩
 */
public class ZipCompressUtil {

	/**
	 * 
	 * @param zos
	 *            ZipOutputStream
	 * @param oppositePath
	 *            相对路径，开始时相对路径为空
	 * @param directory
	 * 
	 * 
	 */
	public void startCompress(ZipOutputStream zos, String oppositePath,
			String directory) {
		File file = new File(directory);
		if (file.isDirectory()) {
			// 如果是压缩目�?
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File aFile = files[i];
				if (aFile.isDirectory()) {
					// 如果是目录，修改相对地址
					String newOppositePath = oppositePath + aFile.getName()
							+ "\\";
					// 创建目录
					compressDirectory(zos, oppositePath, aFile);
					// 进行递归调用
					startCompress(zos, newOppositePath, aFile.getPath());
				} else {
					// 如果不是目录，则进行压缩
					compressFile(zos, oppositePath, aFile);
				}
			}
		} else {
			// 如果是压缩文件，直接调用压缩方法进行压缩
			compressFile(zos, oppositePath, file);
		}
	}

	/**
	 * 压缩文件.
	 * 
	 * @param zos
	 *            ZipOutputStream
	 * @param oppositePath
	 *            相对于路路径
	 * @param file
	 * 
	 * 
	 */
	public void compressFile(ZipOutputStream zos, String oppositePath, File file) {
		// 创建Zip条目，每个Zip条目都是必须相对于根路径
		ZipEntry entry = new ZipEntry(oppositePath + file.getName());
		InputStream is = null;
		try {
			// 将条目保存到Zip压缩文件当中
			zos.putNextEntry(entry);
			// 从文件输入流当中读取数据，并将数据写到输出流当中.
			is = new FileInputStream(file);
			int length = 0;
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			while ((length = is.read(buffer, 0, bufferSize)) >= 0) {
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 在压缩文件当中创建一个新的压缩目录，支持空目�?.
	 * 
	 * @param zos
	 * @param oppositePath
	 * @param file
	 */
	public void compressDirectory(ZipOutputStream zos, String oppositePath,
			File file) {
		// 压缩目录，创建一个目录的条目 "/"
		ZipEntry entry = new ZipEntry(oppositePath + file.getName() + "\\");
		try {
			zos.putNextEntry(entry);
			zos.closeEntry();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 压缩文件
	 * 
	 * @param destFile
	 *            生成的文件完整路径名
	 */
	public void compress(String fileNameAndPath, String destFile) {

		// 参数校验
		if (null == fileNameAndPath || "".equals(fileNameAndPath)
				|| null == destFile || "".equals(destFile)) {
			return;
		}

		// 获得压缩文件所在的目录
		// String str = File.separator;
		int pos = destFile.lastIndexOf("/");
		String descDir = destFile.substring(0, pos);

		File file = new File(descDir);

		// 如果目录不存在，创建新目录
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}

		// 默认的相对地路径，为根路径
		String defaultParentPath = "";
		ZipOutputStream zos = null;
		try {
			// 创建
			zos = new ZipOutputStream(new FileOutputStream(destFile));

			// 启动压缩进程
			this.startCompress(zos, defaultParentPath, fileNameAndPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (zos != null)
					zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 解压缩文件
	public void realiseZipFile(String unzipfile, String extFold) {

		// 参数校验
		if (null == unzipfile || "".equals(unzipfile) || null == extFold
				|| "".equals(extFold)) {
			return;
		}

		// extFold
		if (!File.separator.equals(extFold.charAt(extFold.length() - 1))) {
			extFold += File.separator;
		}
		int intCount = 0;
		ZipInputStream zin = null;
		try {

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
					dout.close();
					fout.close();
					zin.closeEntry();
				}
				intCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
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
		System.out.println("Total: [" + intCount + "] files has been created!");
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
