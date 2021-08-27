package com.mindtree.musicstoreapp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.musicstoreapp.entity.Image;
import com.mindtree.musicstoreapp.repository.IImageRepository;

@RestController
@RequestMapping("image")
@CrossOrigin("*")
public class ImageController {

	
	@Autowired
	    IImageRepository imageRepository;
	
	    @PostMapping("/upload")
	    public ResponseEntity<?> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
	
	        System.out.println("Original Image Byte Size - " + file.getBytes().length);
	
	        Image img = new Image(file.getOriginalFilename(), file.getContentType(),
	
	                compressBytes(file.getBytes()));
	
	        imageRepository.save(img);
	        
	        return new ResponseEntity<String>("Successful",HttpStatus.ACCEPTED);
	
	    }
	
	
	    @GetMapping("/get")
	    public Image getImage(@RequestParam() String imageName) throws IOException {
	System.out.println("ImageName="+imageName);
	        final Optional<Image> retrievedImage = imageRepository.findByName(imageName);
	if(retrievedImage.isPresent())
		System.out.println("Image is present");
	
	        Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
	
	                decompressBytes(retrievedImage.get().getPicByte()));

	        return img;
	
	    }
	
	
	    // compress the image bytes before storing it in the database
	
	    public static byte[] compressBytes(byte[] data) {
	
	        Deflater deflater = new Deflater();
	
	        deflater.setInput(data);
	
	        deflater.finish();
	
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	
	        byte[] buffer = new byte[1024];
	
	        while (!deflater.finished()) {
	
	            int count = deflater.deflate(buffer);
	
	            outputStream.write(buffer, 0, count);
	
	        }
	
	        try {
	
	            outputStream.close();
	
	        } catch (IOException e) {
	        		e.printStackTrace();
	        }
	
	        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
	
	        return outputStream.toByteArray();
	
	    }
	
	
	    // uncompress the image bytes before returning it to the angular application
	
	    public static byte[] decompressBytes(byte[] data) {
	
	        Inflater inflater = new Inflater();
	
	        inflater.setInput(data);
	
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	
	        byte[] buffer = new byte[1024];
	
	        try {
	
	            while (!inflater.finished()) {
	
	                int count = inflater.inflate(buffer);
	
	                outputStream.write(buffer, 0, count);
	
	            }
	
	            outputStream.close();
	
	        } catch (IOException ioe) {
	        		ioe.printStackTrace();
	        } catch (DataFormatException e) {
	e.printStackTrace();
	        }
	System.out.println("Decompress bytes "+outputStream.toByteArray().length);
	        return outputStream.toByteArray();
	
	    }
	
	}
	

