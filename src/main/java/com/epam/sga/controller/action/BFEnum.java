package com.epam.sga.controller.action;

public enum BFEnum {
	LOAD("load"), SORT_GRID_BY_MAKE("SortTableFunction");
	private String name;
	BFEnum(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public static BFEnum getByName(String name)
	{
		BFEnum result = null;
		for(BFEnum v : BFEnum.values())
		{
			if(v.getName().equals(name))
			{
				result = v;
			}
		}
		
		return result;
	}
}
