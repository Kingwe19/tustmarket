<template>
  <div class="registerDivClass">
    <el-card shadow="always" style="top:50px;margin: 0 auto;width: 29%;min-height: 650px;position: relative;">
      <div style="text-align:center;margin-top: 30px;">
        <img src="http://localhost:9090/keda.jpg" alt="logoImg" style="max-width: 90px;">
        <h3 style="font-weight: 400;margin-top: 20px;">注册</h3>
      </div>
      <div style="text-align: center;margin-left: 20px;margin-top: 50px;">
        <el-input placeholder="请输入正确的手机号" v-model="inputUsername" clearable maxlength="11"
                  show-word-limit>
          <template slot="prepend">手机号：</template>
        </el-input>
        <el-input placeholder="请输入昵称，中文或者英文1-8 位" v-model="inputName" clearable maxlength="8"
                  show-word-limit style="margin-top: 30px;">
          <template slot="prepend"><span style="padding-left: 7.2px;padding-right: 7.2px;">昵称：</span></template>
        </el-input>
        <el-input placeholder="数字 + 字母 + 特殊符号，至少 6 位" style="margin-top: 30px;" v-model="inputPassword" show-password
                  maxlength="30"
                  show-word-limit>
          <template slot="prepend"><span style="padding-left: 7.2px;padding-right: 7.2px;">密码：</span></template>
        </el-input>
        <!--  <el-input placeholder="请输入邮箱" v-model="inputEmail" clearable maxlength="30"
                    show-word-limit style="margin-top: 30px;">
            <template slot="prepend"><span style="padding-left: 7.2px;padding-right: 7.2px;">邮箱：</span></template>
          </el-input>-->
      </div>
      <div style="text-align: center;margin-left: 10px;margin-top: 50px;">
        <el-button type="primary" style="min-width: 80%;" @click="login()" :disabled="registerButton">注册</el-button>
      </div>
      <el-row type="flex" class="row-bg" justify="center" style="top: 10px;">
        <el-col :span="24">
          <h6 style="text-align: center;color: #757575;">天津科技大学交易系统 版权所有</h6>
          <h6 style="text-align: center;color: #757575;">Copyright © 2020</h6>
        </el-col>
      </el-row>
    </el-card>

    <!--      弹出提示框-->
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible"
      width="30%"
      center>
      <div style="text-align: center">
        <h4>{{ dialogValue }}</h4>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="clickButton" style="margin-top: 30px;">确 定</el-button>
        </span>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "register",
    data() {
      return {
        "inputUsername": "",
        "inputPassword": "",
        "inputEmail": "",
        "inputName": "",
        "dialogValue": "注册失败",
        "centerDialogVisible": false,
        "registerButton": true
      }
    },
    //数据更新完的生命周期钩子
    updated() {
      //更改登录按钮是否可点击的状态
      if (this.check()) {
        this.registerButton = false;
      } else {
        this.registerButton = true;
      }
    },
    methods: {
      login() {
        let jsonObj = {};
        jsonObj.usernameTel = this.inputUsername;
       // jsonObj.usernameEmail = this.inputEmail;
        jsonObj.username = this.inputName;
        jsonObj.password = this.inputPassword;
        let jsonMsg = JSON.stringify(jsonObj);
        let self = this;
        // $.post("http://yapi.wangkaiyi.com/mock/24/register.do", jsonMsg, function (data) {
        //   if (data != null && data.code != null && data.code !== 0) {
        //     self.dialogValue = "注册成功";
        //   } else if(data.code == 0){
        //       self.dialogValue = "注册失败，用户名重复";
        //     } else {
        //       self.dialogValue = "注册失败，错误码：" + data.code;
        //     }
        //   self.centerDialogVisible = true;
        // }, "json");
        $.ajax({
          type: "POST",
          url: "http://localhost:8083/user/register",
          data: jsonMsg,
          dataType: "json",
          contentType: "application/json",
          success: function (data) {
            if (data != null && data.code != null && data.code !== 0) {
              self.dialogValue = "注册成功";
            } else if (data.code === 0) {
              self.dialogValue = "注册失败，用户名重复";
            } else {
              self.dialogValue = "注册失败，错误码：" + data.code;
            }
            self.centerDialogVisible = true;
          }
        });
      },
      check() {
        //手机号
        let usernameRex = /^1(?:3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/;
        //6到30位数字、字母、特殊符号
        let passwordRex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,30}$/;
        //邮箱，不能带中文
        //let emailRex = /^[A-Za-z0-9]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        //昵称，可以中英文，1-8位
        let nameRex = /^[a-zA-Z\u4e00-\u9fa5]{1,8}$/;
        if (usernameRex.test(this.inputUsername) && passwordRex.test(this.inputPassword)
          /*&& emailRex.test(this.inputEmail)*/ && nameRex.test(this.inputName))
          return true;
        else
          return false;
      },
      clickButton() {
        if (this.dialogValue === "注册成功") {
          this.$router.push("/");
        } else {
          this.centerDialogVisible = false;
        }
      }
    }
  }
</script>

<style scoped>
  .registerDivClass {
    background: url("https://i.loli.net/2019/12/15/A2XS7tNrHCM9f4R.png");
    height: 910px;
  }
</style>
