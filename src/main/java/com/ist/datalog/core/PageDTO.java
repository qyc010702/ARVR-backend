package com.ist.datalog.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

/**
 * @author : LiYiFan
 * @date : 2023/10/25 16:18
 * @desc : 分页数据类型响应体
 */

@ApiModel(value = "分页信息类型", description = "描述分页数据视图的基本对象")
public class PageDTO<T> extends CollectionDTO<T> {

    @ApiModelProperty("总数")
    private final Long totalSize;
    @ApiModelProperty("当前页")
    private final Integer pageCurrent;
    @ApiModelProperty("每页数")
    private final Integer pageSize;
    @ApiModelProperty("总页数")
    private final Integer pageTotal;
    @ApiModelProperty("排序")
    private final String sort;

    public Long getTotalSize() {
        return this.totalSize;
    }

    public Integer getPageCurrent() {
        return this.pageCurrent;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getPageTotal() {
        return this.pageTotal;
    }

    public String getSort() {
        return this.sort;
    }

    public static <T> PageDTO success(Page<T> data) {
        return (new PageBuilder(data)).build();
    }

    public static <T> PageDTO of(HttpStatus status, Page<T> data) {
        return (new PageBuilder(status, data)).build();
    }

    public static <T> PageDTO of(Boolean success, Integer code, Page<T> data) {
        return (new PageBuilder(success, code, data)).build();
    }

    public String toString() {
        return "PageDTO{ totalSize=" + this.totalSize + ", pageCurrent=" + this.pageCurrent + ", pageSize=" + this.pageSize + ", pageTotal=" + this.pageTotal + ", sort='" + this.sort + '\'' + ", " + super.toString() + "} ";
    }

    private PageDTO(PageBuilder<T> builder) {
        super(builder);
        this.totalSize = builder.totalSize;
        this.pageCurrent = builder.pageCurrent;
        this.pageSize = builder.pageSize;
        this.pageTotal = builder.pageTotal;
        this.sort = builder.sort;
    }

    public static class PageBuilder<T> extends CollectionDTO.CollectionBuilder<T> {
        private final Long totalSize;
        private final Integer pageCurrent;
        private final Integer pageSize;
        private final Integer pageTotal;
        private final String sort;

        protected PageBuilder(Page<T> page) {
            super(page.getContent());
            this.totalSize = page.getTotalElements();
            this.pageCurrent = page.getNumber();
            this.pageSize = page.getSize();
            this.pageTotal = page.getTotalPages();
            this.sort = page.getSort().toString();
        }

        protected PageBuilder(HttpStatus status, Page<T> page) {
            super(status, page.getContent());
            this.totalSize = page.getTotalElements();
            this.pageCurrent = page.getNumber();
            this.pageSize = page.getSize();
            this.pageTotal = page.getTotalPages();
            this.sort = page.getSort().toString();
        }

        protected PageBuilder(Boolean success, Integer code, Page<T> page) {
            super(success, code, page.getContent());
            this.totalSize = page.getTotalElements();
            this.pageCurrent = page.getNumber();
            this.pageSize = page.getSize();
            this.pageTotal = page.getTotalPages();
            this.sort = page.getSort().toString();
        }

        public PageDTO<T> build() {
            return new PageDTO(this);
        }

        protected PageBuilder<T> self() {
            return this;
        }
    }
}
