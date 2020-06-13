package com.example.robotclock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class listViewConfig {
    private Context mContext;
    private SessionAdapter mSessionAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Sessions> sessions, List<String> keys){
        mContext = context;
        mSessionAdapter = new SessionAdapter(sessions, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mSessionAdapter);
    }

    class SessionItemView extends RecyclerView.ViewHolder {
        /*
        private TextView mTitle;
        private TextView mAuthor;
        private TextView mCategory;
        private TextView mCode;
         */
        private TextView mNo;
        private TextView mName;
        private TextView mTime;
        private TextView mDate;

        // tak crash
        private TextView mCorrect;
        private TextView mInccorect;
        private TextView mNoRespond;
        private TextView mTotal1, mTotal2, mTotal3;
        //private TextView mEquation1, mEquation2;

        private String key;

        public SessionItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.session_list_item, parent, false));
            mNo = (TextView) itemView.findViewById(R.id.noTV);

            mName = (TextView) itemView.findViewById(R.id.nameTV);
            mTime = (TextView) itemView.findViewById(R.id.timeTV);
            mDate = (TextView) itemView.findViewById(R.id.dateTV);
            // tak crash
            mCorrect = (TextView) itemView.findViewById(R.id.correctTV);
            mInccorect = (TextView) itemView.findViewById(R.id.incorrectTV);
            mNoRespond = (TextView) itemView.findViewById(R.id.noRespondTV);
            // total
            mTotal1 = (TextView) itemView.findViewById(R.id.totalTV1);
            mTotal2 = (TextView) itemView.findViewById(R.id.totalTV2);
            mTotal3 = (TextView) itemView.findViewById(R.id.totalTV3);
            // equation
            //mEquation1 = (TextView) itemView.findViewById(R.id.equationTV1);
            //mEquation2 = (TextView) itemView.findViewById(R.id.equationTV2);

            /*
            mTitle = (TextView) itemView.findViewById(R.id.titleTV);
            mAuthor = (TextView) itemView.findViewById(R.id.authorTV);
            mCategory = (TextView) itemView.findViewById(R.id.categoryTV);
            mCode = (TextView) itemView.findViewById(R.id.codeTV);
             */
        }

        public void bind(Sessions session, String key){
            /*
            mTitle.setText(session.getTitle());
            mAuthor.setText(session.getAuthor());
            mCategory.setText(session.getCategory());
            mCode.setText(session.getCode());
             */
            int correct = session.getCorrect();
            int incorrect = session.getIncorrect();
            int noRespond = session.getNoRespond();
            int num = Integer.parseInt(key) + 1;

            String[] output = session.getTime().split(" ");

            mNo.setText(Integer.toString(num) + ". ");

            mName.setText(session.getName());
            mTime.setText(output[0]);
            mDate.setText(output[1]);
            // punca crash: int tak tukar jadi String
            mCorrect.setText(Integer.toString(correct));
            mInccorect.setText(Integer.toString(incorrect));
            mNoRespond.setText(Integer.toString(noRespond));

            int total = correct + incorrect + noRespond;
            mTotal1.setText("/" + Integer.toString(total));
            mTotal2.setText("/" + Integer.toString(total));
            mTotal3.setText("/" + Integer.toString(total));

            /*
            int equation1 = (correct + incorrect) * 100 / total;
            int equation2 = (correct * 100) / total;

            mEquation1.setText(Integer.toString(equation2) + "%");
            mEquation2.setText(Integer.toString(equation1) + "%");

             */

            this.key = key;
        }
    }
    class SessionAdapter extends RecyclerView.Adapter<SessionItemView>{
        private List<Sessions> mSessionList;
        private List<String> mKeys;

        public SessionAdapter(List<Sessions> mSessionList, List<String> mKeys) {
            this.mSessionList = mSessionList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public SessionItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SessionItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SessionItemView holder, int position) {
            holder.bind(mSessionList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mSessionList.size();
        }
    }
}
