package com.example.tbitak2209;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tbitak2209.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailText;
    private Button resetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Bileşenleri Tanımlama
        emailText = findViewById(R.id.emailText);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);

        // Şifre Sıfırlama Butonuna Tıklama İşlevi
        resetPasswordButton.setOnClickListener(v -> resetPassword());
    }

    private void resetPassword() {
        String email = emailText.getText().toString().trim();

        // E-posta alanı boşsa uyarı ver
        if (email.isEmpty()) {
            Toast.makeText(this, "Lütfen e-posta adresinizi girin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Burada Flask API'ye şifre sıfırlama isteği gönderilecek
        sendResetRequest(email);
    }

    private void sendResetRequest(String email) {
        // Gelecekte Flask API'ye bağlanarak şifre sıfırlama yapılacak
        Toast.makeText(this, "Şifre sıfırlama isteği gönderildi!\nE-posta: " + email, Toast.LENGTH_LONG).show();

        // İşlem tamamlandıktan sonra giriş ekranına yönlendirilebilir
        finish();
    }
}
