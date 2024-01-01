package com.dragon.multigame._AnimalRoulate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dragon.multigame.Interface.ClassCallback;
import com.dragon.multigame.R;
import com.dragon.multigame.Utils.Functions;
import com.dragon.multigame.Utils.SharePref;

import java.util.List;

public class AnimalRouletteAdapter extends RecyclerView.Adapter<AnimalRouletteAdapter.holder> {

    List<AnimalRouletteModel> arraylist;
    ClassCallback callback;
    Context context;

    public AnimalRouletteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animalroullete_type,parent,false));
    }

    public void setArraylist(List<AnimalRouletteModel> arraylist) {
        this.arraylist = arraylist;
        notifyDataSetChanged();
    }

    public void onItemSelectListener(ClassCallback callback){
        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        AnimalRouletteModel model = arraylist.get(position);

        if(model != null)
            holder.bind(model);

    }

    @Override
    public int getItemCount() {
        return arraylist != null ? arraylist.size() : 0;
    }

    class holder extends RecyclerView.ViewHolder{
        public holder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(AnimalRouletteModel model){
            int postion = getAdapterPosition();
            ImageView ivContainerbg = itemView.findViewById(R.id.ivContainerbg);
            Drawable topleft_corner = Functions.getDrawable(context,R.drawable.ic_carrounlate_topleft_side);
            Drawable topright_corner = Functions.getDrawable(context,R.drawable.ic_carrounlate_topright_side);
            Drawable center_corner = Functions.getDrawable(context,R.drawable.ic_carrounlate_centerside);
            Drawable bottomleft_corner = Functions.getDrawable(context,R.drawable.ic_carrounlate_btmleftside);
            Drawable bottomright_corner = Functions.getDrawable(context,R.drawable.ic_carrounlate_btmright_side);
            Drawable itembackground = center_corner;
            if(postion == 0)
            {
                itembackground = topleft_corner;
            }
            else if(postion == 3)
            {
                itembackground = topright_corner;
            }
            else if(postion == 4)
            {
                itembackground = bottomleft_corner;
            }
            else if(postion+1 == arraylist.size())
            {
                itembackground = bottomright_corner;
            }

            ivContainerbg.setBackground(itembackground);
            ivContainerbg.clearAnimation();
            TextView tvJackpotHeading = itemView.findViewById(R.id.tvJackpotHeading);
            TextView tvJackpotamount = itemView.findViewById(R.id.tvJackpotamount);
            TextView tvJackpotSelectamount = itemView.findViewById(R.id.tvJackpotSelectamount);
            TextView tvUsersAddedAmount = itemView.findViewById(R.id.tvUsersAddedAmount);
            ImageView ivCarroulate = itemView.findViewById(R.id.ivCarroulate);
            TextView tvBonus=itemView.findViewById(R.id.iBonus);
            RelativeLayout rltAmountadded = itemView.findViewById(R.id.rltAmountadded);

            tvJackpotHeading.setText(model.rule_type);
            tvBonus.setText(model.bonus);
 //           tvJackpotamount.setText(""+model.added_amount);
            tvJackpotSelectamount.setText(""+model.select_amount);
            Glide.with(context).load(Functions.getDrawable(context,model.getRule_icon())).into(ivCarroulate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((AnimalRoulette_A)context).playButtonTouchSound();
                    Animation animBounce = AnimationUtils.loadAnimation(context,
                            R.anim.bounce);
                    animBounce.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    itemView.startAnimation(animBounce);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            callback.Response(v,postion,model);
                        }
                    },700);



                }
            });

            rltAmountadded.setVisibility(View.GONE);

            if(model.isAnimatedAddedAmount()
                    && model.getLast_added_id() != SharePref.getInstance().getInt(SharePref.lastAmountAddedID))
            {
                SharePref.getInstance().putInt(SharePref.lastAmountAddedID,model.getLast_added_id());
                rltAmountadded.setVisibility(View.VISIBLE);
                tvUsersAddedAmount.setText("+"+model.getLast_added_amount());
                SlideToAbove(rltAmountadded,model);
            }

            if(model.isWine())
            {
                model.setWine(false);
                ivContainerbg.setBackground(Functions.getDrawable(context,R.drawable.ic_jackpot_rule_bg_selected));
                initiAnimation();
                ivContainerbg.startAnimation(blinksAnimation);
            }
        }
    }

    Animation blinksAnimation;
    private void initiAnimation() {
        blinksAnimation = AnimationUtils.loadAnimation(context,R.anim.blink);
        blinksAnimation.setDuration(500);
        blinksAnimation.setRepeatCount(Animation.INFINITE);
//        blinksAnimation.setStartOffset(700);
    }



    public void SlideToAbove(View animationView,AnimalRouletteModel model) {
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -5.0f);

        slide.setDuration(1000);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        animationView.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                animationView.clearAnimation();
                animationView.setVisibility(View.GONE);

//                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
//                        rl_footer.getWidth(), rl_footer.getHeight());
//                // lp.setMargins(0, 0, 0, 0);
//                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//                rl_footer.setLayoutParams(lp);

            }

        });

    }

}
