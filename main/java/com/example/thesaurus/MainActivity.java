package com.example.thesaurus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.thesaurus.adapter.DataAdapter;
import com.example.thesaurus.adapter.ListItem;
import com.example.thesaurus.adapter.RecOnClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavItemSelectorListener {
    private RecOnClickListener recOnClickListener;
    private DataAdapter adapter;
    private List<ListItem> listData;
    private RecyclerView rcView;
    private String category = "";
    private SharedPreferences pref;
    private final String PLANETS = "planets";
    private final String STARS = "stars";
    private final String VSELEN = "universal";
    private final String ANIMALSKK = "animalsKK";
    private final String FISH = "fish";
    private final String MLEKOPIT = "mlekopit";
    private final String PRESMIK = "presmik";
    private final String ZEMNOVOD = "zemnovod";
    private final String PTICI = "ptici";
    private final String PLANTSKK = "plantsKK";
    private final String CVETKOVIE = "cvetkovie";
    private final String DEREVKUST = "derevkust";
    private final String VODOROSLI = "vodorosli";
    private final String GRUST = "grust";
    private final String VESEL = "vesel";
    private final String VLADEL = "vladel";
    private final String SOZDPROG = "sozdprog";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMenu();
        setRecOnClickListener();
        init();
    }
    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuFragmentList mMenuFragment = (MenuFragmentList) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuFragmentList();
            mMenuFragment.setNavItemSelectorListener(this);
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }}
    // слушатель(будет применяться благодаря этому классу)
    @Override
    public void onNavItemSelectedListener(MenuItem item) {
        // Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        // теперь можем обновлять адаптер благодаря прослушиванию
        switch (item.getItemId()){
            case R.id.id_favorite:
                updateFav();

                break;
            case R.id.id_planets:
                updateMainList(getResources().getStringArray(R.array.planets), PLANETS);

                break;
            case R.id.id_stars:
                updateMainList(getResources().getStringArray(R.array.stars), STARS);

                break;
            case R.id.id_universal:
                updateMainList(getResources().getStringArray(R.array.vselen), VSELEN);

                break;
            case R.id.id_animalsKK:
                updateMainList(getResources().getStringArray(R.array.animalsKK), ANIMALSKK);

                break;
            case R.id.id_fish:
                updateMainList(getResources().getStringArray(R.array.fish), FISH);

                break;
            case R.id.id_mlekopit:
                updateMainList(getResources().getStringArray(R.array.mlekopit), MLEKOPIT);

                break;
            case R.id.id_presmik:
                updateMainList(getResources().getStringArray(R.array.presmik), PRESMIK);

                break;
            case R.id.id_zemnovod:
                updateMainList(getResources().getStringArray(R.array.zemnovod), ZEMNOVOD);

                break;
            case R.id.id_ptici:
                updateMainList(getResources().getStringArray(R.array.ptici), PTICI);

                break;
            case R.id.id_plantsKK:
                updateMainList(getResources().getStringArray(R.array.plantsKK), PLANTSKK);

                break;
            case R.id.id_cvetkovie:
                updateMainList(getResources().getStringArray(R.array.cvetkovie), CVETKOVIE);

                break;
            case R.id.id_derevkust:
                updateMainList(getResources().getStringArray(R.array.derevkust), DEREVKUST);

                break;
            case R.id.id_vodorosli:
                updateMainList(getResources().getStringArray(R.array.vodorosli), VODOROSLI);

                break;
            case R.id.id_grust:
                updateMainList(getResources().getStringArray(R.array.grust), GRUST);

                break;
            case R.id.id_vesel:
                updateMainList(getResources().getStringArray(R.array.vesel), VESEL);

                break;
            case R.id.id_vladel:
                updateMainList(getResources().getStringArray(R.array.osnova), VLADEL);

                break;
            case R.id.id_sozdprog:
                updateMainList(getResources().getStringArray(R.array.sozdatel), SOZDPROG);

                break;

        }

    }
    private void updateMainList(String[] array, String cat)

    {
        category = cat;
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();

        String tempCat = pref.getString(cat, "none");
        // делаем избранные при помощи 0 и 1, если 0- то не в избр, 1- в избр.
        if(tempCat != null) {
            // здесь мы просто создаём стринг из 0 для каждой категории
            if (tempCat.equals("none")) {
                for(int i = 0; i < array.length; i++)
                {
                    stringBuilder.append("0");
                }
                Log.d("MyLog", cat + " " + stringBuilder.toString());
                saveString(stringBuilder.toString());
            }
            else
            {

            }
        }
        // проходимся по эррэй и вытаскиваем значения и выводим на экран
        List<ListItem> list = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            ListItem item = new ListItem();
            item.setText(array[i]);
            item.setCat(cat);
            item.setPosition(i);

            list.add(item);
        }
        adapter.updateList(list);

    }
    // функция для Избранных
    private void updateFav(){
        List<ListItem> listFav = new ArrayList<>();
        List<String[]> listData = new ArrayList<>();
        // Добавляем все ресурсы в наш Array
        listData.add(getResources().getStringArray(R.array.planets));
        listData.add(getResources().getStringArray(R.array.stars));
        listData.add(getResources().getStringArray(R.array.vselen));
        listData.add(getResources().getStringArray(R.array.animalsKK));
        listData.add(getResources().getStringArray(R.array.fish));
        listData.add(getResources().getStringArray(R.array.mlekopit));
        listData.add(getResources().getStringArray(R.array.vesel));
        listData.add(getResources().getStringArray(R.array.grust));
        listData.add(getResources().getStringArray(R.array.vodorosli));
        listData.add(getResources().getStringArray(R.array.derevkust));
        listData.add(getResources().getStringArray(R.array.plantsKK));
        listData.add(getResources().getStringArray(R.array.ptici));











        listData.add(getResources().getStringArray(R.array.osnova));
        listData.add(getResources().getStringArray(R.array.sozdatel));

        // когда будем перебирать, чтобы узнавать в какой категории мы находимся и смотреть какие отмеченные элементы
        String[] cat_array = {PLANETS, STARS, VSELEN, ANIMALSKK, FISH, MLEKOPIT, VESEL, GRUST, VODOROSLI, DEREVKUST, PLANTSKK, PTICI, VLADEL, SOZDPROG};
        // перебираем массивы
        for (int i = 0; i < listData.size(); i++)
        {

            for(int p = 0; p < listData.get(i).length; p++)
            {
                if(pref.getString(cat_array[i], "none") != null)if (pref.getString(cat_array[i], "none").charAt(p) == '1'){
                    ListItem item = new ListItem();
                    item.setText(listData.get(i)[p]);
                    item.setPosition(p);
                    item.setCat(cat_array[i]);
                    listFav.add(item);
                }


            }
        }
        adapter.updateList(listFav);


    }


    // здесь мы создаём объекты
    private void init(){
        pref = getSharedPreferences("CAT", MODE_PRIVATE);
        rcView = findViewById(R.id.rcView);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        listData = new ArrayList<>();
        String[] planetsArray = getResources().getStringArray(R.array.planets);


        adapter = new DataAdapter(this, recOnClickListener, listData);
        updateMainList(planetsArray, "planets");
        rcView.setAdapter(adapter);
    }

    private void setRecOnClickListener(){
        recOnClickListener = new RecOnClickListener() {
            @Override
            public void onItemClicked(int pos) {
                // Toast.makeText(MainActivity.this, "Position = " + pos, Toast.LENGTH_SHORT).show();
                // реализуем замену 0 на 1
                String tempCat = pref.getString(category, "net");
                if (tempCat != null) {
                    if (tempCat.charAt(pos) == '0') {
                        saveString(replaceCharAtPos(pos, '1', tempCat));
                    }
                    else
                    {
                        saveString(replaceCharAtPos(pos, '0', tempCat));
                    }
                }



            }
        };

    }
    private String replaceCharAtPos(int position, char ch, String st){
        char[] charArray = st.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }
    // ф-ия для замены
    private void saveString(String stToSave){

        SharedPreferences.Editor editor = pref.edit();
        // тут записываем в память по названию категории(у каждой категории свое ключевое название)
        editor.putString(category, stToSave);
        editor.apply();
        Log.d("MyLog", "Saved data fav : " + pref.getString(category, "none"));
    }}
