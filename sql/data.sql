USE `douyin_mall`;

-- 插入用户数据
INSERT INTO `user` (`username`, `password`, `phone`, `nickname`, `avatar`, `status`, `role`, `created_at`, `updated_at`, `email`)
VALUES
    ('zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '13800138000', '张三', 'https://example.com/avatar1.jpg', 1, 1, NOW(), NOW(), 'zhangsan@example.com'),
    ('lisi', 'e10adc3949ba59abbe56e057f20f883e', '13800138001', '李四', 'https://example.com/avatar2.jpg', 1, 2, NOW(), NOW(), 'lisi@example.com'),
    ('wangwu', 'e10adc3949ba59abbe56e057f20f883e', '13800138002', '王五', 'https://example.com/avatar3.jpg', 1, 3, NOW(), NOW(), 'wangwu@example.com');

-- 插入商品分类数据
INSERT INTO `category` (`name`, `parent_id`, `status`) VALUES
                                                           ('电子产品', NULL, 1),
                                                           ('手机', 1, 1),
                                                           ('电脑', 1, 1),
                                                           ('家电', 1, 1),
                                                           ('服装', NULL, 1),
                                                           ('男装', 5, 1),
                                                           ('女装', 5, 1),
                                                           ('鞋子', 5, 1),
                                                           ('配饰', 5, 1),
                                                           ('食品', NULL, 1),
                                                           ('生鲜', 10, 1),
                                                           ('零食', 10, 1),
                                                           ('饮料', 10, 1),
                                                           ('家居', NULL, 1),
                                                           ('家具', 13, 1),
                                                           ('家纺', 13, 1),
                                                           ('厨房用品', 13, 1),
                                                           ('文具', 13, 1),
                                                           ('玩具', NULL, 1),
                                                           ('乐器', 18, 1);


-- 插入商品数据
INSERT INTO `product` (`seller_id`, `category_id`, `title`, `description`, `price`, `stock`, `sales`, `main_image`, `status`, `created_at`)
VALUES
    (1, 1, '苹果 iPhone 14', '最新款 iPhone 14，128GB 存储，深空黑色', 6999.00, 100, 50, 'https://example.com/images/iphone14.jpg', 1, NOW()),
    (2, 2, '华为 Mate 50', '华为最新旗舰机型，256GB 存储，陶瓷白色', 5999.00, 80, 30, 'https://example.com/images/mate50.jpg', 1, NOW()),
    (3, 3, '小米 13 Pro', '小米最新高端机型，512GB 存储，陶瓷黑色', 4999.00, 150, 70, 'https://example.com/images/xiaomi13pro.jpg', 1, NOW()),
    (4, 4, '三星 Galaxy S23', '三星最新旗舰手机，128GB 存储，幻影银色', 7999.00, 60, 20, 'https://example.com/images/galaxys23.jpg', 1, NOW()),
    (5, 5, 'OPPO Find X5 Pro', 'OPPO 最新高端机型，256GB 存储，星空蓝色', 6999.00, 90, 40, 'https://example.com/images/findx5pro.jpg', 1, NOW()),
    (6, 6, 'vivo X90 Pro+', 'vivo 最新旗舰手机，512GB 存储，赛博朋克色', 8999.00, 70, 25, 'https://example.com/images/vivox90proplus.jpg', 1, NOW()),
    (7, 7, '荣耀 Magic4 Pro', '荣耀最新高端机型，256GB 存储，极光蓝色', 7499.00, 110, 60, 'https://example.com/images/magic4pro.jpg', 1, NOW()),
    (8, 8, '魅族 20 Pro', '魅族最新旗舰手机，128GB 存储，冰川白色', 5999.00, 130, 65, 'https://example.com/images/meizu20pro.jpg', 1, NOW()),
    (9, 9, '一加 11 Pro', '一加最新高端机型，256GB 存储，极光紫色', 6999.00, 85, 35, 'https://example.com/images/oneplus11pro.jpg', 1, NOW()),
    (10, 10, '中兴 Axon 20', '中兴最新旗舰手机，128GB 存储，星际黑色', 4999.00, 120, 55, 'https://example.com/images/axon20.jpg', 1, NOW());


-- 插入购物车数据
INSERT INTO `cart_item` (`user_id`, `product_id`, `quantity`, `updated_at`)
VALUES
    (1, 1, 2, NOW()),
    (2, 2, 1, NOW()),
    (3, 3, 3, NOW());


-- 插入订单数据
INSERT INTO `order` (`order_no`, `user_id`, `seller_id`, `total_amount`, `status`, `address_id`, `created_at`)
VALUES
    ('20240207001', 1, 2, 13998.00, 1, 1, NOW()),
    ('20240207002', 2, 3, 3999.00, 2, 2, NOW());



-- 插入订单商品数据
INSERT INTO `order_item` (`order_id`, `product_id`, `product_title`, `price`, `quantity`, `created_at`)
VALUES
    (1, 1, 'iPhone 14', 6999.00, 2, NOW()),
    (2, 3, '海尔冰箱', 3999.00, 1, NOW());



-- 插入商品图片数据
INSERT INTO `product_image` (`product_id`, `image_url`)
VALUES
    (1, 'https://example.com/images/iphone14_1.jpg'),
    (2, 'https://example.com/images/macbookpro_1.jpg'),
    (3, 'https://example.com/images/haier_1.jpg');



-- 插入收货地址数据
INSERT INTO `address` (`user_id`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_detail_address`, `is_default`)
VALUES
    (1, '张三', '13800138000', '北京市', '北京市', '朝阳区', '建国路88号', 1),
    (2, '李四', '13800138001', '上海市', '上海市', '浦东新区', '陆家嘴88号', 1),
    (3, '王五', '13800138002', '广东省', '广州市', '天河区', '天河北路88号', 1);