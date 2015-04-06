package com.example.activeandroid_sample;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	// レイアウトとの関連付け用
	EditText editText;
	ListView listView;

	// ListViewの要素を変更するために必要(ItemAdapter.java 参照)
	ItemAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// レイアウトとの関連付け
		listView = (ListView) findViewById(R.id.listView1);
		editText = (EditText) findViewById(R.id.editText1);

		// ItemAdapterを新しく作る
		adapter = new ItemAdapter(this, R.layout.list_item);
		// listViewとadapterを関連付け(これでデータを表示できる)
		listView.setAdapter(adapter);
		// ListViewの要素をクリックしたときの動作を指定する
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// クリックした要素のアイテムを取得
				final Item item = adapter.getItem(position);

				new AlertDialog.Builder(MainActivity.this) // ダイアログを生成
						.setTitle("item詳細") // ダイアログのタイトル
						.setMessage("item: " + item.name + "; を削除しますか?") // ダイアログに表示するメッセージ
						// yesボタンの設定
						.setPositiveButton("Yes", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// 選択したアイテムを削除
								item.delete();
								// listViewからも削除するためにadapterからremove
								adapter.remove(item);

								// ダイアログを消す
								dialog.dismiss();
							}
						})
						// Noボタンの設定
						.setNegativeButton("No", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// ダイアログを消す
								dialog.cancel();
							}
						})
						// showメソッドを呼び出すことでダイアログを表示
						.show();
			}
		});

		// 今まで保存されていたアイテムを全取得
		List<Item> items = Item.getItems();
		// adapterに表示したいアイテムを追加
		adapter.addAll(items);
	}

	public void add(View v) {
		// editTextから入力した文字を取得
		String name = editText.getText().toString();
		// 入力された文字から新しくアイテムを作成
		Item item = new Item(name);
		// アイテムを保存する
		item.save();

		// adapterに今作成したアイテムを追加する
		adapter.add(item);
	}
}
