package com.bytedance.model.dto;

/**
 * @author: 15827
 * @date: 2025/2/10 16:29
 * @description: OrdersSubmitDTO类
 */
public class OrdersSubmitDTO {

        private Long addressId;

        private Long couponId;

        private String orderToken;

        private String note;

        public Long getAddressId() {
            return addressId;
        }

        public void setAddressId(Long addressId) {
            this.addressId = addressId;
        }

        public Long getCouponId() {
            return couponId;
        }

        public void setCouponId(Long couponId) {
            this.couponId = couponId;
        }

        public String getOrderToken() {
            return orderToken;
        }

        public void setOrderToken(String orderToken) {
            this.orderToken = orderToken;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

}
