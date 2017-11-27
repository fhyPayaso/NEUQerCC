package cn.abtion.neuqercc.mine.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.team.adapters.TeamMemberListAdapter;
import cn.abtion.neuqercc.team.models.TeamMemberListModel;
import cn.abtion.neuqercc.widget.CustomLinearLayoutManager;

public class MineTeamIfromationActivity extends ToolBarActivity {




    private ArrayList<TeamMemberListModel> teamMemberListModels;

    @BindView(R.id.recylerview_team_member)
    RecyclerView recylerviewTeamMember;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_team_ifromation;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        this.setActivityTitle(getString(R.string.title_team_information));

        recylerviewTeamMember.setNestedScrollingEnabled(false);
        teamMemberListModels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            teamMemberListModels.add(new TeamMemberListModel());
        }
        TeamMemberListAdapter teamMemberListAdapter = new TeamMemberListAdapter(this, teamMemberListModels);

        recylerviewTeamMember.setLayoutManager(new CustomLinearLayoutManager(this, CustomLinearLayoutManager.VERTICAL, false));
        recylerviewTeamMember.setAdapter(teamMemberListAdapter);

    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.btn_join_team)
    public void onButtonJoinTeamClicked() {

        Intent intent = new Intent(MineTeamIfromationActivity.this,UpdateTeamInformationActivity.class);
        startActivity(intent);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("确认加入该队吗？");
//        builder.setNegativeButton("取消", null);
//        builder.setPositiveButton("确定", null);
//        builder.show();
    }




}
