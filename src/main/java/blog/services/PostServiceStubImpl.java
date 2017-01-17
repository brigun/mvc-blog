package blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import blog.models.Post;
import blog.models.User;

@Service
public class PostServiceStubImpl implements PostService{
	
	private List<Post> posts= new ArrayList<Post>() {{
		add (new Post(1L,"First Post", "<p>Line #1.</p><p>Line #2</p>", null));
		add (new Post(2L, "Second Post", "Second Post content: <ul><li>line "
				+ "1</li><li>Line 2</li></p>",
				new User(10L, "pesho10", "Peter Ivanov")));
		add (new Post(3L, "Post 3", "<p>the post number 3 nice</p>",
				new User(10L, "merry", null)));
		add (new Post(4L, "Fourth Post", "<p>not an interesting post</p>", null));
		add (new Post(5L,"Post Number 5", "<p>just posting</p>", null));
		add (new Post(6L, "Sixth Post", "<p>another Interesting Post</p>", null));
	}};

	public List<Post> findAll() {
		// TODO Auto-generated method stub
		return posts;
	}
	@Override
	public List<Post> findLatest5() {
		// TODO Auto-generated method stub
		return this.posts.stream()
				.sorted((a,b) -> b.getDate().compareTo(a.getDate()))
				.limit(5)
				.collect(Collectors.toList());
	}
	@Override
	public Post findById(long id) {
		// TODO Auto-generated method stub
		return this.posts.stream()
				.filter(p -> Objects.equals(p.getId(), id))
				.findFirst()
				.orElse(null);
	}
	@Override
	public Post create(Post post) {
		// TODO Auto-generated method stub
		post.setId(this.posts.stream().mapToLong(
				p -> p.getId()).max().getAsLong() + 1);
		this.posts.add(post);
		return post;
	}
	@Override
	public Post edit(Post post) {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.posts.size(); i++)
		{
			if(Objects.equals(this.posts.get(i).getId(), post.getId()))
			{
				this.posts.set(i, post);
				return post;
			}
		}
		throw new RuntimeException("Post not found: " + post.getId());
	}
	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.posts.size(); i++)
		{
			if(Objects.equals(this.posts.get(i).getId(), id))
			{
				this.posts.remove(i);
				return;
			}
		}
		throw new RuntimeException("Post not found: " + id);
		
	}
	@Override
	public boolean create(String title, String body) {
		// TODO Auto-generated method stub
		if (title != null && body != null){return true;}
		return false;
	}

	
}
