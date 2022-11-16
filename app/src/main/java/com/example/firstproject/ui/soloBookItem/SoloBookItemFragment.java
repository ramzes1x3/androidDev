package com.example.firstproject.ui.soloBookItem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firstproject.R;

public class SoloBookItemFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_solo_book_item, container, false);

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		TextView name = view.findViewById(R.id.book_name_solo);
		TextView author = view.findViewById(R.id.author_value);
		TextView genre = view.findViewById(R.id.genre_value);
		TextView publication_date = view.findViewById(R.id.publication_date_value);
		TextView rating = view.findViewById(R.id.rating_value);
		
		name.setText(getArguments().getString("name"));
		author.setText(getArguments().getString("author"));
		genre.setText(getArguments().getString("genre"));
		publication_date.setText(getArguments().getString("publicationDate"));
		rating.setText(getArguments().getString("rating"));
	}
}