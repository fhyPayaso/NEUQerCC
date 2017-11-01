package cn.abtion.neuqercc.account.activities;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.transition.Explode;
import android.transition.Slide;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.models.LoginRequest;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.main.MainActivity;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.RegexUtil;
import cn.abtion.neuqercc.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/9/22 17:59.
 * email caiheng@hrsoft.net
 */

public class LoginActivity extends NoBarActivity {


    @BindView(R.id.edit_identifier)
    TextInputEditText editIdentifier;
    @BindView(R.id.edit_password)
    TextInputEditText editPassword;

    private LoginRequest loginRequest;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initVariable() {
        loginRequest = new LoginRequest();
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(300);
            getWindow().setEnterTransition(explode);

            Slide slide = new Slide();
            slide.setDuration(300);
            getWindow().setExitTransition(slide);
        }
    }

    @Override
    protected void loadData() {

    }

    /**
     * 登录按钮点击事件
     */
    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
        loginRequest.setIdentifier(editIdentifier.getText().toString().trim());
        loginRequest.setPassword(editPassword.getText().toString().trim());

        if (isDataTrue()) {
            login();
        }
    }

    /**
     * 注册点击事件
     */
    @OnClick(R.id.btn_register)
    public void onBtnRegisterClicked() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 忘记密码点击事件
     */
    @OnClick(R.id.txt_forget_password)
    public void onTxtForgetPasswordClicked() {
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
        finish();

    }


    /**
     * 进行登录的相关操作的方法
     */
    private void login() {
        //弹出progressDialog
        progressDialog.setMessage("请稍候");
        progressDialog.show();

        //网络请求
        RestClient.getService().login(loginRequest).enqueue(new DataCallback<APIResponse>() {

            //请求成功时回调
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                ToastUtil.showToast("登录成功");

                //跳转至MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            //请求失败时回调
            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {

            }

            //无论成功或者失败时都回调，用于dismissDialog或隐藏其他控件
            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });

    }

    /**
     * 用于TextInputEditText控件显示错误信息
     *
     * @param textInputEditText 控件对象
     * @param error             错误信息
     */
    private void showError(TextInputEditText textInputEditText, String error) {
        textInputEditText.setError(error);
        textInputEditText.setFocusable(true);
        textInputEditText.setFocusableInTouchMode(true);
        textInputEditText.requestFocus();
    }

    /**
     * 验证用户输入是否正确
     *
     * @return 正确为true
     */
    private boolean isDataTrue() {
        boolean flag = true;
        if (editIdentifier.getText().toString().trim().length() == 0) {
            showError(editIdentifier, "账号不可为空");
            flag = false;
        } else if (RegexUtil.checkMobile(editIdentifier.getText().toString().trim())) {
            showError(editIdentifier, "账号不可为空");
            flag = false;
        } else if (editPassword.getText().toString().trim().length() < Config.PASSWORD_MIN_LIMIT) {
            showError(editPassword, "密码不得少于6位");
            flag = false;
        } else if (editPassword.getText().toString().trim().length() > Config.PASSWORD_MAX_LIMIT) {
            showError(editPassword, "密码不得多于16位");
            flag = false;
        }
        return flag;
    }


}
