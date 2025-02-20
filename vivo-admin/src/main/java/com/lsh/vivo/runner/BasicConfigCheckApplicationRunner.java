package com.lsh.vivo.runner;

import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.bean.request.user.UserSaveVO;
import com.lsh.vivo.bean.response.role.RoleSelectedVO;
import com.lsh.vivo.entity.*;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.enumerate.SystemEnum;
import com.lsh.vivo.mapper.RoleRelationMapper;
import com.lsh.vivo.mapper.struct.UserMpp;
import com.lsh.vivo.provider.ApplicationContextProvider;
import com.lsh.vivo.service.*;
import com.lsh.vivo.util.OauthContext;
import com.lsh.vivo.util.RedisUtil;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.lsh.vivo.entity.table.GoodsSeckillTableDef.GOODS_SECKILL;
import static com.lsh.vivo.entity.table.GoodsSkuTableDef.GOODS_SKU;
import static com.lsh.vivo.entity.table.RoleRelationTableDef.ROLE_RELATION;
import static com.lsh.vivo.entity.table.RoleTableDef.ROLE;
import static com.lsh.vivo.entity.table.UserRoleTableDef.USER_ROLE;
import static com.lsh.vivo.entity.table.UserTableDef.USER;
import static com.mybatisflex.core.query.QueryMethods.select;


/**
 * 超级管理员账号密码管理
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-12-21 10:37
 **/
