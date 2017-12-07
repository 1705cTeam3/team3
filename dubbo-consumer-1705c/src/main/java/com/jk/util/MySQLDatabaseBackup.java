package com.jk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * MySQL数据库备份
 * 
 */
public class MySQLDatabaseBackup {

	/**
	 * Java代码实现MySQL数据库导出
	 * 
	 * @author GaoHuanjie edit by lxx 2017年12月1日 19:09:56
	 * @param hostIP
	 *            MySQL数据库所在服务器地址IP
	 * @param userName
	 *            进入数据库所需要的用户名
	 * @param password
	 *            进入数据库所需要的密码
	 * @param savePath
	 *            数据库导出文件保存路径
	 * @param fileName
	 *            数据库导出文件文件名
	 * @param databaseName
	 *            要导出的数据库名
	 * @return 返回true表示导出成功，否则返回false。 如果mysql安装目录有空格，如program File（X86）/，
	 *         则需要将mysql/bin/mysqldump.exe复制到c盘根目录
	 */
	public static boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath,
			String databaseName) throws InterruptedException {
		// --start--
		// 复制mysql安装路径bin/mysqldump.exe，到C盘根目录，定义这个路径
		// 根据库名生成随机备份文件，不覆盖
		String mysqldumPath = "C:\\";
		String fileName = databaseName + "_backup_";
		// 文件夹存在，则生成新备份文件，避免覆盖以前的
		fileName += UUID.randomUUID() + ".sql";
		// --end--

		File saveFile = new File(savePath);
		if (!saveFile.exists()) {// 如果目录不存在
			saveFile.mkdirs();// 创建文件夹
		}
		if (!savePath.endsWith(File.separator)) {
			savePath = savePath + File.separator;
		}

		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
			Process process = Runtime.getRuntime().exec("cmd /c " + mysqldumPath + "\\mysqldump -h" + hostIP + " -u" + userName
					+ " -p" + password + " --set-charset=UTF8 " + databaseName);

			/*
			 * Process process = Runtime.getRuntime().exec(" mysqldump -h" +
			 * hostIP + " -u" + userName + " -p" + password +
			 * " --set-charset=UTF8 " + databaseName);
			 */
			InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				printWriter.println(line);
			}
			printWriter.flush();
			if (process.waitFor() == 0) {// 0 表示线程正常终止。
				System.err.println("数据库成功备份！！！");
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.err.println("数据库成功失败！！！");
		return false;
	}

	public static void main(String[] args) {
		try {
			if (exportDatabaseTool("127.0.0.1", "root", "root", "d://", "erp")) {
				System.err.println("数据库成功备份！！！");
			} else {
				System.err.println("数据库备份失败！！！");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
