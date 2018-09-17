<#include "include/macros.ftl">
<@header title="关于 | ${config.siteName}"
keywords="${config.siteName},关于博客"
description="一个程序员的个人博客,关于我的个人原创博客 - ${config.siteName}"
canonical="/about">
</@header>

<div class="container custome-container">
  <nav class="breadcrumb">
    <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip"
       data-placement="bottom"><i class="fa fa-home"></i>首页</a>
    <i class="fa fa-angle-right"></i>关于
  </nav>
  <div class="row">
  <@blogHeader title="书架"></@blogHeader>
    <div class="col-sm-12 blog-main">
      <div class="blog-body expansion">
      <#if page.list?exists && (page.list?size > 0)>
        <#list page.list as item>
          <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
              <img
                  src="${config.qiuniuBasePath + item.coverImage}" alt="稍等">
              <div class="caption">
                <h5 style="text-align: center">${item.author}</h5>
                <h3 style="text-align: center">${item.title}</h3>
                <p><a href="${item.path}" target="_blank" class="btn btn-primary" role="button" style="width: 100%">下载</a>
                </p>
              </div>
            </div>
          </div>
        </#list>
      <#else >
                <article class="fade-in" style="height: auto">
                  <div class="rows">
                    <div class="col-md-10 col-sm-12">
                      <div class="h3 text-center">亲，啥也没找到啊~~<img
                          src="http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/15/j_thumb.gif"
                          alt="[囧]"></div>
                      <div class="h5 text-center">换个姿势，再来一次~~</div>
                    </div>
                    <div class="col-md-2 col-sm-12">
                      <img src="${config.staticWebSite}/img/wrong.gif" class="center-block"
                           alt="惩罚我" style="width: 110px;margin-top: -10px;">
                    </div>
                  </div>
                  <form action="/" method="post" class="form-horizontal searchForm">
                    <input type="hidden" name="pageNumber" value="1">
                    <div class="input-group">
                      <input type="text" class="form-control br-none" name="keywords"
                             placeholder="换个姿势，再来一次~~">
                      <span class="input-group-btn">
                                <button class="btn btn-default br-none nav-search-btn pointer"
                                        type="submit"><i class="fa fa-search"></i> 搜索</button>
                            </span>
                    </div>
                    <div class="clear" style="margin-bottom: 10px"></div>
                    <ul class="list-unstyled list-inline search-hot">
                      <li><strong style="position: relative;top: 2px;color: #999999;">热门搜索：</strong>
                      </li>
                      <li><a class="pointer" rel="external nofollow"><span
                          class="label label-default">Java</span></a></li>
                      <li><a class="pointer" rel="external nofollow"><span
                          class="label label-primary">Springboot</span></a></li>
                      <li><a class="pointer" rel="external nofollow"><span
                          class="label label-success">Linux</span></a></li>
                      <li><a class="pointer" rel="external nofollow"><span class="label label-info">Maven</span></a>
                      </li>
                      <li><a class="pointer" rel="external nofollow"><span
                          class="label label-warning">Bootstrap</span></a></li>
                      <li><a class="pointer" rel="external nofollow"><span
                          class="label label-danger">阿里云</span></a></li>
                    </ul>
                  </form>
                </article>
      </#if>
        <div class="clearfloat"></div>
      </div>
    </div>
  </div>
</div>

<@footer>
    <script src="https://v1.hitokoto.cn/?encode=js&c=d&select=%23hitokoto" defer></script>
</@footer>
