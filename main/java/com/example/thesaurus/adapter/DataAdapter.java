package com.example.thesaurus.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thesaurus.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {
    private Context context;
    private RecOnClickListener recOnClickListener;
    // это и будет сам весь список
    private List<ListItem> listItemArray;
    private SharedPreferences pref;


    // теперь когда мы будем запускать адаптер, он будет запрашивать из активити контекст, макссив и слушатель
    public DataAdapter(Context context, RecOnClickListener recOnClickListener, List<ListItem> listItemArray) {
        this.context = context;
        this.recOnClickListener = recOnClickListener;
        this.listItemArray = listItemArray;
        pref = context.getSharedPreferences("CAT", Context.MODE_PRIVATE);

    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        holder.setData(listItemArray.get(position));


    }

    @Override
    public int getItemCount() {
        return listItemArray.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvText;
        // обозначаем текст и отдельно кнопку
        private ImageButton imButFav;
        private boolean isFavChecked = false;


        public DataHolder(@NonNull View itemView) {
            super(itemView);
            // находим их и берем айдишку кнопки для последующей проверки
            tvText = itemView.findViewById(R.id.tvText);
            imButFav = itemView.findViewById(R.id.imBut);
            imButFav.setOnClickListener(this);
        }

        public void setData(ListItem item){
            // организуем вывод
            tvText.setText(item.getText());
            setFav(item, getAdapterPosition());


        }

        @Override
        public void onClick(View v){
            // если нажали, то тру, еще раз фолс
            isFavChecked = !isFavChecked;
            if (isFavChecked){
                imButFav.setImageResource(android.R.drawable.btn_star_big_on);
            }
            else {
                imButFav.setImageResource(android.R.drawable.btn_star_big_off);
            }
            recOnClickListener.onItemClicked(getAdapterPosition());

        }

// у каждой категории мы берём свой стринг с 0 и 1(0001100), если ничего не записано, то none
        private void setFav(ListItem item, int position){
            String fav_data = pref.getString(item.getCat(), "none");
            // тут просто разбиваем наш стринг чтобы удобнее было обращаться к элементам
            if (fav_data != null){
                char[] charArray = fav_data.toCharArray();
                // если 0- то оставляем пустую звезду и выставляем false, 1 выставляем желтую звезду и выставляем true
                switch (charArray[position]){
                    case '0':
                        imButFav.setImageResource(android.R.drawable.btn_star_big_off);
                        isFavChecked = false;
                        break;
                    case '1':
                        imButFav.setImageResource(android.R.drawable.btn_star_big_on);
                        isFavChecked = true;
                        break;
                }
            }


        }
    }

    // функция которая будет обновлять список в приложении
    public void updateList(List<ListItem> listArray){
        listItemArray.clear();
        listItemArray.addAll(listArray);
        notifyDataSetChanged();
    }
}


