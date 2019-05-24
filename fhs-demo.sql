/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.241
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 192.168.0.241:3306
 Source Schema         : fhs-demo

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 18/05/2019 18:07:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_demo_playground
-- ----------------------------
DROP TABLE IF EXISTS `t_demo_playground`;
CREATE TABLE `t_demo_playground`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操场名称',
  `area` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '面积',
  `build_hours` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建造时间',
  `duty` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `duty_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人电话',
  `description` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `picture` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现场照片',
  `is_disable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否启动0启动，1未启动',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_demo_playground
-- ----------------------------
INSERT INTO `t_demo_playground` VALUES ('1d19affa3eb0db9c4a22afeb02e39c89', 'Tom', '123', '2019-05-17', '王二', '13044444444', ',,语文,计算机科学与技术', '', '1', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_playground` VALUES ('5b51fc54901a36a0d1d5c0a90d7d80fa', 'zhouxp', '123', '2019-05-16', '王二', '13044444444', ',,数学,物理', '', '1', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_playground` VALUES ('7a385b8ad51b67a5d8e44b039b95ce9e', 'Tom', '123', '2019-05-31', '王二', '13044444444', ',,数学,计算机科学与技术', '', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_playground` VALUES ('9884ac0f6eb87f5418bba307901a9eb4', 'Test', '123', '2019-05-04', '王二', '18220313214', '主线程', '', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_playground` VALUES ('9afdfaece2f7401eef2a6b9d728b7db6', 'zhouxp', '123', '2019-05-16', '王二', '13044444444', '阿斯顿', '', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_playground` VALUES ('d6c44ffbfb27d9179d1d7649ae4c45d9', 'Tom', '123', '2019-05-16', '王二', '13044444444', '风格化', '', '1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_demo_role
-- ----------------------------
DROP TABLE IF EXISTS `t_demo_role`;
CREATE TABLE `t_demo_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dept` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_demo_role
-- ----------------------------
INSERT INTO `t_demo_role` VALUES ('1', '我是关联查询出来的字段1', '我是关联查询出来的字段2', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_demo_student
-- ----------------------------
DROP TABLE IF EXISTS `t_demo_student`;
CREATE TABLE `t_demo_student`  (
  `student_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文本测试',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典测试',
  `create_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外部关联',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单行文本测试',
  `is_disable` int(32) NULL DEFAULT NULL COMMENT '滑块测试',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '富文本测试',
  `tree_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下拉tree测试',
  `grid_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下拉列表测试',
  `logo` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片上传测试',
  `files` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '多文件上传测试',
  `start_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日期测试',
  `select_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下拉测试',
  `text_a` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '多行文本框',
  `longitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `latitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维度',
  `address` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `provinceid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份id',
  `cityid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市id',
  `areaid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区县id',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_demo_student
-- ----------------------------
INSERT INTO `t_demo_student` VALUES ('205d318f52cf11e98b5fd02788407b5e', '1', '1', '2019-03-30 17:35:26', '1', '2019-03-30 17:50:54', '1', '1', '1121', 1, '<p>请输入富文本1232122</p>', '121', 'EST-1', 'd49d28c74f2c4fa8902390bc0f2adc4d', 'd2231c017ba64a299013c5a67f139dbc', '2019-03-15', '0', '1212', '116.403847', '39.915526', '北京市东城区天安门', '', '', '');
INSERT INTO `t_demo_student` VALUES ('b6cf7133756b11e9b4c9d02788407b5e', '123', '1', '2019-05-13 18:41:47', '1', '2019-05-13 18:41:47', '1', '', '123', 1, '<p>123</p>', '', '', '', '', '', '', '', '', '', '123', '', '', '');

-- ----------------------------
-- Table structure for t_demo_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_demo_subject`;
CREATE TABLE `t_demo_subject`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科目名称',
  `is_disable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否禁用0启动，1为起点',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_demo_subject
-- ----------------------------
INSERT INTO `t_demo_subject` VALUES ('1', '数学', '1', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_subject` VALUES ('2', '语文', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_subject` VALUES ('3', '物理', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_subject` VALUES ('3aa41ecf2c1ea2c0f7469d47b1b74386', '计算机科学与技术', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_subject` VALUES ('3eee110ffc0aba3ac9bb686b732153fe', '英语', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_subject` VALUES ('4', '化学', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_demo_subject` VALUES ('5', '政治', '1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_demo_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_demo_teacher`;
CREATE TABLE `t_demo_teacher`  (
  `teacher_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `teacher_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名字',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `entry_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '入职时间',
  `logo` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '本人照片',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '资料',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '教授课程',
  `is_disable` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '是否禁用0启动，1未启动',
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `update_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_demo_teacher
-- ----------------------------
INSERT INTO `t_demo_teacher` VALUES ('4693ad6375fa11e9b4c9d02788407b5e', '张三', 18, '0', '18220301234', '2019-05-14', '', '<p>是是是</p>', '数学1', '0', '2019-05-14 11:42:18', '2019-05-16 17:13:20', '1', '1');
INSERT INTO `t_demo_teacher` VALUES ('5fa9d41375ff11e9b4c9d02788407b5e', 'Test12', 12, '1', '18220301234', '2019-05-14', '', '<p>请输入富文本12</p>', '物理', '1', '2019-05-14 12:18:48', '2019-05-16 15:55:14', '1', '1');
INSERT INTO `t_demo_teacher` VALUES ('60b6ad4b75fb11e9b4c9d02788407b5e', '李四', 55, '0', '18220301234', '2019-05-14', '', '<p>请输入富文本123</p>', '政治', '0', '2019-05-14 11:50:12', '2019-05-16 17:11:42', '1', '1');
INSERT INTO `t_demo_teacher` VALUES ('cfba89c076b811e9b4c9d02788407b5e', '小三', 12, '1', '18323123213', '2019-05-15', '', '<p>啊</p>', '数学', '0', '2019-05-15 10:26:14', '2019-05-16 16:05:39', '1', '1');
INSERT INTO `t_demo_teacher` VALUES ('eceebfa075fb11e9b4c9d02788407b5e', '王五', 12, '1', '18220301234', '2019-05-02', '', '<p>请输入富123文本</p>', '计算机科学与技术', '1', '2019-05-14 11:54:07', '2019-05-16 17:07:40', '1', '1');

-- ----------------------------
-- Table structure for t_log_admin_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log_admin_operator_log`;
CREATE TABLE `t_log_admin_operator_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id,主键自增',
  `create_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间 yyyy-MM-dd HH:mm:ss SSS',
  `operator_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户id',
  `log_type` int(1) NULL DEFAULT NULL COMMENT '操作类型(0增，1删，2改，3查，4其他)',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求的url',
  `operat_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作描述',
  `req_param` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数',
  `menu_id` int(32) NULL DEFAULT NULL COMMENT '菜单id',
  `network_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `group_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集团编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_log_admin_operator_log
-- ----------------------------
INSERT INTO `t_log_admin_operator_log` VALUES ('001d4bd1b6ba458f8df9abbeafa305da', '2019-03-28 14:47:23 221', '1', 0, '/ms/sysRole/addSysRole', '角色管理:添加角色', '{\"roleId\":[\"\"],\"roleName\":[\"2333\"],\"organizationId\":[\"001\"],\"methods\":[\"v_1011:1\",\"v_1011:2\",\"v_1011:3\",\"v_1011:4\",\"v_1012:1\",\"v_1012:2\",\"v_1012:3\",\"v_1012:4\",\"v_1014:1\",\"v_1014:2\",\"v_1014:3\",\"v_1014:4\",\"v_1015:1\",\"v_1015:2\",\"v_1015:3\",\"v_1015:4\",\"v_1016:1\",\"v_1016:2\",\"v_1016:3\",\"v_1016:4\",\"v_1017:1\",\"v_1017:2\",\"v_1017:3\",\"v_1017:4\",\"v_1019:1\",\"v_1019:2\",\"v_1019:3\",\"v_1019:4\",\"v_1021:1\",\"v_1021:2\",\"v_1021:3\",\"v_1021:4\",\"v_1023:1\",\"v_1023:2\",\"v_1023:3\",\"v_1023:4\",\"v_1040:1\",\"v_1040:2\",\"v_1040:3\",\"v_1040:4\",\"v_1036:1\",\"v_1036:2\",\"v_1036:3\",\"v_1036:4\",\"v_1037:1\",\"v_1037:2\",\"v_1037:3\",\"v_1037:4\",\"v_1038:1\",\"v_1038:2\",\"v_1038:3\",\"v_1038:4\",\"v_1039:1\",\"v_1039:2\",\"v_1039:3\",\"v_1039:4\",\"v_30:1\",\"v_30:2\",\"v_30:3\",\"v_30:4\",\"v_40:1\",\"v_40:2\",\"v_40:3\",\"v_40:4\",\"v_41:1\",\"v_41:2\",\"v_41:3\",\"v_41:4\",\"t_41:1696\",\"v_151:1\",\"v_151:2\",\"v_151:3\",\"v_151:4\",\"t_151:1702\",\"v_781:1\",\"v_781:2\",\"v_781:3\",\"v_781:4\",\"t_781:1860\",\"v_972:1\",\"v_972:2\",\"v_972:3\",\"v_988:1\",\"t_988:1705\",\"v_1041:1\",\"v_1041:2\",\"v_1041:3\",\"v_1041:4\",\"v_1042:1\",\"v_1042:2\",\"v_1043:1\",\"v_1043:2\",\"v_1043:3\",\"v_1043:4\",\"v_1026:1\",\"v_1026:2\",\"v_1026:3\",\"v_1026:4\"],\"isDisable\":[\"0\"],\"dataPermissions\":[\"{\\\"parkId\\\":\\\"0d601eb23f0e11e99571d02788407b5e\\\"}\"],\"dataPermissions_select\":[\"0d601eb23f0e11e99571d02788407b5e\"],\"remark\":[\"\"],\"jackToken\":[\"27bde992cb97480b8e380e9412273325\"]}', 40, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('01e53a29154242f2bd8bb7686608440d', '2019-03-30 16:56:56 925', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1086\"],\"menuName\":[\"表单演示\"],\"namespace\":[\"student\"],\"menuUrl\":[\"/ms/pagex/student_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"1\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"1085\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('04d0c6d89e3346cfa00bc34acce0a3bf', '2019-03-29 12:04:40 111', '62b5870c510c4e9da3f72460001c42fa', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('05006e8b75d24b5eb251fc06de3b15a5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('0a53039d05d94149b42ec4251d80ab84', '2019-03-25 21:01:47 200', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuName\":[\"操作日志\"],\"namespace\":[\"logAdminOperatorLog\"],\"menuUrl\":[\"/page/ms/system/log/log_admin_operator_log_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"9\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"1739e714876445998432566574d0ae75\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('0e9d35a084194ee0901972cc4abe1ef2', '2019-03-25 19:37:18 431', '1', 4, '/ms/index', '登录', '不可见', 0, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('10b2ed91f1db4c59a4d4d91738ce5f34', '2019-05-13 17:55:54 365', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"老北京\"],\"namespace\":[\"aaa\"],\"menuUrl\":[\"page/ms/ffdd/ss/dd\"],\"isDisable\":[\"0\"],\"menuState\":[\"1\"],\"image\":[\"6aac631d411741eb937633230ea5e7b0\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"4\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"1086\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('1230967d17d441a2b79472fe9bc8c02c', '2019-03-28 17:43:18 833', '62b5870c510c4e9da3f72460001c42fa', 1, '/ms/sysMenu/del', '菜单管理:删除', '{\"id\":[\"1048\"],\"jackToken\":[\"27bde992cb97480b8e380e9412273325\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('12efe344868845a7a8e5cf8fb19692d3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('1415afe3cdfb42478312c29740b17dec', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('176a72212b374ff9a56929bd0feaa1d8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('18e652094bb641f2aa50a58b08040175', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('1b87b80d56ca4f1099efbd778d87b1c0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('1bb587405ff64921ba058851e9fde765', '2019-03-29 11:17:35 765', '62b5870c510c4e9da3f72460001c42fa', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('1c3254d366b140b0a456a1a065d353f6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('21efb22714f545b59f0c05ccf5b039e2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('26bfe04009274775b5a2c01d3701bfc9', '2019-03-28 10:49:35 586', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1026\"],\"menuName\":[\"月租户管理\"],\"namespace\":[\"pay_monthly_record\"],\"menuUrl\":[\"/ms/pagex/pay/pay_monthly_record_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"2\"],\"orderIndex\":[\"1\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"1024\"],\"jackToken\":[\"81039867302a4a79ac045a0695544f0d\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('2a98461086a44a2fbd0672fda7458b83', '2019-03-27 12:24:58 983', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('2ccfc77d1e7248668bfc78e191b80e24', '2019-03-30 16:32:26 616', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1085\"],\"menuName\":[\"demo1\"],\"namespace\":[\"demo\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"2\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"0\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('31b0b909b50b46b0ab55c301a4eaa339', '2019-03-25 21:14:09 668', '1', 4, '/ms/index', '登录', '不可见', 0, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('31d46c8069124d3abd5bfb9db58e439f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('331f8d8f85404c858591f3158880037d', '2019-03-30 16:55:51 432', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1086\"],\"menuName\":[\"表单演示\"],\"namespace\":[\"student\"],\"menuUrl\":[\"/pagex/ms/student_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"1\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"1085\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('36f570d69edd49f591e4423933e761bd', '2019-03-25 19:29:16 477', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"5058fef8a0dd4b6291a416232cee40eb\"]}', 972, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('3a8d8ad4598a4e3aae7654f533fe2f13', '2019-03-25 21:32:21 662', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1042\"],\"menuName\":[\"操作日志\"],\"namespace\":[\"logAdminOperatorLog\"],\"menuUrl\":[\"/page/ms/system/log/log_admin_operator_log_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"b2321aae021d4e93b4630126121ae1ff\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"9\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"5dc4da6e41cf497aa0bacce9655a5900\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('3ab3911f596d4bad8e1983b908aab2e6', '2019-03-25 19:29:44 768', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"5058fef8a0dd4b6291a416232cee40eb\"]}', 972, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('3b4c3521358e4bbb8cc8a82334f4fb3e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('3d86f3d5be8f43ea8ad075e8edbd6fac', '2019-03-25 19:37:47 495', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('3e40f8aa859d4406b5c8d63cc1a5254b', '2019-03-25 21:03:21 662', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1042\"],\"menuName\":[\"操作日志\"],\"namespace\":[\"1\"],\"menuUrl\":[\"/page/ms/system/log/log_admin_operator_log_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"9\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"1739e714876445998432566574d0ae75\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('3f50d7e807274059b834f39599840f53', '2019-03-27 14:25:40 703', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('407786f9a6674de1ad820f8e5cd855df', '2019-03-26 14:14:33 186', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('4088a66d40b24b7fb7c4eb4281eb48fb', '2019-03-25 19:47:08 803', '1', 4, '/ms/index', '登录', '不可见', 0, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('412eb33c7ab04a26b2eabbc022e5e6b1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('4171be88b7344f6ab4aa9c0c0c263dda', '2019-03-25 19:33:01 899', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"5058fef8a0dd4b6291a416232cee40eb\"]}', 972, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('463479190f5f4389a5ad951a80d61aba', '2019-03-28 17:43:25 094', '62b5870c510c4e9da3f72460001c42fa', 1, '/ms/sysMenu/del', '菜单管理:删除', '{\"id\":[\"1024\"],\"jackToken\":[\"27bde992cb97480b8e380e9412273325\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('4671eeeb901c48b4b05a498c7d7bbf99', '2019-03-25 21:03:27 313', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1042\"],\"menuName\":[\"操作日志\"],\"namespace\":[\"logAdminOperatorLog\"],\"menuUrl\":[\"/page/ms/system/log/log_admin_operator_log_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"9\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"1739e714876445998432566574d0ae75\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('49317ed6e8724a718c8382e926545040', '2019-03-29 16:12:36 266', '62b5870c510c4e9da3f72460001c42fa', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('49f982ef506547908907e637656a1049', '2019-05-18 17:50:14 201', '1', 3, '//ms/x/student/info/205d318f52cf11e98b5fd02788407b5e', '查看了学生', '{\"id\":\"205d318f52cf11e98b5fd02788407b5e\",\"groupCode\":null}', 1086, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('4a61076a7a5545d3bddc1c7532b1ad38', '2019-03-29 19:10:53 877', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('4c835846cb954908a9c3b48ab40c07ee', '2019-03-25 21:35:48 988', '1', 0, '/ms/sysRole/addSysRole', '角色管理:添加角色', '{\"roleIdE\":[\"\"],\"roleName\":[\"1231\"],\"organizationId\":[\"001\"],\"methods\":[\"v_1011:1\",\"v_1011:2\",\"v_1011:3\",\"v_1011:4\",\"v_1012:1\",\"v_1012:2\",\"v_1012:3\",\"v_1012:4\",\"v_1014:1\",\"v_1014:2\",\"v_1014:3\",\"v_1014:4\",\"v_1015:1\",\"v_1015:2\",\"v_1015:3\",\"v_1015:4\",\"v_1016:1\",\"v_1016:2\",\"v_1016:3\",\"v_1016:4\",\"v_1017:1\",\"v_1017:2\",\"v_1017:3\",\"v_1017:4\",\"v_1019:1\",\"v_1019:2\",\"v_1019:3\",\"v_1019:4\",\"v_1021:1\",\"v_1021:2\",\"v_1021:3\",\"v_1021:4\",\"v_1023:1\",\"v_1023:2\",\"v_1023:3\",\"v_1023:4\",\"v_1040:1\",\"v_1040:2\",\"v_1040:3\",\"v_1040:4\",\"v_1036:1\",\"v_1036:2\",\"v_1036:3\",\"v_1036:4\",\"v_1037:1\",\"v_1037:2\",\"v_1037:3\",\"v_1037:4\",\"v_1038:1\",\"v_1038:2\",\"v_1038:3\",\"v_1038:4\",\"v_1039:1\",\"v_1039:2\",\"v_1039:3\",\"v_1039:4\",\"v_30:1\",\"v_30:2\",\"v_30:3\",\"v_30:4\",\"v_40:1\",\"v_40:2\",\"v_40:3\",\"v_40:4\",\"v_41:1\",\"v_41:2\",\"v_41:3\",\"v_41:4\",\"t_41:1696\",\"v_151:1\",\"v_151:2\",\"v_151:3\",\"v_151:4\",\"t_151:1702\",\"v_781:1\",\"v_781:2\",\"v_781:3\",\"v_781:4\",\"t_781:1860\",\"v_972:1\",\"v_972:2\",\"v_972:3\",\"v_988:1\",\"t_988:1705\",\"v_1041:1\",\"v_1041:2\",\"v_1041:3\",\"v_1041:4\",\"v_1042:1\",\"v_1042:2\",\"v_1043:1\",\"v_1043:2\",\"v_1043:3\",\"v_1043:4\",\"v_1026:1\",\"v_1026:2\",\"v_1026:3\",\"v_1026:4\"],\"isDisable\":[\"0\"],\"remark\":[\"12123\"],\"jackToken\":[\"5dc4da6e41cf497aa0bacce9655a5900\"]}', 40, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('4c939e3cec784594bac516658cfeb989', '2019-03-30 16:53:58 296', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"表单演示\"],\"namespace\":[\"student\"],\"menuUrl\":[\"/pagex/student_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"1\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"1085\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('4f43799da8934a33919bc4e485b89843', '2019-03-29 12:21:57 809', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('50fda074205a42fbb8fa00d9c2aa50c2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('557bc7f8b1004b03851cefd192e22b35', '2019-03-30 16:38:50 047', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1085\"],\"menuName\":[\"demo12\"],\"namespace\":[\"demo\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"2\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"0\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('6009072f142b48c193d1b29c85f2105f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('63ea9eace13048ab86e4181f081549c3', '2019-03-25 19:31:06 850', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"5058fef8a0dd4b6291a416232cee40eb\"]}', 972, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('6633d34335e64032b2ce27b99d238025', '2019-03-25 19:32:29 700', '1', 2, '/ms/sysMenuPermission/update', '菜单权限管理:更新', '{\"permissionName\":[\"删除\"],\"permissionId\":[\"1956\"],\"method\":[\"del22\"],\"menuId\":[\"1042\"],\"permissionType\":[\"4\"],\"isDisable\":[\"0\"],\"jackToken\":[\"5058fef8a0dd4b6291a416232cee40eb\"]}', 972, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('664e0c5687ae4f2da90596e4d38a7331', '2019-03-25 21:12:52 973', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1043\"],\"menuName\":[\"菜单权限\"],\"namespace\":[\"sysMenuPermission\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"1\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"100\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"d8fb6a1caf0b49de9e187fff293ee76e\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('671fbcee2a494e32913d7fe7444e0529', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('67523e9ca4b94e6992390e8600ebe09f', '2019-03-26 19:41:40 610', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('686067fb5b5e4acb857e2d301e4186e6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('6dc2c21b94c54717a7a0612363dadd71', '2019-03-30 15:52:42 590', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1041\"],\"menuName\":[\"机构管理新(PAGEXtree未完成)\"],\"namespace\":[\"organization\"],\"menuUrl\":[\"/ms/pagex/organization_tree.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"5\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('6e62b52b000b41b4aa2876b8184ede87', '2019-03-28 10:29:27 332', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('6ec3bb596c1c41c9b9746a1e3f449cc2', '2019-03-27 12:33:03 629', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('76a753b2ffec4bf790958778de5f38b6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('779fb81cf83c49b0a489f46f683a5e9f', '2019-03-30 18:52:41 530', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"停车场出入口管理\"],\"namespace\":[\"parking_port\"],\"menuUrl\":[\"/ms/pagex/parking_port_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"4\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"1085\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('799caf5bd0ce4fef86c60ae1cc2ce6d8', '2019-03-26 22:01:47 423', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('79bfababc39643c39134953c493f0ffd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('7acebdf2ea1146c5a12976beff87c6a3', '2019-03-29 14:51:52 348', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('7d8f3f1c47fb4048ba73718ce85b6997', '2019-03-30 16:31:57 661', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"demo\"],\"namespace\":[\"demo\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"2\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"0\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('7f63a2f9811342a595725391541c23ce', '2019-03-29 09:46:38 015', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('7fc401d909964e74b137a6afbcc40cae', '2019-03-25 19:21:00 794', '1', 1, '/ms/sysMenu/del', '菜单管理:删除', '{\"id\":[\"1025\"],\"jackToken\":[\"5058fef8a0dd4b6291a416232cee40eb\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('811f0843016a4155aa20ee24e9d62ee8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('839933204de940e089ee698916adb71d', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('859658796d204196a3b271d89f15ab28', '2019-03-26 19:53:44 981', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('88a50bc1a5ed4e8392c59f947056e0b4', '2019-03-25 21:12:13 539', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"d8fb6a1caf0b49de9e187fff293ee76e\"]}', 972, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('899d8050706847cf9dbb54ee393fb7ad', '2019-03-28 09:53:48 662', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('89a5e027537a4cef9a7f215a4836e2a4', '2019-05-13 18:43:36 849', '1', 1, '/ms/sysMenu/del', '菜单管理:删除', '{\"id\":[\"1090\"],\"jackToken\":[\"null\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('8aced455f0c948828c1a3ce01f4561a2', '2019-05-18 17:49:54 581', '1', 2, '//ms/x/parking/update/cf0a05d652db11e98b5fd02788407b5e', '更新了停车场', '{\"jackToken\":\"\",\"img\":\"b24d46ad28db400488345d683ab9139e\",\"businessUnit\":\"\",\"rightPhone\":\"\",\"parkName\":\"测试停车场\",\"latitude\":\"39.909652\",\"description\":\"1\",\"secret\":\"93629636204727632726423198178145\",\"provinceid\":\"\",\"isSync\":\"0\",\"parkType\":\"\",\"isDisable\":\"0\",\"areaid\":\"\",\"businessCharge\":\"\",\"freeTime\":\"1\",\"parentParkId\":\"0\",\"blackListRemark\":\"1\",\"serviceLife\":\"\",\"id\":\"cf0a05d652db11e98b5fd02788407b5e\",\"longitude\":\"116.404177\",\"groupCode\":null,\"zoomRate\":\"\",\"operationWay\":\"\",\"onlyVip\":\"\",\"address\":\"北京市东城区天安门广场\",\"updateUser\":\"1\",\"cityid\":\"\",\"spaceCount\":\"1\",\"url\":\"\",\"areaCount\":\"\",\"exitTime\":\"1\",\"oneDayMaxFee\":\"1\",\"rightCharge\":\"\",\"rightUnit\":\"\",\"businessPhone\":\"\"}', 1088, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('8c14cbe86ba048559a62c6475bde3b44', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('8ce4f7bafb36495996a485064ca5e715', '2019-03-30 18:52:02 885', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"停车场管理\"],\"namespace\":[\"parking\"],\"menuUrl\":[\"/ms/pagex/parking_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"3\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"1085\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('8d846dfea5a94eef881e746ac857da5a', '2019-03-28 17:43:13 355', '62b5870c510c4e9da3f72460001c42fa', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"1\"],\"namespace\":[\"1\"],\"menuUrl\":[\"1\"],\"isDisable\":[\"0\"],\"menuState\":[\"1\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"1\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"1024\"],\"jackToken\":[\"27bde992cb97480b8e380e9412273325\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('8da4bb07713e448abab38d5d25c8b949', '2019-03-28 16:37:47 209', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('8f36c9b2734e492fab2fe5ba393bc958', '2019-03-26 10:46:53 023', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('8f9dab9a6b7147fb9ddb138f76cfb1fe', '2019-03-29 11:16:08 826', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('8fbd60e0cd904ed8a333711da14b010a', '2019-03-28 14:51:17 858', '1', 0, '/ms/sysUser/addUser', '后管用户:添加后台用户', '{\"jackToken\":[\"27bde992cb97480b8e380e9412273325\"],\"address\":[\"\"],\"sex\":[\"\"],\"mobile\":[\"13455555555\"],\"cityId\":[\"440300\"],\"isAdmin\":[\"0\"],\"userName\":[\"王磊\"],\"roleList\":[\"1\",\"2\"],\"userId\":[\"\"],\"provinceId\":[\"440000\"],\"organizationId\":[\"001\"],\"isDisable\":[\"0\"],\"areaId\":[\"\"],\"passWd\":[\"\"],\"userLoginName\":[\"shuaizai88\"],\"userNameShow\":[\"王磊\"],\"email\":[\"\"]}', 41, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('9018a589d1874a028ccf162f3480d962', '2019-03-25 19:38:38 967', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"15e12171f1924454a30eb2a56f8ddf0f\"]}', 972, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('9200789f0ac54654baa37251515d6408', '2019-03-25 19:28:37 385', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"5058fef8a0dd4b6291a416232cee40eb\"]}', 972, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('954c292a5a82490ca1f1bd3daea1cb52', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('96045afc863f4f8cb7dd6199d4010837', '2019-03-28 10:47:51 502', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"月租户管理\"],\"namespace\":[\"#\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"2\"],\"orderIndex\":[\"5\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"\"],\"jackToken\":[\"81039867302a4a79ac045a0695544f0d\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('972c09f597ac4febbaa477b636c16f3b', '2019-03-29 16:12:12 850', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('9a92505a6c614068aa907969cafb3d8d', '2019-03-25 19:38:16 821', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"15e12171f1924454a30eb2a56f8ddf0f\"]}', 972, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('9d0186054caf479493edf559f47b11d1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('9dbcddd65efb460a8a09cc25e283495a', '2019-03-25 21:32:02 541', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1042\"],\"menuName\":[\"操作日志\"],\"namespace\":[\"logAdminOperatorLog\"],\"menuUrl\":[\"/page/ms/system/log/log_admin_operator_log_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"9a5457114e14485a846b5a7bf04f7858\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"9\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"5dc4da6e41cf497aa0bacce9655a5900\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('9e89743e3b364aeab5470db00cd8d264', '2019-05-13 18:44:57 588', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"余姚\"],\"namespace\":[\"aaa\"],\"menuUrl\":[\"sss/sss/sss/sss\"],\"isDisable\":[\"1\"],\"menuState\":[\"1\"],\"image\":[\"f1d1ed4d2ec14ef49d31c7180cd98498\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"4\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"1085\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('a2a1afe437704d3fbd4864b106c38c98', '2019-03-29 17:17:57 912', '62b5870c510c4e9da3f72460001c42fa', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('a3cd110241084c4988d9053c971d2ff6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('a635644d4e55446c8516fdd0af76623a', '2019-05-13 18:48:08 768', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"出入管理\"],\"namespace\":[\"aaa\"],\"menuUrl\":[\"aaa/aa/a/s\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"7622fa29d3ed4112a2cad468051a7489\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"4\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"1085\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('a65b0dc05a9d4c80aeea21e59506ac8b', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('a70436c29b0645f680c1a3fb57301dbd', '2019-03-25 21:01:27 241', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuName\":[\"操作日志\"],\"namespace\":[\"logAdminOperatorLog\"],\"menuUrl\":[\"/page/ms/system/log/log_admin_operator_log_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"9\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"1739e714876445998432566574d0ae75\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('a82139ea9a4e400cad733037f088f723', '2019-05-18 18:02:14 086', '1', 4, '/ms/index', '登录', '不可见', 0, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('a9126002df634634b97f6451d821c237', '2019-05-18 17:49:53 386', '1', 3, '//ms/x/parking/info/cf0a05d652db11e98b5fd02788407b5e', '查看了停车场', '{\"id\":\"cf0a05d652db11e98b5fd02788407b5e\",\"groupCode\":null}', 1088, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('a9cc61aad56a4ad983762e77586bc845', '2019-03-25 19:30:46 667', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"5058fef8a0dd4b6291a416232cee40eb\"]}', 972, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('aacf369bb3bd4a88a4f8145ba6ed8a0e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ab6168657afd45c38830263eb3719205', '2019-03-25 21:15:01 078', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"5dc4da6e41cf497aa0bacce9655a5900\"]}', 972, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ad410dd1a41e4a92bfe66a2fd94797c3', '2019-03-25 21:04:04 826', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ae053227ec0344d6b21df64443e44f7c', '2019-05-13 18:43:14 252', '1', 1, '/ms/sysMenu/del', '菜单管理:删除', '{\"id\":[\"1092\"],\"jackToken\":[\"null\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('aed8d5d87432423cae323dfe0ccace68', '2019-05-13 18:17:22 235', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"1111\"],\"namespace\":[\"111\"],\"menuUrl\":[\"page/ms/index/index/aaa\"],\"isDisable\":[\"0\"],\"menuState\":[\"1\"],\"image\":[\"425533f6bb4449b9b522aff805cd46cf\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"4\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"30\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('b0d25953912a4c72bd6fd1478d7597a5', '2019-03-26 12:28:20 060', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('b0e1753d39264c78a80fd31fbb7c3826', '2019-05-18 17:29:57 216', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1085\"],\"menuName\":[\"demo\"],\"namespace\":[\"demo\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"2\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"menuType\":[\"2\"],\"fatherMenuId\":[\"0\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('b474430d1d1e4374ba9a1dafd70f1614', '2019-03-25 21:03:13 798', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1042\"],\"menuName\":[\"操作日志\"],\"namespace\":[\"1\"],\"menuUrl\":[\"/page/system/log/log_admin_operator_log_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"9\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"1739e714876445998432566574d0ae75\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('b5c83b5ed3bf4172937cda79f289d7e7', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('b71d9612c49742a1b70f10f93d0fbbe3', '2019-03-29 14:07:31 139', '62b5870c510c4e9da3f72460001c42fa', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('b8470db9752e4d16973edf8f8250b564', '2019-03-28 10:42:34 463', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"月租户管理\"],\"namespace\":[\"pay_monthly_record\"],\"menuUrl\":[\"/ms/pagex/pay/pay_monthly_record_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"2\"],\"orderIndex\":[\"5\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"\"],\"jackToken\":[\"81039867302a4a79ac045a0695544f0d\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ba41d720323c45a7bd54e1aa30acbab7', '2019-03-30 17:40:24 473', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"隐藏菜单\"],\"namespace\":[\"role\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"1\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"2\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"1085\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('c1868876b3ce45d498dacf86bb2f965d', '2019-03-30 16:52:29 287', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1085\"],\"menuName\":[\"demo\"],\"namespace\":[\"demo\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"2\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"0\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('c311e1c5784e4e1d847c4160f0e6cc06', '2019-03-30 16:41:16 358', '1', 1, '/ms/sysMenu/del', '菜单管理:删除', '{\"id\":[\"1041\"],\"jackToken\":[\"null\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('c48769b9a18445c8b96fa0c1a9f0f24b', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('c52658fc189c4cffa5414b66cca50d53', '2019-03-29 20:48:24 714', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('c5c6f61f46fb4a0f90c3d18c0d722e22', '2019-03-27 16:10:54 159', '1', 4, '//ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('c80f507fe4824733aa4173db502fd362', '2019-03-29 16:41:57 792', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('c8d40ff062af4d8c8fde1c7cca0ebeac', '2019-03-25 19:46:20 619', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"15e12171f1924454a30eb2a56f8ddf0f\"]}', 972, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ca3d419704e24917a7cba53520b01ebe', '2019-03-25 19:38:00 392', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1956\"],\"jackToken\":[\"15e12171f1924454a30eb2a56f8ddf0f\"]}', 972, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('cb32bf53cb504c479ffdd6de7c1176d0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('cbf4140fe0d440cb9bf30271a2c21063', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ccc6e47ed3b54706b890ecb887066060', '2019-03-25 19:32:35 133', '1', 2, '/ms/sysMenuPermission/update', '菜单权限管理:更新', '{\"permissionName\":[\"删除\"],\"permissionId\":[\"1956\"],\"method\":[\"del\"],\"menuId\":[\"1042\"],\"permissionType\":[\"4\"],\"isDisable\":[\"0\"],\"jackToken\":[\"5058fef8a0dd4b6291a416232cee40eb\"]}', 972, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ce6835de760c4774b71dda10300e60cd', '2019-03-30 16:38:00 019', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1085\"],\"menuName\":[\"demo1\"],\"namespace\":[\"demo\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"2\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"0\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ce709ff864344af1900ac47b4f4ab519', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('d43828e37d644f50a7349c444843a933', '2019-03-28 14:06:22 685', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('d62bab719570440284c2ca4a371ce7e5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('d8ea3a33542a435a8b3b8f462846f53c', '2019-03-28 10:38:07 858', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"月租户管理\"],\"namespace\":[\"pay_monthly_record\"],\"menuUrl\":[\"/ms/pagex/pay/pay_monthly_record_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"2\"],\"orderIndex\":[\"4\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"\"],\"jackToken\":[\"81039867302a4a79ac045a0695544f0d\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('da3960fd0d374cb2a80d22f3773a2cd8', '2019-03-28 16:55:58 244', '1', 0, '/ms/sysMenu/add', '菜单管理:添加', '{\"menuName\":[\"test\"],\"namespace\":[\"testte\"],\"menuUrl\":[\"tttt\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"1\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"\"],\"jackToken\":[\"0805f70cd15743a5ae45ad8ea59d5214\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('db67401ab90c4eab9e57221df6113c89', '2019-03-25 21:27:36 721', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1042\"],\"menuName\":[\"操作日志\"],\"namespace\":[\"logAdminOperatorLog\"],\"menuUrl\":[\"/page/ms/system/log/log_admin_operator_log_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"9\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"5dc4da6e41cf497aa0bacce9655a5900\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('dc97f9efe9bb47e69946d7b363d009d1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('dcc323dac4584e289d65548845d11b3f', '2019-03-27 20:12:03 403', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('dcd8dfc85af748d9a961295d88ea6ab4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('deccb87267084847a38a36133af5ae26', '2019-03-25 21:27:16 353', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1042\"],\"menuName\":[\"操作日志\"],\"namespace\":[\"logAdminOperatorLog\"],\"menuUrl\":[\"/page/ms/system/log/log_admin_operator_log_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"9\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"5dc4da6e41cf497aa0bacce9655a5900\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ded2ac62a1ad4629bf2ad20e4cbdc388', '2019-03-29 18:10:20 615', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1026\"],\"menuName\":[\"月租户管理\"],\"namespace\":[\"pay_monthly_record\"],\"menuUrl\":[\"/ms/pagex/pay/pay_monthly_record_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"2\"],\"orderIndex\":[\"5\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"1033\"],\"jackToken\":[\"3091bb8e52b74b9290406623c985ad52\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('e03516af80104a918bacc05d28e88358', '2019-03-25 21:31:23 896', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1042\"],\"menuName\":[\"操作日志\"],\"namespace\":[\"logAdminOperatorLog\"],\"menuUrl\":[\"/page/ms/system/log/log_admin_operator_log_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"789281fd3a3f49a1a76d9d189f675e8c\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"9\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"29\"],\"jackToken\":[\"5dc4da6e41cf497aa0bacce9655a5900\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('e09eb1bab539413fa6ec2f47e24dff99', '2019-03-30 16:32:42 615', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1085\"],\"menuName\":[\"demo12\"],\"namespace\":[\"demo\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"2\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"0\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('e16437b07e2e4c258f26f21a24370d88', '2019-03-30 16:39:46 199', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1085\"],\"menuName\":[\"demo121\"],\"namespace\":[\"demo\"],\"menuUrl\":[\"#\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"2\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"0\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('e44cb838f41a4df486665bc7830a29f8', '2019-03-30 18:53:00 205', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1088\"],\"menuName\":[\"停车场管理\"],\"namespace\":[\"parking\"],\"menuUrl\":[\"/ms/pagex/parking_list.jsp?parent_park_id=0\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"1\"],\"orderIndex\":[\"3\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc009\"],\"fatherMenuId\":[\"1085\"],\"jackToken\":[\"\"]}', 30, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ebd5e1bf119c48e2acf22e41c2a88de8', '2019-03-25 19:20:33 622', '1', 1, '/ms/sysMenu/del', '菜单管理:删除', '{\"id\":[\"1007\"],\"jackToken\":[\"5058fef8a0dd4b6291a416232cee40eb\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('ee7ffa1a922c45e28eb7306912cc32e9', '2019-03-27 12:41:40 599', '1', 2, '/ms/sysMenu/update', '菜单管理:更新', '{\"menuId\":[\"1016\"],\"menuName\":[\"临时收费时段\"],\"namespace\":[\"park_temp_segment\"],\"menuUrl\":[\"/ms/pagex/park_temp_segment_list.jsp\"],\"isDisable\":[\"0\"],\"menuState\":[\"0\"],\"image\":[\"\"],\"serverNameId\":[\"2\"],\"orderIndex\":[\"3\"],\"systemId\":[\"55ff598ae42845d4b51ec2bc71bbc008\"],\"fatherMenuId\":[\"1013\"],\"jackToken\":[\"078fad71000e4a07a2187816c41cf2a7\"]}', 30, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('f302829c0ef5438bb0b7cf8e06fea488', '2019-03-26 14:02:32 200', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('f5a55a72f30e4c0493a7e7554c004c7c', '2019-03-27 16:21:05 615', '1', 4, '/ms/index', '登录', '不可见', 0, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('f958106a81a843bb947bb15361b749e4', '2019-03-25 21:15:04 490', '1', 1, '/ms/sysMenuPermission/del', '菜单权限管理:删除', '{\"id\":[\"1955\"],\"jackToken\":[\"5dc4da6e41cf497aa0bacce9655a5900\"]}', 972, '127.0.0.1', NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('fa673b86049047ccb5e3081521b998b0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('fbef48a359924b69a1ce32c76c94f592', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_log_admin_operator_log` VALUES ('fda91f542f394564b65669a29aeea84f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_park_parking
-- ----------------------------
DROP TABLE IF EXISTS `t_park_parking`;
CREATE TABLE `t_park_parking`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '停车场ID',
  `park_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '停车场名称',
  `area_count` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域编码',
  `space_count` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '本停车场停车位数',
  `zoom_rate` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '放大率',
  `longitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '停车场经度  停车场中心的',
  `latitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '停车场纬度  停车场中心的',
  `address` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车场的具体位置，哪条街，哪个路',
  `is_disable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有效 1 有效 0无效',
  `operation_way` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营模式',
  `business_unit` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营单位',
  `park_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '停车场类型',
  `service_life` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用年限',
  `right_unit` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产权单位',
  `right_charge` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产权负责人',
  `right_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产权负责人电话',
  `business_charge` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营负责人',
  `business_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营负责人电话',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `network_state` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '停车场网络状态，1正常 ，0 断网',
  `url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '停车场网络地址',
  `img` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '停车场图片',
  `reserved_lot_num` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留预约车位数',
  `only_vip` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否仅月租 0：否 1：是',
  `dept_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属停车场',
  `parent_park_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父停车场编号',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后修改人',
  `provinceid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `cityid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `areaid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区县',
  `secret` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密钥',
  `black_list_remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '黑名单提示',
  `free_time` int(10) NULL DEFAULT NULL COMMENT '免费时长:分钟',
  `exit_time` int(10) NULL DEFAULT NULL COMMENT '提前付费后出场限制时间',
  `is_sync` int(1) NULL DEFAULT NULL COMMENT '是否下发 0 未下发 1已下发',
  `one_day_max_fee` double NULL DEFAULT NULL COMMENT '一天最多收费多少钱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '停车场表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_park_parking
-- ----------------------------
INSERT INTO `t_park_parking` VALUES ('cf0a05d652db11e98b5fd02788407b5e', '测试停车场', '', '1', '', '116.404177', '39.909652', '北京市东城区天安门广场', '0', '', '', '', '', '', '', '', '', '', '2019-03-30 19:06:13', '2019-05-18 17:50:09', '1', '1', '', 'b24d46ad28db400488345d683ab9139e', NULL, '', NULL, '0', '1', '1', '', '', '', '93629636204727632726423198178145', '1', 1, 1, 0, 1);

-- ----------------------------
-- Table structure for t_park_parking_port
-- ----------------------------
DROP TABLE IF EXISTS `t_park_parking_port`;
CREATE TABLE `t_park_parking_port`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `park_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '所在停车场',
  `real_location` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '实际位置',
  `port_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `port_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '进出口的IP地址:拍照摄像头ip',
  `longitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度 进出口点的 以百度为准',
  `latitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度 进出口点的',
  `port_fun_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '使用功能类别(0:停车场内;1:停车场入口;2:停车场出口)',
  `is_disable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '出入口控制状态(0:正常;1:异常)',
  `control_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '控制类型',
  `create_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `port_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '进出口名称',
  `img` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出入口图片',
  `is_main` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '(1-主闸机, 0-副闸机)来自于字典表',
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信息更新人',
  `camera_port` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摄像头端口',
  `is_sync` int(1) NULL DEFAULT NULL COMMENT '0 未下发 1 已下发',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '进出口管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_park_parking_port
-- ----------------------------
INSERT INTO `t_park_parking_port` VALUES ('7abd35d427a455f589a8613805bb09ea', 'cf0a05d652db11e98b5fd02788407b5e', '11', '1', '192.168.0.0', NULL, NULL, '0', '0', '0', NULL, '2019-05-16 16:26:56', '', '1', '测试端口', '', '1', '1', '', 0);
INSERT INTO `t_park_parking_port` VALUES ('e4c14be052db11e98b5fd02788407b5e', 'cf0a05d652db11e98b5fd02788407b5e', '11', '1', '192.168.0.0', NULL, NULL, '0', '1', '0', '2019-03-30 19:06:49', '2019-05-16 16:27:06', '', '1', '测试端口', '1', '0', '1', '', 0);

-- ----------------------------
-- Table structure for t_service_area
-- ----------------------------
DROP TABLE IF EXISTS `t_service_area`;
CREATE TABLE `t_service_area`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域主键',
  `area_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称',
  `area_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域代码',
  `area_short` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域简称',
  `area_is_hot` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否热门(0:否、1:是)',
  `area_sequence` int(11) NULL DEFAULT NULL COMMENT '区域序列',
  `area_parent_id` int(11) NULL DEFAULT NULL COMMENT '上级主键',
  `init_date` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初始时间',
  `init_addr` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初始地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`area_parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 900001 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_service_area
-- ----------------------------
INSERT INTO `t_service_area` VALUES (110000, '北京', 'Beijing', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110100, '北京市', 'Beijing', NULL, NULL, NULL, 110000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110101, '东城区', 'Dongcheng', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110102, '西城区', 'Xicheng', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110105, '朝阳区', 'Chaoyang', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110106, '丰台区', 'Fengtai', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110107, '石景山区', 'Shijingshan', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110108, '海淀区', 'Haidian', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110109, '门头沟区', 'Mentougou', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110111, '房山区', 'Fangshan', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110112, '通州区', 'Tongzhou', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110113, '顺义区', 'Shunyi', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110114, '昌平区', 'Changping', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110115, '大兴区', 'Daxing', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110116, '怀柔区', 'Huairou', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110117, '平谷区', 'Pinggu', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110228, '密云县', 'Miyun', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (110229, '延庆县', 'Yanqing', NULL, NULL, NULL, 110100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120000, '天津', 'Tianjin', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120100, '天津市', 'Tianjin', NULL, NULL, NULL, 120000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120101, '和平区', 'Heping', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120102, '河东区', 'Hedong', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120103, '河西区', 'Hexi', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120104, '南开区', 'Nankai', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120105, '河北区', 'Hebei', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120106, '红桥区', 'Hongqiao', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120110, '东丽区', 'Dongli', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120111, '西青区', 'Xiqing', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120112, '津南区', 'Jinnan', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120113, '北辰区', 'Beichen', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120114, '武清区', 'Wuqing', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120115, '宝坻区', 'Baodi', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120116, '滨海新区', 'Binhaixinqu', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120221, '宁河县', 'Ninghe', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120223, '静海县', 'Jinghai', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (120225, '蓟县', 'Jixian', NULL, NULL, NULL, 120100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130000, '河北省', 'Hebei', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130100, '石家庄市', 'Shijiazhuang', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130102, '长安区', 'Chang\'an', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130104, '桥西区', 'Qiaoxi', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130105, '新华区', 'Xinhua', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130107, '井陉矿区', 'Jingxingkuangqu', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130108, '裕华区', 'Yuhua', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130109, '藁城区', 'Gaocheng', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130110, '鹿泉区', 'Luquan', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130111, '栾城区', 'Luancheng', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130121, '井陉县', 'Jingxing', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130123, '正定县', 'Zhengding', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130125, '行唐县', 'Xingtang', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130126, '灵寿县', 'Lingshou', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130127, '高邑县', 'Gaoyi', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130128, '深泽县', 'Shenze', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130129, '赞皇县', 'Zanhuang', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130130, '无极县', 'Wuji', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130131, '平山县', 'Pingshan', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130132, '元氏县', 'Yuanshi', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130133, '赵县', 'Zhaoxian', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130181, '辛集市', 'Xinji', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130183, '晋州市', 'Jinzhou', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130184, '新乐市', 'Xinle', NULL, NULL, NULL, 130100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130200, '唐山市', 'Tangshan', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130202, '路南区', 'Lunan', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130203, '路北区', 'Lubei', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130204, '古冶区', 'Guye', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130205, '开平区', 'Kaiping', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130207, '丰南区', 'Fengnan', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130208, '丰润区', 'Fengrun', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130209, '曹妃甸区', 'Caofeidian', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130223, '滦县', 'Luanxian', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130224, '滦南县', 'Luannan', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130225, '乐亭县', 'Laoting', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130227, '迁西县', 'Qianxi', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130229, '玉田县', 'Yutian', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130281, '遵化市', 'Zunhua', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130283, '迁安市', 'Qian\'an', NULL, NULL, NULL, 130200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130300, '秦皇岛市', 'Qinhuangdao', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130302, '海港区', 'Haigang', NULL, NULL, NULL, 130300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130303, '山海关区', 'Shanhaiguan', NULL, NULL, NULL, 130300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130304, '北戴河区', 'Beidaihe', NULL, NULL, NULL, 130300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130321, '青龙满族自治县', 'Qinglong', NULL, NULL, NULL, 130300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130322, '昌黎县', 'Changli', NULL, NULL, NULL, 130300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130323, '抚宁县', 'Funing', NULL, NULL, NULL, 130300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130324, '卢龙县', 'Lulong', NULL, NULL, NULL, 130300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130400, '邯郸市', 'Handan', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130402, '邯山区', 'Hanshan', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130403, '丛台区', 'Congtai', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130404, '复兴区', 'Fuxing', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130406, '峰峰矿区', 'Fengfengkuangqu', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130421, '邯郸县', 'Handan', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130423, '临漳县', 'Linzhang', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130424, '成安县', 'Cheng\'an', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130425, '大名县', 'Daming', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130426, '涉县', 'Shexian', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130427, '磁县', 'Cixian', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130428, '肥乡县', 'Feixiang', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130429, '永年县', 'Yongnian', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130430, '邱县', 'Qiuxian', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130431, '鸡泽县', 'Jize', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130432, '广平县', 'Guangping', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130433, '馆陶县', 'Guantao', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130434, '魏县', 'Weixian', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130435, '曲周县', 'Quzhou', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130481, '武安市', 'Wu\'an', NULL, NULL, NULL, 130400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130500, '邢台市', 'Xingtai', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130502, '桥东区', 'Qiaodong', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130503, '桥西区', 'Qiaoxi', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130521, '邢台县', 'Xingtai', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130522, '临城县', 'Lincheng', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130523, '内丘县', 'Neiqiu', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130524, '柏乡县', 'Baixiang', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130525, '隆尧县', 'Longyao', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130526, '任县', 'Renxian', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130527, '南和县', 'Nanhe', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130528, '宁晋县', 'Ningjin', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130529, '巨鹿县', 'Julu', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130530, '新河县', 'Xinhe', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130531, '广宗县', 'Guangzong', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130532, '平乡县', 'Pingxiang', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130533, '威县', 'Weixian', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130534, '清河县', 'Qinghe', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130535, '临西县', 'Linxi', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130581, '南宫市', 'Nangong', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130582, '沙河市', 'Shahe', NULL, NULL, NULL, 130500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130600, '保定市', 'Baoding', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130602, '新市区', 'Xinshi', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130603, '北市区', 'Beishi', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130604, '南市区', 'Nanshi', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130621, '满城县', 'Mancheng', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130622, '清苑县', 'Qingyuan', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130623, '涞水县', 'Laishui', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130624, '阜平县', 'Fuping', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130625, '徐水县', 'Xushui', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130626, '定兴县', 'Dingxing', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130627, '唐县', 'Tangxian', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130628, '高阳县', 'Gaoyang', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130629, '容城县', 'Rongcheng', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130630, '涞源县', 'Laiyuan', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130631, '望都县', 'Wangdu', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130632, '安新县', 'Anxin', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130633, '易县', 'Yixian', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130634, '曲阳县', 'Quyang', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130635, '蠡县', 'Lixian', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130636, '顺平县', 'Shunping', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130637, '博野县', 'Boye', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130638, '雄县', 'Xiongxian', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130681, '涿州市', 'Zhuozhou', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130682, '定州市', 'Dingzhou', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130683, '安国市', 'Anguo', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130684, '高碑店市', 'Gaobeidian', NULL, NULL, NULL, 130600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130700, '张家口市', 'Zhangjiakou', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130702, '桥东区', 'Qiaodong', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130703, '桥西区', 'Qiaoxi', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130705, '宣化区', 'Xuanhua', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130706, '下花园区', 'Xiahuayuan', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130721, '宣化县', 'Xuanhua', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130722, '张北县', 'Zhangbei', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130723, '康保县', 'Kangbao', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130724, '沽源县', 'Guyuan', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130725, '尚义县', 'Shangyi', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130726, '蔚县', 'Yuxian', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130727, '阳原县', 'Yangyuan', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130728, '怀安县', 'Huai\'an', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130729, '万全县', 'Wanquan', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130730, '怀来县', 'Huailai', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130731, '涿鹿县', 'Zhuolu', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130732, '赤城县', 'Chicheng', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130733, '崇礼县', 'Chongli', NULL, NULL, NULL, 130700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130800, '承德市', 'Chengde', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130802, '双桥区', 'Shuangqiao', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130803, '双滦区', 'Shuangluan', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130804, '鹰手营子矿区', 'Yingshouyingzikuangqu', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130821, '承德县', 'Chengde', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130822, '兴隆县', 'Xinglong', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130823, '平泉县', 'Pingquan', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130824, '滦平县', 'Luanping', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130825, '隆化县', 'Longhua', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130826, '丰宁满族自治县', 'Fengning', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130827, '宽城满族自治县', 'Kuancheng', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130828, '围场满族蒙古族自治县', 'Weichang', NULL, NULL, NULL, 130800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130900, '沧州市', 'Cangzhou', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130902, '新华区', 'Xinhua', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130903, '运河区', 'Yunhe', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130921, '沧县', 'Cangxian', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130922, '青县', 'Qingxian', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130923, '东光县', 'Dongguang', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130924, '海兴县', 'Haixing', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130925, '盐山县', 'Yanshan', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130926, '肃宁县', 'Suning', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130927, '南皮县', 'Nanpi', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130928, '吴桥县', 'Wuqiao', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130929, '献县', 'Xianxian', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130930, '孟村回族自治县', 'Mengcun', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130981, '泊头市', 'Botou', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130982, '任丘市', 'Renqiu', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130983, '黄骅市', 'Huanghua', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (130984, '河间市', 'Hejian', NULL, NULL, NULL, 130900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131000, '廊坊市', 'Langfang', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131002, '安次区', 'Anci', NULL, NULL, NULL, 131000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131003, '广阳区', 'Guangyang', NULL, NULL, NULL, 131000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131022, '固安县', 'Gu\'an', NULL, NULL, NULL, 131000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131023, '永清县', 'Yongqing', NULL, NULL, NULL, 131000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131024, '香河县', 'Xianghe', NULL, NULL, NULL, 131000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131025, '大城县', 'Daicheng', NULL, NULL, NULL, 131000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131026, '文安县', 'Wen\'an', NULL, NULL, NULL, 131000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131028, '大厂回族自治县', 'Dachang', NULL, NULL, NULL, 131000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131081, '霸州市', 'Bazhou', NULL, NULL, NULL, 131000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131082, '三河市', 'Sanhe', NULL, NULL, NULL, 131000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131100, '衡水市', 'Hengshui', NULL, NULL, NULL, 130000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131102, '桃城区', 'Taocheng', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131121, '枣强县', 'Zaoqiang', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131122, '武邑县', 'Wuyi', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131123, '武强县', 'Wuqiang', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131124, '饶阳县', 'Raoyang', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131125, '安平县', 'Anping', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131126, '故城县', 'Gucheng', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131127, '景县', 'Jingxian', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131128, '阜城县', 'Fucheng', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131181, '冀州市', 'Jizhou', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (131182, '深州市', 'Shenzhou', NULL, NULL, NULL, 131100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140000, '山西省', 'Shanxi', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140100, '太原市', 'Taiyuan', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140105, '小店区', 'Xiaodian', NULL, NULL, NULL, 140100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140106, '迎泽区', 'Yingze', NULL, NULL, NULL, 140100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140107, '杏花岭区', 'Xinghualing', NULL, NULL, NULL, 140100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140108, '尖草坪区', 'Jiancaoping', NULL, NULL, NULL, 140100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140109, '万柏林区', 'Wanbailin', NULL, NULL, NULL, 140100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140110, '晋源区', 'Jinyuan', NULL, NULL, NULL, 140100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140121, '清徐县', 'Qingxu', NULL, NULL, NULL, 140100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140122, '阳曲县', 'Yangqu', NULL, NULL, NULL, 140100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140123, '娄烦县', 'Loufan', NULL, NULL, NULL, 140100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140181, '古交市', 'Gujiao', NULL, NULL, NULL, 140100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140200, '大同市', 'Datong', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140202, '城区', 'Chengqu', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140203, '矿区', 'Kuangqu', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140211, '南郊区', 'Nanjiao', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140212, '新荣区', 'Xinrong', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140221, '阳高县', 'Yanggao', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140222, '天镇县', 'Tianzhen', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140223, '广灵县', 'Guangling', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140224, '灵丘县', 'Lingqiu', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140225, '浑源县', 'Hunyuan', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140226, '左云县', 'Zuoyun', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140227, '大同县', 'Datong', NULL, NULL, NULL, 140200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140300, '阳泉市', 'Yangquan', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140302, '城区', 'Chengqu', NULL, NULL, NULL, 140300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140303, '矿区', 'Kuangqu', NULL, NULL, NULL, 140300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140311, '郊区', 'Jiaoqu', NULL, NULL, NULL, 140300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140321, '平定县', 'Pingding', NULL, NULL, NULL, 140300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140322, '盂县', 'Yuxian', NULL, NULL, NULL, 140300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140400, '长治市', 'Changzhi', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140402, '城区', 'Chengqu', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140411, '郊区', 'Jiaoqu', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140421, '长治县', 'Changzhi', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140423, '襄垣县', 'Xiangyuan', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140424, '屯留县', 'Tunliu', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140425, '平顺县', 'Pingshun', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140426, '黎城县', 'Licheng', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140427, '壶关县', 'Huguan', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140428, '长子县', 'Zhangzi', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140429, '武乡县', 'Wuxiang', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140430, '沁县', 'Qinxian', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140431, '沁源县', 'Qinyuan', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140481, '潞城市', 'Lucheng', NULL, NULL, NULL, 140400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140500, '晋城市', 'Jincheng', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140502, '城区', 'Chengqu', NULL, NULL, NULL, 140500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140521, '沁水县', 'Qinshui', NULL, NULL, NULL, 140500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140522, '阳城县', 'Yangcheng', NULL, NULL, NULL, 140500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140524, '陵川县', 'Lingchuan', NULL, NULL, NULL, 140500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140525, '泽州县', 'Zezhou', NULL, NULL, NULL, 140500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140581, '高平市', 'Gaoping', NULL, NULL, NULL, 140500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140600, '朔州市', 'Shuozhou', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140602, '朔城区', 'Shuocheng', NULL, NULL, NULL, 140600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140603, '平鲁区', 'Pinglu', NULL, NULL, NULL, 140600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140621, '山阴县', 'Shanyin', NULL, NULL, NULL, 140600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140622, '应县', 'Yingxian', NULL, NULL, NULL, 140600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140623, '右玉县', 'Youyu', NULL, NULL, NULL, 140600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140624, '怀仁县', 'Huairen', NULL, NULL, NULL, 140600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140700, '晋中市', 'Jinzhong', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140702, '榆次区', 'Yuci', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140721, '榆社县', 'Yushe', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140722, '左权县', 'Zuoquan', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140723, '和顺县', 'Heshun', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140724, '昔阳县', 'Xiyang', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140725, '寿阳县', 'Shouyang', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140726, '太谷县', 'Taigu', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140727, '祁县', 'Qixian', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140728, '平遥县', 'Pingyao', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140729, '灵石县', 'Lingshi', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140781, '介休市', 'Jiexiu', NULL, NULL, NULL, 140700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140800, '运城市', 'Yuncheng', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140802, '盐湖区', 'Yanhu', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140821, '临猗县', 'Linyi', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140822, '万荣县', 'Wanrong', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140823, '闻喜县', 'Wenxi', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140824, '稷山县', 'Jishan', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140825, '新绛县', 'Xinjiang', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140826, '绛县', 'Jiangxian', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140827, '垣曲县', 'Yuanqu', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140828, '夏县', 'Xiaxian', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140829, '平陆县', 'Pinglu', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140830, '芮城县', 'Ruicheng', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140881, '永济市', 'Yongji', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140882, '河津市', 'Hejin', NULL, NULL, NULL, 140800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140900, '忻州市', 'Xinzhou', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140902, '忻府区', 'Xinfu', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140921, '定襄县', 'Dingxiang', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140922, '五台县', 'Wutai', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140923, '代县', 'Daixian', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140924, '繁峙县', 'Fanshi', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140925, '宁武县', 'Ningwu', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140926, '静乐县', 'Jingle', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140927, '神池县', 'Shenchi', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140928, '五寨县', 'Wuzhai', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140929, '岢岚县', 'Kelan', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140930, '河曲县', 'Hequ', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140931, '保德县', 'Baode', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140932, '偏关县', 'Pianguan', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (140981, '原平市', 'Yuanping', NULL, NULL, NULL, 140900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141000, '临汾市', 'Linfen', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141002, '尧都区', 'Yaodu', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141021, '曲沃县', 'Quwo', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141022, '翼城县', 'Yicheng', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141023, '襄汾县', 'Xiangfen', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141024, '洪洞县', 'Hongtong', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141025, '古县', 'Guxian', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141026, '安泽县', 'Anze', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141027, '浮山县', 'Fushan', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141028, '吉县', 'Jixian', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141029, '乡宁县', 'Xiangning', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141030, '大宁县', 'Daning', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141031, '隰县', 'Xixian', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141032, '永和县', 'Yonghe', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141033, '蒲县', 'Puxian', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141034, '汾西县', 'Fenxi', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141081, '侯马市', 'Houma', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141082, '霍州市', 'Huozhou', NULL, NULL, NULL, 141000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141100, '吕梁市', 'Lvliang', NULL, NULL, NULL, 140000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141102, '离石区', 'Lishi', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141121, '文水县', 'Wenshui', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141122, '交城县', 'Jiaocheng', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141123, '兴县', 'Xingxian', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141124, '临县', 'Linxian', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141125, '柳林县', 'Liulin', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141126, '石楼县', 'Shilou', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141127, '岚县', 'Lanxian', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141128, '方山县', 'Fangshan', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141129, '中阳县', 'Zhongyang', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141130, '交口县', 'Jiaokou', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141181, '孝义市', 'Xiaoyi', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (141182, '汾阳市', 'Fenyang', NULL, NULL, NULL, 141100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150000, '内蒙古自治区', 'Inner Mongolia', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150100, '呼和浩特市', 'Hohhot', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150102, '新城区', 'Xincheng', NULL, NULL, NULL, 150100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150103, '回民区', 'Huimin', NULL, NULL, NULL, 150100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150104, '玉泉区', 'Yuquan', NULL, NULL, NULL, 150100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150105, '赛罕区', 'Saihan', NULL, NULL, NULL, 150100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150121, '土默特左旗', 'Tumotezuoqi', NULL, NULL, NULL, 150100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150122, '托克托县', 'Tuoketuo', NULL, NULL, NULL, 150100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150123, '和林格尔县', 'Helingeer', NULL, NULL, NULL, 150100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150124, '清水河县', 'Qingshuihe', NULL, NULL, NULL, 150100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150125, '武川县', 'Wuchuan', NULL, NULL, NULL, 150100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150200, '包头市', 'Baotou', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150202, '东河区', 'Donghe', NULL, NULL, NULL, 150200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150203, '昆都仑区', 'Kundulun', NULL, NULL, NULL, 150200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150204, '青山区', 'Qingshan', NULL, NULL, NULL, 150200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150205, '石拐区', 'Shiguai', NULL, NULL, NULL, 150200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150206, '白云鄂博矿区', 'Baiyunebokuangqu', NULL, NULL, NULL, 150200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150207, '九原区', 'Jiuyuan', NULL, NULL, NULL, 150200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150221, '土默特右旗', 'Tumoteyouqi', NULL, NULL, NULL, 150200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150222, '固阳县', 'Guyang', NULL, NULL, NULL, 150200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150223, '达尔罕茂明安联合旗', 'Damaoqi', NULL, NULL, NULL, 150200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150300, '乌海市', 'Wuhai', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150302, '海勃湾区', 'Haibowan', NULL, NULL, NULL, 150300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150303, '海南区', 'Hainan', NULL, NULL, NULL, 150300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150304, '乌达区', 'Wuda', NULL, NULL, NULL, 150300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150400, '赤峰市', 'Chifeng', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150402, '红山区', 'Hongshan', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150403, '元宝山区', 'Yuanbaoshan', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150404, '松山区', 'Songshan', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150421, '阿鲁科尔沁旗', 'Alukeerqinqi', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150422, '巴林左旗', 'Balinzuoqi', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150423, '巴林右旗', 'Balinyouqi', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150424, '林西县', 'Linxi', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150425, '克什克腾旗', 'Keshiketengqi', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150426, '翁牛特旗', 'Wengniuteqi', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150428, '喀喇沁旗', 'Kalaqinqi', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150429, '宁城县', 'Ningcheng', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150430, '敖汉旗', 'Aohanqi', NULL, NULL, NULL, 150400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150500, '通辽市', 'Tongliao', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150502, '科尔沁区', 'Keerqin', NULL, NULL, NULL, 150500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150521, '科尔沁左翼中旗', 'Keerqinzuoyizhongqi', NULL, NULL, NULL, 150500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150522, '科尔沁左翼后旗', 'Keerqinzuoyihouqi', NULL, NULL, NULL, 150500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150523, '开鲁县', 'Kailu', NULL, NULL, NULL, 150500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150524, '库伦旗', 'Kulunqi', NULL, NULL, NULL, 150500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150525, '奈曼旗', 'Naimanqi', NULL, NULL, NULL, 150500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150526, '扎鲁特旗', 'Zhaluteqi', NULL, NULL, NULL, 150500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150581, '霍林郭勒市', 'Huolinguole', NULL, NULL, NULL, 150500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150600, '鄂尔多斯市', 'Ordos', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150602, '东胜区', 'Dongsheng', NULL, NULL, NULL, 150600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150621, '达拉特旗', 'Dalateqi', NULL, NULL, NULL, 150600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150622, '准格尔旗', 'Zhungeerqi', NULL, NULL, NULL, 150600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150623, '鄂托克前旗', 'Etuokeqianqi', NULL, NULL, NULL, 150600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150624, '鄂托克旗', 'Etuokeqi', NULL, NULL, NULL, 150600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150625, '杭锦旗', 'Hangjinqi', NULL, NULL, NULL, 150600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150626, '乌审旗', 'Wushenqi', NULL, NULL, NULL, 150600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150627, '伊金霍洛旗', 'Yijinhuoluoqi', NULL, NULL, NULL, 150600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150700, '呼伦贝尔市', 'Hulunber', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150702, '海拉尔区', 'Hailaer', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150703, '扎赉诺尔区', 'Zhalainuoer', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150721, '阿荣旗', 'Arongqi', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150722, '莫力达瓦达斡尔族自治旗', 'Moqi', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150723, '鄂伦春自治旗', 'Elunchun', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150724, '鄂温克族自治旗', 'Ewen', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150725, '陈巴尔虎旗', 'Chenbaerhuqi', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150726, '新巴尔虎左旗', 'Xinbaerhuzuoqi', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150727, '新巴尔虎右旗', 'Xinbaerhuyouqi', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150781, '满洲里市', 'Manzhouli', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150782, '牙克石市', 'Yakeshi', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150783, '扎兰屯市', 'Zhalantun', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150784, '额尔古纳市', 'Eerguna', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150785, '根河市', 'Genhe', NULL, NULL, NULL, 150700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150800, '巴彦淖尔市', 'Bayan Nur', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150802, '临河区', 'Linhe', NULL, NULL, NULL, 150800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150821, '五原县', 'Wuyuan', NULL, NULL, NULL, 150800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150822, '磴口县', 'Dengkou', NULL, NULL, NULL, 150800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150823, '乌拉特前旗', 'Wulateqianqi', NULL, NULL, NULL, 150800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150824, '乌拉特中旗', 'Wulatezhongqi', NULL, NULL, NULL, 150800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150825, '乌拉特后旗', 'Wulatehouqi', NULL, NULL, NULL, 150800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150826, '杭锦后旗', 'Hangjinhouqi', NULL, NULL, NULL, 150800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150900, '乌兰察布市', 'Ulanqab', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150902, '集宁区', 'Jining', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150921, '卓资县', 'Zhuozi', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150922, '化德县', 'Huade', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150923, '商都县', 'Shangdu', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150924, '兴和县', 'Xinghe', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150925, '凉城县', 'Liangcheng', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150926, '察哈尔右翼前旗', 'Chayouqianqi', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150927, '察哈尔右翼中旗', 'Chayouzhongqi', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150928, '察哈尔右翼后旗', 'Chayouhouqi', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150929, '四子王旗', 'Siziwangqi', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (150981, '丰镇市', 'Fengzhen', NULL, NULL, NULL, 150900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152200, '兴安盟', 'Hinggan', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152201, '乌兰浩特市', 'Wulanhaote', NULL, NULL, NULL, 152200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152202, '阿尔山市', 'Aershan', NULL, NULL, NULL, 152200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152221, '科尔沁右翼前旗', 'Keyouqianqi', NULL, NULL, NULL, 152200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152222, '科尔沁右翼中旗', 'Keyouzhongqi', NULL, NULL, NULL, 152200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152223, '扎赉特旗', 'Zhalaiteqi', NULL, NULL, NULL, 152200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152224, '突泉县', 'Tuquan', NULL, NULL, NULL, 152200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152500, '锡林郭勒盟', 'Xilin Gol', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152501, '二连浩特市', 'Erlianhaote', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152502, '锡林浩特市', 'Xilinhaote', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152522, '阿巴嘎旗', 'Abagaqi', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152523, '苏尼特左旗', 'Sunitezuoqi', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152524, '苏尼特右旗', 'Suniteyouqi', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152525, '东乌珠穆沁旗', 'Dongwuqi', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152526, '西乌珠穆沁旗', 'Xiwuqi', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152527, '太仆寺旗', 'Taipusiqi', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152528, '镶黄旗', 'Xianghuangqi', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152529, '正镶白旗', 'Zhengxiangbaiqi', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152530, '正蓝旗', 'Zhenglanqi', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152531, '多伦县', 'Duolun', NULL, NULL, NULL, 152500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152900, '阿拉善盟', 'Alxa', NULL, NULL, NULL, 150000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152921, '阿拉善左旗', 'Alashanzuoqi', NULL, NULL, NULL, 152900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152922, '阿拉善右旗', 'Alashanyouqi', NULL, NULL, NULL, 152900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (152923, '额济纳旗', 'Ejinaqi', NULL, NULL, NULL, 152900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210000, '辽宁省', 'Liaoning', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210100, '沈阳市', 'Shenyang', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210102, '和平区', 'Heping', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210103, '沈河区', 'Shenhe', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210104, '大东区', 'Dadong', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210105, '皇姑区', 'Huanggu', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210106, '铁西区', 'Tiexi', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210111, '苏家屯区', 'Sujiatun', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210112, '浑南区', 'Hunnan', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210113, '沈北新区', 'Shenbeixinqu', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210114, '于洪区', 'Yuhong', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210122, '辽中县', 'Liaozhong', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210123, '康平县', 'Kangping', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210124, '法库县', 'Faku', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210181, '新民市', 'Xinmin', NULL, NULL, NULL, 210100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210200, '大连市', 'Dalian', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210202, '中山区', 'Zhongshan', NULL, NULL, NULL, 210200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210203, '西岗区', 'Xigang', NULL, NULL, NULL, 210200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210204, '沙河口区', 'Shahekou', NULL, NULL, NULL, 210200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210211, '甘井子区', 'Ganjingzi', NULL, NULL, NULL, 210200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210212, '旅顺口区', 'Lvshunkou', NULL, NULL, NULL, 210200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210213, '金州区', 'Jinzhou', NULL, NULL, NULL, 210200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210224, '长海县', 'Changhai', NULL, NULL, NULL, 210200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210281, '瓦房店市', 'Wafangdian', NULL, NULL, NULL, 210200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210282, '普兰店市', 'Pulandian', NULL, NULL, NULL, 210200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210283, '庄河市', 'Zhuanghe', NULL, NULL, NULL, 210200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210300, '鞍山市', 'Anshan', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210302, '铁东区', 'Tiedong', NULL, NULL, NULL, 210300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210303, '铁西区', 'Tiexi', NULL, NULL, NULL, 210300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210304, '立山区', 'Lishan', NULL, NULL, NULL, 210300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210311, '千山区', 'Qianshan', NULL, NULL, NULL, 210300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210321, '台安县', 'Tai\'an', NULL, NULL, NULL, 210300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210323, '岫岩满族自治县', 'Xiuyan', NULL, NULL, NULL, 210300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210381, '海城市', 'Haicheng', NULL, NULL, NULL, 210300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210400, '抚顺市', 'Fushun', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210402, '新抚区', 'Xinfu', NULL, NULL, NULL, 210400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210403, '东洲区', 'Dongzhou', NULL, NULL, NULL, 210400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210404, '望花区', 'Wanghua', NULL, NULL, NULL, 210400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210411, '顺城区', 'Shuncheng', NULL, NULL, NULL, 210400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210421, '抚顺县', 'Fushun', NULL, NULL, NULL, 210400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210422, '新宾满族自治县', 'Xinbin', NULL, NULL, NULL, 210400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210423, '清原满族自治县', 'Qingyuan', NULL, NULL, NULL, 210400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210500, '本溪市', 'Benxi', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210502, '平山区', 'Pingshan', NULL, NULL, NULL, 210500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210503, '溪湖区', 'Xihu', NULL, NULL, NULL, 210500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210504, '明山区', 'Mingshan', NULL, NULL, NULL, 210500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210505, '南芬区', 'Nanfen', NULL, NULL, NULL, 210500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210521, '本溪满族自治县', 'Benxi', NULL, NULL, NULL, 210500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210522, '桓仁满族自治县', 'Huanren', NULL, NULL, NULL, 210500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210600, '丹东市', 'Dandong', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210602, '元宝区', 'Yuanbao', NULL, NULL, NULL, 210600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210603, '振兴区', 'Zhenxing', NULL, NULL, NULL, 210600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210604, '振安区', 'Zhen\'an', NULL, NULL, NULL, 210600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210624, '宽甸满族自治县', 'Kuandian', NULL, NULL, NULL, 210600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210681, '东港市', 'Donggang', NULL, NULL, NULL, 210600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210682, '凤城市', 'Fengcheng', NULL, NULL, NULL, 210600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210700, '锦州市', 'Jinzhou', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210702, '古塔区', 'Guta', NULL, NULL, NULL, 210700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210703, '凌河区', 'Linghe', NULL, NULL, NULL, 210700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210711, '太和区', 'Taihe', NULL, NULL, NULL, 210700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210726, '黑山县', 'Heishan', NULL, NULL, NULL, 210700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210727, '义县', 'Yixian', NULL, NULL, NULL, 210700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210781, '凌海市', 'Linghai', NULL, NULL, NULL, 210700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210782, '北镇市', 'Beizhen', NULL, NULL, NULL, 210700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210800, '营口市', 'Yingkou', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210802, '站前区', 'Zhanqian', NULL, NULL, NULL, 210800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210803, '西市区', 'Xishi', NULL, NULL, NULL, 210800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210804, '鲅鱼圈区', 'Bayuquan', NULL, NULL, NULL, 210800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210811, '老边区', 'Laobian', NULL, NULL, NULL, 210800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210881, '盖州市', 'Gaizhou', NULL, NULL, NULL, 210800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210882, '大石桥市', 'Dashiqiao', NULL, NULL, NULL, 210800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210900, '阜新市', 'Fuxin', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210902, '海州区', 'Haizhou', NULL, NULL, NULL, 210900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210903, '新邱区', 'Xinqiu', NULL, NULL, NULL, 210900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210904, '太平区', 'Taiping', NULL, NULL, NULL, 210900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210905, '清河门区', 'Qinghemen', NULL, NULL, NULL, 210900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210911, '细河区', 'Xihe', NULL, NULL, NULL, 210900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210921, '阜新蒙古族自治县', 'Fuxin', NULL, NULL, NULL, 210900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (210922, '彰武县', 'Zhangwu', NULL, NULL, NULL, 210900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211000, '辽阳市', 'Liaoyang', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211002, '白塔区', 'Baita', NULL, NULL, NULL, 211000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211003, '文圣区', 'Wensheng', NULL, NULL, NULL, 211000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211004, '宏伟区', 'Hongwei', NULL, NULL, NULL, 211000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211005, '弓长岭区', 'Gongchangling', NULL, NULL, NULL, 211000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211011, '太子河区', 'Taizihe', NULL, NULL, NULL, 211000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211021, '辽阳县', 'Liaoyang', NULL, NULL, NULL, 211000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211081, '灯塔市', 'Dengta', NULL, NULL, NULL, 211000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211100, '盘锦市', 'Panjin', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211102, '双台子区', 'Shuangtaizi', NULL, NULL, NULL, 211100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211103, '兴隆台区', 'Xinglongtai', NULL, NULL, NULL, 211100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211121, '大洼县', 'Dawa', NULL, NULL, NULL, 211100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211122, '盘山县', 'Panshan', NULL, NULL, NULL, 211100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211200, '铁岭市', 'Tieling', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211202, '银州区', 'Yinzhou', NULL, NULL, NULL, 211200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211204, '清河区', 'Qinghe', NULL, NULL, NULL, 211200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211221, '铁岭县', 'Tieling', NULL, NULL, NULL, 211200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211223, '西丰县', 'Xifeng', NULL, NULL, NULL, 211200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211224, '昌图县', 'Changtu', NULL, NULL, NULL, 211200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211281, '调兵山市', 'Diaobingshan', NULL, NULL, NULL, 211200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211282, '开原市', 'Kaiyuan', NULL, NULL, NULL, 211200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211300, '朝阳市', 'Chaoyang', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211302, '双塔区', 'Shuangta', NULL, NULL, NULL, 211300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211303, '龙城区', 'Longcheng', NULL, NULL, NULL, 211300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211321, '朝阳县', 'Chaoyang', NULL, NULL, NULL, 211300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211322, '建平县', 'Jianping', NULL, NULL, NULL, 211300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211324, '喀喇沁左翼蒙古族自治县', 'Kalaqinzuoyi', NULL, NULL, NULL, 211300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211381, '北票市', 'Beipiao', NULL, NULL, NULL, 211300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211382, '凌源市', 'Lingyuan', NULL, NULL, NULL, 211300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211400, '葫芦岛市', 'Huludao', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211402, '连山区', 'Lianshan', NULL, NULL, NULL, 211400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211403, '龙港区', 'Longgang', NULL, NULL, NULL, 211400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211404, '南票区', 'Nanpiao', NULL, NULL, NULL, 211400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211421, '绥中县', 'Suizhong', NULL, NULL, NULL, 211400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211422, '建昌县', 'Jianchang', NULL, NULL, NULL, 211400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211481, '兴城市', 'Xingcheng', NULL, NULL, NULL, 211400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211500, '金普新区', 'Jinpuxinqu', NULL, NULL, NULL, 210000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211501, '金州新区', 'Jinzhouxinqu', NULL, NULL, NULL, 211500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211502, '普湾新区', 'Puwanxinqu', NULL, NULL, NULL, 211500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (211503, '保税区', 'Baoshuiqu', NULL, NULL, NULL, 211500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220000, '吉林省', 'Jilin', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220100, '长春市', 'Changchun', NULL, NULL, NULL, 220000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220102, '南关区', 'Nanguan', NULL, NULL, NULL, 220100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220103, '宽城区', 'Kuancheng', NULL, NULL, NULL, 220100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220104, '朝阳区', 'Chaoyang', NULL, NULL, NULL, 220100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220105, '二道区', 'Erdao', NULL, NULL, NULL, 220100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220106, '绿园区', 'Lvyuan', NULL, NULL, NULL, 220100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220112, '双阳区', 'Shuangyang', NULL, NULL, NULL, 220100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220113, '九台区', 'Jiutai', NULL, NULL, NULL, 220100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220122, '农安县', 'Nong\'an', NULL, NULL, NULL, 220100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220182, '榆树市', 'Yushu', NULL, NULL, NULL, 220100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220183, '德惠市', 'Dehui', NULL, NULL, NULL, 220100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220200, '吉林市', 'Jilin', NULL, NULL, NULL, 220000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220202, '昌邑区', 'Changyi', NULL, NULL, NULL, 220200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220203, '龙潭区', 'Longtan', NULL, NULL, NULL, 220200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220204, '船营区', 'Chuanying', NULL, NULL, NULL, 220200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220211, '丰满区', 'Fengman', NULL, NULL, NULL, 220200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220221, '永吉县', 'Yongji', NULL, NULL, NULL, 220200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220281, '蛟河市', 'Jiaohe', NULL, NULL, NULL, 220200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220282, '桦甸市', 'Huadian', NULL, NULL, NULL, 220200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220283, '舒兰市', 'Shulan', NULL, NULL, NULL, 220200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220284, '磐石市', 'Panshi', NULL, NULL, NULL, 220200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220300, '四平市', 'Siping', NULL, NULL, NULL, 220000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220302, '铁西区', 'Tiexi', NULL, NULL, NULL, 220300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220303, '铁东区', 'Tiedong', NULL, NULL, NULL, 220300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220322, '梨树县', 'Lishu', NULL, NULL, NULL, 220300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220323, '伊通满族自治县', 'Yitong', NULL, NULL, NULL, 220300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220381, '公主岭市', 'Gongzhuling', NULL, NULL, NULL, 220300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220382, '双辽市', 'Shuangliao', NULL, NULL, NULL, 220300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220400, '辽源市', 'Liaoyuan', NULL, NULL, NULL, 220000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220402, '龙山区', 'Longshan', NULL, NULL, NULL, 220400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220403, '西安区', 'Xi\'an', NULL, NULL, NULL, 220400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220421, '东丰县', 'Dongfeng', NULL, NULL, NULL, 220400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220422, '东辽县', 'Dongliao', NULL, NULL, NULL, 220400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220500, '通化市', 'Tonghua', NULL, NULL, NULL, 220000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220502, '东昌区', 'Dongchang', NULL, NULL, NULL, 220500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220503, '二道江区', 'Erdaojiang', NULL, NULL, NULL, 220500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220521, '通化县', 'Tonghua', NULL, NULL, NULL, 220500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220523, '辉南县', 'Huinan', NULL, NULL, NULL, 220500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220524, '柳河县', 'Liuhe', NULL, NULL, NULL, 220500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220581, '梅河口市', 'Meihekou', NULL, NULL, NULL, 220500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220582, '集安市', 'Ji\'an', NULL, NULL, NULL, 220500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220600, '白山市', 'Baishan', NULL, NULL, NULL, 220000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220602, '浑江区', 'Hunjiang', NULL, NULL, NULL, 220600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220605, '江源区', 'Jiangyuan', NULL, NULL, NULL, 220600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220621, '抚松县', 'Fusong', NULL, NULL, NULL, 220600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220622, '靖宇县', 'Jingyu', NULL, NULL, NULL, 220600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220623, '长白朝鲜族自治县', 'Changbai', NULL, NULL, NULL, 220600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220681, '临江市', 'Linjiang', NULL, NULL, NULL, 220600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220700, '松原市', 'Songyuan', NULL, NULL, NULL, 220000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220702, '宁江区', 'Ningjiang', NULL, NULL, NULL, 220700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220721, '前郭尔罗斯蒙古族自治县', 'Qianguoerluosi', NULL, NULL, NULL, 220700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220722, '长岭县', 'Changling', NULL, NULL, NULL, 220700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220723, '乾安县', 'Qian\'an', NULL, NULL, NULL, 220700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220781, '扶余市', 'Fuyu', NULL, NULL, NULL, 220700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220800, '白城市', 'Baicheng', NULL, NULL, NULL, 220000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220802, '洮北区', 'Taobei', NULL, NULL, NULL, 220800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220821, '镇赉县', 'Zhenlai', NULL, NULL, NULL, 220800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220822, '通榆县', 'Tongyu', NULL, NULL, NULL, 220800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220881, '洮南市', 'Taonan', NULL, NULL, NULL, 220800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (220882, '大安市', 'Da\'an', NULL, NULL, NULL, 220800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (222400, '延边朝鲜族自治州', 'Yanbian', NULL, NULL, NULL, 220000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (222401, '延吉市', 'Yanji', NULL, NULL, NULL, 222400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (222402, '图们市', 'Tumen', NULL, NULL, NULL, 222400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (222403, '敦化市', 'Dunhua', NULL, NULL, NULL, 222400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (222404, '珲春市', 'Hunchun', NULL, NULL, NULL, 222400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (222405, '龙井市', 'Longjing', NULL, NULL, NULL, 222400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (222406, '和龙市', 'Helong', NULL, NULL, NULL, 222400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (222424, '汪清县', 'Wangqing', NULL, NULL, NULL, 222400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (222426, '安图县', 'Antu', NULL, NULL, NULL, 222400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230000, '黑龙江省', 'Heilongjiang', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230100, '哈尔滨市', 'Harbin', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230102, '道里区', 'Daoli', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230103, '南岗区', 'Nangang', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230104, '道外区', 'Daowai', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230108, '平房区', 'Pingfang', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230109, '松北区', 'Songbei', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230110, '香坊区', 'Xiangfang', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230111, '呼兰区', 'Hulan', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230112, '阿城区', 'A\'cheng', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230113, '双城区', 'Shuangcheng', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230123, '依兰县', 'Yilan', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230124, '方正县', 'Fangzheng', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230125, '宾县', 'Binxian', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230126, '巴彦县', 'Bayan', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230127, '木兰县', 'Mulan', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230128, '通河县', 'Tonghe', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230129, '延寿县', 'Yanshou', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230183, '尚志市', 'Shangzhi', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230184, '五常市', 'Wuchang', NULL, NULL, NULL, 230100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230200, '齐齐哈尔市', 'Qiqihar', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230202, '龙沙区', 'Longsha', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230203, '建华区', 'Jianhua', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230204, '铁锋区', 'Tiefeng', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230205, '昂昂溪区', 'Angangxi', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230206, '富拉尔基区', 'Fulaerji', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230207, '碾子山区', 'Nianzishan', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230208, '梅里斯达斡尔族区', 'Meilisi', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230221, '龙江县', 'Longjiang', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230223, '依安县', 'Yi\'an', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230224, '泰来县', 'Tailai', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230225, '甘南县', 'Gannan', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230227, '富裕县', 'Fuyu', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230229, '克山县', 'Keshan', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230230, '克东县', 'Kedong', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230231, '拜泉县', 'Baiquan', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230281, '讷河市', 'Nehe', NULL, NULL, NULL, 230200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230300, '鸡西市', 'Jixi', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230302, '鸡冠区', 'Jiguan', NULL, NULL, NULL, 230300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230303, '恒山区', 'Hengshan', NULL, NULL, NULL, 230300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230304, '滴道区', 'Didao', NULL, NULL, NULL, 230300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230305, '梨树区', 'Lishu', NULL, NULL, NULL, 230300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230306, '城子河区', 'Chengzihe', NULL, NULL, NULL, 230300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230307, '麻山区', 'Mashan', NULL, NULL, NULL, 230300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230321, '鸡东县', 'Jidong', NULL, NULL, NULL, 230300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230381, '虎林市', 'Hulin', NULL, NULL, NULL, 230300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230382, '密山市', 'Mishan', NULL, NULL, NULL, 230300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230400, '鹤岗市', 'Hegang', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230402, '向阳区', 'Xiangyang', NULL, NULL, NULL, 230400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230403, '工农区', 'Gongnong', NULL, NULL, NULL, 230400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230404, '南山区', 'Nanshan', NULL, NULL, NULL, 230400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230405, '兴安区', 'Xing\'an', NULL, NULL, NULL, 230400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230406, '东山区', 'Dongshan', NULL, NULL, NULL, 230400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230407, '兴山区', 'Xingshan', NULL, NULL, NULL, 230400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230421, '萝北县', 'Luobei', NULL, NULL, NULL, 230400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230422, '绥滨县', 'Suibin', NULL, NULL, NULL, 230400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230500, '双鸭山市', 'Shuangyashan', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230502, '尖山区', 'Jianshan', NULL, NULL, NULL, 230500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230503, '岭东区', 'Lingdong', NULL, NULL, NULL, 230500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230505, '四方台区', 'Sifangtai', NULL, NULL, NULL, 230500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230506, '宝山区', 'Baoshan', NULL, NULL, NULL, 230500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230521, '集贤县', 'Jixian', NULL, NULL, NULL, 230500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230522, '友谊县', 'Youyi', NULL, NULL, NULL, 230500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230523, '宝清县', 'Baoqing', NULL, NULL, NULL, 230500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230524, '饶河县', 'Raohe', NULL, NULL, NULL, 230500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230600, '大庆市', 'Daqing', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230602, '萨尔图区', 'Saertu', NULL, NULL, NULL, 230600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230603, '龙凤区', 'Longfeng', NULL, NULL, NULL, 230600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230604, '让胡路区', 'Ranghulu', NULL, NULL, NULL, 230600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230605, '红岗区', 'Honggang', NULL, NULL, NULL, 230600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230606, '大同区', 'Datong', NULL, NULL, NULL, 230600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230621, '肇州县', 'Zhaozhou', NULL, NULL, NULL, 230600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230622, '肇源县', 'Zhaoyuan', NULL, NULL, NULL, 230600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230623, '林甸县', 'Lindian', NULL, NULL, NULL, 230600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230624, '杜尔伯特蒙古族自治县', 'Duerbote', NULL, NULL, NULL, 230600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230700, '伊春市', 'Yichun', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230702, '伊春区', 'Yichun', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230703, '南岔区', 'Nancha', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230704, '友好区', 'Youhao', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230705, '西林区', 'Xilin', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230706, '翠峦区', 'Cuiluan', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230707, '新青区', 'Xinqing', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230708, '美溪区', 'Meixi', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230709, '金山屯区', 'Jinshantun', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230710, '五营区', 'Wuying', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230711, '乌马河区', 'Wumahe', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230712, '汤旺河区', 'Tangwanghe', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230713, '带岭区', 'Dailing', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230714, '乌伊岭区', 'Wuyiling', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230715, '红星区', 'Hongxing', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230716, '上甘岭区', 'Shangganling', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230722, '嘉荫县', 'Jiayin', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230781, '铁力市', 'Tieli', NULL, NULL, NULL, 230700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230800, '佳木斯市', 'Jiamusi', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230803, '向阳区', 'Xiangyang', NULL, NULL, NULL, 230800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230804, '前进区', 'Qianjin', NULL, NULL, NULL, 230800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230805, '东风区', 'Dongfeng', NULL, NULL, NULL, 230800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230811, '郊区', 'Jiaoqu', NULL, NULL, NULL, 230800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230822, '桦南县', 'Huanan', NULL, NULL, NULL, 230800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230826, '桦川县', 'Huachuan', NULL, NULL, NULL, 230800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230828, '汤原县', 'Tangyuan', NULL, NULL, NULL, 230800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230833, '抚远县', 'Fuyuan', NULL, NULL, NULL, 230800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230881, '同江市', 'Tongjiang', NULL, NULL, NULL, 230800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230882, '富锦市', 'Fujin', NULL, NULL, NULL, 230800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230900, '七台河市', 'Qitaihe', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230902, '新兴区', 'Xinxing', NULL, NULL, NULL, 230900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230903, '桃山区', 'Taoshan', NULL, NULL, NULL, 230900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230904, '茄子河区', 'Qiezihe', NULL, NULL, NULL, 230900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (230921, '勃利县', 'Boli', NULL, NULL, NULL, 230900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231000, '牡丹江市', 'Mudanjiang', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231002, '东安区', 'Dong\'an', NULL, NULL, NULL, 231000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231003, '阳明区', 'Yangming', NULL, NULL, NULL, 231000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231004, '爱民区', 'Aimin', NULL, NULL, NULL, 231000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231005, '西安区', 'Xi\'an', NULL, NULL, NULL, 231000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231024, '东宁县', 'Dongning', NULL, NULL, NULL, 231000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231025, '林口县', 'Linkou', NULL, NULL, NULL, 231000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231081, '绥芬河市', 'Suifenhe', NULL, NULL, NULL, 231000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231083, '海林市', 'Hailin', NULL, NULL, NULL, 231000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231084, '宁安市', 'Ning\'an', NULL, NULL, NULL, 231000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231085, '穆棱市', 'Muling', NULL, NULL, NULL, 231000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231100, '黑河市', 'Heihe', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231102, '爱辉区', 'Aihui', NULL, NULL, NULL, 231100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231121, '嫩江县', 'Nenjiang', NULL, NULL, NULL, 231100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231123, '逊克县', 'Xunke', NULL, NULL, NULL, 231100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231124, '孙吴县', 'Sunwu', NULL, NULL, NULL, 231100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231181, '北安市', 'Bei\'an', NULL, NULL, NULL, 231100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231182, '五大连池市', 'Wudalianchi', NULL, NULL, NULL, 231100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231200, '绥化市', 'Suihua', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231202, '北林区', 'Beilin', NULL, NULL, NULL, 231200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231221, '望奎县', 'Wangkui', NULL, NULL, NULL, 231200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231222, '兰西县', 'Lanxi', NULL, NULL, NULL, 231200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231223, '青冈县', 'Qinggang', NULL, NULL, NULL, 231200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231224, '庆安县', 'Qing\'an', NULL, NULL, NULL, 231200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231225, '明水县', 'Mingshui', NULL, NULL, NULL, 231200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231226, '绥棱县', 'Suileng', NULL, NULL, NULL, 231200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231281, '安达市', 'Anda', NULL, NULL, NULL, 231200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231282, '肇东市', 'Zhaodong', NULL, NULL, NULL, 231200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (231283, '海伦市', 'Hailun', NULL, NULL, NULL, 231200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (232700, '大兴安岭地区', 'DaXingAnLing', NULL, NULL, NULL, 230000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (232701, '加格达奇区', 'Jiagedaqi', NULL, NULL, NULL, 232700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (232702, '新林区', 'Xinlin', NULL, NULL, NULL, 232700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (232703, '松岭区', 'Songling', NULL, NULL, NULL, 232700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (232704, '呼中区', 'Huzhong', NULL, NULL, NULL, 232700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (232721, '呼玛县', 'Huma', NULL, NULL, NULL, 232700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (232722, '塔河县', 'Tahe', NULL, NULL, NULL, 232700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (232723, '漠河县', 'Mohe', NULL, NULL, NULL, 232700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310000, '上海', 'Shanghai', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310100, '上海市', 'Shanghai', NULL, NULL, NULL, 310000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310101, '黄浦区', 'Huangpu', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310104, '徐汇区', 'Xuhui', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310105, '长宁区', 'Changning', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310106, '静安区', 'Jing\'an', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310107, '普陀区', 'Putuo', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310108, '闸北区', 'Zhabei', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310109, '虹口区', 'Hongkou', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310110, '杨浦区', 'Yangpu', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310112, '闵行区', 'Minhang', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310113, '宝山区', 'Baoshan', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310114, '嘉定区', 'Jiading', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310115, '浦东新区', 'Pudong', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310116, '金山区', 'Jinshan', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310117, '松江区', 'Songjiang', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310118, '青浦区', 'Qingpu', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310120, '奉贤区', 'Fengxian', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (310230, '崇明县', 'Chongming', NULL, NULL, NULL, 310100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320000, '江苏省', 'Jiangsu', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320100, '南京市', 'Nanjing', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320102, '玄武区', 'Xuanwu', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320104, '秦淮区', 'Qinhuai', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320105, '建邺区', 'Jianye', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320106, '鼓楼区', 'Gulou', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320111, '浦口区', 'Pukou', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320113, '栖霞区', 'Qixia', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320114, '雨花台区', 'Yuhuatai', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320115, '江宁区', 'Jiangning', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320116, '六合区', 'Luhe', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320117, '溧水区', 'Lishui', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320118, '高淳区', 'Gaochun', NULL, NULL, NULL, 320100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320200, '无锡市', 'Wuxi', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320202, '崇安区', 'Chong\'an', NULL, NULL, NULL, 320200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320203, '南长区', 'Nanchang', NULL, NULL, NULL, 320200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320204, '北塘区', 'Beitang', NULL, NULL, NULL, 320200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320205, '锡山区', 'Xishan', NULL, NULL, NULL, 320200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320206, '惠山区', 'Huishan', NULL, NULL, NULL, 320200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320211, '滨湖区', 'Binhu', NULL, NULL, NULL, 320200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320281, '江阴市', 'Jiangyin', NULL, NULL, NULL, 320200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320282, '宜兴市', 'Yixing', NULL, NULL, NULL, 320200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320300, '徐州市', 'Xuzhou', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320302, '鼓楼区', 'Gulou', NULL, NULL, NULL, 320300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320303, '云龙区', 'Yunlong', NULL, NULL, NULL, 320300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320305, '贾汪区', 'Jiawang', NULL, NULL, NULL, 320300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320311, '泉山区', 'Quanshan', NULL, NULL, NULL, 320300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320312, '铜山区', 'Tongshan', NULL, NULL, NULL, 320300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320321, '丰县', 'Fengxian', NULL, NULL, NULL, 320300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320322, '沛县', 'Peixian', NULL, NULL, NULL, 320300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320324, '睢宁县', 'Suining', NULL, NULL, NULL, 320300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320381, '新沂市', 'Xinyi', NULL, NULL, NULL, 320300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320382, '邳州市', 'Pizhou', NULL, NULL, NULL, 320300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320400, '常州市', 'Changzhou', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320402, '天宁区', 'Tianning', NULL, NULL, NULL, 320400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320404, '钟楼区', 'Zhonglou', NULL, NULL, NULL, 320400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320405, '戚墅堰区', 'Qishuyan', NULL, NULL, NULL, 320400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320411, '新北区', 'Xinbei', NULL, NULL, NULL, 320400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320412, '武进区', 'Wujin', NULL, NULL, NULL, 320400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320481, '溧阳市', 'Liyang', NULL, NULL, NULL, 320400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320482, '金坛市', 'Jintan', NULL, NULL, NULL, 320400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320500, '苏州市', 'Suzhou', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320505, '虎丘区', 'Huqiu', NULL, NULL, NULL, 320500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320506, '吴中区', 'Wuzhong', NULL, NULL, NULL, 320500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320507, '相城区', 'Xiangcheng', NULL, NULL, NULL, 320500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320508, '姑苏区', 'Gusu', NULL, NULL, NULL, 320500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320509, '吴江区', 'Wujiang', NULL, NULL, NULL, 320500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320581, '常熟市', 'Changshu', NULL, NULL, NULL, 320500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320582, '张家港市', 'Zhangjiagang', NULL, NULL, NULL, 320500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320583, '昆山市', 'Kunshan', NULL, NULL, NULL, 320500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320585, '太仓市', 'Taicang', NULL, NULL, NULL, 320500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320600, '南通市', 'Nantong', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320602, '崇川区', 'Chongchuan', NULL, NULL, NULL, 320600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320611, '港闸区', 'Gangzha', NULL, NULL, NULL, 320600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320612, '通州区', 'Tongzhou', NULL, NULL, NULL, 320600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320621, '海安县', 'Hai\'an', NULL, NULL, NULL, 320600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320623, '如东县', 'Rudong', NULL, NULL, NULL, 320600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320681, '启东市', 'Qidong', NULL, NULL, NULL, 320600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320682, '如皋市', 'Rugao', NULL, NULL, NULL, 320600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320684, '海门市', 'Haimen', NULL, NULL, NULL, 320600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320700, '连云港市', 'Lianyungang', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320703, '连云区', 'Lianyun', NULL, NULL, NULL, 320700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320706, '海州区', 'Haizhou', NULL, NULL, NULL, 320700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320707, '赣榆区', 'Ganyu', NULL, NULL, NULL, 320700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320722, '东海县', 'Donghai', NULL, NULL, NULL, 320700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320723, '灌云县', 'Guanyun', NULL, NULL, NULL, 320700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320724, '灌南县', 'Guannan', NULL, NULL, NULL, 320700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320800, '淮安市', 'Huai\'an', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320802, '清河区', 'Qinghe', NULL, NULL, NULL, 320800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320803, '淮安区', 'Huai\'an', NULL, NULL, NULL, 320800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320804, '淮阴区', 'Huaiyin', NULL, NULL, NULL, 320800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320811, '清浦区', 'Qingpu', NULL, NULL, NULL, 320800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320826, '涟水县', 'Lianshui', NULL, NULL, NULL, 320800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320829, '洪泽县', 'Hongze', NULL, NULL, NULL, 320800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320830, '盱眙县', 'Xuyi', NULL, NULL, NULL, 320800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320831, '金湖县', 'Jinhu', NULL, NULL, NULL, 320800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320900, '盐城市', 'Yancheng', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320902, '亭湖区', 'Tinghu', NULL, NULL, NULL, 320900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320903, '盐都区', 'Yandu', NULL, NULL, NULL, 320900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320921, '响水县', 'Xiangshui', NULL, NULL, NULL, 320900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320922, '滨海县', 'Binhai', NULL, NULL, NULL, 320900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320923, '阜宁县', 'Funing', NULL, NULL, NULL, 320900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320924, '射阳县', 'Sheyang', NULL, NULL, NULL, 320900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320925, '建湖县', 'Jianhu', NULL, NULL, NULL, 320900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320981, '东台市', 'Dongtai', NULL, NULL, NULL, 320900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (320982, '大丰市', 'Dafeng', NULL, NULL, NULL, 320900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321000, '扬州市', 'Yangzhou', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321002, '广陵区', 'Guangling', NULL, NULL, NULL, 321000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321003, '邗江区', 'Hanjiang', NULL, NULL, NULL, 321000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321012, '江都区', 'Jiangdu', NULL, NULL, NULL, 321000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321023, '宝应县', 'Baoying', NULL, NULL, NULL, 321000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321081, '仪征市', 'Yizheng', NULL, NULL, NULL, 321000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321084, '高邮市', 'Gaoyou', NULL, NULL, NULL, 321000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321100, '镇江市', 'Zhenjiang', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321102, '京口区', 'Jingkou', NULL, NULL, NULL, 321100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321111, '润州区', 'Runzhou', NULL, NULL, NULL, 321100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321112, '丹徒区', 'Dantu', NULL, NULL, NULL, 321100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321181, '丹阳市', 'Danyang', NULL, NULL, NULL, 321100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321182, '扬中市', 'Yangzhong', NULL, NULL, NULL, 321100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321183, '句容市', 'Jurong', NULL, NULL, NULL, 321100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321200, '泰州市', 'Taizhou', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321202, '海陵区', 'Hailing', NULL, NULL, NULL, 321200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321203, '高港区', 'Gaogang', NULL, NULL, NULL, 321200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321204, '姜堰区', 'Jiangyan', NULL, NULL, NULL, 321200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321281, '兴化市', 'Xinghua', NULL, NULL, NULL, 321200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321282, '靖江市', 'Jingjiang', NULL, NULL, NULL, 321200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321283, '泰兴市', 'Taixing', NULL, NULL, NULL, 321200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321300, '宿迁市', 'Suqian', NULL, NULL, NULL, 320000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321302, '宿城区', 'Sucheng', NULL, NULL, NULL, 321300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321311, '宿豫区', 'Suyu', NULL, NULL, NULL, 321300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321322, '沭阳县', 'Shuyang', NULL, NULL, NULL, 321300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321323, '泗阳县', 'Siyang', NULL, NULL, NULL, 321300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (321324, '泗洪县', 'Sihong', NULL, NULL, NULL, 321300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330000, '浙江省', 'Zhejiang', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330100, '杭州市', 'Hangzhou', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330102, '上城区', 'Shangcheng', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330103, '下城区', 'Xiacheng', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330104, '江干区', 'Jianggan', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330105, '拱墅区', 'Gongshu', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330106, '西湖区', 'Xihu', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330108, '滨江区', 'Binjiang', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330109, '萧山区', 'Xiaoshan', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330110, '余杭区', 'Yuhang', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330122, '桐庐县', 'Tonglu', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330127, '淳安县', 'Chun\'an', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330182, '建德市', 'Jiande', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330183, '富阳区', 'Fuyang', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330185, '临安市', 'Lin\'an', NULL, NULL, NULL, 330100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330200, '宁波市', 'Ningbo', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330203, '海曙区', 'Haishu', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330204, '江东区', 'Jiangdong', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330205, '江北区', 'Jiangbei', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330206, '北仑区', 'Beilun', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330211, '镇海区', 'Zhenhai', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330212, '鄞州区', 'Yinzhou', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330225, '象山县', 'Xiangshan', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330226, '宁海县', 'Ninghai', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330281, '余姚市', 'Yuyao', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330282, '慈溪市', 'Cixi', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330283, '奉化市', 'Fenghua', NULL, NULL, NULL, 330200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330300, '温州市', 'Wenzhou', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330302, '鹿城区', 'Lucheng', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330303, '龙湾区', 'Longwan', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330304, '瓯海区', 'Ouhai', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330322, '洞头县', 'Dongtou', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330324, '永嘉县', 'Yongjia', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330326, '平阳县', 'Pingyang', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330327, '苍南县', 'Cangnan', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330328, '文成县', 'Wencheng', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330329, '泰顺县', 'Taishun', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330381, '瑞安市', 'Rui\'an', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330382, '乐清市', 'Yueqing', NULL, NULL, NULL, 330300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330400, '嘉兴市', 'Jiaxing', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330402, '南湖区', 'Nanhu', NULL, NULL, NULL, 330400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330411, '秀洲区', 'Xiuzhou', NULL, NULL, NULL, 330400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330421, '嘉善县', 'Jiashan', NULL, NULL, NULL, 330400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330424, '海盐县', 'Haiyan', NULL, NULL, NULL, 330400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330481, '海宁市', 'Haining', NULL, NULL, NULL, 330400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330482, '平湖市', 'Pinghu', NULL, NULL, NULL, 330400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330483, '桐乡市', 'Tongxiang', NULL, NULL, NULL, 330400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330500, '湖州市', 'Huzhou', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330502, '吴兴区', 'Wuxing', NULL, NULL, NULL, 330500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330503, '南浔区', 'Nanxun', NULL, NULL, NULL, 330500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330521, '德清县', 'Deqing', NULL, NULL, NULL, 330500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330522, '长兴县', 'Changxing', NULL, NULL, NULL, 330500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330523, '安吉县', 'Anji', NULL, NULL, NULL, 330500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330600, '绍兴市', 'Shaoxing', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330602, '越城区', 'Yuecheng', NULL, NULL, NULL, 330600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330603, '柯桥区', 'Keqiao ', NULL, NULL, NULL, 330600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330604, '上虞区', 'Shangyu', NULL, NULL, NULL, 330600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330624, '新昌县', 'Xinchang', NULL, NULL, NULL, 330600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330681, '诸暨市', 'Zhuji', NULL, NULL, NULL, 330600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330683, '嵊州市', 'Shengzhou', NULL, NULL, NULL, 330600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330700, '金华市', 'Jinhua', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330702, '婺城区', 'Wucheng', NULL, NULL, NULL, 330700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330703, '金东区', 'Jindong', NULL, NULL, NULL, 330700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330723, '武义县', 'Wuyi', NULL, NULL, NULL, 330700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330726, '浦江县', 'Pujiang', NULL, NULL, NULL, 330700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330727, '磐安县', 'Pan\'an', NULL, NULL, NULL, 330700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330781, '兰溪市', 'Lanxi', NULL, NULL, NULL, 330700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330782, '义乌市', 'Yiwu', NULL, NULL, NULL, 330700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330783, '东阳市', 'Dongyang', NULL, NULL, NULL, 330700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330784, '永康市', 'Yongkang', NULL, NULL, NULL, 330700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330800, '衢州市', 'Quzhou', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330802, '柯城区', 'Kecheng', NULL, NULL, NULL, 330800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330803, '衢江区', 'Qujiang', NULL, NULL, NULL, 330800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330822, '常山县', 'Changshan', NULL, NULL, NULL, 330800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330824, '开化县', 'Kaihua', NULL, NULL, NULL, 330800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330825, '龙游县', 'Longyou', NULL, NULL, NULL, 330800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330881, '江山市', 'Jiangshan', NULL, NULL, NULL, 330800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330900, '舟山市', 'Zhoushan', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330902, '定海区', 'Dinghai', NULL, NULL, NULL, 330900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330903, '普陀区', 'Putuo', NULL, NULL, NULL, 330900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330921, '岱山县', 'Daishan', NULL, NULL, NULL, 330900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (330922, '嵊泗县', 'Shengsi', NULL, NULL, NULL, 330900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331000, '台州市', 'Taizhou', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331002, '椒江区', 'Jiaojiang', NULL, NULL, NULL, 331000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331003, '黄岩区', 'Huangyan', NULL, NULL, NULL, 331000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331004, '路桥区', 'Luqiao', NULL, NULL, NULL, 331000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331021, '玉环县', 'Yuhuan', NULL, NULL, NULL, 331000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331022, '三门县', 'Sanmen', NULL, NULL, NULL, 331000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331023, '天台县', 'Tiantai', NULL, NULL, NULL, 331000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331024, '仙居县', 'Xianju', NULL, NULL, NULL, 331000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331081, '温岭市', 'Wenling', NULL, NULL, NULL, 331000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331082, '临海市', 'Linhai', NULL, NULL, NULL, 331000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331100, '丽水市', 'Lishui', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331102, '莲都区', 'Liandu', NULL, NULL, NULL, 331100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331121, '青田县', 'Qingtian', NULL, NULL, NULL, 331100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331122, '缙云县', 'Jinyun', NULL, NULL, NULL, 331100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331123, '遂昌县', 'Suichang', NULL, NULL, NULL, 331100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331124, '松阳县', 'Songyang', NULL, NULL, NULL, 331100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331125, '云和县', 'Yunhe', NULL, NULL, NULL, 331100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331126, '庆元县', 'Qingyuan', NULL, NULL, NULL, 331100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331127, '景宁畲族自治县', 'Jingning', NULL, NULL, NULL, 331100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331181, '龙泉市', 'Longquan', NULL, NULL, NULL, 331100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331200, '舟山群岛新区', 'Zhoushan', NULL, NULL, NULL, 330000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331201, '金塘岛', 'Jintang', NULL, NULL, NULL, 331200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331202, '六横岛', 'Liuheng', NULL, NULL, NULL, 331200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331203, '衢山岛', 'Qushan', NULL, NULL, NULL, 331200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331204, '舟山本岛西北部', 'Zhoushan', NULL, NULL, NULL, 331200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331205, '岱山岛西南部', 'Daishan', NULL, NULL, NULL, 331200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331206, '泗礁岛', 'Sijiao', NULL, NULL, NULL, 331200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331207, '朱家尖岛', 'Zhujiajian', NULL, NULL, NULL, 331200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331208, '洋山岛', 'Yangshan', NULL, NULL, NULL, 331200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331209, '长涂岛', 'Changtu', NULL, NULL, NULL, 331200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (331210, '虾峙岛', 'Xiazhi', NULL, NULL, NULL, 331200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340000, '安徽省', 'Anhui', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340100, '合肥市', 'Hefei', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340102, '瑶海区', 'Yaohai', NULL, NULL, NULL, 340100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340103, '庐阳区', 'Luyang', NULL, NULL, NULL, 340100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340104, '蜀山区', 'Shushan', NULL, NULL, NULL, 340100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340111, '包河区', 'Baohe', NULL, NULL, NULL, 340100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340121, '长丰县', 'Changfeng', NULL, NULL, NULL, 340100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340122, '肥东县', 'Feidong', NULL, NULL, NULL, 340100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340123, '肥西县', 'Feixi', NULL, NULL, NULL, 340100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340124, '庐江县', 'Lujiang', NULL, NULL, NULL, 340100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340181, '巢湖市', 'Chaohu', NULL, NULL, NULL, 340100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340200, '芜湖市', 'Wuhu', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340202, '镜湖区', 'Jinghu', NULL, NULL, NULL, 340200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340203, '弋江区', 'Yijiang', NULL, NULL, NULL, 340200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340207, '鸠江区', 'Jiujiang', NULL, NULL, NULL, 340200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340208, '三山区', 'Sanshan', NULL, NULL, NULL, 340200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340221, '芜湖县', 'Wuhu', NULL, NULL, NULL, 340200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340222, '繁昌县', 'Fanchang', NULL, NULL, NULL, 340200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340223, '南陵县', 'Nanling', NULL, NULL, NULL, 340200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340225, '无为县', 'Wuwei', NULL, NULL, NULL, 340200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340300, '蚌埠市', 'Bengbu', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340302, '龙子湖区', 'Longzihu', NULL, NULL, NULL, 340300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340303, '蚌山区', 'Bengshan', NULL, NULL, NULL, 340300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340304, '禹会区', 'Yuhui', NULL, NULL, NULL, 340300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340311, '淮上区', 'Huaishang', NULL, NULL, NULL, 340300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340321, '怀远县', 'Huaiyuan', NULL, NULL, NULL, 340300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340322, '五河县', 'Wuhe', NULL, NULL, NULL, 340300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340323, '固镇县', 'Guzhen', NULL, NULL, NULL, 340300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340400, '淮南市', 'Huainan', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340402, '大通区', 'Datong', NULL, NULL, NULL, 340400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340403, '田家庵区', 'Tianjiaan', NULL, NULL, NULL, 340400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340404, '谢家集区', 'Xiejiaji', NULL, NULL, NULL, 340400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340405, '八公山区', 'Bagongshan', NULL, NULL, NULL, 340400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340406, '潘集区', 'Panji', NULL, NULL, NULL, 340400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340421, '凤台县', 'Fengtai', NULL, NULL, NULL, 340400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340500, '马鞍山市', 'Ma\'anshan', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340503, '花山区', 'Huashan', NULL, NULL, NULL, 340500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340504, '雨山区', 'Yushan', NULL, NULL, NULL, 340500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340506, '博望区', 'Bowang', NULL, NULL, NULL, 340500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340521, '当涂县', 'Dangtu', NULL, NULL, NULL, 340500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340522, '含山县', 'Hanshan ', NULL, NULL, NULL, 340500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340523, '和县', 'Hexian', NULL, NULL, NULL, 340500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340600, '淮北市', 'Huaibei', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340602, '杜集区', 'Duji', NULL, NULL, NULL, 340600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340603, '相山区', 'Xiangshan', NULL, NULL, NULL, 340600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340604, '烈山区', 'Lieshan', NULL, NULL, NULL, 340600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340621, '濉溪县', 'Suixi', NULL, NULL, NULL, 340600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340700, '铜陵市', 'Tongling', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340702, '铜官山区', 'Tongguanshan', NULL, NULL, NULL, 340700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340703, '狮子山区', 'Shizishan', NULL, NULL, NULL, 340700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340711, '郊区', 'Jiaoqu', NULL, NULL, NULL, 340700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340721, '铜陵县', 'Tongling', NULL, NULL, NULL, 340700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340800, '安庆市', 'Anqing', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340802, '迎江区', 'Yingjiang', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340803, '大观区', 'Daguan', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340811, '宜秀区', 'Yixiu', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340822, '怀宁县', 'Huaining', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340823, '枞阳县', 'Zongyang', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340824, '潜山县', 'Qianshan', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340825, '太湖县', 'Taihu', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340826, '宿松县', 'Susong', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340827, '望江县', 'Wangjiang', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340828, '岳西县', 'Yuexi', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (340881, '桐城市', 'Tongcheng', NULL, NULL, NULL, 340800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341000, '黄山市', 'Huangshan', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341002, '屯溪区', 'Tunxi', NULL, NULL, NULL, 341000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341003, '黄山区', 'Huangshan', NULL, NULL, NULL, 341000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341004, '徽州区', 'Huizhou', NULL, NULL, NULL, 341000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341021, '歙县', 'Shexian', NULL, NULL, NULL, 341000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341022, '休宁县', 'Xiuning', NULL, NULL, NULL, 341000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341023, '黟县', 'Yixian', NULL, NULL, NULL, 341000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341024, '祁门县', 'Qimen', NULL, NULL, NULL, 341000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341100, '滁州市', 'Chuzhou', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341102, '琅琊区', 'Langya', NULL, NULL, NULL, 341100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341103, '南谯区', 'Nanqiao', NULL, NULL, NULL, 341100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341122, '来安县', 'Lai\'an', NULL, NULL, NULL, 341100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341124, '全椒县', 'Quanjiao', NULL, NULL, NULL, 341100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341125, '定远县', 'Dingyuan', NULL, NULL, NULL, 341100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341126, '凤阳县', 'Fengyang', NULL, NULL, NULL, 341100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341181, '天长市', 'Tianchang', NULL, NULL, NULL, 341100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341182, '明光市', 'Mingguang', NULL, NULL, NULL, 341100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341200, '阜阳市', 'Fuyang', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341202, '颍州区', 'Yingzhou', NULL, NULL, NULL, 341200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341203, '颍东区', 'Yingdong', NULL, NULL, NULL, 341200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341204, '颍泉区', 'Yingquan', NULL, NULL, NULL, 341200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341221, '临泉县', 'Linquan', NULL, NULL, NULL, 341200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341222, '太和县', 'Taihe', NULL, NULL, NULL, 341200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341225, '阜南县', 'Funan', NULL, NULL, NULL, 341200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341226, '颍上县', 'Yingshang', NULL, NULL, NULL, 341200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341282, '界首市', 'Jieshou', NULL, NULL, NULL, 341200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341300, '宿州市', 'Suzhou', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341302, '埇桥区', 'Yongqiao', NULL, NULL, NULL, 341300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341321, '砀山县', 'Dangshan', NULL, NULL, NULL, 341300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341322, '萧县', 'Xiaoxian', NULL, NULL, NULL, 341300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341323, '灵璧县', 'Lingbi', NULL, NULL, NULL, 341300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341324, '泗县', 'Sixian', NULL, NULL, NULL, 341300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341500, '六安市', 'Lu\'an', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341502, '金安区', 'Jin\'an', NULL, NULL, NULL, 341500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341503, '裕安区', 'Yu\'an', NULL, NULL, NULL, 341500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341521, '寿县', 'Shouxian', NULL, NULL, NULL, 341500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341522, '霍邱县', 'Huoqiu', NULL, NULL, NULL, 341500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341523, '舒城县', 'Shucheng', NULL, NULL, NULL, 341500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341524, '金寨县', 'Jinzhai', NULL, NULL, NULL, 341500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341525, '霍山县', 'Huoshan', NULL, NULL, NULL, 341500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341600, '亳州市', 'Bozhou', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341602, '谯城区', 'Qiaocheng', NULL, NULL, NULL, 341600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341621, '涡阳县', 'Guoyang', NULL, NULL, NULL, 341600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341622, '蒙城县', 'Mengcheng', NULL, NULL, NULL, 341600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341623, '利辛县', 'Lixin', NULL, NULL, NULL, 341600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341700, '池州市', 'Chizhou', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341702, '贵池区', 'Guichi', NULL, NULL, NULL, 341700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341721, '东至县', 'Dongzhi', NULL, NULL, NULL, 341700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341722, '石台县', 'Shitai', NULL, NULL, NULL, 341700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341723, '青阳县', 'Qingyang', NULL, NULL, NULL, 341700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341800, '宣城市', 'Xuancheng', NULL, NULL, NULL, 340000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341802, '宣州区', 'Xuanzhou', NULL, NULL, NULL, 341800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341821, '郎溪县', 'Langxi', NULL, NULL, NULL, 341800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341822, '广德县', 'Guangde', NULL, NULL, NULL, 341800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341823, '泾县', 'Jingxian', NULL, NULL, NULL, 341800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341824, '绩溪县', 'Jixi', NULL, NULL, NULL, 341800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341825, '旌德县', 'Jingde', NULL, NULL, NULL, 341800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (341881, '宁国市', 'Ningguo', NULL, NULL, NULL, 341800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350000, '福建省', 'Fujian', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350100, '福州市', 'Fuzhou', NULL, NULL, NULL, 350000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350102, '鼓楼区', 'Gulou', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350103, '台江区', 'Taijiang', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350104, '仓山区', 'Cangshan', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350105, '马尾区', 'Mawei', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350111, '晋安区', 'Jin\'an', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350121, '闽侯县', 'Minhou', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350122, '连江县', 'Lianjiang', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350123, '罗源县', 'Luoyuan', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350124, '闽清县', 'Minqing', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350125, '永泰县', 'Yongtai', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350128, '平潭县', 'Pingtan', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350181, '福清市', 'Fuqing', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350182, '长乐市', 'Changle', NULL, NULL, NULL, 350100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350200, '厦门市', 'Xiamen', NULL, NULL, NULL, 350000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350203, '思明区', 'Siming', NULL, NULL, NULL, 350200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350205, '海沧区', 'Haicang', NULL, NULL, NULL, 350200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350206, '湖里区', 'Huli', NULL, NULL, NULL, 350200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350211, '集美区', 'Jimei', NULL, NULL, NULL, 350200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350212, '同安区', 'Tong\'an', NULL, NULL, NULL, 350200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350213, '翔安区', 'Xiang\'an', NULL, NULL, NULL, 350200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350300, '莆田市', 'Putian', NULL, NULL, NULL, 350000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350302, '城厢区', 'Chengxiang', NULL, NULL, NULL, 350300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350303, '涵江区', 'Hanjiang', NULL, NULL, NULL, 350300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350304, '荔城区', 'Licheng', NULL, NULL, NULL, 350300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350305, '秀屿区', 'Xiuyu', NULL, NULL, NULL, 350300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350322, '仙游县', 'Xianyou', NULL, NULL, NULL, 350300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350400, '三明市', 'Sanming', NULL, NULL, NULL, 350000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350402, '梅列区', 'Meilie', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350403, '三元区', 'Sanyuan', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350421, '明溪县', 'Mingxi', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350423, '清流县', 'Qingliu', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350424, '宁化县', 'Ninghua', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350425, '大田县', 'Datian', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350426, '尤溪县', 'Youxi', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350427, '沙县', 'Shaxian', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350428, '将乐县', 'Jiangle', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350429, '泰宁县', 'Taining', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350430, '建宁县', 'Jianning', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350481, '永安市', 'Yong\'an', NULL, NULL, NULL, 350400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350500, '泉州市', 'Quanzhou', NULL, NULL, NULL, 350000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350502, '鲤城区', 'Licheng', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350503, '丰泽区', 'Fengze', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350504, '洛江区', 'Luojiang', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350505, '泉港区', 'Quangang', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350521, '惠安县', 'Hui\'an', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350524, '安溪县', 'Anxi', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350525, '永春县', 'Yongchun', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350526, '德化县', 'Dehua', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350527, '金门县', 'Jinmen', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350581, '石狮市', 'Shishi', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350582, '晋江市', 'Jinjiang', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350583, '南安市', 'Nan\'an', NULL, NULL, NULL, 350500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350600, '漳州市', 'Zhangzhou', NULL, NULL, NULL, 350000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350602, '芗城区', 'Xiangcheng', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350603, '龙文区', 'Longwen', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350622, '云霄县', 'Yunxiao', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350623, '漳浦县', 'Zhangpu', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350624, '诏安县', 'Zhao\'an', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350625, '长泰县', 'Changtai', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350626, '东山县', 'Dongshan', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350627, '南靖县', 'Nanjing', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350628, '平和县', 'Pinghe', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350629, '华安县', 'Hua\'an', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350681, '龙海市', 'Longhai', NULL, NULL, NULL, 350600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350700, '南平市', 'Nanping', NULL, NULL, NULL, 350000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350702, '延平区', 'Yanping', NULL, NULL, NULL, 350700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350703, '建阳区', 'Jianyang', NULL, NULL, NULL, 350700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350721, '顺昌县', 'Shunchang', NULL, NULL, NULL, 350700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350722, '浦城县', 'Pucheng', NULL, NULL, NULL, 350700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350723, '光泽县', 'Guangze', NULL, NULL, NULL, 350700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350724, '松溪县', 'Songxi', NULL, NULL, NULL, 350700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350725, '政和县', 'Zhenghe', NULL, NULL, NULL, 350700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350781, '邵武市', 'Shaowu', NULL, NULL, NULL, 350700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350782, '武夷山市', 'Wuyishan', NULL, NULL, NULL, 350700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350783, '建瓯市', 'Jianou', NULL, NULL, NULL, 350700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350800, '龙岩市', 'Longyan', NULL, NULL, NULL, 350000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350802, '新罗区', 'Xinluo', NULL, NULL, NULL, 350800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350821, '长汀县', 'Changting', NULL, NULL, NULL, 350800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350822, '永定区', 'Yongding', NULL, NULL, NULL, 350800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350823, '上杭县', 'Shanghang', NULL, NULL, NULL, 350800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350824, '武平县', 'Wuping', NULL, NULL, NULL, 350800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350825, '连城县', 'Liancheng', NULL, NULL, NULL, 350800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350881, '漳平市', 'Zhangping', NULL, NULL, NULL, 350800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350900, '宁德市', 'Ningde', NULL, NULL, NULL, 350000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350902, '蕉城区', 'Jiaocheng', NULL, NULL, NULL, 350900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350921, '霞浦县', 'Xiapu', NULL, NULL, NULL, 350900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350922, '古田县', 'Gutian', NULL, NULL, NULL, 350900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350923, '屏南县', 'Pingnan', NULL, NULL, NULL, 350900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350924, '寿宁县', 'Shouning', NULL, NULL, NULL, 350900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350925, '周宁县', 'Zhouning', NULL, NULL, NULL, 350900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350926, '柘荣县', 'Zherong', NULL, NULL, NULL, 350900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350981, '福安市', 'Fu\'an', NULL, NULL, NULL, 350900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (350982, '福鼎市', 'Fuding', NULL, NULL, NULL, 350900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360000, '江西省', 'Jiangxi', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360100, '南昌市', 'Nanchang', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360102, '东湖区', 'Donghu', NULL, NULL, NULL, 360100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360103, '西湖区', 'Xihu', NULL, NULL, NULL, 360100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360104, '青云谱区', 'Qingyunpu', NULL, NULL, NULL, 360100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360105, '湾里区', 'Wanli', NULL, NULL, NULL, 360100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360111, '青山湖区', 'Qingshanhu', NULL, NULL, NULL, 360100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360121, '南昌县', 'Nanchang', NULL, NULL, NULL, 360100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360122, '新建县', 'Xinjian', NULL, NULL, NULL, 360100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360123, '安义县', 'Anyi', NULL, NULL, NULL, 360100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360124, '进贤县', 'Jinxian', NULL, NULL, NULL, 360100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360200, '景德镇市', 'Jingdezhen', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360202, '昌江区', 'Changjiang', NULL, NULL, NULL, 360200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360203, '珠山区', 'Zhushan', NULL, NULL, NULL, 360200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360222, '浮梁县', 'Fuliang', NULL, NULL, NULL, 360200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360281, '乐平市', 'Leping', NULL, NULL, NULL, 360200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360300, '萍乡市', 'Pingxiang', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360302, '安源区', 'Anyuan', NULL, NULL, NULL, 360300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360313, '湘东区', 'Xiangdong', NULL, NULL, NULL, 360300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360321, '莲花县', 'Lianhua', NULL, NULL, NULL, 360300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360322, '上栗县', 'Shangli', NULL, NULL, NULL, 360300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360323, '芦溪县', 'Luxi', NULL, NULL, NULL, 360300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360400, '九江市', 'Jiujiang', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360402, '庐山区', 'Lushan', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360403, '浔阳区', 'Xunyang', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360421, '九江县', 'Jiujiang', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360423, '武宁县', 'Wuning', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360424, '修水县', 'Xiushui', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360425, '永修县', 'Yongxiu', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360426, '德安县', 'De\'an', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360427, '星子县', 'Xingzi', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360428, '都昌县', 'Duchang', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360429, '湖口县', 'Hukou', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360430, '彭泽县', 'Pengze', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360481, '瑞昌市', 'Ruichang', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360482, '共青城市', 'Gongqingcheng', NULL, NULL, NULL, 360400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360500, '新余市', 'Xinyu', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360502, '渝水区', 'Yushui', NULL, NULL, NULL, 360500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360521, '分宜县', 'Fenyi', NULL, NULL, NULL, 360500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360600, '鹰潭市', 'Yingtan', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360602, '月湖区', 'Yuehu', NULL, NULL, NULL, 360600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360622, '余江县', 'Yujiang', NULL, NULL, NULL, 360600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360681, '贵溪市', 'Guixi', NULL, NULL, NULL, 360600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360700, '赣州市', 'Ganzhou', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360702, '章贡区', 'Zhanggong', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360703, '南康区', 'Nankang', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360721, '赣县', 'Ganxian', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360722, '信丰县', 'Xinfeng', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360723, '大余县', 'Dayu', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360724, '上犹县', 'Shangyou', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360725, '崇义县', 'Chongyi', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360726, '安远县', 'Anyuan', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360727, '龙南县', 'Longnan', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360728, '定南县', 'Dingnan', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360729, '全南县', 'Quannan', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360730, '宁都县', 'Ningdu', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360731, '于都县', 'Yudu', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360732, '兴国县', 'Xingguo', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360733, '会昌县', 'Huichang', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360734, '寻乌县', 'Xunwu', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360735, '石城县', 'Shicheng', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360781, '瑞金市', 'Ruijin', NULL, NULL, NULL, 360700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360800, '吉安市', 'Ji\'an', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360802, '吉州区', 'Jizhou', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360803, '青原区', 'Qingyuan', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360821, '吉安县', 'Ji\'an', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360822, '吉水县', 'Jishui', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360823, '峡江县', 'Xiajiang', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360824, '新干县', 'Xingan', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360825, '永丰县', 'Yongfeng', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360826, '泰和县', 'Taihe', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360827, '遂川县', 'Suichuan', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360828, '万安县', 'Wan\'an', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360829, '安福县', 'Anfu', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360830, '永新县', 'Yongxin', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360881, '井冈山市', 'Jinggangshan', NULL, NULL, NULL, 360800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360900, '宜春市', 'Yichun', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360902, '袁州区', 'Yuanzhou', NULL, NULL, NULL, 360900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360921, '奉新县', 'Fengxin', NULL, NULL, NULL, 360900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360922, '万载县', 'Wanzai', NULL, NULL, NULL, 360900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360923, '上高县', 'Shanggao', NULL, NULL, NULL, 360900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360924, '宜丰县', 'Yifeng', NULL, NULL, NULL, 360900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360925, '靖安县', 'Jing\'an', NULL, NULL, NULL, 360900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360926, '铜鼓县', 'Tonggu', NULL, NULL, NULL, 360900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360981, '丰城市', 'Fengcheng', NULL, NULL, NULL, 360900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360982, '樟树市', 'Zhangshu', NULL, NULL, NULL, 360900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (360983, '高安市', 'Gao\'an', NULL, NULL, NULL, 360900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361000, '抚州市', 'Fuzhou', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361002, '临川区', 'Linchuan', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361021, '南城县', 'Nancheng', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361022, '黎川县', 'Lichuan', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361023, '南丰县', 'Nanfeng', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361024, '崇仁县', 'Chongren', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361025, '乐安县', 'Le\'an', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361026, '宜黄县', 'Yihuang', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361027, '金溪县', 'Jinxi', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361028, '资溪县', 'Zixi', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361029, '东乡县', 'Dongxiang', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361030, '广昌县', 'Guangchang', NULL, NULL, NULL, 361000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361100, '上饶市', 'Shangrao', NULL, NULL, NULL, 360000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361102, '信州区', 'Xinzhou', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361121, '上饶县', 'Shangrao', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361122, '广丰县', 'Guangfeng', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361123, '玉山县', 'Yushan', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361124, '铅山县', 'Yanshan', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361125, '横峰县', 'Hengfeng', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361126, '弋阳县', 'Yiyang', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361127, '余干县', 'Yugan', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361128, '鄱阳县', 'Poyang', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361129, '万年县', 'Wannian', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361130, '婺源县', 'Wuyuan', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (361181, '德兴市', 'Dexing', NULL, NULL, NULL, 361100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370000, '山东省', 'Shandong', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370100, '济南市', 'Jinan', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370102, '历下区', 'Lixia', NULL, NULL, NULL, 370100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370103, '市中区', 'Shizhongqu', NULL, NULL, NULL, 370100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370104, '槐荫区', 'Huaiyin', NULL, NULL, NULL, 370100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370105, '天桥区', 'Tianqiao', NULL, NULL, NULL, 370100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370112, '历城区', 'Licheng', NULL, NULL, NULL, 370100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370113, '长清区', 'Changqing', NULL, NULL, NULL, 370100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370124, '平阴县', 'Pingyin', NULL, NULL, NULL, 370100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370125, '济阳县', 'Jiyang', NULL, NULL, NULL, 370100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370126, '商河县', 'Shanghe', NULL, NULL, NULL, 370100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370181, '章丘市', 'Zhangqiu', NULL, NULL, NULL, 370100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370200, '青岛市', 'Qingdao', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370202, '市南区', 'Shinan', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370203, '市北区', 'Shibei', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370211, '黄岛区', 'Huangdao', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370212, '崂山区', 'Laoshan', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370213, '李沧区', 'Licang', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370214, '城阳区', 'Chengyang', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370281, '胶州市', 'Jiaozhou', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370282, '即墨市', 'Jimo', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370283, '平度市', 'Pingdu', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370285, '莱西市', 'Laixi', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370286, '西海岸新区', 'Xihai\'an', NULL, NULL, NULL, 370200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370300, '淄博市', 'Zibo', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370302, '淄川区', 'Zichuan', NULL, NULL, NULL, 370300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370303, '张店区', 'Zhangdian', NULL, NULL, NULL, 370300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370304, '博山区', 'Boshan', NULL, NULL, NULL, 370300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370305, '临淄区', 'Linzi', NULL, NULL, NULL, 370300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370306, '周村区', 'Zhoucun', NULL, NULL, NULL, 370300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370321, '桓台县', 'Huantai', NULL, NULL, NULL, 370300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370322, '高青县', 'Gaoqing', NULL, NULL, NULL, 370300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370323, '沂源县', 'Yiyuan', NULL, NULL, NULL, 370300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370400, '枣庄市', 'Zaozhuang', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370402, '市中区', 'Shizhongqu', NULL, NULL, NULL, 370400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370403, '薛城区', 'Xuecheng', NULL, NULL, NULL, 370400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370404, '峄城区', 'Yicheng', NULL, NULL, NULL, 370400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370405, '台儿庄区', 'Taierzhuang', NULL, NULL, NULL, 370400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370406, '山亭区', 'Shanting', NULL, NULL, NULL, 370400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370481, '滕州市', 'Tengzhou', NULL, NULL, NULL, 370400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370500, '东营市', 'Dongying', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370502, '东营区', 'Dongying', NULL, NULL, NULL, 370500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370503, '河口区', 'Hekou', NULL, NULL, NULL, 370500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370521, '垦利县', 'Kenli', NULL, NULL, NULL, 370500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370522, '利津县', 'Lijin', NULL, NULL, NULL, 370500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370523, '广饶县', 'Guangrao', NULL, NULL, NULL, 370500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370600, '烟台市', 'Yantai', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370602, '芝罘区', 'Zhifu', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370611, '福山区', 'Fushan', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370612, '牟平区', 'Muping', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370613, '莱山区', 'Laishan', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370634, '长岛县', 'Changdao', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370681, '龙口市', 'Longkou', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370682, '莱阳市', 'Laiyang', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370683, '莱州市', 'Laizhou', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370684, '蓬莱市', 'Penglai', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370685, '招远市', 'Zhaoyuan', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370686, '栖霞市', 'Qixia', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370687, '海阳市', 'Haiyang', NULL, NULL, NULL, 370600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370700, '潍坊市', 'Weifang', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370702, '潍城区', 'Weicheng', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370703, '寒亭区', 'Hanting', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370704, '坊子区', 'Fangzi', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370705, '奎文区', 'Kuiwen', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370724, '临朐县', 'Linqu', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370725, '昌乐县', 'Changle', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370781, '青州市', 'Qingzhou', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370782, '诸城市', 'Zhucheng', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370783, '寿光市', 'Shouguang', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370784, '安丘市', 'Anqiu', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370785, '高密市', 'Gaomi', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370786, '昌邑市', 'Changyi', NULL, NULL, NULL, 370700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370800, '济宁市', 'Jining', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370811, '任城区', 'Rencheng', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370812, '兖州区', 'Yanzhou ', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370826, '微山县', 'Weishan', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370827, '鱼台县', 'Yutai', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370828, '金乡县', 'Jinxiang', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370829, '嘉祥县', 'Jiaxiang', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370830, '汶上县', 'Wenshang', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370831, '泗水县', 'Sishui', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370832, '梁山县', 'Liangshan', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370881, '曲阜市', 'Qufu', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370883, '邹城市', 'Zoucheng', NULL, NULL, NULL, 370800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370900, '泰安市', 'Tai\'an', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370902, '泰山区', 'Taishan', NULL, NULL, NULL, 370900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370911, '岱岳区', 'Daiyue', NULL, NULL, NULL, 370900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370921, '宁阳县', 'Ningyang', NULL, NULL, NULL, 370900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370923, '东平县', 'Dongping', NULL, NULL, NULL, 370900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370982, '新泰市', 'Xintai', NULL, NULL, NULL, 370900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (370983, '肥城市', 'Feicheng', NULL, NULL, NULL, 370900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371000, '威海市', 'Weihai', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371002, '环翠区', 'Huancui', NULL, NULL, NULL, 371000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371003, '文登区', 'Wendeng', NULL, NULL, NULL, 371000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371082, '荣成市', 'Rongcheng', NULL, NULL, NULL, 371000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371083, '乳山市', 'Rushan', NULL, NULL, NULL, 371000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371100, '日照市', 'Rizhao', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371102, '东港区', 'Donggang', NULL, NULL, NULL, 371100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371103, '岚山区', 'Lanshan', NULL, NULL, NULL, 371100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371121, '五莲县', 'Wulian', NULL, NULL, NULL, 371100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371122, '莒县', 'Juxian', NULL, NULL, NULL, 371100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371200, '莱芜市', 'Laiwu', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371202, '莱城区', 'Laicheng', NULL, NULL, NULL, 371200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371203, '钢城区', 'Gangcheng', NULL, NULL, NULL, 371200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371300, '临沂市', 'Linyi', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371302, '兰山区', 'Lanshan', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371311, '罗庄区', 'Luozhuang', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371312, '河东区', 'Hedong', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371321, '沂南县', 'Yinan', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371322, '郯城县', 'Tancheng', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371323, '沂水县', 'Yishui', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371324, '兰陵县', 'Lanling', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371325, '费县', 'Feixian', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371326, '平邑县', 'Pingyi', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371327, '莒南县', 'Junan', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371328, '蒙阴县', 'Mengyin', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371329, '临沭县', 'Linshu', NULL, NULL, NULL, 371300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371400, '德州市', 'Dezhou', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371402, '德城区', 'Decheng', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371403, '陵城区', 'Lingcheng', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371422, '宁津县', 'Ningjin', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371423, '庆云县', 'Qingyun', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371424, '临邑县', 'Linyi', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371425, '齐河县', 'Qihe', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371426, '平原县', 'Pingyuan', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371427, '夏津县', 'Xiajin', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371428, '武城县', 'Wucheng', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371481, '乐陵市', 'Leling', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371482, '禹城市', 'Yucheng', NULL, NULL, NULL, 371400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371500, '聊城市', 'Liaocheng', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371502, '东昌府区', 'Dongchangfu', NULL, NULL, NULL, 371500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371521, '阳谷县', 'Yanggu', NULL, NULL, NULL, 371500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371522, '莘县', 'Shenxian', NULL, NULL, NULL, 371500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371523, '茌平县', 'Chiping', NULL, NULL, NULL, 371500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371524, '东阿县', 'Dong\'e', NULL, NULL, NULL, 371500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371525, '冠县', 'Guanxian', NULL, NULL, NULL, 371500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371526, '高唐县', 'Gaotang', NULL, NULL, NULL, 371500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371581, '临清市', 'Linqing', NULL, NULL, NULL, 371500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371600, '滨州市', 'Binzhou', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371602, '滨城区', 'Bincheng', NULL, NULL, NULL, 371600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371603, '沾化区', 'Zhanhua', NULL, NULL, NULL, 371600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371621, '惠民县', 'Huimin', NULL, NULL, NULL, 371600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371622, '阳信县', 'Yangxin', NULL, NULL, NULL, 371600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371623, '无棣县', 'Wudi', NULL, NULL, NULL, 371600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371625, '博兴县', 'Boxing', NULL, NULL, NULL, 371600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371626, '邹平县', 'Zouping', NULL, NULL, NULL, 371600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371627, '北海新区', 'Beihaixinqu', NULL, NULL, NULL, 371600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371700, '菏泽市', 'Heze', NULL, NULL, NULL, 370000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371702, '牡丹区', 'Mudan', NULL, NULL, NULL, 371700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371721, '曹县', 'Caoxian', NULL, NULL, NULL, 371700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371722, '单县', 'Shanxian', NULL, NULL, NULL, 371700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371723, '成武县', 'Chengwu', NULL, NULL, NULL, 371700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371724, '巨野县', 'Juye', NULL, NULL, NULL, 371700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371725, '郓城县', 'Yuncheng', NULL, NULL, NULL, 371700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371726, '鄄城县', 'Juancheng', NULL, NULL, NULL, 371700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371727, '定陶县', 'Dingtao', NULL, NULL, NULL, 371700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (371728, '东明县', 'Dongming', NULL, NULL, NULL, 371700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410000, '河南省', 'Henan', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410100, '郑州市', 'Zhengzhou', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410102, '中原区', 'Zhongyuan', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410103, '二七区', 'Erqi', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410104, '管城回族区', 'Guancheng', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410105, '金水区', 'Jinshui', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410106, '上街区', 'Shangjie', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410108, '惠济区', 'Huiji', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410122, '中牟县', 'Zhongmu', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410181, '巩义市', 'Gongyi', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410182, '荥阳市', 'Xingyang', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410183, '新密市', 'Xinmi', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410184, '新郑市', 'Xinzheng', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410185, '登封市', 'Dengfeng', NULL, NULL, NULL, 410100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410200, '开封市', 'Kaifeng', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410202, '龙亭区', 'Longting', NULL, NULL, NULL, 410200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410203, '顺河回族区', 'Shunhe', NULL, NULL, NULL, 410200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410204, '鼓楼区', 'Gulou', NULL, NULL, NULL, 410200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410205, '禹王台区', 'Yuwangtai', NULL, NULL, NULL, 410200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410212, '祥符区', 'Xiangfu', NULL, NULL, NULL, 410200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410221, '杞县', 'Qixian', NULL, NULL, NULL, 410200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410222, '通许县', 'Tongxu', NULL, NULL, NULL, 410200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410223, '尉氏县', 'Weishi', NULL, NULL, NULL, 410200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410225, '兰考县', 'Lankao', NULL, NULL, NULL, 410200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410300, '洛阳市', 'Luoyang', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410302, '老城区', 'Laocheng', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410303, '西工区', 'Xigong', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410304, '瀍河回族区', 'Chanhe', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410305, '涧西区', 'Jianxi', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410306, '吉利区', 'Jili', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410311, '洛龙区', 'Luolong', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410322, '孟津县', 'Mengjin', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410323, '新安县', 'Xin\'an', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410324, '栾川县', 'Luanchuan', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410325, '嵩县', 'Songxian', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410326, '汝阳县', 'Ruyang', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410327, '宜阳县', 'Yiyang', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410328, '洛宁县', 'Luoning', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410329, '伊川县', 'Yichuan', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410381, '偃师市', 'Yanshi', NULL, NULL, NULL, 410300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410400, '平顶山市', 'Pingdingshan', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410402, '新华区', 'Xinhua', NULL, NULL, NULL, 410400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410403, '卫东区', 'Weidong', NULL, NULL, NULL, 410400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410404, '石龙区', 'Shilong', NULL, NULL, NULL, 410400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410411, '湛河区', 'Zhanhe', NULL, NULL, NULL, 410400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410421, '宝丰县', 'Baofeng', NULL, NULL, NULL, 410400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410422, '叶县', 'Yexian', NULL, NULL, NULL, 410400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410423, '鲁山县', 'Lushan', NULL, NULL, NULL, 410400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410425, '郏县', 'Jiaxian', NULL, NULL, NULL, 410400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410481, '舞钢市', 'Wugang', NULL, NULL, NULL, 410400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410482, '汝州市', 'Ruzhou', NULL, NULL, NULL, 410400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410500, '安阳市', 'Anyang', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410502, '文峰区', 'Wenfeng', NULL, NULL, NULL, 410500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410503, '北关区', 'Beiguan', NULL, NULL, NULL, 410500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410505, '殷都区', 'Yindu', NULL, NULL, NULL, 410500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410506, '龙安区', 'Long\'an', NULL, NULL, NULL, 410500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410522, '安阳县', 'Anyang', NULL, NULL, NULL, 410500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410523, '汤阴县', 'Tangyin', NULL, NULL, NULL, 410500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410526, '滑县', 'Huaxian', NULL, NULL, NULL, 410500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410527, '内黄县', 'Neihuang', NULL, NULL, NULL, 410500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410581, '林州市', 'Linzhou', NULL, NULL, NULL, 410500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410600, '鹤壁市', 'Hebi', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410602, '鹤山区', 'Heshan', NULL, NULL, NULL, 410600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410603, '山城区', 'Shancheng', NULL, NULL, NULL, 410600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410611, '淇滨区', 'Qibin', NULL, NULL, NULL, 410600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410621, '浚县', 'Xunxian', NULL, NULL, NULL, 410600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410622, '淇县', 'Qixian', NULL, NULL, NULL, 410600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410700, '新乡市', 'Xinxiang', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410702, '红旗区', 'Hongqi', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410703, '卫滨区', 'Weibin', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410704, '凤泉区', 'Fengquan', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410711, '牧野区', 'Muye', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410721, '新乡县', 'Xinxiang', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410724, '获嘉县', 'Huojia', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410725, '原阳县', 'Yuanyang', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410726, '延津县', 'Yanjin', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410727, '封丘县', 'Fengqiu', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410728, '长垣县', 'Changyuan', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410781, '卫辉市', 'Weihui', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410782, '辉县市', 'Huixian', NULL, NULL, NULL, 410700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410800, '焦作市', 'Jiaozuo', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410802, '解放区', 'Jiefang', NULL, NULL, NULL, 410800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410803, '中站区', 'Zhongzhan', NULL, NULL, NULL, 410800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410804, '马村区', 'Macun', NULL, NULL, NULL, 410800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410811, '山阳区', 'Shanyang', NULL, NULL, NULL, 410800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410821, '修武县', 'Xiuwu', NULL, NULL, NULL, 410800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410822, '博爱县', 'Boai', NULL, NULL, NULL, 410800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410823, '武陟县', 'Wuzhi', NULL, NULL, NULL, 410800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410825, '温县', 'Wenxian', NULL, NULL, NULL, 410800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410882, '沁阳市', 'Qinyang', NULL, NULL, NULL, 410800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410883, '孟州市', 'Mengzhou', NULL, NULL, NULL, 410800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410900, '濮阳市', 'Puyang', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410902, '华龙区', 'Hualong', NULL, NULL, NULL, 410900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410922, '清丰县', 'Qingfeng', NULL, NULL, NULL, 410900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410923, '南乐县', 'Nanle', NULL, NULL, NULL, 410900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410926, '范县', 'Fanxian', NULL, NULL, NULL, 410900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410927, '台前县', 'Taiqian', NULL, NULL, NULL, 410900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (410928, '濮阳县', 'Puyang', NULL, NULL, NULL, 410900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411000, '许昌市', 'Xuchang', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411002, '魏都区', 'Weidu', NULL, NULL, NULL, 411000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411023, '许昌县', 'Xuchang', NULL, NULL, NULL, 411000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411024, '鄢陵县', 'Yanling', NULL, NULL, NULL, 411000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411025, '襄城县', 'Xiangcheng', NULL, NULL, NULL, 411000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411081, '禹州市', 'Yuzhou', NULL, NULL, NULL, 411000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411082, '长葛市', 'Changge', NULL, NULL, NULL, 411000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411100, '漯河市', 'Luohe', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411102, '源汇区', 'Yuanhui', NULL, NULL, NULL, 411100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411103, '郾城区', 'Yancheng', NULL, NULL, NULL, 411100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411104, '召陵区', 'Zhaoling', NULL, NULL, NULL, 411100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411121, '舞阳县', 'Wuyang', NULL, NULL, NULL, 411100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411122, '临颍县', 'Linying', NULL, NULL, NULL, 411100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411200, '三门峡市', 'Sanmenxia', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411202, '湖滨区', 'Hubin', NULL, NULL, NULL, 411200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411221, '渑池县', 'Mianchi', NULL, NULL, NULL, 411200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411222, '陕县', 'Shanxian', NULL, NULL, NULL, 411200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411224, '卢氏县', 'Lushi', NULL, NULL, NULL, 411200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411281, '义马市', 'Yima', NULL, NULL, NULL, 411200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411282, '灵宝市', 'Lingbao', NULL, NULL, NULL, 411200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411300, '南阳市', 'Nanyang', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411302, '宛城区', 'Wancheng', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411303, '卧龙区', 'Wolong', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411321, '南召县', 'Nanzhao', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411322, '方城县', 'Fangcheng', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411323, '西峡县', 'Xixia', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411324, '镇平县', 'Zhenping', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411325, '内乡县', 'Neixiang', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411326, '淅川县', 'Xichuan', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411327, '社旗县', 'Sheqi', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411328, '唐河县', 'Tanghe', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411329, '新野县', 'Xinye', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411330, '桐柏县', 'Tongbai', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411381, '邓州市', 'Dengzhou', NULL, NULL, NULL, 411300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411400, '商丘市', 'Shangqiu', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411402, '梁园区', 'Liangyuan', NULL, NULL, NULL, 411400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411403, '睢阳区', 'Suiyang', NULL, NULL, NULL, 411400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411421, '民权县', 'Minquan', NULL, NULL, NULL, 411400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411422, '睢县', 'Suixian', NULL, NULL, NULL, 411400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411423, '宁陵县', 'Ningling', NULL, NULL, NULL, 411400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411424, '柘城县', 'Zhecheng', NULL, NULL, NULL, 411400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411425, '虞城县', 'Yucheng', NULL, NULL, NULL, 411400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411426, '夏邑县', 'Xiayi', NULL, NULL, NULL, 411400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411481, '永城市', 'Yongcheng', NULL, NULL, NULL, 411400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411500, '信阳市', 'Xinyang', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411502, '浉河区', 'Shihe', NULL, NULL, NULL, 411500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411503, '平桥区', 'Pingqiao', NULL, NULL, NULL, 411500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411521, '罗山县', 'Luoshan', NULL, NULL, NULL, 411500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411522, '光山县', 'Guangshan', NULL, NULL, NULL, 411500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411523, '新县', 'Xinxian', NULL, NULL, NULL, 411500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411524, '商城县', 'Shangcheng', NULL, NULL, NULL, 411500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411525, '固始县', 'Gushi', NULL, NULL, NULL, 411500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411526, '潢川县', 'Huangchuan', NULL, NULL, NULL, 411500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411527, '淮滨县', 'Huaibin', NULL, NULL, NULL, 411500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411528, '息县', 'Xixian', NULL, NULL, NULL, 411500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411600, '周口市', 'Zhoukou', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411602, '川汇区', 'Chuanhui', NULL, NULL, NULL, 411600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411621, '扶沟县', 'Fugou', NULL, NULL, NULL, 411600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411622, '西华县', 'Xihua', NULL, NULL, NULL, 411600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411623, '商水县', 'Shangshui', NULL, NULL, NULL, 411600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411624, '沈丘县', 'Shenqiu', NULL, NULL, NULL, 411600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411625, '郸城县', 'Dancheng', NULL, NULL, NULL, 411600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411626, '淮阳县', 'Huaiyang', NULL, NULL, NULL, 411600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411627, '太康县', 'Taikang', NULL, NULL, NULL, 411600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411628, '鹿邑县', 'Luyi', NULL, NULL, NULL, 411600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411681, '项城市', 'Xiangcheng', NULL, NULL, NULL, 411600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411700, '驻马店市', 'Zhumadian', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411702, '驿城区', 'Yicheng', NULL, NULL, NULL, 411700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411721, '西平县', 'Xiping', NULL, NULL, NULL, 411700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411722, '上蔡县', 'Shangcai', NULL, NULL, NULL, 411700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411723, '平舆县', 'Pingyu', NULL, NULL, NULL, 411700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411724, '正阳县', 'Zhengyang', NULL, NULL, NULL, 411700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411725, '确山县', 'Queshan', NULL, NULL, NULL, 411700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411726, '泌阳县', 'Biyang', NULL, NULL, NULL, 411700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411727, '汝南县', 'Runan', NULL, NULL, NULL, 411700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411728, '遂平县', 'Suiping', NULL, NULL, NULL, 411700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (411729, '新蔡县', 'Xincai', NULL, NULL, NULL, 411700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (419000, '直辖县级', '', NULL, NULL, NULL, 410000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (419001, '济源市', 'Jiyuan', NULL, NULL, NULL, 419000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420000, '湖北省', 'Hubei', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420100, '武汉市', 'Wuhan', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420102, '江岸区', 'Jiang\'an', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420103, '江汉区', 'Jianghan', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420104, '硚口区', 'Qiaokou', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420105, '汉阳区', 'Hanyang', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420106, '武昌区', 'Wuchang', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420107, '青山区', 'Qingshan', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420111, '洪山区', 'Hongshan', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420112, '东西湖区', 'Dongxihu', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420113, '汉南区', 'Hannan', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420114, '蔡甸区', 'Caidian', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420115, '江夏区', 'Jiangxia', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420116, '黄陂区', 'Huangpi', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420117, '新洲区', 'Xinzhou', NULL, NULL, NULL, 420100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420200, '黄石市', 'Huangshi', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420202, '黄石港区', 'Huangshigang', NULL, NULL, NULL, 420200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420203, '西塞山区', 'Xisaishan', NULL, NULL, NULL, 420200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420204, '下陆区', 'Xialu', NULL, NULL, NULL, 420200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420205, '铁山区', 'Tieshan', NULL, NULL, NULL, 420200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420222, '阳新县', 'Yangxin', NULL, NULL, NULL, 420200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420281, '大冶市', 'Daye', NULL, NULL, NULL, 420200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420300, '十堰市', 'Shiyan', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420302, '茅箭区', 'Maojian', NULL, NULL, NULL, 420300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420303, '张湾区', 'Zhangwan', NULL, NULL, NULL, 420300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420304, '郧阳区', 'Yunyang', NULL, NULL, NULL, 420300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420322, '郧西县', 'Yunxi', NULL, NULL, NULL, 420300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420323, '竹山县', 'Zhushan', NULL, NULL, NULL, 420300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420324, '竹溪县', 'Zhuxi', NULL, NULL, NULL, 420300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420325, '房县', 'Fangxian', NULL, NULL, NULL, 420300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420381, '丹江口市', 'Danjiangkou', NULL, NULL, NULL, 420300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420500, '宜昌市', 'Yichang', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420502, '西陵区', 'Xiling', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420503, '伍家岗区', 'Wujiagang', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420504, '点军区', 'Dianjun', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420505, '猇亭区', 'Xiaoting', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420506, '夷陵区', 'Yiling', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420525, '远安县', 'Yuan\'an', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420526, '兴山县', 'Xingshan', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420527, '秭归县', 'Zigui', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420528, '长阳土家族自治县', 'Changyang', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420529, '五峰土家族自治县', 'Wufeng', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420581, '宜都市', 'Yidu', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420582, '当阳市', 'Dangyang', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420583, '枝江市', 'Zhijiang', NULL, NULL, NULL, 420500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420600, '襄阳市', 'Xiangyang', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420602, '襄城区', 'Xiangcheng', NULL, NULL, NULL, 420600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420606, '樊城区', 'Fancheng', NULL, NULL, NULL, 420600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420607, '襄州区', 'Xiangzhou', NULL, NULL, NULL, 420600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420624, '南漳县', 'Nanzhang', NULL, NULL, NULL, 420600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420625, '谷城县', 'Gucheng', NULL, NULL, NULL, 420600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420626, '保康县', 'Baokang', NULL, NULL, NULL, 420600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420682, '老河口市', 'Laohekou', NULL, NULL, NULL, 420600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420683, '枣阳市', 'Zaoyang', NULL, NULL, NULL, 420600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420684, '宜城市', 'Yicheng', NULL, NULL, NULL, 420600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420700, '鄂州市', 'Ezhou', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420702, '梁子湖区', 'Liangzihu', NULL, NULL, NULL, 420700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420703, '华容区', 'Huarong', NULL, NULL, NULL, 420700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420704, '鄂城区', 'Echeng', NULL, NULL, NULL, 420700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420800, '荆门市', 'Jingmen', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420802, '东宝区', 'Dongbao', NULL, NULL, NULL, 420800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420804, '掇刀区', 'Duodao', NULL, NULL, NULL, 420800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420821, '京山县', 'Jingshan', NULL, NULL, NULL, 420800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420822, '沙洋县', 'Shayang', NULL, NULL, NULL, 420800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420881, '钟祥市', 'Zhongxiang', NULL, NULL, NULL, 420800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420900, '孝感市', 'Xiaogan', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420902, '孝南区', 'Xiaonan', NULL, NULL, NULL, 420900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420921, '孝昌县', 'Xiaochang', NULL, NULL, NULL, 420900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420922, '大悟县', 'Dawu', NULL, NULL, NULL, 420900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420923, '云梦县', 'Yunmeng', NULL, NULL, NULL, 420900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420981, '应城市', 'Yingcheng', NULL, NULL, NULL, 420900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420982, '安陆市', 'Anlu', NULL, NULL, NULL, 420900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (420984, '汉川市', 'Hanchuan', NULL, NULL, NULL, 420900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421000, '荆州市', 'Jingzhou', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421002, '沙市区', 'Shashi', NULL, NULL, NULL, 421000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421003, '荆州区', 'Jingzhou', NULL, NULL, NULL, 421000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421022, '公安县', 'Gong\'an', NULL, NULL, NULL, 421000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421023, '监利县', 'Jianli', NULL, NULL, NULL, 421000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421024, '江陵县', 'Jiangling', NULL, NULL, NULL, 421000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421081, '石首市', 'Shishou', NULL, NULL, NULL, 421000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421083, '洪湖市', 'Honghu', NULL, NULL, NULL, 421000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421087, '松滋市', 'Songzi', NULL, NULL, NULL, 421000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421100, '黄冈市', 'Huanggang', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421102, '黄州区', 'Huangzhou', NULL, NULL, NULL, 421100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421121, '团风县', 'Tuanfeng', NULL, NULL, NULL, 421100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421122, '红安县', 'Hong\'an', NULL, NULL, NULL, 421100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421123, '罗田县', 'Luotian', NULL, NULL, NULL, 421100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421124, '英山县', 'Yingshan', NULL, NULL, NULL, 421100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421125, '浠水县', 'Xishui', NULL, NULL, NULL, 421100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421126, '蕲春县', 'Qichun', NULL, NULL, NULL, 421100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421127, '黄梅县', 'Huangmei', NULL, NULL, NULL, 421100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421181, '麻城市', 'Macheng', NULL, NULL, NULL, 421100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421182, '武穴市', 'Wuxue', NULL, NULL, NULL, 421100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421200, '咸宁市', 'Xianning', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421202, '咸安区', 'Xian\'an', NULL, NULL, NULL, 421200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421221, '嘉鱼县', 'Jiayu', NULL, NULL, NULL, 421200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421222, '通城县', 'Tongcheng', NULL, NULL, NULL, 421200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421223, '崇阳县', 'Chongyang', NULL, NULL, NULL, 421200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421224, '通山县', 'Tongshan', NULL, NULL, NULL, 421200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421281, '赤壁市', 'Chibi', NULL, NULL, NULL, 421200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421300, '随州市', 'Suizhou', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421303, '曾都区', 'Zengdu', NULL, NULL, NULL, 421300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421321, '随县', 'Suixian', NULL, NULL, NULL, 421300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (421381, '广水市', 'Guangshui', NULL, NULL, NULL, 421300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (422800, '恩施土家族苗族自治州', 'Enshi', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (422801, '恩施市', 'Enshi', NULL, NULL, NULL, 422800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (422802, '利川市', 'Lichuan', NULL, NULL, NULL, 422800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (422822, '建始县', 'Jianshi', NULL, NULL, NULL, 422800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (422823, '巴东县', 'Badong', NULL, NULL, NULL, 422800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (422825, '宣恩县', 'Xuanen', NULL, NULL, NULL, 422800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (422826, '咸丰县', 'Xianfeng', NULL, NULL, NULL, 422800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (422827, '来凤县', 'Laifeng', NULL, NULL, NULL, 422800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (422828, '鹤峰县', 'Hefeng', NULL, NULL, NULL, 422800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (429000, '直辖县级', '', NULL, NULL, NULL, 420000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (429004, '仙桃市', 'Xiantao', NULL, NULL, NULL, 429000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (429005, '潜江市', 'Qianjiang', NULL, NULL, NULL, 429000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (429006, '天门市', 'Tianmen', NULL, NULL, NULL, 429000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (429021, '神农架林区', 'Shennongjia', NULL, NULL, NULL, 429000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430000, '湖南省', 'Hunan', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430100, '长沙市', 'Changsha', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430102, '芙蓉区', 'Furong', NULL, NULL, NULL, 430100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430103, '天心区', 'Tianxin', NULL, NULL, NULL, 430100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430104, '岳麓区', 'Yuelu', NULL, NULL, NULL, 430100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430105, '开福区', 'Kaifu', NULL, NULL, NULL, 430100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430111, '雨花区', 'Yuhua', NULL, NULL, NULL, 430100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430112, '望城区', 'Wangcheng', NULL, NULL, NULL, 430100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430121, '长沙县', 'Changsha', NULL, NULL, NULL, 430100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430124, '宁乡县', 'Ningxiang', NULL, NULL, NULL, 430100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430181, '浏阳市', 'Liuyang', NULL, NULL, NULL, 430100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430200, '株洲市', 'Zhuzhou', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430202, '荷塘区', 'Hetang', NULL, NULL, NULL, 430200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430203, '芦淞区', 'Lusong', NULL, NULL, NULL, 430200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430204, '石峰区', 'Shifeng', NULL, NULL, NULL, 430200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430211, '天元区', 'Tianyuan', NULL, NULL, NULL, 430200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430221, '株洲县', 'Zhuzhou', NULL, NULL, NULL, 430200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430223, '攸县', 'Youxian', NULL, NULL, NULL, 430200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430224, '茶陵县', 'Chaling', NULL, NULL, NULL, 430200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430225, '炎陵县', 'Yanling', NULL, NULL, NULL, 430200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430281, '醴陵市', 'Liling', NULL, NULL, NULL, 430200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430300, '湘潭市', 'Xiangtan', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430302, '雨湖区', 'Yuhu', NULL, NULL, NULL, 430300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430304, '岳塘区', 'Yuetang', NULL, NULL, NULL, 430300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430321, '湘潭县', 'Xiangtan', NULL, NULL, NULL, 430300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430381, '湘乡市', 'Xiangxiang', NULL, NULL, NULL, 430300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430382, '韶山市', 'Shaoshan', NULL, NULL, NULL, 430300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430400, '衡阳市', 'Hengyang', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430405, '珠晖区', 'Zhuhui', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430406, '雁峰区', 'Yanfeng', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430407, '石鼓区', 'Shigu', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430408, '蒸湘区', 'Zhengxiang', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430412, '南岳区', 'Nanyue', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430421, '衡阳县', 'Hengyang', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430422, '衡南县', 'Hengnan', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430423, '衡山县', 'Hengshan', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430424, '衡东县', 'Hengdong', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430426, '祁东县', 'Qidong', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430481, '耒阳市', 'Leiyang', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430482, '常宁市', 'Changning', NULL, NULL, NULL, 430400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430500, '邵阳市', 'Shaoyang', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430502, '双清区', 'Shuangqing', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430503, '大祥区', 'Daxiang', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430511, '北塔区', 'Beita', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430521, '邵东县', 'Shaodong', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430522, '新邵县', 'Xinshao', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430523, '邵阳县', 'Shaoyang', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430524, '隆回县', 'Longhui', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430525, '洞口县', 'Dongkou', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430527, '绥宁县', 'Suining', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430528, '新宁县', 'Xinning', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430529, '城步苗族自治县', 'Chengbu', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430581, '武冈市', 'Wugang', NULL, NULL, NULL, 430500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430600, '岳阳市', 'Yueyang', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430602, '岳阳楼区', 'Yueyanglou', NULL, NULL, NULL, 430600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430603, '云溪区', 'Yunxi', NULL, NULL, NULL, 430600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430611, '君山区', 'Junshan', NULL, NULL, NULL, 430600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430621, '岳阳县', 'Yueyang', NULL, NULL, NULL, 430600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430623, '华容县', 'Huarong', NULL, NULL, NULL, 430600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430624, '湘阴县', 'Xiangyin', NULL, NULL, NULL, 430600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430626, '平江县', 'Pingjiang', NULL, NULL, NULL, 430600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430681, '汨罗市', 'Miluo', NULL, NULL, NULL, 430600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430682, '临湘市', 'Linxiang', NULL, NULL, NULL, 430600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430700, '常德市', 'Changde', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430702, '武陵区', 'Wuling', NULL, NULL, NULL, 430700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430703, '鼎城区', 'Dingcheng', NULL, NULL, NULL, 430700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430721, '安乡县', 'Anxiang', NULL, NULL, NULL, 430700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430722, '汉寿县', 'Hanshou', NULL, NULL, NULL, 430700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430723, '澧县', 'Lixian', NULL, NULL, NULL, 430700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430724, '临澧县', 'Linli', NULL, NULL, NULL, 430700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430725, '桃源县', 'Taoyuan', NULL, NULL, NULL, 430700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430726, '石门县', 'Shimen', NULL, NULL, NULL, 430700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430781, '津市市', 'Jinshi', NULL, NULL, NULL, 430700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430800, '张家界市', 'Zhangjiajie', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430802, '永定区', 'Yongding', NULL, NULL, NULL, 430800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430811, '武陵源区', 'Wulingyuan', NULL, NULL, NULL, 430800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430821, '慈利县', 'Cili', NULL, NULL, NULL, 430800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430822, '桑植县', 'Sangzhi', NULL, NULL, NULL, 430800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430900, '益阳市', 'Yiyang', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430902, '资阳区', 'Ziyang', NULL, NULL, NULL, 430900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430903, '赫山区', 'Heshan', NULL, NULL, NULL, 430900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430921, '南县', 'Nanxian', NULL, NULL, NULL, 430900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430922, '桃江县', 'Taojiang', NULL, NULL, NULL, 430900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430923, '安化县', 'Anhua', NULL, NULL, NULL, 430900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (430981, '沅江市', 'Yuanjiang', NULL, NULL, NULL, 430900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431000, '郴州市', 'Chenzhou', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431002, '北湖区', 'Beihu', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431003, '苏仙区', 'Suxian', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431021, '桂阳县', 'Guiyang', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431022, '宜章县', 'Yizhang', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431023, '永兴县', 'Yongxing', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431024, '嘉禾县', 'Jiahe', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431025, '临武县', 'Linwu', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431026, '汝城县', 'Rucheng', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431027, '桂东县', 'Guidong', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431028, '安仁县', 'Anren', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431081, '资兴市', 'Zixing', NULL, NULL, NULL, 431000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431100, '永州市', 'Yongzhou', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431102, '零陵区', 'Lingling', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431103, '冷水滩区', 'Lengshuitan', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431121, '祁阳县', 'Qiyang', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431122, '东安县', 'Dong\'an', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431123, '双牌县', 'Shuangpai', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431124, '道县', 'Daoxian', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431125, '江永县', 'Jiangyong', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431126, '宁远县', 'Ningyuan', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431127, '蓝山县', 'Lanshan', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431128, '新田县', 'Xintian', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431129, '江华瑶族自治县', 'Jianghua', NULL, NULL, NULL, 431100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431200, '怀化市', 'Huaihua', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431202, '鹤城区', 'Hecheng', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431221, '中方县', 'Zhongfang', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431222, '沅陵县', 'Yuanling', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431223, '辰溪县', 'Chenxi', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431224, '溆浦县', 'Xupu', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431225, '会同县', 'Huitong', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431226, '麻阳苗族自治县', 'Mayang', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431227, '新晃侗族自治县', 'Xinhuang', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431228, '芷江侗族自治县', 'Zhijiang', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431229, '靖州苗族侗族自治县', 'Jingzhou', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431230, '通道侗族自治县', 'Tongdao', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431281, '洪江市', 'Hongjiang', NULL, NULL, NULL, 431200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431300, '娄底市', 'Loudi', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431302, '娄星区', 'Louxing', NULL, NULL, NULL, 431300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431321, '双峰县', 'Shuangfeng', NULL, NULL, NULL, 431300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431322, '新化县', 'Xinhua', NULL, NULL, NULL, 431300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431381, '冷水江市', 'Lengshuijiang', NULL, NULL, NULL, 431300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (431382, '涟源市', 'Lianyuan', NULL, NULL, NULL, 431300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (433100, '湘西土家族苗族自治州', 'Xiangxi', NULL, NULL, NULL, 430000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (433101, '吉首市', 'Jishou', NULL, NULL, NULL, 433100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (433122, '泸溪县', 'Luxi', NULL, NULL, NULL, 433100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (433123, '凤凰县', 'Fenghuang', NULL, NULL, NULL, 433100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (433124, '花垣县', 'Huayuan', NULL, NULL, NULL, 433100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (433125, '保靖县', 'Baojing', NULL, NULL, NULL, 433100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (433126, '古丈县', 'Guzhang', NULL, NULL, NULL, 433100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (433127, '永顺县', 'Yongshun', NULL, NULL, NULL, 433100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (433130, '龙山县', 'Longshan', NULL, NULL, NULL, 433100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440000, '广东省', 'Guangdong', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440100, '广州市', 'Guangzhou', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440103, '荔湾区', 'Liwan', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440104, '越秀区', 'Yuexiu', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440105, '海珠区', 'Haizhu', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440106, '天河区', 'Tianhe', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440111, '白云区', 'Baiyun', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440112, '黄埔区', 'Huangpu', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440113, '番禺区', 'Panyu', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440114, '花都区', 'Huadu', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440115, '南沙区', 'Nansha', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440117, '从化区', 'Conghua', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440118, '增城区', 'Zengcheng', NULL, NULL, NULL, 440100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440200, '韶关市', 'Shaoguan', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440203, '武江区', 'Wujiang', NULL, NULL, NULL, 440200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440204, '浈江区', 'Zhenjiang', NULL, NULL, NULL, 440200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440205, '曲江区', 'Qujiang', NULL, NULL, NULL, 440200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440222, '始兴县', 'Shixing', NULL, NULL, NULL, 440200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440224, '仁化县', 'Renhua', NULL, NULL, NULL, 440200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440229, '翁源县', 'Wengyuan', NULL, NULL, NULL, 440200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440232, '乳源瑶族自治县', 'Ruyuan', NULL, NULL, NULL, 440200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440233, '新丰县', 'Xinfeng', NULL, NULL, NULL, 440200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440281, '乐昌市', 'Lechang', NULL, NULL, NULL, 440200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440282, '南雄市', 'Nanxiong', NULL, NULL, NULL, 440200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440300, '深圳市', 'Shenzhen', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440303, '罗湖区', 'Luohu', NULL, NULL, NULL, 440300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440304, '福田区', 'Futian', NULL, NULL, NULL, 440300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440305, '南山区', 'Nanshan', NULL, NULL, NULL, 440300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440306, '宝安区', 'Bao\'an', NULL, NULL, NULL, 440300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440307, '龙岗区', 'Longgang', NULL, NULL, NULL, 440300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440308, '盐田区', 'Yantian', NULL, NULL, NULL, 440300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440309, '光明新区', 'Guangmingxinqu', NULL, NULL, NULL, 440300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440310, '坪山新区', 'Pingshanxinqu', NULL, NULL, NULL, 440300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440311, '大鹏新区', 'Dapengxinqu', NULL, NULL, NULL, 440300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440312, '龙华新区', 'Longhuaxinqu', NULL, NULL, NULL, 440300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440400, '珠海市', 'Zhuhai', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440402, '香洲区', 'Xiangzhou', NULL, NULL, NULL, 440400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440403, '斗门区', 'Doumen', NULL, NULL, NULL, 440400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440404, '金湾区', 'Jinwan', NULL, NULL, NULL, 440400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440500, '汕头市', 'Shantou', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440507, '龙湖区', 'Longhu', NULL, NULL, NULL, 440500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440511, '金平区', 'Jinping', NULL, NULL, NULL, 440500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440512, '濠江区', 'Haojiang', NULL, NULL, NULL, 440500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440513, '潮阳区', 'Chaoyang', NULL, NULL, NULL, 440500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440514, '潮南区', 'Chaonan', NULL, NULL, NULL, 440500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440515, '澄海区', 'Chenghai', NULL, NULL, NULL, 440500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440523, '南澳县', 'Nanao', NULL, NULL, NULL, 440500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440600, '佛山市', 'Foshan', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440604, '禅城区', 'Chancheng', NULL, NULL, NULL, 440600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440605, '南海区', 'Nanhai', NULL, NULL, NULL, 440600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440606, '顺德区', 'Shunde', NULL, NULL, NULL, 440600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440607, '三水区', 'Sanshui', NULL, NULL, NULL, 440600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440608, '高明区', 'Gaoming', NULL, NULL, NULL, 440600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440700, '江门市', 'Jiangmen', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440703, '蓬江区', 'Pengjiang', NULL, NULL, NULL, 440700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440704, '江海区', 'Jianghai', NULL, NULL, NULL, 440700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440705, '新会区', 'Xinhui', NULL, NULL, NULL, 440700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440781, '台山市', 'Taishan', NULL, NULL, NULL, 440700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440783, '开平市', 'Kaiping', NULL, NULL, NULL, 440700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440784, '鹤山市', 'Heshan', NULL, NULL, NULL, 440700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440785, '恩平市', 'Enping', NULL, NULL, NULL, 440700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440800, '湛江市', 'Zhanjiang', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440802, '赤坎区', 'Chikan', NULL, NULL, NULL, 440800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440803, '霞山区', 'Xiashan', NULL, NULL, NULL, 440800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440804, '坡头区', 'Potou', NULL, NULL, NULL, 440800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440811, '麻章区', 'Mazhang', NULL, NULL, NULL, 440800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440823, '遂溪县', 'Suixi', NULL, NULL, NULL, 440800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440825, '徐闻县', 'Xuwen', NULL, NULL, NULL, 440800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440881, '廉江市', 'Lianjiang', NULL, NULL, NULL, 440800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440882, '雷州市', 'Leizhou', NULL, NULL, NULL, 440800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440883, '吴川市', 'Wuchuan', NULL, NULL, NULL, 440800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440900, '茂名市', 'Maoming', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440902, '茂南区', 'Maonan', NULL, NULL, NULL, 440900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440904, '电白区', 'Dianbai', NULL, NULL, NULL, 440900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440981, '高州市', 'Gaozhou', NULL, NULL, NULL, 440900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440982, '化州市', 'Huazhou', NULL, NULL, NULL, 440900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (440983, '信宜市', 'Xinyi', NULL, NULL, NULL, 440900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441200, '肇庆市', 'Zhaoqing', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441202, '端州区', 'Duanzhou', NULL, NULL, NULL, 441200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441203, '鼎湖区', 'Dinghu', NULL, NULL, NULL, 441200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441223, '广宁县', 'Guangning', NULL, NULL, NULL, 441200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441224, '怀集县', 'Huaiji', NULL, NULL, NULL, 441200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441225, '封开县', 'Fengkai', NULL, NULL, NULL, 441200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441226, '德庆县', 'Deqing', NULL, NULL, NULL, 441200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441283, '高要市', 'Gaoyao', NULL, NULL, NULL, 441200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441284, '四会市', 'Sihui', NULL, NULL, NULL, 441200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441300, '惠州市', 'Huizhou', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441302, '惠城区', 'Huicheng', NULL, NULL, NULL, 441300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441303, '惠阳区', 'Huiyang', NULL, NULL, NULL, 441300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441322, '博罗县', 'Boluo', NULL, NULL, NULL, 441300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441323, '惠东县', 'Huidong', NULL, NULL, NULL, 441300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441324, '龙门县', 'Longmen', NULL, NULL, NULL, 441300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441400, '梅州市', 'Meizhou', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441402, '梅江区', 'Meijiang', NULL, NULL, NULL, 441400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441403, '梅县区', 'Meixian', NULL, NULL, NULL, 441400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441422, '大埔县', 'Dabu', NULL, NULL, NULL, 441400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441423, '丰顺县', 'Fengshun', NULL, NULL, NULL, 441400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441424, '五华县', 'Wuhua', NULL, NULL, NULL, 441400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441426, '平远县', 'Pingyuan', NULL, NULL, NULL, 441400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441427, '蕉岭县', 'Jiaoling', NULL, NULL, NULL, 441400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441481, '兴宁市', 'Xingning', NULL, NULL, NULL, 441400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441500, '汕尾市', 'Shanwei', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441502, '城区', 'Chengqu', NULL, NULL, NULL, 441500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441521, '海丰县', 'Haifeng', NULL, NULL, NULL, 441500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441523, '陆河县', 'Luhe', NULL, NULL, NULL, 441500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441581, '陆丰市', 'Lufeng', NULL, NULL, NULL, 441500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441600, '河源市', 'Heyuan', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441602, '源城区', 'Yuancheng', NULL, NULL, NULL, 441600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441621, '紫金县', 'Zijin', NULL, NULL, NULL, 441600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441622, '龙川县', 'Longchuan', NULL, NULL, NULL, 441600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441623, '连平县', 'Lianping', NULL, NULL, NULL, 441600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441624, '和平县', 'Heping', NULL, NULL, NULL, 441600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441625, '东源县', 'Dongyuan', NULL, NULL, NULL, 441600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441700, '阳江市', 'Yangjiang', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441702, '江城区', 'Jiangcheng', NULL, NULL, NULL, 441700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441704, '阳东区', 'Yangdong', NULL, NULL, NULL, 441700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441721, '阳西县', 'Yangxi', NULL, NULL, NULL, 441700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441781, '阳春市', 'Yangchun', NULL, NULL, NULL, 441700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441800, '清远市', 'Qingyuan', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441802, '清城区', 'Qingcheng', NULL, NULL, NULL, 441800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441803, '清新区', 'Qingxin', NULL, NULL, NULL, 441800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441821, '佛冈县', 'Fogang', NULL, NULL, NULL, 441800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441823, '阳山县', 'Yangshan', NULL, NULL, NULL, 441800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441825, '连山壮族瑶族自治县', 'Lianshan', NULL, NULL, NULL, 441800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441826, '连南瑶族自治县', 'Liannan', NULL, NULL, NULL, 441800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441881, '英德市', 'Yingde', NULL, NULL, NULL, 441800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441882, '连州市', 'Lianzhou', NULL, NULL, NULL, 441800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441900, '东莞市', 'Dongguan', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441901, '莞城区', 'Guancheng', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441902, '南城区', 'Nancheng', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441904, '万江区', 'Wanjiang', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441905, '石碣镇', 'Shijie', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441906, '石龙镇', 'Shilong', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441907, '茶山镇', 'Chashan', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441908, '石排镇', 'Shipai', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441909, '企石镇', 'Qishi', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441910, '横沥镇', 'Hengli', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441911, '桥头镇', 'Qiaotou', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441912, '谢岗镇', 'Xiegang', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441913, '东坑镇', 'Dongkeng', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441914, '常平镇', 'Changping', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441915, '寮步镇', 'Liaobu', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441916, '大朗镇', 'Dalang', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441917, '麻涌镇', 'Machong', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441918, '中堂镇', 'Zhongtang', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441919, '高埗镇', 'Gaobu', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441920, '樟木头镇', 'Zhangmutou', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441921, '大岭山镇', 'Dalingshan', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441922, '望牛墩镇', 'Wangniudun', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441923, '黄江镇', 'Huangjiang', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441924, '洪梅镇', 'Hongmei', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441925, '清溪镇', 'Qingxi', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441926, '沙田镇', 'Shatian', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441927, '道滘镇', 'Daojiao', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441928, '塘厦镇', 'Tangxia', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441929, '虎门镇', 'Humen', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441930, '厚街镇', 'Houjie', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441931, '凤岗镇', 'Fenggang', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (441932, '长安镇', 'Chang\'an', NULL, NULL, NULL, 441900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442000, '中山市', 'Zhongshan', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442001, '石岐区', 'Shiqi', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442004, '南区', 'Nanqu', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442005, '五桂山区', 'Wuguishan', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442006, '火炬开发区', 'Huoju', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442007, '黄圃镇', 'Huangpu', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442008, '南头镇', 'Nantou', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442009, '东凤镇', 'Dongfeng', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442010, '阜沙镇', 'Fusha', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442011, '小榄镇', 'Xiaolan', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442012, '东升镇', 'Dongsheng', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442013, '古镇镇', 'Guzhen', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442014, '横栏镇', 'Henglan', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442015, '三角镇', 'Sanjiao', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442016, '民众镇', 'Minzhong', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442017, '南朗镇', 'Nanlang', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442018, '港口镇', 'Gangkou', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442019, '大涌镇', 'Dayong', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442020, '沙溪镇', 'Shaxi', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442021, '三乡镇', 'Sanxiang', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442022, '板芙镇', 'Banfu', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442023, '神湾镇', 'Shenwan', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (442024, '坦洲镇', 'Tanzhou', NULL, NULL, NULL, 442000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445100, '潮州市', 'Chaozhou', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445102, '湘桥区', 'Xiangqiao', NULL, NULL, NULL, 445100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445103, '潮安区', 'Chao\'an', NULL, NULL, NULL, 445100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445122, '饶平县', 'Raoping', NULL, NULL, NULL, 445100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445200, '揭阳市', 'Jieyang', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445202, '榕城区', 'Rongcheng', NULL, NULL, NULL, 445200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445203, '揭东区', 'Jiedong', NULL, NULL, NULL, 445200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445222, '揭西县', 'Jiexi', NULL, NULL, NULL, 445200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445224, '惠来县', 'Huilai', NULL, NULL, NULL, 445200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445281, '普宁市', 'Puning', NULL, NULL, NULL, 445200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445300, '云浮市', 'Yunfu', NULL, NULL, NULL, 440000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445302, '云城区', 'Yuncheng', NULL, NULL, NULL, 445300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445303, '云安区', 'Yun\'an', NULL, NULL, NULL, 445300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445321, '新兴县', 'Xinxing', NULL, NULL, NULL, 445300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445322, '郁南县', 'Yunan', NULL, NULL, NULL, 445300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (445381, '罗定市', 'Luoding', NULL, NULL, NULL, 445300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450000, '广西壮族自治区', 'Guangxi', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450100, '南宁市', 'Nanning', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450102, '兴宁区', 'Xingning', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450103, '青秀区', 'Qingxiu', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450105, '江南区', 'Jiangnan', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450107, '西乡塘区', 'Xixiangtang', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450108, '良庆区', 'Liangqing', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450109, '邕宁区', 'Yongning', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450122, '武鸣县', 'Wuming', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450123, '隆安县', 'Long\'an', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450124, '马山县', 'Mashan', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450125, '上林县', 'Shanglin', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450126, '宾阳县', 'Binyang', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450127, '横县', 'Hengxian', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450128, '埌东新区', 'Langdong', NULL, NULL, NULL, 450100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450200, '柳州市', 'Liuzhou', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450202, '城中区', 'Chengzhong', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450203, '鱼峰区', 'Yufeng', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450204, '柳南区', 'Liunan', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450205, '柳北区', 'Liubei', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450221, '柳江县', 'Liujiang', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450222, '柳城县', 'Liucheng', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450223, '鹿寨县', 'Luzhai', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450224, '融安县', 'Rong\'an', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450225, '融水苗族自治县', 'Rongshui', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450226, '三江侗族自治县', 'Sanjiang', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450227, '柳东新区', 'Liudong', NULL, NULL, NULL, 450200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450300, '桂林市', 'Guilin', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450302, '秀峰区', 'Xiufeng', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450303, '叠彩区', 'Diecai', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450304, '象山区', 'Xiangshan', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450305, '七星区', 'Qixing', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450311, '雁山区', 'Yanshan', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450312, '临桂区', 'Lingui', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450321, '阳朔县', 'Yangshuo', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450323, '灵川县', 'Lingchuan', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450324, '全州县', 'Quanzhou', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450325, '兴安县', 'Xing\'an', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450326, '永福县', 'Yongfu', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450327, '灌阳县', 'Guanyang', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450328, '龙胜各族自治县', 'Longsheng', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450329, '资源县', 'Ziyuan', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450330, '平乐县', 'Pingle', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450331, '荔浦县', 'Lipu', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450332, '恭城瑶族自治县', 'Gongcheng', NULL, NULL, NULL, 450300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450400, '梧州市', 'Wuzhou', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450403, '万秀区', 'Wanxiu', NULL, NULL, NULL, 450400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450405, '长洲区', 'Changzhou', NULL, NULL, NULL, 450400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450406, '龙圩区', 'Longxu', NULL, NULL, NULL, 450400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450421, '苍梧县', 'Cangwu', NULL, NULL, NULL, 450400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450422, '藤县', 'Tengxian', NULL, NULL, NULL, 450400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450423, '蒙山县', 'Mengshan', NULL, NULL, NULL, 450400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450481, '岑溪市', 'Cenxi', NULL, NULL, NULL, 450400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450500, '北海市', 'Beihai', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450502, '海城区', 'Haicheng', NULL, NULL, NULL, 450500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450503, '银海区', 'Yinhai', NULL, NULL, NULL, 450500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450512, '铁山港区', 'Tieshangang', NULL, NULL, NULL, 450500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450521, '合浦县', 'Hepu', NULL, NULL, NULL, 450500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450600, '防城港市', 'Fangchenggang', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450602, '港口区', 'Gangkou', NULL, NULL, NULL, 450600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450603, '防城区', 'Fangcheng', NULL, NULL, NULL, 450600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450621, '上思县', 'Shangsi', NULL, NULL, NULL, 450600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450681, '东兴市', 'Dongxing', NULL, NULL, NULL, 450600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450700, '钦州市', 'Qinzhou', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450702, '钦南区', 'Qinnan', NULL, NULL, NULL, 450700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450703, '钦北区', 'Qinbei', NULL, NULL, NULL, 450700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450721, '灵山县', 'Lingshan', NULL, NULL, NULL, 450700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450722, '浦北县', 'Pubei', NULL, NULL, NULL, 450700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450800, '贵港市', 'Guigang', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450802, '港北区', 'Gangbei', NULL, NULL, NULL, 450800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450803, '港南区', 'Gangnan', NULL, NULL, NULL, 450800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450804, '覃塘区', 'Qintang', NULL, NULL, NULL, 450800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450821, '平南县', 'Pingnan', NULL, NULL, NULL, 450800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450881, '桂平市', 'Guiping', NULL, NULL, NULL, 450800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450900, '玉林市', 'Yulin', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450902, '玉州区', 'Yuzhou', NULL, NULL, NULL, 450900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450903, '福绵区', 'Fumian', NULL, NULL, NULL, 450900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450904, '玉东新区', 'Yudong', NULL, NULL, NULL, 450900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450921, '容县', 'Rongxian', NULL, NULL, NULL, 450900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450922, '陆川县', 'Luchuan', NULL, NULL, NULL, 450900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450923, '博白县', 'Bobai', NULL, NULL, NULL, 450900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450924, '兴业县', 'Xingye', NULL, NULL, NULL, 450900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (450981, '北流市', 'Beiliu', NULL, NULL, NULL, 450900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451000, '百色市', 'Baise', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451002, '右江区', 'Youjiang', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451021, '田阳县', 'Tianyang', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451022, '田东县', 'Tiandong', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451023, '平果县', 'Pingguo', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451024, '德保县', 'Debao', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451025, '靖西县', 'Jingxi', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451026, '那坡县', 'Napo', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451027, '凌云县', 'Lingyun', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451028, '乐业县', 'Leye', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451029, '田林县', 'Tianlin', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451030, '西林县', 'Xilin', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451031, '隆林各族自治县', 'Longlin', NULL, NULL, NULL, 451000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451100, '贺州市', 'Hezhou', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451102, '八步区', 'Babu', NULL, NULL, NULL, 451100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451121, '昭平县', 'Zhaoping', NULL, NULL, NULL, 451100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451122, '钟山县', 'Zhongshan', NULL, NULL, NULL, 451100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451123, '富川瑶族自治县', 'Fuchuan', NULL, NULL, NULL, 451100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451124, '平桂管理区', 'Pingui', NULL, NULL, NULL, 451100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451200, '河池市', 'Hechi', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451202, '金城江区', 'Jinchengjiang', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451221, '南丹县', 'Nandan', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451222, '天峨县', 'Tiane', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451223, '凤山县', 'Fengshan', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451224, '东兰县', 'Donglan', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451225, '罗城仫佬族自治县', 'Luocheng', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451226, '环江毛南族自治县', 'Huanjiang', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451227, '巴马瑶族自治县', 'Bama', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451228, '都安瑶族自治县', 'Du\'an', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451229, '大化瑶族自治县', 'Dahua', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451281, '宜州市', 'Yizhou', NULL, NULL, NULL, 451200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451300, '来宾市', 'Laibin', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451302, '兴宾区', 'Xingbin', NULL, NULL, NULL, 451300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451321, '忻城县', 'Xincheng', NULL, NULL, NULL, 451300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451322, '象州县', 'Xiangzhou', NULL, NULL, NULL, 451300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451323, '武宣县', 'Wuxuan', NULL, NULL, NULL, 451300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451324, '金秀瑶族自治县', 'Jinxiu', NULL, NULL, NULL, 451300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451381, '合山市', 'Heshan', NULL, NULL, NULL, 451300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451400, '崇左市', 'Chongzuo', NULL, NULL, NULL, 450000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451402, '江州区', 'Jiangzhou', NULL, NULL, NULL, 451400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451421, '扶绥县', 'Fusui', NULL, NULL, NULL, 451400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451422, '宁明县', 'Ningming', NULL, NULL, NULL, 451400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451423, '龙州县', 'Longzhou', NULL, NULL, NULL, 451400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451424, '大新县', 'Daxin', NULL, NULL, NULL, 451400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451425, '天等县', 'Tiandeng', NULL, NULL, NULL, 451400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (451481, '凭祥市', 'Pingxiang', NULL, NULL, NULL, 451400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460000, '海南省', 'Hainan', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460100, '海口市', 'Haikou', NULL, NULL, NULL, 460000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460105, '秀英区', 'Xiuying', NULL, NULL, NULL, 460100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460106, '龙华区', 'Longhua', NULL, NULL, NULL, 460100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460107, '琼山区', 'Qiongshan', NULL, NULL, NULL, 460100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460108, '美兰区', 'Meilan', NULL, NULL, NULL, 460100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460200, '三亚市', 'Sanya', NULL, NULL, NULL, 460000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460202, '海棠区', 'Haitang', NULL, NULL, NULL, 460200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460203, '吉阳区', 'Jiyang', NULL, NULL, NULL, 460200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460204, '天涯区', 'Tianya', NULL, NULL, NULL, 460200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460205, '崖州区', 'Yazhou', NULL, NULL, NULL, 460200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460300, '三沙市', 'Sansha', NULL, NULL, NULL, 460000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460321, '西沙群岛', 'Xisha Islands', NULL, NULL, NULL, 460300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460322, '南沙群岛', 'Nansha Islands', NULL, NULL, NULL, 460300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (460323, '中沙群岛', 'Zhongsha Islands', NULL, NULL, NULL, 460300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469000, '直辖县级', '', NULL, NULL, NULL, 460000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469001, '五指山市', 'Wuzhishan', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469002, '琼海市', 'Qionghai', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469003, '儋州市', 'Danzhou', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469005, '文昌市', 'Wenchang', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469006, '万宁市', 'Wanning', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469007, '东方市', 'Dongfang', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469021, '定安县', 'Ding\'an', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469022, '屯昌县', 'Tunchang', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469023, '澄迈县', 'Chengmai', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469024, '临高县', 'Lingao', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469025, '白沙黎族自治县', 'Baisha', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469026, '昌江黎族自治县', 'Changjiang', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469027, '乐东黎族自治县', 'Ledong', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469028, '陵水黎族自治县', 'Lingshui', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469029, '保亭黎族苗族自治县', 'Baoting', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (469030, '琼中黎族苗族自治县', 'Qiongzhong', NULL, NULL, NULL, 469000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500000, '重庆', 'Chongqing', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500100, '重庆市', 'Chongqing', NULL, NULL, NULL, 500000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500101, '万州区', 'Wanzhou', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500102, '涪陵区', 'Fuling', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500103, '渝中区', 'Yuzhong', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500104, '大渡口区', 'Dadukou', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500105, '江北区', 'Jiangbei', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500106, '沙坪坝区', 'Shapingba', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500107, '九龙坡区', 'Jiulongpo', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500108, '南岸区', 'Nan\'an', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500109, '北碚区', 'Beibei', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500110, '綦江区', 'Qijiang', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500111, '大足区', 'Dazu', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500112, '渝北区', 'Yubei', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500113, '巴南区', 'Banan', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500114, '黔江区', 'Qianjiang', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500115, '长寿区', 'Changshou', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500116, '江津区', 'Jiangjin', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500117, '合川区', 'Hechuan', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500118, '永川区', 'Yongchuan', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500119, '南川区', 'Nanchuan', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500120, '璧山区', 'Bishan', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500151, '铜梁区', 'Tongliang', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500223, '潼南县', 'Tongnan', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500226, '荣昌县', 'Rongchang', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500228, '梁平县', 'Liangping', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500229, '城口县', 'Chengkou', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500230, '丰都县', 'Fengdu', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500231, '垫江县', 'Dianjiang', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500232, '武隆县', 'Wulong', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500233, '忠县', 'Zhongxian', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500234, '开县', 'Kaixian', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500235, '云阳县', 'Yunyang', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500236, '奉节县', 'Fengjie', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500237, '巫山县', 'Wushan', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500238, '巫溪县', 'Wuxi', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500240, '石柱土家族自治县', 'Shizhu', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500241, '秀山土家族苗族自治县', 'Xiushan', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500242, '酉阳土家族苗族自治县', 'Youyang', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500243, '彭水苗族土家族自治县', 'Pengshui', NULL, NULL, NULL, 500100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500300, '两江新区', 'Liangjiangxinqu', NULL, NULL, NULL, 500000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500301, '北部新区', 'Beibuxinqu', NULL, NULL, NULL, 500300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500302, '保税港区', 'Baoshuigangqu', NULL, NULL, NULL, 500300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (500303, '工业园区', 'Gongyeyuanqu', NULL, NULL, NULL, 500300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510000, '四川省', 'Sichuan', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510100, '成都市', 'Chengdu', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510104, '锦江区', 'Jinjiang', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510105, '青羊区', 'Qingyang', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510106, '金牛区', 'Jinniu', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510107, '武侯区', 'Wuhou', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510108, '成华区', 'Chenghua', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510112, '龙泉驿区', 'Longquanyi', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510113, '青白江区', 'Qingbaijiang', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510114, '新都区', 'Xindu', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510115, '温江区', 'Wenjiang', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510121, '金堂县', 'Jintang', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510122, '双流县', 'Shuangliu', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510124, '郫县', 'Pixian', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510129, '大邑县', 'Dayi', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510131, '蒲江县', 'Pujiang', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510132, '新津县', 'Xinjin', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510181, '都江堰市', 'Dujiangyan', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510182, '彭州市', 'Pengzhou', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510183, '邛崃市', 'Qionglai', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510184, '崇州市', 'Chongzhou', NULL, NULL, NULL, 510100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510300, '自贡市', 'Zigong', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510302, '自流井区', 'Ziliujing', NULL, NULL, NULL, 510300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510303, '贡井区', 'Gongjing', NULL, NULL, NULL, 510300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510304, '大安区', 'Da\'an', NULL, NULL, NULL, 510300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510311, '沿滩区', 'Yantan', NULL, NULL, NULL, 510300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510321, '荣县', 'Rongxian', NULL, NULL, NULL, 510300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510322, '富顺县', 'Fushun', NULL, NULL, NULL, 510300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510400, '攀枝花市', 'Panzhihua', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510402, '东区', 'Dongqu', NULL, NULL, NULL, 510400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510403, '西区', 'Xiqu', NULL, NULL, NULL, 510400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510411, '仁和区', 'Renhe', NULL, NULL, NULL, 510400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510421, '米易县', 'Miyi', NULL, NULL, NULL, 510400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510422, '盐边县', 'Yanbian', NULL, NULL, NULL, 510400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510500, '泸州市', 'Luzhou', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510502, '江阳区', 'Jiangyang', NULL, NULL, NULL, 510500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510503, '纳溪区', 'Naxi', NULL, NULL, NULL, 510500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510504, '龙马潭区', 'Longmatan', NULL, NULL, NULL, 510500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510521, '泸县', 'Luxian', NULL, NULL, NULL, 510500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510522, '合江县', 'Hejiang', NULL, NULL, NULL, 510500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510524, '叙永县', 'Xuyong', NULL, NULL, NULL, 510500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510525, '古蔺县', 'Gulin', NULL, NULL, NULL, 510500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510600, '德阳市', 'Deyang', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510603, '旌阳区', 'Jingyang', NULL, NULL, NULL, 510600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510623, '中江县', 'Zhongjiang', NULL, NULL, NULL, 510600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510626, '罗江县', 'Luojiang', NULL, NULL, NULL, 510600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510681, '广汉市', 'Guanghan', NULL, NULL, NULL, 510600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510682, '什邡市', 'Shifang', NULL, NULL, NULL, 510600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510683, '绵竹市', 'Mianzhu', NULL, NULL, NULL, 510600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510700, '绵阳市', 'Mianyang', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510703, '涪城区', 'Fucheng', NULL, NULL, NULL, 510700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510704, '游仙区', 'Youxian', NULL, NULL, NULL, 510700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510722, '三台县', 'Santai', NULL, NULL, NULL, 510700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510723, '盐亭县', 'Yanting', NULL, NULL, NULL, 510700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510724, '安县', 'Anxian', NULL, NULL, NULL, 510700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510725, '梓潼县', 'Zitong', NULL, NULL, NULL, 510700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510726, '北川羌族自治县', 'Beichuan', NULL, NULL, NULL, 510700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510727, '平武县', 'Pingwu', NULL, NULL, NULL, 510700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510781, '江油市', 'Jiangyou', NULL, NULL, NULL, 510700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510800, '广元市', 'Guangyuan', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510802, '利州区', 'Lizhou', NULL, NULL, NULL, 510800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510811, '昭化区', 'Zhaohua', NULL, NULL, NULL, 510800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510812, '朝天区', 'Chaotian', NULL, NULL, NULL, 510800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510821, '旺苍县', 'Wangcang', NULL, NULL, NULL, 510800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510822, '青川县', 'Qingchuan', NULL, NULL, NULL, 510800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510823, '剑阁县', 'Jiange', NULL, NULL, NULL, 510800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510824, '苍溪县', 'Cangxi', NULL, NULL, NULL, 510800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510900, '遂宁市', 'Suining', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510903, '船山区', 'Chuanshan', NULL, NULL, NULL, 510900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510904, '安居区', 'Anju', NULL, NULL, NULL, 510900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510921, '蓬溪县', 'Pengxi', NULL, NULL, NULL, 510900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510922, '射洪县', 'Shehong', NULL, NULL, NULL, 510900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (510923, '大英县', 'Daying', NULL, NULL, NULL, 510900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511000, '内江市', 'Neijiang', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511002, '市中区', 'Shizhongqu', NULL, NULL, NULL, 511000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511011, '东兴区', 'Dongxing', NULL, NULL, NULL, 511000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511024, '威远县', 'Weiyuan', NULL, NULL, NULL, 511000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511025, '资中县', 'Zizhong', NULL, NULL, NULL, 511000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511028, '隆昌县', 'Longchang', NULL, NULL, NULL, 511000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511100, '乐山市', 'Leshan', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511102, '市中区', 'Shizhongqu', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511111, '沙湾区', 'Shawan', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511112, '五通桥区', 'Wutongqiao', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511113, '金口河区', 'Jinkouhe', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511123, '犍为县', 'Qianwei', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511124, '井研县', 'Jingyan', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511126, '夹江县', 'Jiajiang', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511129, '沐川县', 'Muchuan', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511132, '峨边彝族自治县', 'Ebian', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511133, '马边彝族自治县', 'Mabian', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511181, '峨眉山市', 'Emeishan', NULL, NULL, NULL, 511100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511300, '南充市', 'Nanchong', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511302, '顺庆区', 'Shunqing', NULL, NULL, NULL, 511300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511303, '高坪区', 'Gaoping', NULL, NULL, NULL, 511300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511304, '嘉陵区', 'Jialing', NULL, NULL, NULL, 511300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511321, '南部县', 'Nanbu', NULL, NULL, NULL, 511300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511322, '营山县', 'Yingshan', NULL, NULL, NULL, 511300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511323, '蓬安县', 'Peng\'an', NULL, NULL, NULL, 511300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511324, '仪陇县', 'Yilong', NULL, NULL, NULL, 511300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511325, '西充县', 'Xichong', NULL, NULL, NULL, 511300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511381, '阆中市', 'Langzhong', NULL, NULL, NULL, 511300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511400, '眉山市', 'Meishan', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511402, '东坡区', 'Dongpo', NULL, NULL, NULL, 511400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511403, '彭山区', 'Pengshan', NULL, NULL, NULL, 511400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511421, '仁寿县', 'Renshou', NULL, NULL, NULL, 511400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511423, '洪雅县', 'Hongya', NULL, NULL, NULL, 511400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511424, '丹棱县', 'Danling', NULL, NULL, NULL, 511400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511425, '青神县', 'Qingshen', NULL, NULL, NULL, 511400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511500, '宜宾市', 'Yibin', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511502, '翠屏区', 'Cuiping', NULL, NULL, NULL, 511500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511503, '南溪区', 'Nanxi', NULL, NULL, NULL, 511500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511521, '宜宾县', 'Yibin', NULL, NULL, NULL, 511500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511523, '江安县', 'Jiang\'an', NULL, NULL, NULL, 511500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511524, '长宁县', 'Changning', NULL, NULL, NULL, 511500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511525, '高县', 'Gaoxian', NULL, NULL, NULL, 511500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511526, '珙县', 'Gongxian', NULL, NULL, NULL, 511500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511527, '筠连县', 'Junlian', NULL, NULL, NULL, 511500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511528, '兴文县', 'Xingwen', NULL, NULL, NULL, 511500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511529, '屏山县', 'Pingshan', NULL, NULL, NULL, 511500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511600, '广安市', 'Guang\'an', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511602, '广安区', 'Guang\'an', NULL, NULL, NULL, 511600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511603, '前锋区', 'Qianfeng', NULL, NULL, NULL, 511600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511621, '岳池县', 'Yuechi', NULL, NULL, NULL, 511600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511622, '武胜县', 'Wusheng', NULL, NULL, NULL, 511600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511623, '邻水县', 'Linshui', NULL, NULL, NULL, 511600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511681, '华蓥市', 'Huaying', NULL, NULL, NULL, 511600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511700, '达州市', 'Dazhou', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511702, '通川区', 'Tongchuan', NULL, NULL, NULL, 511700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511703, '达川区', 'Dachuan', NULL, NULL, NULL, 511700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511722, '宣汉县', 'Xuanhan', NULL, NULL, NULL, 511700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511723, '开江县', 'Kaijiang', NULL, NULL, NULL, 511700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511724, '大竹县', 'Dazhu', NULL, NULL, NULL, 511700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511725, '渠县', 'Quxian', NULL, NULL, NULL, 511700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511781, '万源市', 'Wanyuan', NULL, NULL, NULL, 511700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511800, '雅安市', 'Ya\'an', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511802, '雨城区', 'Yucheng', NULL, NULL, NULL, 511800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511803, '名山区', 'Mingshan', NULL, NULL, NULL, 511800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511822, '荥经县', 'Yingjing', NULL, NULL, NULL, 511800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511823, '汉源县', 'Hanyuan', NULL, NULL, NULL, 511800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511824, '石棉县', 'Shimian', NULL, NULL, NULL, 511800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511825, '天全县', 'Tianquan', NULL, NULL, NULL, 511800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511826, '芦山县', 'Lushan', NULL, NULL, NULL, 511800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511827, '宝兴县', 'Baoxing', NULL, NULL, NULL, 511800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511900, '巴中市', 'Bazhong', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511902, '巴州区', 'Bazhou', NULL, NULL, NULL, 511900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511903, '恩阳区', 'Enyang', NULL, NULL, NULL, 511900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511921, '通江县', 'Tongjiang', NULL, NULL, NULL, 511900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511922, '南江县', 'Nanjiang', NULL, NULL, NULL, 511900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (511923, '平昌县', 'Pingchang', NULL, NULL, NULL, 511900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (512000, '资阳市', 'Ziyang', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (512002, '雁江区', 'Yanjiang', NULL, NULL, NULL, 512000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (512021, '安岳县', 'Anyue', NULL, NULL, NULL, 512000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (512022, '乐至县', 'Lezhi', NULL, NULL, NULL, 512000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (512081, '简阳市', 'Jianyang', NULL, NULL, NULL, 512000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513200, '阿坝藏族羌族自治州', 'Aba', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513221, '汶川县', 'Wenchuan', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513222, '理县', 'Lixian', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513223, '茂县', 'Maoxian', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513224, '松潘县', 'Songpan', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513225, '九寨沟县', 'Jiuzhaigou', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513226, '金川县', 'Jinchuan', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513227, '小金县', 'Xiaojin', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513228, '黑水县', 'Heishui', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513229, '马尔康县', 'Maerkang', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513230, '壤塘县', 'Rangtang', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513231, '阿坝县', 'Aba', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513232, '若尔盖县', 'Ruoergai', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513233, '红原县', 'Hongyuan', NULL, NULL, NULL, 513200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513300, '甘孜藏族自治州', 'Garze', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513321, '康定县', 'Kangding', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513322, '泸定县', 'Luding', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513323, '丹巴县', 'Danba', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513324, '九龙县', 'Jiulong', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513325, '雅江县', 'Yajiang', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513326, '道孚县', 'Daofu', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513327, '炉霍县', 'Luhuo', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513328, '甘孜县', 'Ganzi', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513329, '新龙县', 'Xinlong', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513330, '德格县', 'Dege', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513331, '白玉县', 'Baiyu', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513332, '石渠县', 'Shiqu', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513333, '色达县', 'Seda', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513334, '理塘县', 'Litang', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513335, '巴塘县', 'Batang', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513336, '乡城县', 'Xiangcheng', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513337, '稻城县', 'Daocheng', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513338, '得荣县', 'Derong', NULL, NULL, NULL, 513300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513400, '凉山彝族自治州', 'Liangshan', NULL, NULL, NULL, 510000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513401, '西昌市', 'Xichang', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513422, '木里藏族自治县', 'Muli', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513423, '盐源县', 'Yanyuan', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513424, '德昌县', 'Dechang', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513425, '会理县', 'Huili', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513426, '会东县', 'Huidong', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513427, '宁南县', 'Ningnan', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513428, '普格县', 'Puge', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513429, '布拖县', 'Butuo', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513430, '金阳县', 'Jinyang', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513431, '昭觉县', 'Zhaojue', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513432, '喜德县', 'Xide', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513433, '冕宁县', 'Mianning', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513434, '越西县', 'Yuexi', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513435, '甘洛县', 'Ganluo', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513436, '美姑县', 'Meigu', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (513437, '雷波县', 'Leibo', NULL, NULL, NULL, 513400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520000, '贵州省', 'Guizhou', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520100, '贵阳市', 'Guiyang', NULL, NULL, NULL, 520000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520102, '南明区', 'Nanming', NULL, NULL, NULL, 520100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520103, '云岩区', 'Yunyan', NULL, NULL, NULL, 520100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520111, '花溪区', 'Huaxi', NULL, NULL, NULL, 520100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520112, '乌当区', 'Wudang', NULL, NULL, NULL, 520100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520113, '白云区', 'Baiyun', NULL, NULL, NULL, 520100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520115, '观山湖区', 'Guanshanhu', NULL, NULL, NULL, 520100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520121, '开阳县', 'Kaiyang', NULL, NULL, NULL, 520100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520122, '息烽县', 'Xifeng', NULL, NULL, NULL, 520100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520123, '修文县', 'Xiuwen', NULL, NULL, NULL, 520100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520181, '清镇市', 'Qingzhen', NULL, NULL, NULL, 520100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520200, '六盘水市', 'Liupanshui', NULL, NULL, NULL, 520000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520201, '钟山区', 'Zhongshan', NULL, NULL, NULL, 520200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520203, '六枝特区', 'Liuzhi', NULL, NULL, NULL, 520200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520221, '水城县', 'Shuicheng', NULL, NULL, NULL, 520200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520222, '盘县', 'Panxian', NULL, NULL, NULL, 520200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520300, '遵义市', 'Zunyi', NULL, NULL, NULL, 520000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520302, '红花岗区', 'Honghuagang', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520303, '汇川区', 'Huichuan', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520321, '遵义县', 'Zunyi', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520322, '桐梓县', 'Tongzi', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520323, '绥阳县', 'Suiyang', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520324, '正安县', 'Zheng\'an', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520325, '道真仡佬族苗族自治县', 'Daozhen', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520326, '务川仡佬族苗族自治县', 'Wuchuan', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520327, '凤冈县', 'Fenggang', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520328, '湄潭县', 'Meitan', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520329, '余庆县', 'Yuqing', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520330, '习水县', 'Xishui', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520381, '赤水市', 'Chishui', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520382, '仁怀市', 'Renhuai', NULL, NULL, NULL, 520300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520400, '安顺市', 'Anshun', NULL, NULL, NULL, 520000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520402, '西秀区', 'Xixiu', NULL, NULL, NULL, 520400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520421, '平坝区', 'Pingba', NULL, NULL, NULL, 520400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520422, '普定县', 'Puding', NULL, NULL, NULL, 520400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520423, '镇宁布依族苗族自治县', 'Zhenning', NULL, NULL, NULL, 520400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520424, '关岭布依族苗族自治县', 'Guanling', NULL, NULL, NULL, 520400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520425, '紫云苗族布依族自治县', 'Ziyun', NULL, NULL, NULL, 520400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520500, '毕节市', 'Bijie', NULL, NULL, NULL, 520000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520502, '七星关区', 'Qixingguan', NULL, NULL, NULL, 520500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520521, '大方县', 'Dafang', NULL, NULL, NULL, 520500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520522, '黔西县', 'Qianxi', NULL, NULL, NULL, 520500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520523, '金沙县', 'Jinsha', NULL, NULL, NULL, 520500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520524, '织金县', 'Zhijin', NULL, NULL, NULL, 520500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520525, '纳雍县', 'Nayong', NULL, NULL, NULL, 520500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520526, '威宁彝族回族苗族自治县', 'Weining', NULL, NULL, NULL, 520500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520527, '赫章县', 'Hezhang', NULL, NULL, NULL, 520500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520600, '铜仁市', 'Tongren', NULL, NULL, NULL, 520000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520602, '碧江区', 'Bijiang', NULL, NULL, NULL, 520600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520603, '万山区', 'Wanshan', NULL, NULL, NULL, 520600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520621, '江口县', 'Jiangkou', NULL, NULL, NULL, 520600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520622, '玉屏侗族自治县', 'Yuping', NULL, NULL, NULL, 520600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520623, '石阡县', 'Shiqian', NULL, NULL, NULL, 520600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520624, '思南县', 'Sinan', NULL, NULL, NULL, 520600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520625, '印江土家族苗族自治县', 'Yinjiang', NULL, NULL, NULL, 520600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520626, '德江县', 'Dejiang', NULL, NULL, NULL, 520600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520627, '沿河土家族自治县', 'Yuanhe', NULL, NULL, NULL, 520600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (520628, '松桃苗族自治县', 'Songtao', NULL, NULL, NULL, 520600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522300, '黔西南布依族苗族自治州', 'Qianxinan', NULL, NULL, NULL, 520000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522301, '兴义市 ', 'Xingyi', NULL, NULL, NULL, 522300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522322, '兴仁县', 'Xingren', NULL, NULL, NULL, 522300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522323, '普安县', 'Pu\'an', NULL, NULL, NULL, 522300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522324, '晴隆县', 'Qinglong', NULL, NULL, NULL, 522300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522325, '贞丰县', 'Zhenfeng', NULL, NULL, NULL, 522300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522326, '望谟县', 'Wangmo', NULL, NULL, NULL, 522300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522327, '册亨县', 'Ceheng', NULL, NULL, NULL, 522300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522328, '安龙县', 'Anlong', NULL, NULL, NULL, 522300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522600, '黔东南苗族侗族自治州', 'Qiandongnan', NULL, NULL, NULL, 520000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522601, '凯里市', 'Kaili', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522622, '黄平县', 'Huangping', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522623, '施秉县', 'Shibing', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522624, '三穗县', 'Sansui', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522625, '镇远县', 'Zhenyuan', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522626, '岑巩县', 'Cengong', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522627, '天柱县', 'Tianzhu', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522628, '锦屏县', 'Jinping', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522629, '剑河县', 'Jianhe', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522630, '台江县', 'Taijiang', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522631, '黎平县', 'Liping', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522632, '榕江县', 'Rongjiang', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522633, '从江县', 'Congjiang', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522634, '雷山县', 'Leishan', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522635, '麻江县', 'Majiang', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522636, '丹寨县', 'Danzhai', NULL, NULL, NULL, 522600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522700, '黔南布依族苗族自治州', 'Qiannan', NULL, NULL, NULL, 520000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522701, '都匀市', 'Duyun', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522702, '福泉市', 'Fuquan', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522722, '荔波县', 'Libo', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522723, '贵定县', 'Guiding', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522725, '瓮安县', 'Weng\'an', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522726, '独山县', 'Dushan', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522727, '平塘县', 'Pingtang', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522728, '罗甸县', 'Luodian', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522729, '长顺县', 'Changshun', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522730, '龙里县', 'Longli', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522731, '惠水县', 'Huishui', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (522732, '三都水族自治县', 'Sandu', NULL, NULL, NULL, 522700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530000, '云南省', 'Yunnan', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530100, '昆明市', 'Kunming', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530102, '五华区', 'Wuhua', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530103, '盘龙区', 'Panlong', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530111, '官渡区', 'Guandu', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530112, '西山区', 'Xishan', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530113, '东川区', 'Dongchuan', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530114, '呈贡区', 'Chenggong', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530122, '晋宁县', 'Jinning', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530124, '富民县', 'Fumin', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530125, '宜良县', 'Yiliang', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530126, '石林彝族自治县', 'Shilin', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530127, '嵩明县', 'Songming', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530128, '禄劝彝族苗族自治县', 'Luquan', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530129, '寻甸回族彝族自治县 ', 'Xundian', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530181, '安宁市', 'Anning', NULL, NULL, NULL, 530100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530300, '曲靖市', 'Qujing', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530302, '麒麟区', 'Qilin', NULL, NULL, NULL, 530300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530321, '马龙县', 'Malong', NULL, NULL, NULL, 530300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530322, '陆良县', 'Luliang', NULL, NULL, NULL, 530300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530323, '师宗县', 'Shizong', NULL, NULL, NULL, 530300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530324, '罗平县', 'Luoping', NULL, NULL, NULL, 530300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530325, '富源县', 'Fuyuan', NULL, NULL, NULL, 530300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530326, '会泽县', 'Huize', NULL, NULL, NULL, 530300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530328, '沾益县', 'Zhanyi', NULL, NULL, NULL, 530300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530381, '宣威市', 'Xuanwei', NULL, NULL, NULL, 530300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530400, '玉溪市', 'Yuxi', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530402, '红塔区', 'Hongta', NULL, NULL, NULL, 530400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530421, '江川县', 'Jiangchuan', NULL, NULL, NULL, 530400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530422, '澄江县', 'Chengjiang', NULL, NULL, NULL, 530400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530423, '通海县', 'Tonghai', NULL, NULL, NULL, 530400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530424, '华宁县', 'Huaning', NULL, NULL, NULL, 530400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530425, '易门县', 'Yimen', NULL, NULL, NULL, 530400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530426, '峨山彝族自治县', 'Eshan', NULL, NULL, NULL, 530400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530427, '新平彝族傣族自治县', 'Xinping', NULL, NULL, NULL, 530400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530428, '元江哈尼族彝族傣族自治县', 'Yuanjiang', NULL, NULL, NULL, 530400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530500, '保山市', 'Baoshan', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530502, '隆阳区', 'Longyang', NULL, NULL, NULL, 530500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530521, '施甸县', 'Shidian', NULL, NULL, NULL, 530500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530522, '腾冲县', 'Tengchong', NULL, NULL, NULL, 530500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530523, '龙陵县', 'Longling', NULL, NULL, NULL, 530500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530524, '昌宁县', 'Changning', NULL, NULL, NULL, 530500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530600, '昭通市', 'Zhaotong', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530602, '昭阳区', 'Zhaoyang', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530621, '鲁甸县', 'Ludian', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530622, '巧家县', 'Qiaojia', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530623, '盐津县', 'Yanjin', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530624, '大关县', 'Daguan', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530625, '永善县', 'Yongshan', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530626, '绥江县', 'Suijiang', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530627, '镇雄县', 'Zhenxiong', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530628, '彝良县', 'Yiliang', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530629, '威信县', 'Weixin', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530630, '水富县', 'Shuifu', NULL, NULL, NULL, 530600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530700, '丽江市', 'Lijiang', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530702, '古城区', 'Gucheng', NULL, NULL, NULL, 530700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530721, '玉龙纳西族自治县', 'Yulong', NULL, NULL, NULL, 530700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530722, '永胜县', 'Yongsheng', NULL, NULL, NULL, 530700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530723, '华坪县', 'Huaping', NULL, NULL, NULL, 530700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530724, '宁蒗彝族自治县', 'Ninglang', NULL, NULL, NULL, 530700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530800, '普洱市', 'Pu\'er', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530802, '思茅区', 'Simao', NULL, NULL, NULL, 530800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530821, '宁洱哈尼族彝族自治县', 'Ninger', NULL, NULL, NULL, 530800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530822, '墨江哈尼族自治县', 'Mojiang', NULL, NULL, NULL, 530800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530823, '景东彝族自治县', 'Jingdong', NULL, NULL, NULL, 530800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530824, '景谷傣族彝族自治县', 'Jinggu', NULL, NULL, NULL, 530800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530825, '镇沅彝族哈尼族拉祜族自治县', 'Zhenyuan', NULL, NULL, NULL, 530800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530826, '江城哈尼族彝族自治县', 'Jiangcheng', NULL, NULL, NULL, 530800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530827, '孟连傣族拉祜族佤族自治县', 'Menglian', NULL, NULL, NULL, 530800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530828, '澜沧拉祜族自治县', 'Lancang', NULL, NULL, NULL, 530800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530829, '西盟佤族自治县', 'Ximeng', NULL, NULL, NULL, 530800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530900, '临沧市', 'Lincang', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530902, '临翔区', 'Linxiang', NULL, NULL, NULL, 530900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530921, '凤庆县', 'Fengqing', NULL, NULL, NULL, 530900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530922, '云县', 'Yunxian', NULL, NULL, NULL, 530900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530923, '永德县', 'Yongde', NULL, NULL, NULL, 530900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530924, '镇康县', 'Zhenkang', NULL, NULL, NULL, 530900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530925, '双江拉祜族佤族布朗族傣族自治县', 'Shuangjiang', NULL, NULL, NULL, 530900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530926, '耿马傣族佤族自治县', 'Gengma', NULL, NULL, NULL, 530900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (530927, '沧源佤族自治县', 'Cangyuan', NULL, NULL, NULL, 530900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532300, '楚雄彝族自治州', 'Chuxiong', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532301, '楚雄市', 'Chuxiong', NULL, NULL, NULL, 532300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532322, '双柏县', 'Shuangbai', NULL, NULL, NULL, 532300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532323, '牟定县', 'Mouding', NULL, NULL, NULL, 532300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532324, '南华县', 'Nanhua', NULL, NULL, NULL, 532300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532325, '姚安县', 'Yao\'an', NULL, NULL, NULL, 532300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532326, '大姚县', 'Dayao', NULL, NULL, NULL, 532300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532327, '永仁县', 'Yongren', NULL, NULL, NULL, 532300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532328, '元谋县', 'Yuanmou', NULL, NULL, NULL, 532300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532329, '武定县', 'Wuding', NULL, NULL, NULL, 532300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532331, '禄丰县', 'Lufeng', NULL, NULL, NULL, 532300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532500, '红河哈尼族彝族自治州', 'Honghe', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532501, '个旧市', 'Gejiu', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532502, '开远市', 'Kaiyuan', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532503, '蒙自市', 'Mengzi', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532504, '弥勒市', 'Mile ', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532523, '屏边苗族自治县', 'Pingbian', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532524, '建水县', 'Jianshui', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532525, '石屏县', 'Shiping', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532527, '泸西县', 'Luxi', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532528, '元阳县', 'Yuanyang', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532529, '红河县', 'Honghexian', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532530, '金平苗族瑶族傣族自治县', 'Jinping', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532531, '绿春县', 'Lvchun', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532532, '河口瑶族自治县', 'Hekou', NULL, NULL, NULL, 532500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532600, '文山壮族苗族自治州', 'Wenshan', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532601, '文山市', 'Wenshan', NULL, NULL, NULL, 532600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532622, '砚山县', 'Yanshan', NULL, NULL, NULL, 532600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532623, '西畴县', 'Xichou', NULL, NULL, NULL, 532600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532624, '麻栗坡县', 'Malipo', NULL, NULL, NULL, 532600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532625, '马关县', 'Maguan', NULL, NULL, NULL, 532600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532626, '丘北县', 'Qiubei', NULL, NULL, NULL, 532600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532627, '广南县', 'Guangnan', NULL, NULL, NULL, 532600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532628, '富宁县', 'Funing', NULL, NULL, NULL, 532600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532800, '西双版纳傣族自治州', 'Xishuangbanna', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532801, '景洪市', 'Jinghong', NULL, NULL, NULL, 532800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532822, '勐海县', 'Menghai', NULL, NULL, NULL, 532800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532823, '勐腊县', 'Mengla', NULL, NULL, NULL, 532800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532900, '大理白族自治州', 'Dali', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532901, '大理市', 'Dali', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532922, '漾濞彝族自治县', 'Yangbi', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532923, '祥云县', 'Xiangyun', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532924, '宾川县', 'Binchuan', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532925, '弥渡县', 'Midu', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532926, '南涧彝族自治县', 'Nanjian', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532927, '巍山彝族回族自治县', 'Weishan', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532928, '永平县', 'Yongping', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532929, '云龙县', 'Yunlong', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532930, '洱源县', 'Eryuan', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532931, '剑川县', 'Jianchuan', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (532932, '鹤庆县', 'Heqing', NULL, NULL, NULL, 532900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533100, '德宏傣族景颇族自治州', 'Dehong', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533102, '瑞丽市', 'Ruili', NULL, NULL, NULL, 533100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533103, '芒市', 'Mangshi', NULL, NULL, NULL, 533100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533122, '梁河县', 'Lianghe', NULL, NULL, NULL, 533100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533123, '盈江县', 'Yingjiang', NULL, NULL, NULL, 533100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533124, '陇川县', 'Longchuan', NULL, NULL, NULL, 533100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533300, '怒江傈僳族自治州', 'Nujiang', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533321, '泸水县', 'Lushui', NULL, NULL, NULL, 533300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533323, '福贡县', 'Fugong', NULL, NULL, NULL, 533300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533324, '贡山独龙族怒族自治县', 'Gongshan', NULL, NULL, NULL, 533300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533325, '兰坪白族普米族自治县', 'Lanping', NULL, NULL, NULL, 533300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533400, '迪庆藏族自治州', 'Deqen', NULL, NULL, NULL, 530000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533421, '香格里拉市', 'Xianggelila', NULL, NULL, NULL, 533400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533422, '德钦县', 'Deqin', NULL, NULL, NULL, 533400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (533423, '维西傈僳族自治县', 'Weixi', NULL, NULL, NULL, 533400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540000, '西藏自治区', 'Tibet', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540100, '拉萨市', 'Lhasa', NULL, NULL, NULL, 540000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540102, '城关区', 'Chengguan', NULL, NULL, NULL, 540100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540121, '林周县', 'Linzhou', NULL, NULL, NULL, 540100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540122, '当雄县', 'Dangxiong', NULL, NULL, NULL, 540100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540123, '尼木县', 'Nimu', NULL, NULL, NULL, 540100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540124, '曲水县', 'Qushui', NULL, NULL, NULL, 540100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540125, '堆龙德庆县', 'Duilongdeqing', NULL, NULL, NULL, 540100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540126, '达孜县', 'Dazi', NULL, NULL, NULL, 540100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540127, '墨竹工卡县', 'Mozhugongka', NULL, NULL, NULL, 540100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540200, '日喀则市', 'Rikaze', NULL, NULL, NULL, 540000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540202, '桑珠孜区', 'Sangzhuzi', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540221, '南木林县', 'Nanmulin', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540222, '江孜县', 'Jiangzi', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540223, '定日县', 'Dingri', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540224, '萨迦县', 'Sajia', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540225, '拉孜县', 'Lazi', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540226, '昂仁县', 'Angren', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540227, '谢通门县', 'Xietongmen', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540228, '白朗县', 'Bailang', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540229, '仁布县', 'Renbu', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540230, '康马县', 'Kangma', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540231, '定结县', 'Dingjie', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540232, '仲巴县', 'Zhongba', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540233, '亚东县', 'Yadong', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540234, '吉隆县', 'Jilong', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540235, '聂拉木县', 'Nielamu', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540236, '萨嘎县', 'Saga', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540237, '岗巴县', 'Gangba', NULL, NULL, NULL, 540200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540300, '昌都市', 'Qamdo', NULL, NULL, NULL, 540000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540302, '卡若区', 'Karuo', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540321, '江达县', 'Jiangda', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540322, '贡觉县', 'Gongjue', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540323, '类乌齐县', 'Leiwuqi', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540324, '丁青县', 'Dingqing', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540325, '察雅县', 'Chaya', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540326, '八宿县', 'Basu', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540327, '左贡县', 'Zuogong', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540328, '芒康县', 'Mangkang', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540329, '洛隆县', 'Luolong', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (540330, '边坝县', 'Bianba', NULL, NULL, NULL, 540300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542200, '山南地区', 'Shannan', NULL, NULL, NULL, 540000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542221, '乃东县', 'Naidong', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542222, '扎囊县', 'Zhanang', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542223, '贡嘎县', 'Gongga', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542224, '桑日县', 'Sangri', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542225, '琼结县', 'Qiongjie', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542226, '曲松县', 'Qusong', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542227, '措美县', 'Cuomei', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542228, '洛扎县', 'Luozha', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542229, '加查县', 'Jiacha', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542231, '隆子县', 'Longzi', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542232, '错那县', 'Cuona', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542233, '浪卡子县', 'Langkazi', NULL, NULL, NULL, 542200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542400, '那曲地区', 'Nagqu', NULL, NULL, NULL, 540000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542421, '那曲县', 'Naqu', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542422, '嘉黎县', 'Jiali', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542423, '比如县', 'Biru', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542424, '聂荣县', 'Nierong', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542425, '安多县', 'Anduo', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542426, '申扎县', 'Shenzha', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542427, '索县', 'Suoxian', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542428, '班戈县', 'Bange', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542429, '巴青县', 'Baqing', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542430, '尼玛县', 'Nima', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542431, '双湖县', 'Shuanghu', NULL, NULL, NULL, 542400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542500, '阿里地区', 'Ngari', NULL, NULL, NULL, 540000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542521, '普兰县', 'Pulan', NULL, NULL, NULL, 542500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542522, '札达县', 'Zhada', NULL, NULL, NULL, 542500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542523, '噶尔县', 'Gaer', NULL, NULL, NULL, 542500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542524, '日土县', 'Ritu', NULL, NULL, NULL, 542500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542525, '革吉县', 'Geji', NULL, NULL, NULL, 542500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542526, '改则县', 'Gaize', NULL, NULL, NULL, 542500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542527, '措勤县', 'Cuoqin', NULL, NULL, NULL, 542500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542600, '林芝地区', 'Nyingchi', NULL, NULL, NULL, 540000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542621, '林芝县', 'Linzhi', NULL, NULL, NULL, 542600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542622, '工布江达县', 'Gongbujiangda', NULL, NULL, NULL, 542600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542623, '米林县', 'Milin', NULL, NULL, NULL, 542600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542624, '墨脱县', 'Motuo', NULL, NULL, NULL, 542600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542625, '波密县', 'Bomi', NULL, NULL, NULL, 542600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542626, '察隅县', 'Chayu', NULL, NULL, NULL, 542600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (542627, '朗县', 'Langxian', NULL, NULL, NULL, 542600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610000, '陕西省', 'Shaanxi', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610100, '西安市', 'Xi\'an', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610102, '新城区', 'Xincheng', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610103, '碑林区', 'Beilin', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610104, '莲湖区', 'Lianhu', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610111, '灞桥区', 'Baqiao', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610112, '未央区', 'Weiyang', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610113, '雁塔区', 'Yanta', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610114, '阎良区', 'Yanliang', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610115, '临潼区', 'Lintong', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610116, '长安区', 'Chang\'an', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610122, '蓝田县', 'Lantian', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610124, '周至县', 'Zhouzhi', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610125, '户县', 'Huxian', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610126, '高陵区', 'Gaoling', NULL, NULL, NULL, 610100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610200, '铜川市', 'Tongchuan', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610202, '王益区', 'Wangyi', NULL, NULL, NULL, 610200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610203, '印台区', 'Yintai', NULL, NULL, NULL, 610200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610204, '耀州区', 'Yaozhou', NULL, NULL, NULL, 610200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610222, '宜君县', 'Yijun', NULL, NULL, NULL, 610200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610300, '宝鸡市', 'Baoji', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610302, '渭滨区', 'Weibin', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610303, '金台区', 'Jintai', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610304, '陈仓区', 'Chencang', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610322, '凤翔县', 'Fengxiang', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610323, '岐山县', 'Qishan', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610324, '扶风县', 'Fufeng', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610326, '眉县', 'Meixian', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610327, '陇县', 'Longxian', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610328, '千阳县', 'Qianyang', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610329, '麟游县', 'Linyou', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610330, '凤县', 'Fengxian', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610331, '太白县', 'Taibai', NULL, NULL, NULL, 610300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610400, '咸阳市', 'Xianyang', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610402, '秦都区', 'Qindu', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610403, '杨陵区', 'Yangling', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610404, '渭城区', 'Weicheng', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610422, '三原县', 'Sanyuan', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610423, '泾阳县', 'Jingyang', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610424, '乾县', 'Qianxian', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610425, '礼泉县', 'Liquan', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610426, '永寿县', 'Yongshou', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610427, '彬县', 'Binxian', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610428, '长武县', 'Changwu', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610429, '旬邑县', 'Xunyi', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610430, '淳化县', 'Chunhua', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610431, '武功县', 'Wugong', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610481, '兴平市', 'Xingping', NULL, NULL, NULL, 610400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610500, '渭南市', 'Weinan', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610502, '临渭区', 'Linwei', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610521, '华县', 'Huaxian', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610522, '潼关县', 'Tongguan', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610523, '大荔县', 'Dali', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610524, '合阳县', 'Heyang', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610525, '澄城县', 'Chengcheng', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610526, '蒲城县', 'Pucheng', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610527, '白水县', 'Baishui', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610528, '富平县', 'Fuping', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610581, '韩城市', 'Hancheng', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610582, '华阴市', 'Huayin', NULL, NULL, NULL, 610500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610600, '延安市', 'Yan\'an', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610602, '宝塔区', 'Baota', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610621, '延长县', 'Yanchang', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610622, '延川县', 'Yanchuan', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610623, '子长县', 'Zichang', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610624, '安塞县', 'Ansai', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610625, '志丹县', 'Zhidan', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610626, '吴起县', 'Wuqi', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610627, '甘泉县', 'Ganquan', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610628, '富县', 'Fuxian', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610629, '洛川县', 'Luochuan', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610630, '宜川县', 'Yichuan', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610631, '黄龙县', 'Huanglong', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610632, '黄陵县', 'Huangling', NULL, NULL, NULL, 610600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610700, '汉中市', 'Hanzhong', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610702, '汉台区', 'Hantai', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610721, '南郑县', 'Nanzheng', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610722, '城固县', 'Chenggu', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610723, '洋县', 'Yangxian', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610724, '西乡县', 'Xixiang', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610725, '勉县', 'Mianxian', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610726, '宁强县', 'Ningqiang', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610727, '略阳县', 'Lueyang', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610728, '镇巴县', 'Zhenba', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610729, '留坝县', 'Liuba', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610730, '佛坪县', 'Foping', NULL, NULL, NULL, 610700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610800, '榆林市', 'Yulin', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610802, '榆阳区', 'Yuyang', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610821, '神木县', 'Shenmu', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610822, '府谷县', 'Fugu', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610823, '横山县', 'Hengshan', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610824, '靖边县', 'Jingbian', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610825, '定边县', 'Dingbian', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610826, '绥德县', 'Suide', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610827, '米脂县', 'Mizhi', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610828, '佳县', 'Jiaxian', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610829, '吴堡县', 'Wubu', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610830, '清涧县', 'Qingjian', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610831, '子洲县', 'Zizhou', NULL, NULL, NULL, 610800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610900, '安康市', 'Ankang', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610902, '汉滨区', 'Hanbin', NULL, NULL, NULL, 610900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610921, '汉阴县', 'Hanyin', NULL, NULL, NULL, 610900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610922, '石泉县', 'Shiquan', NULL, NULL, NULL, 610900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610923, '宁陕县', 'Ningshan', NULL, NULL, NULL, 610900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610924, '紫阳县', 'Ziyang', NULL, NULL, NULL, 610900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610925, '岚皋县', 'Langao', NULL, NULL, NULL, 610900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610926, '平利县', 'Pingli', NULL, NULL, NULL, 610900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610927, '镇坪县', 'Zhenping', NULL, NULL, NULL, 610900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610928, '旬阳县', 'Xunyang', NULL, NULL, NULL, 610900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (610929, '白河县', 'Baihe', NULL, NULL, NULL, 610900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611000, '商洛市', 'Shangluo', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611002, '商州区', 'Shangzhou', NULL, NULL, NULL, 611000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611021, '洛南县', 'Luonan', NULL, NULL, NULL, 611000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611022, '丹凤县', 'Danfeng', NULL, NULL, NULL, 611000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611023, '商南县', 'Shangnan', NULL, NULL, NULL, 611000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611024, '山阳县', 'Shanyang', NULL, NULL, NULL, 611000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611025, '镇安县', 'Zhen\'an', NULL, NULL, NULL, 611000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611026, '柞水县', 'Zhashui', NULL, NULL, NULL, 611000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611100, '西咸新区', 'Xixian', NULL, NULL, NULL, 610000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611101, '空港新城', 'Konggang', NULL, NULL, NULL, 611100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611102, '沣东新城', 'Fengdong', NULL, NULL, NULL, 611100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611103, '秦汉新城', 'Qinhan', NULL, NULL, NULL, 611100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611104, '沣西新城', 'Fengxi', NULL, NULL, NULL, 611100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (611105, '泾河新城', 'Jinghe', NULL, NULL, NULL, 611100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620000, '甘肃省', 'Gansu', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620100, '兰州市', 'Lanzhou', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620102, '城关区', 'Chengguan', NULL, NULL, NULL, 620100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620103, '七里河区', 'Qilihe', NULL, NULL, NULL, 620100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620104, '西固区', 'Xigu', NULL, NULL, NULL, 620100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620105, '安宁区', 'Anning', NULL, NULL, NULL, 620100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620111, '红古区', 'Honggu', NULL, NULL, NULL, 620100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620121, '永登县', 'Yongdeng', NULL, NULL, NULL, 620100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620122, '皋兰县', 'Gaolan', NULL, NULL, NULL, 620100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620123, '榆中县', 'Yuzhong', NULL, NULL, NULL, 620100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620200, '嘉峪关市', 'Jiayuguan', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620201, '雄关区', 'Xiongguan', NULL, NULL, NULL, 620200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620202, '长城区', 'Changcheng', NULL, NULL, NULL, 620200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620203, '镜铁区', 'Jingtie', NULL, NULL, NULL, 620200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620300, '金昌市', 'Jinchang', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620302, '金川区', 'Jinchuan', NULL, NULL, NULL, 620300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620321, '永昌县', 'Yongchang', NULL, NULL, NULL, 620300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620400, '白银市', 'Baiyin', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620402, '白银区', 'Baiyin', NULL, NULL, NULL, 620400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620403, '平川区', 'Pingchuan', NULL, NULL, NULL, 620400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620421, '靖远县', 'Jingyuan', NULL, NULL, NULL, 620400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620422, '会宁县', 'Huining', NULL, NULL, NULL, 620400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620423, '景泰县', 'Jingtai', NULL, NULL, NULL, 620400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620500, '天水市', 'Tianshui', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620502, '秦州区', 'Qinzhou', NULL, NULL, NULL, 620500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620503, '麦积区', 'Maiji', NULL, NULL, NULL, 620500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620521, '清水县', 'Qingshui', NULL, NULL, NULL, 620500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620522, '秦安县', 'Qin\'an', NULL, NULL, NULL, 620500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620523, '甘谷县', 'Gangu', NULL, NULL, NULL, 620500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620524, '武山县', 'Wushan', NULL, NULL, NULL, 620500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620525, '张家川回族自治县', 'Zhangjiachuan', NULL, NULL, NULL, 620500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620600, '武威市', 'Wuwei', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620602, '凉州区', 'Liangzhou', NULL, NULL, NULL, 620600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620621, '民勤县', 'Minqin', NULL, NULL, NULL, 620600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620622, '古浪县', 'Gulang', NULL, NULL, NULL, 620600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620623, '天祝藏族自治县', 'Tianzhu', NULL, NULL, NULL, 620600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620700, '张掖市', 'Zhangye', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620702, '甘州区', 'Ganzhou', NULL, NULL, NULL, 620700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620721, '肃南裕固族自治县', 'Sunan', NULL, NULL, NULL, 620700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620722, '民乐县', 'Minle', NULL, NULL, NULL, 620700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620723, '临泽县', 'Linze', NULL, NULL, NULL, 620700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620724, '高台县', 'Gaotai', NULL, NULL, NULL, 620700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620725, '山丹县', 'Shandan', NULL, NULL, NULL, 620700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620800, '平凉市', 'Pingliang', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620802, '崆峒区', 'Kongtong', NULL, NULL, NULL, 620800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620821, '泾川县', 'Jingchuan', NULL, NULL, NULL, 620800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620822, '灵台县', 'Lingtai', NULL, NULL, NULL, 620800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620823, '崇信县', 'Chongxin', NULL, NULL, NULL, 620800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620824, '华亭县', 'Huating', NULL, NULL, NULL, 620800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620825, '庄浪县', 'Zhuanglang', NULL, NULL, NULL, 620800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620826, '静宁县', 'Jingning', NULL, NULL, NULL, 620800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620900, '酒泉市', 'Jiuquan', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620902, '肃州区', 'Suzhou', NULL, NULL, NULL, 620900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620921, '金塔县', 'Jinta', NULL, NULL, NULL, 620900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620922, '瓜州县', 'Guazhou', NULL, NULL, NULL, 620900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620923, '肃北蒙古族自治县', 'Subei', NULL, NULL, NULL, 620900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620924, '阿克塞哈萨克族自治县', 'Akesai', NULL, NULL, NULL, 620900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620981, '玉门市', 'Yumen', NULL, NULL, NULL, 620900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (620982, '敦煌市', 'Dunhuang', NULL, NULL, NULL, 620900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621000, '庆阳市', 'Qingyang', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621002, '西峰区', 'Xifeng', NULL, NULL, NULL, 621000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621021, '庆城县', 'Qingcheng', NULL, NULL, NULL, 621000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621022, '环县', 'Huanxian', NULL, NULL, NULL, 621000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621023, '华池县', 'Huachi', NULL, NULL, NULL, 621000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621024, '合水县', 'Heshui', NULL, NULL, NULL, 621000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621025, '正宁县', 'Zhengning', NULL, NULL, NULL, 621000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621026, '宁县', 'Ningxian', NULL, NULL, NULL, 621000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621027, '镇原县', 'Zhenyuan', NULL, NULL, NULL, 621000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621100, '定西市', 'Dingxi', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621102, '安定区', 'Anding', NULL, NULL, NULL, 621100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621121, '通渭县', 'Tongwei', NULL, NULL, NULL, 621100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621122, '陇西县', 'Longxi', NULL, NULL, NULL, 621100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621123, '渭源县', 'Weiyuan', NULL, NULL, NULL, 621100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621124, '临洮县', 'Lintao', NULL, NULL, NULL, 621100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621125, '漳县', 'Zhangxian', NULL, NULL, NULL, 621100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621126, '岷县', 'Minxian', NULL, NULL, NULL, 621100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621200, '陇南市', 'Longnan', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621202, '武都区', 'Wudu', NULL, NULL, NULL, 621200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621221, '成县', 'Chengxian', NULL, NULL, NULL, 621200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621222, '文县', 'Wenxian', NULL, NULL, NULL, 621200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621223, '宕昌县', 'Dangchang', NULL, NULL, NULL, 621200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621224, '康县', 'Kangxian', NULL, NULL, NULL, 621200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621225, '西和县', 'Xihe', NULL, NULL, NULL, 621200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621226, '礼县', 'Lixian', NULL, NULL, NULL, 621200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621227, '徽县', 'Huixian', NULL, NULL, NULL, 621200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (621228, '两当县', 'Liangdang', NULL, NULL, NULL, 621200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (622900, '临夏回族自治州', 'Linxia', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (622901, '临夏市', 'Linxia', NULL, NULL, NULL, 622900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (622921, '临夏县', 'Linxia', NULL, NULL, NULL, 622900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (622922, '康乐县', 'Kangle', NULL, NULL, NULL, 622900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (622923, '永靖县', 'Yongjing', NULL, NULL, NULL, 622900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (622924, '广河县', 'Guanghe', NULL, NULL, NULL, 622900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (622925, '和政县', 'Hezheng', NULL, NULL, NULL, 622900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (622926, '东乡族自治县', 'Dongxiangzu', NULL, NULL, NULL, 622900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (622927, '积石山保安族东乡族撒拉族自治县', 'Jishishan', NULL, NULL, NULL, 622900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (623000, '甘南藏族自治州', 'Gannan', NULL, NULL, NULL, 620000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (623001, '合作市', 'Hezuo', NULL, NULL, NULL, 623000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (623021, '临潭县', 'Lintan', NULL, NULL, NULL, 623000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (623022, '卓尼县', 'Zhuoni', NULL, NULL, NULL, 623000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (623023, '舟曲县', 'Zhouqu', NULL, NULL, NULL, 623000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (623024, '迭部县', 'Diebu', NULL, NULL, NULL, 623000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (623025, '玛曲县', 'Maqu', NULL, NULL, NULL, 623000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (623026, '碌曲县', 'Luqu', NULL, NULL, NULL, 623000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (623027, '夏河县', 'Xiahe', NULL, NULL, NULL, 623000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630000, '青海省', 'Qinghai', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630100, '西宁市', 'Xining', NULL, NULL, NULL, 630000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630102, '城东区', 'Chengdong', NULL, NULL, NULL, 630100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630103, '城中区', 'Chengzhong', NULL, NULL, NULL, 630100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630104, '城西区', 'Chengxi', NULL, NULL, NULL, 630100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630105, '城北区', 'Chengbei', NULL, NULL, NULL, 630100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630121, '大通回族土族自治县', 'Datong', NULL, NULL, NULL, 630100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630122, '湟中县', 'Huangzhong', NULL, NULL, NULL, 630100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630123, '湟源县', 'Huangyuan', NULL, NULL, NULL, 630100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630200, '海东市', 'Haidong', NULL, NULL, NULL, 630000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630202, '乐都区', 'Ledu', NULL, NULL, NULL, 630200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630221, '平安县', 'Ping\'an', NULL, NULL, NULL, 630200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630222, '民和回族土族自治县', 'Minhe', NULL, NULL, NULL, 630200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630223, '互助土族自治县', 'Huzhu', NULL, NULL, NULL, 630200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630224, '化隆回族自治县', 'Hualong', NULL, NULL, NULL, 630200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (630225, '循化撒拉族自治县', 'Xunhua', NULL, NULL, NULL, 630200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632200, '海北藏族自治州', 'Haibei', NULL, NULL, NULL, 630000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632221, '门源回族自治县', 'Menyuan', NULL, NULL, NULL, 632200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632222, '祁连县', 'Qilian', NULL, NULL, NULL, 632200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632223, '海晏县', 'Haiyan', NULL, NULL, NULL, 632200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632224, '刚察县', 'Gangcha', NULL, NULL, NULL, 632200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632300, '黄南藏族自治州', 'Huangnan', NULL, NULL, NULL, 630000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632321, '同仁县', 'Tongren', NULL, NULL, NULL, 632300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632322, '尖扎县', 'Jianzha', NULL, NULL, NULL, 632300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632323, '泽库县', 'Zeku', NULL, NULL, NULL, 632300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632324, '河南蒙古族自治县', 'Henan', NULL, NULL, NULL, 632300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632500, '海南藏族自治州', 'Hainan', NULL, NULL, NULL, 630000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632521, '共和县', 'Gonghe', NULL, NULL, NULL, 632500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632522, '同德县', 'Tongde', NULL, NULL, NULL, 632500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632523, '贵德县', 'Guide', NULL, NULL, NULL, 632500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632524, '兴海县', 'Xinghai', NULL, NULL, NULL, 632500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632525, '贵南县', 'Guinan', NULL, NULL, NULL, 632500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632600, '果洛藏族自治州', 'Golog', NULL, NULL, NULL, 630000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632621, '玛沁县', 'Maqin', NULL, NULL, NULL, 632600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632622, '班玛县', 'Banma', NULL, NULL, NULL, 632600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632623, '甘德县', 'Gande', NULL, NULL, NULL, 632600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632624, '达日县', 'Dari', NULL, NULL, NULL, 632600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632625, '久治县', 'Jiuzhi', NULL, NULL, NULL, 632600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632626, '玛多县', 'Maduo', NULL, NULL, NULL, 632600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632700, '玉树藏族自治州', 'Yushu', NULL, NULL, NULL, 630000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632701, '玉树市', 'Yushu', NULL, NULL, NULL, 632700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632722, '杂多县', 'Zaduo', NULL, NULL, NULL, 632700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632723, '称多县', 'Chenduo', NULL, NULL, NULL, 632700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632724, '治多县', 'Zhiduo', NULL, NULL, NULL, 632700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632725, '囊谦县', 'Nangqian', NULL, NULL, NULL, 632700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632726, '曲麻莱县', 'Qumalai', NULL, NULL, NULL, 632700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632800, '海西蒙古族藏族自治州', 'Haixi', NULL, NULL, NULL, 630000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632801, '格尔木市', 'Geermu', NULL, NULL, NULL, 632800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632802, '德令哈市', 'Delingha', NULL, NULL, NULL, 632800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632821, '乌兰县', 'Wulan', NULL, NULL, NULL, 632800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632822, '都兰县', 'Dulan', NULL, NULL, NULL, 632800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (632823, '天峻县', 'Tianjun', NULL, NULL, NULL, 632800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640000, '宁夏回族自治区', 'Ningxia', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640100, '银川市', 'Yinchuan', NULL, NULL, NULL, 640000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640104, '兴庆区', 'Xingqing', NULL, NULL, NULL, 640100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640105, '西夏区', 'Xixia', NULL, NULL, NULL, 640100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640106, '金凤区', 'Jinfeng', NULL, NULL, NULL, 640100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640121, '永宁县', 'Yongning', NULL, NULL, NULL, 640100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640122, '贺兰县', 'Helan', NULL, NULL, NULL, 640100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640181, '灵武市', 'Lingwu', NULL, NULL, NULL, 640100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640200, '石嘴山市', 'Shizuishan', NULL, NULL, NULL, 640000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640202, '大武口区', 'Dawukou', NULL, NULL, NULL, 640200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640205, '惠农区', 'Huinong', NULL, NULL, NULL, 640200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640221, '平罗县', 'Pingluo', NULL, NULL, NULL, 640200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640300, '吴忠市', 'Wuzhong', NULL, NULL, NULL, 640000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640302, '利通区', 'Litong', NULL, NULL, NULL, 640300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640303, '红寺堡区', 'Hongsibao', NULL, NULL, NULL, 640300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640323, '盐池县', 'Yanchi', NULL, NULL, NULL, 640300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640324, '同心县', 'Tongxin', NULL, NULL, NULL, 640300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640381, '青铜峡市', 'Qingtongxia', NULL, NULL, NULL, 640300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640400, '固原市', 'Guyuan', NULL, NULL, NULL, 640000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640402, '原州区', 'Yuanzhou', NULL, NULL, NULL, 640400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640422, '西吉县', 'Xiji', NULL, NULL, NULL, 640400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640423, '隆德县', 'Longde', NULL, NULL, NULL, 640400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640424, '泾源县', 'Jingyuan', NULL, NULL, NULL, 640400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640425, '彭阳县', 'Pengyang', NULL, NULL, NULL, 640400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640500, '中卫市', 'Zhongwei', NULL, NULL, NULL, 640000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640502, '沙坡头区', 'Shapotou', NULL, NULL, NULL, 640500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640521, '中宁县', 'Zhongning', NULL, NULL, NULL, 640500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (640522, '海原县', 'Haiyuan', NULL, NULL, NULL, 640500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650000, '新疆维吾尔自治区', 'Xinjiang', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650100, '乌鲁木齐市', 'Urumqi', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650102, '天山区', 'Tianshan', NULL, NULL, NULL, 650100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650103, '沙依巴克区', 'Shayibake', NULL, NULL, NULL, 650100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650104, '新市区', 'Xinshi', NULL, NULL, NULL, 650100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650105, '水磨沟区', 'Shuimogou', NULL, NULL, NULL, 650100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650106, '头屯河区', 'Toutunhe', NULL, NULL, NULL, 650100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650107, '达坂城区', 'Dabancheng', NULL, NULL, NULL, 650100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650109, '米东区', 'Midong', NULL, NULL, NULL, 650100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650121, '乌鲁木齐县', 'Wulumuqi', NULL, NULL, NULL, 650100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650200, '克拉玛依市', 'Karamay', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650202, '独山子区', 'Dushanzi', NULL, NULL, NULL, 650200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650203, '克拉玛依区', 'Kelamayi', NULL, NULL, NULL, 650200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650204, '白碱滩区', 'Baijiantan', NULL, NULL, NULL, 650200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (650205, '乌尔禾区', 'Wuerhe', NULL, NULL, NULL, 650200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652100, '吐鲁番地区', 'Turpan', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652101, '吐鲁番市', 'Tulufan', NULL, NULL, NULL, 652100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652122, '鄯善县', 'Shanshan', NULL, NULL, NULL, 652100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652123, '托克逊县', 'Tuokexun', NULL, NULL, NULL, 652100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652200, '哈密地区', 'Hami', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652201, '哈密市', 'Hami', NULL, NULL, NULL, 652200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652222, '巴里坤哈萨克自治县', 'Balikun', NULL, NULL, NULL, 652200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652223, '伊吾县', 'Yiwu', NULL, NULL, NULL, 652200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652300, '昌吉回族自治州', 'Changji', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652301, '昌吉市', 'Changji', NULL, NULL, NULL, 652300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652302, '阜康市', 'Fukang', NULL, NULL, NULL, 652300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652323, '呼图壁县', 'Hutubi', NULL, NULL, NULL, 652300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652324, '玛纳斯县', 'Manasi', NULL, NULL, NULL, 652300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652325, '奇台县', 'Qitai', NULL, NULL, NULL, 652300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652327, '吉木萨尔县', 'Jimusaer', NULL, NULL, NULL, 652300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652328, '木垒哈萨克自治县', 'Mulei', NULL, NULL, NULL, 652300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652700, '博尔塔拉蒙古自治州', 'Bortala', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652701, '博乐市', 'Bole', NULL, NULL, NULL, 652700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652702, '阿拉山口市', 'Alashankou', NULL, NULL, NULL, 652700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652722, '精河县', 'Jinghe', NULL, NULL, NULL, 652700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652723, '温泉县', 'Wenquan', NULL, NULL, NULL, 652700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652800, '巴音郭楞蒙古自治州', 'Bayingol', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652801, '库尔勒市', 'Kuerle', NULL, NULL, NULL, 652800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652822, '轮台县', 'Luntai', NULL, NULL, NULL, 652800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652823, '尉犁县', 'Yuli', NULL, NULL, NULL, 652800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652824, '若羌县', 'Ruoqiang', NULL, NULL, NULL, 652800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652825, '且末县', 'Qiemo', NULL, NULL, NULL, 652800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652826, '焉耆回族自治县', 'Yanqi', NULL, NULL, NULL, 652800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652827, '和静县', 'Hejing', NULL, NULL, NULL, 652800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652828, '和硕县', 'Heshuo', NULL, NULL, NULL, 652800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652829, '博湖县', 'Bohu', NULL, NULL, NULL, 652800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652900, '阿克苏地区', 'Aksu', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652901, '阿克苏市', 'Akesu', NULL, NULL, NULL, 652900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652922, '温宿县', 'Wensu', NULL, NULL, NULL, 652900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652923, '库车县', 'Kuche', NULL, NULL, NULL, 652900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652924, '沙雅县', 'Shaya', NULL, NULL, NULL, 652900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652925, '新和县', 'Xinhe', NULL, NULL, NULL, 652900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652926, '拜城县', 'Baicheng', NULL, NULL, NULL, 652900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652927, '乌什县', 'Wushi', NULL, NULL, NULL, 652900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652928, '阿瓦提县', 'Awati', NULL, NULL, NULL, 652900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (652929, '柯坪县', 'Keping', NULL, NULL, NULL, 652900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653000, '克孜勒苏柯尔克孜自治州', 'Kizilsu', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653001, '阿图什市', 'Atushi', NULL, NULL, NULL, 653000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653022, '阿克陶县', 'Aketao', NULL, NULL, NULL, 653000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653023, '阿合奇县', 'Aheqi', NULL, NULL, NULL, 653000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653024, '乌恰县', 'Wuqia', NULL, NULL, NULL, 653000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653100, '喀什地区', 'Kashgar', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653101, '喀什市', 'Kashi', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653121, '疏附县', 'Shufu', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653122, '疏勒县', 'Shule', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653123, '英吉沙县', 'Yingjisha', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653124, '泽普县', 'Zepu', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653125, '莎车县', 'Shache', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653126, '叶城县', 'Yecheng', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653127, '麦盖提县', 'Maigaiti', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653128, '岳普湖县', 'Yuepuhu', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653129, '伽师县', 'Jiashi', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653130, '巴楚县', 'Bachu', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653131, '塔什库尔干塔吉克自治县', 'Tashikuergantajike', NULL, NULL, NULL, 653100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653200, '和田地区', 'Hotan', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653201, '和田市', 'Hetianshi', NULL, NULL, NULL, 653200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653221, '和田县', 'Hetianxian', NULL, NULL, NULL, 653200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653222, '墨玉县', 'Moyu', NULL, NULL, NULL, 653200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653223, '皮山县', 'Pishan', NULL, NULL, NULL, 653200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653224, '洛浦县', 'Luopu', NULL, NULL, NULL, 653200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653225, '策勒县', 'Cele', NULL, NULL, NULL, 653200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653226, '于田县', 'Yutian', NULL, NULL, NULL, 653200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (653227, '民丰县', 'Minfeng', NULL, NULL, NULL, 653200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654000, '伊犁哈萨克自治州', 'Ili', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654002, '伊宁市', 'Yining', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654003, '奎屯市', 'Kuitun', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654004, '霍尔果斯市', 'Huoerguosi', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654021, '伊宁县', 'Yining', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654022, '察布查尔锡伯自治县', 'Chabuchaerxibo', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654023, '霍城县', 'Huocheng', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654024, '巩留县', 'Gongliu', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654025, '新源县', 'Xinyuan', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654026, '昭苏县', 'Zhaosu', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654027, '特克斯县', 'Tekesi', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654028, '尼勒克县', 'Nileke', NULL, NULL, NULL, 654000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654200, '塔城地区', 'Qoqek', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654201, '塔城市', 'Tacheng', NULL, NULL, NULL, 654200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654202, '乌苏市', 'Wusu', NULL, NULL, NULL, 654200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654221, '额敏县', 'Emin', NULL, NULL, NULL, 654200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654223, '沙湾县', 'Shawan', NULL, NULL, NULL, 654200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654224, '托里县', 'Tuoli', NULL, NULL, NULL, 654200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654225, '裕民县', 'Yumin', NULL, NULL, NULL, 654200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654226, '和布克赛尔蒙古自治县', 'Hebukesaier', NULL, NULL, NULL, 654200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654300, '阿勒泰地区', 'Altay', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654301, '阿勒泰市', 'Aletai', NULL, NULL, NULL, 654300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654321, '布尔津县', 'Buerjin', NULL, NULL, NULL, 654300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654322, '富蕴县', 'Fuyun', NULL, NULL, NULL, 654300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654323, '福海县', 'Fuhai', NULL, NULL, NULL, 654300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654324, '哈巴河县', 'Habahe', NULL, NULL, NULL, 654300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654325, '青河县', 'Qinghe', NULL, NULL, NULL, 654300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (654326, '吉木乃县', 'Jimunai', NULL, NULL, NULL, 654300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (659000, '直辖县级', '', NULL, NULL, NULL, 650000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (659001, '石河子市', 'Shihezi', NULL, NULL, NULL, 659000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (659002, '阿拉尔市', 'Aral', NULL, NULL, NULL, 659000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (659003, '图木舒克市', 'Tumxuk', NULL, NULL, NULL, 659000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (659004, '五家渠市', 'Wujiaqu', NULL, NULL, NULL, 659000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (659005, '北屯市', 'Beitun', NULL, NULL, NULL, 659000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (659006, '铁门关市', 'Tiemenguan', NULL, NULL, NULL, 659000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (659007, '双河市', 'Shuanghe', NULL, NULL, NULL, 659000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710000, '台湾', 'Taiwan', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710100, '台北市', 'Taipei', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710101, '松山区', 'Songshan', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710102, '信义区', 'Xinyi', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710103, '大安区', 'Da\'an', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710104, '中山区', 'Zhongshan', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710105, '中正区', 'Zhongzheng', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710106, '大同区', 'Datong', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710107, '万华区', 'Wanhua', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710108, '文山区', 'Wenshan', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710109, '南港区', 'Nangang', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710110, '内湖区', 'Nahu', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710111, '士林区', 'Shilin', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710112, '北投区', 'Beitou', NULL, NULL, NULL, 710100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710200, '高雄市', 'Kaohsiung', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710201, '盐埕区', 'Yancheng', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710202, '鼓山区', 'Gushan', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710203, '左营区', 'Zuoying', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710204, '楠梓区', 'Nanzi', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710205, '三民区', 'Sanmin', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710206, '新兴区', 'Xinxing', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710207, '前金区', 'Qianjin', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710208, '苓雅区', 'Lingya', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710209, '前镇区', 'Qianzhen', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710210, '旗津区', 'Qijin', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710211, '小港区', 'Xiaogang', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710212, '凤山区', 'Fengshan', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710213, '林园区', 'Linyuan', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710214, '大寮区', 'Daliao', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710215, '大树区', 'Dashu', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710216, '大社区', 'Dashe', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710217, '仁武区', 'Renwu', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710218, '鸟松区', 'Niaosong', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710219, '冈山区', 'Gangshan', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710220, '桥头区', 'Qiaotou', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710221, '燕巢区', 'Yanchao', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710222, '田寮区', 'Tianliao', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710223, '阿莲区', 'Alian', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710224, '路竹区', 'Luzhu', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710225, '湖内区', 'Huna', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710226, '茄萣区', 'Qieding', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710227, '永安区', 'Yong\'an', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710228, '弥陀区', 'Mituo', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710229, '梓官区', 'Ziguan', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710230, '旗山区', 'Qishan', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710231, '美浓区', 'Meinong', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710232, '六龟区', 'Liugui', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710233, '甲仙区', 'Jiaxian', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710234, '杉林区', 'Shanlin', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710235, '内门区', 'Namen', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710236, '茂林区', 'Maolin', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710237, '桃源区', 'Taoyuan', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710238, '那玛夏区', 'Namaxia', NULL, NULL, NULL, 710200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710300, '基隆市', 'Keelung', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710301, '中正区', 'Zhongzheng', NULL, NULL, NULL, 710300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710302, '七堵区', 'Qidu', NULL, NULL, NULL, 710300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710303, '暖暖区', 'Nuannuan', NULL, NULL, NULL, 710300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710304, '仁爱区', 'Renai', NULL, NULL, NULL, 710300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710305, '中山区', 'Zhongshan', NULL, NULL, NULL, 710300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710306, '安乐区', 'Anle', NULL, NULL, NULL, 710300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710307, '信义区', 'Xinyi', NULL, NULL, NULL, 710300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710400, '台中市', 'Taichung', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710401, '中区', 'Zhongqu', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710402, '东区', 'Dongqu', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710403, '南区', 'Nanqu', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710404, '西区', 'Xiqu', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710405, '北区', 'Beiqu', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710406, '西屯区', 'Xitun', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710407, '南屯区', 'Nantun', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710408, '北屯区', 'Beitun', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710409, '丰原区', 'Fengyuan', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710410, '东势区', 'Dongshi', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710411, '大甲区', 'Dajia', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710412, '清水区', 'Qingshui', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710413, '沙鹿区', 'Shalu', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710414, '梧栖区', 'Wuqi', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710415, '后里区', 'Houli', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710416, '神冈区', 'Shengang', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710417, '潭子区', 'Tanzi', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710418, '大雅区', 'Daya', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710419, '新社区', 'Xinshe', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710420, '石冈区', 'Shigang', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710421, '外埔区', 'Waipu', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710422, '大安区', 'Da\'an', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710423, '乌日区', 'Wuri', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710424, '大肚区', 'Dadu', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710425, '龙井区', 'Longjing', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710426, '雾峰区', 'Wufeng', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710427, '太平区', 'Taiping', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710428, '大里区', 'Dali', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710429, '和平区', 'Heping', NULL, NULL, NULL, 710400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710500, '台南市', 'Tainan', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710501, '东区', 'Dongqu', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710502, '南区', 'Nanqu', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710504, '北区', 'Beiqu', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710506, '安南区', 'Annan', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710507, '安平区', 'Anping', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710508, '中西区', 'Zhongxi', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710509, '新营区', 'Xinying', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710510, '盐水区', 'Yanshui', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710511, '白河区', 'Baihe', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710512, '柳营区', 'Liuying', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710513, '后壁区', 'Houbi', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710514, '东山区', 'Dongshan', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710515, '麻豆区', 'Madou', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710516, '下营区', 'Xiaying', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710517, '六甲区', 'Liujia', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710518, '官田区', 'Guantian', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710519, '大内区', 'Dana', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710520, '佳里区', 'Jiali', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710521, '学甲区', 'Xuejia', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710522, '西港区', 'Xigang', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710523, '七股区', 'Qigu', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710524, '将军区', 'Jiangjun', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710525, '北门区', 'Beimen', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710526, '新化区', 'Xinhua', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710527, '善化区', 'Shanhua', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710528, '新市区', 'Xinshi', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710529, '安定区', 'Anding', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710530, '山上区', 'Shanshang', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710531, '玉井区', 'Yujing', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710532, '楠西区', 'Nanxi', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710533, '南化区', 'Nanhua', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710534, '左镇区', 'Zuozhen', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710535, '仁德区', 'Rende', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710536, '归仁区', 'Guiren', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710537, '关庙区', 'Guanmiao', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710538, '龙崎区', 'Longqi', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710539, '永康区', 'Yongkang', NULL, NULL, NULL, 710500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710600, '新竹市', 'Hsinchu', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710601, '东区', 'Dongqu', NULL, NULL, NULL, 710600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710602, '北区', 'Beiqu', NULL, NULL, NULL, 710600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710603, '香山区', 'Xiangshan', NULL, NULL, NULL, 710600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710700, '嘉义市', 'Chiayi', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710701, '东区', 'Dongqu', NULL, NULL, NULL, 710700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710702, '西区', 'Xiqu', NULL, NULL, NULL, 710700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710800, '新北市', 'New Taipei', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710801, '板桥区', 'Banqiao', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710802, '三重区', 'Sanzhong', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710803, '中和区', 'Zhonghe', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710804, '永和区', 'Yonghe', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710805, '新庄区', 'Xinzhuang', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710806, '新店区', 'Xindian', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710807, '树林区', 'Shulin', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710808, '莺歌区', 'Yingge', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710809, '三峡区', 'Sanxia', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710810, '淡水区', 'Danshui', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710811, '汐止区', 'Xizhi', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710812, '瑞芳区', 'Ruifang', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710813, '土城区', 'Tucheng', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710814, '芦洲区', 'Luzhou', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710815, '五股区', 'Wugu', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710816, '泰山区', 'Taishan', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710817, '林口区', 'Linkou', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710818, '深坑区', 'Shenkeng', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710819, '石碇区', 'Shiding', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710820, '坪林区', 'Pinglin', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710821, '三芝区', 'Sanzhi', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710822, '石门区', 'Shimen', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710823, '八里区', 'Bali', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710824, '平溪区', 'Pingxi', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710825, '双溪区', 'Shuangxi', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710826, '贡寮区', 'Gongliao', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710827, '金山区', 'Jinshan', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710828, '万里区', 'Wanli', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (710829, '乌来区', 'Wulai', NULL, NULL, NULL, 710800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712200, '宜兰县', 'Yilan', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712201, '宜兰市', 'Yilan', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712221, '罗东镇', 'Luodong', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712222, '苏澳镇', 'Suao', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712223, '头城镇', 'Toucheng', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712224, '礁溪乡', 'Jiaoxi', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712225, '壮围乡', 'Zhuangwei', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712226, '员山乡', 'Yuanshan', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712227, '冬山乡', 'Dongshan', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712228, '五结乡', 'Wujie', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712229, '三星乡', 'Sanxing', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712230, '大同乡', 'Datong', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712231, '南澳乡', 'Nanao', NULL, NULL, NULL, 712200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712300, '桃园县', 'Taoyuan', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712301, '桃园市', 'Taoyuan', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712302, '中坜市', 'Zhongli', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712303, '平镇市', 'Pingzhen', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712304, '八德市', 'Bade', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712305, '杨梅市', 'Yangmei', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712306, '芦竹市', 'Luzhu', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712321, '大溪镇', 'Daxi', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712324, '大园乡', 'Dayuan', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712325, '龟山乡', 'Guishan', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712327, '龙潭乡', 'Longtan', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712329, '新屋乡', 'Xinwu', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712330, '观音乡', 'Guanyin', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712331, '复兴乡', 'Fuxing', NULL, NULL, NULL, 712300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712400, '新竹县', 'Hsinchu', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712401, '竹北市', 'Zhubei', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712421, '竹东镇', 'Zhudong', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712422, '新埔镇', 'Xinpu', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712423, '关西镇', 'Guanxi', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712424, '湖口乡', 'Hukou', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712425, '新丰乡', 'Xinfeng', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712426, '芎林乡', 'Xionglin', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712427, '横山乡', 'Hengshan', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712428, '北埔乡', 'Beipu', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712429, '宝山乡', 'Baoshan', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712430, '峨眉乡', 'Emei', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712431, '尖石乡', 'Jianshi', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712432, '五峰乡', 'Wufeng', NULL, NULL, NULL, 712400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712500, '苗栗县', 'Miaoli', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712501, '苗栗市', 'Miaoli', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712521, '苑里镇', 'Yuanli', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712522, '通霄镇', 'Tongxiao', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712523, '竹南镇', 'Zhunan', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712524, '头份镇', 'Toufen', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712525, '后龙镇', 'Houlong', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712526, '卓兰镇', 'Zhuolan', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712527, '大湖乡', 'Dahu', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712528, '公馆乡', 'Gongguan', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712529, '铜锣乡', 'Tongluo', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712530, '南庄乡', 'Nanzhuang', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712531, '头屋乡', 'Touwu', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712532, '三义乡', 'Sanyi', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712533, '西湖乡', 'Xihu', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712534, '造桥乡', 'Zaoqiao', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712535, '三湾乡', 'Sanwan', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712536, '狮潭乡', 'Shitan', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712537, '泰安乡', 'Tai\'an', NULL, NULL, NULL, 712500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712700, '彰化县', 'Changhua', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712701, '彰化市', 'Zhanghuashi', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712721, '鹿港镇', 'Lugang', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712722, '和美镇', 'Hemei', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712723, '线西乡', 'Xianxi', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712724, '伸港乡', 'Shengang', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712725, '福兴乡', 'Fuxing', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712726, '秀水乡', 'Xiushui', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712727, '花坛乡', 'Huatan', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712728, '芬园乡', 'Fenyuan', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712729, '员林镇', 'Yuanlin', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712730, '溪湖镇', 'Xihu', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712731, '田中镇', 'Tianzhong', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712732, '大村乡', 'Dacun', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712733, '埔盐乡', 'Puyan', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712734, '埔心乡', 'Puxin', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712735, '永靖乡', 'Yongjing', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712736, '社头乡', 'Shetou', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712737, '二水乡', 'Ershui', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712738, '北斗镇', 'Beidou', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712739, '二林镇', 'Erlin', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712740, '田尾乡', 'Tianwei', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712741, '埤头乡', 'Pitou', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712742, '芳苑乡', 'Fangyuan', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712743, '大城乡', 'Dacheng', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712744, '竹塘乡', 'Zhutang', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712745, '溪州乡', 'Xizhou', NULL, NULL, NULL, 712700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712800, '南投县', 'Nantou', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712801, '南投市', 'Nantoushi', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712821, '埔里镇', 'Puli', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712822, '草屯镇', 'Caotun', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712823, '竹山镇', 'Zhushan', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712824, '集集镇', 'Jiji', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712825, '名间乡', 'Mingjian', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712826, '鹿谷乡', 'Lugu', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712827, '中寮乡', 'Zhongliao', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712828, '鱼池乡', 'Yuchi', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712829, '国姓乡', 'Guoxing', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712830, '水里乡', 'Shuili', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712831, '信义乡', 'Xinyi', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712832, '仁爱乡', 'Renai', NULL, NULL, NULL, 712800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712900, '云林县', 'Yunlin', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712901, '斗六市', 'Douliu', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712921, '斗南镇', 'Dounan', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712922, '虎尾镇', 'Huwei', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712923, '西螺镇', 'Xiluo', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712924, '土库镇', 'Tuku', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712925, '北港镇', 'Beigang', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712926, '古坑乡', 'Gukeng', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712927, '大埤乡', 'Dapi', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712928, '莿桐乡', 'Citong', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712929, '林内乡', 'Linna', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712930, '二仑乡', 'Erlun', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712931, '仑背乡', 'Lunbei', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712932, '麦寮乡', 'Mailiao', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712933, '东势乡', 'Dongshi', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712934, '褒忠乡', 'Baozhong', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712935, '台西乡', 'Taixi', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712936, '元长乡', 'Yuanchang', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712937, '四湖乡', 'Sihu', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712938, '口湖乡', 'Kouhu', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (712939, '水林乡', 'Shuilin', NULL, NULL, NULL, 712900, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713000, '嘉义县', 'Chiayi', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713001, '太保市', 'Taibao', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713002, '朴子市', 'Puzi', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713023, '布袋镇', 'Budai', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713024, '大林镇', 'Dalin', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713025, '民雄乡', 'Minxiong', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713026, '溪口乡', 'Xikou', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713027, '新港乡', 'Xingang', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713028, '六脚乡', 'Liujiao', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713029, '东石乡', 'Dongshi', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713030, '义竹乡', 'Yizhu', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713031, '鹿草乡', 'Lucao', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713032, '水上乡', 'Shuishang', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713033, '中埔乡', 'Zhongpu', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713034, '竹崎乡', 'Zhuqi', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713035, '梅山乡', 'Meishan', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713036, '番路乡', 'Fanlu', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713037, '大埔乡', 'Dapu', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713038, '阿里山乡', 'Alishan', NULL, NULL, NULL, 713000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713300, '屏东县', 'Pingtung', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713301, '屏东市', 'Pingdong', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713321, '潮州镇', 'Chaozhou', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713322, '东港镇', 'Donggang', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713323, '恒春镇', 'Hengchun', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713324, '万丹乡', 'Wandan', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713325, '长治乡', 'Changzhi', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713326, '麟洛乡', 'Linluo', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713327, '九如乡', 'Jiuru', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713328, '里港乡', 'Ligang', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713329, '盐埔乡', 'Yanpu', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713330, '高树乡', 'Gaoshu', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713331, '万峦乡', 'Wanluan', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713332, '内埔乡', 'Napu', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713333, '竹田乡', 'Zhutian', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713334, '新埤乡', 'Xinpi', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713335, '枋寮乡', 'Fangliao', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713336, '新园乡', 'Xinyuan', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713337, '崁顶乡', 'Kanding', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713338, '林边乡', 'Linbian', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713339, '南州乡', 'Nanzhou', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713340, '佳冬乡', 'Jiadong', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713341, '琉球乡', 'Liuqiu', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713342, '车城乡', 'Checheng', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713343, '满州乡', 'Manzhou', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713344, '枋山乡', 'Fangshan', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713345, '三地门乡', 'Sandimen', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713346, '雾台乡', 'Wutai', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713347, '玛家乡', 'Majia', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713348, '泰武乡', 'Taiwu', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713349, '来义乡', 'Laiyi', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713350, '春日乡', 'Chunri', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713351, '狮子乡', 'Shizi', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713352, '牡丹乡', 'Mudan', NULL, NULL, NULL, 713300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713400, '台东县', 'Taitung', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713401, '台东市', 'Taidong', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713421, '成功镇', 'Chenggong', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713422, '关山镇', 'Guanshan', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713423, '卑南乡', 'Beinan', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713424, '鹿野乡', 'Luye', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713425, '池上乡', 'Chishang', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713426, '东河乡', 'Donghe', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713427, '长滨乡', 'Changbin', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713428, '太麻里乡', 'Taimali', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713429, '大武乡', 'Dawu', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713430, '绿岛乡', 'Lvdao', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713431, '海端乡', 'Haiduan', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713432, '延平乡', 'Yanping', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713433, '金峰乡', 'Jinfeng', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713434, '达仁乡', 'Daren', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713435, '兰屿乡', 'Lanyu', NULL, NULL, NULL, 713400, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713500, '花莲县', 'Hualien', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713501, '花莲市', 'Hualian', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713521, '凤林镇', 'Fenglin', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713522, '玉里镇', 'Yuli', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713523, '新城乡', 'Xincheng', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713524, '吉安乡', 'Ji\'an', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713525, '寿丰乡', 'Shoufeng', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713526, '光复乡', 'Guangfu', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713527, '丰滨乡', 'Fengbin', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713528, '瑞穗乡', 'Ruisui', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713529, '富里乡', 'Fuli', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713530, '秀林乡', 'Xiulin', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713531, '万荣乡', 'Wanrong', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713532, '卓溪乡', 'Zhuoxi', NULL, NULL, NULL, 713500, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713600, '澎湖县', 'Penghu', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713601, '马公市', 'Magong', NULL, NULL, NULL, 713600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713621, '湖西乡', 'Huxi', NULL, NULL, NULL, 713600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713622, '白沙乡', 'Baisha', NULL, NULL, NULL, 713600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713623, '西屿乡', 'Xiyu', NULL, NULL, NULL, 713600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713624, '望安乡', 'Wang\'an', NULL, NULL, NULL, 713600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713625, '七美乡', 'Qimei', NULL, NULL, NULL, 713600, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713700, '金门县', 'Jinmen', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713701, '金城镇', 'Jincheng', NULL, NULL, NULL, 713700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713702, '金湖镇', 'Jinhu', NULL, NULL, NULL, 713700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713703, '金沙镇', 'Jinsha', NULL, NULL, NULL, 713700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713704, '金宁乡', 'Jinning', NULL, NULL, NULL, 713700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713705, '烈屿乡', 'Lieyu', NULL, NULL, NULL, 713700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713706, '乌丘乡', 'Wuqiu', NULL, NULL, NULL, 713700, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713800, '连江县', 'Lienchiang', NULL, NULL, NULL, 710000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713801, '南竿乡', 'Nangan', NULL, NULL, NULL, 713800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713802, '北竿乡', 'Beigan', NULL, NULL, NULL, 713800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713803, '莒光乡', 'Juguang', NULL, NULL, NULL, 713800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (713804, '东引乡', 'Dongyin', NULL, NULL, NULL, 713800, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810000, '香港特别行政区', 'Hong Kong', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810100, '香港岛', 'Hong Kong Island', NULL, NULL, NULL, 810000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810101, '中西区', 'Central and Western District', NULL, NULL, NULL, 810100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810102, '湾仔区', 'Wan Chai District', NULL, NULL, NULL, 810100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810103, '东区', 'Eastern District', NULL, NULL, NULL, 810100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810104, '南区', 'Southern District', NULL, NULL, NULL, 810100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810200, '九龙', 'Kowloon', NULL, NULL, NULL, 810000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810201, '油尖旺区', 'Yau Tsim Mong', NULL, NULL, NULL, 810200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810202, '深水埗区', 'Sham Shui Po', NULL, NULL, NULL, 810200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810203, '九龙城区', 'Jiulongcheng', NULL, NULL, NULL, 810200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810204, '黄大仙区', 'Wong Tai Sin', NULL, NULL, NULL, 810200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810205, '观塘区', 'Kwun Tong', NULL, NULL, NULL, 810200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810300, '新界', 'New Territories', NULL, NULL, NULL, 810000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810301, '荃湾区', 'Tsuen Wan', NULL, NULL, NULL, 810300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810302, '屯门区', 'Tuen Mun', NULL, NULL, NULL, 810300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810303, '元朗区', 'Yuen Long', NULL, NULL, NULL, 810300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810304, '北区', 'North District', NULL, NULL, NULL, 810300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810305, '大埔区', 'Tai Po', NULL, NULL, NULL, 810300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810306, '西贡区', 'Sai Kung', NULL, NULL, NULL, 810300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810307, '沙田区', 'Sha Tin', NULL, NULL, NULL, 810300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810308, '葵青区', 'Kwai Tsing', NULL, NULL, NULL, 810300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (810309, '离岛区', 'Outlying Islands', NULL, NULL, NULL, 810300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820000, '澳门特别行政区', 'Macau', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820100, '澳门半岛', 'MacauPeninsula', NULL, NULL, NULL, 820000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820101, '花地玛堂区', 'Nossa Senhora de Fatima', NULL, NULL, NULL, 820100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820102, '圣安多尼堂区', 'Santo Antonio', NULL, NULL, NULL, 820100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820103, '大堂区', 'Sé', NULL, NULL, NULL, 820100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820104, '望德堂区', 'Sao Lazaro', NULL, NULL, NULL, 820100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820105, '风顺堂区', 'Sao Lourenco', NULL, NULL, NULL, 820100, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820200, '氹仔岛', 'Taipa', NULL, NULL, NULL, 820000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820201, '嘉模堂区', 'Our Lady Of Carmel\'s Parish', NULL, NULL, NULL, 820200, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820300, '路环岛', 'Coloane', NULL, NULL, NULL, 820000, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (820301, '圣方济各堂区', 'St Francis Xavier\'s Parish', NULL, NULL, NULL, 820300, '2017-08-08 14:48:16', '127.0.0.1');
INSERT INTO `t_service_area` VALUES (900000, '钓鱼岛', 'DiaoyuDao', NULL, NULL, NULL, 0, '2017-08-08 14:48:16', '127.0.0.1');

-- ----------------------------
-- Table structure for t_service_file
-- ----------------------------
DROP TABLE IF EXISTS `t_service_file`;
CREATE TABLE `t_service_file`  (
  `file_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_suffix` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `group_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '暂时无用',
  `upload_date` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传日期'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_service_file
-- ----------------------------
INSERT INTO `t_service_file` VALUES ('30f14c3aaa9340e98432e717bea842ed', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('770d10f823544256842178c93e487aa1', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('c4b6a705c84f494b8d123d915f7250e0', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('55bfb8eb54ad4b66a5ab06ab05a74c12', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('4b1ddd89a90a4020b29980bfabf3cf82', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('dff74cbb64bf499f953394f1d3ffbb6a', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('36a86f6c658d41ceb94f56a3930b6df2', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('6b371583448d4238ba8631e8ae837967', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('411585e8fdad4a82b4b54bb9356fcc6b', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('e90b320b94ba4819a4d43d89d36255fa', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('440a2ae22cf349799ce7ca98bf166ecb', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('27657b999656487a8caf90f207acbb3a', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('5d6afcf70cec40019c990c70eefca2ef', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('c10d139edef64e0fb615bd80c09c0746', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('bc0507d9334b48dc91dbf9a53e6a2dff', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('b7cb072681294c92adab166a69917b65', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('61545b6ea02d44d4b698758abfc26f38', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('86bd2ac4a0d14e89b65f85d79d770137', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('a4feb31c0b66455594e0d2bb413af032', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('bb7df480898244789d11107cc6a602a6', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('918f8a1297d54209bf827a4f63896c95', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('0b06353363974d6fb1605d8665213713', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('94f43970321f4479887a168fd44e6411', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('9667758b26974ea7ab0be9ab814aa8ad', '1.png', '.png', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('2ad7561bc2c548228b8be407e84d518e', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('b5adb784e02946d1b12b6e9bd76a5aaa', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('e2d1d28c979f4db6ac54a160a8d881e7', 'Magic_Select_add_tool.mp4', '.mp4', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('0aaaee2a6d234993ad6212269edcfe1e', 'Magic_Select_add_tool.mp4', '.mp4', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('31f55fcde3d149488b18511971b543db', 'Magic_Select_add_tool.mp4', '.mp4', NULL, '2019/02/28');
INSERT INTO `t_service_file` VALUES ('c863c01ffae84b049c2732afd35e3518', 'Magic_Select_add_tool.mp4', '.mp4', NULL, '2019/03/01');
INSERT INTO `t_service_file` VALUES ('1c552978f3ac4838976c1d105a3ee0b7', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/01');
INSERT INTO `t_service_file` VALUES ('921d540e48114b309b3aad8296de46e6', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/01');
INSERT INTO `t_service_file` VALUES ('ad03421600734266a8684165ee9db5de', 'Magic_Select_add_tool.mp4', '.mp4', NULL, '2019/03/01');
INSERT INTO `t_service_file` VALUES ('87c53f01ce7d40e0abef8684f8b59e36', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/01');
INSERT INTO `t_service_file` VALUES ('c1144247117b4e36a10ee380b109f6f1', 'Magic_Select_add_tool.mp4', '.mp4', NULL, '2019/03/01');
INSERT INTO `t_service_file` VALUES ('822fd6d62f134a8f9bfebdce62c368d9', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/01');
INSERT INTO `t_service_file` VALUES ('f38946f5d8ce4799948bb4b4737854ca', 'Magic_Select_add_tool.mp4', '.mp4', NULL, '2019/03/01');
INSERT INTO `t_service_file` VALUES ('2ed3e93c3eeb4ee58178bcd7c5901f44', 'Magic_Select_add_tool.mp4', '.mp4', NULL, '2019/03/01');
INSERT INTO `t_service_file` VALUES ('2e8f0adee705480e9c9e129602d01e31', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/04');
INSERT INTO `t_service_file` VALUES ('e4e2cefaa0524a808e9a49e86885fd00', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/04');
INSERT INTO `t_service_file` VALUES ('5a0f2fb8cb484e839d05328e30bb4c51', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/05');
INSERT INTO `t_service_file` VALUES ('b8177cc0257a4ae4adb81bf0a47ba03e', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/05');
INSERT INTO `t_service_file` VALUES ('d067371bf1df48df98d1c6808c406adf', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/05');
INSERT INTO `t_service_file` VALUES ('3a6e2bdad865404e8fda997f8cd88043', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/05');
INSERT INTO `t_service_file` VALUES ('917d15dc996f475cab1a39fd5258a1d9', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/05');
INSERT INTO `t_service_file` VALUES ('889195fd503b46458c42dd7701027f69', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/05');
INSERT INTO `t_service_file` VALUES ('966932e395a0465082e4d7352aed73b3', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/05');
INSERT INTO `t_service_file` VALUES ('2d727fe5a4254129bec05029dd30efa4', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/05');
INSERT INTO `t_service_file` VALUES ('b7f3d6e565fc4b5a84c10e4f35d5d560', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/05');
INSERT INTO `t_service_file` VALUES ('d2843a2667284a86addc6423066a1884', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('124fb419c89240319dc1897a51461aa3', '菜单.png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('01aa7eeb7774434e843017920214dd0c', '菜单 (2).png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('c7228b3378a54e4e9b2058d2a90cbc2b', '菜单 (3).png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('9d13df63498845d1a3a802d69c89e409', '角色.png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('3b9c57c1ab7549028418f5276a223a1b', '用户.png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('157e22f48318444eaed850dfc95ba8e1', '数据字典管理.png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('952ac2ddde51475a8c035fb906911148', '机构.png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('5a3c0e90e9c143af88a973b106319170', 'icon_地区.png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('96d718f84f5a45fe9c8c7939d4ab876a', '停车场 (1).png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('26ffff6d26634a80a975a41af1b3aff9', '规则.png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('1719d8bc474649538ba4f487a033a31b', '黑名单.png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('1858564bcc5a470caf0b57823bc2a8a2', '参数.png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('201d40e56c374346864ae87a406d9d86', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('601c58f901a447449355106a6a79d9a7', '支付.png', '.png', NULL, '2019/03/06');
INSERT INTO `t_service_file` VALUES ('c43209c60505407bbb3f83bc667e8f57', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/12');
INSERT INTO `t_service_file` VALUES ('f6561b9a99f940d0ac2766c2f94f7c94', '1.jpg', '.jpg', NULL, '2019/03/12');
INSERT INTO `t_service_file` VALUES ('be9b1573c0cd48359eb8308c4d47f43e', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('c872bb563e99418a8a60f7944d829e65', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('bde6ca7a584e4c46b75c38bffecd8f34', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('d38df052a5e842dab6fc2bfbc9dff7ca', 'v2-8c7168c3480f12a8de41419e3ec52253_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('7a1a38ac7e144ad488ac69e9c56d2ac3', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('260b6068a2b44bd6873de21ac3fe99b9', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('0c964b462fd7478eb2c7362c44fe8eb9', 'v2-8c7168c3480f12a8de41419e3ec52253_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('a749c2813ef648469a95621c75ce3f7e', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('8aacb86902ae4e06a21b2da696afb09f', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('d52ab876c85e40258c3c3df64b12fa70', 'v2-8c7168c3480f12a8de41419e3ec52253_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('8e604a51e3964d98a3570504c35cc4e6', 'v2-8c7168c3480f12a8de41419e3ec52253_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('b29f99b619a14893871263de05860513', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('c51dd94ac68c411eb1f6410bc5b3fe42', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('4f11dfeca7d14523a9a812b48d9b9170', 'v2-00c8323aea372c4219b58c3be0b791c7_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('084c3d60725343febb34329bdcac566f', 'v2-8c7168c3480f12a8de41419e3ec52253_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('a54f6067f9024f73a5245372d62fb8e9', 'v2-8c7168c3480f12a8de41419e3ec52253_b.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('980a8f4a06674557a167084a80143c84', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/13');
INSERT INTO `t_service_file` VALUES ('19796821c1e9404caf0e3eb1ae4901b7', '1120x920-560x460-1 (55).jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('9818f890d03e4a7dab28d78864cc83bc', '1120x920-560x460-1 (55).jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('ac54e9b7318644d992b7966e0d555ef5', '1120x920-560x460-1.jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('b2c2d6b84a2e405fa4c1cbefb774fb74', '1120x920-560x460-1.jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('01b578981c0745fda1dbdb6f316591cf', '1120x920-560x460-1 (55).jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('47db0ce77606463593c3df29b7240595', '1120x920-560x460-1 (55).jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('37e6504e07c84b3692acda3179e12780', '1120x920-560x460-1 (55).jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('687df65b8add4f23903975b10eba9abd', '1120x920-560x460-1.jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('8ce5a2a2045e4b26a77e72259e1f03b3', '1120x920-560x460-1.jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('6408a6883dca426fa852d80161fdec66', 'WeChat_20190321162023.mp4', '.mp4', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('3f17449567a14ee4b68d7248f492499e', 'WeChat_20190321162023.mp4', '.mp4', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('299507161e424b5a84d14659a43ed279', 'icons.png', '.png', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('63f41356c67445469ffe51bd2f074385', '1120x920-560x460-1 (4).jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('293cb842d27a4ce8a806f97e3070a2df', '1120x920-560x460-1 (1).jpg', '.jpg', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('428a808195ee4011aedf7cddc15a1b07', 'WeChat_20190321161941.mp4', '.mp4', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('136bbea03ff547fa88ebbabeab56a6ff', 'WeChat_20190321162023.mp4', '.mp4', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('97064976943f4fdbb02d4f27e868fb4a', 'WeChat_20190321161941.mp4', '.mp4', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('36ade6b3c0a84aecb7d3f0c8a84e15b7', 'WeChat_20190321162023.mp4', '.mp4', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('7089394dca7745ac94593fd3db388283', 'WeChat_20190321161941.mp4', '.mp4', NULL, '2019/03/21');
INSERT INTO `t_service_file` VALUES ('eb979eceae4240a08bc98496e56a67ee', '布达拉宫3.jpg', '.jpg', NULL, '2019/03/22');
INSERT INTO `t_service_file` VALUES ('7e6ca01c5f524fb2a0d8562e2544b9c0', '布达拉宫3.jpg', '.jpg', NULL, '2019/03/22');
INSERT INTO `t_service_file` VALUES ('de871ee7d7214bd286e237b90754901a', '布达拉宫3.jpg', '.jpg', NULL, '2019/03/22');
INSERT INTO `t_service_file` VALUES ('fe40e0d1f84e4dadaae28869ea222c10', 'WeChat_20190321162023.mp4', '.mp4', NULL, '2019/03/22');
INSERT INTO `t_service_file` VALUES ('e896d04a872e4c7f936db713a1a2a1ff', 'WeChat_20190321161941.mp4', '.mp4', NULL, '2019/03/22');
INSERT INTO `t_service_file` VALUES ('8e0738eba7df4d5cb20a1a632f59cc6a', 'E300609FB8DC2DB6204289F5DB7271FE.mp4', '.mp4', NULL, '2019/03/22');
INSERT INTO `t_service_file` VALUES ('36e774d3b6ab439ab70000a25e60cd12', 'F35E768B453CFD56EE29F9F5E665C8B3.mp4', '.mp4', NULL, '2019/03/22');
INSERT INTO `t_service_file` VALUES ('29b3b0ce61564347bd66a0fb0582109f', '642D887EFD006E54E116DD6DC0C234B5.png', '.png', NULL, '2019/03/22');
INSERT INTO `t_service_file` VALUES ('f60765520fd645db8db2a4aaf5b3b563', '642D887EFD006E54E116DD6DC0C234B5.png', '.png', NULL, '2019/03/22');
INSERT INTO `t_service_file` VALUES ('a07c246a85d54521b8d988162b1dc6de', '日志.png', '.png', NULL, '2019/03/25');
INSERT INTO `t_service_file` VALUES ('713254c48bdb45ec830b4fbf868dc5b1', '日志.png', '.png', NULL, '2019/03/25');
INSERT INTO `t_service_file` VALUES ('789281fd3a3f49a1a76d9d189f675e8c', '日志.png', '.png', NULL, '2019/03/25');
INSERT INTO `t_service_file` VALUES ('9a5457114e14485a846b5a7bf04f7858', '日志 (1).png', '.png', NULL, '2019/03/25');
INSERT INTO `t_service_file` VALUES ('b2321aae021d4e93b4630126121ae1ff', '日志 (2).png', '.png', NULL, '2019/03/25');
INSERT INTO `t_service_file` VALUES ('5134f109507b40368ab83390ae0396ea', 'u=447535364,404003059&fm=26&gp=0.jpg', '.jpg', NULL, '2019/03/26');
INSERT INTO `t_service_file` VALUES ('9baac6e2b5a3482984cd7c8c6378d857', '微信图片_20190327183600.png', '.png', NULL, '2019/03/30');
INSERT INTO `t_service_file` VALUES ('60eb1f793139410b958bb98128e9e28c', '微信图片_20190327183600.png', '.png', NULL, '2019/03/30');
INSERT INTO `t_service_file` VALUES ('5823a75946064f7e83c42879b120d378', 'Magic_Select_add_tool.mp4', '.mp4', NULL, '2019/03/30');
INSERT INTO `t_service_file` VALUES ('d49d28c74f2c4fa8902390bc0f2adc4d', '下载.jpg', '.jpg', NULL, '2019/03/30');
INSERT INTO `t_service_file` VALUES ('d2231c017ba64a299013c5a67f139dbc', 'Magic_Select_add_tool.mp4', '.mp4', NULL, '2019/03/30');
INSERT INTO `t_service_file` VALUES ('b24d46ad28db400488345d683ab9139e', '下载.jpg', '.jpg', NULL, '2019/03/30');
INSERT INTO `t_service_file` VALUES ('fdf3d5c9a64c4ef79cc348498b16a625', 'chapter6_4_1.png', '.png', NULL, '2019/05/13');
INSERT INTO `t_service_file` VALUES ('6aac631d411741eb937633230ea5e7b0', 'chapter6_5_1.png', '.png', NULL, '2019/05/13');
INSERT INTO `t_service_file` VALUES ('60297d1801c64f1da60feab144287856', 'chapter6_5_1.png', '.png', NULL, '2019/05/13');
INSERT INTO `t_service_file` VALUES ('a0650823af2942488adb4b1e319e13f9', 'chapter6_5_1.png', '.png', NULL, '2019/05/13');
INSERT INTO `t_service_file` VALUES ('3b5fca3a49e34c94bf79644c1c31c55e', 'chapter6_5_1.png', '.png', NULL, '2019/05/13');
INSERT INTO `t_service_file` VALUES ('425533f6bb4449b9b522aff805cd46cf', 'chapter6_5_1.png', '.png', NULL, '2019/05/13');
INSERT INTO `t_service_file` VALUES ('f1d1ed4d2ec14ef49d31c7180cd98498', 'chapter6_4_1.png', '.png', NULL, '2019/05/13');
INSERT INTO `t_service_file` VALUES ('7622fa29d3ed4112a2cad468051a7489', 'chapter6_4_1.png', '.png', NULL, '2019/05/13');

-- ----------------------------
-- Table structure for t_service_order_log
-- ----------------------------
DROP TABLE IF EXISTS `t_service_order_log`;
CREATE TABLE `t_service_order_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_service_order_log
-- ----------------------------
INSERT INTO `t_service_order_log` VALUES ('0a1e423d142a4afd974500a73e1e5c06', 'temp_order', '20190317', 501);
INSERT INTO `t_service_order_log` VALUES ('35448e06b9494f90bcb6e18849b171c9', 'temp_order', '20190329', 501);
INSERT INTO `t_service_order_log` VALUES ('5016a4d3f727467484a3a924555f9af6', 'temp_order', '20190320', 501);
INSERT INTO `t_service_order_log` VALUES ('a9e88791f9d74447a208fe4ec1f565fc', NULL, '20190315', 1001);
INSERT INTO `t_service_order_log` VALUES ('c409b7694030424f84b5a46eb1cb1eb4', 'temp_order', '20190319', 501);

-- ----------------------------
-- Table structure for t_service_order_log_copy
-- ----------------------------
DROP TABLE IF EXISTS `t_service_order_log_copy`;
CREATE TABLE `t_service_order_log_copy`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_service_wordbook
-- ----------------------------
DROP TABLE IF EXISTS `t_service_wordbook`;
CREATE TABLE `t_service_wordbook`  (
  `wordbook_id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `wordbook_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典code',
  `wordbook_desc` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典解释/描述',
  `wordbook_group_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典分组code',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '排序字段',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `wordbook_desc_en` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文翻译',
  `wordbook_desc_tw` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '繁体翻译',
  PRIMARY KEY (`wordbook_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 429 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_service_wordbook
-- ----------------------------
INSERT INTO `t_service_wordbook` VALUES (1, 'Zookeeper错误', 'Zookeeper错误', 'super_error_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (2, 'MQ错误', 'MQ错误', 'super_error_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (3, 'Redis错误', 'Redis错误', 'super_error_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (4, '0', '未处理', 'super_error_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (5, '1', '已处理', 'super_error_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (6, '2', '已忽略', 'super_error_status', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (9, '1', '国有企业', 'merchant_nature', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (10, '2', '三资企业', 'merchant_nature', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (11, '3', '集体企业', 'merchant_nature', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (12, '4', '私营企业', 'merchant_nature', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (13, '1', '大商户', 'merchant_level', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (14, '2', '一户一报', 'merchant_level', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (20, '00', '支付宝对账文件', 'file_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (21, '01', '微信免充值优惠对账单', 'file_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (22, '02', ' 微信非免充值对账单', 'file_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (24, '0', '未处理', 'record_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (25, '1', '已处理', 'record_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (26, '2', '无需处理', 'record_status', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (30, '00', '支付宝账单', 'bill_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (31, '01', '微信账单', 'bill_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (32, '01', '支付', 'wz_trans_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (33, '02', '退款', 'wz_trans_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (34, '03', '撤销', 'wz_trans_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (35, '-1', '交易已撤销', 'order_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (36, '00', '交易处理中', 'order_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (37, '01', '交易成功', 'order_status', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (39, '02', '交易失败', 'order_status', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (40, '03', '交易创建，等待买家付款 ', 'order_status', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (41, '04', '未付款交易超时关闭，或支付完成后全额退款', 'order_status', 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (42, '05', ' 交易结束，不可退款', 'order_status', 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (43, '0', '开通成功', 'appPayType_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (44, '1', '开通失败', 'appPayType_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (45, '2', '已同步', 'appPayType_status', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (46, '003', '支付宝', 'product_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (47, '004', '微信', 'product_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (48, '01', '对公', 'acct_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (49, '02', '对私', 'acct_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (50, '03', '微钱包账号', 'acct_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (51, '0', '未发布', 'cms_classify_status', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (52, '1', '已发布', 'cms_classify_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (53, '2', '已撤销', 'cms_classify_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (54, '0', '未发布', 'cms_article_status', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (55, '1', '待发布', 'cms_article_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (56, '2', '已发布', 'cms_article_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (57, '3', '已撤销', 'cms_article_status', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (59, '0', '运营菜单', 'menu_type', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (60, '1', '园区菜单', 'menu_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (63, '0', '停用', 'park_status', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (64, '1', '启用', 'park_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (68, '0', '不发布', 'ad_is_release', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (69, '1', '发布', 'ad_is_release', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (74, '0', '无需支付', 'act_pay_type', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (76, '1', '微信支付', 'act_pay_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (77, 'cservice', '公共服务', 'classify_model', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (78, 'cms_article', '文章', 'classify_model', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (79, 'third_party', '第三方服务', 'cservice', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (80, 'life', '生活服务', 'cservice', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (81, '1', '入驻企业', 'enterprise_ification', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (82, '2', '入驻商户', 'enterprise_ification', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (83, '3', '其他商户', 'enterprise_ification', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (84, '4', '服务企业', 'enterprise_ification', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (87, '0', '否', 'is_authentication', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (88, '1', '是', 'is_authentication', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (89, '0', '租房', 'business_type', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (90, '1', '售房', 'business_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (91, '0', '显示', 'is_show', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (92, '1', '不显示', 'is_show', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (93, '0', '轮播', 'pageSettType', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (94, '1', '单图连接', 'pageSettType', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (95, '2', '图标连接', 'pageSettType', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (96, 'online_retailers', '电商企业', 'custom_classify_name', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (98, '0', '不使用', 'is_use_advert', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (99, '1', '使用', 'is_use_advert', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (100, '1', '打电话', 'user_event_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (101, '2', '导航', 'user_event_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (102, '3', '收藏', 'user_event_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (103, '4', '点击', 'user_event_type', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (104, 'park_Introduction', '其他', 'pub_classify_model', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (105, 'leasing', '招商租赁', 'pub_classify_model', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (106, 'peripheral_matching', '周边配套', 'pub_classify_model', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (107, 'park_notice', '园区公告', 'pub_classify_model', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (108, 'policy_services', '政策服务', 'pub_classify_model', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (109, 'talent_policy', '人才政策', 'pub_classify_model', 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (110, 'training_materials', '培训资料', 'pub_classify_model', 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (111, 'policy_interpretation', '政策解读', 'pub_classify_model', 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (112, 'industry_dynamics', '行业动态', 'pub_classify_model', 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (113, 'party_discipline', '党纪党规', 'pub_classify_model', 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (114, 'twolearn_to_doone', '两学一做', 'pub_classify_model', 11, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (115, 'policy_news', '政策要闻', 'pub_classify_model', 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (116, '1', '普通住宅', 'village_property_category', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (117, '1', '高层', 'village_building_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (118, '0', '需要显示的菜单', 'menu_state', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (119, '1', '不需要显示的菜单', 'menu_state', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (120, '1', '物业服务', 'suggestion_classify', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (121, '2', '党群服务', 'suggestion_classify', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (122, '3', '停车场服务', 'suggestion_classify', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (123, '4', '园区服务', 'suggestion_classify', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (124, '1', '查询', 'button_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (125, '2', '添加', 'button_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (126, '3', '修改', 'button_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (129, '0', '启用', 'is_disable', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (130, '1', '禁用', 'is_disable', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (131, '4', '删除', 'button_type', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (132, '1', '合资', 'ent_classify', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (133, '2', '独资', 'ent_classify', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (134, '3', '国有', 'ent_classify', 3, NULL, NULL, NULL, NULL, NULL, 'gy', 'aa');
INSERT INTO `t_service_wordbook` VALUES (135, '4', '私营', 'ent_classify', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (136, '5', '股份制', 'ent_classify', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (137, '6', '有限责任', 'ent_classify', 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (138, '7', '全民所有制', 'ent_classify', 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (139, '8', '集体所有制', 'ent_classify', 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (140, '1', '男', 'sex', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (141, '0', '女', 'sex', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (142, '1', '微信', 'user_resource', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (143, '0', '门户网站', 'user_resource', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (144, '0', '汉语', 'language', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (145, '1', '英语 ', 'language', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (146, '2', '法语', 'language', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (147, '3', '日语', 'language', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (148, '4', '韩语', 'language', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (149, '5', '其他', 'language', 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (150, '5', '其他', 'button_type', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (151, '0', '添加', 'log_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (152, '1', '删除', 'log_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (153, '2', '更新', 'log_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (154, '3', '查看', 'log_type', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (155, '4', '其他', 'log_type', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (156, '0', '企业管理员认证', 'front_user_authentication_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (157, '1', '商户认证', 'front_user_authentication_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (158, '2', '业主认证', 'front_user_authentication_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (159, '3', '租户认证', 'front_user_authentication_type', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (160, '0', '待审核', 'front_user_verify_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (161, '1', '审核通过', 'front_user_verify_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (162, '2', '审核未通过', 'front_user_verify_status', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (163, '0', '未审核', 'examine_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (164, '1', '审核通过', 'examine_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (165, '2', '审核未通过', 'examine_status', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (166, '4', '职工认证', 'front_user_authentication_type', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (167, '0', '可见', 'is_display', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (169, '1', '屏蔽', 'is_hide', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (170, '0', '精华', 'is_essential', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (171, '1', '置顶', 'is_top', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (173, '1', '不可见', 'is_display', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (174, '1', '是', 'front_fans_register_state', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (175, '0', '否', 'front_fans_register_state', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (176, '1', '已关注', 'front_fans_follow_state', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (177, '0', '已取消关注', 'front_fans_follow_state', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (178, '1', '男', 'front_fans_sex', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (179, '2', '女', 'front_fans_sex', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (180, '1', '中文', 'front_fans_lang', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (181, '2', '英文', 'front_fans_lang', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (182, '3', '繁体', 'front_fans_lang', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (183, '0', '增加可用积分', 'integral_strategy_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (184, '1', '减少可用积分', 'integral_strategy_type', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (185, '0', '已绑定', 'front_user_room_binding_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (186, '1', '已解除', 'front_user_room_binding_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (188, '1', '封停', 'article_status', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (189, '2', '未填写', 'sex', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (190, '1', '体育', 'section_group', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (191, '2', '社会', 'section_group', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (192, '0', '暴力', 'filter_type', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (193, '1', '色情', 'filter_type', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (195, '5', '粉丝', 'front_user_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (196, '6', '普通用户', 'front_user_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (197, '0', '企业管理员', 'front_user_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (198, '1', '商户', 'front_user_type', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (199, '2', '业主', 'front_user_type', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (200, '3', '租户', 'front_user_type', 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (201, '4', '员工', 'front_user_type', 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (202, '1', '折扣券', 'voucher_type', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (203, '2', '代金券', 'voucher_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (204, '3', '兑换券', 'voucher_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (205, '4', '团购券', 'voucher_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (206, '5', '优惠券', 'voucher_type', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (207, '1', '未审核', 'voucher_status', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (208, '2', '已通过', 'voucher_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (209, '3', '已投放', 'voucher_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (210, '4', '未通过', 'voucher_status', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (211, '0', '是', 'voucher_is_share', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (212, '1', '否', 'voucher_is_share', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (213, '0', '是', 'voucher_is_transfer', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (214, '1', '否', 'voucher_is_transfer', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (215, '0', '可与其他优惠共享', 'voucher_use_condition', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (216, '1', '不与其他优惠共享', 'voucher_use_condition', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (217, '0', '图片', 'newsType', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (218, '1', '视频', 'newsType', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (219, '2', '音频', 'newsType', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (220, '3', '图文', 'newsType', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (221, '4', '文字', 'newsType', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (222, '1', '大商户', 'merchant_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (223, '2', '特约商户', 'merchant_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (224, '0', '临时', 'qrcode_type', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (225, '1', '永久', 'qrcode_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (228, '#63b359', '淡绿', 'voucher_color', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (229, '#2c9f67', '深绿', 'voucher_color', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (230, '#509fc9', '淡蓝', 'voucher_color', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (231, '#5885cf', '深蓝', 'voucher_color', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (232, '#9062c0', '深紫', 'voucher_color', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (233, '#d09a45', '深棕', 'voucher_color', 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (234, '#e4b138', '浅棕', 'voucher_color', 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (235, '#ee903c', '橘黄', 'voucher_color', 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (236, '#f08500', '浅红', 'voucher_color', 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (237, '#cc463d', '深红', 'voucher_color', 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (238, '0', '全新', 'system_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (239, '1', '集成现有', 'system_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (240, '1-activity', '活动报名', 'writeoff_activity', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (243, 'activity', '活动', 'activity_classify', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (246, '0', '室内', 'propaganda_position', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (247, '1', '室外', 'propaganda_position', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (248, '2', '网络', 'propaganda_position', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (249, '1', '已办理', 'propaganda_bespeak', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (250, '0', '待办理', 'propaganda_bespeak', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (251, '2', '已取消', 'propaganda_bespeak', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (252, '0', '是', 'is_first_level', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (253, '1', '否', 'is_first_level', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (254, '0', '禁用', 'auditor_status', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (255, '1', '启用', 'auditor_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (256, '1', '活动报名推送', 'msgType', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (257, '2', '会议室预约推送', 'msgType', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (258, '3', '营销资源预约通知', 'msgType', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (259, '0', '未处理', 'suggestion_state', 1, NULL, NULL, NULL, NULL, NULL, 'untreated', '未處理');
INSERT INTO `t_service_wordbook` VALUES (260, '1', '已处理', 'suggestion_state', 2, NULL, NULL, NULL, NULL, NULL, 'processed', '已處理');
INSERT INTO `t_service_wordbook` VALUES (261, 'zh-cn', '中文简体', 'language_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (262, 'zh-tw', '繁体', 'language_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (263, 'en-us', '美式英语', 'language_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (264, 'wx_message', '消息', 'auditing_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (265, 'cms_article', '文章', 'auditing_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (266, '4', '审核员-审核申请', 'msgType', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (267, '0', '积分抽奖', 'deduct_integral_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (268, '1', '积分购买，直接抵扣', 'deduct_integral_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (269, '2', '积分买券', 'deduct_integral_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (270, '3', '游戏消费', 'deduct_integral_type', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (271, 'meetings', 'Meetings会议', 'mice_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (272, 'incentives', 'Incentives奖励旅游', 'mice_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (273, 'conventions', 'Conferencing/Conventions（大型企业会议', 'mice_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (274, 'exposition', 'Exposition（活动展览）和Event（节事活动）', 'mice_type', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (277, '0', '成功', 'temp_msg_send_status', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (278, '1', '失败', 'temp_msg_send_status', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (279, '0', '别处价格更低', 'why_cancel', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (280, '1', '预约时间有变化', 'why_cancel', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (281, '2', '网络支付余额不足', 'why_cancel', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (282, '3', '其他原因', 'why_cancel', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (284, '1', '已辦理', 'propaganda_bespeak_zh-tw', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (285, '2', '已取消', 'propaganda_bespeak_zh-tw', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (286, '0', '代辦理', 'propaganda_bespeak_zh-tw', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (287, '0', 'pending', 'propaganda_bespeak_en-us', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (288, '1', 'already processed', 'propaganda_bespeak_en-us', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (289, '2', 'cancelled', 'propaganda_bespeak_en-us', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (293, '0', '否', 'is_follow_state', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (294, '1', '是', 'is_follow_state', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (295, '0', '企业管理员', 'frontUser_user_type', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (296, '1', '商户', 'frontUser_user_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (297, '2', '业主', 'frontUser_user_type', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (298, '3', '租户', 'frontUser_user_type', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (299, '4', '职工', 'frontUser_user_type', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (300, '6', '普通用户', 'frontUser_user_type', 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (301, 'meetings', 'Meetings會議', 'mice_type_zh-tw', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (302, 'incentives', 'Incentives獎勵旅遊', 'mice_type_zh-tw', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (303, 'conventions', 'Conferencing/Conventions（大型企業會議）', 'mice_type_zh-tw', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (304, 'exposition', 'Exposition（活動展覽）和Event（節事活動）', 'mice_type_zh-tw', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (305, 'meetings', 'Meetings', 'mice_type_en-us', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (306, 'incentives', 'Incentives', 'mice_type_en-us', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (307, 'conventions', 'Conferencing/Conventions', 'mice_type_en-us', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (308, 'exposition', 'Exposition and Event', 'mice_type_en-us', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (309, '0', 'indoor', 'propaganda_position_en-us', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (310, '1', 'outdoor', 'propaganda_position_en-us', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (311, '2', 'network', 'propaganda_position_en-us', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (312, '0', '室內', 'propaganda_position_zh-tw', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (313, '1', '室外', 'propaganda_position_zh-tw', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (314, '2', '網絡', 'propaganda_position_zh-tw', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (315, '1', '未开始', 'regStatus', 0, NULL, NULL, NULL, NULL, NULL, 'Not beginning', '未開始');
INSERT INTO `t_service_wordbook` VALUES (316, '2', '报名中', 'regStatus', 1, NULL, NULL, NULL, NULL, NULL, 'Enrolment', '報名中');
INSERT INTO `t_service_wordbook` VALUES (317, '3', '已结束', 'regStatus', 2, NULL, NULL, NULL, NULL, NULL, 'Finished', '已結束');
INSERT INTO `t_service_wordbook` VALUES (318, '0', 'To be audited', 'front_user_verify_status-en-us', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (319, '1', 'Audit pass', 'front_user_verify_status-en-us', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (320, '2', 'Audit failed', 'front_user_verify_status-en-us', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (321, '0', '待審核', 'front_user_verify_status-zh-tw', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (322, '1', '審核通過', 'front_user_verify_status-zh-tw', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (323, '2', '審核未通過', 'front_user_verify_status-zh-tw', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (324, '0', 'Enterprise Administrator', 'authentication_type-en-us', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (325, '0', '企業管理員', 'authentication_type-zh-tw', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (326, '1', 'Merchant Certification', 'authentication_type-en-us', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (327, '1', '商戶認證', 'authentication_type-zh-tw', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (328, '2', 'Owner Certification', 'authentication_type-en-us', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (329, '2', '業主認證', 'authentication_type-zh-tw', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (330, '3', 'Tenant Certification', 'authentication_type-en-us', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (331, '4', 'Employee Certification', 'authentication_type-en-us', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (332, '4', '職工認證', 'authentication_type-zh-tw', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (333, '3', '租戶認證', 'authentication_type-zh-tw', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (334, '2', '积分支付', 'act_pay_type', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (335, '0', '订阅号', 'mp_type', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (336, '1', '服务号', 'mp_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (337, '1', '住宅', 'useAttr', 1, NULL, NULL, NULL, NULL, NULL, 'Residential', '住宅');
INSERT INTO `t_service_wordbook` VALUES (338, '2', '商办', 'useAttr', 2, NULL, NULL, NULL, NULL, NULL, 'Business office', '商辦');
INSERT INTO `t_service_wordbook` VALUES (339, '3', '商铺', 'useAttr', 3, NULL, NULL, NULL, NULL, NULL, 'Shop', '商舖');
INSERT INTO `t_service_wordbook` VALUES (340, '1', '出售', 'rentAttr', 1, NULL, NULL, NULL, NULL, NULL, 'sell', '出售');
INSERT INTO `t_service_wordbook` VALUES (341, '2', '出租', 'rentAttr', 2, NULL, NULL, NULL, NULL, NULL, 'rent', '出租');
INSERT INTO `t_service_wordbook` VALUES (342, '5', '物业费催费通知', 'msgType', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook` VALUES (343, '0', '非置顶', 'is_top', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (344, '0', '不需要', 'isNeed', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (345, '1', '需要', 'isNeed', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (346, '3', '已拒绝', 'propaganda_bespeak', 3, NULL, NULL, NULL, NULL, NULL, 'rejected', '已拒絕');
INSERT INTO `t_service_wordbook` VALUES (347, '1', '运营商直营', 'operation_way', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (348, '2', '商家委托运营', 'operation_way', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (349, '3', '商家直营', 'operation_way', 3, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (350, '1', '地上', 'park_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (351, '2', '地下', 'park_type', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (352, '3', '混合', 'park_type', 3, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (353, '4', '道沿', 'park_type', 4, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (354, '1', '是', 'yesOrNo', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (355, '0', '否', 'yesOrNo', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (356, '1', '普通节点', 'port_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (357, '0', '出口', 'port_fun_type', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (358, '1', '入口', 'port_fun_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (359, '2', '场内', 'port_fun_type', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (360, '1', '轿车', 'car_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (361, '2', '大客车', 'car_type', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (362, '3', '卡车', 'car_type', 3, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (363, '4', '其他', 'car_type', 4, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (364, '0', '月全租', 'monthly_type', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (365, '1', '月白租', 'monthly_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (366, '2', '月晚租', 'monthly_type', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (367, '3', '季全租', 'monthly_type', 3, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (368, '4', '季白租', 'monthly_type', 4, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (369, '5', '季晚租', 'monthly_type', 5, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (370, '6', '年全租', 'monthly_type', 6, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (371, '7', '年白租', 'monthly_type', 7, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (372, '8', '年晚租', 'monthly_type', 8, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (373, '9', '免费用户', 'monthly_type', 9, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (374, '0', '临时用户', 'user_type', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (375, '1', 'app用户', 'user_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (376, '1', '周一', 'week_data', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (377, '2', '周二', 'week_data', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (378, '3', '周三', 'week_data', 3, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (379, '4', '周四', 'week_data', 4, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (380, '5', '周五', 'week_data', 5, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (381, '6', '周六', 'week_data', 6, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (382, '7', '周日', 'week_data', 7, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (383, '0', '整天规则', 'temp_charge_type', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (384, '1', '白天+晚上', 'temp_charge_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (385, '2', '普通整天+休息整天', 'temp_charge_type', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (386, '3', '白天+晚上+休息白天', 'temp_charge_type', 4, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (387, '0', '解除', 'lock_status', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (388, '1', '未解除', 'lock_status', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (389, 'cash', '现金', 'pay_type', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (390, 'wx', '微信支付', 'pay_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (391, 'alipay', '支付宝', 'pay_type', 4, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (392, '1', '微信公众号', 'mch_type', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (393, '2', '支付宝服务窗', 'mch_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (394, '0', '入场记录', 'enter_or_exit', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (395, '1', '出场记录', 'enter_or_exit', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (396, '0', '临时车', 'record_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (397, '1', '月租车', 'record_type', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (398, '2', '免费车', 'record_type', 3, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (399, '0', '红', 'car_color', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (400, '1', '黑', 'car_color', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (401, '2', '白', 'car_color', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (402, '4', '灰', 'car_color', 4, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (403, '0', '未同步', 'is_sync', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (404, '1', '已同步', 'is_sync', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (405, '0', '入库', 'car_status', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (406, '1', '已创建订单', 'car_status', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (407, '2', '已支付', 'car_status', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (408, '0', '未同步', 'syn_status', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (409, '1', '已下发', 'syn_status', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (410, '2', '完成', 'syn_status', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (411, '0', '线下缴费', 'is_online', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (412, '1', '线上缴费', 'is_online', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (413, '0', '未支付', 'temp_order_status', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (414, '1', '已支付', 'temp_order_status', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (415, '2', '免费放行', 'temp_order_status', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (416, '3', '黑名单放行', 'temp_order_status', 3, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (417, '4', '异常订单', 'temp_order_status', 4, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (418, '-1', '撤销', 'temp_order_status', 5, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (419, '0', '现金', 'temp_pay_type', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (420, '1', '微信支付', 'temp_pay_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (421, '2', '支付宝', 'temp_pay_type', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (422, '0', '前缀包含', 'free_type', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (423, '0', '一个月', 'date_type', 0, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (424, '1', '一季度', 'date_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (425, '2', '半年', 'date_type', 2, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (426, '3', '一年', 'date_type', 3, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (427, '1', '后缀包含', 'free_type', 1, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `t_service_wordbook` VALUES (428, '2', '包含', 'free_type', 2, NULL, NULL, NULL, NULL, NULL, '', '');

-- ----------------------------
-- Table structure for t_service_wordbook_group
-- ----------------------------
DROP TABLE IF EXISTS `t_service_wordbook_group`;
CREATE TABLE `t_service_wordbook_group`  (
  `group_id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `group_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组名称',
  `wordbook_group_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组编码',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`group_id`, `group_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_service_wordbook_group
-- ----------------------------
INSERT INTO `t_service_wordbook_group` VALUES (1, '超级错误类型', 'super_error_type', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (2, '超级错误状态', 'super_error_status', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (3, '是否启用', 'is_disable', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (33, '菜单类型', 'menu_type', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (46, '通用是否显示', 'is_show', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (49, '通用是否使用', 'is_use_advert', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (54, '菜单是否显示', 'menu_state', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (60, '按钮权限类型', 'button_type', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (62, '性别', 'sex', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (65, '操作日志类型', 'log_type', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (69, '是否可见', 'is_display', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (70, '是否置顶', 'is_top', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (123, '是否需要', 'isNeed', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (126, '是否', 'yesOrNo', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_service_wordbook_group` VALUES (140, '是否同步', 'is_sync', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_ucenter_alipay_sett
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_alipay_sett`;
CREATE TABLE `t_ucenter_alipay_sett`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '服务号名称',
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'appid',
  `app_key` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '应用公钥',
  `alipay_key` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '支付宝公钥',
  `extends_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '扩展编码',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `update_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `app_private_key` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用私钥',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_alipay_sett
-- ----------------------------
INSERT INTO `t_ucenter_alipay_sett` VALUES ('1', '小伙伴停车', '2019031963556609', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn8QR+OHGSTvSuAYhBMz/+93Mtd4etn+pz4UjfpY/V6PWmGIPV19OWzU2cMF0qgprzveRz+ywsmmZfzpd0w1jvMohv+lquZPRPXYANz+voz1728Foo5GJC1R3LftLminrrrTaPvn2jlh1yG8d02bM7X6F2CWN2wCBt1kUxy3kEtgPBKfsjrdkncDrC4CSGgjyTsk3FH5kwsyHW0c29Q6ckFxh6jp+yftBC/1r1otJbn5Ir/z3zPIBPf03suxrK73hGfxopGQgXYFG75ggw9B70196HKW3YD+LHUmNAzYKACk2bg50JZCOnXUaGeISQnbunDS4AtmKSnpYCBQMSDII0QIDAQAB', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiUh1SzSjozKrCX/Lf9BBeIVhAuln8yzPQaXs0PxUmEbwN+qn5QOosoi+VYQJ+A/YXPlL2AeKU95s7y39q61ruBM5IIDY2ab5qeRxk3/KzyR0mVWjgMelybU83C3ahwthA815GgCqwBnybs1GeEIJHutZidcWlSTt4rAq7fLbzJ72n364vzKRx6goW3kxr9/zmvnMXQGpDMT3Hhz6AqKshpjYGPFEaLVKCF91Ak0Uo8vmGLFpofgMV8GlIRRycUlAcm9NcmZQ1yHsxQIKipsA4ffI00BHmjB3EJ2QFOFlN7jYOQQ1obKzkHeNOUm41VtBG55SJoIkdWyLe/Ke9Lax6QIDAQAB', '1', NULL, NULL, NULL, NULL, 'MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfxBH44cZJO9K4BiEEzP/73cy13h62f6nPhSN+lj9Xo9aYYg9XX05bNTZwwXSqCmvO95HP7LCyaZl/Ol3TDWO8yiG/6Wq5k9E9dgA3P6+jPXvbwWijkYkLVHct+0uaKeuutNo++faOWHXIbx3TZsztfoXYJY3bAIG3WRTHLeQS2A8Ep+yOt2SdwOsLgJIaCPJOyTcUfmTCzIdbRzb1DpyQXGHqOn7J+0EL/WvWi0lufkiv/PfM8gE9/Tey7GsrveEZ/GikZCBdgUbvmCDD0HvTX3ocpbdgP4sdSY0DNgoAKTZuDnQlkI6ddRoZ4hJCdu6cNLgC2YpKelgIFAxIMgjRAgMBAAECggEBAJNUqI4vBj/6nvN9E23t7hJCE8mTMBdz2xC34meV/ou/VuHna+ZSyVtq0u1gOUmlmo2d2CU6TCf7IcYl+Ofr7RHoBP+JLRY9rjNTYheQ71NGG7YnwWs1hn+gQNdyaeGczu6pcKmqsSfLg7J2ZbbRkiFhD+SnnQ1chkKiL3sOYsjMaJR8HLovbqS8EVERwOpyrYLimcSkDE8VE2QxOMwFUPu2c/kV3oWrYe/++Sro3ewS4WyZoKzOOJpsYwlLBdenFjJBYsI/PsBrw5eHYdeB9Khpsj6FuyhFCMWz5I3XvdGuQHVzAIHQrH9AN7BasJZcNOcl7cIG10RT94ol7CNGhBECgYEA0yZHqihs6dITuuXviue+ZlN+S5HrNaMWP/POwXm1aw7Zq6AkAhDfXRn+JSQas2LIlfppbbUtsQ6Hahg7ngp/txfDSWZGsz/QSGWlSOUWHDmryOlObd1Vs7lF+URq0cC89VE7k2ErDhrMT1hWR6BLJDX6/vCpZa6ItA4Jgzpz0qMCgYEAwbOxOjkaEYZQquozRWU1URRIrj7Ts3k8YGhQq9kwk1EN8EFWULBS+bzBtyBcYsmcDmBFBIQ+fLXV0OawzhE+gxeXOOE40djFdkgpQpXNyj/+Ki0wyMhl6h5fiYEIijKYp4DyB2UeMZrIrytmNO7Np8lQ9zQFk0m0BHYUIfbOofsCgYBI5bRkOKSxbQfGxM+Yoz2G0liN+oi78dwipCfDgCN0HKJlsFn+JJkY6XpmlDprqDUcImH3sE9gC2YQ6ODcRT+mlJulYZwTEnr0I7OWoRCrGfYG3RVotLt72wEfd9FxLRuPjkYFMczNh871rcfL3rlSfRh0n9277y/fCeGa3rlSBQKBgQCEvU5TVvQ9VC0Noyb1hFDQOjVAaz+KOyeb7YBy84htO9twbGXpL5v9FlQt2VhWPlDaLTC+0p07uyRGXwCEhp6vGUiBKx5T99gBoZvczGnVEptrbcKDpYjpslzIDwu6IMgp6ZxyYuIZHjguinAlEA0Q0U3Rye2ULKrXb8+GIushZwKBgDmQOXxddot+TbDqm7uCjXoPlpdkQjmc9W1CcjkpzD46hUPGILX/WSwhEP6NngPHXOGr51Dl5ktAqobBZlgHlQIpf+cvOji2/Yx29G+RBRO4AOvtfzCw857HM3OLnGZ9wughNR6tomj7mzUnC5T7prhNUyuzAkMqtGDUixOG30Rs');

-- ----------------------------
-- Table structure for t_ucenter_front_user
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_front_user`;
CREATE TABLE `t_ucenter_front_user`  (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户Id',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `birthday` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生日',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '性别 1-男 0-女 2-未填写',
  `passwd` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_card` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `user_resource` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户来源 0 PC 1微信',
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语种',
  `province_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `city_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
  `area_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `image_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `is_disable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '启用标识 0-启用 1-禁用',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  FULLTEXT INDEX `PROVINCE_ID_INDEX`(`province_id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '前端用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_front_user
-- ----------------------------
INSERT INTO `t_ucenter_front_user` VALUES ('0342f39c3d9f4d9791ded610a0d3bd73', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_front_user` VALUES ('33fa45d043c24b91a3ac4b1ad48ade3e', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_front_user` VALUES ('38eb06d891ad4e9ba84c567f42c22271', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_front_user` VALUES ('43e32eaa851645b4b6fed59854b73f1b', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_front_user` VALUES ('8b222a9ec7534abb8a3e50f73e9f5592', NULL, '王磊', NULL, NULL, '1', NULL, NULL, NULL, 'zh_CN', '陕西', '西安', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/zFBv1GhticWsUcO5Ldx4B6Rfe0yia9E0C5oSj7pAe8cmm4jFU54AZibra9NgSwn8tBq6ibpOSs145Ts8FwFhd6oPiaN69Nst1RJcO/132', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_front_user` VALUES ('98f6de46058e49cb8882fe7a1bdc5129', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_front_user` VALUES ('ae714bb67a46412dbe273173f3d45dd9', NULL, '王磊', NULL, NULL, '1', NULL, NULL, NULL, 'zh_CN', '陕西', '西安', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/zFBv1GhticWsUcO5Ldx4B6Rfe0yia9E0C5oSj7pAe8cmm4jFU54AZibra9NgSwn8tBq6ibpOSs145Ts8FwFhd6oPiaN69Nst1RJcO/132', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_front_user` VALUES ('b425a90096c44adeb36bedfb5a4b2c93', NULL, '王磊', NULL, NULL, '1', NULL, NULL, NULL, 'zh_CN', '陕西', '西安', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/zFBv1GhticWsUcO5Ldx4B6Rfe0yia9E0C5oSj7pAe8cmm4jFU54AZibra9NgSwn8tBq6ibpOSs145Ts8FwFhd6oPiaN69Nst1RJcO/132', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_front_user` VALUES ('edcbfe8b66074382a504c099148491cd', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_ucenter_front_user_bind
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_front_user_bind`;
CREATE TABLE `t_ucenter_front_user_bind`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '逐渐id',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `auth_openId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'openId',
  `auth_openId_type` int(1) NOT NULL COMMENT '0微信公众号 1 小程序 2 微信APP 3 qq app 4 微博app',
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_front_user_bind
-- ----------------------------
INSERT INTO `t_ucenter_front_user_bind` VALUES ('4c58da4b90e248e296c1685ce653a140', '98f6de46058e49cb8882fe7a1bdc5129', '2088102107440700', 1, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_front_user_bind` VALUES ('e1c1021b6dc948148e1a0a3c430099ff', 'ae714bb67a46412dbe273173f3d45dd9', 'oqtEruOAy46ej7ORYZhjBc2Lw9Bg', 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_ucenter_mp_sett
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_mp_sett`;
CREATE TABLE `t_ucenter_mp_sett`  (
  `id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'id',
  `name` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '公众号名称',
  `app_id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'appid',
  `app_secret` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '密钥',
  `contacts` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '管理员',
  `extends_code` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '第三方编码(做多个公众号的时候使用)',
  `token` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'token',
  `aes_key` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'aeskey',
  `create_user` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `update_time` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '公众号配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_mp_sett
-- ----------------------------
INSERT INTO `t_ucenter_mp_sett` VALUES ('1', 'test', 'wx32fbbf2d4bdcac42', '1a2b1f3626b8fd6dafa9a1b1c6e28f9d', '1', '1', 'weixin', 'KYUsaGbNKEON0CU4pRV8ywlur2SMM3zdV86kqF7XQGV', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_ucenter_ms_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_menu`;
CREATE TABLE `t_ucenter_ms_menu`  (
  `menu_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `father_menu_id` int(11) NULL DEFAULT NULL COMMENT '父菜单id',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `namespace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Namespace',
  `menu_type` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型0 运营菜单  1 租户菜单',
  `is_disable` int(1) NULL DEFAULT NULL COMMENT '是否禁用 0:启用 1:禁用',
  `is_attention` int(1) NULL DEFAULT 0 COMMENT '是否关注，0不关注，1,关注',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `server_name_id` int(11) NULL DEFAULT NULL COMMENT '服务器连接',
  `order_index` int(5) NULL DEFAULT NULL COMMENT '菜单排序',
  `check_help_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '查看帮助的路径',
  `menu_state` int(1) NULL DEFAULT 0 COMMENT '0:需要显示的菜单;1 不需要显示的菜单',
  `configuration_home_menu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '适配的主页菜单',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `system_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属系统',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'T_admin_Menu 菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_menu
-- ----------------------------
INSERT INTO `t_ucenter_ms_menu` VALUES (0, 'root', NULL, '#', NULL, '0', 0, 0, NULL, 1, -1, NULL, 0, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_ms_menu` VALUES (29, '系统管理', 0, '#', '', '0', 0, 0, '01ad9fd6c8194e79843ce9f220c136a3', 1, 1, '0', 0, '0', NULL, NULL, NULL, NULL, NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (30, '菜单管理', 29, 'page/ms/ucenter/sys_main_frame.jsp', 'sysMenu', '0', 0, 0, 'c7228b3378a54e4e9b2058d2a90cbc2b', 1, 0, '0', 0, '0', NULL, NULL, NULL, NULL, NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (40, '角色管理', 29, 'page/ms/ucenter/sys_role_main_frame.jsp', 'sysRole', '0', 0, 0, '9d13df63498845d1a3a802d69c89e409', 1, 1, '0', 0, '0', NULL, NULL, NULL, NULL, NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (41, '后管用户', 29, 'page/ms/ucenter/sys_user_main_frame.jsp', 'sysUser', '0', 0, 0, '3b9c57c1ab7549028418f5276a223a1b', 1, 2, '0', 0, '0', NULL, NULL, NULL, NULL, NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (151, '字典管理', 29, 'page/ms/system/wordbook/wordbook_main_frame.jsp', 'wordbook', '0', 0, 0, '157e22f48318444eaed850dfc95ba8e1', 1, 3, '0', 0, '0', NULL, NULL, NULL, NULL, NULL, '5099346ee2cb4bf980daa0dbef4d25aa');
INSERT INTO `t_ucenter_ms_menu` VALUES (781, '机构管理', 29, 'page/ms/ucenter/sys_organization_frame.jsp', 'sysOrganization', '0', 0, 0, '952ac2ddde51475a8c035fb906911148', 1, 4, '0', 0, '0', NULL, NULL, NULL, NULL, NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (972, '菜单权限管理', 29, '#', 'sysMenuPermission', '0', 0, 0, '', 1, 5, '0', 1, '0', NULL, NULL, NULL, NULL, NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (988, '地区管理', 29, 'page/ms/system/area/area_list.jsp', 'area', '0', 0, 0, '5a3c0e90e9c143af88a973b106319170', 1, 6, '0', 0, '0', NULL, NULL, NULL, NULL, NULL, '5099346ee2cb4bf980daa0dbef4d25aa');
INSERT INTO `t_ucenter_ms_menu` VALUES (1042, '操作日志', 29, '/page/ms/system/log/log_admin_operator_log_list.jsp', 'logAdminOperatorLog', '0', 0, 0, 'b2321aae021d4e93b4630126121ae1ff', 1, 9, '0', 0, '0', NULL, NULL, '1', '2019-03-25 21:32:21', NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (1043, '菜单权限', 29, '#', 'sysMenuPermission', '0', 0, 0, '', 1, 100, '0', 1, '0', NULL, NULL, '1', '2019-03-25 21:12:53', NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (1085, 'demo', 0, '#', 'demo', '2', 0, NULL, '', 1, 2, NULL, 0, '0', '1', '2019-03-30 16:31:57', '1', '2019-05-18 17:29:57', NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (1086, '表单演示', 1085, '/ms/pagex/student_list.jsp', 'student', NULL, 0, NULL, '', 1, 1, NULL, 0, '0', '1', '2019-03-30 16:53:58', '1', '2019-03-30 16:56:56', NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (1087, '隐藏菜单', 1085, '#', 'role', NULL, 0, NULL, '', 1, 2, NULL, 1, '0', '1', '2019-03-30 17:40:24', '1', '2019-03-30 17:40:24', NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (1088, '停车场管理', 1085, '/ms/pagex/parking_list.jsp?parent_park_id=0', 'parking', NULL, 0, NULL, '', 1, 3, NULL, 0, '0', '1', '2019-03-30 18:52:02', '1', '2019-03-30 18:53:00', NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (1089, '停车场出入口管理', 1085, '/ms/pagex/parking_port_list.jsp', 'parking_port', NULL, 0, NULL, '', 1, 4, NULL, 0, '0', '1', '2019-03-30 18:52:41', '1', '2019-03-30 18:52:41', NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (1091, '老师管理', 1085, '/ms/pagex/teacher_list.jsp', 'teacher', NULL, 0, NULL, '', 1, 5, NULL, 0, '0', '1', '2019-05-13 17:58:51', '1', '2019-05-13 18:19:08', NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (1098, '操场管理', 1085, 'page/demo/playground_list.jsp', 'demoPlayground', NULL, 0, NULL, '', 1, 6, NULL, 0, '0', '1', '2019-05-15 12:00:39', '1', '2019-05-15 14:31:16', NULL, '55ff598ae42845d4b51ec2bc71bbc009');
INSERT INTO `t_ucenter_ms_menu` VALUES (1099, '课程管理', 1085, 'page/demo/subject_list.jsp', 'demoSubject', NULL, 0, NULL, '', 1, 7, NULL, 0, '0', '1', '2019-05-16 15:50:55', '1', '2019-05-16 15:50:55', NULL, '55ff598ae42845d4b51ec2bc71bbc009');

-- ----------------------------
-- Table structure for t_ucenter_ms_menu_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_menu_permission`;
CREATE TABLE `t_ucenter_ms_menu_permission`  (
  `permission_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Namespace方法名字',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  `is_disable` int(1) NULL DEFAULT NULL COMMENT '是否禁用 0:启用 1:禁用',
  `permission_type` int(1) NULL DEFAULT NULL COMMENT '权限类型12345，查询，添加，修改，删除，其他',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2001 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'T_admin_Menu_button 菜单按钮' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_menu_permission
-- ----------------------------
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (30, '查询', 'see', 30, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (31, '添加', 'add', 30, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (32, '修改', 'update', 30, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (33, '删除', 'del', 30, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (34, '添加', 'add', 40, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (35, '修改', 'update', 40, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (36, '删除', 'del', 40, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (37, '查询', 'see', 40, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (38, '添加', 'add', 41, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (39, '删除', 'del', 41, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (40, '修改', 'update', 41, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (41, '查询', 'see', 41, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (444, '查询', 'see', 151, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (445, '删除', 'del', 151, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (446, '添加', 'add', 151, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (447, '修改', 'update', 151, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1134, '添加', 'add', 781, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1135, '查询', 'see', 781, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1136, '修改', 'update', 781, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1137, '删除', 'del', 781, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1652, '添加', 'add', 972, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1653, '查询', 'see', 972, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1654, '修改', 'update', 972, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1696, '刷新缓存', 'refreshRedisCache', 41, 0, 5);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1702, '刷新缓存', 'refreshRedisCache', 151, 0, 5);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1704, '查询', 'see', 988, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1705, '刷新缓存', 'refreshRedisCache', 988, 0, 5);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1715, '查询', 'see', 1008, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1860, '刷新缓存', 'refreshRedisCache', 781, 0, 5);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1861, '添加', 'add', 1011, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1862, '查询', 'see', 1011, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1863, '修改', 'update', 1011, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1864, '删除', 'del', 1011, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1865, '添加', 'add', 1012, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1866, '查询', 'see', 1012, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1867, '修改', 'update', 1012, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1868, '删除', 'del', 1012, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1869, '添加', 'add', 1014, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1870, '查询', 'see', 1014, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1871, '修改', 'update', 1014, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1872, '删除', 'del', 1014, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1873, '添加', 'add', 1015, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1874, '查询', 'see', 1015, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1875, '修改', 'update', 1015, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1876, '删除', 'del', 1015, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1877, '添加', 'add', 1016, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1878, '查询', 'see', 1016, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1879, '修改', 'update', 1016, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1880, '删除', 'del', 1016, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1881, '添加', 'add', 1017, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1882, '查询', 'see', 1017, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1883, '修改', 'update', 1017, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1884, '删除', 'del', 1017, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1885, '添加', 'add', 1019, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1886, '查询', 'see', 1019, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1887, '修改', 'update', 1019, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1888, '删除', 'del', 1019, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1889, '添加', 'add', 1021, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1890, '查询', 'see', 1021, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1891, '修改', 'update', 1021, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1892, '删除', 'del', 1021, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1893, '添加', 'add', 1023, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1894, '查询', 'see', 1023, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1895, '修改', 'update', 1023, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1896, '删除', 'del', 1023, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1897, '添加', 'add', 1025, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1898, '查询', 'see', 1025, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1899, '修改', 'update', 1025, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1900, '删除', 'del', 1025, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1901, '添加', 'add', 1026, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1902, '查询', 'see', 1026, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1903, '修改', 'update', 1026, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1904, '删除', 'del', 1026, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1905, '添加', 'add', 1027, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1906, '查询', 'see', 1027, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1907, '修改', 'update', 1027, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1908, '删除', 'del', 1027, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1909, '添加', 'add', 1028, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1910, '查询', 'see', 1028, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1911, '修改', 'update', 1028, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1912, '删除', 'del', 1028, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1913, '添加', 'add', 1029, 1, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1914, '查询', 'see', 1029, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1915, '修改', 'update', 1029, 1, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1916, '删除', 'del', 1029, 1, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1917, '添加', 'add', 1031, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1918, '查询', 'see', 1031, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1919, '修改', 'update', 1031, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1920, '删除', 'del', 1031, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1921, '添加', 'add', 1032, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1922, '查询', 'see', 1032, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1923, '修改', 'update', 1032, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1924, '删除', 'del', 1032, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1925, '添加', 'add', 1036, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1926, '查询', 'see', 1036, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1927, '修改', 'update', 1036, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1928, '删除', 'del', 1036, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1929, '添加', 'add', 1037, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1930, '查询', 'see', 1037, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1931, '修改', 'update', 1037, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1932, '删除', 'del', 1037, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1933, '添加', 'add', 1038, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1934, '查询', 'see', 1038, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1935, '修改', 'update', 1038, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1936, '删除', 'del', 1038, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1937, '添加', 'add', 1039, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1938, '查询', 'see', 1039, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1939, '修改', 'update', 1039, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1940, '删除', 'del', 1039, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1941, '添加', 'add', 1034, 1, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1942, '查询', 'see', 1034, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1943, '修改', 'update', 1034, 1, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1944, '删除', 'del', 1034, 1, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1945, '添加', 'add', 1040, 1, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1946, '查询', 'see', 1040, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1947, '修改', 'update', 1040, 1, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1948, '删除', 'del', 1040, 1, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1949, '添加', 'add', 1041, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1950, '查询', 'see', 1041, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1951, '修改', 'update', 1041, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1952, '删除', 'del', 1041, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1953, '添加', 'add', 1042, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1954, '查询', 'see', 1042, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1957, '添加', 'add', 1043, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1958, '查询', 'see', 1043, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1959, '修改', 'update', 1043, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1960, '删除', 'del', 1043, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1961, '添加', 'add', 1086, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1962, '查询', 'see', 1086, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1963, '修改', 'update', 1086, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1964, '删除', 'del', 1086, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1965, '添加', 'add', 1087, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1966, '查询', 'see', 1087, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1967, '修改', 'update', 1087, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1968, '删除', 'del', 1087, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1969, '添加', 'add', 1088, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1970, '查询', 'see', 1088, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1971, '修改', 'update', 1088, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1972, '删除', 'del', 1088, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1973, '添加', 'add', 1089, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1974, '查询', 'see', 1089, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1975, '修改', 'update', 1089, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1976, '删除', 'del', 1089, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1977, '添加', 'add', 1091, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1978, '查询', 'see', 1091, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1979, '修改', 'update', 1091, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1980, '删除', 'del', 1091, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1981, '添加', 'add', 1093, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1982, '查询', 'see', 1093, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1983, '修改', 'update', 1093, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1984, '删除', 'del', 1093, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1985, '添加', 'add', 1096, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1986, '查询', 'see', 1096, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1987, '修改', 'update', 1096, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1988, '删除', 'del', 1096, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1989, '添加', 'add', 1097, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1990, '查询', 'see', 1097, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1991, '修改', 'update', 1097, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1992, '删除', 'del', 1097, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1993, '添加', 'add', 1098, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1994, '查询', 'see', 1098, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1995, '修改', 'update', 1098, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1996, '删除', 'del', 1098, 0, 4);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1997, '添加', 'add', 1099, 0, 2);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1998, '查询', 'see', 1099, 0, 1);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (1999, '修改', 'update', 1099, 0, 3);
INSERT INTO `t_ucenter_ms_menu_permission` VALUES (2000, '删除', 'del', 1099, 0, 4);

-- ----------------------------
-- Table structure for t_ucenter_ms_menu_permission_url_rela
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_menu_permission_url_rela`;
CREATE TABLE `t_ucenter_ms_menu_permission_url_rela`  (
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联url',
  PRIMARY KEY (`permission_id`, `url`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_menu_permission_url_rela
-- ----------------------------
INSERT INTO `t_ucenter_ms_menu_permission_url_rela` VALUES (30, '/user/add');

-- ----------------------------
-- Table structure for t_ucenter_ms_menu_server
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_menu_server`;
CREATE TABLE `t_ucenter_ms_menu_server`  (
  `id` int(11) NOT NULL,
  `server_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `server_url` varchar(102) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_menu_server
-- ----------------------------
INSERT INTO `t_ucenter_ms_menu_server` VALUES (1, 'base', 'http://localhost:8081/');

-- ----------------------------
-- Table structure for t_ucenter_ms_model
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_model`;
CREATE TABLE `t_ucenter_ms_model`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模块编号',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `model_server_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务编号',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_model
-- ----------------------------
INSERT INTO `t_ucenter_ms_model` VALUES ('b1d02a7673944496a170c8554358ac16', 'teta', 'dsada', '1', '2018-06-01 15:46:27', '1', '2018-06-01 15:46:27', 'dsadad');
INSERT INTO `t_ucenter_ms_model` VALUES ('c79e02e43776401693b119c03ab5039d', 'teta', 'dsada', '1', '2018-06-01 15:49:38', '1', '2018-06-01 15:49:38', 'dsadad');
INSERT INTO `t_ucenter_ms_model` VALUES ('e1e6647899aa43e8a8e8f8a324b1d5df', 'teta5555', 'dsada55', '1', '2018-06-01 15:47:51', '1', '2018-06-01 16:07:01', 'dsadad');

-- ----------------------------
-- Table structure for t_ucenter_ms_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_organization`;
CREATE TABLE `t_ucenter_ms_organization`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构名称',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父类编号',
  `ranking` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '同级菜单排行第几',
  `is_disable` int(1) NOT NULL COMMENT '是否启用(0:启用 1:禁用)',
  `project_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区id',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建用户',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `group_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集团编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_organization
-- ----------------------------
INSERT INTO `t_ucenter_ms_organization` VALUES ('001', '机构', '', '1', 0, '029c78e311014ff88d2eae8b7266d2fa', '1', '2018-09-13 16:49:17', '1', '2018-09-13 16:49:17', NULL);
INSERT INTO `t_ucenter_ms_organization` VALUES ('001001', '机构1', '001', '1', 0, '3127f1dd14794211a22568fcbf89c72d', '1', '2018-09-13 17:08:06', '1', '2018-09-13 17:08:06', NULL);
INSERT INTO `t_ucenter_ms_organization` VALUES ('001001001', '机构11', '001001', '1', 0, '029c78e311014ff88d2eae8b7266d2fa', '1', '2018-09-13 17:08:25', '1', '2018-09-13 17:08:25', NULL);
INSERT INTO `t_ucenter_ms_organization` VALUES ('001002', '机构2', '001', '2', 0, '029c78e311014ff88d2eae8b7266d2fa', '1', '2018-09-13 17:08:15', '1', '2018-09-13 17:08:15', NULL);

-- ----------------------------
-- Table structure for t_ucenter_ms_role
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_role`;
CREATE TABLE `t_ucenter_ms_role`  (
  `role_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_disable` int(1) NOT NULL COMMENT '是否启用(0:启用 1:禁用)',
  `organization_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属机构',
  `data_permissions` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '数据权限(资源类型，部门及小区)',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `group_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集团编码',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = ' T_admin_Role 平台角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_role
-- ----------------------------
INSERT INTO `t_ucenter_ms_role` VALUES (1, '1231', '12123', 0, '001', '{\"parkIds\":\"0d601eb23f0e11e99571d02788407b5e\"}', '1', '2019-03-25 21:35:49', '62b5870c510c4e9da3f72460001c42fa', '2019-03-29 14:46:25', NULL);
INSERT INTO `t_ucenter_ms_role` VALUES (2, '2333', '', 0, '001', '{\"parkId\":\"0d601eb23f0e11e99571d02788407b5e\"}', '1', '2019-03-28 14:47:23', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_ucenter_ms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_role_permission`;
CREATE TABLE `t_ucenter_ms_role_permission`  (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_role_permission
-- ----------------------------
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 30);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 31);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 32);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 33);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 34);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 35);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 36);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 37);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 38);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 39);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 40);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 41);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 444);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 445);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 446);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 447);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1134);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1135);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1136);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1137);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1652);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1653);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1654);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1696);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1702);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1704);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1705);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1860);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1861);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1862);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1863);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1864);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1865);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1866);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1867);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1868);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1869);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1870);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1871);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1872);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1873);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1874);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1875);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1876);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1877);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1878);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1879);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1880);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1881);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1882);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1883);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1884);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1885);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1886);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1887);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1888);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1889);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1890);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1891);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1892);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1893);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1894);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1895);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1896);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1925);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1926);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1927);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1928);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1929);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1930);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1931);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1932);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1933);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1934);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1935);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1936);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1937);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1938);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1939);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1940);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1945);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1946);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1947);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1948);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1949);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1950);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1951);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1952);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1953);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1954);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1957);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1958);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1959);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (1, 1960);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 30);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 31);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 32);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 33);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 34);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 35);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 36);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 37);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 38);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 39);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 40);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 41);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 444);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 445);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 446);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 447);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1134);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1135);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1136);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1137);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1652);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1653);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1654);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1696);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1702);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1704);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1705);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1860);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1861);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1862);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1863);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1864);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1865);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1866);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1867);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1868);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1869);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1870);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1871);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1872);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1873);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1874);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1875);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1876);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1877);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1878);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1879);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1880);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1881);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1882);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1883);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1884);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1885);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1886);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1887);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1888);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1889);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1890);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1891);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1892);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1893);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1894);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1895);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1896);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1901);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1902);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1903);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1904);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1925);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1926);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1927);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1928);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1929);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1930);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1931);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1932);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1933);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1934);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1935);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1936);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1937);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1938);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1939);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1940);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1945);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1946);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1947);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1948);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1949);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1950);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1951);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1952);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1953);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1954);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1957);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1958);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1959);
INSERT INTO `t_ucenter_ms_role_permission` VALUES (2, 1960);

-- ----------------------------
-- Table structure for t_ucenter_ms_role_user_rela
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_role_user_rela`;
CREATE TABLE `t_ucenter_ms_role_user_rela`  (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  PRIMARY KEY (`role_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'T_admin_Role_user_rela   平台角色用户中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_role_user_rela
-- ----------------------------
INSERT INTO `t_ucenter_ms_role_user_rela` VALUES (1, '62b5870c510c4e9da3f72460001c42fa');
INSERT INTO `t_ucenter_ms_role_user_rela` VALUES (2, '62b5870c510c4e9da3f72460001c42fa');

-- ----------------------------
-- Table structure for t_ucenter_ms_system
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_system`;
CREATE TABLE `t_ucenter_ms_system`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '子系统名称',
  `sort` int(10) NOT NULL COMMENT '排序',
  `logo` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '子系统logo',
  `is_disable` int(1) NOT NULL COMMENT '是否禁用 0:启用 1:禁用',
  `type` int(1) NOT NULL COMMENT '0 全新 1 集成现有',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '如果是集成第三方，则写第三方url',
  `index_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子系统首页url',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_system
-- ----------------------------
INSERT INTO `t_ucenter_ms_system` VALUES ('55ff598ae42845d4b51ec2bc71bbc008', '停车场', 1, '1', 1, 1, '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_ucenter_ms_system` VALUES ('55ff598ae42845d4b51ec2bc71bbc009', 'fhs基础功能', 1, '1', 1, 1, '1', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_ucenter_ms_user
-- ----------------------------
DROP TABLE IF EXISTS `t_ucenter_ms_user`;
CREATE TABLE `t_ucenter_ms_user`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `user_login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 登录名',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的名字',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别 0 男 1 女 2 未设置',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `is_disable` int(1) NULL DEFAULT NULL COMMENT '是否禁用 0:启用 1:禁用',
  `is_admin` int(1) NULL DEFAULT NULL COMMENT '是否超管 0:否 1:是',
  `province_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `city_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
  `area_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '县区',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `organization_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属机构',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `group_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集团编码-SAAS模式适用',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_login_name`(`user_login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'T_admin_USER  平台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ucenter_ms_user
-- ----------------------------
INSERT INTO `t_ucenter_ms_user` VALUES ('1', 'admin', 'admin22', 'e10adc3949ba59abbe56e057f20f883e', '13455393733', 0, '12333@123.com', 0, 1, '510000', '510700', '510704', 'AAAA', '001', NULL, NULL, '1', '2018-12-26 18:21:06', NULL);

SET FOREIGN_KEY_CHECKS = 1;
