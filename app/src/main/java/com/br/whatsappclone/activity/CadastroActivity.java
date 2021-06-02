package com.br.whatsappclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.br.whatsappclone.R;
import com.br.whatsappclone.configuracaoFirebase.ConfiguracaoFirebase;
import com.br.whatsappclone.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome,campoEmail,campoSenha;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializaComponentes();
    }

    public void salvarUsuario(Usuario usuario){
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    exibirMensagem("Cadastro realizado com sucesso!");
                }else{
                    // em caso de erro mastra a mensagem correspondente
                    String erroExecao="";

                    try{
                        throw task.getException();
                    }catch(FirebaseAuthWeakPasswordException e){
                        erroExecao = "Digite uma senha mais forte!";
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        erroExecao = "Por favor, digite um email válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        erroExecao = "Email já cadastrado";
                    }catch(Exception e){
                        erroExecao = "ao cadastrar usuário: "+ e.getMessage();
                        e.printStackTrace();
                    }
                        exibirMensagem("Erro: "+ erroExecao);
                }
            }
        });
    }

    public void cadastrarUsuario(View view){
        // Recupera textos dos campos
        String textoNome= campoNome.getText().toString();
        String textoEmail= campoEmail.getText().toString();
        String textoSenha= campoSenha.getText().toString();


        if(!textoNome.isEmpty()){
            if(!textoEmail.isEmpty()){
                if(!textoSenha.isEmpty()){
                    Usuario usuario = new Usuario();
                    usuario.setNome(textoNome);
                    usuario.setEmail(textoEmail);
                    usuario.setSenha(textoSenha);

                    salvarUsuario(usuario);
                    home();
                    finish();
                }else{ exibirMensagem("preencha sua senha"); }
            }else{ exibirMensagem("preencha seu email"); }
        }else{ exibirMensagem("Digite seu nome"); }
    }

    private void home() {
        startActivity(new Intent(this,HomeActivity.class));
    }

    public void exibirMensagem(String texto){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();
    }

    private void inicializaComponentes() {
        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
    }
}