<template>
<!--封装 iTable.vue 组件 (骨架)-->
  <div class="table">
    <!--region 表格-->
    <el-table
      id="iTable"
    v-loading.iTable="options.loading"
    :data="list()"
    :max-height="height"
    :stripe="options.stripe"
    ref="mutipleTable"
    @selection-change="handleSelectionChange">
      <!--region 选择框-->
      <el-table-column v-if="options.mutiSelect" type="selection" style="width: 55px;">
      </el-table-column>
      <!--endregion-->
      <!--region 数据列-->
      <template v-for="(column, index) in columns">
        <el-table-column :prop="column.prop"
                         :label="column.label"
                         :align="column.align"
                         :width="column.width">
          <template slot-scope="scope">
            <template v-if="!column.render">
              <template v-if="column.formatter">
                <span v-html="column.formatter(scope.row, column)"></span>
              </template>
              <template v-else>
                <span>{{scope.row[column.prop]}}</span>
              </template>
            </template>
            <template v-else>
              <expand-dom :column="column" :row="scope.row" :render="column.render" :index="index"></expand-dom>
            </template>
          </template>
        </el-table-column>
      </template>
      <!--endregion-->
      <!--region 按钮操作组-->
      <el-table-column ref="fixedColumn" label="操作" align="center" :width="operates.width" :fixed="operates.fixed"
                       v-if="operates.list.filter(_x=>_x.show === true).length > 0">
        <template slot-scope="scope">
          <div class="operate-group">
            <template v-for="(btn, key) in operates.list">
              <div class="item" v-if="btn.show">
                <el-button :type="btn.type" size="mini" :icon="btn.icon" :disabled="btn.disabled"
                           :plain="btn.plain" @click.native.prevent="btn.method(key,scope.row)">{{ btn.label }}
                </el-button>
              </div>
            </template>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <!--endregion-->

    <!--region 分页-->
    <el-pagination v-if="pagination" @size-change="handleSizeChange"
                   @current-change="handleIndexChange"
                   :page-size="tableCurrentPagination.pageSize"
                   :page-sizes="this.tableCurrentPagination.pageArray" :current-page="tableCurrentPagination.pageIndex"
                   layout="total,sizes, prev, pager, next,jumper"
                   :total="total">

    </el-pagination>
    <!--endregion-->

  </div>
</template>

<script>
  const _pageArray = [20, 50, 100] // 每页展示条数的控制集合
  export default {
    props: {
      list: {
        type: Array,
        default: [] // prop:表头绑定的地段，label：表头名称，align：每列数据展示形式（left, center, right），width:列宽
      }, // 数据列表
      columns: {
        type: Array,
        default: [] // 需要展示的列 === prop：列数据对应的属性，label：列名，align：对齐方式，width：列宽
      },
      operates: {
        type: Object,
        default: {} // width:按钮列宽，fixed：是否固定（left,right）,按钮集合 === label: 文本，type :类型（primary / success / warning / danger / info / text），show：是否显示，icon：按钮图标，plain：是否朴素按钮，disabled：是否禁用，method：回调方法
      },
      total: {
        type: Number,
        default: 0
      }, // 总数
      pagination: {
        type: Object,
        default: null // 分页参数 === pageSize:每页展示的条数，pageIndex:当前页，pageArray: 每页展示条数的控制集合，默认 _page_array
      },
      otherHeight: {
        type: Number,
        default: 160
      }, // 计算表格的高度
      options: {
        type: Object,
        default: {
          stripe: false, // 是否为斑马纹 table
          loading: false, // 是否添加表格loading加载动画
          highlightCurrentRow: false, // 是否支持当前行高亮显示
          mutiSelect: false // 是否支持列表项选中功能
        }
      } // table 表格的控制参数
    },
    components: {
      expandDom: {
        functional: true,
        props: {
          row: Object,
          render: Function,
          index: Number,
          column: {
            type: Object,
            default: null
          }
        },
        render: (h, ctx) => {
          const params = {
            row: ctx.props.row,
            index: ctx.props.index
          }
          if (ctx.props.column) params.column = ctx.props.column
          return ctx.props.render(h, params)
        }
      }
    },
    data () {
      return {
        pageIndex: 1,
        tableCurrentPagination: {},
        multipleSelection: [] // 多行选中
      }
    },
    created () {},
    mounted () {
      if (this.pagination && !this.pagination.pageSizes) {
        this.pagination.pageArray = _pageArray // 每页展示条数控制
      }
      this.tableCurrentPagination = this.pagination || {
        pageSize: this.total,
        pageIndex: 1
      } // 判断是否需要分页
    },
    computed: {
      // 计算table高度
      height () {
        return this.$utils.Common.getWidthHeight().height - this.otherHeight
      }
    },
    methods: {
      // 切换每页显示的数量
      handleSizeChange (size) {
        if (this.pagination) {
          this.tableCurrentPagination = {
            pageIndex: 1,
            pageSize: size
          }
          this.$emit('handleSizeChange', this.tableCurrentPagination)
        }
      },
      // 切换页码
      handleIndexChange (currnet) {
        if (this.pagination) {
          this.tableCurrentPagination.pageIndex = currnet
          this.$emit('handleIndexChange', this.tableCurrentPagination)
        }
      },
      // 多行选中
      handleSelectionChange (val) {
        this.multipleSelection = val
        this.$emit('handleSelectionChange', val)
      },
      // 显示 筛选弹窗
      showfilterDataDialog () {
        this.$emit('handleFilter')
      },
      // 显示 表格操作弹窗
      showActionTableDialog () {
        this.$emit('handelAction')
      }
    }
  }
</script>

<style lang="less" rel="stylesheet/less">

  .table {
    height: 100%;
    .el-pagination {
      float: right;
      margin: 20px;
    }
    .el-table__header-wrapper, .el-table__fixed-header-wrapper {
      thead {
        tr {
          th {
            color: #333333;
          }
        }
      }
    }
    .el-table-column--selection .cell {
      padding: 0;
      text-align: center;
    }
    .el-table__fixed-right {
      bottom: 0 !important;
      right: 6px !important;
      z-index: 1004;
    }
    .operate-group {
      display: flex;
      flex-wrap: wrap;
      .item {
        margin-top: 4px;
        margin-bottom: 4px;
        display: block;
        flex: 0 0 50%;
      }
    }
    .filter-data {
      top: e("calc((100% - 100px) / 3)");
      background-color: rgba(0, 0, 0, 0.7);
    }
    .table-action {
      top: e("calc((100% - 100px) / 2)");
      background-color: rgba(0, 0, 0, 0.7);
    }
    .fix-right {
      position: absolute;
      right: 0;
      height: 100px;
      color: #ffffff;
      width: 30px;
      display: block;
      z-index: 1005;
      writing-mode: vertical-rl;
      text-align: center;
      line-height: 28px;
      border-bottom-left-radius: 6px;
      border-top-left-radius: 6px;
      cursor: pointer;
    }
  }
</style>

