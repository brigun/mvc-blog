package blog.forms;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import blog.models.User;

public class CreatePostForm {
	
	@NotNull
	@Max(40)
	private String title;
	
	@NotEmpty(message="You cannot leave this field empty.")
	private String body;
	
	
	private User user;
	
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User author)
	{
		this.user = author;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getBody()
	{
		return body;
	}
	
	public void setBody(String body)
	{
		this.body = body;
	}
}
