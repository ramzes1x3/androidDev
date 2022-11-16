package com.example.firstproject.ui.favorite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firstproject.MainActivity2;
import com.example.firstproject.R;
import com.example.firstproject.db.AppDatabase;
import com.example.firstproject.db.Book;
import com.example.firstproject.db.User;
import com.example.firstproject.db.UserFavoriteBooks;
import com.example.firstproject.ui.adapters.BooksAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
	private RecyclerView mRecyclerView;
	List<Book> bookList = new ArrayList<Book>();
	User user;
	List<UserFavoriteBooks> userFavoriteBooks;

	String userEmail = MainActivity2.userEmail;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_favorite, container, false);

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		AppDatabase db = AppDatabase.getInstance(this.getContext());

		user = db.userDao().getUserByEmail(userEmail);

		userFavoriteBooks = db.userFavoriteBooksDao().getUserFavoriteBooksById(user.id);

		bookList.clear();

		for(int i = 0; i < userFavoriteBooks.size(); i++) {
			Book book = db.bookDao().getBookById(userFavoriteBooks.get(i).bookId);
			if (book != null) {
				bookList.add(book);
			}
		}

		mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view2);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setHasFixedSize(true);

		if (bookList == null) {
			return;
		}

		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
		mRecyclerView.addItemDecoration(dividerItemDecoration);

		BooksAdapter adapter = new BooksAdapter(getContext(), (List<Book>) bookList);
		mRecyclerView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}