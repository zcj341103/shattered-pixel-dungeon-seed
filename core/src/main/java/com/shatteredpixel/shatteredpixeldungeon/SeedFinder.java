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
			record.append("查找次数"+(x)+"\n");		
			SPDSettings.customSeed(DungeonSeed.convertToCode(storage_seed)); //设置地牢种子	
		}else
			SPDSettings.customSeed(seed); //设置地牢种子
		
		GamesInProgress.selectedClass = HeroClass.WARRIOR; //默认英雄战士
		Dungeon.init(); //初始化地牢
		storage_seed = Dungeon.seed;

		//默认查看4层查看物品数据
		for (int i = 0; i < floors; i++) {
			Level l = Dungeon.newLevel();
			record.append("[第"+(i+1)+"层]\n");
			LevelInfo(l,record);
			Dungeon.depth++;
		}
		record.append("\n");
		//查看任务奖励
		QuestInfo(record);
		
		data[0] = record.toString();
		data[1] = DungeonSeed.convertToCode(storage_seed);// 转字符串
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
		//要的数据 武器 护甲 戒指 神器 法杖
		ArrayList<Heap> heaps = new ArrayList<>(l.heaps.valueList()); //散布的物品
		ArrayList<Item> equipment = new ArrayList<>(); //装备 武器 护甲
		ArrayList<Item> rings = new ArrayList<>(); //戒指
		ArrayList<Item> artifacts = new ArrayList<>(); //神器
		ArrayList<Item> wands = new ArrayList<>(); //法杖
		for (Heap h : heaps) {
			for (Item item : h.items) {
				item.identify();
				if (h.type == Type.FOR_SALE) continue;
				else if (item instanceof MeleeWeapon || item instanceof Armor) equipment.add(item);//武器或护甲
				else if (item instanceof Ring) rings.add(item);//戒指
				else if (item instanceof Artifact) artifacts.add(item);//神器
				else if (item instanceof Wand) wands.add(item);//法杖
			}
		}
		addRecord("装备",equipment,record);
		addRecord("戒指",rings,record);
		addRecord("神器",artifacts,record);
		addRecord("法杖",wands,record);
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
		SPDSettings.customSeed(""); //设置种子为空
		GamesInProgress.selectedClass = HeroClass.WARRIOR; //默认英雄战士
		Dungeon.init(); //初始化地牢
		storage_seed = Dungeon.seed;
		//要的数据 武器 护甲 戒指 神器 法杖
		ArrayList<Item> rings = new ArrayList<>(); //戒指
		ArrayList<Item> artifacts = new ArrayList<>(); //神器
		ArrayList<Item> wands = new ArrayList<>(); //法杖
		for (int i = 0; i < 4; i++) {
			Level l = Dungeon.newLevel();
			ArrayList<Heap> heaps = new ArrayList<>(l.heaps.valueList()); //散布的物品
			for (Heap h : heaps) {
				for (Item item : h.items) {
					item.identify();
					if (h.type == Type.FOR_SALE) continue;
					else if (item instanceof Ring) rings.add(item);//戒指
					else if (item instanceof Artifact) artifacts.add(item);//神器
					else if (item instanceof Wand) wands.add(item);//法杖
				}
			}
			Dungeon.depth++;
		}
		int size = 0;
		size += FindSeek(seek,rings);
		size += FindSeek(seek,artifacts);
		size += FindSeek(seek,wands);

		//该如何判断呢
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
			addRecord("鬼魂任务奖励",ghost,record);
		}

		if (Wandmaker.Quest.wand1 != null) {
			wandmaker.add(Wandmaker.Quest.wand1.identify());
			wandmaker.add(Wandmaker.Quest.wand2.identify());
			Wandmaker.Quest.complete();
			addRecord("法杖任务奖励",wandmaker,record);
		}

		if (Imp.Quest.reward != null) {
			imp.add(Imp.Quest.reward.identify());
			Imp.Quest.complete();
			addRecord("小鬼任务奖励",imp,record);
		}
	}
	public ArrayList<String> getData(String sj){
		return ItemData.split(sj);
	} 

	private static class ItemData {
		private static HashMap<String, String> items;
		static{
			items = new HashMap<String, String>();

			items.put("精准之戒", "RingOfAccuracy");
			items.put("奥术之戒", "RingOfArcana");
			items.put("元素之戒", "RingOfElements");
			items.put("能量之戒", "RingOfEnergy");
			items.put("闪避之戒", "RingOfEvasion");
			items.put("武力之戒", "RingOfForce");
			items.put("狂怒之戒", "RingOfFuror");
			items.put("疾速之戒", "RingOfHaste");
			items.put("根骨之戒", "RingOfMight");
			items.put("神射之戒", "RingOfSharpshooting");
			items.put("韧性之戒", "RingOfTenacity");
			items.put("财富之戒", "RingOfWealth");

			items.put("冲击波法杖", "WandOfBlastWave");
			items.put("酸蚀法杖", "WandOfCorrosion");
			items.put("腐化法杖", "WandOfCorruption");
			items.put("解离法杖", "WandOfDisintegration");
			items.put("焰浪法杖", "WandOfFireblast");
			items.put("冰霜法杖", "WandOfFrost");
			items.put("雷霆法杖", "WandOfLightning");
			items.put("灵壤法杖", "WandOfLivingeEarth");
			items.put("魔弹法杖", "WandOfMagicMissile");
			items.put("棱光法杖", "WandOfPrismaticLight");
			items.put("再生法杖", "WandOfRegrowth");
			items.put("注魂法杖", "WandOfTransfusion");
			items.put("哨卫法杖", "WandOfWarding");

			items.put("炼金工具箱", "AlchemistsToolkit");
			items.put("蓄血圣杯", "ChaliceOfBlood");
			items.put("暗影斗篷", "CloakOfShadows");
			items.put("干枯玫瑰", "DriedRose");
			items.put("虚空锁链", "EtherealChains");
			items.put("丰饶之角", "HornOfPlenty");
			items.put("神偷袖章", "MasterThievesArmband");
			items.put("自然之履", "SandalsOfNature");
			items.put("先见护符", "TalismanOfForesight");
			items.put("时光沙漏", "TimekeepersHourglass");
			items.put("无序魔典", "UnstableSpellbook");			
		}

		//返回表
		public static ArrayList<String> split(String input){
			ArrayList<String> jg = new ArrayList<String>();
			if(input.isEmpty()) return jg; // 检查输入是否为空
			String value = "";
			for (String item: input.split(" ")){
		        value = items.get(item);
		        if(value != null){
		        	//如果存在，那么加入表中
		        	jg.add(value);
		        }
		    }
		    return jg;
		}
	}

}

// Messages.get(对象或类名, "name");