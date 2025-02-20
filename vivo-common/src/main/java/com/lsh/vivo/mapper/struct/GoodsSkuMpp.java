package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.request.goods.GoodsSaveVO;
import com.lsh.vivo.bean.request.goods.GoodsStatusVO;
import com.lsh.vivo.bean.request.goods.GoodsUpdateVO;
import com.lsh.vivo.bean.request.goods.sku.GoodsSkuSaveVO;
import com.lsh.vivo.bean.request.goods.sku.GoodsSkuStatusVO;
import com.lsh.vivo.bean.request.goods.sku.GoodsSkuUpdateVO;
import com.lsh.vivo.bean.request.goods.sku.StockUpdateVO;
import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategorySelectVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.Goods;
import com.lsh.vivo.entity.GoodsCategory;
import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.mybatisflex.core.paginate.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/20 22:15
 */
@Mapper(uses = {MapperStructTypeConvert.class})
public interface GoodsSkuMpp {

    /**
     * 定义实例
     */
    GoodsSkuMpp INSTANCE = Mappers.getMapper(GoodsSkuMpp.class);

    /**
     * 转前端交互商品Sku
     *
     * @param goodsSku 商品Sku
     * @return 返回前端交互商品Sku
     */
    @Mapping(target = "startTime", source = "goodsSeckill.startTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "endTime", source = "goodsSeckill.endTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "seckillId", source = "goodsSeckill.id")
    GoodsSkuVO toVO(GoodsSku goodsSku);

    /**
     * 转前端交互商品Sku集
     *
     * @param goodsPage 分页信息
     * @return 返回前端交互商品Sku
     */
    @Mapping(target = "page.total", source = "totalRow")
    @Mapping(target = "page.size", source = "pageSize")
    @Mapping(target = "page.current", source = "pageNumber")
    @Mapping(target = "results", source = "records")
    PageVO<GoodsSkuVO> toPageVO(Page<GoodsSku> goodsPage);

    /**
     * 转实体
     *
     * @param goodsSaveVO 前端交互用户
     * @return
     */
    Goods toDO(GoodsSaveVO goodsSaveVO);

    /**
     * 修改转实体
     */
    Goods toDO(GoodsUpdateVO goodsUpdateVO);

    /**
     * 修改状态转实体
     */
    Goods toDO(GoodsStatusVO goodsStatusVO);

    /**
     * 转前端交互商品Sku下拉框数据
     */
    List<GoodsCategorySelectVO> toSelectVO(List<GoodsCategory> goodsCats);

    /**
     * 转实体
     */
    GoodsSku toDO(GoodsSkuSaveVO goodsSkuSaveVO);

    /**
     * 修改转实体
     */
    GoodsSku toDO(GoodsSkuUpdateVO goodsSkuUpdateVO);

    /**
     * 修改状态转实体
     */
    GoodsSku toDO(GoodsSkuStatusVO goodsSkuStatusVO);


    GoodsSku toDO(StockUpdateVO stockUpdateVO);

    List<GoodsSkuVO> toVO(List<GoodsSku> goodsSkus);
}
