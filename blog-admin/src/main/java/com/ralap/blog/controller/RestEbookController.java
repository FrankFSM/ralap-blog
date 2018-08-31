/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.ralap.blog.controller;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.business.entity.Ebook;
import com.ralap.blog.business.entity.Link;
import com.ralap.blog.business.enums.LinkSourceEnum;
import com.ralap.blog.business.enums.ResponseStatus;
import com.ralap.blog.business.enums.TemplateKeyEnum;
import com.ralap.blog.business.service.BizEbookService;
import com.ralap.blog.business.service.MailService;
import com.ralap.blog.business.service.SysLinkService;
import com.ralap.blog.business.vo.EbookConditionVO;
import com.ralap.blog.business.vo.LinkConditionVO;
import com.ralap.blog.framework.object.PageResult;
import com.ralap.blog.framework.object.ResponseVO;
import com.ralap.blog.persistence.beans.BizEbook;
import com.ralap.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestEbookController
 * 电子书
 * @author: ralap 
 * @date: created at 2018/8/31 16:30
 */
@RestController
@RequestMapping("/ebook")
public class RestEbookController {
    @Autowired
    private BizEbookService bizEbookService;
    @Autowired
    private MailService mailService;

    @RequiresPermissions("ebooks")
    @PostMapping("/list")
    public PageResult list(EbookConditionVO vo) {
        PageInfo<Ebook> pageInfo = bizEbookService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("ebook:add")
    @PostMapping(value = "/add")
    public ResponseVO add(Ebook ebook) {
        bizEbookService.insert(ebook);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"ebook:batchDelete", "ebook:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            bizEbookService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个友情链接");
    }

    @RequiresPermissions("ebook:get")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.bizEbookService.getByPrimaryKey(id));
    }

    @RequiresPermissions("ebook:edit")
    @PostMapping("/edit")
    public ResponseVO edit(Ebook ebook) {
        try {
            bizEbookService.updateSelective(ebook);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("友情链接修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
