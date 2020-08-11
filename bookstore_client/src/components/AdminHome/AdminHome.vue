<template>
  <div class="content">
    <div class="plain_Box">
      <div class="plain_show" style="float: left">
        <div class="plain_title">商品总览</div>
        <div class="plain_content">
          <div class="el_row">
            <div class="el_col num">100</div>
            <div class="el_col num">100</div>
            <div class="el_col num">100</div>
            <div class="el_col num">100</div>
          </div>
          <div class="el_row">
            <div class="el_col item_title">已上架</div>
            <div class="el_col item_title">已下架</div>
            <div class="el_col item_title">库存紧张</div>
            <div class="el_col item_title">全部商品</div>
          </div>
        </div>
      </div>
      <div class="plain_show" style="float: right">
        <div class="plain_title">用户总览</div>
        <div class="plain_content">
          <div class="el_row">
            <div class="el_col num">100</div>
            <div class="el_col num">100</div>
            <div class="el_col num">100</div>
            <div class="el_col num">100</div>
          </div>
          <div class="el_row">
            <div class="el_col item_title">今日新增</div>
            <div class="el_col item_title">昨日新增</div>
            <div class="el_col item_title">本月新增</div>
            <div class="el_col item_title">用户总数</div>
          </div>
        </div>
      </div>
    </div>
    <div class="echart_Box">
      <div class="plain_title">
        <div style="float: left">订单统计</div>
        <div class="block" style="float: right;font-size: 10px;font-weight: normal">
          <el-date-picker
            :style="{width: '400px'}"
            v-model="value"
            type="daterange"
            align="right"
            unlink-panels
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :picker-options="pickerOptions">
          </el-date-picker>
        </div>
      </div>
      <div id="myChart" class="echart_content"></div>
    </div>
  </div>
</template>

<script>
  import {reqGetOrderStatistic} from "../../api/order";

  export default {
        name: "AdminHome",
        data(){
            return {
                //ecahrt参数
                option:{
                    title: {
                        text: ''
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['订单数量', '订单总额']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        // data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                        data: []
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: '订单数量',
                            type: 'line',
                            stack: '总量',
                            // data: [120, 132, 101, 134, 90, 230, 210]
                            data: [0, 0, 0, 0, 0, 0, 0]
                        },
                        {
                            name: '订单总额',
                            type: 'line',
                            stack: '总量',
                            // data: [220, 182, 191, 234, 290, 330, 310]
                            data: [0, 0, 0, 0, 0, 0, 0]
                        }
                    ]
                },

                //时间选择器参数
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
                value: ''
            }
        },
        mounted(){
          this.drawLine();
        },
        methods:{
            drawLine(){
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('myChart'))
                // 绘制图表
                myChart.setOption(this.option);
            },
            //得到指定日期内的订单统计数据
            getOrderStatistic(beginDate,endDate){
                reqGetOrderStatistic(beginDate,endDate).then(response=>{
                    if(response.code==200){
                        this.option.series[0].data= [];
                        this.option.series[1].data= [];
                        this.option.xAxis.data = [];
                        console.log(" this.option.series[0].data"+this.option.series[0].data);
                        // console.log(response.orderStatistic);
                        let date = new Date(beginDate);
                        // console.log("=======date:====="+date+"==========");
                        for(let i=0;i<this.dateDiff(this.value[0],this.value[1]);i++){
                            this.option.xAxis.data.push((date.getMonth()+1).toString()+"/"+date.getDate());
                            let exist = false;
                            for(let order of response.orderStatistic){
                                let orderDate = new Date(order.orderTime);
                                if(orderDate.getDate() == date.getDate()){
                                    exist = true;
                                    this.option.series[0].data.push(order.count);
                                    this.option.series[1].data.push(order.amount);
                                    // console.log("=======orderDate======:"+orderDate+"========");
                                }
                            }
                            if(!exist){
                                // console.log("====当前日期不存在==="+date)
                                this.option.series[0].data.push(0);
                                this.option.series[1].data.push(0);
                            }
                            date.setDate(date.getDate() + 1);
                        }
                        this.drawLine();
                    }else {
                        this.$message({
                            message: "获取订单统计信息出错了！",
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    this.$message({
                        message: "获取订单统计信息出错了！请检查网络",
                        type: "warning"
                    })
                })
            },
            dateDiff(beginDate,endDate){
                if (beginDate != null && endDate != null) {
                    let minus = endDate.getTime()-beginDate.getTime()
                    let days = parseInt(minus / (1000*60*60*24))
                    return days+1
                } else {
                    return null
                }
            }
        },
        watch: {
            // 如果 `question` 发生改变，这个函数就会运行
            value: function () {
                // console.log("==this.value[0]==="+this.value[0]+"==");
                // console.log("==this.value[1]==="+this.value[1]+"==");
                this.getOrderStatistic(new Date(this.value[0]).format("yyyy-MM-dd"),new Date(this.value[1]).format("yyyy-MM-dd"));
                this.dateDiff(this.value[0],this.value[1]);
            }
        },
    }
</script>

<style scoped>
  .content{
    margin: 0px auto;
    width: 980px;
    min-width: 980px;
  }
  .plain_Box{
    margin-top: 20px;
    width: 980px;
    height: 200px;
  }
  .plain_show{
    width: 48%;
    height: 200px;
    border: 1px solid #e1e5eb;
  }
  .plain_title{
    background-color: #F2F6FC;
    width: 100%;
    height: 50px;
    line-height: 50px;
    color: #606266;
    font-size: 20px;
    font-weight: 700;
    padding-left: 15px;
  }
  .plain_content{
    width: 90%;
    height: 100px;
    margin: 40px auto;
  }
  .num{
    color: #f56c6c;
    font-size: 20px;
  }
  .item_title{
    font-size: 16px;
    color: #606266;
  }
  .el_row{
    width: 100%;
    height: 35px;
    line-height: 35px;
    /*background-color: #8acfd1;*/
  }
  .el_col{
    width: 25%;
    float: left;
    text-align: center;
  }

  .echart_Box{
    margin-top: 20px;
    width: 980px;
    height: 600px;
    border: 1px solid #e1e5eb;
  }

  .echart_content{
    width: 900px;
    height: 500px;
    margin: 30px auto;
  }


</style>
