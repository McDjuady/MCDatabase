package com.gmail.mcdjuady.mcdatabase.bridges;


import org.bukkit.Material;

import com.gmail.mcdjuady.mcdatabase.DatabaseGroup;

public class DatabaseMaterialBridge implements DatabaseBridge<Material>{

	@Override
	public Material loadAndTranslate(DatabaseGroup group) {
		return Material.valueOf(group.getString("value"));
	}

	@Override
	public boolean canTranslate(Object o) {
		return (o instanceof Material);
	}

	@Override
	public boolean canTranslate(DatabaseGroup group) {
		String clssName = group.getString("class");
		return (clssName.equals("Material"));
	}

	@Override
	public void translateAndSave(Material t, DatabaseGroup group) {
		group.addString("class", "Material");
		group.addString("value", t.toString());
	}

}