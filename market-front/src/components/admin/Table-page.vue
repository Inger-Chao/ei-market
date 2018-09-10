<template>
  <div class="table-page">
    <!--region table 表格-->
    <i-table
      :list="list"
      :total="total"
      :otherHeight="otherHeight"
      :options="options"
      :pagination="pagination"
      :columns="columns"
      :operates="operates"
      @handleSizeChange="handleSizeChange"
      @handleIndexChange="handleIndexChange"
      @handleSelectionChange="handleSelectionChange"
      @handleFilter="handleFilter"
      @handelAction="handelAction">
    </i-table>
    <!--endregion-->
  </div>
</template>

<script>
  import iTable from './iTable.vue'
  import Api from '../../server/api.js'
  import Axios from 'axios'
    export default {
      components: {iTable},
      data() {
        return {
          total: 0,
          list: [],
          otherHeight: 208,
          columns: [
            {
              prop: 'id',
              label: '编号',
              align: 'center',
              width: 60
            },
            {
              prop: 'title',
              label: '标题',
              align: 'center',
              width: 400,
              formatter: (row, column, cellValue) => {
                return `<span style="white-space: nowrap;color: indianred;">${row.title}</span>`
              }
            },
            {
              prop: 'state',
              label: '状态',
              align: 'center',
              width: '160',
              render: (h, params) => {
                return h('el-tag', {
                  props: {type: params.row.state === 0 ? 'success' : params.row.state === 1 ? 'info' : 'danger'} // 组件的props
                }, params.row.state === 0 ? '上架' : params.row.state === 1 ? '下架' : '审核中')
              }
            },
            {
              prop: 'seller',
              label: '卖家',
              align: 'center',
              width: 120
            },
            {
              prop: 'phone',
              label: '联系方式',
              align: 'center',
              width: 160
            },
            {
              prop: 'price',
              label: '价格',
              align: 'center',
              width: 240
            },
            {
              prop: 'createDate',
              label: '发布时间',
              align: 'center',
              width: 180,
              formatter: (row, column, cellValue) => {
                return this.$utils.Common.dateFormat(row.createDate, 'YYYY年MM月DD日 hh:mm')
              }
            }
          ], // 需要展示的列
          operates: {
            width: 200,
            fixed: 'right',
            list: [
              {
                label: '编辑',
                type: 'warning',
                show: true,
                icon: 'el-icon-edit',
                plain: true,
                disabled: false,
                method: (index, row) => {
                  this.handleEdit(index, row)
                }
              },
              {
                label: '删除',
                type: 'danger',
                icon: 'el-icon-delete',
                show: true,
                plain: false,
                disabled: false,
                method: (index, row) => {
                  this.handleDel(index, row)
                }
              }
            ]
          }, // 操作按钮组
          pagination: {
            pageIndex: 1,
            pageSize: 20
          }, // 分页参数
          options: {
            stripe: true, // 是否为斑马纹 table
            loading: false, // 是否添加表格loading加载动画
            highlightCurrentRow: true, // 是否支持当前行高亮显示
            mutiSelect: true // 是否支持列表项选中功能
          } // table 的参数
        }
      },
      components: {
        expandDom: {
          props: {
            column: {
              required: true
            },
            row: {
              required: true
            }
          },
          render(h) {
            return h('div', {}, ([this.column.render(this.row, this.column)]))
          }
        }
      },
      mounted() {
        GetGoodsList()
        {
          var self = this;
          Axios.get(Api.goodsList(self.page), {
            headers: {
              'Content-Type': 'application/x-www-userSignIn-urlencoded',
              'X-Requested-With': 'XMLHttpRequest'
            }
          })
            .then(function (response) {
              self.list = response.data.data
              console.log(response)

            })
            .catch(function (error) {
              console.log(error);
            });
        }
      },
      methods: {
        // 切换每页显示的数量
        handleSizeChange(pagination) {
          this.pagination = pagination
        },
        // 切换页码
        handleIndexChange(pagination) {
          this.pagination = pagination
        },
        // 选中行
        handleSelectionChange(val) {
          console.log('val:', val)
        },
        // 编辑
        handleEdit(index, row) {
          console.log(' index:', index)
          console.log(' row:', row)
        },
        // 删除
        handleDel(index, row) {
          console.log(' index:', index)
          console.log(' row:', row)
        }
      }
    }
</script>

<style scoped>

</style>
