/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : microservice_sso

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-03-04 15:00:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_role_id` bigint(15) NOT NULL COMMENT '??????ID',
  `role_id` bigint(15) DEFAULT NULL COMMENT '??ID',
  `user_id` bigint(15) DEFAULT NULL COMMENT '??ID',
  PRIMARY KEY (`user_role_id`),
  KEY `FKo4jryuw2ef7sb3vedhdwddp9x` (`role_id`),
  KEY `FKp53n1gguvrlo0fy8y0f7o7cx9` (`user_id`),
  CONSTRAINT `FKo4jryuw2ef7sb3vedhdwddp9x` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `FKp53n1gguvrlo0fy8y0f7o7cx9` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '259625530728448');
INSERT INTO `sys_user_role` VALUES ('2', '1', '259625868635136');
INSERT INTO `sys_user_role` VALUES ('3', '3', '272182553772032');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(15) NOT NULL,
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `mobile_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `credentials_salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gender` int(2) DEFAULT NULL,
  `delete_status` int(2) DEFAULT NULL,
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `tenant_id` bigint(15) DEFAULT NULL COMMENT '租户id',
  `open_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '第三方openid',
  `third_party_type` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '第三方类型',
  `avatar_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像URL',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('259625530728448', 'wangyong', 'wy-test', '$2a$10$XP7TNME251JiEIeVJAaKTO7Ka6aJ48rXZ4wkimdkeTOKRGfBtvYn2', '18190767007', null, '1', '0', '1546611602000', '271548828253184', null, null, null);
INSERT INTO `sys_user` VALUES ('259625868635136', 'zhangsan2', 'test1_nick', '$2a$10$uVLTk6KI6Tshz2va9uU74eY0630JLjjSATJBkrZTeBWhOXYJK3aGO', '18682561280', null, '1', '0', '1546611602000', '271548881272832', null, null, null);
INSERT INTO `sys_user` VALUES ('259625891326976', 'xiaobai', 'test1_nick', '$2a$10$Bw6Lad2aKa4.zjQLRf0NQ.PObCdH6/cFHCuQMinKcHUbmWFV0H5VK', '17708181221', null, '1', '0', '1546611602000', '271550916805632', null, null, null);
INSERT INTO `sys_user` VALUES ('260392889939968', 'test1', 'test1_u', 'test1_u', null, null, '1', '0', '1546611602000', null, null, null, null);
INSERT INTO `sys_user` VALUES ('272101213086720', '久久鸭脖', '90', '$2a$10$qidvUOVkuqZgXUDg.K3enOOzRUubcizlnh2wESkd09LNHcwX5UbOq', '13890909090', '', '0', '0', '1554558815687', '272103238562816', 'oid90', 'weixin', 'imgurl');
INSERT INTO `sys_user` VALUES ('272182553772032', 'zhangsan', 'zhangsan', '$2a$10$qnvFpMupR3FWcpWDzpi2tO6s/NoFhe3gVz4zKnzMiGdQgfduaEVaq', '18989898989', '', '1', '0', '1554638250159', null, 'weixin89', 'weixin', '');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_resource_id` bigint(15) NOT NULL COMMENT '??????ID',
  `resource_id` bigint(15) DEFAULT NULL COMMENT ' ??ID',
  `role_id` bigint(15) DEFAULT NULL COMMENT '??ID',
  PRIMARY KEY (`role_resource_id`),
  KEY `FKlquxtncq97x72r3dsbvlrpd4i` (`resource_id`),
  KEY `FKicubjaqbf5l8rqsdnrnkcc3xq` (`role_id`),
  CONSTRAINT `FKicubjaqbf5l8rqsdnrnkcc3xq` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `FKlquxtncq97x72r3dsbvlrpd4i` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色资源关系';

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('3', '101001002', '3');
INSERT INTO `sys_role_resource` VALUES ('259625530728449', '101', '1');
INSERT INTO `sys_role_resource` VALUES ('259625530728450', '101001', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(15) NOT NULL,
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `role_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `locked` int(2) DEFAULT NULL,
  `delete_status` int(2) DEFAULT NULL,
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `tenant_id` bigint(15) DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`role_id`),
  KEY `index_role_rolename` (`role_name`),
  KEY `index_role_rolecode` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='岗位信息';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', 'ADMIN', '0', '0', '0', null, null);
