package com.example.activeandroid_sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Item> {

	// ListViewの一つ一つの要素に使うレイアウトのID
	int resourceId;
	// レイアウトのIDからViewを生成するためのもの
	LayoutInflater inflater;
	
	public ItemAdapter(Context context, int resource) {
		super(context, resource);

		this.resourceId = resource;
		// contextからLayoutInflaterを取得する
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convertViewがnullだったら新しく作る
		if(convertView == null){
			// layoutInflaterを使うとxmlからViewを作れる
			convertView = inflater.inflate(resourceId, null);
		}
		
		// 今の位置のitemを取得
		Item item = getItem(position);
		
		// textViewをconvertViewから取ってくる
		TextView textView = (TextView)convertView.findViewById(R.id.textView1);
		
		// textViewにitemの名前をセットする
		textView.setText(item.name);
		
		// convertViewを表示するViewとして返す
		return convertView;
	}

}
