package com.example.firstproject.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
	public interface UserFavoriteBooksDao {
		@Query("SELECT * FROM userFavoriteBooks")
		List<UserFavoriteBooks> getAll();

		@Query("SELECT * FROM userFavoriteBooks WHERE userFavoriteBooks.user_id =:userId")
		List<UserFavoriteBooks> getUserFavoriteBooksById(long userId);

		@Insert(onConflict = OnConflictStrategy.REPLACE)
		void insert(UserFavoriteBooks... userFavoriteBooks);
	}
