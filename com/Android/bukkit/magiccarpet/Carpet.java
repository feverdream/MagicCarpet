package com.Android.bukkit.magiccarpet;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
* Magic Carpet 1.0
* Copyright (C) 2011 Android <spparr@gmail.com>
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

/**
* Carpet.java
* <br /><br />
* Defines the basic 5x5 carpet object that is placed underneath the player.
* 
*
* @author Android <spparr@gmail.com>
*/
public class Carpet {
	Location currentLoc = null;
	
	public Carpet(){}
	
	public class CarpetFiber
	{
		public CarpetFiber(int x, int y, int z, int type)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.type = type;
		}
		int x,y,z,type = 0;
		boolean imadeit = false;
	}
	//The basic 5x5 array of carpet fibers, all made out of glass
	public CarpetFiber[] fibers = {
			new CarpetFiber(2, 0, 2, 20),
			new CarpetFiber(2, 0, 1, 20),
			new CarpetFiber(2, 0, 0, 20),
			new CarpetFiber(2, 0, -1, 20),
			new CarpetFiber(2, 0, -2, 20),
			new CarpetFiber(1, 0, 2, 20),
			new CarpetFiber(1, 0, 1, 20),
			new CarpetFiber(1, 0, 0, 20),
			new CarpetFiber(1, 0, -1, 20),
			new CarpetFiber(1, 0, -2, 20),
			new CarpetFiber(0, 0, 2, 20),
			new CarpetFiber(0, 0, 1, 20),
			new CarpetFiber(0, 0, 0, 20),
			new CarpetFiber(0, 0, -1, 20),
			new CarpetFiber(0, 0, -2, 20),
			new CarpetFiber(-1, 0, 2, 20),
			new CarpetFiber(-1, 0, 1, 20),
			new CarpetFiber(-1, 0, 0, 20),
			new CarpetFiber(-1, 0, -1, 20),
			new CarpetFiber(-1, 0, -2, 20),
			new CarpetFiber(-2, 0, 2, 20),
			new CarpetFiber(-2, 0, 1, 20),
			new CarpetFiber(-2, 0, 0, 20),
			new CarpetFiber(-2, 0, -1, 20),
			new CarpetFiber(-2, 0, -2, 20)
			};
	//Goes through a 5x5 grid of the area underneath the player, and if the block is glass that is part of the magic carpet, it is removed
	public void removeCarpet(World wo) {
		if (currentLoc == null)
			return;
		for(int i = 0; i < fibers.length; i++)
		{
			Block bl = wo.getBlockAt((int)Math.floor(currentLoc.getX()) + fibers[i].x, (int)Math.floor(currentLoc.getY()) - fibers[i].y, (int)Math.floor(currentLoc.getZ()) + fibers[i].z);
			if (fibers[i].imadeit && bl.getTypeId() == 20) bl.setTypeId(0);
			fibers[i].imadeit = false;
		}
	}
	
	//Places glass in a 5x5 area underneath the player if the block was just air previously
	public void drawCarpet(World wo) {
		Block bl = null;
		for(int i = 0; i < fibers.length; i++)
		{
			bl = wo.getBlockAt((int)Math.floor(currentLoc.getX()) + fibers[i].x, (int)Math.floor(currentLoc.getY()) - fibers[i].y, (int)Math.floor(currentLoc.getZ()) + fibers[i].z);
			if (bl.getTypeId() == 0) {
				fibers[i].imadeit = true;
				bl.setTypeId(fibers[i].type);
			} else {
				fibers[i].imadeit = false;
			}
		}
	}
}
