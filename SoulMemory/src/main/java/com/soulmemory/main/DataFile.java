package com.soulmemory.main;
import java.io.*;
import java.util.*;

public final class DataFile 
{
	private String name;
	private String path;
	private ArrayList<String> lines = new ArrayList<String>();
	
	public DataFile(String name, String path)
	{
		this.name = name;
		this.path = path + "\\" + name + ".txt";

		try
		{
			read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	String getName()
	{
		return name;
	}
	
	String getPath()
	{
		return path;
	}
	
	ArrayList<String> getLines()
	{
		return lines;
	}
	
	void read() throws IOException
	{
		Scanner scan = new Scanner(new File(path));
		
		while (scan.hasNext())
		{
			lines.add(scan.nextLine());
		}
		scan.close();
	}
	
	public String toString()
	{
		String result = "Data file \"" + name + "\" contains text:\n";
		
		for (int i = 0; i < lines.size(); i++)
		{
			result = lines.get(i) + "\n";
		}
		
		return result;
	}
}