INSERT INTO `sys_role` VALUES ('2', 'NORMAL', 'NORMAL', '2', '0', '0', null, null);
INSERT INTO `sys_role` VALUES ('3', 'ROLE_ZS', 'ROLE_ZS', '张三的角色', '0', '0', null, null);

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `resource_id` bigint(15) NOT NULL,
  `delete_status` int(2) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `hide` int(2) DEFAULT NULL,
  `rank` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `parent_id` bigint(15) DEFAULT NULL,
  `resource_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `resource_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `resource_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='资源信息';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('101', null, '系统基础管理', 'fa-desktop', null, '1', null, 'system', '系统管理', 'system', '0', null);
INSERT INTO `sys_resource` VALUES ('102', null, '业务管理', 'large_chart', null, '1', null, 'business', '业务管理', 'business', '0', null);
INSERT INTO `sys_resource` VALUES ('103', null, '系统监控管理', 'fa-tag', null, '16', null, 'monitor', '系统监控管理', 'monitor', '0', null);
INSERT INTO `sys_resource` VALUES ('104', null, null, 'fa-desktop', null, '21', null, 'log', '日志管理', 'log', '1', null);
INSERT INTO `sys_resource` VALUES ('101001', null, null, 'fa-desktop', null, '2', null, 'system:user:listUI', '用户管理', 'system/user/listUI.shtml', '1', null);
INSERT INTO `sys_resource` VALUES ('101002', null, '组管理', 'fa-list', null, '7', null, 'system:role:listUI', '角色管理', 'system/role/rolelistUI.shtml', '1', null);
INSERT INTO `sys_resource` VALUES ('101003', null, '菜单管理', 'fa-list-alt', null, '12', null, 'system:resource:listUI', '菜单资源管理', 'system/resources/list.shtml', '1', null);
INSERT INTO `sys_resource` VALUES ('102001', null, '合同管理', 'large_chart', null, null, null, 'business:contract:listUI', '合同管理', 'business/contract/listUI.shtml', '1', null);
INSERT INTO `sys_resource` VALUES ('103001', null, '实时监控', 'fa-desktop', null, '17', null, 'monitor:realtime:listUI', '实时监控', 'monitor/realtime/listUI.shtml', '1', null);
INSERT INTO `sys_resource` VALUES ('103002', null, '告警列表', 'fa-desktop', null, null, null, 'monitor:warn:listUI', '告警列表', 'monitor/warn/list.shtml', '1', null);
INSERT INTO `sys_resource` VALUES ('104001', null, '用户登录记录', 'fa-desktop', null, '19', null, 'log:login:listUI', '登录日志管理', 'log/login/listUI.shtml', '1', null);
INSERT INTO `sys_resource` VALUES ('104002', null, '操作日志管理', 'fa-picture-o', null, '20', null, 'log:operation:listUI', '操作日志管理', 'log/operation/listUI.shtml', '0', null);
INSERT INTO `sys_resource` VALUES ('101001001', null, '&lt;button type=&quot;button&quot; id=&quot;addUser&quot; class=&quot;btn btn-primary marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, '3', null, 'system:user:addUI', '新增用户', 'system/user/addUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101001002', null, '&lt;button type=&quot;button&quot; id=&quot;editUser&quot; class=&quot;btn btn-info marR10&quot;&gt;编辑&lt;/button&gt;', 'fa-desktop', null, '4', null, 'system:user:editUI', '修改用户', 'system/user/editUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101001003', null, '&lt;button type=&quot;button&quot; id=&quot;delUser&quot; class=&quot;btn btn-danger marR10&quot;&gt;删除&lt;/button&gt;', 'fa-desktop', null, '5', null, 'system:user:delete', '用户删除', 'system/user/delete.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101001004', null, '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;permissions&quot;&nbsp;class=&quot;btn&nbsp;btn&nbsp;btn-grey&nbsp;marR10&quot;&gt;分配权限&lt;/button&gt;', 'fa-desktop', null, '6', null, 'system:user:authorization', '用户授权', 'system/user/authorization.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101001005', null, '&lt;button type=&quot;button&quot; id=&quot;addUser&quot; class=&quot;btn btn-primary marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, '3', null, 'system:user:add', '用户新增', 'system/user/addUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101001006', null, null, null, null, null, null, 'system:user:edit', '用户编辑', null, null, null);
INSERT INTO `sys_resource` VALUES ('101002001', null, '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addRole&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, '8', null, 'system:role:addUI', '新增角色', 'system/role/addUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101002002', null, '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editRole&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;', 'fa-desktop', null, '9', null, 'system:role:editUI', '修改角色', 'system/role/editUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101002003', null, '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delRole&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;', 'fa-desktop', null, '10', null, 'system:role:delete', '角色删除', 'system/role/delete.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101002004', null, '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;permissions&quot;&nbsp;class=&quot;btn&nbsp;btn&nbsp;btn-grey&nbsp;marR10&quot;&gt;分配权限&lt;/button&gt;', 'fa-desktop', null, '11', null, 'system:role:authorization', '角色授权', 'system/role/authorization.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101002005', null, '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delRole&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;', 'fa-desktop', null, '10', null, 'system:role:add', '角色新增', 'system/role/delete.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101002006', null, '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editRole&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;', 'fa-desktop', null, '9', null, 'system:role:edit', '角色修改', 'system/role/editUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101003001', null, '&lt;button type=&quot;button&quot; id=&quot;addResources&quot; class=&quot;btn btn-primary marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, '13', null, 'system:resources:addUI', '新增菜单资源', 'system/resources/addUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101003002', null, '&lt;button type=&quot;button&quot; id=&quot;editResources&quot; class=&quot;btn btn-info marR10&quot;&gt;编辑&lt;/button&gt;', 'fa-desktop', null, '14', null, 'system:resource:editUI', '修改菜单资源', 'system/resources/editUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101003003', null, '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;', 'fa-desktop', null, '15', null, 'system:resource:delete', '删除菜单资源', 'system/resource/delete.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101003004', null, '&lt;button type=&quot;button&quot; id=&quot;addResources&quot; class=&quot;btn btn-primary marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, '13', null, 'system:resources:add', '菜单资源新增', 'system/resources/addUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('101003005', null, '&lt;button type=&quot;button&quot; id=&quot;addResources&quot; class=&quot;btn btn-primary marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, '13', null, 'system:resources:edit', '菜单资源编辑', 'system/resources/addUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('102001001', null, '&lt;button type=&quot;button&quot; id=&quot;addUser&quot; class=&quot;btn btn-primary marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, null, null, 'business:contract:addUI', '新增合同', 'business/contract/addUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('102001002', null, '&lt;button type=&quot;button&quot; id=&quot;addUser&quot; class=&quot;btn btn-primary marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, null, null, 'business:contract:editUI', '编辑合同', 'business/contract/addUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('102001003', null, '&lt;button type=&quot;button&quot; id=&quot;addUser&quot; class=&quot;btn btn-primary marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, null, null, 'business:contract:delete', '合同删除', 'business/contract/addUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('102001004', null, '&lt;button type=&quot;button&quot; id=&quot;addUser&quot; class=&quot;btn btn-primary marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, null, null, 'business:contract:add', '合同新增', 'business/contract/addUI.shtml', '2', null);
INSERT INTO `sys_resource` VALUES ('102001005', null, '&lt;button type=&quot;button&quot; id=&quot;addUser&quot; class=&quot;btn btn-primary marR10&quot;&gt;新增&lt;/button&gt;', 'fa-desktop', null, null, null, 'business:contract:edit', '合同编辑', 'business/contract/addUI.shtml', '2', null);

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) CHARACTER SET utf8 NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) COLLATE utf8_bin NOT NULL,
  `resource_ids` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `client_secret` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `scope` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `authorized_grant_types` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `authorities` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) COLLATE utf8_bin DEFAULT NULL,
  `autoapprove` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client1', null, '$2a$10$dYRcFip80f0jIKGzRGulFelK12036xWQKgajanfxT65QB4htsEXNK', 'app', 'authorization_code', 'http://localhost:8080/login', null, null, null, null, null);
INSERT INTO `oauth_client_details` VALUES ('client2', null, '$2a$10$dYRcFip80f0jIKGzRGulFelK12036xWQKgajanfxT65QB4htsEXNK', 'app', 'authorization_code', 'http://localhost:8081/login', null, null, null, null, null);
INSERT INTO `oauth_client_details` VALUES ('demo', null, '$2a$10$dYRcFip80f0jIKGzRGulFelK12036xWQKgajanfxT65QB4htsEXNK', 'app', 'authorization_code', 'http://localhost:9090/login', null, null, null, null, null);
INSERT INTO `oauth_client_details` VALUES ('system', null, '$2a$10$dYRcFip80f0jIKGzRGulFelK12036xWQKgajanfxT65QB4htsEXNK', 'app', 'authorization_code', 'http://localhost:12000/login', null, null, null, null, null);
INSERT INTO `oauth_client_details` VALUES ('test1', null, '$2a$10$BVke270Ht1Jh.ZOoquIIGOV.PHaD.OfUxDF5RHF3iEUTIxf6pku0y', 'app', 'authorization_code', 'http://locahost:9091/login', null, null, null, null, null);
INSERT INTO `oauth_client_details` VALUES ('test2', null, '{bcrypt}$2a$10$aaU55B0gReIF0f.H5cDnreiutLytnfoybaeZauHuICONbwcK8GHea', 'app', 'authorization_code', 'http://locahost:9091/login', null, null, null, null, null);

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `clientId` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) CHARACTER SET utf8 NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------
INSERT INTO `oauth_access_token` VALUES ('b22a96826065f1d766a387dab5421c72', 0xACED0005737200436F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744F4175746832416363657373546F6B656E0CB29E361B24FACE0200064C00156164646974696F6E616C496E666F726D6174696F6E74000F4C6A6176612F7574696C2F4D61703B4C000A65787069726174696F6E7400104C6A6176612F7574696C2F446174653B4C000C72656672657368546F6B656E74003F4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F636F6D6D6F6E2F4F417574683252656672657368546F6B656E3B4C000573636F706574000F4C6A6176612F7574696C2F5365743B4C0009746F6B656E547970657400124C6A6176612F6C616E672F537472696E673B4C000576616C756571007E000578707372001E6A6176612E7574696C2E436F6C6C656374696F6E7324456D7074794D6170593614855ADCE7D002000078707372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000165368930387870737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574801D92D18F9B80550200007872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C0001637400164C6A6176612F7574696C2F436F6C6C656374696F6E3B7870737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E020000787200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000103F400000000000017400036170707874000662656172657274002434613732633633382D643131392D346263642D626163332D613930666136656231393761, '4fc22277cc4e335186985f7ed7511b9d', 'client', 'client', 0xACED0005737200416F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F417574683241757468656E7469636174696F6EBD400B02166252130200024C000D73746F7265645265717565737474003C4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F4F4175746832526571756573743B4C00127573657241757468656E7469636174696F6E7400324C6F72672F737072696E676672616D65776F726B2F73656375726974792F636F72652F41757468656E7469636174696F6E3B787200476F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E416273747261637441757468656E7469636174696F6E546F6B656ED3AA287E6E47640E0200035A000D61757468656E746963617465644C000B617574686F7269746965737400164C6A6176612F7574696C2F436F6C6C656374696F6E3B4C000764657461696C737400124C6A6176612F6C616E672F4F626A6563743B787000737200266A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654C697374FC0F2531B5EC8E100200014C00046C6973747400104C6A6176612F7574696C2F4C6973743B7872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C00016371007E00047870737200136A6176612E7574696C2E41727261794C6973747881D21D99C7619D03000149000473697A657870000000007704000000007871007E000C707372003A6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F41757468325265717565737400000000000000010200075A0008617070726F7665644C000B617574686F72697469657371007E00044C000A657874656E73696F6E7374000F4C6A6176612F7574696C2F4D61703B4C000B72656469726563745572697400124C6A6176612F6C616E672F537472696E673B4C00077265667265736874003B4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F546F6B656E526571756573743B4C000B7265736F7572636549647374000F4C6A6176612F7574696C2F5365743B4C000D726573706F6E7365547970657371007E0011787200386F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E426173655265717565737436287A3EA37169BD0200034C0008636C69656E74496471007E000F4C001172657175657374506172616D657465727371007E000E4C000573636F706571007E00117870740006636C69656E74737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170F1A5A8FE74F507420200014C00016D71007E000E7870737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000006740004636F646574000637584D6D797174000A6772616E745F74797065740012617574686F72697A6174696F6E5F636F646574000D726573706F6E73655F74797065740004636F646574000D636C69656E745F73656372657474000673656372657474000C72656469726563745F757269740014687474703A2F2F7777772E62616964752E636F6D740009636C69656E745F696471007E001478737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574801D92D18F9B80550200007871007E0009737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E020000787200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000103F4000000000000174000361707078017371007E0027770C000000103F40000000000000787371007E00173F400000000000007708000000100000000078740014687474703A2F2F7777772E62616964752E636F6D707371007E0027770C000000103F40000000000000787371007E0027770C000000103F4000000000000171007E001E787372004F6F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E557365726E616D6550617373776F726441757468656E7469636174696F6E546F6B656E00000000000001A40200024C000B63726564656E7469616C7371007E00054C00097072696E636970616C71007E00057871007E0003017371007E00077371007E000B000000007704000000007871007E0032737200486F72672E737072696E676672616D65776F726B2E73656375726974792E7765622E61757468656E7469636174696F6E2E57656241757468656E7469636174696F6E44657461696C7300000000000001A40200024C000D72656D6F74654164647265737371007E000F4C000973657373696F6E496471007E000F787074000F303A303A303A303A303A303A303A317070740006636C69656E74, null);
