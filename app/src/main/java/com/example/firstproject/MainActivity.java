package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstproject.db.AppDatabase;
import com.example.firstproject.db.User;
import com.example.firstproject.db.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
	Button signInButton;
	Intent mainActivity2;
	EditText inputUserPassword;
	EditText inputUserEmail;
	String password;
	String email;
	List<User> userList;

	@SuppressLint("WrongThread")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		AppDatabase db = AppDatabase.getInstance(this);
		UserDao userDao = db.userDao();

//		User dbUser = new User();
//		String[] emails = getResources().getStringArray(R.array.emails);
//		String[] passwords = getResources().getStringArray(R.array.passwords);
//
//		for (int i = 0; i < emails.length; i++) {
//			dbUser.email = emails[i];
//			dbUser.password = passwords[i];
//			userDao.insert(dbUser);
//		}

		userList = userDao.getAll();

		signInButton = findViewById(R.id.signInButton);
		inputUserPassword = findViewById(R.id.inputUserPassword);
		inputUserEmail = findViewById(R.id.inputUserEmail);

		signInButton.setOnClickListener(view -> {
			password = inputUserPassword.getText().toString();
			email = inputUserEmail.getText().toString();

			int emailIndexFound = findEmail(email);
			if (emailIndexFound != -1) {
				if (isPasswordCorrect(emailIndexFound, password)) {
					mainActivity2 = new Intent(this, MainActivity2.class);
					mainActivity2.putExtra("userEmail", email);

					startActivity(mainActivity2);
				} else {
					Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(this, "Incorrect email", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private int findEmail(String emailSearch) {

		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).email.equals(emailSearch)) {
				return i;
			};
		}

		return -1;
	}

	private boolean isPasswordCorrect(int emailIndexFound, String inputUserPassword) {
		return userList.get(emailIndexFound).password.equals(inputUserPassword);
	}
}