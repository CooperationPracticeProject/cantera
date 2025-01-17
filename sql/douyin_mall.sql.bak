create database `douyin_mall` default character set utf8mb4;
use `douyin_mall`;


-- 用户表
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                        `username` varchar(50) NOT NULL COMMENT '用户名',
                        `password` varchar(64) NOT NULL COMMENT '密码(加密)',
                        `phone` varchar(20) NOT NULL COMMENT '手机号',
                        `nickname` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
                        `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
                        `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态(0:禁用 1:启用)',
                        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_username` (`username`),
                        UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 权限表
CREATE TABLE `permission` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
                              `name` varchar(50) NOT NULL COMMENT '权限名称',
                              `description` varchar(100) NOT NULL DEFAULT '' COMMENT '权限描述',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 角色表
CREATE TABLE `role` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                        `name` varchar(50) NOT NULL COMMENT '角色名称',
                        `code` varchar(50) NOT NULL COMMENT '角色编码（BUYER:买家, SELLER:卖家, ADMIN:管理员, SUPER_ADMIN:超级管理员）',
                        `description` varchar(100) NOT NULL DEFAULT '' COMMENT '角色描述',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 用户角色关联表
CREATE TABLE `user_role` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `user_id` bigint NOT NULL COMMENT '用户ID',
                             `role_id` bigint NOT NULL COMMENT '角色ID',
                             `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
                             KEY `idx_user_id` (`user_id`),
                             KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 角色权限关联表
CREATE TABLE `role_permission` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `role_id` bigint NOT NULL COMMENT '角色ID',
                                   `permission_id` bigint NOT NULL COMMENT '权限ID',
                                   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`),
                                   KEY `idx_role_id` (`role_id`),
                                   KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- 商品分类表
CREATE TABLE `category` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类ID',
                            `name` varchar(50) NOT NULL COMMENT '分类名称',
                            `parent_id` bigint DEFAULT NULL COMMENT '父分类ID',
                            `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态(0:禁用 1:启用)',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- 商品表
CREATE TABLE `product` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品ID',
                           `seller_id` bigint NOT NULL COMMENT '卖家ID',
                           `category_id` bigint NOT NULL COMMENT '分类ID',
                           `title` varchar(200) NOT NULL COMMENT '商品标题',
                           `description` text COMMENT '商品描述',
                           `price` decimal(10,2) NOT NULL COMMENT '售价',
                           `stock` int NOT NULL COMMENT '库存',
                           `sales` int DEFAULT 0 COMMENT '销量',
                           `main_image` varchar(255) NOT NULL COMMENT '主图URL',
                           `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态(0:下架 1:上架)',
                           `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           PRIMARY KEY (`id`),
                           KEY `idx_seller_id` (`seller_id`),
                           KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 商品图片表
CREATE TABLE `product_image` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '图片ID',
                                 `product_id` bigint NOT NULL COMMENT '商品ID',
                                 `image_url` varchar(255) NOT NULL COMMENT '图片URL',
                                 `sort` int DEFAULT 0 COMMENT '排序',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品图片表';

-- 购物车表
CREATE TABLE `cart` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
                        `user_id` bigint NOT NULL COMMENT '用户ID',
                        `product_id` bigint NOT NULL COMMENT '商品ID',
                        `quantity` int NOT NULL COMMENT '商品数量',
                        `selected` tinyint NOT NULL DEFAULT '1' COMMENT '是否选中(0:未选中 1:选中)',
                        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_user_product` (`user_id`, `product_id`),
                        KEY `idx_user_id` (`user_id`),
                        KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- 订单表
CREATE TABLE `order` (
                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                         `order_no` varchar(50) NOT NULL COMMENT '订单编号',
                         `user_id` bigint NOT NULL COMMENT '用户ID',
                         `seller_id` bigint NOT NULL COMMENT '卖家ID',
                         `total_amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
                         `status` tinyint NOT NULL COMMENT '订单状态(0:待付款 1:待发货 2:待收货 3:已完成 4:已取消)',
                         `address_id` bigint NOT NULL COMMENT '收货地址ID',
                         `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `uk_order_no` (`order_no`),
                         KEY `idx_user_id` (`user_id`),
                         KEY `idx_address_id` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 订单商品表
CREATE TABLE `order_item` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单商品ID',
                              `order_id` bigint NOT NULL COMMENT '订单ID',
                              `product_id` bigint NOT NULL COMMENT '商品ID',
                              `product_title` varchar(200) NOT NULL COMMENT '商品标题',
                              `price` decimal(10,2) NOT NULL COMMENT '购买价格',
                              `quantity` int NOT NULL COMMENT '购买数量',
                              `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              PRIMARY KEY (`id`),
                              KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品表';

-- 收货地址表
CREATE TABLE `address` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '地址ID',
                           `user_id` bigint NOT NULL COMMENT '用户ID',
                           `receiver_name` varchar(50) NOT NULL COMMENT '收货人姓名',
                           `receiver_phone` varchar(20) NOT NULL COMMENT '收货人电话',
                           `receiver_province` varchar(50) NOT NULL COMMENT '省',
                           `receiver_city` varchar(50) NOT NULL COMMENT '市',
                           `receiver_district` varchar(50) NOT NULL COMMENT '区',
                           `receiver_detail_address` varchar(255) NOT NULL COMMENT '详细地址',
                           `is_default` tinyint NOT NULL DEFAULT '0' COMMENT '是否默认(0:否 1:是)',
                           PRIMARY KEY (`id`),
                           KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货地址表';



-- 插入权限数据
INSERT INTO `permission` (`name`, `description`) VALUES
                                                     ('商品管理', '管理商品信息'),
                                                     ('订单管理', '管理订单信息'),
                                                     ('用户管理', '管理用户信息'),
                                                     ('系统设置', '管理系统配置');

