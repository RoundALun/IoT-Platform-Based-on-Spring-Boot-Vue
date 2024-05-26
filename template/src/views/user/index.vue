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
        <el-form-item label="用户名" prop="userName">
          <el-input
            v-model="queryParams.userName"
            placeholder="请输入用户名"
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
        :data="userList"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="用户编号" align="center" prop="userId" />
        <el-table-column label="用户名" align="center" prop="userName" />
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
  
      <!-- 添加或修改用户对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="用户名" prop="userName">
            <el-input v-model="form.userName" placeholder="请输入用户名" />
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" placeholder="请输入密码" />
          </el-form-item>
          <el-form-item label="用户角色" >
            <el-checkbox-group 
              style="width: 85%"
              v-model="form.roleIdList"
              :max="2">
              <el-checkbox v-for="role in roleList" :label="role.roleId" :key="role.roleId">{{role.roleDesc}}</el-checkbox>
            </el-checkbox-group>
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
  import { listUser, getUser, delUser, addUser, updateUser } from "@/api/user";
  import { getAllRole} from "@/api/roleManage";
  export default {
    name: "UserInfoView",
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
        // 用户表格数据
        userList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          userName: null,
        },
        // 表单参数
        form: {roleIdList: []},
        // 表单校验
        rules: {
          userName: [
            { required: true, message: "请输入用户名", trigger: "blur" },
          ],
          password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        },
        roleList: [],
        formLabelWidth: "130px",
      };
    },
    created() {
      this.getList();
      this.getAllRole();
    },
    methods: {
      /** 查询用户列表 */
      getList() {
        this.loading = true;
        listUser(this.queryParams).then((response) => {
          this.userList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      //获取所有角色列表
      getAllRole() {
        getAllRole().then((response) => {
          this.roleList = response.data;
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
          userId: null,
          userName: null,
          password: null,
          parentId: null,
          roleIdList: [],
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
        this.ids = selection.map((item) => item.userId);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      /** 新增按钮操作 */
      handleAdd() {
        console.log(this.form);
        this.reset();
        console.log(this.form);
        this.open = true;
        this.title = "添加用户";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        console.log(this.form);
        this.reset();
        const userId = row.userId || this.ids;
        getUser(userId).then((response) => {
          this.form = response.data;
          console.log(this.form);
          this.open = true;
          this.title = "修改用户";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate((valid) => {
          if (valid) {
            if (this.form.userId != null) {
              updateUser(this.form).then(() => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addUser(this.form).then(() => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
        console.log(this.form);
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const userIds = row.userId || this.ids;
        this.$modal
          .confirm('是否确认删除用户编号为"' + userIds + '"的数据项？')
          .then(function () {
            return delUser(userIds);
          })
          .then(() => {
            this.getList();
            this.$modal.msgSuccess("删除成功");
          })
          .catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download(
          "system/user/export",
          {
            ...this.queryParams,
          },
          `user_${new Date().getTime()}.xlsx`
        );
      },
    },
  };
  </script>
  