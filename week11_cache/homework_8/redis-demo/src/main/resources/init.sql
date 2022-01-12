CREATE SCHEMA `test`;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS test.`stock`;
CREATE TABLE test.`stock`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(0) NOT NULL COMMENT '商品id',
  `stock_num` bigint(0) NOT NULL COMMENT '库存数量',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;