package kg.geektech.newsapp40;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.newsapp40.databinding.ItemNewsBinding;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {


    private List<News> newsList =  new ArrayList<>();
    private ItemNewsBinding binding;
    private onItemClick onItemClick;

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }


    public void setNewsList(News news) {
        this.newsList.add(news);
        notifyItemInserted(0);
    }

    public News getNews(int pos) {
        return newsList.get(pos);
    }

    public void removeItem(int pos) {
        newsList.remove(pos);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(newsList.get(position));
        if (position %2 == 0){
            holder.itemView.setBackgroundResource(R.drawable.gradient);
        }else{

            holder.itemView.setBackgroundResource(R.drawable.gradient2);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();

    }
    public void addItems(List<News> list) {
        this.newsList = list;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());

        }

        public void onBind(News news) {
            binding.textView.setText(news.getTitle());
            binding.tvTime.setText(String.valueOf(news.getCreatedAt()));

            binding.getRoot().setOnClickListener(v -> {
                onItemClick.onClick(getAdapterPosition());
            });
            binding.getRoot().setOnLongClickListener(v -> {
                onItemClick.onLongClick(getAdapterPosition());
                return true;
            });
        }
    }




    public interface onItemClick {
        void onClick(int pos);
        void onLongClick(int pos);
    }

}
