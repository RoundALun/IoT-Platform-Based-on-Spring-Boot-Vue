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
        <el-form-item label="角色名" prop="roleName">
          <el-input
            v-model="queryParams.roleName"
            placeholder="请输入角色名"
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
        <!-- <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            >修改
          </el-button>
        </el-col> -->
        <!-- <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            >删除
          </el-button>
        </el-col> -->
      </el-row>
  
      <el-table
        v-loading="loading"
        :data="roleList"
        @selection-change="handleSelectionChange"
      >
        <!-- <el-table-column type="selection" width="55" align="center" /> -->
        <el-table-column label="角色编号" align="center" prop="roleId" width="200"/>
        <el-table-column label="角色名" align="center" prop="roleName" width="260"/>
        <el-table-column label="角色描述" prop="roleDesc" align="center"> </el-table-column>
        
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
  
      <!-- 添加或修改角色对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="角色名" prop="roleName">
            <el-input v-model="form.roleName" placeholder="请输入角色名" />
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDesc">
            <el-input v-model="form.roleDesc" placeholder="请输入角色描述" />
          </el-form-item>
          <el-form-item
            label="权限设置"
            prop="menuIdList"
            :label-width="formLabelWidth"
          >
            <el-tree :data="menuList" :props="menuProps" show-checkbox
             default-expand-all
             node-key="menuId"
             ref="menuRef"
             style="width:85%"></el-tree>
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
  import { listRole, getRole, delRole, addRole, updateRole } from "@/api/roleManage";
  import menuApi from "@/api/menuManage";
  export default {
    name: "RoleInfoView",
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
        // 角色表格数据
        roleList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          roleName: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          roleName: [
            { required: true, message: "请输入角色名", trigger: "blur" },
          ],
          roleDesc: [{ required: true, message: "请输入密码", trigger: "blur" }],
        },
        // 菜单树
        menuProps: {
          children: "children",
          label: "title",
        },
        // 菜单列表
        menuList: [],
        formLabelWidth: "130px",
      };
    },
    created() {
      this.getList();
      this.getAllMenu();
    },
    methods: {
      /** 查询角色列表 */
      getList() {
        this.loading = true;
        listRole(this.queryParams).then((response) => {
          this.roleList = response.rows;
          console.log(this.roleList);
          this.total = response.total;
          this.loading = false;
        });
      },
      getAllMenu() {
        menuApi.getAllMenu().then((response) => {
          this.menuList = response.data;
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
          roleId: null,
          roleName: null,
          roleDesc: null,
          menuIdList: [],
        };
        this.menuRef = {};
        this.resetForm("form");
        this.$nextTick(() => {
          this.$refs.menuRef.setCheckedKeys([]);
        });
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
        this.ids = selection.map((item) => item.roleId);
        this.single = selection.length !== 1;
        this.multiple = !selection.length;
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加角色";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const roleId = row.roleId || this.ids;
        getRole(roleId).then((response) => {
          this.form = response.data;
          console.log(response.data);
          this.$nextTick(() => {
            this.$refs.menuRef.setCheckedKeys(response.data.menuIdList);
          });
          this.open = true;
          this.title = "修改角色";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate((valid) => {
          if (valid) {
            let checkedKeys = this.$refs.menuRef.getCheckedKeys(); 
            let halfCheckedKeys = this.$refs.menuRef.getHalfCheckedKeys();
            this.form.menuIdList = checkedKeys.concat(halfCheckedKeys);
            console.log(this.form);
            if (this.form.roleId != null) {
              updateRole(this.form).then(() => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addRole(this.form).then(() => {
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
        const roleIds = row.roleId || this.ids;
        this.$modal
          .confirm('是否确认删除角色编号为"' + roleIds + '"的数据项？')
          .then(function () {
            return delRole(roleIds);
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
          "system/role/export",
          {
            ...this.queryParams,
          },
          `role_${new Date().getTime()}.xlsx`
        );
      },
      
    },
  };
  </script>
  