/*
 Navicat Premium Data Transfer

 Source Server         : 新环境服务器
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : 124.220.46.104:3306
 Source Schema         : wxm_msfast

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 22/11/2023 10:36:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fr_blog
-- ----------------------------
DROP TABLE IF EXISTS `fr_blog`;
CREATE TABLE `fr_blog`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `user_id` int NOT NULL COMMENT '前台用户id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文字内容',
  `images` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '图片内容',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '动态地址',
  `lon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '纬度',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fr_blog_user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fr_blog_user_id` FOREIGN KEY (`user_id`) REFERENCES `fr_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户动态' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for fr_blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `fr_blog_comment`;
CREATE TABLE `fr_blog_comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `blog_id` int NOT NULL COMMENT '所属博客id',
  `parent_id` int NULL DEFAULT NULL COMMENT '所属评论id',
  `user_id` int NOT NULL COMMENT '评论用户id',
  `reply_id` int NULL DEFAULT NULL COMMENT '回复用户id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `blog_comment_blog_id`(`blog_id` ASC) USING BTREE,
  INDEX `blog_comment_user_id`(`user_id` ASC) USING BTREE,
  INDEX `blog_comment_reply_id`(`reply_id` ASC) USING BTREE,
  CONSTRAINT `blog_comment_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `fr_blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_comment_reply_id` FOREIGN KEY (`reply_id`) REFERENCES `fr_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `fr_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '备注' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for fr_user
-- ----------------------------
DROP TABLE IF EXISTS `fr_user`;
CREATE TABLE `fr_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登陆账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号密码',
  `head_portrait` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '昵称',
  `birthday` datetime NOT NULL COMMENT '出生日期',
  `lon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '纬度',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '城市',
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '性别',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户状态',
  `gold_balance` int NOT NULL DEFAULT 0 COMMENT '金币余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '前台用户信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for fr_user_follow
-- ----------------------------
DROP TABLE IF EXISTS `fr_user_follow`;
CREATE TABLE `fr_user_follow`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `user_id` int NOT NULL COMMENT '用户id',
  `follow_id` int NOT NULL COMMENT '关注者id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_follow_user_id`(`user_id` ASC) USING BTREE,
  INDEX `user_follow_follow_id`(`follow_id` ASC) USING BTREE,
  CONSTRAINT `user_follow_follow_id` FOREIGN KEY (`follow_id`) REFERENCES `fr_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_follow_user_id` FOREIGN KEY (`user_id`) REFERENCES `fr_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '前台用户关注关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for msf_config
-- ----------------------------
DROP TABLE IF EXISTS `msf_config`;
CREATE TABLE `msf_config`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设置编号',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '系统值',
  `descr` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述信息',
  `access` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '访问类型 PUBLIC/PRIVATE/INNER',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

INSERT INTO `wxm_msfast`.`msf_config` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `code`, `value`, `descr`, `access`) VALUES (NULL, now(), NULL, NULL, 0, 0, 'video_matching_tips', '[\"文明发言\",\"正在匹配中，请稍等\",\"好好学习，好好干饭\",\"晚上不要熬夜\"]', '视频匹配提示信息', NULL);
INSERT INTO `wxm_msfast`.`msf_config` (`creator`, `create_time`, `modifyer`, `modify_time`, `del_flag`, `version`, `code`, `value`, `descr`, `access`) VALUES (NULL, now(), NULL, NULL, 0, 0, 'home_bgm', 'https://www.wxmblog.com/files/2022/11/09/32717733-a1d6-4365-9640-2674661ff57a.mp3', '首页播放背景音乐', NULL);


-- ----------------------------
-- Table structure for msf_file
-- ----------------------------
DROP TABLE IF EXISTS `msf_file`;
CREATE TABLE `msf_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` int NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifyer` int NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `original` bit(1) NULL DEFAULT b'1' COMMENT '是否是原图',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件路径',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件状态 TEMP-临时文件 SAVED-已保存的文件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '备注' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
