package com.example.StarCraftIIResults;

public class Player {

	private String Name;
	private String Race;
	private Integer Icon;
	
	/**
	 * @return the race
	 */
	public String getRace() {
		return Race;
	}
	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		Race = race;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the icon
	 */
	public Integer getIcon() {
		return Icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(Integer icon) {
		Icon = icon;
	}
}
