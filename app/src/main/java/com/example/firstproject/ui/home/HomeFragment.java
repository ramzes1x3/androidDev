package com.example.firstproject.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firstproject.R;
import com.example.firstproject.db.AppDatabase;
import com.example.firstproject.db.Book;
import com.example.firstproject.db.BookDao;
import com.example.firstproject.ui.adapters.BooksAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HomeFragment extends Fragment {
	private RecyclerView mRecyclerView;
	List<Book> bookList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		AppDatabase db = AppDatabase.getInstance(this.getContext());

//		BookDao bookDao = db.bookDao();
//		Book dbBook = new Book();
//		Thread fetch = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				StringBuilder response = new StringBuilder();
//
//				try {
//					String currUrl = "https://api.npoint.io/9d9526bc5f8d6d1b81ab";
//					URL url = null;
//					url = new URL(currUrl);
//
//					HttpURLConnection httpURLConnection = null;
//					httpURLConnection = (HttpURLConnection) url.openConnection();
//					httpURLConnection.setRequestMethod("GET");
//
//					if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//						BufferedReader input = new BufferedReader(
//								new InputStreamReader(httpURLConnection.getInputStream()), 8192);
//
//						String line = null;
//
//						while ((line = input.readLine()) != null) {
//							response.append(line);
//						}
//
//						input.close();
//					}
//
//					if (response != null) {
//						JSONObject jsonObject = new JSONObject(String.valueOf(response));
//						JSONArray books = jsonObject.getJSONArray("books");
//
//						for (int i = 0; i < books.length(); i++) {
//							JSONObject booksJSON = books.getJSONObject(i);
//							String author = booksJSON.getString("Author");
//							String genre = booksJSON.getString("Genre");
//							String name = booksJSON.getString("Name");
//							String publicationDate = booksJSON.getString("PublicationDate");
//							int rating = booksJSON.getInt("rating");
//
//							dbBook.author = author;
//							dbBook.genre = genre;
//							dbBook.nameBook = name;
//							dbBook.publicationDate = publicationDate;
//							dbBook.rating = rating;
//							bookDao.insert(dbBook);
//						}
//
//						bookList = db.bookDao().getAll();
//					}
//
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//
//		fetch.start();
//
//		try {
//			fetch.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		bookList = db.bookDao().getAll();

		mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
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