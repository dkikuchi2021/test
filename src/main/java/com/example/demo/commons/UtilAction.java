package com.example.demo.commons;

import java.io.File;

public class UtilAction  {
	
	/**
     * 外部からインスタンス化できないよう、コンストラクタをprivateで宣言する
     */
    private UtilAction() {
        // 実際はファイル・データベースなどから設定情報を
        // 読み込む処理をここに書く
    }

    /**
     * 唯一のインスタンスを返す
     * @return このクラスの唯一のインスタンス
     */
    public static UtilAction getInstance() {
        return UtilActionInstanceHander.INSTANCE;
    }

    /**
     * Configクラスの唯一のインスタンスを保持する内部クラス
     */
    public static class UtilActionInstanceHander {
        /** 唯一のインスタンス */
        private static final UtilAction INSTANCE = new UtilAction();
    }
    
    private String appPathString = null; 
    
    public String getPath() {
    	
    	if(appPathString == null || appPathString.isEmpty()) {
    		this.appPathString = new File(".").getAbsoluteFile().getParent();
    	}
    	
    	return appPathString;
    }
}
