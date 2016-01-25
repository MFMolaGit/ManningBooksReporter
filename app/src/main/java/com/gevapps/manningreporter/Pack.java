package com.gevapps.manningreporter;
import java.util.*;

public class Pack {
	private String id;
	private Integer times;
	private List<Book> books;

	public void setBooks(List<Book> books)
	{
		this.books = books;
	}

	public List<Book> getBooks()
	{
		return books;
	}

	public void setTimes(Integer times)
	{
		this.times = times;
	}

	public Integer getTimes()
	{
		return times;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}}
