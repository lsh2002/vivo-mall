package com.lsh.vivo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class RoleTableDef extends TableDef {

    /**
     * 角色信息表

 @author lsh
     */
    public static final RoleTableDef ROLE = new RoleTableDef();

    /**
     * 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 是否系统的;T-系统预留不允许删除，F-自定义
     */
    public final QueryColumn SYS = new QueryColumn(this, "sys");

    /**
     * 角色名
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 创建人
     */
    public final QueryColumn CREATOR = new QueryColumn(this, "creator");

    /**
     * 更新人
     */
    public final QueryColumn MODIFIER = new QueryColumn(this, "modifier");

    /**
     * 乐观锁
     */
    public final QueryColumn REVISION = new QueryColumn(this, "revision");

    /**
     * 创建人id
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新人id
     */
    public final QueryColumn MODIFIER_ID = new QueryColumn(this, "modifier_id");

    /**
     * 更新时间
     */
    public final QueryColumn MODIFIED_TIME = new QueryColumn(this, "modified_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, SYS, NAME, STATUS, CREATOR, MODIFIER, REVISION, CREATOR_ID, CREATE_TIME, MODIFIER_ID, MODIFIED_TIME};

    public RoleTableDef() {
        super("", "role");
    }

}
