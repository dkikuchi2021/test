package com.example.demo.commons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppLog {

	/**
	 * 外部からインスタンス化できないよう、コンストラクタをprivateで宣言する
	 */
	private AppLog() {
		// 実際はファイル・データベースなどから設定情報を
		// 読み込む処理をここに書く
	}

	/**
	 * 唯一のインスタンスを返す
	 * @return このクラスの唯一のインスタンス
	 */
	public static AppLog getInstance() {
		return AppLogInstanceHander.INSTANCE;
	}

	/**
	 * Configクラスの唯一のインスタンスを保持する内部クラス
	 */
	public static class AppLogInstanceHander {
		/** 唯一のインスタンス */
		private static final AppLog INSTANCE = new AppLog();
	}

	public void Log(String messgae) {
		Log(messgae, null);
	}

	public void Log(String messgae, String logType) {
		Log(messgae, null, null);
	}

	private FileWriter filewriter = null;

	private String writeFileName = null;

	public void Log(String messgae, String logType, String id ) {

		String fileName = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()) + ".log";
		
		File dir = new File(Config.getInstance().getValue("LogPath") );
		if(dir.exists() == false) {
			try {
				dir.mkdir();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		if(writeFileName == null ||  writeFileName != fileName) {
			writeFileName = fileName;
			try {
				if(filewriter != null) {
					filewriter.close();
				}
				filewriter = new FileWriter(Config.getInstance().getValue("LogPath") + "/"+ writeFileName, true);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		
		try {
			String dateString = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
			filewriter.write(dateString + ":" + "[" + logType + "]:" + messgae + "\r\n"); 
			filewriter.flush();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}