-- phpMyAdmin SQL Dump
-- version 5.2.1deb3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 19, 2025 at 06:17 AM
-- Server version: 8.0.43-0ubuntu0.24.04.1
-- PHP Version: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crms`
--

-- --------------------------------------------------------

--
-- Table structure for table `audit_logs`
--

CREATE TABLE `audit_logs` (
  `audit_id` bigint NOT NULL,
  `action` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `data` text COLLATE utf8mb4_general_ci,
  `entity` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `entity_id` bigint NOT NULL,
  `timestamp` datetime(6) NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `branches`
--

CREATE TABLE `branches` (
  `branch_id` bigint NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `branches`
--

INSERT INTO `branches` (`branch_id`, `address`, `name`, `phone`) VALUES
(1, 'Bole Road, Addis Ababa', 'Bole Main Branch', '251911987001'),
(2, 'Piassa, Churchill Ave, Addis Ababa', 'Piassa City Center', '251911987002'),
(3, 'CMC Road, Addis Ababa', 'CMC Subcity Branch', '251911987003');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` bigint NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `description`, `name`) VALUES
(1, 'Start your meal with our delicious appetizers.', 'Appetizers'),
(2, 'Fresh and healthy salads.', 'Salads'),
(3, 'Warm and comforting soups.', 'Soups'),
(4, 'Classic Italian pasta dishes.', 'Pasta'),
(5, 'Wood-fired pizzas with various toppings.', 'Pizza'),
(6, 'Juicy burgers with fresh ingredients.', 'Burgers'),
(7, 'Tasty sandwiches for any time of day.', 'Sandwiches'),
(8, 'Fresh seafood dishes.', 'Seafood'),
(9, 'Premium grilled steaks.', 'Steaks'),
(10, 'Chicken dishes cooked to perfection.', 'Chicken'),
(11, 'Delicious vegetarian options.', 'Vegetarian'),
(12, 'Plant-based vegan meals.', 'Vegan'),
(13, 'Asian-inspired rice and noodle dishes.', 'Rice & Noodles'),
(14, 'Sweet treats to finish your meal.', 'Desserts'),
(15, 'Variety of ice cream flavors.', 'Ice Cream'),
(16, 'Soft drinks, juices, and more.', 'Beverages'),
(17, 'Hot and cold coffee and tea.', 'Coffee & Tea'),
(18, 'Hearty breakfast options.', 'Breakfast'),
(19, 'Special meals for kids.', 'Kids Menu'),
(20, 'Chef’s daily specials.', 'Specials');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `loyalty_id` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `notes` text COLLATE utf8mb4_general_ci,
  `phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `created_at`, `loyalty_id`, `name`, `notes`, `phone`, `updated_at`, `user_id`) VALUES
(1, '2025-09-19 09:16:30.089619', NULL, 'Abebe Kebede', NULL, '251911234567', '2025-09-19 09:16:30.089640', NULL),
(2, '2025-09-19 09:16:30.099981', NULL, 'Almaz Bekele', NULL, '251912345678', '2025-09-19 09:16:30.100013', NULL),
(3, '2025-09-19 09:16:30.112613', NULL, 'Mulugeta Tadesse', NULL, '251913456789', '2025-09-19 09:16:30.112642', NULL),
(4, '2025-09-19 09:16:30.121347', NULL, 'Hanna Gebremedhin', NULL, '251914567890', '2025-09-19 09:16:30.121375', NULL),
(5, '2025-09-19 09:16:30.131617', NULL, 'Kebede Alemu', NULL, '251915678901', '2025-09-19 09:16:30.131651', NULL),
(6, '2025-09-19 09:16:30.149234', NULL, 'Selamawit Tesfaye', NULL, '251916789012', '2025-09-19 09:16:30.149274', NULL),
(7, '2025-09-19 09:16:30.165535', NULL, 'Birhanu Mekonnen', NULL, '251917890123', '2025-09-19 09:16:30.165563', NULL),
(8, '2025-09-19 09:16:30.175963', NULL, 'Tigist Fikre', NULL, '251918901234', '2025-09-19 09:16:30.175993', NULL),
(9, '2025-09-19 09:16:30.191427', NULL, 'Yared Asfaw', NULL, '251919012345', '2025-09-19 09:16:30.191456', NULL),
(10, '2025-09-19 09:16:30.200988', NULL, 'Saba Girma', NULL, '251920123456', '2025-09-19 09:16:30.201026', NULL),
(11, '2025-09-19 09:16:30.220785', NULL, 'Fitsum Desta', NULL, '251921234567', '2025-09-19 09:16:30.220826', NULL),
(12, '2025-09-19 09:16:30.230135', NULL, 'Mekdes Solomon', NULL, '251922345678', '2025-09-19 09:16:30.230165', NULL),
(13, '2025-09-19 09:16:30.238501', NULL, 'Samuel Getachew', NULL, '251923456789', '2025-09-19 09:16:30.238545', NULL),
(14, '2025-09-19 09:16:30.246676', NULL, 'Ruth Tsegaye', NULL, '251924567890', '2025-09-19 09:16:30.246702', NULL),
(15, '2025-09-19 09:16:30.254098', NULL, 'Eyob Worku', NULL, '251925678901', '2025-09-19 09:16:30.254120', NULL),
(16, '2025-09-19 09:16:30.262149', NULL, 'Lulit Hailu', NULL, '251926789012', '2025-09-19 09:16:30.262176', NULL),
(17, '2025-09-19 09:16:30.270273', NULL, 'Dawit Mesfin', NULL, '251927890123', '2025-09-19 09:16:30.270307', NULL),
(18, '2025-09-19 09:16:30.278343', NULL, 'Marta Abate', NULL, '251928901234', '2025-09-19 09:16:30.278372', NULL),
(19, '2025-09-19 09:16:30.287600', NULL, 'Solomon Tulu', NULL, '251929012345', '2025-09-19 09:16:30.287631', NULL),
(20, '2025-09-19 09:16:30.301084', NULL, 'Betelhem Mulu', NULL, '251930123456', '2025-09-19 09:16:30.301114', NULL),
(21, '2025-09-19 09:16:30.311300', NULL, 'Nahom Kassahun', NULL, '251931234567', '2025-09-19 09:16:30.311329', NULL),
(22, '2025-09-19 09:16:30.320330', NULL, 'Rahel Tadesse', NULL, '251932345678', '2025-09-19 09:16:30.320376', NULL),
(23, '2025-09-19 09:16:30.330143', NULL, 'Henok Gebre', NULL, '251933456789', '2025-09-19 09:16:30.330192', NULL),
(24, '2025-09-19 09:16:30.337728', NULL, 'Tsedey Alemayehu', NULL, '251934567890', '2025-09-19 09:16:30.337751', NULL),
(25, '2025-09-19 09:16:30.343610', NULL, 'Mikias Zerihun', NULL, '251935678901', '2025-09-19 09:16:30.343629', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `inventory_items`
--

CREATE TABLE `inventory_items` (
  `inventory_item_id` bigint NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `quantity` decimal(38,2) NOT NULL,
  `threshold` decimal(38,2) NOT NULL,
  `unit` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `supplier_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory_items`
--

INSERT INTO `inventory_items` (`inventory_item_id`, `name`, `quantity`, `threshold`, `unit`, `supplier_id`) VALUES
(1, 'Beef Steak', 50.00, 10.00, 'kg', 5),
(2, 'Chicken Breast', 60.00, 12.00, 'kg', 11),
(3, 'Salmon Fillet', 30.00, 8.00, 'kg', 7),
(4, 'Shrimp', 20.00, 5.00, 'kg', 7),
(5, 'Eggs', 100.00, 20.00, 'dozen', 11),
(6, 'Milk', 80.00, 15.00, 'L', 4),
(7, 'Butter', 25.00, 5.00, 'kg', 4),
(8, 'Cheddar Cheese', 18.00, 4.00, 'kg', 4),
(9, 'Mozzarella Cheese', 22.00, 5.00, 'kg', 4),
(10, 'Parmesan Cheese', 10.00, 3.00, 'kg', 4),
(11, 'Tomatoes', 70.00, 15.00, 'kg', 2),
(12, 'Lettuce', 40.00, 8.00, 'kg', 2),
(13, 'Cucumbers', 35.00, 7.00, 'kg', 2),
(14, 'Onions', 60.00, 12.00, 'kg', 2),
(15, 'Potatoes', 90.00, 20.00, 'kg', 2),
(16, 'Carrots', 50.00, 10.00, 'kg', 2),
(17, 'Bell Peppers', 30.00, 6.00, 'kg', 2),
(18, 'Spinach', 20.00, 5.00, 'kg', 2),
(19, 'Broccoli', 18.00, 4.00, 'kg', 2),
(20, 'Mushrooms', 25.00, 6.00, 'kg', 2),
(21, 'Rice', 100.00, 25.00, 'kg', 6),
(22, 'Pasta', 80.00, 20.00, 'kg', 6),
(23, 'Flour', 120.00, 30.00, 'kg', 9),
(24, 'Sugar', 60.00, 15.00, 'kg', 9),
(25, 'Salt', 40.00, 10.00, 'kg', 9),
(26, 'Black Pepper', 8.00, 2.00, 'kg', 8),
(27, 'Oregano', 5.00, 1.00, 'kg', 8),
(28, 'Basil', 5.00, 1.00, 'kg', 8),
(29, 'Cumin', 4.00, 1.00, 'kg', 8),
(30, 'Paprika', 4.00, 1.00, 'kg', 8),
(31, 'Cooking Oil', 60.00, 15.00, 'L', 15),
(32, 'Olive Oil', 25.00, 6.00, 'L', 15),
(33, 'Butter (Baking)', 10.00, 2.00, 'kg', 9),
(34, 'Yeast', 2.00, 1.00, 'kg', 9),
(35, 'Baking Powder', 3.00, 1.00, 'kg', 9),
(36, 'Vanilla Extract', 1.00, 1.00, 'L', 9),
(37, 'Chocolate Chips', 6.00, 2.00, 'kg', 9),
(38, 'Coffee Beans', 20.00, 5.00, 'kg', 12),
(39, 'Tea Leaves', 15.00, 4.00, 'kg', 12),
(40, 'Soft Drinks', 30.00, 8.00, 'case', 3),
(41, 'Juice', 40.00, 10.00, 'L', 3),
(42, 'Beer', 25.00, 6.00, 'case', 3),
(43, 'Wine', 18.00, 5.00, 'bottle', 3),
(44, 'Cleaning Detergent', 20.00, 5.00, 'L', 13),
(45, 'Dish Soap', 15.00, 4.00, 'L', 13),
(46, 'Paper Towels', 40.00, 10.00, 'roll', 13),
(47, 'Napkins', 30.00, 8.00, 'pack', 13),
(48, 'Milk (Sodore)', 30.00, 8.00, 'L', 14),
(49, 'Yogurt', 20.00, 5.00, 'L', 14),
(50, 'Sunflower Oil', 25.00, 6.00, 'L', 15);

-- --------------------------------------------------------

--
-- Table structure for table `menu_items`
--

CREATE TABLE `menu_items` (
  `menu_item_id` bigint NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `is_available` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `price` decimal(38,2) NOT NULL,
  `category_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu_items`
--

INSERT INTO `menu_items` (`menu_item_id`, `description`, `is_available`, `name`, `price`, `category_id`) VALUES
(1, 'Crispy vegetable spring rolls with sweet chili sauce.', b'1', 'Spring Rolls', 500.00, 1),
(2, 'Toasted bread with garlic butter.', b'1', 'Garlic Bread', 450.00, 1),
(3, 'Grilled bread topped with tomato and basil.', b'1', 'Bruschetta', 600.00, 1),
(4, 'Mushrooms stuffed with cheese and herbs.', b'1', 'Stuffed Mushrooms', 700.00, 1),
(5, 'Spicy buffalo chicken wings.', b'1', 'Chicken Wings', 900.00, 1),
(6, 'Fried mozzarella with marinara sauce.', b'1', 'Mozzarella Sticks', 800.00, 1),
(7, 'Romaine lettuce, parmesan, croutons, Caesar dressing.', b'1', 'Caesar Salad', 850.00, 2),
(8, 'Tomatoes, cucumber, olives, feta cheese.', b'1', 'Greek Salad', 950.00, 2),
(9, 'Fresh mozzarella, tomatoes, basil, olive oil.', b'1', 'Caprese Salad', 1000.00, 2),
(10, 'Quinoa, veggies, lemon vinaigrette.', b'1', 'Quinoa Salad', 1100.00, 2),
(11, 'Grilled chicken, greens, honey mustard.', b'1', 'Chicken Salad', 1200.00, 2),
(12, 'Tuna, potatoes, green beans, olives.', b'1', 'Tuna Nicoise', 1300.00, 2),
(13, 'Classic creamy tomato soup.', b'1', 'Tomato Soup', 500.00, 3),
(14, 'Homemade chicken noodle soup.', b'1', 'Chicken Noodle Soup', 700.00, 3),
(15, 'Italian vegetable soup.', b'1', 'Minestrone', 800.00, 3),
(16, 'Creamy pumpkin soup with croutons.', b'1', 'Pumpkin Soup', 900.00, 3),
(17, 'Rich chowder with mixed seafood.', b'1', 'Seafood Chowder', 1500.00, 3),
(18, 'Caramelized onions, beef broth, cheese.', b'1', 'French Onion Soup', 1200.00, 3),
(19, 'Spaghetti with rich meat sauce.', b'1', 'Spaghetti Bolognese', 1400.00, 4),
(20, 'Fettuccine pasta in creamy Alfredo sauce.', b'1', 'Fettuccine Alfredo', 1300.00, 4),
(21, 'Penne pasta in spicy tomato sauce.', b'1', 'Penne Arrabbiata', 1200.00, 4),
(22, 'Layered pasta with meat and cheese.', b'1', 'Lasagna', 1600.00, 4),
(23, 'Pasta tossed in basil pesto.', b'1', 'Pesto Pasta', 1100.00, 4),
(24, 'Linguine with mixed seafood.', b'1', 'Seafood Linguine', 1800.00, 4),
(25, 'Tomato, mozzarella, basil.', b'1', 'Margherita Pizza', 1200.00, 5),
(26, 'Pepperoni, mozzarella, tomato sauce.', b'1', 'Pepperoni Pizza', 1400.00, 5),
(27, 'Mixed vegetables, mozzarella, tomato sauce.', b'1', 'Veggie Pizza', 1300.00, 5),
(28, 'Chicken, BBQ sauce, onions.', b'1', 'BBQ Chicken Pizza', 1500.00, 5),
(29, 'Ham, pineapple, mozzarella.', b'1', 'Hawaiian Pizza', 1350.00, 5),
(30, 'Mozzarella, cheddar, parmesan, blue cheese.', b'1', 'Four Cheese Pizza', 1600.00, 5),
(31, 'Beef patty, lettuce, tomato, cheese.', b'1', 'Classic Burger', 1200.00, 6),
(32, 'Grilled chicken breast, lettuce, mayo.', b'1', 'Chicken Burger', 1100.00, 6),
(33, 'Plant-based patty, lettuce, tomato.', b'1', 'Veggie Burger', 1000.00, 6),
(34, 'Beef patty, double cheese.', b'1', 'Cheese Burger', 1300.00, 6),
(35, 'Beef patty, bacon, cheese.', b'1', 'Bacon Burger', 1400.00, 6),
(36, 'Beef patty, jalapenos, spicy sauce.', b'1', 'Spicy Burger', 1250.00, 6),
(37, 'Turkey, bacon, lettuce, tomato, mayo.', b'1', 'Club Sandwich', 1100.00, 7),
(38, 'Melted cheese on toasted bread.', b'1', 'Grilled Cheese', 700.00, 7),
(39, 'Bacon, lettuce, tomato, mayo.', b'1', 'BLT Sandwich', 900.00, 7),
(40, 'Grilled chicken, pesto, mozzarella.', b'1', 'Chicken Panini', 1200.00, 7),
(41, 'Egg salad, lettuce, whole wheat bread.', b'1', 'Egg Salad Sandwich', 800.00, 7),
(42, 'Tuna salad, cheddar, grilled bread.', b'1', 'Tuna Melt', 950.00, 7),
(43, 'Salmon fillet with lemon butter.', b'1', 'Grilled Salmon', 1800.00, 8),
(44, 'Battered fish, fries, tartar sauce.', b'1', 'Fish & Chips', 1400.00, 8),
(45, 'Shrimp sautéed in garlic butter.', b'1', 'Shrimp Scampi', 1600.00, 8),
(46, 'Rice with mixed seafood.', b'1', 'Seafood Paella', 2000.00, 8),
(47, 'Fried calamari rings, aioli.', b'1', 'Calamari', 1200.00, 8),
(48, 'Crab meat patties, remoulade.', b'1', 'Crab Cakes', 1700.00, 8),
(49, 'Grilled ribeye steak, choice of side.', b'1', 'Ribeye Steak', 2000.00, 9),
(50, 'Juicy sirloin, grilled to order.', b'1', 'Sirloin Steak', 1800.00, 9),
(51, 'Tender filet mignon, red wine sauce.', b'1', 'Filet Mignon', 2000.00, 9),
(52, 'Classic T-bone, grilled.', b'1', 'T-Bone Steak', 1900.00, 9),
(53, 'Steak with fries.', b'1', 'Steak Frites', 1700.00, 9),
(54, 'Steak with peppercorn sauce.', b'1', 'Peppercorn Steak', 1850.00, 9),
(55, 'Marinated grilled chicken breast.', b'1', 'Grilled Chicken Breast', 1200.00, 10),
(56, 'Breaded chicken, marinara, mozzarella.', b'1', 'Chicken Parmesan', 1400.00, 10),
(57, 'Chicken with house BBQ sauce.', b'1', 'BBQ Chicken', 1300.00, 10),
(58, 'Spicy chicken curry, rice.', b'1', 'Chicken Curry', 1100.00, 10),
(59, 'Grilled chicken, Indian spices.', b'1', 'Chicken Tikka', 1500.00, 10),
(60, 'Chicken with lemon and herbs.', b'1', 'Lemon Herb Chicken', 1250.00, 10),
(61, 'Bell peppers stuffed with rice and veggies.', b'1', 'Stuffed Peppers', 1000.00, 11),
(62, 'Mixed vegetables in soy-ginger sauce.', b'1', 'Vegetable Stir Fry', 900.00, 11),
(63, 'Breaded eggplant, marinara, cheese.', b'1', 'Eggplant Parmesan', 1100.00, 11),
(64, 'Creamy risotto with mushrooms.', b'1', 'Mushroom Risotto', 1200.00, 11),
(65, 'Grilled paneer, Indian spices.', b'1', 'Paneer Tikka', 1300.00, 11),
(66, 'Layered pasta with vegetables.', b'1', 'Vegetable Lasagna', 1150.00, 11),
(67, 'Plant-based patty, vegan cheese, lettuce.', b'1', 'Vegan Burger', 1100.00, 12),
(68, 'Chickpeas, vegetables, coconut curry.', b'1', 'Vegan Curry', 1200.00, 12),
(69, 'Tofu, mixed vegetables, teriyaki sauce.', b'1', 'Tofu Stir Fry', 1000.00, 12),
(70, 'Pasta with vegan tomato sauce.', b'1', 'Vegan Pasta', 950.00, 12),
(71, 'Vegan cheese, veggies, tomato sauce.', b'1', 'Vegan Pizza', 1300.00, 12),
(72, 'Grilled veggies, hummus, wrap.', b'1', 'Vegan Wrap', 900.00, 12),
(73, 'Rice, chicken, vegetables, soy sauce.', b'1', 'Chicken Fried Rice', 1100.00, 13),
(74, 'Rice noodles, tofu, peanuts, tamarind sauce.', b'1', 'Pad Thai', 1200.00, 13),
(75, 'Egg noodles, beef, vegetables.', b'1', 'Beef Lo Mein', 1300.00, 13),
(76, 'Spiced rice with vegetables.', b'1', 'Vegetable Biryani', 1000.00, 13),
(77, 'Noodles with shrimp and veggies.', b'1', 'Shrimp Noodles', 1400.00, 13),
(78, 'Rice, eggs, spring onions.', b'1', 'Egg Fried Rice', 900.00, 13),
(79, 'Classic New York cheesecake.', b'1', 'Cheesecake', 800.00, 14),
(80, 'Warm chocolate cake with molten center.', b'1', 'Chocolate Lava Cake', 900.00, 14),
(81, 'Homemade apple pie, cinnamon.', b'1', 'Apple Pie', 700.00, 14),
(82, 'Coffee-flavored Italian dessert.', b'1', 'Tiramisu', 950.00, 14),
(83, 'Brownie with ice cream and chocolate sauce.', b'1', 'Brownie Sundae', 1000.00, 14),
(84, 'Seasonal fruits, custard, pastry.', b'1', 'Fruit Tart', 850.00, 14),
(85, 'Classic vanilla ice cream scoop.', b'1', 'Vanilla Ice Cream', 400.00, 15),
(86, 'Rich chocolate ice cream.', b'1', 'Chocolate Ice Cream', 400.00, 15),
(87, 'Fresh strawberry ice cream.', b'1', 'Strawberry Ice Cream', 400.00, 15),
(88, 'Refreshing mango sorbet.', b'1', 'Mango Sorbet', 500.00, 15),
(89, 'Ice cream with cookie chunks.', b'1', 'Cookies & Cream', 600.00, 15),
(90, 'Italian pistachio gelato.', b'1', 'Pistachio Gelato', 700.00, 15),
(91, 'Chilled soft drink.', b'1', 'Coca-Cola', 400.00, 16),
(92, 'Freshly squeezed orange juice.', b'1', 'Orange Juice', 500.00, 16),
(93, 'Homemade lemonade.', b'1', 'Lemonade', 500.00, 16),
(94, 'Chilled black tea with lemon.', b'1', 'Iced Tea', 450.00, 16),
(95, 'Carbonated mineral water.', b'1', 'Sparkling Water', 400.00, 16),
(96, 'Fresh apple juice.', b'1', 'Apple Juice', 500.00, 16),
(97, 'Strong Italian espresso.', b'1', 'Espresso', 500.00, 17),
(98, 'Espresso with steamed milk foam.', b'1', 'Cappuccino', 700.00, 17),
(99, 'Hot green tea.', b'1', 'Green Tea', 600.00, 17),
(100, 'Espresso with steamed milk.', b'1', 'Latte', 800.00, 17),
(101, 'Classic black tea.', b'1', 'Black Tea', 500.00, 17),
(102, 'Chilled coffee with milk.', b'1', 'Iced Coffee', 700.00, 17),
(103, 'Fluffy pancakes with syrup.', b'1', 'Pancake Stack', 900.00, 18),
(104, 'Three-egg omelette, choice of fillings.', b'1', 'Omelette', 1000.00, 18),
(105, 'Brioche French toast, powdered sugar.', b'1', 'French Toast', 950.00, 18),
(106, 'Poached eggs, ham, hollandaise.', b'1', 'Eggs Benedict', 1200.00, 18),
(107, 'Eggs, cheese, sausage, wrap.', b'1', 'Breakfast Burrito', 1100.00, 18),
(108, 'Granola, yogurt, fresh fruit.', b'1', 'Granola Bowl', 800.00, 18),
(109, 'Crispy chicken nuggets, fries.', b'1', 'Kids Chicken Nuggets', 700.00, 19),
(110, 'Creamy macaroni and cheese.', b'1', 'Kids Mac & Cheese', 600.00, 19),
(111, 'Small beef burger, fries.', b'1', 'Kids Mini Burger', 700.00, 19),
(112, 'Mini cheese pizza.', b'1', 'Kids Pizza', 600.00, 19),
(113, 'Fish fingers, fries.', b'1', 'Kids Fish Fingers', 700.00, 19),
(114, 'Pasta with tomato sauce.', b'1', 'Kids Pasta', 600.00, 19),
(115, 'Daily special pasta creation.', b'1', 'Chef’s Special Pasta', 1500.00, 20),
(116, 'Fresh catch of the day.', b'1', 'Market Fish', 2000.00, 20),
(117, 'Salad with seasonal ingredients.', b'1', 'Seasonal Salad', 1200.00, 20),
(118, 'Chef’s steak special.', b'1', 'Special Steak', 2000.00, 20),
(119, 'Specialty burger of the day.', b'1', 'Gourmet Burger', 1600.00, 20),
(120, 'Chef’s daily dessert creation.', b'1', 'Dessert of the Day', 1000.00, 20);

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `notification_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `data_json` text COLLATE utf8mb4_general_ci,
  `is_read` bit(1) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `severity` enum('CRITICAL','HIGH','LOW','MEDIUM') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` enum('ERROR','INFO','SUCCESS','SYSTEM','WARNING') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` bigint NOT NULL,
  `branch_id` bigint NOT NULL,
  `staff_id` bigint NOT NULL,
  `status` enum('CANCELLED','COMPLETED','CREATED','IN_PROGRESS') COLLATE utf8mb4_general_ci NOT NULL,
  `table_id` bigint NOT NULL,
  `total_amount` decimal(38,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `order_item_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` decimal(38,2) NOT NULL,
  `menu_item_id` bigint NOT NULL,
  `order_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `permission_id` bigint NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `permissions`
--

INSERT INTO `permissions` (`permission_id`, `description`, `name`) VALUES
(1, 'Branch read', 'BRANCH_READ'),
(2, 'Branch create', 'BRANCH_CREATE'),
(3, 'Branch update', 'BRANCH_UPDATE'),
(4, 'Branch delete', 'BRANCH_DELETE'),
(5, 'Inventory read', 'INVENTORY_READ'),
(6, 'Inventory create', 'INVENTORY_CREATE'),
(7, 'Inventory update', 'INVENTORY_UPDATE'),
(8, 'Inventory delete', 'INVENTORY_DELETE'),
(9, 'Menu read', 'MENU_READ'),
(10, 'Menu create', 'MENU_CREATE'),
(11, 'Menu update', 'MENU_UPDATE'),
(12, 'Menu delete', 'MENU_DELETE'),
(13, 'Order read', 'ORDER_READ'),
(14, 'Order create', 'ORDER_CREATE'),
(15, 'Order update', 'ORDER_UPDATE'),
(16, 'Order delete', 'ORDER_DELETE'),
(17, 'Reservation read', 'RESERVATION_READ'),
(18, 'Reservation create', 'RESERVATION_CREATE'),
(19, 'Reservation update', 'RESERVATION_UPDATE'),
(20, 'Reservation delete', 'RESERVATION_DELETE'),
(21, 'Staff read', 'STAFF_READ'),
(22, 'Staff create', 'STAFF_CREATE'),
(23, 'Staff update', 'STAFF_UPDATE'),
(24, 'Staff delete', 'STAFF_DELETE'),
(25, 'User read', 'USER_READ'),
(26, 'User create', 'USER_CREATE'),
(27, 'User update', 'USER_UPDATE'),
(28, 'User delete', 'USER_DELETE'),
(29, 'Role read', 'ROLE_READ'),
(30, 'Role create', 'ROLE_CREATE'),
(31, 'Role update', 'ROLE_UPDATE'),
(32, 'Role delete', 'ROLE_DELETE'),
(33, 'Permission read', 'PERMISSION_READ'),
(34, 'Permission create', 'PERMISSION_CREATE'),
(35, 'Permission update', 'PERMISSION_UPDATE'),
(36, 'Permission delete', 'PERMISSION_DELETE'),
(37, 'Waiter_request read', 'WAITER_REQUEST_READ'),
(38, 'Waiter_request create', 'WAITER_REQUEST_CREATE'),
(39, 'Waiter_request update', 'WAITER_REQUEST_UPDATE'),
(40, 'Waiter_request delete', 'WAITER_REQUEST_DELETE'),
(41, 'Category read', 'CATEGORY_READ'),
(42, 'Category create', 'CATEGORY_CREATE'),
(43, 'Category update', 'CATEGORY_UPDATE'),
(44, 'Category delete', 'CATEGORY_DELETE'),
(45, 'Supplier read', 'SUPPLIER_READ'),
(46, 'Supplier create', 'SUPPLIER_CREATE'),
(47, 'Supplier update', 'SUPPLIER_UPDATE'),
(48, 'Supplier delete', 'SUPPLIER_DELETE'),
(49, 'Shift read', 'SHIFT_READ'),
(50, 'Shift create', 'SHIFT_CREATE'),
(51, 'Shift update', 'SHIFT_UPDATE'),
(52, 'Shift delete', 'SHIFT_DELETE'),
(53, 'Audit_log read', 'AUDIT_LOG_READ'),
(54, 'Audit_log create', 'AUDIT_LOG_CREATE'),
(55, 'Audit_log update', 'AUDIT_LOG_UPDATE'),
(56, 'Audit_log delete', 'AUDIT_LOG_DELETE'),
(57, 'Table read', 'TABLE_READ'),
(58, 'Table create', 'TABLE_CREATE'),
(59, 'Table update', 'TABLE_UPDATE'),
(60, 'Table delete', 'TABLE_DELETE'),
(61, 'Customer read', 'CUSTOMER_READ'),
(62, 'Customer create', 'CUSTOMER_CREATE'),
(63, 'Customer update', 'CUSTOMER_UPDATE'),
(64, 'Customer delete', 'CUSTOMER_DELETE');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` bigint NOT NULL,
  `branch_id` bigint NOT NULL,
  `customer_id` bigint NOT NULL,
  `party_size` int NOT NULL,
  `reservation_time` datetime(6) NOT NULL,
  `status` enum('CANCELLED','COMPLETED','CONFIRMED','PENDING') COLLATE utf8mb4_general_ci NOT NULL,
  `table_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_table`
--

CREATE TABLE `restaurant_table` (
  `table_id` bigint NOT NULL,
  `capacity` int NOT NULL,
  `location` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` enum('AVAILABLE','OCCUPIED','OUT_OF_SERVICE','RESERVED') COLLATE utf8mb4_general_ci NOT NULL,
  `table_number` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `branch_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `restaurant_table`
--

INSERT INTO `restaurant_table` (`table_id`, `capacity`, `location`, `status`, `table_number`, `branch_id`) VALUES
(1, 4, 'Room 1', 'AVAILABLE', '1', 1),
(2, 2, 'Room 1', 'AVAILABLE', '2', 1),
(3, 6, 'Room 2', 'AVAILABLE', '3', 1),
(4, 4, 'Room 2', 'AVAILABLE', '4', 1),
(5, 8, 'Room 3', 'AVAILABLE', '5', 1),
(6, 2, 'Room 3', 'AVAILABLE', '6', 1),
(7, 4, 'Room 4', 'AVAILABLE', '7', 1),
(8, 6, 'Room 4', 'AVAILABLE', '8', 1),
(9, 2, 'Room 5', 'AVAILABLE', '9', 1),
(10, 4, 'Room 5', 'AVAILABLE', '10', 1),
(11, 4, 'Room 6', 'AVAILABLE', '11', 1),
(12, 6, 'Room 6', 'AVAILABLE', '12', 1),
(13, 2, 'Room 7', 'AVAILABLE', '13', 1),
(14, 4, 'Room 7', 'AVAILABLE', '14', 1),
(15, 8, 'Room 8', 'AVAILABLE', '15', 1),
(16, 2, 'Room 8', 'AVAILABLE', '16', 1),
(17, 4, 'Room 9', 'AVAILABLE', '17', 1),
(18, 6, 'Room 9', 'AVAILABLE', '18', 1),
(19, 2, 'Room 10', 'AVAILABLE', '19', 1),
(20, 4, 'Room 10', 'AVAILABLE', '20', 1),
(21, 4, 'Room 1', 'AVAILABLE', '1', 2),
(22, 2, 'Room 1', 'AVAILABLE', '2', 2),
(23, 6, 'Room 2', 'AVAILABLE', '3', 2),
(24, 4, 'Room 2', 'AVAILABLE', '4', 2),
(25, 8, 'Room 3', 'AVAILABLE', '5', 2),
(26, 2, 'Room 3', 'AVAILABLE', '6', 2),
(27, 4, 'Room 4', 'AVAILABLE', '7', 2),
(28, 6, 'Room 4', 'AVAILABLE', '8', 2),
(29, 2, 'Room 5', 'AVAILABLE', '9', 2),
(30, 4, 'Room 5', 'AVAILABLE', '10', 2),
(31, 4, 'Room 6', 'AVAILABLE', '11', 2),
(32, 6, 'Room 6', 'AVAILABLE', '12', 2),
(33, 2, 'Room 7', 'AVAILABLE', '13', 2),
(34, 4, 'Room 7', 'AVAILABLE', '14', 2),
(35, 8, 'Room 8', 'AVAILABLE', '15', 2),
(36, 2, 'Room 8', 'AVAILABLE', '16', 2),
(37, 4, 'Room 9', 'AVAILABLE', '17', 2),
(38, 6, 'Room 9', 'AVAILABLE', '18', 2),
(39, 2, 'Room 10', 'AVAILABLE', '19', 2),
(40, 4, 'Room 10', 'AVAILABLE', '20', 2),
(41, 4, 'Room 1', 'AVAILABLE', '1', 3),
(42, 2, 'Room 1', 'AVAILABLE', '2', 3),
(43, 6, 'Room 2', 'AVAILABLE', '3', 3),
(44, 4, 'Room 2', 'AVAILABLE', '4', 3),
(45, 8, 'Room 3', 'AVAILABLE', '5', 3),
(46, 2, 'Room 3', 'AVAILABLE', '6', 3),
(47, 4, 'Room 4', 'AVAILABLE', '7', 3),
(48, 6, 'Room 4', 'AVAILABLE', '8', 3),
(49, 2, 'Room 5', 'AVAILABLE', '9', 3),
(50, 4, 'Room 5', 'AVAILABLE', '10', 3),
(51, 4, 'Room 6', 'AVAILABLE', '11', 3),
(52, 6, 'Room 6', 'AVAILABLE', '12', 3),
(53, 2, 'Room 7', 'AVAILABLE', '13', 3),
(54, 4, 'Room 7', 'AVAILABLE', '14', 3),
(55, 8, 'Room 8', 'AVAILABLE', '15', 3),
(56, 2, 'Room 8', 'AVAILABLE', '16', 3),
(57, 4, 'Room 9', 'AVAILABLE', '17', 3),
(58, 6, 'Room 9', 'AVAILABLE', '18', 3),
(59, 2, 'Room 10', 'AVAILABLE', '19', 3),
(60, 4, 'Room 10', 'AVAILABLE', '20', 3),
(61, 4, 'Patio', 'AVAILABLE', '21', 1),
(62, 2, 'Patio', 'AVAILABLE', '22', 1),
(63, 6, 'Patio', 'AVAILABLE', '23', 1),
(64, 4, 'Patio', 'AVAILABLE', '24', 1),
(65, 8, 'Patio', 'AVAILABLE', '25', 1),
(66, 2, 'Patio', 'AVAILABLE', '26', 1),
(67, 4, 'Patio', 'AVAILABLE', '27', 1),
(68, 6, 'Patio', 'AVAILABLE', '28', 1),
(69, 2, 'Patio', 'AVAILABLE', '29', 1),
(70, 4, 'Patio', 'AVAILABLE', '30', 1),
(71, 4, 'Patio', 'AVAILABLE', '21', 2),
(72, 2, 'Patio', 'AVAILABLE', '22', 2),
(73, 6, 'Patio', 'AVAILABLE', '23', 2),
(74, 4, 'Patio', 'AVAILABLE', '24', 2),
(75, 8, 'Patio', 'AVAILABLE', '25', 2),
(76, 2, 'Patio', 'AVAILABLE', '26', 2),
(77, 4, 'Patio', 'AVAILABLE', '27', 2),
(78, 6, 'Patio', 'AVAILABLE', '28', 2),
(79, 2, 'Patio', 'AVAILABLE', '29', 2),
(80, 4, 'Patio', 'AVAILABLE', '30', 2);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` bigint NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `description`, `name`) VALUES
(1, 'administrative privileges', 'Admin'),
(2, 'Manager role', 'Manager'),
(3, 'Chef role', 'Chef'),
(4, 'Waiter role', 'Waiter'),
(5, 'Customer role', 'Customer');

-- --------------------------------------------------------

--
-- Table structure for table `role_permissions`
--

CREATE TABLE `role_permissions` (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role_permissions`
--

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(1, 3),
(2, 3),
(1, 4),
(1, 5),
(2, 5),
(3, 5),
(1, 6),
(2, 6),
(1, 7),
(2, 7),
(3, 7),
(1, 8),
(1, 9),
(2, 9),
(3, 9),
(4, 9),
(5, 9),
(1, 10),
(2, 10),
(1, 11),
(2, 11),
(1, 12),
(2, 12),
(1, 13),
(2, 13),
(3, 13),
(4, 13),
(5, 13),
(1, 14),
(4, 14),
(1, 15),
(3, 15),
(4, 15),
(1, 16),
(1, 17),
(2, 17),
(4, 17),
(5, 17),
(1, 18),
(4, 18),
(5, 18),
(1, 19),
(4, 19),
(5, 19),
(1, 20),
(4, 20),
(5, 20),
(1, 21),
(2, 21),
(1, 22),
(2, 22),
(1, 23),
(2, 23),
(1, 24),
(2, 24),
(1, 25),
(1, 26),
(1, 27),
(1, 28),
(1, 29),
(1, 30),
(1, 31),
(1, 32),
(1, 33),
(1, 34),
(1, 35),
(1, 36),
(1, 37),
(4, 37),
(1, 38),
(5, 38),
(1, 39),
(4, 39),
(1, 40),
(1, 41),
(1, 42),
(1, 43),
(1, 44),
(1, 45),
(1, 46),
(1, 47),
(1, 48),
(1, 49),
(2, 49),
(1, 50),
(2, 50),
(1, 51),
(2, 51),
(1, 52),
(2, 52),
(1, 53),
(1, 54),
(1, 55),
(1, 56),
(1, 57),
(1, 58),
(1, 59),
(1, 60),
(1, 61),
(1, 62),
(1, 63),
(1, 64);

-- --------------------------------------------------------

--
-- Table structure for table `shifts`
--

CREATE TABLE `shifts` (
  `shift_id` bigint NOT NULL,
  `end_time` datetime(6) NOT NULL,
  `start_time` datetime(6) NOT NULL,
  `branch_id` bigint NOT NULL,
  `staff_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staff_id` bigint NOT NULL,
  `contact` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `contact`, `user_id`, `role_id`) VALUES
(1, '2519262585806', 2, 2),
(2, '2519262585807', 3, 3),
(3, '2519262585808', 4, 4),
(4, '2519262585809', 5, 4),
(5, '2519262585810', 6, 4),
(6, '2519262585811', 7, 4),
(7, '2519262585812', 8, 4),
(8, '2519262585813', 9, 2),
(9, '2519262585814', 10, 3),
(10, '2519262585815', 11, 4),
(11, '2519262585816', 12, 4),
(12, '2519262585817', 13, 4),
(13, '2519262585818', 14, 4),
(14, '2519262585819', 15, 4),
(15, '2519262585820', 16, 2),
(16, '2519262585821', 17, 3),
(17, '2519262585822', 18, 4),
(18, '2519262585823', 19, 4),
(19, '2519262585824', 20, 4),
(20, '2519262585825', 21, 4),
(21, '2519262585826', 22, 4),
(22, '2519262585827', 23, 4);

-- --------------------------------------------------------

--
-- Table structure for table `stock_movements`
--

CREATE TABLE `stock_movements` (
  `movement_id` bigint NOT NULL,
  `created_by` bigint NOT NULL,
  `quantity_change` decimal(38,2) NOT NULL,
  `reason` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `inventory_item_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stock_movements`
--

INSERT INTO `stock_movements` (`movement_id`, `created_by`, `quantity_change`, `reason`, `inventory_item_id`) VALUES
(1, 1, 20.00, 'Restock', 1),
(2, 2, 30.00, 'Restock', 2),
(3, 3, 10.00, 'Restock', 3),
(4, 4, 8.00, 'Restock', 4),
(5, 5, 40.00, 'Restock', 5),
(6, 6, 25.00, 'Restock', 6),
(7, 7, 10.00, 'Restock', 7),
(8, 8, 7.00, 'Restock', 8),
(9, 9, 8.00, 'Restock', 9),
(10, 10, 5.00, 'Restock', 10),
(11, 11, 30.00, 'Restock', 11),
(12, 12, 15.00, 'Restock', 12),
(13, 13, 12.00, 'Restock', 13),
(14, 14, 20.00, 'Restock', 14),
(15, 15, 40.00, 'Restock', 15),
(16, 1, 20.00, 'Restock', 16),
(17, 2, 10.00, 'Restock', 17),
(18, 3, 8.00, 'Restock', 18),
(19, 4, 7.00, 'Restock', 19),
(20, 5, 10.00, 'Restock', 20),
(21, 6, 50.00, 'Restock', 21),
(22, 7, 40.00, 'Restock', 22),
(23, 8, 60.00, 'Restock', 23),
(24, 9, 30.00, 'Restock', 24),
(25, 10, 20.00, 'Restock', 25),
(26, 11, 5.00, 'Restock', 26),
(27, 12, 3.00, 'Restock', 27),
(28, 13, 2.00, 'Restock', 28),
(29, 14, 4.00, 'Restock', 29),
(30, 15, 6.00, 'Restock', 30),
(31, 1, 10.00, 'Restock', 31),
(32, 2, 8.00, 'Restock', 32),
(33, 3, 7.00, 'Restock', 33),
(34, 4, 5.00, 'Restock', 34),
(35, 5, 3.00, 'Restock', 35),
(36, 6, 2.00, 'Restock', 36),
(37, 7, 4.00, 'Restock', 37),
(38, 8, 6.00, 'Restock', 38),
(39, 9, 8.00, 'Restock', 39),
(40, 10, 10.00, 'Restock', 40),
(41, 11, 12.00, 'Restock', 41),
(42, 12, 14.00, 'Restock', 42),
(43, 13, 16.00, 'Restock', 43),
(44, 14, 18.00, 'Restock', 44),
(45, 15, 20.00, 'Restock', 45),
(46, 1, 22.00, 'Restock', 46),
(47, 2, 24.00, 'Restock', 47),
(48, 3, 26.00, 'Restock', 48),
(49, 4, 28.00, 'Restock', 49),
(50, 5, 30.00, 'Restock', 50),
(51, 6, -5.00, 'Usage', 1),
(52, 7, -8.00, 'Usage', 2),
(53, 8, -3.00, 'Usage', 3),
(54, 9, -2.00, 'Usage', 4),
(55, 10, -10.00, 'Usage', 5),
(56, 11, -7.00, 'Usage', 6),
(57, 12, -2.00, 'Usage', 7),
(58, 13, -1.00, 'Usage', 8),
(59, 14, -2.00, 'Usage', 9),
(60, 15, -3.00, 'Usage', 10),
(61, 1, -4.00, 'Usage', 11),
(62, 2, -2.00, 'Usage', 12),
(63, 3, -1.00, 'Usage', 13),
(64, 4, -2.00, 'Usage', 14),
(65, 5, -5.00, 'Usage', 15),
(66, 6, -3.00, 'Usage', 16),
(67, 7, -2.00, 'Usage', 17),
(68, 8, -1.00, 'Usage', 18),
(69, 9, -2.00, 'Usage', 19),
(70, 10, -3.00, 'Usage', 20),
(71, 11, -6.00, 'Usage', 21),
(72, 12, -4.00, 'Usage', 22),
(73, 13, -8.00, 'Usage', 23),
(74, 14, -3.00, 'Usage', 24),
(75, 15, -2.00, 'Usage', 25),
(76, 1, -1.00, 'Usage', 26),
(77, 2, -1.00, 'Usage', 27),
(78, 3, -1.00, 'Usage', 28),
(79, 4, -1.00, 'Usage', 29),
(80, 5, -1.00, 'Usage', 30),
(81, 6, -2.00, 'Usage', 31),
(82, 7, -2.00, 'Usage', 32),
(83, 8, -1.00, 'Usage', 33),
(84, 9, -1.00, 'Usage', 34),
(85, 10, -1.00, 'Usage', 35),
(86, 11, -1.00, 'Usage', 36),
(87, 12, -1.00, 'Usage', 37),
(88, 13, -1.00, 'Usage', 38),
(89, 14, -1.00, 'Usage', 39),
(90, 15, -1.00, 'Usage', 40),
(91, 1, -2.00, 'Usage', 41),
(92, 2, -2.00, 'Usage', 42),
(93, 3, -1.00, 'Usage', 43),
(94, 4, -1.00, 'Usage', 44),
(95, 5, -1.00, 'Usage', 45),
(96, 6, -1.00, 'Usage', 46),
(97, 7, -1.00, 'Usage', 47),
(98, 8, -1.00, 'Usage', 48),
(99, 9, -1.00, 'Usage', 49),
(100, 10, -1.00, 'Usage', 50),
(101, 11, 15.00, 'Restock', 1),
(102, 12, 18.00, 'Restock', 2),
(103, 13, 12.00, 'Restock', 3),
(104, 14, 10.00, 'Restock', 4),
(105, 15, 25.00, 'Restock', 5),
(106, 1, 20.00, 'Restock', 6),
(107, 2, 8.00, 'Restock', 7),
(108, 3, 6.00, 'Restock', 8),
(109, 4, 7.00, 'Restock', 9),
(110, 5, 9.00, 'Restock', 10),
(111, 6, 22.00, 'Restock', 11),
(112, 7, 14.00, 'Restock', 12),
(113, 8, 11.00, 'Restock', 13),
(114, 9, 13.00, 'Restock', 14),
(115, 10, 19.00, 'Restock', 15),
(116, 11, 17.00, 'Restock', 16),
(117, 12, 9.00, 'Restock', 17),
(118, 13, 5.00, 'Restock', 18),
(119, 14, 6.00, 'Restock', 19),
(120, 15, 8.00, 'Restock', 20),
(121, 6, 10.00, 'Restock', 1),
(122, 7, 12.00, 'Restock', 2),
(123, 8, 8.00, 'Restock', 3),
(124, 9, 7.00, 'Restock', 4),
(125, 10, 15.00, 'Restock', 5),
(126, 11, 13.00, 'Restock', 6),
(127, 12, 6.00, 'Restock', 7),
(128, 13, 5.00, 'Restock', 8),
(129, 14, 6.00, 'Restock', 9),
(130, 15, 8.00, 'Restock', 10),
(131, 1, 16.00, 'Restock', 11),
(132, 2, 10.00, 'Restock', 12),
(133, 3, 7.00, 'Restock', 13),
(134, 4, 9.00, 'Restock', 14),
(135, 5, 13.00, 'Restock', 15),
(136, 6, 11.00, 'Restock', 16),
(137, 7, 5.00, 'Restock', 17),
(138, 8, 3.00, 'Restock', 18),
(139, 9, 4.00, 'Restock', 19),
(140, 10, 6.00, 'Restock', 20),
(141, 11, 20.00, 'Restock', 21),
(142, 12, 18.00, 'Restock', 22),
(143, 13, 25.00, 'Restock', 23),
(144, 14, 12.00, 'Restock', 24),
(145, 15, 8.00, 'Restock', 25),
(146, 1, 2.00, 'Restock', 26),
(147, 2, 1.00, 'Restock', 27),
(148, 3, 1.00, 'Restock', 28),
(149, 4, 2.00, 'Restock', 29),
(150, 5, 3.00, 'Restock', 30),
(151, 6, 4.00, 'Restock', 31),
(152, 7, 3.00, 'Restock', 32),
(153, 8, 2.00, 'Restock', 33),
(154, 9, 1.00, 'Restock', 34),
(155, 10, 1.00, 'Restock', 35),
(156, 11, 1.00, 'Restock', 36),
(157, 12, 1.00, 'Restock', 37),
(158, 13, 1.00, 'Restock', 38),
(159, 14, 1.00, 'Restock', 39),
(160, 15, 1.00, 'Restock', 40),
(161, 1, -3.00, 'Usage', 1),
(162, 2, -4.00, 'Usage', 2),
(163, 3, -1.00, 'Usage', 3),
(164, 4, -1.00, 'Usage', 4),
(165, 5, -5.00, 'Usage', 5),
(166, 6, -3.00, 'Usage', 6),
(167, 7, -1.00, 'Usage', 7),
(168, 8, -1.00, 'Usage', 8),
(169, 9, -1.00, 'Usage', 9),
(170, 10, -1.00, 'Usage', 10),
(171, 11, -2.00, 'Usage', 11),
(172, 12, -1.00, 'Usage', 12),
(173, 13, -1.00, 'Usage', 13),
(174, 14, -1.00, 'Usage', 14),
(175, 15, -2.00, 'Usage', 15),
(176, 1, -1.00, 'Usage', 16),
(177, 2, -1.00, 'Usage', 17),
(178, 3, -1.00, 'Usage', 18),
(179, 4, -1.00, 'Usage', 19),
(180, 5, -1.00, 'Usage', 20),
(181, 6, -2.00, 'Usage', 21),
(182, 7, -2.00, 'Usage', 22),
(183, 8, -3.00, 'Usage', 23),
(184, 9, -1.00, 'Usage', 24),
(185, 10, -1.00, 'Usage', 25),
(186, 11, -1.00, 'Usage', 26),
(187, 12, -1.00, 'Usage', 27),
(188, 13, -1.00, 'Usage', 28),
(189, 14, -1.00, 'Usage', 29),
(190, 15, -1.00, 'Usage', 30),
(191, 1, -1.00, 'Usage', 31),
(192, 2, -1.00, 'Usage', 32),
(193, 3, -1.00, 'Usage', 33),
(194, 4, -1.00, 'Usage', 34),
(195, 5, -1.00, 'Usage', 35),
(196, 6, -1.00, 'Usage', 36),
(197, 7, -1.00, 'Usage', 37),
(198, 8, -1.00, 'Usage', 38),
(199, 9, -1.00, 'Usage', 39),
(200, 10, -1.00, 'Usage', 40),
(201, 11, -1.00, 'Usage', 41),
(202, 12, -1.00, 'Usage', 42),
(203, 13, -1.00, 'Usage', 43),
(204, 14, -1.00, 'Usage', 44),
(205, 15, -1.00, 'Usage', 45),
(206, 1, -1.00, 'Usage', 46),
(207, 2, -1.00, 'Usage', 47),
(208, 3, -1.00, 'Usage', 48),
(209, 4, -1.00, 'Usage', 49),
(210, 5, -1.00, 'Usage', 50),
(211, 6, 5.00, 'Restock', 1),
(212, 7, 6.00, 'Restock', 2),
(213, 8, 4.00, 'Restock', 3),
(214, 9, 3.00, 'Restock', 4),
(215, 10, 7.00, 'Restock', 5),
(216, 11, 6.00, 'Restock', 6),
(217, 12, 2.00, 'Restock', 7),
(218, 13, 1.00, 'Restock', 8),
(219, 14, 2.00, 'Restock', 9),
(220, 15, 3.00, 'Restock', 10);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `supplier_id` bigint NOT NULL,
  `contact` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `terms` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`supplier_id`, `contact`, `name`, `phone`, `terms`) VALUES
(1, 'Mulugeta Tadesse', 'Addis Food Distributors', '251911112223', 'Net 30'),
(2, 'Selamawit Tesfaye', 'Ethio Fresh Produce', '251912223334', 'Net 15'),
(3, 'Birhanu Mekonnen', 'Blue Nile Beverages', '251913334445', 'COD'),
(4, 'Tigist Fikre', 'Habesha Dairy Supply', '251914445556', 'Net 30'),
(5, 'Yared Asfaw', 'Lalibela Meat Packers', '251915556667', 'Net 45'),
(6, 'Fitsum Desta', 'Awash Grain Traders', '251916667778', 'Net 30'),
(7, 'Mekdes Solomon', 'Sheger Seafood', '251917778889', 'COD'),
(8, 'Samuel Getachew', 'Lucy Spices & Herbs', '251918889990', 'Net 15'),
(9, 'Ruth Tsegaye', 'Axum Bakery Supplies', '251919990001', 'Net 30'),
(10, 'Eyob Worku', 'Dire Dawa Veggies', '251920001112', 'Net 30'),
(11, 'Lulit Hailu', 'Gondar Poultry Farms', '251921112223', 'Net 45'),
(12, 'Dawit Mesfin', 'Walia Beverage Import', '251922223334', 'Net 15'),
(13, 'Marta Abate', 'Entoto Cleaning Products', '251923334445', 'COD'),
(14, 'Solomon Tulu', 'Sodore Dairy', '251924445556', 'Net 30'),
(15, 'Betelhem Mulu', 'Bahir Dar Oils', '251925556667', 'Net 30');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` bigint NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `email`, `password_hash`, `username`) VALUES
(1, 'admin@gmail.com', '$2a$10$gDOPITBMtRObcNMnZvePe.poUOSUplhxmO.RZwAlHKh1hnB5bjkTW', 'admin'),
(2, 'manager92@b1.com', '$2a$10$OFQXB0yzKVaTqWG8LWZ//ObyplPCmrVciNfJBOXzfqBi713CmL5Ca', 'manager92b1'),
(3, 'chef52@b1.com', '$2a$10$JbWjk35qXHroMmDKUFRn7eKnH6kCVfaNQim.piygQn5G9kshq.ioy', 'chef52b1'),
(4, 'waiter44@b1.com', '$2a$10$wTuSAHO1HCw0.OnivfhANOVIwx5bFjNF4ezHw8TsyLCghLy0w2Gly', 'waiter44b1'),
(5, 'waiter26@b1.com', '$2a$10$ptDLVJieaWh28KrEQDtdd..YHiRIiHF/jalXl.wG5rA8VdVwyXS9C', 'waiter26b1'),
(6, 'waiter13@b1.com', '$2a$10$EX4jItAxESd8pzU2jA3DC.3lhQ6MM.hrNXB19Txob6MxgGm6i/G2K', 'waiter13b1'),
(7, 'waiter43@b1.com', '$2a$10$zNgEuUHuYEpa7K2ah5oL7.fj3SRZk/ljfkJbfAPzmQhKSR44bVsq6', 'waiter43b1'),
(8, 'waiter25@b1.com', '$2a$10$Zkuw9sxHjHuqQeyg4TnrQ.wvkSdZdkoF8x0xfH67Rc5EuUGkDO08.', 'waiter25b1'),
(9, 'manager52@b2.com', '$2a$10$a1hOgi0hqLjB4qYdDJrDMeo1k6I7Q3CpYuY6RCBPd/7W1FxBvKiM2', 'manager52b2'),
(10, 'chef36@b2.com', '$2a$10$PcdHkUYJEl35tFX8wri.7.LwVkDPWhW24ar4PbDJ.jHMfptiK.MO.', 'chef36b2'),
(11, 'waiter61@b2.com', '$2a$10$bwG4EZ/2w4RWoDEDPGL0OuQ3ucV0DJmFAGV1kNXF/3JpD6WzWRJ6K', 'waiter61b2'),
(12, 'waiter36@b2.com', '$2a$10$NNEnE//GYYS3XQ28VeTFCeCwcuH4Q9oFBaP2p/CTR2LES6NXl9Cma', 'waiter36b2'),
(13, 'waiter56@b2.com', '$2a$10$UEOYdahRUJRRZhTFKLKTOeha2VI6cbtHruvzNL52TDLLjbolCYltO', 'waiter56b2'),
(14, 'waiter37@b2.com', '$2a$10$1EUmoO/SmDXPtsBIpeJhYe72m7qYByo.67BvuyQ52rDrdbqBXf9gC', 'waiter37b2'),
(15, 'waiter74@b2.com', '$2a$10$/T.UO1yYN2vfRlJ62fdDye3DOZ7PBJCjDatVsywe0WsoqxHa4PqYW', 'waiter74b2'),
(16, 'manager43@b3.com', '$2a$10$XIMNpRxyc.b0FMAEWQr1dOxinIPM3pe/wiPYWuxostO/xPjlcF1Ky', 'manager43b3'),
(17, 'chef90@b3.com', '$2a$10$SaBIsZZDpo/jEDcNvioq0uBIzbgO8.xZFV5Qku5yDf5KhOQb2Xq1.', 'chef90b3'),
(18, 'waiter10@b3.com', '$2a$10$aznHUdl1Ni0BIDkxukC6KOtoZaynptMm71ExYjM6gizvhCIlf4Ahq', 'waiter10b3'),
(19, 'waiter44@b3.com', '$2a$10$xgTUtshyAI96.k3ALFzZq.jSvxpZ6QNP7YD71xJqLQUVbk/MzgtYK', 'waiter44b3'),
(20, 'waiter38@b3.com', '$2a$10$b19/79NNtSd1oP1B7RjRDOrUZldlBmmyw/WHdjbxJxVYSTJkjwD1a', 'waiter38b3'),
(21, 'waiter50@b3.com', '$2a$10$4h2DYDZvQLQxvCzhUBwGbupK5cSjITI5C.kT7Hmd/AYwB0IaLqws6', 'waiter50b3'),
(22, 'waiter76@b3.com', '$2a$10$tTIBcyJ/hpubhSwHHEF6u.YiiiTVVj3LFc/3wrbchBS.AQd0/Kfx6', 'waiter76b3'),
(23, 'waiter80@b3.com', '$2a$10$4GZM1g4a71fcsGGawTjoqOmSoaNpVA71kg9on0h7UnngCs1hNXYoO', 'waiter80b3');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(9, 2),
(16, 2),
(3, 3),
(10, 3),
(17, 3),
(4, 4),
(5, 4),
(6, 4),
(7, 4),
(8, 4),
(11, 4),
(12, 4),
(13, 4),
(14, 4),
(15, 4),
(18, 4),
(19, 4),
(20, 4),
(21, 4),
(22, 4),
(23, 4);

-- --------------------------------------------------------

--
-- Table structure for table `waiter_requests`
--

CREATE TABLE `waiter_requests` (
  `request_id` bigint NOT NULL,
  `branch_id` bigint NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `handled_by` bigint DEFAULT NULL,
  `request_type` enum('CALL_WAITER','CLEAN_TABLE','ORDER_ASSISTANCE','OTHER','REQUEST_BILL') COLLATE utf8mb4_general_ci NOT NULL,
  `status` enum('ACKNOWLEDGED','NEW','RESOLVED') COLLATE utf8mb4_general_ci NOT NULL,
  `table_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `audit_logs`
--
ALTER TABLE `audit_logs`
  ADD PRIMARY KEY (`audit_id`);

--
-- Indexes for table `branches`
--
ALTER TABLE `branches`
  ADD PRIMARY KEY (`branch_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `UKeuat1oase6eqv195jvb71a93s` (`user_id`);

--
-- Indexes for table `inventory_items`
--
ALTER TABLE `inventory_items`
  ADD PRIMARY KEY (`inventory_item_id`),
  ADD KEY `FKhc7q0chmfralakw27k36ds0c1` (`supplier_id`);

--
-- Indexes for table `menu_items`
--
ALTER TABLE `menu_items`
  ADD PRIMARY KEY (`menu_item_id`),
  ADD KEY `FK5bg0vbmql5ggu48n7d5pwgjg3` (`category_id`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`notification_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`order_item_id`),
  ADD KEY `FKdtfg1f49yr5yye2fpl2xid2xo` (`menu_item_id`),
  ADD KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`permission_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`);

--
-- Indexes for table `restaurant_table`
--
ALTER TABLE `restaurant_table`
  ADD PRIMARY KEY (`table_id`),
  ADD KEY `FK1isvk9bki9cju6mccgobq5khw` (`branch_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `role_permissions`
--
ALTER TABLE `role_permissions`
  ADD PRIMARY KEY (`role_id`,`permission_id`),
  ADD KEY `FKegdk29eiy7mdtefy5c7eirr6e` (`permission_id`);

--
-- Indexes for table `shifts`
--
ALTER TABLE `shifts`
  ADD PRIMARY KEY (`shift_id`),
  ADD KEY `FKll9m6u08c6wyjkbcpsb475vrd` (`branch_id`),
  ADD KEY `FKq5smvqe0qkm69n1e9b3ys4qnr` (`staff_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_id`),
  ADD KEY `FKm671w66r18c9w9a38i9ysxyh9` (`role_id`);

--
-- Indexes for table `stock_movements`
--
ALTER TABLE `stock_movements`
  ADD PRIMARY KEY (`movement_id`),
  ADD KEY `FKsf8xqne4s20910sgk48jvyx4u` (`inventory_item_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supplier_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- Indexes for table `waiter_requests`
--
ALTER TABLE `waiter_requests`
  ADD PRIMARY KEY (`request_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `audit_logs`
--
ALTER TABLE `audit_logs`
  MODIFY `audit_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `branches`
--
ALTER TABLE `branches`
  MODIFY `branch_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `inventory_items`
--
ALTER TABLE `inventory_items`
  MODIFY `inventory_item_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `menu_items`
--
ALTER TABLE `menu_items`
  MODIFY `menu_item_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `notification_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `order_item_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permissions`
--
ALTER TABLE `permissions`
  MODIFY `permission_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `restaurant_table`
--
ALTER TABLE `restaurant_table`
  MODIFY `table_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `shifts`
--
ALTER TABLE `shifts`
  MODIFY `shift_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staff_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `stock_movements`
--
ALTER TABLE `stock_movements`
  MODIFY `movement_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=221;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supplier_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `waiter_requests`
--
ALTER TABLE `waiter_requests`
  MODIFY `request_id` bigint NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `fk_customer_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `inventory_items`
--
ALTER TABLE `inventory_items`
  ADD CONSTRAINT `FKhc7q0chmfralakw27k36ds0c1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`supplier_id`);

--
-- Constraints for table `menu_items`
--
ALTER TABLE `menu_items`
  ADD CONSTRAINT `FK5bg0vbmql5ggu48n7d5pwgjg3` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `FKdtfg1f49yr5yye2fpl2xid2xo` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_items` (`menu_item_id`);

--
-- Constraints for table `restaurant_table`
--
ALTER TABLE `restaurant_table`
  ADD CONSTRAINT `FK1isvk9bki9cju6mccgobq5khw` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`branch_id`);

--
-- Constraints for table `role_permissions`
--
ALTER TABLE `role_permissions`
  ADD CONSTRAINT `FKegdk29eiy7mdtefy5c7eirr6e` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`permission_id`),
  ADD CONSTRAINT `FKn5fotdgk8d1xvo8nav9uv3muc` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);

--
-- Constraints for table `shifts`
--
ALTER TABLE `shifts`
  ADD CONSTRAINT `FKll9m6u08c6wyjkbcpsb475vrd` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`branch_id`),
  ADD CONSTRAINT `FKq5smvqe0qkm69n1e9b3ys4qnr` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`);

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `FKm671w66r18c9w9a38i9ysxyh9` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);

--
-- Constraints for table `stock_movements`
--
ALTER TABLE `stock_movements`
  ADD CONSTRAINT `FKsf8xqne4s20910sgk48jvyx4u` FOREIGN KEY (`inventory_item_id`) REFERENCES `inventory_items` (`inventory_item_id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
