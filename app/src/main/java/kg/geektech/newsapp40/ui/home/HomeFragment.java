package kg.geektech.newsapp40.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import kg.geektech.newsapp40.News;
import kg.geektech.newsapp40.NewsAdapter;
import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentHomeBinding;
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new NewsAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragmentResultListener();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment();
            }
        });


        }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);

    }



    private void initFragmentResultListener() {
        getParentFragmentManager().setFragmentResultListener("rk_news",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        News news = (News) result.getSerializable("news");
                        adapter.setNewsList(news);
                        Log.e("Home", "text:" + news.getTitle());

                    }
                });
        initRv()
        ;
    }

    private void initRv() {
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new NewsAdapter.onItemClick() {
            @Override
            public void onClick(int pos) {

            }

            @Override
            public void onLongClick(int pos) {
                new AlertDialog.Builder(requireContext()).setTitle("Delete?").setMessage("Are you sure?")
                        .setNegativeButton("CANCEL", null)
                        .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.getNews(pos);
                                adapter.removeItem(pos);


                            }

                        })
                        .show();
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
