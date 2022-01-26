package com.soulmemory.main;

public class Enemy
{
	protected String name;
	
	Enemy(String name)
	{
		this.name = name;
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
	
	public String toString()
	{
		return "[ENEMY] " + getName();
	}
}
