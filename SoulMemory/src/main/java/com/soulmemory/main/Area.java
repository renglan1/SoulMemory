package com.soulmemory.main;
import java.util.ArrayList;

public final class Area 
{
	private String name;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Boss> bosses = new ArrayList<Boss>();
	
	Area(String name, DataFile enemies, DataFile bosses)
	{
		this.name = name;
		load(enemies);
		load(bosses);
	}
	
	String getName()
	{
		String name = "";
		String[] parts = this.name.split("_");
		
		//for (int i = 0; i < SoulMemory.occurencesOf(this.name, "_")+1; i++)
		//{
		//	name += parts[i] + " ";
		//}
		
		return name;
	}
	
	void load(DataFile file)
	{
		String[] lines = new String[file.getLines().size()];
		for (int i = 0; i < file.getLines().size(); i++)
		{
			if (file.getLines().get(i).contains(name))
			{
				lines = file.getLines().get(i).split(" ");
				
				if (file.getName().equals("bosses"))
				{
					bosses.add(new Boss(lines[0], Boolean.parseBoolean(lines[2])));
				}
				else if (file.getName().equals("enemies"))
				{
					enemies.add(new Enemy(lines[0]));
				}
			}
		}
	}
	
	ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}
	
	ArrayList<Boss> getBosses()
	{
		return bosses;
	}
	
	public String toString()
	{
		String result = "[AREA] " + getName() + "has the following enemies:\n";
		
		for (int i = 0; i < enemies.size(); i++)
		{
			result += "\t" + enemies.get(i).getName() + "\n";
		}
		
		result += "\n[AREA] " + getName() + "has the following bosses:\n";
		
		for (int i = 0; i < bosses.size(); i++)
		{
			result += "\t" + bosses.get(i).toString() + "\n";
		}
		
		return result;
	}
}
