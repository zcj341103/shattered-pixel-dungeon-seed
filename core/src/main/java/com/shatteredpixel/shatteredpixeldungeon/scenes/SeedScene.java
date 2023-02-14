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

package com.shatteredpixel.shatteredpixeldungeon.scenes;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.ui.Archs;
import com.shatteredpixel.shatteredpixeldungeon.ui.ExitButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.StyledButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.ScrollPane;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.DungeonSeed;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTextInput;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndMessage;

import com.shatteredpixel.shatteredpixeldungeon.SeedFinder;

import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;

import com.watabou.input.PointerEvent;
import com.watabou.noosa.Camera;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.Group;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextInput;
import com.watabou.noosa.PointerArea;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.DeviceCompat;

public class SeedScene extends PixelScene {
	private String seed_ = "";
	private String input = "";
	private SeedFinder sf = new SeedFinder();

	private StyledButton seedButton;
	private RenderedTextBlock zw;

	@Override
	public void create() {
		super.create();

		final float colWidth = 120;
		final float fullWidth = colWidth * (landscape() ? 2 : 1);

		int w = Camera.main.width;
		int h = Camera.main.height;

		//背景楼梯
		Archs archs = new Archs();
		archs.setSize( w, h );
		add( archs );

		//滚动窗格
		ScrollPane list = new ScrollPane( new Component() );
		add( list );
		//容器
		Component content = list.content();
		content.clear();
		content.setSize( fullWidth, 10 );

		//显示文本内容
		zw = PixelScene.renderTextBlock("", 8);
		zw.setPos(0, 75);
		zw.maxWidth(130);
		content.add(zw);

		//显示且设置种子按钮
		seedButton = new StyledButton(Chrome.Type.BLANK, "点我设查的种子", 10){
			@Override
			protected void onClick() {
				String existingSeedtext = SPDSettings.customSeed();// 获取全局设置的种子
				ShatteredPixelDungeon.scene().addToFront( new WndTextInput("设置查看种子界面",
						"输入查看种子，会自动转换为对应的字符串。\n点[查看种子]就可查看。",
						seed_,//要改为自定义的种子
						20,
						false,
						"查看种子",
						"清除返回"){
					@Override
					public void onSelect(boolean positive, String text) {
						text = DungeonSeed.formatText(text);
						long seed = DungeonSeed.convertFromText(text);

						if (positive && seed != -1){
							seed_ = text;
							input = "";
							onFind();
						} else {
							seed_ = "";
						}
					}
				});
			}
		};
		seedButton.setRect(15, 50, 100, 20);
		content.add(seedButton);

		//输入查看对象
		TextInput textBox = new TextInput(Chrome.get(Chrome.Type.TOAST_WHITE), false, (int)PixelScene.uiCamera.zoom * 9){
			@Override
			public void enterPressed() {
				input = getText();
			}
		}; // 类型， 多行  字体大小
		textBox.setRect(5, 25, 100, 20);
		content.add(textBox);
		
		//随机种子按钮
		StyledButton randomseedButton = new StyledButton(Chrome.Type.GREY_BUTTON_TR,"随机种子"){
			@Override
			public void onClick() {
				seed_ = "";
				onFind();
			}			
		};
		randomseedButton.setRect(5, 5, 50, 20);
		add(randomseedButton);

		StyledButton introButton = new StyledButton(Chrome.Type.GREY_BUTTON_TR,"介绍"){
			@Override
			public void onClick() {
				zw.text("1、随机种子按钮：随机一个种子的前4层物品信息。不包括怪物掉落的物品。\n2、输入框：输入可查范围内xx之戒|法杖|神器,每个物品中用\" \"空格隔开。回车确定，再点随机种子按钮查看。不支持查等级。最大查次数400次。\n3、点我设查的种子：可输入查看的种子。\n4、设置先挑战，查找的都是挑战种子。");
			}			
		};
		introButton.setRect(75, 5, 35, 20);
		add(introButton);

		list.setRect( 0, 0, w, h );
		list.scrollTo(0, 0);

		//退出按钮
		ExitButton btnExit = new ExitButton();
		btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
		add( btnExit );
	}
	//按背景时
	@Override
	protected void onBackPressed() {
		ShatteredPixelDungeon.switchScene(SeedScene.class);
	}
 
	protected void onFind(){
		String[] data = sf.addDungeon(4, seed_, sf.getData(input));
		zw.text(data[0]);
		seedButton.text(data[1]);
		seed_=data[1];//设置种子		
	}

}
