/*
 Navicat Premium Data Transfer

 Source Server         : local-Mysql
 Source Server Type    : MySQL
 Source Server Version : 100420
 Source Host           : localhost:3306
 Source Schema         : vetproject

 Target Server Type    : MySQL
 Target Server Version : 100420
 File Encoding         : 65001

 Date: 07/10/2021 18:57:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `cu_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cid` int NULL DEFAULT NULL,
  `did` int NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`) USING BTREE,
  INDEX `FK2uftfxae973hl3iuthmvmkoxo`(`cid`) USING BTREE,
  INDEX `FK25r6hwihqbeejvuc6v3jdgilh`(`did`) USING BTREE,
  CONSTRAINT `FK25r6hwihqbeejvuc6v3jdgilh` FOREIGN KEY (`did`) REFERENCES `district` (`did`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK2uftfxae973hl3iuthmvmkoxo` FOREIGN KEY (`cid`) REFERENCES `city` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 'İSTANBUL', 7, 98);
INSERT INTO `address` VALUES (2, 'MANİSA', 7, 104);
INSERT INTO `address` VALUES (3, 'MARDİN', 47, 703);
INSERT INTO `address` VALUES (4, 'MARDİN', 47, 703);
INSERT INTO `address` VALUES (5, 'MARDİN', 47, 703);
INSERT INTO `address` VALUES (6, 'MARDİN', 47, 703);
INSERT INTO `address` VALUES (7, 'MARDİN', 47, 703);
INSERT INTO `address` VALUES (8, 'İZMİR', 6, 72);
INSERT INTO `address` VALUES (9, 'İZMİR', 6, 72);
INSERT INTO `address` VALUES (10, 'İZMİR', 4, 45);
INSERT INTO `address` VALUES (11, 'İZMİR', 4, 45);
INSERT INTO `address` VALUES (12, 'Ö', 10, 160);
INSERT INTO `address` VALUES (13, 'Ö', 10, 160);
INSERT INTO `address` VALUES (14, '1', 6, 71);
INSERT INTO `address` VALUES (15, '1', 6, 71);

-- ----------------------------
-- Table structure for breed_pet
-- ----------------------------
DROP TABLE IF EXISTS `breed_pet`;
CREATE TABLE `breed_pet`  (
  `br_id` int NOT NULL AUTO_INCREMENT,
  `br_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_pet_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`br_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of breed_pet
-- ----------------------------
INSERT INTO `breed_pet` VALUES (1, 'Aksolotl', 1);
INSERT INTO `breed_pet` VALUES (2, 'Iguana', 1);
INSERT INTO `breed_pet` VALUES (3, 'Kapuçin maymunu', 1);
INSERT INTO `breed_pet` VALUES (4, 'Timsah', 1);
INSERT INTO `breed_pet` VALUES (5, 'Kapibara', 1);
INSERT INTO `breed_pet` VALUES (6, 'Papağan', 2);
INSERT INTO `breed_pet` VALUES (7, 'Flamingo', 2);
INSERT INTO `breed_pet` VALUES (8, 'Serçe', 2);
INSERT INTO `breed_pet` VALUES (9, 'Kartal', 2);
INSERT INTO `breed_pet` VALUES (10, 'Karga', 2);
INSERT INTO `breed_pet` VALUES (11, 'Persian', 3);
INSERT INTO `breed_pet` VALUES (12, 'British Shorthair', 3);
INSERT INTO `breed_pet` VALUES (13, 'Devon Rex', 3);
INSERT INTO `breed_pet` VALUES (14, 'Abyssinian', 3);
INSERT INTO `breed_pet` VALUES (15, 'American Shorthair', 3);
INSERT INTO `breed_pet` VALUES (16, 'Labrador Retriever', 4);
INSERT INTO `breed_pet` VALUES (17, 'Shetland Sheepdog', 4);
INSERT INTO `breed_pet` VALUES (18, 'Golden Retriever', 4);
INSERT INTO `breed_pet` VALUES (19, 'Shih Tzu', 4);
INSERT INTO `breed_pet` VALUES (20, 'Siberian Husky', 4);
INSERT INTO `breed_pet` VALUES (21, 'Manda', 5);
INSERT INTO `breed_pet` VALUES (22, 'At', 5);
INSERT INTO `breed_pet` VALUES (23, 'Eşek', 5);
INSERT INTO `breed_pet` VALUES (24, 'Katır', 5);
INSERT INTO `breed_pet` VALUES (25, 'Sığır', 5);
INSERT INTO `breed_pet` VALUES (26, 'Koyun', 6);
INSERT INTO `breed_pet` VALUES (27, 'Keçi', 6);
INSERT INTO `breed_pet` VALUES (28, 'Ördek', 6);
INSERT INTO `breed_pet` VALUES (29, 'Tavuk', 6);
INSERT INTO `breed_pet` VALUES (30, 'Horoz', 6);
INSERT INTO `breed_pet` VALUES (31, 'Tavuskuşu', 7);
INSERT INTO `breed_pet` VALUES (32, 'Arizona Kertenkelesi', 7);
INSERT INTO `breed_pet` VALUES (33, 'Zebra', 7);
INSERT INTO `breed_pet` VALUES (34, 'Dev Yumuşak Kabuklu Kaplumbağa', 7);
INSERT INTO `breed_pet` VALUES (35, 'Zürafa', 7);
INSERT INTO `breed_pet` VALUES (36, 'Boz ayı', 8);
INSERT INTO `breed_pet` VALUES (37, 'Bozkurt', 8);
INSERT INTO `breed_pet` VALUES (38, 'Hatay Dağ Ceylanı', 8);
INSERT INTO `breed_pet` VALUES (39, 'Akdeniz Foku', 8);
INSERT INTO `breed_pet` VALUES (40, 'Anadolu dikenli faresi', 8);

-- ----------------------------
-- Table structure for calendar_info
-- ----------------------------
DROP TABLE IF EXISTS `calendar_info`;
CREATE TABLE `calendar_info`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cbg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cborder_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ccolor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cdrag_bg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of calendar_info
-- ----------------------------
INSERT INTO `calendar_info` VALUES (1, '#4287f5', '#4287f5', '#ffffff', '#9e5fff', 'Randevu');
INSERT INTO `calendar_info` VALUES (2, '#f5a742', '#f5a742', '#ffffff', '#9e5fff', 'Mübesil');
INSERT INTO `calendar_info` VALUES (3, '#6cf542', '#6cf542', '#ffffff', '#9e5fff', 'Hasta');
INSERT INTO `calendar_info` VALUES (4, '#f54242', '#f54242', '#ffffff', '#9e5fff', 'Takvim');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_detail` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `UK_aoocyteu6o3xrboa98gll9wra`(`category_title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Pamuk, makas vs.', 'Araç-Gereç');
INSERT INTO `category` VALUES (2, 'Yiyecek', 'Mama');
INSERT INTO `category` VALUES (3, 'Çiftlik Hayvanları için genel malzemeler', 'Çiftlik Ürünleri');
INSERT INTO `category` VALUES (4, 'Hayvanlar için hızlı test kitleri', 'Hızlı Test Kitleri');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, 'ADANA');
INSERT INTO `city` VALUES (2, 'ADIYAMAN');
INSERT INTO `city` VALUES (3, 'AFYON');
INSERT INTO `city` VALUES (4, 'AĞRI');
INSERT INTO `city` VALUES (5, 'AMASYA');
INSERT INTO `city` VALUES (6, 'ANKARA');
INSERT INTO `city` VALUES (7, 'ANTALYA');
INSERT INTO `city` VALUES (8, 'ARTVİN');
INSERT INTO `city` VALUES (9, 'AYDIN');
INSERT INTO `city` VALUES (10, 'BALIKESİR');
INSERT INTO `city` VALUES (11, 'BİLECİK');
INSERT INTO `city` VALUES (12, 'BİNGÖL');
INSERT INTO `city` VALUES (13, 'BİTLİS');
INSERT INTO `city` VALUES (14, 'BOLU');
INSERT INTO `city` VALUES (15, 'BURDUR');
INSERT INTO `city` VALUES (16, 'BURSA');
INSERT INTO `city` VALUES (17, 'ÇANAKKALE');
INSERT INTO `city` VALUES (18, 'ÇANKIRI');
INSERT INTO `city` VALUES (19, 'ÇORUM');
INSERT INTO `city` VALUES (20, 'DENİZLİ');
INSERT INTO `city` VALUES (21, 'DİYARBAKIR');
INSERT INTO `city` VALUES (22, 'EDİRNE');
INSERT INTO `city` VALUES (23, 'ELAZIĞ');
INSERT INTO `city` VALUES (24, 'ERZİNCAN');
INSERT INTO `city` VALUES (25, 'ERZURUM');
INSERT INTO `city` VALUES (26, 'ESKİŞEHİR');
INSERT INTO `city` VALUES (27, 'GAZİANTEP');
INSERT INTO `city` VALUES (28, 'GİRESUN');
INSERT INTO `city` VALUES (29, 'GÜMÜŞHANE');
INSERT INTO `city` VALUES (30, 'HAKKARİ');
INSERT INTO `city` VALUES (31, 'HATAY');
INSERT INTO `city` VALUES (32, 'ISPARTA');
INSERT INTO `city` VALUES (33, 'İÇEL');
INSERT INTO `city` VALUES (34, 'İSTANBUL');
INSERT INTO `city` VALUES (35, 'İZMİR');
INSERT INTO `city` VALUES (36, 'KARS');
INSERT INTO `city` VALUES (37, 'KASTAMONU');
INSERT INTO `city` VALUES (38, 'KAYSERİ');
INSERT INTO `city` VALUES (39, 'KIRKLARELİ');
INSERT INTO `city` VALUES (40, 'KIRŞEHİR');
INSERT INTO `city` VALUES (41, 'KOCAELİ');
INSERT INTO `city` VALUES (42, 'KONYA');
INSERT INTO `city` VALUES (43, 'KÜTAHYA');
INSERT INTO `city` VALUES (44, 'MALATYA');
INSERT INTO `city` VALUES (45, 'MANİSA');
INSERT INTO `city` VALUES (46, 'KAHRAMANMARAŞ');
INSERT INTO `city` VALUES (47, 'MARDİN');
INSERT INTO `city` VALUES (48, 'MUĞLA');
INSERT INTO `city` VALUES (49, 'MUŞ');
INSERT INTO `city` VALUES (50, 'NEVŞEHİR');
INSERT INTO `city` VALUES (51, 'NİĞDE');
INSERT INTO `city` VALUES (52, 'ORDU');
INSERT INTO `city` VALUES (53, 'RİZE');
INSERT INTO `city` VALUES (54, 'SAKARYA');
INSERT INTO `city` VALUES (55, 'SAMSUN');
INSERT INTO `city` VALUES (56, 'SİİRT');
INSERT INTO `city` VALUES (57, 'SİNOP');
INSERT INTO `city` VALUES (58, 'SİVAS');
INSERT INTO `city` VALUES (59, 'TEKİRDAĞ');
INSERT INTO `city` VALUES (60, 'TOKAT');
INSERT INTO `city` VALUES (61, 'TRABZON');
INSERT INTO `city` VALUES (62, 'TUNCELİ');
INSERT INTO `city` VALUES (63, 'ŞANLIURFA');
INSERT INTO `city` VALUES (64, 'UŞAK');
INSERT INTO `city` VALUES (65, 'VAN');
INSERT INTO `city` VALUES (66, 'YOZGAT');
INSERT INTO `city` VALUES (67, 'ZONGULDAK');
INSERT INTO `city` VALUES (68, 'AKSARAY');
INSERT INTO `city` VALUES (69, 'BAYBURT');
INSERT INTO `city` VALUES (70, 'KARAMAN');
INSERT INTO `city` VALUES (71, 'KIRIKKALE');
INSERT INTO `city` VALUES (72, 'BATMAN');
INSERT INTO `city` VALUES (73, 'ŞIRNAK');
INSERT INTO `city` VALUES (74, 'BARTIN');
INSERT INTO `city` VALUES (75, 'ARDAHAN');
INSERT INTO `city` VALUES (76, 'IĞDIR');
INSERT INTO `city` VALUES (77, 'YALOVA');
INSERT INTO `city` VALUES (78, 'KARABÜK');
INSERT INTO `city` VALUES (79, 'KİLİS');
INSERT INTO `city` VALUES (80, 'OSMANİYE');
INSERT INTO `city` VALUES (81, 'DÜZCE');

-- ----------------------------
-- Table structure for color_pet
-- ----------------------------
DROP TABLE IF EXISTS `color_pet`;
CREATE TABLE `color_pet`  (
  `color_id` int NOT NULL AUTO_INCREMENT,
  `color_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`color_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of color_pet
-- ----------------------------
INSERT INTO `color_pet` VALUES (1, 'Siyah');
INSERT INTO `color_pet` VALUES (2, 'Sarı');
INSERT INTO `color_pet` VALUES (3, 'Mavi');
INSERT INTO `color_pet` VALUES (4, 'Yeşil');
INSERT INTO `color_pet` VALUES (5, 'Beyaz');
INSERT INTO `color_pet` VALUES (6, 'Mor');
INSERT INTO `color_pet` VALUES (7, 'Eflatun');
INSERT INTO `color_pet` VALUES (8, 'Bordo');
INSERT INTO `color_pet` VALUES (9, 'Pembe');
INSERT INTO `color_pet` VALUES (10, 'Kahverengi');
INSERT INTO `color_pet` VALUES (11, 'Lacivert');
INSERT INTO `color_pet` VALUES (12, 'Gri');
INSERT INTO `color_pet` VALUES (13, 'Bej');
INSERT INTO `color_pet` VALUES (14, 'Turuncu');
INSERT INTO `color_pet` VALUES (15, 'Kızıl');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `cu_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `cu_mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_mail_notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_rate_of_discount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_sms_notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_surname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_taxname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_tcnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_tel1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_tel2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address_id` int NULL DEFAULT NULL,
  `cu_gr_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`cu_id`) USING BTREE,
  UNIQUE INDEX `UK_26xl63wwt2q0vhi57e1wbwskk`(`cu_tcnumber`) USING BTREE,
  UNIQUE INDEX `cu_mail`(`cu_mail`) USING BTREE,
  INDEX `FKglkhkmh2vyn790ijs6hiqqpi`(`address_id`) USING BTREE,
  INDEX `FKao2bcjqe9oc9044j8cdtg0vby`(`cu_gr_id`) USING BTREE,
  CONSTRAINT `FKao2bcjqe9oc9044j8cdtg0vby` FOREIGN KEY (`cu_gr_id`) REFERENCES `customer_group` (`cu_gr_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKglkhkmh2vyn790ijs6hiqqpi` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'mert@mail.com', '2021-09-23 23:17:56', 'mert@mail.com', '2021-09-23 23:17:56', 'hakanhoca@mail.com', 'on', 'HAKAN', 'HAKAN HOCA', '99', 'on', 'ÖZER', 'İSTANBUL', '100000000', '5555555555', NULL, 1, 3);
INSERT INTO `customer` VALUES (2, 'mert@mail.com', '2021-09-23 23:47:43', 'mert@mail.com', '2021-09-23 23:47:43', 'ali@mail.com', 'on', 'ALİ', 'TIMARHANE', '15', 'on', 'BİLMEM', 'MANİSA', '1234567890', '4444444444', NULL, 2, 2);
INSERT INTO `customer` VALUES (3, 'selen@mail.com', '2021-09-24 10:58:06', 'selen@mail.com', '2021-09-24 10:58:06', 'mert@mail.com', 'on', 'MERT', '', '10', 'on', 'DUMANLI', 'İZMİR', '123456789', '5050103555', NULL, 3, 3);
INSERT INTO `customer` VALUES (7, 'selen@mail.com', '2021-09-24 10:59:39', 'selen@mail.com', '2021-09-24 10:59:39', 'mert12@mail.com', 'on', 'MERT', '', '10', 'on', 'DUMANLI', 'İZMİR', '11111111111', '2325445555', NULL, 7, 3);
INSERT INTO `customer` VALUES (15, 'selen@mail.com', '2021-09-24 18:08:59', 'selen@mail.com', '2021-09-24 18:08:59', 'mert1@mail.com', 'on', 'M', '.', '1', 'on', 'D', 'Ö', '1000000001', '5555554444', NULL, 15, 2);

-- ----------------------------
-- Table structure for customer_group
-- ----------------------------
DROP TABLE IF EXISTS `customer_group`;
CREATE TABLE `customer_group`  (
  `cu_gr_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `cu_gr_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`cu_gr_id`) USING BTREE,
  UNIQUE INDEX `UK_kx0032jqt5x4t8qicvwcsg89r`(`cu_gr_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_group
-- ----------------------------
INSERT INTO `customer_group` VALUES (2, 'mert@mail.com', '2021-09-23 23:11:48', 'mert@mail.com', '2021-09-23 23:11:48', 'Beşiktaşlılar');
INSERT INTO `customer_group` VALUES (3, 'mert@mail.com', '2021-09-23 23:11:53', 'mert@mail.com', '2021-09-23 23:11:53', 'Fenerliler');

-- ----------------------------
-- Table structure for diary
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary`  (
  `diary_id` int NOT NULL AUTO_INCREMENT,
  `diary_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diary_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diary_statu` bit(1) NULL DEFAULT NULL,
  `diary_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diary_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_id` int NULL DEFAULT NULL,
  `us_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`diary_id`) USING BTREE,
  INDEX `FK19iwlec3gc5sqmk0a7vx4w5t4`(`cu_id`) USING BTREE,
  INDEX `FKnhyqnftb1dpf5qrq43m5mog0d`(`us_id`) USING BTREE,
  CONSTRAINT `FK19iwlec3gc5sqmk0a7vx4w5t4` FOREIGN KEY (`cu_id`) REFERENCES `customer` (`cu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKnhyqnftb1dpf5qrq43m5mog0d` FOREIGN KEY (`us_id`) REFERENCES `user` (`us_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diary
-- ----------------------------
INSERT INTO `diary` VALUES (1, '1998-01-23', 'Kutlu Doğum Haftası', b'1', '09:05', 'Selen\'in Doğum Günü', 1, 3);
INSERT INTO `diary` VALUES (2, '1998-01-12', 'O kara gün.', b'1', '05:00', 'Mert\'in Doğum Günü', 1, 2);
INSERT INTO `diary` VALUES (3, '2028-06-14', 'O mutlu gün.', b'1', '03:46', 'Aydın\'ın Doğum Günü', 1, 1);
INSERT INTO `diary` VALUES (4, '2021-09-24', 'Acil', b'1', '10:03', 'Toplantı', 2, 2);
INSERT INTO `diary` VALUES (5, '2021-09-23', 'Spring Boot', b'1', '23:39', 'KURS', 2, 1);
INSERT INTO `diary` VALUES (6, '2021-09-23', 'FB', b'1', '21:55', 'Maç', 1, 2);
INSERT INTO `diary` VALUES (7, '2021-09-23', 'DG', b'1', '22:45', 'Parti', 1, 1);
INSERT INTO `diary` VALUES (8, '2021-09-25', 'Görüşme yapılacak.', b'1', '13:22', 'Randevu', 1, 1);
INSERT INTO `diary` VALUES (9, '2021-09-24', 'A', b'1', '14:24', 'Randevu', 3, 1);
INSERT INTO `diary` VALUES (10, '2021-09-24', 'A', b'1', '10:21', 'A', 3, 1);
INSERT INTO `diary` VALUES (11, '2021-09-24', 'B', b'1', '11:17', 'B', 1, 1);
INSERT INTO `diary` VALUES (12, '2021-09-25', 'b', b'1', '18:11', 'a', 3, 3);
INSERT INTO `diary` VALUES (13, '2021-09-24', 'a', b'1', '18:14', 'a', 7, 3);
INSERT INTO `diary` VALUES (14, '2021-09-16', 'asd', b'1', '23:20', 'sad', 7, 4);
INSERT INTO `diary` VALUES (15, '2021-09-15', 'a', b'1', '21:19', 'a', 7, 1);
INSERT INTO `diary` VALUES (16, '2021-09-08', 'c', b'1', '21:19', 'a', 7, 3);
INSERT INTO `diary` VALUES (17, '2021-09-01', 'a', b'1', '20:22', 'a', 15, 4);
INSERT INTO `diary` VALUES (18, '2021-09-01', 'd', b'1', '23:30', 'm', 15, 4);
INSERT INTO `diary` VALUES (19, '2021-09-10', 'deneme', b'1', '20:36', 'Deneme', 7, 1);
INSERT INTO `diary` VALUES (20, '2021-09-25', 'B', b'1', '19:44', 'A', 3, 1);
INSERT INTO `diary` VALUES (21, '2021-08-31', 'c', b'1', '19:52', 'a', 2, 1);
INSERT INTO `diary` VALUES (22, '2021-09-23', 'c', b'1', '20:00', 'a', 7, 1);
INSERT INTO `diary` VALUES (23, '2021-09-26', 'b', b'1', '16:00', 'a', 2, 4);
INSERT INTO `diary` VALUES (25, '2021-09-27', 'Ali Bilmem gelecek.', b'1', '16:00', 'Hakan hocaya gelecekler.', 2, 4);

-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district`  (
  `did` int NOT NULL AUTO_INCREMENT,
  `cid` int NULL DEFAULT NULL,
  `dname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 974 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of district
-- ----------------------------
INSERT INTO `district` VALUES (1, 1, 'ALADAĞ');
INSERT INTO `district` VALUES (2, 1, 'CEYHAN');
INSERT INTO `district` VALUES (3, 1, 'ÇUKUROVA');
INSERT INTO `district` VALUES (4, 1, 'FEKE');
INSERT INTO `district` VALUES (5, 1, 'İMAMOĞLU');
INSERT INTO `district` VALUES (6, 1, 'KARAİSALI');
INSERT INTO `district` VALUES (7, 1, 'KARATAŞ');
INSERT INTO `district` VALUES (8, 1, 'KOZAN');
INSERT INTO `district` VALUES (9, 1, 'POZANTI');
INSERT INTO `district` VALUES (10, 1, 'SAİMBEYLİ');
INSERT INTO `district` VALUES (11, 1, 'SARIÇAM');
INSERT INTO `district` VALUES (12, 1, 'SEYHAN');
INSERT INTO `district` VALUES (13, 1, 'TUFANBEYLİ');
INSERT INTO `district` VALUES (14, 1, 'YUMURTALIK');
INSERT INTO `district` VALUES (15, 1, 'YÜREĞİR');
INSERT INTO `district` VALUES (16, 2, 'BESNİ');
INSERT INTO `district` VALUES (17, 2, 'ÇELİKHAN');
INSERT INTO `district` VALUES (18, 2, 'GERGER');
INSERT INTO `district` VALUES (19, 2, 'GÖLBAŞI');
INSERT INTO `district` VALUES (20, 2, 'KAHTA');
INSERT INTO `district` VALUES (21, 2, 'MERKEZ');
INSERT INTO `district` VALUES (22, 2, 'SAMSAT');
INSERT INTO `district` VALUES (23, 2, 'SİNCİK');
INSERT INTO `district` VALUES (24, 2, 'TUT');
INSERT INTO `district` VALUES (25, 3, 'BAŞMAKÇI');
INSERT INTO `district` VALUES (26, 3, 'BAYAT');
INSERT INTO `district` VALUES (27, 3, 'BOLVADİN');
INSERT INTO `district` VALUES (28, 3, 'ÇAY');
INSERT INTO `district` VALUES (29, 3, 'ÇOBANLAR');
INSERT INTO `district` VALUES (30, 3, 'DAZKIRI');
INSERT INTO `district` VALUES (31, 3, 'DİNAR');
INSERT INTO `district` VALUES (32, 3, 'EMİRDAĞ');
INSERT INTO `district` VALUES (33, 3, 'EVCİLER');
INSERT INTO `district` VALUES (34, 3, 'HOCALAR');
INSERT INTO `district` VALUES (35, 3, 'İHSANİYE');
INSERT INTO `district` VALUES (36, 3, 'İSCEHİSAR');
INSERT INTO `district` VALUES (37, 3, 'KIZILÖREN');
INSERT INTO `district` VALUES (38, 3, 'MERKEZ');
INSERT INTO `district` VALUES (39, 3, 'SANDIKLI');
INSERT INTO `district` VALUES (40, 3, 'SİNANPAŞA');
INSERT INTO `district` VALUES (41, 3, 'SULTANDAĞI');
INSERT INTO `district` VALUES (42, 3, 'ŞUHUT');
INSERT INTO `district` VALUES (43, 4, 'DİYADİN');
INSERT INTO `district` VALUES (44, 4, 'DOĞUBAYAZIT');
INSERT INTO `district` VALUES (45, 4, 'ELEŞKİRT');
INSERT INTO `district` VALUES (46, 4, 'HAMUR');
INSERT INTO `district` VALUES (47, 4, 'MERKEZ');
INSERT INTO `district` VALUES (48, 4, 'PATNOS');
INSERT INTO `district` VALUES (49, 4, 'TAŞLIÇAY');
INSERT INTO `district` VALUES (50, 4, 'TUTAK');
INSERT INTO `district` VALUES (51, 68, 'AĞAÇÖREN');
INSERT INTO `district` VALUES (52, 68, 'ESKİL');
INSERT INTO `district` VALUES (53, 68, 'GÜLAĞAÇ');
INSERT INTO `district` VALUES (54, 68, 'GÜZELYURT');
INSERT INTO `district` VALUES (55, 68, 'MERKEZ');
INSERT INTO `district` VALUES (56, 68, 'ORTAKÖY');
INSERT INTO `district` VALUES (57, 68, 'SARIYAHŞİ');
INSERT INTO `district` VALUES (58, 68, 'SULTANHANI');
INSERT INTO `district` VALUES (59, 5, 'GÖYNÜCEK');
INSERT INTO `district` VALUES (60, 5, 'GÜMÜŞHACIKÖY');
INSERT INTO `district` VALUES (61, 5, 'HAMAMÖZÜ');
INSERT INTO `district` VALUES (62, 5, 'MERKEZ');
INSERT INTO `district` VALUES (63, 5, 'MERZİFON');
INSERT INTO `district` VALUES (64, 5, 'SULUOVA');
INSERT INTO `district` VALUES (65, 5, 'TAŞOVA');
INSERT INTO `district` VALUES (66, 6, 'AKYURT');
INSERT INTO `district` VALUES (67, 6, 'ALTINDAĞ');
INSERT INTO `district` VALUES (68, 6, 'AYAŞ');
INSERT INTO `district` VALUES (69, 6, 'BALA');
INSERT INTO `district` VALUES (70, 6, 'BEYPAZARI');
INSERT INTO `district` VALUES (71, 6, 'ÇAMLIDERE');
INSERT INTO `district` VALUES (72, 6, 'ÇANKAYA');
INSERT INTO `district` VALUES (73, 6, 'ÇUBUK');
INSERT INTO `district` VALUES (74, 6, 'ELMADAĞ');
INSERT INTO `district` VALUES (75, 6, 'ETİMESGUT');
INSERT INTO `district` VALUES (76, 6, 'EVREN');
INSERT INTO `district` VALUES (77, 6, 'GÖLBAŞI');
INSERT INTO `district` VALUES (78, 6, 'GÜDÜL');
INSERT INTO `district` VALUES (79, 6, 'HAYMANA');
INSERT INTO `district` VALUES (80, 6, 'KAHRAMANKAZAN');
INSERT INTO `district` VALUES (81, 6, 'KALECİK');
INSERT INTO `district` VALUES (82, 6, 'KEÇİÖREN');
INSERT INTO `district` VALUES (83, 6, 'KIZILCAHAMAM');
INSERT INTO `district` VALUES (84, 6, 'MAMAK');
INSERT INTO `district` VALUES (85, 6, 'NALLIHAN');
INSERT INTO `district` VALUES (86, 6, 'POLATLI');
INSERT INTO `district` VALUES (87, 6, 'PURSAKLAR');
INSERT INTO `district` VALUES (88, 6, 'SİNCAN');
INSERT INTO `district` VALUES (89, 6, 'ŞEREFLİKOÇHİSAR');
INSERT INTO `district` VALUES (90, 6, 'YENİMAHALLE');
INSERT INTO `district` VALUES (91, 7, 'AKSEKİ');
INSERT INTO `district` VALUES (92, 7, 'AKSU');
INSERT INTO `district` VALUES (93, 7, 'ALANYA');
INSERT INTO `district` VALUES (94, 7, 'DEMRE');
INSERT INTO `district` VALUES (95, 7, 'DÖŞEMEALTI');
INSERT INTO `district` VALUES (96, 7, 'ELMALI');
INSERT INTO `district` VALUES (97, 7, 'FİNİKE');
INSERT INTO `district` VALUES (98, 7, 'GAZİPAŞA');
INSERT INTO `district` VALUES (99, 7, 'GÜNDOĞMUŞ');
INSERT INTO `district` VALUES (100, 7, 'İBRADI');
INSERT INTO `district` VALUES (101, 7, 'KAŞ');
INSERT INTO `district` VALUES (102, 7, 'KEMER');
INSERT INTO `district` VALUES (103, 7, 'KEPEZ');
INSERT INTO `district` VALUES (104, 7, 'KONYAALTI');
INSERT INTO `district` VALUES (105, 7, 'KORKUTELİ');
INSERT INTO `district` VALUES (106, 7, 'KUMLUCA');
INSERT INTO `district` VALUES (107, 7, 'MANAVGAT');
INSERT INTO `district` VALUES (108, 7, 'MURATPAŞA');
INSERT INTO `district` VALUES (109, 7, 'SERİK');
INSERT INTO `district` VALUES (110, 75, 'ÇILDIR');
INSERT INTO `district` VALUES (111, 75, 'DAMAL');
INSERT INTO `district` VALUES (112, 75, 'GÖLE');
INSERT INTO `district` VALUES (113, 75, 'HANAK');
INSERT INTO `district` VALUES (114, 75, 'MERKEZ');
INSERT INTO `district` VALUES (115, 75, 'POSOF');
INSERT INTO `district` VALUES (116, 8, 'ARDANUÇ');
INSERT INTO `district` VALUES (117, 8, 'ARHAVİ');
INSERT INTO `district` VALUES (118, 8, 'BORÇKA');
INSERT INTO `district` VALUES (119, 8, 'HOPA');
INSERT INTO `district` VALUES (120, 8, 'KEMALPAŞA');
INSERT INTO `district` VALUES (121, 8, 'MERKEZ');
INSERT INTO `district` VALUES (122, 8, 'MURGUL');
INSERT INTO `district` VALUES (123, 8, 'ŞAVŞAT');
INSERT INTO `district` VALUES (124, 8, 'YUSUFELİ');
INSERT INTO `district` VALUES (125, 9, 'BOZDOĞAN');
INSERT INTO `district` VALUES (126, 9, 'BUHARKENT');
INSERT INTO `district` VALUES (127, 9, 'ÇİNE');
INSERT INTO `district` VALUES (128, 9, 'DİDİM');
INSERT INTO `district` VALUES (129, 9, 'EFELER');
INSERT INTO `district` VALUES (130, 9, 'GERMENCİK');
INSERT INTO `district` VALUES (131, 9, 'İNCİRLİOVA');
INSERT INTO `district` VALUES (132, 9, 'KARACASU');
INSERT INTO `district` VALUES (133, 9, 'KARPUZLU');
INSERT INTO `district` VALUES (134, 9, 'KOÇARLI');
INSERT INTO `district` VALUES (135, 9, 'KÖŞK');
INSERT INTO `district` VALUES (136, 9, 'KUŞADASI');
INSERT INTO `district` VALUES (137, 9, 'KUYUCAK');
INSERT INTO `district` VALUES (138, 9, 'NAZİLLİ');
INSERT INTO `district` VALUES (139, 9, 'SÖKE');
INSERT INTO `district` VALUES (140, 9, 'SULTANHİSAR');
INSERT INTO `district` VALUES (141, 9, 'YENİPAZAR');
INSERT INTO `district` VALUES (142, 10, 'ALTIEYLÜL');
INSERT INTO `district` VALUES (143, 10, 'AYVALIK');
INSERT INTO `district` VALUES (144, 10, 'BALYA');
INSERT INTO `district` VALUES (145, 10, 'BANDIRMA');
INSERT INTO `district` VALUES (146, 10, 'BİGADİÇ');
INSERT INTO `district` VALUES (147, 10, 'BURHANİYE');
INSERT INTO `district` VALUES (148, 10, 'DURSUNBEY');
INSERT INTO `district` VALUES (149, 10, 'EDREMİT');
INSERT INTO `district` VALUES (150, 10, 'ERDEK');
INSERT INTO `district` VALUES (151, 10, 'GÖMEÇ');
INSERT INTO `district` VALUES (152, 10, 'GÖNEN');
INSERT INTO `district` VALUES (153, 10, 'HAVRAN');
INSERT INTO `district` VALUES (154, 10, 'İVRİNDİ');
INSERT INTO `district` VALUES (155, 10, 'KARESİ');
INSERT INTO `district` VALUES (156, 10, 'KEPSUT');
INSERT INTO `district` VALUES (157, 10, 'MANYAS');
INSERT INTO `district` VALUES (158, 10, 'MARMARA');
INSERT INTO `district` VALUES (159, 10, 'SAVAŞTEPE');
INSERT INTO `district` VALUES (160, 10, 'SINDIRGI');
INSERT INTO `district` VALUES (161, 10, 'SUSURLUK');
INSERT INTO `district` VALUES (162, 74, 'AMASRA');
INSERT INTO `district` VALUES (163, 74, 'KURUCAŞİLE');
INSERT INTO `district` VALUES (164, 74, 'MERKEZ');
INSERT INTO `district` VALUES (165, 74, 'ULUS');
INSERT INTO `district` VALUES (166, 72, 'BEŞİRİ');
INSERT INTO `district` VALUES (167, 72, 'GERCÜŞ');
INSERT INTO `district` VALUES (168, 72, 'HASANKEYF');
INSERT INTO `district` VALUES (169, 72, 'KOZLUK');
INSERT INTO `district` VALUES (170, 72, 'MERKEZ');
INSERT INTO `district` VALUES (171, 72, 'SASON');
INSERT INTO `district` VALUES (172, 69, 'AYDINTEPE');
INSERT INTO `district` VALUES (173, 69, 'DEMİRÖZÜ');
INSERT INTO `district` VALUES (174, 69, 'MERKEZ');
INSERT INTO `district` VALUES (175, 11, 'BOZÜYÜK');
INSERT INTO `district` VALUES (176, 11, 'GÖLPAZARI');
INSERT INTO `district` VALUES (177, 11, 'İNHİSAR');
INSERT INTO `district` VALUES (178, 11, 'MERKEZ');
INSERT INTO `district` VALUES (179, 11, 'OSMANELİ');
INSERT INTO `district` VALUES (180, 11, 'PAZARYERİ');
INSERT INTO `district` VALUES (181, 11, 'SÖĞÜT');
INSERT INTO `district` VALUES (182, 11, 'YENİPAZAR');
INSERT INTO `district` VALUES (183, 12, 'ADAKLI');
INSERT INTO `district` VALUES (184, 12, 'GENÇ');
INSERT INTO `district` VALUES (185, 12, 'KARLIOVA');
INSERT INTO `district` VALUES (186, 12, 'KİĞI');
INSERT INTO `district` VALUES (187, 12, 'MERKEZ');
INSERT INTO `district` VALUES (188, 12, 'SOLHAN');
INSERT INTO `district` VALUES (189, 12, 'YAYLADERE');
INSERT INTO `district` VALUES (190, 12, 'YEDİSU');
INSERT INTO `district` VALUES (191, 13, 'ADİLCEVAZ');
INSERT INTO `district` VALUES (192, 13, 'AHLAT');
INSERT INTO `district` VALUES (193, 13, 'GÜROYMAK');
INSERT INTO `district` VALUES (194, 13, 'HİZAN');
INSERT INTO `district` VALUES (195, 13, 'MERKEZ');
INSERT INTO `district` VALUES (196, 13, 'MUTKİ');
INSERT INTO `district` VALUES (197, 13, 'TATVAN');
INSERT INTO `district` VALUES (198, 14, 'DÖRTDİVAN');
INSERT INTO `district` VALUES (199, 14, 'GEREDE');
INSERT INTO `district` VALUES (200, 14, 'GÖYNÜK');
INSERT INTO `district` VALUES (201, 14, 'KIBRISCIK');
INSERT INTO `district` VALUES (202, 14, 'MENGEN');
INSERT INTO `district` VALUES (203, 14, 'MERKEZ');
INSERT INTO `district` VALUES (204, 14, 'MUDURNU');
INSERT INTO `district` VALUES (205, 14, 'SEBEN');
INSERT INTO `district` VALUES (206, 14, 'YENİÇAĞA');
INSERT INTO `district` VALUES (207, 15, 'AĞLASUN');
INSERT INTO `district` VALUES (208, 15, 'ALTINYAYLA');
INSERT INTO `district` VALUES (209, 15, 'BUCAK');
INSERT INTO `district` VALUES (210, 15, 'ÇAVDIR');
INSERT INTO `district` VALUES (211, 15, 'ÇELTİKÇİ');
INSERT INTO `district` VALUES (212, 15, 'GÖLHİSAR');
INSERT INTO `district` VALUES (213, 15, 'KARAMANLI');
INSERT INTO `district` VALUES (214, 15, 'KEMER');
INSERT INTO `district` VALUES (215, 15, 'MERKEZ');
INSERT INTO `district` VALUES (216, 15, 'TEFENNİ');
INSERT INTO `district` VALUES (217, 15, 'YEŞİLOVA');
INSERT INTO `district` VALUES (218, 16, 'BÜYÜKORHAN');
INSERT INTO `district` VALUES (219, 16, 'GEMLİK');
INSERT INTO `district` VALUES (220, 16, 'GÜRSU');
INSERT INTO `district` VALUES (221, 16, 'HARMANCIK');
INSERT INTO `district` VALUES (222, 16, 'İNEGÖL');
INSERT INTO `district` VALUES (223, 16, 'İZNİK');
INSERT INTO `district` VALUES (224, 16, 'KARACABEY');
INSERT INTO `district` VALUES (225, 16, 'KELES');
INSERT INTO `district` VALUES (226, 16, 'KESTEL');
INSERT INTO `district` VALUES (227, 16, 'MUDANYA');
INSERT INTO `district` VALUES (228, 16, 'MUSTAFAKEMALPAŞA');
INSERT INTO `district` VALUES (229, 16, 'NİLÜFER');
INSERT INTO `district` VALUES (230, 16, 'ORHANELİ');
INSERT INTO `district` VALUES (231, 16, 'ORHANGAZİ');
INSERT INTO `district` VALUES (232, 16, 'OSMANGAZİ');
INSERT INTO `district` VALUES (233, 16, 'YENİŞEHİR');
INSERT INTO `district` VALUES (234, 16, 'YILDIRIM');
INSERT INTO `district` VALUES (235, 17, 'AYVACIK');
INSERT INTO `district` VALUES (236, 17, 'BAYRAMİÇ');
INSERT INTO `district` VALUES (237, 17, 'BİGA');
INSERT INTO `district` VALUES (238, 17, 'BOZCAADA');
INSERT INTO `district` VALUES (239, 17, 'ÇAN');
INSERT INTO `district` VALUES (240, 17, 'ECEABAT');
INSERT INTO `district` VALUES (241, 17, 'EZİNE');
INSERT INTO `district` VALUES (242, 17, 'GELİBOLU');
INSERT INTO `district` VALUES (243, 17, 'GÖKÇEADA');
INSERT INTO `district` VALUES (244, 17, 'LAPSEKİ');
INSERT INTO `district` VALUES (245, 17, 'MERKEZ');
INSERT INTO `district` VALUES (246, 17, 'YENİCE');
INSERT INTO `district` VALUES (247, 18, 'ATKARACALAR');
INSERT INTO `district` VALUES (248, 18, 'BAYRAMÖREN');
INSERT INTO `district` VALUES (249, 18, 'ÇERKEŞ');
INSERT INTO `district` VALUES (250, 18, 'ELDİVAN');
INSERT INTO `district` VALUES (251, 18, 'ILGAZ');
INSERT INTO `district` VALUES (252, 18, 'KIZILIRMAK');
INSERT INTO `district` VALUES (253, 18, 'KORGUN');
INSERT INTO `district` VALUES (254, 18, 'KURŞUNLU');
INSERT INTO `district` VALUES (255, 18, 'MERKEZ');
INSERT INTO `district` VALUES (256, 18, 'ORTA');
INSERT INTO `district` VALUES (257, 18, 'ŞABANÖZÜ');
INSERT INTO `district` VALUES (258, 18, 'YAPRAKLI');
INSERT INTO `district` VALUES (259, 19, 'ALACA');
INSERT INTO `district` VALUES (260, 19, 'BAYAT');
INSERT INTO `district` VALUES (261, 19, 'BOĞAZKALE');
INSERT INTO `district` VALUES (262, 19, 'DODURGA');
INSERT INTO `district` VALUES (263, 19, 'İSKİLİP');
INSERT INTO `district` VALUES (264, 19, 'KARGI');
INSERT INTO `district` VALUES (265, 19, 'LAÇİN');
INSERT INTO `district` VALUES (266, 19, 'MECİTÖZÜ');
INSERT INTO `district` VALUES (267, 19, 'MERKEZ');
INSERT INTO `district` VALUES (268, 19, 'OĞUZLAR');
INSERT INTO `district` VALUES (269, 19, 'ORTAKÖY');
INSERT INTO `district` VALUES (270, 19, 'OSMANCIK');
INSERT INTO `district` VALUES (271, 19, 'SUNGURLU');
INSERT INTO `district` VALUES (272, 19, 'UĞURLUDAĞ');
INSERT INTO `district` VALUES (273, 20, 'ACIPAYAM');
INSERT INTO `district` VALUES (274, 20, 'BABADAĞ');
INSERT INTO `district` VALUES (275, 20, 'BAKLAN');
INSERT INTO `district` VALUES (276, 20, 'BEKİLLİ');
INSERT INTO `district` VALUES (277, 20, 'BEYAĞAÇ');
INSERT INTO `district` VALUES (278, 20, 'BOZKURT');
INSERT INTO `district` VALUES (279, 20, 'BULDAN');
INSERT INTO `district` VALUES (280, 20, 'ÇAL');
INSERT INTO `district` VALUES (281, 20, 'ÇAMELİ');
INSERT INTO `district` VALUES (282, 20, 'ÇARDAK');
INSERT INTO `district` VALUES (283, 20, 'ÇİVRİL');
INSERT INTO `district` VALUES (284, 20, 'GÜNEY');
INSERT INTO `district` VALUES (285, 20, 'HONAZ');
INSERT INTO `district` VALUES (286, 20, 'KALE');
INSERT INTO `district` VALUES (287, 20, 'MERKEZEFENDİ');
INSERT INTO `district` VALUES (288, 20, 'PAMUKKALE');
INSERT INTO `district` VALUES (289, 20, 'SARAYKÖY');
INSERT INTO `district` VALUES (290, 20, 'SERİNHİSAR');
INSERT INTO `district` VALUES (291, 20, 'TAVAS');
INSERT INTO `district` VALUES (292, 21, 'BAĞLAR');
INSERT INTO `district` VALUES (293, 21, 'BİSMİL');
INSERT INTO `district` VALUES (294, 21, 'ÇERMİK');
INSERT INTO `district` VALUES (295, 21, 'ÇINAR');
INSERT INTO `district` VALUES (296, 21, 'ÇÜNGÜŞ');
INSERT INTO `district` VALUES (297, 21, 'DİCLE');
INSERT INTO `district` VALUES (298, 21, 'EĞİL');
INSERT INTO `district` VALUES (299, 21, 'ERGANİ');
INSERT INTO `district` VALUES (300, 21, 'HANİ');
INSERT INTO `district` VALUES (301, 21, 'HAZRO');
INSERT INTO `district` VALUES (302, 21, 'KAYAPINAR');
INSERT INTO `district` VALUES (303, 21, 'KOCAKÖY');
INSERT INTO `district` VALUES (304, 21, 'KULP');
INSERT INTO `district` VALUES (305, 21, 'LİCE');
INSERT INTO `district` VALUES (306, 21, 'SİLVAN');
INSERT INTO `district` VALUES (307, 21, 'SUR');
INSERT INTO `district` VALUES (308, 21, 'YENİŞEHİR');
INSERT INTO `district` VALUES (309, 81, 'AKÇAKOCA');
INSERT INTO `district` VALUES (310, 81, 'CUMAYERİ');
INSERT INTO `district` VALUES (311, 81, 'ÇİLİMLİ');
INSERT INTO `district` VALUES (312, 81, 'GÖLYAKA');
INSERT INTO `district` VALUES (313, 81, 'GÜMÜŞOVA');
INSERT INTO `district` VALUES (314, 81, 'KAYNAŞLI');
INSERT INTO `district` VALUES (315, 81, 'MERKEZ');
INSERT INTO `district` VALUES (316, 81, 'YIĞILCA');
INSERT INTO `district` VALUES (317, 22, 'ENEZ');
INSERT INTO `district` VALUES (318, 22, 'HAVSA');
INSERT INTO `district` VALUES (319, 22, 'İPSALA');
INSERT INTO `district` VALUES (320, 22, 'KEŞAN');
INSERT INTO `district` VALUES (321, 22, 'LALAPAŞA');
INSERT INTO `district` VALUES (322, 22, 'MERİÇ');
INSERT INTO `district` VALUES (323, 22, 'MERKEZ');
INSERT INTO `district` VALUES (324, 22, 'SÜLOĞLU');
INSERT INTO `district` VALUES (325, 22, 'UZUNKÖPRÜ');
INSERT INTO `district` VALUES (326, 23, 'AĞIN');
INSERT INTO `district` VALUES (327, 23, 'ALACAKAYA');
INSERT INTO `district` VALUES (328, 23, 'ARICAK');
INSERT INTO `district` VALUES (329, 23, 'BASKİL');
INSERT INTO `district` VALUES (330, 23, 'KARAKOÇAN');
INSERT INTO `district` VALUES (331, 23, 'KEBAN');
INSERT INTO `district` VALUES (332, 23, 'KOVANCILAR');
INSERT INTO `district` VALUES (333, 23, 'MADEN');
INSERT INTO `district` VALUES (334, 23, 'MERKEZ');
INSERT INTO `district` VALUES (335, 23, 'PALU');
INSERT INTO `district` VALUES (336, 23, 'SİVRİCE');
INSERT INTO `district` VALUES (337, 24, 'ÇAYIRLI');
INSERT INTO `district` VALUES (338, 24, 'İLİÇ');
INSERT INTO `district` VALUES (339, 24, 'KEMAH');
INSERT INTO `district` VALUES (340, 24, 'KEMALİYE');
INSERT INTO `district` VALUES (341, 24, 'MERKEZ');
INSERT INTO `district` VALUES (342, 24, 'OTLUKBELİ');
INSERT INTO `district` VALUES (343, 24, 'REFAHİYE');
INSERT INTO `district` VALUES (344, 24, 'TERCAN');
INSERT INTO `district` VALUES (345, 24, 'ÜZÜMLÜ');
INSERT INTO `district` VALUES (346, 25, 'AŞKALE');
INSERT INTO `district` VALUES (347, 25, 'AZİZİYE');
INSERT INTO `district` VALUES (348, 25, 'ÇAT');
INSERT INTO `district` VALUES (349, 25, 'HINIS');
INSERT INTO `district` VALUES (350, 25, 'HORASAN');
INSERT INTO `district` VALUES (351, 25, 'İSPİR');
INSERT INTO `district` VALUES (352, 25, 'KARAÇOBAN');
INSERT INTO `district` VALUES (353, 25, 'KARAYAZI');
INSERT INTO `district` VALUES (354, 25, 'KÖPRÜKÖY');
INSERT INTO `district` VALUES (355, 25, 'NARMAN');
INSERT INTO `district` VALUES (356, 25, 'OLTU');
INSERT INTO `district` VALUES (357, 25, 'OLUR');
INSERT INTO `district` VALUES (358, 25, 'PALANDÖKEN');
INSERT INTO `district` VALUES (359, 25, 'PASİNLER');
INSERT INTO `district` VALUES (360, 25, 'PAZARYOLU');
INSERT INTO `district` VALUES (361, 25, 'ŞENKAYA');
INSERT INTO `district` VALUES (362, 25, 'TEKMAN');
INSERT INTO `district` VALUES (363, 25, 'TORTUM');
INSERT INTO `district` VALUES (364, 25, 'UZUNDERE');
INSERT INTO `district` VALUES (365, 25, 'YAKUTİYE');
INSERT INTO `district` VALUES (366, 26, 'ALPU');
INSERT INTO `district` VALUES (367, 26, 'BEYLİKOVA');
INSERT INTO `district` VALUES (368, 26, 'ÇİFTELER');
INSERT INTO `district` VALUES (369, 26, 'GÜNYÜZÜ');
INSERT INTO `district` VALUES (370, 26, 'HAN');
INSERT INTO `district` VALUES (371, 26, 'İNÖNÜ');
INSERT INTO `district` VALUES (372, 26, 'MAHMUDİYE');
INSERT INTO `district` VALUES (373, 26, 'MİHALGAZİ');
INSERT INTO `district` VALUES (374, 26, 'MİHALIÇÇIK');
INSERT INTO `district` VALUES (375, 26, 'ODUNPAZARI');
INSERT INTO `district` VALUES (376, 26, 'SARICAKAYA');
INSERT INTO `district` VALUES (377, 26, 'SEYİTGAZİ');
INSERT INTO `district` VALUES (378, 26, 'SİVRİHİSAR');
INSERT INTO `district` VALUES (379, 26, 'TEPEBAŞI');
INSERT INTO `district` VALUES (380, 27, 'ARABAN');
INSERT INTO `district` VALUES (381, 27, 'İSLAHİYE');
INSERT INTO `district` VALUES (382, 27, 'KARKAMIŞ');
INSERT INTO `district` VALUES (383, 27, 'NİZİP');
INSERT INTO `district` VALUES (384, 27, 'NURDAĞI');
INSERT INTO `district` VALUES (385, 27, 'OĞUZELİ');
INSERT INTO `district` VALUES (386, 27, 'ŞAHİNBEY');
INSERT INTO `district` VALUES (387, 27, 'ŞEHİTKAMİL');
INSERT INTO `district` VALUES (388, 27, 'YAVUZELİ');
INSERT INTO `district` VALUES (389, 28, 'ALUCRA');
INSERT INTO `district` VALUES (390, 28, 'BULANCAK');
INSERT INTO `district` VALUES (391, 28, 'ÇAMOLUK');
INSERT INTO `district` VALUES (392, 28, 'ÇANAKÇI');
INSERT INTO `district` VALUES (393, 28, 'DERELİ');
INSERT INTO `district` VALUES (394, 28, 'DOĞANKENT');
INSERT INTO `district` VALUES (395, 28, 'ESPİYE');
INSERT INTO `district` VALUES (396, 28, 'EYNESİL');
INSERT INTO `district` VALUES (397, 28, 'GÖRELE');
INSERT INTO `district` VALUES (398, 28, 'GÜCE');
INSERT INTO `district` VALUES (399, 28, 'KEŞAP');
INSERT INTO `district` VALUES (400, 28, 'MERKEZ');
INSERT INTO `district` VALUES (401, 28, 'PİRAZİZ');
INSERT INTO `district` VALUES (402, 28, 'ŞEBİNKARAHİSAR');
INSERT INTO `district` VALUES (403, 28, 'TİREBOLU');
INSERT INTO `district` VALUES (404, 28, 'YAĞLIDERE');
INSERT INTO `district` VALUES (405, 29, 'KELKİT');
INSERT INTO `district` VALUES (406, 29, 'KÖSE');
INSERT INTO `district` VALUES (407, 29, 'KÜRTÜN');
INSERT INTO `district` VALUES (408, 29, 'MERKEZ');
INSERT INTO `district` VALUES (409, 29, 'ŞİRAN');
INSERT INTO `district` VALUES (410, 29, 'TORUL');
INSERT INTO `district` VALUES (411, 30, 'ÇUKURCA');
INSERT INTO `district` VALUES (412, 30, 'DERECİK');
INSERT INTO `district` VALUES (413, 30, 'MERKEZ');
INSERT INTO `district` VALUES (414, 30, 'ŞEMDİNLİ');
INSERT INTO `district` VALUES (415, 30, 'YÜKSEKOVA');
INSERT INTO `district` VALUES (416, 31, 'ALTINÖZÜ');
INSERT INTO `district` VALUES (417, 31, 'ANTAKYA');
INSERT INTO `district` VALUES (418, 31, 'ARSUZ');
INSERT INTO `district` VALUES (419, 31, 'BELEN');
INSERT INTO `district` VALUES (420, 31, 'DEFNE');
INSERT INTO `district` VALUES (421, 31, 'DÖRTYOL');
INSERT INTO `district` VALUES (422, 31, 'ERZİN');
INSERT INTO `district` VALUES (423, 31, 'HASSA');
INSERT INTO `district` VALUES (424, 31, 'İSKENDERUN');
INSERT INTO `district` VALUES (425, 31, 'KIRIKHAN');
INSERT INTO `district` VALUES (426, 31, 'KUMLU');
INSERT INTO `district` VALUES (427, 31, 'PAYAS');
INSERT INTO `district` VALUES (428, 31, 'REYHANLI');
INSERT INTO `district` VALUES (429, 31, 'SAMANDAĞ');
INSERT INTO `district` VALUES (430, 31, 'YAYLADAĞI');
INSERT INTO `district` VALUES (431, 76, 'ARALIK');
INSERT INTO `district` VALUES (432, 76, 'KARAKOYUNLU');
INSERT INTO `district` VALUES (433, 76, 'MERKEZ');
INSERT INTO `district` VALUES (434, 76, 'TUZLUCA');
INSERT INTO `district` VALUES (435, 32, 'AKSU');
INSERT INTO `district` VALUES (436, 32, 'ATABEY');
INSERT INTO `district` VALUES (437, 32, 'EĞİRDİR');
INSERT INTO `district` VALUES (438, 32, 'GELENDOST');
INSERT INTO `district` VALUES (439, 32, 'GÖNEN');
INSERT INTO `district` VALUES (440, 32, 'KEÇİBORLU');
INSERT INTO `district` VALUES (441, 32, 'MERKEZ');
INSERT INTO `district` VALUES (442, 32, 'SENİRKENT');
INSERT INTO `district` VALUES (443, 32, 'SÜTÇÜLER');
INSERT INTO `district` VALUES (444, 32, 'ŞARKİKARAAĞAÇ');
INSERT INTO `district` VALUES (445, 32, 'ULUBORLU');
INSERT INTO `district` VALUES (446, 32, 'YALVAÇ');
INSERT INTO `district` VALUES (447, 32, 'YENİŞARBADEMLİ');
INSERT INTO `district` VALUES (448, 34, 'ADALAR');
INSERT INTO `district` VALUES (449, 34, 'ARNAVUTKÖY');
INSERT INTO `district` VALUES (450, 34, 'ATAŞEHİR');
INSERT INTO `district` VALUES (451, 34, 'AVCILAR');
INSERT INTO `district` VALUES (452, 34, 'BAĞCILAR');
INSERT INTO `district` VALUES (453, 34, 'BAHÇELİEVLER');
INSERT INTO `district` VALUES (454, 34, 'BAKIRKÖY');
INSERT INTO `district` VALUES (455, 34, 'BAŞAKŞEHİR');
INSERT INTO `district` VALUES (456, 34, 'BAYRAMPAŞA');
INSERT INTO `district` VALUES (457, 34, 'BEŞİKTAŞ');
INSERT INTO `district` VALUES (458, 34, 'BEYKOZ');
INSERT INTO `district` VALUES (459, 34, 'BEYLİKDÜZÜ');
INSERT INTO `district` VALUES (460, 34, 'BEYOĞLU');
INSERT INTO `district` VALUES (461, 34, 'BÜYÜKÇEKMECE');
INSERT INTO `district` VALUES (462, 34, 'ÇATALCA');
INSERT INTO `district` VALUES (463, 34, 'ÇEKMEKÖY');
INSERT INTO `district` VALUES (464, 34, 'ESENLER');
INSERT INTO `district` VALUES (465, 34, 'ESENYURT');
INSERT INTO `district` VALUES (466, 34, 'EYÜPSULTAN');
INSERT INTO `district` VALUES (467, 34, 'FATİH');
INSERT INTO `district` VALUES (468, 34, 'GAZİOSMANPAŞA');
INSERT INTO `district` VALUES (469, 34, 'GÜNGÖREN');
INSERT INTO `district` VALUES (470, 34, 'KADIKÖY');
INSERT INTO `district` VALUES (471, 34, 'KAĞITHANE');
INSERT INTO `district` VALUES (472, 34, 'KARTAL');
INSERT INTO `district` VALUES (473, 34, 'KÜÇÜKÇEKMECE');
INSERT INTO `district` VALUES (474, 34, 'MALTEPE');
INSERT INTO `district` VALUES (475, 34, 'PENDİK');
INSERT INTO `district` VALUES (476, 34, 'SANCAKTEPE');
INSERT INTO `district` VALUES (477, 34, 'SARIYER');
INSERT INTO `district` VALUES (478, 34, 'SİLİVRİ');
INSERT INTO `district` VALUES (479, 34, 'SULTANBEYLİ');
INSERT INTO `district` VALUES (480, 34, 'SULTANGAZİ');
INSERT INTO `district` VALUES (481, 34, 'ŞİLE');
INSERT INTO `district` VALUES (482, 34, 'ŞİŞLİ');
INSERT INTO `district` VALUES (483, 34, 'TUZLA');
INSERT INTO `district` VALUES (484, 34, 'ÜMRANİYE');
INSERT INTO `district` VALUES (485, 34, 'ÜSKÜDAR');
INSERT INTO `district` VALUES (486, 34, 'ZEYTİNBURNU');
INSERT INTO `district` VALUES (487, 35, 'ALİAĞA');
INSERT INTO `district` VALUES (488, 35, 'BALÇOVA');
INSERT INTO `district` VALUES (489, 35, 'BAYINDIR');
INSERT INTO `district` VALUES (490, 35, 'BAYRAKLI');
INSERT INTO `district` VALUES (491, 35, 'BERGAMA');
INSERT INTO `district` VALUES (492, 35, 'BEYDAĞ');
INSERT INTO `district` VALUES (493, 35, 'BORNOVA');
INSERT INTO `district` VALUES (494, 35, 'BUCA');
INSERT INTO `district` VALUES (495, 35, 'ÇEŞME');
INSERT INTO `district` VALUES (496, 35, 'ÇİĞLİ');
INSERT INTO `district` VALUES (497, 35, 'DİKİLİ');
INSERT INTO `district` VALUES (498, 35, 'FOÇA');
INSERT INTO `district` VALUES (499, 35, 'GAZİEMİR');
INSERT INTO `district` VALUES (500, 35, 'GÜZELBAHÇE');
INSERT INTO `district` VALUES (501, 35, 'KARABAĞLAR');
INSERT INTO `district` VALUES (502, 35, 'KARABURUN');
INSERT INTO `district` VALUES (503, 35, 'KARŞIYAKA');
INSERT INTO `district` VALUES (504, 35, 'KEMALPAŞA');
INSERT INTO `district` VALUES (505, 35, 'KINIK');
INSERT INTO `district` VALUES (506, 35, 'KİRAZ');
INSERT INTO `district` VALUES (507, 35, 'KONAK');
INSERT INTO `district` VALUES (508, 35, 'MENDERES');
INSERT INTO `district` VALUES (509, 35, 'MENEMEN');
INSERT INTO `district` VALUES (510, 35, 'NARLIDERE');
INSERT INTO `district` VALUES (511, 35, 'ÖDEMİŞ');
INSERT INTO `district` VALUES (512, 35, 'SEFERİHİSAR');
INSERT INTO `district` VALUES (513, 35, 'SELÇUK');
INSERT INTO `district` VALUES (514, 35, 'TİRE');
INSERT INTO `district` VALUES (515, 35, 'TORBALI');
INSERT INTO `district` VALUES (516, 35, 'URLA');
INSERT INTO `district` VALUES (517, 46, 'AFŞİN');
INSERT INTO `district` VALUES (518, 46, 'ANDIRIN');
INSERT INTO `district` VALUES (519, 46, 'ÇAĞLAYANCERİT');
INSERT INTO `district` VALUES (520, 46, 'DULKADİROĞLU');
INSERT INTO `district` VALUES (521, 46, 'EKİNÖZÜ');
INSERT INTO `district` VALUES (522, 46, 'ELBİSTAN');
INSERT INTO `district` VALUES (523, 46, 'GÖKSUN');
INSERT INTO `district` VALUES (524, 46, 'NURHAK');
INSERT INTO `district` VALUES (525, 46, 'ONİKİŞUBAT');
INSERT INTO `district` VALUES (526, 46, 'PAZARCIK');
INSERT INTO `district` VALUES (527, 46, 'TÜRKOĞLU');
INSERT INTO `district` VALUES (528, 78, 'EFLANİ');
INSERT INTO `district` VALUES (529, 78, 'ESKİPAZAR');
INSERT INTO `district` VALUES (530, 78, 'MERKEZ');
INSERT INTO `district` VALUES (531, 78, 'OVACIK');
INSERT INTO `district` VALUES (532, 78, 'SAFRANBOLU');
INSERT INTO `district` VALUES (533, 78, 'YENİCE');
INSERT INTO `district` VALUES (534, 70, 'AYRANCI');
INSERT INTO `district` VALUES (535, 70, 'BAŞYAYLA');
INSERT INTO `district` VALUES (536, 70, 'ERMENEK');
INSERT INTO `district` VALUES (537, 70, 'KAZIMKARABEKİR');
INSERT INTO `district` VALUES (538, 70, 'MERKEZ');
INSERT INTO `district` VALUES (539, 70, 'SARIVELİLER');
INSERT INTO `district` VALUES (540, 36, 'AKYAKA');
INSERT INTO `district` VALUES (541, 36, 'ARPAÇAY');
INSERT INTO `district` VALUES (542, 36, 'DİGOR');
INSERT INTO `district` VALUES (543, 36, 'KAĞIZMAN');
INSERT INTO `district` VALUES (544, 36, 'MERKEZ');
INSERT INTO `district` VALUES (545, 36, 'SARIKAMIŞ');
INSERT INTO `district` VALUES (546, 36, 'SELİM');
INSERT INTO `district` VALUES (547, 36, 'SUSUZ');
INSERT INTO `district` VALUES (548, 37, 'ABANA');
INSERT INTO `district` VALUES (549, 37, 'AĞLI');
INSERT INTO `district` VALUES (550, 37, 'ARAÇ');
INSERT INTO `district` VALUES (551, 37, 'AZDAVAY');
INSERT INTO `district` VALUES (552, 37, 'BOZKURT');
INSERT INTO `district` VALUES (553, 37, 'CİDE');
INSERT INTO `district` VALUES (554, 37, 'ÇATALZEYTİN');
INSERT INTO `district` VALUES (555, 37, 'DADAY');
INSERT INTO `district` VALUES (556, 37, 'DEVREKANİ');
INSERT INTO `district` VALUES (557, 37, 'DOĞANYURT');
INSERT INTO `district` VALUES (558, 37, 'HANÖNÜ');
INSERT INTO `district` VALUES (559, 37, 'İHSANGAZİ');
INSERT INTO `district` VALUES (560, 37, 'İNEBOLU');
INSERT INTO `district` VALUES (561, 37, 'KÜRE');
INSERT INTO `district` VALUES (562, 37, 'MERKEZ');
INSERT INTO `district` VALUES (563, 37, 'PINARBAŞI');
INSERT INTO `district` VALUES (564, 37, 'SEYDİLER');
INSERT INTO `district` VALUES (565, 37, 'ŞENPAZAR');
INSERT INTO `district` VALUES (566, 37, 'TAŞKÖPRÜ');
INSERT INTO `district` VALUES (567, 37, 'TOSYA');
INSERT INTO `district` VALUES (568, 38, 'AKKIŞLA');
INSERT INTO `district` VALUES (569, 38, 'BÜNYAN');
INSERT INTO `district` VALUES (570, 38, 'DEVELİ');
INSERT INTO `district` VALUES (571, 38, 'FELAHİYE');
INSERT INTO `district` VALUES (572, 38, 'HACILAR');
INSERT INTO `district` VALUES (573, 38, 'İNCESU');
INSERT INTO `district` VALUES (574, 38, 'KOCASİNAN');
INSERT INTO `district` VALUES (575, 38, 'MELİKGAZİ');
INSERT INTO `district` VALUES (576, 38, 'ÖZVATAN');
INSERT INTO `district` VALUES (577, 38, 'PINARBAŞI');
INSERT INTO `district` VALUES (578, 38, 'SARIOĞLAN');
INSERT INTO `district` VALUES (579, 38, 'SARIZ');
INSERT INTO `district` VALUES (580, 38, 'TALAS');
INSERT INTO `district` VALUES (581, 38, 'TOMARZA');
INSERT INTO `district` VALUES (582, 38, 'YAHYALI');
INSERT INTO `district` VALUES (583, 38, 'YEŞİLHİSAR');
INSERT INTO `district` VALUES (584, 71, 'BAHŞILI');
INSERT INTO `district` VALUES (585, 71, 'BALIŞEYH');
INSERT INTO `district` VALUES (586, 71, 'ÇELEBİ');
INSERT INTO `district` VALUES (587, 71, 'DELİCE');
INSERT INTO `district` VALUES (588, 71, 'KARAKEÇİLİ');
INSERT INTO `district` VALUES (589, 71, 'KESKİN');
INSERT INTO `district` VALUES (590, 71, 'MERKEZ');
INSERT INTO `district` VALUES (591, 71, 'SULAKYURT');
INSERT INTO `district` VALUES (592, 71, 'YAHŞİHAN');
INSERT INTO `district` VALUES (593, 39, 'BABAESKİ');
INSERT INTO `district` VALUES (594, 39, 'DEMİRKÖY');
INSERT INTO `district` VALUES (595, 39, 'KOFÇAZ');
INSERT INTO `district` VALUES (596, 39, 'LÜLEBURGAZ');
INSERT INTO `district` VALUES (597, 39, 'MERKEZ');
INSERT INTO `district` VALUES (598, 39, 'PEHLİVANKÖY');
INSERT INTO `district` VALUES (599, 39, 'PINARHİSAR');
INSERT INTO `district` VALUES (600, 39, 'VİZE');
INSERT INTO `district` VALUES (601, 40, 'AKÇAKENT');
INSERT INTO `district` VALUES (602, 40, 'AKPINAR');
INSERT INTO `district` VALUES (603, 40, 'BOZTEPE');
INSERT INTO `district` VALUES (604, 40, 'ÇİÇEKDAĞI');
INSERT INTO `district` VALUES (605, 40, 'KAMAN');
INSERT INTO `district` VALUES (606, 40, 'MERKEZ');
INSERT INTO `district` VALUES (607, 40, 'MUCUR');
INSERT INTO `district` VALUES (608, 79, 'ELBEYLİ');
INSERT INTO `district` VALUES (609, 79, 'MERKEZ');
INSERT INTO `district` VALUES (610, 79, 'MUSABEYLİ');
INSERT INTO `district` VALUES (611, 79, 'POLATELİ');
INSERT INTO `district` VALUES (612, 41, 'BAŞİSKELE');
INSERT INTO `district` VALUES (613, 41, 'ÇAYIROVA');
INSERT INTO `district` VALUES (614, 41, 'DARICA');
INSERT INTO `district` VALUES (615, 41, 'DERİNCE');
INSERT INTO `district` VALUES (616, 41, 'DİLOVASI');
INSERT INTO `district` VALUES (617, 41, 'GEBZE');
INSERT INTO `district` VALUES (618, 41, 'GÖLCÜK');
INSERT INTO `district` VALUES (619, 41, 'İZMİT');
INSERT INTO `district` VALUES (620, 41, 'KANDIRA');
INSERT INTO `district` VALUES (621, 41, 'KARAMÜRSEL');
INSERT INTO `district` VALUES (622, 41, 'KARTEPE');
INSERT INTO `district` VALUES (623, 41, 'KÖRFEZ');
INSERT INTO `district` VALUES (624, 42, 'AHIRLI');
INSERT INTO `district` VALUES (625, 42, 'AKÖREN');
INSERT INTO `district` VALUES (626, 42, 'AKŞEHİR');
INSERT INTO `district` VALUES (627, 42, 'ALTINEKİN');
INSERT INTO `district` VALUES (628, 42, 'BEYŞEHİR');
INSERT INTO `district` VALUES (629, 42, 'BOZKIR');
INSERT INTO `district` VALUES (630, 42, 'CİHANBEYLİ');
INSERT INTO `district` VALUES (631, 42, 'ÇELTİK');
INSERT INTO `district` VALUES (632, 42, 'ÇUMRA');
INSERT INTO `district` VALUES (633, 42, 'DERBENT');
INSERT INTO `district` VALUES (634, 42, 'DEREBUCAK');
INSERT INTO `district` VALUES (635, 42, 'DOĞANHİSAR');
INSERT INTO `district` VALUES (636, 42, 'EMİRGAZİ');
INSERT INTO `district` VALUES (637, 42, 'EREĞLİ');
INSERT INTO `district` VALUES (638, 42, 'GÜNEYSINIR');
INSERT INTO `district` VALUES (639, 42, 'HADİM');
INSERT INTO `district` VALUES (640, 42, 'HALKAPINAR');
INSERT INTO `district` VALUES (641, 42, 'HÜYÜK');
INSERT INTO `district` VALUES (642, 42, 'ILGIN');
INSERT INTO `district` VALUES (643, 42, 'KADINHANI');
INSERT INTO `district` VALUES (644, 42, 'KARAPINAR');
INSERT INTO `district` VALUES (645, 42, 'KARATAY');
INSERT INTO `district` VALUES (646, 42, 'KULU');
INSERT INTO `district` VALUES (647, 42, 'MERAM');
INSERT INTO `district` VALUES (648, 42, 'SARAYÖNÜ');
INSERT INTO `district` VALUES (649, 42, 'SELÇUKLU');
INSERT INTO `district` VALUES (650, 42, 'SEYDİŞEHİR');
INSERT INTO `district` VALUES (651, 42, 'TAŞKENT');
INSERT INTO `district` VALUES (652, 42, 'TUZLUKÇU');
INSERT INTO `district` VALUES (653, 42, 'YALIHÜYÜK');
INSERT INTO `district` VALUES (654, 42, 'YUNAK');
INSERT INTO `district` VALUES (655, 43, 'ALTINTAŞ');
INSERT INTO `district` VALUES (656, 43, 'ASLANAPA');
INSERT INTO `district` VALUES (657, 43, 'ÇAVDARHİSAR');
INSERT INTO `district` VALUES (658, 43, 'DOMANİÇ');
INSERT INTO `district` VALUES (659, 43, 'DUMLUPINAR');
INSERT INTO `district` VALUES (660, 43, 'EMET');
INSERT INTO `district` VALUES (661, 43, 'GEDİZ');
INSERT INTO `district` VALUES (662, 43, 'HİSARCIK');
INSERT INTO `district` VALUES (663, 43, 'MERKEZ');
INSERT INTO `district` VALUES (664, 43, 'PAZARLAR');
INSERT INTO `district` VALUES (665, 43, 'SİMAV');
INSERT INTO `district` VALUES (666, 43, 'ŞAPHANE');
INSERT INTO `district` VALUES (667, 43, 'TAVŞANLI');
INSERT INTO `district` VALUES (668, 44, 'AKÇADAĞ');
INSERT INTO `district` VALUES (669, 44, 'ARAPGİR');
INSERT INTO `district` VALUES (670, 44, 'ARGUVAN');
INSERT INTO `district` VALUES (671, 44, 'BATTALGAZİ');
INSERT INTO `district` VALUES (672, 44, 'DARENDE');
INSERT INTO `district` VALUES (673, 44, 'DOĞANŞEHİR');
INSERT INTO `district` VALUES (674, 44, 'DOĞANYOL');
INSERT INTO `district` VALUES (675, 44, 'HEKİMHAN');
INSERT INTO `district` VALUES (676, 44, 'KALE');
INSERT INTO `district` VALUES (677, 44, 'KULUNCAK');
INSERT INTO `district` VALUES (678, 44, 'PÜTÜRGE');
INSERT INTO `district` VALUES (679, 44, 'YAZIHAN');
INSERT INTO `district` VALUES (680, 44, 'YEŞİLYURT');
INSERT INTO `district` VALUES (681, 45, 'AHMETLİ');
INSERT INTO `district` VALUES (682, 45, 'AKHİSAR');
INSERT INTO `district` VALUES (683, 45, 'ALAŞEHİR');
INSERT INTO `district` VALUES (684, 45, 'DEMİRCİ');
INSERT INTO `district` VALUES (685, 45, 'GÖLMARMARA');
INSERT INTO `district` VALUES (686, 45, 'GÖRDES');
INSERT INTO `district` VALUES (687, 45, 'KIRKAĞAÇ');
INSERT INTO `district` VALUES (688, 45, 'KÖPRÜBAŞI');
INSERT INTO `district` VALUES (689, 45, 'KULA');
INSERT INTO `district` VALUES (690, 45, 'SALİHLİ');
INSERT INTO `district` VALUES (691, 45, 'SARIGÖL');
INSERT INTO `district` VALUES (692, 45, 'SARUHANLI');
INSERT INTO `district` VALUES (693, 45, 'SELENDİ');
INSERT INTO `district` VALUES (694, 45, 'SOMA');
INSERT INTO `district` VALUES (695, 45, 'ŞEHZADELER');
INSERT INTO `district` VALUES (696, 45, 'TURGUTLU');
INSERT INTO `district` VALUES (697, 45, 'YUNUSEMRE');
INSERT INTO `district` VALUES (698, 47, 'ARTUKLU');
INSERT INTO `district` VALUES (699, 47, 'DARGEÇİT');
INSERT INTO `district` VALUES (700, 47, 'DERİK');
INSERT INTO `district` VALUES (701, 47, 'KIZILTEPE');
INSERT INTO `district` VALUES (702, 47, 'MAZIDAĞI');
INSERT INTO `district` VALUES (703, 47, 'MİDYAT');
INSERT INTO `district` VALUES (704, 47, 'NUSAYBİN');
INSERT INTO `district` VALUES (705, 47, 'ÖMERLİ');
INSERT INTO `district` VALUES (706, 47, 'SAVUR');
INSERT INTO `district` VALUES (707, 47, 'YEŞİLLİ');
INSERT INTO `district` VALUES (708, 33, 'AKDENİZ');
INSERT INTO `district` VALUES (709, 33, 'ANAMUR');
INSERT INTO `district` VALUES (710, 33, 'AYDINCIK');
INSERT INTO `district` VALUES (711, 33, 'BOZYAZI');
INSERT INTO `district` VALUES (712, 33, 'ÇAMLIYAYLA');
INSERT INTO `district` VALUES (713, 33, 'ERDEMLİ');
INSERT INTO `district` VALUES (714, 33, 'GÜLNAR');
INSERT INTO `district` VALUES (715, 33, 'MEZİTLİ');
INSERT INTO `district` VALUES (716, 33, 'MUT');
INSERT INTO `district` VALUES (717, 33, 'SİLİFKE');
INSERT INTO `district` VALUES (718, 33, 'TARSUS');
INSERT INTO `district` VALUES (719, 33, 'TOROSLAR');
INSERT INTO `district` VALUES (720, 33, 'YENİŞEHİR');
INSERT INTO `district` VALUES (721, 48, 'BODRUM');
INSERT INTO `district` VALUES (722, 48, 'DALAMAN');
INSERT INTO `district` VALUES (723, 48, 'DATÇA');
INSERT INTO `district` VALUES (724, 48, 'FETHİYE');
INSERT INTO `district` VALUES (725, 48, 'KAVAKLIDERE');
INSERT INTO `district` VALUES (726, 48, 'KÖYCEĞİZ');
INSERT INTO `district` VALUES (727, 48, 'MARMARİS');
INSERT INTO `district` VALUES (728, 48, 'MENTEŞE');
INSERT INTO `district` VALUES (729, 48, 'MİLAS');
INSERT INTO `district` VALUES (730, 48, 'ORTACA');
INSERT INTO `district` VALUES (731, 48, 'SEYDİKEMER');
INSERT INTO `district` VALUES (732, 48, 'ULA');
INSERT INTO `district` VALUES (733, 48, 'YATAĞAN');
INSERT INTO `district` VALUES (734, 49, 'BULANIK');
INSERT INTO `district` VALUES (735, 49, 'HASKÖY');
INSERT INTO `district` VALUES (736, 49, 'KORKUT');
INSERT INTO `district` VALUES (737, 49, 'MALAZGİRT');
INSERT INTO `district` VALUES (738, 49, 'MERKEZ');
INSERT INTO `district` VALUES (739, 49, 'VARTO');
INSERT INTO `district` VALUES (740, 50, 'ACIGÖL');
INSERT INTO `district` VALUES (741, 50, 'AVANOS');
INSERT INTO `district` VALUES (742, 50, 'DERİNKUYU');
INSERT INTO `district` VALUES (743, 50, 'GÜLŞEHİR');
INSERT INTO `district` VALUES (744, 50, 'HACIBEKTAŞ');
INSERT INTO `district` VALUES (745, 50, 'KOZAKLI');
INSERT INTO `district` VALUES (746, 50, 'MERKEZ');
INSERT INTO `district` VALUES (747, 50, 'ÜRGÜP');
INSERT INTO `district` VALUES (748, 51, 'ALTUNHİSAR');
INSERT INTO `district` VALUES (749, 51, 'BOR');
INSERT INTO `district` VALUES (750, 51, 'ÇAMARDI');
INSERT INTO `district` VALUES (751, 51, 'ÇİFTLİK');
INSERT INTO `district` VALUES (752, 51, 'MERKEZ');
INSERT INTO `district` VALUES (753, 51, 'ULUKIŞLA');
INSERT INTO `district` VALUES (754, 52, 'AKKUŞ');
INSERT INTO `district` VALUES (755, 52, 'ALTINORDU');
INSERT INTO `district` VALUES (756, 52, 'AYBASTI');
INSERT INTO `district` VALUES (757, 52, 'ÇAMAŞ');
INSERT INTO `district` VALUES (758, 52, 'ÇATALPINAR');
INSERT INTO `district` VALUES (759, 52, 'ÇAYBAŞI');
INSERT INTO `district` VALUES (760, 52, 'FATSA');
INSERT INTO `district` VALUES (761, 52, 'GÖLKÖY');
INSERT INTO `district` VALUES (762, 52, 'GÜLYALI');
INSERT INTO `district` VALUES (763, 52, 'GÜRGENTEPE');
INSERT INTO `district` VALUES (764, 52, 'İKİZCE');
INSERT INTO `district` VALUES (765, 52, 'KABADÜZ');
INSERT INTO `district` VALUES (766, 52, 'KABATAŞ');
INSERT INTO `district` VALUES (767, 52, 'KORGAN');
INSERT INTO `district` VALUES (768, 52, 'KUMRU');
INSERT INTO `district` VALUES (769, 52, 'MESUDİYE');
INSERT INTO `district` VALUES (770, 52, 'PERŞEMBE');
INSERT INTO `district` VALUES (771, 52, 'ULUBEY');
INSERT INTO `district` VALUES (772, 52, 'ÜNYE');
INSERT INTO `district` VALUES (773, 80, 'BAHÇE');
INSERT INTO `district` VALUES (774, 80, 'DÜZİÇİ');
INSERT INTO `district` VALUES (775, 80, 'HASANBEYLİ');
INSERT INTO `district` VALUES (776, 80, 'KADİRLİ');
INSERT INTO `district` VALUES (777, 80, 'MERKEZ');
INSERT INTO `district` VALUES (778, 80, 'SUMBAS');
INSERT INTO `district` VALUES (779, 80, 'TOPRAKKALE');
INSERT INTO `district` VALUES (780, 53, 'ARDEŞEN');
INSERT INTO `district` VALUES (781, 53, 'ÇAMLIHEMŞİN');
INSERT INTO `district` VALUES (782, 53, 'ÇAYELİ');
INSERT INTO `district` VALUES (783, 53, 'DEREPAZARI');
INSERT INTO `district` VALUES (784, 53, 'FINDIKLI');
INSERT INTO `district` VALUES (785, 53, 'GÜNEYSU');
INSERT INTO `district` VALUES (786, 53, 'HEMŞİN');
INSERT INTO `district` VALUES (787, 53, 'İKİZDERE');
INSERT INTO `district` VALUES (788, 53, 'İYİDERE');
INSERT INTO `district` VALUES (789, 53, 'KALKANDERE');
INSERT INTO `district` VALUES (790, 53, 'MERKEZ');
INSERT INTO `district` VALUES (791, 53, 'PAZAR');
INSERT INTO `district` VALUES (792, 54, 'ADAPAZARI');
INSERT INTO `district` VALUES (793, 54, 'AKYAZI');
INSERT INTO `district` VALUES (794, 54, 'ARİFİYE');
INSERT INTO `district` VALUES (795, 54, 'ERENLER');
INSERT INTO `district` VALUES (796, 54, 'FERİZLİ');
INSERT INTO `district` VALUES (797, 54, 'GEYVE');
INSERT INTO `district` VALUES (798, 54, 'HENDEK');
INSERT INTO `district` VALUES (799, 54, 'KARAPÜRÇEK');
INSERT INTO `district` VALUES (800, 54, 'KARASU');
INSERT INTO `district` VALUES (801, 54, 'KAYNARCA');
INSERT INTO `district` VALUES (802, 54, 'KOCAALİ');
INSERT INTO `district` VALUES (803, 54, 'PAMUKOVA');
INSERT INTO `district` VALUES (804, 54, 'SAPANCA');
INSERT INTO `district` VALUES (805, 54, 'SERDİVAN');
INSERT INTO `district` VALUES (806, 54, 'SÖĞÜTLÜ');
INSERT INTO `district` VALUES (807, 54, 'TARAKLI');
INSERT INTO `district` VALUES (808, 55, '19 MAYIS');
INSERT INTO `district` VALUES (809, 55, 'ALAÇAM');
INSERT INTO `district` VALUES (810, 55, 'ASARCIK');
INSERT INTO `district` VALUES (811, 55, 'ATAKUM');
INSERT INTO `district` VALUES (812, 55, 'AYVACIK');
INSERT INTO `district` VALUES (813, 55, 'BAFRA');
INSERT INTO `district` VALUES (814, 55, 'CANİK');
INSERT INTO `district` VALUES (815, 55, 'ÇARŞAMBA');
INSERT INTO `district` VALUES (816, 55, 'HAVZA');
INSERT INTO `district` VALUES (817, 55, 'İLKADIM');
INSERT INTO `district` VALUES (818, 55, 'KAVAK');
INSERT INTO `district` VALUES (819, 55, 'LADİK');
INSERT INTO `district` VALUES (820, 55, 'SALIPAZARI');
INSERT INTO `district` VALUES (821, 55, 'TEKKEKÖY');
INSERT INTO `district` VALUES (822, 55, 'TERME');
INSERT INTO `district` VALUES (823, 55, 'VEZİRKÖPRÜ');
INSERT INTO `district` VALUES (824, 55, 'YAKAKENT');
INSERT INTO `district` VALUES (825, 56, 'BAYKAN');
INSERT INTO `district` VALUES (826, 56, 'ERUH');
INSERT INTO `district` VALUES (827, 56, 'KURTALAN');
INSERT INTO `district` VALUES (828, 56, 'MERKEZ');
INSERT INTO `district` VALUES (829, 56, 'PERVARİ');
INSERT INTO `district` VALUES (830, 56, 'ŞİRVAN');
INSERT INTO `district` VALUES (831, 56, 'TİLLO');
INSERT INTO `district` VALUES (832, 57, 'AYANCIK');
INSERT INTO `district` VALUES (833, 57, 'BOYABAT');
INSERT INTO `district` VALUES (834, 57, 'DİKMEN');
INSERT INTO `district` VALUES (835, 57, 'DURAĞAN');
INSERT INTO `district` VALUES (836, 57, 'ERFELEK');
INSERT INTO `district` VALUES (837, 57, 'GERZE');
INSERT INTO `district` VALUES (838, 57, 'MERKEZ');
INSERT INTO `district` VALUES (839, 57, 'SARAYDÜZÜ');
INSERT INTO `district` VALUES (840, 57, 'TÜRKELİ');
INSERT INTO `district` VALUES (841, 58, 'AKINCILAR');
INSERT INTO `district` VALUES (842, 58, 'ALTINYAYLA');
INSERT INTO `district` VALUES (843, 58, 'DİVRİĞİ');
INSERT INTO `district` VALUES (844, 58, 'DOĞANŞAR');
INSERT INTO `district` VALUES (845, 58, 'GEMEREK');
INSERT INTO `district` VALUES (846, 58, 'GÖLOVA');
INSERT INTO `district` VALUES (847, 58, 'GÜRÜN');
INSERT INTO `district` VALUES (848, 58, 'HAFİK');
INSERT INTO `district` VALUES (849, 58, 'İMRANLI');
INSERT INTO `district` VALUES (850, 58, 'KANGAL');
INSERT INTO `district` VALUES (851, 58, 'KOYULHİSAR');
INSERT INTO `district` VALUES (852, 58, 'MERKEZ');
INSERT INTO `district` VALUES (853, 58, 'SUŞEHRİ');
INSERT INTO `district` VALUES (854, 58, 'ŞARKIŞLA');
INSERT INTO `district` VALUES (855, 58, 'ULAŞ');
INSERT INTO `district` VALUES (856, 58, 'YILDIZELİ');
INSERT INTO `district` VALUES (857, 58, 'ZARA');
INSERT INTO `district` VALUES (858, 63, 'AKÇAKALE');
INSERT INTO `district` VALUES (859, 63, 'BİRECİK');
INSERT INTO `district` VALUES (860, 63, 'BOZOVA');
INSERT INTO `district` VALUES (861, 63, 'CEYLANPINAR');
INSERT INTO `district` VALUES (862, 63, 'EYYÜBİYE');
INSERT INTO `district` VALUES (863, 63, 'HALFETİ');
INSERT INTO `district` VALUES (864, 63, 'HALİLİYE');
INSERT INTO `district` VALUES (865, 63, 'HARRAN');
INSERT INTO `district` VALUES (866, 63, 'HİLVAN');
INSERT INTO `district` VALUES (867, 63, 'KARAKÖPRÜ');
INSERT INTO `district` VALUES (868, 63, 'SİVEREK');
INSERT INTO `district` VALUES (869, 63, 'SURUÇ');
INSERT INTO `district` VALUES (870, 63, 'VİRANŞEHİR');
INSERT INTO `district` VALUES (871, 73, 'BEYTÜŞŞEBAP');
INSERT INTO `district` VALUES (872, 73, 'CİZRE');
INSERT INTO `district` VALUES (873, 73, 'GÜÇLÜKONAK');
INSERT INTO `district` VALUES (874, 73, 'İDİL');
INSERT INTO `district` VALUES (875, 73, 'MERKEZ');
INSERT INTO `district` VALUES (876, 73, 'SİLOPİ');
INSERT INTO `district` VALUES (877, 73, 'ULUDERE');
INSERT INTO `district` VALUES (878, 59, 'ÇERKEZKÖY');
INSERT INTO `district` VALUES (879, 59, 'ÇORLU');
INSERT INTO `district` VALUES (880, 59, 'ERGENE');
INSERT INTO `district` VALUES (881, 59, 'HAYRABOLU');
INSERT INTO `district` VALUES (882, 59, 'KAPAKLI');
INSERT INTO `district` VALUES (883, 59, 'MALKARA');
INSERT INTO `district` VALUES (884, 59, 'MARMARAEREĞLİSİ');
INSERT INTO `district` VALUES (885, 59, 'MURATLI');
INSERT INTO `district` VALUES (886, 59, 'SARAY');
INSERT INTO `district` VALUES (887, 59, 'SÜLEYMANPAŞA');
INSERT INTO `district` VALUES (888, 59, 'ŞARKÖY');
INSERT INTO `district` VALUES (889, 60, 'ALMUS');
INSERT INTO `district` VALUES (890, 60, 'ARTOVA');
INSERT INTO `district` VALUES (891, 60, 'BAŞÇİFTLİK');
INSERT INTO `district` VALUES (892, 60, 'ERBAA');
INSERT INTO `district` VALUES (893, 60, 'MERKEZ');
INSERT INTO `district` VALUES (894, 60, 'NİKSAR');
INSERT INTO `district` VALUES (895, 60, 'PAZAR');
INSERT INTO `district` VALUES (896, 60, 'REŞADİYE');
INSERT INTO `district` VALUES (897, 60, 'SULUSARAY');
INSERT INTO `district` VALUES (898, 60, 'TURHAL');
INSERT INTO `district` VALUES (899, 60, 'YEŞİLYURT');
INSERT INTO `district` VALUES (900, 60, 'ZİLE');
INSERT INTO `district` VALUES (901, 61, 'AKÇAABAT');
INSERT INTO `district` VALUES (902, 61, 'ARAKLI');
INSERT INTO `district` VALUES (903, 61, 'ARSİN');
INSERT INTO `district` VALUES (904, 61, 'BEŞİKDÜZÜ');
INSERT INTO `district` VALUES (905, 61, 'ÇARŞIBAŞI');
INSERT INTO `district` VALUES (906, 61, 'ÇAYKARA');
INSERT INTO `district` VALUES (907, 61, 'DERNEKPAZARI');
INSERT INTO `district` VALUES (908, 61, 'DÜZKÖY');
INSERT INTO `district` VALUES (909, 61, 'HAYRAT');
INSERT INTO `district` VALUES (910, 61, 'KÖPRÜBAŞI');
INSERT INTO `district` VALUES (911, 61, 'MAÇKA');
INSERT INTO `district` VALUES (912, 61, 'OF');
INSERT INTO `district` VALUES (913, 61, 'ORTAHİSAR');
INSERT INTO `district` VALUES (914, 61, 'SÜRMENE');
INSERT INTO `district` VALUES (915, 61, 'ŞALPAZARI');
INSERT INTO `district` VALUES (916, 61, 'TONYA');
INSERT INTO `district` VALUES (917, 61, 'VAKFIKEBİR');
INSERT INTO `district` VALUES (918, 61, 'YOMRA');
INSERT INTO `district` VALUES (919, 62, 'ÇEMİŞGEZEK');
INSERT INTO `district` VALUES (920, 62, 'HOZAT');
INSERT INTO `district` VALUES (921, 62, 'MAZGİRT');
INSERT INTO `district` VALUES (922, 62, 'MERKEZ');
INSERT INTO `district` VALUES (923, 62, 'NAZIMİYE');
INSERT INTO `district` VALUES (924, 62, 'OVACIK');
INSERT INTO `district` VALUES (925, 62, 'PERTEK');
INSERT INTO `district` VALUES (926, 62, 'PÜLÜMÜR');
INSERT INTO `district` VALUES (927, 64, 'BANAZ');
INSERT INTO `district` VALUES (928, 64, 'EŞME');
INSERT INTO `district` VALUES (929, 64, 'KARAHALLI');
INSERT INTO `district` VALUES (930, 64, 'MERKEZ');
INSERT INTO `district` VALUES (931, 64, 'SİVASLI');
INSERT INTO `district` VALUES (932, 64, 'ULUBEY');
INSERT INTO `district` VALUES (933, 65, 'BAHÇESARAY');
INSERT INTO `district` VALUES (934, 65, 'BAŞKALE');
INSERT INTO `district` VALUES (935, 65, 'ÇALDIRAN');
INSERT INTO `district` VALUES (936, 65, 'ÇATAK');
INSERT INTO `district` VALUES (937, 65, 'EDREMİT');
INSERT INTO `district` VALUES (938, 65, 'ERCİŞ');
INSERT INTO `district` VALUES (939, 65, 'GEVAŞ');
INSERT INTO `district` VALUES (940, 65, 'GÜRPINAR');
INSERT INTO `district` VALUES (941, 65, 'İPEKYOLU');
INSERT INTO `district` VALUES (942, 65, 'MURADİYE');
INSERT INTO `district` VALUES (943, 65, 'ÖZALP');
INSERT INTO `district` VALUES (944, 65, 'SARAY');
INSERT INTO `district` VALUES (945, 65, 'TUŞBA');
INSERT INTO `district` VALUES (946, 77, 'ALTINOVA');
INSERT INTO `district` VALUES (947, 77, 'ARMUTLU');
INSERT INTO `district` VALUES (948, 77, 'ÇINARCIK');
INSERT INTO `district` VALUES (949, 77, 'ÇİFTLİKKÖY');
INSERT INTO `district` VALUES (950, 77, 'MERKEZ');
INSERT INTO `district` VALUES (951, 77, 'TERMAL');
INSERT INTO `district` VALUES (952, 66, 'AKDAĞMADENİ');
INSERT INTO `district` VALUES (953, 66, 'AYDINCIK');
INSERT INTO `district` VALUES (954, 66, 'BOĞAZLIYAN');
INSERT INTO `district` VALUES (955, 66, 'ÇANDIR');
INSERT INTO `district` VALUES (956, 66, 'ÇAYIRALAN');
INSERT INTO `district` VALUES (957, 66, 'ÇEKEREK');
INSERT INTO `district` VALUES (958, 66, 'KADIŞEHRİ');
INSERT INTO `district` VALUES (959, 66, 'MERKEZ');
INSERT INTO `district` VALUES (960, 66, 'SARAYKENT');
INSERT INTO `district` VALUES (961, 66, 'SARIKAYA');
INSERT INTO `district` VALUES (962, 66, 'SORGUN');
INSERT INTO `district` VALUES (963, 66, 'ŞEFAATLİ');
INSERT INTO `district` VALUES (964, 66, 'YENİFAKILI');
INSERT INTO `district` VALUES (965, 66, 'YERKÖY');
INSERT INTO `district` VALUES (966, 67, 'ALAPLI');
INSERT INTO `district` VALUES (967, 67, 'ÇAYCUMA');
INSERT INTO `district` VALUES (968, 67, 'DEVREK');
INSERT INTO `district` VALUES (969, 67, 'EREĞLİ');
INSERT INTO `district` VALUES (970, 67, 'GÖKÇEBEY');
INSERT INTO `district` VALUES (971, 67, 'KİLİMLİ');
INSERT INTO `district` VALUES (972, 67, 'KOZLU');
INSERT INTO `district` VALUES (973, 67, 'MERKEZ');

-- ----------------------------
-- Table structure for join_pet_customer
-- ----------------------------
DROP TABLE IF EXISTS `join_pet_customer`;
CREATE TABLE `join_pet_customer`  (
  `jpt_id` int NOT NULL AUTO_INCREMENT,
  `cu_id` int NULL DEFAULT NULL,
  `pet_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`jpt_id`) USING BTREE,
  INDEX `FK3uyq20458seoh35infpm0pdwp`(`cu_id`) USING BTREE,
  INDEX `FKhj4g08bd3pyhnhx1tboxsye2c`(`pet_id`) USING BTREE,
  CONSTRAINT `FK3uyq20458seoh35infpm0pdwp` FOREIGN KEY (`cu_id`) REFERENCES `customer` (`cu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhj4g08bd3pyhnhx1tboxsye2c` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of join_pet_customer
-- ----------------------------
INSERT INTO `join_pet_customer` VALUES (1, 1, 1);
INSERT INTO `join_pet_customer` VALUES (2, 2, 2);
INSERT INTO `join_pet_customer` VALUES (3, 2, 3);
INSERT INTO `join_pet_customer` VALUES (4, 3, 4);
INSERT INTO `join_pet_customer` VALUES (6, 7, 6);
INSERT INTO `join_pet_customer` VALUES (7, 7, 7);
INSERT INTO `join_pet_customer` VALUES (16, 15, 16);
INSERT INTO `join_pet_customer` VALUES (20, 15, 20);
INSERT INTO `join_pet_customer` VALUES (21, 15, 21);
INSERT INTO `join_pet_customer` VALUES (24, 15, 24);
INSERT INTO `join_pet_customer` VALUES (25, 15, 25);
INSERT INTO `join_pet_customer` VALUES (26, 15, 26);
INSERT INTO `join_pet_customer` VALUES (28, 3, 28);
INSERT INTO `join_pet_customer` VALUES (29, 1, 29);
INSERT INTO `join_pet_customer` VALUES (30, 3, 30);
INSERT INTO `join_pet_customer` VALUES (35, 1, 35);

-- ----------------------------
-- Table structure for join_type_breed_pet
-- ----------------------------
DROP TABLE IF EXISTS `join_type_breed_pet`;
CREATE TABLE `join_type_breed_pet`  (
  `jtbp_id` int NOT NULL AUTO_INCREMENT,
  `br_id` int NULL DEFAULT NULL,
  `ty_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`jtbp_id`) USING BTREE,
  INDEX `FK1r8r447jgydjxp9y2paodm33d`(`br_id`) USING BTREE,
  INDEX `FK8wmgx38jb8gt9e2h0vgy7qppx`(`ty_id`) USING BTREE,
  CONSTRAINT `FK1r8r447jgydjxp9y2paodm33d` FOREIGN KEY (`br_id`) REFERENCES `breed_pet` (`br_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK8wmgx38jb8gt9e2h0vgy7qppx` FOREIGN KEY (`ty_id`) REFERENCES `type_pet` (`ty_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of join_type_breed_pet
-- ----------------------------
INSERT INTO `join_type_breed_pet` VALUES (1, 39, 8);
INSERT INTO `join_type_breed_pet` VALUES (2, 5, 1);
INSERT INTO `join_type_breed_pet` VALUES (3, 24, 5);
INSERT INTO `join_type_breed_pet` VALUES (4, 18, 4);
INSERT INTO `join_type_breed_pet` VALUES (6, 14, 3);
INSERT INTO `join_type_breed_pet` VALUES (7, 18, 4);
INSERT INTO `join_type_breed_pet` VALUES (16, 11, 3);
INSERT INTO `join_type_breed_pet` VALUES (20, 6, 2);
INSERT INTO `join_type_breed_pet` VALUES (21, 8, 2);
INSERT INTO `join_type_breed_pet` VALUES (24, 9, 2);
INSERT INTO `join_type_breed_pet` VALUES (25, 14, 3);
INSERT INTO `join_type_breed_pet` VALUES (26, 13, 3);
INSERT INTO `join_type_breed_pet` VALUES (28, 17, 4);
INSERT INTO `join_type_breed_pet` VALUES (29, 13, 3);
INSERT INTO `join_type_breed_pet` VALUES (30, 2, 1);
INSERT INTO `join_type_breed_pet` VALUES (35, 17, 4);

-- ----------------------------
-- Table structure for lab
-- ----------------------------
DROP TABLE IF EXISTS `lab`;
CREATE TABLE `lab`  (
  `lab_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `lab_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lab_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lab_type` int NULL DEFAULT NULL,
  `jpt_id` int NULL DEFAULT NULL,
  `us_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`lab_id`) USING BTREE,
  INDEX `FKtdrxfyx6dg4yridbn8shmugbm`(`jpt_id`) USING BTREE,
  INDEX `FK9p0ocmgbx755bkuhdhlfj6ybt`(`us_id`) USING BTREE,
  CONSTRAINT `FK9p0ocmgbx755bkuhdhlfj6ybt` FOREIGN KEY (`us_id`) REFERENCES `user` (`us_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtdrxfyx6dg4yridbn8shmugbm` FOREIGN KEY (`jpt_id`) REFERENCES `join_pet_customer` (`jpt_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab
-- ----------------------------
INSERT INTO `lab` VALUES (3, 'selen@mail.com', '2021-09-26 14:54:02', 'selen@mail.com', '2021-09-26 14:54:02', 'Anahtar yutmuş.', '45d0fe4c-2a2f-47c9-ab4a-ae1b34a79eae.jpg', 1, 1, 3);
INSERT INTO `lab` VALUES (4, 'selen@mail.com', '2021-09-26 14:54:59', 'selen@mail.com', '2021-09-26 14:54:59', 'Daha detaylı incelenmeli. Kesin tanı konulamadı.', 'a36e88c1-5a55-4e3e-9cb7-3b104332dee3.jpg', 2, 29, 1);

-- ----------------------------
-- Table structure for payment_in
-- ----------------------------
DROP TABLE IF EXISTS `payment_in`;
CREATE TABLE `payment_in`  (
  `pin_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `pin_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pin_operation_type` int NULL DEFAULT NULL,
  `pin_payment_type` int NULL DEFAULT NULL,
  `pin_price` int NULL DEFAULT NULL,
  `cu_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`pin_id`) USING BTREE,
  INDEX `FKddur8r3p1i8caorao565mrxxa`(`cu_id`) USING BTREE,
  CONSTRAINT `FKddur8r3p1i8caorao565mrxxa` FOREIGN KEY (`cu_id`) REFERENCES `customer` (`cu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_in
-- ----------------------------
INSERT INTO `payment_in` VALUES (1, 'mert@mail.com', '2021-09-23 23:33:19', 'mert@mail.com', '2021-09-23 23:33:19', '23.09.2021 ödeme alındı.', 0, 3, 5, 1);
INSERT INTO `payment_in` VALUES (3, 'selen@mail.com', '2021-09-24 11:11:59', 'selen@mail.com', '2021-09-24 11:11:59', '.', 0, 2, 680, 7);
INSERT INTO `payment_in` VALUES (4, 'selen@mail.com', '2021-09-24 11:12:26', 'selen@mail.com', '2021-09-24 11:12:26', '.', 0, 1, 9, 7);

-- ----------------------------
-- Table structure for payment_out
-- ----------------------------
DROP TABLE IF EXISTS `payment_out`;
CREATE TABLE `payment_out`  (
  `pout_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `pout_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pout_operation_type` int NULL DEFAULT NULL,
  `pout_payment_type` int NULL DEFAULT NULL,
  `pout_price` int NULL DEFAULT NULL,
  PRIMARY KEY (`pout_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_out
-- ----------------------------
INSERT INTO `payment_out` VALUES (1, 'mert@mail.com', '2021-09-23 23:33:34', 'mert@mail.com', '2021-09-23 23:33:34', 'Kasadan haraç kesildi.', 1, 3, 4);
INSERT INTO `payment_out` VALUES (2, 'selen@mail.com', '2021-09-24 11:13:01', 'selen@mail.com', '2021-09-24 11:13:01', 'Cam kırıldı.', 1, 1, 500);

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet`  (
  `pet_id` int NOT NULL AUTO_INCREMENT,
  `pet_born_date` datetime NULL DEFAULT NULL,
  `pet_chip_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pet_ear_tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pet_gender` bit(1) NULL DEFAULT NULL,
  `pet_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pet_neutering` bit(1) NULL DEFAULT NULL,
  `color_pet_color_id` int NULL DEFAULT NULL,
  `join_type_breed_pet` int NULL DEFAULT NULL,
  PRIMARY KEY (`pet_id`) USING BTREE,
  INDEX `FKhl7tkyjalv2t4siraqtri15wo`(`color_pet_color_id`) USING BTREE,
  INDEX `FK5kggjwb4bwtna1kv58rlrq652`(`join_type_breed_pet`) USING BTREE,
  CONSTRAINT `FK5kggjwb4bwtna1kv58rlrq652` FOREIGN KEY (`join_type_breed_pet`) REFERENCES `join_type_breed_pet` (`jtbp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhl7tkyjalv2t4siraqtri15wo` FOREIGN KEY (`color_pet_color_id`) REFERENCES `color_pet` (`color_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES (1, '2021-09-23 03:00:00', '1', '1', b'1', 'MONSTER', b'1', 11, 1);
INSERT INTO `pet` VALUES (2, '2021-09-23 03:00:00', '123', '123', b'0', 'ÇAMUR', b'1', 8, 2);
INSERT INTO `pet` VALUES (3, '2021-09-23 03:00:00', '1', '1', b'0', 'NALET', b'1', 6, 3);
INSERT INTO `pet` VALUES (4, '2021-09-24 03:00:00', '1', '1', b'0', 'ÇAMUR', b'1', 7, 4);
INSERT INTO `pet` VALUES (6, '2021-09-24 03:00:00', '3', '3', b'0', 'PAMUK', b'1', 8, 6);
INSERT INTO `pet` VALUES (7, '2021-09-24 03:00:00', '1', '1', b'0', 'ÇAMUR', b'1', 7, 7);
INSERT INTO `pet` VALUES (16, '2021-09-08 03:00:00', '2', '2', b'0', 'a', b'1', 2, 16);
INSERT INTO `pet` VALUES (20, '2021-09-17 03:00:00', '1', '1', b'0', 'KÖMÜR', b'1', 2, 20);
INSERT INTO `pet` VALUES (21, '2021-09-09 03:00:00', '1', '1', b'0', 'MAVİ', b'1', 2, 21);
INSERT INTO `pet` VALUES (24, '2021-09-18 03:00:00', '213123123', '213123123', b'0', 'asdasdsad', b'1', 2, 24);
INSERT INTO `pet` VALUES (25, '2021-09-03 03:00:00', '2131213123', '123123', b'1', 'asd', b'1', 3, 25);
INSERT INTO `pet` VALUES (26, '2021-08-31 03:00:00', '3213213', '321', b'0', '123asd', b'1', 3, 26);
INSERT INTO `pet` VALUES (28, '2021-09-04 03:00:00', '123', '123', b'0', 'sad', b'1', 3, 28);
INSERT INTO `pet` VALUES (29, '2021-09-02 03:00:00', '3123', '3123', b'0', 'Kömür', b'1', 6, 29);
INSERT INTO `pet` VALUES (30, '2021-09-23 03:00:00', '3', '21321', b'0', 'asd', b'1', 3, 30);
INSERT INTO `pet` VALUES (35, '2021-09-09 03:00:00', '333', '333', b'0', 'Jennifer', b'1', 3, 35);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `product_alis` int NULL DEFAULT NULL,
  `product_kdv` int NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_satis` int NULL DEFAULT NULL,
  `product_statu` bit(1) NULL DEFAULT NULL,
  `product_stok_miktari` int NULL DEFAULT NULL,
  `product_unit` int NULL DEFAULT NULL,
  `category_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `FK1mtsbur82frn64de7balymq9s`(`category_id`) USING BTREE,
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'mert@mail.com', '2021-09-23 22:52:11', 'selen@mail.com', '2021-09-24 19:31:24', 100, 2, 'Pet Yakalık', 150, b'1', 1008, 1, 1);
INSERT INTO `product` VALUES (2, 'mert@mail.com', '2021-09-23 22:52:44', 'selen@mail.com', '2021-09-24 19:49:19', 125, 3, 'Ateş Ölçer', 170, b'0', 725, 1, 1);
INSERT INTO `product` VALUES (3, 'mert@mail.com', '2021-09-23 22:53:38', 'selen@mail.com', '2021-09-24 11:10:17', 200, 2, 'Köpek Maması', 300, b'1', 250, 2, 2);
INSERT INTO `product` VALUES (4, 'mert@mail.com', '2021-09-23 22:54:07', 'mert@mail.com', '2021-09-23 22:54:07', 300, 3, 'Çiğneme Kemiği', 400, b'1', 750, 1, 2);
INSERT INTO `product` VALUES (5, 'mert@mail.com', '2021-09-23 23:08:52', 'mert@mail.com', '2021-09-23 23:24:04', 1000, 1, 'Dedektörler', 2000, b'1', 19, 1, 3);
INSERT INTO `product` VALUES (6, 'mert@mail.com', '2021-09-23 23:09:35', 'mert@mail.com', '2021-09-23 23:09:35', 150, 2, 'Tırnak Bakım Ürünü', 250, b'1', 100, 1, 3);
INSERT INTO `product` VALUES (7, NULL, NULL, 'selen@mail.com', '2021-09-24 11:10:55', 100, 2, 'Kedi maması2', 150, b'1', 195, 1, 1);

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `purchase_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `purchase_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purchase_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purchase_number` int NULL DEFAULT NULL,
  `purchase_total` int NULL DEFAULT NULL,
  `purchase_type` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  `supplier_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`purchase_id`) USING BTREE,
  INDEX `FK3s4jktret4nl7m8yhfc8mfrn5`(`product_id`) USING BTREE,
  INDEX `FK8omm6fki86s9oqk0o9s6w43h5`(`supplier_id`) USING BTREE,
  CONSTRAINT `FK3s4jktret4nl7m8yhfc8mfrn5` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK8omm6fki86s9oqk0o9s6w43h5` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ro_id` int NOT NULL AUTO_INCREMENT,
  `ro_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ro_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `role` VALUES (2, 'ROLE_DOCTOR');
INSERT INTO `role` VALUES (3, 'ROLE_SECRETARY');
INSERT INTO `role` VALUES (4, 'ROLE_BEGINNER');

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale`  (
  `sale_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `sale_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_number` int NULL DEFAULT NULL,
  `sale_total` int NULL DEFAULT NULL,
  `sale_type` int NULL DEFAULT NULL,
  `cu_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`sale_id`) USING BTREE,
  INDEX `FKg7d1nmnivxg6fayyi56vg7uxq`(`cu_id`) USING BTREE,
  INDEX `FKonrcqwf09u6spb6ty6sh11jh5`(`product_id`) USING BTREE,
  CONSTRAINT `FKg7d1nmnivxg6fayyi56vg7uxq` FOREIGN KEY (`cu_id`) REFERENCES `customer` (`cu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKonrcqwf09u6spb6ty6sh11jh5` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale
-- ----------------------------
INSERT INTO `sale` VALUES (1, 'mert@mail.com', '2021-09-23 23:24:05', 'mert@mail.com', '2021-09-23 23:24:05', '4b3028bc-c559-4a87-8ec1-57ff229cb29e', 'Dedektör satıldı.', 1, 21, 1, 1, 5);
INSERT INTO `sale` VALUES (2, 'mert@mail.com', '2021-09-23 23:24:59', 'mert@mail.com', '2021-09-23 23:24:59', 'f138dea7-3e98-42aa-9586-bc72ff062b89', 'Yakalık satıldı.', 3, 5, 3, 1, 1);
INSERT INTO `sale` VALUES (4, 'selen@mail.com', '2021-09-24 11:06:20', 'selen@mail.com', '2021-09-24 11:06:20', '360f115d-6d8e-40f4-be75-fec4893922f4', 'Köpek maması satıldı.', 5, 689, 1, 7, 7);

-- ----------------------------
-- Table structure for schedule_calendar
-- ----------------------------
DROP TABLE IF EXISTS `schedule_calendar`;
CREATE TABLE `schedule_calendar`  (
  `sid` int NOT NULL AUTO_INCREMENT,
  `bg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `border_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `calendar_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drag_bg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `due_date_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_all_day` bit(1) NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `raw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_calendar
-- ----------------------------
INSERT INTO `schedule_calendar` VALUES (1, '#4287f5', '#4287f5', '1', 'time', '#ffffff', '#4287f5', '', 'Thu Sep 23 2021 15:00:00 GMT+0300 (GMT+03:00)', 'be4c14e2-246f-55eb-848f-73a653641409', b'0', 'PC Başı', 'public', 'Thu Sep 23 2021 10:00:00 GMT+0300 (GMT+03:00)', 'Meşgul', 'Sunum');
INSERT INTO `schedule_calendar` VALUES (2, '#4287f5', '#4287f5', '1', 'time', '#ffffff', '#4287f5', '', 'Tue Sep 21 2021 15:00:00 GMT+0300 (GMT+03:00)', 'a84152cd-d1b9-4715-8e7a-699d269a2902', b'0', 'MERT', 'public', 'Tue Sep 21 2021 10:00:00 GMT+0300 (GMT+03:00)', 'Free', 'AYDIN');
INSERT INTO `schedule_calendar` VALUES (3, '#4287f5', '#4287f5', '1', 'time', '#ffffff', '#9e5fff', '', 'Fri Sep 24 2021 20:44:00 GMT+0300 ', 'ef951046-aafe-42e3-a460-7054b45b4609', b'0', 'MERT', 'public', 'Fri Sep 24 2021 19:44:00 GMT+0300 ', 'Free', 'AYDIN');
INSERT INTO `schedule_calendar` VALUES (4, '#4287f5', '#4287f5', '1', 'time', '#ffffff', '#9e5fff', '', 'Fri Sep 24 19:52:06 EET 2021', 'e891c92a-40a1-4ef7-a77f-2ca9162b9b95', b'0', 'ALİ', 'public', 'Fri Sep 24 18:52:06 EET 2021', 'Free', 'AYDIN');
INSERT INTO `schedule_calendar` VALUES (5, '#4287f5', '#4287f5', '1', 'time', '#ffffff', '#9e5fff', '', 'Thu Sep 23 2021 20:00:00 GMT+0300', 'd30cf02b-f6d8-4d8e-ad13-e446bd90e3ce', b'0', 'MERT', 'public', 'Thu Sep 23 2021 20:00:00 GMT+0300', 'Free', 'AYDIN');
INSERT INTO `schedule_calendar` VALUES (8, '#4287f5', '#4287f5', '1', 'time', '#ffffff', '#9e5fff', '', 'Mon Sep 27 2021 17:00:00 GMT+0300', 'b492b1ae-851b-4943-b8d2-6546c14ef20c', b'0', 'ALİ', 'public', 'Mon Sep 27 2021 16:00:00 GMT+0300', 'Free', 'Hakan');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `store_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `store_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_statu` bit(1) NULL DEFAULT NULL,
  `store_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplier_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `supplier_mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `supplier_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `supplier_statu` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `supplier_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`supplier_id`) USING BTREE,
  UNIQUE INDEX `UK_aof5tqg9b2ya6ybg99y9ebbhy`(`supplier_mail`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------

-- ----------------------------
-- Table structure for type_pet
-- ----------------------------
DROP TABLE IF EXISTS `type_pet`;
CREATE TABLE `type_pet`  (
  `ty_id` int NOT NULL AUTO_INCREMENT,
  `ty_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ty_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type_pet
-- ----------------------------
INSERT INTO `type_pet` VALUES (1, 'Egzotik');
INSERT INTO `type_pet` VALUES (2, 'Kanatlı');
INSERT INTO `type_pet` VALUES (3, 'Kedi');
INSERT INTO `type_pet` VALUES (4, 'Köpek');
INSERT INTO `type_pet` VALUES (5, 'Büyükbaş');
INSERT INTO `type_pet` VALUES (6, 'Küçükbaş');
INSERT INTO `type_pet` VALUES (7, 'Tropikal');
INSERT INTO `type_pet` VALUES (8, 'Yabani');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `us_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token_expired` bit(1) NOT NULL,
  `us_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `us_surname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `us_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`us_id`) USING BTREE,
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'anonymousUser', '2021-09-23 22:42:15', 'anonymousUser', '2021-09-23 22:42:15', 'aydin@mail.com', b'1', '$2a$10$werTAtZBt9vPBaHlK0N60uJMBTkLXP79zsERE7msQS3fK3EtddEC.', b'1', 'AYDIN', 'UZUN', '5345907262', 'd2d9edba-53ef-4dff-8a20-68ebf493853a.png');
INSERT INTO `user` VALUES (2, 'anonymousUser', '2021-09-23 22:43:36', 'aydin@mail.com', '2021-10-07 18:54:18', 'mert@mail.com', b'1', '$2a$10$JzHkkauXUM9Uus4na7bGa.q7Ld9B79TYB6kIzkoXALszhLnro2f4u', b'1', 'MERT', 'DUMANLI', '5050103555', '88c74aa8-9972-4795-9e8e-11b25dc1cedc.jpeg');
INSERT INTO `user` VALUES (3, 'anonymousUser', '2021-09-23 22:44:44', 'selen@mail.com', '2021-09-24 11:26:32', 'selen@mail.com', b'1', '$2a$10$OiF.jqDQhWgCWE6/eQJIpO./3RczWhDVY8RzEqqqEbQIszr1Yg4.u', b'1', 'SELEN', 'KÖSOĞLU', '5555555555', '866f0a73-f17e-4e5d-8a1e-b53d8bc8345b.JPG');
INSERT INTO `user` VALUES (4, 'selen@mail.com', '2021-09-24 11:29:23', 'selen@mail.com', '2021-09-24 11:29:23', 'hakanhoca1@mail.com', b'1', '$2a$10$vOSitomdRbywL8TeUiF7tODO8MpWwl35C7kYLo5VQG/MFtVU.Ubh.', b'1', 'Hakan', 'Özer', '5554445555', 'anonim.jpg');
INSERT INTO `user` VALUES (5, 'aydin@mail.com', '2021-10-07 18:54:55', 'aydin@mail.com', '2021-10-07 18:54:55', 'demo@mail.com', b'1', '$2a$10$4KcKu3XPBGnO.UUzdLIlTu5L5PxtUszy.VkUvJ2uVuHtbyvfn2DvS', b'1', 'demo', 'demos', '5523852499', 'anonim.jpg');

-- ----------------------------
-- Table structure for user_follow_in
-- ----------------------------
DROP TABLE IF EXISTS `user_follow_in`;
CREATE TABLE `user_follow_in`  (
  `user_follow_in_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`user_follow_in_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follow_in
-- ----------------------------
INSERT INTO `user_follow_in` VALUES (1, 'mert@mail.com', '2021-09-23 22:45:21', 'mert@mail.com', '2021-09-23 22:45:21');
INSERT INTO `user_follow_in` VALUES (2, 'mert@mail.com', '2021-09-23 23:23:37', 'mert@mail.com', '2021-09-23 23:23:37');
INSERT INTO `user_follow_in` VALUES (3, 'mert@mail.com', '2021-09-23 23:53:48', 'mert@mail.com', '2021-09-23 23:53:48');
INSERT INTO `user_follow_in` VALUES (4, 'mert@mail.com', '2021-09-24 00:52:39', 'mert@mail.com', '2021-09-24 00:52:39');
INSERT INTO `user_follow_in` VALUES (5, 'mert@mail.com', '2021-09-24 10:27:26', 'mert@mail.com', '2021-09-24 10:27:26');
INSERT INTO `user_follow_in` VALUES (6, 'selen@mail.com', '2021-09-24 10:50:10', 'selen@mail.com', '2021-09-24 10:50:10');
INSERT INTO `user_follow_in` VALUES (7, 'selen@mail.com', '2021-09-24 11:20:52', 'selen@mail.com', '2021-09-24 11:20:52');
INSERT INTO `user_follow_in` VALUES (8, 'mert@mail.com', '2021-09-24 11:30:37', 'mert@mail.com', '2021-09-24 11:30:37');
INSERT INTO `user_follow_in` VALUES (9, 'mert@mail.com', '2021-09-24 17:56:33', 'mert@mail.com', '2021-09-24 17:56:33');
INSERT INTO `user_follow_in` VALUES (10, 'selen@mail.com', '2021-09-24 17:57:16', 'selen@mail.com', '2021-09-24 17:57:16');
INSERT INTO `user_follow_in` VALUES (11, 'selen@mail.com', '2021-09-24 19:01:21', 'selen@mail.com', '2021-09-24 19:01:21');
INSERT INTO `user_follow_in` VALUES (12, 'selen@mail.com', '2021-09-24 19:49:42', 'selen@mail.com', '2021-09-24 19:49:42');
INSERT INTO `user_follow_in` VALUES (13, 'mert@mail.com', '2021-09-24 21:37:58', 'mert@mail.com', '2021-09-24 21:37:58');
INSERT INTO `user_follow_in` VALUES (14, 'selen@mail.com', '2021-09-24 21:38:29', 'selen@mail.com', '2021-09-24 21:38:29');
INSERT INTO `user_follow_in` VALUES (15, 'selen@mail.com', '2021-09-24 22:42:39', 'selen@mail.com', '2021-09-24 22:42:39');
INSERT INTO `user_follow_in` VALUES (16, 'selen@mail.com', '2021-09-26 13:18:00', 'selen@mail.com', '2021-09-26 13:18:00');
INSERT INTO `user_follow_in` VALUES (17, 'selen@mail.com', '2021-09-26 14:48:12', 'selen@mail.com', '2021-09-26 14:48:12');
INSERT INTO `user_follow_in` VALUES (18, 'aydin@mail.com', '2021-10-07 18:46:23', 'aydin@mail.com', '2021-10-07 18:46:23');
INSERT INTO `user_follow_in` VALUES (19, 'selen@mail.com', '2021-10-07 18:46:42', 'selen@mail.com', '2021-10-07 18:46:42');
INSERT INTO `user_follow_in` VALUES (20, 'mert@mail.com', '2021-10-07 18:47:01', 'mert@mail.com', '2021-10-07 18:47:01');
INSERT INTO `user_follow_in` VALUES (21, 'demo@mail.com', '2021-10-07 18:55:16', 'demo@mail.com', '2021-10-07 18:55:16');

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles`  (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  INDEX `FKt4v0rrweyk393bdgt107vdx0x`(`role_id`) USING BTREE,
  INDEX `FKgd3iendaoyh04b95ykqise6qh`(`user_id`) USING BTREE,
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`us_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`ro_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES (1, 1);
INSERT INTO `users_roles` VALUES (1, 2);
INSERT INTO `users_roles` VALUES (1, 3);
INSERT INTO `users_roles` VALUES (1, 4);
INSERT INTO `users_roles` VALUES (3, 1);
INSERT INTO `users_roles` VALUES (3, 2);
INSERT INTO `users_roles` VALUES (3, 3);
INSERT INTO `users_roles` VALUES (3, 4);
INSERT INTO `users_roles` VALUES (4, 1);
INSERT INTO `users_roles` VALUES (4, 2);
INSERT INTO `users_roles` VALUES (2, 1);
INSERT INTO `users_roles` VALUES (2, 2);
INSERT INTO `users_roles` VALUES (2, 3);
INSERT INTO `users_roles` VALUES (2, 4);
INSERT INTO `users_roles` VALUES (5, 4);

SET FOREIGN_KEY_CHECKS = 1;
