package com.gmail.mcdjuady.mcdatabase.bridges;

import com.gmail.mcdjuady.mcdatabase.DatabaseGroup;
import org.bukkit.block.BlockFace;


public class DatabaseBlockFaceBridge implements DatabaseBridge<BlockFace>{

	@Override
	public void translateAndSave(BlockFace t, DatabaseGroup group) {
		group.addString("class", "BlockFace");
		group.addString("value", t.toString());
	}

	@Override
	public BlockFace loadAndTranslate(DatabaseGroup group) {
		return BlockFace.valueOf(group.getString("value"));
	}

	@Override
	public boolean canTranslate(Object o) {
		return (o instanceof BlockFace);
	}

	@Override
	public boolean canTranslate(DatabaseGroup group) {
		String clssName = group.getString("class");
		return (clssName.equals("BlockFace"));
	}

}
