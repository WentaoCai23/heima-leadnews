package com.heima.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    @Autowired
    private ApArticleMapper apArticleMapper;

    private final static short MAX_PAGE_SIZE = 50;

    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {

        if (dto == null) {
            return ResponseResult.okResult(null);
        }

        Integer size = dto.getSize();
        if (Objects.isNull(size) || size.equals(0)){
            size = 10;
        }
        size = Math.min(size, MAX_PAGE_SIZE);
        dto.setSize(size);

        if (!ArticleConstants.LOADTYPE_LOAD_MORE.equals(type) && !ArticleConstants.LOADTYPE_LOAD_NEW.equals(type)){
            type = ArticleConstants.LOADTYPE_LOAD_MORE;
        }

        if(StringUtils.isBlank(dto.getTag())){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }

        if (dto.getMaxBehotTime() == null) {
            dto.setMaxBehotTime(new Date());
        }

        if (dto.getMinBehotTime() == null) {
            dto.setMinBehotTime(new Date());
        }

        List<ApArticle> articleList = apArticleMapper.loadArticleList(dto, type);
        return ResponseResult.okResult(articleList);
    }
}