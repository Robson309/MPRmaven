package com.pl.robert;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Publisher {
	private static Logger logger = Logger.getLogger(Person.class);
	
	public Integer id;
	public String name;
	public String urlpublisher;
		
	public List<Publisher> publisherList = new ArrayList<Publisher>();
	 
	public Publisher() {}
	
	public Publisher(Integer id, String name, String urlpublisher)
	{
		this.id = id;
		this.name = name;
		this.urlpublisher = urlpublisher;
	}
	
	public Publisher(String name, String urlpublisher, List<Publisher> publisherList)
	{
		this.name = name;
		this.urlpublisher = urlpublisher;
		this.publisherList = publisherList;
	}
	
	public void showPublisher()
	{
		for(Publisher p : this.publisherList)
		{
			p.showPublishers();
		}
	}

	public void showPublishers() {
		System.out.println("name " + name + " urlpublisher " + urlpublisher);
	}

	public void printPublisher() {
		for (Publisher p : publisherList) {
			p.showPublisher();
		}
	}

	public void addPublisher(Publisher p) {
		publisherList.add(p);
	}

	public void removePublisher(Publisher publisher) {		
		publisherList.remove(publisher);
		}
		

	public void clearPublisherList() {
		publisherList.clear();
	}


	public Publisher searchPublisherName(String name) {
		for (Publisher publisher : publisherList) {
			if (publisher.getName().equals(name)) {
				return publisher;
			}
		}
		return null;
	}
	
	public List<Publisher> findPublisherAll(String Name) {
		List<Publisher> resultsList= new ArrayList<Publisher>();
		for (Publisher publisher : publisherList) {
			if (publisher.getName().equals(Name)) {
				resultsList.add(publisher);
			}
		}
		return resultsList;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlPublisher() {
		return urlpublisher;
	}
	
	public void setUrlPublisher(String urlpublisher) {
		this.urlpublisher = urlpublisher;
	}

	public List<Publisher> getPublisherList() {
		return publisherList;
	}

	public void setPublisherList(List<Publisher> publisherList) {
		this.publisherList = publisherList;
	}
}
