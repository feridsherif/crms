package com.hilcoe.crms.config;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.Staff;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.entity.Category;
import com.hilcoe.crms.entity.MenuItem;
import com.hilcoe.crms.entity.Customer;
import com.hilcoe.crms.entity.Supplier;
import com.hilcoe.crms.entity.InventoryItem;
import com.hilcoe.crms.entity.StockMovement;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.entity.RestaurantTable;
import com.hilcoe.crms.repository.BranchRepository;
import com.hilcoe.crms.repository.CategoryRepository;
import com.hilcoe.crms.repository.MenuItemRepository;
import com.hilcoe.crms.repository.PermissionRepository;
import com.hilcoe.crms.repository.RoleRepository;
import com.hilcoe.crms.repository.StaffRepository;
import com.hilcoe.crms.repository.SupplierRepository;
import com.hilcoe.crms.repository.UserRepository;
import com.hilcoe.crms.repository.InventoryItemRepository;
import com.hilcoe.crms.repository.StockMovementRepository;
import com.hilcoe.crms.repository.CustomerRepository;
import com.hilcoe.crms.repository.RestaurantTableRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataSeeder {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private InventoryItemRepository inventoryItemRepository;
	@Autowired
	private StockMovementRepository stockMovementRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private RestaurantTableRepository restaurantTableRepository;

	@PostConstruct
	public void seed() {
		// 1. Seed Permissions
		String[] entities = { "BRANCH", "INVENTORY", "MENU", "ORDER", "RESERVATION", "STAFF", "USER", "ROLE",
				"PERMISSION", "WAITER_REQUEST", "CATEGORY", "SUPPLIER", "SHIFT", "AUDIT_LOG", "TABLE", "CUSTOMER" };
		String[] actions = { "READ", "CREATE", "UPDATE", "DELETE" };
		for (String entity : entities) {
			for (String action : actions) {
				String name = entity + "_" + action;
				String description = entity.charAt(0) + entity.substring(1).toLowerCase() + " " + action.toLowerCase();
				if (!permissionRepository.existsByName(name)) {
					Permission permission = new Permission();
					permission.setName(name);
					permission.setDescription(description);
					permissionRepository.save(permission);
				}
			}
		}

		// 2. Seed Admin Role
		Role adminRole = roleRepository.findByName("Admin");
		if (adminRole == null) {
			adminRole = new Role();
			adminRole.setName("Admin");
			adminRole.setDescription("administrative privileges");
			adminRole = roleRepository.save(adminRole);
		}
		// Assign all permissions to Admin role
		Set<Permission> allPermissions = new HashSet<>(permissionRepository.findAll());
		adminRole.setPermissions(allPermissions);
		roleRepository.save(adminRole);

		// 3. Seed Admin User
		Optional<User> adminUserOpt = userRepository.findByUsername("admin");
		User adminUser;
		if (adminUserOpt.isPresent()) {
			adminUser = adminUserOpt.get();
		} else {
			adminUser = new User();
			adminUser.setUsername("admin");
			adminUser.setEmail("admin@gmail.com");
			adminUser.setPasswordHash(passwordEncoder.encode("12345678"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			adminUser.setRoles(roles);
			adminUser = userRepository.save(adminUser);
		}

		// 4. Seed Manager, Chef, and Waiter Roles if not exist
		String[] staffRoles = { "Manager", "Chef", "Waiter", "Customer" };
		for (String roleName : staffRoles) {
			Role role = roleRepository.findByName(roleName);
			if (role == null) {
				role = new Role();
				role.setName(roleName);
				role.setDescription(roleName + " role");
				roleRepository.save(role);
			}
		}

		// 4b. Assign permissions to roles
		// Manager Permissions
		Role managerRole = roleRepository.findByName("Manager");
		if (managerRole != null) {
			Set<Permission> managerPerms = new HashSet<>();
			String[] managerPermNames = {
				"BRANCH_READ", "BRANCH_CREATE", "BRANCH_UPDATE",
				"INVENTORY_READ", "INVENTORY_CREATE", "INVENTORY_UPDATE",
				"MENU_READ", "MENU_CREATE", "MENU_UPDATE", "MENU_DELETE",
				"ORDER_READ", "RESERVATION_READ",
				"STAFF_READ", "STAFF_CREATE", "STAFF_UPDATE", "STAFF_DELETE",
				"SHIFT_READ", "SHIFT_CREATE", "SHIFT_UPDATE", "SHIFT_DELETE"
			};
			for (String permName : managerPermNames) {
				permissionRepository.findByName(permName).ifPresent(managerPerms::add);
			}
			managerRole.setPermissions(managerPerms);
			roleRepository.save(managerRole);
		}

		// Waiter Permissions
		Role waiterRole = roleRepository.findByName("Waiter");
		if (waiterRole != null) {
			Set<Permission> waiterPerms = new HashSet<>();
			String[] waiterPermNames = {
				"ORDER_CREATE", "ORDER_READ", "ORDER_UPDATE",
				"RESERVATION_CREATE", "RESERVATION_READ", "RESERVATION_UPDATE", "RESERVATION_DELETE",
				"MENU_READ", "WAITER_REQUEST_READ", "WAITER_REQUEST_UPDATE"
			};
			for (String permName : waiterPermNames) {
				permissionRepository.findByName(permName).ifPresent(waiterPerms::add);
			}
			waiterRole.setPermissions(waiterPerms);
			roleRepository.save(waiterRole);
		}

		// Chef Permissions
		Role chefRole = roleRepository.findByName("Chef");
		if (chefRole != null) {
			Set<Permission> chefPerms = new HashSet<>();
			String[] chefPermNames = {
				"INVENTORY_READ", "INVENTORY_UPDATE",
				"ORDER_READ", "ORDER_UPDATE",
				"MENU_READ"
			};
			for (String permName : chefPermNames) {
				permissionRepository.findByName(permName).ifPresent(chefPerms::add);
			}
			chefRole.setPermissions(chefPerms);
			roleRepository.save(chefRole);
		}

		// Customer Permissions
		Role customerRole = roleRepository.findByName("Customer");
		if (customerRole != null) {
			Set<Permission> customerPerms = new HashSet<>();
			String[] customerPermNames = {
				"RESERVATION_CREATE", "RESERVATION_READ", "RESERVATION_UPDATE", "RESERVATION_DELETE",
				"ORDER_READ", "MENU_READ", "WAITER_REQUEST_CREATE"
			};
			for (String permName : customerPermNames) {
				permissionRepository.findByName(permName).ifPresent(customerPerms::add);
			}
			customerRole.setPermissions(customerPerms);
			roleRepository.save(customerRole);
		}

		// 5. Seed users for each branch
		String[] branches = { "b1", "b2", "b3" };
		int[] waiterCounts = { 5, 6, 7 };
		long phoneSeed = System.currentTimeMillis() % 1000000000L;
		for (int i = 0; i < branches.length; i++) {
			String branch = branches[i];
			int branchNum = i + 1;

			// Manager
			String managerSuffix = String.format("%02d", (int) (Math.random() * 90 + 10));
			String managerUsername = "manager" + managerSuffix + "b" + branchNum;
			String managerEmail = "manager" + managerSuffix + "@" + branch + ".com";
			if (!userRepository.findByUsername(managerUsername).isPresent()) {
				User manager = new User();
				manager.setUsername(managerUsername);
				manager.setEmail(managerEmail);
				manager.setPasswordHash(passwordEncoder.encode("12345678"));
				Set<Role> managerRoles = new HashSet<>();
				managerRoles.add(roleRepository.findByName("Manager"));
				manager.setRoles(managerRoles);
				userRepository.save(manager);
				// Create Staff for manager
				Staff managerStaff = new Staff();
				managerStaff.setUserId(manager.getUserId());
				managerStaff.setRole(roleRepository.findByName("Manager"));
				managerStaff.setContact("2519" + String.format("%09d", phoneSeed++));
				staffRepository.save(managerStaff);
			}

			// Chef
			String chefSuffix = String.format("%02d", (int) (Math.random() * 90 + 10));
			String chefUsername = "chef" + chefSuffix + "b" + branchNum;
			String chefEmail = "chef" + chefSuffix + "@" + branch + ".com";
			if (!userRepository.findByUsername(chefUsername).isPresent()) {
				User chef = new User();
				chef.setUsername(chefUsername);
				chef.setEmail(chefEmail);
				chef.setPasswordHash(passwordEncoder.encode("12345678"));
				Set<Role> chefRoles = new HashSet<>();
				chefRoles.add(roleRepository.findByName("Chef"));
				chef.setRoles(chefRoles);
				userRepository.save(chef);
				// Create Staff for chef
				Staff chefStaff = new Staff();
				chefStaff.setUserId(chef.getUserId());
				chefStaff.setRole(roleRepository.findByName("Chef"));
				chefStaff.setContact("2519" + String.format("%09d", phoneSeed++));
				staffRepository.save(chefStaff);
			}

			// Waiters
			for (int w = 1; w <= waiterCounts[i]; w++) {
				String waiterSuffix = String.format("%02d", (int) (Math.random() * 90 + 10));
				String waiterUsername = "waiter" + waiterSuffix + "b" + branchNum;
				String waiterEmail = "waiter" + waiterSuffix + "@" + branch + ".com";
				if (!userRepository.findByUsername(waiterUsername).isPresent()) {
					User waiter = new User();
					waiter.setUsername(waiterUsername);
					waiter.setEmail(waiterEmail);
					waiter.setPasswordHash(passwordEncoder.encode("12345678"));
					Set<Role> waiterRoles = new HashSet<>();
					waiterRoles.add(roleRepository.findByName("Waiter"));
					waiter.setRoles(waiterRoles);
					userRepository.save(waiter);
					// Create Staff for waiter
					Staff waiterStaff = new Staff();
					waiterStaff.setUserId(waiter.getUserId());
					waiterStaff.setRole(roleRepository.findByName("Waiter"));
					waiterStaff.setContact("2519" + String.format("%09d", phoneSeed++));
					staffRepository.save(waiterStaff);
				}
			}
		}

		// 6. Seed Categories
		Object[][] categories = new Object[][]{
			{1L, "Appetizers", "Start your meal with our delicious appetizers."},
			{2L, "Salads", "Fresh and healthy salads."},
			{3L, "Soups", "Warm and comforting soups."},
			{4L, "Pasta", "Classic Italian pasta dishes."},
			{5L, "Pizza", "Wood-fired pizzas with various toppings."},
			{6L, "Burgers", "Juicy burgers with fresh ingredients."},
			{7L, "Sandwiches", "Tasty sandwiches for any time of day."},
			{8L, "Seafood", "Fresh seafood dishes."},
			{9L, "Steaks", "Premium grilled steaks."},
			{10L, "Chicken", "Chicken dishes cooked to perfection."},
			{11L, "Vegetarian", "Delicious vegetarian options."},
			{12L, "Vegan", "Plant-based vegan meals."},
			{13L, "Rice & Noodles", "Asian-inspired rice and noodle dishes."},
			{14L, "Desserts", "Sweet treats to finish your meal."},
			{15L, "Ice Cream", "Variety of ice cream flavors."},
			{16L, "Beverages", "Soft drinks, juices, and more."},
			{17L, "Coffee & Tea", "Hot and cold coffee and tea."},
			{18L, "Breakfast", "Hearty breakfast options."},
			{19L, "Kids Menu", "Special meals for kids."},
			{20L, "Specials", "Chef’s daily specials."}
		};
		for (Object[] cat : categories) {
			Long id = (Long)cat[0];
			String name = (String)cat[1];
			String desc = (String)cat[2];
			if (!categoryRepository.existsById(id)) {
				Category c = new Category();
				c.setName(name);
				c.setDescription(desc);
				categoryRepository.save(c);
			} else {
				// Optionally update existing category's name/description
				Category existing = categoryRepository.findById(id).orElse(null);
				if (existing != null) {
					existing.setName(name);
					existing.setDescription(desc);
					categoryRepository.save(existing);
				}
			}
		}

		// 7. Seed Menu Items (full data)
		Object[][] menuItems = new Object[][]{
			{1L, 1L, "Spring Rolls", "Crispy vegetable spring rolls with sweet chili sauce.", 500, true},
			{2L, 1L, "Garlic Bread", "Toasted bread with garlic butter.", 450, true},
			{3L, 1L, "Bruschetta", "Grilled bread topped with tomato and basil.", 600, true},
			{4L, 1L, "Stuffed Mushrooms", "Mushrooms stuffed with cheese and herbs.", 700, true},
			{5L, 1L, "Chicken Wings", "Spicy buffalo chicken wings.", 900, true},
			{6L, 1L, "Mozzarella Sticks", "Fried mozzarella with marinara sauce.", 800, true},
			{7L, 2L, "Caesar Salad", "Romaine lettuce, parmesan, croutons, Caesar dressing.", 850, true},
			{8L, 2L, "Greek Salad", "Tomatoes, cucumber, olives, feta cheese.", 950, true},
			{9L, 2L, "Caprese Salad", "Fresh mozzarella, tomatoes, basil, olive oil.", 1000, true},
			{10L, 2L, "Quinoa Salad", "Quinoa, veggies, lemon vinaigrette.", 1100, true},
			{11L, 2L, "Chicken Salad", "Grilled chicken, greens, honey mustard.", 1200, true},
			{12L, 2L, "Tuna Nicoise", "Tuna, potatoes, green beans, olives.", 1300, true},
			{13L, 3L, "Tomato Soup", "Classic creamy tomato soup.", 500, true},
			{14L, 3L, "Chicken Noodle Soup", "Homemade chicken noodle soup.", 700, true},
			{15L, 3L, "Minestrone", "Italian vegetable soup.", 800, true},
			{16L, 3L, "Pumpkin Soup", "Creamy pumpkin soup with croutons.", 900, true},
			{17L, 3L, "Seafood Chowder", "Rich chowder with mixed seafood.", 1500, true},
			{18L, 3L, "French Onion Soup", "Caramelized onions, beef broth, cheese.", 1200, true},
			{19L, 4L, "Spaghetti Bolognese", "Spaghetti with rich meat sauce.", 1400, true},
			{20L, 4L, "Fettuccine Alfredo", "Fettuccine pasta in creamy Alfredo sauce.", 1300, true},
			{21L, 4L, "Penne Arrabbiata", "Penne pasta in spicy tomato sauce.", 1200, true},
			{22L, 4L, "Lasagna", "Layered pasta with meat and cheese.", 1600, true},
			{23L, 4L, "Pesto Pasta", "Pasta tossed in basil pesto.", 1100, true},
			{24L, 4L, "Seafood Linguine", "Linguine with mixed seafood.", 1800, true},
			{25L, 5L, "Margherita Pizza", "Tomato, mozzarella, basil.", 1200, true},
			{26L, 5L, "Pepperoni Pizza", "Pepperoni, mozzarella, tomato sauce.", 1400, true},
			{27L, 5L, "Veggie Pizza", "Mixed vegetables, mozzarella, tomato sauce.", 1300, true},
			{28L, 5L, "BBQ Chicken Pizza", "Chicken, BBQ sauce, onions.", 1500, true},
			{29L, 5L, "Hawaiian Pizza", "Ham, pineapple, mozzarella.", 1350, true},
			{30L, 5L, "Four Cheese Pizza", "Mozzarella, cheddar, parmesan, blue cheese.", 1600, true},
			{31L, 6L, "Classic Burger", "Beef patty, lettuce, tomato, cheese.", 1200, true},
			{32L, 6L, "Chicken Burger", "Grilled chicken breast, lettuce, mayo.", 1100, true},
			{33L, 6L, "Veggie Burger", "Plant-based patty, lettuce, tomato.", 1000, true},
			{34L, 6L, "Cheese Burger", "Beef patty, double cheese.", 1300, true},
			{35L, 6L, "Bacon Burger", "Beef patty, bacon, cheese.", 1400, true},
			{36L, 6L, "Spicy Burger", "Beef patty, jalapenos, spicy sauce.", 1250, true},
			{37L, 7L, "Club Sandwich", "Turkey, bacon, lettuce, tomato, mayo.", 1100, true},
			{38L, 7L, "Grilled Cheese", "Melted cheese on toasted bread.", 700, true},
			{39L, 7L, "BLT Sandwich", "Bacon, lettuce, tomato, mayo.", 900, true},
			{40L, 7L, "Chicken Panini", "Grilled chicken, pesto, mozzarella.", 1200, true},
			{41L, 7L, "Egg Salad Sandwich", "Egg salad, lettuce, whole wheat bread.", 800, true},
			{42L, 7L, "Tuna Melt", "Tuna salad, cheddar, grilled bread.", 950, true},
			{43L, 8L, "Grilled Salmon", "Salmon fillet with lemon butter.", 1800, true},
			{44L, 8L, "Fish & Chips", "Battered fish, fries, tartar sauce.", 1400, true},
			{45L, 8L, "Shrimp Scampi", "Shrimp sautéed in garlic butter.", 1600, true},
			{46L, 8L, "Seafood Paella", "Rice with mixed seafood.", 2000, true},
			{47L, 8L, "Calamari", "Fried calamari rings, aioli.", 1200, true},
			{48L, 8L, "Crab Cakes", "Crab meat patties, remoulade.", 1700, true},
			{49L, 9L, "Ribeye Steak", "Grilled ribeye steak, choice of side.", 2000, true},
			{50L, 9L, "Sirloin Steak", "Juicy sirloin, grilled to order.", 1800, true},
			{51L, 9L, "Filet Mignon", "Tender filet mignon, red wine sauce.", 2000, true},
			{52L, 9L, "T-Bone Steak", "Classic T-bone, grilled.", 1900, true},
			{53L, 9L, "Steak Frites", "Steak with fries.", 1700, true},
			{54L, 9L, "Peppercorn Steak", "Steak with peppercorn sauce.", 1850, true},
			{55L, 10L, "Grilled Chicken Breast", "Marinated grilled chicken breast.", 1200, true},
			{56L, 10L, "Chicken Parmesan", "Breaded chicken, marinara, mozzarella.", 1400, true},
			{57L, 10L, "BBQ Chicken", "Chicken with house BBQ sauce.", 1300, true},
			{58L, 10L, "Chicken Curry", "Spicy chicken curry, rice.", 1100, true},
			{59L, 10L, "Chicken Tikka", "Grilled chicken, Indian spices.", 1500, true},
			{60L, 10L, "Lemon Herb Chicken", "Chicken with lemon and herbs.", 1250, true},
			{61L, 11L, "Stuffed Peppers", "Bell peppers stuffed with rice and veggies.", 1000, true},
			{62L, 11L, "Vegetable Stir Fry", "Mixed vegetables in soy-ginger sauce.", 900, true},
			{63L, 11L, "Eggplant Parmesan", "Breaded eggplant, marinara, cheese.", 1100, true},
			{64L, 11L, "Mushroom Risotto", "Creamy risotto with mushrooms.", 1200, true},
			{65L, 11L, "Paneer Tikka", "Grilled paneer, Indian spices.", 1300, true},
			{66L, 11L, "Vegetable Lasagna", "Layered pasta with vegetables.", 1150, true},
			{67L, 12L, "Vegan Burger", "Plant-based patty, vegan cheese, lettuce.", 1100, true},
			{68L, 12L, "Vegan Curry", "Chickpeas, vegetables, coconut curry.", 1200, true},
			{69L, 12L, "Tofu Stir Fry", "Tofu, mixed vegetables, teriyaki sauce.", 1000, true},
			{70L, 12L, "Vegan Pasta", "Pasta with vegan tomato sauce.", 950, true},
			{71L, 12L, "Vegan Pizza", "Vegan cheese, veggies, tomato sauce.", 1300, true},
			{72L, 12L, "Vegan Wrap", "Grilled veggies, hummus, wrap.", 900, true},
			{73L, 13L, "Chicken Fried Rice", "Rice, chicken, vegetables, soy sauce.", 1100, true},
			{74L, 13L, "Pad Thai", "Rice noodles, tofu, peanuts, tamarind sauce.", 1200, true},
			{75L, 13L, "Beef Lo Mein", "Egg noodles, beef, vegetables.", 1300, true},
			{76L, 13L, "Vegetable Biryani", "Spiced rice with vegetables.", 1000, true},
			{77L, 13L, "Shrimp Noodles", "Noodles with shrimp and veggies.", 1400, true},
			{78L, 13L, "Egg Fried Rice", "Rice, eggs, spring onions.", 900, true},
			{79L, 14L, "Cheesecake", "Classic New York cheesecake.", 800, true},
			{80L, 14L, "Chocolate Lava Cake", "Warm chocolate cake with molten center.", 900, true},
			{81L, 14L, "Apple Pie", "Homemade apple pie, cinnamon.", 700, true},
			{82L, 14L, "Tiramisu", "Coffee-flavored Italian dessert.", 950, true},
			{83L, 14L, "Brownie Sundae", "Brownie with ice cream and chocolate sauce.", 1000, true},
			{84L, 14L, "Fruit Tart", "Seasonal fruits, custard, pastry.", 850, true},
			{85L, 15L, "Vanilla Ice Cream", "Classic vanilla ice cream scoop.", 400, true},
			{86L, 15L, "Chocolate Ice Cream", "Rich chocolate ice cream.", 400, true},
			{87L, 15L, "Strawberry Ice Cream", "Fresh strawberry ice cream.", 400, true},
			{88L, 15L, "Mango Sorbet", "Refreshing mango sorbet.", 500, true},
			{89L, 15L, "Cookies & Cream", "Ice cream with cookie chunks.", 600, true},
			{90L, 15L, "Pistachio Gelato", "Italian pistachio gelato.", 700, true},
			{91L, 16L, "Coca-Cola", "Chilled soft drink.", 400, true},
			{92L, 16L, "Orange Juice", "Freshly squeezed orange juice.", 500, true},
			{93L, 16L, "Lemonade", "Homemade lemonade.", 500, true},
			{94L, 16L, "Iced Tea", "Chilled black tea with lemon.", 450, true},
			{95L, 16L, "Sparkling Water", "Carbonated mineral water.", 400, true},
			{96L, 16L, "Apple Juice", "Fresh apple juice.", 500, true},
			{97L, 17L, "Espresso", "Strong Italian espresso.", 500, true},
			{98L, 17L, "Cappuccino", "Espresso with steamed milk foam.", 700, true},
			{99L, 17L, "Green Tea", "Hot green tea.", 600, true},
			{100L, 17L, "Latte", "Espresso with steamed milk.", 800, true},
			{101L, 17L, "Black Tea", "Classic black tea.", 500, true},
			{102L, 17L, "Iced Coffee", "Chilled coffee with milk.", 700, true},
			{103L, 18L, "Pancake Stack", "Fluffy pancakes with syrup.", 900, true},
			{104L, 18L, "Omelette", "Three-egg omelette, choice of fillings.", 1000, true},
			{105L, 18L, "French Toast", "Brioche French toast, powdered sugar.", 950, true},
			{106L, 18L, "Eggs Benedict", "Poached eggs, ham, hollandaise.", 1200, true},
			{107L, 18L, "Breakfast Burrito", "Eggs, cheese, sausage, wrap.", 1100, true},
			{108L, 18L, "Granola Bowl", "Granola, yogurt, fresh fruit.", 800, true},
			{109L, 19L, "Kids Chicken Nuggets", "Crispy chicken nuggets, fries.", 700, true},
			{110L, 19L, "Kids Mac & Cheese", "Creamy macaroni and cheese.", 600, true},
			{111L, 19L, "Kids Mini Burger", "Small beef burger, fries.", 700, true},
			{112L, 19L, "Kids Pizza", "Mini cheese pizza.", 600, true},
			{113L, 19L, "Kids Fish Fingers", "Fish fingers, fries.", 700, true},
			{114L, 19L, "Kids Pasta", "Pasta with tomato sauce.", 600, true},
			{115L, 20L, "Chef’s Special Pasta", "Daily special pasta creation.", 1500, true},
			{116L, 20L, "Market Fish", "Fresh catch of the day.", 2000, true},
			{117L, 20L, "Seasonal Salad", "Salad with seasonal ingredients.", 1200, true},
			{118L, 20L, "Special Steak", "Chef’s steak special.", 2000, true},
			{119L, 20L, "Gourmet Burger", "Specialty burger of the day.", 1600, true},
			{120L, 20L, "Dessert of the Day", "Chef’s daily dessert creation.", 1000, true}
		};
		for (Object[] mi : menuItems) {
			Long id = ((Number)mi[0]).longValue();
			Long catId = ((Number)mi[1]).longValue();
			String name = (String)mi[2];
			String desc = (String)mi[3];
			int price = ((Number)mi[4]).intValue();
			Boolean avail = (Boolean)mi[5];
			if (!menuItemRepository.existsById(id)) {
				MenuItem m = new MenuItem();
				m.setCategory(categoryRepository.findById(catId).orElse(null));
				m.setName(name);
				m.setDescription(desc);
				m.setPrice(new java.math.BigDecimal(price));
				m.setIsAvailable(avail);
				menuItemRepository.save(m);
			} else {
				// Optionally update existing menu item fields
				MenuItem existing = menuItemRepository.findById(id).orElse(null);
				if (existing != null) {
					existing.setCategory(categoryRepository.findById(catId).orElse(null));
					existing.setName(name);
					existing.setDescription(desc);
					existing.setPrice(new java.math.BigDecimal(price));
					existing.setIsAvailable(avail);
					menuItemRepository.save(existing);
				}
			}
		}

		// 8. Seed Customers (full data)
		Object[][] customers = new Object[][]{
			{1L, "Abebe Kebede", "251911234567"},
			{2L, "Almaz Bekele", "251912345678"},
			{3L, "Mulugeta Tadesse", "251913456789"},
			{4L, "Hanna Gebremedhin", "251914567890"},
			{5L, "Kebede Alemu", "251915678901"},
			{6L, "Selamawit Tesfaye", "251916789012"},
			{7L, "Birhanu Mekonnen", "251917890123"},
			{8L, "Tigist Fikre", "251918901234"},
			{9L, "Yared Asfaw", "251919012345"},
			{10L, "Saba Girma", "251920123456"},
			{11L, "Fitsum Desta", "251921234567"},
			{12L, "Mekdes Solomon", "251922345678"},
			{13L, "Samuel Getachew", "251923456789"},
			{14L, "Ruth Tsegaye", "251924567890"},
			{15L, "Eyob Worku", "251925678901"},
			{16L, "Lulit Hailu", "251926789012"},
			{17L, "Dawit Mesfin", "251927890123"},
			{18L, "Marta Abate", "251928901234"},
			{19L, "Solomon Tulu", "251929012345"},
			{20L, "Betelhem Mulu", "251930123456"},
			{21L, "Nahom Kassahun", "251931234567"},
			{22L, "Rahel Tadesse", "251932345678"},
			{23L, "Henok Gebre", "251933456789"},
			{24L, "Tsedey Alemayehu", "251934567890"},
			{25L, "Mikias Zerihun", "251935678901"}
		};
		for (Object[] cust : customers) {
			Long id = (Long)cust[0];
			String name = (String)cust[1];
			String phone = (String)cust[2];
			if (!customerRepository.existsById(id)) {
				Customer c = new Customer();
				c.setName(name);
				c.setPhone(phone);
				customerRepository.save(c);
			} else {
				// Optionally update existing customer fields
				Customer existing = customerRepository.findById(id).orElse(null);
				if (existing != null) {
					existing.setName(name);
					existing.setPhone(phone);
					customerRepository.save(existing);
				}
			}
		}

		// 9. Seed Suppliers (full data)
		Object[][] suppliers = new Object[][]{
			{1L, "Addis Food Distributors", "Mulugeta Tadesse", "251911112223", "Net 30"},
			{2L, "Ethio Fresh Produce", "Selamawit Tesfaye", "251912223334", "Net 15"},
			{3L, "Blue Nile Beverages", "Birhanu Mekonnen", "251913334445", "COD"},
			{4L, "Habesha Dairy Supply", "Tigist Fikre", "251914445556", "Net 30"},
			{5L, "Lalibela Meat Packers", "Yared Asfaw", "251915556667", "Net 45"},
			{6L, "Awash Grain Traders", "Fitsum Desta", "251916667778", "Net 30"},
			{7L, "Sheger Seafood", "Mekdes Solomon", "251917778889", "COD"},
			{8L, "Lucy Spices & Herbs", "Samuel Getachew", "251918889990", "Net 15"},
			{9L, "Axum Bakery Supplies", "Ruth Tsegaye", "251919990001", "Net 30"},
			{10L, "Dire Dawa Veggies", "Eyob Worku", "251920001112", "Net 30"},
			{11L, "Gondar Poultry Farms", "Lulit Hailu", "251921112223", "Net 45"},
			{12L, "Walia Beverage Import", "Dawit Mesfin", "251922223334", "Net 15"},
			{13L, "Entoto Cleaning Products", "Marta Abate", "251923334445", "COD"},
			{14L, "Sodore Dairy", "Solomon Tulu", "251924445556", "Net 30"},
			{15L, "Bahir Dar Oils", "Betelhem Mulu", "251925556667", "Net 30"}
		};
		for (Object[] sup : suppliers) {
			Long id = (Long)sup[0];
			String name = (String)sup[1];
			String contact = (String)sup[2];
			String phone = (String)sup[3];
			String terms = (String)sup[4];
			if (!supplierRepository.existsById(id)) {
				Supplier s = new Supplier();
				s.setName(name);
				s.setContact(contact);
				s.setPhone(phone);
				s.setTerms(terms);
				supplierRepository.save(s);
			} else {
				// Optionally update existing supplier fields
				Supplier existing = supplierRepository.findById(id).orElse(null);
				if (existing != null) {
					existing.setName(name);
					existing.setContact(contact);
					existing.setPhone(phone);
					existing.setTerms(terms);
					supplierRepository.save(existing);
				}
			}
		}

		// 10. Seed Inventory Items (full data)
		Object[][] inventoryItems = new Object[][]{
			{1L, "Beef Steak", "kg", 50, 10, 5L},
			{2L, "Chicken Breast", "kg", 60, 12, 11L},
			{3L, "Salmon Fillet", "kg", 30, 8, 7L},
			{4L, "Shrimp", "kg", 20, 5, 7L},
			{5L, "Eggs", "dozen", 100, 20, 11L},
			{6L, "Milk", "L", 80, 15, 4L},
			{7L, "Butter", "kg", 25, 5, 4L},
			{8L, "Cheddar Cheese", "kg", 18, 4, 4L},
			{9L, "Mozzarella Cheese", "kg", 22, 5, 4L},
			{10L, "Parmesan Cheese", "kg", 10, 3, 4L},
			{11L, "Tomatoes", "kg", 70, 15, 2L},
			{12L, "Lettuce", "kg", 40, 8, 2L},
			{13L, "Cucumbers", "kg", 35, 7, 2L},
			{14L, "Onions", "kg", 60, 12, 2L},
			{15L, "Potatoes", "kg", 90, 20, 2L},
			{16L, "Carrots", "kg", 50, 10, 2L},
			{17L, "Bell Peppers", "kg", 30, 6, 2L},
			{18L, "Spinach", "kg", 20, 5, 2L},
			{19L, "Broccoli", "kg", 18, 4, 2L},
			{20L, "Mushrooms", "kg", 25, 6, 2L},
			{21L, "Rice", "kg", 100, 25, 6L},
			{22L, "Pasta", "kg", 80, 20, 6L},
			{23L, "Flour", "kg", 120, 30, 9L},
			{24L, "Sugar", "kg", 60, 15, 9L},
			{25L, "Salt", "kg", 40, 10, 9L},
			{26L, "Black Pepper", "kg", 8, 2, 8L},
			{27L, "Oregano", "kg", 5, 1, 8L},
			{28L, "Basil", "kg", 5, 1, 8L},
			{29L, "Cumin", "kg", 4, 1, 8L},
			{30L, "Paprika", "kg", 4, 1, 8L},
			{31L, "Cooking Oil", "L", 60, 15, 15L},
			{32L, "Olive Oil", "L", 25, 6, 15L},
			{33L, "Butter (Baking)", "kg", 10, 2, 9L},
			{34L, "Yeast", "kg", 2, 1, 9L},
			{35L, "Baking Powder", "kg", 3, 1, 9L},
			{36L, "Vanilla Extract", "L", 1, 1, 9L},
			{37L, "Chocolate Chips", "kg", 6, 2, 9L},
			{38L, "Coffee Beans", "kg", 20, 5, 12L},
			{39L, "Tea Leaves", "kg", 15, 4, 12L},
			{40L, "Soft Drinks", "case", 30, 8, 3L},
			{41L, "Juice", "L", 40, 10, 3L},
			{42L, "Beer", "case", 25, 6, 3L},
			{43L, "Wine", "bottle", 18, 5, 3L},
			{44L, "Cleaning Detergent", "L", 20, 5, 13L},
			{45L, "Dish Soap", "L", 15, 4, 13L},
			{46L, "Paper Towels", "roll", 40, 10, 13L},
			{47L, "Napkins", "pack", 30, 8, 13L},
			{48L, "Milk (Sodore)", "L", 30, 8, 14L},
			{49L, "Yogurt", "L", 20, 5, 14L},
			{50L, "Sunflower Oil", "L", 25, 6, 15L}
		};
		for (Object[] inv : inventoryItems) {
			Long id = ((Number)inv[0]).longValue();
			String name = (String)inv[1];
			String unit = (String)inv[2];
			int qty = ((Number)inv[3]).intValue();
			int threshold = ((Number)inv[4]).intValue();
			Long supplierId = ((Number)inv[5]).longValue();
			if (!inventoryItemRepository.existsById(id)) {
				InventoryItem item = new InventoryItem();
				item.setName(name);
				item.setUnit(unit);
				item.setQuantity(new java.math.BigDecimal(qty));
				item.setThreshold(new java.math.BigDecimal(threshold));
				item.setSupplier(supplierRepository.findById(supplierId).orElse(null));
				inventoryItemRepository.save(item);
			} else {
				// Optionally update existing inventory item fields
				InventoryItem existing = inventoryItemRepository.findById(id).orElse(null);
				if (existing != null) {
					existing.setName(name);
					existing.setUnit(unit);
					existing.setQuantity(new java.math.BigDecimal(qty));
					existing.setThreshold(new java.math.BigDecimal(threshold));
					existing.setSupplier(supplierRepository.findById(supplierId).orElse(null));
					inventoryItemRepository.save(existing);
				}
			}
		}

		// 11. Seed Stock Movements (full data)
		Object[][] stockMovements = new Object[][]{
			{1L, 1L, 20, "Restock", 1L},
			{2L, 2L, 30, "Restock", 2L},
			{3L, 3L, 10, "Restock", 3L},
			{4L, 4L, 8, "Restock", 4L},
			{5L, 5L, 40, "Restock", 5L},
			{6L, 6L, 25, "Restock", 6L},
			{7L, 7L, 10, "Restock", 7L},
			{8L, 8L, 7, "Restock", 8L},
			{9L, 9L, 8, "Restock", 9L},
			{10L, 10L, 5, "Restock", 10L},
			{11L, 11L, 30, "Restock", 11L},
			{12L, 12L, 15, "Restock", 12L},
			{13L, 13L, 12, "Restock", 13L},
			{14L, 14L, 20, "Restock", 14L},
			{15L, 15L, 40, "Restock", 15L},
			{16L, 16L, 20, "Restock", 1L},
			{17L, 17L, 10, "Restock", 2L},
			{18L, 18L, 8, "Restock", 3L},
			{19L, 19L, 7, "Restock", 4L},
			{20L, 20L, 10, "Restock", 5L},
			{21L, 21L, 50, "Restock", 6L},
			{22L, 22L, 40, "Restock", 7L},
			{23L, 23L, 60, "Restock", 8L},
			{24L, 24L, 30, "Restock", 9L},
			{25L, 25L, 20, "Restock", 10L},
			{26L, 26L, 5, "Restock", 11L},
			{27L, 27L, 3, "Restock", 12L},
			{28L, 28L, 2, "Restock", 13L},
			{29L, 29L, 4, "Restock", 14L},
			{30L, 30L, 6, "Restock", 15L},
			{31L, 31L, 10, "Restock", 1L},
			{32L, 32L, 8, "Restock", 2L},
			{33L, 33L, 7, "Restock", 3L},
			{34L, 34L, 5, "Restock", 4L},
			{35L, 35L, 3, "Restock", 5L},
			{36L, 36L, 2, "Restock", 6L},
			{37L, 37L, 4, "Restock", 7L},
			{38L, 38L, 6, "Restock", 8L},
			{39L, 39L, 8, "Restock", 9L},
			{40L, 40L, 10, "Restock", 10L},
			{41L, 41L, 12, "Restock", 11L},
			{42L, 42L, 14, "Restock", 12L},
			{43L, 43L, 16, "Restock", 13L},
			{44L, 44L, 18, "Restock", 14L},
			{45L, 45L, 20, "Restock", 15L},
			{46L, 46L, 22, "Restock", 1L},
			{47L, 47L, 24, "Restock", 2L},
			{48L, 48L, 26, "Restock", 3L},
			{49L, 49L, 28, "Restock", 4L},
			{50L, 50L, 30, "Restock", 5L},
			{51L, 1L, -5, "Usage", 6L},
			{52L, 2L, -8, "Usage", 7L},
			{53L, 3L, -3, "Usage", 8L},
			{54L, 4L, -2, "Usage", 9L},
			{55L, 5L, -10, "Usage", 10L},
			{56L, 6L, -7, "Usage", 11L},
			{57L, 7L, -2, "Usage", 12L},
			{58L, 8L, -1, "Usage", 13L},
			{59L, 9L, -2, "Usage", 14L},
			{60L, 10L, -3, "Usage", 15L},
			{61L, 11L, -4, "Usage", 1L},
			{62L, 12L, -2, "Usage", 2L},
			{63L, 13L, -1, "Usage", 3L},
			{64L, 14L, -2, "Usage", 4L},
			{65L, 15L, -5, "Usage", 5L},
			{66L, 16L, -3, "Usage", 6L},
			{67L, 17L, -2, "Usage", 7L},
			{68L, 18L, -1, "Usage", 8L},
			{69L, 19L, -2, "Usage", 9L},
			{70L, 20L, -3, "Usage", 10L},
			{71L, 21L, -6, "Usage", 11L},
			{72L, 22L, -4, "Usage", 12L},
			{73L, 23L, -8, "Usage", 13L},
			{74L, 24L, -3, "Usage", 14L},
			{75L, 25L, -2, "Usage", 15L},
			{76L, 26L, -1, "Usage", 1L},
			{77L, 27L, -1, "Usage", 2L},
			{78L, 28L, -1, "Usage", 3L},
			{79L, 29L, -1, "Usage", 4L},
			{80L, 30L, -1, "Usage", 5L},
			{81L, 31L, -2, "Usage", 6L},
			{82L, 32L, -2, "Usage", 7L},
			{83L, 33L, -1, "Usage", 8L},
			{84L, 34L, -1, "Usage", 9L},
			{85L, 35L, -1, "Usage", 10L},
			{86L, 36L, -1, "Usage", 11L},
			{87L, 37L, -1, "Usage", 12L},
			{88L, 38L, -1, "Usage", 13L},
			{89L, 39L, -1, "Usage", 14L},
			{90L, 40L, -1, "Usage", 15L},
			{91L, 41L, -2, "Usage", 1L},
			{92L, 42L, -2, "Usage", 2L},
			{93L, 43L, -1, "Usage", 3L},
			{94L, 44L, -1, "Usage", 4L},
			{95L, 45L, -1, "Usage", 5L},
			{96L, 46L, -1, "Usage", 6L},
			{97L, 47L, -1, "Usage", 7L},
			{98L, 48L, -1, "Usage", 8L},
			{99L, 49L, -1, "Usage", 9L},
			{100L, 50L, -1, "Usage", 10L},
			{101L, 1L, 15, "Restock", 11L},
			{102L, 2L, 18, "Restock", 12L},
			{103L, 3L, 12, "Restock", 13L},
			{104L, 4L, 10, "Restock", 14L},
			{105L, 5L, 25, "Restock", 15L},
			{106L, 6L, 20, "Restock", 1L},
			{107L, 7L, 8, "Restock", 2L},
			{108L, 8L, 6, "Restock", 3L},
			{109L, 9L, 7, "Restock", 4L},
			{110L, 10L, 9, "Restock", 5L},
			{111L, 11L, 22, "Restock", 6L},
			{112L, 12L, 14, "Restock", 7L},
			{113L, 13L, 11, "Restock", 8L},
			{114L, 14L, 13, "Restock", 9L},
			{115L, 15L, 19, "Restock", 10L},
			{116L, 16L, 17, "Restock", 11L},
			{117L, 17L, 9, "Restock", 12L},
			{118L, 18L, 5, "Restock", 13L},
			{119L, 19L, 6, "Restock", 14L},
			{120L, 20L, 8, "Restock", 15L},
			{201L, 1L, 10, "Restock", 6L},
			{202L, 2L, 12, "Restock", 7L},
			{203L, 3L, 8, "Restock", 8L},
			{204L, 4L, 7, "Restock", 9L},
			{205L, 5L, 15, "Restock", 10L},
			{206L, 6L, 13, "Restock", 11L},
			{207L, 7L, 6, "Restock", 12L},
			{208L, 8L, 5, "Restock", 13L},
			{209L, 9L, 6, "Restock", 14L},
			{210L, 10L, 8, "Restock", 15L},
			{211L, 11L, 16, "Restock", 1L},
			{212L, 12L, 10, "Restock", 2L},
			{213L, 13L, 7, "Restock", 3L},
			{214L, 14L, 9, "Restock", 4L},
			{215L, 15L, 13, "Restock", 5L},
			{216L, 16L, 11, "Restock", 6L},
			{217L, 17L, 5, "Restock", 7L},
			{218L, 18L, 3, "Restock", 8L},
			{219L, 19L, 4, "Restock", 9L},
			{220L, 20L, 6, "Restock", 10L},
			{221L, 21L, 20, "Restock", 11L},
			{222L, 22L, 18, "Restock", 12L},
			{223L, 23L, 25, "Restock", 13L},
			{224L, 24L, 12, "Restock", 14L},
			{225L, 25L, 8, "Restock", 15L},
			{226L, 26L, 2, "Restock", 1L},
			{227L, 27L, 1, "Restock", 2L},
			{228L, 28L, 1, "Restock", 3L},
			{229L, 29L, 2, "Restock", 4L},
			{230L, 30L, 3, "Restock", 5L},
			{231L, 31L, 4, "Restock", 6L},
			{232L, 32L, 3, "Restock", 7L},
			{233L, 33L, 2, "Restock", 8L},
			{234L, 34L, 1, "Restock", 9L},
			{235L, 35L, 1, "Restock", 10L},
			{236L, 36L, 1, "Restock", 11L},
			{237L, 37L, 1, "Restock", 12L},
			{238L, 38L, 1, "Restock", 13L},
			{239L, 39L, 1, "Restock", 14L},
			{240L, 40L, 1, "Restock", 15L},
			{241L, 1L, -3, "Usage", 1L},
			{242L, 2L, -4, "Usage", 2L},
			{243L, 3L, -1, "Usage", 3L},
			{244L, 4L, -1, "Usage", 4L},
			{245L, 5L, -5, "Usage", 5L},
			{246L, 6L, -3, "Usage", 6L},
			{247L, 7L, -1, "Usage", 7L},
			{248L, 8L, -1, "Usage", 8L},
			{249L, 9L, -1, "Usage", 9L},
			{250L, 10L, -1, "Usage", 10L},
			{251L, 11L, -2, "Usage", 11L},
			{252L, 12L, -1, "Usage", 12L},
			{253L, 13L, -1, "Usage", 13L},
			{254L, 14L, -1, "Usage", 14L},
			{255L, 15L, -2, "Usage", 15L},
			{256L, 16L, -1, "Usage", 1L},
			{257L, 17L, -1, "Usage", 2L},
			{258L, 18L, -1, "Usage", 3L},
			{259L, 19L, -1, "Usage", 4L},
			{260L, 20L, -1, "Usage", 5L},
			{261L, 21L, -2, "Usage", 6L},
			{262L, 22L, -2, "Usage", 7L},
			{263L, 23L, -3, "Usage", 8L},
			{264L, 24L, -1, "Usage", 9L},
			{265L, 25L, -1, "Usage", 10L},
			{266L, 26L, -1, "Usage", 11L},
			{267L, 27L, -1, "Usage", 12L},
			{268L, 28L, -1, "Usage", 13L},
			{269L, 29L, -1, "Usage", 14L},
			{270L, 30L, -1, "Usage", 15L},
			{271L, 31L, -1, "Usage", 1L},
			{272L, 32L, -1, "Usage", 2L},
			{273L, 33L, -1, "Usage", 3L},
			{274L, 34L, -1, "Usage", 4L},
			{275L, 35L, -1, "Usage", 5L},
			{276L, 36L, -1, "Usage", 6L},
			{277L, 37L, -1, "Usage", 7L},
			{278L, 38L, -1, "Usage", 8L},
			{279L, 39L, -1, "Usage", 9L},
			{280L, 40L, -1, "Usage", 10L},
			{281L, 41L, -1, "Usage", 11L},
			{282L, 42L, -1, "Usage", 12L},
			{283L, 43L, -1, "Usage", 13L},
			{284L, 44L, -1, "Usage", 14L},
			{285L, 45L, -1, "Usage", 15L},
			{286L, 46L, -1, "Usage", 1L},
			{287L, 47L, -1, "Usage", 2L},
			{288L, 48L, -1, "Usage", 3L},
			{289L, 49L, -1, "Usage", 4L},
			{290L, 50L, -1, "Usage", 5L},
			{291L, 1L, 5, "Restock", 6L},
			{292L, 2L, 6, "Restock", 7L},
			{293L, 3L, 4, "Restock", 8L},
			{294L, 4L, 3, "Restock", 9L},
			{295L, 5L, 7, "Restock", 10L},
			{296L, 6L, 6, "Restock", 11L},
			{297L, 7L, 2, "Restock", 12L},
			{298L, 8L, 1, "Restock", 13L},
			{299L, 9L, 2, "Restock", 14L},
			{300L, 10L, 3, "Restock", 15L},
		};
		for (Object[] sm : stockMovements) {
			Long id = ((Number)sm[0]).longValue();
			Long itemId = ((Number)sm[1]).longValue();
			int qtyChange = ((Number)sm[2]).intValue();
			String reason = (String)sm[3];
			Long createdBy = ((Number)sm[4]).longValue();
			if (!stockMovementRepository.existsById(id)) {
				StockMovement movement = new StockMovement();
				movement.setInventoryItem(inventoryItemRepository.findById(itemId).orElse(null));
				movement.setQuantityChange(new java.math.BigDecimal(qtyChange));
				movement.setReason(reason);
				movement.setCreatedBy(createdBy);
				stockMovementRepository.save(movement);
			} else {
				// Optionally update existing stock movement fields
				StockMovement existing = stockMovementRepository.findById(id).orElse(null);
				if (existing != null) {
					existing.setInventoryItem(inventoryItemRepository.findById(itemId).orElse(null));
					existing.setQuantityChange(new java.math.BigDecimal(qtyChange));
					existing.setReason(reason);
					existing.setCreatedBy(createdBy);
					stockMovementRepository.save(existing);
				}
			}
		}

		// 12. Seed Branches
		Object[][] branchData = new Object[][]{
			{1L, "Bole Main Branch", "Bole Road, Addis Ababa", "251911987001"},
			{2L, "Piassa City Center", "Piassa, Churchill Ave, Addis Ababa", "251911987002"},
			{3L, "CMC Subcity Branch", "CMC Road, Addis Ababa", "251911987003"}
		};
		for (Object[] br : branchData) {
			Long id = (Long)br[0];
			String name = (String)br[1];
			String address = (String)br[2];
			String phone = (String)br[3];
			if (!branchRepository.existsById(id)) {
				Branch b = new Branch();
				b.setName(name);
				b.setAddress(address);
				b.setPhone(phone);
				branchRepository.save(b);
			} else {
				// Optionally update existing branch fields
				Branch existing = branchRepository.findById(id).orElse(null);
				if (existing != null) {
					existing.setName(name);
					existing.setAddress(address);
					existing.setPhone(phone);
					branchRepository.save(existing);
				}
			}
		}

		// 13. Seed Restaurant Tables (full data)
		Object[][] tables = new Object[][]{
			{1L, 4, "Room 1", 1, 1L},
			{2L, 2, "Room 1", 2, 1L},
			{3L, 6, "Room 2", 3, 1L},
			{4L, 4, "Room 2", 4, 1L},
			{5L, 8, "Room 3", 5, 1L},
			{6L, 2, "Room 3", 6, 1L},
			{7L, 4, "Room 4", 7, 1L},
			{8L, 6, "Room 4", 8, 1L},
			{9L, 2, "Room 5", 9, 1L},
			{10L, 4, "Room 5", 10, 1L},
			{11L, 4, "Room 6", 11, 1L},
			{12L, 6, "Room 6", 12, 1L},
			{13L, 2, "Room 7", 13, 1L},
			{14L, 4, "Room 7", 14, 1L},
			{15L, 8, "Room 8", 15, 1L},
			{16L, 2, "Room 8", 16, 1L},
			{17L, 4, "Room 9", 17, 1L},
			{18L, 6, "Room 9", 18, 1L},
			{19L, 2, "Room 10", 19, 1L},
			{20L, 4, "Room 10", 20, 1L},
			{21L, 4, "Room 1", 1, 2L},
			{22L, 2, "Room 1", 2, 2L},
			{23L, 6, "Room 2", 3, 2L},
			{24L, 4, "Room 2", 4, 2L},
			{25L, 8, "Room 3", 5, 2L},
			{26L, 2, "Room 3", 6, 2L},
			{27L, 4, "Room 4", 7, 2L},
			{28L, 6, "Room 4", 8, 2L},
			{29L, 2, "Room 5", 9, 2L},
			{30L, 4, "Room 5", 10, 2L},
			{31L, 4, "Room 6", 11, 2L},
			{32L, 6, "Room 6", 12, 2L},
			{33L, 2, "Room 7", 13, 2L},
			{34L, 4, "Room 7", 14, 2L},
			{35L, 8, "Room 8", 15, 2L},
			{36L, 2, "Room 8", 16, 2L},
			{37L, 4, "Room 9", 17, 2L},
			{38L, 6, "Room 9", 18, 2L},
			{39L, 2, "Room 10", 19, 2L},
			{40L, 4, "Room 10", 20, 2L},
			{41L, 4, "Room 1", 1, 3L},
			{42L, 2, "Room 1", 2, 3L},
			{43L, 6, "Room 2", 3, 3L},
			{44L, 4, "Room 2", 4, 3L},
			{45L, 8, "Room 3", 5, 3L},
			{46L, 2, "Room 3", 6, 3L},
			{47L, 4, "Room 4", 7, 3L},
			{48L, 6, "Room 4", 8, 3L},
			{49L, 2, "Room 5", 9, 3L},
			{50L, 4, "Room 5", 10, 3L},
			{51L, 4, "Room 6", 11, 3L},
			{52L, 6L, "Room 6", 12, 3L},
			{53L, 2, "Room 7", 13, 3L},
			{54L, 4, "Room 7", 14, 3L},
			{55L, 8, "Room 8", 15, 3L},
			{56L, 2, "Room 8", 16, 3L},
			{57L, 4, "Room 9", 17, 3L},
			{58L, 6, "Room 9", 18, 3L},
			{59L, 2, "Room 10", 19, 3L},
			{60L, 4, "Room 10", 20, 3L},
			{61L, 4, "Patio", 21, 1L},
			{62L, 2, "Patio", 22, 1L},
			{63L, 6, "Patio", 23, 1L},
			{64L, 4, "Patio", 24, 1L},
			{65L, 8, "Patio", 25, 1L},
			{66L, 2, "Patio", 26, 1L},
			{67L, 4, "Patio", 27, 1L},
			{68L, 6, "Patio", 28, 1L},
			{69L, 2, "Patio", 29, 1L},
			{70L, 4, "Patio", 30, 1L},
			{71L, 4, "Patio", 21, 2L},
			{72L, 2, "Patio", 22, 2L},
			{73L, 6, "Patio", 23, 2L},
			{74L, 4, "Patio", 24, 2L},
			{75L, 8, "Patio", 25, 2L},
			{76L, 2, "Patio", 26, 2L},
			{77L, 4, "Patio", 27, 2L},
			{78L, 6, "Patio", 28, 2L},
			{79L, 2, "Patio", 29, 2L},
			{80L, 4, "Patio", 30, 2L}
		};
		for (Object[] t : tables) {
			Long id = ((Number)t[0]).longValue();
			int capacity = ((Number)t[1]).intValue();
			String location = (String)t[2];
			int tableNum = ((Number)t[3]).intValue();
			Long branchId = ((Number)t[4]).longValue();
			if (!restaurantTableRepository.existsById(id)) {
				RestaurantTable table = new RestaurantTable();
				table.setCapacity(capacity);
				table.setLocation(location);
				table.setTableNumber(String.valueOf(tableNum));
				table.setBranch(branchRepository.findById(branchId).orElse(null));
				restaurantTableRepository.save(table);
			} else {
				// Optionally update existing table fields
				RestaurantTable existing = restaurantTableRepository.findById(id).orElse(null);
				if (existing != null) {
					existing.setCapacity(capacity);
					existing.setLocation(location);
					existing.setTableNumber(String.valueOf(tableNum));
					existing.setBranch(branchRepository.findById(branchId).orElse(null));
					restaurantTableRepository.save(existing);
				}
			}
		}
	}
}
