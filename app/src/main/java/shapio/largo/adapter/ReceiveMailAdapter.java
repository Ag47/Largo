package shapio.largo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import shapio.largo.R;
import shapio.largo.activity.WriteMessageContentActivity;
import shapio.largo.model.Mail;

/**
 * Created by TheOSka on 16/4/2016.
 */
public class ReceiveMailAdapter extends RecyclerView.Adapter<ReceiveMailAdapter.MyViewHolder> {

    ArrayList<Mail> data = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private LinearLayout gotRowTags;
    private boolean isButtomSheet;


    public ReceiveMailAdapter(Context context, ArrayList<Mail> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // get the root of the custom row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sender, parent, false);

        // pass the view to the view holder
        MyViewHolder holder = new MyViewHolder(view);
        Log.v("result", "in the MyViewHolder");
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Mail current = data.get(position);

        holder.username.setText(data.get(position).getUserName());
//        holder.paperDesc.setText(data.get(position).getPaperDesc());
        holder.userIcon.setImageResource(data.get(position).getProfileImage());
    }


    public void addItem(Mail item, int index) {

        data.add(item);
        notifyItemInserted(index);
    }

    @Override
    public int getItemCount() {
        Log.v("result ", "the getItemCount size is " + data.size());
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView username;
        CircleImageView userIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            userIcon = (CircleImageView) itemView.findViewById(R.id.user_image);
            username = (TextView) itemView.findViewById(R.id.user_name);
//            paperDesc = (TextView) itemView.findViewById(R.id.paper_desc);
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

