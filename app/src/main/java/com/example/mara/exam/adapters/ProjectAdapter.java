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

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectVH> {


    private Context ctx;
    private List<Project> projects;

    public ProjectAdapter(Context ctx, List<Project> projects) {
        this.ctx = ctx;
        this.projects = projects;
    }

    @Override
    public ProjectVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.project_layout, parent, false);
        return new ProjectVH(view);
    }

    @Override
    public void onBindViewHolder(ProjectVH holder, final int position) {
        final Project project = projects.get(position);
        holder.name.setText(project.getName());
        holder.budget.setText(String.valueOf(project.getBudget()));
        holder.type.setText(project.getType());
        holder.status.setText(project.getStatus());

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String special = "remove";
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

        holder.discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Discard");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String special = "discard";
                        String id = String.valueOf(project.getId());
                        BackgroundWorker bw = new BackgroundWorker(ctx);
                        bw.execute(special, id);
                        notifyDataSetChanged();
                        dialogInterface.dismiss();
                        project.setStatus("discarded");
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
        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Approve");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String special = "approve";
                        String id = String.valueOf(project.getId());
                        BackgroundWorker bw = new BackgroundWorker(ctx);
                        bw.execute(special, id);
                        notifyDataSetChanged();
                        dialogInterface.dismiss();
                        project.setStatus("approve");
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
        holder.promote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Promote");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String special = "promote";
                        String id = String.valueOf(project.getId());
                        BackgroundWorker bw = new BackgroundWorker(ctx);
                        bw.execute(special, id);
                        notifyDataSetChanged();
                        dialogInterface.dismiss();
                        project.setStatus("pending");
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

    class ProjectVH extends RecyclerView.ViewHolder{

        private TextView name, budget, type, status;
        private Button promote, approve, discard, remove;

        public ProjectVH(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.project_name);
            budget = itemView.findViewById(R.id.project_budget);
            type = itemView.findViewById(R.id.project_type);
            status = itemView.findViewById(R.id.project_status);

            promote = itemView.findViewById(R.id.promote_project);
            approve = itemView.findViewById(R.id.approve_project);
            discard = itemView.findViewById(R.id.discard_project);
            remove = itemView.findViewById(R.id.delete_project);
        }


    }
}
