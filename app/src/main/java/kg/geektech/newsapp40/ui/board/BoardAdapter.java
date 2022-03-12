package kg.geektech.newsapp40.ui.board;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ItemBoardBinding binding;
    private  int[] listImg = {R.raw.notebook,R.raw.notebook2,R.raw.notebook3,R.raw.notebook4};
    private String[] titles = new String[]{"Салам","Привет","Hello", "嘿"};
    private String subtitles[] = {"Жанылыктар колдонмосу","Приложение новостей","News app","新闻应用"};

    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.onBind(position);
        if (position == 3) {
            binding.startBtn.setVisibility(View.VISIBLE);
        } else {
            binding.startBtn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemBoardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }

        public void onBind(int position) {
            binding.titleTv.setText(titles[position]);
            binding.subtitleTv.setText(subtitles[position]);
            binding.boardIv.setAnimation(listImg[position]);

            new Handler().postDelayed(() -> {}, 2000);

            binding.startBtn.setOnClickListener(this::close);

        }

        private void close(View view) {
            NavController navController = Navigation.findNavController((Activity) view.
                    getContext(), R.id.nav_host_fragment_activity_main);
            navController.navigateUp();
        }

    }

}
