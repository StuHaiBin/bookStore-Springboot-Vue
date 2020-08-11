<template>
  <div>
    <Nav></Nav>
    <div class="content">
      <div class="login_content">
        <h2>登陆</h2>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm">
          <el-form-item prop="account">
            <el-input type="text" v-model="ruleForm.account" autocomplete="off" placeholder="请输入手机号或者邮箱"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="ruleForm.password" autocomplete="off" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login('ruleForm')" style="width: 100%">登陆</el-button>
          </el-form-item>
          <el-form-item>
            <span style="float:left;">没有账号？<a href="#/register">去注册</a></span><span style="float: right">忘记密码</span>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>

    import {reqLogin} from "../../api/user";
    import Nav from "../../components/Common/Nav";
    import Footer from "../../components/Common/Footer";
    import axios from 'axios'
    export default {
        name: "Login",
        components: {Nav,Footer},
        data() {
            var checkAccount = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('账号不能为空'));
                }
                setTimeout(() => {
                    if(value.length>13){
                        callback(new Error('账号不能大于13位'));
                    }else {
                        callback();
                    }
                }, 1000);
            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    account: '',
                    password: '',
                },
                rules: {
                    account: [
                        { validator: checkAccount, trigger: 'blur' }
                    ],
                    password: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                }
            };
        },
        methods: {
            login(formName) {
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // let param = new URLSearchParams()
                        // param.append('account', this.ruleForm.account)
                        // param.append('password', this.ruleForm.password)
                        // axios({
                        //     method: 'POST',
                        //     url: 'api/login',
                        //     headers: {
                        //         'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                        //     },
                        //     data:{
                        //         'account': this.ruleForm.account,
                        //         'password': this.ruleForm.password
                        //     }
                        // }).then((response) => {
                        //     console.log("登陆后返回的response："+response);
                        //     console.log(response.data);
                        //     let jwt = response.headers['authorization']
                        //     console.log("====jwt:=== "+jwt);
                        //     if(response.data.code == 200){
                        //         console.log("登录成功");
                        //         console.log(response);
                        //         // const jwt = response.headers['authorization']
                        //         // console.log("jwt: "+jwt);
                        //         // //把数据共享出去
                        //         // _this.$store.commit("SET_TOKEN", jwt)
                        //         // _this.$store.commit("SET_USERINFO", response.data.userInfo)
                        //         // console.log("返回来的SET_USERINFO:"+response.data.userInfo)
                        //         // this.$router.push({path:'/devHome/appList'});
                        //     }else {
                        //         this.$message({
                        //             type: 'waring',
                        //             message: "登录失败"
                        //         })
                        //     }
                        // }).catch(err=>{
                        //     this.$message({
                        //         type: 'waring',
                        //         message: "登录失败"
                        //     })
                        // })
                        console.log("=====开始登陆=======")
                        reqLogin({
                            account: this.ruleForm.account,
                            password: this.ruleForm.password
                        }).then((response) => {
                            console.log("登陆后的response："+response.data);
                            let user  = response.data.user;
                            console.log("=====user:==="+user+"=========");
                            const jwt = response.headers['authorization']
                            console.log("====jwt:=== "+jwt);
                            if(response.data.code == 200){
                                console.log("登录成功");
                                console.log(response);
                                const jwt = response.headers['authorization']
                                console.log("====jwt:=== "+jwt);
                                // //把数据共享出去
                                _this.$store.commit("SET_TOKEN", jwt)
                                console.log("===localStorage.getItem(\"token\")==="+localStorage.getItem("token")+"==");
                                _this.$store.commit("SET_USERINFO", response.data.user);
                                console.log("=====response.data.user====="+response.data.user.manage+"========")

                                if(response.data.user.manage){
                                    this.$message({
                                        type: 'success',
                                        message: "登录成功！",
                                        duration: 1000
                                    })
                                    setTimeout(() => {
                                        this.$router.push({path:'/admin/home'});
                                    }, 1000);
                                }else {
                                    this.$message({
                                        type: 'success',
                                        message: "登录成功！",
                                        duration: 1000
                                    })
                                    setTimeout(() => {
                                        this.$router.push({path:'/user/userCenter'});
                                    }, 1000);
                                }
                                // console.log("返回来的SET_USERINFO:"+response.data.userInfo)
                                // this.$router.push({path:'/devHome/appList'});
                            }else {
                                this.$message({
                                    type: 'waring',
                                    message: "登录失败"
                                })
                            }
                        }).catch(() => {
                            // this.$message.error("登录失败")
                        })
                    } else {
                        //数据校验失败，不可以进行提交
                        this.$message.error("账号密码不符合要求，登陆失败");
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>
  .content{
    height: 100vh;
    width: 100%;
    min-width: 1240px;
    overflow: hidden;
    background-color: #B3C0D1;
    background-image: url("../../assets/image/15124.jpg");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
  }
  .login_content{
    margin: 130px auto;
    padding: 15px;
    width: 350px;
    height: 300px;
    background-color: white;
    border-radius: 10px;
  }
  h2{
    text-align: center;
    margin-bottom: 30px;
  }
</style>
