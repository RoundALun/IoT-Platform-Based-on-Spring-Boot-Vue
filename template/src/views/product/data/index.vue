<template>
  <div class="app-container">
    <div class="data-box">
      <el-empty v-if="propertyList.length == 0" description="暂无数据" style="margin: 0 auto"></el-empty>
      <div v-for="item in propertyList" :key="item.propertyKey" class="property-box">
        <div class="show-data" @click="
          handleShowChart(item.propertyKey, item.propertyName, item.unit)
          ">
          查看数据
        </div>
        <div class="property-name">{{ item.propertyName }}</div>
        <div class="property-value">
          {{ item.propertyValue + " " + item.unit }}
          <el-switch v-if="item.propertyKey == 'switcher'" v-model="item.propertyValue"
            @change="handleSwitchChange(item.deviceSn, item.propertyKey, item.propertyValue, item)">
          </el-switch>
        </div>
        <div class="collection-time">
          {{ item.propertyValue == "--" ? "--" : item.collectionTime }}
        </div>
      </div>
    </div>
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-date-picker v-model="value" type="datetimerange" :picker-options="pickerOptions" range-separator="至"
        start-placeholder="开始日期" end-placeholder="结束日期" align="left" @change="handleChange">
      </el-date-picker>
      <div ref="propChart" style="height: 350px" v-loading="loading" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPropData, getHistoryData, putChange } from "@/api/data";
export default {
  name: "DataView",
  data() {
    return {
      // 遮罩层
      loading: false,
      // 属性数据
      propertyList: [],
      // 弹出层标题
      title: "查看数据",
      // 是否显示弹出层
      open: false,
      // 曲线图表
      propChart: null,
      // 查询参数
      queryParams: {
        deviceSn: null,
        propertyKey: null,
        beginTime: null,
        endTime: null,
      },
      // 时间范围快捷项
      pickerOptions: {
        shortcuts: [
          {
            text: "最近1小时",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近24小时",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近7天",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      // 时间范围初始值
      value: [new Date().getTime() - 3600 * 1000, new Date().getTime()],
      option: {
        propertyName: "",
        unit: "",
      },
    };
  },
  created() {
    this.queryParams.beginTime = this.value[0];
    this.queryParams.endTime = this.value[1];
    this.getPropDataList();
  },
  methods: {
    getPropDataList() {
      var deviceSn = this.$route.params && this.$route.params.deviceSn;
      this.queryParams.deviceSn = deviceSn;
      listPropData(deviceSn).then((response) => {
        this.propertyList = response.data;
        console.log(this.propertyList);
      });
    },
    handleSwitchChange(deviceSn, propertyKey, propertyValue, item) {
      this.loading = true;
      // console.log(deviceSn,propertyKey, propertyValue);
      putChange(deviceSn, propertyKey, propertyValue).then((response) => {
        console.log(response);
        if (response.code == 200) {
          this.$message({
            message: "操作成功",
            type: "success",
          });
          this.loading = false;
        } else {
          item.propertyValue = !propertyValue;
        }
      });
    },
    handleShowChart(propertyKey, propertyName, unit) {
      this.queryParams.propertyKey = propertyKey;
      this.option.propertyName = propertyName;
      this.option.unit = unit;
      this.setChartOption();
      this.open = true;
    },
    showChart(data) {
      if (this.propChart == null) {
        console.log(this.$refs.propChart);
        this.propChart = this.echarts.init(this.$refs.propChart, "macarons");
      }
      var option = {
        title: {
          text: "",
        },
        grid: {
          x: 40,
          y: 45,
          x2: 40,
          y2: 30,
          borderWidth: 1,
        },
        toolbox: {
          feature: {
            saveAsImage: {
              pixelRatio: 2,
            },
          },
        },
        tooltip: {
          trigger: "axis",
        },
        xAxis: {
          type: "time",
          name: "时间",
        },
        yAxis: {
          axisTick: {
            show: true,
          },
          axisLine: {
            show: true,
          },
          splitLine: {
            show: true,
          },
          type: "value",
          name: this.option.propertyName + " " + this.option.unit,
        },
        series: [
          {
            name: "",
            data: data,
            type: "line",
            smooth: true,
            showSymbol: false,
          },
        ],
      };
      this.propChart.setOption(option);
    },
    handleChange(e) {
      if (e != null) {
        this.queryParams.beginTime = e[0];
        this.queryParams.endTime = e[1];
        this.setChartOption();
      }
    },
    setChartOption() {
      getHistoryData(this.queryParams).then((response) => {
        var historyList = response.data;
        var data = [];
        historyList.forEach((item) => {
          data.push([
            item.collectionTime,
            JSON.parse(item.value)[this.queryParams.propertyKey],
          ]);
        });
        this.$nextTick(() => {
          this.showChart(data);
        });
      });
    },
  },
};
</script>

<style scoped>
.app-container {
  padding: 10px;
}

.data-box {
  display: flex;
  flex-flow: wrap;
}

.property-box {
  width: 300px;
  height: 100px;
  box-shadow: 0 0 10px 5px rgba(0, 0, 0, 0.1);
  margin: 10px;
  padding: 10px 20px 10px 20px;
}

.property-name {
  font-size: 16px;
  line-height: 20px;
}

.property-value {
  font-size: 20px;
  font-weight: 600px;
  line-height: 40px;
}

.collection-time {
  font-size: 14px;
  color: #666;
  line-height: 20px;
}

.show-data {
  float: right;
  font-size: 12px;
  color: #409eff;
  cursor: pointer;
}
</style>