package com.example.demo.commons;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler  {
	
	private String name = null;
	
    @Override
    public void startElement (String namespaceURI, String localName, String qName, Attributes atts ) {
    	this.name = qName;
    }

    @Override
    public void characters (char[] ch, int start, int length ) {
        String text = new String (ch, start, length );
        
        if(this.name == null || this.name.isEmpty() == false) {        	
        	Config.getInstance().setValue(this.name, text);        
        }
    }
}
