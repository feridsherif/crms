package com.hilcoe.crms.dto;

import java.math.BigDecimal;
import java.util.List;

public class InventoryReportDTO {
	public static class ItemReport {
		public Long id;
		public BigDecimal itemValue; // Optional: if price/cost is available
		public String name;
		public BigDecimal quantity;
		public List<StockMovementInfo> recentMovements;
		public SupplierInfo supplier;
		public BigDecimal threshold;
		public String unit;
	}

	public static class StockMovementInfo {
		public String date;
		public BigDecimal quantityChange;
		public String reason;
	}

	public static class SupplierInfo {
		public String contact;
		public Long id;
		public String name;
		public String phone;
		public String terms;
	}

	public List<ItemReport> items;
	public List<ItemReport> lowStockItems;
	public BigDecimal totalInventoryValue;
}
