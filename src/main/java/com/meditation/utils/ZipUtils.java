package com.meditation.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 基于Ant的Zip压缩工具类 ， 可以多文件夹结合压缩，并且完美处理中文文件名
 * 
 * @author zhangyuting
 */
public class ZipUtils {

	public static final String ENCODING_DEFAULT = "UTF-8";

	public static final int BUFFER_SIZE_DIFAULT = 128;

	public static void makeZip(String[] inFilePaths, String zipPath) {
		try {
			makeZip(inFilePaths, zipPath, ENCODING_DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("zip fail");
		}
	}

	public static void makeZip(String[] inFilePaths, String zipPath, String encoding) throws Exception {
		File[] inFiles = new File[inFilePaths.length];
		for (int i = 0; i < inFilePaths.length; i++) {
			inFiles[i] = new File(inFilePaths[i]);
		}
		makeZip(inFiles, zipPath, encoding);
	}

	public static void makeZip(File[] inFiles, String zipPath) throws Exception {
		makeZip(inFiles, zipPath, ENCODING_DEFAULT);
	}

	public static void makeZip(File[] inFiles, String zipPath, String encoding) throws Exception {
		ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath)));
		zipOut.setEncoding(encoding);
		for (int i = 0; i < inFiles.length; i++) {
			File file = inFiles[i];
			doZipFile(zipOut, file, file.getParent());
		}
		zipOut.flush();
		zipOut.close();
	}

	private static void doZipFile(ZipOutputStream zipOut, File file, String dirPath)
			throws FileNotFoundException, IOException {
		if (file.isFile()) {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			String zipName = file.getPath().substring(dirPath.length());
			while (zipName.charAt(0) == '\\' || zipName.charAt(0) == '/') {
				zipName = zipName.substring(1);
			}
			ZipEntry entry = new ZipEntry(zipName);
			zipOut.putNextEntry(entry);
			byte[] buff = new byte[BUFFER_SIZE_DIFAULT];
			int size;
			while ((size = bis.read(buff, 0, buff.length)) != -1) {
				zipOut.write(buff, 0, size);
			}
			zipOut.closeEntry();
			bis.close();
		} else {
			File[] subFiles = file.listFiles();
			for (File subFile : subFiles) {
				doZipFile(zipOut, subFile, dirPath);
			}
		}
	}

	public static void unZip(String zipFilePath, String storePath) throws IOException {
		unZip(new File(zipFilePath), storePath);
	}

	public static void unZip(File zipFile, String storePath) throws IOException {
		if (new File(storePath).exists()) {
			new File(storePath).delete();
		}
		new File(storePath).mkdirs();

		@SuppressWarnings("resource")
		ZipFile zip = new ZipFile(zipFile);
		Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.getEntries();
		while (entries.hasMoreElements()) {
			ZipEntry zipEntry = entries.nextElement();
			if (zipEntry.isDirectory()) {
			} else {
				String zipEntryName = zipEntry.getName();
				if (zipEntryName.indexOf(File.separator) > 0) {
					String zipEntryDir = zipEntryName.substring(0, zipEntryName.lastIndexOf(File.separator) + 1);
					String unzipFileDir = storePath + File.separator + zipEntryDir;
					File unzipFileDirFile = new File(unzipFileDir);
					if (!unzipFileDirFile.exists()) {
						unzipFileDirFile.mkdirs();
					}
				}

				InputStream is = zip.getInputStream(zipEntry);
				FileOutputStream fos = new FileOutputStream(new File(storePath + File.separator + zipEntryName));
				byte[] buff = new byte[BUFFER_SIZE_DIFAULT];
				int size;
				while ((size = is.read(buff)) > 0) {
					fos.write(buff, 0, size);
				}
				fos.flush();
				fos.close();
				is.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		List<String> wordPath = new ArrayList<String>();
		wordPath.add("E:\\1576664238328\\");
		ZipUtils.makeZip((String[]) wordPath.toArray(new String[wordPath.size()]), "e:\\1576664238328.zip");
	}
}