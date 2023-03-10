/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Awareness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Light;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSight;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MindVision;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.RevealedArea;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Blacksmith;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Imp;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Wandmaker;

import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.EnergyCrystal;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap.Type;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.*;
// import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.GoldenKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.CeremonialCandle;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.CorpseDust;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.Embers;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.Pickaxe;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.*;
// import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.*;
// import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;

import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.levels.CavesLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.CityLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.DeadEndLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.HallsLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.LastLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.CavesBossLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.CityBossLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.HallsBossLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.PrisonBossLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.PrisonLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.RegularLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.SewerBossLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.SewerLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.LevelTransition;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.secret.SecretRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.SpecialRoom;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Toolbar;
import com.shatteredpixel.shatteredpixeldungeon.utils.BArray;
import com.shatteredpixel.shatteredpixeldungeon.utils.DungeonSeed;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndResurrect;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.SparseArray;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;
import java.util.HashMap;
import java.lang.Integer;
import java.util.Iterator;

public class SeedFinder {

	private long storage_seed;

	public String[] addDungeon(int floors, String seed, ArrayList<String> seek){
		StringBuilder record = new StringBuilder();
		String[] data = new String[2];
		if(seek.size()>0){
			int x = 0;
			int max = 400;
			while (x<max){
				if(FindLevel(seek)) break;
				x++;
			}
			record.append("????????????"+(x)+"\n");		
			SPDSettings.customSeed(DungeonSeed.convertToCode(storage_seed)); //??????????????????	
		}else
			SPDSettings.customSeed(seed); //??????????????????
		
		GamesInProgress.selectedClass = HeroClass.WARRIOR; //??????????????????
		Dungeon.init(); //???????????????
		storage_seed = Dungeon.seed;
		Dungeon.challenges = SPDSettings.challenges();//?????????????????????

		//????????????4?????????????????????
		for (int i = 0; i < floors; i++) {
			Level l = Dungeon.newLevel();
			LevelInfo(l,record);
			Dungeon.depth++;
		}
		record.append("\n");
		//??????????????????
		QuestInfo(record);
		
		data[0] = record.toString();
		data[1] = DungeonSeed.convertToCode(storage_seed);// ????????????
		return data;
	}

	private void addRecord(String type, ArrayList<Item> items, StringBuilder record){
		if (!items.isEmpty()) {
			record.append(""+type+": ");
			for (Item item : items) {
				record.append(item.name()+item.level()+"  ");
			}
			record.append("\n");
		}
	}
	private void LevelInfo(Level l, StringBuilder record){
		//???????????? ?????? ?????? ?????? ?????? ??????
		ArrayList<Heap> heaps = new ArrayList<>(l.heaps.valueList()); //???????????????
		ArrayList<Item> equipment = new ArrayList<>(); //?????? ?????? ??????
		ArrayList<Item> rings = new ArrayList<>(); //??????
		ArrayList<Item> artifacts = new ArrayList<>(); //??????
		ArrayList<Item> wands = new ArrayList<>(); //??????
		int i = 0;//????????????????????????????????????

		for (Heap h : heaps) {
			for (Item item : h.items) {
				item.identify();
				if (h.type == Type.FOR_SALE) continue;
				else if (item instanceof MeleeWeapon || item instanceof Armor){i++;equipment.add(item);}//???????????????
				else if (item instanceof Ring) {i++;rings.add(item);}//??????
				else if (item instanceof Artifact) {i++;artifacts.add(item);}//??????
				else if (item instanceof Wand) {i++;wands.add(item);}//??????
			}
		}
		if(i>0){
			record.append("[???"+Dungeon.depth+"???]\n");
			addRecord("??????",equipment,record);
			addRecord("??????",rings,record);
			addRecord("??????",artifacts,record);
			addRecord("??????",wands,record);
		}
	}
	private int FindSeek(ArrayList<String> seek, ArrayList<Item> items){
		int size = 0;
		Iterator<Item> it = items.iterator();
		for(String s:seek){
			while (it.hasNext()) {  
		        Item item = it.next();  
		        if (s.equalsIgnoreCase(item.getClass().getSimpleName())) {  
		        	size++;
		            it.remove();
		            break;  
		        }  
		    } 
		}
		return size;
	}
	private boolean FindLevel(ArrayList<String> seek){
		SPDSettings.customSeed(""); //??????????????????
		GamesInProgress.selectedClass = HeroClass.WARRIOR; //??????????????????
		Dungeon.init(); //???????????????
		storage_seed = Dungeon.seed;
		Dungeon.challenges = SPDSettings.challenges();//?????????????????????

		//???????????? ?????? ?????? ?????? ?????? ??????
		ArrayList<Item> rings = new ArrayList<>(); //??????
		ArrayList<Item> artifacts = new ArrayList<>(); //??????
		ArrayList<Item> wands = new ArrayList<>(); //??????
		for (int i = 0; i < 4; i++) {
			Level l = Dungeon.newLevel();
			ArrayList<Heap> heaps = new ArrayList<>(l.heaps.valueList()); //???????????????
			for (Heap h : heaps) {
				for (Item item : h.items) {
					item.identify();
					if (h.type == Type.FOR_SALE) continue;
					else if (item instanceof Ring) rings.add(item);//??????
					else if (item instanceof Artifact) artifacts.add(item);//??????
					else if (item instanceof Wand) wands.add(item);//??????
				}
			}
			Dungeon.depth++;
		}
		int size = 0;
		size += FindSeek(seek,rings);
		size += FindSeek(seek,artifacts);
		size += FindSeek(seek,wands);

		//??????????????????
		if(size >= seek.size()){
			return true;
		}

		return false;
	}

