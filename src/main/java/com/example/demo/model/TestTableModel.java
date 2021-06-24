package com.example.demo.model;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Timestamp;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;

import com.example.demo.model.Entity.TestTableEntity;
import com.example.demo.model.base.TableBaseModel;


public class TestTableModel extends TableBaseModel{

	private TestTableEntity entityData = null;

	private String gazou = null;

	public TestTableModel() {
		setTableName("test_table");
	}

	public TestTableModel(TestTableEntity entity) {
		setTableName("test_table");
		entityData = entity;
	}

	public String getId() {
		return entityData.getId();
	}

	public void setId(String id) {
		entityData.setId(id);
	}

	public Integer getNumber() {
		return entityData.getNumber();
	}

	public void setNumber(Integer number) {
		entityData.setNumber(number);
	}

	public Timestamp getDate() {
		return entityData.getDate();
	}

	public void setDate(Timestamp date) {
		entityData.setDate(date);
	}

	/**
	 * @return image
	 */
	public String getGazou() {

		if(gazou == null) {
			StringBuffer data = new StringBuffer();

			//submitされた画像データストリームを取得する
			//InputStream is = uploadfile.getInputStream();

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			File file = new File("C:/eclipse/sample/image/20210521112313527.jpeg");
			String base64 = null;
			try {
				BufferedImage image = null;
				image = ImageIO.read(file);
				image.flush();
				ImageIO.write(image, "jpg", bos);
				bos.flush();
				bos.close();
				//画像データをbase64エンコードする
				base64 = new String(Base64.encodeBase64(os.toByteArray()), "ASCII");

				//画像タイプはJPEG固定
				data.append("data:image/jpeg;base64,");
				data.append(base64);

			} catch (Exception e) {
				// TODO: handle exception
			}

			setGazou(data.toString());
		}
		return this.gazou;
	}

	/**
	 * @param image セットする image
	 */
	public void setGazou(String gazou) {
		this.gazou = gazou;
	}
}