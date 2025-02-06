package com.example.tbitak2209;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tbitak2209.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullName, emailText, phoneNumber, passwordText, teacherInviteCode;
    private RadioGroup roleGroup;
    private RadioButton radioStudent, radioTeacher;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Bileşenleri Tanımlama
        fullName = findViewById(R.id.fullName);
        emailText = findViewById(R.id.emailText);
        phoneNumber = findViewById(R.id.phoneNumber);
        passwordText = findViewById(R.id.passwordText);
        teacherInviteCode = findViewById(R.id.teacherInviteCode);
        roleGroup = findViewById(R.id.roleGroup);
        radioStudent = findViewById(R.id.radioStudent);
        radioTeacher = findViewById(R.id.radioTeacher);
        registerButton = findViewById(R.id.registerButton);

        // Rol Seçimine Göre Eğitmen Davet Kodunu Gizleme / Gösterme
        roleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioTeacher) {
                teacherInviteCode.setVisibility(View.VISIBLE);
            } else {
                teacherInviteCode.setVisibility(View.GONE);
            }
        });

        // Kayıt Ol Butonu İşlevi
        registerButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String name = fullName.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        String phone = phoneNumber.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String inviteCode = teacherInviteCode.getText().toString().trim();
        boolean isTeacher = radioTeacher.isChecked();

        // Zorunlu Alan Kontrolleri
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Eğitmenler İçin Davet Kodu Kontrolü
        if (isTeacher && inviteCode.isEmpty()) {
            Toast.makeText(this, "Eğitmen kaydı için davet kodu gereklidir!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Burada API'ye veri gönderme işlemi yapılabilir (JSON formatında)
        if (isTeacher) {
            Toast.makeText(this, "Eğitmen kaydı başarılı! (Davet Kodu: " + inviteCode + ")", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Öğrenci kaydı başarılı!", Toast.LENGTH_LONG).show();
        }

        // Kayıt işlemi tamamlandıktan sonra giriş ekranına yönlendirilebilir
        finish();
    }
}
