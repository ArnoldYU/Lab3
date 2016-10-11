package service;

import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import domin.Author;
import domin.Book;

public class BookService {

	public Integer validateBook(Book book){
		return getAll(book);
	}
	public int addBook(Book book) {
		PreparedStatement str;
		Connection conn = getConn();
		String sql = "insert into books (authorID,publiser,publiserDate,price,title,isbn) values(?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    int yes=1;
	    try {
	    	str = (PreparedStatement)conn.prepareStatement("select * from books");
	    	ResultSet rs = str.executeQuery();
	    	while(rs.next()){
	    		if(book.getIsbn().equals(rs.getString(6))){
	    			yes=0;
	    			break;
	    		}
	    	}
	    	if(yes==1){
	    		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        pstmt.setString(1, book.getAuthorID());
		        pstmt.setString(2, book.getPubliser());
		        pstmt.setString(3, book.getPubliserDate());
		        pstmt.setDouble(4, book.getPrice());
		        pstmt.setString(5, book.getTitle());
		        pstmt.setString(6, book.getIsbn());
		        pstmt.executeUpdate();
		        pstmt.close();
		        System.out.println(searchAuthor(book));
		        return searchAuthor(book);
	    	}
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return -1;
	    
	}
	public List<Book> getAllBooks() {
		List<Book> bookDb;
		bookDb=new ArrayList<>();
		Connection conn = getConn();
	    String sql = "select * from books";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        bookDb.clear();
	        while(rs.next()){
	        	bookDb.add(new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getString(6)));
	        }	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return bookDb;
	}
	//打开数据库
	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://ksctbymotdft.rds.sae.sina.com.cn:10409/author";
	    String username = "root";
	    String password = "501874997";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	private static Integer getAll(Book book) {
	    Connection conn = getConn();
	    String sql = "select * from books";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	if(rs.getString(1).equals(book.getAuthorID()))
	        		return 1;
	        }
	        return -1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	private static Integer searchAuthor(Book book){
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://ksctbymotdft.rds.sae.sina.com.cn:10409/author";
	    String username = "root";
	    String password = "501874997";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    String sql = "select * from authors";
	    System.out.println("11111111111111111111");
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	System.out.print(rs.getString(1));
	        	if(rs.getString(1).equals(book.getAuthorID()))
	        		return 1;
	        }
	        return -1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	public List<Book> getAuthorBooks(Author author) {
		List<Book> authorbookDb;
		authorbookDb=new ArrayList<>();
		Connection conn = getConn();
	    String sql = "select * from books";
	    PreparedStatement pstmt;
	    try {
	    	authorbookDb.clear();
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	System.out.println(author.getAuthorID()+rs.getString(1));
	        	if(rs.getString(1).equals(author.getAuthorID())){
	        		System.out.println("here");
	        		
	        		authorbookDb.add(new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getString(6)));
	        	}
	        		
	        }	
	        return authorbookDb;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return authorbookDb;
	}
	public Integer changebook(Book book){
		Connection conn = getConn();
	    int i = 0,j=0,z=0;
	    String authorID=book.getAuthorID();
	    String Publiser=book.getPubliser();
	    String PubliserDate=book.getPubliserDate();
	    String ISBN =book.getIsbn();
	    String Book=book.getTitle();
	    double price=book.getPrice();
	    System.out.println(book.getTitle());
	   
	    String sql1 = "update books set authorID='" + authorID + "'where title='" + Book + "'";
	    String sql2 = "update books set publiser='" + Publiser + "'where title='" + Book + "'";
	    String sql3 = "update books set publiserDate='" + PubliserDate + "'where title='" +Book + "'";
	    String sql4 = "update books set price='" + price + "'where title='" +Book +"'";
	    String sql5 = "update books set isbn='" + ISBN + "'where title='" + Book+"'";
	    PreparedStatement pstmt1;
	    PreparedStatement pstmt2;
	    PreparedStatement pstmt3;
	    PreparedStatement pstmt4;
	    PreparedStatement pstmt5;
	    try {
	        pstmt1 = (PreparedStatement) conn.prepareStatement(sql1);
	        pstmt2 = (PreparedStatement) conn.prepareStatement(sql2);
	        pstmt3 = (PreparedStatement) conn.prepareStatement(sql3);
	        pstmt4 = (PreparedStatement) conn.prepareStatement(sql4);
	        pstmt5 = (PreparedStatement) conn.prepareStatement(sql5);
	        i = pstmt1.executeUpdate();
	        j = pstmt2.executeUpdate();
	        z = pstmt3.executeUpdate();
	        i = pstmt4.executeUpdate();
	        i = pstmt5.executeUpdate();
	        System.out.println("resutl:1 " + i+j+z);
	        pstmt1.close();
	        pstmt2.close();
	        pstmt3.close();
	        pstmt4.close();
	        pstmt5.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	public void deleteBook(Book book) {
		Connection conn = getConn();
		int i = 0;
		String booktitle=book.getTitle();
		//System.out.println("1"+booktitle);
		String sql = "delete from books where title='" + booktitle + "'";
		PreparedStatement pstmt;
		try {
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		i = pstmt.executeUpdate();
		System.out.println("resutlbook: " + i);
		pstmt.close();
		conn.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}

	}
	public Book searchbook(Book book) {
		Connection conn = getConn();
		int i = 0;
		Book onebook=new Book("null","null","null",0,"null","null");
		String booktitle=book.getTitle();
		System.out.println(booktitle);
		String sql = "select * from books";
		PreparedStatement pstmt;
		try {
			 pstmt = (PreparedStatement)conn.prepareStatement(sql);
		     ResultSet rs = pstmt.executeQuery();
		     while(rs.next()){
		    	 if(rs.getString(5).equals(book.getTitle())){
		    		 onebook=new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getString(6));
		    		 return onebook;
		    	 }
		    }	
		    return onebook;
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return null;
	}
}
