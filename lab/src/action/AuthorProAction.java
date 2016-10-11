package action;

import com.opensymphony.xwork2.Action;

import domin.Author;
import service.AuthorService;

public class AuthorProAction implements Action {
	
	private Author author;
	
	
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		AuthorService au=new AuthorService();
		Integer i =au.validateAuthor(author);
		if (i>0)
			return SUCCESS;
		return ERROR;
	}

	public String regist(){
		AuthorService au=new AuthorService();
		au.addAuthor(author);
		return SUCCESS;
	}
	public String change(){
		AuthorService au=new AuthorService();
		Integer i=au.changeAuthor(author);
		if (i>0)
			return SUCCESS;
		return ERROR;
	}
	public String delete(){
		AuthorService au=new AuthorService();
		au.deleteAuthor(author);
		return SUCCESS;
	}
	
	
}
