<template>
    <div class="app-container">
      <el-form
        :model="queryParams"
        ref="queryForm"
        size="small"
        :inline="true"
        v-show="showSearch"
        label-width="68px"
      >
        <el-form-item label="接入SN" prop="deviceSn">
          <el-input
            v-model="queryParams.deviceSn"
            placeholder="请输入接入SN"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            icon="el-icon-search"
            size="mini"
            @click="handleQuery"
            >搜索</el-button
          >
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            >新增</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            >修改
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            >删除
          </el-button>
        </el-col>
      </el-row>
  
      <el-table
        v-loading="loading"
        :data="deviceList"
        @selection-change="handleSelectionChange"
        class="app-table"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="接入编号" align="center" prop="deviceId" />
        <el-table-column label="接入SN" align="center" prop="deviceSn" />
        <el-table-column label="接入密码" align="center" prop="devicePwd" />
        <el-table-column
          label="关联产品"
          align="center"
          prop="protocol.protocolName"
        />
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              >修改</el-button
            >
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              >删除</el-button
            >
            <el-button
              size="mini"
              type="text"
              icon="el-icon-document"
              @click="handleCheck(scope.row)"
              >数据</el-button
            >
          </template>
        </el-table-column>
      </el-table>
  
      <PaginationTool
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
  
      <!-- 添加或修改接入对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="接入SN" prop="deviceSn">
            <el-input v-model="form.deviceSn" placeholder="请输入接入SN" />
          </el-form-item>
          <el-form-item label="接入密码" prop="devicePwd">
            <el-input v-model="form.devicePwd" placeholder="请输入接入密码" />
          </el-form-item>
          <el-form-item label="关联产品" prop="protocolId">
            <el-select v-model="form.protocolId" placeholder="请选择产品">
              <el-option
                v-for="item in protocolOptions"
                :key="item.protocolId"
                :label="item.protocolName"
                :value="item.protocolId"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import {
    listDevice,
    getDevice,
    delDevice,
    addDevice,
    updateDevice,
  } from "@/api/device";
  
  export default {
    name: "DeviceView",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 接入表格数据
        deviceList: [],
        // 产品
        protocolOptions: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          deviceSn: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          deviceSn: [
            { required: true, message: "请输入接入SN", trigger: "blur" },
          ],
          devicePwd: [
            { required: true, message: "请输入接入密码", trigger: "blur" },
          ],
          protocolId: [
            { required: true, message: "请输入选择产品", trigger: "blur" },
          ],
        },
      };
    },
    created() {
      this.getList();
      getDevice().then((response) => {
        this.protocolOptions = response.protocols;
      });
    },
    methods: {
      /** 查询接入列表 */
      getList() {
        this.loading = true;
        listDevice(this.queryParams).then((response) => {
          this.deviceList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          deviceSn: null,
          devicePwd: null,
          userId: null,
          protocolId: null,
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map((item) => item.deviceId);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加接入";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const deviceId = row.deviceId || this.ids;
        getDevice(deviceId).then((response) => {
          this.form = response.data;
          this.open = true;
          this.title = "修改接入";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate((valid) => {
          if (valid) {
            if (this.form.deviceId != null) {
              updateDevice(this.form).then(() => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addDevice(this.form).then(() => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const deviceSns = row.deviceId || this.ids;
        this.$modal
          .confirm('是否确认删除接入编号为"' + deviceSns + '"的数据项？')
          .then(function () {
            return delDevice(deviceSns);
          })
          .then(() => {
            this.getList();
            this.$modal.msgSuccess("删除成功");
          })
          .catch(() => {});
      },
      /** 跳转数据页面 */
      handleCheck(row) {
        console.log(row);
        const deviceSn = row.deviceSn;
        this.$router.push("/product/data/" + deviceSn);
      },
    },
  };
  </script>
  