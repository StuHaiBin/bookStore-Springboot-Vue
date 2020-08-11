<!--测试axios是否有用-->
<template>
    <div>
      <h1>{{msg}}</h1>
      <button @click="getUserInfo">点击获取User的信息</button>
      <button @click="reRouter">点击跳转路由</button>
      <button @click="reRouter2">点击跳转路由2</button>
      <button @click="reRouter3">点击跳转路由3</button>
      <table>
        <tr>
          <td>作者</td>
          <td>生日</td>
        </tr>
      </table>
      <table>
        <tr v-for="use in users">
          <td>{{ use.id }}</td>
          <td>{{ use.name }}</td>
        </tr>
      </table>
    </div>
</template>

<script>

    import axios from "axios"
    import {reqLogin} from "../api/index"
    export default {
        name: "AxiosTest",
        data () {
            return {
                msg: '这是axios测试页面',
                users:[]
            }
        },
        methods: {
            getUserInfo(){
                var that = this;
                axios.get("http://localhost:8080/ssm/getDog").then(function (response) {
                    console.log("请求后端数据成功！");
                    console.log("response.data.dogList:"+response.data.dogList);
                    console.log("response.data.name:"+response.data.name);
                    console.log("response.data.pwd:"+response.data.pwd);
                    // that.users = response.data;
                }).catch(function (err) {
                    console.log(err);
                })


                // this.$http({
                //     method: 'get',
                //     url: 'http://localhost:8082/getBook?msg=hello',
                //     headers: {
                //         'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                //     },
                //     data: {
                //         msg: "hello"
                //     }
                // }).then(res=>{                    //请求成功后执行函数
                //         that.users = res.data;
                //     }).catch(err=>{                   //请求错误后执行函
                //         console.log("请求错误")
                //     })
                //
                // reqLogin({
                //     account: "huangLong",
                //     password: "123456"
                // }).then(function (response) {
                //     // console.log(response);
                //     // console.log("user:"+that.users);
                //     that.users = response.books;
                //     // console.log("user:"+that.users[1].id);
                //     // console.log("token:"+response.token);
                // }).catch(err=>{
                //     console.log("请求出错了！")
                // })
            },
            reRouter(){
                this.$router.push('/table/info');
            },
            reRouter2(){
                this.$router.push({path:"/table/info"})
            },
            reRouter3(){
                this.$router.push({name:'home'})
            }
        }
    }
</script>

<style scoped>

</style>
