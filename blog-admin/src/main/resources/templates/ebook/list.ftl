<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
  <div class="col-md-12 col-sm-12 col-xs-12">
    <ol class="breadcrumb">
      <li><a href="/">首页</a></li>
      <li class="active">电子书管理</li>
    </ol>
    <div class="x_panel">
      <div class="x_content">
        <div class="<#--table-responsive-->">
          <div class="btn-group hidden-xs" id="toolbar">
                        <@shiro.hasPermission name="link:add">
                            <button id="btn_add" type="button" class="btn btn-default"
                                    title="新增电子书">
                              <i class="fa fa-plus"></i> 新增电子书
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="link:batchDelete">
                            <button id="btn_delete_ids" type="button" class="btn btn-default"
                                    title="删除选中">
                              <i class="fa fa-trash-o"></i> 批量删除
                            </button>
                        </@shiro.hasPermission>
          </div>
          <table id="tablelist">
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
<!--添加弹框-->
<div class="modal fade" id="addOrUpdateModal" tabindex="-1" role="dialog"
     aria-labelledby="addroleLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
            aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addroleLabel">添加电子书</h4>
      </div>
      <div class="modal-body">
        <form id="addOrUpdateForm" class="form-horizontal form-label-left" novalidate>
          <input type="hidden" name="id">
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-3" for="title">书名: <span
                class="required">*</span></label>
            <div class="col-md-7 col-sm-7 col-xs-7">
              <input type="text" class="form-control" name="title" id="title" required="required"
                     placeholder="请输入书名"/>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-3" for="author">作者: <span
                class="required">*</span></label>
            <div class="col-md-7 col-sm-7 col-xs-7">
              <input type="text" class="form-control" name="author" id="author" required="required"
                     placeholder="请输入作者"/>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"
                   for="homeDesc">封面 </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="file" class="form-control col-md-7 col-xs-12 uploadPreview"
                     data-preview-container="#coverImageFilePreview"
                     name="coverImageFile" id="coverImageFile"/>
            </div>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <div id="coverImageFilePreview" style="width: 200px;height: auto"></div>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-3" for="description">下载地址: </label>
            <div class="col-md-7 col-sm-7 col-xs-7">
              <input type="text" class="form-control" id="path" name="path"
                     placeholder="请输下载地址"/>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-3" for="description">描述: </label>
            <div class="col-md-7 col-sm-7 col-xs-7">
              <input type="text" class="form-control" id="description" name="description"
                     placeholder="请输入描述"/>
            </div>
          </div>

          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-3" for="status">状态: </label>
            <div class="col-md-7 col-sm-7 col-xs-7">
              <ul class="list-unstyled list-inline">
                <li><input type="radio" class="square" name="status" value="1"> 启用</li>
                <li><input type="radio" class="square" name="status" value="0"> 禁用</li>
              </ul>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close">
          关闭</i></button>
        <button type="button" class="btn btn-success addOrUpdateBtn"><i class="fa fa-save"> 保存</i>
        </button>
      </div>
    </div>
  </div>
</div>
<!--/添加弹框-->
<@footer>
    <script>
      /**
       * 操作按钮
       * @param code
       * @param row
       * @param index
       * @returns {string}
       */
      function operateFormatter(code, row, index) {
        var trId = row.id;
        var operateBtn = [
          '<@shiro.hasPermission name="ebook:edit"><a class="btn btn-xs btn-primary btn-update" data-id="'
          + trId + '"><i class="fa fa-edit"></i>编辑</a></@shiro.hasPermission>',
          '<@shiro.hasPermission name="ebook:delete"><a class="btn btn-xs btn-danger btn-remove" data-id="'
          + trId + '"><i class="fa fa-trash-o"></i>删除</a></@shiro.hasPermission>'
        ];
        return operateBtn.join('');
      }

      $(function () {
        var options = {
          modalName: "电子书",
          url: "/ebook/list",
          getInfoUrl: "/ebook/get/{id}",
          updateUrl: "/ebook/edit",
          removeUrl: "/ebook/remove",
          createUrl: "/ebook/add",
          columns: [
            {
              checkbox: true
            }, {
              field: 'title',
              title: '书名',
              width: '100px',
              editable: false
            }, {
              field: 'author',
              title: '作者',
              width: '100px',
              editable: false
            }, {
              field: 'coverImage',
              title: '封面',
              editable: false,
              width: '40px',
              formatter: function (code) {
                return !code ? '' : '<img src="' + appConfig.qiniuPath + code + '" width="50px">';
              }
            }, {
              field: 'path',
              title: '地址',
              width: '120px',
              editable: false,
              formatter: function (code) {
                return '<a href="' + code + '" target="_blank" rel="nofollow ">' + code + '</a>';
              }
            }, {
              field: 'status',
              title: '状态',
              editable: false,
              width: '40px',
              formatter: function (code, row, index) {
                return code ? "启用" : "<strong style='color: red;' title='" + row.remark
                    + "'>禁用</strong>";
              }
            }, {
              field: 'description',
              title: '描述',
              width: '200px',
              editable: false
            }, {
              field: 'updateTime',
              title: '更新时间',
              width: '120px',
              editable: false,
              formatter: function (code) {
                return new Date(code).format("yyyy-MM-dd hh:mm:ss")
              }
            }, {
              field: 'operate',
              title: '操作',
              width: '150px',
              formatter: operateFormatter //自定义方法，添加操作按钮
            }
          ],
          rowStyle: function (row, index) {
            //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
            var strclass = "";
            if (row.status) {
              // strclass = 'success';//还有一个active
            } else {
              strclass = 'danger';
            }
            return {'classes': strclass}
          }
        };
        //1.初始化Table
        $.tableUtil.init(options);
        //2.初始化Button的点击事件
        $.buttonUtil.init(options);
      });
    </script>
</@footer>