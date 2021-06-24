package com.example.demo.commons;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class Xml {
	
	public void ReadSettings() {
				
		try ( InputStream is = Files.newInputStream(Paths.get(UtilAction.getInstance().getPath() + "/src/main/resources/Config.xml")) ) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XmlHandler handler = new XmlHandler();
            parser.parse(is, handler);
            parser = factory.newSAXParser();
        } catch ( ParserConfigurationException | SAXException | IOException e ) {
            System.out.println( e.getMessage() );
        }
	}
}
