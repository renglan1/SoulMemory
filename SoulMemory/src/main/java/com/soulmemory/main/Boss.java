package com.soulmemory.main;

public final class Boss extends Enemy
{
	private boolean isMiniboss;
	
	Boss(String name, boolean isMiniboss)
	{
		super(name);
		this.isMiniboss = isMiniboss;
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
	
	boolean getIsMiniboss()
	{
		return isMiniboss;
	}
	
	public String toString()
	{
		String result = super.toString();
		
		if (isMiniboss)
		{
			result += "(miniboss)";
		}
		
		return result;
	}
}
