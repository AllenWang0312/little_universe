package edu.tjrac.swant.bestcase.moudle.main.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

import edu.tjrac.swant.bestcase.R;
import edu.tjrac.swant.bestcase.base.ProgressBarActivity;
import edu.tjrac.swant.bestcase.been.interfaze.OnItemClickListener;
import edu.tjrac.swant.bestcase.manager.SystemResProvider;

public class GalleryActivity extends ProgressBarActivity {

    private HashMap<String, List<String>> mGruopMap = new HashMap<String, List<String>>();
    RecyclerView mRecyclerView;

    public static final int GET_RES_FINISH = 1;
    Handler mUiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        //initData
        getImages();
        //initView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyc_gallery_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//bindData
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        GalleryAdapter adapter = new GalleryAdapter(this, mGruopMap);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(GalleryActivity.this,ImagesActivity.class));
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中
     */
    private void getImages() {
        mGruopMap = SystemResProvider.getSystemImgs(this);
        mUiHandler.sendEmptyMessage(GET_RES_FINISH);
    }

    class GalleryAdapter extends RecyclerView.Adapter<ViewHolder> {
        Context mContext;
        LayoutInflater mInflater;
        HashMap<String, List<String>> data;
        Object[] keys;
        OnItemClickListener mOnItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            mOnItemClickListener = onItemClickListener;
        }

        GalleryAdapter(Context context, HashMap<String, List<String>> data) {
            mContext = context;
            mInflater = LayoutInflater.from(mContext);
            this.data = data;
            keys = data.keySet().toArray();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(mInflater.inflate(R.layout.gallery_recyc_item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            List<String> imgs = mGruopMap.get(keys[i]);
            Glide.with(GalleryActivity.this).load(imgs.get(0)).into(viewHolder.img);
            viewHolder.text.setText(keys[i].toString());
            viewHolder.count.setText("(" + imgs.size() + ")");
            viewHolder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viewHolder.root, i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return keys.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        ImageView img;
        TextView text;
        TextView count;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            img = (ImageView) itemView.findViewById(R.id.iv_item_gallery);
            text = (TextView) itemView.findViewById(R.id.tv_item_gallery);
            count = (TextView) itemView.findViewById(R.id.tv2_item_gallery);
        }
    }
}
