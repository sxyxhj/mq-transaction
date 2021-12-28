package org.sxyxhj.productservice.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.sxyxhj.productservice.dto.SkuInventory;

/**
 * @program: mq-transaction
 * @description:
 * @author: sxyxhj
 * @create: 2021-12-28 17:11
 **/

public interface InventoryMapper {

    @Select("select id,sku_no as skuNo,qty from sku_inventory where sku_no = #{skuNo}")
    SkuInventory get(int skuNo);

    @Update("update sku_inventory set qty = #{qty} where sku_no = #{skuNo}")
    int updateQty(SkuInventory skuInventory);
}
