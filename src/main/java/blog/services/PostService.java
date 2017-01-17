package blog.services;

import java.util.List;

import blog.models.Post;

public interface PostService {

	List<Post> findAll();
	List<Post> findLatest5();
	Post findById(long id);
	Post create(Post post);
	boolean create(String title, String body);
	Post edit(Post post);
	void deleteById(long id);
	
}