	private void QuestInfo(StringBuilder record){
		ArrayList<Item> ghost = new ArrayList<>();
		ArrayList<Item> wandmaker = new ArrayList<>();
		ArrayList<Item> imp = new ArrayList<>();
		if (Ghost.Quest.armor != null) {
			ghost.add(Ghost.Quest.armor.identify());
			ghost.add(Ghost.Quest.weapon.identify());
			Ghost.Quest.complete();
			addRecord("??????????????????",ghost,record);
		}

		if (Wandmaker.Quest.wand1 != null) {
			wandmaker.add(Wandmaker.Quest.wand1.identify());
			wandmaker.add(Wandmaker.Quest.wand2.identify());
			Wandmaker.Quest.complete();
			addRecord("??????????????????",wandmaker,record);
		}

		if (Imp.Quest.reward != null) {
			imp.add(Imp.Quest.reward.identify());
			Imp.Quest.complete();
			addRecord("??????????????????",imp,record);
		}
	}
	public ArrayList<String> getData(String sj){
		return ItemData.split(sj);
	} 

	private static class ItemData {
		private static HashMap<String, String> items;
		static{
			items = new HashMap<String, String>();

			items.put("????????????", "RingOfAccuracy");
			items.put("????????????", "RingOfArcana");
			items.put("????????????", "RingOfElements");
			items.put("????????????", "RingOfEnergy");
			items.put("????????????", "RingOfEvasion");
			items.put("????????????", "RingOfForce");
			items.put("????????????", "RingOfFuror");
			items.put("????????????", "RingOfHaste");
			items.put("????????????", "RingOfMight");
			items.put("????????????", "RingOfSharpshooting");
			items.put("????????????", "RingOfTenacity");
			items.put("????????????", "RingOfWealth");

			items.put("???????????????", "WandOfBlastWave");
			items.put("????????????", "WandOfCorrosion");
			items.put("????????????", "WandOfCorruption");
			items.put("????????????", "WandOfDisintegration");
			items.put("????????????", "WandOfFireblast");
			items.put("????????????", "WandOfFrost");
			items.put("????????????", "WandOfLightning");
			items.put("????????????", "WandOfLivingeEarth");
			items.put("????????????", "WandOfMagicMissile");
			items.put("????????????", "WandOfPrismaticLight");
			items.put("????????????", "WandOfRegrowth");
			items.put("????????????", "WandOfTransfusion");
			items.put("????????????", "WandOfWarding");

			items.put("???????????????", "AlchemistsToolkit");
			items.put("????????????", "ChaliceOfBlood");
			items.put("????????????", "CloakOfShadows");
			items.put("????????????", "DriedRose");
			items.put("????????????", "EtherealChains");
			items.put("????????????", "HornOfPlenty");
			items.put("????????????", "MasterThievesArmband");
			items.put("????????????", "SandalsOfNature");
			items.put("????????????", "TalismanOfForesight");
			items.put("????????????", "TimekeepersHourglass");
			items.put("????????????", "UnstableSpellbook");			
		}

		//?????????
		public static ArrayList<String> split(String input){
			ArrayList<String> jg = new ArrayList<String>();
			if(input.isEmpty()) return jg; // ????????????????????????
			String value = "";
			for (String item: input.split(" ")){
		        value = items.get(item);
		        if(value != null){
		        	//?????????????????????????????????
		        	jg.add(value);
		        }
		    }
		    return jg;
		}
	}

}

// Messages.get(???????????????, "name");