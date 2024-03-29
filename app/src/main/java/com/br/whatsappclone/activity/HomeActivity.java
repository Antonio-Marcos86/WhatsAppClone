package com.br.whatsappclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.br.whatsappclone.R;
import com.br.whatsappclone.configuracaoFirebase.ConfiguracaoFirebase;
import com.br.whatsappclone.fragment.ContatosFragment;
import com.br.whatsappclone.fragment.ConversasFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("WhatsApp");
        setSupportActionBar(toolbar);
        // Configurar abas
        FragmentPagerItemAdapter adapter= new FragmentPagerItemAdapter(
              getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Conversas", ConversasFragment.class)
                        .add("Contatos", ContatosFragment.class).create()
        );
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = findViewById(R.id.viewPagerTab);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuSair:
                deslogarUsuario();
                finish();
                break;
            case R.id.menuConfiguracao:
                abrirConfiguracoes();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void abrirConfiguracoes() {
        startActivity(new Intent(this,ConfiguracoesActivity.class));
    }

    private void deslogarUsuario() {

        try{
            autenticacao.signOut();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}