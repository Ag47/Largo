package shapio.largo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import shapio.largo.R;
import shapio.largo.activity.WriteMessageContentActivity;
import shapio.largo.model.Paper;

/**
 * Created by TheOSka on 16/4/2016.
 */
public class SelectPaperAdapter extends RecyclerView.Adapter<SelectPaperAdapter.MyViewHolder> {

    ArrayList<Paper> data = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private LinearLayout gotRowTags;
    private boolean isButtomSheet;


    public SelectPaperAdapter(Context context, ArrayList<Paper> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // get the root of the custom row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_select_paper_theme, parent, false);

        // pass the view to the view holder
        MyViewHolder holder = new MyViewHolder(view);
        Log.v("result", "in the MyViewHolder");
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Paper current = data.get(position);
        Log.v("bottomSheet", "the bs name" + current.getPaperName());

        holder.paperName.setText(data.get(position).getPaperName());
//        holder.paperDesc.setText(data.get(position).getPaperDesc());
        holder.paperThumbIcon.setImageResource(data.get(position).getPaperThumbImage());
    }


    public void addItem(Paper item, int index) {

        data.add(item);
        notifyItemInserted(index);
    }

    @Override
    public int getItemCount() {
        Log.v("result ", "the getItemCount size is " + data.size());
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView paperThumbIcon;
        TextView paperName, paperDesc;

        public MyViewHolder(View itemView) {
            super(itemView);
            paperThumbIcon = (ImageView) itemView.findViewById(R.id.paper_thumb_icon);
            paperName = (TextView) itemView.findViewById(R.id.paper_name);
//            paperDesc = (TextView) itemView.findViewById(R.id.paper_desc);
            paperThumbIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Paper current = data.get(getPosition());
                        Intent mIntent = new Intent(context, WriteMessageContentActivity.class);
                        Bundle paperMaterial = new Bundle();

                        if(current.getPaperName().equals(context.getResources().getString(R.string.paper_general))){
                            Toast.makeText(context,"you clicked general", Toast.LENGTH_LONG).show();
                            Log.v("oska","adapter general ");
                            paperMaterial.putString(context.getResources().getString(R.string.papers_material_type), context.getResources().getString(R.string.paper_general));
                        }else if(current.getPaperName().equals(context.getResources().getString(R.string.paper_love))) {
                            paperMaterial.putString(context.getResources().getString(R.string.papers_material_type), context.getResources().getString(R.string.paper_love));
                            Log.v("oska","adapter love");

                        }
                        else if(current.getPaperName().equals(context.getResources().getString(R.string.paper_apologize))) {
                            paperMaterial.putString(context.getResources().getString(R.string.papers_material_type), context.getResources().getString(R.string.paper_apologize));
                        }
                        mIntent.putExtras(paperMaterial);
                        context.startActivity(mIntent);
                    }
                });
            }

        @Override
        public void onClick(View v) {
//            Paper current = data.get(getPosition());
//            if(current.getPaperName().equals(context.getResources().getString(R.string.paper_general))){
//                Toast.makeText(context,"you clicked general", Toast.LENGTH_LONG).show();
//
//            }

        }
    }
}

