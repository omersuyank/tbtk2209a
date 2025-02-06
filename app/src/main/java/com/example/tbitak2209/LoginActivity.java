package com.example.tbitak2209;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.tbitak2209.R;

public class LoginActivity extends AppCompatActivity {
    private EditText emailText, passwordText;
    private Button loginButton;
    private CheckBox rememberMe;
    private TextView forgotPasswordText, registerText;
    private ImageView passwordToggle;
    private SharedPreferences sharedPreferences;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bileşenleri Tanımla
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        rememberMe = findViewById(R.id.rememberMe);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);
        registerText = findViewById(R.id.registerText);
        passwordToggle = findViewById(R.id.passwordToggle);

        // SharedPreferences ile "Beni Hatırla" kontrolü
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        checkRememberedUser();

        // Şifre Göster/Gizle Butonu
        passwordToggle.setOnClickListener(v -> togglePasswordVisibility());

        // Giriş Butonu Click Listener
        loginButton.setOnClickListener(v -> validateLogin());

        // "Şifremi Unuttum" tıklanınca
        forgotPasswordText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        // "Kayıt Ol" tıklanınca
        registerText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    // Şifre göster/gizle işlemi
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordToggle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.visibility));
        } else {
            passwordText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordToggle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.visible));
        }
        isPasswordVisible = !isPasswordVisible;
        passwordText.setSelection(passwordText.getText().length()); // İmleci sona götür
    }

    // Kullanıcı giriş kontrolü
    private void validateLogin() {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Burada gerçek bir kimlik doğrulama API isteği yapılabilir
        if (email.equals("test@example.com") && password.equals("123456")) {
            Toast.makeText(this, "Giriş başarılı!", Toast.LENGTH_SHORT).show();

            // Beni Hatırla seçiliyse bilgileri kaydet
            if (rememberMe.isChecked()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putBoolean("remember", true);
                editor.apply();
            } else {
                sharedPreferences.edit().clear().apply();
            }

            // Giriş başarılıysa Ana Sayfa'ya yönlendir
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Hatalı e-posta veya şifre!", Toast.LENGTH_SHORT).show();
        }
    }

    // SharedPreferences'tan "Beni Hatırla" bilgilerini getir
    private void checkRememberedUser() {
        boolean isRemembered = sharedPreferences.getBoolean("remember", false);
        if (isRemembered) {
            emailText.setText(sharedPreferences.getString("email", ""));
            passwordText.setText(sharedPreferences.getString("password", ""));
            rememberMe.setChecked(true);
        }
    }
}
