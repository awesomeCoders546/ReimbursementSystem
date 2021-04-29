package com.sjp.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;

public class ImageService {
	public String encodeFileToBase64Binary(String path){
		  String basePath=path;
		  File file=new File(basePath); 
	       String encodedfile = null;
	       try {
	           FileInputStream fileInputStreamReader = new FileInputStream(file);
	           byte[] bytes = new byte[(int)file.length()];
	           fileInputStreamReader.read(bytes);
	         
	           encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
	       } catch (FileNotFoundException e) {
	          
	           e.printStackTrace();
	       } catch (IOException e) {
	          
	           e.printStackTrace();
	       }
	       String newPath=","+encodedfile;
	       System.out.println(newPath);
	     //  System.out.println("encodedFile"+encodedfile);
	      path= convertBase(newPath, "F:\\imageTest");
	      return path;
	      
	   }
	public String convertBase(String base,String basePath)
	{
		
		String base64String = base;
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        
   
        Date date= new Date(); 
		 long time = date.getTime();
		 Timestamp ts = new Timestamp(time);
		 String currentTime=ts.toString();
		
		 String latest=currentTime.replace(":", ".");
		
        String path = basePath +"\\"+latest+"."+ extension;

        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        System.out.println(path);
        return path;
	}

}
