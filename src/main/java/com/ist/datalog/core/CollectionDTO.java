package com.ist.datalog.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;


import java.util.Collection;

/**
 * @author : LiYiFan
 * @date : 2023/10/25 16:13
 * @desc : 集合数据类型响应体
 */
@ApiModel(value = "集合数据返回结构", description = "集合类型的数据信息返回结构")
public class CollectionDTO<T> extends AbstractHttpDTO {

    @ApiModelProperty("集合数据")
    private final Collection<T> data;
    @ApiModelProperty("总数")
    private final Integer total;

    public Collection<T> getData() {
        return this.data;
    }

    public Integer getTotal() {
        return this.total;
    }

    protected CollectionDTO(CollectionBuilder<T> builder) {
        super(builder);
        this.data = builder.data;
        this.total = builder.total;
    }

    public String toString() {
        return "CollectionDTO{data=" + this.data + ", total=" + this.total + ", " + super.toString() + "} ";
    }

    public static <T> CollectionDTO success(Collection<T> data) {
        return (new CollectionBuilder(data)).build();
    }

    public static <T> CollectionDTO of(HttpStatus status, Collection<T> data) {
        return (new CollectionBuilder(status, data)).build();
    }

    public static <T> CollectionDTO of(Boolean success, Integer code, Collection<T> data) {
        return (new CollectionBuilder(success, code, data)).build();
    }

    public static class CollectionBuilder<T> extends AbstractHttpDTO.AbstractHttpBuilder<CollectionBuilder<T>> {
        private final Collection<T> data;
        private final Integer total;

        protected CollectionBuilder(Collection<T> data) {
            super.success();
            this.data = data;
            this.total = data.size();
        }

        protected CollectionBuilder(HttpStatus status, Collection<T> data) {
            super.status(status);
            this.data = data;
            this.total = data.size();
        }

        protected CollectionBuilder(Boolean success, Integer code, Collection<T> data) {
            super.status(success, code);
            this.data = data;
            this.total = data.size();
        }

        public CollectionDTO<T> build() {
            return new CollectionDTO(this);
        }

        protected CollectionBuilder<T> self() {
            return this;
        }
    }
}
