package com.br.whatsappclone.configuracaoFirebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracaoFirebase {
    private static DatabaseReference databaseRef;
    private static FirebaseAuth firebaseAuth;
    private static StorageReference storageReference;

    // retorna a instância do firebase database
    public static DatabaseReference getFirebaseDatabase(){
        if(databaseRef == null){
            databaseRef= FirebaseDatabase.getInstance().getReference();
        }
        return databaseRef;
    }
    // retorna a instância do firebase auth
    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }
    // retorna a instância do firebase storage
    public static StorageReference getFirebaseStorage(){
        if(storageReference == null){
            storageReference = FirebaseStorage.getInstance().getReference();
        }
        return storageReference;
    }


}
