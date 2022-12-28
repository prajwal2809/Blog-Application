package com.prajwal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prajwal.entity.Comment;
import com.prajwal.entity.Post;
import com.prajwal.entity.Tags;
import com.prajwal.service.CommentService;
import com.prajwal.service.PostService;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
//	@Autowired
//	private TagService tagService;
	
	@RequestMapping("/")
	public String listPosts(Model theModel) {
		
		List<Post> thePosts=postService.getPosts();
		List<String> authorList=postService.getAuthorList();
		List<String> tagList=postService.getTagList();
		theModel.addAttribute("posts", thePosts);
		theModel.addAttribute("authorList", authorList);
		theModel.addAttribute("tagList", tagList);
		return "list-posts";			
	}
	@GetMapping("/newpost")
	public String showNewEditPost(Model theModel) {
		
		Post thePost=new Post();
		theModel.addAttribute("post", thePost);
		Tags tags=new Tags();
		theModel.addAttribute("tagnames",tags );
		return "newedit-form";
	}
	@PostMapping("/savePost")
	public String savePost(@ModelAttribute("post") Post thePost,@RequestParam("id") int id,@RequestParam("publish") String submitType,@RequestParam("tagNames") String tagNames) {
	
//		int postId=postService.addPost(thePost,id);
		postService.savePost(thePost,id,submitType);
		postService.saveTags(tagNames, thePost.getId());
		
//		System.out.println(tagNames);
		System.out.println(id);
		System.out.println(thePost.getId());
		
//		tagService.createTags(tagNames);
		return "redirect:/";
	}
	@GetMapping("/showPost")
	public String showPost(@RequestParam("postId") int theID,Model theModel) {

		Post post = postService.showPost(theID);
		theModel.addAttribute("post", post);
		
		return "show-post";
	}
	@RequestMapping("/updatePost")
	public String updatePost(@RequestParam("postId") int theID,Model theModel) {;
		
		
		theModel.addAttribute(postService.showPost(theID));
	
		return "newedit-form";
	}
	@RequestMapping("/deletePost")
	public String deletePost(@RequestParam("postId") int theID,Model theModel) {
		postService.deletePost(theID);
		return "redirect:/";
		}
	
	@RequestMapping("/createPost")
	public String createPost(Model theModel) {
		theModel.addAttribute("post",new Post());
//		theModel.addAttribute("tags",new Tags());
		return "newedit-form";
	}
	
	@RequestMapping("/comment")
	public String comment(Model theModel, @RequestParam("id") int id) {
		theModel.addAttribute("comment", new Comment());
		return "newedit-comment";
		
	}
	@RequestMapping("/addComment")
	public String addComment(@ModelAttribute("comment")Comment comment,@RequestParam("id") int id,Model model,@RequestParam("commentId") int commentId) {

		commentService.addComment(comment, id,commentId);
		model.addAttribute("post",postService.showPost(id));  
        model.addAttribute("comment",commentService.showComment(commentId));
        
		return "show-post" ;
	}
	@RequestMapping("/deleteComment")
	public String deleteComment(@RequestParam("commentId")int id, Model theModel,@RequestParam("postId") int postId) {
		commentService.deleteComment(id);
		theModel.addAttribute("post",postService.showPost(postId));
		return "show-post";
	}
	@RequestMapping("/updateComment")
	
	public String updateComment(@RequestParam("id") int id,Model theModel) {
		theModel.addAttribute("comment",commentService.showComment(id));
		return "newedit-comment";
	}	
    @RequestMapping("/search")
    public String searchPosts(@RequestParam("searchName") String searchName, Model theModel) {
        List<Post> thePost = postService.searchPosts(searchName);
        theModel.addAttribute("posts", thePost);
        return "list-posts";        
    }
//	@RequestMapping("/sort")
//	public String sortPost(@RequestParam("sortBy") String sortBy,Model model) {
//		List<Post> posts=this.postService.sortPosts(sortBy);
//		model.addAttribute("posts",posts);
//		return "post-confirmation";	
//		}
    @RequestMapping("/sortPost")
    public String sortPost(@RequestParam("sortBy") String value, Model theModel) {
		List<Post> listOfPosts = null;
		if(value != null) {
			listOfPosts = postService.getListOfPosts(value);
		}
		else {
			listOfPosts = postService.getListOfPosts("asc");
		}
		
		theModel.addAttribute("posts", listOfPosts);
    	return "list-posts";
    }
	@RequestMapping("/filter")
	public String filter(@RequestParam("author") String authorName,Model model,@RequestParam("tag") String tagName) {
//		List<Post> posts=postService.searchPosts(name);
//		model.addAttribute("posts",posts);
//		System.out.println(name);
		List<Post> postList=postService.filterPost(authorName,tagName);
		List<String> authorList=postService.getAuthorList();
		List<String> tagList=postService.getTagList();
		model.addAttribute("posts", postList);
		model.addAttribute("authorList", authorList);
		model.addAttribute("tagList", tagList);
		return "list-posts";		
	}
}