-- 插入角色数据
INSERT INTO `role` (`name`, `code`, `description`) VALUES
                                                       ('买家', 'BUYER', '普通用户，购买商品'),
                                                       ('卖家', 'SELLER', '商家，发布商品'),
                                                       ('管理员', 'ADMIN', '管理后台'),
                                                       ('超级管理员', 'SUPER_ADMIN', '系统最高权限');


-- 插入角色权限关联数据
INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES
                                                               (2, 1), -- 卖家有商品管理权限
                                                               (3, 1), -- 管理员有商品管理权限
                                                               (3, 2), -- 管理员有订单管理权限
                                                               (3, 3), -- 管理员有用户管理权限
                                                               (4, 1), -- 超级管理员有商品管理权限
                                                               (4, 2), -- 超级管理员有订单管理权限
                                                               (4, 3), -- 超级管理员有用户管理权限
                                                               (4, 4); -- 超级管理员有系统设置权限

-- 插入用户数据
INSERT INTO `user` (`username`, `password`, `phone`, `nickname`, `avatar`, `status`) VALUES
                                                                                         ('alice', '5f4dcc3b5aa765d61d8327deb882cf99', '13800138001', 'Alice', 'https://example.com/avatars/alice.jpg', 1),
                                                                                         ('bob', '5f4dcc3b5aa765d61d8327deb882cf99', '13800138002', 'Bob', 'https://example.com/avatars/bob.jpg', 1),
                                                                                         ('charlie', '5f4dcc3b5aa765d61d8327deb882cf99', '13800138003', 'Charlie', 'https://example.com/avatars/charlie.jpg', 1),
                                                                                         ('david', '5f4dcc3b5aa765d61d8327deb882cf99', '13800138004', 'David', 'https://example.com/avatars/david.jpg', 1),
                                                                                         ('eve', '5f4dcc3b5aa765d61d8327deb882cf99', '13800138005', 'Eve', 'https://example.com/avatars/eve.jpg', 1);

-- 插入用户角色关联数据
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
                                                   (1, 1), -- Alice 是买家
                                                   (2, 2), -- Bob 是卖家
                                                   (3, 3), -- Charlie 是管理员
                                                   (4, 4), -- David 是超级管理员
                                                   (5, 1); -- Eve 是买家


-- 插入商品分类数据
INSERT INTO `category` (`name`, `parent_id`, `status`) VALUES
                                                           ('手机', NULL, 1), -- 顶级分类
                                                           ('智能手机', 1, 1), -- 子分类
                                                           ('笔记本电脑', NULL, 1), -- 顶级分类
                                                           ('游戏本', 3, 1); -- 子分类

-- 插入商品数据
INSERT INTO `product` (`seller_id`, `category_id`, `title`, `description`, `price`, `stock`, `sales`, `main_image`, `status`) VALUES
                                                                                                                                  (2, 2, 'iPhone 14', '最新款苹果手机', 6999.00, 100, 10, 'https://example.com/images/iphone14.jpg', 1),
                                                                                                                                  (2, 4, 'MacBook Pro 16', '高性能笔记本电脑', 18999.00, 50, 5, 'https://example.com/images/macbookpro16.jpg', 1),
                                                                                                                                  (2, 2, 'Samsung Galaxy S22', '三星旗舰手机', 5999.00, 80, 8, 'https://example.com/images/s22.jpg', 1);

-- 插入商品图片数据
INSERT INTO `product_image` (`product_id`, `image_url`, `sort`) VALUES
                                                                    (1, 'https://example.com/images/iphone14_1.jpg', 1),
                                                                    (1, 'https://example.com/images/iphone14_2.jpg', 2),
                                                                    (2, 'https://example.com/images/macbookpro16_1.jpg', 1),
                                                                    (3, 'https://example.com/images/s22_1.jpg', 1);

-- 插入购物车数据
INSERT INTO `cart` (`user_id`, `product_id`, `quantity`, `selected`) VALUES
                                                                         (1, 1, 1, 1), -- Alice 将 iPhone 14 加入购物车
                                                                         (1, 2, 1, 1), -- Alice 将 MacBook Pro 16 加入购物车
                                                                         (5, 3, 2, 1); -- Eve 将 Samsung Galaxy S22 加入购物车

-- 插入收货地址数据
INSERT INTO `address` (`user_id`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_detail_address`, `is_default`) VALUES
                                                                                                                                                                             (1, 'Alice', '13800138001', '北京市', '北京市', '海淀区', '中关村大街1号', 1),
                                                                                                                                                                             (5, 'Eve', '13800138005', '上海市', '上海市', '浦东新区', '张江高科技园区', 1);

-- 插入订单数据
INSERT INTO `order` (`order_no`, `user_id`, `seller_id`, `total_amount`, `status`, `address_id`) VALUES
                                                                                                     ('202310010001', 1, 2, 25998.00, 1, 1), -- Alice 的订单
                                                                                                     ('202310010002', 5, 2, 11998.00, 0, 2); -- Eve 的订单

-- 插入订单商品数据
INSERT INTO `order_item` (`order_id`, `product_id`, `product_title`, `price`, `quantity`) VALUES
                                                                                              (1, 1, 'iPhone 14', 6999.00, 1), -- Alice 的订单包含 iPhone 14
                                                                                              (1, 2, 'MacBook Pro 16', 18999.00, 1), -- Alice 的订单包含 MacBook Pro 16
                                                                                              (2, 3, 'Samsung Galaxy S22', 5999.00, 2); -- Eve 的订单包含 Samsung Galaxy S22