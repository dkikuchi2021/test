package com.example.demo.commons;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class UploadForm {
   
  private List<MultipartFile> multipartFile;

   /**
   * * @return multipartFile
  */
  public List<MultipartFile> getMultipartFile() {
	  return multipartFile;
  }
	
	/**
	 * @param multipartFile セットする multipartFile
	 */
	public void setMultipartFile(List<MultipartFile> multipartFile) {
		this.multipartFile = multipartFile;
	}
}