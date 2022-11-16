package com.example.firstproject.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
	public class Book {
		@PrimaryKey(autoGenerate = true)
		public long id;

		@ColumnInfo(name = "author")
		public String author;

		@ColumnInfo(name = "genre")
		public String genre;

		@ColumnInfo(name = "name_book")
		public String nameBook;

		@ColumnInfo(name = "publication_date")
		public String publicationDate;

		@ColumnInfo(name = "rating")
		public int rating;
	}