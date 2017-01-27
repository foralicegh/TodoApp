package example.s.orijinaru3;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s on 2016/12/16.
 */
public class CustomAdapter extends ArrayAdapter<Homework> {
    List<Homework> hwList;
    private LayoutInflater inflater;

    //コンストラクタ
    public CustomAdapter(Context context, int textViewResourceId, List<Homework> cList) {
        super(context, textViewResourceId, cList);
        this.hwList = cList;
    }



    @Override
    public int getCount() {
        return hwList.size();
    }

    @Override
    public Homework getItem(int position) {
        return hwList.get(position);
    }


    private class ViewHolder {
        TextView subjectTv;
      //  TextView deadlineTv;
        TextView contentTv;
        TextView diffTv;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;


        if (convertView == null) {
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.subject_list_new, null);
            TextView subject = (TextView) convertView.findViewById(R.id.subject);
//            TextView deadline = (TextView) convertView.findViewById(R.id.deadline);
            TextView content = (TextView) convertView.findViewById(R.id.content);
            TextView diff = (TextView) convertView.findViewById(R.id.diff);
            holder = new ViewHolder();
            holder.subjectTv = subject;
        //    holder.deadlineTv = deadline;
            holder.contentTv = content;
            holder.diffTv = diff;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Homework item = getItem(position);

        if (item != null) {

            holder.subjectTv.setText(item.getSubject());
      //      holder.deadlineTv.setText(item.getDeadline());
            holder.contentTv.setText(item.getContent());
            holder.diffTv.setText(String.valueOf(item.getDiffday()));
        }

        return convertView;


        // 行毎に背景色を変える
//        if(position%2==0){
//            holder.labelText.setBackgroundColor(Color.parseColor("#aa0000"));
//        }else{
//            holder.labelText.setBackgroundColor(Color.parseColor("#880000"));
//        }
//
//        // XMLで定義したアニメーションを読み込む
//        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.item_motion);
//        // リストアイテムのアニメーションを開始
//        view.startAnimation(anim);


    }
}


