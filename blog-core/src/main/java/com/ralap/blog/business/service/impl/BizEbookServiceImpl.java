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
package com.ralap.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.business.annotation.RedisCache;
import com.ralap.blog.business.entity.Ebook;
import com.ralap.blog.business.service.BizEbookService;
import com.ralap.blog.business.vo.EbookConditionVO;
import com.ralap.blog.persistence.beans.BizEbook;
import com.ralap.blog.persistence.mapper.BizEbookMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * 友情链接
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Service
public class BizEbookServiceImpl implements BizEbookService {

    @Autowired
    private BizEbookMapper bizEbookMapper;


    /**
     * 分页查询
     */
    @Override
    public PageInfo<Ebook> findPageBreakByCondition(EbookConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizEbook> list = bizEbookMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Ebook> boList = new ArrayList<>();
        for (BizEbook bizEbook : list) {
            boList.add(new Ebook(bizEbook));
        }
        PageInfo bean = new PageInfo<BizEbook>(list);
        bean.setList(boList);
        return bean;
    }

    /**
     * 查询可在首页显示的友情链接列表
     */
    @Override
    public List<Ebook> listOfIndex() {
        EbookConditionVO vo = new EbookConditionVO(1);
        vo.setPageSize(100);
        PageInfo<Ebook> pageInfo = this.findPageBreakByCondition(vo);
        return pageInfo == null ? null : pageInfo.getList();
    }

    /**
     * 查询可在内页显示的友情链接列表
     */
    @Override
    @RedisCache
    public List<Ebook> listOfInside() {
        EbookConditionVO vo = new EbookConditionVO(1);
        vo.setPageSize(100);
        PageInfo<Ebook> pageInfo = this.findPageBreakByCondition(vo);
        return pageInfo == null ? null : pageInfo.getList();
    }

    /**
     * 查询已禁用的友情链接列表
     */
    @Override
    @RedisCache
    public List<Ebook> listOfDisable() {
        EbookConditionVO vo = new EbookConditionVO(0);
        vo.setPageSize(100);
        PageInfo<Ebook> pageInfo = this.findPageBreakByCondition(vo);
        return pageInfo == null ? null : pageInfo.getList();
    }

    /**
     * 分组获取所有连接
     * {indexList:首页显示,insideList:内页,disableList:禁用}
     */
    @Override
    @RedisCache
    public Map<String, List<Ebook>> listAllByGroup() {
        // 首页连接
        List<Ebook> listOfIndex = this.listOfIndex();
        // 内页连接
        List<Ebook> listOfInside = this.listOfInside();
        // 已禁用的连接
        List<Ebook> listOfDisable = this.listOfDisable();
        Map<String, List<Ebook>> resultMap = new HashMap<>();
        resultMap.put("indexList", listOfIndex);
        resultMap.put("insideList", listOfInside);
        resultMap.put("disableList", listOfDisable);
        return resultMap;
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Ebook insert(Ebook entity) {
        Assert.notNull(entity, "Ebook不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        entity.setStatus(entity.getStatus());
        bizEbookMapper.insertSelective(entity.getBizEbook());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     */
    @Override
    public void insertList(List<Ebook> entities) {
        Assert.notNull(entities, "Ebooks不可为空！");
        List<BizEbook> list = new ArrayList<>();
        for (Ebook entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            entity.setStatus(entity.getStatus());
            list.add(entity.getBizEbook());
        }
        bizEbookMapper.insertList(list);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return bizEbookMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean update(Ebook entity) {
        Assert.notNull(entity, "Ebook不可为空！");
        entity.setUpdateTime(new Date());
        entity.setStatus(entity.getStatus());
        return bizEbookMapper.updateByPrimaryKey(entity.getBizEbook()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Ebook entity) {
        Assert.notNull(entity, "Ebook不可为空！");
        entity.setUpdateTime(new Date());
        entity.setStatus(entity.getStatus());
        return bizEbookMapper.updateByPrimaryKeySelective(entity.getBizEbook()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    @Override
    public Ebook getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizEbook entity = bizEbookMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Ebook(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    @Override
    public Ebook getOneByEntity(Ebook entity) {
        Assert.notNull(entity, "Ebook不可为空！");
        BizEbook bo = bizEbookMapper.selectOne(entity.getBizEbook());
        return null == bo ? null : new Ebook(bo);
    }


    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     */
    @Override
    @RedisCache
    public List<Ebook> listAll() {
        List<BizEbook> entityList = bizEbookMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Ebook> list = new ArrayList<>();
        for (BizEbook entity : entityList) {
            list.add(new Ebook(entity));
        }
        return list;
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    @Override
    @RedisCache
    public List<Ebook> listByEntity(Ebook entity) {
        Assert.notNull(entity, "Ebook不可为空！");
        List<BizEbook> entityList = bizEbookMapper.select(entity.getBizEbook());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Ebook> list = new ArrayList<>();
        for (BizEbook po : entityList) {
            list.add(new Ebook(po));
        }
        return list;
    }
}
