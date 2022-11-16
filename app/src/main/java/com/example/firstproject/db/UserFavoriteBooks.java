package com.example.firstproject.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserFavoriteBooks {
	@PrimaryKey(autoGenerate = true)
	public long id;

	@ColumnInfo(name = "user_id")
	public long userId;

	@ColumnInfo(name = "book_id")
	public long bookId;
}
