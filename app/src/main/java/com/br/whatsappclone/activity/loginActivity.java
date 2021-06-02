package com.br.whatsappclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.br.whatsappclone.R;
import com.br.whatsappclone.activity.CadastroActivity;
import com.br.whatsappclone.configuracaoFirebase.ConfiguracaoFirebase;
import com.br.whatsappclone.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class loginActivity extends AppCompatActivity {
        private EditText campoEmail,campoSenha;
        private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        autenticacao= ConfiguracaoFirebase.getFirebaseAuth();
        inicializaComponentes();

    }

    public void logarusuario(Usuario usuario){
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Home();

                }else{
                    String excecao="";

                    try{
                        throw task.getException();
                    }catch(FirebaseAuthInvalidUserException e){
                        excecao="usuário não está cadastrado";
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        excecao="Email e senha inválidos";
                    }catch(Exception e){
                        excecao = "Erro ao cadastrar usuário: "+ e.getMessage();
                        e.printStackTrace();
                    }
                    exibirMensagem(excecao);
                }
            }
        });
    }
    public void ValidarUsuario(View view){
        // recuperar textos dos campos
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        // validar se email e senha foram digitados
            if(!email.isEmpty()){
                if(!senha.isEmpty()){
                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    logarusuario(usuario);

                }else{ exibirMensagem("preencha sua senha"); }
            }else{ exibirMensagem("preencha seu email"); }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioLogado = autenticacao.getCurrentUser();
        if(usuarioLogado != null){
            Home();
        }
    }

    public void exibirMensagem(String texto){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();
    }
    public void Home(){
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void Cadastro(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
    private void inicializaComponentes() {

        campoEmail = findViewById(R.id.editEmailEntrar);
        campoSenha = findViewById(R.id.editSenhaEntrar);
    }
}