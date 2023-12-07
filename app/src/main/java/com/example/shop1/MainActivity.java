package com.example.shop1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.shop1.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private CheckBox selectAll;
    private boolean[]f;
    private TextView clr;
    private TextView del;
    private Button buttonAll;
    private TextView priceAll;

    private List<Shop> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private ShopAdapter adapter;
    public void initShop(){
        Shop shop=new Shop("5G手机 小米 红米","Redmi K50 电竞版 全新",R.drawable.pic1,1,3849);
        list.add(shop);
        Shop shop1=new Shop("全友家居餐桌椅","家用小户型吃饭桌子",R.drawable.pic2,1,4071);
        list.add(shop1);
        Shop shop2=new Shop("15.6英寸轻薄游戏本","华硕天选air 2022 12代",R.drawable.pic3,1,9999);
        list.add(shop2);
        Shop shop3=new Shop("圣闲女童爱莎公主裙","春夏款可拆披纱棉质舒适",R.drawable.pic4,1,108);
        list.add(shop3);
        Shop shop4=new Shop("高钙儿童纯牛奶","伊利 QQ星儿童纯牛奶",R.drawable.pic5,1,56);
        list.add(shop4);
        Shop shop5=new Shop("男女同款厚底熊猫鞋","老爹鞋休闲运动鞋情侣鞋",R.drawable.pic6,1,359);
        list.add(shop5);
        Shop shop6=new Shop("一次性医用外科口罩","无菌三层外科灭菌口罩",R.drawable.pic7,1,39);
        list.add(shop6);
        Shop shop7=new Shop("苹果13promax手机壳","iphone13保护套透明超薄",R.drawable.pic8,1,34);
        list.add(shop7);
        Shop shop8=new Shop("佳洁士3D炫白牙膏","美白牙膏去黄去牙渍",R.drawable.pic9,1,27);
        list.add(shop8);
        Shop shop9=new Shop("大连美早樱桃","车厘子巨无霸JJJ级",R.drawable.pic10,1,160);
        list.add(shop9);
        Shop shop10=new Shop("华为nova9","9号色 8+128G全网通",R.drawable.pic11,1,2519);
        list.add(shop10);
        Shop shop11=new Shop("蓝牙耳机","oppo 白色尊享升级版",R.drawable.pic12,1,89);
        list.add(shop11);
        Shop shop12=new Shop(" T9民谣吉他","新手入门练习琴guitar",R.drawable.pic13,1,175);
        list.add(shop12);
        Shop shop13=new Shop("卡西欧（CASIO）手表","商务休闲男表石英表",R.drawable.pic14,1,505);
        list.add(shop13);

    }

    public void initRecyclerView2() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // 定义一个线性布局管理器（默认是垂直排列）
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //默认垂直排列
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ShopAdapter(list);
        recyclerView.setAdapter(adapter);

        //添加默认的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    //全选和全不选功能
    public void selectAll(){
        selectAll=(CheckBox)findViewById(R.id.checkBoxall);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        f=new boolean[100];
        selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    for(int i=0;i<100;i++){
                        adapter.flag[i]=true;
                        adapter.notifyDataSetChanged();
                    }
                }
                else{
                    for(int i=0;i<100;i++){
                        adapter.flag[i]=false;
                        adapter.notifyDataSetChanged();
                    }
                }
                //更新数据
                // adapter = new ShopAdapter(list);
                // adapter.notifyDataSetChanged();
            }
        });
    }
    //移除功能
    public void removeItem(int position){
        list.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position,list.size());//必须用这个不然会紊乱
    }
    //删除功能
    public  void delete(){
        del=(TextView)findViewById(R.id.delect);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(MainActivity.this,"删除功能",Toast.LENGTH_SHORT).show();
                for(int i=0;i<list.size();i++){
                    if(adapter.flag[i]==true){
                        removeItem(i);
                        //删除list数组后还要更新flag数组 i没有了i+1的变成了i所有flag[i]=flag[1+1]
                        for(int j=i;j<list.size()-1;j++) adapter.flag[j]=adapter.flag[j+1];
                        //删除后i,i--才行,列如第0行删除后仍然从第0行开始
                        i--;
                    }
                }
            }
        });
    }
    //清空功能
    public void clear(){
        clr=(TextView)findViewById(R.id.clear);
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Toast.makeText(MainActivity.this,"清空功能",Toast.LENGTH_SHORT).show();
                list.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
    //计算合计总价钱
    public void allPrice(){
        buttonAll=(Button)findViewById(R.id.buttonall);
        priceAll=(TextView)findViewById(R.id.priceall);
        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priceAll.setText(adapter.getSum1()+".0");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initShop();
        initRecyclerView2();
        selectAll();
        delete();
        clear();
        allPrice();

        //ActionBar
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        //清屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setEventListener();
    }

    private void setEventListener() {
        adapter.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                priceAll.setText(adapter.getSum1()+".0元");
            }

            @Override
            public void photo_addClick(ShopAdapter.ViewHolder holder, View view, int position) {
                Shop shop= list.get(position);
                int x=shop.getNum();
                x++;
                shop.setNum(x);
                holder.num.setText(x+"");
                if(adapter.flag[position]==true){
                    priceAll.setText(adapter.getSum1()+".0元");
                }
            }

            @Override
            public void photo_downClick(ShopAdapter.ViewHolder holder, View view, int position) {
                Shop shop= list.get(position);
                int x=shop.getNum();
                x--;
                if(x<0) x=0;
                shop.setNum(x);
                holder.num.setText(x+"");
                if(adapter.flag[position]==true){
                    priceAll.setText(adapter.getSum1()+".0元");
                }
            }
        });
    }

}
