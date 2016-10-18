/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author bruce
 */
public class BookImage 
{
    private String fileName;
    private InputStream file;
    private String contentType;
    private byte[] loadImage;
    private String imageBytes;

    public String getImageBytes() 
    {
        return imageBytes;
    }

    public void setImageBytes(String imageBytes) 
    {
        this.imageBytes = imageBytes;
    }
   
    public InputStream getFile() 
    {
        return file;
    }

    public void setFile(InputStream file)
    {
        this.file = file;
    }

    public byte[] getLoadImage() 
    {
        return loadImage;
    }

    public void setLoadImage(byte[] loadImage) 
    {
        this.loadImage = loadImage;
    }

    public String getFileName() 
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getContentType() 
    {
        return contentType;
    }

    public void setContentType(String contentType) 
    {
        this.contentType = contentType;
    }
}
