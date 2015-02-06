package android.couchbaselabs.com.instamaterial;

import android.app.Activity;
import android.content.Context;
import android.couchbaselabs.com.instamaterial.view.SquaredImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.couchbase.lite.Document;
import com.couchbase.lite.LiveQuery;
import com.couchbase.lite.QueryEnumerator;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ANIMATED_ITEMS_COUNT = 2;
    private int lastAnimatedPosition = -1;

    private Context context;

    public FeedAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new CellFeedViewHolder(view);
    }

    private void runEnterAnimation(View view, int position) {
        if (position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(Utils.getScreenHeight(context));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        runEnterAnimation(viewHolder.itemView, position);
        CellFeedViewHolder holder = (CellFeedViewHolder) viewHolder;

        if (position % 2 == 0) {
            holder.ivFeedImage.setImageResource(R.drawable.img_feed_center_1);
        } else {
            holder.ivFeedImage.setImageResource(R.drawable.img_feed_center_2);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        TextView ivFeedUsername;
        SquaredImageView ivFeedImage;
        TextView ivFeedStatus;

        public CellFeedViewHolder(View view) {
            super(view);
            ivFeedUsername = (TextView) view.findViewById(R.id.ivFeedUsername);
            ivFeedImage = (SquaredImageView) view.findViewById(R.id.ivFeedImage);
            ivFeedStatus = (TextView) view.findViewById(R.id.ivFeedStatus);
        }
    }

}