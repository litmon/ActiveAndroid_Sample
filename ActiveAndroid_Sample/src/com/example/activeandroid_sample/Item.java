package com.example.activeandroid_sample;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "Items")
public class Item extends Model {

	@Column(name = "Name", notNull = true)
	String name;

	/**
	 * これがないと取得のときになぜかnullが返る
	 */
	public Item() {
		super();
	}

	/**
	 * 引数ありのコンストラクタ 通常はこちらを使う
	 * 
	 * @param name
	 */
	public Item(String name) {
		super();

		this.name = name;
	}

	/**
	 * 今まで登録した全Itemを取得
	 * 
	 * @return 登録されているItemのリスト
	 */
	public static List<Item> getItems() {
		return new Select() //
				.from(Item.class) // Itemクラスに対して検索を行う
				.execute();
	}
}
