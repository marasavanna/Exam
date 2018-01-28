package com.example.mara.exam.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mara.exam.R;
import com.example.mara.exam.db.BackgroundWorker;
import com.example.mara.exam.domain.Project;

import java.util.List;

/**
 * Created by Mara on 1/26/2018.
 */

public class IdeaAdapter extends RecyclerView.Adapter<IdeaAdapter.IdeaVH>  {

    Context ctx;
    List<Project> projects;

    public IdeaAdapter(Context ctx, List<Project> projects) {
        this.ctx = ctx;
        this.projects = projects;
    }

    @Override
    public IdeaVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.idea_layout, parent, false);
        return new IdeaVH(view);
    }

    @Override
    public void onBindViewHolder(IdeaVH holder, final int position) {
        final Project project = projects.get(position);
        holder.name.setText(project.getName());
        holder.budget.setText(String.valueOf(project.getBudget()));
        holder.type.setText(project.getType());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String special = "delete";
                        String id = String.valueOf(project.getId());
                        BackgroundWorker bw = new BackgroundWorker(ctx);
                        bw.execute(special, id);
                        notifyDataSetChanged();
                        dialogInterface.dismiss();
                        projects.remove(position);
                        notifyDataSetChanged();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    class IdeaVH extends RecyclerView.ViewHolder {

        private TextView name, budget, type;
        private Button remove;

        public IdeaVH(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.idea_name);
            budget = itemView.findViewById(R.id.idea_budget);
            type = itemView.findViewById(R.id.idea_type);

            remove = itemView.findViewById(R.id.delete_idea);
        }

    }

}
