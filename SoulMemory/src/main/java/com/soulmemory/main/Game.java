package com.soulmemory.main;
import java.util.ArrayList;

public class Game 
{
	protected String name;
	protected ArrayList<Area> areas = new ArrayList<Area>();
	// protected Image gameImages;
	
	Game(String name, DataFile areas, DataFile enemies, DataFile bosses)
	{
		this.name = name;
		
		for (int i = 0; i < areas.getLines().size(); i++)
		{
			this.areas.add(new Area(areas.getLines().get(i), enemies, bosses));
		}
	}
	
	String getName()
	{
		return name;
	}
	
	ArrayList<Area> getAreas()
	{
		return areas;
	}
	
	public String toString()
	{
		String result = "[GAME] " + name + " has the following areas:\n";
		
		for (int i = 0; i < areas.size(); i++)
		{
			result += "\t" + areas.get(i).getName() + "\n";
		}
		
		return result;
	}
}