@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class BasicConfigCheckApplicationRunner implements ApplicationRunner {

    @Value("${project.root.name}")
    private String rootName;

    @Value("${project.root.pass}")
    private String rootPassword;

    private final GoodsSkuService goodsSkuService;
    private final GoodsSeckillService goodsSeckillService;
    private final RedisUtil redisUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            if (StringUtils.isBlank(rootName)) {
                rootName = "admin";
            }
            if (StringUtils.isBlank(rootPassword)) {
                rootPassword = "123456";
            }
            OauthContext.set(GlobalConstant.HTTP_USER_ID, "SYSTEM");
            OauthContext.set(GlobalConstant.HTTP_USER, "系统生成");
            RoleService roleService = ApplicationContextProvider.getBean(RoleService.class);
            QueryWrapper queryWrapper = select(ROLE.ID)
                    .from(ROLE)
                    .where(ROLE.NAME.eq("超级管理员"))
                    .limit(1);
            String roleId = roleService.getObjAs(queryWrapper, String.class);
            if (StringUtils.isBlank(roleId)) {
                Role superRoot = new Role();
                superRoot.setName("超级管理员");
                superRoot.setSys(SystemEnum.T.name());
                superRoot.setStatus(CommonStatusEnum.I.name());
                superRoot.setRevision(1);
                superRoot.setCreator("系统生成");
                superRoot.setCreatorId("SYSTEM");
                superRoot.setCreateTime(LocalDateTime.now());
                roleService.save(superRoot);
                roleId = superRoot.getId();
            }
            List<String> functions = new ArrayList<>(List.of("user:*", "role:*", "goods:*", "goods-cate:*", "goods-sku:*", "order:*", "goods-seckill:*"));
            RoleRelationService roleRelationService = ApplicationContextProvider.getBean(RoleRelationService.class);
            queryWrapper = select(ROLE_RELATION.RELATION_CODE)
                    .from(ROLE_RELATION)
                    .where(ROLE_RELATION.ROLE_ID.eq(roleId));
            List<String> hasFunctions = roleRelationService.listAs(queryWrapper, String.class);
            if (hasFunctions == null) {
                hasFunctions = new ArrayList<>(0);
            }
            Collection<String> intersections = CollectionUtils.intersection(functions, hasFunctions);
            functions.removeAll(intersections);
            hasFunctions.removeAll(intersections);
            RoleRelationMapper roleRelationMapper = ApplicationContextProvider.getBean(RoleRelationMapper.class);
            if (CollectionUtils.isNotEmpty(functions)) {
                // 新增额外的绑定权限
                String finalRoleId = roleId;
                List<RoleRelation> roleRelations = functions.stream().map(perm -> {
                    RoleRelation relation = new RoleRelation();
                    relation.setRoleId(finalRoleId);
                    relation.setRelationCode(perm);
                    relation.setRevision(1);
                    relation.setCreator("系统生成");
                    relation.setCreatorId("SYSTEM");
                    relation.setCreateTime(LocalDateTime.now());
                    return relation;
                }).toList();
                roleRelationMapper.insertBatch(roleRelations);
            }
            if (CollectionUtils.isNotEmpty(hasFunctions)) {
                // 删去不再绑定的权限
                QueryWrapper wrapper = select()
                        .from(ROLE_RELATION)
                        .where(ROLE_RELATION.ROLE_ID.eq(roleId))
                        .and(ROLE_RELATION.RELATION_CODE.in(hasFunctions));
                roleRelationMapper.deleteByQuery(wrapper);
            }

            UserRoleService userRoleRelationService = ApplicationContextProvider.getBean(UserRoleService.class);
            queryWrapper = select(USER_ROLE.USER_ID)
                    .from(USER_ROLE)
                    .where(USER_ROLE.ROLE_ID.eq(roleId)).limit(1);
            String userId = userRoleRelationService.getObjAs(queryWrapper, String.class);

            UserService userService = ApplicationContextProvider.getBean(UserService.class);
            queryWrapper = QueryWrapper.create().where(USER.ID.eq(userId));
            boolean exists = userId != null && userService.exists(queryWrapper);
            if (!exists) {
                UserSaveVO userSaveVO = new UserSaveVO();
                userSaveVO.setUsername(rootName);
                userSaveVO.setPhone("");

                RoleSelectedVO roleSelectedVO = new RoleSelectedVO();
                roleSelectedVO.setId(roleId);
                userSaveVO.setRoles(List.of(roleSelectedVO));

                User user = UserMpp.INSTANCE.toUser(userSaveVO);
                user.setNickname("超级管理员");
                user.setPassword(rootPassword);
                user.setSys(SystemEnum.T.name());
                userService.save(user);
            }

            // 查询所有秒杀商品
            QueryWrapper queryWrapper2 = select()
                    .where(GOODS_SECKILL.START_TIME.lt(LocalDateTime.now()))
                    .and(GOODS_SECKILL.END_TIME.gt(LocalDateTime.now()))
                    .and(GOODS_SECKILL.STATUS.eq(GoodsStatusEnum.U.name()))
                    .and(GOODS_SECKILL.SECKILL_NUM.gt(0));
            List<GoodsSeckill> goodsSeckills = goodsSeckillService.list(queryWrapper2);
            if (CollectionUtils.isNotEmpty(goodsSeckills)) {
                for (GoodsSeckill goodsSeckill : goodsSeckills) {
                    if (!redisUtil.hasKey("seckill:" + goodsSeckill.getSkuId() + ":")) {
                        redisUtil.set("seckill:" + goodsSeckill.getSkuId() + ":", goodsSeckill.getSeckillNum());
                    }
                }
                List<String> skuIds = goodsSeckills.stream().map(GoodsSeckill::getSkuId).toList();
                QueryWrapper queryWrapper1 = select()
                        .where(GOODS_SKU.ID.in(skuIds))
                        .and(GOODS_SKU.STATUS.eq(GoodsStatusEnum.U.name()));
                List<GoodsSku> goodsSkus = goodsSkuService.list(queryWrapper1);
                for (GoodsSku goodsSku : goodsSkus) {
                    if (!redisUtil.hasKey("goodsSku:" + goodsSku.getId() + ":")) {
                        redisUtil.set("goodsSku:" + goodsSku.getId() + ":", goodsSku.getStock());
                    }
                }
                log.info("秒杀商品缓存成功");
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
