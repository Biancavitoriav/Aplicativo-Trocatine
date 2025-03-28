package com.example.trocatine.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trocatine.R;
import com.example.trocatine.home.Home;
import com.example.trocatine.register.Register2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class Login extends AppCompatActivity {
    private TextInputEditText loginEmail, loginPassword;
    private TextView errorTextLoginEmail, errorTextLoginPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);

        errorTextLoginEmail = findViewById(R.id.errorTextLoginEmail);
        errorTextLoginPassword = findViewById(R.id.errorTextLoginPassword);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){
            Intent main = new Intent(Login.this, Home.class);
            startActivity(main);
        }

        Button logar = findViewById(R.id.button_login);
    }
    public void onClickNext(View view) {
        //Verificações de input do usuário
        boolean hasError = false;

        if (loginEmail.getText().toString().equals("")) {
            showError("Digite a informação necessária", errorTextLoginEmail);
            hasError = true;
//        } else if (!loginEmail.getText().toString().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$")) {
//                showError("Digite um e-mail válido", errorTextLoginEmail);
//                hasError = true;
        } else {
            hideError(errorTextLoginEmail);
        }

        if (loginPassword.getText().toString().equals("")) {
            showError("Digite a informação necessária", errorTextLoginPassword);
            hasError = true;
        } else {
            hideError(errorTextLoginPassword);
        }
        if (!hasError) {
            String email = loginEmail.getText().toString();
            String senha = loginPassword.getText().toString();

            //fazer login no firebase
            FirebaseAuth autenticar = FirebaseAuth.getInstance();
            autenticar.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            String msg="Você esqueceu...";
                            if (task.isSuccessful()) {
                                //redirecionar para a proxima tela
                                Intent main = new Intent(Login.this, Home.class);
                                startActivity(main);
                                finish();
                            }else{
                                try{
                                    throw task.getException();
                                }catch (FirebaseAuthInvalidUserException faiue){
                                    msg = "Usuário não encontrado";
                                }catch (FirebaseAuthInvalidCredentialsException e) {
                                    msg = "Senha invalidos";
                                }catch (Exception e){
                                    Log.e("ERRO",e.getMessage());
                                }
                                Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
    //Método que quando acionado, deixa a mensagem de erro do input visivel
    public void showError(String mensagem, TextView texto) {
        texto.setText(mensagem);
        texto.setVisibility(View.VISIBLE);
    }

    //Método que quando acionado, deixa a mensagem de erro do input invisivel
    public void hideError(TextView erro) {
        erro.setVisibility(View.INVISIBLE);
    }
}