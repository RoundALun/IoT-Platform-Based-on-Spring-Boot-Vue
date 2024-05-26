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
        <el-form-item label="产品名称" prop="protocolName">
          <el-input
            v-model="queryParams.protocolName"
            placeholder="请输入产品名称"
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
        :data="protocolList"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="产品编号" align="center" prop="protocolId" />
        <el-table-column label="产品名称" align="center" prop="protocolName" />
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
              >属性</el-button
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
  
      <!-- 添加或修改产品对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="产品名称" prop="protocolName">
            <el-input v-model="form.protocolName" placeholder="请输入产品名称" />
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
    listProtocol,
    getProtocol,
    delProtocol,
    addProtocol,
    updateProtocol,
  } from "@/api/protocol";
  
  export default {
    name: "ProtocolView",
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
        // 产品表格数据
        protocolList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          protocolName: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          protocolName: [
            { required: true, message: "请输入产品名称", trigger: "blur" },
          ],
        },
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询产品列表 */
      getList() {
        this.loading = true;
        listProtocol(this.queryParams).then((response) => {
          this.protocolList = response.rows;
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
          protocolId: null,
          protocolName: null,
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
        this.ids = selection.map((item) => item.protocolId);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加产品";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const protocolId = row.protocolId || this.ids;
        getProtocol(protocolId).then((response) => {
          this.form = response.data;
          this.open = true;
          this.title = "修改产品";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate((valid) => {
          if (valid) {
            if (this.form.protocolId != null) {
              updateProtocol(this.form).then(() => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addProtocol(this.form).then(() => {
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
        const protocolIds = row.protocolId || this.ids;
        this.$modal
          .confirm('是否确认删除产品编号为"' + protocolIds + '"的数据项？')
          .then(function () {
            return delProtocol(protocolIds);
          })
          .then(() => {
            this.getList();
            this.$modal.msgSuccess("删除成功");
          })
          .catch(() => {});
      },
      /** 跳转属性页面 */
      handleCheck(row) {
        const protocolId = row.protocolId;
        this.$router.push("/product/property/" + protocolId);
      },
    },
  };
  </script>
  