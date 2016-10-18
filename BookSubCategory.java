/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author bruce
 */
public class BookSubCategory implements Serializable
{
    private BookCategory id;
    private String subCategory;
    private int idSubCategory;

    public BookSubCategory() 
    {
    }
    
    public BookSubCategory(BookCategory id, String subCategory, int idSubCategory)
    {
        this.id = id;
        this.subCategory = subCategory;
        this.idSubCategory = idSubCategory;
    }
    
    public BookCategory getId() 
    {
        return id;
    }

    public void setId(BookCategory id) 
    {
        this.id = id;
    }

    public String getSubCategory() 
    {
        return subCategory;
    }

    public void setSubCategory(String subCategory) 
    {
       
            this.subCategory = subCategory;
        
    
    }

    public int getIdSubCategory() 
    {
        return idSubCategory;
    }

    public void setIdSubCategory(int idSubCategory) 
    {
        this.idSubCategory = idSubCategory;
    }
    
}
