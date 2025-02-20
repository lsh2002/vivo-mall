package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.request.goods.GoodsSaveVO;
import com.lsh.vivo.bean.request.goods.GoodsStatusVO;
import com.lsh.vivo.bean.request.goods.GoodsUpdateVO;
import com.lsh.vivo.bean.request.goods.sku.GoodsSkuSaveVO;
import com.lsh.vivo.bean.request.goods.sku.GoodsSkuStatusVO;
import com.lsh.vivo.bean.request.goods.sku.GoodsSkuUpdateVO;
import com.lsh.vivo.bean.request.goods.sku.StockUpdateVO;
import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.goods.GoodsVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategorySelectVO;
import com.lsh.vivo.bean.response.goods.picture.GoodsPictureVO;
import com.lsh.vivo.bean.response.system.PageResult;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.Goods;
import com.lsh.vivo.entity.GoodsCategory;
import com.lsh.vivo.entity.GoodsPicture;
import com.lsh.vivo.entity.GoodsSeckill;
import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.enumerate.CommonColumnEnum;
import com.lsh.vivo.enumerate.GoodsCatLevelEnum;
import com.lsh.vivo.enumerate.GoodsPictureEnum;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.mybatisflex.core.paginate.Page;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-18T01:12:39+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class GoodsSkuMppImpl implements GoodsSkuMpp {

    private final MapperStructTypeConvert mapperStructTypeConvert = new MapperStructTypeConvert();

    @Override
    public GoodsSkuVO toVO(GoodsSku goodsSku) {
        if ( goodsSku == null ) {
            return null;
        }

        GoodsSkuVO goodsSkuVO = new GoodsSkuVO();

        goodsSkuVO.setStartTime( mapperStructTypeConvert.localDateTimeToLong( goodsSkuGoodsSeckillStartTime( goodsSku ) ) );
        goodsSkuVO.setEndTime( mapperStructTypeConvert.localDateTimeToLong( goodsSkuGoodsSeckillEndTime( goodsSku ) ) );
        goodsSkuVO.setSeckillId( goodsSkuGoodsSeckillId( goodsSku ) );
        goodsSkuVO.setId( goodsSku.getId() );
        goodsSkuVO.setRevision( goodsSku.getRevision() );
        goodsSkuVO.setName( goodsSku.getName() );
        goodsSkuVO.setGoodsId( goodsSku.getGoodsId() );
        goodsSkuVO.setGoodsName( goodsSku.getGoodsName() );
        goodsSkuVO.setAttribute( goodsSku.getAttribute() );
        goodsSkuVO.setPrice( goodsSku.getPrice() );
        goodsSkuVO.setSellingPrice( goodsSku.getSellingPrice() );
        goodsSkuVO.setStock( goodsSku.getStock() );
        goodsSkuVO.setSales( goodsSku.getSales() );
        goodsSkuVO.setRemark( goodsSku.getRemark() );
        if ( goodsSku.getStatus() != null ) {
            goodsSkuVO.setStatus( Enum.valueOf( GoodsStatusEnum.class, goodsSku.getStatus() ) );
        }
        if ( goodsSku.getSeckill() != null ) {
            goodsSkuVO.setSeckill( Enum.valueOf( CommonColumnEnum.class, goodsSku.getSeckill() ) );
        }
        if ( goodsSku.getSeckillPrice() != null ) {
            goodsSkuVO.setSeckillPrice( Double.parseDouble( goodsSku.getSeckillPrice() ) );
        }
        goodsSkuVO.setGoodsPictures( goodsPictureListToGoodsPictureVOList( goodsSku.getGoodsPictures() ) );
        goodsSkuVO.setGoods( goodsToGoodsVO( goodsSku.getGoods() ) );

        return goodsSkuVO;
    }

    @Override
    public PageVO<GoodsSkuVO> toPageVO(Page<GoodsSku> goodsPage) {
        if ( goodsPage == null ) {
            return null;
        }

        PageVO<GoodsSkuVO> pageVO = new PageVO<GoodsSkuVO>();

        pageVO.setPage( goodsSkuPageToPageResult( goodsPage ) );
        if ( goodsPage.hasRecords() ) {
            pageVO.setResults( toVO( goodsPage.getRecords() ) );
        }

        return pageVO;
    }

    @Override
    public Goods toDO(GoodsSaveVO goodsSaveVO) {
        if ( goodsSaveVO == null ) {
            return null;
        }

        Goods goods = new Goods();

        goods.setName( goodsSaveVO.getName() );
        goods.setCategoryId( goodsSaveVO.getCategoryId() );
        goods.setCategory( goodsSaveVO.getCategory() );
        goods.setDescription( goodsSaveVO.getDescription() );

        return goods;
    }

    @Override
    public Goods toDO(GoodsUpdateVO goodsUpdateVO) {
        if ( goodsUpdateVO == null ) {
            return null;
        }

        Goods goods = new Goods();

        goods.setId( goodsUpdateVO.getId() );
        goods.setRevision( goodsUpdateVO.getRevision() );
        goods.setName( goodsUpdateVO.getName() );
        goods.setCategoryId( goodsUpdateVO.getCategoryId() );
        goods.setCategory( goodsUpdateVO.getCategory() );
        goods.setDescription( goodsUpdateVO.getDescription() );

        return goods;
    }

    @Override
    public Goods toDO(GoodsStatusVO goodsStatusVO) {
        if ( goodsStatusVO == null ) {
            return null;
        }

        Goods goods = new Goods();

        goods.setId( goodsStatusVO.getId() );
        if ( goodsStatusVO.getStatus() != null ) {
            goods.setStatus( goodsStatusVO.getStatus().name() );
        }
        goods.setRevision( goodsStatusVO.getRevision() );

        return goods;
    }

    @Override
    public List<GoodsCategorySelectVO> toSelectVO(List<GoodsCategory> goodsCats) {
        if ( goodsCats == null ) {
            return null;
        }

        List<GoodsCategorySelectVO> list = new ArrayList<GoodsCategorySelectVO>( goodsCats.size() );
        for ( GoodsCategory goodsCategory : goodsCats ) {
            list.add( goodsCategoryToGoodsCategorySelectVO( goodsCategory ) );
        }

        return list;
    }

    @Override
    public GoodsSku toDO(GoodsSkuSaveVO goodsSkuSaveVO) {
        if ( goodsSkuSaveVO == null ) {
            return null;
        }

        GoodsSku goodsSku = new GoodsSku();

        goodsSku.setName( goodsSkuSaveVO.getName() );
        goodsSku.setGoodsId( goodsSkuSaveVO.getGoodsId() );
        goodsSku.setGoodsName( goodsSkuSaveVO.getGoodsName() );
        goodsSku.setAttribute( goodsSkuSaveVO.getAttribute() );
        goodsSku.setPrice( goodsSkuSaveVO.getPrice() );
        goodsSku.setSellingPrice( goodsSkuSaveVO.getSellingPrice() );
        goodsSku.setStock( goodsSkuSaveVO.getStock() );
        goodsSku.setSales( goodsSkuSaveVO.getSales() );
        goodsSku.setRemark( goodsSkuSaveVO.getRemark() );
        List<GoodsPicture> list = goodsSkuSaveVO.getGoodsPictures();
        if ( list != null ) {
            goodsSku.setGoodsPictures( new ArrayList<GoodsPicture>( list ) );
        }

        return goodsSku;
    }

    @Override
    public GoodsSku toDO(GoodsSkuUpdateVO goodsSkuUpdateVO) {
        if ( goodsSkuUpdateVO == null ) {
            return null;
        }

        GoodsSku goodsSku = new GoodsSku();

        goodsSku.setId( goodsSkuUpdateVO.getId() );
        goodsSku.setRevision( goodsSkuUpdateVO.getRevision() );
        goodsSku.setGoodsId( goodsSkuUpdateVO.getGoodsId() );
        goodsSku.setGoodsName( goodsSkuUpdateVO.getGoodsName() );
        goodsSku.setAttribute( goodsSkuUpdateVO.getAttribute() );
        goodsSku.setPrice( goodsSkuUpdateVO.getPrice() );
        goodsSku.setSellingPrice( goodsSkuUpdateVO.getSellingPrice() );
        goodsSku.setStock( goodsSkuUpdateVO.getStock() );
        goodsSku.setSales( goodsSkuUpdateVO.getSales() );
        goodsSku.setRemark( goodsSkuUpdateVO.getRemark() );
        List<GoodsPicture> list = goodsSkuUpdateVO.getGoodsPictures();
        if ( list != null ) {
            goodsSku.setGoodsPictures( new ArrayList<GoodsPicture>( list ) );
        }

        return goodsSku;
    }

    @Override
    public GoodsSku toDO(GoodsSkuStatusVO goodsSkuStatusVO) {
        if ( goodsSkuStatusVO == null ) {
            return null;
        }

        GoodsSku goodsSku = new GoodsSku();

        goodsSku.setId( goodsSkuStatusVO.getId() );
        if ( goodsSkuStatusVO.getStatus() != null ) {
            goodsSku.setStatus( goodsSkuStatusVO.getStatus().name() );
        }
        goodsSku.setRevision( goodsSkuStatusVO.getRevision() );

        return goodsSku;
    }

    @Override
    public GoodsSku toDO(StockUpdateVO stockUpdateVO) {
        if ( stockUpdateVO == null ) {
            return null;
        }

        GoodsSku goodsSku = new GoodsSku();

        goodsSku.setId( stockUpdateVO.getId() );
        goodsSku.setRevision( stockUpdateVO.getRevision() );

        return goodsSku;
    }

    @Override
    public List<GoodsSkuVO> toVO(List<GoodsSku> goodsSkus) {
        if ( goodsSkus == null ) {
            return null;
        }

        List<GoodsSkuVO> list = new ArrayList<GoodsSkuVO>( goodsSkus.size() );
        for ( GoodsSku goodsSku : goodsSkus ) {
            list.add( toVO( goodsSku ) );
        }

        return list;
    }

    private LocalDateTime goodsSkuGoodsSeckillStartTime(GoodsSku goodsSku) {
        if ( goodsSku == null ) {
            return null;
        }
        GoodsSeckill goodsSeckill = goodsSku.getGoodsSeckill();
        if ( goodsSeckill == null ) {
            return null;
        }
        LocalDateTime startTime = goodsSeckill.getStartTime();
        if ( startTime == null ) {
            return null;
        }
        return startTime;
    }

    private LocalDateTime goodsSkuGoodsSeckillEndTime(GoodsSku goodsSku) {
        if ( goodsSku == null ) {
            return null;
        }
        GoodsSeckill goodsSeckill = goodsSku.getGoodsSeckill();
        if ( goodsSeckill == null ) {
            return null;
        }
        LocalDateTime endTime = goodsSeckill.getEndTime();
        if ( endTime == null ) {
            return null;
        }
        return endTime;
    }

    private String goodsSkuGoodsSeckillId(GoodsSku goodsSku) {
        if ( goodsSku == null ) {
            return null;
        }
        GoodsSeckill goodsSeckill = goodsSku.getGoodsSeckill();
        if ( goodsSeckill == null ) {
            return null;
        }
        String id = goodsSeckill.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected GoodsPictureVO goodsPictureToGoodsPictureVO(GoodsPicture goodsPicture) {
        if ( goodsPicture == null ) {
            return null;
        }

        GoodsPictureVO goodsPictureVO = new GoodsPictureVO();

        goodsPictureVO.setId( goodsPicture.getId() );
        if ( goodsPicture.getMaster() != null ) {
            goodsPictureVO.setMaster( Enum.valueOf( GoodsPictureEnum.class, goodsPicture.getMaster() ) );
        }
        goodsPictureVO.setUrl( goodsPicture.getUrl() );

        return goodsPictureVO;
    }

    protected List<GoodsPictureVO> goodsPictureListToGoodsPictureVOList(List<GoodsPicture> list) {
        if ( list == null ) {
            return null;
        }

        List<GoodsPictureVO> list1 = new ArrayList<GoodsPictureVO>( list.size() );
        for ( GoodsPicture goodsPicture : list ) {
            list1.add( goodsPictureToGoodsPictureVO( goodsPicture ) );
        }

        return list1;
    }

    protected GoodsVO goodsToGoodsVO(Goods goods) {
        if ( goods == null ) {
            return null;
        }

        GoodsVO goodsVO = new GoodsVO();

        goodsVO.setId( goods.getId() );
        goodsVO.setRevision( goods.getRevision() );
        goodsVO.setName( goods.getName() );
        goodsVO.setCategoryId( goods.getCategoryId() );
        goodsVO.setCategory( goods.getCategory() );
        goodsVO.setDescription( goods.getDescription() );
        if ( goods.getStatus() != null ) {
            goodsVO.setStatus( Enum.valueOf( GoodsStatusEnum.class, goods.getStatus() ) );
        }

        return goodsVO;
    }

    protected PageResult goodsSkuPageToPageResult(Page<GoodsSku> page) {
        if ( page == null ) {
            return null;
        }

        PageResult pageResult = new PageResult();

        pageResult.setTotal( page.getTotalRow() );
        pageResult.setSize( page.getPageSize() );
        pageResult.setCurrent( page.getPageNumber() );

        return pageResult;
    }

    protected GoodsCategorySelectVO goodsCategoryToGoodsCategorySelectVO(GoodsCategory goodsCategory) {
        if ( goodsCategory == null ) {
            return null;
        }

        GoodsCategorySelectVO goodsCategorySelectVO = new GoodsCategorySelectVO();

        goodsCategorySelectVO.setId( goodsCategory.getId() );
        goodsCategorySelectVO.setName( goodsCategory.getName() );
        if ( goodsCategory.getLevel() != null ) {
            goodsCategorySelectVO.setLevel( Enum.valueOf( GoodsCatLevelEnum.class, goodsCategory.getLevel() ) );
        }

        return goodsCategorySelectVO;
    }
}
