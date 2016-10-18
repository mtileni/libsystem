package model;

import java.io.Serializable;

public class UserBean implements Serializable
{
	private String username;
	private String password;
	private String userLevel;
        private Person person;
		//	new String[ROW][COL];
	
	
	public UserBean()
	{
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param username
	 * @param password
	 * @param userLevel
	 */
	public UserBean(String username, String password, String userLevel)
	{
		this.username = username;
		this.password = password;
		this.userLevel = userLevel;
	}

    public Person getPerson() 
    {
        return person;
    }

    public void setPerson(Person person) 
    {
        this.person = person;
    }
        
	/**
	 * @return the userLevel
	 */
	public String getUserLevel()
	{
		return userLevel;
	}
	/**
	 * @param userLevel the userLevel to set
	 */
	public void setUserLevel(String userLevel) 
	{
		this.userLevel = userLevel;
	}
	/**
	 * @return the username
	 */
	public String getUsername() 
	{
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() 
	{
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
}
