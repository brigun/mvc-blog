package blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.forms.CreatePostForm;
import blog.models.Post;
import blog.services.NotificationService;
import blog.services.PostService;

@Controller
public class PostsController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private NotificationService notifyService;
	
	@RequestMapping("/posts/view/{id}")
	public String view(@PathVariable("id") Long id, Model model)
	{
		Post post = postService.findById(id);
		if(post == null)
		{
			notifyService.addErrorMessage("Cannot find post #" + id);
			return "redirect:/";
		}
		model.addAttribute("post", post);
		return "posts/view";
	}
	
	@RequestMapping("/posts/index")
	public String fullList(Model model)
	{
		List<Post> indexList = postService.findAll();
		model.addAttribute("indexList", indexList);
		return "posts/index";
		
	}
	
	@RequestMapping("/posts/create")
	public String createPost(CreatePostForm createPostForm)
	{
		return "posts/create";
	}
	
	@RequestMapping(value = "/posts/create", method = RequestMethod.POST)
	public String createPostPage(@Valid CreatePostForm createPostForm, 
										BindingResult bindingResult)
	{
		Post nextPost = new Post();
		if(!postService.create(
				createPostForm.getTitle(),
				createPostForm.getBody()))
		{
			notifyService.addErrorMessage("Invalid post submission");
			return "posts/create";
		}
		notifyService.addInfoMessage("Post created successfully!");
		nextPost.setTitle(createPostForm.getTitle());
		nextPost.setBody(createPostForm.getBody());
		postService.create(nextPost);
		
		return"redirect:/";
	}
	
	@RequestMapping("/posts/delete/{id}")
	public String deletePost(@PathVariable("id") Long id, Model model)
	{
		Post post = postService.findById(id);
		if(post == null)
		{
			notifyService.addErrorMessage("Cannot find post #" + id);
			return "redirect:/";
		}
		model.addAttribute("post", post);
		return "posts/delete";
	}
	
	@RequestMapping(value = "/posts/delete/{id}", method = RequestMethod.POST)
	public String deletePostPage(Post post)
	{
		
		Long postId = post.getId();
		postService.deleteById(postId);
		notifyService.addInfoMessage("Post Deleted Successfully");
		return"redirect:/";
	}
	
	@RequestMapping("/posts/edit/{id}")
	public String editPost(@PathVariable("id") Long id, Model model)
	{
		Post post = postService.findById(id);
		if(post == null)
		{
			notifyService.addErrorMessage("Cannot find post #" + id);
			return "redirect:/";
		}
		model.addAttribute("post", post);
		return "posts/edit";
	}
	
	@RequestMapping(value = "/posts/edit/{id}", method = RequestMethod.POST)
	public String editPostPage(Post post)
	{
		postService.edit(post);
		notifyService.addInfoMessage("Post Successfully Updated");
		return"redirect:/";
	}

}
