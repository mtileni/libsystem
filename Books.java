/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author bruce
 */
public class Books implements Serializable
{
    private BookSubCategory id;
    private int idBook;
    private String bookName;
    private String bookAuthor;
    private boolean bookStatus;
    private int numberOfBooks;
    private BookImage image;
    
    public Books()
    {
    }
    
    public BookSubCategory getId()
    {
        return id;
    }

    public void setId(BookSubCategory id) 
    {
        this.id = id;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public String getBookAuthor()
    {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor)
    {
        this.bookAuthor = bookAuthor;
    }

    public boolean isBookStatus()
    {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus)
    {
        this.bookStatus = bookStatus;
    }

    public int getNumberOfBooks() 
    {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) 
    {
        this.numberOfBooks = numberOfBooks;
    }

    public int getIdBook() 
    {
        return idBook;
    }

    public void setIdBook(int idBook)
    {
        this.idBook = idBook;
    }

    public BookImage getImage() 
    {
        return image;
    }

    public void setImage(BookImage image)
    {
        this.image = image;
    }
    
    
}
