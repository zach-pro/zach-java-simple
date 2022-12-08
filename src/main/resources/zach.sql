/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : 127.0.0.1:3306
 Source Schema         : zach

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 08/11/2022 10:40:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ai_menu
-- ----------------------------
DROP TABLE IF EXISTS `ai_menu`;
CREATE TABLE `ai_menu` (
  `id` varchar(30) NOT NULL,
  `parent_id` varchar(30) DEFAULT NULL COMMENT '父类id',
  `menu_href` varchar(255) DEFAULT NULL COMMENT '菜单地址\r\n',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `sort` int DEFAULT NULL COMMENT '排序',
  `type` int DEFAULT NULL COMMENT '0:父 1:子',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建用户\r\n',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='菜单表';

-- ----------------------------
-- Records of ai_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_permission
-- ----------------------------
DROP TABLE IF EXISTS `ai_permission`;
CREATE TABLE `ai_permission` (
  `id` varchar(30) NOT NULL COMMENT '主键id',
  `permission_code` varchar(32) DEFAULT NULL COMMENT '权限code',
  `permission_name` varchar(32) DEFAULT NULL COMMENT '权限名',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='权限表';

-- ----------------------------
-- Records of ai_permission
-- ----------------------------
BEGIN;
INSERT INTO `ai_permission` (`id`, `permission_code`, `permission_name`, `create_user`, `create_time`) VALUES ('DrTpGpf3sfb96PENrEpLV', 'modify_user', '修改用户', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_permission` (`id`, `permission_code`, `permission_name`, `create_user`, `create_time`) VALUES ('EHHbJkd77qPfS3TKlBNeA', 'query_user', '查看用户', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_permission` (`id`, `permission_code`, `permission_name`, `create_user`, `create_time`) VALUES ('RR0_F521-0h6t6fRm_von', 'create_user', '创建用户', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_permission` (`id`, `permission_code`, `permission_name`, `create_user`, `create_time`) VALUES ('ZO7sddSwa5F4WdevPd4eb', 'delete_user', '删除用户', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for ai_request_path
-- ----------------------------
DROP TABLE IF EXISTS `ai_request_path`;
CREATE TABLE `ai_request_path` (
  `id` varchar(30) NOT NULL COMMENT '主键id',
  `url` varchar(200) NOT NULL COMMENT '请求路径',
  `tag_name` varchar(255) DEFAULT NULL COMMENT '标签组',
  `tag_rank` int DEFAULT NULL COMMENT '下标值',
  `description` varchar(128) DEFAULT NULL COMMENT '路径描述',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='请求路径';

-- ----------------------------
-- Records of ai_request_path
-- ----------------------------
BEGIN;
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('-AeheieDEh3tBIOyjrPeF', '/graph_nebula/nextVertex', 'graph_nebula', 8, '下个节点查询', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('0-Oht9MtPUc7ZovEETqHd', '/ai_task_journal/aiTaskJournal/selTaskJournalLists', 'ai_task_journal', 0, '查询任务日志列表', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('208piMtF2oKRStaFsQVnJ', '/ai_task/aiTask/saveTask', 'ai_task', 0, '新建定时任务', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('31tNocQ5kIKV2uagc2XaZ', '/ai_menu/aiMenu/delMenu', 'ai_menu', 3, '删除菜单', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('3H_-MIf1tjbm8APi1Jo-t', '/ai_request_path/aiRequestPath/saveRequestPath', 'ai_request_path', 1, '新建请求路径', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('3MHHXoBInKalrnU5TN8ng', '/ai_task/aiTask/delTask', 'ai_task', 3, '删除定时任务', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('3usVr_mHUxDf5tvON2gTR', '/ai_menu/aiMenu/selMenuInfo', 'ai_menu', 5, '查询菜单详情', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('4jYsdykRnre2tkN8yev0w', '/graph_nebula/queryVertexDetails', 'graph_nebula', 10, '查询tag详情', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('4qMefrQNoyxVZpK9gjxVS', '/demo_url/Demo/saveDemo', 'demo_url', 1, '保存demo', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('8U4mJ9TxE4YGAhgUaCDSU', '/minio/download', 'minio', 2, '下载文件', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('8XLun5G7CrvtgoqowLbdg', '/ai_request_path/aiRequestPath/selRequestPathList', 'ai_request_path', 5, '查询请求路径列表', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('aq-1HDUxjltrEJ9RWA74p', '/ai_task/aiTask/selTaskLists', 'ai_task', 2, '查询定时任务列表', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('bfYzZb1jgdiHF7k4zX6vl', '/minio/previewImg', 'minio', 3, '预览图片', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('BlPTGcj_hbK2fLqCgNfp8', '/graph_nebula/queryPath', 'graph_nebula', 11, '两点之间路径', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('CJqmYjuAe8CDNYRN9F4Dw', '/ai_task/aiTask/enableTask', 'ai_task', 5, '启用定时任务', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('ckL_6-JI5IVq1beCKbZ7g', '/minio/analysisWord', 'minio', 5, '解析Word文件', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('D8m9QeML_4fX0YTuVH_gC', '/ai_user/aiUser/setEnabledUser', 'ai_user', 4, '设置用户是否可用', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('DD0gEekkRqv-cAbyMfEav', '/ai_user/aiUser/delUser', 'ai_user', 8, '删除用户', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('DXTZI8nupewEWcySjwhpF', '/graph_nebula/updateEdge', 'graph_nebula', 0, '更新关系', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('ejVmOUoBjOjjJTcftnNHs', '/minio/preview', 'minio', 1, '预览文件', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('g8_CcFFLbAVW9YunBXhhK', '/ai_menu/aiMenu/selMenuLists', 'ai_menu', 1, '查询菜单列表', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('h0RL4P1SRAAFGJhLiRW2w', '/ai_task/aiTask/ceaseTask', 'ai_task', 1, '停止定时任务', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('hmdWcQTNDy0ZSbhq35jU9', '/graph_nebula/deleteEdge', 'graph_nebula', 1, '删除关系', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('if9raPEbh_XdJkDnRBJUq', '/ai_role/aiRole/delRole', 'ai_role', 2, '删除角色', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('IOiilwYpSk70qcFxZuGUi', '/graph_nebula/visualization', 'graph_nebula', 12, '数据可视化界面', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('ivr9AaFBJo716F0MIgvLe', '/ai_task/aiTask/selTaskInfo', 'ai_task', 7, '查询定时任务详情', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('JCdl4pbqMBb6qU5NNyYCT', '/graph_nebula/showTags', 'graph_nebula', 7, '查询实体标签', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('JEYYc807M0vnkpHQpcI5B', '/minio/analysisPDF', 'minio', 4, '解析PDF文件', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('K11mSN416G4MxZ9anwQSD', '/ai_request_path/aiRequestPath/selSwaggerApiDocAdd', 'ai_request_path', 3, 'Api赋权', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('KrPSb4Yn6LLXMHcbXeLjO', '/minio/remove', 'minio', 6, '删除文件', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('L0C5kynyvMA9FY3BbDOEq', '/ai_request_path/aiRequestPath/upRequestPath', 'ai_request_path', 4, '修改请求路径', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('mjFy_dgRSkF7IDZrItn9_', '/graph_nebula/clearSpaceAll', 'graph_nebula', 15, '清空数据', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('nsJgvufBXo_hnhnvvJv5q', '/graph_nebula/deleteTagEdge', 'graph_nebula', 14, '删除节点及关系', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('ovnRsyFxGNgJwjHnk95HD', '/ai_user/aiUser/upUser', 'ai_user', 6, '修改用户', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('P4yRc_zlQIkiKpqhTHGm5', '/ai_task/aiTask/runTask', 'ai_task', 4, '执行定时任务', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('pGPvxvm2fPLBx3UW9RV5K', '/graph_nebula/extendedQuery', 'graph_nebula', 2, '扩展查询', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('Pg_7HbY1AABSR_gYbOkUf', '/ai_role/aiRole/upRole', 'ai_role', 3, '修改角色', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('PJbD-Fb8b-Ea-poAHIpkH', '/ai_menu/aiMenu/upMenu', 'ai_menu', 4, '修改菜单', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('PpZnTfu8QnlLg1lVHA2--', '/ai_task/aiTask/upTask', 'ai_task', 6, '修改定时任务', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('Q-p9XNc1NAUIWZWzTLud0', '/demo_url/Demo/getDemo', 'demo_url', 0, '查询demo', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('QK2dO-PyWHjBmtluxkW9D', '/ai_menu/aiMenu/saveMenu', 'ai_menu', 0, '新建菜单', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('Q_th4bdl_9ziaIjVjQbhm', '/ai_role/aiRole/selRoleInfo', 'ai_role', 4, '查询角色详情', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('r0ILyBOuYh7S-xY6r-1sa', '/graph_nebula/insertEdge', 'graph_nebula', 9, '新增关系', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('sdxDOEE7RA8j0zl8As1Bf', '/ai_user/aiUser/selUserLists', 'ai_user', 1, '查询用户列表', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('SPyhZd7Oa36zoPY5c2zsv', '/graph_nebula/insertTag', 'graph_nebula', 13, '新增节点', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('sqiWGwQGV_ht7snHR7Ppz', '/ai_request_path/aiRequestPath/delRequestPath', 'ai_request_path', 0, '删除请求路径', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('sV9lv-qjHL6WL_454dP5-', '/ai_role/aiRole/selRoleLists', 'ai_role', 0, '查询角色列表', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('T8lbsmO0hI85U2cCW_lAm', '/ai_menu/aiMenu/selParentMenuLists', 'ai_menu', 2, '查询父级菜单列表', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('TervwRyD05m6UIEmqWM0U', '/graph_nebula/updateTag', 'graph_nebula', 3, '更新节点', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('uEfmPXK4-TsLBmzGGrFzJ', '/minio/upload', 'minio', 0, '上传文件', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('VdI_HHUEDwdhofSfuK8Ob', '/ai_role/aiRole/saveRole', 'ai_role', 1, '新建角色', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('w1o29cxyXqSxa5lQJYyG5', '/ai_user/aiUser/setLockUser', 'ai_user', 7, '设置用户是否锁定', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('WYgDXkZcX82TNOnn2L0qJ', '/ai_user/aiUser/resetPassword', 'ai_user', 0, '重置密码', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('XGyAQIXRirjARFbJR_3Q9', '/ai_request_path/aiRequestPath/selRequestPath', 'ai_request_path', 2, '查询请求路径详情', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('xvHti7i7kHx3t3t-dpYva', '/graph_nebula/showTagAttrs', 'graph_nebula', 16, '查询实体属性', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('xwXq_IdQ_6SxE-HCdyjRJ', '/ai_user/aiUser/upPassword', 'ai_user', 3, '修改密码', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('Yg1y8TNBa_80zhDWxx9DD', '/graph_nebula/showTagEdge', 'graph_nebula', 6, '查询实体关联边', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('YjkF-pv1idSgcWpKlKXp7', '/ai_user/aiUser/selUserInfo', 'ai_user', 5, '查询用户详情', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('YUS_TOqqo0FGFusSEAuaD', '/ai_user/aiUser/saveUser', 'ai_user', 2, '保存用户信息', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('yzVhDRl3xUdWYAUT5m2BF', '/graph_nebula/customStatement', 'graph_nebula', 4, '图数据库查询', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path` (`id`, `url`, `tag_name`, `tag_rank`, `description`, `create_user`, `create_time`) VALUES ('zNV5ky6hYR66DKilKezlC', '/graph_nebula/deleteTag', 'graph_nebula', 5, '删除节点', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for ai_request_path_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ai_request_path_permission_relation`;
CREATE TABLE `ai_request_path_permission_relation` (
  `id` varchar(30) NOT NULL COMMENT '主键id',
  `url_id` varchar(30) DEFAULT NULL COMMENT '请求路径id',
  `permission_id` varchar(30) DEFAULT NULL COMMENT '权限id',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='路径权限关联表';

-- ----------------------------
-- Records of ai_request_path_permission_relation
-- ----------------------------
BEGIN;
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('-5sSntNAr2v9dZ5Z8s780', 'DXTZI8nupewEWcySjwhpF', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('-6us9K-xCe80JT5JvKGGn', 'IOiilwYpSk70qcFxZuGUi', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('-b_jT7GUMeV2TReiS_Fuw', '4jYsdykRnre2tkN8yev0w', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('-l_K4CZfPgRyftg7iponb', 'SPyhZd7Oa36zoPY5c2zsv', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('02E7RDPjwO7wnyh-gPraX', 'JCdl4pbqMBb6qU5NNyYCT', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('04bvw_kqeoj8ofdVFpj3v', 'TervwRyD05m6UIEmqWM0U', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('0bhGmBIsh0sAGc7RUWizC', 'P4yRc_zlQIkiKpqhTHGm5', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('0LaFPIjMzVgEoPyi20ajj', 'pGPvxvm2fPLBx3UW9RV5K', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('0OWCYqZ4lyjybd7dvcO1T', '8U4mJ9TxE4YGAhgUaCDSU', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('0unS7-Df4q4nfnjHTtrWQ', 'PpZnTfu8QnlLg1lVHA2--', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('0y3tg5Y62r-_NS9Gwqfmk', 'PJbD-Fb8b-Ea-poAHIpkH', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('1-LV4XeqkjKTyvAT__G7B', 'g8_CcFFLbAVW9YunBXhhK', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('1cnjw4H5xU4wUv2g7EZRQ', '4qMefrQNoyxVZpK9gjxVS', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('1PU75WHAYUGziQm7sE06K', '-AeheieDEh3tBIOyjrPeF', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('1rKFX05p2O2Vi7b3Hedhn', '208piMtF2oKRStaFsQVnJ', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('27aW3MuW-cqKZKjYU4D6t', 'Yg1y8TNBa_80zhDWxx9DD', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('2DNK26AZx9COSmbf_9yT-', '8U4mJ9TxE4YGAhgUaCDSU', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('2FsXhQH88wChmVLdd1Yx4', 'g8_CcFFLbAVW9YunBXhhK', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('2PjJg8ZGLF6ylZM_7D8gd', 'sqiWGwQGV_ht7snHR7Ppz', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('35eVQ--JKQKAVUEeASuyU', 'mjFy_dgRSkF7IDZrItn9_', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('38UCBwOU-J9JFR1y-BVXb', 'PpZnTfu8QnlLg1lVHA2--', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('3BAF03kDIbUH2umMCv1ZC', 'YUS_TOqqo0FGFusSEAuaD', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('3CP68XoISPsY4KcdZLtE8', 'P4yRc_zlQIkiKpqhTHGm5', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('3naGusvBb1tI75-XnWe02', '0-Oht9MtPUc7ZovEETqHd', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('3r4xfRuOSWYbLQvQejP3o', 'DD0gEekkRqv-cAbyMfEav', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('3zMyyhtYr6KmHASp8U8ok', 'sV9lv-qjHL6WL_454dP5-', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('4nQCTgfR0HEcwpRqggJq9', 'VdI_HHUEDwdhofSfuK8Ob', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('4yGG2NC8mPg0G2G9Ccajf', 'JCdl4pbqMBb6qU5NNyYCT', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('5DaGC4epdZ-absNa5Ordw', 'Q-p9XNc1NAUIWZWzTLud0', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('5k6QF_Xozq_6CqsEu3yOx', 'sdxDOEE7RA8j0zl8As1Bf', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('5mqwy7VXYSdkHXVT8_7dh', 'JEYYc807M0vnkpHQpcI5B', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('5rD-ZhXVeuTYhC-DNEWkf', 'ckL_6-JI5IVq1beCKbZ7g', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('5Yt9CW6oldMn8g1nufBY3', 'YUS_TOqqo0FGFusSEAuaD', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('6WHuQBXRNSTXllwicDuEj', 'mjFy_dgRSkF7IDZrItn9_', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('6ZURHE8TTTq-cnNvr6aTe', 'L0C5kynyvMA9FY3BbDOEq', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('7boymetInEMb7j5IkujD4', 'K11mSN416G4MxZ9anwQSD', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('7Ku78iTGiKQ9uVnqhU361', 'CJqmYjuAe8CDNYRN9F4Dw', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('7NEE9Vx13_fLTKBuf1CSd', 'hmdWcQTNDy0ZSbhq35jU9', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('7VzP57c2o0OeZ-ZbTmH3z', 'bfYzZb1jgdiHF7k4zX6vl', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('7x1mg1tridcl9nGZ92IMR', 'ivr9AaFBJo716F0MIgvLe', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('87QCx5q-eaPNl66yw25eO', 'YjkF-pv1idSgcWpKlKXp7', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('8B1zdfmfGN6mvJ7VWewwJ', 'DD0gEekkRqv-cAbyMfEav', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('8cEdhW5cE7cXa3RZXdKsM', 'ejVmOUoBjOjjJTcftnNHs', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('8KQVhq6sxfh70izkIBhQS', 'h0RL4P1SRAAFGJhLiRW2w', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('8LKcT_Jy9wuqyOfExf89p', 'pGPvxvm2fPLBx3UW9RV5K', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('8_mQV91bW0Oa5jexUdrnS', 'PJbD-Fb8b-Ea-poAHIpkH', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('9aKmJeYb4TA7iycyxowJJ', 'TervwRyD05m6UIEmqWM0U', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('9ig_JDcBLnAWWTPAQBinc', 'ckL_6-JI5IVq1beCKbZ7g', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('9rO2mqm2ZSLCYG-Wjf7b7', '3usVr_mHUxDf5tvON2gTR', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('9soDHeHsIgY1T1bwgDV2B', 'Pg_7HbY1AABSR_gYbOkUf', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('9TgTwYNCXJnuJjMxIWHAj', 'if9raPEbh_XdJkDnRBJUq', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('aa4UEb_vBITKxy9PGGgpg', 'BlPTGcj_hbK2fLqCgNfp8', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('AAxuEnx-3BMl6FMWrh737', 'r0ILyBOuYh7S-xY6r-1sa', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Ae2iPAyHr8Z6gTQdNaJbM', 'Yg1y8TNBa_80zhDWxx9DD', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('AEpimAH2tBRZNeY930rGL', 'sqiWGwQGV_ht7snHR7Ppz', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ahdBf01kuJpCCnXEctJ4f', 'JEYYc807M0vnkpHQpcI5B', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('AksYh4mZqQC0VgKHmmF6_', 'PJbD-Fb8b-Ea-poAHIpkH', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ALORkZXQ4pPIvew4pqTn_', 'h0RL4P1SRAAFGJhLiRW2w', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ALtNGnwgC3Sr4cb87jVIS', 'P4yRc_zlQIkiKpqhTHGm5', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('amMcRJ5ei-EdAZy8IFE25', 'YjkF-pv1idSgcWpKlKXp7', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('AnWtjL3OFryDc3UVf4CX8', 'sdxDOEE7RA8j0zl8As1Bf', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('AOAWo4_heu3De36k_CXsq', 'IOiilwYpSk70qcFxZuGUi', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('aOP3gHorns0VgipVXtj84', 'D8m9QeML_4fX0YTuVH_gC', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('BB8DZmaSV1YGlIkFMOkH8', 'hmdWcQTNDy0ZSbhq35jU9', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('bH1CaMv1Sp_vj0iHrJZOl', '-AeheieDEh3tBIOyjrPeF', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('bHOZ8fAoK2lKcVP7h4QVV', 'nsJgvufBXo_hnhnvvJv5q', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('BNRk9OnM-3_8HNY1fmhZW', '3MHHXoBInKalrnU5TN8ng', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('c1Nzswje-MFO4PbKEjSn6', 'YjkF-pv1idSgcWpKlKXp7', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ca2mKAPpTUZzwf2c-Gz5O', 'YUS_TOqqo0FGFusSEAuaD', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('cCQVX9E5n7n66aoG4FnKY', '208piMtF2oKRStaFsQVnJ', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('CHkkiMdGFR9xvisAixqKa', '3MHHXoBInKalrnU5TN8ng', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ciUc4_eYpIQqEFYgB9ZMo', 'CJqmYjuAe8CDNYRN9F4Dw', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('cQ3VlMkLTF_NOWCHv02__', 'uEfmPXK4-TsLBmzGGrFzJ', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Cvl-gKmOvAhm4McmFjCP9', 'h0RL4P1SRAAFGJhLiRW2w', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('CVW_neAJQi-UglxtMU1t8', 'xvHti7i7kHx3t3t-dpYva', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('cXJg8m7wMQnjbzpoIUjz2', 'PpZnTfu8QnlLg1lVHA2--', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('D-K3vb6FUiiBNYyta8WKm', 'if9raPEbh_XdJkDnRBJUq', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('d2Qs20DuJ66fH3bkUBlYg', '3MHHXoBInKalrnU5TN8ng', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('d8Z2Q89h7ffD3PLKhXUcG', 'JEYYc807M0vnkpHQpcI5B', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('dBS03rpHlhG-IVxqPYPCl', 'zNV5ky6hYR66DKilKezlC', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('deBk-8tueQsAGnZziS6YV', 'QK2dO-PyWHjBmtluxkW9D', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Di2kzQhJqpDc3qPWmwW_U', 'uEfmPXK4-TsLBmzGGrFzJ', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('dmEPqDKYtGFt7z_G2eamk', 'if9raPEbh_XdJkDnRBJUq', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('dQ6keLOvy2_hLADqt2om6', 'JEYYc807M0vnkpHQpcI5B', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('duHSg9zRpOxALxEQhZvns', 'yzVhDRl3xUdWYAUT5m2BF', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('DuPZlJB4qvhFW5rtv4jJJ', 'yzVhDRl3xUdWYAUT5m2BF', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('DVUzagpjvU6mTDmMA_oxq', '3H_-MIf1tjbm8APi1Jo-t', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Dx1HvQs7mP5wMlHmRrLUg', '208piMtF2oKRStaFsQVnJ', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('DycPGZyHR7duNSM0r-ghi', 'zNV5ky6hYR66DKilKezlC', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('dzxLk2zmV3AtAClg9D3KM', 'JCdl4pbqMBb6qU5NNyYCT', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('e2HI6Q666fIK_vUONtTbw', 'ejVmOUoBjOjjJTcftnNHs', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('E3SV5tYQEYQz8jtSVBk0V', '0-Oht9MtPUc7ZovEETqHd', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('e5C1laVtNz82dI1Disb30', 'IOiilwYpSk70qcFxZuGUi', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('E891lDCC0aM345hW_iMHr', 'Q-p9XNc1NAUIWZWzTLud0', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('EFwENJWZyhiSjYAN8aDV9', 'sV9lv-qjHL6WL_454dP5-', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('epYrSGAiSUnlOBSV_YsAu', 'w1o29cxyXqSxa5lQJYyG5', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ErchtkzBFhHpcoU911FJ-', 'L0C5kynyvMA9FY3BbDOEq', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('eVgG35u8vXA4n98EzUcgg', 'SPyhZd7Oa36zoPY5c2zsv', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('F-d1yQCcFYOjx1ohkzs7U', 'aq-1HDUxjltrEJ9RWA74p', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('F9a7JRrXRLBSp5w_xo_Vu', 'QK2dO-PyWHjBmtluxkW9D', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('FaLnoK6A63zRtg446TifX', '3usVr_mHUxDf5tvON2gTR', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('FBnWvWBEcxUnhpSQf9n98', 'Q-p9XNc1NAUIWZWzTLud0', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('fdD5Vi9Nffd_Hg6E9uZpq', 'mjFy_dgRSkF7IDZrItn9_', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('FJlseCkRtA_yUqmBDlQtk', '4jYsdykRnre2tkN8yev0w', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('FkdHQdpMoWEd7W0xxTjLq', 'YUS_TOqqo0FGFusSEAuaD', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('gcwfghsn2OixHGvLEeaSc', 'aq-1HDUxjltrEJ9RWA74p', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('GfoMyxbtx6hYX7Wq1nTPe', 'D8m9QeML_4fX0YTuVH_gC', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('GG6jNK4yAXnCTjyW0z5mr', 'QK2dO-PyWHjBmtluxkW9D', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('GgTv0Cu_Rus1vShu1-nsO', 'DXTZI8nupewEWcySjwhpF', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ghsvpcWoVNCDSGy6-Ekr-', 'r0ILyBOuYh7S-xY6r-1sa', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('gw2fQza21VlFMWWJBlo_3', 'aq-1HDUxjltrEJ9RWA74p', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Gwdi7xH5iJHXgiiI6hnV_', 'JCdl4pbqMBb6qU5NNyYCT', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('GWIEfvowtR200c2gJjiBo', 'SPyhZd7Oa36zoPY5c2zsv', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('h7BXHawCgBW45e40fPqAc', 'L0C5kynyvMA9FY3BbDOEq', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('hdnDdIoyVnCnE7IkLg98N', '4qMefrQNoyxVZpK9gjxVS', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('hgYWfwG77wffswSlodXa-', '8U4mJ9TxE4YGAhgUaCDSU', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('hhJPTEI-ly-0c_p_9c_Ha', 'TervwRyD05m6UIEmqWM0U', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('HM__PJk-1IZ0HniBvIXqZ', 'Pg_7HbY1AABSR_gYbOkUf', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('HPFe3IRH36OUq0_4JZhUX', 'bfYzZb1jgdiHF7k4zX6vl', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('HT1ed0v21eDFyskjuWhd0', 'g8_CcFFLbAVW9YunBXhhK', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('I3Q6t9s7QGsoOpAFDwA8_', 'CJqmYjuAe8CDNYRN9F4Dw', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('I4GZbd9mWikuVdu_2x0cc', 'TervwRyD05m6UIEmqWM0U', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('I598iDL0VvTu_ab7JUVmL', 'yzVhDRl3xUdWYAUT5m2BF', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('iM2xkI9uKVi15pknLEfaa', '31tNocQ5kIKV2uagc2XaZ', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('iQNC8z7er2W0XtpBkifrd', '4jYsdykRnre2tkN8yev0w', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('isltM9SGlARExvwjffnhI', 'D8m9QeML_4fX0YTuVH_gC', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('IY1UlP2YacT7w8AAdubfI', 'h0RL4P1SRAAFGJhLiRW2w', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('j7mkjRVZmyoXdrb5TI_eX', 'PpZnTfu8QnlLg1lVHA2--', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('jaC0jgnvx7hfFyTG3XdoB', 'Q_th4bdl_9ziaIjVjQbhm', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('JCR7ABcw6sGdp673ihQjm', '8XLun5G7CrvtgoqowLbdg', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('jPMx1qyGKPZV9U8uBEz-N', 'P4yRc_zlQIkiKpqhTHGm5', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('jzVlhzPi-hb4HnTGCvsSv', 'hmdWcQTNDy0ZSbhq35jU9', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('k6HadD6BvrErXyJCPtFo6', '8XLun5G7CrvtgoqowLbdg', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('KlI8SbUEoha2PsJz4NwoG', 'Pg_7HbY1AABSR_gYbOkUf', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('KP9OQuEihh16dCgQtnC6z', 'ivr9AaFBJo716F0MIgvLe', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('kvg8Ho8-Eh-nRYNl9ROSs', 'g8_CcFFLbAVW9YunBXhhK', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Linc9Zerf_4B-IC14W-3_', 'sV9lv-qjHL6WL_454dP5-', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('LKuq5y7rzXqPWVgfW9fye', '31tNocQ5kIKV2uagc2XaZ', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('lO4YlyMHcQRhsp7_lIDqs', 'T8lbsmO0hI85U2cCW_lAm', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Lz1ZNWrii6uvSbYbcvpLj', 'Q_th4bdl_9ziaIjVjQbhm', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('l_lvwTf-rSNyv-XoMq5X7', 'WYgDXkZcX82TNOnn2L0qJ', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('m8-prJI-Avz4ly0MZ65aj', '-AeheieDEh3tBIOyjrPeF', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('MaLy1z0WZHw-BMDIcxHLI', '0-Oht9MtPUc7ZovEETqHd', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Mr8FTlu451Rftagz5_nCm', 'ivr9AaFBJo716F0MIgvLe', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('MRjKJ4a7gYlBA8o3WR2l5', '4jYsdykRnre2tkN8yev0w', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('muY_iAgwILgN-Pmc6fYpj', '3H_-MIf1tjbm8APi1Jo-t', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('mVP8cNZ7XSlgOBkjnBRXx', 'BlPTGcj_hbK2fLqCgNfp8', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Mwj0XDWDukWyG4v11tLQ2', 'Pg_7HbY1AABSR_gYbOkUf', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('nan0LnUhvO0juSBCdo-7O', 'xvHti7i7kHx3t3t-dpYva', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('nccm6doOFfxSnpASgP-NL', 'Q_th4bdl_9ziaIjVjQbhm', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('nE8Ojk7oVpRAKw3O8HM3L', 'ckL_6-JI5IVq1beCKbZ7g', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('nke8R_rFOB5HqGmCTcVjM', 'XGyAQIXRirjARFbJR_3Q9', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('NQH_YSLwCBmh8QbqGoi0q', 'zNV5ky6hYR66DKilKezlC', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('nQlm8EtlDEwkex4cWW6Dj', 'r0ILyBOuYh7S-xY6r-1sa', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Ntrb6hcafDzwcjy0AGzzX', 'uEfmPXK4-TsLBmzGGrFzJ', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('nyn9FPdR-45V7dRmLUksH', 'aq-1HDUxjltrEJ9RWA74p', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('o53scnTEmqzavJnWuA7Qm', '208piMtF2oKRStaFsQVnJ', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ODKiNRQKz2hNyHbaz19aK', 'Yg1y8TNBa_80zhDWxx9DD', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('of_iaIUTlm2BHkrbZjOtJ', 'KrPSb4Yn6LLXMHcbXeLjO', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('oLC63K4ijIUl4REDndujA', 'xvHti7i7kHx3t3t-dpYva', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('oP8VOgaj8SLMtswgM34Q4', 'ejVmOUoBjOjjJTcftnNHs', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('oQ04onT07XCHRPJdT_4z8', 'WYgDXkZcX82TNOnn2L0qJ', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('oqFIakd8ExhN3J4cLJg97', 'nsJgvufBXo_hnhnvvJv5q', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('OR5QT20sGP2JPhcSKjxWE', 'xwXq_IdQ_6SxE-HCdyjRJ', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('OthyyugsC6EWT-pwavlEk', 'K11mSN416G4MxZ9anwQSD', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('OTku0mhXX-JhVyq8g18A6', '3H_-MIf1tjbm8APi1Jo-t', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('o_zuquNDX5gGM63NNUuSz', 'sqiWGwQGV_ht7snHR7Ppz', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Peit0lL6FJOJpC1GW-f9B', 'Q_th4bdl_9ziaIjVjQbhm', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('PJzf2meY1NZiGwuwKUYtE', '8XLun5G7CrvtgoqowLbdg', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('pKmRlT6-fdoUdkDKLtma9', 'Yg1y8TNBa_80zhDWxx9DD', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('PMA_crmJ3ssOXi9a54DPi', 'r0ILyBOuYh7S-xY6r-1sa', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('PMIVEBFqj1JGt4GOrajUy', 'BlPTGcj_hbK2fLqCgNfp8', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Pnjawl12Gvkzqw44LCJmf', 'bfYzZb1jgdiHF7k4zX6vl', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('poIEMiwUwi70nJX6_9kkI', 'bfYzZb1jgdiHF7k4zX6vl', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('pOSA4eTrBkXzHkymLAyum', '3H_-MIf1tjbm8APi1Jo-t', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('pOxzBHjXACsjbIIilU1rY', 'VdI_HHUEDwdhofSfuK8Ob', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('pp1bH8HsI3AqeVwNp8UH2', '4qMefrQNoyxVZpK9gjxVS', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('PuZGEpOw_o7x96210LaQ1', 'mjFy_dgRSkF7IDZrItn9_', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('PVOjRwaU_AJIEUWFcDghm', 'ovnRsyFxGNgJwjHnk95HD', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('qmtBdRanNceASdrkvsYwI', 'xwXq_IdQ_6SxE-HCdyjRJ', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('QO5jUmUE3VlAPK5YYMsk7', 'K11mSN416G4MxZ9anwQSD', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('QpJZrVSIcErxRrK-oJB_n', 'BlPTGcj_hbK2fLqCgNfp8', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('qRjUWPvK3B0gv_hc_VpZ_', 'sdxDOEE7RA8j0zl8As1Bf', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('qX0ofLA0zrdtcXWFhPmUs', 'VdI_HHUEDwdhofSfuK8Ob', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('qyl9Iz8jdEOBWy535z1ze', 'T8lbsmO0hI85U2cCW_lAm', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('R3ukVSgOYt9_Ct1i1tD7H', 'ovnRsyFxGNgJwjHnk95HD', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('r5me4vkHS4NMcRjPkKeto', 'XGyAQIXRirjARFbJR_3Q9', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('rC_YHs7OnjZ8NiOWS_T-Q', 'uEfmPXK4-TsLBmzGGrFzJ', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('reo4yuEbpyz2c4NmqZrNh', '-AeheieDEh3tBIOyjrPeF', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('RpefWPkUjsGgmKtdsq7zW', 'XGyAQIXRirjARFbJR_3Q9', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('rRHag0_0Ol4FlF8wz5lTJ', 'ejVmOUoBjOjjJTcftnNHs', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('R_TNdCGSQ92j0QWY1V405', '8U4mJ9TxE4YGAhgUaCDSU', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('s2VqPVzCVviCACQ8tx9dv', '31tNocQ5kIKV2uagc2XaZ', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('s2W1W1dwt4N801757M14K', 'ckL_6-JI5IVq1beCKbZ7g', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('T0xSgEyI8AaqHicPYyd97', 'zNV5ky6hYR66DKilKezlC', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('t1gB5_igztoOYxuFvhmFq', 'DXTZI8nupewEWcySjwhpF', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('TABIwDdvuATIunIBA4l6k', 'Q-p9XNc1NAUIWZWzTLud0', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ti_zhFfjE6piuTh7O78Iw', '3usVr_mHUxDf5tvON2gTR', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('TJXKmeBKnwIuGo7DGVVFE', 'WYgDXkZcX82TNOnn2L0qJ', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('tM-1mtqe7B0SjXqJnQUoC', 'YjkF-pv1idSgcWpKlKXp7', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ToBUS617j7aQLZ8j5xrmO', 'xwXq_IdQ_6SxE-HCdyjRJ', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Trhlu9JkfmH13nScbT0tr', 'T8lbsmO0hI85U2cCW_lAm', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('tsjqSFD_juIyu8_qeZ9m2', 'D8m9QeML_4fX0YTuVH_gC', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('tYPACz_chsHq4yZJ-txmv', 'nsJgvufBXo_hnhnvvJv5q', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('tyzqoTUKlFGt0u-GOqga2', 'IOiilwYpSk70qcFxZuGUi', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('u9XcTqS8W2kENluagLQWV', 'KrPSb4Yn6LLXMHcbXeLjO', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('uaUEAzdTH_aHzTc-KPw12', 'VdI_HHUEDwdhofSfuK8Ob', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('uKI9C8jtFpT3Sr1lXlYx5', '4qMefrQNoyxVZpK9gjxVS', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('UNRY5K82BGFNEiIO4d7Sx', '8XLun5G7CrvtgoqowLbdg', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('UOCRQszKZyBM0e-lNc5oD', 'QK2dO-PyWHjBmtluxkW9D', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('UsN4QhTs0H1jqU9MnPfzX', 'pGPvxvm2fPLBx3UW9RV5K', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('vbGjgUiScehtBbYhKAWJh', 'if9raPEbh_XdJkDnRBJUq', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('VBydvo1TI8zLq6w1oFH0e', 'xwXq_IdQ_6SxE-HCdyjRJ', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('vIFVAoju7y8q3L9C6l8qK', 'DD0gEekkRqv-cAbyMfEav', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('vQcAxVUFefS0S_7UX24WT', 'hmdWcQTNDy0ZSbhq35jU9', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('vxy25BDvHrK-s-9WCPKvG', 'WYgDXkZcX82TNOnn2L0qJ', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('wcyUKYIugHFNV_3WJ7IHL', 'SPyhZd7Oa36zoPY5c2zsv', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('wNK5oJ3CGsIyU3WNSZXDK', 'ovnRsyFxGNgJwjHnk95HD', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('WSkPyExWSPOF_Q3gOon4r', 'PJbD-Fb8b-Ea-poAHIpkH', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('WUfPs0t0G_kPl4gWDaFdc', 'KrPSb4Yn6LLXMHcbXeLjO', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('WXJ13FQAcLs0Eu0s0p2MT', 'sdxDOEE7RA8j0zl8As1Bf', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('w_aMa9QLa_c1ScJKdMdUe', 'sqiWGwQGV_ht7snHR7Ppz', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('w_f5oYcUM67qGIYc_aELH', 'XGyAQIXRirjARFbJR_3Q9', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('X4mHnFpWPME0llESZTI3O', 'sV9lv-qjHL6WL_454dP5-', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Xb4UqBO_lhOIW7MG7Nlea', 'CJqmYjuAe8CDNYRN9F4Dw', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('xc-1-EsAc4xIW06cM1Eqd', '31tNocQ5kIKV2uagc2XaZ', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('XhlcmAQfaopW0GjmwF2Hk', 'DD0gEekkRqv-cAbyMfEav', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('xhMLUzxzPOqB7hsOvL9VN', 'L0C5kynyvMA9FY3BbDOEq', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('XP1r7F1j2D8VI9UT2q_08', 'ovnRsyFxGNgJwjHnk95HD', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('XUy7NmML_1ggtrcADADRh', '3usVr_mHUxDf5tvON2gTR', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('y2Jzzdk5Gv3hn2aCzs0A7', 'w1o29cxyXqSxa5lQJYyG5', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('y37ODuAq6_ZEbmG0I7TX7', 'ivr9AaFBJo716F0MIgvLe', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('y7KIPtx9ngetAJrSSPVdt', '3MHHXoBInKalrnU5TN8ng', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('YFuEeKKE69yFYnGR2eRBu', 'w1o29cxyXqSxa5lQJYyG5', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ygMoBybCZ0Fb3y1Jne1R-', '0-Oht9MtPUc7ZovEETqHd', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('YlUiap9afIGW3Budhwiyu', 'xvHti7i7kHx3t3t-dpYva', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('yRH-SfRVw5eUuDH9SUhZH', 'w1o29cxyXqSxa5lQJYyG5', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('yRs5JGo4fHY58yoCxIT3D', 'KrPSb4Yn6LLXMHcbXeLjO', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('YupIrhg9ncuTMpvirvs_y', 'nsJgvufBXo_hnhnvvJv5q', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('Z9ylZnlVXPuYQ7vmEUHQ1', 'DXTZI8nupewEWcySjwhpF', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('ziSGC6_OYAEapHTecLfh5', 'pGPvxvm2fPLBx3UW9RV5K', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('_7BWMi-5zCN1-tMNZF8so', 'K11mSN416G4MxZ9anwQSD', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('_W2OXc-J3OUycSD_kYHek', 'yzVhDRl3xUdWYAUT5m2BF', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_request_path_permission_relation` (`id`, `url_id`, `permission_id`, `create_user`, `create_time`) VALUES ('_xn1PH1XnU3Kkas7XgRmO', 'T8lbsmO0hI85U2cCW_lAm', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for ai_role
-- ----------------------------
DROP TABLE IF EXISTS `ai_role`;
CREATE TABLE `ai_role` (
  `id` varchar(30) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_describe` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建用户\r\n',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of ai_role
-- ----------------------------
BEGIN;
INSERT INTO `ai_role` (`id`, `role_name`, `role_describe`, `create_user`, `create_time`) VALUES ('9sPf6jbdHCUXoTfitHu6r', '普通用户', '普通用户，拥有部分权限', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role` (`id`, `role_name`, `role_describe`, `create_user`, `create_time`) VALUES ('F866eyle3e3mQfp727Fog', '管理员', '管理员，拥有所有权限', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for ai_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ai_role_menu_relation`;
CREATE TABLE `ai_role_menu_relation` (
  `id` varchar(30) NOT NULL,
  `role_id` varchar(30) DEFAULT NULL,
  `menu_id` varchar(30) DEFAULT NULL,
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of ai_role_menu_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ai_role_permission_relation`;
CREATE TABLE `ai_role_permission_relation` (
  `id` varchar(30) NOT NULL DEFAULT '' COMMENT '主键id',
  `role_id` varchar(30) DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(30) DEFAULT NULL COMMENT '权限id',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='角色-权限关联关系表';

-- ----------------------------
-- Records of ai_role_permission_relation
-- ----------------------------
BEGIN;
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('-DaKsm6Lu9m9WGJBRE58u', 'PBhQRDIPCKD7eXIRww7Ws', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('2KMCNky-NKhSyMEyaswzG', 'F866eyle3e3mQfp727Fog', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('5b_tG_p6BdElNkLzUA6in', 'F866eyle3e3mQfp727Fog', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('8agN4MBGwuD8GD-6NB4S6', 'ID2jyoAXjMXp6jmsMyZJA', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('bnVgn8PhiW0F-Frnp-TJN', 'F866eyle3e3mQfp727Fog', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('cIW85PoBrbaENEzYxZIyr', 'PBhQRDIPCKD7eXIRww7Ws', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('EqqOLSZtA1eso-15Hzvk7', 'ID2jyoAXjMXp6jmsMyZJA', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('kSJ0TrkZqkhA_ar0E0eHx', 'PBhQRDIPCKD7eXIRww7Ws', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('OcCk_Nnk0ClHa5tNFc4Si', '9sPf6jbdHCUXoTfitHu6r', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('oqJWZoDgICn1pkIB-6JEu', '9sPf6jbdHCUXoTfitHu6r', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('oQTg4uk3IvQoRcmesvMB4', 'PBhQRDIPCKD7eXIRww7Ws', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('SuWgcjyiTEAtRONB9G9uB', '9sPf6jbdHCUXoTfitHu6r', 'ZO7sddSwa5F4WdevPd4eb', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('uDltVcABzfMitMcvS1VDA', '9sPf6jbdHCUXoTfitHu6r', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('up8zWpII2smP4PhFT8ZH6', 'ID2jyoAXjMXp6jmsMyZJA', 'EHHbJkd77qPfS3TKlBNeA', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('wXleSZdNI35MPwc76si5o', 'F866eyle3e3mQfp727Fog', 'RR0_F521-0h6t6fRm_von', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
INSERT INTO `ai_role_permission_relation` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`) VALUES ('XzwQVi0WVxvzf2g9roxiG', 'ID2jyoAXjMXp6jmsMyZJA', 'DrTpGpf3sfb96PENrEpLV', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-01 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for ai_task
-- ----------------------------
DROP TABLE IF EXISTS `ai_task`;
CREATE TABLE `ai_task` (
  `id` varchar(30) NOT NULL,
  `task_name` varchar(30) DEFAULT NULL COMMENT '任务名称',
  `task_mode` int DEFAULT NULL COMMENT '定时任务方式',
  `time_hour` int DEFAULT NULL COMMENT '小时',
  `time_minute` int DEFAULT NULL COMMENT '分钟',
  `time_second` int DEFAULT NULL COMMENT '秒',
  `cron_text` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `enabled` int DEFAULT NULL COMMENT '是否启动',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ai_task
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_task_journal
-- ----------------------------
DROP TABLE IF EXISTS `ai_task_journal`;
CREATE TABLE `ai_task_journal` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `task_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '任务ID\n',
  `task_mode` int DEFAULT NULL COMMENT '任务类型: 1:自动 2:手动',
  `task_batch` int DEFAULT NULL COMMENT '批次号',
  `task_status` int DEFAULT NULL COMMENT '执行状态',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_user` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ai_task_journal
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_user
-- ----------------------------
DROP TABLE IF EXISTS `ai_user`;
CREATE TABLE `ai_user` (
  `id` varchar(30) NOT NULL,
  `account` varchar(255) DEFAULT NULL COMMENT '账号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `iphone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `enabled` int DEFAULT NULL COMMENT '账号是否可用 默认为1(可用)',
  `account_not_locked` int DEFAULT NULL COMMENT '账号是否锁定(审核情况) 默认为1 不锁定\r\n',
  `corporate_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `purpose` varchar(255) DEFAULT NULL COMMENT '用途',
  `last_login_time` datetime DEFAULT NULL COMMENT '上一次登录时间',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of ai_user
-- ----------------------------
BEGIN;
INSERT INTO `ai_user` (`id`, `account`, `user_name`, `password`, `iphone`, `email`, `enabled`, `account_not_locked`, `corporate_name`, `purpose`, `last_login_time`, `create_user`, `create_time`) VALUES ('8oaW8aGSJ9jkghsg8ikcz', 'admin', '系统管理员', '$2a$10$U3jvULCAZ/iASiEMAZ4I7OrqwsmhuCvDU5UdRDL6uLmJV7HBhIJ6.', '15701333384', 'newsense@126.com', 1, 1, '流深科技', '知识图谱分析', '2022-11-08 10:19:53', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-09 00:49:43');
COMMIT;

-- ----------------------------
-- Table structure for ai_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ai_user_role_relation`;
CREATE TABLE `ai_user_role_relation` (
  `id` varchar(30) NOT NULL,
  `user_id` varchar(30) DEFAULT NULL,
  `role_id` varchar(30) DEFAULT NULL,
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='用户角色关联关系表';

-- ----------------------------
-- Records of ai_user_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `ai_user_role_relation` (`id`, `user_id`, `role_id`, `create_user`, `create_time`) VALUES ('UdkAYQmAmLUzdBKPh6PVZ', '8oaW8aGSJ9jkghsg8ikcz', 'F866eyle3e3mQfp727Fog', '8oaW8aGSJ9jkghsg8ikcz', '2022-08-09 14:34:52');
COMMIT;

-- ----------------------------
-- View structure for user_view
-- ----------------------------
DROP VIEW IF EXISTS `user_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `user_view` AS select `ai_user`.`id` AS `id`,`ai_user`.`account` AS `account`,`ai_user`.`user_name` AS `user_name` from `ai_user`;

SET FOREIGN_KEY_CHECKS = 1;
