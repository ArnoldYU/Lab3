package action;

import java.util.List;

import com.opensymphony.xwork2.Action;

import  domin.Author;
import  service.AuthorService;

public class ViewAuthorAction implements Action {

	private List<Author> authors;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		AuthorService au = new AuthorService();
		
		setAuthors(au.getAllAuthors());
		return SUCCESS;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	

}
