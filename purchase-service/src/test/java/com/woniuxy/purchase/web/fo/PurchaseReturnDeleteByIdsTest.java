package com.woniuxy.purchase.web.fo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseReturnDeleteByIdsTest {

    @Test
    void getIds() {
        PurchaseReturnDeleteByIds ids = new PurchaseReturnDeleteByIds();
        ids.setIds("1,2,3");
        Long[] idsIds = ids.getIds();
        for (Long idsId : idsIds) {
            System.out.println(idsId);
        }
    }
}