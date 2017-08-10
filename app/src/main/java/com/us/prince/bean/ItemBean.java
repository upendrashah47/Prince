package com.us.prince.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Upen on 29/7/17 in Prince.
 */

public class ItemBean implements Serializable {
    public String id;
    public String itemName;
    public String itemDescription;
    public ArrayList<ItemImageBean> itemImageList;
    public String price;
    public int itemCoverImage;
}
