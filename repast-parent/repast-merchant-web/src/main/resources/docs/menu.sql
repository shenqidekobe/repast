INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (1, '2017-09-05 17:07:23', '系统管理', 50, NULL, 1, NULL, 'system:manager');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (2, '2017-09-05 17:07:41', '帐号管理', 51, NULL, 1, 1, 'system:account');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (3, '2017-09-27 16:37:54', '客户管理', 10, NULL, 1, NULL, 'user:manager');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (4, '2017-09-27 16:37:54', '白名单管理', 11, NULL, 1, 3, 'user:white');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (5, '2017-09-27 16:37:54', '授权申请管理', 12, NULL, 1, 3, 'user:auth');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (6, '2017-09-27 16:37:54', '商品中心', 1, NULL, 1, NULL, 'goods:manager');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (7, '2017-09-27 16:37:54', '商品管理', 2, NULL, 1, 6, 'goods:goods');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (8, '2017-09-27 16:37:54', '每日商品', 3, NULL, 1, NULL, 'goods:daily');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (9, '2017-09-27 16:37:54', '推荐商品', 4, NULL, 1, 6, 'goods:remcomend');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (10, '2017-09-27 16:37:54', '订单中心', 20, NULL, 1, NULL, 'order:manager');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (11, '2017-09-27 16:37:54', '订单管理', 21, NULL, 1, NULL, 'order:order');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (12, '2017-09-27 16:37:54', '角色管理', 52, NULL, 1, 1, 'system:role');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (13, '2017-09-27 16:37:54', '日志管理', 53, NULL, 1, 1, 'system:logs');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (14, '2017-09-27 17:17:50', '二维码管理', 40, NULL, 1, NULL, 'qrcode:manager');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (15, '2017-09-27 17:17:50', '商家二维码', 41, NULL, 1, 14, 'qrcode:merchant');
INSERT INTO `t_merchant_menu` (`id`, `create_time`, `name`, `sort`, `url`, `merchant_id`, `parent_id`, `permission`) VALUES (16, '2017-09-27 17:17:50', '桌子二维码', 42, NULL, 1, 14, 'qrcode:desk');