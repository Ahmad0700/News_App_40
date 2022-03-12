package kg.geektech.newsapp40;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import kg.geektech.newsapp40.databinding.FragmentBoardBinding;
import kg.geektech.newsapp40.ui.board.BoardAdapter;
public class BoardFragment extends Fragment {
    private FragmentBoardBinding binding;
    private BoardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new BoardAdapter();
        binding.boardVp.setAdapter(adapter);
        binding.wormDotsIndicator.setViewPager2(binding.boardVp);

        binding.boardVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);


                if (position == 3) {
                    binding.tvSkip.setVisibility(View.INVISIBLE);
                } else {
                    binding.tvSkip.setVisibility(View.VISIBLE);
                }
                binding.tvSkip.setOnClickListener(v -> {
                    NavController navController = Navigation.findNavController((Activity) v.getContext(),
                            R.id.board_vp);
                    navController.navigateUp();
                });
            }
        });

    }
}