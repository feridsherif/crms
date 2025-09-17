# CRMS - Cloud Restaurant Management System

A cloud-based restaurant management system backend built with Spring Boot, Java 21, Maven, MySQL 8, JWT authentication, MapStruct, and Lombok.

## Project Overview
CRMS (Cloud Restaurant Management System) is designed to streamline restaurant operations for single or multi-branch businesses. It covers all core restaurant processes: branch management, inventory, menu, orders, reservations, staff, administration, and waiter requests. The system supports role-based access for admins, managers, staff, and customers, and integrates notification services for real-time updates.

## System Architecture
- **Frontend**: React + Metronic (not included in this repo)
- **Backend**: Spring Boot 6 REST API (`/api/v1/*`)
- **Database**: MySQL 8 (InnoDB)
- **Authentication**: JWT (Spring Security)
- **Notification Service**: Push/In-app notifications for order, reservation, and waiter request events

## Business Logic & Modules
### Branch Management
- Add, update, and view restaurant branches
- Each branch has its own inventory, staff, and menu

### Inventory Management
- Add/update inventory items, suppliers, and stock movements
- Track stock levels, usage, and generate inventory reports
- Automatic threshold alerts for low stock

### Menu Management
- CRUD for menu items and categories
- Menu items linked to inventory for availability
- Only available items can be ordered

### Orders
- Waiters create and update orders for tables
- Orders consist of multiple items, each linked to menu and inventory
- Order status tracked (e.g., NEW, IN_PROGRESS, SERVED, COMPLETED)
- Kitchen notified on new/updated orders

### Reservations
- Customers (via staff) can create, update, or cancel reservations
- Reservation status tracked (e.g., CONFIRMED, CANCELLED, COMPLETED)
- Notifications sent on confirmation/cancellation

### Staff Management
- Add/update/remove staff, assign roles and shifts
- Roles: Waiter, Chef, Manager, Admin, etc.
- Shift management for scheduling

### System Administration
- Manage users, roles, permissions, and audit logs
- Generate operational and audit reports

### Waiter Requests
- Customers can request waiter assistance (e.g., call waiter, request bill)
- Waiters acknowledge and resolve requests
- Real-time notifications to assigned staff

## API Overview
- RESTful endpoints under `/api/v1/`
- Example endpoints:
  - `POST /api/v1/branches` - Add branch
  - `GET /api/v1/inventory/report` - Inventory report
  - `POST /api/v1/menu` - Add menu item
  - `POST /api/v1/orders` - Create order
  - `POST /api/v1/reservations` - Create reservation
  - `POST /api/v1/staff` - Add staff
  - `POST /api/v1/admin/users` - Create user
  - `POST /api/v1/requests` - Create waiter request

## Key Entities & Relationships
- **Branch**: name, address, phone
- **InventoryItem**: name, unit, quantity, threshold, supplier
- **MenuItem**: name, category, price, availability
- **Order**: branch, table, staff, status, items
- **Reservation**: customer, table, time, status
- **Staff/User**: roles, permissions, shifts
- **WaiterRequest**: table, type, status, handledBy

## Authentication & Security
- JWT-based authentication for all endpoints
- Role-based access control (RBAC) for sensitive operations
- Passwords stored as hashes

## Notification Service
- Push/in-app notifications for:
  - New/updated orders (to kitchen/staff)
  - Reservation confirmations/cancellations (to customers/staff)
  - Waiter requests (to assigned waiters)

## Development & Contribution
1. Prerequisites: Java 21+, MySQL 8, Maven
2. Configure `src/main/resources/application.yml` for DB and JWT settings
3. Build & run:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
4. Run tests:
   ```bash
   ./mvnw test
   ```
5. Contributions welcome! Please fork, branch, and submit pull requests.

## Documentation & Diagrams
- See `/docs/` or project documentation for UML diagrams (class, sequence, DFD)
- Diagrams cover system context, module flows, and business processes

## License
[MIT License](LICENSE)