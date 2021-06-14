package com.br.whatsappclone.model;

import com.br.whatsappclone.configuracaoFirebase.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuario {
    private String Uid;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }
    public void salvarUsuario(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference usuario = firebaseRef.child("usuario").child(getUId());
        usuario.setValue(this);
    }

    private String getUId() {
        return Uid;
    }

    public void setUid(String uid) {
       this.Uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
