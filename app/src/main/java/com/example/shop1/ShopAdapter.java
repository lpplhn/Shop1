package com.example.shop1;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.shop1.model.Shop;

import java.util.List;

/**
 *  适配器类要继承RecyclerView.Adapter类，并重新三个方法：
 * ◼ onCreateViewHolder()：用于创建ViewHolder实例（加载的自定义布局）
 * ◼ onBindViewHolder()：对子项的数据进行赋值（会在每个子项被滚动到屏幕内时执行）
 * ◼ getItemCount()：返回RecyclerView的子项数目
 * ◼ 适配器类中需要定义一个ViewHolder内部类，这两个类配合使用，共同定义数据的显示方式
 * ————————————————
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private List<Shop> list;
    public ShopAdapter(List<Shop>list){
        this.list=list;
    }
    public boolean[] flag = new boolean[100];
    public int sum=0;
    public int sum1=0;
    TextView priceAll;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    //接口
    public interface OnItemClickListener{
        public void OnItemClick(View view,int position);
        public void photo_addClick(@NonNull ViewHolder holder,View view,int position);
        public void photo_downClick(@NonNull ViewHolder holder,View view,int position);
    }
    //计算sum和
    public int getSum1(){
        sum1=0;
        for(int i=0;i<list.size();i++){
            if(flag[i]==true){
                sum1+=list.get(i).getNum()*list.get(i).getPrice();
            }
        }
        return sum1;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        ImageView photo_add;
        ImageView photo_down;
        TextView text1;
        TextView text2;
        TextView price;
        public TextView num;
        CheckBox radio;
        public ViewHolder(@NonNull View view) {
            super(view);
            photo=(ImageView) view.findViewById(R.id.photo);
            photo_add=(ImageView) view.findViewById(R.id.photo_add);
            photo_down=(ImageView) view.findViewById(R.id.ptoto_down);
            text1=(TextView) view.findViewById(R.id.text1);
            text2=(TextView) view.findViewById(R.id.test2);
            price=(TextView) view.findViewById(R.id.price);
            num=(TextView) view.findViewById(R.id.num);
            radio=(CheckBox) view.findViewById(R.id.radio);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shop,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        View view2=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent,false);
        ViewHolder viewHolder2=new ViewHolder(view2);//获取MainActivity的布局操作

        //监听事件
        //监听点击的图片事件
        viewHolder.photo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                setPosition( viewHolder.getAdapterPosition());
                int pos=viewHolder.getAdapterPosition();
                Shop shop=list.get(pos);
                Uri webpage;
                if(shop.getPhotoId()==R.drawable.pic1){
                    webpage = Uri.parse("https://item.jd.com/100018761109.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic2){
                    webpage = Uri.parse("https://item.jd.com/10023842249226.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic3){
                    webpage = Uri.parse("https://item.jd.com/100018633249.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic4){
                    webpage = Uri.parse("https://item.jd.com/10022335886010.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic5){
                    webpage = Uri.parse("https://item.jd.com/8534207.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic6){
                    webpage = Uri.parse("https://item.jd.com/100014205656.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic7){
                    webpage = Uri.parse("https://item.jd.com/100027703540.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic8){
                    webpage = Uri.parse("https://item.jd.com/10040851500488.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic9){
                    webpage = Uri.parse("https://item.jd.com/100031564120.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic10){
                    webpage = Uri.parse("https://item.jd.com/7170704.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic11){
                    webpage = Uri.parse("https://item.jd.com/10036491869801.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic12){
                    webpage = Uri.parse("https://item.jd.com/10032573727362.html");
                }
                else if(shop.getPhotoId()==R.drawable.pic13){
                    webpage = Uri.parse("https://item.jd.com/11820243915.html#none");
                }
                else if(shop.getPhotoId()==R.drawable.pic14){
                    webpage = Uri.parse("https://item.jd.com/1198996.html");
                }
                else {
                    webpage=Uri.parse("");
                }
                Intent intent = new Intent();
                intent.setAction( Intent.ACTION_VIEW );
                intent.setData( webpage );
                parent.getContext().startActivity(intent);
            }
        });
        //add+1
//        viewHolder.photo_add.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                int pos=viewHolder.getAdapterPosition();
//                Shop shop= list.get(pos);
//                int x=shop.getNum();
//                x++;
//                shop.setNum(x);
//                viewHolder.num.setText(x+"");
//                if(flag[pos]==true){
//                    priceAll=view2.findViewById(R.id.priceall);
//                    priceAll.setText("一共："+getSum1()+".0元");
//                    Toast.makeText(view.getContext(),""+priceAll.getText(),Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });

        //down-1
//        viewHolder.photo_down.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                int pos=viewHolder.getAdapterPosition();
//                Shop shop= list.get(pos);
//                int x=shop.getNum();
//                x--;
//                if(x<0) x=0;
//                shop.setNum(x);
//                viewHolder.num.setText(x+"");
//                if(flag[pos]==true){
//                    priceAll=view2.findViewById(R.id.priceall);
//                    priceAll.setText("一共："+getSum1()+".0元");
//                    Toast.makeText(view.getContext(),""+priceAll.getText(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        //设置图片监听事件



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shop shop=list.get(position);
        holder.text1.setText(shop.getText1());
        holder.text2.setText(shop.getText2());
        holder.price.setText(shop.getPrice()+".0");
        holder.num.setText(shop.getNum()+"");
        holder.photo.setImageResource(shop.getPhotoId());
        //       holder.photo_add.setImageResource(shop.getPhoto_addId());
//        holder.photo_down.setImageResource(shop.getPhoto_downId());
//        holder.radio.setVisibility(View.VISIBLE);
        //checkbutton的绑定
        holder.radio.setText((position+1)+"");//设置多选按钮的位置值，删除行后会更新
        holder.radio.setOnCheckedChangeListener(null);
        holder.radio.setChecked(flag[position]);

        holder.radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                flag[position]=b;
            }
        });

        holder.photo_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.photo_addClick(holder,view,position);
            }
        });
        holder.photo_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.photo_downClick(holder,view,position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.OnItemClick(view,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private int position;
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

}

