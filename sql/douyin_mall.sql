-- 创建数据库
CREATE DATABASE `douyin_mall` DEFAULT CHARACTER SET utf8mb4;
USE `douyin_mall`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '地址ID',
                            `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                            `receiver_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人姓名',
                            `receiver_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人电话',
                            `receiver_province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省',
                            `receiver_city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '市',
                            `receiver_district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区',
                            `receiver_detail_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细地址',
                            `is_default` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否默认(0:否 1:是)',
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item`  (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物项ID',
                              `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                              `product_id` bigint(20) NOT NULL COMMENT '商品ID',
                              `quantity` int(11) NOT NULL DEFAULT 1 COMMENT '商品数量',
                              `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE INDEX `unique_cart_item`(`user_id` ASC, `product_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '购物车商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
                             `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
                             `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父分类ID',
                             `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态(0:禁用 1:启用)',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                          `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
                          `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                          `seller_id` bigint(20) NOT NULL COMMENT '卖家ID',
                          `total_amount` decimal(10, 2) NOT NULL COMMENT '订单总金额',
                          `status` tinyint(4) NOT NULL COMMENT '订单状态(0:待付款 1:待发货 2:待收货 3:已完成 4:已取消)',
                          `address_id` bigint(20) NOT NULL COMMENT '收货地址ID',
                          `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE INDEX `uk_order_no`(`order_no` ASC) USING BTREE,
                          INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
                          INDEX `idx_address_id`(`address_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单商品ID',
                               `order_id` bigint(20) NOT NULL COMMENT '订单ID',
                               `product_id` bigint(20) NOT NULL COMMENT '商品ID',
                               `product_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品标题',
                               `price` decimal(10, 2) NOT NULL COMMENT '购买价格',
                               `quantity` int(11) NOT NULL COMMENT '购买数量',
                               `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `idx_order_id`(`order_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
                            `seller_id` bigint(20) NOT NULL COMMENT '卖家ID',
                            `category_id` bigint(20) NOT NULL COMMENT '分类ID',
                            `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品标题',
                            `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '商品描述',
                            `price` decimal(10, 2) NOT NULL COMMENT '售价',
                            `stock` int(11) NOT NULL COMMENT '库存',
                            `sales` int(11) NULL DEFAULT 0 COMMENT '销量',
                            `main_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主图URL',
                            `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态(0:下架 1:上架)',
                            `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `idx_seller_id`(`seller_id` ASC) USING BTREE,
                            INDEX `idx_category_id`(`category_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_image
-- ----------------------------
DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image`  (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
                                  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
                                  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片URL',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  INDEX `idx_product_id`(`product_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                         `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
                         `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码(加密)',
                         `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
                         `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '昵称',
                         `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像URL',
                         `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态(0:禁用 1:启用)',
                         `role_id` bigint(20) NOT NULL COMMENT '角色ID,用户角色(1:买家 2:卖家 3:管理员 4:超级管理员)',
                         `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '邮箱',
                         PRIMARY KEY (`id`) USING BTREE,
                         UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE,
                         UNIQUE INDEX `uk_phone`(`phone` ASC) USING BTREE,
                         UNIQUE INDEX `uk_email`(`email` ASC) USING BTREE,
                         CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                        `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
                              `perm_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
                                   `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                   `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
                                   PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
                                   CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE,
                                   CONSTRAINT `fk_permission` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;