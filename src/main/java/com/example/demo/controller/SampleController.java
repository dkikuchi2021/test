package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.commons.Config;
import com.example.demo.commons.UploadForm;
import com.example.demo.model.TestTableModel;
import com.example.demo.model.container.TestTableContainer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RequestMapping("/sample/*")
@Controller
public class SampleController {
	@GetMapping("/")
	public String Index(Model model) {
		String str = "Hello World";
		model.addAttribute("value", str);
		
		Config.getInstance();
		return "sample/index";
	}

	@RequestMapping(path = "/upload", method = RequestMethod.GET)
	String uploadview(Model model) {
		model.addAttribute("uploadForm", new UploadForm());
		return "sample/upload";
	}

	@Autowired
	TestTableContainer container;
	
	@RequestMapping(path = "/table", method = RequestMethod.GET)
	public String hello() {
		return  "sample/table";
	}

	@GetMapping("/searchajax")
	@ResponseBody	
	public String ajaxTest() {
		container.findAll();
		//model.addAttribute("testTableModelDataList", container.getData());
		return getJson(container.getData());
	}
 
    /**
     * 引数のUserDataオブジェクトをJSON文字列に変換する
     * @param userDataList UserDataオブジェクトのリスト
     * @return 変換後JSON文字列
     */
    private String getJson(List<TestTableModel> userDataList){
        String retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            retVal = objectMapper.writeValueAsString(userDataList);
        } catch (JsonProcessingException e) {
            System.err.println(e);
        }
        return retVal;
    }
	
	@RequestMapping(path = "/table", params = "search" ,method = RequestMethod.POST)
	public String hello(Model model) {
		container.findAll();
		model.addAttribute("testTableModelDataList", container.getData());
		return "sample/table";
	}

	private void uploadFileAction(String path, MultipartFile  file) {
		int dot = file.getOriginalFilename().lastIndexOf(".");
		String extention = "";
		if (dot > 0) {
			extention = file.getOriginalFilename().substring(dot).toLowerCase();
		}
		String filename = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
		Path uploadfile = Paths
				.get(path + "/" + filename + extention);

		try (OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
			byte[] bytes = file.getBytes();
			os.write(bytes);
		} catch (IOException ex) {
			System.err.println(ex);
		}	  
	}

	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	String upload(UploadForm uploadForm) {



		if (uploadForm.getMultipartFile() == null || uploadForm.getMultipartFile().size() == 0) {
			return "sample/upload";
		}

		// check upload distination directory.If there was no directory, make
		// func.
		String basePath = "C:/eclipse/sample/image"; 
		Path path = Paths.get(basePath);
		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (NoSuchFileException ex) {
				System.err.println(ex);
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}

		for (MultipartFile file : uploadForm.getMultipartFile()) {
			uploadFileAction(basePath, file);
		}

		return "redirect:/sample/";
	}
}