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
      <el-form-item label="属性名称" prop="propertyName">
        <el-input
          v-model="queryParams.propertyName"
          placeholder="请输入属性名称"
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
      :data="propertyList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="属性编号" align="center" prop="propertyId" />
      <el-table-column
        label="属性类型"
        align="center"
        prop="propType.propTypeName"
      />
      <el-table-column label="属性名称" align="center" prop="propertyName" />
      <el-table-column label="属性标识" align="center" prop="propertyKey" />
      <el-table-column label="单位" align="center" prop="unit" />
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

    <!-- 添加或修改属性对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品ID" prop="protocolId" v-show="false">
          <el-input v-model="form.protocolId" placeholder="请输入产品ID" />
        </el-form-item>
        <el-form-item label="属性类型" prop="propTypeId">
          <el-select v-model="form.propTypeId" placeholder="请选择属性类型">
            <el-option
              v-for="item in propTypeOptions"
              :key="item.propTypeId"
              :label="item.propTypeName"
              :value="item.propTypeId"
              ><span style="float: left">{{ item.propTypeName }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{
                item.remark
              }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="属性名称" prop="propertyName">
          <el-input v-model="form.propertyName" placeholder="请输入属性名称" />
        </el-form-item>
        <el-form-item label="属性标识" prop="propertyKey">
          <el-input v-model="form.propertyKey" placeholder="请输入属性标识" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" />
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
  listProperty,
  getProperty,
  delProperty,
  addProperty,
  updateProperty,
} from "@/api/property";

export default {
  name: "PropertyView",
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
      // 属性表格数据
      propertyList: [],
      // 属性类型
      propTypeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        propertyName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        propTypeId: [
          { required: true, message: "请选择属性类型", trigger: "blur" },
        ],
        propertyName: [
          { required: true, message: "请输入属性名称", trigger: "blur" },
        ],
        propertyKey: [
          { required: true, message: "请输入属性标识", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
    getProperty().then((response) => {
      this.propTypeOptions = response.propTypes;
    });
  },
  methods: {
    /** 查询属性列表 */
    getList() {
      this.loading = true;
      this.queryParams.protocolId =
        this.$route.params && this.$route.params.protocolId;
      listProperty(this.queryParams).then((response) => {
        this.propertyList = response.rows;
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
        propertyId: null,
        protocolId: null,
        propTypeId: null,
        propertyName: null,
        propertyKey: null,
        unit: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.queryParams.protocolId =
        this.$route.params && this.$route.params.protocolId;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.propertyId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加属性";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const propertyId = row.propertyId || this.ids;
      getProperty(propertyId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改属性";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.protocolId =
        this.$route.params && this.$route.params.protocolId;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.propertyId != null) {
            updateProperty(this.form).then(() => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProperty(this.form).then(() => {
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
      const propertyIds = row.propertyId || this.ids;
      this.$modal
        .confirm('是否确认删除属性编号为"' + propertyIds + '"的数据项？')
        .then(function () {
          return delProperty(propertyIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
  },
};
</script>
