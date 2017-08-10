package com.us.prince.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.us.prince.ItemAdapter;
import com.us.prince.R;
import com.us.prince.bean.ItemBean;
import com.us.prince.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by Upen on 26/7/17 in Prince.
 */

public class HomeActivity extends BaseActivity {

    private Context context;

    //Database
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference usersDatabaseReference;
    private DatabaseReference itemsDatabaseReference;

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;

    private RecyclerView rcvItem;
    private RecyclerView.LayoutManager layoutManager;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = HomeActivity.this;

        findViewById();
        initCollapseToolbar();
        getItemList();
        initDatabase();
        addData();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    public void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rcvItem = (RecyclerView) findViewById(R.id.rcvItem);
        layoutManager = new GridLayoutManager(this, 2);
        rcvItem.setLayoutManager(layoutManager);
        rcvItem.addItemDecoration(new GridSpacingItemDecoration(2, dpToDx(10), true));
        rcvItem.setItemAnimator(new DefaultItemAnimator());

        itemAdapter = new ItemAdapter(context, getItemList());
        rcvItem.setAdapter(itemAdapter);
    }

    private int dpToDx(int dp) {
        Resources resources = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        public int spacing, spanCount;
        public boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); //item position
            int column = position % spanCount; //column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private void initCollapseToolbar() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));

                    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo);

                    Palette.from(icon).generate(new Palette.PaletteAsyncListener() {
                        public void onGenerated(Palette palette) {
//                            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();

                            if (palette.getVibrantSwatch() != null) {
                                System.out.println("1");
                                collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedSwatch().getRgb());
//                                outerLayout.setBackgroundColor(vibrantSwatch.getRgb());
//                                titleText.setTextColor(vibrantSwatch.getTitleTextColor());
//                                bodyText.setTextColor(vibrantSwatch.getBodyTextColor());
                            } else if (palette.getDarkMutedSwatch() != null) {
                                System.out.println("2");

                                collapsingToolbarLayout.setCollapsedTitleTextColor(palette.getDarkMutedSwatch().getRgb());

                            } else if (palette.getDarkVibrantSwatch() != null) {
                                System.out.println("3");
                                collapsingToolbarLayout.setCollapsedTitleTextColor(palette.getDarkVibrantSwatch().getRgb());

                            } else if (palette.getLightMutedSwatch() != null) {
                                System.out.println("4");
                                collapsingToolbarLayout.setCollapsedTitleTextColor(palette.getLightMutedSwatch().getRgb());

                            } else if (palette.getMutedSwatch() != null) {
                                System.out.println("5");
                                collapsingToolbarLayout.setCollapsedTitleTextColor(palette.getMutedSwatch().getRgb());

                            } else if (palette.getLightVibrantSwatch() != null) {
                                System.out.println("6");
                                collapsingToolbarLayout.setCollapsedTitleTextColor(palette.getLightVibrantSwatch().getRgb());

                            }
                        }
                    });
                    isShow = true;
                } else {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    public ArrayList<ItemBean> getItemList() {

        ArrayList<ItemBean> itemBeanArrayList = new ArrayList<>();
        ItemBean itemBean;

        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        itemBean = new ItemBean();
        itemBean.itemName = "True Romance";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[0];
        itemBeanArrayList.add(itemBean);

        itemBean = new ItemBean();
        itemBean.itemName = "Xscpae";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[1];
        itemBeanArrayList.add(itemBean);

        itemBean = new ItemBean();
        itemBean.itemName = "Maroon 5";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[2];
        itemBeanArrayList.add(itemBean);

        itemBean = new ItemBean();
        itemBean.itemName = "Born to Die";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[3];
        itemBeanArrayList.add(itemBean);

        itemBean = new ItemBean();
        itemBean.itemName = "Honeymoon";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[4];
        itemBeanArrayList.add(itemBean);

        itemBean = new ItemBean();
        itemBean.itemName = "I Need a Doctor";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[5];
        itemBeanArrayList.add(itemBean);

        itemBean = new ItemBean();
        itemBean.itemName = "Loud";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[6];
        itemBeanArrayList.add(itemBean);

        itemBean = new ItemBean();
        itemBean.itemName = "Legend";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[7];
        itemBeanArrayList.add(itemBean);

        itemBean = new ItemBean();
        itemBean.itemName = "Hello";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[8];
        itemBeanArrayList.add(itemBean);

        itemBean = new ItemBean();
        itemBean.itemName = "Greatest Hits";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[9];
        itemBeanArrayList.add(itemBean);


        itemBean = new ItemBean();
        itemBean.itemName = "JT The 20/20";
        itemBean.price = "13";
        itemBean.itemCoverImage = covers[10];
        itemBeanArrayList.add(itemBean);

        return itemBeanArrayList;
    }

    private void initDatabase() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        usersDatabaseReference = databaseReference.child("users");
        itemsDatabaseReference = databaseReference.child("items");
    }

    public UserBean createUser() {
        System.out.println("=============createUser== ");

        UserBean userBean = new UserBean();
        userBean.email = "upendrashah47@gmail.com";
        userBean.password = "12345678";

        return userBean;
    }

    public ArrayList<ItemBean> createItemList() {

        ArrayList<ItemBean> itemBeanArrayList = new ArrayList<>();
        ItemBean itemBean;

        itemBean = new ItemBean();
        itemBean.itemName = "1";
        itemBean.itemDescription = "23.1111111111";
        itemBeanArrayList.add(itemBean);

        itemBean = new ItemBean();
        itemBean.itemName = "1";
        itemBean.itemDescription = "23.1111111111";
        itemBeanArrayList.add(itemBean);

        return itemBeanArrayList;
    }

    public void addData() {

        UserBean userBean = createUser();
        String userId = usersDatabaseReference.push().getKey();
        userBean.id = userId;
        usersDatabaseReference.child(userBean.id).setValue(userBean);

        for (ItemBean itemBean : createItemList()) {
            String itemId = itemsDatabaseReference.push().getKey();
            itemBean.id = itemId;
            itemsDatabaseReference.child(itemId).setValue(itemBean);
        }

    }

}
