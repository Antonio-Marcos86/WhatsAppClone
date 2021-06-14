package com.br.whatsappclone.helper;

import com.br.whatsappclone.configuracaoFirebase.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;



public class UsuarioFirebase {

    public static String getUidUsuario(){
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        return autenticacao.getCurrentUser().getUid();
    }


}
