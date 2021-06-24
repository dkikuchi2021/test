package com.example.demo.commons;

import java.util.HashMap;

public class Config {

	private HashMap<String, String> settings = null;

	/**
	 * 外部からインスタンス化できないよう、コンストラクタをprivateで宣言する
	 */
	private Config() {
		// 実際はファイル・データベースなどから設定情報を
		// 読み込む処理をここに書く
	}
	
	public void Init() {
		this.settings = new HashMap<String, String>();
		Xml xmlData = new Xml();
		xmlData.ReadSettings();
	}

	/**
	 * 唯一のインスタンスを返す
	 * @return このクラスの唯一のインスタンス
	 */
	public static Config getInstance() {
		return ConfigInstanceHander.INSTANCE;
	}

	public String getValue(String key) {
		
		if(settings.containsKey(key)) {
			return  settings.get(key);
		}
		
		return "";
	}

	public void setValue(String key, String value) {
		if (settings.containsKey(key) == false) {
			settings.put(key, value);
		}
	}

	/**
	 * Configクラスの唯一のインスタンスを保持する内部クラス
	 */
	public static class ConfigInstanceHander {
		/** 唯一のインスタンス */
		private static final Config INSTANCE = new Config();
	}
}
